package code.miaware.api.utils.player;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K37c6e69e88a6980262;
import code.miaware.api.utils.rotation.manager.K3cdaeb9115f886e56b;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.api.utils.rotation.rotations.K6a50a7884a8b240fad;
import code.miaware.api.utils.task.K08a614ab3119f6f1e9;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1792;

public class Kc6dac1a9241a197c7d {
   private final class_1792 item;
   private final Kab8a8c478f7d0077cd provider;
   private boolean forceUse = false;
   private boolean useRotation = true;
   private Keb4a85ca52fb971c57 customRotation = new Keb4a85ca52fb971c57(Float.NaN, Float.NaN);
   private int targetSlot = -1;
   private int previousSlot = -1;
   private int stateDelay = 0;
   private Runnable pendingAction = null;
   private K2764d7305d3a3a597f usageState;

   public void onDisable() {
      this.usageState = K2764d7305d3a3a597f.IDLE;
      this.stateDelay = 0;
   }

   public void updateCustomRotation(Keb4a85ca52fb971c57 rotation) {
      this.customRotation = rotation;
   }

   public void applyRotation() {
      if (this.useRotation) {
         float yaw = !Float.isNaN(this.customRotation.getYaw()) ? this.customRotation.getYaw() : K4600bef438dafc1a9c.mc.field_1724.method_36454();
         float pitch = !Float.isNaN(this.customRotation.getPitch()) ? this.customRotation.getPitch() : K4600bef438dafc1a9c.mc.field_1724.method_36455();
         K3cdaeb9115f886e56b configurable = new K3cdaeb9115f886e56b(new K6a50a7884a8b240fad(), false, false);
         K960322f580344d3bad.getInstance().addRotation(new Keb4a85ca52fb971c57(yaw, pitch), configurable, K08a614ab3119f6f1e9.REQUIRED, this.provider);
      }
   }

   public void handleUse(boolean isLegit) {
      if (K4600bef438dafc1a9c.mc.field_1755 == null && this.pendingAction != null) {
         this.pendingAction.run();
      }

      if (!K4600bef438dafc1a9c.mc.field_1724.method_7357().method_7904(this.item.method_7854())) {
         if (isLegit) {
            this.pendingAction = () -> this.executeLegitMode(-1);
         } else {
            this.executePacketMode();
         }

         this.forceUse = false;
      }
   }

   public void handleUse(int bind, boolean isLegit) {
      if (!isLegit) {
         this.pendingAction = null;
      }

      if (K4600bef438dafc1a9c.mc.field_1755 == null && this.pendingAction != null) {
         this.pendingAction.run();
      }

      if (!Kd9271a319f911decbe.isPressed(bind) && K4600bef438dafc1a9c.mc.field_1755 == null) {
         this.forceUse = false;
      } else if (!this.forceUse && K4600bef438dafc1a9c.mc.field_1755 == null && !K4600bef438dafc1a9c.mc.field_1724.method_7357().method_7904(this.item.method_7854())) {
         if (isLegit) {
            this.pendingAction = () -> this.executeLegitMode(bind);
         } else {
            this.executePacketMode();
         }

      }
   }

   public void executePacketMode() {
      int invSlot = Kd03e5f745eca318ca1.findItem(this.item, false);
      int hbSlot = Kd03e5f745eca318ca1.findItem(this.item, true);
      if (K4600bef438dafc1a9c.mc.field_1724.method_6079().method_31574(this.item)) {
         this.applyRotation();
         Kd03e5f745eca318ca1.useItem(class_1268.field_5810);
         this.forceUse = true;
      } else if (K4600bef438dafc1a9c.mc.field_1724.method_6047().method_31574(this.item)) {
         this.applyRotation();
         Kd03e5f745eca318ca1.useItem(class_1268.field_5808);
         this.forceUse = true;
      } else {
         int oldSlot = K4600bef438dafc1a9c.mc.field_1724.method_31548().field_7545;
         int bestSlot = Kd03e5f745eca318ca1.findBestSlotInHotBar();
         if (hbSlot != -1) {
            this.applyRotation();
            Kd03e5f745eca318ca1.swapToSlot(hbSlot);
            Kd03e5f745eca318ca1.useItem(class_1268.field_5808);
            Kd03e5f745eca318ca1.swapToSlot(oldSlot);
            this.forceUse = true;
         } else if (invSlot != -1) {
            Runnable runnable = () -> {
               this.applyRotation();
               Kd03e5f745eca318ca1.swapSlots(invSlot, bestSlot);
               Kd03e5f745eca318ca1.swapToSlot(bestSlot);
               Kd03e5f745eca318ca1.useItem(class_1268.field_5808);
               Kd03e5f745eca318ca1.swapToSlot(oldSlot);
               Kd03e5f745eca318ca1.swapSlots(invSlot, bestSlot);
               this.forceUse = true;
            };
            if (K37c6e69e88a6980262.isEnabled()) {
               K37c6e69e88a6980262.applySlowness(10L, runnable);
            } else {
               runnable.run();
            }
         }

      }
   }

