package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.l8_I7.lI1__l1_l___II_1lIIl1Ii;
import net.minecraft.class_10055;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_10055.class})
public class PlayerEntityRenderStateMixin implements lI1__l1_l___II_1lIIl1Ii {
   @Unique
   private boolean phantom$localPlayer;

   public boolean phantom$isLocalPlayer() {
      return this.phantom$localPlayer;
   }

   public void phantom$setLocalPlayer(boolean localPlayer) {
      this.phantom$localPlayer = localPlayer;
   }
}
