package dev.redstones.mediaplayerinfo.impl.linux;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaInfo;
import dev.redstones.mediaplayerinfo.impl.linux.dbus.Player;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.freedesktop.dbus.DBusMap;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"},
   d2 = {"Ldev/redstones/mediaplayerinfo/impl/linux/LinuxMediaSession;", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "dbus", "Ldev/redstones/mediaplayerinfo/impl/linux/dbus/Player;", "owner", "", "(Ldev/redstones/mediaplayerinfo/impl/linux/dbus/Player;Ljava/lang/String;)V", "media", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "getMedia", "()Ldev/redstones/mediaplayerinfo/MediaInfo;", "getOwner", "()Ljava/lang/String;", "generateMediaInfo", "next", "", "pause", "play", "playPause", "previous", "stop", "MediaPlayerInfo"}
)
public final class LinuxMediaSession implements IMediaSession {
   @NotNull
   private final Player dbus;
   @NotNull
   private final String owner;
   @NotNull
   private final MediaInfo media;

   public LinuxMediaSession(@NotNull Player dbus, @NotNull String owner) {
      Intrinsics.checkNotNullParameter(dbus, "dbus");
      Intrinsics.checkNotNullParameter(owner, "owner");
      super();
      this.dbus = dbus;
      this.owner = owner;
      this.media = this.generateMediaInfo();
   }

   @NotNull
   public String getOwner() {
      return this.owner;
   }

   @NotNull
   public MediaInfo getMedia() {
      return this.media;
   }

   public void play() {
      this.dbus.Play();
   }

   public void pause() {
      this.dbus.Pause();
   }

   public void playPause() {
      this.dbus.PlayPause();
   }

   public void stop() {
      this.dbus.Stop();
   }

   public void next() {
      this.dbus.Next();
   }

   public void previous() {
      this.dbus.Previous();
   }

   private final MediaInfo generateMediaInfo() {
      DBusMap metadata = (DBusMap)LinuxMediaPlayerInfo.INSTANCE.getProperty$MediaPlayerInfo(this.getOwner(), "Metadata");
      boolean playing = Intrinsics.areEqual((Object)LinuxMediaPlayerInfo.INSTANCE.getProperty$MediaPlayerInfo(this.getOwner(), "PlaybackStatus"), (Object)"Playing");
      long position = (long)((Number)LinuxMediaPlayerInfo.INSTANCE.getProperty$MediaPlayerInfo(this.getOwner(), "Position")).doubleValue() / (long)1000000;
      Object var10000 = metadata.get("mpris:length");
      Intrinsics.checkNotNull(var10000);
      long duration = Long.parseLong(var10000.toString()) / (long)1000000;
      var10000 = metadata.get("xesam:title");
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.String");
      String title = (String)var10000;
      Object $this$generateMediaInfo_u24lambda_u240 = metadata.get("xesam:artist");
      int var11 = 0;
      if ($this$generateMediaInfo_u24lambda_u240 instanceof String var14) {
         ;
      } else {
         Intrinsics.checkNotNull($this$generateMediaInfo_u24lambda_u240, "null cannot be cast to non-null type kotlin.collections.List<*>");
         var14 = CollectionsKt.joinToString$default((Iterable)((List)$this$generateMediaInfo_u24lambda_u240), (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
      }

      String artist = var14;
      Object artworkUrl = metadata.get("mpris:artUrl");
      byte[] artwork = artworkUrl instanceof String ? TextStreamsKt.readBytes(new URL((String)artworkUrl)) : new byte[0];
      return new MediaInfo(title, artist, artwork, position, duration, playing);
   }
}
