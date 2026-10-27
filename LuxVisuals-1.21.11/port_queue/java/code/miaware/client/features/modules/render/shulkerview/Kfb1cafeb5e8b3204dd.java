package code.miaware.client.features.modules.render.shulkerview;

import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import code.miaware.client.features.modules.render.K6a4053265563878282;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_2371;
import net.minecraft.class_2480;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import org.joml.Vector2f;

public final class Kfb1cafeb5e8b3204dd {
   private static final int COLS = 9;
   private static final int ROWS = 3;
   private static final int SLOT_SIZE = 18;
   private static final int PANEL_PAD = 7;
   private static final int CONTENT_W = 162;
   private static final int CONTENT_H = 54;
   private static final int PANEL_W = 176;
   private static final int PANEL_H = 68;
   private static final Kfb1cafeb5e8b3204dd INSTANCE = new Kfb1cafeb5e8b3204dd();

   private Kfb1cafeb5e8b3204dd() {
   }

   public static Kfb1cafeb5e8b3204dd getInstance() {
      return INSTANCE;
   }

   public void renderHoveredShulkerPreview(class_465<?> screen, class_332 context, int mouseX, int mouseY, class_1735 slot, int screenWidth, int screenHeight) {
      K6a4053265563878282 module = K6a4053265563878282.getInstance();
      if (module.shouldRenderScreenPreview() && slot != null && slot.method_7681()) {
         class_1799 stack = slot.method_7677();
         if (isShulker(stack)) {
            class_2371<class_1799> items = getShulkerItems(stack);
            if (!K276b455704d7621cda(items)) {
               int x = mouseX + 12;
               int y = mouseY + 12;
               if (x + 176 > screenWidth) {
                  x = mouseX - 176 - 12;
               }

               if (y + 68 > screenHeight) {
                  y = screenHeight - 68 - 6;
               }

               if (x < 6) {
                  x = 6;
               }

               if (y < 6) {
                  y = 6;
               }

               this.K613a738b2b96d6fcc8(context, x, y, stack.method_7964(), items, getShulkerColorArgb(stack, 235));
            }
         }
      }
   }

