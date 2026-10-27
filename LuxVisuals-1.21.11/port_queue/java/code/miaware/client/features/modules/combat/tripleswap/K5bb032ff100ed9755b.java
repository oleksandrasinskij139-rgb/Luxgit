package code.miaware.client.features.modules.combat.tripleswap;

import code.miaware.api.module.setting.K917aeb5cdb0f7aca26;
import code.miaware.api.utils.server.Kbe30937e40237cb7db;
import code.miaware.client.features.modules.combat.K9f996c494276981418;
import code.miaware.client.features.modules.combat.Kc233b6623d88914a28;
import code.miaware.client.ui.swap.K960d3835845d6d5dfa;
import code.miaware.client.ui.swap.Kb7a870ae80057bb597;
import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_490;
import net.minecraft.class_7923;
import net.minecraft.class_9334;

public final class K5bb032ff100ed9755b {
   private static final class_310 mc = class_310.method_1551();
   private final Kc233b6623d88914a28 module;
   private final K917aeb5cdb0f7aca26[] slots = new K917aeb5cdb0f7aca26[3];
   private final class_1799[] icons;
   private K0c3572b92ba3b09941 phase;
   private int picking;
   private int target;
   private int delay;
   private String pendingKey;
   private class_490 ownedInventory;
   private K960d3835845d6d5dfa picker;
   private Object world;
   private Object player;

   public K5bb032ff100ed9755b(Kc233b6623d88914a28 module) {
      this.icons = new class_1799[]{class_1799.field_8037, class_1799.field_8037, class_1799.field_8037};
      this.phase = K0c3572b92ba3b09941.IDLE;
      this.picking = -1;
      this.target = -1;
      this.pendingKey = "";
      this.module = module;

      for(int i = 0; i < 3; ++i) {
         this.slots[i] = (new K917aeb5cdb0f7aca26("tripleSlot" + (i + 1))).maxLength(4096).value("").setVisible(() -> false);
      }

   }

   public K917aeb5cdb0f7aca26[] settings() {
      return this.slots;
   }

   public boolean busy() {
      return this.phase != K0c3572b92ba3b09941.IDLE || this.picking >= 0 || mc.field_1755 instanceof Kb7a870ae80057bb597;
   }

   private boolean enabled() {
      return this.module.isEnabled() && this.module.isTripleSwapEnabled() && !Kbe30937e40237cb7db.isModuleBlocked(this.module);
   }

   private boolean K39af1ee87ffdf460b3() {
      return mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null && mc.field_1724.method_5805() && !mc.field_1724.method_7325() && mc.method_1569() && mc.field_1724.field_7512 == mc.field_1724.field_7498 && mc.field_1724.field_7498.method_34255().method_7960();
   }

   public void open() {
      if (this.enabled() && this.K39af1ee87ffdf460b3() && mc.field_1755 == null && !this.module.isManualSwapBusy() && !K9f996c494276981418.isMovementLocked()) {
         this.world = mc.field_1687;
         this.player = mc.field_1724;
         mc.method_1507(new Kb7a870ae80057bb597(this, this.module.tripleBind()));
      }
   }

   public void beginPick(int sector) {
      if (Kb64b8db37bafed12ae.valid(sector) && this.enabled() && this.K39af1ee87ffdf460b3() && this.phase == K0c3572b92ba3b09941.IDLE && !this.module.isManualSwapBusy()) {
         this.picking = sector;
         this.world = mc.field_1687;
         this.player = mc.field_1724;
         this.picker = new K960d3835845d6d5dfa(this, mc.field_1724);
         mc.method_1507(this.picker);
      }
   }

   public void pick(class_1799 stack) {
      if (this.picking >= 0 && stack != null && !stack.method_7960() && this.enabled() && mc.field_1755 == this.picker && this.K39af1ee87ffdf460b3()) {
         int sector = this.picking;
         this.icons[sector] = stack.method_7972();
         this.slots[sector].setValue(key(stack));
         this.picking = -1;
         this.picker = null;
         mc.method_1507(new Kb7a870ae80057bb597(this, this.module.tripleBind()));
      }
   }

   public void cancelPick() {
      this.picking = -1;
      this.picker = null;
      if (this.enabled() && this.K39af1ee87ffdf460b3()) {
         mc.method_1507(new Kb7a870ae80057bb597(this, this.module.tripleBind()));
      } else {
         mc.method_1507((class_437)null);
      }

   }

   public boolean has(int sector) {
      return Kb64b8db37bafed12ae.valid(sector) && !((String)this.slots[sector].getValue()).isEmpty();
   }

   public boolean inOffhand(int sector) {
      return this.has(sector) && mc.field_1724 != null && matches(mc.field_1724.method_6079(), (String)this.slots[sector].getValue());
   }

