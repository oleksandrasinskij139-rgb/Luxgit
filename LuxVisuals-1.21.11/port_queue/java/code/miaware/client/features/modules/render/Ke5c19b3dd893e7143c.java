package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5250;
import net.minecraft.class_640;

@ModuleRegister(
   name = "TabCustomizer",
   category = Kf76f042931183af5ba.RENDER,
   description = "Подсветка друзей и себя в Tab, друзья выше всех"
)
public class Ke5c19b3dd893e7143c extends Kab8a8c478f7d0077cd {
   private static final Ke5c19b3dd893e7143c instance = new Ke5c19b3dd893e7143c();
   private final K00fef91b3d4d1b3ecc showFriends = (new K00fef91b3d4d1b3ecc("Показывать друзей")).value(true);
   private final K00fef91b3d4d1b3ecc friendsOnTop = (new K00fef91b3d4d1b3ecc("Показывать выше всех")).value(true).setVisible(() -> (Boolean)this.showFriends.getValue());
   private final K00fef91b3d4d1b3ecc highlightSelf = (new K00fef91b3d4d1b3ecc("Подсветка себя")).value(true);
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(85, 255, 127, 255));

   public Ke5c19b3dd893e7143c() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.showFriends, this.friendsOnTop, this.highlightSelf, this.color});
   }

   private static boolean K83dfa2a4434181fe1b(String name) {
      if (name != null && !name.isEmpty()) {
         for(String f : K137d4e93c7270b0a04.getInstance().getData()) {
            if (name.equalsIgnoreCase(f)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean Kbfa239395419f59199(class_640 e) {
      class_310 mc = class_310.method_1551();
      return mc.field_1724 != null && e.method_2966() != null && (mc.field_1724.method_7334().getId().equals(e.method_2966().getId()) || mc.field_1724.method_7334().getName().equalsIgnoreCase(e.method_2966().getName()));
   }

   public static List<class_640> reorder(List<class_640> list) {
      if (list != null && instance.isEnabled() && (Boolean)instance.showFriends.getValue() && (Boolean)instance.friendsOnTop.getValue()) {
         List<class_640> top = new ArrayList();
         List<class_640> rest = new ArrayList(list.size());

         for(class_640 e : list) {
            boolean up = e != null && e.method_2966() != null && K83dfa2a4434181fe1b(e.method_2966().getName());
            (up ? top : rest).add(e);
         }

         if (top.isEmpty()) {
            return list;
         } else {
            top.addAll(rest);
            return top;
         }
      } else {
         return list;
      }
   }

   public static class_2561 decorate(class_640 e, class_2561 orig) {
      if (orig != null && e != null && e.method_2966() != null && instance.isEnabled()) {
         String name = e.method_2966().getName();
         boolean paint = (Boolean)instance.showFriends.getValue() && K83dfa2a4434181fe1b(name) || (Boolean)instance.highlightSelf.getValue() && Kbfa239395419f59199(e);
         if (paint && name != null && !name.isEmpty()) {
            int rgb = ((Color)instance.color.getValue()).getRGB() & 16777215;
            class_5250 out = class_2561.method_43473();
            boolean[] done = new boolean[]{false};
            orig.method_27658((style, str) -> {
               int i = done[0] ? -1 : str.indexOf(name);
               if (i < 0) {
                  out.method_10852(class_2561.method_43470(str).method_10862(style));
               } else {
                  done[0] = true;
                  if (i > 0) {
                     out.method_10852(class_2561.method_43470(str.substring(0, i)).method_10862(style));
                  }

                  out.method_10852(class_2561.method_43470(name).method_10862(style.method_36139(rgb)));
                  if (i + name.length() < str.length()) {
                     out.method_10852(class_2561.method_43470(str.substring(i + name.length())).method_10862(style));
                  }
               }

               return Optional.empty();
            }, class_2583.field_24360);
            return !done[0] ? class_2561.method_43470(orig.getString()).method_10862(orig.method_10866().method_36139(rgb)) : out;
         } else {
            return orig;
         }
      } else {
         return orig;
      }
   }

   public void onEvent() {
   }

   @Generated
   public static Ke5c19b3dd893e7143c getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getShowFriends() {
      return this.showFriends;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getFriendsOnTop() {
      return this.friendsOnTop;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHighlightSelf() {
      return this.highlightSelf;
   }

   @Generated
   public Ka52d224feda41b0761 getColor() {
      return this.color;
   }
}
