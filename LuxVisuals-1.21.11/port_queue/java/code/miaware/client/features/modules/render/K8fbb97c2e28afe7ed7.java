package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_327;
import net.minecraft.class_3959;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_239.class_240;
import net.minecraft.class_327.class_6415;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Damage Particles",
   category = Kf76f042931183af5ba.RENDER,
   description = "Показывает урон цифрами при атаке"
)
public class K8fbb97c2e28afe7ed7 extends Kab8a8c478f7d0077cd {
   private static final K8fbb97c2e28afe7ed7 instance = new K8fbb97c2e28afe7ed7();
   private final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Комбо").values("Хил", "Дамаг", "Комбо");
   private final K00fef91b3d4d1b3ecc onlyCrits = (new K00fef91b3d4d1b3ecc("Только криты")).value(false);
   private final Kb9e875ead00e0d03bd lifeTime = (new Kb9e875ead00e0d03bd("Время жизни")).value(3.0F).range(1.0F, 10.0F).step(0.5F);
   private final Kb9e875ead00e0d03bd particleSize = (new Kb9e875ead00e0d03bd("Размер")).value(1.0F).range(0.5F, 1.5F).step(0.1F);
   private final List<K1736f93360a509caa5> particles = new ArrayList();
   private final Map<Integer, Float> lastHealthMap = new HashMap();
   private final Map<Integer, Boolean> recentAttacks = new HashMap();

