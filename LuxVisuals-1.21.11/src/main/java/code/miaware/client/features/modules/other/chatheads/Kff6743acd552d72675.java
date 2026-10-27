package code.miaware.client.features.modules.other.chatheads;

import code.miaware.client.features.modules.other.Kd9e1b5bb4ad8e12a08;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.Locale;
import java.util.regex.Pattern;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_303;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_640;
import net.minecraft.class_8685;
import net.minecraft.class_9848;

public final class Kff6743acd552d72675 {
   private static final Pattern FORMATTING_PATTERN = Pattern.compile("(?i)[\\u00A7&][0-9A-FK-OR]");
   private static final int HEAD_SIZE = 8;
   private static final int HEAD_OFFSET = 10;
   private static final int HEAD_OFFSET_WITH_SHADOW = 12;
   private static Kca2883196afc0ea01c incomingHeadData;
   private static Kca2883196afc0ea01c pendingVisibleHeadData;

   private Kff6743acd552d72675() {
   }

   public static void preparePlayerMessage(GameProfile sender, class_2561 decorated) {
      if (!K23bb47821d59c6c5ad()) {
         incomingHeadData = Kca2883196afc0ea01c.EMPTY;
      } else {
         incomingHeadData = K1495081df1ecc70724(sender, decorated);
      }
   }

   public static void prepareSystemMessage(class_2561 message, boolean overlay) {
      if (K23bb47821d59c6c5ad() && !overlay) {
         incomingHeadData = Ke239d0757d5830362f(message);
      } else {
         incomingHeadData = Kca2883196afc0ea01c.EMPTY;
      }
   }

   public static Kca2883196afc0ea01c consumeIncomingHeadData() {
      Kca2883196afc0ea01c data = incomingHeadData;
      incomingHeadData = Kca2883196afc0ea01c.EMPTY;
      return data;
   }

   public static Kca2883196afc0ea01c peekIncomingHeadData() {
      return K2397488ff10af11877(incomingHeadData);
   }

   public static void clearIncomingHeadData() {
      incomingHeadData = Kca2883196afc0ea01c.EMPTY;
   }

   public static void beginVisibleMessage(Kca2883196afc0ea01c data) {
      pendingVisibleHeadData = K2397488ff10af11877(data);
   }

   public static Kca2883196afc0ea01c getPendingVisibleHeadData() {
      return K2397488ff10af11877(pendingVisibleHeadData);
   }

   public static void endVisibleMessage() {
      pendingVisibleHeadData = Kca2883196afc0ea01c.EMPTY;
   }

   public static Kca2883196afc0ea01c getHeadData(Object renderable) {
      if (renderable instanceof Kd7ebad26b0611ce013 carrier) {
         return K2397488ff10af11877(carrier.miaware$getChatHeadData());
      } else {
         return Kca2883196afc0ea01c.EMPTY;
      }
   }

   public static void setHeadData(Object renderable, Kca2883196afc0ea01c data) {
      if (renderable instanceof Kd7ebad26b0611ce013 carrier) {
         carrier.miaware$setChatHeadData(K2397488ff10af11877(data));
      }

   }

   public static int getChatOffset(class_303.class_7590 line) {
      return 0;
   }

   public static void renderHead(class_332 context, int x, int y, class_303.class_7590 line, float alpha) {
      Kd9e1b5bb4ad8e12a08 module = Kd9e1b5bb4ad8e12a08.getInstance();
      if (module != null && module.isEnabled()) {
         Kca2883196afc0ea01c data = getHeadData(line);
         if (!data.isEmpty()) {
            class_640 playerInfo = data.playerInfo();
            if (playerInfo != null) {
               int color = class_9848.method_61324(class_3532.method_15340(Math.round(alpha * 255.0F), 0, 255), 255, 255, 255);
               int headX = x + 2;
               if ((Boolean)module.getShadow().getValue()) {
                  int shadowColor = class_9848.method_61324(class_3532.method_15340(Math.round(alpha * 92.0F), 0, 255), 0, 0, 0);
                  K060d880b8736b25484(context, playerInfo, headX + 1, y + 1, shadowColor);
               }

               K060d880b8736b25484(context, playerInfo, headX, y, color);
            }
         }
      }
   }

   private static void K060d880b8736b25484(class_332 context, class_640 playerInfo, int x, int y, int color) {
      class_8685 skinTextures = playerInfo.method_52810();
      class_2960 texture = skinTextures.comp_1626();
      context.method_25291(class_1921::method_62277, texture, x, y, 8.0F, 8.0F, 8, 8, 64, 64, color);
      if (playerInfo.method_65195()) {
         context.method_25291(class_1921::method_62277, texture, x, y, 40.0F, 8.0F, 8, 8, 64, 64, color);
      }

   }

