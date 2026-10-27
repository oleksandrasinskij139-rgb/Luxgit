package code.miaware.client.features.cosmetics;

import java.util.List;
import net.minecraft.class_10055;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_591;

public class K240a370ce98295326b extends class_3887<class_10055, class_591> {
   public K240a370ce98295326b(class_3883<class_10055, class_591> context) {
      super(context);
   }

   public void render(class_4587 matrices, class_4597 vertexConsumers, int light, class_10055 state, float limbAngle, float limbDistance) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && state != null && state.field_53528 == mc.field_1724.method_5628() && !state.field_53333 && !state.field_53542) {
         K697051202eac7cfc74 manager = K697051202eac7cfc74.getInstance();
         List<Ka5dfe6b89219e29d09> models = manager.equippedModels();
         if (!models.isEmpty()) {
            Kb4880a82bc3a5e90a5 motion = state.field_53411 ? Kb4880a82bc3a5e90a5.FLYING : (state.field_53451 > 0.15F ? Kb4880a82bc3a5e90a5.MOVING : Kb4880a82bc3a5e90a5.IDLE);
            float seconds = manager.seconds();

            for(Ka5dfe6b89219e29d09 entry : models) {
               Kf70c8667f5f3361806 loaded = manager.model(entry);
               if (loaded != null) {
                  K24d5dfabcf442da65f.apply(loaded.model(), loaded.animation().pick(motion, state.field_53410, state.field_53458), seconds);
                  matrices.method_22903();
                  class_591 model = (class_591)this.method_17165();
                  Ka4846d078fcf91c1d3.apply(matrices, entry.pos == 2 ? model.field_3398 : model.field_3391, entry);
                  class_4588 vc = vertexConsumers.getBuffer(class_1921.method_23578(entry.texture));
                  loaded.model().render(matrices, vc, light, class_4608.field_21444, manager.textureOffset(entry));
                  matrices.method_22909();
               }
            }

         }
      }
   }
}
