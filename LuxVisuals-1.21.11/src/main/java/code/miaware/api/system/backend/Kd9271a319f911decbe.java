package code.miaware.api.system.backend;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.Generated;
import org.lwjgl.glfw.GLFW;

public final class Kd9271a319f911decbe implements K4600bef438dafc1a9c {
   public static final int SCROLL_UP = -200;
   public static final int SCROLL_DOWN = -201;
   private static final Map<String, Integer> NAME_TO_KEY = new HashMap();
   private static final Map<Integer, String> KEY_TO_NAME = new HashMap();

   public static int migrateLegacyScrollBind(int key) {
      return key == -97 ? -200 : (key == -96 ? -201 : key);
   }

   public static boolean isPressed(int keyCode) {
      if (keyCode != -1 && keyCode != -999) {
         if (keyCode != -200 && keyCode != -201) {
            if (mc != null && mc.method_22683() != null) {
               if (keyCode >= -100 && keyCode <= -93) {
                  return GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), 100 + keyCode) == 1;
               } else {
                  return GLFW.glfwGetKey(mc.method_22683().method_4490(), keyCode) == 1;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static int getBind(String s) {
      if (s != null && !s.isEmpty()) {
         if (s.equalsIgnoreCase("None")) {
            return -1;
         } else if (s.equalsIgnoreCase("ScrollUp")) {
            return -200;
         } else if (s.equalsIgnoreCase("ScrollDown")) {
            return -201;
         } else if (s.startsWith("Mouse")) {
            try {
               int mouseIndex = Integer.parseInt(s.substring(5)) - 1;
               if (mouseIndex >= 0 && mouseIndex <= 7) {
                  return -100 + mouseIndex;
               }
            } catch (NumberFormatException var2) {
            }

            return -1;
         } else {
            return (Integer)NAME_TO_KEY.getOrDefault(Ka3494bfe4102caddbf(s), -1);
         }
      } else {
         return -1;
      }
   }

   public static String getBind(int key) {
      if (key != -1 && key != -999) {
         if (key == -200) {
            return "ScrollUp";
         } else if (key == -201) {
            return "ScrollDown";
         } else {
            for(int i = 0; i <= 7; ++i) {
               if (key == -100 + i) {
                  return "Mouse" + (i + 1);
               }
            }

            String englishName = (String)KEY_TO_NAME.get(key);
            if (englishName != null) {
               return englishName;
            } else {
               return "None";
            }
         }
      } else {
         return "None";
      }
   }

   private static String Ka3494bfe4102caddbf(String input) {
      return input == null ? "" : input.replace("_", "").replace(" ", "").toLowerCase(Locale.ROOT);
   }

   private static String Kad9151361a6d4aa1a0(String input) {
      if (input != null && !input.isEmpty()) {
         if (input.startsWith("LEFT_")) {
            return "L" + K198c5d39b412d2ef7e(input.substring(5));
         } else if (input.startsWith("RIGHT_")) {
            return "R" + K198c5d39b412d2ef7e(input.substring(6));
         } else if (input.startsWith("KP_")) {
            return "Numpad" + K198c5d39b412d2ef7e(input.substring(3));
         } else {
            String var10000;
            switch (input) {
               case "PRINT_SCREEN" -> var10000 = "PrintScreen";
               case "CAPS_LOCK" -> var10000 = "CapsLock";
               case "NUM_LOCK" -> var10000 = "NumLock";
               case "PAGE_UP" -> var10000 = "PageUp";
               case "PAGE_DOWN" -> var10000 = "PageDown";
               default -> var10000 = K198c5d39b412d2ef7e(input.replace("_", ""));
            }

            return var10000;
         }
      } else {
         return input;
      }
   }

   private static String K198c5d39b412d2ef7e(String raw) {
      if (raw != null && !raw.isEmpty()) {
         String s = raw.toLowerCase();
         s = s.replace("accent", "");
         s = s.replace("control", "ctrl");
         s = s.replace("super", "Super");
         s = s.replace("minus", "Minus");
         s = s.replace("equals", "Equals");
         char var10000 = Character.toUpperCase(s.charAt(0));
         return var10000 + s.substring(1);
      } else {
         return "";
      }
   }

   @Generated
   private Kd9271a319f911decbe() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      for(Field field : GLFW.class.getDeclaredFields()) {
         if (field.getName().startsWith("GLFW_KEY_") && field.getType() == Integer.TYPE) {
            try {
               int keyCode = field.getInt((Object)null);
               String fieldName = field.getName().substring("GLFW_KEY_".length());
               String formattedName = Kad9151361a6d4aa1a0(fieldName);
               NAME_TO_KEY.putIfAbsent(Ka3494bfe4102caddbf(fieldName), keyCode);
               NAME_TO_KEY.putIfAbsent(Ka3494bfe4102caddbf(formattedName), keyCode);
               KEY_TO_NAME.putIfAbsent(keyCode, formattedName);
            } catch (IllegalAccessException var7) {
            }
         }
      }

   }
}
