package dev.redstones.mediaplayerinfo;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0006"},
   d2 = {"Ldev/redstones/mediaplayerinfo/MediaPlayerInfo;", "", "getMediaSessions", "", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "Instance", "MediaPlayerInfo"}
)
public interface MediaPlayerInfo {
   @NotNull
   Instance Instance = MediaPlayerInfo.Instance.$$INSTANCE;

   @NotNull
   List<IMediaSession> getMediaSessions();

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096\u0001¨\u0006\u0006"},
      d2 = {"Ldev/redstones/mediaplayerinfo/MediaPlayerInfo$Instance;", "Ldev/redstones/mediaplayerinfo/MediaPlayerInfo;", "()V", "getMediaSessions", "", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "MediaPlayerInfo"}
   )
   public static final class Instance implements MediaPlayerInfo {
      // $FF: synthetic field
      static final Instance $$INSTANCE = new Instance();
      // $FF: synthetic field
      private final MediaPlayerInfo $$delegate_0 = MediaPlayerInfoKt.getSystemMediaPlayerInfo();

      private Instance() {
      }

      @NotNull
      public List<IMediaSession> getMediaSessions() {
         return this.$$delegate_0.getMediaSessions();
      }
   }
}
