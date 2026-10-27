package code.miaware.api.utils.player;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3612;
import net.minecraft.class_3959;
import net.minecraft.class_640;
import net.minecraft.class_9334;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class K131e64425767c5c203 implements K4600bef438dafc1a9c {
   private static final Pattern namePattern = Pattern.compile("^\\w{3,16}$");
   private static final Pattern formattingPattern = Pattern.compile("(?i)(?:[§&][0-9A-FK-OR])|(?:[§&]x(?:[§&][0-9A-F]){6})");

   public static boolean isEating() {
      return mc.field_1724.method_6115() && mc.field_1724.method_6030().method_57353().method_57832(class_9334.field_50075);
   }

   public static boolean canSee(class_243 to) {
      class_239 hitResult = mc.field_1687.method_17742(new class_3959(mc.method_1560().method_33571(), to, class_3960.field_17559, class_242.field_1348, mc.method_1560()));
      return hitResult == null || hitResult.method_17783() == class_240.field_1333;
   }

   public static boolean isAboveWater() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         return mc.field_1687 == null ? false : isAboveWater(mc.field_1724);
      }
   }

   public static boolean isAboveWater(class_1309 entity) {
      if (entity != null && mc.field_1687 != null) {
         return entity.method_5869() ? true : Kb7c18765b517cbada5(entity.method_24515(), mc.field_1687, 3);
      } else {
         return false;
      }
   }

   public static boolean isInWeb() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_238 playerBox = mc.field_1724.method_5829();
         class_2338 playerPosition = mc.field_1724.method_24515();
         return getNearbyBlockPositions(playerPosition).stream().anyMatch((pos) -> K85251d803ea8dd8421(playerBox, pos));
      }
   }

   public static boolean isInWeb(class_1309 entity) {
      if (entity == null) {
         return false;
      } else {
         class_238 entityBox = entity.method_5829();
         class_2338 entityPosition = entity.method_24515();
         return getNearbyBlockPositions(entityPosition).stream().anyMatch((pos) -> K85251d803ea8dd8421(entityBox, pos));
      }
   }

   private static boolean K85251d803ea8dd8421(class_238 playerBox, class_2338 blockPos) {
      return playerBox.method_994(new class_238(blockPos)) && mc.field_1687 != null && mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10343;
   }

   public static List<class_2338> getNearbyBlockPositions(class_2338 center) {
      List<class_2338> positions = new ArrayList();

      for(int x = center.method_10263() - 2; x <= center.method_10263() + 2; ++x) {
         for(int y = center.method_10264() - 1; y <= center.method_10264() + 4; ++y) {
            for(int z = center.method_10260() - 2; z <= center.method_10260() + 2; ++z) {
               positions.add(new class_2338(x, y, z));
            }
         }
      }

      return positions;
   }

   public static class_2248 getBlock(float x, float y, float z) {
      class_243 pos = mc.field_1724.method_19538();
      return mc.field_1687.method_8320(class_2338.method_49637(pos.field_1352 + (double)x, pos.field_1351 + (double)y, pos.field_1350 + (double)z)).method_26204();
   }

   public static boolean hasCollisionWith(class_1297 entity) {
      return hasCollisionWith(entity, 0.0F);
   }

   public static boolean hasCollisionWith(class_1297 entity, float expand) {
      class_238 box = mc.field_1724.method_5829();
      class_238 targetbox = entity.method_5829().method_1009((double)expand, (double)0.0F, (double)expand);
      return box.field_1320 > targetbox.field_1323 && box.field_1325 > targetbox.field_1322 && box.field_1324 > targetbox.field_1321 && box.field_1323 < targetbox.field_1320 && box.field_1322 < targetbox.field_1325 && box.field_1321 < targetbox.field_1324;
   }

   public static boolean isValidName(String name) {
      return namePattern.matcher(name).matches();
   }

   public static String sanitizeDisplayText(String text) {
      if (text != null && !text.isBlank()) {
         String sanitized = formattingPattern.matcher(text).replaceAll("");
         sanitized = sanitized.replace(' ', ' ').replaceAll("[\\p{Cntrl}&&[^\n\t]]", "").replaceAll("\\s{2,}", " ").trim();
         return sanitized;
      } else {
         return "";
      }
   }

   public static boolean hasLetters(String text) {
      if (text != null && !text.isBlank()) {
         for(int i = 0; i < text.length(); ++i) {
            if (Character.isLetter(text.charAt(i))) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static String resolvePrefix(class_1657 player, String name) {
      if (player == null) {
         return "";
      } else {
         String resolvedName = sanitizeDisplayText(name != null ? name : player.method_5477().getString());
         class_640 entry = mc.method_1562() != null ? mc.method_1562().method_2871(player.method_5667()) : null;
         if (entry != null && entry.method_2971() != null) {
            String display = sanitizeDisplayText(entry.method_2971().getString());
            int nameIndex = display.lastIndexOf(resolvedName);
            if (nameIndex > 0) {
               String prefix = Kd309f3ebe5648f6e21(display.substring(0, nameIndex));
               if (!prefix.isBlank()) {
                  return prefix;
               }
            }
         }

         if (player.method_5781() != null) {
            class_2561 teamPrefix = player.method_5781().method_1144();
            if (teamPrefix != null) {
               return Kd309f3ebe5648f6e21(teamPrefix.getString());
            }
         }

         return "";
      }
   }

   public static String formatPlayerWithPrefix(class_1657 player) {
      if (player == null) {
         return "";
      } else {
         String name = sanitizeDisplayText(player.method_5477().getString());
         String prefix = resolvePrefix(player, name);
         return prefix.isBlank() ? name : prefix + " " + name;
      }
   }

   private static String Kd309f3ebe5648f6e21(String prefix) {
      String sanitized = sanitizeDisplayText(prefix);
      if (sanitized.isBlank()) {
         return "";
      } else {
         boolean hasAlphaNumeric = false;

         for(int i = 0; i < sanitized.length(); ++i) {
            if (Character.isLetterOrDigit(sanitized.charAt(i))) {
               hasAlphaNumeric = true;
               break;
            }
         }

         return hasAlphaNumeric ? sanitized : sanitized.replace(" ", "");
      }
   }

   private static boolean Kb7c18765b517cbada5(class_2338 origin, class_1937 world, int maxDepth) {
      for(int depth = 1; depth <= maxDepth; ++depth) {
         class_2338 checkPos = origin.method_10087(depth);
         class_2680 state = world.method_8320(checkPos);
         if (state.method_26227().method_39360(class_3612.field_15910) || state.method_26204() == class_2246.field_10382) {
            return true;
         }

         if (!state.method_26215()) {
            return false;
         }
      }

      return false;
   }

   @Generated
   private K131e64425767c5c203() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
