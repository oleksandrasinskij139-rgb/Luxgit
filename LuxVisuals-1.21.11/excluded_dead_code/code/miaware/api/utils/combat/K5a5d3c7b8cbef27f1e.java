package code.miaware.api.utils.combat;

import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import lombok.Generated;

public class K5a5d3c7b8cbef27f1e {
   public static final String ONLY_CRITS = "Only crits";
   public static final String SMART_CRITS = "Smart crits";
   public static final String RAYTRACE = "Raytrace";
   public static final String SHIELD_BREAK = "Shield break";
   public static final String PACKET_SHIELD_BREAK = "Packet shield break";
   public static final String UNBLOCK = "Unblock shield";
   public static final String RESET_SPRINT = "Reset sprint";
   public static final String THROUGH_WALLS = "Through walls";
   public static final String NO_ATTACK_EAT = "No attack while eating";
   private final K48861d9d76e5aa971f combatManager = new K48861d9d76e5aa971f();
   private final Ka4b85e821e3749c3d9 options = (new Ka4b85e821e3749c3d9("Options")).value((new K00fef91b3d4d1b3ecc("Only crits")).value(true), (new K00fef91b3d4d1b3ecc("Smart crits")).value(true).setVisible(() -> this.options().isEnabled("Only crits")), (new K00fef91b3d4d1b3ecc("Raytrace")).value(true), (new K00fef91b3d4d1b3ecc("Shield break")).value(true), (new K00fef91b3d4d1b3ecc("Packet shield break")).value(false).setVisible(() -> this.options().isEnabled("Shield break")), (new K00fef91b3d4d1b3ecc("Unblock shield")).value(false), (new K00fef91b3d4d1b3ecc("Reset sprint")).value(true), (new K00fef91b3d4d1b3ecc("Through walls")).value(false), (new K00fef91b3d4d1b3ecc("No attack while eating")).value(true));

   public void performAttack() {
      this.combatManager.handleAttack();
   }

   @Generated
   public K48861d9d76e5aa971f combatManager() {
      return this.combatManager;
   }

   @Generated
   public Ka4b85e821e3749c3d9 options() {
      return this.options;
   }
}
