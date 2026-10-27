package code.miaware.client.ui.screen;

import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_437;

public class K4fb532bfd9466813ac extends class_437 {
   private final class_437 parent;
   private final Runnable onConfirm;

   public K4fb532bfd9466813ac(class_437 parent, Runnable onConfirm) {
      super(class_2561.method_43470("Подтверждение выхода"));
      this.parent = parent;
      this.onConfirm = onConfirm;
   }

   protected void method_25426() {
      super.method_25426();
      int buttonWidth = 200;
      int buttonHeight = 20;
      int centerX = this.field_22789 / 2;
      int centerY = this.field_22790 / 2;
      this.method_37063(class_4185.method_46430(class_2561.method_43470("§cДа, выйти с сервера"), (button) -> {
         if (this.onConfirm != null) {
            this.onConfirm.run();
         }

      }).method_46434(centerX - buttonWidth / 2, centerY - 10, buttonWidth, buttonHeight).method_46431());
      this.method_37063(class_4185.method_46430(class_2561.method_43470("§aОтмена"), (button) -> {
         if (this.field_22787 != null) {
            this.field_22787.method_1507(this.parent);
         }

      }).method_46434(centerX - buttonWidth / 2, centerY + 15, buttonWidth, buttonHeight).method_46431());
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      this.method_25420(context, mouseX, mouseY, delta);
      context.method_25300(this.field_22793, "§c§lВНИМАНИЕ!", this.field_22789 / 2, this.field_22790 / 2 - 50, 16777215);
      context.method_25300(this.field_22793, "§fВы находитесь в режиме PvP!", this.field_22789 / 2, this.field_22790 / 2 - 35, 16777215);
      context.method_25300(this.field_22793, "§fВы уверены, что хотите выйти?", this.field_22789 / 2, this.field_22790 / 2 - 25, 16777215);
      super.method_25394(context, mouseX, mouseY, delta);
   }

   public boolean method_25422() {
      return false;
   }
}
