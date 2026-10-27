package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9334;

@ModuleRegister(
   name = "HealingHelper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Подсвечивает лучший предмет для хила"
)
public class K3772938c260ac1ad93 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K3772938c260ac1ad93 instance = new K3772938c260ac1ad93();

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            int bestSlot = this.Kc445bada486518435a();
            if (bestSlot != -1) {
               this.K07405f73885e0b3869(event.context(), event.matrixStack(), bestSlot);
            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent});
   }

   private int Kc445bada486518435a() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         float currentHealth = mc.field_1724.method_6032();
         float maxHealth = mc.field_1724.method_6063();
         float missingHealth = maxHealth - currentHealth;
         if (missingHealth < 2.0F) {
            return -1;
         } else {
            int bestSlot = -1;
            int bestPriority = -1;

            for(int i = 0; i < 9; ++i) {
               class_1799 stack = mc.field_1724.method_31548().method_5438(i);
               if (!stack.method_7960()) {
                  int priority = this.K18fbdf2128d9dc344f(stack, missingHealth);
                  if (priority > bestPriority) {
                     bestPriority = priority;
                     bestSlot = i;
                  }
               }
            }

            return bestSlot;
         }
      }
   }

   private int K18fbdf2128d9dc344f(class_1799 stack, float missingHealth) {
      if (stack.method_31574(class_1802.field_8574)) {
         class_1844 potionContents = (class_1844)stack.method_57824(class_9334.field_49651);
         if (potionContents != null) {
            for(class_1293 effect : potionContents.method_57397()) {
               if (effect.method_5579() == class_1294.field_5915) {
                  int amplifier = effect.method_5578();
                  if (amplifier >= 1) {
                     if (missingHealth >= 7.0F) {
                        return 1000;
                     }

                     if (missingHealth >= 5.0F) {
                        return 500;
                     }

                     return 100;
                  }

                  if (missingHealth >= 4.0F) {
                     return 800;
                  }

                  if (missingHealth >= 3.0F) {
                     return 400;
                  }

                  return 80;
               }
            }
         }
      }

      if (stack.method_31574(class_1802.field_8367)) {
         if (missingHealth >= 6.0F) {
            return 900;
         } else {
            return missingHealth >= 4.0F ? 600 : 200;
         }
      } else if (stack.method_31574(class_1802.field_8463)) {
         if (missingHealth >= 5.0F) {
            return 700;
         } else {
            return missingHealth >= 3.0F ? 650 : 300;
         }
      } else if (stack.method_31574(class_1802.field_8071)) {
         if (missingHealth <= 3.0F) {
            return 950;
         } else {
            return missingHealth <= 5.0F ? 550 : 250;
         }
      } else {
         return 0;
      }
   }

   private void K07405f73885e0b3869(class_332 context, class_4587 matrices, int slot) {
      if (mc.method_22683() != null) {
         int screenWidth = mc.method_22683().method_4486();
         int screenHeight = mc.method_22683().method_4502();
         int slotX = screenWidth / 2 - 90 + slot * 20 + 2;
         int slotY = screenHeight - 22 + 3;
         matrices.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         Color green = new Color(0, 255, 0, 180);
         context.method_25294(slotX, slotY, slotX + 16, slotY + 16, green.getRGB());
         Color greenBorder = new Color(0, 255, 0, 255);
         context.method_25294(slotX - 1, slotY - 1, slotX + 17, slotY, greenBorder.getRGB());
         context.method_25294(slotX - 1, slotY + 16, slotX + 17, slotY + 17, greenBorder.getRGB());
         context.method_25294(slotX - 1, slotY - 1, slotX, slotY + 17, greenBorder.getRGB());
         context.method_25294(slotX + 16, slotY - 1, slotX + 17, slotY + 17, greenBorder.getRGB());
         RenderSystem.disableBlend();
         matrices.method_22909();
      }
   }

   @Generated
   public static K3772938c260ac1ad93 getInstance() {
      return instance;
   }
}
