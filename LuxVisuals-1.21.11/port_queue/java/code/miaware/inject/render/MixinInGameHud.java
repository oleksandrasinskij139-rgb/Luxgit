package code.miaware.inject.render;

import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.utils.render.Kecb75e73598eb5799c;
import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import code.miaware.client.features.modules.render.K64002e36b6d857aee4;
import code.miaware.client.features.modules.render.K7a3ade6f5e6840c330;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_266;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class MixinInGameHud {
   private boolean lastInterfaceEnabled = false;
   private boolean lastPotionsEnabled = false;
   private boolean lastScoreboardRemoved = false;
   private boolean lastBadEffectsRemoved = false;
   private long lastBlurCheck = 0L;
   private boolean shouldRenderBlur = false;

   @Inject(
      method = {"renderFood"},
      at = {@At("RETURN")}
   )
   private void lux$totemBar(class_332 context, class_1657 player, int foodY, int right, CallbackInfo ci) {
      K64002e36b6d857aee4.getInstance().renderAboveFood(context, player, foodY, right);
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void lux$journeyArrival(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      K7a3ade6f5e6840c330.getInstance().renderArrival(context);
   }

   @Inject(
      method = {"renderMiscOverlays"},
      at = {@At("HEAD")},
      cancellable = true,
      require = 0
   )
   private void onRenderMiscOverlays(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (K3875e4bdc53cc1074e.getInstance().isBadEffects() && class_310.method_1551().field_1724 != null && (class_310.method_1551().field_1724.method_6059(class_1294.field_5916) || class_310.method_1551().field_1724.method_6059(class_1294.field_5919) || class_310.method_1551().field_1724.method_6059(class_1294.field_5899))) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void renderHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      this.lastInterfaceEnabled = Kfae29c687712462375.getInstance().isEnabled();
      class_310 mc = class_310.method_1551();
      if (mc.field_1755 != null && mc.field_1755.getClass().getSimpleName().equals("ScreenIRCGui")) {
         K14caf22f676a0204f7.getInstance().call(new Kb590f148cfda70c907(context, context.method_51448(), tickCounter.method_60637(false)));
      } else {
         long now = System.currentTimeMillis();
         if (now - this.lastBlurCheck > 50L) {
            this.shouldRenderBlur = this.lastInterfaceEnabled && mc.field_1755 != null;
            this.lastBlurCheck = now;
         }

         if (this.shouldRenderBlur) {
            Kecb75e73598eb5799c.render(context.method_51448());
         }

         K14caf22f676a0204f7.getInstance().call(new Kb590f148cfda70c907(context, context.method_51448(), tickCounter.method_60637(false)));
      }
   }

   @Inject(
      method = {"renderStatusEffectOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderStatusEffectOverlay(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      this.lastBadEffectsRemoved = K3875e4bdc53cc1074e.getInstance().isBadEffects();
      if (this.lastInterfaceEnabled) {
         this.lastPotionsEnabled = Kfae29c687712462375.getInstance().widgets.isEnabled("Potions");
         if (this.lastPotionsEnabled) {
            ci.cancel();
         }
      }

   }

   @Inject(
      method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderScoreboardSidebar(class_332 context, class_266 objective, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null) {
         this.lastScoreboardRemoved = K3875e4bdc53cc1074e.getInstance().isScoreboard();
         if (this.lastScoreboardRemoved) {
            ci.cancel();
         }

      }
   }
}
