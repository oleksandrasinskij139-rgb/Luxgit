package ru.phantom.mixin.minecraft.client.accessors;

import net.minecraft.class_1102;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1102.class})
public interface AbstractSoundInstanceAccessor {
   @Accessor("field_5442")
   float phantom$getVolume();

   @Accessor("field_5442")
   void phantom$setVolume(float var1);
}
