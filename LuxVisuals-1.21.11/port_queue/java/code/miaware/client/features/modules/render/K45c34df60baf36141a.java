package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.move.K7dc5f8e115b48c51ca;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_2960;
import net.minecraft.class_4587;

@ModuleRegister(
   name = "Jump Circle",
   category = Kf76f042931183af5ba.RENDER,
   description = "Создает круг при прыжке"
)
public class K45c34df60baf36141a extends Kab8a8c478f7d0077cd {
   private static final K45c34df60baf36141a instance = new K45c34df60baf36141a();
   private final Kbd66519397e9a89967 texture = (new Kbd66519397e9a89967("Texture")).value("Круг 1").values("Круг 1", "Круг 2", "Круг 3", "Круг 4", "Круг 5", "Круг 6", "Круг 7", "Круг 8", "Круг 9", "Круг 10");
   private final Kbd66519397e9a89967 outAnimation = (new Kbd66519397e9a89967("Out animation")).value("Slow").values("In", "Slow");
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Size")).value(1.0F).range(0.1F, 3.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd lifeTime = (new Kb9e875ead00e0d03bd("Life time")).value(10.0F).range(1.0F, 30.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd spawnDur = (new Kb9e875ead00e0d03bd("Spawn duration")).value(6.0F).range(1.0F, 30.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd dyingDur = (new Kb9e875ead00e0d03bd("Dying duration")).value(4.0F).range(1.0F, 30.0F).step(1.0F);
   private final List<Ke3847952eb862510db> circles = new ArrayList();
   private final class_2960 bubblesTexture = class_2960.method_60655("luxvisuals", "textures/hitbubble.png");
   private static final int LUT_SIZE = 512;
   private static final float[] SIN_LUT = new float[512];
   private static final float[] COS_LUT = new float[512];
   private static final float[] PULSE_LAYER_A = new float[]{0.12F, 0.45F, 1.0F};
   private static final float[] PULSE_LEN_M = new float[]{1.5F, 1.0F, 0.65F};
   private static final float[] SPIRAL_LAYER_ALPHA = new float[]{0.1F, 0.4F, 0.9F};
   private static final float[] ARC_SPEEDS = new float[]{9.0E-4F, -7.0E-4F, 0.0013F, -0.0011F, 6.0E-4F, -0.0015F};
   private static final float[] ARC_LEN = new float[]{0.55F, 0.7F, 0.45F, 0.65F, 0.5F, 0.6F};
   private static final float[] ARC_R_MULT = new float[]{1.0F, 0.96F, 1.03F, 0.98F, 1.01F, 0.95F};
   private static final int[] ORBIT_DOT_COUNTS = new int[]{16, 24};
   private static final float[] ORBIT_SPDS = new float[]{0.001F, 6.0E-4F};
   private static final int[] ORBIT_DIRS = new int[]{1, -1};
   private static final float[] NOVA_LAYER_A = new float[]{0.08F, 0.35F, 0.9F};
   private static final float[] NOVA_LAYER_W = new float[]{3.0F, 1.5F, 0.6F};
   private static final double TWO_PI_ORBIT_0;
   private static final double TWO_PI_ORBIT_1;
   private static final float[] SPOKE_EDGE;
   private static final float[] ARC_GLOW_POW;
   private static final float[] DOT_GLOW_POW;
   private static final float[] CENTER_GLOW_POW;
   private static final float[] HALO_POW;
   private static final float[] ARC_EDGE_LUT;
   private static final float[] NEB_POW;
   private static final float[] PULSE_CENTER_POW;
   private static final float[] TRAIL_EDGE;
   private static final float[] TRAIL_ANGLE_OFFSET;
   private static final float[] TRAIL_F;
   private static final float[] TRAIL_ANGLE_OFFSET_B;
   private static final float[] TRAIL_F_B;
   private static final float[] SPIRAL_HALO_POW;
   private static final float[] SPIRAL_INNER_POW;
   private static final float[] SPIRAL_TIP_POW;
   private static final float[] TRAIL_COS_OFFSET;
   private static final float[] TRAIL_SIN_OFFSET;
   private static final float[] TRAIL_COS_OFFSET_B;
   private static final float[] TRAIL_SIN_OFFSET_B;
   private static final float[] SPIRAL_EDGE_POW07;
   private static final float TAIL_COS_DELTA;
   private static final float TAIL_SIN_DELTA;

