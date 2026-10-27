package code.miaware.api.module;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.render.fonts.Icons;
import lombok.Generated;

public enum Kf76f042931183af5ba {
   COMBAT("Combat", Icons.COMBAT_ICON),
   MOVEMENT("Movement", Icons.MOVEMENT_ICON),
   RENDER("Render", Icons.RENDER_ICON),
   PLAYER("Player", Icons.PLAYER_ICON),
   OTHER("Other", Icons.OTHER_ICON),
   COSMETICS("Cosmetics", Icons.OTHER_ICON);

   private final String label;
   private final Icons icon;

   public String getDisplayLabel() {
      return Ka35e7d6b31db9984f1.getInstance().translate(this.label);
   }

   @Generated
   public String getLabel() {
      return this.label;
   }

   @Generated
   public Icons getIcon() {
      return this.icon;
   }

   @Generated
   private Kf76f042931183af5ba(final String label, final Icons icon) {
      this.label = label;
      this.icon = icon;
   }

   // $FF: synthetic method
   private static Kf76f042931183af5ba[] $values() {
      return new Kf76f042931183af5ba[]{COMBAT, MOVEMENT, RENDER, PLAYER, OTHER, COSMETICS};
   }
}
