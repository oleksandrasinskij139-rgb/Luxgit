package code.miaware.api.utils.combat;

import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.List;
import net.minecraft.class_1309;

public class Kbbe454d0235a8ec5cb {
   public final class_1309 target;
   public final Keb4a85ca52fb971c57 rotation;
   public final float distance;
   public final float hitboxExpand;
   public final boolean onlyCrits;
   public final boolean smartCrits;
   public final boolean raytrace;
   public final boolean shieldBreak;
   public final boolean packetShieldBreak;
   public final boolean alwaysShield;
   public final boolean resetSprint;
   public final boolean ignoreWalls;
   public final boolean noAttackIfEat;
   public final boolean syncTps;

   public Kbbe454d0235a8ec5cb(class_1309 target, Keb4a85ca52fb971c57 rotation, float distance, float hitboxExpand, boolean onlyCrits, boolean smartCrits, boolean raytrace, boolean shieldBreak, boolean packetShieldBreak, boolean alwaysShield, boolean resetSprint, boolean ignoreWalls, boolean noAttackIfEat, boolean syncTps) {
      this.target = target;
      this.rotation = rotation;
      this.distance = distance;
      this.hitboxExpand = hitboxExpand;
      this.onlyCrits = onlyCrits;
      this.smartCrits = smartCrits;
      this.raytrace = raytrace;
      this.shieldBreak = shieldBreak;
      this.packetShieldBreak = packetShieldBreak;
      this.alwaysShield = alwaysShield;
      this.resetSprint = resetSprint;
      this.ignoreWalls = ignoreWalls;
      this.noAttackIfEat = noAttackIfEat;
      this.syncTps = syncTps;
   }

   public Kbbe454d0235a8ec5cb(class_1309 target, Keb4a85ca52fb971c57 rotation, float distance, float hitboxExpand, List<String> options) {
      this(target, rotation, distance, hitboxExpand, options, false);
   }

   public Kbbe454d0235a8ec5cb(class_1309 target, Keb4a85ca52fb971c57 rotation, float distance, float hitboxExpand, List<String> options, boolean syncTps) {
      this(target, rotation, distance, hitboxExpand, options.contains("Only crits"), options.contains("Smart crits"), options.contains("Raytrace"), options.contains("Shield break"), options.contains("Packet shield break"), options.contains("Unblock shield"), options.contains("Reset sprint"), options.contains("Through walls"), options.contains("No attack while eating"), syncTps);
   }

   public Kbbe454d0235a8ec5cb(class_1309 target, Keb4a85ca52fb971c57 rotation, float distance, List<String> options) {
      this(target, rotation, distance, 0.0F, options);
   }
}
