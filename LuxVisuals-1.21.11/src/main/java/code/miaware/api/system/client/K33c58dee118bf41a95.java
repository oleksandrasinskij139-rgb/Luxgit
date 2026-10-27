package code.miaware.api.system.client;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class K33c58dee118bf41a95 {
   private static final K33c58dee118bf41a95 instance = new K33c58dee118bf41a95();
   private final List<K6ac827b634fa8bac43> waypoints = new ArrayList();

   public void addWaypoint(String name, int x, int y, int z) {
      this.waypoints.add(new K6ac827b634fa8bac43(name, x, y, z));
   }

   public void removeWaypoint(String name) {
      this.waypoints.removeIf((waypoint) -> waypoint.getName().equalsIgnoreCase(name));
   }

   public void clearWaypoints() {
      this.waypoints.clear();
   }

   public K6ac827b634fa8bac43 getWaypoint(String name) {
      return (K6ac827b634fa8bac43)this.waypoints.stream().filter((waypoint) -> waypoint.getName().equalsIgnoreCase(name)).findFirst().orElse((Object)null);
   }

   @Generated
   public static K33c58dee118bf41a95 getInstance() {
      return instance;
   }

   @Generated
   public List<K6ac827b634fa8bac43> getWaypoints() {
      return this.waypoints;
   }
}
