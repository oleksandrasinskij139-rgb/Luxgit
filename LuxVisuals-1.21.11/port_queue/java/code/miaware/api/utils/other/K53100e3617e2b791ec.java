package code.miaware.api.utils.other;

import code.miaware.api.event.events.other.Kbec6a700ec92806750;
import code.miaware.api.system.backend.K6e82e9f893ec34a816;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.player.Kd03e5f745eca318ca1;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_490;

public final class K53100e3617e2b791ec implements K4600bef438dafc1a9c {
   public static void drawButton(Kbec6a700ec92806750 event) {
      int buttonWidth = 80;
      int buttonHeight = 20;
      class_437 screen = event.screen();
      if (screen instanceof class_490 inv) {
         ;
      } else if (screen instanceof class_465<?> handledScreen) {
         class_1703 var7 = handledScreen.method_17577();
         if (var7 instanceof class_1707 genericHandler) {
            String title = handledScreen.method_25440().getString();
            if (title.contains("Аукцион") && title.contains("Поиск")) {
               return;
            }

            int rows = genericHandler.method_17388();
            int backgroundHeight = 114 + rows * 18;
            int buttonX = screen.field_22789 / 2 + 90;
            int var11 = (screen.field_22790 - backgroundHeight) / 2;
         }
      }

   }

   @SafeVarargs
   private static void K3a70baeb94bafad011(Kbec6a700ec92806750 event, int x, int y, int width, int height, K6e82e9f893ec34a816<String, Runnable>... pairs) {
      List<class_4185> buttons = new ArrayList();
      int offsetY = y;

      for(K6e82e9f893ec34a816<String, Runnable> pair : pairs) {
         class_4185 button = class_4185.method_46430(class_2561.method_43470(pair.left()), (b) -> ((Runnable)pair.right()).run()).method_46434(x, offsetY, width, height).method_46431();
         buttons.add(button);
         offsetY += height + 5;
      }

      event.buttons().addAll(buttons);
   }

   private static void Kff757b84e3e58ace36(class_1707 containerHandler) {
      for(int i = 0; i < containerHandler.method_7629().method_5439(); ++i) {
         class_1735 slot = containerHandler.method_7611(i);
         if (slot.method_7681()) {
            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7794, mc.field_1724);
         }
      }

   }

   private static void Ka9b903edecb13d85bc(class_1707 containerHandler) {
      for(int i = containerHandler.method_7629().method_5439(); i < containerHandler.field_7761.size(); ++i) {
         class_1735 slot = containerHandler.method_7611(i);
         if (slot.method_7681()) {
            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7794, mc.field_1724);
         }
      }

   }

   private static void drop(class_465<?> screen) {
      for(class_1735 slot : screen.method_17577().field_7761) {
         if (!slot.method_7677().method_7960()) {
            Kd03e5f745eca318ca1.dropSlot(slot.field_7874);
         }
      }

   }

   @Generated
   private K53100e3617e2b791ec() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
