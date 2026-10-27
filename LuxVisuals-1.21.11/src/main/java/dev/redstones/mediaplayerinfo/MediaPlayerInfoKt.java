package dev.redstones.mediaplayerinfo;

import dev.redstones.mediaplayerinfo.impl.DummyMediaPlayerInfo;
import dev.redstones.mediaplayerinfo.impl.linux.LinuxMediaPlayerInfo;
import dev.redstones.mediaplayerinfo.impl.win.WindowsMediaPlayerInfo;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
   d2 = {"systemMediaPlayerInfo", "Ldev/redstones/mediaplayerinfo/MediaPlayerInfo;", "getSystemMediaPlayerInfo", "()Ldev/redstones/mediaplayerinfo/MediaPlayerInfo;", "MediaPlayerInfo"}
)
public final class MediaPlayerInfoKt {
   @NotNull
   private static final MediaPlayerInfo systemMediaPlayerInfo;

   @NotNull
   public static final MediaPlayerInfo getSystemMediaPlayerInfo() {
      return systemMediaPlayerInfo;
   }

   static {
      String var10000 = System.getProperty("os.name");
      Intrinsics.checkNotNullExpressionValue(var10000, "getProperty(...)");
      var10000 = var10000.toLowerCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
      MediaPlayerInfo var1;
      if (StringsKt.startsWith$default(var10000, "windows", false, 2, (Object)null)) {
         var1 = WindowsMediaPlayerInfo.INSTANCE;
      } else {
         String var2 = System.getProperty("os.name");
         Intrinsics.checkNotNullExpressionValue(var2, "getProperty(...)");
         var2 = var2.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var2, "toLowerCase(...)");
         var1 = Intrinsics.areEqual((Object)var2, (Object)"linux") ? (MediaPlayerInfo)LinuxMediaPlayerInfo.INSTANCE : (MediaPlayerInfo)DummyMediaPlayerInfo.INSTANCE;
      }

      systemMediaPlayerInfo = var1;
   }
}
