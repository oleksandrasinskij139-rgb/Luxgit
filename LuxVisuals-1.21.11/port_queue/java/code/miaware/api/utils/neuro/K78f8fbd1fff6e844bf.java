package code.miaware.api.utils.neuro;

import code.miaware.api.event.events.client.Kb251204d734ffa1d20;
import code.miaware.api.event.events.player.world.K8d0b25c690b59e234d;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2767;
import net.minecraft.class_3417;
import net.minecraft.class_3532;

public class K78f8fbd1fff6e844bf implements K4600bef438dafc1a9c {
   private static final K78f8fbd1fff6e844bf instance = new K78f8fbd1fff6e844bf();
   private static final String DATASET_HEADER = "yaw_delta,pitch_delta,target_yaw,target_pitch,since_attack,distance,on_ground,mini_hitbox,player_yaw,player_pitch,player_pos_x,player_pos_y,player_pos_z,player_vel_x,player_vel_y,player_vel_z,target_pos_x,target_pos_y,target_pos_z,target_vel_x,target_vel_y,target_vel_z,delta_x,delta_y,delta_z";
   private static final Path DATASET_PATH;
   private int dataSize = 0;
   private final List<K213c4611a801b39670> dataBuffer = Collections.synchronizedList(new ObjectArrayList(20));
   private class_1309 target;
   private final Kf39dc27225f4d9fe6e timer = new Kf39dc27225f4d9fe6e();
   private final Kf39dc27225f4d9fe6e attackTimer = new Kf39dc27225f4d9fe6e();
   private final AtomicBoolean flushQueued = new AtomicBoolean();
   private final ExecutorService flushExecutor = Executors.newSingleThreadExecutor((runnable) -> {
      Thread thread = new Thread(runnable, "miaware-dataset-flush");
      thread.setDaemon(true);
      return thread;
   });

   public void startCollecting() {
      synchronized(this.dataBuffer) {
         this.dataBuffer.clear();
      }

      this.dataSize = 0;
      this.print("Начата сборка датасета");
   }

   public void stopCollecting() {
      this.dataSize = 0;
      this.saveDataset();
      synchronized(this.dataBuffer) {
         this.dataBuffer.clear();
      }

      this.print("Прекращена сборка датасета");
   }

   public void onAttack(K8d0b25c690b59e234d event) {
      class_1297 var3 = event.entity();
      if (var3 instanceof class_1309 livingEntity) {
         this.target = livingEntity;
         this.timer.reset();
         this.attackTimer.reset();
      }

   }

   public void onPacket(Kb251204d734ffa1d20 event) {
      if (event.isReceive()) {
         class_2596 var3 = event.packet();
         if (var3 instanceof class_2767) {
            class_2767 play = (class_2767)var3;
            if ((play.method_11894().comp_349() == class_3417.field_15016 || play.method_11894().comp_349() == class_3417.field_14706) && !this.attackTimer.finished(200L) && this.flushQueued.compareAndSet(false, true)) {
               this.flushExecutor.execute(() -> {
                  try {
                     this.saveDataset();
                  } finally {
                     this.flushQueued.set(false);
                  }

               });
            }
         }
      }

   }

   public void onUpdate() {
      this.parse(mc.field_1724);
   }

   public synchronized void saveDataset() {
      List<K213c4611a801b39670> snapshot;
      synchronized(this.dataBuffer) {
         if (this.dataBuffer.isEmpty()) {
            return;
         }

         snapshot = new ArrayList(this.dataBuffer);
         this.dataBuffer.clear();
      }

      try {
         Files.createDirectories(DATASET_PATH.getParent());
      } catch (IOException e) {
         e.printStackTrace();
         return;
      }

      boolean writeHeader = !Files.exists(DATASET_PATH, new LinkOption[0]) || DATASET_PATH.toFile().length() == 0L;

      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(DATASET_PATH.toFile(), true));

