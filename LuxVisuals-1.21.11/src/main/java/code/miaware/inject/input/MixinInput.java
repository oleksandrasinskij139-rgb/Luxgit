package code.miaware.inject.input;

import code.miaware.api.system.interfaces.K05e8563c6cb46099e8;
import net.minecraft.class_10185;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_744.class})
public abstract class MixinInput implements K05e8563c6cb46099e8 {
   @Unique
   protected class_10185 untransformed;

   public MixinInput() {
      this.untransformed = class_10185.field_54098;
   }

   public class_10185 evelina$getUntransformed() {
      return this.untransformed;
   }
}
