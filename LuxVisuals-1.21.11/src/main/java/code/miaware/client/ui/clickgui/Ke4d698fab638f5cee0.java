package code.miaware.client.ui.clickgui;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import java.util.List;

public interface Ke4d698fab638f5cee0 {
   List<Kab8a8c478f7d0077cd> modules();

   String clientName();

   String gameVersion();

   String username();

   String role();

   String subscriptionText();

   float subscriptionProgress();

   String keyName(int var1);

   String clipboard();

   boolean controlDown();

   void playSelect();

   default void playHover() {
   }

   default void playDescriptionKey() {
   }

   default void stopDescriptionSound() {
   }

   boolean isLocked(Kab8a8c478f7d0077cd var1);
}
