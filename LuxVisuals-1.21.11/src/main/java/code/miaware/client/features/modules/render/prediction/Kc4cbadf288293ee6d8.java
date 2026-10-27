package code.miaware.client.features.modules.render.prediction;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_243;

public final class Kc4cbadf288293ee6d8 {
   public static K9e53644071e19ed440 trace(class_243 start, class_243 velocity, double gravity, double waterDrag, Kc9d7fb9704003de4b6 world, int maxTicks, double bottom) {
      List<class_243> path = new ArrayList();
      path.add(start);
      class_243 pos = start;

      for(int tick = 0; tick < maxTicks; ++tick) {
         class_243 next = pos.method_1019(velocity);
         Kf199c7e91110f107cc hit = world.hit(pos, next);
         if (hit != null) {
            path.add(hit.position());
            double fraction = velocity.method_1033() > 1.0E-8 ? Math.min((double)1.0F, pos.method_1022(hit.position()) / velocity.method_1033()) : (double)0.0F;
            return new K9e53644071e19ed440(List.copyOf(path), hit, (double)tick + fraction);
         }

         path.add(next);
         if (next.field_1351 < bottom) {
            break;
         }

         velocity = velocity.method_1021(world.water(next) ? waterDrag : 0.99).method_1031((double)0.0F, -gravity, (double)0.0F);
         pos = next;
      }

      return new K9e53644071e19ed440(List.copyOf(path), (Kf199c7e91110f107cc)null, (double)(path.size() - 1));
   }
}
