package code.miaware.api.system.configs;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import lombok.Generated;

public class Kff3bf030da07bd80a5 {
   private static final Kff3bf030da07bd80a5 instance = new Kff3bf030da07bd80a5();
   private final GsonBuilder gson = (new GsonBuilder()).setPrettyPrinting();

   public void load(File file, List<K8855472f0415e5af4f> macros) {
      if (!file.exists()) {
         try {
            file.createNewFile();
         } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
         }
      }

      try {
         FileReader reader = new FileReader(file);

         try {
            Type type = (new TypeToken<List<K8855472f0415e5af4f>>() {
            }).getType();
            List<K8855472f0415e5af4f> loaded = (List)this.gson.create().fromJson(reader, type);
            macros.clear();
            if (loaded != null) {
               macros.addAll(loaded);
            }
         } catch (Throwable var8) {
            try {
               reader.close();
            } catch (Throwable var6) {
               var8.addSuppressed(var6);
            }

            throw var8;
         }

         reader.close();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }

   }

   public void save(File file, List<K8855472f0415e5af4f> macros) {
      File parentDir = file.getParentFile();
      if (parentDir != null) {
         parentDir.mkdirs();
      }

      if (!file.exists()) {
         try {
            file.createNewFile();
         } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
         }
      }

      try {
         FileWriter writer = new FileWriter(file);

         try {
            this.gson.create().toJson(macros, writer);
         } catch (Throwable var8) {
            try {
               writer.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         writer.close();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }

   }

   @Generated
   public static Kff3bf030da07bd80a5 getInstance() {
      return instance;
   }
}
