package code.miaware.client.features.modules.player.invsave;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_9334;

public final class Kb13d26191b3efb7ba6 {
   public static K8ee6f9291ec95d69c2 next(List<class_1799> wanted, List<class_1799> actual, K60ca82cc92a7fce3e8 access) {
      for(int target = 0; target < 41; ++target) {
         class_1799 expected = (class_1799)wanted.get(target);
         class_1799 current = (class_1799)actual.get(target);
         if (!expected.method_7960()) {
            boolean matching = K775957aef270c0236c.fits(expected, current);
            int need = expected.method_7947() - (matching ? current.method_7947() : 0);
            if (need > 0) {
               ArrayList<Integer> donors = new ArrayList();

               for(int i = 0; i < 41; ++i) {
                  donors.add(i);
               }

               if (!expected.method_57826(class_9334.field_49631)) {
                  donors.sort(Comparator.comparing((ix) -> ((class_1799)actual.get(ix)).method_57826(class_9334.field_49631)));
               }

               for(int source : donors) {
                  class_1799 donor = (class_1799)actual.get(source);
                  if (source != target && K775957aef270c0236c.fits(expected, donor) && access.canTake(source)) {
                     boolean reclaimNamed = expected.method_57826(class_9334.field_49631) && !((class_1799)wanted.get(source)).method_57826(class_9334.field_49631);
                     int reserved = !reclaimNamed && K775957aef270c0236c.fits((class_1799)wanted.get(source), donor) ? ((class_1799)wanted.get(source)).method_7947() : 0;
                     int available = donor.method_7947() - reserved;
                     if (available > 0) {
                        if (!current.method_7960() && !class_1799.method_31577(current, donor)) {
                           if (!matching && access.canTake(target)) {
                              if (available == donor.method_7947() && access.accepts(target, donor) && access.accepts(source, current)) {
                                 return new K8ee6f9291ec95d69c2(source, target, donor.method_7947(), true);
                              }

                              for(int empty = 0; empty < 36; ++empty) {
                                 if (empty != source && ((class_1799)actual.get(empty)).method_7960() && access.accepts(empty, current)) {
                                    return new K8ee6f9291ec95d69c2(target, empty, current.method_7947(), false);
                                 }
                              }
                           }
                        } else {
                           int amount = Math.min(Math.min(need, available), donor.method_7914() - current.method_7947());
                           if (amount > 0) {
                              class_1799 result = donor.method_46651(current.method_7947() + amount);
                              if (access.accepts(target, result)) {
                                 return new K8ee6f9291ec95d69c2(source, target, amount, false);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }
}
