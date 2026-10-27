package code.miaware.client.features.modules.player.invsave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_9334;

public record Kb829f755817df4f5cd(K3d3e59b2721b64e503[] status, List<Ka90d44e8813eebf293> missing, boolean canArrange) {
   public static Kb829f755817df4f5cd analyze(List<class_1799> wanted, List<class_1799> actual) {
      int[] remaining = new int[41];
      K3d3e59b2721b64e503[] status = new K3d3e59b2721b64e503[41];
      Arrays.fill(status, K3d3e59b2721b64e503.NONE);

      for(int i = 0; i < 41; ++i) {
         remaining[i] = ((class_1799)actual.get(i)).method_7947();
      }

      ArrayList<Ka90d44e8813eebf293> missing = new ArrayList();
      boolean movable = false;
      ArrayList<Integer> order = new ArrayList();

      for(int i = 0; i < 41; ++i) {
         if (!((class_1799)wanted.get(i)).method_7960()) {
            order.add(i);
         }
      }

      order.sort(Comparator.comparing((ix) -> !((class_1799)wanted.get(ix)).method_57826(class_9334.field_49631)));

      for(int target : order) {
         class_1799 expected = (class_1799)wanted.get(target);
         int own = K775957aef270c0236c.fits(expected, (class_1799)actual.get(target)) ? Math.min(expected.method_7947(), remaining[target]) : 0;
         remaining[target] -= own;
         int deficit = expected.method_7947() - own;

         for(int source = 0; source < 41 && deficit > 0; ++source) {
            if (source != target && remaining[source] > 0 && K775957aef270c0236c.fits(expected, (class_1799)actual.get(source))) {
               int used = Math.min(deficit, remaining[source]);
               remaining[source] -= used;
               deficit -= used;
               movable = true;
            }
         }

         status[target] = deficit > 0 ? K3d3e59b2721b64e503.MISSING : (own == expected.method_7947() ? K3d3e59b2721b64e503.CORRECT : K3d3e59b2721b64e503.MISPLACED);
         if (deficit > 0) {
            int group = -1;

            for(int j = 0; j < missing.size(); ++j) {
               if (K3a0689bb2dfdb22dfd(expected, ((Ka90d44e8813eebf293)missing.get(j)).stack())) {
                  group = j;
                  break;
               }
            }

            if (group < 0) {
               missing.add(new Ka90d44e8813eebf293(expected.method_7972(), deficit));
            } else {
               Ka90d44e8813eebf293 previous = (Ka90d44e8813eebf293)missing.get(group);
               missing.set(group, new Ka90d44e8813eebf293(previous.stack(), previous.count() + deficit));
            }
         }
      }

      return new Kb829f755817df4f5cd(status, List.copyOf(missing), movable);
   }

   private static boolean K3a0689bb2dfdb22dfd(class_1799 a, class_1799 b) {
      return K775957aef270c0236c.fits(a, b) && K775957aef270c0236c.fits(b, a);
   }
}
