package code.miaware.client.features.modules.combat.bloodorb;

import java.util.Locale;
import java.util.regex.Pattern;

public final class K28e7758a0f166b30e4 {
   public static final float HEALTH_LIMIT = 1.5F;
   private static final Pattern FORMATTING = Pattern.compile("§[0-9a-fk-orA-FK-OR]");
   private static final Pattern SPACING = Pattern.compile("\\s+");

   private K28e7758a0f166b30e4() {
   }

   public static boolean matchesName(String name) {
      return name != null && SPACING.matcher(FORMATTING.matcher(name).replaceAll("").strip()).replaceAll(" ").toLowerCase(Locale.ROOT).equals("шар крови");
   }

   public static boolean inRange(double squaredDistance) {
      return Double.isFinite(squaredDistance) && squaredDistance >= (double)0.0F && squaredDistance <= (double)225.0F;
   }

   public static boolean lowHealth(float hp) {
      return Float.isFinite(hp) && hp > 0.0F && hp <= 1.5F;
   }

   public static boolean predictedLow(float hp, float absorption, float damage) {
      return Float.isFinite(hp) && Float.isFinite(absorption) && Float.isFinite(damage) && !(damage <= 0.0F) && !(hp <= 0.0F) ? lowHealth(hp - Math.max(0.0F, damage - Math.max(0.0F, absorption))) : false;
   }

   public static boolean activationStillValid(float hp, float beforeHit, boolean predicted, boolean expired) {
      if (lowHealth(hp)) {
         return true;
      } else {
         return predicted && !expired && Float.isFinite(hp) && hp > 0.0F && Math.abs(hp - beforeHit) < 0.02F;
      }
   }

   public static float damage(float attack, float cooldown, boolean critical, int sharpness, float armor, float toughness, int protection, int resistance) {
      cooldown = Math.clamp(cooldown, 0.0F, 1.0F);
      float base = Math.max(0.0F, attack) * (0.2F + 0.8F * cooldown * cooldown);
      if (critical && cooldown > 0.9F) {
         base *= 1.5F;
      }

      float enchanted = sharpness > 0 ? (0.5F * (float)sharpness + 0.5F) * cooldown : 0.0F;
      float raw = base + enchanted;
      float effectiveArmor = Math.clamp(armor - raw / (2.0F + toughness / 4.0F), armor * 0.2F, 20.0F);
      float reduced = raw * (1.0F - effectiveArmor / 25.0F);
      reduced *= Math.max(0.0F, 1.0F - (float)resistance * 0.2F);
      return Math.max(0.0F, reduced * (1.0F - (float)Math.clamp((long)protection, 0, 20) / 25.0F));
   }
}
