package code.miaware.api.system.backend;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.class_310;
import net.minecraft.class_337;
import net.minecraft.class_345;
import net.minecraft.class_638;
import net.minecraft.class_746;

public final class K8e471a90dc4db42e81 implements K4600bef438dafc1a9c {
   public static class_746 player() {
      return class_310.method_1551().field_1724;
   }

   public static class_638 world() {
      return class_310.method_1551().field_1687;
   }

   public static boolean inPvp() {
      if (mc != null && mc.field_1705 != null) {
         class_337 bossOverlayGui = mc.field_1705.method_1740();
         Map<UUID, class_345> bossBars = bossOverlayGui.field_2060;

         for(class_345 bossInfo : bossBars.values()) {
            String nameStrLower = bossInfo.method_5414().getString().toLowerCase(Locale.ROOT);
            if (nameStrLower.contains("pvp") || nameStrLower.contains("пвп")) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static boolean openFolder(String path) {
      String os = getOSName().toLowerCase();

      try {
         if (os.contains("win")) {
            Runtime.getRuntime().exec("explorer \"" + path + "\"");
            return true;
         }

         if (os.contains("mac")) {
            Runtime.getRuntime().exec(new String[]{"open", path});
            return true;
         }

         if (os.contains("lin")) {
            String[] fileManagers = new String[]{"thunar", "dolphin", "nautilus", "nemo", "pcmanfm", "caja", "konqueror"};

            for(String manager : fileManagers) {
               try {
                  Process p = Runtime.getRuntime().exec(new String[]{manager, path});
                  if (p.isAlive()) {
                     return true;
                  }
               } catch (IOException var8) {
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return false;
   }

   public static String getOSName() {
      if (!System.getProperty("java.vendor").toLowerCase().contains("android") && !System.getProperty("java.vm.vendor").toLowerCase().contains("android")) {
         String osName = System.getProperty("os.name").toLowerCase();
         if (osName.contains("win")) {
            return "Windows";
         } else if (osName.contains("mac")) {
            return "MacOS";
         } else if (osName.contains("lin")) {
            return "Linux";
         } else {
            return !osName.contains("nix") && !osName.contains("nux") && !osName.contains("aix") ? "Unknown" : "Linux/Unix";
         }
      } else {
         return "Android";
      }
   }

   @Generated
   private K8e471a90dc4db42e81() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
