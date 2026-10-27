package luxvisuals.customsword;

import dev.luxvisuals.fix.Sel;

public class CustomSword {
   public static CustomSword INSTANCE = new CustomSword();

   public boolean isToggled() {
      return selectedName() != null;
   }

   public String selected() {
      return selectedName();
   }

   public static String selectedName() {
      return Sel.sword();
   }
}
