package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.Kffa5f9cadb08aa2081;
import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Kfbf316195041468d42 extends K548ca8198549ddf9d7 {
   private static final ExecutorService MEDIA_WORKER = Executors.newSingleThreadExecutor((task) -> {
      Thread thread = new Thread(task, "lux-music-poll");
      thread.setDaemon(true);
      return thread;
   });
   private final Kffa5f9cadb08aa2081<String> mediaPoll;
   private String lastWindowTitle;
   private Ke4cad521398f2b2a75 mediaInfo;
   private final class_2960 artwork;
   private final Kf39dc27225f4d9fe6e lastMedia;
   private boolean artworkRegistered;
   private float exitAnimation;
   private float progressAnimation;
   private Color accentColor;
   private Color fallbackColor;
   private class_1011 currentArtworkImage;
   private long lastUpdateTime;
   private long localPosition;
   private boolean wasPlaying;
   private boolean mediaApiAvailable;
   private boolean checkedAvailability;
   private static final float CARD_W = 150.0F;
   private static final float CARD_H = 56.0F;

   public Kfbf316195041468d42() {
      super(10.0F, 250.0F);
      this.mediaPoll = new Kffa5f9cadb08aa2081<String>(MEDIA_WORKER, this::K14c32ed25b961c204f, 1000000000L);
      this.artwork = class_2960.method_60655("luxvisuals", "textures/music_artwork.png");
      this.lastMedia = new Kf39dc27225f4d9fe6e();
      this.artworkRegistered = false;
      this.exitAnimation = 0.0F;
      this.progressAnimation = 0.0F;
      this.accentColor = null;
      this.fallbackColor = new Color(100, 150, 255);
      this.currentArtworkImage = null;
      this.lastUpdateTime = 0L;
      this.localPosition = 0L;
      this.wasPlaying = false;
      this.mediaApiAvailable = true;
      this.checkedAvailability = false;
      this.setEnabled(true);
      this.mediaInfo = new Ke4cad521398f2b2a75("Unknown Track", "Unknown Artist", new byte[0], 0, 100, false);
      this.mediaApiAvailable = true;
      this.checkedAvailability = true;
   }

   public String getName() {
      return "Music Info";
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   protected String getSettingsMenuTitle() {
      return "Music Info";
   }

   private Color Ka0702001b4a1d896b8() {
      if (this.accentColor != null) {
         return this.accentColor;
      } else {
         try {
            Color themeColor = K6ea7546faa48739c0d.getInstance().getCurrentTheme().getPrimaryColor();
            if (themeColor != null) {
               return themeColor;
            }
         } catch (Exception var2) {
         }

         return this.fallbackColor;
      }
   }

   private void Kd5687211e78c8fdaa0() {
      this.mediaPoll.update(System.nanoTime(), (title) -> {
         if (title != null && !title.isEmpty()) {
            if (!title.equals(this.lastWindowTitle)) {
               this.K74585b213ae719efb7(title);
               this.lastWindowTitle = title;
            }

            this.lastMedia.reset();
         }

      });
   }

   private String K14c32ed25b961c204f() {
      try {
         String[] result = new String[]{null};
         User32.INSTANCE.EnumWindows((hWnd, arg) -> {
            char[] windowText = new char[512];
            User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
            String title = Native.toString(windowText);
            if (title.isEmpty() || !User32.INSTANCE.IsWindowVisible(hWnd) || !title.contains("SoundCloud") && !title.contains("YouTube Music") && !title.contains("YouTube") && !title.contains("Spotify") && !title.contains("Яндекс.Музыка") && !title.contains("Музыка") && !title.contains("ВКонтакте") && !title.contains("vk.com") && !title.contains("Telegram") && !title.contains("AIMP") && !title.contains("foobar") && (!title.contains("—") || title.equals("—"))) {
               return true;
            } else {
               result[0] = title;
               return false;
            }
         }, (Pointer)null);
         return result[0];
      } catch (Exception var2) {
         return null;
      }
   }

   private void K74585b213ae719efb7(String windowTitle) {
      try {
         String cleanTitle = windowTitle.replace(" - Яндекс.Браузер", "").replace(" - Яндекс Браузер", "").replace(" — Яндекс.Браузер", "").replace(" — Яндекс Браузер", "");
         if (cleanTitle.contains("Stream ") && cleanTitle.contains("SoundCloud")) {
            String[] parts = cleanTitle.split("\\|")[0].replace("Stream ", "").trim().split(" ");
            if (parts.length > 0) {
               String artistName = parts[0].trim();
               this.mediaInfo = new Ke4cad521398f2b2a75("Playing...", artistName, new byte[0], 0, 0, true);
               this.lastMedia.reset();
               return;
            }
         }

         if (cleanTitle.contains(" by ") && cleanTitle.contains("SoundCloud")) {
            String[] parts = cleanTitle.split(" \\| ")[0].split(" by ");
            if (parts.length >= 2) {
               String trackName = parts[0].trim();
               String artistName = parts[1].trim();
               this.mediaInfo = new Ke4cad521398f2b2a75(trackName, artistName, new byte[0], 0, 0, true);
               this.lastMedia.reset();
               return;
            }
         }

         if (cleanTitle.contains(" - ") && (cleanTitle.contains("YouTube") || cleanTitle.contains("Music"))) {
            String[] parts = cleanTitle.split(" - ");
            if (parts.length >= 2) {
               String trackName = parts[0].trim();
               String artistName = parts[1].trim();
               this.mediaInfo = new Ke4cad521398f2b2a75(trackName, artistName, new byte[0], 0, 0, true);
               this.lastMedia.reset();
               return;
            }
         }
      } catch (Exception var6) {
      }

   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (mc.field_1724 == null) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (!this.mediaApiAvailable) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         this.Kd5687211e78c8fdaa0();
         this.exitAnimation = 1.0F;
         this.K9c23e2bb5f434b018d(matrixStack);
      }
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   private void K9c23e2bb5f434b018d(class_4587 matrixStack) {
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      float w = this.scaled(150.0F);
      float h = this.scaled(56.0F);
      float pad = this.scaled(5.0F);
      this.getDraggable().setWidth(w);
      this.getDraggable().setHeight(h);
      Ka20f8cfdbc113ccdec.panel(matrixStack, x, y, w, h, this.scaled(8.0F), 1.0F);
      float cover = h - pad * 2.0F;
      float coverX = x + pad;
      float coverY = y + pad;
      boolean drawn = false;
      if (this.artworkRegistered && this.mediaInfo.getArtworkPng() != null && this.mediaInfo.getArtworkPng().length > 0) {
         try {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(matrixStack, coverX, coverY, cover, cover, this.scaled(5.0F), Color.WHITE, 0.0F, 0.0F, 1.0F, 1.0F, mc.method_1531().method_4619(this.artwork).method_4624());
            RenderSystem.disableBlend();
            drawn = true;
         } catch (Exception var29) {
            this.artworkRegistered = false;
         }
      }

      if (!drawn) {
         Ka20f8cfdbc113ccdec.rect(matrixStack, coverX, coverY, cover, cover, this.scaled(5.0F), Ka20f8cfdbc113ccdec.SLOT);
         Ka20f8cfdbc113ccdec.icon(matrixStack, "music", coverX + cover / 2.0F, coverY + cover / 2.0F, cover * 0.42F, Ka20f8cfdbc113ccdec.ACCENT);
      }

      float tx = coverX + cover + this.scaled(7.0F);
      float right = x + w - this.scaled(8.0F);
      float maxText = right - tx;
      String title = this.mediaInfo.getTitle() != null && !this.mediaInfo.getTitle().isEmpty() ? this.mediaInfo.getTitle() : "Unknown Track";
      String artist = this.mediaInfo.getArtist() != null && !this.mediaInfo.getArtist().isEmpty() ? this.mediaInfo.getArtist() : "Unknown Artist";
      float titleSize = this.scaled(8.4F);
      float artistSize = this.scaled(6.6F);
      Ka20f8cfdbc113ccdec.text(matrixStack, Ka20f8cfdbc113ccdec.semibold(), Ka20f8cfdbc113ccdec.ellipsize(Ka20f8cfdbc113ccdec.semibold(), title, titleSize, maxText), tx, y + this.scaled(13.5F), titleSize, Ka20f8cfdbc113ccdec.TEXT);
      Ka20f8cfdbc113ccdec.text(matrixStack, Ka20f8cfdbc113ccdec.regular(), Ka20f8cfdbc113ccdec.ellipsize(Ka20f8cfdbc113ccdec.regular(), artist, artistSize, maxText), tx, y + this.scaled(24.0F), artistSize, Ka20f8cfdbc113ccdec.MUTED);
      long duration = (long)this.mediaInfo.getDuration() * 1000L;
      long currentPos;
      if (this.mediaInfo.getPlaying()) {
         long now = System.currentTimeMillis();
         if (this.lastUpdateTime > 0L) {
            this.localPosition += now - this.lastUpdateTime;
            if (duration > 0L && this.localPosition > duration) {
               this.localPosition = duration;
            }
         }

         this.lastUpdateTime = now;
         currentPos = this.localPosition;
      } else {
         currentPos = (long)this.mediaInfo.getPosition() * 1000L;
         this.localPosition = currentPos;
         this.lastUpdateTime = System.currentTimeMillis();
      }

      float timeSize = this.scaled(5.8F);
      float timesY = y + h - this.scaled(17.5F);
      String total = this.K6be938d92dc69141dd(duration);
      Ka20f8cfdbc113ccdec.text(matrixStack, Ka20f8cfdbc113ccdec.regular(), this.K6be938d92dc69141dd(currentPos), tx, timesY, timeSize, Ka20f8cfdbc113ccdec.ACCENT_SOFT);
      Ka20f8cfdbc113ccdec.text(matrixStack, Ka20f8cfdbc113ccdec.regular(), total, right - Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.regular(), total, timeSize), timesY, timeSize, Ka20f8cfdbc113ccdec.MUTED);
      float barH = this.scaled(3.0F);
      float barY = y + h - this.scaled(10.5F);
      Ka20f8cfdbc113ccdec.rect(matrixStack, tx, barY, maxText, barH, barH / 2.0F, Ka20f8cfdbc113ccdec.TRACK);
      float progress = duration > 0L ? (float)currentPos / (float)duration : 0.0F;
      this.progressAnimation += (progress - this.progressAnimation) * 0.1F;
      if (this.progressAnimation > 0.001F) {
         float filled = Math.max(barH, maxText * Math.min(1.0F, this.progressAnimation));
         Ka20f8cfdbc113ccdec.rect(matrixStack, tx, barY, filled, barH, barH / 2.0F, Ka20f8cfdbc113ccdec.ACCENT);
      }

   }

   private String K6be938d92dc69141dd(long millis) {
      long seconds = millis / 1000L;
      long minutes = seconds / 60L;
      seconds %= 60L;
      return String.format("%d:%02d", minutes, seconds);
   }

   private String K75f5bd7e3446012fc9(String text, float fontSize, float maxWidth) {
      if (text != null && !text.isEmpty()) {
         float textWidth = this.getMediumFont().getWidth(text, fontSize);
         if (textWidth <= maxWidth) {
            return text;
         } else {
            String ellipsis = "...";
            float ellipsisWidth = this.getMediumFont().getWidth(ellipsis, fontSize);
            float availableWidth = maxWidth - ellipsisWidth;
            if (availableWidth <= 0.0F) {
               return ellipsis;
            } else {
               int left = 0;
               int right = text.length();
               String result = text;

               while(left < right) {
                  int mid = (left + right + 1) / 2;
                  String truncated = text.substring(0, mid);
                  float truncatedWidth = this.getMediumFont().getWidth(truncated, fontSize);
                  if (truncatedWidth <= availableWidth) {
                     result = truncated;
                     left = mid;
                  } else {
                     right = mid - 1;
                  }
               }

               return result + ellipsis;
            }
         }
      } else {
         return text;
      }
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      this.getDraggable().onClick(button);
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
