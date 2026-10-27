package code.miaware.client.features.modules.combat;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.ServerRestriction;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K756e8fdd34195c2119;
import code.miaware.api.utils.server.K079c3cf92902a5b0ab;
import code.miaware.client.features.modules.combat.bloodorb.K1db4bba3d9962fa8a5;
import code.miaware.client.features.modules.combat.tripleswap.K5bb032ff100ed9755b;
import java.util.Objects;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2868;
import net.minecraft.class_490;

@ServerRestriction(
   blockedServers = {K079c3cf92902a5b0ab.REALLYWORLD, K079c3cf92902a5b0ab.VIBEPVP, K079c3cf92902a5b0ab.SWORLD}
)
@ModuleRegister(
   name = "AutoSwap",
   category = Kf76f042931183af5ba.COMBAT,
   description = "Переключает предметы в левой руке"
)
public class Kc233b6623d88914a28 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kc233b6623d88914a28 instance = new Kc233b6623d88914a28();
   private boolean updatingSwapMode;
   private final K00fef91b3d4d1b3ecc doubleSwap = (new K00fef91b3d4d1b3ecc("Двойной свап")).value(true);
   private final K00fef91b3d4d1b3ecc tripleSwap = (new K00fef91b3d4d1b3ecc("Тройной свап")).value(false);
   private final K1d3d0b4020f933282f tripleKey;
   private final K5bb032ff100ed9755b tripleController;
   private final K1d3d0b4020f933282f key;
   private final Kbd66519397e9a89967 from;
   private final Kbd66519397e9a89967 to;
   private final K1d3d0b4020f933282f helmetKey;
   private final Kbd66519397e9a89967 helmetFrom;
   private final Kbd66519397e9a89967 helmetTo;
   private final K00fef91b3d4d1b3ecc shortDelay;
   private final K00fef91b3d4d1b3ecc bloodOrb;
   private final K1db4bba3d9962fa8a5 bloodController;
   private int swapStep;
   private int swapDelay;
   private boolean swapping;
   private int savedSlot;
   private int targetSlotCache;
   private class_1792 cachedTargetItem;
   private boolean fastSwap;
   private boolean helmetSwapping;

   public Kc233b6623d88914a28() {
      K1d3d0b4020f933282f var10001 = new K1d3d0b4020f933282f("Клавиша кольца");
      K00fef91b3d4d1b3ecc var10002 = this.tripleSwap;
      Objects.requireNonNull(var10002);
      this.tripleKey = var10001.setVisible(var10002::getValue);
      this.tripleController = new K5bb032ff100ed9755b(this);
      this.key = new K1d3d0b4020f933282f("Клавиша");
      this.from = (new Kbd66519397e9a89967("Свап с")).values("Тотем", "Шар", "Щит", "Гепл").value("Тотем");
      this.to = (new Kbd66519397e9a89967("Свап на")).values("Тотем", "Шар", "Щит", "Гепл").value("Гепл");
      this.helmetKey = new K1d3d0b4020f933282f("Клавиша свапа головы");
      this.helmetFrom = (new Kbd66519397e9a89967("Свап головы с")).values("Голова Джека", "Тигриная голова", "Нерушимый Шлем", "Шлем").value("Голова Джека");
      this.helmetTo = (new Kbd66519397e9a89967("Свап головы на")).values("Голова Джека", "Тигриная голова", "Нерушимый Шлем", "Шлем").value("Шлем");
      this.shortDelay = (new K00fef91b3d4d1b3ecc("Длинная задержка")).value(false);
      this.bloodOrb = (new K00fef91b3d4d1b3ecc("Шар крови")).value(false);
      this.bloodController = new K1db4bba3d9962fa8a5();
      this.swapStep = 0;
      this.swapDelay = 0;
      this.swapping = false;
      this.savedSlot = -1;
      this.targetSlotCache = -1;
      this.cachedTargetItem = null;
      this.fastSwap = false;
      this.helmetSwapping = false;
      K1d3d0b4020f933282f var10000 = this.key;
      K00fef91b3d4d1b3ecc var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var10000.setVisible(var7::getValue);
      Kbd66519397e9a89967 var1 = this.from;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var1.setVisible(var7::getValue);
      var1 = this.to;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var1.setVisible(var7::getValue);
      K1d3d0b4020f933282f var3 = this.helmetKey;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var3.setVisible(var7::getValue);
      Kbd66519397e9a89967 var4 = this.helmetFrom;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var4.setVisible(var7::getValue);
      var4 = this.helmetTo;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var4.setVisible(var7::getValue);
      K00fef91b3d4d1b3ecc var6 = this.shortDelay;
      var7 = this.doubleSwap;
      Objects.requireNonNull(var7);
      var6.setVisible(var7::getValue);
      this.doubleSwap.onAction(() -> this.K3430d196f73072abc4(true));
      this.tripleSwap.onAction(() -> this.K3430d196f73072abc4(false));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.doubleSwap, this.tripleSwap, this.key, this.from, this.to, this.helmetKey, this.helmetFrom, this.helmetTo, this.shortDelay, this.tripleKey, this.bloodOrb});
      this.addSettings(this.tripleController.settings());
      ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> client.execute(() -> {
            this.tripleController.reset();
            this.bloodController.reset();
         }));
      this.bloodOrb.onAction(() -> {
         if (!(Boolean)this.bloodOrb.getValue()) {
            this.bloodController.reset();
         }

      });
   }

   public boolean isManualSwapBusy() {
      return this.swapping || this.bloodController.isBusy();
   }

   public boolean isSwapBusy() {
      return this.isEnabled() && (this.isManualSwapBusy() || this.tripleController.busy());
   }

   public boolean isTripleSwapEnabled() {
      return (Boolean)this.tripleSwap.getValue();
   }

   public int tripleBind() {
      return (Integer)this.tripleKey.getValue();
   }

   public boolean longSwapDelay() {
      return (Boolean)this.shortDelay.getValue();
   }

   private void K3430d196f73072abc4(boolean changedDouble) {
      if (!this.updatingSwapMode) {
         this.updatingSwapMode = true;

         try {
            if (changedDouble) {
               if ((Boolean)this.doubleSwap.getValue()) {
                  this.tripleSwap.setValue(false);
               } else if (!(Boolean)this.tripleSwap.getValue()) {
                  this.doubleSwap.setValue(true);
               }
            } else if ((Boolean)this.tripleSwap.getValue()) {
               this.doubleSwap.setValue(false);
            } else if (!(Boolean)this.doubleSwap.getValue()) {
               this.tripleSwap.setValue(true);
            }

            if (!(Boolean)this.tripleSwap.getValue()) {
               this.tripleController.reset();
            }
         } finally {
            this.updatingSwapMode = false;
         }

      }
   }

   public String getSuffix() {
      return (Boolean)this.tripleSwap.getValue() ? "Тройной" : (String)this.from.getValue() + " -> " + (String)this.to.getValue();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1755 == null && !K9f996c494276981418.isMovementLocked()) {
            if ((Boolean)this.tripleSwap.getValue()) {
               if (event.action() == 1 && event.matchesBind((Integer)this.tripleKey.getValue())) {
                  this.tripleController.open();
               }

            } else if ((Boolean)this.doubleSwap.getValue()) {
               if (event.matchesBind((Integer)this.key.getValue()) && event.action() == 1 && !this.swapping && !this.bloodController.isBusy()) {
                  this.helmetSwapping = false;
                  this.K8912dfc04a347bb11f();
               }

               if (event.matchesBind((Integer)this.helmetKey.getValue()) && event.action() == 1 && !this.swapping && !this.bloodController.isBusy()) {
                  this.helmetSwapping = true;
                  this.K0475716e39e7550952();
               }

            }
         }
      }));
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.tripleController.tick();
         this.bloodController.tick((Boolean)this.bloodOrb.getValue(), this.swapping || this.tripleController.busy() || K9f996c494276981418.isMovementLocked());
         if (this.swapping) {
            if (this.swapDelay > 0) {
               --this.swapDelay;
            } else {
               if (this.fastSwap) {
                  int delay = (Boolean)this.shortDelay.getValue() ? 6 : 1;
                  switch (this.swapStep) {
                     case 0:
                        this.savedSlot = mc.field_1724.method_31548().field_7545;
                        System.out.println("[AutoSwap FastSwap] Step 0: savedSlot=" + this.savedSlot + ", targetSlot=" + this.targetSlotCache);
                        K756e8fdd34195c2119.sendPacket(new class_2868(this.targetSlotCache));
                        mc.field_1724.method_31548().field_7545 = this.targetSlotCache;
                        this.swapDelay = delay;
                        ++this.swapStep;
                        break;
                     case 1:
                        System.out.println("[AutoSwap FastSwap] Step 1: Свапаем через слот");
                        if (mc.field_1761 != null) {
                           int syncId = mc.field_1724.field_7512.field_7763;
                           mc.field_1761.method_2906(syncId, 36 + this.targetSlotCache, 40, class_1713.field_7791, mc.field_1724);
                        }

                        this.swapDelay = delay;
                        ++this.swapStep;
                        break;
                     case 2:
                        System.out.println("[AutoSwap FastSwap] Step 2: Возвращаем слот " + this.savedSlot);
                        K756e8fdd34195c2119.sendPacket(new class_2868(this.savedSlot));
                        mc.field_1724.method_31548().field_7545 = this.savedSlot;
                        this.swapping = false;
                        this.swapStep = 0;
                        this.fastSwap = false;
                        this.savedSlot = -1;
                        this.targetSlotCache = -1;
                        this.cachedTargetItem = null;
                        this.helmetSwapping = false;
                  }
               } else {
                  int invDelay = (Boolean)this.shortDelay.getValue() ? 2 : 1;
                  int swapDelayInv = (Boolean)this.shortDelay.getValue() ? 3 : 1;
                  switch (this.swapStep) {
                     case 0:
                        if (mc.field_1755 == null) {
                           mc.method_1507(new class_490(mc.field_1724));
                        }

                        this.swapDelay = invDelay;
                        ++this.swapStep;
                        break;
                     case 1:
                        this.K599968398ce418d6c0();
                        this.swapDelay = swapDelayInv;
                        ++this.swapStep;
                        break;
                     case 2:
                        if (mc.field_1755 != null) {
                           mc.field_1724.method_3137();
                        }

                        this.swapping = false;
                        this.swapStep = 0;
                        this.cachedTargetItem = null;
                        this.helmetSwapping = false;
                  }
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, updateEvent, K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.bloodController.onAttack(event, (Boolean)this.bloodOrb.getValue(), this.swapping || this.tripleController.busy() || K9f996c494276981418.isMovementLocked()))), K2a56ae9ebefcb944e3.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.bloodController.guard();
         this.tripleController.guard();
      }))});
   }

   public void onDisable() {
      this.bloodController.reset();
      this.tripleController.reset();
      if (mc.field_1724 != null && this.savedSlot >= 0 && this.fastSwap && mc.field_1724.method_31548().field_7545 == this.targetSlotCache) {
         mc.field_1724.method_31548().field_7545 = this.savedSlot;
         if (mc.field_1724.field_3944 != null) {
            mc.field_1724.field_3944.method_52787(new class_2868(this.savedSlot));
         }
      }

      this.swapping = false;
      this.fastSwap = false;
      this.helmetSwapping = false;
      this.swapStep = this.swapDelay = 0;
      this.savedSlot = this.targetSlotCache = -1;
      this.cachedTargetItem = null;
   }

   private void K8912dfc04a347bb11f() {
      if (mc.field_1724 != null) {
         class_1799 offhandItem = mc.field_1724.method_6079();
         class_1792 targetItem;
         if (offhandItem.method_7909() == this.Ke4e926c790904670cb((String)this.from.getValue())) {
            targetItem = this.Ke4e926c790904670cb((String)this.to.getValue());
         } else {
            targetItem = this.Ke4e926c790904670cb((String)this.from.getValue());
         }

         System.out.println("[AutoSwap] Ищем предмет: " + String.valueOf(targetItem));
         System.out.println("[AutoSwap] В оффханде: " + String.valueOf(offhandItem.method_7909()));
         this.cachedTargetItem = targetItem;
         int hotbarSlot = this.Kb8cab746403c0906c9(targetItem);
         System.out.println("[AutoSwap] Найден в хотбаре на слоте: " + hotbarSlot);
         if (hotbarSlot != -1) {
            this.targetSlotCache = hotbarSlot;
            this.fastSwap = true;
            System.out.println("[AutoSwap] Используем БЫСТРЫЙ свап через F");
         } else {
            this.fastSwap = false;
            System.out.println("[AutoSwap] Используем свап через ИНВЕНТАРЬ");
         }

         this.swapping = true;
         this.swapStep = 0;
         this.swapDelay = 0;
      }
   }

   private void K599968398ce418d6c0() {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int targetSwapSlot;
         if (this.helmetSwapping) {
            targetSwapSlot = 5;
         } else {
            targetSwapSlot = 45;
         }

         int targetSlot = this.K83335a358d2cb81ad8(this.cachedTargetItem);
         if (targetSlot != -1) {
            int syncId = mc.field_1724.field_7512.field_7763;
            mc.field_1761.method_2906(syncId, targetSlot, 0, class_1713.field_7790, mc.field_1724);
            mc.field_1761.method_2906(syncId, targetSwapSlot, 0, class_1713.field_7790, mc.field_1724);
            mc.field_1761.method_2906(syncId, targetSlot, 0, class_1713.field_7790, mc.field_1724);
         }
      }
   }

   private int K83335a358d2cb81ad8(class_1792 item) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            int slot = i + 36;
            class_1799 stack = mc.field_1724.field_7512.method_7611(slot).method_7677();
            if (stack.method_7909() == item) {
               return slot;
            }
         }

         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.field_7512.method_7611(i).method_7677();
            if (stack.method_7909() == item && stack.method_7942()) {
               return i;
            }
         }

         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.field_7512.method_7611(i).method_7677();
            if (stack.method_7909() == item) {
               return i;
            }
         }

         return -1;
      }
   }

   private class_1792 Ke4e926c790904670cb(String itemType) {
      class_1792 var10000;
      switch (itemType) {
         case "Тотем" -> var10000 = class_1802.field_8288;
         case "Шар" -> var10000 = class_1802.field_8575;
         case "Щит" -> var10000 = class_1802.field_8255;
         case "Гепл" -> var10000 = class_1802.field_8463;
         default -> var10000 = class_1802.field_8162;
      }

      return var10000;
   }

   private int Kb8cab746403c0906c9(class_1792 item) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() == item) {
               return i;
            }
         }

         return -1;
      }
   }

   private void K0475716e39e7550952() {
      if (mc.field_1724 != null) {
         class_1799 currentHelmet = mc.field_1724.method_31548().method_7372(3);
         class_1799 targetHelmet = null;
         String fromMode = (String)this.helmetFrom.getValue();
         String toMode = (String)this.helmetTo.getValue();
         if (this.Keab6223f8a1324d817(currentHelmet, fromMode)) {
            if (toMode.equals("Шлем")) {
               targetHelmet = this.K896bda946ec1a84a5f();
            } else {
               targetHelmet = this.K8459af421881f0aa2d(toMode);
            }
         } else if (fromMode.equals("Шлем")) {
            targetHelmet = this.K896bda946ec1a84a5f();
         } else {
            targetHelmet = this.K8459af421881f0aa2d(fromMode);
         }

         if (targetHelmet != null && !targetHelmet.method_7960()) {
            System.out.println("[AutoSwap Helmet] Найден шлем: " + String.valueOf(targetHelmet.method_7909()));
            this.fastSwap = false;
            this.cachedTargetItem = targetHelmet.method_7909();
            this.swapping = true;
            this.swapStep = 0;
            this.swapDelay = 0;
         } else {
            System.out.println("[AutoSwap Helmet] Шлем не найден");
         }
      }
   }

   private boolean Keab6223f8a1324d817(class_1799 helmet, String mode) {
      if (helmet != null && !helmet.method_7960()) {
         class_1792 item = helmet.method_7909();
         String displayName = helmet.method_7964().getString().toLowerCase();
         boolean var10000;
         switch (mode) {
            case "Голова Джека" -> var10000 = item == class_1802.field_8575 && (displayName.contains("jack") || displayName.contains("джек"));
            case "Тигриная голова" -> var10000 = item == class_1802.field_8575 && (displayName.contains("tiger") || displayName.contains("тигр"));
            case "Нерушимый Шлем" -> var10000 = displayName.contains("нерушим") || displayName.contains("unbreakable");
            case "Шлем" -> var10000 = this.Kb01a5aff1c493a8796(item);
            default -> var10000 = false;
         }

         return var10000;
      } else {
         return false;
      }
   }

   private boolean Kb01a5aff1c493a8796(class_1792 item) {
      return item == class_1802.field_22027 || item == class_1802.field_8805 || item == class_1802.field_8862 || item == class_1802.field_8743 || item == class_1802.field_8283 || item == class_1802.field_8267 || item == class_1802.field_8090;
   }

   private class_1799 K8459af421881f0aa2d(String mode) {
      if (mc.field_1724 == null) {
         return null;
      } else {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               class_1792 item = stack.method_7909();
               String displayName = stack.method_7964().getString().toLowerCase();
               switch (mode) {
                  case "Голова Джека":
                     if (item == class_1802.field_8575 && (displayName.contains("jack") || displayName.contains("джек"))) {
                        return stack;
                     }
                     break;
                  case "Тигриная голова":
                     if (item == class_1802.field_8575 && (displayName.contains("tiger") || displayName.contains("тигр"))) {
                        return stack;
                     }
                     break;
                  case "Нерушимый Шлем":
                     if (displayName.contains("нерушим") || displayName.contains("unbreakable")) {
                        return stack;
                     }
               }
            }
         }

         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               class_1792 item = stack.method_7909();
               String displayName = stack.method_7964().getString().toLowerCase();
               switch (mode) {
                  case "Голова Джека":
                     if (item == class_1802.field_8575 && (displayName.contains("jack") || displayName.contains("джек"))) {
                        return stack;
                     }
                     break;
                  case "Тигриная голова":
                     if (item == class_1802.field_8575 && (displayName.contains("tiger") || displayName.contains("тигр"))) {
                        return stack;
                     }
                     break;
                  case "Нерушимый Шлем":
                     if (displayName.contains("нерушим") || displayName.contains("unbreakable")) {
                        return stack;
                     }
               }
            }
         }

         return null;
      }
   }

   private class_1799 K896bda946ec1a84a5f() {
      if (mc.field_1724 == null) {
         return null;
      } else {
         class_1799 bestHelmet = null;
         int bestPriority = -1;

         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               int priority = this.K8e7fd7bc468bfee308(stack);
               if (priority > bestPriority) {
                  bestPriority = priority;
                  bestHelmet = stack;
               }
            }
         }

         if (bestHelmet != null) {
            return bestHelmet;
         } else {
            for(int i = 0; i < 9; ++i) {
               class_1799 stack = mc.field_1724.method_31548().method_5438(i);
               if (!stack.method_7960()) {
                  int priority = this.K8e7fd7bc468bfee308(stack);
                  if (priority > bestPriority) {
                     bestPriority = priority;
                     bestHelmet = stack;
                  }
               }
            }

            return bestHelmet;
         }
      }
   }

   private int K8e7fd7bc468bfee308(class_1799 stack) {
      class_1792 item = stack.method_7909();
      String displayName = stack.method_7964().getString().toLowerCase();
      if (!displayName.contains("нерушим") && !displayName.contains("unbreakable")) {
         if (item == class_1802.field_8575) {
            if (!displayName.contains("jack") && !displayName.contains("джек")) {
               return !displayName.contains("tiger") && !displayName.contains("тигр") ? 800 : 850;
            } else {
               return 900;
            }
         } else if (item == class_1802.field_22027) {
            return 700;
         } else if (item == class_1802.field_8805) {
            return 600;
         } else if (item == class_1802.field_8862) {
            return 500;
         } else if (item == class_1802.field_8743) {
            return 400;
         } else if (item == class_1802.field_8283) {
            return 300;
         } else if (item == class_1802.field_8267) {
            return 200;
         } else {
            return item == class_1802.field_8090 ? 100 : -1;
         }
      } else {
         return 1000;
      }
   }

   public static Kc233b6623d88914a28 getInstance() {
      return instance;
   }
}
