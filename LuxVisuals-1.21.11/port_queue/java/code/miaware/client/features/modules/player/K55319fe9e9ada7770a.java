package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

@ModuleRegister(
   name = "PVPFix",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически чинит вещи после завершения PVP"
)
public class K55319fe9e9ada7770a extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K55319fe9e9ada7770a instance = new K55319fe9e9ada7770a();

   public void onEvent() {
      K8d7165024ff567f5e7 packetListener = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((data) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (data.isReceive()) {
               class_2596 patt0$temp = data.packet();
               if (patt0$temp instanceof class_7439) {
                  class_7439 p = (class_7439)patt0$temp;
                  String raw = p.comp_763().getString();
                  if (raw.contains("АнтиРелог") && raw.contains("PVP режим закончился")) {
                     mc.method_1562().method_45730("fix all");
                  }
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{packetListener});
   }

   @Generated
   public static K55319fe9e9ada7770a getInstance() {
      return instance;
   }
}
