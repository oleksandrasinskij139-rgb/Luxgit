package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.math.K038ab8652c7f91cee6;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1541;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Vector2f;

@ModuleRegister(
   name = "TNT Timer",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Показывает время через которое взорвётся ТНТ"
)
public class Kf57a0d374055cfebde extends Kab8a8c478f7d0077cd {
   private static final Kf57a0d374055cfebde instance = new Kf57a0d374055cfebde();

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!K45978f900e11d6615f.getInstance().isHeavyModulesOptimized()) {
            if (mc.field_1687 != null && mc.field_1724 != null) {
               class_4587 matrices = event.matrixStack();

               for(class_1297 entity : mc.field_1687.method_18112()) {
                  if (entity instanceof class_1541) {
                     class_1541 tnt = (class_1541)entity;
                     double x = class_3532.method_16436((double)event.partialTicks(), tnt.field_6014, tnt.method_23317());
                     double y = class_3532.method_16436((double)event.partialTicks(), tnt.field_6036, tnt.method_23318());
                     double z = class_3532.method_16436((double)event.partialTicks(), tnt.field_5969, tnt.method_23321());
                     Vector2f vec = K038ab8652c7f91cee6.project((double)((float)x), (double)((float)(y + (double)tnt.method_17682() + (double)0.5F)), (double)((float)z));
                     if (vec.x != Float.MAX_VALUE || vec.y != Float.MAX_VALUE) {
                        float var10000 = (float)tnt.method_6969() / 20.0F;
                        String text = Ka0484a23398fb5f9ac.formatStepValue(var10000, 0.1F) + " сек";
                        float width = K896065ea0a2bba4f47.SF_BOLD.getWidth(text, 7.0F) + 4.0F;
                        float height = K896065ea0a2bba4f47.SF_BOLD.getHeight(7.0F) + 2.0F;
                        float x1 = vec.x - width / 2.0F;
                        float y1 = vec.y;
                        K2b5e2c5f2b9891b450.RECT.draw(matrices, x1, y1, width, height, 3.0F, new Color(0, 0, 0, 128));
                        K896065ea0a2bba4f47.SF_BOLD.drawCenteredText(matrices, text, vec.x, y1 + 1.0F, 7.0F, Color.RED);
                     }
                  }
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent});
   }

   @Generated
   public static Kf57a0d374055cfebde getInstance() {
      return instance;
   }
}
