package code.miaware.client.ui.widget.overlay;

import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import java.awt.Color;

public enum Ka58c9e2c4d8aa9af2e {
   SUCCESS("Success"),
   INFO("Info"),
   WARNING("Warning"),
   ERROR("Alert");

   private final String display;

   private Ka58c9e2c4d8aa9af2e(String display) {
      this.display = display;
   }

   private Color accent() {
      Color var10000;
      switch (this.ordinal()) {
         case 0:
         case 1:
            var10000 = Ka20f8cfdbc113ccdec.ACCENT;
            break;
         case 2:
            var10000 = Ka20f8cfdbc113ccdec.WARM;
            break;
         case 3:
            var10000 = Ka20f8cfdbc113ccdec.RED;
            break;
         default:
            throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }

   // $FF: synthetic method
   private static Ka58c9e2c4d8aa9af2e[] $values() {
      return new Ka58c9e2c4d8aa9af2e[]{SUCCESS, INFO, WARNING, ERROR};
   }
}
