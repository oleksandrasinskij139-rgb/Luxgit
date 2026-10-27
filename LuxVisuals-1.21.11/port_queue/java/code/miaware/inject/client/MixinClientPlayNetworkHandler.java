package code.miaware.inject.client;

import code.miaware.api.command.Ke66e08ec83c92514e4;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.system.client.K9a4619034baf09d6b2;
import code.miaware.api.utils.server.Kbe30937e40237cb7db;
import code.miaware.client.features.modules.player.K9cd17d416e33f25c15;
import code.miaware.client.features.modules.render.K1162659203460bc8c0;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_2663;
import net.minecraft.class_2678;
import net.minecraft.class_634;
import net.minecraft.class_702;
import net.minecraft.class_8143;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public class MixinClientPlayNetworkHandler {
   @Redirect(
      method = {"onEntityStatus"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"
)
   )
   private void replaceTotemParticles(class_702 manager, class_1297 entity, class_2394 effect, int ticks) {
      if (effect != class_2398.field_11220 || !K1162659203460bc8c0.getInstance().emit(entity)) {
         manager.method_3051(entity, effect, ticks);
      }

   }

   @Inject(
      method = {"onGameJoin"},
      at = {@At("RETURN")}
   )
   private void onGameJoin(class_2678 packet, CallbackInfo ci) {
      Kbe30937e40237cb7db.detectServer();
      System.out.println("[ServerDetector] Подключение к серверу: " + String.valueOf(Kbe30937e40237cb7db.getCurrentServer()));
   }

   @Inject(
      method = {"sendChatMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendChatMessage(String content, CallbackInfo ci) {
      K9cd17d416e33f25c15.getInstance().onChatMessage(content);
      Ke66e08ec83c92514e4.getInstance().executeCommands(content, ci);
   }

   @Inject(
      method = {"onEntityStatus"},
      at = {@At("RETURN")}
   )
   private void onEntityStatus(class_2663 packet, CallbackInfo ci) {
      if (packet.method_11470() == 3 && K8e471a90dc4db42e81.world() != null && K8e471a90dc4db42e81.player() != null) {
         class_1297 entity = packet.method_11469(K8e471a90dc4db42e81.world());
         if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            K9a4619034baf09d6b2.death(living, (class_1282)null);
         }

      }
   }

   @Inject(
      method = {"onEntityDamage"},
      at = {@At("RETURN")}
   )
   private void onEntityDamage(class_8143 packet, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.world() != null && K8e471a90dc4db42e81.player() != null) {
         class_1297 entity = K8e471a90dc4db42e81.world().method_8469(packet.comp_1267());
         if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            class_1282 source = packet.method_49071(K8e471a90dc4db42e81.world());
            K9a4619034baf09d6b2.damage(living, source);
            if (living.method_29504() || living.method_6032() <= 0.0F) {
               K9a4619034baf09d6b2.death(living, source);
            }
         }

      }
   }
}
