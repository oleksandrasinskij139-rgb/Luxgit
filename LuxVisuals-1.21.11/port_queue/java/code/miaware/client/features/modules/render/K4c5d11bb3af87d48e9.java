package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_638;
import net.minecraft.class_640;

@ModuleRegister(
   name = "BetterNear",
   category = Kf76f042931183af5ba.RENDER,
   description = "Ближайшие игроки: ник, дистанция и направление"
)
public class K4c5d11bb3af87d48e9 extends Kab8a8c478f7d0077cd {
   private static final K4c5d11bb3af87d48e9 instance = new K4c5d11bb3af87d48e9();
   public final K00fef91b3d4d1b3ecc showFriends = (new K00fef91b3d4d1b3ecc("Показывать друзей")).value(true);
   private static final int MAX_PLAYERS = 5;
   private static final double MAX_DISTANCE = (double)512.0F;
   private static final long REMEMBER_MS = 30000L;
   public static final boolean SNAP_ARROWS = true;
   private final Map<UUID, K2d30cfc9532670dc97> known = new HashMap();
   private class_638 lastWorld;

   public K4c5d11bb3af87d48e9() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.showFriends});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tick = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.Kd49160560b463979a8()));
      this.addEvents(new K8d7165024ff567f5e7[]{tick});
   }

   public void onDisable() {
      this.known.clear();
   }

   private void Kd49160560b463979a8() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (mc.field_1687 != this.lastWorld) {
            this.known.clear();
            this.lastWorld = mc.field_1687;
         }

         long now = System.currentTimeMillis();

         for(K2d30cfc9532670dc97 n : this.known.values()) {
            n.live = false;
            n.entity = null;
         }

         for(class_1657 player : mc.field_1687.method_18456()) {
            if (player != mc.field_1724 && !player.method_7325()) {
               class_640 entry = mc.method_1562() != null ? mc.method_1562().method_2871(player.method_5667()) : null;
               if (entry != null) {
                  String name = player.method_5477().getString();
                  boolean friend = K137d4e93c7270b0a04.getInstance().contains(name);
                  if (!friend || (Boolean)this.showFriends.getValue()) {
                     K2d30cfc9532670dc97 n = (K2d30cfc9532670dc97)this.known.computeIfAbsent(player.method_5667(), K2d30cfc9532670dc97::new);
                     n.name = name;
                     n.pos = player.method_19538();
                     n.lastSeen = now;
                     n.live = true;
                     n.friend = friend;
                     n.entity = player;
                     if (entry != null) {
                        n.skin = entry.method_52810().comp_1626();
                     }
                  }
               }
            }
         }

         long keep = 30000L;
         Iterator<K2d30cfc9532670dc97> it = this.known.values().iterator();

         while(it.hasNext()) {
            K2d30cfc9532670dc97 n = (K2d30cfc9532670dc97)it.next();
            if (!n.live) {
               boolean online = mc.method_1562() != null && mc.method_1562().method_2871(n.uuid) != null;
               if (!online || now - n.lastSeen > keep) {
                  it.remove();
               }
            }
         }

      } else {
         this.known.clear();
      }
   }

   public List<K2d30cfc9532670dc97> nearest() {
      List<K2d30cfc9532670dc97> out = new ArrayList();
      if (mc.field_1724 == null) {
         return out;
      } else {
         class_243 me = mc.field_1724.method_19538();
         double max = (double)512.0F;

         for(K2d30cfc9532670dc97 n : this.known.values()) {
            if (n.pos != null && !(n.pos.method_1022(me) > max)) {
               out.add(n);
            }
         }

         out.sort(Comparator.comparingInt((nx) -> nx.live ? 0 : 1).thenComparingDouble((nx) -> nx.pos.method_1025(me)));
         int limit = 5;
         return (List<K2d30cfc9532670dc97>)(out.size() > limit ? new ArrayList(out.subList(0, limit)) : out);
      }
   }

   @Generated
   public static K4c5d11bb3af87d48e9 getInstance() {
      return instance;
   }
}
