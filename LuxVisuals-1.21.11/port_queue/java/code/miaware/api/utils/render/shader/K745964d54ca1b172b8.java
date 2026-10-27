package code.miaware.api.utils.render.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_284;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_5944;

public class K745964d54ca1b172b8 {
   private static final List<K745964d54ca1b172b8> REGISTERED_PROGRAMS = new ArrayList();
   public class_5944 backingProgram;
   protected class_10156 programKey;

   public K745964d54ca1b172b8(class_2960 id, class_293 vertexFormat) {
      this.programKey = new class_10156(id, vertexFormat, class_10149.field_53930);
      REGISTERED_PROGRAMS.add(this);
   }

   public class_5944 use() {
      this.backingProgram = RenderSystem.setShader(this.programKey);
      if (this.backingProgram == null) {
         System.err.println("[LuxVisuals] Warning: Shader program not loaded: " + String.valueOf(this.programKey));
      }

      return this.backingProgram;
   }

   protected void setup() {
   }

   public class_284 findUniform(String name) {
      if (this.backingProgram == null) {
         System.err.println("[LuxVisuals] Warning: Shader program not loaded, cannot find uniform: " + name);
         return null;
      } else {
         return this.backingProgram.method_34582(name);
      }
   }

   public static void loadAndSetupPrograms() {
      System.out.println("[LuxVisuals] Registered " + REGISTERED_PROGRAMS.size() + " shader programs");
      REGISTERED_PROGRAMS.forEach((program) -> {
         program.setup();
         System.out.println("[LuxVisuals] Setup shader: " + String.valueOf(program.programKey));
      });
   }

   public static int getRegisteredProgramsCount() {
      return REGISTERED_PROGRAMS.size();
   }
}
