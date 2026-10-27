package code.miaware.client.features.modules.render;

import java.util.ArrayDeque;

final class K16dfd93b08cf6dce39 {
   final ArrayDeque<Ka12296f43d5e0e0e80>[] queues;

   K16dfd93b08cf6dce39(int count) {
      this.queues = new ArrayDeque[count];

      for(int i = 0; i < count; ++i) {
         this.queues[i] = new ArrayDeque();
      }

   }
}
