package code.miaware.inject.input;

import code.miaware.api.event.events.player.move.K363439524bd338fc89;
import code.miaware.api.event.events.player.move.K9127285e6d255e9450;
import code.miaware.api.event.events.player.other.K74e7c4e8fcaba1b06a;
import code.miaware.api.event.events.player.other.K8253e996424b7f5e4d;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.player.K8c202e08d32fc608f9;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.client.features.modules.combat.K9f996c494276981418;
import code.miaware.client.ui.swap.Kb7a870ae80057bb597;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_10185;
import net.minecraft.class_743;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_743.class})
public class MixinKeyboardInput extends MixinInput {
   @ModifyExpressionValue(
      method = {"tick"},
      at = {@At(
   value = "NEW",
   target = "(ZZZZZZZ)Lnet/minecraft/util/PlayerInput;"
)}
   )
   private class_10185 onTick(class_10185 original) {
      original = Kb7a870ae80057bb597.movementInput(original);
      original = K9f996c494276981418.filterMovement(original);
      K8253e996424b7f5e4d movementInputEvent = new K8253e996424b7f5e4d(original, original.comp_3163(), original.comp_3164(), new K8c202e08d32fc608f9(original));
      K74e7c4e8fcaba1b06a.getInstance().call(movementInputEvent);
      K8c202e08d32fc608f9 untransformedDirectionalInput = movementInputEvent.getDirectionalInput();
      K8c202e08d32fc608f9 directionalInput = this.transformDirection(untransformedDirectionalInput);
      K363439524bd338fc89 sprintEvent = new K363439524bd338fc89(original.comp_3165(), directionalInput);
      K9127285e6d255e9450.getInstance().call(sprintEvent);
      if (K9f996c494276981418.isMovementLocked()) {
         this.untransformed = K9f996c494276981418.filterMovement(original);
         return this.untransformed;
      } else {
         this.untransformed = new class_10185(untransformedDirectionalInput.isForwards(), untransformedDirectionalInput.isBackwards(), untransformedDirectionalInput.isLeft(), untransformedDirectionalInput.isRight(), original.comp_3163(), original.comp_3164(), sprintEvent.isSprint());
         return new class_10185(directionalInput.isForwards(), directionalInput.isBackwards(), directionalInput.isLeft(), directionalInput.isRight(), movementInputEvent.isJump(), movementInputEvent.isSneak(), sprintEvent.isSprint());
      }
   }

   @Unique
   private K8c202e08d32fc608f9 transformDirection(K8c202e08d32fc608f9 input) {
      class_746 player = K8e471a90dc4db42e81.player();
      K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
      Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
      K1311b30b569cc830b6 rotationPlan = rotationManager.getCurrentRotationPlan();
      return rotationPlan != null && rotation != null && player != null && rotationPlan.moveCorrection() ? input : input;
   }
}
