package code.miaware.client.features.modules.combat.bloodorb;

import code.miaware.api.event.events.player.world.K8d0b25c690b59e234d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.inject.input.KeyBindingStateAccess;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_3675;
import net.minecraft.class_490;
import net.minecraft.class_9334;
import net.minecraft.class_3675.class_307;
import org.lwjgl.glfw.GLFW;

public final class K1db4bba3d9962fa8a5 implements K4600bef438dafc1a9c {
   private Kbab661c4f3c8e5a07a phase;
   private Object world;
   private Object player;
   private class_490 ownedScreen;
   private class_1657 target;
   private class_1657 forecastTarget;
   private long tick;
   private long forecastUntil;
   private long nextActivation;
   private int hold;
   private boolean ownsSneak;
   private boolean predictedActivation;
   private boolean savedSneakState;
   private float forecastBefore;
   private float activeBefore;
   private long activePredictionUntil;
   private final Set<UUID> used;
   private final Map<UUID, K7a64d50d43397deabd> hits;
   private final Map<UUID, Float> factors;

   public K1db4bba3d9962fa8a5() {
      this.phase = Kbab661c4f3c8e5a07a.IDLE;
      this.used = new HashSet();
      this.hits = new HashMap();
      this.factors = new HashMap();
   }

   public boolean isBusy() {
      return this.phase != Kbab661c4f3c8e5a07a.IDLE;
   }

   public void guard() {
      if (this.isBusy() && (this.world != mc.field_1687 || this.player != mc.field_1724 || mc.field_1724 == null || !mc.field_1724.method_5805())) {
         this.reset();
      }

   }

   public void reset() {
      this.Kb29a7152871259b5c3();
      this.used.clear();
      this.hits.clear();
      this.factors.clear();
      this.forecastTarget = null;
      this.world = null;
      this.player = null;
      this.nextActivation = 0L;
   }

   private void Kb29a7152871259b5c3() {
      if (this.ownsSneak) {
         this.Kfa554e60cae6c8ebe9();
         this.ownsSneak = false;
      }

      if (this.ownedScreen != null && mc.field_1755 == this.ownedScreen && mc.field_1724 != null) {
         mc.field_1724.method_3137();
      }

      this.ownedScreen = null;
      this.target = null;
      this.phase = Kbab661c4f3c8e5a07a.IDLE;
      this.hold = 0;
   }

   public void onAttack(K8d0b25c690b59e234d event, boolean enabled, boolean manualBusy) {
      if (enabled && !manualBusy && !this.isBusy() && mc.field_1724 != null && mc.field_1687 != null && mc.field_1755 == null && this.K66fcdcfbd4b286950c() >= 0) {
         class_1297 var5 = event.entity();
         if (var5 instanceof class_1657) {
            class_1657 victim = (class_1657)var5;
            if (this.K5249537671bc4f0c62(victim)) {
               this.K88b521f04d4fed3de1();
               float estimate = K22f5c502d85cadd5fa.estimate(mc.field_1724, victim);
               float calibrated = estimate * (Float)this.factors.getOrDefault(victim.method_5667(), 1.0F);
               if (K28e7758a0f166b30e4.predictedLow(victim.method_6032(), victim.method_6067(), calibrated)) {
                  this.forecastTarget = victim;
                  this.forecastBefore = victim.method_6032();
                  this.forecastUntil = this.tick + 6L;
               }

               if (this.hits.size() >= 32) {
                  this.hits.clear();
               }

               K7a64d50d43397deabd hit = new K7a64d50d43397deabd();
               hit.target = victim;
               hit.before = victim.method_6032() + victim.method_6067();
               hit.expected = estimate;
               hit.expires = this.tick + 12L;
               this.hits.put(victim.method_5667(), hit);
               return;
            }
         }

      }
   }

