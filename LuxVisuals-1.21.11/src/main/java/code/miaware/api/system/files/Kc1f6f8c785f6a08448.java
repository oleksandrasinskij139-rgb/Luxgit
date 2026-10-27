package code.miaware.api.system.files;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.Collectors;
import lombok.Generated;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class Kc1f6f8c785f6a08448 implements K4600bef438dafc1a9c {
   private static final Gson GSON = new Gson();

   public static InputStream getFromAssets(String input) {
      try {
         class_2960 id = class_2960.method_60655("luxvisuals".toLowerCase(), input);
         InputStream stream = class_310.method_1551().method_1478().open(id);
         if (stream != null) {
            return stream;
         }
      } catch (Exception e) {
         System.err.println("[FileUtil] ResourceManager failed for: " + input + " - " + e.getMessage());
      }

      String var10000 = "luxvisuals".toLowerCase();
      String fullPath = "/assets/" + var10000 + "/" + input;
      InputStream stream = Kc1f6f8c785f6a08448.class.getResourceAsStream(fullPath);
      if (stream != null) {
         return stream;
      } else {
         ClassLoader var7 = Thread.currentThread().getContextClassLoader();
         String var10001 = "luxvisuals".toLowerCase();
         stream = var7.getResourceAsStream("assets/" + var10001 + "/" + input);
         if (stream != null) {
            return stream;
         } else {
            System.err.println("[FileUtil] FAILED to load resource: " + input);
            System.err.println("[FileUtil] Tried paths:");
            PrintStream var8 = System.err;
            var10001 = "luxvisuals".toLowerCase();
            var8.println("[FileUtil]   - ResourceManager: " + var10001 + ":" + input);
            System.err.println("[FileUtil]   - Class path: " + fullPath);
            var8 = System.err;
            var10001 = "luxvisuals".toLowerCase();
            var8.println("[FileUtil]   - ClassLoader: assets/" + var10001 + "/" + input);
            return null;
         }
      }
   }

   public static class_2960 getImage(String path) {
      return class_2960.method_60655("luxvisuals".toLowerCase(), "images/" + path + ".png");
   }

   public static class_2960 getShader(String name) {
      return class_2960.method_60655("luxvisuals".toLowerCase(), "core/" + name);
   }

   public static <T> T fromJsonToInstance(class_2960 identifier, Class<T> clazz) {
      return (T)GSON.fromJson(toString(identifier), clazz);
   }

   public static String toString(class_2960 identifier) {
      return toString(identifier, "\n");
   }

   public static String toString(class_2960 identifier, String delimiter) {
      try {
         InputStream inputStream = mc.method_1478().open(identifier);

         String var4;
         try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
               var4 = (String)reader.lines().collect(Collectors.joining(delimiter));
            } catch (Throwable var8) {
               try {
                  reader.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            reader.close();
         } catch (Throwable var9) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Throwable var6) {
                  var9.addSuppressed(var6);
               }
            }

            throw var9;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         return var4;
      } catch (IOException ex) {
         throw new RuntimeException(ex);
      }
   }

   @Generated
   private Kc1f6f8c785f6a08448() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
