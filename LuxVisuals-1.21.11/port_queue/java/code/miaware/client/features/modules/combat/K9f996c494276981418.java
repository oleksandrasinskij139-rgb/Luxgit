package code.miaware.client.features.modules.combat;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K256ab2bf60c15ab260;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.ServerRestriction;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.server.K079c3cf92902a5b0ab;
import code.miaware.client.features.modules.combat.fastswap.Kef594ce17099203175;
import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import java.util.Locale;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_10185;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2848;
import net.minecraft.class_642;
import net.minecraft.class_2848.class_2849;

@ServerRestriction(
   allowedServers = {K079c3cf92902a5b0ab.FUNTIME}
)
@ModuleRegister(
   name = "FastSwap",
   category = Kf76f042931183af5ba.COMBAT,
   description = "Быстрый свап двух предметов на FunTime"
)
public final class K9f996c494276981418 extends Kab8a8c478f7d0077cd {
   private static final K9f996c494276981418 instance = new K9f996c494276981418();
   private final K1d3d0b4020f933282f key = new K1d3d0b4020f933282f("Кнопка");
   private final Kbd66519397e9a89967 firstItem = (new Kbd66519397e9a89967("Первый предмет")).values("Золотое яблоко", "Щит", "Шар", "Тотем").value("Шар");
   private final Kbd66519397e9a89967 secondItem = (new Kbd66519397e9a89967("Второй предмет")).values("Золотое яблоко", "Щит", "Шар", "Тотем").value("Тотем");
   private final K00fef91b3d4d1b3ecc showSwap = (new K00fef91b3d4d1b3ecc("Показ свапа")).value(true);
   private final K00fef91b3d4d1b3ecc enchantedTotems = (new K00fef91b3d4d1b3ecc("Только чар. тотемы")).value(false);
   private final Kef594ce17099203175 sequence = new Kef594ce17099203175();
   private boolean firstNext = true;
   private boolean requestedFirst;
   private Object world;
   private Object player;
   private class_1799 expected;
   private String requestedName;

   public static K9f996c494276981418 getInstance() {
      return instance;
   }

   public K9f996c494276981418() {
      this.expected = class_1799.field_8037;
      this.requestedName = "";
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.key, this.firstItem, this.secondItem, this.showSwap, this.enchantedTotems});
      ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> client.execute(this::reset));
   }

   public void onEnable() {
      this.reset();
   }

   public void onDisable() {
      this.reset();
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K856906f6fbe36fc35e)), K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.tick())), K2a56ae9ebefcb944e3.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.Ka7c1f04af130ffb1d0()))});
   }

   private boolean K8111fc661814045137() {
      class_642 server = mc.method_1558();
      return mc.field_1724 != null && mc.field_1687 != null && mc.field_1724.method_5805() && !mc.field_1724.method_7325() && mc.field_1761 != null && mc.field_1724.field_3944 != null && mc.method_1569() && !mc.method_1493() && server != null && server.field_3761.toLowerCase(Locale.ROOT).contains("funtime") && mc.field_1755 == null && mc.field_1724.field_7512 == mc.field_1724.field_7498 && mc.field_1724.field_7498.method_34255().method_7960();
   }

   private void K856906f6fbe36fc35e(K256ab2bf60c15ab260 event) {
      long now = System.nanoTime();
      if (event.action() == 1 && event.matchesBind((Integer)this.key.getValue()) && this.K8111fc661814045137() && !this.sequence.busy() && this.sequence.ready(now) && !Kc233b6623d88914a28.getInstance().isSwapBusy()) {
         boolean first = this.K0bd4d8e4c6c8d82522(mc.field_1724.method_6079(), (String)this.firstItem.getValue());
         boolean second = this.K0bd4d8e4c6c8d82522(mc.field_1724.method_6079(), (String)this.secondItem.getValue());
         this.requestedFirst = first != second ? !first : this.firstNext;
         this.requestedName = this.requestedFirst ? (String)this.firstItem.getValue() : (String)this.secondItem.getValue();
         int slot = this.K876c71f9ce8eefc7ce(this.requestedName);
         if (slot < 0) {
            if ((Boolean)this.showSwap.getValue()) {
               Kb7ca6c2b4209b8a116.notify("Предмет «" + this.requestedName + "» не найден", Ka58c9e2c4d8aa9af2e.WARNING);
            }

         } else {
            if (this.sequence.begin(slot, now)) {
               this.expected = mc.field_1724.method_31548().method_5438(slot).method_7972();
               this.world = mc.field_1687;
               this.player = mc.field_1724;
            }

         }
      }
   }

   private void Ka7c1f04af130ffb1d0() {
      if (this.sequence.busy() && (this.world != mc.field_1687 || this.player != mc.field_1724 || !this.K8111fc661814045137())) {
         this.reset();
      }

   }

   private void tick() {
      this.Ka7c1f04af130ffb1d0();
      if (this.sequence.busy()) {
         if (Kc233b6623d88914a28.getInstance().isSwapBusy()) {
            this.reset();
         } else if (this.sequence.tick(System.nanoTime())) {
            int slot = Kef594ce17099203175.screenSlot(this.sequence.slot());
            if (slot >= 0 && class_1799.method_7973(mc.field_1724.field_7498.method_7611(slot).method_7677(), this.expected) && this.K0bd4d8e4c6c8d82522(this.expected, this.requestedName)) {
               boolean sprinting = mc.field_1724.method_5624();

               try {
                  if (sprinting) {
                     mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12985));
                  }

                  int syncId = mc.field_1724.field_7498.field_7763;
                  mc.field_1761.method_2906(syncId, slot, 0, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(syncId, 45, 0, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(syncId, slot, 0, class_1713.field_7790, mc.field_1724);
               } finally {
                  if (sprinting) {
                     mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12981));
                  }

               }

               this.firstNext = !this.requestedFirst;
               if ((Boolean)this.showSwap.getValue()) {
                  Kb7ca6c2b4209b8a116.notify("FastSwap: " + this.requestedName, Ka58c9e2c4d8aa9af2e.SUCCESS);
               }

            } else {
               this.reset();
            }
         }
      }
   }

   private int K876c71f9ce8eefc7ce(String mode) {
      for(int i = 35; i >= 0; --i) {
         if (this.K0bd4d8e4c6c8d82522(mc.field_1724.method_31548().method_5438(i), mode)) {
            return i;
         }
      }

      return -1;
   }

   private boolean K0bd4d8e4c6c8d82522(class_1799 stack, String mode) {
      if (stack.method_7960()) {
         return false;
      } else {
         class_1792 var10000;
         switch (mode) {
            case "Шар" -> var10000 = class_1802.field_8575;
            case "Тотем" -> var10000 = class_1802.field_8288;
            case "Щит" -> var10000 = class_1802.field_8255;
            case "Золотое яблоко" -> var10000 = class_1802.field_8463;
            default -> var10000 = class_1802.field_8162;
         }

         class_1792 item = var10000;
         return stack.method_31574(item) && (item != class_1802.field_8288 || !(Boolean)this.enchantedTotems.getValue() || stack.method_7942());
      }
   }

   private void reset() {
      this.sequence.cancel();
      this.expected = class_1799.field_8037;
      this.world = this.player = null;
      this.requestedName = "";
      this.firstNext = true;
   }

   public static boolean isMovementLocked() {
      return instance.isEnabled() && instance.sequence.busy();
   }

   public static class_10185 filterMovement(class_10185 original) {
      return isMovementLocked() ? new class_10185(false, false, false, false, false, original.comp_3164(), false) : original;
   }
}
