package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I0_i9.I11i1iIiI_11iI_i1___1liIIii;
import II1II1II1II1II1II1II1II1.I8_i7.i1iliI1II_1___I__111_i11i_l;
import II1II1II1II1II1II1II1II1.I9_i8.i1_lII1i1l_1_ii1_l_IiillllIllII;
import II1II1II1II1II1II1II1II1.l8_I7.lliI1II_i1_lillIl1Il1i;
import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.cosmetics.Ka5dfe6b89219e29d09;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import net.fabricmc.api.ClientModInitializer;
import ru.phantom.Phantom;

public final class LuxBridge implements ClientModInitializer {
   private static final List<LuxModuleAdapter> ADAPTERS = new ArrayList();
   private static volatile boolean installed;
   private static volatile boolean subscribed;
   private static final LuxBridge INSTANCE = new LuxBridge();
   private static LuxHudSelector HUD;
   private static Kab8a8c478f7d0077cd luxGui;

   public void onInitializeClient() {
      Thread var1 = new Thread(() -> {
         long var0 = System.currentTimeMillis() + 300000L;

         while(!subscribed && System.currentTimeMillis() < var0) {
            try {
               Phantom var2 = Phantom.getInstance();
               EventBus var3 = var2.getEventBus();
               if (var3 != null && var2.getModuleManager() != null && !K5c460ed4a42f0797d6.getInstance().getModules().isEmpty()) {
                  var3.register(INSTANCE);
                  subscribed = true;
                  break;
               }
            } catch (Throwable var5) {
            }

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var4) {
               return;
            }
         }

      }, "LuxBridge-wait");
      var1.setDaemon(true);
      var1.start();
   }

   @Subscribe
   public void onTick(lliI1II_i1_lillIl1Il1i var1) {
      if (!installed) {
         install();
      } else {
         MenuGuiScale.tick();

         for(LuxModuleAdapter var3 : ADAPTERS) {
            var3.sync();
         }

         if (HUD != null) {
            HUD.sync();
         }

         killLuxGuiBind();
      }
   }

   private static void killLuxGuiBind() {
      try {
         if (luxGui == null) {
            for(Kab8a8c478f7d0077cd var1 : K5c460ed4a42f0797d6.getInstance().getModules()) {
               if ("ClickGUI".equals(var1.getName())) {
                  luxGui = var1;
                  break;
               }
            }
         }

         if (luxGui != null && luxGui.hasBind()) {
            luxGui.setBind(-999);
         }
      } catch (Throwable var2) {
      }

   }

   private static synchronized void install() {
      if (!installed) {
         installed = true;
         killLuxGuiBind();
         installCosmetics();

         try {
            i1_lII1i1l_1_ii1_l_IiillllIllII var0 = Phantom.getInstance().getModuleManager();
            List var1 = var0.getModules();
            HashSet var2 = new HashSet();

            for(i1iliI1II_1___I__111_i11i_l var4 : var1) {
               if (var4.getName() != null) {
                  var2.add(var4.getName().toLowerCase(Locale.ROOT));
               }
            }

            int var13 = 0;
            int var14 = 0;

            for(Kab8a8c478f7d0077cd var6 : K5c460ed4a42f0797d6.getInstance().getModules()) {
               if (!"ClickGUI".equals(var6.getName())) {
                  if (!"Ambience".equals(var6.getName()) && !"AspectRatio-hidden-disabled".equals(var6.getName())) {
                     try {
                        boolean var7 = var2.contains(var6.getName().toLowerCase(Locale.ROOT));
                        LuxModuleAdapter var8 = new LuxModuleAdapter(var6, var7);
                        var1.add(var8);
                        ADAPTERS.add(var8);
                        ++var13;
                     } catch (Throwable var10) {
                        ++var14;
                        PrintStream var10000 = System.out;
                        String var10001 = var6.getName();
                        var10000.println("[LuxBridge] модуль не добавлен: " + var10001 + " : " + String.valueOf(var10));
                     }
                  } else {
                     try {
                        if (var6.isEnabled()) {
                           var6.setEnabled(false);
                        }
                     } catch (Throwable var11) {
                     }
                  }
               }
            }

            try {
               HUD = new LuxHudSelector();
               var1.add(HUD);
            } catch (Throwable var9) {
               System.out.println("[LuxBridge] HUD-селектор не добавлен: " + String.valueOf(var9));
            }

            System.out.println("[LuxBridge] модулей Lux добавлено в меню Phantom: " + var13 + ", ошибок: " + var14);
         } catch (Throwable var12) {
            System.out.println("[LuxBridge] ошибка установки: " + String.valueOf(var12));
            var12.printStackTrace();
         }

      }
   }

   private static void installCosmetics() {
      try {
         List var0 = K697051202eac7cfc74.getInstance().entries();
         if (var0.isEmpty()) {
            System.out.println("[LuxBridge] косметика Lux пуста (index.json не прочитан) - родные карточки Phantom оставлены");
            return;
         }

         List var1 = Phantom.getInstance().getCapesManager().getCapes();

         for(I11i1iIiI_11iI_i1___1liIIii var3 : new ArrayList(var1)) {
            try {
               if (var3.isEnable()) {
                  var3.setEnable(false);
               }
            } catch (Throwable var7) {
            }
         }

         var1.clear();
         int var9 = 0;

         for(Ka5dfe6b89219e29d09 var4 : var0) {
            try {
               var1.add(new LuxCosmeticAdapter(var4));
               ++var9;
            } catch (Throwable var6) {
               String var10001 = var4.name;
               System.out.println("[LuxBridge] косметика не добавлена: " + var10001 + " : " + String.valueOf(var6));
            }
         }

         System.out.println("[LuxBridge] косметики Lux добавлено в меню Phantom: " + var9);
      } catch (Throwable var8) {
         System.out.println("[LuxBridge] ошибка установки косметики: " + String.valueOf(var8));
         var8.printStackTrace();
      }

   }

   public static List<LuxModuleAdapter> adapters() {
      return ADAPTERS;
   }
}
