param([string]$OwnerBase64, [string]$TitleBase64, [long]$PositionMs)
$ErrorActionPreference = 'Stop'
try {
    Add-Type -AssemblyName System.Runtime.WindowsRuntime
    $managerType = [Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager, Windows.Media.Control, ContentType=WindowsRuntime]
    $propertiesType = [Windows.Media.Control.GlobalSystemMediaTransportControlsSessionMediaProperties, Windows.Media.Control, ContentType=WindowsRuntime]
    $asTask = [System.WindowsRuntimeSystemExtensions].GetMethods() | Where-Object {
        $_.Name -eq 'AsTask' -and $_.IsGenericMethod -and $_.GetGenericArguments().Count -eq 1 -and
        $_.GetParameters().Count -eq 1 -and $_.GetParameters()[0].ParameterType.Name -eq 'IAsyncOperation`1'
    } | Select-Object -First 1
    function Await-Result($Operation, [Type]$ResultType) {
        $task = $asTask.MakeGenericMethod($ResultType).Invoke($null, @($Operation))
        if (!$task.Wait(2500)) { throw 'Media operation timed out' }
        return $task.Result
    }
    $owner = [Text.Encoding]::UTF8.GetString([Convert]::FromBase64String($OwnerBase64))
    $title = [Text.Encoding]::UTF8.GetString([Convert]::FromBase64String($TitleBase64))
    $manager = Await-Result ($managerType::RequestAsync()) $managerType
    foreach ($session in $manager.GetSessions()) {
        if ($session.SourceAppUserModelId -cne $owner) { continue }
        $properties = Await-Result ($session.TryGetMediaPropertiesAsync()) $propertiesType
        if ($properties.Title -cne $title) { continue }
        if (!$session.GetPlaybackInfo().Controls.IsPlaybackPositionEnabled) { break }
        $timeline = $session.GetTimelineProperties()
        $minimum = [Math]::Max($timeline.StartTime.Ticks, $timeline.MinSeekTime.Ticks)
        $maximum = [Math]::Min($timeline.EndTime.Ticks, $timeline.MaxSeekTime.Ticks)
        if ($maximum -le $minimum) { break }
        # Java positions are elapsed milliseconds; WinRT expects absolute 100 ns ticks.
        $ticks = [long][Math]::Max([decimal]$minimum, [Math]::Min([decimal]$maximum,
                [decimal]$timeline.StartTime.Ticks + [decimal]$PositionMs * 10000))
        $accepted = Await-Result ($session.TryChangePlaybackPositionAsync($ticks)) ([bool])
        Write-Output $accepted
        exit 0
    }
    Write-Output $false
} catch {
    Write-Output $false
    exit 1
}
