package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K503912bdf3fa552d14;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.client.features.modules.render.killeffect.K1037f35c571a07da2f;
import code.miaware.client.features.modules.render.killeffect.K540b1eed9037d7ce28;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_7833;

@ModuleRegister(
   name = "Kill Effect",
   category = Kf76f042931183af5ba.RENDER,
   description = "Душелов, пасть бездны, разрыв души и солнечное испепеление при убийстве"
)
public class K48b6e929bec239c249 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K48b6e929bec239c249 instance = new K48b6e929bec239c249();
   private static final int MAX_EFFECTS = 8;
   private static final long DUPLICATE_WINDOW = 2000000000L;
   private final Kbd66519397e9a89967 mode;
   private final Kbd66519397e9a89967 colorMode;
   private final Ka52d224feda41b0761 color;
   private final Kb9e875ead00e0d03bd duration;
   private final List<K0736db54d4568050e4> effects;
   private final Map<UUID, Long> recentKills;
   private class_638 effectWorld;

   public K48b6e929bec239c249() {
      this.mode = (new Kbd66519397e9a89967("Режим") {
         public void setValue(String value) {
            super.setValue(K1037f35c571a07da2f.fromName(value).label);
         }
      }).value(K1037f35c571a07da2f.SOUL_CATCHER.label).values((String[])Arrays.stream(K1037f35c571a07da2f.values()).map((s) -> s.label).toArray((x$0) -> new String[x$0]));
      this.colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Клиентская").values("Клиентская", "Свой цвет", "Случайный");
      this.color = (new Ka52d224feda41b0761("Цвет")).value(new Color(177, 101, 240, 255)).setVisible(() -> this.colorMode.is("Свой цвет"));
      this.duration = (new Kb9e875ead00e0d03bd("Длительность")).value(3.0F).range(0.5F, 5.0F).step(0.5F);
      this.effects = new ArrayList();
      this.recentKills = new HashMap();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.colorMode, this.color, this.duration});
   }

   public void onEnable() {
      this.reset();
   }

   public void onDisable() {
      this.reset();
   }

   private void reset() {
      synchronized(this.effects) {
         this.effects.clear();
         this.recentKills.clear();
         this.effectWorld = mc.field_1687;
      }
   }

   private void Ka61e2487de7b21e4e3(long now) {
      if (this.effectWorld != mc.field_1687) {
         this.reset();
      }

      this.effects.removeIf((effect) -> effect.expired(now));
      this.recentKills.entrySet().removeIf((entry) -> now - (Long)entry.getValue() >= 2000000000L);
   }

   public void onEvent() {
      K8d7165024ff567f5e7 kills = K503912bdf3fa552d14.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null && event.killedEntity() != null) {
            class_1309 killed = event.killedEntity();
            if (!(mc.field_1724.method_5858(killed) > (double)4096.0F)) {
               K1037f35c571a07da2f style = K1037f35c571a07da2f.fromName((String)this.mode.getValue());
               long now = System.nanoTime();
               synchronized(this.effects) {
                  this.Ka61e2487de7b21e4e3(now);
                  if (this.recentKills.putIfAbsent(killed.method_5667(), now) == null) {
                     if (this.effects.size() >= 8) {
                        this.effects.removeFirst();
                     }

                     this.effects.add(new K0736db54d4568050e4(killed.method_19538(), style, this.K65e4e7d7a78a6badc3().getRGB(), (double)killed.method_17682(), (double)killed.method_17681(), killed.method_43078(), now, (long)(this.duration.getValue() * 1.0E9F)));
                  }
               }
            }
         }
      }));
      K8d7165024ff567f5e7 update = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         synchronized(this.effects) {
            this.Ka61e2487de7b21e4e3(System.nanoTime());
         }
      }));
      K8d7165024ff567f5e7 render = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         synchronized(this.effects) {
            long now = System.nanoTime();
            this.Ka61e2487de7b21e4e3(now);
            if (mc.field_1687 != null && mc.field_1724 != null) {
               for(K0736db54d4568050e4 effect : this.effects) {
                  if (!(effect.position.method_1025(event.cameraPos()) > (double)4096.0F)) {
                     class_4587 matrices = event.matrixStack();
                     matrices.method_22903();

                     try {
                        class_243 relative = effect.position.method_1020(event.cameraPos());
                        matrices.method_22904(relative.field_1352, relative.field_1351, relative.field_1350);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(-effect.yaw));
                        K540b1eed9037d7ce28.render(matrices, effect.style, effect.progress(now), effect.color, effect.height, effect.width);
                     } finally {
                        matrices.method_22909();
                     }
                  }
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{kills, update, render});
   }

   private Color K65e4e7d7a78a6badc3() {
      Color var10000;
      switch ((String)this.colorMode.getValue()) {
         case "Свой цвет" -> var10000 = (Color)this.color.getValue();
         case "Случайный" -> var10000 = new Color(Color.HSBtoRGB(ThreadLocalRandom.current().nextFloat(), 0.85F, 1.0F));
         default -> var10000 = Kb18d549c13309af9a5.primary();
      }

      return var10000;
   }

   @Generated
   public static K48b6e929bec239c249 getInstance() {
      return instance;
   }
}
