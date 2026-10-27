package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "TotemEffect",
   category = Kf76f042931183af5ba.RENDER,
   description = "Частицы при сносе тотема"
)
public final class K1162659203460bc8c0 extends Kab8a8c478f7d0077cd {
   private static final K1162659203460bc8c0 instance = new K1162659203460bc8c0();
   private final Kbd66519397e9a89967 style = (new Kbd66519397e9a89967("Частицы")).value("Кубы").values("Кубы", "Искры", "Бабочки", "Дымки");
   private final Kbd66519397e9a89967 palette = (new Kbd66519397e9a89967("Цвет")).value("Тема").values("Тема", "Тотем");
   private final Kb9e875ead00e0d03bd count = (new Kb9e875ead00e0d03bd("Количество")).value(70.0F).range(15.0F, 150.0F).step(5.0F);
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Размер")).value(1.0F).range(0.5F, 2.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd lifetime = (new Kb9e875ead00e0d03bd("Время жизни")).value(1.6F).range(0.5F, 3.0F).step(0.1F);
   private static final int LIMIT = 640;
   private static final float[] SMOKE_X = new float[17];
   private static final float[] SMOKE_Y = new float[17];
   private final ArrayList<K8f50597f468ef675bf> particles = new ArrayList();
   private class_638 world;

