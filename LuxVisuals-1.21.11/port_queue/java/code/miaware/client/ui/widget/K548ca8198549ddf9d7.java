package code.miaware.client.ui.widget;

import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.system.draggable.K8b86f285a8bad749b5;
import code.miaware.api.system.draggable.Kd8969ae8f12d70edf3;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.system.interfaces.Kca12759a49272a9bd8;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;

public abstract class K548ca8198549ddf9d7 implements K4600bef438dafc1a9c, Kca12759a49272a9bd8 {
   private final Kf3b62ecfba0c3544e6 easing;
   private final long duration;
   private final Ka91715a8c4e3f3b7c4 visibilityAnimation;
   private final K8b86f285a8bad749b5 draggable;
   private final K84ff281e75236bde7a settingsPopup;
   private boolean enabled;
   private float editorHover;
   private float editorDrag;

   protected K548ca8198549ddf9d7(float x, float y) {
      this.easing = Kf3b62ecfba0c3544e6.SINE_OUT;
      this.duration = 100L;
      this.visibilityAnimation = new Ka91715a8c4e3f3b7c4();
      this.settingsPopup = new K84ff281e75236bde7a(this);
      this.draggable = this.K717b2c68d6d9aca003(x, y, this.getName());
   }

   public abstract String getName();

   public float getWidgetScale() {
      return this.draggable.getWidgetScale();
   }

   public void setWidgetScale(float scale) {
      this.draggable.setWidgetScale(scale);
   }

   public String getDisplayName() {
      return Ka35e7d6b31db9984f1.getInstance().translateFeature(this.getName());
   }

   private K8b86f285a8bad749b5 K717b2c68d6d9aca003(float x, float y, String name) {
      return Kd8969ae8f12d70edf3.getInstance().create(Kfae29c687712462375.getInstance(), name, x, y);
   }

   public void render(Kb590f148cfda70c907 event) {
      this.render(event.matrixStack());
   }

   public float scaled(float value) {
      return Ke4708cf52c3b89f5ae.getInstance().scaledHud(value) * this.getWidgetScale();
   }

   public float getScale() {
      return Ke4708cf52c3b89f5ae.getInstance().getHudScale() * this.getWidgetScale();
   }

   public float getGap() {
      return this.scaled(2.85F);
   }

   protected float hudTextSize() {
      return this.scaled(6.65F);
   }

   protected float hudHeaderSize() {
      return this.scaled(7.35F);
   }

   protected float hudSingleLineHeight() {
      return this.hudTextSize() + this.getGap() * 1.28F;
   }

   protected Kdeecdbb204319837a4 pillMetrics() {
      return new Kdeecdbb204319837a4(this.scaled(19.5F), this.scaled(8.0F), this.scaled(8.6F), this.scaled(4.0F), this.scaled(6.9F), this.scaled(6.5F), this.scaled(6.0F));
   }

   protected float pillGap() {
      return this.scaled(4.5F);
   }

   protected K9c25dd69cb23ed0eb8 listMetrics() {
      return new K9c25dd69cb23ed0eb8(this.scaled(8.0F), this.scaled(21.0F), this.scaled(9.5F), this.scaled(7.8F), this.scaled(13.6F), this.scaled(6.9F), this.scaled(7.2F), this.scaled(14.0F), this.scaled(6.0F), this.scaled(6.5F));
   }

   protected float hudListRowHeight() {
      return this.hudTextSize() + this.getGap() * 0.68F;
   }

   public float updateVisibilityAnimation() {
      this.visibilityAnimation.update();
      this.visibilityAnimation.run(this.enabled ? (double)1.0F : (double)0.0F, this.enabled ? 170L : 210L, Kf3b62ecfba0c3544e6.QUINT_OUT, true);
      return (float)this.visibilityAnimation.getValue();
   }

