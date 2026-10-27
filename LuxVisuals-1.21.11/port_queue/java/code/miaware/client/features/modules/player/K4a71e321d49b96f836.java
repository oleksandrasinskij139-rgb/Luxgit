package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

@ModuleRegister(
   name = "AutoInvest",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически инвестирует монеты в клан"
)
public class K4a71e321d49b96f836 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K4a71e321d49b96f836 instance = new K4a71e321d49b96f836();
   private final Kb9e875ead00e0d03bd amount = (new Kb9e875ead00e0d03bd("Сумма")).value(15000.0F).range(100.0F, 1000000.0F).step(100.0F);
   private final Kb9e875ead00e0d03bd delay = (new Kb9e875ead00e0d03bd("Задержка (тики)")).value(10.0F).range(10.0F, 100.0F).step(1.0F);
   private int tickCounter = 0;

   public K4a71e321d49b96f836() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.amount, this.delay});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            ++this.tickCounter;
            if (this.tickCounter >= this.delay.getValue().intValue()) {
               this.K96117139bba53f1214();
               this.tickCounter = 0;
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent});
   }

   private void K96117139bba53f1214() {
      if (mc.field_1724 != null) {
         int investAmount = this.amount.getValue().intValue();
         String command = "/clan invest " + investAmount;
         mc.field_1724.field_3944.method_45729(command);
      }
   }

   public void onEnable() {
      super.onEnable();
      this.tickCounter = 0;
   }

   public void onDisable() {
      super.onDisable();
      this.tickCounter = 0;
   }

   @Generated
   public static K4a71e321d49b96f836 getInstance() {
      return instance;
   }
}
