package ru.phantom.mixin.minecraft.client.accessors;

import net.minecraft.class_10017;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_897.class})
public interface EntityRendererAccessor {
   @Invoker("method_3926")
   void Phantom$renderLabelIfPresent(class_10017 var1, class_2561 var2, class_4587 var3, class_4597 var4, int var5);
}
