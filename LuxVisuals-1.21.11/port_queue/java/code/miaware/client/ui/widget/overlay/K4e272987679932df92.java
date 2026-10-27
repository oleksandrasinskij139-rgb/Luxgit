package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kd80e03babf074b8b76;
import code.miaware.client.ui.widget.Kdeecdbb204319837a4;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;

public class K4e272987679932df92 extends K548ca8198549ddf9d7 {
   private float animBps;

   public K4e272987679932df92() {
      super(10.0F, 100.0F);
   }

   public String getName() {
      return "Coordinates";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (mc.field_1724 == null) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (mc.method_22683() != null) {
         String posX = String.format("%.0f", mc.field_1724.method_23317());
         String posY = String.format("%.0f", mc.field_1724.method_23318());
         String posZ = String.format("%.0f", mc.field_1724.method_23321());
         this.animBps = Ka0484a23398fb5f9ac.interpolate(this.animBps, (float)this.Kb5be2cf409533b3fa4(), 0.18F);
         String bpsValue = String.format("%.1f", this.animBps);
         Kdeecdbb204319837a4 m = this.pillMetrics();
         List<Kd80e03babf074b8b76> segments = List.of((new Kd80e03babf074b8b76("pin", Ka20f8cfdbc113ccdec.ACCENT)).add(posX, Ka20f8cfdbc113ccdec.TEXT).add("x  ", Ka20f8cfdbc113ccdec.ACCENT_SOFT).add(posY, Ka20f8cfdbc113ccdec.TEXT).add("y  ", Ka20f8cfdbc113ccdec.ACCENT_SOFT).add(posZ, Ka20f8cfdbc113ccdec.TEXT).add("z", Ka20f8cfdbc113ccdec.ACCENT_SOFT), (new Kd80e03babf074b8b76("plane", Ka20f8cfdbc113ccdec.ACCENT)).add(bpsValue, Ka20f8cfdbc113ccdec.TEXT).add(" bps", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
         boolean inChat = mc.field_1755 instanceof class_408;
         float chatHeight = inChat ? this.scaled(14.0F) : 0.0F;
         float x = this.scaled(3.0F);
         float y = (float)mc.method_22683().method_4502() - m.height() - this.scaled(3.0F) - chatHeight;
         this.getDraggable().setX(x);
         this.getDraggable().setY(y);
         float width = Ka20f8cfdbc113ccdec.pill(matrixStack, x, y, segments, m, 1.0F);
         this.getDraggable().setWidth(width);
         this.getDraggable().setHeight(m.height());
      }
   }

   private double Kb5be2cf409533b3fa4() {
      if (mc.field_1724 == null) {
         return (double)0.0F;
      } else {
         double bps = mc.field_1724.method_18798().method_37267() * (double)20.0F;
         return !Double.isNaN(bps) && !Double.isInfinite(bps) ? Math.min((double)50.0F, Math.max((double)0.0F, bps)) : (double)0.0F;
      }
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
