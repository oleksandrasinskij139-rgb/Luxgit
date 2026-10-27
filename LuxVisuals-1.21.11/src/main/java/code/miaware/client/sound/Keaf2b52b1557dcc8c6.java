package code.miaware.client.sound;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_746;

@Environment(EnvType.CLIENT)
public class Keaf2b52b1557dcc8c6 {
   private class_746 player;
   private boolean rtxDebug = false;
   private boolean tooPerfomance = false;
   private final List<class_243> listOfTestVecs = new ArrayList();

   public static Keaf2b52b1557dcc8c6 build() {
      return new Keaf2b52b1557dcc8c6();
   }

   public float[] getGainArgsFromWorld() {
      if (this.player != null && class_310.method_1551().field_1687 != null) {
         class_1937 world = class_310.method_1551().field_1687;
         class_2338 playerPos = this.player.method_24515();
         int scanRadius = this.tooPerfomance ? 3 : 5;
         int scanHeight = this.tooPerfomance ? 2 : 3;
         int solidBlocks = 0;
         int totalBlocks = 0;
         int airBlocks = 0;

         for(int x = -scanRadius; x <= scanRadius; ++x) {
            for(int y = -scanHeight; y <= scanHeight; ++y) {
               for(int z = -scanRadius; z <= scanRadius; ++z) {
                  class_2338 checkPos = playerPos.method_10069(x, y, z);
                  class_2680 state = world.method_8320(checkPos);
                  ++totalBlocks;
                  if (!state.method_26215()) {
                     ++solidBlocks;
                     if (this.rtxDebug && this.listOfTestVecs.size() < 100) {
                        this.listOfTestVecs.add(new class_243((double)checkPos.method_10263() + (double)0.5F, (double)checkPos.method_10264() + (double)0.5F, (double)checkPos.method_10260() + (double)0.5F));
                     }
                  } else {
                     ++airBlocks;
                  }
               }
            }
         }

         float enclosureRatio = totalBlocks > 0 ? (float)solidBlocks / (float)totalBlocks : 0.0F;
         float echoLevel = class_3532.method_15363(enclosureRatio * 2.0F, 0.0F, 1.5F);
         float reflectionLevel = class_3532.method_15363(enclosureRatio * 1.5F, 0.0F, 1.0F);
         float lowPassGain = class_3532.method_15363(1.0F - enclosureRatio * 0.3F, 0.5F, 1.0F);
         float lowPassGainHF = class_3532.method_15363(1.0F - enclosureRatio * 0.5F, 0.3F, 1.0F);
         if (this.player.method_5869()) {
            echoLevel *= 0.3F;
            reflectionLevel *= 0.5F;
            lowPassGain *= 0.6F;
            lowPassGainHF *= 0.4F;
         }

         if (this.player.method_5771()) {
            echoLevel *= 0.2F;
            reflectionLevel *= 0.3F;
            lowPassGain *= 0.5F;
            lowPassGainHF *= 0.3F;
         }

         return new float[]{echoLevel, reflectionLevel, lowPassGain, lowPassGainHF};
      } else {
         return new float[]{0.0F, 0.0F, 1.0F, 1.0F};
      }
   }

   public class_746 getPlayer() {
      return this.player;
   }

   public void setPlayer(class_746 player) {
      this.player = player;
   }

   public boolean isRtxDebug() {
      return this.rtxDebug;
   }

   public void setRtxDebug(boolean rtxDebug) {
      this.rtxDebug = rtxDebug;
   }

   public boolean isTooPerfomance() {
      return this.tooPerfomance;
   }

   public void setTooPerfomance(boolean tooPerfomance) {
      this.tooPerfomance = tooPerfomance;
   }

   public List<class_243> getListOfTestVecs() {
      return this.listOfTestVecs;
   }
}
