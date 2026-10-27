package code.miaware.client.ui.clickgui;

import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;

public final class K87984c5639b98954c5 {
   private K87984c5639b98954c5() {
   }

   private static Color K13a39ecd5733652cd7(Color color, int alpha) {
      return K80ee108ea129adac11.setAlpha(color, alpha);
   }

   private static Color K7630f1cbfadd059a82(Color color1, Color color2, float t) {
      return K80ee108ea129adac11.interpolate(color1, color2, t);
   }

   public static Color panelOutline(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.35F), alpha);
   }

   public static Color panelBody(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.backgroundBlur(), Kb18d549c13309af9a5.blur(), 0.45F), alpha);
   }

   public static Color panelHeader(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.22F), alpha);
   }

   public static Color panelSeparator(int alpha) {
      return K13a39ecd5733652cd7(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.primary(), 60), alpha);
   }

   public static Color rowOutline(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.18F), alpha);
   }

   public static Color rowIdleLeft(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.backgroundBlur(), alpha);
   }

   public static Color rowIdleRight(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.backgroundBlur(), Kb18d549c13309af9a5.blur(), 0.15F), alpha);
   }

   public static Color rowActiveLeft(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.45F), alpha);
   }

   public static Color rowActiveRight(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.secondary(), 0.38F), alpha);
   }

   public static Color fieldFill(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.backgroundBlur(), alpha);
   }

   public static Color fieldOutline(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.25F), alpha);
   }

   public static Color fieldOutlineHovered(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.55F), alpha);
   }

   public static Color settingsBackground(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.backgroundBlur(), 0.5F), alpha);
   }

   public static Color settingsOutline(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.2F), alpha);
   }

   public static Color settingsCard(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.backgroundBlur(), Kb18d549c13309af9a5.blur(), 0.18F), alpha);
   }

   public static Color trackOff(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.backgroundBlur(), alpha);
   }

   public static Color trackBorder(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.backgroundBlur(), new Color(0, 0, 0, 255), 0.5F), alpha);
   }

   public static Color accentStart(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.primary(), alpha);
   }

   public static Color accentEnd(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.secondary(), alpha);
   }

   public static Color accent(int alpha) {
      return K80ee108ea129adac11.gradient(0.42F, accentStart(alpha), accentEnd(alpha));
   }

   public static Color text(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.textColor(), alpha);
   }

   public static Color mutedText(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.inactiveTextColor(), alpha);
   }

   public static Color inactiveKnob(int alpha) {
      return K13a39ecd5733652cd7(Kb18d549c13309af9a5.inactiveKnob(), alpha);
   }

   public static Color widgetBody(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.backgroundBlur(), Kb18d549c13309af9a5.blur(), 0.35F), alpha);
   }

   public static Color widgetOutline(int alpha) {
      return K13a39ecd5733652cd7(K7630f1cbfadd059a82(Kb18d549c13309af9a5.blur(), Kb18d549c13309af9a5.primary(), 0.22F), alpha);
   }
}
