package dev.redstones.mediaplayerinfo.impl.win;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u000eH\u0096 J\t\u0010\u000f\u001a\u00020\u000eH\u0096 J\t\u0010\u0010\u001a\u00020\u000eH\u0096 J\t\u0010\u0011\u001a\u00020\u000eH\u0096 J\t\u0010\u0012\u001a\u00020\u000eH\u0096 J\t\u0010\u0013\u001a\u00020\u000eH\u0096 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"},
   d2 = {"Ldev/redstones/mediaplayerinfo/impl/win/WindowsMediaSession;", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "media", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "owner", "", "index", "", "(Ldev/redstones/mediaplayerinfo/MediaInfo;Ljava/lang/String;I)V", "getMedia", "()Ldev/redstones/mediaplayerinfo/MediaInfo;", "getOwner", "()Ljava/lang/String;", "next", "", "pause", "play", "playPause", "previous", "stop", "MediaPlayerInfo"}
)
public final class WindowsMediaSession implements IMediaSession {
   @NotNull
   private final MediaInfo media;
   @NotNull
   private final String owner;
   private final int index;

   public WindowsMediaSession(@NotNull MediaInfo media, @NotNull String owner, int index) {
      Intrinsics.checkNotNullParameter(media, "media");
      Intrinsics.checkNotNullParameter(owner, "owner");
      super();
      this.media = media;
      this.owner = owner;
      this.index = index;
   }

   @NotNull
   public MediaInfo getMedia() {
      return this.media;
   }

   @NotNull
   public String getOwner() {
      return this.owner;
   }

   public native void play();

   public native void pause();

   public native void playPause();

   public native void stop();

   public native void next();

   public native void previous();
}
