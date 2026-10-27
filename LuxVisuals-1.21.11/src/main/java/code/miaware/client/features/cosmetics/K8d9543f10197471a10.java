package code.miaware.client.features.cosmetics;

import java.util.ArrayList;
import java.util.List;

public final class K8d9543f10197471a10 {
   public final String name;
   final float pivotX;
   final float pivotY;
   final float pivotZ;
   final float baseRotX;
   final float baseRotY;
   final float baseRotZ;
   final List<Ka06b3f80b800a13ac7> cubes = new ArrayList();
   final List<K8d9543f10197471a10> children = new ArrayList();
   float animRotX;
   float animRotY;
   float animRotZ;
   float animPosX;
   float animPosY;
   float animPosZ;
   float animScaleX = 1.0F;
   float animScaleY = 1.0F;
   float animScaleZ = 1.0F;

   K8d9543f10197471a10(String name, float pivotX, float pivotY, float pivotZ, float rotX, float rotY, float rotZ) {
      this.name = name;
      this.pivotX = pivotX;
      this.pivotY = pivotY;
      this.pivotZ = pivotZ;
      this.baseRotX = rotX;
      this.baseRotY = rotY;
      this.baseRotZ = rotZ;
   }

   void resetAnimation() {
      this.animRotX = this.animRotY = this.animRotZ = 0.0F;
      this.animPosX = this.animPosY = this.animPosZ = 0.0F;
      this.animScaleX = this.animScaleY = this.animScaleZ = 1.0F;
   }
}