   private static Kca2883196afc0ea01c K1495081df1ecc70724(GameProfile sender, class_2561 decorated) {
      class_310 mc = class_310.method_1551();
      class_640 playerInfo = null;
      if (mc.method_1562() != null && sender != null) {
         if (sender.getId() != null) {
            playerInfo = mc.method_1562().method_2871(sender.getId());
         }

         if (playerInfo == null && sender.getName() != null && !sender.getName().isBlank()) {
            playerInfo = mc.method_1562().method_2874(sender.getName());
         }
      }

      if (playerInfo == null) {
         playerInfo = K5ecf1bc59407a4448d(decorated);
      }

      return Kca2883196afc0ea01c.of(playerInfo);
   }

   private static Kca2883196afc0ea01c Ke239d0757d5830362f(class_2561 message) {
      return Kca2883196afc0ea01c.of(K5ecf1bc59407a4448d(message));
   }

   private static class_640 K5ecf1bc59407a4448d(class_2561 message) {
      class_310 mc = class_310.method_1551();
      if (mc.method_1562() != null && message != null) {
         String normalizedText = Kab481eac2ffebb9691(message.getString());
         if (normalizedText.isBlank()) {
            return null;
         } else {
            Collection<class_640> entries = mc.method_1562().method_45732();
            if (entries == null || entries.isEmpty()) {
               entries = mc.method_1562().method_2880();
            }

            class_640 bestEntry = null;
            int bestScore = Integer.MIN_VALUE;

            for(class_640 entry : entries) {
               int score = Kf7714a2156afab3bc4(normalizedText, entry);
               if (score > bestScore) {
                  bestScore = score;
                  bestEntry = entry;
               }
            }

            return bestScore >= 120 ? bestEntry : null;
         }
      } else {
         return null;
      }
   }

   private static int Kf7714a2156afab3bc4(String text, class_640 entry) {
      if (entry != null && entry.method_2966() != null && entry.method_2966().getName() != null) {
         int best = K5128066c1d6333d3ba(text, entry.method_2966().getName());
         if (entry.method_2971() != null) {
            best = Math.max(best, K5128066c1d6333d3ba(text, entry.method_2971().getString()));
         }

         return best;
      } else {
         return Integer.MIN_VALUE;
      }
   }

   private static int K5128066c1d6333d3ba(String text, String candidate) {
      String normalizedCandidate = Kab481eac2ffebb9691(candidate);
      if (normalizedCandidate.isBlank()) {
         return Integer.MIN_VALUE;
      } else {
         int index = text.indexOf(normalizedCandidate);
         if (index >= 0 && index <= 28) {
            int score = 220 - index * 9 + Math.min(normalizedCandidate.length() * 3, 42);
            if (index == 0) {
               score += 80;
            }

            char before = index > 0 ? text.charAt(index - 1) : 32;
            char after = index + normalizedCandidate.length() < text.length() ? text.charAt(index + normalizedCandidate.length()) : 32;
            if (index != 0 && !K77acc1013cadc16a39(before)) {
               score -= 40;
            } else {
               score += 28;
            }

            if (Ka17bc34b3f496cc181(after)) {
               score += 36;
            } else if (!Character.isWhitespace(after)) {
               score -= 26;
            }

            return score;
         } else {
            return Integer.MIN_VALUE;
         }
      }
   }

   private static boolean K77acc1013cadc16a39(char character) {
      return Character.isWhitespace(character) || character == '[' || character == '(' || character == '<' || character == '{' || character == '|';
   }

   private static boolean Ka17bc34b3f496cc181(char character) {
      return Character.isWhitespace(character) || character == ':' || character == '>' || character == ']' || character == ')' || character == '|' || character == '-' || character == 187 || character == 8250;
   }

   private static boolean K23bb47821d59c6c5ad() {
      Kd9e1b5bb4ad8e12a08 module = Kd9e1b5bb4ad8e12a08.getInstance();
      return module != null && module.isEnabled();
   }

   private static Kca2883196afc0ea01c K2397488ff10af11877(Kca2883196afc0ea01c data) {
      return data == null ? Kca2883196afc0ea01c.EMPTY : data;
   }

   private static String Kab481eac2ffebb9691(String text) {
      return text == null ? "" : FORMATTING_PATTERN.matcher(text).replaceAll("").toLowerCase(Locale.ROOT).replace('ё', 'е').replace('\n', ' ').replace('\r', ' ').replace(' ', ' ').replaceAll("\\s+", " ").trim();
   }

   static {
      incomingHeadData = Kca2883196afc0ea01c.EMPTY;
      pendingVisibleHeadData = Kca2883196afc0ea01c.EMPTY;
   }
}
