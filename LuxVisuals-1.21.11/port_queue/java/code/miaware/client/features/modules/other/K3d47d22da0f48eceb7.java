package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.ServerRestriction;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.server.K079c3cf92902a5b0ab;
import java.awt.Color;
import java.util.regex.Pattern;
import lombok.Generated;
import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9290;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

@ServerRestriction(
   blockedServers = {K079c3cf92902a5b0ab.FUNTIME}
)
@ModuleRegister(
   name = "AucHelper",
   category = Kf76f042931183af5ba.OTHER,
   description = "Подсвечивает на аукционе предметы с Отдачей и Остротой VII"
)
public class K3d47d22da0f48eceb7 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K3d47d22da0f48eceb7 instance = new K3d47d22da0f48eceb7();
   private final K00fef91b3d4d1b3ecc knockback = (new K00fef91b3d4d1b3ecc("Подсвечивать Отдачу")).value(true);
   private final K00fef91b3d4d1b3ecc sharpness7 = (new K00fef91b3d4d1b3ecc("Подсвечивать Остроту 7")).value(true);
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(255, 59, 59, 255));
   private static final Pattern SHARP7 = Pattern.compile("острота\\s*(vii|7)(?![iv0-9])");

   public K3d47d22da0f48eceb7() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.knockback, this.sharpness7, this.color});
   }

   public void onEvent() {
   }

   public void renderSlotHighlight(class_332 ctx, class_465<?> screen, class_1735 slot) {
      if (this.isEnabled() && slot != null && slot.method_7681()) {
         if (!(slot.field_7871 instanceof class_1661)) {
            if (this.Kd6980fdd9a3b42123d(slot.method_7677())) {
               int rgb = ((Color)this.color.getValue()).getRGB() & 16777215;
               int x = slot.field_7873;
               int y = slot.field_7872;
               ctx.method_25294(x, y, x + 16, y + 16, -1610612736 | rgb);
               ctx.method_25294(x - 1, y - 1, x + 17, y + 1, -16777216 | rgb);
               ctx.method_25294(x - 1, y + 15, x + 17, y + 17, -16777216 | rgb);
               ctx.method_25294(x - 1, y + 1, x + 1, y + 15, -16777216 | rgb);
               ctx.method_25294(x + 15, y + 1, x + 17, y + 15, -16777216 | rgb);
            }
         }
      }
   }

   private boolean Kd6980fdd9a3b42123d(class_1799 stack) {
      boolean kb = (Boolean)this.knockback.getValue();
      boolean sh = (Boolean)this.sharpness7.getValue();
      if (!kb && !sh) {
         return false;
      } else if (kb && K2f08a15600c8669998(stack, class_1893.field_9121) > 0) {
         return true;
      } else if (sh && K2f08a15600c8669998(stack, class_1893.field_9118) >= 7) {
         return true;
      } else {
         class_9290 lore = (class_9290)stack.method_57824(class_9334.field_49632);
         if (lore != null) {
            for(class_2561 line : lore.comp_2400()) {
               String s = line.getString().toLowerCase();
               if (kb && s.contains("отдача")) {
                  return true;
               }

               if (sh && SHARP7.matcher(s).find()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static int K2f08a15600c8669998(class_1799 stack, class_5321<class_1887> key) {
      return Math.max(Ka777a1f25651ec2a57((class_9304)stack.method_57824(class_9334.field_49633), key), Ka777a1f25651ec2a57((class_9304)stack.method_57824(class_9334.field_49643), key));
   }

   private static int Ka777a1f25651ec2a57(class_9304 c, class_5321<class_1887> key) {
      if (c != null && !c.method_57543()) {
         for(class_6880<class_1887> e : c.method_57534()) {
            if (e.method_40225(key)) {
               return c.method_57536(e);
            }
         }

         return 0;
      } else {
         return 0;
      }
   }

   @Generated
   public static K3d47d22da0f48eceb7 getInstance() {
      return instance;
   }
}