   public K1162659203460bc8c0() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.style, this.palette, this.count, this.size, this.lifetime});
   }

   public void onEnable() {
      this.clear();
   }

   public void onDisable() {
      this.clear();
   }

   private void clear() {
      this.particles.clear();
      this.world = null;
   }

   private void Kb1e956ef995efb8af2() {
      if (this.world != mc.field_1687) {
         this.particles.clear();
         this.world = mc.field_1687;
      }

   }

   public boolean emit(class_1297 entity) {
      if (this.isEnabled() && entity != null && mc.field_1687 != null && mc.field_1724 != null) {
         this.Kb1e956ef995efb8af2();
         if (mc.field_1724.method_5858(entity) > (double)4096.0F) {
            return true;
         } else {
            int amount = this.count.getValue().intValue();
            int overflow = this.particles.size() + amount - 640;
            if (overflow > 0) {
               this.particles.subList(0, overflow).clear();
            }

            ThreadLocalRandom random = ThreadLocalRandom.current();
            byte var10000;
            switch ((String)this.style.getValue()) {
               case "Искры" -> var10000 = 1;
               case "Бабочки" -> var10000 = 2;
               case "Дымки" -> var10000 = 3;
               default -> var10000 = 0;
            }

            int kind = var10000;
            boolean theme = ((String)this.palette.getValue()).equals("Тема");

            for(int i = 0; i < amount; ++i) {
               double angle = random.nextDouble((Math.PI * 2D));
               double speed = random.nextDouble(0.045, 0.15);
               K8f50597f468ef675bf p = new K8f50597f468ef675bf();
               p.x = p.px = entity.method_23317() + random.nextDouble((double)-0.25F, (double)0.25F);
               p.y = p.py = entity.method_23318() + random.nextDouble(0.15, Math.max(0.3, (double)entity.method_17682()));
               p.z = p.pz = entity.method_23321() + random.nextDouble((double)-0.25F, (double)0.25F);
               p.vx = Math.cos(angle) * speed;
               p.vz = Math.sin(angle) * speed;
               p.vy = random.nextDouble(0.035, 0.18);
               p.kind = kind;
               p.theme = theme;
               p.seed = random.nextFloat() * 6.283185F;
               p.scale = this.size.getValue() * random.nextFloat(0.7F, 1.3F);
               p.life = Math.max(2, (int)((double)(this.lifetime.getValue() * 20.0F) * random.nextDouble(0.7, 1.2)));
               p.index = i * 29;
               p.color = random.nextBoolean() ? (new Color(random.nextInt(45, 100), random.nextInt(175, 235), 45)).getRGB() : (new Color(240, random.nextInt(180, 235), random.nextInt(35, 85))).getRGB();
               this.particles.add(p);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.Kb1e956ef995efb8af2();
         if (this.world != null && mc.field_1724 != null) {
            if (!mc.method_1493()) {
               this.particles.removeIf((px) -> ++px.age >= px.life);

               for(K8f50597f468ef675bf p : this.particles) {
                  p.px = p.x;
                  p.py = p.y;
                  p.pz = p.z;
                  p.x += p.vx;
                  p.y += p.vy;
                  p.z += p.vz;
                  double drag = p.kind == 1 ? 0.94 : 0.9;
                  p.vx *= drag;
                  p.vz *= drag;
                  p.vy *= drag;
                  p.vy += p.kind == 3 ? 0.002 : (p.kind == 2 ? 8.0E-4 : -0.003);
                  if (p.kind == 2) {
                     p.x += Math.sin((double)p.age * 0.3 + (double)p.seed) * 0.012;
                  }
               }

            }
         } else {
            this.particles.clear();
         }
      })), Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::render))});
   }

   private void render(K9ee087b2e1791ad6b2 event) {
      this.Kb1e956ef995efb8af2();
      if (!this.particles.isEmpty() && mc.field_1724 != null) {
         class_243 camera = event.cameraPos();

         for(K8f50597f468ef675bf p : this.particles) {
            double dx = p.x - camera.field_1352;
            double dy = p.y - camera.field_1351;
            double dz = p.z - camera.field_1350;
            p.distance = dx * dx + dy * dy + dz * dz;
         }

         this.particles.sort(Comparator.comparingDouble((px) -> px.distance).reversed());

         try (K96b2df1735cfd9ad42 ignored = new K96b2df1735cfd9ad42()) {
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            class_4587 matrices = event.matrixStack();
            float partial = mc.method_1493() ? 1.0F : event.partialTicks();

            for(K8f50597f468ef675bf p : this.particles) {
               float age = Math.max(0.0F, (float)(p.age - 1) + partial);
               float progress = age / (float)p.life;
               float opacity = Math.min(1.0F, (age + 1.0F) / 3.0F) * (float)Math.pow((double)(1.0F - progress), 1.4);
               int rgb = p.theme ? Kb18d549c13309af9a5.gradient(p.index).getRGB() : p.color;
               matrices.method_22903();

               try {
                  matrices.method_22904(p.px + (p.x - p.px) * (double)partial - camera.field_1352, p.py + (p.y - p.py) * (double)partial - camera.field_1351, p.pz + (p.z - p.pz) * (double)partial - camera.field_1350);
                  if (p.kind == 0) {
                     matrices.method_22907(class_7833.field_40716.rotation(p.seed + age * 0.055F));
                     matrices.method_22907(class_7833.field_40714.rotation(p.seed * 0.7F + age * 0.04F));
                  } else {
                     matrices.method_22907(mc.field_1773.method_19418().method_23767());
                     if (p.kind == 1 || p.kind == 3) {
                        matrices.method_22907(class_7833.field_40718.rotation(p.seed));
                     }
                  }

                  float s = p.scale * (p.kind == 3 ? 0.13F + progress * 0.26F : (p.kind == 2 ? 0.12F : 0.055F));
                  matrices.method_22905(s, s, s);
                  Matrix4f m = matrices.method_23760().method_23761();
                  int color = Ke332a758d9a9133e67(rgb, opacity * (p.kind == 3 ? 0.32F : 0.92F));
                  switch (p.kind) {
                     case 0:
                        cube(buffer, m, color);
                        break;
                     case 1:
                        Ke9e8ea5494849fc46c(buffer, m, color);
                        break;
                     case 2:
                        Kee0de1112433ff4cb6(buffer, m, color, age, p.seed);
                        break;
                     default:
                        K599e53f4b205f21b9d(buffer, m, color);
                  }
               } finally {
                  matrices.method_22909();
               }
            }

            class_9801 built = buffer.method_60794();
            if (built != null) {
               class_286.method_43433(built);
            }
         }

      }
   }

   private static int Ke332a758d9a9133e67(int color, float alpha) {
      return color & 16777215 | (int)(255.0F * alpha) << 24;
   }

   private static int Kdda3584b4ccbfd9e73(int color, float light) {
      return color & -16777216 | (int)((float)(color >> 16 & 255) * light) << 16 | (int)((float)(color >> 8 & 255) * light) << 8 | (int)((float)(color & 255) * light);
   }

   private static void K6e6e86d6407db70fe5(class_287 b, Matrix4f m, float x, float y, float z, int c) {
      b.method_22918(m, x, y, z).method_39415(c);
   }

   private static void Kc975ed3ab0a05a7399(class_287 b, Matrix4f m, int c, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
      K6e6e86d6407db70fe5(b, m, x1, y1, z1, c);
      K6e6e86d6407db70fe5(b, m, x2, y2, z2, c);
      K6e6e86d6407db70fe5(b, m, x3, y3, z3, c);
      K6e6e86d6407db70fe5(b, m, x1, y1, z1, c);
      K6e6e86d6407db70fe5(b, m, x3, y3, z3, c);
      K6e6e86d6407db70fe5(b, m, x4, y4, z4, c);
   }

   private static void cube(class_287 b, Matrix4f m, int c) {
      Kc975ed3ab0a05a7399(b, m, c, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F);
      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.48F), -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F);
      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.8F), -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F);
      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.6F), 1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F);
      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.7F), -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F);
      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.9F), 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F);
   }

   private static void Ke9e8ea5494849fc46c(class_287 b, Matrix4f m, int c) {
      int white = c & -16777216 | 16774617;

      for(int sign = -1; sign <= 1; sign += 2) {
         K6e6e86d6407db70fe5(b, m, 0.0F, 0.0F, 0.0F, white);
         K6e6e86d6407db70fe5(b, m, -0.32F, (float)sign * 0.45F, 0.0F, c);
         K6e6e86d6407db70fe5(b, m, 0.0F, (float)sign * 3.8F, 0.0F, c & 16777215);
         K6e6e86d6407db70fe5(b, m, 0.0F, 0.0F, 0.0F, white);
         K6e6e86d6407db70fe5(b, m, 0.0F, (float)sign * 3.8F, 0.0F, c & 16777215);
         K6e6e86d6407db70fe5(b, m, 0.32F, (float)sign * 0.45F, 0.0F, c);
      }

   }

   private static void Kee0de1112433ff4cb6(class_287 b, Matrix4f m, int c, float age, float phase) {
      float flap = 0.25F + 0.75F * (float)((Math.sin((double)age * 0.85 + (double)phase) + (double)1.0F) * (double)0.5F);

      for(int sign = -1; sign <= 1; sign += 2) {
         float x = (float)sign * flap;
         float z = (1.0F - flap) * 0.85F;
         Kc975ed3ab0a05a7399(b, m, c, 0.0F, 0.25F, 0.0F, x * 0.75F, 1.0F, z * 0.7F, x * 1.4F, 0.55F, z, x * 0.75F, -0.12F, z * 0.7F);
         Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.78F), 0.0F, 0.15F, 0.0F, x * 0.75F, -0.12F, z * 0.7F, x, -0.7F, z, x * 0.3F, -0.8F, z * 0.3F);
      }

      Kc975ed3ab0a05a7399(b, m, Kdda3584b4ccbfd9e73(c, 0.4F), -0.045F, -0.65F, 0.0F, 0.045F, -0.65F, 0.0F, 0.06F, 0.5F, 0.0F, -0.06F, 0.5F, 0.0F);
   }

   private static void K599e53f4b205f21b9d(class_287 b, Matrix4f m, int c) {
      int mid = Ke332a758d9a9133e67(c, (float)(c >>> 24) / 255.0F * 0.55F);
      int edge = c & 16777215;

      for(int i = 0; i < 16; ++i) {
         float x1 = SMOKE_X[i];
         float y1 = SMOKE_Y[i];
         float x2 = SMOKE_X[i + 1];
         float y2 = SMOKE_Y[i + 1];
         K6e6e86d6407db70fe5(b, m, 0.0F, 0.0F, 0.0F, c);
         K6e6e86d6407db70fe5(b, m, x1 * 0.5F, y1 * 0.5F, 0.0F, mid);
         K6e6e86d6407db70fe5(b, m, x2 * 0.5F, y2 * 0.5F, 0.0F, mid);
         K6e6e86d6407db70fe5(b, m, x1 * 0.5F, y1 * 0.5F, 0.0F, mid);
         K6e6e86d6407db70fe5(b, m, x1, y1, 0.0F, edge);
         K6e6e86d6407db70fe5(b, m, x2, y2, 0.0F, edge);
         K6e6e86d6407db70fe5(b, m, x1 * 0.5F, y1 * 0.5F, 0.0F, mid);
         K6e6e86d6407db70fe5(b, m, x2, y2, 0.0F, edge);
         K6e6e86d6407db70fe5(b, m, x2 * 0.5F, y2 * 0.5F, 0.0F, mid);
      }

   }

   @Generated
   public static K1162659203460bc8c0 getInstance() {
      return instance;
   }

   static {
      for(int i = 0; i <= 16; ++i) {
         double angle = (double)i * Math.PI / (double)8.0F;
         double radius = (double)1.0F + 0.1 * Math.sin(angle * (double)3.0F);
         SMOKE_X[i] = (float)(Math.cos(angle) * radius);
         SMOKE_Y[i] = (float)(Math.sin(angle) * radius);
      }

   }
}
