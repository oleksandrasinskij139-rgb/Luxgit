package code.miaware.api.utils.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import org.joml.Matrix4f;

public final class K5c7f003ccf3606eac5 {
   private static final int TILE = 16;
   private final BitSet occupied = new BitSet();
   private int width;
   private int height;
   private int columns;
   private int rows;

   public void reset(int width, int height) {
      this.width = width;
      this.height = height;
      this.columns = (width + 16 - 1) / 16;
      this.rows = (height + 16 - 1) / 16;
      this.occupied.clear();
   }

   public void add(Matrix4f m, float x, float y, float w, float h) {
      if (!(w <= 0.0F) && !(h <= 0.0F)) {
         float ax = m.m00() * x + m.m10() * y + m.m30();
         float ay = m.m01() * x + m.m11() * y + m.m31();
         float bx = ax + m.m00() * w;
         float by = ay + m.m01() * w;
         float cx = ax + m.m10() * h;
         float cy = ay + m.m11() * h;
         float dx = bx + m.m10() * h;
         float dy = by + m.m11() * h;
         this.add(Math.min(Math.min(ax, bx), Math.min(cx, dx)), Math.min(Math.min(ay, by), Math.min(cy, dy)), Math.max(Math.max(ax, bx), Math.max(cx, dx)), Math.max(Math.max(ay, by), Math.max(cy, dy)));
      }
   }

   public void add(float left, float top, float right, float bottom) {
      if (Float.isFinite(left) && Float.isFinite(top) && Float.isFinite(right) && Float.isFinite(bottom) && !(right <= 0.0F) && !(bottom <= 0.0F) && !(left >= (float)this.width) && !(top >= (float)this.height) && !(right <= left) && !(bottom <= top)) {
         int x0 = Math.max(0, (int)Math.floor((double)(left / 16.0F)));
         int y0 = Math.max(0, (int)Math.floor((double)(top / 16.0F)));
         int x1 = Math.min(this.columns, (int)Math.ceil((double)(right / 16.0F)));
         int y1 = Math.min(this.rows, (int)Math.ceil((double)(bottom / 16.0F)));

         for(int row = y0; row < y1; ++row) {
            this.occupied.set(row * this.columns + x0, row * this.columns + x1);
         }

      }
   }

   public List<K6eebe516d539e8f184> regions() {
      List<K6eebe516d539e8f184> result = new ArrayList();
      int[] previous = new int[this.columns];
      int[] current = new int[this.columns];
      Arrays.fill(previous, -1);

      for(int row = 0; row < this.rows; ++row) {
         Arrays.fill(current, -1);
         int end = (row + 1) * this.columns;

         int next;
         for(int index = this.occupied.nextSetBit(row * this.columns); index >= 0 && index < end; index = this.occupied.nextSetBit(next)) {
            next = Math.min(end, this.occupied.nextClearBit(index));
            int x = (index - row * this.columns) * 16;
            int right = Math.min(this.width, (next - row * this.columns) * 16);
            int column = index - row * this.columns;
            int prior = previous[column];
            int rowHeight = Math.min(16, this.height - row * 16);
            if (prior >= 0 && ((K6eebe516d539e8f184)result.get(prior)).width() == right - x) {
               K6eebe516d539e8f184 above = (K6eebe516d539e8f184)result.get(prior);
               result.set(prior, new K6eebe516d539e8f184(x, above.y(), above.width(), above.height() + rowHeight));
               current[column] = prior;
            } else {
               current[column] = result.size();
               result.add(new K6eebe516d539e8f184(x, row * 16, right - x, rowHeight));
            }
         }

         int[] swap = previous;
         previous = current;
         current = swap;
      }

      return result;
   }
}
