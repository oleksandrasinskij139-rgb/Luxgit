package code.miaware.api.utils.neuro;

import ai.catboost.CatBoostModel;
import ai.catboost.CatBoostPredictions;
import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.system.backend.K5d3444fb5779729097;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.animation.wrap.infinity.Kcd44fb0f71a556c8e0;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.api.utils.rotation.rotations.K61006a43f5c18a1cfa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_2246;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5611;

public class K49e4f6d5d86e774072 extends K5d3444fb5779729097 implements K4600bef438dafc1a9c {
   private CatBoostModel yawModel;
   private CatBoostModel pitchModel;
   private final Kf39dc27225f4d9fe6e timer = new Kf39dc27225f4d9fe6e();
   public Kcd44fb0f71a556c8e0 interpolation = new Kcd44fb0f71a556c8e0();
   private final List<Path> tempModelFiles = new ArrayList();

   public void onEvent() {
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.timer.reset()));
      this.addEvents(new K8d7165024ff567f5e7[]{attackEvent});
   }

   public void loadModel(String model) {
      this.close();
      if (!model.equals("Default")) {
         try {
            String modelPath = K93d0ef925a2fcdf0d6.CONFIG_PATH_AI_MODELS + "/" + model;
            this.yawModel = CatBoostModel.loadModel(modelPath + "_yaw.model");
            this.pitchModel = CatBoostModel.loadModel(modelPath + "_pitch.model");
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } else {
         try {
            this.yawModel = this.Kae374c94e0bb9d963a(model.toLowerCase() + "_yaw.model");
            this.pitchModel = this.Kae374c94e0bb9d963a(model.toLowerCase() + "_pitch.model");
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

   }

   private CatBoostModel Kae374c94e0bb9d963a(String resourceName) throws Exception {
      InputStream is = Kc1f6f8c785f6a08448.getFromAssets("models/" + resourceName);

      CatBoostModel var13;
      try {
         if (is == null) {
            throw new FileNotFoundException(resourceName);
         }

         Path tempPath = Files.createTempFile("miaware-" + resourceName.replace('.', '-'), ".cbm");
         File temp = tempPath.toFile();
         this.tempModelFiles.add(tempPath);
         byte[] buffer = is.readAllBytes();
         FileOutputStream out = new FileOutputStream(temp);

         try {
            out.write(buffer);
         } catch (Throwable var11) {
            try {
               out.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }

            throw var11;
         }

         out.close();
         var13 = CatBoostModel.loadModel(temp.getAbsolutePath());
      } catch (Throwable var12) {
         if (is != null) {
            try {
               is.close();
            } catch (Throwable var9) {
               var12.addSuppressed(var9);
            }
         }

         throw var12;
      }

      if (is != null) {
         is.close();
      }

      return var13;
   }

   public boolean isLoaded() {
      return this.yawModel != null && this.pitchModel != null;
   }

   public void close() {
      try {
         if (this.yawModel != null) {
            this.yawModel.close();
         }

         if (this.pitchModel != null) {
            this.pitchModel.close();
         }

         this.yawModel = null;
         this.pitchModel = null;
         this.K5331925a78be0bc8c6();
      } catch (Throwable $ex) {
         throw $ex;
      }
   }

   private void K5331925a78be0bc8c6() {
      for(Path tempModelFile : this.tempModelFiles) {
         try {
            Files.deleteIfExists(tempModelFile);
         } catch (IOException var4) {
         }
      }

      this.tempModelFiles.clear();
   }

   public Keb4a85ca52fb971c57 predict(class_1297 target, Keb4a85ca52fb971c57 current, Keb4a85ca52fb971c57 prev, class_5611 speed) {
      try {
         if (!this.isLoaded()) {
            return current;
         } else {
            class_243 playerPos = mc.field_1724.method_19538();
            class_243 targetPos = target.method_19538();
            class_243 playerVel = mc.field_1724.method_18798();
            class_243 targetVel = target.method_18798();
            Keb4a85ca52fb971c57 atTarget = this.Kc4929f6361dc1feea3(targetPos);
            float yawDiff = class_3532.method_15393(atTarget.getYaw());
            float pitchDiff = class_3532.method_15363(atTarget.getPitch(), -90.0F, 90.0F);
            float deltaX = (float)(target.method_23317() - target.field_6014);
            float deltaY = (float)(target.method_23318() - target.field_6036);
            float deltaZ = (float)(target.method_23321() - target.field_5969);
            K213c4611a801b39670 d = new K213c4611a801b39670(Math.abs(current.getYaw() - prev.getYaw()), Math.abs(current.getPitch() - prev.getPitch()), yawDiff, pitchDiff, (float)this.timer.getElapsedTime(), (float)mc.field_1724.method_19538().method_1022(targetPos), mc.field_1724.method_24828() ? 1 : 0, !mc.field_1724.method_6128() && !mc.field_1724.method_5869() ? 0 : 1, class_3532.method_15393(current.getYaw()), current.getPitch(), (float)playerPos.field_1352, (float)playerPos.field_1351, (float)playerPos.field_1350, (float)playerVel.field_1352, (float)playerVel.field_1351, (float)playerVel.field_1350, (float)targetPos.field_1352, (float)targetPos.field_1351, (float)targetPos.field_1350, (float)targetVel.field_1352, (float)targetVel.field_1351, (float)targetVel.field_1350, deltaX, deltaY, deltaZ);
            float[] features = new float[]{d.yawDelta(), d.pitchDelta(), d.targetYaw(), d.targetPitch(), d.sinceAttack(), d.distance(), (float)d.onGround(), (float)d.miniHitbox(), d.playerYaw(), d.playerPitch(), d.playerPosX(), d.playerPosY(), d.playerPosZ(), d.playerVelX(), d.playerVelY(), d.playerVelZ(), d.targetPosX(), d.targetPosY(), d.targetPosZ(), d.targetVelX(), d.targetVelY(), d.targetVelZ(), d.deltaX(), d.deltaY(), d.deltaZ()};
            String[] crendil = new String[]{String.valueOf(d.onGround()), String.valueOf(d.miniHitbox())};
            CatBoostPredictions yawPred = this.yawModel.predict(features, crendil);
            CatBoostPredictions pitchPred = this.pitchModel.predict(features, crendil);
            float predictedYaw = (float)yawPred.get(0, 0);
            float predictedPitch = (float)pitchPred.get(0, 0);
            float shortestYawPath = ((predictedYaw + yawDiff - this.interpolation.getYaw()) % 360.0F + 540.0F) % 360.0F - 180.0F;
            float targetYaw = this.interpolation.getYaw() + shortestYawPath;
            float targetPitch = predictedPitch - this.interpolation.getPitch();
            if (mc.field_1724.method_23318() > target.method_23318() + (double)target.method_17682()) {
               targetPitch = this.Kc4929f6361dc1feea3(target.method_19538().method_1031((double)0.0F, (double)target.method_17682(), (double)0.0F)).getPitch();
            }

            if (mc.field_1724.method_23318() + (double)1.0F < target.method_23318()) {
               targetPitch = this.Kc4929f6361dc1feea3(target.method_19538().method_1031((double)0.0F, (double)0.5F, (double)0.0F)).getPitch();
            }

            if (mc.field_1724.method_5681()) {
               targetPitch = this.Kc4929f6361dc1feea3(target.method_19538().method_1031((double)0.0F, (double)(target.method_17682() / 2.0F), (double)0.0F)).getPitch();
               speed = new class_5611((float)Ka0484a23398fb5f9ac.randomInRange(200, 350), (float)Ka0484a23398fb5f9ac.randomInRange(200, 350));
            }

            if (K131e64425767c5c203.hasCollisionWith(target) && (K61006a43f5c18a1cfa.stalin(target) || K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10382 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10382) || K131e64425767c5c203.hasCollisionWith(target, -0.7F)) {
               speed = new class_5611(speed.method_32118() * 1.6F, speed.method_32119() * 1.6F);
            }

            this.interpolation.easing(Kf3b62ecfba0c3544e6.LINEAR).animate(new Keb4a85ca52fb971c57(targetYaw, targetPitch), (int)speed.method_32118(), (int)speed.method_32119());
            float finalYaw = speed.method_32118() == 0.0F ? targetYaw : this.interpolation.getYaw();
            float finalPitch = speed.method_32119() == 0.0F ? targetPitch : this.interpolation.getPitch();
            return new Keb4a85ca52fb971c57(finalYaw, finalPitch);
         }
      } catch (Throwable $ex) {
         throw $ex;
      }
   }

   private Keb4a85ca52fb971c57 Kc4929f6361dc1feea3(class_243 pos) {
      return Kef2fce79615318605c.rotationAt(pos);
   }
}