   public K8fbb97c2e28afe7ed7() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.onlyCrits, this.lifeTime, this.particleSize});
   }

   public void onEnable() {
      super.onEnable();
      this.particles.clear();
      this.lastHealthMap.clear();
      this.recentAttacks.clear();
   }

   public void onDisable() {
      super.onDisable();
      this.particles.clear();
      this.lastHealthMap.clear();
      this.recentAttacks.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            for(class_1297 entity : mc.field_1687.method_18112()) {
               if (entity != null && !(mc.field_1724.method_5739(entity) > 32.0F) && entity.method_5805() && entity != mc.field_1724 && entity instanceof class_1309) {
                  class_1309 livingEntity = (class_1309)entity;
                  if (livingEntity.method_5767() && livingEntity instanceof class_1657) {
                     class_1657 player = (class_1657)livingEntity;
                     boolean hasArmor = !player.method_6118(class_1304.field_6169).method_7960() && !player.method_6118(class_1304.field_6174).method_7960() && !player.method_6118(class_1304.field_6172).method_7960() && !player.method_6118(class_1304.field_6166).method_7960();
                     if (!hasArmor) {
                        continue;
                     }
                  }

                  int entityId = entity.method_5628();
                  float currentHealth = livingEntity.method_6032();
                  float lastHealth = (Float)this.lastHealthMap.getOrDefault(entityId, livingEntity.method_6063());
                  this.lastHealthMap.put(entityId, currentHealth);
                  if (Math.abs(lastHealth - currentHealth) > 0.01F) {
                     float healthDiff = currentHealth - lastHealth;
                     String currentMode = (String)this.mode.getValue();
                     if ((!currentMode.equals("Хил") || !(healthDiff <= 0.0F)) && (!currentMode.equals("Дамаг") || !(healthDiff >= 0.0F))) {
                        if (healthDiff < 0.0F && (Boolean)this.onlyCrits.getValue()) {
                           if (!this.recentAttacks.containsKey(entityId)) {
                              continue;
                           }

                           boolean wasCrit = (Boolean)this.recentAttacks.get(entityId);
                           if (!wasCrit) {
                              continue;
                           }
                        }

                        if (this.Kcddbf1e6d5bb4db09f(livingEntity)) {
                           class_243 pos = entity.method_19538();
                           double y = entity.method_5829().field_1322 + (entity.method_5829().field_1325 - entity.method_5829().field_1322) / (double)2.0F;
                           long maxTime = (long)(this.lifeTime.getValue() * 1000.0F);
                           class_243 toPlayer = mc.field_1724.method_19538().method_1020(pos).method_1029();
                           double distance = (double)1.0F + Math.random() * (double)2.0F;
                           double offsetX = toPlayer.field_1352 * distance;
                           double offsetY = (double)0.5F + Math.random() * (double)1.0F;
                           double offsetZ = toPlayer.field_1350 * distance;
                           this.particles.add(new K1736f93360a509caa5(new class_243(pos.field_1352, y, pos.field_1350), healthDiff, new class_243(offsetX, offsetY, offsetZ), maxTime, livingEntity));
                        }
                     }
                  }
               }
            }

            this.lastHealthMap.entrySet().removeIf((entry) -> {
               class_1297 entity = mc.field_1687.method_8469((Integer)entry.getKey());
               return entity == null || !entity.method_5805();
            });
         }
      }));
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_1297 patt0$temp = event.entity();
         if (patt0$temp instanceof class_1309 target) {
            int entityId = target.method_5628();
            boolean isCrit = mc.field_1724.field_6017 > 0.0F && !mc.field_1724.method_24828() && !mc.field_1724.method_5624();
            this.recentAttacks.put(entityId, isCrit);
            (new Thread(() -> {
               try {
                  Thread.sleep(1000L);
                  this.recentAttacks.remove(entityId);
               } catch (InterruptedException var3) {
               }

            })).start();
         }
      }));
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.particles.removeIf(K1736f93360a509caa5::isToRemove);
         if (!this.particles.isEmpty()) {
            class_4587 matrices = event.matrixStack();
            class_4184 camera = mc.field_1773.method_19418();
            class_243 cameraPos = camera.method_19326();
            class_327 textRenderer = mc.field_1772;
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();

            for(K1736f93360a509caa5 particle : this.particles) {
               particle.update();
               if (particle.canRender()) {
                  matrices.method_22903();
                  class_243 pos = particle.getCurrentPos();
                  double x = pos.field_1352 - cameraPos.field_1352;
                  double y = pos.field_1351 - cameraPos.field_1351;
                  double z = pos.field_1350 - cameraPos.field_1350;
                  matrices.method_22904(x, y, z);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                  float scale = 0.025F * this.particleSize.getValue();
                  matrices.method_22905(-scale, -scale, scale);
                  String damageText;
                  int colorRGB;
                  if (particle.healthDiff > 0.0F) {
                     float hp = particle.healthDiff;
                     if (hp >= 4.0F) {
                        damageText = String.format("+%d", Math.round(hp));
                     } else {
                        damageText = String.format("+%.1f", hp);
                     }

                     colorRGB = 5635925;
                  } else {
                     float hp = -particle.healthDiff;
                     if (hp >= 4.0F) {
                        damageText = String.format("-%d", Math.round(hp));
                     } else {
                        damageText = String.format("-%.1f", hp);
                     }

                     colorRGB = 16724787;
                  }

                  float textWidth = (float)textRenderer.method_1727(damageText);
                  float percent = particle.percentRunner.getPercent();
                  float alpha;
                  if (percent < 0.1F) {
                     alpha = percent / 0.1F;
                  } else if (percent > 0.7F) {
                     alpha = (1.0F - percent) / 0.3F;
                  } else {
                     alpha = 1.0F;
                  }

                  int alphaInt = (int)(alpha * 255.0F);
                  int colorWithAlpha = alphaInt << 24 | colorRGB & 16777215;
                  Matrix4f matrix = matrices.method_23760().method_23761();
                  class_4597.class_4598 immediate = mc.method_22940().method_23000();
                  textRenderer.method_27521(damageText, -textWidth / 2.0F, 0.0F, colorWithAlpha, false, matrix, immediate, class_6415.field_33993, 0, 15728880);
                  immediate.method_22993();
                  matrices.method_22909();
               }
            }

            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, attackEvent, render3DEvent});
   }

   private boolean Kcddbf1e6d5bb4db09f(class_1309 entity) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_243 playerEyePos = mc.field_1724.method_33571();
         class_243 entityCenter = entity.method_5829().method_1005();
         class_3959 context = new class_3959(playerEyePos, entityCenter, class_3960.field_17558, class_242.field_1348, mc.field_1724);
         class_239 result = mc.field_1687.method_17742(context);
         return result.method_17783() == class_240.field_1333 || playerEyePos.method_1022(result.method_17784()) >= playerEyePos.method_1022(entityCenter) - (double)0.5F;
      } else {
         return false;
      }
   }

   @Generated
   public static K8fbb97c2e28afe7ed7 getInstance() {
      return instance;
   }
}
