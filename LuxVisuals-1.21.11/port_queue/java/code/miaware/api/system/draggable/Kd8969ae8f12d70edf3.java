package code.miaware.api.system.draggable;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_310;
import net.minecraft.class_4587;

public class Kd8969ae8f12d70edf3 {
   private static final Kd8969ae8f12d70edf3 instance = new Kd8969ae8f12d70edf3();
   private final LinkedHashMap<String, K8b86f285a8bad749b5> draggables = new LinkedHashMap();
   private Float guideVertical;
   private Float guideHorizontal;
   private final File CONFIG_DIR;
   private final Gson GSON;
   private float guideVAlpha;
   private float guideHAlpha;
   private float lastGuideV;
   private float lastGuideH;
   private static final float INSET = 0.4F;

   public Kd8969ae8f12d70edf3() {
      this.CONFIG_DIR = new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER + "/drags.json");
      this.GSON = (new GsonBuilder()).setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
   }

   public K8b86f285a8bad749b5 create(Kab8a8c478f7d0077cd module, String name, float x, float y) {
      K8b86f285a8bad749b5 existing = (K8b86f285a8bad749b5)this.draggables.get(name);
      if (existing != null) {
         return existing;
      } else {
         K8b86f285a8bad749b5 created = new K8b86f285a8bad749b5(module, name, x, y);
         this.draggables.put(name, created);
         return created;
      }
   }

   public void updateGuides() {
      this.guideVertical = null;
      this.guideHorizontal = null;
      K8b86f285a8bad749b5 active = (K8b86f285a8bad749b5)this.draggables.values().stream().filter(K8b86f285a8bad749b5::isDragging).findFirst().orElse((Object)null);
      if (active != null && !(active.getWidth() <= 0.0F) && !(active.getHeight() <= 0.0F)) {
         class_310 mc = class_310.method_1551();
         if (mc != null && mc.method_22683() != null) {
            float snapDistance = 2.25F;
            float screenWidth = (float)mc.method_22683().method_4486();
            float screenHeight = (float)mc.method_22683().method_4502();
            float activeX = active.getX();
            float activeY = active.getY();
            float activeRight = activeX + active.getWidth();
            float activeBottom = activeY + active.getHeight();
            float activeCenterX = activeX + active.getWidth() * 0.5F;
            float activeCenterY = activeY + active.getHeight() * 0.5F;
            Float snappedX = null;
            Float snappedY = null;
            float bestXDiff = snapDistance + 1.0F;
            float bestYDiff = snapDistance + 1.0F;
            bestXDiff = this.K2df448e2d2b26de3fb(screenWidth * 0.5F, activeCenterX, active.getWidth() * 0.5F, bestXDiff, true);
            if (this.guideVertical != null) {
               snappedX = this.guideVertical - active.getWidth() * 0.5F;
            }

            bestYDiff = this.K777a94f72d31547627(screenHeight * 0.5F, activeCenterY, active.getHeight() * 0.5F, bestYDiff, true);
            if (this.guideHorizontal != null) {
               snappedY = this.guideHorizontal - active.getHeight() * 0.5F;
            }

            for(K8b86f285a8bad749b5 other : this.draggables.values()) {
               if (other != active && other.getModule().isEnabled() && !(other.getWidth() <= 0.0F) && !(other.getHeight() <= 0.0F)) {
                  float otherX = other.getX();
                  float otherY = other.getY();
                  float otherRight = otherX + other.getWidth();
                  float otherBottom = otherY + other.getHeight();
                  float otherCenterX = otherX + other.getWidth() * 0.5F;
                  float otherCenterY = otherY + other.getHeight() * 0.5F;
                  float diffLeft = Math.abs(activeX - otherX);
                  if (diffLeft < bestXDiff && diffLeft <= snapDistance) {
                     bestXDiff = diffLeft;
                     snappedX = otherX;
                     this.guideVertical = otherX;
                  }

                  float diffRight = Math.abs(activeRight - otherRight);
                  if (diffRight < bestXDiff && diffRight <= snapDistance) {
                     bestXDiff = diffRight;
                     snappedX = otherRight - active.getWidth();
                     this.guideVertical = otherRight;
                  }

                  float diffCenterX = Math.abs(activeCenterX - otherCenterX);
                  if (diffCenterX < bestXDiff && diffCenterX <= snapDistance) {
                     bestXDiff = diffCenterX;
                     snappedX = otherCenterX - active.getWidth() * 0.5F;
                     this.guideVertical = otherCenterX;
                  }

                  float diffTop = Math.abs(activeY - otherY);
                  if (diffTop < bestYDiff && diffTop <= snapDistance) {
                     bestYDiff = diffTop;
                     snappedY = otherY;
                     this.guideHorizontal = otherY;
                  }

                  float diffBottom = Math.abs(activeBottom - otherBottom);
                  if (diffBottom < bestYDiff && diffBottom <= snapDistance) {
                     bestYDiff = diffBottom;
                     snappedY = otherBottom - active.getHeight();
                     this.guideHorizontal = otherBottom;
                  }

                  float diffCenterY = Math.abs(activeCenterY - otherCenterY);
                  if (diffCenterY < bestYDiff && diffCenterY <= snapDistance) {
                     bestYDiff = diffCenterY;
                     snappedY = otherCenterY - active.getHeight() * 0.5F;
                     this.guideHorizontal = otherCenterY;
                  }
               }
            }

            if (snappedX != null) {
               active.setX(this.K4e9655519d09abd74a(snappedX));
            }

            if (snappedY != null) {
               active.setY(this.K4e9655519d09abd74a(snappedY));
            }

         }
      }
   }

   private float K2df448e2d2b26de3fb(float guide, float activeCenter, float halfWidth, float bestDiff, boolean center) {
      float diff = Math.abs(activeCenter - guide);
      if (diff < bestDiff && diff <= 2.25F) {
         this.guideVertical = guide;
         return diff;
      } else {
         return bestDiff;
      }
   }

   private float K777a94f72d31547627(float guide, float activeCenter, float halfHeight, float bestDiff, boolean center) {
      float diff = Math.abs(activeCenter - guide);
      if (diff < bestDiff && diff <= 2.25F) {
         this.guideHorizontal = guide;
         return diff;
      } else {
         return bestDiff;
      }
   }

   private float K4e9655519d09abd74a(float value) {
      return (float)Math.round(value * 2.0F) / 2.0F;
   }

   public void renderGuides(class_4587 matrices) {
      class_310 mc = class_310.method_1551();
      if (mc != null && mc.method_22683() != null) {
         float screenWidth = (float)mc.method_22683().method_4486();
         float screenHeight = (float)mc.method_22683().method_4502();
         if (this.guideVertical != null) {
            this.lastGuideV = this.guideVertical;
         }

         if (this.guideHorizontal != null) {
            this.lastGuideH = this.guideHorizontal;
         }

         this.guideVAlpha += ((this.guideVertical != null ? 1.0F : 0.0F) - this.guideVAlpha) * 0.25F;
         this.guideHAlpha += ((this.guideHorizontal != null ? 1.0F : 0.0F) - this.guideHAlpha) * 0.25F;
         if (this.guideVAlpha > 0.01F) {
            this.K5455ba6d445ffa51e2(matrices, this.lastGuideV, screenHeight, true, this.guideVAlpha);
         }

         if (this.guideHAlpha > 0.01F) {
            this.K5455ba6d445ffa51e2(matrices, this.lastGuideH, screenWidth, false, this.guideHAlpha);
         }

      }
   }

   private void K5455ba6d445ffa51e2(class_4587 ms, float pos, float length, boolean vertical, float alpha) {
      this.K1b43c32fbb614341c8(ms, pos, length, 3.4F, vertical, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, alpha * 0.18F));
      this.K1b43c32fbb614341c8(ms, pos, length, 0.8F, vertical, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT_SOFT, alpha * 0.95F));
   }

   private void K1b43c32fbb614341c8(class_4587 ms, float pos, float length, float thickness, boolean vertical, Color color) {
      Color clear = Ka20f8cfdbc113ccdec.a(color, 0.0F);
      float fade = length * 0.24F;
      float overlap = 0.6F;
      this.K276390c13af712d8d1(ms, pos, 0.0F, fade + overlap, thickness, vertical, clear, color);
      this.K276390c13af712d8d1(ms, pos, fade, length - fade * 2.0F, thickness, vertical, color, color);
      this.K276390c13af712d8d1(ms, pos, length - fade - overlap, fade + overlap, thickness, vertical, color, clear);
   }

   private void K276390c13af712d8d1(class_4587 ms, float pos, float start, float len, float thickness, boolean vertical, Color from, Color to) {
      float t = thickness + 0.8F;
      float l = len + 0.8F;
      float s = start - 0.4F;
      float p = pos - t / 2.0F;
      if (vertical) {
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, p, s, t, l, 0.0F, from, from, to, to);
      } else {
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, s, p, l, t, 0.0F, from, to, from, to);
      }

   }

   public void save() {
      if (!this.CONFIG_DIR.exists()) {
         this.CONFIG_DIR.getParentFile().mkdirs();
      }

      if (this.CONFIG_DIR.toPath().getFileSystem().isOpen()) {
         try {
            Files.writeString(this.CONFIG_DIR.toPath(), this.GSON.toJson(this.draggables));
         } catch (IOException ex) {
            ex.printStackTrace();
         }
      } else {
         System.err.println("File system closed. Could not save drag data.");
      }

   }

   public void load() {
      if (!this.CONFIG_DIR.exists()) {
         this.CONFIG_DIR.getParentFile().mkdirs();
      } else {
         try {
            String json = Files.readString(this.CONFIG_DIR.toPath());
            Map<String, K8b86f285a8bad749b5> loadedDraggables = (Map)this.GSON.fromJson(json, (new TypeToken<Map<String, K8b86f285a8bad749b5>>() {
            }).getType());
            if (loadedDraggables != null) {
               for(Map.Entry<String, K8b86f285a8bad749b5> entry : loadedDraggables.entrySet()) {
                  String name = (String)entry.getKey();
                  K8b86f285a8bad749b5 draggable = (K8b86f285a8bad749b5)entry.getValue();
                  if (draggable != null) {
                     K8b86f285a8bad749b5 currentDraggable = (K8b86f285a8bad749b5)this.draggables.get(name);
                     if (currentDraggable != null) {
                        currentDraggable.setX(draggable.getX());
                        currentDraggable.setY(draggable.getY());
                        currentDraggable.setWidgetScale(draggable.getWidgetScale());
                        this.draggables.put(name, currentDraggable);
                     }
                  }
               }
            }
         } catch (IOException ex) {
            ex.printStackTrace();
         }

      }
   }

   @Generated
   public static Kd8969ae8f12d70edf3 getInstance() {
      return instance;
   }

   @Generated
   public LinkedHashMap<String, K8b86f285a8bad749b5> getDraggables() {
      return this.draggables;
   }
}
