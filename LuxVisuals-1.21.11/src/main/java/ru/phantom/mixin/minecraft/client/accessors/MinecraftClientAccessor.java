package ru.phantom.mixin.minecraft.client.accessors;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_542;
import net.minecraft.class_5520;
import net.minecraft.class_6628;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_310.class})
public interface MinecraftClientAccessor {
   @Mutable
   @Accessor("field_1726")
   void setSession(class_320 var1);

   @Mutable
   @Invoker("method_31382")
   UserApiService hachclient$createUserApiService(YggdrasilAuthenticationService var1, class_542 var2);

   @Mutable
   @Accessor("field_39420")
   YggdrasilAuthenticationService getAuthenticationService();

   @Mutable
   @Accessor("field_26902")
   void setUserApiService(UserApiService var1);

   @Mutable
   @Accessor("field_26842")
   void setSocialInteractionsManager(class_5520 var1);

   @Mutable
   @Accessor("field_41331")
   void setTelemetryManager(class_6628 var1);

   @Mutable
   @Accessor("field_39068")
   void setProfileKeys(class_7853 var1);

   @Mutable
   @Accessor("field_39492")
   void setAbuseReport(class_7574 var1);

   @Mutable
   @Accessor("field_47680")
   void setUserProperties(CompletableFuture<UserApiService.UserProperties> var1);
}
