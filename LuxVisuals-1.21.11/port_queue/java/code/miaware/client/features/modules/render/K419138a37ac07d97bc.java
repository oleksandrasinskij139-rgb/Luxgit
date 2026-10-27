package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;

@ModuleRegister(
   name = "Footsteps",
   category = Kf76f042931183af5ba.RENDER,
   description = "Оставляет следы при ходьбе"
)
public class K419138a37ac07d97bc extends Kab8a8c478f7d0077cd {
   private static final K419138a37ac07d97bc instance = new K419138a37ac07d97bc();
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Радуга").values("Радуга", "Тема");
   private final K00fef91b3d4d1b3ecc showFriends = (new K00fef91b3d4d1b3ecc("Показывать друзей")).value(true);
   private final K00fef91b3d4d1b3ecc showOthers = (new K00fef91b3d4d1b3ecc("Показывать других")).value(true);
   private final Kb9e875ead00e0d03bd lifeTime = (new Kb9e875ead00e0d03bd("Время жизни")).value(20.0F).range(5.0F, 60.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd spawnDur = (new Kb9e875ead00e0d03bd("Время появления")).value(5.0F).range(1.0F, 20.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd dyingDur = (new Kb9e875ead00e0d03bd("Время исчезновения")).value(5.0F).range(1.0F, 20.0F).step(1.0F);
   private final Map<class_1657, Kf7a85a9ade792fc05f> playerFootstepsMap = new HashMap();
   private final class_2960 iconTexture = class_2960.method_60655("luxvisuals", "textures/footstep_arrow.png");
   private int rainbowOffset = 0;

   public K419138a37ac07d97bc() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.colorMode, this.showFriends, this.showOthers, this.lifeTime, this.spawnDur, this.dyingDur});
   }

   public void onEnable() {
      super.onEnable();
      this.playerFootstepsMap.clear();
      this.rainbowOffset = 0;
   }

   public void onDisable() {
      super.onDisable();
      this.playerFootstepsMap.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            this.playerFootstepsMap.entrySet().removeIf((entry) -> entry.getKey() != mc.field_1724 && !mc.field_1687.method_18456().contains(entry.getKey()));
            Kf7a85a9ade792fc05f playerFootsteps = (Kf7a85a9ade792fc05f)this.playerFootstepsMap.computeIfAbsent(mc.field_1724, (k) -> new Kf7a85a9ade792fc05f(this, false));
            playerFootsteps.update(mc.field_1724);

            for(class_1657 player : mc.field_1687.method_18456()) {
               if (player != mc.field_1724) {
                  boolean isFriend = K137d4e93c7270b0a04.getInstance().contains(player.method_5477().getString());
                  if ((isFriend || (Boolean)this.showOthers.getValue()) && (!isFriend || (Boolean)this.showFriends.getValue())) {
                     Kf7a85a9ade792fc05f footsteps = (Kf7a85a9ade792fc05f)this.playerFootstepsMap.computeIfAbsent(player, (k) -> new Kf7a85a9ade792fc05f(this, isFriend));
                     footsteps.update(player);
                  }
               }
            }

            ++this.rainbowOffset;
            if (this.rainbowOffset >= 360) {
               this.rainbowOffset = 0;
            }

         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!this.playerFootstepsMap.isEmpty()) {
            class_4587 matrixStack = event.matrixStack();
            class_243 camera = event.cameraPos();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(770, 771);
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.setShader(class_10142.field_53880);
            int textureId = mc.method_1531().method_4619(this.iconTexture).method_4624();
            RenderSystem.setShaderTexture(0, textureId);
            class_287 batchBuffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            boolean hasFootsteps = false;

            for(Kf7a85a9ade792fc05f playerFootsteps : this.playerFootstepsMap.values()) {
               if (!playerFootsteps.footsteps.isEmpty()) {
                  playerFootsteps.render(matrixStack, batchBuffer, camera);
                  hasFootsteps = true;
               }
            }

            if (hasFootsteps) {
               class_286.method_43433(batchBuffer.method_60800());
            }

            RenderSystem.enableCull();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, renderEvent});
   }

   private static float K9dec508b3419e71718(class_243 velocity) {
      if (velocity.method_1027() < 1.0E-4) {
         return 0.0F;
      } else {
         double dx = velocity.field_1352;
         double dz = velocity.field_1350;
         return (float)(Math.atan2(dx, dz) * (180D / Math.PI)) + 180.0F;
      }
   }

   @Generated
   public static K419138a37ac07d97bc getInstance() {
      return instance;
   }
}
