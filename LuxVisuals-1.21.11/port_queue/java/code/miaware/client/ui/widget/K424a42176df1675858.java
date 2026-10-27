package code.miaware.client.ui.widget;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.K5b5bf2e4f791eb67a2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_408;
import net.minecraft.class_4587;

public abstract class K424a42176df1675858 extends K548ca8198549ddf9d7 {
   private final List<Kd3e9d1fa34f548625d> activeElements = new ArrayList();
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private final Ka91715a8c4e3f3b7c4 widthAnimation = new Ka91715a8c4e3f3b7c4();
   private final Ka91715a8c4e3f3b7c4 heightAnimation = new Ka91715a8c4e3f3b7c4();
   private float width;
   private float height;
   private float containerHeight;

   public K424a42176df1675858(float x, float y) {
      super(x, y);
   }

   public boolean shouldShow() {
      return this.getActiveElements().stream().anyMatch((e) -> e.getAnimation().getValue() > 0.1);
   }

   public List<Kd3e9d1fa34f548625d> containerElements() {
      this.K27cb5539677b42331f();
      return this.getActiveElements();
   }

   protected abstract Map<String, K8974f150cc9dad5e95> getCurrentData();

   protected boolean shouldKeep(Kd3e9d1fa34f548625d element, Map<String, K8974f150cc9dad5e95> currentData) {
      return currentData.containsKey(element.getFirst());
   }

   private float K94c099275f45ba3360() {
      return this.getGap() * 1.15F;
   }

   private float K716941dc47d55c1de9() {
      return this.Kac0ebbb97603be1601(true) + this.getGap() * 2.45F;
   }

   private int K83e132d6981a24e397() {
      return (int)(this.showAnimation.getValue() * (double)255.0F);
   }

   private float Kac0ebbb97603be1601(boolean header) {
      return header ? this.hudHeaderSize() : this.hudTextSize();
   }

   public void render(class_4587 matrixStack) {
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      this.widthAnimation.update();
      this.heightAnimation.update();
      this.showAnimation.update();
      this.showAnimation.run(!this.shouldShow() && !(mc.field_1755 instanceof class_408) ? (double)0.0F : (double)1.0F, this.getDuration(), this.getEasing());
      float animHeight = (float)this.heightAnimation.getValue();
      float animWidth = (float)this.widthAnimation.getValue();
      float offset = this.K94c099275f45ba3360() * 1.2F;
      float round = this.getGap() * 2.0F;
      float showValue = (float)this.showAnimation.getValue();
      float contentFade = Math.max(0.0F, Math.min(1.0F, (showValue - 0.12F) / 0.88F));
      int alpha = this.K83e132d6981a24e397();
      float headerSize = this.Kac0ebbb97603be1601(true);
      String headerName = this.getDisplayName();
      float headerWidth = this.getMediumFont().getWidth(headerName, headerSize);
      this.width = headerWidth + this.K94c099275f45ba3360() * 4.0F;
      this.height = this.K716941dc47d55c1de9();
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, animWidth, animHeight, round, Kb18d549c13309af9a5.widgetBlur(alpha));
      this.getMediumFont().drawCenteredGradientText(matrixStack, headerName, x + animWidth / 2.0F, y + this.K716941dc47d55c1de9() / 2.0F - headerSize / 2.0F, headerSize, Kb18d549c13309af9a5.secondary(alpha), Kb18d549c13309af9a5.primary(alpha), headerWidth / 4.0F);
      K5b5bf2e4f791eb67a2.start(matrixStack, x, y, animWidth, animHeight);
      this.containerHeight = 0.0F;
      float elementY = y + this.K716941dc47d55c1de9() + offset / 2.0F;
      float elementSize = this.Kac0ebbb97603be1601(false);
      float xFirst = x + offset;
      float xSecondBase = x + animWidth - offset;

