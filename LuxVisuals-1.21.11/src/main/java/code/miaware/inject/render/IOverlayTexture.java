package code.miaware.inject.render;

import net.minecraft.class_1043;
import net.minecraft.class_4608;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_4608.class})
public interface IOverlayTexture {
   @Accessor("texture")
   class_1043 lux$texture();
}
