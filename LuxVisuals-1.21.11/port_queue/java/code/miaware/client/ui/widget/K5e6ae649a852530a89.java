package code.miaware.client.ui.widget;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.utils.render.K142f6ac381bd580456;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.overlay.K0bcd6005ac6f8aedcc;
import code.miaware.client.ui.widget.overlay.K1d1af359ed93c5ac1a;
import code.miaware.client.ui.widget.overlay.K24c96eb9c47ca05ca8;
import code.miaware.client.ui.widget.overlay.K49cedf6bb2f6b45927;
import code.miaware.client.ui.widget.overlay.K4e272987679932df92;
import code.miaware.client.ui.widget.overlay.K81d5beb79596ea4544;
import code.miaware.client.ui.widget.overlay.K9ee1eeb793b49de7cb;
import code.miaware.client.ui.widget.overlay.Ka09e5bdbcc17da3434;
import code.miaware.client.ui.widget.overlay.Ka211a380631bff2eff;
import code.miaware.client.ui.widget.overlay.Ka747e2877c632013b5;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import code.miaware.client.ui.widget.overlay.Kc01b33a65c9e10c8ea;
import code.miaware.client.ui.widget.overlay.Kcf5c3bed12d824555f;
import code.miaware.client.ui.widget.overlay.Ke7bc31f4b136a6abd9;
import code.miaware.client.ui.widget.overlay.Kec0bd40f95e10c2d4e;
import code.miaware.client.ui.widget.overlay.Kf266cf69140b2e4d29;
import code.miaware.client.ui.widget.overlay.Kfbf316195041468d42;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3264;
import net.minecraft.class_3300;
import net.minecraft.class_332;
import net.minecraft.class_408;
import org.lwjgl.glfw.GLFW;

public class K5e6ae649a852530a89 {
   private static final K5e6ae649a852530a89 instance = new K5e6ae649a852530a89();
   private final List<K548ca8198549ddf9d7> widgets = new ArrayList();
   private boolean loaded;
   private K8d7165024ff567f5e7 renderListener;
   private final Ke8433384857ddcab3d renderCache = new Ke8433384857ddcab3d();
   private Ka211a380631bff2eff keyCaps;
   private int previousKeys = -1;

