package code.miaware.api.utils.rotation.manager;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.event.events.other.Ka6f0ac7a42b81455f2;
import code.miaware.api.event.events.player.move.K24d5217269a82ac585;
import code.miaware.api.event.events.player.other.K74e7c4e8fcaba1b06a;
import code.miaware.api.event.events.player.other.Kaf151817eb75ab6184;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.task.K08a614ab3119f6f1e9;
import code.miaware.api.utils.task.K6f7e2c58827daa2a0b;
import code.miaware.api.utils.task.Kb3e3d55f28819672e6;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public class K960322f580344d3bad implements K4600bef438dafc1a9c {
   private static final K960322f580344d3bad instance = new K960322f580344d3bad();
   private K1311b30b569cc830b6 lastRotationPlan;
   private final K6f7e2c58827daa2a0b<K1311b30b569cc830b6> rotationPlanRequestProcessor = new K6f7e2c58827daa2a0b<K1311b30b569cc830b6>();
   private Keb4a85ca52fb971c57 currentRotation;
   private Keb4a85ca52fb971c57 previousRotation;
   private Keb4a85ca52fb971c57 serverRotation;

   public K960322f580344d3bad() {
      this.serverRotation = Keb4a85ca52fb971c57.DEFAULT;
   }

   public void load() {
      K24d5217269a82ac585.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.getCurrentRotationPlan() != null && this.getCurrentRotationPlan().moveCorrection()) {
            event.setVelocity(class_1297.method_18795(event.getMovementInput(), event.getSpeed(), this.getRotation().getYaw()));
         }

      }));
      K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         Keb4a85ca52fb971c57 rotation;
         label24: {
            rotation = null;
            if (event.isSend()) {
               class_2596 patt0$temp = event.packet();
               if (patt0$temp instanceof class_2828) {
                  class_2828 packet = (class_2828)patt0$temp;
                  if (packet.method_36172()) {
                     rotation = new Keb4a85ca52fb971c57(packet.method_12271(1.0F), packet.method_12270(1.0F));
                  }
                  break label24;
               }
            }

            if (event.isReceive()) {
               class_2596 patt1$temp = event.packet();
               if (patt1$temp instanceof class_2708) {
                  class_2708 packet = (class_2708)patt1$temp;
                  rotation = new Keb4a85ca52fb971c57(packet.comp_3228().comp_3150(), packet.comp_3228().comp_3151());
               }
            }
         }

         if (rotation != null && !K001d59b5164d1d1f95.getInstance().isCancel()) {
            this.serverRotation = rotation;
         }

      }));
      K74e7c4e8fcaba1b06a.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> Kaf151817eb75ab6184.getInstance().call()));
      K2a56ae9ebefcb944e3.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            Ka6f0ac7a42b81455f2.getInstance().call();
            this.K04969efc5ac2e80665();
         }
      }));
   }

   private void K94b9e16dfd4af4368b(Keb4a85ca52fb971c57 value) {
      this.previousRotation = this.currentRotation != null ? this.currentRotation : (mc.field_1724 != null ? new Keb4a85ca52fb971c57(mc.field_1724.method_36454(), mc.field_1724.method_36455()) : Keb4a85ca52fb971c57.DEFAULT);
      this.currentRotation = value;
   }

   public Keb4a85ca52fb971c57 getRotation() {
      if (mc.field_1724 == null) {
         return Keb4a85ca52fb971c57.DEFAULT;
      } else {
         return this.currentRotation != null ? this.currentRotation : Kef2fce79615318605c.fromVec2f(mc.field_1724.method_5802());
      }
   }

   public Keb4a85ca52fb971c57 getPreviousRotation() {
      if (mc.field_1724 == null) {
         return Keb4a85ca52fb971c57.DEFAULT;
      } else {
         return this.previousRotation != null ? this.previousRotation : Kef2fce79615318605c.fromVec2f(mc.field_1724.method_5802());
      }
   }

   public K1311b30b569cc830b6 getCurrentRotationPlan() {
      K1311b30b569cc830b6 activePlan = this.rotationPlanRequestProcessor.fetchActiveTaskValue();
      if (activePlan != null) {
         return activePlan;
      } else if (this.lastRotationPlan != null && this.currentRotation != null) {
         return this.lastRotationPlan.provider() != null && this.lastRotationPlan.provider().isEnabled() ? this.lastRotationPlan : null;
      } else {
         return null;
      }
   }

   public void addRotation(K6ef22c7d29572c2ace vecRotation, class_1309 entity, K3cdaeb9115f886e56b configurable, K08a614ab3119f6f1e9 requestPriority, Kab8a8c478f7d0077cd provider) {
      this.Kf595efa193713ee931(configurable.createRotationPlan(vecRotation.rotation(), vecRotation.vec(), entity, provider), requestPriority, provider);
   }

   public void addRotation(Keb4a85ca52fb971c57 rotation, K3cdaeb9115f886e56b configurable, K08a614ab3119f6f1e9 requestPriority, Kab8a8c478f7d0077cd provider) {
      this.Kf595efa193713ee931(configurable.createRotationPlan(rotation, provider), requestPriority, provider);
   }

   public void clearProvider(Kab8a8c478f7d0077cd provider) {
      this.rotationPlanRequestProcessor.removeTasksByProvider(provider);
      if (this.lastRotationPlan != null && this.lastRotationPlan.provider() == provider) {
         this.lastRotationPlan = null;
         this.currentRotation = null;
         this.previousRotation = null;
         if (mc.field_1724 != null) {
            this.serverRotation = new Keb4a85ca52fb971c57(mc.field_1724.method_36454(), mc.field_1724.method_36455());
         }
      }

   }

   private void Kf595efa193713ee931(K1311b30b569cc830b6 plan, K08a614ab3119f6f1e9 requestPriority, Kab8a8c478f7d0077cd provider) {
      this.rotationPlanRequestProcessor.addTask(new Kb3e3d55f28819672e6(plan.ticksUntilReset(), requestPriority.getPriority(), provider, plan));
   }

   private void K04969efc5ac2e80665() {
      K1311b30b569cc830b6 activePlan = this.getCurrentRotationPlan();
      if (activePlan != null) {
         Keb4a85ca52fb971c57 playerRotation = Kef2fce79615318605c.fromVec2f(mc.field_1724.method_5802());
         if (this.rotationPlanRequestProcessor.fetchActiveTaskValue() == null) {
            double differenceFromCurrentToPlayer = this.K221f5cf7fdad36d82a(this.serverRotation, playerRotation);
            if (differenceFromCurrentToPlayer < (double)activePlan.resetThreshold()) {
               if (this.currentRotation != null) {
                  mc.field_1724.method_36456(this.currentRotation.getYaw() + this.K9e20470630cfc7fbd4(mc.field_1724.method_36454(), this.currentRotation.getYaw()));
                  mc.field_1724.method_36457(this.currentRotation.getPitch() + this.K9e20470630cfc7fbd4(mc.field_1724.method_36455(), this.currentRotation.getPitch()));
               }

               this.K94b9e16dfd4af4368b((Keb4a85ca52fb971c57)null);
               this.lastRotationPlan = null;
               return;
            }
         }

         Keb4a85ca52fb971c57 newRotation = activePlan.nextRotation(this.currentRotation != null ? this.currentRotation : playerRotation, this.rotationPlanRequestProcessor.fetchActiveTaskValue() == null).adjustSensitivity();
         this.K94b9e16dfd4af4368b(newRotation);
         if (activePlan.clientLook()) {
            mc.field_1724.method_36456(newRotation.getYaw());
            mc.field_1724.method_36457(newRotation.getPitch());
         }

         this.lastRotationPlan = activePlan;
         this.rotationPlanRequestProcessor.tick(1);
      }
   }

   private double K221f5cf7fdad36d82a(Keb4a85ca52fb971c57 a, Keb4a85ca52fb971c57 b) {
      return Math.hypot((double)class_3532.method_15379(this.K9e20470630cfc7fbd4(a.getYaw(), b.getYaw())), (double)class_3532.method_15379(this.K9e20470630cfc7fbd4(a.getPitch(), b.getPitch())));
   }

   private float K9e20470630cfc7fbd4(float a, float b) {
      return class_3532.method_15393(a - b);
   }

   @Generated
   public K1311b30b569cc830b6 getLastRotationPlan() {
      return this.lastRotationPlan;
   }

   @Generated
   public K6f7e2c58827daa2a0b<K1311b30b569cc830b6> getRotationPlanRequestProcessor() {
      return this.rotationPlanRequestProcessor;
   }

   @Generated
   public Keb4a85ca52fb971c57 getCurrentRotation() {
      return this.currentRotation;
   }

   @Generated
   public Keb4a85ca52fb971c57 getServerRotation() {
      return this.serverRotation;
   }

   @Generated
   public void setLastRotationPlan(K1311b30b569cc830b6 lastRotationPlan) {
      this.lastRotationPlan = lastRotationPlan;
   }

   @Generated
   public void setCurrentRotation(Keb4a85ca52fb971c57 currentRotation) {
      this.currentRotation = currentRotation;
   }

   @Generated
   public void setPreviousRotation(Keb4a85ca52fb971c57 previousRotation) {
      this.previousRotation = previousRotation;
   }

   @Generated
   public void setServerRotation(Keb4a85ca52fb971c57 serverRotation) {
      this.serverRotation = serverRotation;
   }

   @Generated
   public static K960322f580344d3bad getInstance() {
      return instance;
   }
}
