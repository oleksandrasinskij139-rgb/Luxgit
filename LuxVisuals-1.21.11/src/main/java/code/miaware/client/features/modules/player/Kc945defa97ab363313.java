package code.miaware.client.features.modules.player;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.features.modules.player.freelook.K3493796debcf5e7184;

public class Kc945defa97ab363313 implements K4600bef438dafc1a9c {
   private static final K3493796debcf5e7184 state = new K3493796debcf5e7184();

   public static boolean isActive() {
      return state.active();
   }

   public static float getFreeYaw() {
      return state.yaw();
   }

   public static float getFreePitch() {
      return state.pitch();
   }

   public static void setActive(boolean state) {
      if (state && mc.field_1724 != null && !isActive()) {
         Kc945defa97ab363313.state.begin(mc.field_1724.method_36454(), mc.field_1724.method_36455());
      } else if (!state) {
         Kc945defa97ab363313.state.stop();
      }

   }

   public static void updateRotation(double deltaYaw, double deltaPitch) {
      state.rotate(deltaYaw, deltaPitch);
   }

   public static void reset() {
      state.stop();
   }
}
