package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I2_i1.IIIII_1lIii1iill1_III1iIIiIil_i;
import II1II1II1II1II1II1II1II1.I5_i4.iI1iI_ll11l11liiIli11ii_i111l1;
import II1II1II1II1II1II1II1II1.I7_i6.I_il_ll1ilii__llIl_Il1i_ilI;
import II1II1II1II1II1II1II1II1.I8_i7.i1iliI1II_1___I__111_i11i_l;
import II1II1II1II1II1II1II1II1.l2_I1.I_llII1lII_1Ii_li1I1i_i_I_1I;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import ru.phantom.Phantom;

public final class LuxHudSelector extends i1iliI1II_1___I__111_i11i_l {
   public static final String[] STYLES = new String[]{"1 (Phantom)", "2 (Lux)", "3 (скоро)"};
   private static final Path MARK = Paths.get("config", "luxvisuals", "hud_v4.done");
   private static final Path MODE = Paths.get("config", "luxvisuals", "hud_mode.txt");
   private final IIIII_1lIii1iill1_III1iIIiIil_i modeSetting;
   private boolean on = true;
   private boolean firstRun = true;
   private boolean ready = false;
   private int lastMode = -1;
   private boolean lastOn = false;
   private boolean applied = false;

   public LuxHudSelector() {
      this.setName("HUD");
      this.setDescription("Выбор интерфейса: 1 - Phantom, 2 - Lux, 3 - скоро");
      this.setCategory(I_llII1lII_1Ii_li1I1i_i_I_1I.HUD);
      this.modeSetting = new IIIII_1lIii1iill1_III1iIIiIil_i("Интерфейс", STYLES);
      this.modeSetting.set(STYLES[1]);
      ArrayList var1 = new ArrayList();
      var1.add(this.modeSetting);

      try {
         i1iliI1II_1___I__111_i11i_l var2 = this.phantomHud();
         if (var2 != null) {
            this.chain(var1, var2.getSettings(), 0);
         }
      } catch (Throwable var4) {
         Patch.err(var4);
      }

      try {
         LuxModuleAdapter var5 = this.luxInterface();
         if (var5 != null) {
            this.chain(var1, var5.getSettings(), 1);
         }
      } catch (Throwable var3) {
         Patch.err(var3);
      }

      this.setup((I_il_ll1ilii__llIl_Il1i_ilI[])var1.toArray(new I_il_ll1ilii__llIl_Il1i_ilI[0]));
   }

   private void chain(List<I_il_ll1ilii__llIl_Il1i_ilI> var1, List<I_il_ll1ilii__llIl_Il1i_ilI> var2, int var3) {
      if (var2 != null) {
         for(I_il_ll1ilii__llIl_Il1i_ilI var5 : new ArrayList(var2)) {
            Supplier var6 = var5.getVisible();
            var5.setVisible(() -> {
               if (this.mode() != var3) {
                  return Boolean.FALSE;
               } else if (var6 == null) {
                  return Boolean.TRUE;
               } else {
                  Boolean var3x = (Boolean)var6.get();
                  return var3x == null || var3x;
               }
            });
            var1.add(var5);
         }

      }
   }

   private int mode() {
      String var1 = this.modeSetting.get();

      for(int var2 = 0; var2 < STYLES.length; ++var2) {
         if (STYLES[var2].equals(var1)) {
            return var2;
         }
      }

      return 1;
   }

   private i1iliI1II_1___I__111_i11i_l phantomHud() {
      for(i1iliI1II_1___I__111_i11i_l var2 : new ArrayList(Phantom.getInstance().getModuleManager().getModules())) {
         if (var2 instanceof iI1iI_ll11l11liiIli11ii_i111l1) {
            return var2;
         }
      }

      return null;
   }

   private LuxModuleAdapter luxInterface() {
      for(LuxModuleAdapter var2 : LuxBridge.adapters()) {
         if ("Interface".equals(var2.getLux().getName())) {
            return var2;
         }
      }

      return null;
   }

   private void loadMode() {
      try {
         if (Files.exists(MODE, new LinkOption[0])) {
            String var1 = (new String(Files.readAllBytes(MODE), StandardCharsets.UTF_8)).trim();
            int var2 = Integer.parseInt(var1);
            if (var2 >= 0 && var2 < STYLES.length) {
               this.modeSetting.set(STYLES[var2]);
            }
         }
      } catch (Throwable var3) {
         Patch.err(var3);
      }

   }

   private void saveMode(int var1) {
      try {
         Files.createDirectories(MODE.getParent());
         Files.write(MODE, String.valueOf(var1).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
      } catch (Throwable var3) {
         Patch.err(var3);
      }

   }

   public void sync() {
      try {
         i1iliI1II_1___I__111_i11i_l var1 = this.phantomHud();
         LuxModuleAdapter var2 = this.luxInterface();
         if (this.firstRun) {
            this.firstRun = false;
            if (!Files.exists(MARK, new LinkOption[0])) {
               boolean var3 = var1 != null && var1.isEnable();
               boolean var4 = var2 != null && var2.isEnable();
               this.modeSetting.set(STYLES[var3 && !var4 ? 0 : 1]);
               this.on = true;
               this.saveMode(this.mode());

               try {
                  Files.createDirectories(MARK.getParent());
                  Files.write(MARK, "1".getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
               } catch (Throwable var6) {
                  Patch.err(var6);
               }
            } else {
               this.loadMode();
            }

            this.ready = true;
         }

         if (!this.ready) {
            return;
         }

         if (var1 != null) {
            var1.setHidden(true);
         }

         if (var2 != null) {
            var2.setHidden(true);
         }

         int var8 = this.mode();
         if (var8 != this.lastMode) {
            if (this.lastMode != -1) {
               this.saveMode(var8);
            }

            this.lastMode = var8;
            this.applied = false;
         }

         if (this.on != this.lastOn) {
            this.lastOn = this.on;
            this.applied = false;
         }

         boolean var9 = this.on && var8 == 0;
         boolean var5 = this.on && var8 == 1;
         if (var1 != null && var1.isEnable() != var9) {
            var1.setEnable(var9);
         }

         if (var2 != null && var2.isEnable() != var5) {
            var2.setEnable(var5);
         }

         this.applied = true;
      } catch (Throwable var7) {
         Patch.err(var7);
      }

   }

   public boolean isEnable() {
      return this.on;
   }

   public boolean isUserEnabled() {
      return this.on;
   }

   public void setEnable(boolean var1) {
      this.on = var1;
   }

   public void toggle() {
      this.on = !this.on;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public boolean isAvailable() {
      return true;
   }

   public boolean isServerSuspended() {
      return false;
   }

   public void suspendByServer() {
   }

   public void resumeByServer() {
   }

   public void handleModuleState(i1iliI1II_1___I__111_i11i_l var1, int var2) {
   }

   public int getKey() {
      return -1;
   }

   public void setKey(int var1) {
   }
}
