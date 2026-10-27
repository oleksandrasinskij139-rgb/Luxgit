package code.miaware.client.features.modules.combat.hitbox;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_238;
import net.minecraft.class_243;

public final class Ka3966ff009ad323fe6 {
   private Ka3966ff009ad323fe6() {
   }

   public static List<K7da25e2776ad4abe8f> edges(class_238 box, boolean corners) {
      List<K7da25e2776ad4abe8f> result = new ArrayList(corners ? 24 : 12);
      double[] low = new double[]{box.field_1323, box.field_1322, box.field_1321};
      double[] high = new double[]{box.field_1320, box.field_1325, box.field_1324};

      for(int axis = 0; axis < 3; ++axis) {
         int u = (axis + 1) % 3;
         int v = (axis + 2) % 3;

         for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
               double[] a = (double[])(([D)low).clone();
               double[] b = (double[])(([D)low).clone();
               a[u] = b[u] = i == 0 ? low[u] : high[u];
               a[v] = b[v] = j == 0 ? low[v] : high[v];
               a[axis] = low[axis];
               b[axis] = high[axis];
               class_243 start = new class_243(a[0], a[1], a[2]);
               class_243 end = new class_243(b[0], b[1], b[2]);
               if (!(start.method_1025(end) < 1.0E-12)) {
                  if (corners) {
                     result.add(new K7da25e2776ad4abe8f(start, start.method_35590(end, 0.22)));
                     result.add(new K7da25e2776ad4abe8f(end, end.method_35590(start, 0.22)));
                  } else {
                     result.add(new K7da25e2776ad4abe8f(start, end));
                  }
               }
            }
         }
      }

      return result;
   }

   public static List<List<class_243>> faces(class_238 box) {
      class_243[] p = new class_243[8];

      for(int i = 0; i < 8; ++i) {
         p[i] = new class_243((i & 1) == 0 ? box.field_1323 : box.field_1320, (i & 2) == 0 ? box.field_1322 : box.field_1325, (i & 4) == 0 ? box.field_1321 : box.field_1324);
      }

      return List.of(List.of(p[0], p[1], p[5], p[4]), List.of(p[2], p[6], p[7], p[3]), List.of(p[0], p[2], p[3], p[1]), List.of(p[4], p[5], p[7], p[6]), List.of(p[0], p[4], p[6], p[2]), List.of(p[1], p[3], p[7], p[5]));
   }
}
