package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.client.features.modules.player.chestsorter.Kdd323fef6f9190020d;
import lombok.Generated;
import net.minecraft.class_1703;
import net.minecraft.class_1733;
import net.minecraft.class_2248;
import net.minecraft.class_2281;
import net.minecraft.class_2336;
import net.minecraft.class_2338;
import net.minecraft.class_2480;
import net.minecraft.class_3708;
import net.minecraft.class_638;

@ModuleRegister(
   name = "ChestSorter",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Сортировка сундуков и шалкеров"
)
public final class K1cb52ce4c79b2f47f0 extends Kab8a8c478f7d0077cd {
   private static final K1cb52ce4c79b2f47f0 instance = new K1cb52ce4c79b2f47f0();
   private final Kdd323fef6f9190020d sorter = new Kdd323fef6f9190020d();
   private class_638 world;
   private class_1703 authorized;
   private long openedBlock;
   private boolean openedShulker;

   public void onEnable() {
      this.world = mc.field_1687;
      this.authorized = null;
      this.openedBlock = 0L;
   }

   public void onDisable() {
      this.sorter.stop();
      this.authorized = null;
      this.openedBlock = 0L;
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.world != mc.field_1687) {
            this.world = mc.field_1687;
            this.sorter.stop();
            this.authorized = null;
            this.openedBlock = 0L;
         }

         if (mc.field_1724 == null || mc.field_1724.field_7512 != this.authorized) {
            this.authorized = null;
         }

         this.sorter.tick();
      }))});
   }

   public void openingBlock(class_2338 position) {
      if (this.isEnabled() && mc.field_1687 != null) {
         class_2248 block = mc.field_1687.method_8320(position).method_26204();
         this.openedShulker = block instanceof class_2480;
         this.openedBlock = !(block instanceof class_2281) && !(block instanceof class_2336) && !(block instanceof class_3708) && !this.openedShulker ? 0L : System.nanoTime();
         this.world = mc.field_1687;
      }
   }

   public boolean canSort(class_1703 handler) {
      if (this.isEnabled() && Kdd323fef6f9190020d.containerSlots(handler) != 0) {
         if (handler == this.authorized) {
            return true;
         } else if (this.openedBlock != 0L && mc.field_1687 == this.world && System.nanoTime() - this.openedBlock <= 5000000000L) {
            if (this.openedShulker != (handler instanceof class_1733)) {
               return false;
            } else {
               this.authorized = handler;
               this.openedBlock = 0L;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Generated
   public static K1cb52ce4c79b2f47f0 getInstance() {
      return instance;
   }

   @Generated
   public Kdd323fef6f9190020d getSorter() {
      return this.sorter;
   }
}
