package code.miaware.client.services;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K256ab2bf60c15ab260;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.client.K8f3417125c2e5b6c2e;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.event.events.other.Kd85c1184f6b82de9cb;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.system.client.K908217fb7543df2740;
import code.miaware.api.system.configs.Kae5f6f8dfd322901c3;
import code.miaware.api.system.configs.Kc8165f59ce078825eb;
import code.miaware.api.system.draggable.Kd8969ae8f12d70edf3;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K37c6e69e88a6980262;
import code.miaware.api.utils.other.K53100e3617e2b791ec;
import lombok.Generated;
import net.minecraft.class_408;

public class Kf479eb8a461436e337 implements K4600bef438dafc1a9c {
   private static final Kf479eb8a461436e337 instance = new Kf479eb8a461436e337();

   public void load() {
      this.K3412559329bfb71540();
      this.K6592d2e3e72e4c2701();
      this.Ka697e22d307dcd48bd();
      this.K509c580f9e8b56c4a4();
      this.K21a93df6f5cf6d341e();
   }

   private void K21a93df6f5cf6d341e() {
      Kd85c1184f6b82de9cb.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> K53100e3617e2b791ec.drawButton(event)));
   }

   private void K509c580f9e8b56c4a4() {
      Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         K37c6e69e88a6980262.tick();
         Kc8165f59ce078825eb.getInstance().fetchSkin();
      }));
   }

   private void Ka697e22d307dcd48bd() {
      K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1755 instanceof class_408) {
            Kd8969ae8f12d70edf3.getInstance().getDraggables().forEach((s, draggable) -> {
               if (draggable.getModule().isEnabled()) {
                  draggable.onDraw();
               }

            });
            Kd8969ae8f12d70edf3.getInstance().updateGuides();
            Kd8969ae8f12d70edf3.getInstance().renderGuides(event.matrixStack());
         }

         K908217fb7543df2740.getInstance().update(event.context());
      }));
   }

   private void K3412559329bfb71540() {
      K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.action() == 1 && event.key() != -999 && event.key() != -1) {
            int action = event.action();
            int key = event.key() + (event.mouse() ? -100 : 0);
            if (mc.field_1755 == null) {
               K5c460ed4a42f0797d6.getInstance().getModules().forEach((module) -> {
                  int bind = module.getBind();
                  if (bind == key && module.hasBind()) {
                     module.toggle();
                  }

               });
               Kae5f6f8dfd322901c3.getInstance().onKeyPressed(key);
            }

         }
      }));
   }

   private void K6592d2e3e72e4c2701() {
      K8f3417125c2e5b6c2e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1755 == null) {
            int scrollKey = -1;
            if (event.verticalAmount() > (double)0.0F) {
               scrollKey = -200;
            } else if (event.verticalAmount() < (double)0.0F) {
               scrollKey = -201;
            }

            if (scrollKey != -1) {
               K6449169e9fd0c18e12.getInstance().call(new K256ab2bf60c15ab260(scrollKey, 1, false));
               K6449169e9fd0c18e12.getInstance().call(new K256ab2bf60c15ab260(scrollKey, 0, false));
            }

         }
      }));
   }

   @Generated
   public static Kf479eb8a461436e337 getInstance() {
      return instance;
   }
}
