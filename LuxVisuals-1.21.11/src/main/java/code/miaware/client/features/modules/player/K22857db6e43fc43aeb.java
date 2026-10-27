package code.miaware.client.features.modules.player;

import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import net.minecraft.class_1792;

class K22857db6e43fc43aeb {
   private final String inventoryKeyword;
   private final class_1792 item;
   private final K1d3d0b4020f933282f bindSetting;
   private final String notFoundMessage;
   private final String successMessage;
   private boolean queuedUse;

   private K22857db6e43fc43aeb(String inventoryKeyword, class_1792 item, K1d3d0b4020f933282f bindSetting, String notFoundMessage, String successMessage) {
      this.inventoryKeyword = inventoryKeyword;
      this.item = item;
      this.bindSetting = bindSetting;
      this.notFoundMessage = notFoundMessage;
      this.successMessage = successMessage;
   }

   public String getInventoryKeyword() {
      return this.inventoryKeyword;
   }

   public class_1792 getItem() {
      return this.item;
   }

   public K1d3d0b4020f933282f getBindSetting() {
      return this.bindSetting;
   }

   public String getNotFoundMessage() {
      return this.notFoundMessage;
   }

   public String getSuccessMessage() {
      return this.successMessage;
   }

   public void queueUse() {
      this.queuedUse = true;
   }

   public boolean consumeQueuedUse() {
      if (!this.queuedUse) {
         return false;
      } else {
         this.queuedUse = false;
         return true;
      }
   }

   public void reset() {
      this.queuedUse = false;
   }
}
