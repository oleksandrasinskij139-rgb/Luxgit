package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.awt.Color;
import lombok.Generated;

@ModuleRegister(
   name = "PlayerHealth",
   category = Kf76f042931183af5ba.RENDER,
   description = "Полоска здоровья над игроками"
)
public class K983de48a5ef1d9f665 extends Kab8a8c478f7d0077cd {
   private static final K983de48a5ef1d9f665 instance = new K983de48a5ef1d9f665();
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(255, 255, 255, 255));

   public K983de48a5ef1d9f665() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.color});
   }

   public void onEvent() {
   }

   @Generated
   public static K983de48a5ef1d9f665 getInstance() {
      return instance;
   }

   @Generated
   public Ka52d224feda41b0761 getColor() {
      return this.color;
   }
}
