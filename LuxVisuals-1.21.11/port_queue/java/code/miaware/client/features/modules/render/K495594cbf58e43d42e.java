package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.client.util.render.effects.K210427f95fa84672e0;
import code.miaware.client.util.render.effects.Ke506047f060db15818;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5498;

@ModuleRegister(
   name = "GlassVapor",
   category = Kf76f042931183af5ba.RENDER,
   description = "Стеклянный след с преломлением за игроком"
)
public final class K495594cbf58e43d42e extends Kab8a8c478f7d0077cd {
   private static final K495594cbf58e43d42e instance = new K495594cbf58e43d42e();
   private Object world;
   private Object player;
   private final Kb9e875ead00e0d03bd frequency = (new Kb9e875ead00e0d03bd("Частота")).range(3.0F, 60.0F).step(1.0F).value(13.0F);
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Размер")).range(0.05F, 0.8F).step(0.01F).value(0.25F);
   private final Kb9e875ead00e0d03bd lifetime = (new Kb9e875ead00e0d03bd("Время жизни")).range(0.3F, 4.0F).step(0.1F).value(1.6F);
   private final Kb9e875ead00e0d03bd refraction = (new Kb9e875ead00e0d03bd("Преломление")).range(0.0F, 0.15F).step(0.005F).value(0.04F);
   private final Kb9e875ead00e0d03bd chromatic = (new Kb9e875ead00e0d03bd("Хроматика")).range(0.0F, 1.0F).step(0.05F).value(0.25F);
   private final Kb9e875ead00e0d03bd tint = (new Kb9e875ead00e0d03bd("Оттенок")).range(0.0F, 1.0F).step(0.05F).value(0.45F);
   private final Kb9e875ead00e0d03bd outline = (new Kb9e875ead00e0d03bd("Контур")).range(0.0F, 1.0F).step(0.05F).value(0.35F);
   private final Kb9e875ead00e0d03bd wobble = (new Kb9e875ead00e0d03bd("Колебание")).range(0.0F, 1.0F).step(0.05F).value(0.0F);
   private final K00fef91b3d4d1b3ecc firstPerson = (new K00fef91b3d4d1b3ecc("От первого лица")).value(true);
   private final K00fef91b3d4d1b3ecc themeColor = (new K00fef91b3d4d1b3ecc("Цвет темы")).value(true);
   private final List<Ke506047f060db15818> bubbles = new ArrayList();
   private final K210427f95fa84672e0 renderer = new K210427f95fa84672e0();
   private class_243 previousPosition;
   private long lastEmission;
   private boolean renderFailed;

   public static K495594cbf58e43d42e getInstance() {
      return instance;
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K9cdeae6ba6bdade35a))});
   }

   public K495594cbf58e43d42e() {
      ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> client.execute(() -> {
            this.K3f258e04885123c0df();
            this.world = null;
            this.player = null;
         }));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.frequency, this.size, this.lifetime, this.refraction, this.chromatic, this.tint, this.outline, this.wobble, this.firstPerson, this.themeColor});
   }

   public void onEnable() {
      this.clear();
      this.renderFailed = false;
   }

   public void onDisable() {
      this.clear();
      this.renderer.close();
   }

   private void K3f258e04885123c0df() {
      this.clear();
      this.renderer.close();
      this.renderFailed = false;
   }

   private void clear() {
      this.bubbles.clear();
      this.previousPosition = null;
      this.lastEmission = 0L;
   }

   private void K9cdeae6ba6bdade35a(K9ee087b2e1791ad6b2 event) {
      if (this.world != mc.field_1687 || this.player != mc.field_1724) {
         this.K3f258e04885123c0df();
         this.world = mc.field_1687;
         this.player = mc.field_1724;
      }

      if (mc.field_1724 != null && mc.field_1687 != null && !this.renderFailed) {
         long now = System.nanoTime();
         this.bubbles.removeIf((b) -> now - b.birth() >= b.duration());
         float partial = event.partialTicks();
         class_243 position = new class_243(class_3532.method_16436((double)partial, mc.field_1724.field_6038, mc.field_1724.method_23317()), class_3532.method_16436((double)partial, mc.field_1724.field_5971, mc.field_1724.method_23318()), class_3532.method_16436((double)partial, mc.field_1724.field_5989, mc.field_1724.method_23321()));
         if (this.previousPosition != null) {
            class_243 movement = position.method_1020(this.previousPosition);
            double horizontal = movement.field_1352 * movement.field_1352 + movement.field_1350 * movement.field_1350;
            if (movement.method_1027() > (double)16.0F) {
               this.bubbles.clear();
               this.lastEmission = now;
            } else if (horizontal > 1.0E-8 && (float)(now - this.lastEmission) >= 1.0E9F / this.frequency.getValue()) {
               ThreadLocalRandom random = ThreadLocalRandom.current();
               class_243 direction = (new class_243(movement.field_1352, (double)0.0F, movement.field_1350)).method_1029();
               class_243 origin = position.method_1020(direction.method_1021((double)mc.field_1724.method_17681() * 0.55)).method_1031(random.nextDouble(-0.12, 0.12), (double)mc.field_1724.method_17682() * 0.6 + random.nextDouble(-0.13, 0.13), random.nextDouble(-0.12, 0.12));
               if (this.bubbles.size() >= 128) {
                  this.bubbles.remove(0);
               }

               this.bubbles.add(new Ke506047f060db15818(origin, this.size.getValue() * random.nextFloat(0.6F, 1.45F), now, (long)(this.lifetime.getValue() * 1.0E9F), random.nextFloat() * 6.283185F));
               this.lastEmission = now;
            }
         }

         this.previousPosition = position;
         if (!this.bubbles.isEmpty() && !mc.field_1690.field_1842 && ((Boolean)this.firstPerson.getValue() || mc.field_1690.method_31044() != class_5498.field_26664)) {
            int color = (Boolean)this.themeColor.getValue() ? Kb18d549c13309af9a5.primary().getRGB() : -2886166;

            try {
               this.renderer.render(event, this.bubbles, now, color, this.refraction.getValue(), this.chromatic.getValue(), this.tint.getValue(), this.outline.getValue(), this.wobble.getValue());
            } catch (RuntimeException exception) {
               this.renderFailed = true;
               this.renderer.close();
               System.err.println("[GlassVapor] Renderer stopped: " + exception.getMessage());
            }

         }
      }
   }
}
