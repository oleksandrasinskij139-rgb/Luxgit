package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.player.world.K8d0b25c690b59e234d;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.features.modules.render.K1162659203460bc8c0;
import code.miaware.client.features.modules.render.Ke4080118fb4a1a51fb;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_638;
import net.minecraft.class_745;
import net.minecraft.class_1297.class_5529;

@ModuleRegister(
   name = "FakePlayer",
   category = Kf76f042931183af5ba.OTHER,
   description = "Создает фейкового игрока перед вами"
)
public class K83ce831abd917ce842 extends Kab8a8c478f7d0077cd {
   private static final K83ce831abd917ce842 instance = new K83ce831abd917ce842();
   private static final int FAKE_PLAYER_ENTITY_ID = -910431;
   private final Kb9e875ead00e0d03bd distance = (new Kb9e875ead00e0d03bd("Дистанция")).value(2.2F).range(1.0F, 6.0F).step(0.1F);
   private final K00fef91b3d4d1b3ecc showName = (new K00fef91b3d4d1b3ecc("Показать имя")).value(true);
   private final K00fef91b3d4d1b3ecc copyInventory = (new K00fef91b3d4d1b3ecc("Копировать инвентарь")).value(true);
   private final K00fef91b3d4d1b3ecc totemMode = (new K00fef91b3d4d1b3ecc("Тотем")).value(true);
   private class_638 trackedWorld;
   private class_745 fakePlayer;
   private class_243 spawnPosition;
   private float lastDistanceValue = -1.0F;

