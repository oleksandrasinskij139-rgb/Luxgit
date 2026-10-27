package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.math.K038ab8652c7f91cee6;
import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.prediction.K602c8c3c069aad5074;
import code.miaware.client.features.modules.render.prediction.K9e53644071e19ed440;
import code.miaware.client.features.modules.render.prediction.Kc4cbadf288293ee6d8;
import code.miaware.client.features.modules.render.prediction.Kc9d7fb9704003de4b6;
import code.miaware.client.features.modules.render.prediction.Kf199c7e91110f107cc;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1657;
import net.minecraft.class_1667;
import net.minecraft.class_1675;
import net.minecraft.class_1676;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1828;
import net.minecraft.class_1893;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3857;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_4587;
import net.minecraft.class_9278;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import net.minecraft.class_239.class_240;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

@ModuleRegister(
   name = "Predictions",
   category = Kf76f042931183af5ba.RENDER,
   description = "Показывает траекторию снаряда и точку приземления"
)
public final class K9b230e9e951a95fb0f extends Kab8a8c478f7d0077cd {
   private static final K9b230e9e951a95fb0f instance = new K9b230e9e951a95fb0f();
   private final Ka4b85e821e3749c3d9 render = (new Ka4b85e821e3749c3d9("Render")).value((new K00fef91b3d4d1b3ecc("Ender pearl")).value(true), (new K00fef91b3d4d1b3ecc("Trident")).value(false), (new K00fef91b3d4d1b3ecc("Arrow")).value(false), (new K00fef91b3d4d1b3ecc("Splash potion")).value(false));
   private final K00fef91b3d4d1b3ecc walls = (new K00fef91b3d4d1b3ecc("Through walls")).value(true);
   private final K00fef91b3d4d1b3ecc preview = (new K00fef91b3d4d1b3ecc("Предпросмотр")).value(true);
   private final List<Kb05ef0c4c4969251a3> markers = new ArrayList();