   public class_1799 icon(int sector) {
      if (!this.has(sector)) {
         return class_1799.field_8037;
      } else {
         String key = (String)this.slots[sector].getValue();
         if (matches(this.icons[sector], key)) {
            return this.icons[sector];
         } else {
            if (mc.field_1724 != null) {
               if (matches(mc.field_1724.method_6079(), key)) {
                  return this.icons[sector] = mc.field_1724.method_6079().method_7972();
               }

               for(int i = 0; i < 36; ++i) {
                  if (matches(mc.field_1724.method_31548().method_5438(i), key)) {
                     return this.icons[sector] = mc.field_1724.method_31548().method_5438(i).method_7972();
                  }
               }
            }

            return class_1799.field_8037;
         }
      }
   }

   public String name(int sector) {
      class_1799 icon = this.icon(sector);
      if (!icon.method_7960()) {
         return icon.method_7964().getString();
      } else if (!this.has(sector)) {
         return "Добавить";
      } else {
         String key = (String)this.slots[sector].getValue();
         int bar = key.indexOf(124);
         return bar >= 0 && bar < key.length() - 1 ? key.substring(bar + 1) : "Нет в инвентаре";
      }
   }

   public void swap(int sector) {
      if (this.has(sector) && this.enabled() && this.K39af1ee87ffdf460b3() && this.phase == K0c3572b92ba3b09941.IDLE && !this.module.isManualSwapBusy() && !K9f996c494276981418.isMovementLocked()) {
         if (mc.field_1755 == null || mc.field_1755 instanceof Kb7a870ae80057bb597) {
            if (this.inOffhand(sector)) {
               notify("Уже в левой руке", false);
            } else {
               this.pendingKey = (String)this.slots[sector].getValue();
               this.target = this.K8b32877d2f0e226f44();
               if (this.target < 0) {
                  notify("Предмет «" + this.name(sector) + "» не найден", true);
                  this.pendingKey = "";
               } else {
                  this.world = mc.field_1687;
                  this.player = mc.field_1724;
                  mc.method_1507((class_437)null);
                  this.ownedInventory = new class_490(mc.field_1724);
                  mc.method_1507(this.ownedInventory);
                  this.phase = K0c3572b92ba3b09941.SWAP;
                  this.delay = this.module.longSwapDelay() ? 2 : 1;
               }
            }
         }
      }
   }

   public void guard() {
      if (this.busy()) {
         if (this.enabled() && this.K39af1ee87ffdf460b3() && this.world == mc.field_1687 && this.player == mc.field_1724) {
            if (this.picking >= 0 && mc.field_1755 != this.picker) {
               this.picking = -1;
               this.picker = null;
            }

            if (this.phase != K0c3572b92ba3b09941.IDLE && mc.field_1755 != this.ownedInventory) {
               this.reset();
            }

         } else {
            this.reset();
         }
      }
   }

   public void tick() {
      this.guard();
      if (this.phase != K0c3572b92ba3b09941.IDLE) {
         if (this.delay > 0) {
            --this.delay;
         } else {
            if (this.phase == K0c3572b92ba3b09941.SWAP) {
               this.target = this.K8b32877d2f0e226f44();
               if (this.target < 0) {
                  notify("Предмет больше не найден", true);
                  this.reset();
                  return;
               }

               int sync = mc.field_1724.field_7498.field_7763;
               mc.field_1761.method_2906(sync, this.target, 40, class_1713.field_7791, mc.field_1724);
               if (matches(mc.field_1724.method_6079(), this.pendingKey)) {
                  notify("AutoSwap: " + mc.field_1724.method_6079().method_7964().getString(), false);
               } else {
                  notify("Свап не выполнен", true);
               }

               this.phase = K0c3572b92ba3b09941.CLOSE;
               this.delay = this.module.longSwapDelay() ? 3 : 1;
            } else {
               this.reset();
            }

         }
      }
   }

   public void reset() {
      boolean owns = mc.field_1755 == this.ownedInventory || mc.field_1755 == this.picker || mc.field_1755 instanceof Kb7a870ae80057bb597;
      if (owns && mc.field_1755 != null) {
         mc.method_1507((class_437)null);
      }

      this.phase = K0c3572b92ba3b09941.IDLE;
      this.picking = this.target = -1;
      this.delay = 0;
      this.pendingKey = "";
      this.ownedInventory = null;
      this.picker = null;
      this.world = this.player = null;
   }

   private int K8b32877d2f0e226f44() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 36; ++i) {
            if (matches(mc.field_1724.method_31548().method_5438(i), this.pendingKey)) {
               return i < 9 ? i + 36 : i;
            }
         }

         return -1;
      }
   }

   private static String key(class_1799 stack) {
      class_2561 name = (class_2561)stack.method_57824(class_9334.field_49631);
      return Kb64b8db37bafed12ae.key(class_7923.field_41178.method_10221(stack.method_7909()).toString(), name == null ? "" : name.getString());
   }

   public static boolean matches(class_1799 stack, String key) {
      return stack != null && !stack.method_7960() && key(stack).equals(key);
   }

   private static void notify(String text, boolean warning) {
      Kb7ca6c2b4209b8a116.notify(text, warning ? Ka58c9e2c4d8aa9af2e.WARNING : Ka58c9e2c4d8aa9af2e.INFO);
   }
}
