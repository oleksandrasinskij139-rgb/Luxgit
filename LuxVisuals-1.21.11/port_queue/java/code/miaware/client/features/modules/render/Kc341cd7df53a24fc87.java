package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K0d93ffa2e6f8c5772e;
import code.miaware.api.event.events.render.Ke3aa62f5221bb20ffb;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_1306;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

@ModuleRegister(
   name = "Hotbar",
   category = Kf76f042931183af5ba.RENDER,
   description = "Кастомный хотбар с цветом темы"
)
public class Kc341cd7df53a24fc87 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kc341cd7df53a24fc87 instance = new Kc341cd7df53a24fc87();
   private float selectedSlotX = 0.0F;
   private float targetSlotX = 0.0F;
   private static final float ANIMATION_SPEED = 0.3F;

   public void onEvent() {
      K8d7165024ff567f5e7 hotbarEvent = K0d93ffa2e6f8c5772e.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K964b3fced9a6d2add8));
      this.addEvents(new K8d7165024ff567f5e7[]{hotbarEvent});
   }

   private void K964b3fced9a6d2add8(Ke3aa62f5221bb20ffb event) {
      if (mc.field_1724 != null) {
         K0d93ffa2e6f8c5772e.getInstance().setCancel(true);
         class_332 context = event.context();
         class_4587 matrices = event.matrixStack();
         float partialTicks = event.partialTicks();
         class_1657 player = mc.field_1724;
         int screenWidth = mc.method_22683().method_4486();
         int screenHeight = mc.method_22683().method_4502();
         int centerX = screenWidth / 2;
         int hotbarY = screenHeight - 22;
         Color themeColor = Kb18d549c13309af9a5.primary();
         int bgColor = (new Color(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue(), 128)).getRGB();
         int selectedColor = (new Color(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue(), 180)).getRGB();
         int separatorColor = (new Color(0, 0, 0, 100)).getRGB();
         this.targetSlotX = (float)(centerX - 91 + player.method_31548().field_7545 * 20);
         this.selectedSlotX += (this.targetSlotX - this.selectedSlotX) * 0.3F;
         matrices.method_22903();
         K2b5e2c5f2b9891b450.RECT.draw(matrices, (float)(centerX - 91), (float)hotbarY, 182.0F, 22.0F, 2.0F, new Color(bgColor, true));

         for(int i = 1; i < 9; ++i) {
            float separatorX = (float)(centerX - 91 + i * 20);
            K2b5e2c5f2b9891b450.RECT.draw(matrices, separatorX, (float)(hotbarY + 2), 1.0F, 18.0F, 0.0F, new Color(separatorColor, true));
         }

         K2b5e2c5f2b9891b450.RECT.draw(matrices, this.selectedSlotX + 1.0F, (float)(hotbarY + 1), 20.0F, 20.0F, 2.0F, new Color(selectedColor, true));
         class_1799 offhandStack = player.method_6079();
         if (!offhandStack.method_7960()) {
            class_1306 mainArm = player.method_6068();
            int offhandX = mainArm == class_1306.field_6183 ? centerX - 91 - 29 : centerX + 91 + 7;
            K2b5e2c5f2b9891b450.RECT.draw(matrices, (float)offhandX, (float)hotbarY, 22.0F, 22.0F, 2.0F, new Color(bgColor, true));
         }

         matrices.method_22909();
         RenderSystem.enableBlend();

         for(int slot = 0; slot < 9; ++slot) {
            int itemX = centerX - 90 + slot * 20 + 2;
            int itemY = hotbarY + 3;
            class_1799 stack = (class_1799)player.method_31548().field_7547.get(slot);
            context.method_51427(stack, itemX, itemY);
            context.method_51431(mc.field_1772, stack, itemX, itemY);
         }

         if (!offhandStack.method_7960()) {
            class_1306 mainArm = player.method_6068();
            int offhandX = mainArm == class_1306.field_6183 ? centerX - 91 - 26 : centerX + 91 + 10;
            int offhandY = hotbarY + 3;
            context.method_51427(offhandStack, offhandX, offhandY);
            context.method_51431(mc.field_1772, offhandStack, offhandX, offhandY);
         }

         RenderSystem.disableBlend();
      }
   }

   @Generated
   public static Kc341cd7df53a24fc87 getInstance() {
      return instance;
   }
}
