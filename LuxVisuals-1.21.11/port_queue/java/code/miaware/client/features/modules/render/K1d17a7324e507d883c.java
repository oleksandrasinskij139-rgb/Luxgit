package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;

@ModuleRegister(
   name = "HitWaves",
   category = Kf76f042931183af5ba.RENDER,
   description = "Волны при ударе"
)
public class K1d17a7324e507d883c extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K1d17a7324e507d883c instance = new K1d17a7324e507d883c();
   private static final int MAX_ACTIVE_WAVES = 6;
   private static final int SEARCH_HEIGHT = 4;
   private static final float WAVE_THICKNESS = 1.15F;
   private static final long BASE_DURATION_MS = 900L;
   private static final long DURATION_PER_SIZE_MS = 25L;
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Своя тема").values("Своя тема", "Клиентская");
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(255, 180, 90, 180));
   private final K00fef91b3d4d1b3ecc randomColor = (new K00fef91b3d4d1b3ecc("Случайный цвет")).value(false);
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Размер")).value(12.0F).range(4.0F, 24.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd fillIntensity = (new Kb9e875ead00e0d03bd("Интенсивность заливки")).value(50.0F).range(0.0F, 100.0F).step(5.0F);
   private final List<K503fbacce527792274> waveEffects = new ArrayList();

   public K1d17a7324e507d883c() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.colorMode, this.color, this.randomColor, this.size, this.fillIntensity});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            class_1297 patt0$temp = event.entity();
            if (patt0$temp instanceof class_1657) {
               class_1657 target = (class_1657)patt0$temp;
               if (target != mc.field_1724) {
                  this.waveEffects.add(new K503fbacce527792274(this, target.method_24515().method_10062(), System.currentTimeMillis(), this.size.getValue(), this.K199ce7de2b97283c3f()));
                  if (this.waveEffects.size() > 6) {
                     this.waveEffects.remove(0);
                  }

                  return;
               }
            }

         }
      }));
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && !this.waveEffects.isEmpty()) {
            Iterator<K503fbacce527792274> iterator = this.waveEffects.iterator();

            while(iterator.hasNext()) {
               K503fbacce527792274 wave = (K503fbacce527792274)iterator.next();
               if (wave.isExpired()) {
                  iterator.remove();
               } else {
                  wave.render(event.matrixStack());
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{attackEvent, render3DEvent});
   }

   private int K199ce7de2b97283c3f() {
      Color baseColor;
      if (((String)this.colorMode.getValue()).equals("Клиентская")) {
         baseColor = Kb18d549c13309af9a5.primary();
      } else {
         baseColor = (Color)this.color.getValue();
      }

      if (!(Boolean)this.randomColor.getValue()) {
         return baseColor.getRGB();
      } else {
         int alpha = baseColor.getAlpha();
         float hue = ThreadLocalRandom.current().nextFloat();
         int rgb = Color.HSBtoRGB(hue, 0.9F, 1.0F);
         return K80ee108ea129adac11.setAlpha(new Color(rgb), alpha).getRGB();
      }
   }

   private class_2338 Kea1464a8dc6f6e63eb(class_2338 origin) {
      if (!mc.field_1687.method_24794(origin)) {
         return null;
      } else if (!mc.field_1687.method_8320(origin).method_26215()) {
         return origin;
      } else {
         for(int i = 1; i <= 4; ++i) {
            class_2338 down = origin.method_10087(i);
            if (mc.field_1687.method_24794(down) && !mc.field_1687.method_8320(down).method_26215()) {
               return down;
            }

            class_2338 up = origin.method_10086(i);
            if (mc.field_1687.method_24794(up) && !mc.field_1687.method_8320(up).method_26215()) {
               return up;
            }
         }

         return null;
      }
   }

   @Generated
   public static K1d17a7324e507d883c getInstance() {
      return instance;
   }
}