         try {
            if (writeHeader) {
               writer.write("yaw_delta,pitch_delta,target_yaw,target_pitch,since_attack,distance,on_ground,mini_hitbox,player_yaw,player_pitch,player_pos_x,player_pos_y,player_pos_z,player_vel_x,player_vel_y,player_vel_z,target_pos_x,target_pos_y,target_pos_z,target_vel_x,target_vel_y,target_vel_z,delta_x,delta_y,delta_z");
               writer.newLine();
            }

            for(K213c4611a801b39670 d : snapshot) {
               writer.write(String.format(Locale.US, "%f,%f,%f,%f,%f,%f,%d,%d,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f%n", d.yawDelta(), d.pitchDelta(), d.targetYaw(), d.targetPitch(), d.sinceAttack(), d.distance(), d.onGround(), d.miniHitbox(), d.playerYaw(), d.playerPitch(), d.playerPosX(), d.playerPosY(), d.playerPosZ(), d.playerVelX(), d.playerVelY(), d.playerVelZ(), d.targetPosX(), d.targetPosY(), d.targetPosZ(), d.targetVelX(), d.targetVelY(), d.targetVelZ(), d.deltaX(), d.deltaY(), d.deltaZ()));
            }

            this.dataSize += snapshot.size();
         } catch (Throwable var8) {
            try {
               writer.close();
            } catch (Throwable var6) {
               var8.addSuppressed(var6);
            }

            throw var8;
         }

         writer.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void parse(class_1309 player) {
      if (player != null && mc.field_1687 != null) {
         if (!this.timer.finished(2000L) && !player.method_29504() && mc.field_1687.method_18456().contains(player)) {
            if (this.target != null && !this.target.method_29504()) {
               Keb4a85ca52fb971c57 atTarget = Kef2fce79615318605c.rotationAt(this.target.method_19538());
               float currentYaw = player.method_36454();
               float currentPitch = player.method_36455();
               float prevYaw = player.field_5982;
               float prevPitch = player.field_6004;
               K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
               if (rotationManager.getCurrentRotationPlan() != null) {
                  Keb4a85ca52fb971c57 current = rotationManager.getRotation();
                  Keb4a85ca52fb971c57 prev = rotationManager.getPreviousRotation();
                  currentYaw = current.getYaw();
                  currentPitch = current.getPitch();
                  prevYaw = prev.getYaw();
                  prevPitch = prev.getPitch();
               }

               float yawDiff = class_3532.method_15393(atTarget.getYaw());
               float pitchDiff = class_3532.method_15393(atTarget.getPitch());
               class_243 playerVelocity = player.method_18798();
               class_243 targetVelocity = this.target.method_18798();
               class_243 playerPos = player.method_19538();
               class_243 targetPos = this.target.method_19538();
               float deltaX = (float)(this.target.method_23317() - this.target.field_6014);
               float deltaY = (float)(this.target.method_23318() - this.target.field_6036);
               float deltaZ = (float)(this.target.method_23321() - this.target.field_5969);
               this.dataBuffer.add(new K213c4611a801b39670(Math.abs(class_3532.method_15393(currentYaw - prevYaw)), Math.abs(class_3532.method_15393(currentPitch - prevPitch)), yawDiff, pitchDiff, (float)this.timer.getElapsedTime(), (float)playerPos.method_1022(targetPos), player.method_24828() ? 1 : 0, !player.method_6128() && !player.method_5681() ? 0 : 1, currentYaw, currentPitch, (float)playerPos.field_1352, (float)playerPos.field_1351, (float)playerPos.field_1350, (float)playerVelocity.field_1352, (float)playerVelocity.field_1351, (float)playerVelocity.field_1350, (float)targetPos.field_1352, (float)targetPos.field_1351, (float)targetPos.field_1350, (float)targetVelocity.field_1352, (float)targetVelocity.field_1351, (float)targetVelocity.field_1350, deltaX, deltaY, deltaZ));
            }
         }
      }
   }

   public float normalizeTo360(float angle) {
      angle %= 360.0F;
      if (angle < 0.0F) {
         angle += 360.0F;
      }

      return angle;
   }

   public float normalizeTo180(float angle) {
      angle = this.normalizeTo360(angle);
      if (angle > 180.0F) {
         angle -= 360.0F;
      }

      return angle;
   }

   public float getAngleDifference(float angle1, float angle2) {
      float normalizedAngle1 = this.normalizeTo360(angle1);
      float normalizedAngle2 = this.normalizeTo360(angle2);
      return this.normalizeTo180(normalizedAngle1 - normalizedAngle2);
   }

   @Generated
   public static K78f8fbd1fff6e844bf getInstance() {
      return instance;
   }

   @Generated
   public int getDataSize() {
      return this.dataSize;
   }

   @Generated
   public List<K213c4611a801b39670> getDataBuffer() {
      return this.dataBuffer;
   }

   static {
      DATASET_PATH = Path.of(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, "rotation_data.csv");
   }
}
