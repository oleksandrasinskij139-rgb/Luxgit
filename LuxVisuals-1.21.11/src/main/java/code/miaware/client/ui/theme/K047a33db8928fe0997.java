package code.miaware.client.ui.theme;

import java.awt.Color;

public final class K047a33db8928fe0997 {
   private K047a33db8928fe0997() {
   }

   public static Theme create(int index) {
      K51ad80c57a5258fb43 preset = (K51ad80c57a5258fb43)K2c59a7f522ec4ea35b.PRESETS.get(index);
      Theme theme = new Theme(preset.nameEn());

      for(K89eed12a5c142a77f4 element : theme.getElementColors()) {
         Color var10000;
         switch (element.getName()) {
            case "Primary":
            case "Knob":
               var10000 = preset.primary();
               break;
            case "Secondary":
               var10000 = preset.secondary();
               break;
            case "Blur":
               var10000 = K2c59a7f522ec4ea35b.mix(new Color(38, 35, 46), preset.primary(), 0.08F);
               break;
            case "Widget blur":
               var10000 = K2c59a7f522ec4ea35b.mix(new Color(24, 23, 31), preset.primary(), 0.06F);
               break;
            case "Background blur":
               var10000 = K2c59a7f522ec4ea35b.mix(new Color(16, 15, 22), preset.primary(), 0.04F);
               break;
            case "Text":
               var10000 = new Color(241, 239, 247);
               break;
            case "Inactive text":
               var10000 = new Color(166, 160, 178);
               break;
            case "Inactive knob":
               var10000 = new Color(109, 103, 122);
               break;
            default:
               var10000 = element.getColor();
         }

         Color color = var10000;
         element.setColor(color);
      }

      return theme;
   }
}
