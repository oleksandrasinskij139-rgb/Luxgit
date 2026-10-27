package code.miaware.client.util.render.effects;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;

public final class K98eb176eb5e9618d43 {
   private static final class_310 mc = class_310.method_1551();
   private final K035b86a6e761d0d2cb modelRenderer = new K035b86a6e761d0d2cb();
   private Object world;
   private Object shapeModel;
   private class_2338 shapePos;
   private class_2680 shapeState;
   private K0b86f0877e6ecce4fe shape;
   private class_2338 lastPos;
   private class_243 displayedPos;
   private K0b86f0877e6ecce4fe prevShape;
   private class_243 prevPos;
   private float prevFade;
   private class_238 displayedBox;
   private long lastFrame;
   private float visibility;
   private boolean renderFailed;

   public boolean canReplaceOutline() {
      boolean var10000;
      if (!this.renderFailed && this.shape != null && this.world == mc.field_1687) {
         class_239 var2 = mc.field_1765;
         if (var2 instanceof class_3965) {
            class_3965 hit = (class_3965)var2;
            if (hit.method_17777().equals(this.shapePos)) {
               var10000 = true;
               return var10000;
            }
         }
      }

      var10000 = false;
      return var10000;
   }

   public void reset() {
      this.displayedBox = null;
      this.visibility = 0.0F;
      this.lastFrame = 0L;
      this.renderFailed = false;
      this.modelRenderer.close();
      this.shapeModel = null;
      this.shape = null;
      this.shapePos = null;
      this.shapeState = null;
      this.lastPos = null;
      this.displayedPos = null;
      this.prevShape = null;
      this.prevPos = null;
      this.prevFade = 0.0F;
   }

   public void render(K9ee087b2e1791ad6b2 event, String mode, boolean themeOutline, float opacity, float plasmaSpeed, float plasmaScale, float outlineWidth, float outlineOpacity, float glow, boolean smooth, float smoothSpeed) {
      if (this.world != mc.field_1687) {
         this.reset();
         this.world = mc.field_1687;
      }

      if (mc.field_1687 != null && mc.field_1724 != null && !mc.field_1690.field_1842 && !this.renderFailed) {
         long now = System.nanoTime();
         float dt = this.lastFrame == 0L ? 0.016F : Math.min(0.1F, (float)(now - this.lastFrame) / 1.0E9F);
         this.lastFrame = now;
         class_238 target = null;
         class_239 var17 = mc.field_1765;
         if (var17 instanceof class_3965) {
            class_3965 hit = (class_3965)var17;
            if (hit.method_17783() == class_240.field_1332) {
               class_2338 pos = hit.method_17777();
               class_2680 state = mc.field_1687.method_8320(pos);
               class_265 outline = state.method_26218(mc.field_1687, pos);
               if (!state.method_26215() && !outline.method_1110() && mc.field_1687.method_8621().method_11952(pos)) {
                  target = outline.method_1107().method_996(pos).method_1014(0.002);
                  if (!pos.equals(this.shapePos) || state != this.shapeState || this.shapeModel != mc.method_1541().method_3349(state)) {
                     this.shapeModel = mc.method_1541().method_3349(state);
                     this.shapePos = pos.method_10062();
                     this.shapeState = state;
                     if (this.shape != null && this.displayedPos != null && this.visibility > 0.05F) {
                        this.prevShape = this.shape;
                        this.prevPos = this.displayedPos;
                        this.prevFade = this.visibility;
                     }

                     try {
                        this.shape = K035b86a6e761d0d2cb.build(state, this.shapePos);
                     } catch (RuntimeException var21) {
                        this.shape = null;
                     }
                  }

                  this.lastPos = this.shapePos;
               }
            }
         }

         float blend = smooth ? 1.0F - (float)Math.exp((double)(-smoothSpeed * dt)) : 1.0F;
         this.visibility += ((float)(target == null ? 0 : 1) - this.visibility) * blend;
         if (target != null) {
            if (this.displayedBox != null && !(this.displayedBox.method_1005().method_1025(target.method_1005()) > (double)256.0F)) {
               this.displayedBox = new class_238(K456f32e5c7f0fde70a(this.displayedBox.field_1323, target.field_1323, blend), K456f32e5c7f0fde70a(this.displayedBox.field_1322, target.field_1322, blend), K456f32e5c7f0fde70a(this.displayedBox.field_1321, target.field_1321, blend), K456f32e5c7f0fde70a(this.displayedBox.field_1320, target.field_1320, blend), K456f32e5c7f0fde70a(this.displayedBox.field_1325, target.field_1325, blend), K456f32e5c7f0fde70a(this.displayedBox.field_1324, target.field_1324, blend));
            } else {
               this.displayedBox = target;
            }
         }

         if (this.displayedBox != null && !(this.visibility < 0.005F)) {
            int color = Kb18d549c13309af9a5.primary().getRGB();
            int modeIndex = mode.equals("Контур") ? 2 : (mode.equals("Заливка") ? 1 : 0);

            try {
               if (this.shape != null && this.lastPos != null) {
                  class_243 goal = class_243.method_24954(this.lastPos);
                  if (this.displayedPos != null && !(this.displayedPos.method_1025(goal) > (double)256.0F)) {
                     this.displayedPos = this.displayedPos.method_35590(goal, (double)blend);
                  } else {
                     this.displayedPos = goal;
                  }

                  if (this.prevShape != null) {
                     this.prevFade *= 1.0F - blend;
                     if (this.prevFade < 0.01F) {
                        this.prevShape = null;
                     } else {
                        this.modelRenderer.render(event, this.prevShape, this.prevPos, this.prevFade * 0.6F, now, color, themeOutline, opacity, plasmaSpeed, plasmaScale, outlineWidth, outlineOpacity / 100.0F, glow, modeIndex);
                     }
                  }

                  this.modelRenderer.render(event, this.shape, this.displayedPos, this.visibility, now, color, themeOutline, opacity, plasmaSpeed, plasmaScale, outlineWidth, outlineOpacity / 100.0F, glow, modeIndex);
                  return;
               }
            } catch (RuntimeException exception) {
               this.renderFailed = true;
               this.modelRenderer.close();
               System.err.println("[BlockOverlay] Renderer stopped: " + exception.getMessage());
            }

         }
      }
   }

   private static double K456f32e5c7f0fde70a(double start, double end, float blend) {
      return start + (end - start) * (double)blend;
   }
}
