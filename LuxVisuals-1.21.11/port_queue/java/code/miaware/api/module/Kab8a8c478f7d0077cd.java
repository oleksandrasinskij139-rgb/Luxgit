package code.miaware.api.module;

import code.miaware.MiaWare;
import code.miaware.api.system.backend.K5d3444fb5779729097;
import code.miaware.api.system.configs.Kd4542f1791364048a9;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.server.Kbe30937e40237cb7db;
import code.miaware.client.features.modules.other.Kf7d850fe5c3821209f;
import code.miaware.client.features.modules.render.K30b126617e809f138b;
import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import lombok.Generated;

public abstract class Kab8a8c478f7d0077cd extends K5d3444fb5779729097 implements K4600bef438dafc1a9c {
   private final String name;
   private final Kf76f042931183af5ba category;
   private final String description;
   private int bind;
   private boolean enabled;

   public Kab8a8c478f7d0077cd() {
      ModuleRegister data = (ModuleRegister)this.getClass().getAnnotation(ModuleRegister.class);
      if (data == null) {
         try {
            throw new Exception("No data for " + this.getClass().getName());
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } else {
         this.name = data.name();
         this.category = data.category();
         this.description = data.description();
         this.bind = data.bind();
      }
   }

   public boolean hasBind() {
      return this.bind != -999 && this.bind != -1;
   }

   public String getDisplayName() {
      return Ka35e7d6b31db9984f1.getInstance().translateFeature(this.name);
   }

   public void toggle() {
      this.setEnabled(!this.enabled, false);
   }

   public void setEnabled(boolean newState) {
      this.setEnabled(newState, false);
   }

   public void setEnabled(boolean newState, boolean config) {
      if (this.enabled != newState) {
         if (newState && Kbe30937e40237cb7db.isModuleBlocked(this)) {
            if (!config) {
               String serverName = Kbe30937e40237cb7db.getCurrentServer().name();
               Kb7ca6c2b4209b8a116.notify("Модуль " + this.name + " заблокирован на сервере " + serverName, Ka58c9e2c4d8aa9af2e.ERROR);
            }

         } else {
            this.enabled = newState;
            if (this.enabled) {
               this.onEnable();
               this.onEvent();
            } else {
               this.onDisable();
               this.removeAllEvents();
            }

            if (!config && !MiaWare.isInitializing()) {
               System.out.println("[AutoSave] Сохраняем конфиг после изменения модуля: " + this.name + " -> " + this.enabled);
               Kd4542f1791364048a9.getInstance().save("autoConfig");
            }

            if (!config && !(this instanceof K30b126617e809f138b)) {
               Kf7d850fe5c3821209f.playToggle(newState);
               if (Kb7ca6c2b4209b8a116.isModuleNotificationsEnabled()) {
                  Kb7ca6c2b4209b8a116.notifyModule(this.name, this.enabled);
               }

            }
         }
      }
   }

   public abstract void onEvent();

   public void onEnable() {
   }

   public void onDisable() {
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public Kf76f042931183af5ba getCategory() {
      return this.category;
   }

   @Generated
   public String getDescription() {
      return this.description;
   }

   @Generated
   public int getBind() {
      return this.bind;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public void setBind(int bind) {
      this.bind = bind;
   }
}
