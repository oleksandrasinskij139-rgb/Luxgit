package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.render.elytra.K27418430766eb72d11;
import code.miaware.api.utils.render.elytra.K8f299431979bb8cb2f;
import java.awt.Color;
import java.util.Objects;
import lombok.Generated;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_638;

@ModuleRegister(
   name = "ElytraEffect",
   category = Kf76f042931183af5ba.RENDER,
   description = "Воздушные кольца, потоки ветра и ощущение разгона на элитрах"
)
public final class Kce5e38c1ce7561008e extends Kab8a8c478f7d0077cd {
   private static final Kce5e38c1ce7561008e instance = new Kce5e38c1ce7561008e();
   private final K00fef91b3d4d1b3ecc rings = (new K00fef91b3d4d1b3ecc("Воздушные кольца")).value(true);
   private final K00fef91b3d4d1b3ecc wind = (new K00fef91b3d4d1b3ecc("Ветер от первого лица")).value(true);
   private final K00fef91b3d4d1b3ecc widenFov = (new K00fef91b3d4d1b3ecc("Расширение обзора")).value(true);
   private final Kb9e875ead00e0d03bd fov;
   private final Kb9e875ead00e0d03bd size;
   private final Kb9e875ead00e0d03bd opacity;
   private final Kb9e875ead00e0d03bd sensitivity;
   private final Ka52d224feda41b0761 color;
   private final K8f299431979bb8cb2f state;
   private final K27418430766eb72d11 renderer;
   private class_638 world;
   private class_1657 player;
   private double lastX;
   private double lastY;
   private double lastZ;
   private boolean hasPosition;

   public Kce5e38c1ce7561008e() {
      Kb9e875ead00e0d03bd var10001 = (new Kb9e875ead00e0d03bd("Сила FOV")).range(0.0F, 30.0F).step(1.0F).value(18.0F);
      K00fef91b3d4d1b3ecc var10002 = this.widenFov;
      Objects.requireNonNull(var10002);
      this.fov = var10001.setVisible(var10002::getValue);
      var10001 = (new Kb9e875ead00e0d03bd("Размер колец")).range(0.5F, 2.5F).step(0.1F).value(1.0F);
      var10002 = this.rings;
      Objects.requireNonNull(var10002);
      this.size = var10001.setVisible(var10002::getValue);
      this.opacity = (new Kb9e875ead00e0d03bd("Интенсивность")).range(10.0F, 100.0F).step(5.0F).value(75.0F);
      this.sensitivity = (new Kb9e875ead00e0d03bd("Чувствительность разгона")).range(0.5F, 2.0F).step(0.1F).value(1.0F);
      this.color = (new Ka52d224feda41b0761("Оттенок воздуха")).value(new Color(165, 227, 255));
      this.state = new K8f299431979bb8cb2f();
      this.renderer = new K27418430766eb72d11();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.rings, this.wind, this.widenFov, this.fov, this.size, this.opacity, this.sensitivity, this.color});
   }

   public void onEnable() {
      this.reset();
   }

   public void onDisable() {
      this.reset();
   }

   private void reset() {
      this.state.reset();
      this.world = null;
      this.player = null;
      this.hasPosition = false;
   }

   private boolean K8fddff76e1daf95b90() {
      if (mc.field_1687 != this.world || mc.field_1724 != this.player) {
         this.reset();
         this.world = mc.field_1687;
         this.player = mc.field_1724;
      }

      return this.player != null && this.world != null;
   }

   private void tick() {
      if (this.K8fddff76e1daf95b90() && !mc.method_1493()) {
         class_243 position = this.player.method_19538();
         double deltaX = position.field_1352 - this.lastX;
         double deltaY = position.field_1351 - this.lastY;
         double deltaZ = position.field_1350 - this.lastZ;
         if (this.hasPosition && deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ > (double)4096.0F) {
            this.state.reset();
         }

         this.lastX = position.field_1352;
         this.lastY = position.field_1351;
         this.lastZ = position.field_1350;
         this.hasPosition = true;
         class_243 velocity = this.player.method_18798();
         boolean gliding = this.player.method_6128() && this.player.method_5805() && !this.player.method_5799();
         this.state.tick(gliding, velocity.method_1033() * (double)20.0F, position.field_1352, position.field_1351 + 0.35, position.field_1350, velocity.field_1352, velocity.field_1351, velocity.field_1350, (double)this.sensitivity.getValue());
      }
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.tick())), Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.K8fddff76e1daf95b90() && (Boolean)this.rings.getValue()) {
            this.renderer.renderRings(event, this.state, this.size.getValue(), this.opacity.getValue() / 100.0F, (Color)this.color.getValue());
         }
      })), K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.K8fddff76e1daf95b90() && (Boolean)this.wind.getValue() && mc.field_1690.method_31044().method_31034() && !mc.field_1690.field_1842 && mc.field_1755 == null && mc.method_1560() == this.player) {
            this.renderer.renderWind(event, this.state, mc.method_22683().method_4486(), mc.method_22683().method_4502(), this.opacity.getValue() / 100.0F, (Color)this.color.getValue());
         }
      }))});
   }

   public float modifyFov(float original, float partial) {
      return this.isEnabled() && this.K8fddff76e1daf95b90() && (Boolean)this.widenFov.getValue() && mc.method_1560() == this.player && !Kc2f9539a5b5775f961.getInstance().isEnabled() ? this.state.expandedFov(original, this.fov.getValue(), partial) : original;
   }

   @Generated
   public static Kce5e38c1ce7561008e getInstance() {
      return instance;
   }
}
