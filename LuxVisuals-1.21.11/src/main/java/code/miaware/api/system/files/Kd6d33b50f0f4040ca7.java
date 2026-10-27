package code.miaware.api.system.files;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class Kd6d33b50f0f4040ca7 {
   private List<String> data = new ArrayList();
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

   public abstract String fileName();

   private Path Ka4a7a74c72e9df1d79() {
      return Path.of(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, this.fileName() + ".json");
   }

   public void save() {
      try {
         Files.createDirectories(Path.of(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER));
         FileWriter writer = new FileWriter(this.Ka4a7a74c72e9df1d79().toFile());

         try {
            GSON.toJson(this.data, writer);
         } catch (Throwable var5) {
            try {
               writer.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         writer.close();
      } catch (IOException e) {
         throw new RuntimeException("Failed to save: " + String.valueOf(this.Ka4a7a74c72e9df1d79()), e);
      }
   }

   public void load() {
      try {
         Files.createDirectories(Path.of(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER));
         File file = this.Ka4a7a74c72e9df1d79().toFile();
         if (!file.exists()) {
            file.createNewFile();
            this.save();
            return;
         }

         FileReader reader = new FileReader(file);

         try {
            Type listType = (new TypeToken<ArrayList<String>>() {
            }).getType();
            this.data = (List)GSON.fromJson(reader, listType);
            if (this.data == null) {
               this.data = new ArrayList();
            }
         } catch (Throwable var6) {
            try {
               reader.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         reader.close();
      } catch (IOException var7) {
         this.data = new ArrayList();
      }

   }

   public void add(String value) {
      if (value != null && !value.trim().isEmpty() && !this.data.contains(value)) {
         this.data.add(value);
         this.save();
      }

   }

   public boolean remove(String value) {
      boolean b = this.data.remove(value);
      if (b) {
         this.save();
      }

      return b;
   }

   public List<String> getData() {
      return new ArrayList(this.data);
   }

   public void clear() {
      this.data.clear();
      this.save();
   }

   public boolean contains(String value) {
      return this.data.contains(value);
   }

   public int size() {
      return this.data.size();
   }
}