   public K45c34df60baf36141a() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.texture, this.outAnimation, this.size, this.lifeTime, this.spawnDur, this.dyingDur});
   }

   private String texture() {
      switch ((String)this.texture.getValue()) {
         case "Круг 1" -> {
            return "circle/lean";
         }
         case "Круг 2" -> {
            return "circle/glow_fat";
         }
         case "Круг 3" -> {
            return "circle/oval";
         }
         case "Круг 4" -> {
            return "circle/ux";
         }
         case "Круг 5" -> {
            return "circle/dry-clean";
         }
         default -> {
            return "circle/glow_fat";
         }
      }
   }

   private boolean K21e502bc8c2571d3c1() {
      return this.texture.is("Круг 6");
   }

   private boolean K90f7442b9644490d37() {
      return this.texture.is("Круг 7");
   }

   private boolean K6af46f99573dc7a4ab() {
      return this.texture.is("Круг 3") || this.texture.is("Круг 4") || this.texture.is("Круг 5");
   }

   private boolean K8fa2599e36f2184af2() {
      return this.texture.is("Круг 8") || this.texture.is("Круг 9") || this.texture.is("Круг 10");
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.circles.removeIf(Ke3847952eb862510db::update)));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_4587 matrixStack = event.matrixStack();
         K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);
         boolean pentagram = this.K90f7442b9644490d37();
         boolean bubbles = this.K21e502bc8c2571d3c1();
         boolean procedural = this.K8fa2599e36f2184af2();
         if (!procedural && !pentagram && !bubbles) {
            RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage(this.texture()));
         } else if (bubbles) {
            int textureId = mc.method_1531().method_4619(this.bubblesTexture).method_4624();
            RenderSystem.setShaderTexture(0, textureId);
         }

         for(Ke3847952eb862510db circle : this.circles) {
            circle.render(matrixStack, pentagram, bubbles, procedural);
         }

         K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
      }));
      K8d7165024ff567f5e7 jumpEvent = K7dc5f8e115b48c51ca.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.circles.add(new Ke3847952eb862510db(this, mc.field_1724.method_19538().method_1031((double)0.0F, 0.15, (double)0.0F), this.size.getValue(), this.circles.size() * 9, this.lifeTime.getValue().intValue() * 50, this.spawnDur.getValue().intValue() * 50, this.dyingDur.getValue().intValue() * 50, (String)this.outAnimation.getValue(), (String)this.texture.getValue(), mc.field_1724.method_36454()))));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, renderEvent, jumpEvent});
   }

   @Generated
   public static K45c34df60baf36141a getInstance() {
      return instance;
   }

   static {
      TWO_PI_ORBIT_0 = (Math.PI * 2D) / (double)ORBIT_DOT_COUNTS[0];
      TWO_PI_ORBIT_1 = (Math.PI * 2D) / (double)ORBIT_DOT_COUNTS[1];
      SPOKE_EDGE = new float[11];
      ARC_GLOW_POW = new float[8];
      DOT_GLOW_POW = new float[5];
      CENTER_GLOW_POW = new float[10];
      HALO_POW = new float[14];
      ARC_EDGE_LUT = new float[64];
      NEB_POW = new float[7];
      PULSE_CENTER_POW = new float[9];
      TRAIL_EDGE = new float[40];
      TRAIL_ANGLE_OFFSET = new float[40];
      TRAIL_F = new float[40];
      TRAIL_ANGLE_OFFSET_B = new float[40];
      TRAIL_F_B = new float[40];
      SPIRAL_HALO_POW = new float[10];
      SPIRAL_INNER_POW = new float[10];
      SPIRAL_TIP_POW = new float[7];
      TRAIL_COS_OFFSET = new float[40];
      TRAIL_SIN_OFFSET = new float[40];
      TRAIL_COS_OFFSET_B = new float[40];
      TRAIL_SIN_OFFSET_B = new float[40];
      SPIRAL_EDGE_POW07 = new float[80];
      TAIL_COS_DELTA = (float)Math.cos(0.1);
      TAIL_SIN_DELTA = (float)Math.sin(0.1);

      for(int i = 0; i < 512; ++i) {
         double a = 0.01227184630308513 * (double)i;
         SIN_LUT[i] = (float)Math.sin(a);
         COS_LUT[i] = (float)Math.cos(a);
      }

      for(int i = 0; i <= 10; ++i) {
         SPOKE_EDGE[i] = (float)Math.sin(Math.PI * (double)((float)i / 10.0F));
      }

      for(int g = 0; g < 8; ++g) {
         float v = 1.0F - (float)g / 8.0F;
         ARC_GLOW_POW[g] = v * v;
      }

      for(int g = 0; g < 5; ++g) {
         float v = 1.0F - (float)g / 5.0F;
         DOT_GLOW_POW[g] = v * v;
      }

      for(int g = 0; g < 10; ++g) {
         float v = 1.0F - (float)g / 10.0F;
         CENTER_GLOW_POW[g] = v * v * v;
      }

      for(int g = 0; g < 14; ++g) {
         float gf = (float)g / 14.0F;
         HALO_POW[g] = (float)Math.pow((double)(1.0F - gf), (double)2.2F);
      }

      for(int i = 0; i < 64; ++i) {
         ARC_EDGE_LUT[i] = (float)Math.sin(Math.PI * (double)((float)i / 63.0F));
      }

      for(int b = 0; b < 7; ++b) {
         float v = 1.0F - (float)b / 7.0F;
         NEB_POW[b] = (float)Math.pow((double)v, (double)1.4F);
      }

      for(int g = 0; g < 9; ++g) {
         float v = 1.0F - (float)g / 9.0F;
         PULSE_CENTER_POW[g] = v * v * v;
      }

      for(int seg = 0; seg < 40; ++seg) {
         float fA = (float)seg / 40.0F;
         TRAIL_EDGE[seg] = (float)Math.sin(Math.PI * (double)fA);
         TRAIL_ANGLE_OFFSET[seg] = (float)((double)fA * Math.PI * 1.6);
         TRAIL_F[seg] = fA;
      }

      for(int seg = 0; seg < 40; ++seg) {
         float fB = (float)(seg + 1) / 40.0F;
         TRAIL_ANGLE_OFFSET_B[seg] = (float)((double)fB * Math.PI * 1.6);
         TRAIL_F_B[seg] = fB;
      }

      for(int g = 0; g < 10; ++g) {
         float v = 1.0F - (float)g / 10.0F;
         SPIRAL_HALO_POW[g] = v * v;
      }

      for(int i = 0; i < 10; ++i) {
         float v = 1.0F - (float)i / 10.0F;
         SPIRAL_INNER_POW[i] = (float)Math.pow((double)v, (double)2.5F);
      }

      for(int g = 0; g < 7; ++g) {
         float v = 1.0F - (float)g / 7.0F;
         SPIRAL_TIP_POW[g] = v * v;
      }

      for(int seg = 0; seg < 40; ++seg) {
         TRAIL_COS_OFFSET[seg] = (float)Math.cos((double)TRAIL_ANGLE_OFFSET[seg]);
         TRAIL_SIN_OFFSET[seg] = (float)Math.sin((double)TRAIL_ANGLE_OFFSET[seg]);
         TRAIL_COS_OFFSET_B[seg] = (float)Math.cos((double)TRAIL_ANGLE_OFFSET_B[seg]);
         TRAIL_SIN_OFFSET_B[seg] = (float)Math.sin((double)TRAIL_ANGLE_OFFSET_B[seg]);
      }

      for(int i = 0; i < 80; ++i) {
         float fA = (float)i / 80.0F;
         SPIRAL_EDGE_POW07[i] = (float)Math.pow(Math.sin(Math.PI * (double)fA), (double)0.7F);
      }

   }
}
