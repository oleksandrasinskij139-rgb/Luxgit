package code.miaware.client.util.render;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import net.minecraft.class_1799;
import net.minecraft.class_7923;
import net.minecraft.class_9282;
import net.minecraft.class_9334;

public final class K422e40c9643803bc40 {
   private static final Map<String, Integer> COLORS = load();

   private K422e40c9643803bc40() {
   }

   private static Map<String, Integer> load() {
      try {
         InputStream stream = K422e40c9643803bc40.class.getResourceAsStream("/assets/luxvisuals/data/item_colors.json");

         Map var6;
         label48: {
            try {
               if (stream == null) {
                  var6 = Map.of();
                  break label48;
               }

               var6 = (Map)(new Gson()).fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), (new TypeToken<Map<String, Integer>>() {
               }).getType());
            } catch (Throwable var4) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var3) {
                     var4.addSuppressed(var3);
                  }
               }

               throw var4;
            }

            if (stream != null) {
               stream.close();
            }

            return var6;
         }

         if (stream != null) {
            stream.close();
         }

         return var6;
      } catch (Exception var5) {
         return Map.of();
      }
   }

   public static int color(class_1799 stack, int fallback) {
      class_9282 dyed = (class_9282)stack.method_57824(class_9334.field_49644);
      return dyed != null ? -16777216 | dyed.comp_2384() : -16777216 | (Integer)COLORS.getOrDefault(class_7923.field_41178.method_10221(stack.method_7909()).toString(), fallback & 16777215);
   }
}
