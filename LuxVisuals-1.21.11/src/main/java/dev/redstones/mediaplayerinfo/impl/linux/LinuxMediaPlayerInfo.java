package dev.redstones.mediaplayerinfo.impl.linux;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaPlayerInfo;
import dev.redstones.mediaplayerinfo.impl.linux.dbus.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder;
import org.freedesktop.dbus.interfaces.DBus;
import org.freedesktop.dbus.interfaces.Properties;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J%\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Ldev/redstones/mediaplayerinfo/impl/linux/LinuxMediaPlayerInfo;", "Ldev/redstones/mediaplayerinfo/MediaPlayerInfo;", "()V", "conn", "Lorg/freedesktop/dbus/connections/impl/DBusConnection;", "kotlin.jvm.PlatformType", "dbus", "Lorg/freedesktop/dbus/interfaces/DBus;", "getMediaSessions", "", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "getProperty", "T", "owner", "", "property", "getProperty$MediaPlayerInfo", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "MediaPlayerInfo"}
)
@SourceDebugExtension({"SMAP\nLinuxMediaPlayerInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LinuxMediaPlayerInfo.kt\ndev/redstones/mediaplayerinfo/impl/linux/LinuxMediaPlayerInfo\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,30:1\n3792#2:31\n4307#2,2:32\n1549#3:34\n1620#3,3:35\n766#3:38\n857#3,2:39\n*S KotlinDebug\n*F\n+ 1 LinuxMediaPlayerInfo.kt\ndev/redstones/mediaplayerinfo/impl/linux/LinuxMediaPlayerInfo\n*L\n19#1:31\n19#1:32,2\n20#1:34\n20#1:35,3\n21#1:38\n21#1:39,2\n*E\n"})
public final class LinuxMediaPlayerInfo implements MediaPlayerInfo {
   @NotNull
   public static final LinuxMediaPlayerInfo INSTANCE = new LinuxMediaPlayerInfo();
   private static final DBusConnection conn = DBusConnectionBuilder.forSessionBus().build();
   private static final DBus dbus;

   private LinuxMediaPlayerInfo() {
   }

   @NotNull
   public List<IMediaSession> getMediaSessions() {
      String[] var10000 = dbus.ListNames();
      Intrinsics.checkNotNullExpressionValue(var10000, "ListNames(...)");
      Iterable $this$filter$iv = var10000;
      int $i$f$filter = 0;
      Object[] $this$filterTo$iv$iv = $this$filter$iv;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = 0;
      int var6 = 0;

      for(int var7 = ((Object[])$this$filter$iv).length; var6 < var7; ++var6) {
         Object element$iv$iv = $this$filterTo$iv$iv[var6];
         String it = (String)element$iv$iv;
         int var10 = 0;
         Intrinsics.checkNotNull(it);
         if (StringsKt.startsWith$default(it, "org.mpris.MediaPlayer2.", false, 2, (Object)null)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $this$filter$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filter = 0;
      destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10)));
      $i$f$filterTo = 0;

      for(Object item$iv$iv : $this$filter$iv) {
         String it = (String)item$iv$iv;
         int var26 = 0;
         Player var10002 = conn.getRemoteObject(it, "/org/mpris/MediaPlayer2", Player.class);
         Intrinsics.checkNotNullExpressionValue(var10002, "getRemoteObject(...)");
         var10002 = var10002;
         Intrinsics.checkNotNull(it);
         destination$iv$iv.add(new LinuxMediaSession(var10002, StringsKt.removePrefix(it, (CharSequence)"org.mpris.MediaPlayer2.")));
      }

      $this$filter$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filter = 0;
      destination$iv$iv = (Collection)(new ArrayList());
      $i$f$filterTo = 0;

      for(Object element$iv$iv : $this$filter$iv) {
         LinuxMediaSession it = (LinuxMediaSession)element$iv$iv;
         int var27 = 0;
         if (!Intrinsics.areEqual((Object)INSTANCE.getProperty$MediaPlayerInfo(it.getOwner(), "PlaybackStatus"), (Object)"Stopped")) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return (List)destination$iv$iv;
   }

   public final <T> T getProperty$MediaPlayerInfo(@NotNull String owner, @NotNull String property) {
      Intrinsics.checkNotNullParameter(owner, "owner");
      Intrinsics.checkNotNullParameter(property, "property");
      Properties properties = (Properties)conn.getRemoteObject("org.mpris.MediaPlayer2." + owner, "/org/mpris/MediaPlayer2", Properties.class);
      return (T)properties.Get("org.mpris.MediaPlayer2.Player", property);
   }

   static {
      dbus = (DBus)conn.getRemoteObject("org.freedesktop.DBus", "/", DBus.class);
   }
}
