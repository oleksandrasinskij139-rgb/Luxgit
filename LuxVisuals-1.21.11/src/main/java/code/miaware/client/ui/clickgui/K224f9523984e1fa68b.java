package code.miaware.client.ui.clickgui;

import java.awt.Color;

public interface K224f9523984e1fa68b {
   void setAlpha(float var1);

   void rect(float var1, float var2, float var3, float var4, float var5, Color var6);

   void glass(float var1, float var2, float var3, float var4, float var5, Color var6, Color var7);

   void gradient(float var1, float var2, float var3, float var4, float var5, Color var6, Color var7, Color var8, Color var9);

   void text(Kc517862407c2394736 var1, String var2, float var3, float var4, float var5, Color var6);

   float textWidth(Kc517862407c2394736 var1, String var2, float var3);

   void icon(K756e30c0738d06c926 var1, float var2, float var3, float var4, Color var5);

   void image(String var1, float var2, float var3, float var4, float var5, Color var6);

   void logo(float var1, float var2, float var3, Color var4);

   void head(float var1, float var2, float var3);

   default boolean cosmeticPreview(float x, float y, float w, float h, float yaw, float zoom, int itemId) {
      return false;
   }

   default void skinHead(String texture, float x, float y, float size) {
      this.head(x, y, size);
   }

   void pushClip(float var1, float var2, float var3, float var4);

   void popClip();
}
