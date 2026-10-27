package code.miaware.client.ui.theme;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Theme {
   private final String name;
   private final List<K89eed12a5c142a77f4> elementColors = new ArrayList();

   public Theme(String name) {
      this.name = name;
      this.elementColors.add(new K89eed12a5c142a77f4("Primary", new Color(132, 170, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Secondary", new Color(110, 212, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Blur", new Color(31, 40, 63)));
      this.elementColors.add(new K89eed12a5c142a77f4("Widget blur", new Color(37, 49, 76, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Background blur", new Color(23, 33, 52, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Text", new Color(239, 246, 255, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Inactive text", new Color(173, 185, 206)));
      this.elementColors.add(new K89eed12a5c142a77f4("Knob", new Color(157, 197, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Inactive knob", new Color(255, 255, 255)));
      this.elementColors.add(new K89eed12a5c142a77f4("Positive", new Color(130, 255, 130)));
      this.elementColors.add(new K89eed12a5c142a77f4("Middle", new Color(255, 200, 95)));
      this.elementColors.add(new K89eed12a5c142a77f4("Negative", new Color(255, 80, 80)));
   }

   public Color getPrimaryColor() {
      return this.getElementColor("Primary");
   }

   public Color getSecondaryColor() {
      return this.getElementColor("Secondary");
   }

   public Color getBlurColor() {
      return this.getElementColor("Blur");
   }

   public Color getWidgetBlurColor() {
      return this.getElementColor("Widget blur");
   }

   public Color getBackgroundBlurColor() {
      return this.getElementColor("Background blur");
   }

   public Color getTextColor() {
      return this.getElementColor("Text");
   }

   public Color getInactiveTextColor() {
      return this.getElementColor("Inactive text");
   }

   public Color getKnobColor() {
      return this.getElementColor("Knob");
   }

   public Color getInactiveKnobColor() {
      return this.getElementColor("Inactive knob");
   }

   public Color getPositiveColor() {
      return this.getElementColor("Positive");
   }

   public Color getMiddleColor() {
      return this.getElementColor("Middle");
   }

   public Color getNegativeColor() {
      return this.getElementColor("Negative");
   }

   public Color getElementColor(String elementName) {
      for(K89eed12a5c142a77f4 element : this.elementColors) {
         if (element.getName().equalsIgnoreCase(elementName)) {
            return element.getColor();
         }
      }

      return new Color(-1);
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public List<K89eed12a5c142a77f4> getElementColors() {
      return this.elementColors;
   }
}
