package code.miaware.client.features.modules.player.chestsorter;

import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1733;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_465;

public final class Kdd323fef6f9190020d {
   private final class_310 mc = class_310.method_1551();
   private class_1703 handler;
   private List<class_1799> expected;
   private int moved;
   private Kb08aa924c2dac14f3e flight;

   public boolean active() {
      return this.handler != null;
   }

   public Kb08aa924c2dac14f3e flight() {
      return this.flight;
   }

   public int moved() {
      return this.moved;
   }

   public void stop() {
      this.handler = null;
      this.expected = null;
   }

   public static int containerSlots(class_1703 container) {
      if (container instanceof class_1707 chest) {
         return chest.method_17388() * 9;
      } else {
         return container instanceof class_1733 ? 27 : 0;
      }
   }

   public boolean start(class_1703 container) {
      if (this.mc.field_1724 != null && this.mc.field_1761 != null && this.mc.field_1724.field_7512 == container && containerSlots(container) != 0 && container.method_34255().method_7960()) {
         this.handler = container;
         this.expected = this.K7740d66213a7b85430();
         this.moved = 0;
         this.flight = null;
         return true;
      } else {
         return false;
      }
   }

   private List<class_1799> K7740d66213a7b85430() {
      List<class_1799> copy = new ArrayList();

      for(int i = 0; i < containerSlots(this.handler); ++i) {
         copy.add(((class_1735)this.handler.field_7761.get(i)).method_7677().method_7972());
      }

      return copy;
   }

   private boolean K81801c117c2b89bbb8() {
      for(int i = 0; i < this.expected.size(); ++i) {
         if (!class_1799.method_7973((class_1799)this.expected.get(i), ((class_1735)this.handler.field_7761.get(i)).method_7677())) {
            return false;
         }
      }

      return true;
   }

   public void tick() {
      if (this.active()) {
         if (this.mc.field_1724 != null && this.mc.field_1761 != null && this.mc.field_1724.field_7512 == this.handler) {
            class_437 var2 = this.mc.field_1755;
            if (var2 instanceof class_465) {
               class_465<?> screen = (class_465)var2;
               if (screen.method_17577() == this.handler && this.handler.method_34255().method_7960() && this.K81801c117c2b89bbb8()) {
                  List<K13d4503099adf1f988> values = this.expected.stream().map(K4febcf0aa2c97b6028::value).toList();
                  Kb2d4b15c936e847c0e swap = K4febcf0aa2c97b6028.next(values, Comparator.naturalOrder());
                  if (swap == null) {
                     this.stop();
                     Kb7ca6c2b4209b8a116.notify("ChestSorter: контейнер отсортирован", Ka58c9e2c4d8aa9af2e.SUCCESS);
                     return;
                  }

                  class_1735 source = (class_1735)this.handler.field_7761.get(swap.source());
                  class_1735 target = (class_1735)this.handler.field_7761.get(swap.target());
                  class_1799 a = source.method_7677().method_7972();
                  class_1799 b = target.method_7677().method_7972();
                  if (source.method_7674(this.mc.field_1724) && target.method_7674(this.mc.field_1724) && target.method_7680(a) && (b.method_7960() || source.method_7680(b)) && target.method_7676(a) >= a.method_7947() && (b.method_7960() || source.method_7676(b) >= b.method_7947()) && !class_1799.method_31577(a, b)) {
                     this.Ke01aa8a3328e336161(source);
                     if (class_1799.method_7973(this.handler.method_34255(), a) && !source.method_7681()) {
                        this.Ke01aa8a3328e336161(target);
                        if (class_1799.method_7973(target.method_7677(), a) && class_1799.method_7973(this.handler.method_34255(), b)) {
                           if (!b.method_7960()) {
                              this.Ke01aa8a3328e336161(source);
                           }

                           if (this.handler.method_34255().method_7960() && class_1799.method_7973(source.method_7677(), b)) {
                              this.flight = new Kb08aa924c2dac14f3e(a, source.field_7873, source.field_7872, target.field_7873, target.field_7872, System.nanoTime());
                              ++this.moved;
                              this.expected = this.K7740d66213a7b85430();
                              return;
                           }

                           this.K5b9d507e5683d9a04c(source);
                           this.stop();
                           return;
                        }

                        this.K5b9d507e5683d9a04c(source);
                        this.stop();
                        return;
                     }

                     this.K5b9d507e5683d9a04c(source);
                     this.stop();
                     return;
                  }

                  this.stop();
                  return;
               }
            }
         }

         this.stop();
      }
   }

   private void Ke01aa8a3328e336161(class_1735 slot) {
      this.mc.field_1761.method_2906(this.handler.field_7763, slot.field_7874, 0, class_1713.field_7790, this.mc.field_1724);
   }

   private void K5b9d507e5683d9a04c(class_1735 source) {
      class_1799 cursor = this.handler.method_34255();
      if (!cursor.method_7960() && !source.method_7681() && source.method_7680(cursor) && source.method_7676(cursor) >= cursor.method_7947()) {
         this.Ke01aa8a3328e336161(source);
      }

   }
}
