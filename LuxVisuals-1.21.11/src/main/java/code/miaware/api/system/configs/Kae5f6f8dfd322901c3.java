package code.miaware.api.system.configs;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Kae5f6f8dfd322901c3 implements K4600bef438dafc1a9c {
   private static final Kae5f6f8dfd322901c3 instance = new Kae5f6f8dfd322901c3();
   private final File file;
   private final List<K8855472f0415e5af4f> macros;

   public Kae5f6f8dfd322901c3() {
      this.file = new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER + "/macros.json");
      this.macros = new ArrayList();
   }

   public void load() {
      Kff3bf030da07bd80a5.getInstance().load(this.file, this.macros);
   }

   public void save() {
      Kff3bf030da07bd80a5.getInstance().save(this.file, this.macros);
   }

   public void add(String name, String message, int key) {
      this.macros.add(new K8855472f0415e5af4f(name, message, key));
      this.save();
   }

   public void remove(String name) {
      this.macros.removeIf((m) -> m.getName().equalsIgnoreCase(name));
      this.save();
   }

   public boolean has(String name) {
      return this.macros.stream().anyMatch((m) -> m.getName().equalsIgnoreCase(name));
   }

   public void clear() {
      this.macros.clear();
      this.save();
   }

   public void onKeyPressed(int key) {
      if (mc.field_1724 != null) {
         this.macros.stream().filter((m) -> m.getKey() == key).forEach((m) -> {
            if (m.message.startsWith("/")) {
               mc.field_1724.field_3944.method_45730(m.message.substring(1));
            } else {
               mc.field_1724.field_3944.method_45729(m.message);
            }

         });
      }
   }

   @Generated
   public File getFile() {
      return this.file;
   }

   @Generated
   public List<K8855472f0415e5af4f> getMacros() {
      return this.macros;
   }

   @Generated
   public static Kae5f6f8dfd322901c3 getInstance() {
      return instance;
   }
}
