package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Line Glyphs",
   category = Kf76f042931183af5ba.RENDER,
   description = "Добавляет анимированные символы вокруг игрока"
)
public class Ke3e0017c49d95e97e2 extends Kab8a8c478f7d0077cd {
   private static final Ke3e0017c49d95e97e2 instance = new Ke3e0017c49d95e97e2();
   private static final double MAX_GLYPH_DISTANCE_SQ = (double)5184.0F;
   private final Kb9e875ead00e0d03bd count = (new Kb9e875ead00e0d03bd("Count")).value(55.0F).range(10.0F, 200.0F).step(5.0F);
   private final K00fef91b3d4d1b3ecc slow = (new K00fef91b3d4d1b3ecc("Slow Speed")).value(false);
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Color")).value("Theme").values("Theme", "Rainbow", "Single");
   private final List<K4d69a1bf9db467dafb> glyphs = new ArrayList();
   private final Random random = new Random(93882L);

   public Ke3e0017c49d95e97e2() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.count, this.slow, this.colorMode});
   }

   public void onEnable() {
      this.glyphs.clear();
   }

   public void onDisable() {
      this.glyphs.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            this.glyphs.removeIf(K4d69a1bf9db467dafb::K925b9b8ee6fb4e35c1);
            int cap = this.count.getValue().intValue();
            int attempts = 8;

            while(attempts-- > 0 && this.glyphs.size() < cap) {
               this.glyphs.add(new K4d69a1bf9db467dafb(this, this.K4800be88a48c46ab6c(), this.random.nextInt(7, 13)));
            }

            this.glyphs.forEach(K4d69a1bf9db467dafb::tick);
         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            this.Kdda8d495688f0a567b(event.matrixStack());
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent, renderEvent});
   }

   private void Kdda8d495688f0a567b(class_4587 matrixStack) {
      if (!this.glyphs.isEmpty()) {
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.setShader(class_10142.field_53876);

         try {
            class_243 camera = mc.field_1773.method_19418().method_19326();
            Matrix4f matrix = matrixStack.method_23760().method_23761();
            long now = System.currentTimeMillis();
            int index = 0;

            for(K4d69a1bf9db467dafb glyph : this.glyphs) {
               int pointCount = glyph.Kcc791a631b8764bf60();
               if (pointCount < 2) {
                  ++index;
               } else {
                  float lineWidth = this.K21bfef1b58bf9a2a46(glyph, camera);
                  RenderSystem.lineWidth(lineWidth);
                  class_287 lineBuffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
                  glyph.K13507035993e9aa619(lineBuffer, matrix, camera, index, now, (String)this.colorMode.getValue());
                  class_286.method_43433(lineBuffer.method_60800());
                  RenderSystem.lineWidth(Math.max(1.0F, lineWidth * 1.55F));
                  class_287 dotBuffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
                  glyph.Kf030095fcc0c303a7c(dotBuffer, matrix, camera, index, now, (String)this.colorMode.getValue(), Math.max(0.02F, lineWidth * 0.06F));
                  class_286.method_43433(dotBuffer.method_60800());
                  ++index;
               }
            }
         } finally {
            RenderSystem.lineWidth(1.0F);
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
         }

      }
   }

   private Color K0eb63415f23be5c87a(int index, float alphaPart, long now, String mode) {
      Color var10000;
      switch (mode) {
         case "Rainbow":
            float hue = ((float)now / 20.0F + (float)index * 2.0F) % 360.0F / 360.0F;
            var10000 = Color.getHSBColor(hue, 1.0F, 1.0F);
            break;
         case "Single":
            var10000 = Kb18d549c13309af9a5.primary();
            break;
         default:
            var10000 = Kb18d549c13309af9a5.gradient(index);
      }

      Color base = var10000;
      return K80ee108ea129adac11.setAlpha(base, Math.clamp((long)((int)(alphaPart * 255.0F)), 0, 255));
   }

   private float K21bfef1b58bf9a2a46(K4d69a1bf9db467dafb glyph, class_243 camera) {
      if (glyph.nodes.isEmpty()) {
         return 1.0F;
      } else {
         int[] first = (int[])glyph.nodes.getFirst();
         double dx = (double)first[0] - camera.field_1352;
         double dy = (double)first[1] - camera.field_1351;
         double dz = (double)first[2] - camera.field_1350;
         double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
         return 0.45F + 2.55F * (float)Math.max((double)0.0F, Math.min((double)1.0F, (double)1.0F - distance / (double)20.0F));
      }
   }

   private int[] K4800be88a48c46ab6c() {
      double fov = (double)(Integer)mc.field_1690.method_41808().method_41753();
      double yaw = Math.toRadians((double)this.random.nextInt((int)((double)mc.field_1724.method_36454() - fov * (double)0.75F), (int)((double)mc.field_1724.method_36454() + fov * (double)0.75F)));
      double distance = (double)this.random.nextInt(6, 24);
      int dx = (int)(-(Math.sin(yaw) * distance));
      int dy = this.random.nextInt(0, 12);
      int dz = (int)(Math.cos(yaw) * distance);
      class_243 eyePos = mc.field_1724.method_33571();
      return new int[]{(int)eyePos.field_1352 + dx, (int)eyePos.field_1351 + dy, (int)eyePos.field_1350 + dz};
   }

   private int[] Kf54825f985654b0897() {
      return new int[]{this.random.nextInt(0, 4) * 90, this.random.nextInt(-1, 2) * 90};
   }

   private int[] K15d229dd1ebf2ef727(int[] previousDirection) {
      int previousYaw = previousDirection[0];
      int previousPitch = previousDirection[1];
      int nextPitch = previousPitch;

      for(int i = 150; i > 0 && Math.abs(nextPitch - previousPitch) != 90; --i) {
         nextPitch = this.random.nextInt(-2, 2) * 90;
      }

      int nextYaw = previousYaw;

      for(int i = 5; i > 0 && Math.abs(nextYaw - previousYaw) != 90; --i) {
         nextYaw = this.random.nextInt(0, 4) * 90;
      }

      return new int[]{nextYaw, nextPitch};
   }

   private int[] step(int[] position, int[] direction, int range) {
      double yaw = Math.toRadians((double)direction[0]);
      double pitch = Math.toRadians((double)direction[1]);
      double horizontalRange = (double)range;
      int dy = (int)(Math.sin(pitch) * horizontalRange);
      if (pitch != (double)0.0F) {
         horizontalRange = (double)0.0F;
      }

      int dx = (int)(-(Math.sin(yaw) * horizontalRange));
      int dz = (int)(Math.cos(yaw) * horizontalRange);
      return new int[]{position[0] + dx, position[1] + dy, position[2] + dz};
   }

   @Generated
   public static Ke3e0017c49d95e97e2 getInstance() {
      return instance;
   }
}