   public void executeLegitMode(int bind) {
      switch (this.usageState.ordinal()) {
         case 0:
            if (bind != -1 && !Kd9271a319f911decbe.isPressed(bind)) {
               return;
            }

            this.targetSlot = Kd03e5f745eca318ca1.findItem(this.item, true);
            this.previousSlot = K4600bef438dafc1a9c.mc.field_1724.method_31548().field_7545;
            if (this.targetSlot == -1) {
               this.targetSlot = Kd03e5f745eca318ca1.findItem(this.item, false);
               if (this.targetSlot == -1) {
                  return;
               }

               this.usageState = K2764d7305d3a3a597f.SWAP_ITEM;
               this.stateDelay = 1;
            } else {
               K4600bef438dafc1a9c.mc.field_1724.method_31548().field_7545 = this.targetSlot;
               this.usageState = K2764d7305d3a3a597f.USE_ITEM;
               this.stateDelay = 1;
            }
            break;
         case 1:
            if (this.stateDelay-- > 0) {
               return;
            }

            int hotbarSlot = Kd03e5f745eca318ca1.findBestSlotInHotBar();
            if (hotbarSlot != -1) {
               Runnable toUseItem = () -> {
                  Kd03e5f745eca318ca1.swapSlots(this.targetSlot, hotbarSlot);
                  this.targetSlot = hotbarSlot;
                  K4600bef438dafc1a9c.mc.field_1724.method_31548().field_7545 = this.targetSlot;
                  Kd03e5f745eca318ca1.swapToSlot(this.targetSlot);
                  this.usageState = K2764d7305d3a3a597f.USE_ITEM;
                  this.stateDelay = 1;
               };
               if (K37c6e69e88a6980262.isEnabled()) {
                  K37c6e69e88a6980262.applySlowness(10L, toUseItem);
               } else {
                  toUseItem.run();
               }
            } else {
               this.usageState = K2764d7305d3a3a597f.IDLE;
            }
            break;
         case 2:
            if (this.stateDelay-- > 0) {
               return;
            }

            this.applyRotation();
            Kd03e5f745eca318ca1.useItem(class_1268.field_5808);
            this.usageState = K2764d7305d3a3a597f.RESTORE_SLOT;
            this.stateDelay = 1;
            break;
         case 3:
            if (this.stateDelay-- > 0) {
               return;
            }

            K4600bef438dafc1a9c.mc.field_1724.method_31548().field_7545 = this.previousSlot;
            this.usageState = K2764d7305d3a3a597f.IDLE;
            this.pendingAction = null;
            this.forceUse = true;
      }

   }

   @Generated
   public class_1792 getItem() {
      return this.item;
   }

   @Generated
   public Kab8a8c478f7d0077cd getProvider() {
      return this.provider;
   }

   @Generated
   public boolean isForceUse() {
      return this.forceUse;
   }

   @Generated
   public boolean isUseRotation() {
      return this.useRotation;
   }

   @Generated
   public Keb4a85ca52fb971c57 getCustomRotation() {
      return this.customRotation;
   }

   @Generated
   public int getTargetSlot() {
      return this.targetSlot;
   }

   @Generated
   public int getPreviousSlot() {
      return this.previousSlot;
   }

   @Generated
   public int getStateDelay() {
      return this.stateDelay;
   }

   @Generated
   public Runnable getPendingAction() {
      return this.pendingAction;
   }

   @Generated
   public K2764d7305d3a3a597f getUsageState() {
      return this.usageState;
   }

   @Generated
   public Kc6dac1a9241a197c7d(class_1792 item, Kab8a8c478f7d0077cd provider) {
      this.usageState = K2764d7305d3a3a597f.IDLE;
      this.item = item;
      this.provider = provider;
   }

   @Generated
   public void setUseRotation(boolean useRotation) {
      this.useRotation = useRotation;
   }
}
