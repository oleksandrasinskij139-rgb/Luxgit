package code.miaware;

import code.miaware.api.command.Ke66e08ec83c92514e4;
import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.system.K7a815b1a7330bf39b3;
import code.miaware.api.system.backend.K04bfb908d9b141e27c;
import code.miaware.api.system.client.K4d1f360e533c3997d9;
import code.miaware.api.system.configs.Kae5f6f8dfd322901c3;
import code.miaware.api.system.configs.Kc8165f59ce078825eb;
import code.miaware.api.system.configs.Kd4542f1791364048a9;
import code.miaware.api.system.draggable.Kd8969ae8f12d70edf3;
import code.miaware.api.system.files.Kad89a1433174c7a563;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.api.utils.render.Kecb75e73598eb5799c;
import code.miaware.api.utils.render.shader.K745964d54ca1b172b8;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.client.features.misc.Kdd27279f42e50d9574;
import code.miaware.client.features.modules.other.K2f63e835e247f11580;
import code.miaware.client.network.badge.Ka424c22578b339e00d;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import code.miaware.client.services.Kf479eb8a461436e337;
import code.miaware.client.sound.K750b17c76d0e455cf4;
import code.miaware.client.ui.splash.K0c57d339ab743d4837;
import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import code.miaware.client.ui.widget.K5e6ae649a852530a89;
import code.miaware.client.util.render.K2516fc881f143ca1ea;
import code.miaware.client.util.render.K3dbb0e5dfbba4d83dc;
import code.miaware.client.util.render.Kefb5586baca5cdbb77;
import lombok.Generated;
import net.fabricmc.api.ClientModInitializer;

public class MiaWare implements ClientModInitializer {
   public static MiaWare INSTANCE;
   private static boolean initializing = true;
   public static K750b17c76d0e455cf4 soundRTX;
   public K5c460ed4a42f0797d6 moduleManager;

   public void onInitializeClient() {
      INSTANCE = this;
      K7d074a66e20f0c1531.load();
      soundRTX = K750b17c76d0e455cf4.makeDistorterMixer();
      soundRTX.init();
      Kdd27279f42e50d9574.installAsync();
      initializing = false;
      this.loadManagers();
      this.loadServices();
      this.loadFiles();
   }

   public void postLoad() {
      K4600bef438dafc1a9c.mc.method_1507(new K0c57d339ab743d4837());
      Kecb75e73598eb5799c.load();
      Kefb5586baca5cdbb77.init();
      K745964d54ca1b172b8.loadAndSetupPrograms();
      K2516fc881f143ca1ea.init();
      K3dbb0e5dfbba4d83dc.init();
      K04bfb908d9b141e27c.getInstance().loadRole();
   }

   private void loadFiles() {
      Ka35e7d6b31db9984f1.getInstance().load();
      boolean hasDiscordModuleEntry = Kd4542f1791364048a9.getInstance().hasModuleEntry("autoConfig", K2f63e835e247f11580.getInstance().getName());
      Kd4542f1791364048a9.getInstance().load("autoConfig");
      if (!hasDiscordModuleEntry) {
         K2f63e835e247f11580.getInstance().setEnabled(true, true);
      }

      Kd8969ae8f12d70edf3.getInstance().load();
      Kad89a1433174c7a563.getInstance().load();
      Kae5f6f8dfd322901c3.getInstance().load();
   }

   private void loadManagers() {
      K5e6ae649a852530a89.getInstance().load();
      K960322f580344d3bad.getInstance().load();
      this.moduleManager = K5c460ed4a42f0797d6.getInstance();
      this.moduleManager.load();
      Ke66e08ec83c92514e4.getInstance().load();
      K6ea7546faa48739c0d.getInstance().load();
      K4d1f360e533c3997d9.getInstance().init();
   }

   private void loadServices() {
      Ka424c22578b339e00d.init();
      Kf479eb8a461436e337.getInstance().load();
      Ke4708cf52c3b89f5ae.getInstance().load();
      Kc8165f59ce078825eb.getInstance().load();
   }

   public void onClose() {
      Kd4542f1791364048a9.getInstance().save("autoConfig");
      Kad89a1433174c7a563.getInstance().save();
      K6ea7546faa48739c0d.getInstance().save(true);
      Kd8969ae8f12d70edf3.getInstance().save();
      Kae5f6f8dfd322901c3.getInstance().save();
      Ka35e7d6b31db9984f1.getInstance().save();
      if (soundRTX != null) {
         soundRTX.unload();
      }

      try {
         K7a815b1a7330bf39b3.stopRPC();
      } catch (Throwable t) {
         System.err.printf("Discord RPC shutdown failed: %s%n", t.getMessage());
      }

   }

   @Generated
   public static MiaWare getINSTANCE() {
      return INSTANCE;
   }

   @Generated
   public static boolean isInitializing() {
      return initializing;
   }

   @Generated
   public static K750b17c76d0e455cf4 getSoundRTX() {
      return soundRTX;
   }

   @Generated
   public K5c460ed4a42f0797d6 getModuleManager() {
      return this.moduleManager;
   }
}
