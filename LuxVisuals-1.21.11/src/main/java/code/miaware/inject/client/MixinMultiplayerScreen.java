package code.miaware.inject.client;

import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.class_4267;
import net.minecraft.class_500;
import net.minecraft.class_641;
import net.minecraft.class_642;
import net.minecraft.class_642.class_8678;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_500.class})
public class MixinMultiplayerScreen {
   @Shadow
   private class_641 serverList;
   @Shadow
   private class_4267 serverListWidget;

   @Inject(
      method = {"init"},
      at = {@At("RETURN")}
   )
   private void onInit(CallbackInfo ci) {
      try {
         System.out.println("[MiaWare] MultiplayerScreen init called!");
         if (this.serverList != null) {
            System.out.println("[MiaWare] ServerList found! Current count: " + this.serverList.method_2984());
            boolean hasCloudWorld = false;

            for(int i = 0; i < this.serverList.method_2984(); ++i) {
               class_642 server = this.serverList.method_2982(i);
               if (server.field_3761 != null && server.field_3761.equals("ked.cloudworld.pw")) {
                  System.out.println("[MiaWare] Found existing CloudWorld server at index " + i);
                  hasCloudWorld = true;
                  break;
               }
            }

            if (!hasCloudWorld) {
               System.out.println("[MiaWare] No CloudWorld servers found. Adding 3 servers at the TOP...");
               class_642 server1 = new class_642("ЛУЧШИЙ СЕРВЕР BY LUX VISUALS", "ked.cloudworld.pw", class_8678.field_45611);
               class_642 server2 = new class_642("ЛУЧШИЙ СЕРВЕР BY LUX VISUALS", "ked.cloudworld.pw", class_8678.field_45611);
               class_642 server3 = new class_642("ЛУЧШИЙ СЕРВЕР BY LUX VISUALS", "ked.cloudworld.pw", class_8678.field_45611);

               try {
                  Field serversField = class_641.class.getDeclaredField("servers");
                  serversField.setAccessible(true);
                  List<class_642> internalList = (List)serversField.get(this.serverList);
                  System.out.println("[MiaWare] Got internal list via reflection, size: " + internalList.size());
                  internalList.add(0, server3);
                  internalList.add(0, server2);
                  internalList.add(0, server1);
                  System.out.println("[MiaWare] Servers inserted at index 0! New count: " + internalList.size());
               } catch (NoSuchFieldException var8) {
                  System.err.println("[MiaWare] Field 'servers' not found, trying alternative approach...");
                  this.serverList.method_2988(server1, false);
                  this.serverList.method_2988(server2, false);
                  this.serverList.method_2988(server3, false);
                  System.out.println("[MiaWare] Servers added (fallback method)! New count: " + this.serverList.method_2984());
               }

               System.out.println("[MiaWare] Saving server list...");
               this.serverList.method_2987();
               System.out.println("[MiaWare] Server list saved successfully!");
               if (this.serverListWidget != null) {
                  this.serverListWidget.method_20125(this.serverList);
                  System.out.println("[MiaWare] Server list widget updated!");
               }
            } else {
               System.out.println("[MiaWare] CloudWorld servers already exist. Skipping.");
            }
         } else {
            System.err.println("[MiaWare] ServerList is NULL!");
         }
      } catch (Exception e) {
         System.err.println("[MiaWare] ERROR in MixinMultiplayerScreen:");
         e.printStackTrace();
      }

   }
}
