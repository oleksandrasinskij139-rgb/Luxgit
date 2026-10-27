package code.miaware.inject.input;

import net.minecraft.class_304;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_304.class})
public interface KeyBindingStateAccess {
   @Accessor("pressed")
   void lux$setPressedState(boolean var1);
}
