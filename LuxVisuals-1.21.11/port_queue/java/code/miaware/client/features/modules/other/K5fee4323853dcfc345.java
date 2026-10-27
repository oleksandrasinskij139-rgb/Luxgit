package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K917aeb5cdb0f7aca26;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Generated;

@ModuleRegister(
   name = "Streamer",
   category = Kf76f042931183af5ba.OTHER,
   description = "Скрывает личную информацию для стримов"
)
public class K5fee4323853dcfc345 extends Kab8a8c478f7d0077cd {
   private static final K5fee4323853dcfc345 instance = new K5fee4323853dcfc345();
   private final K00fef91b3d4d1b3ecc hideName = (new K00fef91b3d4d1b3ecc("Name")).value(true);
   private final K00fef91b3d4d1b3ecc hideFriends = (new K00fef91b3d4d1b3ecc("Hide friends")).value(true);
   private final Ka4b85e821e3749c3d9 hide = new Ka4b85e821e3749c3d9("Hides");
   private final K917aeb5cdb0f7aca26 customName = (new K917aeb5cdb0f7aca26("Nickname")).value("Smertnix").placeholder("Enter nickname...").maxLength(16).setVisible(() -> this.hide.isEnabled("Name"));
   private final ConcurrentHashMap<String, Integer> friendCounter = new ConcurrentHashMap();
   private final AtomicInteger globalCounter = new AtomicInteger(1);

   public K5fee4323853dcfc345() {
      this.hideFriends.setVisible(() -> this.hide.isEnabled("Name"));
      this.hide.value(this.hideName, this.hideFriends);
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.hide, this.customName});
   }

   public String getProtectedName() {
      if (this.isEnabled() && this.hide.isEnabled("Name")) {
         return ((String)this.customName.getValue()).isBlank() ? "Smertnix" : (String)this.customName.getValue();
      } else {
         return mc.method_1548().method_1676();
      }
   }

   public String getProtectedFriendName(String name) {
      return this.isEnabled() && this.hide.isEnabled("Name") && this.hide.isEnabled("Hide friends") && K137d4e93c7270b0a04.getInstance().contains(name) ? this.generateProtectedFriendName(name) : name;
   }

   public String generateProtectedFriendName(String originalName) {
      int id = (Integer)this.friendCounter.computeIfAbsent(originalName.toLowerCase(), (key) -> this.globalCounter.getAndIncrement());
      return "Friend " + id;
   }

   public void onDisable() {
      this.friendCounter.clear();
      this.globalCounter.set(1);
   }

   public void onEvent() {
   }

   @Generated
   public static K5fee4323853dcfc345 getInstance() {
      return instance;
   }

   @Generated
   public Ka4b85e821e3749c3d9 getHide() {
      return this.hide;
   }
}
