package code.miaware.api.system.backend;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;

public class K5d3444fb5779729097 implements K4600bef438dafc1a9c {
   private final List<Kdced9e23f1e229ba9d<?>> settings = new ArrayList();
   private final List<K8d7165024ff567f5e7> eventListeners = new ArrayList();

   public void addSettings(Kdced9e23f1e229ba9d<?>... settings) {
      this.settings.addAll(Arrays.asList(settings));
   }

   public void addSettings(List<Kdced9e23f1e229ba9d<?>> settings) {
      this.settings.addAll(settings);
   }

   public void addEvents(K8d7165024ff567f5e7... eventListeners) {
      this.eventListeners.addAll(Arrays.asList(eventListeners));
   }

   public void addEvents(List<K8d7165024ff567f5e7> eventListeners) {
      this.eventListeners.addAll(eventListeners);
   }

   public void removeAllEvents() {
      (new ArrayList(this.eventListeners)).forEach(K8d7165024ff567f5e7::unsubscribe);
      this.eventListeners.clear();
   }

   public void removeEvents(K8d7165024ff567f5e7... eventListeners) {
      Arrays.asList(eventListeners).forEach(K8d7165024ff567f5e7::unsubscribe);
      this.eventListeners.removeAll(Arrays.asList(eventListeners));
   }

   public void removeEvents(List<K8d7165024ff567f5e7> eventListeners) {
      eventListeners.forEach(K8d7165024ff567f5e7::unsubscribe);
      this.eventListeners.removeAll(eventListeners);
   }

   public void onEvent() {
   }

   @Generated
   public List<Kdced9e23f1e229ba9d<?>> getSettings() {
      return this.settings;
   }

   @Generated
   public List<K8d7165024ff567f5e7> getEventListeners() {
      return this.eventListeners;
   }
}
