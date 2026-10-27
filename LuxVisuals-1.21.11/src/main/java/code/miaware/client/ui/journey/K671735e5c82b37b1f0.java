package code.miaware.client.ui.journey;

import code.miaware.client.ui.clickgui.K224f9523984e1fa68b;
import code.miaware.client.ui.clickgui.Kc517862407c2394736;
import java.awt.Color;

public final class K671735e5c82b37b1f0 {
   public static void render(K224f9523984e1fa68b canvas, String dimension, double seconds, float w, float h, float opacity, float darkness, boolean title, boolean loading) {
      canvas.setAlpha(opacity);
      canvas.rect(0.0F, 0.0F, w, h, 0.0F, new Color(5, 8, 15, Math.round(darkness * 0.5F)));
      canvas.gradient(0.0F, 0.0F, w, h, 0.0F, new Color(4, 7, 15, 40), new Color(4, 7, 15, 40), new Color(4, 7, 15, 90), new Color(4, 7, 15, 90));
      canvas.rect(0.0F, 0.0F, w, h * 0.055F, 0.0F, new Color(3, 5, 11, 165));
      canvas.rect(0.0F, h * 0.945F, w, h * 0.055F, 0.0F, new Color(3, 5, 11, 165));
      Kfd874cf8dd9b0294d6 palette = K35d6e3ad814c5a9922.palette(dimension);
      float ui = Math.min(w / 1600.0F, h / 900.0F);
      canvas.text(Kc517862407c2394736.SEMIBOLD, "LUXVISUALS / JOURNEYS", w * 0.045F, h * 0.1F, 13.0F * ui, new Color(240, 231, 219, 165));
      String chapter = "CHAPTER " + palette.chapter();
      canvas.text(Kc517862407c2394736.MEDIUM, chapter, w * 0.955F - canvas.textWidth(Kc517862407c2394736.MEDIUM, chapter, 12.0F * ui), h * 0.1F, 12.0F * ui, new Color(240, 231, 219, 140));
      if (title) {
         float reveal = Math.min(1.0F, (float)seconds / 0.65F);
         float lift = 13.0F * ui * (1.0F - reveal);
         Color accent = palette.accent();
         K773da2e5fc9e84702c(canvas, "C I N E M A T I C   J O U R N E Y", Kc517862407c2394736.MEDIUM, w, h * 0.405F + lift, 13.0F * ui, new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), Math.round(210.0F * reveal)));
         float size = 76.0F * ui;
         size = Math.min(size, w * 0.8F / Math.max(1.0F, canvas.textWidth(Kc517862407c2394736.SEMIBOLD, palette.name(), 1.0F)));
         K773da2e5fc9e84702c(canvas, palette.name(), Kc517862407c2394736.SEMIBOLD, w, h * 0.485F + lift, size, new Color(252, 246, 236, Math.round(255.0F * reveal)));
         canvas.rect(w / 2.0F - 27.0F * ui, h * 0.563F + lift, 54.0F * ui, 1.5F * ui, 0.0F, new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), Math.round(170.0F * reveal)));
      }

      if (loading) {
         K773da2e5fc9e84702c(canvas, "Preparing terrain", Kc517862407c2394736.MEDIUM, w, h * 0.818F, 15.0F * ui, new Color(244, 238, 233, 180));
         float trackW = 132.0F * ui;
         float trackX = (w - trackW) / 2.0F;
         float trackY = h * 0.85F;
         canvas.rect(trackX, trackY, trackW, 2.0F * ui, ui, new Color(255, 245, 230, 35));
         float phase = (float)((double)0.5F + (double)0.5F * Math.sin(seconds * 1.7));
         canvas.rect(trackX + phase * (trackW - 30.0F * ui), trackY, 30.0F * ui, 2.0F * ui, ui, palette.accent());
      }

      canvas.setAlpha(1.0F);
   }

   private static void K773da2e5fc9e84702c(K224f9523984e1fa68b canvas, String text, Kc517862407c2394736 font, float w, float y, float size, Color color) {
      canvas.text(font, text, (w - canvas.textWidth(font, text, size)) / 2.0F, y, size, color);
   }
}
