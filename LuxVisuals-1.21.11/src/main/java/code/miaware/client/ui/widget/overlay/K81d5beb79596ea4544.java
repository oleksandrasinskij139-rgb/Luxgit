package code.miaware.client.ui.widget.overlay;

import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K81d5beb79596ea4544 extends K548ca8198549ddf9d7 {
   public K81d5beb79596ea4544() {
      super(3.0F, 3.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "Logo";
   }

   public void render(class_4587 matrixStack) {
      this.getDraggable().setWidth(0.0F);
      this.getDraggable().setHeight(0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   public void renderEditorOverlay(class_4587 matrixStack) {
   }
}
