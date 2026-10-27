package code.miaware.inject.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4224;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin({class_4224.class})
public interface SourceAccessor {
   @Accessor("pointer")
   int rtx$getSourceId();
}