   public void load() {
      if (!this.loaded) {
         this.register(new Ka09e5bdbcc17da3434(), new K81d5beb79596ea4544(), new K24c96eb9c47ca05ca8(), new Ka747e2877c632013b5(), new Kec0bd40f95e10c2d4e(), new Kcf5c3bed12d824555f(), new Kc01b33a65c9e10c8ea(), new K1d1af359ed93c5ac1a(), new K0bcd6005ac6f8aedcc(), new Kf266cf69140b2e4d29(), new K49cedf6bb2f6b45927(), new K4e272987679932df92(), new Ka211a380631bff2eff(), new K9ee1eeb793b49de7cb(), new Kfbf316195041468d42(), new Ke7bc31f4b136a6abd9(), new Kb7ca6c2b4209b8a116() {
            public void render(class_332 context, int mouseX, int mouseY, float delta) {
            }

            public void mouseClicked(double mouseX, double mouseY, int button) {
               super.mouseClicked(mouseX, mouseY, button);
            }

            public void mouseReleased(double mouseX, double mouseY, int button) {
               super.mouseReleased(mouseX, mouseY, button);
            }

            public void keyPressed(int keyCode, int scanCode, int modifiers) {
            }

            public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
            }
         });

         for(K548ca8198549ddf9d7 widget : this.widgets) {
            if (widget instanceof Ka211a380631bff2eff) {
               Ka211a380631bff2eff caps = (Ka211a380631bff2eff)widget;
               this.keyCaps = caps;
            }
         }

         Kfae29c687712462375.getInstance().init();
         this.renderListener = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         }));
         ResourceManagerHelper.get(class_3264.field_14188).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            public class_2960 getFabricId() {
               return class_2960.method_60655("luxvisuals", "hud_render_cache");
            }

            public void method_14491(class_3300 manager) {
               K5e6ae649a852530a89.this.renderCache.invalidate();
            }
         });
         ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> {
            Ke8433384857ddcab3d var10001 = this.renderCache;
            Objects.requireNonNull(var10001);
            client.execute(var10001::release);
         });
         this.loaded = true;
      }
   }

   private void K89760a20d550042107(Kb590f148cfda70c907 event, K3f5fbd2c16fc2a9fd5 pass) {
      for(K548ca8198549ddf9d7 widget : this.widgets) {
         boolean live = widget instanceof Ke7bc31f4b136a6abd9;
         if (pass == K3f5fbd2c16fc2a9fd5.ALL || live == (pass == K3f5fbd2c16fc2a9fd5.LIVE)) {
            float visibility = widget.updateVisibilityAnimation();
            if (!(visibility <= 0.025F)) {
               float width = Math.max(1.0F, widget.getDraggable().getWidth());
               float height = Math.max(1.0F, widget.getDraggable().getHeight());
               float centerX = widget.getDraggable().getX() + width / 2.0F;
               float centerY = widget.getDraggable().getY() + height / 2.0F;
               float scale = 0.92F + visibility * 0.08F;
               event.matrixStack().method_22903();
               K142f6ac381bd580456 sample = K142f6ac381bd580456.start(widget.getName());

               try {
                  event.matrixStack().method_46416(centerX, centerY, 0.0F);
                  event.matrixStack().method_22905(scale, scale, 1.0F);
                  event.matrixStack().method_46416(-centerX, -centerY, 0.0F);
                  if (widget.isEnabled()) {
                     widget.renderEditorBackdrop(event.matrixStack());
                  }

                  widget.render(event);
                  if (pass == K3f5fbd2c16fc2a9fd5.CACHED && widget.getDraggable().getWidth() > 0.0F && widget.getDraggable().getHeight() > 0.0F) {
                     Ke8433384857ddcab3d.record(event.matrixStack().method_23760().method_23761(), widget.getDraggable().getX() - 2.0F, widget.getDraggable().getY() - 2.0F, widget.getDraggable().getWidth() + 4.0F, widget.getDraggable().getHeight() + 4.0F);
                  }

                  if (widget.isEnabled()) {
                     widget.renderEditorOverlay(event.matrixStack());
                  }
               } finally {
                  event.matrixStack().method_22909();
                  K142f6ac381bd580456.finish(sample);
               }
            }
         }
      }

   }

   public void register(K548ca8198549ddf9d7... widgets) {
      this.widgets.addAll(List.of(widgets));
   }

   public boolean dispatchHudMouseClick(double mouseX, double mouseY, int button) {
      return false;
   }

   public void dispatchWidgetMouseClick(double mouseX, double mouseY, int button) {
      for(K548ca8198549ddf9d7 widget : this.widgets) {
         if (widget.isEnabled()) {
            widget.mouseClicked(mouseX, mouseY, button);
         }
      }

   }

   public void dispatchWidgetMouseRelease(double mouseX, double mouseY, int button) {
      for(K548ca8198549ddf9d7 widget : this.widgets) {
         if (widget.isEnabled()) {
            widget.mouseReleased(mouseX, mouseY, button);
         }
      }

   }

   public boolean resizeHeldWidget(double verticalAmount) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1755 instanceof class_408 && Kfae29c687712462375.getInstance().isEnabled() && Double.isFinite(verticalAmount) && verticalAmount != (double)0.0F && GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), 0) == 1) {
         for(K548ca8198549ddf9d7 widget : this.widgets) {
            if (widget.isEnabled() && widget.getDraggable().isDragging()) {
               float previous = widget.getWidgetScale();
               widget.setWidgetScale(previous + (float)Math.max((double)-10.0F, Math.min((double)10.0F, verticalAmount)) * 0.05F);
               float next = widget.getWidgetScale();
               if (next != previous) {
                  widget.getDraggable().resizeWhileDragging(next / previous);
               }

               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Generated
   public List<K548ca8198549ddf9d7> getWidgets() {
      return this.widgets;
   }

   @Generated
   public boolean isLoaded() {
      return this.loaded;
   }

   @Generated
   public K8d7165024ff567f5e7 getRenderListener() {
      return this.renderListener;
   }

   @Generated
   public Ke8433384857ddcab3d getRenderCache() {
      return this.renderCache;
   }

   @Generated
   public Ka211a380631bff2eff getKeyCaps() {
      return this.keyCaps;
   }

   @Generated
   public int getPreviousKeys() {
      return this.previousKeys;
   }

   @Generated
   public static K5e6ae649a852530a89 getInstance() {
      return instance;
   }

   // $FF: synthetic method
   private void lambda$load$0(Kb590f148cfda70c907 event) {
   }
}
