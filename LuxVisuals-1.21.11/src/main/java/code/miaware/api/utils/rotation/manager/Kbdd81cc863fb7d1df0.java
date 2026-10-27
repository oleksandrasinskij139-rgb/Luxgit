package code.miaware.api.utils.rotation.manager;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public abstract class Kbdd81cc863fb7d1df0 implements K4600bef438dafc1a9c, Ke298bfdac4e34a00d9 {
   private final String name;

   public Kbdd81cc863fb7d1df0(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation) {
      return this.process(currentRotation, targetRotation, (class_243)null, (class_1297)null);
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d) {
      return this.process(currentRotation, targetRotation, vec3d, (class_1297)null);
   }

   public abstract Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 var1, Keb4a85ca52fb971c57 var2, class_243 var3, class_1297 var4);
}