   public void renderWorldShulkerPreviews(class_332 context, float tickDelta) {
      if (!K45978f900e11d6615f.getInstance().isShulkerViewerDisabled()) {
         K6a4053265563878282 module = K6a4053265563878282.getInstance();
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null && mc.field_1724 != null) {
            double maxDistSq = (double)2304.0F;

            for(class_1297 entity : mc.field_1687.method_18112()) {
               if (entity != mc.field_1724) {
                  class_1799 shulkerStack;
                  double wx;
                  double wy;
                  double wz;
                  if (entity instanceof class_1657) {
                     class_1657 player = (class_1657)entity;
                     shulkerStack = player.method_6047();
                     if (!isShulker(shulkerStack)) {
                        continue;
                     }

                     wx = Kcceb929e3bc61eb49c(entity.field_6014, entity.method_23317(), tickDelta);
                     wy = Kcceb929e3bc61eb49c(entity.field_6036, entity.method_23318(), tickDelta) + (double)player.method_17682() + (double)0.5F;
                     wz = Kcceb929e3bc61eb49c(entity.field_5969, entity.method_23321(), tickDelta);
                  } else {
                     if (!(entity instanceof class_1542)) {
                        continue;
                     }

                     class_1542 itemEntity = (class_1542)entity;
                     shulkerStack = itemEntity.method_6983();
                     if (!isShulker(shulkerStack)) {
                        continue;
                     }

                     wx = Kcceb929e3bc61eb49c(entity.field_6014, entity.method_23317(), tickDelta);
                     wy = Kcceb929e3bc61eb49c(entity.field_6036, entity.method_23318(), tickDelta) + 0.3;
                     wz = Kcceb929e3bc61eb49c(entity.field_5969, entity.method_23321(), tickDelta);
                  }

                  if (!(mc.field_1724.method_5649(wx, wy, wz) > maxDistSq)) {
                     class_2371<class_1799> items = getShulkerItems(shulkerStack);
                     if (!K276b455704d7621cda(items)) {
                        Vector2f screenPos = K8234ec92a2fab0a6d2.worldToScreen(wx, wy, wz);
                        if (screenPos != null) {
                           float sw = (float)mc.method_22683().method_4486();
                           float sh = (float)mc.method_22683().method_4502();
                           if (!(screenPos.x < -176.0F) && !(screenPos.x > sw + 176.0F) && !(screenPos.y < -68.0F) && !(screenPos.y > sh + 68.0F)) {
                              context.method_51448().method_22903();
                              context.method_51448().method_46416(screenPos.x, screenPos.y, 0.0F);
                              double distance = Math.sqrt(mc.field_1724.method_5649(wx, wy, wz));
                              float distanceFactor = (float)Math.min((double)1.0F, distance / (double)48.0F);
                              float scale = module.getScaleValue() * Math.max(0.42F, 1.0F - distanceFactor * 0.48F);
                              context.method_51448().method_22905(scale, scale, 1.0F);
                              this.K613a738b2b96d6fcc8(context, -88, -34, shulkerStack.method_7964(), items, getShulkerColorArgb(shulkerStack, 210));
                              context.method_51448().method_22909();
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   private void K613a738b2b96d6fcc8(class_332 context, int x, int y, class_2561 title, class_2371<class_1799> items, int accent) {
      class_310 mc = class_310.method_1551();
      float round = 5.0F;
      float outlineWidth = 0.5F;
      K2b5e2c5f2b9891b450.RECT.draw(context.method_51448(), (float)x - outlineWidth, (float)y - outlineWidth, 176.0F + outlineWidth * 2.0F, 68.0F + outlineWidth * 2.0F, round + outlineWidth, K87984c5639b98954c5.widgetOutline(120));
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(context.method_51448(), (float)x, (float)y, 176.0F, 68.0F, round, K87984c5639b98954c5.widgetBody(166));
      int startX = x + 7;
      int startY = y + 7;

      for(int i = 0; i < 27; ++i) {
         int slotX = startX + i % 9 * 18;
         int slotY = startY + i / 9 * 18;
         float slotRound = 3.0F;
         Color slotColor1 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 10), 80);
         Color slotColor2 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 10 + 90), 80);
         Color slotColor3 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 10 + 180), 80);
         Color slotColor4 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 10 + 270), 80);
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(context.method_51448(), (float)slotX, (float)slotY, 18.0F, 18.0F, slotRound, slotColor1, slotColor2, slotColor3, slotColor4);
         if (i % 9 < 8) {
            K2b5e2c5f2b9891b450.RECT.draw(context.method_51448(), (float)(slotX + 18) - 0.5F, (float)(slotY + 2), 0.5F, 14.0F, 0.0F, new Color(255, 255, 255, 15));
         }

         if (i / 9 < 2) {
            K2b5e2c5f2b9891b450.RECT.draw(context.method_51448(), (float)(slotX + 2), (float)(slotY + 18) - 0.5F, 14.0F, 0.5F, 0.0F, new Color(255, 255, 255, 15));
         }

         class_1799 item = (class_1799)items.get(i);
         if (!item.method_7960()) {
            context.method_51427(item, slotX + 1, slotY + 1);
            context.method_51431(mc.field_1772, item, slotX + 1, slotY + 1);
         }
      }

   }

   public static boolean isShulker(class_1799 stack) {
      return !stack.method_7960() && class_2248.method_9503(stack.method_7909()) instanceof class_2480;
   }

   public static class_2371<class_1799> getShulkerItems(class_1799 stack) {
      class_2371<class_1799> items = class_2371.method_10213(27, class_1799.field_8037);
      class_9288 container = (class_9288)stack.method_57824(class_9334.field_49622);
      if (container != null) {
         container.method_57492(items);
      }

      return items;
   }

   public static int getShulkerColorArgb(class_1799 stack, int alpha) {
      class_2248 block = class_2248.method_9503(stack.method_7909());
      if (block instanceof class_2480 shulker) {
         class_1767 color = shulker.method_10528();
         if (color != null) {
            return (alpha & 255) << 24 | color.method_7787() & 16777215;
         }
      }

      return (alpha & 255) << 24 | 9002889;
   }

   private static boolean K276b455704d7621cda(class_2371<class_1799> items) {
      return items.stream().allMatch(class_1799::method_7960);
   }

   private static int Kb961a21825964e28a7(int argb, int alpha) {
      return (alpha & 255) << 24 | argb & 16777215;
   }

   private static double Kcceb929e3bc61eb49c(double from, double to, float delta) {
      return from + (to - from) * (double)delta;
   }
}