   public void tick(boolean enabled, boolean manualBusy) {
      ++this.tick;
      if (!enabled) {
         if (this.world != null || this.isBusy()) {
            this.reset();
         }

      } else {
         if (this.world != mc.field_1687 || this.player != mc.field_1724) {
            this.reset();
            this.world = mc.field_1687;
            this.player = mc.field_1724;
         }

         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null && mc.field_1724.method_5805() && !mc.field_1724.method_7325()) {
            if (!mc.method_1493()) {
               if (manualBusy) {
                  this.Kb29a7152871259b5c3();
               } else if (this.isBusy()) {
                  this.K60f455d98f2756ab11();
               } else if (this.K66fcdcfbd4b286950c() < 0) {
                  this.forecastTarget = null;
                  this.hits.clear();
               } else if (mc.field_1755 == null && mc.field_1724.field_7512 == mc.field_1724.field_7498 && mc.field_1724.field_7512.method_34255().method_7960()) {
                  this.K88b521f04d4fed3de1();
                  this.used.removeIf((id) -> {
                     class_1657 p = mc.field_1687.method_18470(id);
                     return p == null || !p.method_5805() || !K28e7758a0f166b30e4.inRange(mc.field_1724.method_5858(p)) || p.method_6032() > 1.75F;
                  });
                  if (this.tick >= this.nextActivation) {
                     class_1657 selected = null;
                     double distance = Double.MAX_VALUE;

                     for(class_1657 p : mc.field_1687.method_18456()) {
                        if (this.K5249537671bc4f0c62(p) && !this.used.contains(p.method_5667()) && K28e7758a0f166b30e4.lowHealth(p.method_6032())) {
                           double d = mc.field_1724.method_5858(p);
                           if (d < distance) {
                              selected = p;
                              distance = d;
                           }
                        }
                     }

                     this.predictedActivation = false;
                     if (selected == null && this.forecastTarget != null && this.tick <= this.forecastUntil && this.K5249537671bc4f0c62(this.forecastTarget) && !this.used.contains(this.forecastTarget.method_5667()) && K28e7758a0f166b30e4.activationStillValid(this.forecastTarget.method_6032(), this.forecastBefore, true, false)) {
                        selected = this.forecastTarget;
                        this.predictedActivation = true;
                        this.activeBefore = this.forecastBefore;
                        this.activePredictionUntil = this.forecastUntil;
                     }

                     if (selected != null) {
                        this.target = selected;
                        this.forecastTarget = null;
                        this.ownedScreen = new class_490(mc.field_1724);
                        mc.method_1507(this.ownedScreen);
                        this.phase = Kbab661c4f3c8e5a07a.SWAP;
                     }
                  }
               }
            }
         } else {
            this.Kb29a7152871259b5c3();
         }
      }
   }

   private void K60f455d98f2756ab11() {
      if (this.K5249537671bc4f0c62(this.target) && mc.field_1724.field_7512 == mc.field_1724.field_7498) {
         if (this.phase != Kbab661c4f3c8e5a07a.HOLD_SNEAK && !K28e7758a0f166b30e4.activationStillValid(this.target.method_6032(), this.activeBefore, this.predictedActivation, this.tick > this.activePredictionUntil)) {
            this.Kb29a7152871259b5c3();
         } else {
            switch (this.phase.ordinal()) {
               case 1:
                  if (mc.field_1755 != this.ownedScreen || !mc.field_1724.field_7512.method_34255().method_7960()) {
                     this.Kb29a7152871259b5c3();
                     return;
                  }

                  int slot = this.K66fcdcfbd4b286950c();
                  if (slot < 0) {
                     this.Kb29a7152871259b5c3();
                     return;
                  }

                  if (slot != 45) {
                     mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slot, 40, class_1713.field_7791, mc.field_1724);
                  }

                  this.phase = Kbab661c4f3c8e5a07a.CLOSE;
                  break;
               case 2:
                  if (mc.field_1755 != this.ownedScreen || !this.K576c415f81693f1c5e(mc.field_1724.method_6079())) {
                     this.Kb29a7152871259b5c3();
                     return;
                  }

                  mc.field_1724.method_3137();
                  this.ownedScreen = null;
                  if (!mc.field_1724.method_5715() && !mc.field_1690.field_1832.method_1434()) {
                     this.phase = Kbab661c4f3c8e5a07a.PRESS_SNEAK;
                  } else {
                     this.Kcc62507cf1cb7620f5();
                     this.Kca9e2eed52ce49ce61(false);
                     this.phase = Kbab661c4f3c8e5a07a.RELEASE_SNEAK;
                  }
                  break;
               case 3:
                  this.phase = Kbab661c4f3c8e5a07a.PRESS_SNEAK;
                  break;
               case 4:
                  if (mc.field_1755 != null || !this.K576c415f81693f1c5e(mc.field_1724.method_6079())) {
                     this.Kb29a7152871259b5c3();
                     return;
                  }

                  this.Kcc62507cf1cb7620f5();
                  this.Kca9e2eed52ce49ce61(true);
                  this.hold = 2;
                  this.phase = Kbab661c4f3c8e5a07a.HOLD_SNEAK;
                  this.used.add(this.target.method_5667());
                  this.nextActivation = this.tick + 10L;
                  break;
               case 5:
                  if (mc.field_1755 != null) {
                     this.Kb29a7152871259b5c3();
                     return;
                  }

                  if (--this.hold <= 0) {
                     this.Kb29a7152871259b5c3();
                  } else {
                     this.Kca9e2eed52ce49ce61(true);
                  }
            }

         }
      } else {
         this.Kb29a7152871259b5c3();
      }
   }

   private boolean K5249537671bc4f0c62(class_1657 p) {
      return p != null && p != mc.field_1724 && p.method_37908() == mc.field_1687 && p.method_5805() && p.method_6032() > 0.0F && !p.method_7325() && !p.method_7337() && K28e7758a0f166b30e4.inRange(mc.field_1724.method_5858(p));
   }

   private void K88b521f04d4fed3de1() {
      Iterator<Map.Entry<UUID, K7a64d50d43397deabd>> iterator = this.hits.entrySet().iterator();

      while(iterator.hasNext()) {
         Map.Entry<UUID, K7a64d50d43397deabd> entry = (Map.Entry)iterator.next();
         K7a64d50d43397deabd hit = (K7a64d50d43397deabd)entry.getValue();
         if (this.K5249537671bc4f0c62(hit.target) && this.tick <= hit.expires) {
            float actual = hit.before - hit.target.method_6032() - hit.target.method_6067();
            if (actual > 0.02F) {
               if (hit.expected > 0.05F) {
                  if (this.factors.size() >= 32 && !this.factors.containsKey(entry.getKey())) {
                     this.factors.clear();
                  }

                  float ratio = Math.clamp(actual / hit.expected, 0.35F, 2.0F);
                  this.factors.merge((UUID)entry.getKey(), ratio, (old, next) -> old * 0.6F + next * 0.4F);
               }

               iterator.remove();
            } else if (actual < -0.02F) {
               iterator.remove();
            }
         } else {
            iterator.remove();
         }
      }

   }

   private int K66fcdcfbd4b286950c() {
      if (mc.field_1724 == null) {
         return -1;
      } else if (this.K576c415f81693f1c5e(mc.field_1724.method_6079())) {
         return 45;
      } else {
         for(int i = 0; i < 36; ++i) {
            if (this.K576c415f81693f1c5e(mc.field_1724.method_31548().method_5438(i))) {
               return i < 9 ? i + 36 : i;
            }
         }

         return -1;
      }
   }

   private boolean K576c415f81693f1c5e(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else {
         class_2561 name = (class_2561)stack.method_57824(class_9334.field_49631);
         return name != null && K28e7758a0f166b30e4.matchesName(name.getString());
      }
   }

   private void Kcc62507cf1cb7620f5() {
      if (!this.ownsSneak) {
         this.savedSneakState = mc.field_1690.field_1832.method_1434();
         this.ownsSneak = true;
      }

   }

   private void Kca9e2eed52ce49ce61(boolean pressed) {
      ((KeyBindingStateAccess)mc.field_1690.field_1832).lux$setPressedState(pressed);
   }

   private void Kfa554e60cae6c8ebe9() {
      if (mc.field_1690 != null && mc.method_22683() != null) {
         class_3675.class_306 key = class_3675.method_15981(mc.field_1690.field_1832.method_1428());
         boolean pressed = key.method_1442() == class_307.field_1672 ? GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), key.method_1444()) == 1 : key.method_1444() >= 0 && class_3675.method_15987(mc.method_22683().method_4490(), key.method_1444());
         this.Kca9e2eed52ce49ce61((Boolean)mc.field_1690.method_42449().method_41753() ? this.savedSneakState : pressed);
      }
   }
}
