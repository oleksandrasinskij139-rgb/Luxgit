package code.miaware.api.utils.render;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.joml.Vector3f;

public final class K9f5942af1d8c712eab {
   private static final int MAX_QUADS = 65536;
   private static final ThreadLocal<K9f5942af1d8c712eab> LOCAL = ThreadLocal.withInitial(K9f5942af1d8c712eab::new);
   private int depth;
   private Vector3f[] vectors = new Vector3f[0];
   private Vector3f[] view = new Vector3f[0];

   public static void enter() {
      ++((K9f5942af1d8c712eab)LOCAL.get()).depth;
   }

   public static void leave() {
      --((K9f5942af1d8c712eab)LOCAL.get()).depth;
   }

   public static Vector3f[] collect(ByteBuffer buffer, int vertexCount, int strideBytes, int positionOffset) {
      K9f5942af1d8c712eab scratch = (K9f5942af1d8c712eab)LOCAL.get();
      int count = vertexCount / 4;
      if (scratch.depth == 1 && count <= 65536 && positionOffset >= 0) {
         if (scratch.vectors.length < count) {
            int capacity = Math.min(65536, Math.max(count, Math.max(16, scratch.vectors.length * 2)));
            Vector3f[] grown = (Vector3f[])Arrays.copyOf(scratch.vectors, capacity);

            for(int i = scratch.vectors.length; i < capacity; ++i) {
               grown[i] = new Vector3f();
            }

            scratch.vectors = grown;
         }

         if (scratch.view.length != count) {
            scratch.view = new Vector3f[count];
         }

         int strideFloats = strideBytes / 4;
         int base = buffer.position();

         for(int i = 0; i < count; ++i) {
            int a = base + (i * strideFloats * 4 + positionOffset) * 4;
            int b = a + strideFloats * 2 * 4;
            Vector3f center = scratch.vectors[i];
            center.set((buffer.getFloat(a) + buffer.getFloat(b)) / 2.0F, (buffer.getFloat(a + 4) + buffer.getFloat(b + 4)) / 2.0F, (buffer.getFloat(a + 8) + buffer.getFloat(b + 8)) / 2.0F);
            scratch.view[i] = center;
         }

         return scratch.view;
      } else {
         return null;
      }
   }
}
