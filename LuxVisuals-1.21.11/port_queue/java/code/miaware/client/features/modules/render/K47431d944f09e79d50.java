package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke9769112fe9e34d7c2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kb9fa339b3f0f7560be;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1764;
import net.minecraft.class_4587;

@ModuleRegister(
   name = "View Model",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройка положения и вида предметов в руке"
)
public class K47431d944f09e79d50 extends Kab8a8c478f7d0077cd {
   private static final K47431d944f09e79d50 instance = new K47431d944f09e79d50();
   public final Kb9e875ead00e0d03bd rightHandX = (new Kb9e875ead00e0d03bd("Правая рука X")).value(0.0F).range(-1.0F, 1.7F).step(0.05F);
   public final Kb9e875ead00e0d03bd rightHandY = (new Kb9e875ead00e0d03bd("Правая рука Y")).value(0.0F).range(-1.0F, 1.7F).step(0.05F);
   public final Kb9e875ead00e0d03bd rightHandZ = (new Kb9e875ead00e0d03bd("Правая рука Z")).value(0.0F).range(-2.5F, 2.5F).step(0.05F);
   public final Kb9e875ead00e0d03bd leftHandX = (new Kb9e875ead00e0d03bd("Левая рука X")).value(0.0F).range(-1.0F, 1.7F).step(0.05F);
   public final Kb9e875ead00e0d03bd leftHandY = (new Kb9e875ead00e0d03bd("Левая рука Y")).value(0.0F).range(-1.0F, 1.7F).step(0.05F);
   public final Kb9e875ead00e0d03bd leftHandZ = (new Kb9e875ead00e0d03bd("Левая рука Z")).value(0.0F).range(-2.5F, 2.5F).step(0.05F);
   private final Kb9fa339b3f0f7560be reset = (new Kb9fa339b3f0f7560be("Сбросить позицию")).value(this::Ked41a1a7c31c384ec0);
   private double lastAppliedX = (double)0.0F;
   private double lastAppliedY = (double)0.0F;
   private double lastAppliedZ = (double)0.0F;
   private int lastMatrixStackHash = 0;

   public K47431d944f09e79d50() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.rightHandX, this.rightHandY, this.rightHandZ, this.leftHandX, this.leftHandY, this.leftHandZ, this.reset});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 handOffsetEvent = Ke9769112fe9e34d7c2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_1306 arm = event.arm();
         class_1268 hand = event.hand();
         class_4587 matrices = event.matrices();
         int currentHash = matrices.hashCode();
         if (arm != class_1306.field_6183 || !(event.stack().method_7909() instanceof class_1764)) {
            if (currentHash == this.lastMatrixStackHash && (this.lastAppliedX != (double)0.0F || this.lastAppliedY != (double)0.0F || this.lastAppliedZ != (double)0.0F)) {
               matrices.method_22904(-this.lastAppliedX, -this.lastAppliedY, -this.lastAppliedZ);
            }

            double x = (double)0.0F;
            double y = (double)0.0F;
            double z = (double)0.0F;
            if (arm == class_1306.field_6183) {
               x = this.rightHandX.getValue().doubleValue();
               y = this.rightHandY.getValue().doubleValue();
               z = this.rightHandZ.getValue().doubleValue();
            } else if (arm == class_1306.field_6182) {
               x = this.leftHandX.getValue().doubleValue();
               y = this.leftHandY.getValue().doubleValue();
               z = this.leftHandZ.getValue().doubleValue();
            }

            matrices.method_22904(x, y, z);
            this.lastAppliedX = x;
            this.lastAppliedY = y;
            this.lastAppliedZ = z;
            this.lastMatrixStackHash = currentHash;
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{handOffsetEvent});
   }

   private void Ked41a1a7c31c384ec0() {
      this.rightHandX.setValue(0.0F);
      this.rightHandY.setValue(0.0F);
      this.rightHandZ.setValue(0.0F);
      this.leftHandX.setValue(0.0F);
      this.leftHandY.setValue(0.0F);
      this.leftHandZ.setValue(0.0F);
   }

   @Generated
   public static K47431d944f09e79d50 getInstance() {
      return instance;
   }
}
