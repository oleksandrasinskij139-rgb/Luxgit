package code.miaware.client.util.render.effects;

import net.minecraft.class_2960;

public record K0b86f0877e6ecce4fe(float[] vertices, class_2960 atlas) {
   public int count() {
      return this.vertices.length / 7;
   }
}