   public K83ce831abd917ce842() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.distance, this.showName, this.copyInventory, this.totemMode});
   }

   public void onEnable() {
      this.trackedWorld = mc.field_1687;
      this.lastDistanceValue = this.distance.getValue();
      this.Kbf33f5a5f6bce74d53();
   }

   public void onDisable() {
      this.K21934b48b0f3c80a24();
      this.trackedWorld = null;
      this.spawnPosition = null;
      this.lastDistanceValue = -1.0F;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 attackListener = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.K64ba1dc4cb78f28187(event)));
      K8d7165024ff567f5e7 updateListener = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.K4116d48aa2bc902e09()));
      this.addEvents(new K8d7165024ff567f5e7[]{attackListener, updateListener});
   }

   private void K4116d48aa2bc902e09() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (this.trackedWorld != mc.field_1687) {
            this.K21934b48b0f3c80a24();
            this.trackedWorld = mc.field_1687;
            this.spawnPosition = null;
            this.Kbf33f5a5f6bce74d53();
         } else if (this.fakePlayer != null && !this.fakePlayer.method_31481() && mc.field_1687.method_8469(-910431) == this.fakePlayer) {
            if ((Boolean)this.totemMode.getValue() && this.fakePlayer.method_6032() <= 0.0F) {
               if (mc.field_1687 != null) {
                  mc.field_1687.method_8396(mc.field_1724, this.fakePlayer.method_24515(), class_3417.field_14931, class_3419.field_15248, 1.0F, 1.0F);
               }

               this.K870ff3de5f0a6f2e0f();
               this.fakePlayer.method_6033(this.fakePlayer.method_6063());
               this.fakePlayer.method_6122(class_1268.field_5810, new class_1799(class_1802.field_8288));
               this.fakePlayer.field_6235 = 0;
               this.fakePlayer.field_6254 = 0;
               this.fakePlayer.field_6213 = 0;
            }

            if (Math.abs(this.lastDistanceValue - this.distance.getValue()) > 0.01F) {
               this.spawnPosition = this.K10325579646fe7e51c();
               this.lastDistanceValue = this.distance.getValue();
            }

            this.K3ec92f7b07d300924e();
         } else {
            this.Kbf33f5a5f6bce74d53();
         }
      } else {
         this.K21934b48b0f3c80a24();
         this.trackedWorld = null;
         this.spawnPosition = null;
      }
   }

   private void K64ba1dc4cb78f28187(K8d0b25c690b59e234d event) {
      if (mc.field_1724 != null && this.fakePlayer != null && event.entity() == this.fakePlayer) {
         this.fakePlayer.field_6235 = 10;
         this.fakePlayer.field_6254 = 10;
         this.fakePlayer.field_6213 = 0;
         if ((Boolean)this.totemMode.getValue()) {
            float currentHealth = this.fakePlayer.method_6032();
            float damage = 5.0F;
            boolean isCrit = mc.field_1724.field_6017 > 0.0F && !mc.field_1724.method_24828() && !mc.field_1724.method_6101() && !mc.field_1724.method_5799() && !mc.field_1724.method_6059(class_1294.field_5919) && !mc.field_1724.method_5765();
            if (isCrit) {
               damage *= 1.5F;
            }

            this.fakePlayer.method_6033(Math.max(0.0F, currentHealth - damage));
         } else {
            this.fakePlayer.method_6033(this.fakePlayer.method_6063());
         }

         if (mc.field_1687 != null) {
            boolean isCrit = mc.field_1724.field_6017 > 0.0F && !mc.field_1724.method_24828() && !mc.field_1724.method_6101() && !mc.field_1724.method_5799() && !mc.field_1724.method_6059(class_1294.field_5919) && !mc.field_1724.method_5765();
            if (isCrit) {
               mc.field_1687.method_8396(mc.field_1724, this.fakePlayer.method_24515(), class_3417.field_15016, class_3419.field_15248, 1.0F, 1.0F);
            } else {
               mc.field_1687.method_8396(mc.field_1724, this.fakePlayer.method_24515(), class_3417.field_14840, class_3419.field_15248, 1.0F, 1.0F);
            }
         }

      }
   }

   private void Kbf33f5a5f6bce74d53() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         this.K21934b48b0f3c80a24();
         GameProfile profile = new GameProfile(UUID.randomUUID(), "FakeNPC");
         profile.getProperties().putAll(mc.field_1724.method_7334().getProperties());
         class_745 dummy = new class_745(mc.field_1687, profile);
         dummy.method_5838(-910431);
         dummy.method_5826(profile.getId());
         dummy.method_5875(true);
         dummy.method_6033(dummy.method_6063());
         dummy.method_5665(class_2561.method_43470("FakeNPC"));
         dummy.method_5880((Boolean)this.showName.getValue());
         if ((Boolean)this.copyInventory.getValue()) {
            dummy.method_31548().method_7377(mc.field_1724.method_31548());
         }

         if ((Boolean)this.totemMode.getValue()) {
            dummy.method_6122(class_1268.field_5810, new class_1799(class_1802.field_8288));
         }

         dummy.field_5960 = true;
         this.spawnPosition = this.K10325579646fe7e51c();
         this.lastDistanceValue = this.distance.getValue();
         dummy.method_60949(this.spawnPosition, mc.field_1724.method_36454() + 180.0F, 0.0F);
         dummy.method_5847(dummy.method_36454());
         dummy.method_5636(dummy.method_36454());
         dummy.field_5982 = dummy.method_36454();
         dummy.field_6259 = dummy.method_5791();
         dummy.field_6220 = dummy.method_43078();
         dummy.method_24830(true);
         class_1297 existing = mc.field_1687.method_8469(-910431);
         if (existing != null) {
            mc.field_1687.method_2945(existing.method_5628(), class_5529.field_26999);
         }

         mc.field_1687.method_53875(dummy);
         this.fakePlayer = dummy;
         this.trackedWorld = mc.field_1687;
      }
   }

   private void K3ec92f7b07d300924e() {
      if (this.fakePlayer != null && mc.field_1724 != null) {
         if (this.spawnPosition == null) {
            this.spawnPosition = this.fakePlayer.method_19538();
         }

         this.fakePlayer.method_5880((Boolean)this.showName.getValue());
         this.fakePlayer.method_5665(class_2561.method_43470("FakeNPC"));
         this.fakePlayer.method_5875(true);
         this.fakePlayer.field_6213 = 0;
         if (!(Boolean)this.totemMode.getValue()) {
            this.fakePlayer.method_6033(this.fakePlayer.method_6063());
         }

         this.fakePlayer.method_24830(true);
         this.fakePlayer.method_18799(class_243.field_1353);
         this.fakePlayer.method_60949(this.spawnPosition, this.fakePlayer.method_36454(), 0.0F);
         this.fakePlayer.method_36457(0.0F);
         this.fakePlayer.field_6241 = this.fakePlayer.method_36454();
         this.fakePlayer.field_6283 = this.fakePlayer.method_36454();
         if ((Boolean)this.copyInventory.getValue()) {
            this.fakePlayer.method_31548().method_7377(mc.field_1724.method_31548());
         }

         if ((Boolean)this.totemMode.getValue()) {
            this.fakePlayer.method_6122(class_1268.field_5810, new class_1799(class_1802.field_8288));
         } else {
            this.fakePlayer.method_6122(class_1268.field_5810, class_1799.field_8037);
         }

         this.fakePlayer.field_5960 = true;
      }
   }

   private class_243 K10325579646fe7e51c() {
      float yawRadians = mc.field_1724.method_36454() * ((float)Math.PI / 180F);
      double forwardX = (double)(-class_3532.method_15374(yawRadians));
      double forwardZ = (double)class_3532.method_15362(yawRadians);
      class_243 desired = mc.field_1724.method_19538().method_1031(forwardX * (double)this.distance.getValue(), (double)0.0F, forwardZ * (double)this.distance.getValue());
      return new class_243(desired.field_1352, mc.field_1724.method_23318(), desired.field_1350);
   }

   private void K21934b48b0f3c80a24() {
      if (this.fakePlayer != null) {
         class_638 world = this.trackedWorld != null ? this.trackedWorld : mc.field_1687;
         if (world != null) {
            world.method_2945(this.fakePlayer.method_5628(), class_5529.field_26999);
         }

         this.fakePlayer = null;
      }
   }

   private void K870ff3de5f0a6f2e0f() {
      if (this.fakePlayer != null && mc.field_1687 != null) {
         K1162659203460bc8c0.getInstance().emit(this.fakePlayer);
         class_243 pos = this.fakePlayer.method_19538();
         float yaw = this.fakePlayer.method_43078();
         boolean sneak = this.fakePlayer.method_5715();
         float phase = (float)this.fakePlayer.field_6012;
         if (Ke4080118fb4a1a51fb.getInstance().isEnabled()) {
            Ke4080118fb4a1a51fb.getInstance().addSoul(pos, yaw, sneak, phase);
         }

      }
   }

   public class_745 getFakePlayer() {
      return this.fakePlayer;
   }

   @Generated
   public static K83ce831abd917ce842 getInstance() {
      return instance;
   }
}
