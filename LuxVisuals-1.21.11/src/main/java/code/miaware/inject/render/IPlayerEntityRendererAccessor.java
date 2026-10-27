package code.miaware.inject.render;

import net.minecraft.class_1007;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_1007.class})
public interface IPlayerEntityRendererAccessor {
   @Invoker("renderRightArm")
   void invokeRenderRightArm(class_4587 var1, class_4597 var2, int var3, class_2960 var4, boolean var5);

   @Invoker("renderLeftArm")
   void invokeRenderLeftArm(class_4587 var1, class_4597 var2, int var3, class_2960 var4, boolean var5);
}
