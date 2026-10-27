package code.miaware.client.features.modules.player.invsave;

import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_490;

public final class K7f83bd6cb90ae8d223 {
   private final class_310 mc = class_310.method_1551();
   private class_1723 handler;
   private K9e64dd2dd1a3d84dbb layout;
   private List<class_1799> expected;
   private final class_1735[] slots = new class_1735[41];
   private int steps;

   public boolean active() {
      return this.handler != null;
   }

   public void stop() {
      this.handler = null;
      this.layout = null;
      this.expected = null;
      Arrays.fill(this.slots, (Object)null);
   }

   private boolean K235ff50da88e7fa8bc() {
      return this.handler != null && this.mc.field_1724 != null && this.mc.field_1761 != null && this.mc.field_1724.field_7512 == this.handler && this.mc.field_1724.field_7498 == this.handler && this.mc.field_1755 != null && this.mc.field_1755.getClass() == class_490.class && this.mc.field_1724.method_5805();
   }

   public boolean start(K9e64dd2dd1a3d84dbb saved) {
      this.stop();
      if (saved != null && this.mc.field_1724 != null) {
         this.handler = this.mc.field_1724.field_7498;
         if (this.K235ff50da88e7fa8bc() && this.handler.method_34255().method_7960()) {
            for(class_1735 slot : this.handler.field_7761) {
               if (slot.field_7871 == this.mc.field_1724.method_31548() && slot.method_34266() >= 0 && slot.method_34266() < 41) {
                  this.slots[slot.method_34266()] = slot;
               }
            }

            for(class_1735 slot : this.slots) {
               if (slot == null) {
                  this.stop();
                  return false;
               }
            }

            this.layout = saved;
            this.expected = this.K8e8061c7569a13fca5();
            this.steps = 0;
            return true;
         } else {
            this.stop();
            return false;
         }
      } else {
         return false;
      }
   }

   private List<class_1799> K8e8061c7569a13fca5() {
      ArrayList<class_1799> copy = new ArrayList(41);

      for(class_1735 slot : this.slots) {
         copy.add(slot.method_7677().method_7972());
      }

      return copy;
   }

   private boolean K3ab6e102bf1ff207ee(List<class_1799> snapshot) {
      for(int i = 0; i < 41; ++i) {
         if (!class_1799.method_7973((class_1799)snapshot.get(i), this.slots[i].method_7677())) {
            return false;
         }
      }

      return true;
   }

   private boolean Kef69d480b2d40367ba(int index, class_1799 stack) {
      return stack.method_7960() || this.slots[index].method_7680(stack) && this.slots[index].method_7676(stack) >= stack.method_7947();
   }

   public void tick(K9e64dd2dd1a3d84dbb selected) {
      if (this.active()) {
         if (this.K235ff50da88e7fa8bc() && selected == this.layout && this.handler.method_34255().method_7960() && this.K3ab6e102bf1ff207ee(this.expected) && ++this.steps <= 160) {
            K8ee6f9291ec95d69c2 move = Kb13d26191b3efb7ba6.next(this.layout.slots(), this.expected, new K60ca82cc92a7fce3e8() {
               public boolean canTake(int i) {
                  return K7f83bd6cb90ae8d223.this.slots[i].method_7674(K7f83bd6cb90ae8d223.this.mc.field_1724);
               }

               public boolean accepts(int i, class_1799 stack) {
                  return K7f83bd6cb90ae8d223.this.Kef69d480b2d40367ba(i, stack);
               }
            });
            if (move == null) {
               this.stop();
               Kb7ca6c2b4209b8a116.notify("InvSave: доступные предметы разложены", Ka58c9e2c4d8aa9af2e.SUCCESS);
            } else {
               int source = move.source();
               int target = move.target();
               class_1799 a = ((class_1799)this.expected.get(source)).method_7972();
               class_1799 b = ((class_1799)this.expected.get(target)).method_7972();
               if (!this.Kbd95bdc31eb67ec742(source, 0, a, class_1799.field_8037)) {
                  this.Kdb73fc92668ae422cd(source);
               } else {
                  if (move.exchange()) {
                     if (!this.Kbd95bdc31eb67ec742(target, 0, b, a)) {
                        this.Kdb73fc92668ae422cd(source);
                        return;
                     }

                     if (!this.Kbd95bdc31eb67ec742(source, 0, class_1799.field_8037, b)) {
                        this.Kdb73fc92668ae422cd(source);
                        return;
                     }
                  } else if (move.amount() == a.method_7947()) {
                     if (!this.Kbd95bdc31eb67ec742(target, 0, class_1799.field_8037, a.method_46651(b.method_7947() + a.method_7947()))) {
                        this.Kdb73fc92668ae422cd(source);
                        return;
                     }
                  } else {
                     for(int n = 1; n <= move.amount(); ++n) {
                        if (!this.Kbd95bdc31eb67ec742(target, 1, a.method_46651(a.method_7947() - n), a.method_46651(b.method_7947() + n))) {
                           this.Kdb73fc92668ae422cd(source);
                           return;
                        }
                     }

                     class_1799 remainder = a.method_46651(a.method_7947() - move.amount());
                     if (!this.Kbd95bdc31eb67ec742(source, 0, class_1799.field_8037, remainder)) {
                        this.Kdb73fc92668ae422cd(source);
                        return;
                     }
                  }

               }
            }
         } else {
            this.stop();
         }
      }
   }

   private boolean Kbd95bdc31eb67ec742(int index, int button, class_1799 cursorAfter, class_1799 slotAfter) {
      if (this.K235ff50da88e7fa8bc() && this.K3ab6e102bf1ff207ee(this.expected)) {
         this.mc.field_1761.method_2906(this.handler.field_7763, this.slots[index].field_7874, button, class_1713.field_7790, this.mc.field_1724);
         this.expected.set(index, slotAfter.method_7972());
         return this.K235ff50da88e7fa8bc() && this.K3ab6e102bf1ff207ee(this.expected) && class_1799.method_7973(this.handler.method_34255(), cursorAfter);
      } else {
         return false;
      }
   }

   private void Kdb73fc92668ae422cd(int source) {
      if (this.K235ff50da88e7fa8bc()) {
         class_1799 cursor = this.handler.method_34255();
         if (!cursor.method_7960() && !this.slots[source].method_7681() && this.Kef69d480b2d40367ba(source, cursor)) {
            this.mc.field_1761.method_2906(this.handler.field_7763, this.slots[source].field_7874, 0, class_1713.field_7790, this.mc.field_1724);
         }
      }

      this.stop();
      Kb7ca6c2b4209b8a116.notify("InvSave: перекладка остановлена, проверь инвентарь", Ka58c9e2c4d8aa9af2e.INFO);
   }
}
