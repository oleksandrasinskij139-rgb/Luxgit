package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;
import net.minecraft.class_5498;
import net.minecraft.class_638;
import net.minecraft.class_746;
import org.lwjgl.glfw.GLFW;

@ModuleRegister(
   name = "Free Look",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Позволяет смотреть что происходит вокруг вас"
)
public class K4d8e14bc1bc511ee65 extends Kab8a8c478f7d0077cd {
   private static final K4d8e14bc1bc511ee65 instance = new K4d8e14bc1bc511ee65();
   private final K1d3d0b4020f933282f key = (new K1d3d0b4020f933282f("Кнопка")).value(-1);
   private final K00fef91b3d4d1b3ecc autoThirdPerson = (new K00fef91b3d4d1b3ecc("Авто 3-е лицо")).value(true);
   private class_5498 prevPerspective;
   private boolean ownsPerspective;
   private int heldBind;
   private class_746 sessionPlayer;
   private class_638 sessionWorld;

   public K4d8e14bc1bc511ee65() {
      this.prevPerspective = class_5498.field_26664;
      this.heldBind = -1;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.key, this.autoThirdPerson});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.action() == 0 && event.matchesBind(this.heldBind)) {
            this.K28a035e70f1aa46dec();
         } else {
            if (event.action() == 1 && event.matchesBind((Integer)this.key.getValue()) && this.K3bcf62f1ce0bb705ee() && !Kc945defa97ab363313.isActive()) {
               this.heldBind = (Integer)this.key.getValue();
               this.sessionPlayer = mc.field_1724;
               this.sessionWorld = mc.field_1687;
               Kc945defa97ab363313.setActive(true);
               if ((Boolean)this.autoThirdPerson.getValue()) {
                  this.prevPerspective = mc.field_1690.method_31044();
                  mc.field_1690.method_31043(class_5498.field_26665);
                  this.ownsPerspective = true;
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, K2a56ae9ebefcb944e3.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.isFreeLookActive()))});
   }

   public void onDisable() {
      super.onDisable();
      this.K28a035e70f1aa46dec();
   }

   public boolean isFreeLookActive() {
      if (!Kc945defa97ab363313.isActive()) {
         return false;
      } else if (this.isEnabled() && this.K3bcf62f1ce0bb705ee() && mc.field_1724 == this.sessionPlayer && mc.field_1687 == this.sessionWorld && (Integer)this.key.getValue() == this.heldBind && this.K1bf821a77073e9e2bd() && (!this.ownsPerspective || mc.field_1690.method_31044() == class_5498.field_26665)) {
         return true;
      } else {
         this.K28a035e70f1aa46dec();
         return false;
      }
   }

   public void onEnable() {
      this.K28a035e70f1aa46dec();
   }

   private boolean K3bcf62f1ce0bb705ee() {
      return mc.field_1724 != null && mc.field_1687 != null && mc.field_1724.method_5805() && mc.field_1755 == null && mc.method_1569() && mc.field_1729.method_1613() && mc.method_1560() == mc.field_1724;
   }

   private boolean K1bf821a77073e9e2bd() {
      long window = mc.method_22683().method_4490();
      if (this.heldBind >= 32 && this.heldBind <= 348) {
         return GLFW.glfwGetKey(window, this.heldBind) == 1;
      } else {
         int mouse = this.heldBind + 100;
         return this.heldBind <= -93 && this.heldBind >= -100 && GLFW.glfwGetMouseButton(window, mouse) == 1;
      }
   }

   private void K28a035e70f1aa46dec() {
      Kc945defa97ab363313.reset();
      if (this.ownsPerspective && mc.field_1690 != null && mc.field_1690.method_31044() == class_5498.field_26665) {
         mc.field_1690.method_31043(this.prevPerspective);
      }

      this.ownsPerspective = false;
      this.heldBind = -1;
      this.sessionPlayer = null;
      this.sessionWorld = null;
   }

   public float getSavedYaw() {
      return Kc945defa97ab363313.getFreeYaw();
   }

   public float getSavedPitch() {
      return Kc945defa97ab363313.getFreePitch();
   }

   @Generated
   public static K4d8e14bc1bc511ee65 getInstance() {
      return instance;
   }
}