   public K9b230e9e951a95fb0f() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.render, this.walls, this.preview});
   }

   public void onDisable() {
      this.markers.clear();
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K964b9fc3d850187274)), K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K858602b0318f1fe54c))});
   }

   private boolean K8c4987f371595b160b(class_1799 item) {
      return item.method_31574(class_1802.field_8634) && this.render.isEnabled("Ender pearl") || item.method_31574(class_1802.field_8547) && this.render.isEnabled("Trident") || (item.method_7909() instanceof class_1753 || item.method_7909() instanceof class_1764 || item.method_31574(class_1802.field_8107)) && this.render.isEnabled("Arrow") || item.method_7909() instanceof class_1828 && this.render.isEnabled("Splash potion");
   }

   private class_1799 Kf7703ca9d0ab484e4d(class_1297 entity) {
      if (entity instanceof class_1684) {
         return new class_1799(class_1802.field_8634);
      } else if (entity instanceof class_1685) {
         return new class_1799(class_1802.field_8547);
      } else if (entity instanceof class_1667) {
         return new class_1799(class_1802.field_8107);
      } else {
         if (entity instanceof class_3857) {
            class_3857 thrown = (class_3857)entity;
            if (thrown.method_7495().method_7909() instanceof class_1828) {
               return thrown.method_7495();
            }
         }

         return class_1799.field_8037;
      }
   }

   private K9e53644071e19ed440 K1fe5bcc27950ace2de(class_243 position, class_243 velocity, class_1799 item, final class_1297 source, final class_1297 owner) {
      boolean arrow = item.method_31574(class_1802.field_8107) || item.method_7909() instanceof class_1753 || item.method_7909() instanceof class_1764;
      double gravity = item.method_31574(class_1802.field_8634) ? 0.03 : 0.05;
      return Kc4cbadf288293ee6d8.trace(position, velocity, gravity, arrow ? 0.6 : (item.method_31574(class_1802.field_8547) ? 0.99 : 0.8), new Kc9d7fb9704003de4b6() {
         public boolean water(class_243 p) {
            return K4600bef438dafc1a9c.mc.field_1687.method_8316(class_2338.method_49638(p)).method_15767(class_3486.field_15517);
         }

         public Kf199c7e91110f107cc hit(class_243 from, class_243 to) {
            class_3965 block = K4600bef438dafc1a9c.mc.field_1687.method_17742(new class_3959(from, to, class_3960.field_17558, class_242.field_1348, (class_1297)(source == null ? K4600bef438dafc1a9c.mc.field_1724 : source)));
            class_243 point = null;
            class_243 normal = null;
            if (block.method_17783() == class_240.field_1332) {
               point = block.method_17784();
               class_2350 side = block.method_17780();
               normal = new class_243((double)side.method_10148(), (double)side.method_10164(), (double)side.method_10165());
            }

            class_1297 context = (class_1297)(source == null ? K4600bef438dafc1a9c.mc.field_1724 : source);
            class_3966 entityHit = class_1675.method_37226(K4600bef438dafc1a9c.mc.field_1687, context, from, point == null ? to : point, (new class_238(from, to)).method_1014((double)1.0F), (e) -> e != source && e != owner && e.method_5805() && e.method_5863() && !e.method_7325(), 0.3F);
            if (entityHit != null) {
               point = entityHit.method_17784();
               normal = new class_243((double)0.0F, (double)1.0F, (double)0.0F);
            }

            return point == null ? null : new Kf199c7e91110f107cc(point, normal);
         }
      }, 300, (double)(mc.field_1687.method_31607() - 8));
   }

   private void K1366e2dbace4e158fb(float delta, class_243 camera) {
      this.markers.clear();

      for(class_1297 entity : mc.field_1687.method_18112()) {
         class_1799 item = this.Kf7703ca9d0ab484e4d(entity);
         if (!item.method_7960() && this.K8c4987f371595b160b(item) && !(entity.method_18798().method_1027() < 1.0E-6)) {
            class_1297 var10000;
            if (entity instanceof class_1676) {
               class_1676 p = (class_1676)entity;
               var10000 = p.method_24921();
            } else {
               var10000 = null;
            }

            class_1297 owner = var10000;
            String name = owner instanceof class_1657 ? K131e64425767c5c203.sanitizeDisplayText(owner.method_5477().getString()) : "";
            K9e53644071e19ed440 trace = this.K1fe5bcc27950ace2de(entity.method_30950(delta), entity.method_18798(), item, entity, owner);
            this.markers.add(new Kb05ef0c4c4969251a3(trace, trace.path(), item, name, entity.method_5628(), false));
         }
      }

      if ((Boolean)this.preview.getValue()) {
         boolean offHand = false;
         class_1799 item = mc.field_1724.method_6047();
         if (!this.K8c4987f371595b160b(item)) {
            item = mc.field_1724.method_6079();
            offHand = true;
         }

         if (this.K8c4987f371595b160b(item)) {
            class_243 eye = new class_243(class_3532.method_16436((double)delta, mc.field_1724.field_6038, mc.field_1724.method_23317()), class_3532.method_16436((double)delta, mc.field_1724.field_5971, mc.field_1724.method_23318()) + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()), class_3532.method_16436((double)delta, mc.field_1724.field_5989, mc.field_1724.method_23321()));
            class_243 position = eye.method_1031((double)0.0F, -0.1, (double)0.0F);
            boolean bow = item.method_7909() instanceof class_1753;
            boolean crossbow = item.method_7909() instanceof class_1764;
            double speed = item.method_31574(class_1802.field_8634) ? (double)1.5F : (item.method_31574(class_1802.field_8547) ? (double)2.5F : (bow ? (double)3.0F : (crossbow ? 3.15 : (double)0.5F)));
            if (bow && mc.field_1724.method_6115()) {
               speed *= (double)class_1753.method_7722(mc.field_1724.method_6048());
            }

            if (item.method_31574(class_1802.field_8547) && mc.field_1724.method_6115()) {
               speed *= (double)class_3532.method_15363((float)mc.field_1724.method_6048() / 10.0F, 0.0F, 1.0F);
            }

            float yaw = mc.field_1724.method_5705(delta);
            float pitch = mc.field_1724.method_5695(delta);
            class_243 look = class_243.method_1030(pitch, yaw);
            class_243 direction = item.method_7909() instanceof class_1828 ? class_243.method_1030(pitch - 20.0F, yaw) : look;
            boolean firstPerson = mc.field_1690.method_31044().method_31034();
            class_243 hand = K602c8c3c069aad5074.origin(firstPerson ? camera : eye, look, yaw, mc.field_1724.method_6068() == class_1306.field_6183, offHand, firstPerson);
            class_243 inherited = K602c8c3c069aad5074.inheritedMovement(mc.field_1724.method_60478(), mc.field_1724.method_24828(), crossbow);
            if (crossbow && this.Kbccd6efa14464b6ad3(item)) {
               double y = Math.toRadians((double)yaw);
               double p = Math.toRadians((double)pitch);
               class_243 axis = new class_243(-Math.sin(y) * Math.sin(p), Math.cos(p), Math.cos(y) * Math.sin(p));

               for(int degrees : new int[]{-10, 0, 10}) {
                  double angle = Math.toRadians((double)degrees);
                  class_243 shot = direction.method_1021(Math.cos(angle)).method_1019(axis.method_1036(direction).method_1021(Math.sin(angle))).method_1029();
                  this.Kc428c4d6b0f046876d(position, shot.method_1021(speed), item, hand);
               }
            } else {
               this.Kc428c4d6b0f046876d(position, direction.method_1021(speed).method_1019(inherited), item, hand);
            }

         }
      }
   }

   private boolean Kbccd6efa14464b6ad3(class_1799 stack) {
      class_9278 charged = (class_9278)stack.method_57824(class_9334.field_49649);
      if (charged != null && charged.method_57437().size() > 1) {
         return true;
      } else {
         class_9304 enchantments = (class_9304)stack.method_57824(class_9334.field_49633);
         return enchantments != null && enchantments.method_57534().stream().anyMatch((e) -> e.method_40225(class_1893.field_9108));
      }
   }

   private void Kc428c4d6b0f046876d(class_243 position, class_243 velocity, class_1799 item, class_243 hand) {
      K9e53644071e19ed440 trace = this.K1fe5bcc27950ace2de(position, velocity, item, (class_1297)null, mc.field_1724);
      this.markers.add(new Kb05ef0c4c4969251a3(trace, K602c8c3c069aad5074.create(trace.path(), hand), item, mc.field_1724.method_5477().getString(), -1, true));
   }

   private void K964b9fc3d850187274(K9ee087b2e1791ad6b2 event) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_243 camera = event.cameraPos();
         this.K1366e2dbace4e158fb(event.partialTicks(), camera);
         class_4587 matrices = event.matrixStack();
         boolean depth = GL11.glIsEnabled(2929);
         boolean blend = GL11.glIsEnabled(3042);
         boolean cull = GL11.glIsEnabled(2884);
         boolean depthMask = GL11.glGetBoolean(2930);
         float[] shaderColor = (float[])RenderSystem.getShaderColor().clone();
         int srcRgb = GL11.glGetInteger(32969);
         int dstRgb = GL11.glGetInteger(32968);
         int srcAlpha = GL11.glGetInteger(32971);
         int dstAlpha = GL11.glGetInteger(32970);
         matrices.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableCull();
         RenderSystem.depthMask(false);
         if ((Boolean)this.walls.getValue()) {
            RenderSystem.disableDepthTest();
         } else {
            RenderSystem.enableDepthTest();
         }

         RenderSystem.setShader(class_10142.field_53876);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

         try {
            class_287 mesh = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            Matrix4f matrix = matrices.method_23760().method_23761();
            int count = 0;
            double time = (double)System.nanoTime() / (double)1.0E9F;

            for(Kb05ef0c4c4969251a3 marker : this.markers) {
               List<class_243> path = marker.renderPath;

               for(int i = 1; i < path.size(); ++i) {
                  class_243 a = ((class_243)path.get(i - 1)).method_1020(camera);
                  class_243 b = ((class_243)path.get(i)).method_1020(camera);
                  class_243 side = b.method_1020(a).method_1036(a.method_1019(b).method_1021((double)0.5F)).method_1029();

                  for(double[] layer : new double[][]{{0.035, (double)12.0F}, {0.014, (double)38.0F}, {0.003, (double)200.0F}}) {
                     class_243 offset = side.method_1021(layer[0]);
                     K93f4b8c86d1a887bb1(mesh, matrix, a.method_1019(offset), a.method_1020(offset), b.method_1020(offset), b.method_1019(offset), (int)layer[1]);
                     count += 6;
                  }
               }

               if (marker.trace.impact() != null) {
                  Kf199c7e91110f107cc hit = marker.trace.impact();
                  class_243 normal = hit.normal();
                  class_243 center = hit.position().method_1019(normal.method_1021(0.012)).method_1020(camera);
                  class_243 u = normal.method_1036(Math.abs(normal.field_1351) > 0.8 ? new class_243((double)1.0F, (double)0.0F, (double)0.0F) : new class_243((double)0.0F, (double)1.0F, (double)0.0F)).method_1029();
                  class_243 v = normal.method_1036(u).method_1029();

                  for(double[] layer : new double[][]{{0.07, (double)16.0F}, {0.028, (double)55.0F}, {0.006, (double)245.0F}}) {
                     count += K11b384d9b0136fb7f7(mesh, matrix, center, u, v, 0.55, layer[0], (int)layer[1]);
                  }

                  double pulse = (time * 0.7 + (double)marker.id * 0.13) % (double)1.0F;
                  if (pulse < (double)0.0F) {
                     ++pulse;
                  }

                  count += K11b384d9b0136fb7f7(mesh, matrix, center, u, v, 0.55 + pulse * 0.22, 0.003, (int)(((double)1.0F - pulse) * (double)38.0F));

                  for(int i = 0; i < 64; ++i) {
                     double a = (double)i * Math.PI * (double)2.0F / (double)64.0F;
                     double b = (double)(i + 1) * Math.PI * (double)2.0F / (double)64.0F;
                     K0eb9f2e68f28c4f1c7(mesh, matrix, center, 100);
                     K0eb9f2e68f28c4f1c7(mesh, matrix, center.method_1019(u.method_1021(Math.cos(a) * 0.17)).method_1019(v.method_1021(Math.sin(a) * 0.17)), 0);
                     K0eb9f2e68f28c4f1c7(mesh, matrix, center.method_1019(u.method_1021(Math.cos(b) * 0.17)).method_1019(v.method_1021(Math.sin(b) * 0.17)), 0);
                     count += 3;
                  }
               }
            }

            if (count > 0) {
               class_286.method_43433(mesh.method_60800());
            } else {
               mesh.method_60794();
            }
         } finally {
            matrices.method_22909();
            RenderSystem.depthMask(depthMask);
            if (depth) {
               RenderSystem.enableDepthTest();
            } else {
               RenderSystem.disableDepthTest();
            }

            if (blend) {
               RenderSystem.enableBlend();
            } else {
               RenderSystem.disableBlend();
            }

            if (cull) {
               RenderSystem.enableCull();
            } else {
               RenderSystem.disableCull();
            }

            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha);
            RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3]);
         }

      } else {
         this.markers.clear();
      }
   }

   private static int K11b384d9b0136fb7f7(class_287 mesh, Matrix4f m, class_243 center, class_243 u, class_243 v, double radius, double width, int alpha) {
      for(int i = 0; i < 96; ++i) {
         double a = (double)i * Math.PI * (double)2.0F / (double)96.0F;
         double b = (double)(i + 1) * Math.PI * (double)2.0F / (double)96.0F;
         class_243 da = u.method_1021(Math.cos(a)).method_1019(v.method_1021(Math.sin(a)));
         class_243 db = u.method_1021(Math.cos(b)).method_1019(v.method_1021(Math.sin(b)));
         K93f4b8c86d1a887bb1(mesh, m, center.method_1019(da.method_1021(radius - width)), center.method_1019(da.method_1021(radius + width)), center.method_1019(db.method_1021(radius + width)), center.method_1019(db.method_1021(radius - width)), alpha);
      }

      return 576;
   }

   private static void K0eb9f2e68f28c4f1c7(class_287 b, Matrix4f m, class_243 p, int alpha) {
      b.method_22918(m, (float)p.field_1352, (float)p.field_1351, (float)p.field_1350).method_1336(199, 249, 255, alpha);
   }

   private static void K93f4b8c86d1a887bb1(class_287 b, Matrix4f m, class_243 a, class_243 c, class_243 d, class_243 e, int alpha) {
      K0eb9f2e68f28c4f1c7(b, m, a, alpha);
      K0eb9f2e68f28c4f1c7(b, m, c, alpha);
      K0eb9f2e68f28c4f1c7(b, m, d, alpha);
      K0eb9f2e68f28c4f1c7(b, m, a, alpha);
      K0eb9f2e68f28c4f1c7(b, m, d, alpha);
      K0eb9f2e68f28c4f1c7(b, m, e, alpha);
   }

   private void K858602b0318f1fe54c(Kb590f148cfda70c907 event) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         for(Kb05ef0c4c4969251a3 marker : this.markers) {
            if (!marker.held && marker.trace.impact() != null) {
               class_243 anchor = marker.trace.impact().position().method_1031((double)0.0F, 0.12, (double)0.0F);
               Vector2f p = K038ab8652c7f91cee6.project((double)((float)anchor.field_1352), (double)((float)anchor.field_1351), (double)((float)anchor.field_1350));
               if (Float.isFinite(p.x) && p.x != Float.MAX_VALUE && p.y != Float.MAX_VALUE) {
                  String owner = marker.owner;
                  if (owner.length() > 13) {
                     owner = owner.substring(0, 12) + "…";
                  }

                  String seconds = String.format(Locale.ROOT, "%.1fс", marker.trace.ticks() * 0.05);
                  float size = 5.3F;
                  float ownerWidth = K896065ea0a2bba4f47.SF_MEDIUM.getWidth(owner, size);
                  float timeWidth = K896065ea0a2bba4f47.SF_MEDIUM.getWidth(seconds, size);
                  float width = 15.0F + ownerWidth + (float)(owner.isEmpty() ? 0 : 3) + timeWidth;
                  float x = p.x - width / 2.0F;
                  float y = p.y - 13.0F;
                  class_4587 ms = event.matrixStack();
                  K2b5e2c5f2b9891b450.RECT.draw(ms, x - 0.4F, y - 0.4F, width + 0.8F, 10.8F, 5.4F, new Color(156, 206, 207, 30));
                  K2b5e2c5f2b9891b450.RECT.draw(ms, x, y, width, 10.0F, 5.0F, new Color(13, 26, 26, 186));
                  event.context().method_51448().method_22903();
                  event.context().method_51448().method_46416(x + 3.0F, y + 2.0F, 0.0F);
                  event.context().method_51448().method_22905(0.375F, 0.375F, 1.0F);
                  event.context().method_51427(marker.item, 0, 0);
                  event.context().method_51448().method_22909();
                  K896065ea0a2bba4f47.SF_MEDIUM.drawText(ms, owner, x + 12.0F, y + 2.2F, size, new Color(234, 244, 243));
                  K896065ea0a2bba4f47.SF_MEDIUM.drawText(ms, seconds, x + 12.0F + ownerWidth + (float)(owner.isEmpty() ? 0 : 3), y + 2.2F, size, new Color(172, 193, 193));
               }
            }
         }

      }
   }

   @Generated
   public static K9b230e9e951a95fb0f getInstance() {
      return instance;
   }
}