      for(Kd3e9d1fa34f548625d containerElement : this.containerElements()) {
         String first = containerElement.getFirst();
         String second = containerElement.getSecond().text();
         float anim = (float)containerElement.getAnimation().getValue();
         float layoutAnim = Math.max(0.0F, Math.min(1.0F, (anim - 0.04F) / 0.96F));
         float textAnim = Math.max(0.0F, Math.min(1.0F, (anim - 0.34F) / 0.66F));
         float sex = this.scaled(1.15F) * (1.0F - layoutAnim);
         int elementAlpha = (int)(textAnim * contentFade * 255.0F);
         this.getMediumFont().drawText(matrixStack, first, xFirst, elementY - sex, elementSize, Kb18d549c13309af9a5.textColor(elementAlpha));
         float secondWidth = this.getMediumFont().getWidth(second, elementSize);
         this.getMediumFont().drawText(matrixStack, second, xSecondBase - secondWidth, elementY - sex, elementSize, K80ee108ea129adac11.setAlpha(containerElement.getSecond().color, elementAlpha));
         float addition = elementSize + this.getGap();
         elementY += addition * layoutAnim;
         if (containerElement.isValid()) {
            this.addHeight(addition);
         }

         this.updateWidth(first, second, offset * 4.0F);
      }

      K5b5bf2e4f791eb67a2.stop(matrixStack);
      this.widthAnimation.run(this.width, this.getDuration(), this.getEasing());
      this.heightAnimation.run(this.height, this.getDuration(), this.getEasing());
      this.getDraggable().setWidth((float)this.widthAnimation.getValue());
      this.getDraggable().setHeight((float)this.heightAnimation.getValue());
   }

   public void addHeight(float value) {
      this.containerHeight += value;
      this.height = this.K716941dc47d55c1de9() + this.containerHeight + this.K94c099275f45ba3360();
   }

   public void updateWidth(String first, String second, float gap) {
      float fontSize = this.Kac0ebbb97603be1601(false);
      float firstWidth = this.getMediumFont().getWidth(first, fontSize);
      float secondWidth = this.getMediumFont().getWidth(second, fontSize);
      float total = firstWidth + gap + secondWidth;
      if (total > this.width) {
         this.width = total;
      }

   }

   private void K27cb5539677b42331f() {
      Kf3b62ecfba0c3544e6 easing = this.getEasing();
      long duration = this.getDuration();

      for(Kd3e9d1fa34f548625d element : this.activeElements) {
         element.getAnimation().update();
      }

      Map<String, K8974f150cc9dad5e95> currentData = this.getCurrentData();
      if (currentData == null) {
         currentData = Collections.emptyMap();
      }

      Map<String, Kd3e9d1fa34f548625d> lookup = new HashMap(this.activeElements.size() * 2 + 1);

      for(Kd3e9d1fa34f548625d e : this.activeElements) {
         lookup.put(e.getFirst(), e);
      }

      for(Map.Entry<String, K8974f150cc9dad5e95> entry : currentData.entrySet()) {
         String name = (String)entry.getKey();
         K8974f150cc9dad5e95 value = (K8974f150cc9dad5e95)entry.getValue();
         Kd3e9d1fa34f548625d element = (Kd3e9d1fa34f548625d)lookup.get(name);
         if (element == null) {
            Kd3e9d1fa34f548625d newElement = new Kd3e9d1fa34f548625d(name, value);
            newElement.getAnimation().run((double)1.0F, duration, easing);
            this.activeElements.add(newElement);
            lookup.put(name, newElement);
         } else {
            if (!element.getSecond().equals(value)) {
               element.setSecond(value);
            }

            element.setValid(true);
         }
      }

      Iterator<Kd3e9d1fa34f548625d> iterator = this.activeElements.iterator();

      while(iterator.hasNext()) {
         Kd3e9d1fa34f548625d element = (Kd3e9d1fa34f548625d)iterator.next();
         Ka91715a8c4e3f3b7c4 anim = element.getAnimation();
         if (!this.shouldKeep(element, currentData)) {
            element.setValid(false);
            anim.run((double)0.0F, duration, easing);
            if (anim.getValue() <= 0.01) {
               iterator.remove();
            }
         } else if (element.isValid()) {
            anim.run((double)1.0F, duration, easing);
         }
      }

   }

   @Generated
   public List<Kd3e9d1fa34f548625d> getActiveElements() {
      return this.activeElements;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getShowAnimation() {
      return this.showAnimation;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getWidthAnimation() {
      return this.widthAnimation;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getHeightAnimation() {
      return this.heightAnimation;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public float getContainerHeight() {
      return this.containerHeight;
   }

   @Generated
   public void setWidth(float width) {
      this.width = width;
   }

   @Generated
   public void setHeight(float height) {
      this.height = height;
   }

   @Generated
   public void setContainerHeight(float containerHeight) {
      this.containerHeight = containerHeight;
   }
}
