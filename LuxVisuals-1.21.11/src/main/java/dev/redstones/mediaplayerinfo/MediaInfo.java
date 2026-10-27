package dev.redstones.mediaplayerinfo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 62\u00020\u0001:\u000256BO\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u0011J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\t\u0010'\u001a\u00020\rHÆ\u0003JE\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010)\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010+\u001a\u00020\u0003H\u0016J\b\u0010,\u001a\u00020\u0005H\u0016J&\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203HÁ\u0001¢\u0006\u0002\b4R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013¨\u00067"},
   d2 = {"Ldev/redstones/mediaplayerinfo/MediaInfo;", "", "seen1", "", "title", "", "artist", "artworkPng", "", "position", "", "duration", "playing", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;[BJJZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;[BJJZ)V", "getArtist", "()Ljava/lang/String;", "artwork", "Ljava/awt/image/BufferedImage;", "getArtwork", "()Ljava/awt/image/BufferedImage;", "artwork$delegate", "Lkotlin/Lazy;", "getArtworkPng", "()[B", "getDuration", "()J", "getPlaying", "()Z", "getPosition", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$MediaPlayerInfo", "$serializer", "Companion", "MediaPlayerInfo"}
)
public final class MediaInfo {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String title;
   @NotNull
   private final String artist;
   @NotNull
   private final byte[] artworkPng;
   private final long position;
   private final long duration;
   private final boolean playing;
   @NotNull
   private final Lazy artwork$delegate;

   public MediaInfo(@NotNull String title, @NotNull String artist, @NotNull byte[] artworkPng, long position, long duration, boolean playing) {
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(artist, "artist");
      Intrinsics.checkNotNullParameter(artworkPng, "artworkPng");
      super();
      this.title = title;
      this.artist = artist;
      this.artworkPng = artworkPng;
      this.position = position;
      this.duration = duration;
      this.playing = playing;
      this.artwork$delegate = LazyKt.lazy(new Function0<BufferedImage>(this) {
         @Nullable
         public final BufferedImage invoke() {
            System.currentTimeMillis();

            BufferedImage var1;
            try {
               var1 = ImageIO.read((InputStream)(new ByteArrayInputStream(MediaInfo.this.getArtworkPng())));
            } catch (Exception var3) {
               var1 = null;
            }

            return var1;
         }
      });
   }

   @NotNull
   public final String getTitle() {
      return this.title;
   }

   @NotNull
   public final String getArtist() {
      return this.artist;
   }

   @NotNull
   public final byte[] getArtworkPng() {
      return this.artworkPng;
   }

   public final long getPosition() {
      return this.position;
   }

   public final long getDuration() {
      return this.duration;
   }

   public final boolean getPlaying() {
      return this.playing;
   }

   @Nullable
   public final BufferedImage getArtwork() {
      Lazy var1 = this.artwork$delegate;
      return (BufferedImage)var1.getValue();
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!Intrinsics.areEqual((Object)this.getClass(), (Object)(other != null ? other.getClass() : null))) {
         return false;
      } else {
         Intrinsics.checkNotNull(other, "null cannot be cast to non-null type dev.redstones.mediaplayerinfo.MediaInfo");
         MediaInfo var10000 = (MediaInfo)other;
         if (!Intrinsics.areEqual((Object)this.title, (Object)((MediaInfo)other).title)) {
            return false;
         } else if (!Intrinsics.areEqual((Object)this.artist, (Object)((MediaInfo)other).artist)) {
            return false;
         } else if (!Arrays.equals(this.artworkPng, ((MediaInfo)other).artworkPng)) {
            return false;
         } else if (this.position != ((MediaInfo)other).position) {
            return false;
         } else if (this.duration != ((MediaInfo)other).duration) {
            return false;
         } else {
            return this.playing == ((MediaInfo)other).playing;
         }
      }
   }

   public int hashCode() {
      int result = this.title.hashCode();
      result = 31 * result + this.artist.hashCode();
      result = 31 * result + Arrays.hashCode(this.artworkPng);
      result = 31 * result + Long.hashCode(this.position);
      result = 31 * result + Long.hashCode(this.duration);
      result = 31 * result + Boolean.hashCode(this.playing);
      return result;
   }

   @NotNull
   public String toString() {
      return "MediaInfo(title='" + this.title + "', artist='" + this.artist + "', position=" + this.position + ", duration=" + this.duration + ", playing=" + this.playing + ")";
   }

   @NotNull
   public final String component1() {
      return this.title;
   }

   @NotNull
   public final String component2() {
      return this.artist;
   }

   @NotNull
   public final byte[] component3() {
      return this.artworkPng;
   }

   public final long component4() {
      return this.position;
   }

   public final long component5() {
      return this.duration;
   }

   public final boolean component6() {
      return this.playing;
   }

   @NotNull
   public final MediaInfo copy(@NotNull String title, @NotNull String artist, @NotNull byte[] artworkPng, long position, long duration, boolean playing) {
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(artist, "artist");
      Intrinsics.checkNotNullParameter(artworkPng, "artworkPng");
      return new MediaInfo(title, artist, artworkPng, position, duration, playing);
   }

   // $FF: synthetic method
   public static MediaInfo copy$default(MediaInfo var0, String var1, String var2, byte[] var3, long var4, long var6, boolean var8, int var9, Object var10) {
      if ((var9 & 1) != 0) {
         var1 = var0.title;
      }

      if ((var9 & 2) != 0) {
         var2 = var0.artist;
      }

      if ((var9 & 4) != 0) {
         var3 = var0.artworkPng;
      }

      if ((var9 & 8) != 0) {
         var4 = var0.position;
      }

      if ((var9 & 16) != 0) {
         var6 = var0.duration;
      }

      if ((var9 & 32) != 0) {
         var8 = var0.playing;
      }

      return var0.copy(var1, var2, var3, var4, var6, var8);
   }

   // $FF: synthetic method
   @JvmStatic
   public static final void write$Self$MediaPlayerInfo(MediaInfo self, CompositeEncoder output, SerialDescriptor serialDesc) {
      output.encodeStringElement(serialDesc, 0, self.title);
      output.encodeStringElement(serialDesc, 1, self.artist);
      output.encodeSerializableElement(serialDesc, 2, ByteArraySerializer.INSTANCE, self.artworkPng);
      output.encodeLongElement(serialDesc, 3, self.position);
      output.encodeLongElement(serialDesc, 4, self.duration);
      output.encodeBooleanElement(serialDesc, 5, self.playing);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This synthesized declaration should not be used directly",
      replaceWith = @ReplaceWith(
   expression = "",
   imports = {}
),
      level = DeprecationLevel.HIDDEN
   )
   public MediaInfo(int seen1, String title, String artist, byte[] artworkPng, long position, long duration, boolean playing, SerializationConstructorMarker serializationConstructorMarker) {
      if (63 != (63 & seen1)) {
         PluginExceptionsKt.throwMissingFieldException(seen1, 63, MediaInfo.$serializer.INSTANCE.getDescriptor());
      }

      super();
      this.title = title;
      this.artist = artist;
      this.artworkPng = artworkPng;
      this.position = position;
      this.duration = duration;
      this.playing = playing;
      this.artwork$delegate = LazyKt.lazy(new Function0<BufferedImage>(this) {
         @Nullable
         public final BufferedImage invoke() {
            System.currentTimeMillis();

            BufferedImage var1;
            try {
               var1 = ImageIO.read((InputStream)(new ByteArrayInputStream(MediaInfo.this.getArtworkPng())));
            } catch (Exception var3) {
               var1 = null;
            }

            return var1;
         }
      });
   }

   /** @deprecated */
   @Deprecated(
      message = "This synthesized declaration should not be used directly",
      replaceWith = @ReplaceWith(
   expression = "",
   imports = {}
),
      level = DeprecationLevel.HIDDEN
   )
   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"},
      d2 = {"dev/redstones/mediaplayerinfo/MediaInfo.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "MediaPlayerInfo"}
   )
   public static final class $serializer implements GeneratedSerializer<MediaInfo> {
      @NotNull
      public static final $serializer INSTANCE = new $serializer();
      // $FF: synthetic field
      private static final PluginGeneratedSerialDescriptor descriptor;

      private $serializer() {
      }

      @NotNull
      public KSerializer<?>[] typeParametersSerializers() {
         return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
      }

      @NotNull
      public SerialDescriptor getDescriptor() {
         return descriptor;
      }

      @NotNull
      public KSerializer<?>[] childSerializers() {
         KSerializer[] var1 = new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, ByteArraySerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, BooleanSerializer.INSTANCE};
         return var1;
      }

      @NotNull
      public MediaInfo deserialize(@NotNull Decoder decoder) {
         Intrinsics.checkNotNullParameter(decoder, "decoder");
         SerialDescriptor var2 = this.getDescriptor();
         boolean var3 = true;
         int var5 = 0;
         String var6 = null;
         String var7 = null;
         byte[] var8 = null;
         long var9 = 0L;
         long var11 = 0L;
         boolean var13 = false;
         CompositeDecoder var14 = decoder.beginStructure(var2);
         if (var14.decodeSequentially()) {
            var6 = var14.decodeStringElement(var2, 0);
            var5 |= 1;
            var7 = var14.decodeStringElement(var2, 1);
            var5 |= 2;
            var8 = (byte[])var14.decodeSerializableElement(var2, 2, ByteArraySerializer.INSTANCE, var8);
            var5 |= 4;
            var9 = var14.decodeLongElement(var2, 3);
            var5 |= 8;
            var11 = var14.decodeLongElement(var2, 4);
            var5 |= 16;
            var13 = var14.decodeBooleanElement(var2, 5);
            var5 |= 32;
         } else {
            while(var3) {
               int var4 = var14.decodeElementIndex(var2);
               switch (var4) {
                  case -1:
                     var3 = false;
                     break;
                  case 0:
                     var6 = var14.decodeStringElement(var2, 0);
                     var5 |= 1;
                     break;
                  case 1:
                     var7 = var14.decodeStringElement(var2, 1);
                     var5 |= 2;
                     break;
                  case 2:
                     var8 = (byte[])var14.decodeSerializableElement(var2, 2, ByteArraySerializer.INSTANCE, var8);
                     var5 |= 4;
                     break;
                  case 3:
                     var9 = var14.decodeLongElement(var2, 3);
                     var5 |= 8;
                     break;
                  case 4:
                     var11 = var14.decodeLongElement(var2, 4);
                     var5 |= 16;
                     break;
                  case 5:
                     var13 = var14.decodeBooleanElement(var2, 5);
                     var5 |= 32;
                     break;
                  default:
                     throw new UnknownFieldException(var4);
               }
            }
         }

         var14.endStructure(var2);
         return new MediaInfo(var5, var6, var7, var8, var9, var11, var13, (SerializationConstructorMarker)null);
      }

      public void serialize(@NotNull Encoder encoder, @NotNull MediaInfo value) {
         Intrinsics.checkNotNullParameter(encoder, "encoder");
         Intrinsics.checkNotNullParameter(value, "value");
         SerialDescriptor var3 = this.getDescriptor();
         CompositeEncoder var4 = encoder.beginStructure(var3);
         MediaInfo.write$Self$MediaPlayerInfo(value, var4, var3);
         var4.endStructure(var3);
      }

      static {
         PluginGeneratedSerialDescriptor var0 = new PluginGeneratedSerialDescriptor("dev.redstones.mediaplayerinfo.MediaInfo", INSTANCE, 6);
         var0.addElement("title", false);
         var0.addElement("artist", false);
         var0.addElement("artworkPng", false);
         var0.addElement("position", false);
         var0.addElement("duration", false);
         var0.addElement("playing", false);
         descriptor = var0;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"},
      d2 = {"Ldev/redstones/mediaplayerinfo/MediaInfo$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "MediaPlayerInfo"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final KSerializer<MediaInfo> serializer() {
         return MediaInfo.$serializer.INSTANCE;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
