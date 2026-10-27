package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import java.util.function.Supplier;
import lombok.Generated;

@ModuleRegister(
   name = "Tape Mouse",
   category = Kf76f042931183af5ba.OTHER,
   description = "Зажимает кнопки мыши автоматически"
)
public class Kd27924012e2cda521b extends Kab8a8c478f7d0077cd {
   private static final Kd27924012e2cda521b instance = new Kd27924012e2cda521b();
   private final Ka4b85e821e3749c3d9 actions = (new Ka4b85e821e3749c3d9("Actions")).value((new K00fef91b3d4d1b3ecc("Attack")).value(true), (new K00fef91b3d4d1b3ecc("Use")).value(false));
   private final Supplier<Boolean> isAttack = () -> this.actions.isEnabled("Attack");
   private final Supplier<Boolean> isUse = () -> this.actions.isEnabled("Use");
   private final Kb9e875ead00e0d03bd attackDelay;
   private final Kb9e875ead00e0d03bd useDelay;
   private final Kf39dc27225f4d9fe6e attackTimer;
   private final Kf39dc27225f4d9fe6e useTimer;

   public Kd27924012e2cda521b() {
      this.attackDelay = (new Kb9e875ead00e0d03bd("Attack delay")).value(10.0F).range(1.0F, 20.0F).step(1.0F).setVisible(this.isAttack);
      this.useDelay = (new Kb9e875ead00e0d03bd("Use delay")).value(10.0F).range(1.0F, 20.0F).step(1.0F).setVisible(this.isUse);
      this.attackTimer = new Kf39dc27225f4d9fe6e();
      this.useTimer = new Kf39dc27225f4d9fe6e();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.actions, this.attackDelay, this.useDelay});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.isAttack.get()) {
            this.K7aa30930c49a450c33(this.attackDelay.getValue(), this.attackTimer, () -> mc.method_1536());
         }

         if ((Boolean)this.isUse.get()) {
            this.K7aa30930c49a450c33(this.useDelay.getValue(), this.useTimer, () -> mc.method_1583());
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   private void K7aa30930c49a450c33(float delay, Kf39dc27225f4d9fe6e timer, Runnable run) {
      if (timer.finished(delay * 50.0F)) {
         run.run();
         timer.reset();
      }

   }

   @Generated
   public static Kd27924012e2cda521b getInstance() {
      return instance;
   }
}
