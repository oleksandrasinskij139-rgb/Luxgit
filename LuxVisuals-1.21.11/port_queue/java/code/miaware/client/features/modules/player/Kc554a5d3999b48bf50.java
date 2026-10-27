package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_476;

@ModuleRegister(
   name = "RWJoiner",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматический вход на выбранный гриф"
)
public class Kc554a5d3999b48bf50 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kc554a5d3999b48bf50 instance = new Kc554a5d3999b48bf50();
   private final Kb9e875ead00e0d03bd grifNumber = (new Kb9e875ead00e0d03bd("Номер грифа")).value(1.0F).range(1.0F, 45.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd delaySeconds = (new Kb9e875ead00e0d03bd("Задержка (сек)")).value(5.0F).range(0.5F, 20.0F).step(0.5F);
   private K3a285c31dc955564e5 currentState;
   private long lastActionTime;
   private static final long ACTION_DELAY = 50L;
   private int compassSlot;
   private String initialWorld;
   private int previousSlot;
   private long slotSwitchTime;
   private long serverCooldownTime;
   private static final long SERVER_COOLDOWN_MS = 500L;

   public Kc554a5d3999b48bf50() {
      this.currentState = K3a285c31dc955564e5.IDLE;
      this.lastActionTime = 0L;
      this.compassSlot = -1;
      this.initialWorld = null;
      this.previousSlot = -1;
      this.slotSwitchTime = 0L;
      this.serverCooldownTime = 0L;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.grifNumber, this.delaySeconds});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1761 != null) {
            long currentTime = System.currentTimeMillis();
            if (this.previousSlot != -1 && currentTime - this.slotSwitchTime >= 50L) {
               mc.field_1724.method_31548().field_7545 = this.previousSlot;
               this.previousSlot = -1;
            }

            if (mc.field_1687 != null && this.initialWorld != null) {
               String currentWorld = mc.field_1687.method_27983().method_29177().toString();
               if (!currentWorld.equals(this.initialWorld)) {
                  this.toggle();
                  return;
               }
            }

            if (currentTime - this.lastActionTime >= 50L) {
               switch (this.currentState.ordinal()) {
                  case 0:
                     if (mc.field_1687 != null) {
                        this.initialWorld = mc.field_1687.method_27983().method_29177().toString();
                     }

                     this.currentState = K3a285c31dc955564e5.FIND_COMPASS;
                     this.lastActionTime = currentTime;
                     break;
                  case 1:
                     this.compassSlot = this.K33e848b099bdc4de60();
                     if (this.compassSlot != -1) {
                        this.previousSlot = mc.field_1724.method_31548().field_7545;
                        mc.field_1724.method_31548().field_7545 = this.compassSlot;
                        this.currentState = K3a285c31dc955564e5.SPAM_COMPASS_USE;
                        this.lastActionTime = currentTime;
                        System.out.println("[RWJoiner] Found compass in slot " + this.compassSlot + ", starting spam");
                     } else {
                        this.toggle();
                     }
                     break;
                  case 2:
                     if (mc.field_1755 instanceof class_476) {
                        System.out.println("[RWJoiner] GUI opened! Stopping compass spam");
                        this.currentState = K3a285c31dc955564e5.WAIT_FOR_FIRST_GUI;
                        this.lastActionTime = currentTime;
                     } else if (mc.field_1724.method_31548().field_7545 != this.compassSlot) {
                        System.out.println("[RWJoiner] Compass not in hand! Switching back to slot " + this.compassSlot);
                        mc.field_1724.method_31548().field_7545 = this.compassSlot;
                        this.lastActionTime = currentTime;
                     } else {
                        class_1799 heldItem = mc.field_1724.method_31548().method_5438(this.compassSlot);
                        if (!heldItem.method_7960() && heldItem.method_7909() == class_1802.field_8251) {
                           if (this.serverCooldownTime > 0L && currentTime - this.serverCooldownTime < 500L) {
                              long timeLeft = 500L - (currentTime - this.serverCooldownTime);
                              if (timeLeft % 500L == 0L) {
                                 System.out.println("[RWJoiner] Server cooldown, waiting " + timeLeft + "ms");
                              }
                           } else if (currentTime - this.lastActionTime >= 500L) {
                              System.out.println("[RWJoiner] Using compass...");
                              mc.method_1583();
                              this.lastActionTime = currentTime;
                           }
                        } else {
                           System.out.println("[RWJoiner] No compass in slot! Searching again");
                           this.currentState = K3a285c31dc955564e5.FIND_COMPASS;
                           this.lastActionTime = currentTime;
                        }
                     }
                     break;
                  case 3:
                     class_437 patt0$temp = mc.field_1755;
                     if (patt0$temp instanceof class_476) {
                        class_476 screen = (class_476)patt0$temp;
                        String title = screen.method_25440().getString();
                        if (title != null) {
                           String upperTitle = title.toUpperCase();
                           String lowerTitle = title.toLowerCase();
                           if ((!upperTitle.contains("ВЫБОР") || !upperTitle.contains("МИР")) && !lowerTitle.contains("выбор мира")) {
                              this.currentState = K3a285c31dc955564e5.CLICK_CRAFTING_TABLE;
                              this.lastActionTime = currentTime;
                           } else {
                              this.currentState = K3a285c31dc955564e5.FIND_AND_CLICK_GRIF;
                              this.lastActionTime = currentTime;
                           }
                        }
                     }
                     break;
                  case 4:
                     class_437 patt1$temp = mc.field_1755;
                     if (patt1$temp instanceof class_476) {
                        class_476 screen = (class_476)patt1$temp;
                        boolean foundEnchantedTable = false;

                        for(class_1735 slot : ((class_1707)screen.method_17577()).field_7761) {
                           class_1799 stack = slot.method_7677();
                           if (!stack.method_7960() && stack.method_7909() == class_1802.field_8465) {
                              boolean hasEnchant = stack.method_7942() || stack.method_7958();
                              if (hasEnchant) {
                                 mc.field_1761.method_2906(((class_1707)screen.method_17577()).field_7763, slot.field_7874, 0, class_1713.field_7790, mc.field_1724);
                                 foundEnchantedTable = true;
                                 this.currentState = K3a285c31dc955564e5.WAIT_FOR_GRIF_GUI;
                                 this.lastActionTime = currentTime;
                                 break;
                              }
                           }
                        }

                        if (!foundEnchantedTable) {
                           this.currentState = K3a285c31dc955564e5.FIND_AND_CLICK_GRIF;
                           this.lastActionTime = currentTime;
                        }
                     }
                     break;
                  case 5:
                     class_437 patt2$temp = mc.field_1755;
                     if (patt2$temp instanceof class_476) {
                        class_476 screen = (class_476)patt2$temp;
                        String title = screen.method_25440().getString();
                        if (title != null && (title.toLowerCase().contains("гриф") || title.toLowerCase().contains("выбор мира"))) {
                           this.currentState = K3a285c31dc955564e5.FIND_AND_CLICK_GRIF;
                           this.lastActionTime = currentTime;
                        }
                     }
                     break;
                  case 6:
                     if (!(mc.field_1755 instanceof class_476)) {
                        this.currentState = K3a285c31dc955564e5.GUI_CLOSED_REOPEN;
                        this.lastActionTime = currentTime;
                     } else {
                        class_476 screen = (class_476)mc.field_1755;
                        int targetGrif = this.grifNumber.getValue().intValue();
                        String targetName = "гриф #" + targetGrif;

                        for(class_1735 slot : ((class_1707)screen.method_17577()).field_7761) {
                           class_1799 stack = slot.method_7677();
                           if (!stack.method_7960() && stack.method_7909() == class_1802.field_8575) {
                              String displayName = class_124.method_539(stack.method_7964().getString());
                              if (displayName != null) {
                                 String lowerName = displayName.toLowerCase();
                                 if (lowerName.contains(targetName)) {
                                    mc.field_1761.method_2906(((class_1707)screen.method_17577()).field_7763, slot.field_7874, 0, class_1713.field_7790, mc.field_1724);
                                    this.currentState = K3a285c31dc955564e5.CHECK_GUI_AFTER_CLICK;
                                    this.lastActionTime = currentTime;
                                    return;
                                 }
                              }
                           }
                        }

                        this.currentState = K3a285c31dc955564e5.WAIT_BEFORE_RETRY;
                        this.lastActionTime = currentTime;
                     }
                     break;
                  case 7:
                     if (currentTime - this.lastActionTime >= 300L) {
                        if (!(mc.field_1755 instanceof class_476)) {
                           System.out.println("[RWJoiner] GUI closed after clicking grif! Reopening compass");
                           this.currentState = K3a285c31dc955564e5.GUI_CLOSED_REOPEN;
                           this.lastActionTime = currentTime;
                        } else {
                           this.currentState = K3a285c31dc955564e5.WAIT_BEFORE_RETRY;
                           this.lastActionTime = currentTime;
                        }
                     }
                     break;
                  case 8:
                     if (!(mc.field_1755 instanceof class_476)) {
                        this.currentState = K3a285c31dc955564e5.GUI_CLOSED_REOPEN;
                        this.lastActionTime = currentTime;
                     } else {
                        long delayMs = (long)(this.delaySeconds.getValue() * 1000.0F);
                        if (currentTime - this.lastActionTime >= delayMs) {
                           this.currentState = K3a285c31dc955564e5.FIND_AND_CLICK_GRIF;
                           this.lastActionTime = currentTime;
                        }
                     }
                  case 9:
                  default:
                     break;
                  case 10:
                     System.out.println("[RWJoiner] GUI closed! Restarting compass spam");
                     if (this.compassSlot != -1) {
                        if (mc.field_1724.method_31548().field_7545 != this.compassSlot) {
                           this.previousSlot = mc.field_1724.method_31548().field_7545;
                           mc.field_1724.method_31548().field_7545 = this.compassSlot;
                        }

                        this.currentState = K3a285c31dc955564e5.SPAM_COMPASS_USE;
                        this.lastActionTime = currentTime;
                     } else {
                        this.currentState = K3a285c31dc955564e5.FIND_COMPASS;
                        this.lastActionTime = currentTime;
                        System.out.println("[RWJoiner] Compass lost, searching again");
                     }
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent});
   }

   public void onChatMessage(String message) {
      if (this.isEnabled()) {
         String lowerMessage = message.toLowerCase();
         if (lowerMessage.contains("не так быстро") || lowerMessage.contains("подождите") || lowerMessage.contains("cooldown")) {
            this.serverCooldownTime = System.currentTimeMillis();
            System.out.println("[RWJoiner] Server cooldown detected! Waiting 500ms");
         }

      }
   }

   public void onDisable() {
      this.currentState = K3a285c31dc955564e5.IDLE;
      this.compassSlot = -1;
      this.initialWorld = null;
      this.previousSlot = -1;
      this.slotSwitchTime = 0L;
      this.serverCooldownTime = 0L;
      if (mc.field_1690 != null) {
         mc.field_1690.field_1904.method_23481(false);
      }

      super.onDisable();
   }

   private int K33e848b099bdc4de60() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960() && stack.method_7909() == class_1802.field_8251) {
               return i;
            }
         }

         return -1;
      }
   }

   public int getSelectedGrif() {
      return this.grifNumber.getValue().intValue();
   }

   @Generated
   public static Kc554a5d3999b48bf50 getInstance() {
      return instance;
   }
}