   protected boolean hudLayoutMode() {
      return mc.field_1755 instanceof class_408;
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   protected String getSettingsMenuTitle() {
      return this.getDisplayName();
   }

   protected String getSettingsMenuBadge() {
      return "HUD";
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      return List.of();
   }

   protected void updateSettingsMenuState() {
      this.settingsPopup.updateState(this.hudLayoutMode() && this.hasSettingsMenu());
   }

   protected void closeSettingsMenu() {
      this.settingsPopup.close();
   }

   protected void setSettingsMenuAnchor(float x, float y, float width, float height) {
      this.settingsPopup.setAnchor(x, y, width, height);
   }

   protected void renderSettingsMenu(class_4587 matrixStack) {
      if (this.hasSettingsMenu() && this.settingsPopup.isVisible()) {
         this.settingsPopup.render(matrixStack, this.getSettingsMenuTitle(), this.getSettingsMenuBadge(), this.getSettingsMenuEntries());
      }
   }

   protected boolean isSettingsMenuVisible() {
      return this.settingsPopup.isVisible();
   }

   protected float getSettingsMenuBottom() {
      return this.settingsPopup.getBottom();
   }

   protected boolean handleSettingsMenuClick(double mouseX, double mouseY, int button) {
      return this.hasSettingsMenu() && this.settingsPopup.mouseClicked(mouseX, mouseY, button, this.getSettingsMenuEntries());
   }

   protected void handleSettingsMenuRelease(double mouseX, double mouseY, int button) {
      if (this.hasSettingsMenu()) {
         this.settingsPopup.mouseReleased(mouseX, mouseY, button);
      }

   }

   private boolean K9095b78161b9a1a17d() {
      return this.hudLayoutMode() && this.draggable.getWidth() > 1.0F && this.draggable.getHeight() > 1.0F;
   }

   private static float Kfb47959960eb3cf9ed(float value, float target, float speed) {
      float next = value + (target - value) * speed;
      return Math.abs(next - target) < 0.004F ? target : next;
   }

   public void renderEditorBackdrop(class_4587 ms) {
      boolean active = this.K9095b78161b9a1a17d();
      boolean dragging = active && this.draggable.isDragging();
      boolean hovered = active && (dragging || this.draggable.isHovering());
      this.editorHover = Kfb47959960eb3cf9ed(this.editorHover, hovered ? 1.0F : 0.0F, 0.22F);
      this.editorDrag = Kfb47959960eb3cf9ed(this.editorDrag, dragging ? 1.0F : 0.0F, 0.22F);
      if (!(this.editorHover <= 0.01F) && !(this.draggable.getWidth() <= 1.0F)) {
         float pad = this.scaled(3.0F + this.editorDrag * 0.8F);
         float x = this.draggable.getX() - pad;
         float y = this.draggable.getY() - pad;
         float w = this.draggable.getWidth() + pad * 2.0F;
         float h = this.draggable.getHeight() + pad * 2.0F;
         float r = this.scaled(8.0F);
         float a = this.editorHover;
         float glow = 0.55F + this.editorDrag * 0.45F;

         for(int i = 3; i >= 1; --i) {
            float g = this.scaled(1.6F * (float)i);
            Ka20f8cfdbc113ccdec.rect(ms, x - g, y - g, w + g * 2.0F, h + g * 2.0F, r + g, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, a * glow * (0.05F + 0.03F * (float)(3 - i))));
         }

         float ring = this.scaled(1.0F);
         Ka20f8cfdbc113ccdec.rect(ms, x - ring, y - ring, w + ring * 2.0F, h + ring * 2.0F, r + ring, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT_SOFT, a * (0.7F + this.editorDrag * 0.3F)));
         Ka20f8cfdbc113ccdec.rect(ms, x, y, w, h, r, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.BG, a * 0.88F));
      }
   }

   public void renderEditorOverlay(class_4587 ms) {
      if (!(this.editorHover <= 0.01F) && !(this.draggable.getWidth() <= 1.0F)) {
         float a = this.editorHover;
         float pad = this.scaled(3.0F + this.editorDrag * 0.8F);
         float x = this.draggable.getX() - pad;
         float y = this.draggable.getY() - pad;
         float w = this.draggable.getWidth() + pad * 2.0F;
         float h = this.draggable.getHeight() + pad * 2.0F;
         boolean ru = Ka35e7d6b31db9984f1.getInstance().isRussian();
         String text;
         String icon;
         if (this.editorDrag > 0.5F) {
            icon = "pin";
            int var10000 = Math.round(this.draggable.getX());
            text = var10000 + ", " + Math.round(this.draggable.getY()) + " · " + Math.round(this.getWidgetScale() * 100.0F) + "%";
         } else {
            icon = "command";
            text = this.hasSettingsMenu() ? (ru ? "ЛКМ — двигать · ЛКМ + колесо — размер · ПКМ — настройки" : "Drag to move · Hold + scroll to resize · Right click to edit") : (ru ? "ЛКМ — двигать · ЛКМ + колесо — размер" : "Drag to move · Hold + scroll to resize");
         }

         Kdeecdbb204319837a4 m = new Kdeecdbb204319837a4(this.scaled(12.5F), this.scaled(5.5F), this.scaled(6.0F), this.scaled(3.0F), this.scaled(5.6F), this.scaled(4.0F), this.scaled(4.5F));
         List<Kd80e03babf074b8b76> segs = List.of((new Kd80e03babf074b8b76(icon, Ka20f8cfdbc113ccdec.ACCENT)).add(text, Ka20f8cfdbc113ccdec.TEXT));
         float pw = Ka20f8cfdbc113ccdec.pillWidth(segs, m);
         float px = Math.max(this.scaled(2.0F), Math.min(x + w / 2.0F - pw / 2.0F, (float)mc.method_22683().method_4486() - pw - this.scaled(2.0F)));
         float below = y + h + this.scaled(4.0F);
         float py = below + m.height() <= (float)mc.method_22683().method_4502() - this.scaled(2.0F) ? below : y - m.height() - this.scaled(4.0F);
         Ka20f8cfdbc113ccdec.pill(ms, px, py, segs, m, a);
      }
   }

   public K5fe84dad3db1af4bb7 getMediumFont() {
      return K896065ea0a2bba4f47.PS_MEDIUM;
   }

   public K5fe84dad3db1af4bb7 getSemiBoldFont() {
      return K896065ea0a2bba4f47.PS_BOLD;
   }

   public abstract void render(class_332 var1, int var2, int var3, float var4);

   public abstract void mouseClicked(double var1, double var3, int var5);

   public abstract void mouseReleased(double var1, double var3, int var5);

   public abstract void keyPressed(int var1, int var2, int var3);

   public abstract void mouseScrolled(double var1, double var3, double var5, double var7);

   @Generated
   public Kf3b62ecfba0c3544e6 getEasing() {
      return this.easing;
   }

   @Generated
   public long getDuration() {
      Objects.requireNonNull(this);
      return 100L;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getVisibilityAnimation() {
      return this.visibilityAnimation;
   }

   @Generated
   public K8b86f285a8bad749b5 getDraggable() {
      return this.draggable;
   }

   @Generated
   public K84ff281e75236bde7a getSettingsPopup() {
      return this.settingsPopup;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public float getEditorHover() {
      return this.editorHover;
   }

   @Generated
   public float getEditorDrag() {
      return this.editorDrag;
   }

   @Generated
   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   @Generated
   public void setEditorHover(float editorHover) {
      this.editorHover = editorHover;
   }

   @Generated
   public void setEditorDrag(float editorDrag) {
      this.editorDrag = editorDrag;
   }
}
