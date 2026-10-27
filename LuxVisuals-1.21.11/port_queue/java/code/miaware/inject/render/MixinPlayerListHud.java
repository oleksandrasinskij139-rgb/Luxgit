package code.miaware.inject.render;

import code.miaware.client.features.modules.render.Kacf94aeb4cdc6e13ca;
import code.miaware.client.features.modules.render.Ke5c19b3dd893e7143c;
import code.miaware.client.network.badge.Ka424c22578b339e00d;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_355.class})
public class MixinPlayerListHud {
   @Shadow
   @Final
   private class_310 field_2155;
   @Unique
   private long showTime = -1L;
   @Unique
   private boolean wasRendering = false;
   @Unique
   private static final long TAB_ANIMATION_DURATION = 200L;

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void applyTabAnimation(class_332 context, int scaledWindowWidth, class_269 scoreboard, class_266 objective, CallbackInfo ci) {
      if (Kacf94aeb4cdc6e13ca.getInstance().isEnabled() && (Boolean)Kacf94aeb4cdc6e13ca.getInstance().getTabAnimation().getValue()) {
         if (!this.wasRendering) {
            this.showTime = System.currentTimeMillis();
            this.wasRendering = true;
         }

         long elapsed = System.currentTimeMillis() - this.showTime;
         if (elapsed < 200L) {
            float progress = (float)elapsed / 200.0F;
            progress = 1.0F - (float)Math.pow((double)(1.0F - progress), (double)3.0F);
            float yOffset = -100.0F * (1.0F - progress);
            context.method_51448().method_22903();
            context.method_51448().method_46416(0.0F, yOffset, 0.0F);
         }
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void removeTabAnimation(class_332 context, int scaledWindowWidth, class_269 scoreboard, class_266 objective, CallbackInfo ci) {
      if (Kacf94aeb4cdc6e13ca.getInstance().isEnabled() && (Boolean)Kacf94aeb4cdc6e13ca.getInstance().getTabAnimation().getValue()) {
         long elapsed = System.currentTimeMillis() - this.showTime;
         if (elapsed < 200L) {
            context.method_51448().method_22909();
         }
      }
   }

   @Inject(
      method = {"setVisible"},
      at = {@At("HEAD")}
   )
   private void onVisibilityChange(boolean visible, CallbackInfo ci) {
      if (!visible) {
         this.wasRendering = false;
      }

   }

   @Inject(
      method = {"collectPlayerEntries"},
      at = {@At("RETURN")},
      cancellable = true,
      require = 0
   )
   private void tabCustomizerReorder(CallbackInfoReturnable<List<class_640>> cir) {
      List<class_640> list = Ke5c19b3dd893e7143c.reorder((List)cir.getReturnValue());
      if (list != cir.getReturnValue()) {
         cir.setReturnValue(list);
      }

   }

   @Inject(
      method = {"getPlayerName"},
      at = {@At("RETURN")},
      cancellable = true,
      require = 0
   )
   private void tabCustomizerName(class_640 entry, CallbackInfoReturnable<class_2561> cir) {
      class_2561 painted = Ke5c19b3dd893e7143c.decorate(entry, (class_2561)cir.getReturnValue());
      painted = Ka424c22578b339e00d.decorate(entry.method_2966().getId(), entry.method_2966().getName(), painted);
      if (painted != cir.getReturnValue()) {
         cir.setReturnValue(painted);
      }

   }
}
