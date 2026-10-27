package code.miaware.client.features.modules.combat.bloodorb;

import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_9304;

public final class K22f5c502d85cadd5fa {
   private K22f5c502d85cadd5fa() {
   }

   public static float estimate(class_1657 attacker, class_1657 victim) {
      class_1799 weapon = attacker.method_6047();
      if (weapon.method_7909() instanceof class_1829 && !attacker.method_6123() && !victim.method_6039() && victim.field_6235 <= 0) {
         int sharpness = 0;
         int protection = 0;
         class_9304 enchantments = weapon.method_58657();

         for(class_6880<class_1887> enchantment : enchantments.method_57534()) {
            if (enchantment.method_40225(class_1893.field_9118)) {
               sharpness = enchantments.method_57536(enchantment);
            }
         }

         for(class_1799 armor : victim.method_5661()) {
            class_9304 enchants = armor.method_58657();

            for(class_6880<class_1887> enchantment : enchants.method_57534()) {
               if (enchantment.method_40225(class_1893.field_9111)) {
                  protection += enchants.method_57536(enchantment);
               }
            }
         }

         class_1293 resistance = victim.method_6112(class_1294.field_5907);
         float cooldown = attacker.method_7261(0.5F);
         boolean critical = attacker.field_6017 > 0.0F && !attacker.method_24828() && !attacker.method_6101() && !attacker.method_5799() && !attacker.method_6059(class_1294.field_5919) && !attacker.method_5765() && !attacker.method_5624();
         return K28e7758a0f166b30e4.damage((float)attacker.method_45325(class_5134.field_23721), cooldown, critical, sharpness, (float)victim.method_6096(), (float)victim.method_45325(class_5134.field_23725), protection, resistance == null ? 0 : resistance.method_5578() + 1);
      } else {
         return 0.0F;
      }
   }
}
