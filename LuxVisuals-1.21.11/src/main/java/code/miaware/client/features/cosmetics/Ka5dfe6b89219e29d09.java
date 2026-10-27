package code.miaware.client.features.cosmetics;

import net.minecraft.class_2960;

public final class Ka5dfe6b89219e29d09 {
   public final int id;
   public final String name;
   public final K9a0eb2753a3143de82 type;
   public final int pos;
   public final float x;
   public final float y;
   public final float z;
   public final float yaw;
   public final float pitch;
   public final float roll;
   public final float scale;
   public final int texFrames;
   public final int texFrameTime;
   public final float texFrameShare;
   public final int capeFrames;
   public final int capeFrameW;
   public final int capeFrameH;
   public final int capeFrameTime;
   public final class_2960 texture;
   public final class_2960 preview;

   Ka5dfe6b89219e29d09(int id, String name, K9a0eb2753a3143de82 type, int pos, float x, float y, float z, float yaw, float pitch, float roll, float scale, int texFrames, int texFrameTime, float texFrameShare, int capeFrames, int capeFrameW, int capeFrameH, int capeFrameTime) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.pos = pos;
      this.x = x;
      this.y = y;
      this.z = z;
      this.yaw = yaw;
      this.pitch = pitch;
      this.roll = roll;
      this.scale = scale;
      this.texFrames = texFrames;
      this.texFrameTime = texFrameTime;
      this.texFrameShare = texFrameShare;
      this.capeFrames = capeFrames;
      this.capeFrameW = capeFrameW;
      this.capeFrameH = capeFrameH;
      this.capeFrameTime = capeFrameTime;
      this.texture = class_2960.method_60655("luxvisuals", "cosmetics/" + id + "/texture.png");
      this.preview = class_2960.method_60655("luxvisuals", "cosmetics/" + id + "/preview.png");
   }

   public boolean isCape() {
      return this.type == K9a0eb2753a3143de82.CAPE;
   }
}
