package code.miaware.api.system.files;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Kad89a1433174c7a563 {
   private static final Kad89a1433174c7a563 instance = new Kad89a1433174c7a563();
   private final List<Kd6d33b50f0f4040ca7> files = new ArrayList();

   public void load() {
      if (this.files.isEmpty()) {
         this.register(K137d4e93c7270b0a04.getInstance());
      }

      for(Kd6d33b50f0f4040ca7 file : this.files) {
         file.load();
      }

   }

   public void save() {
      for(Kd6d33b50f0f4040ca7 file : this.files) {
         file.save();
      }

   }

   public void register(Kd6d33b50f0f4040ca7... files) {
      this.files.addAll(List.of(files));
   }

   @Generated
   public List<Kd6d33b50f0f4040ca7> getFiles() {
      return this.files;
   }

   @Generated
   public static Kad89a1433174c7a563 getInstance() {
      return instance;
   }
}
