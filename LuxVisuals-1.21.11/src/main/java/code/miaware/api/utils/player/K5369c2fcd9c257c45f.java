package code.miaware.api.utils.player;

public final class K5369c2fcd9c257c45f {
   private K5369c2fcd9c257c45f() {
   }

   public static K8c202e08d32fc608f9 findBestInput(float correctedForward, float correctedSideways, Kaa319a6d1fab153fd5 penalty) {
      float bestForward = 0.0F;
      float bestStrafe = 0.0F;
      double bestScore = Double.MAX_VALUE;

      for(float forward = -1.0F; forward <= 1.0F; ++forward) {
         for(float strafe = -1.0F; strafe <= 1.0F; ++strafe) {
            if (forward != 0.0F || strafe != 0.0F) {
               double score = squaredDifference(correctedForward, correctedSideways, forward, strafe);
               if (penalty != null) {
                  score += penalty.apply(forward, strafe);
               }

               if (score < bestScore) {
                  bestScore = score;
                  bestForward = forward;
                  bestStrafe = strafe;
               }
            }
         }
      }

      return new K8c202e08d32fc608f9(bestForward, bestStrafe);
   }

   public static double squaredDifference(float desiredForward, float desiredSideways, float forward, float strafe) {
      double forwardDiff = (double)(desiredForward - forward);
      double strafeDiff = (double)(desiredSideways - strafe);
      return forwardDiff * forwardDiff + strafeDiff * strafeDiff;
   }
}
