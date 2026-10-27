package ru.phantom.mixin.minecraft.client.accessors;

import net.minecraft.class_572;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_572.class})
public interface BipedEntityModelAccessor {
   @Accessor("field_3398")
   class_630 phantom$getHead();

   @Accessor("field_3394")
   class_630 phantom$getHat();
}
