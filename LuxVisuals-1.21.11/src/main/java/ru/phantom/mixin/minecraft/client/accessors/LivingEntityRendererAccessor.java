package ru.phantom.mixin.minecraft.client.accessors;

import java.util.List;
import net.minecraft.class_3887;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_922.class})
public interface LivingEntityRendererAccessor {
   @Accessor("field_4738")
   List<class_3887<?, ?>> phantom$getFeatures();
}
