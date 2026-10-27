package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.render.display.K9fca83c9ab54ebe745;
import code.miaware.api.utils.render.display.Kdbe29c088d9ae5b9a4;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2595;
import net.minecraft.class_2627;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_465;
import net.minecraft.class_7923;

@ModuleRegister(
   name = "ItemESP",
   category = Kf76f042931183af5ba.RENDER,
   description = "Подсвечивает выбранные предметы в контейнерах"
)
public class K58d4d66f061a2a6fed extends Kab8a8c478f7d0077cd {
   private static final K58d4d66f061a2a6fed instance = new K58d4d66f061a2a6fed();
   private final Set<String> trackedItems = new HashSet();
   private final K9fca83c9ab54ebe745 boxRender = new K9fca83c9ab54ebe745();
   private final Map<class_2338, Kfb2eab2b78d7b89322> containerCache = new HashMap();
   private int updateTicks = 0;

   public void onEvent() {
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!this.trackedItems.isEmpty() && mc.field_1724 != null && mc.field_1687 != null) {
            ++this.updateTicks;
            if (this.updateTicks >= 20) {
               this.updateTicks = 0;
               this.Kbf91cdda56e46d882c();
            }

            class_4587 matrices = event.matrixStack();
            matrices.method_22903();
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            this.Kb7e507077b88a5b913();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            matrices.method_22909();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{renderEvent});
   }

   public void onDisable() {
      super.onDisable();
      this.containerCache.clear();
      this.updateTicks = 0;
   }

   public void renderSlotHighlight(class_332 context, class_465<?> screen, class_1735 slot) {
      if (this.isEnabled() && !this.trackedItems.isEmpty()) {
         if (mc.field_1724 != null) {
            class_1799 stack = slot.method_7677();
            if (!stack.method_7960()) {
               String itemId = class_7923.field_41178.method_10221(stack.method_7909()).toString();
               if (this.trackedItems.contains(itemId)) {
                  Color color = this.Kf9183ff757f420f160(stack);
                  this.K900fa28c448bc845da(context, slot, color);
               }
            }
         }
      }
   }

   private void K900fa28c448bc845da(class_332 context, class_1735 slot, Color baseColor) {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      Color fillColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 80);
      context.method_25294(slot.field_7873, slot.field_7872, slot.field_7873 + 16, slot.field_7872 + 16, fillColor.getRGB());
      Color borderColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 200);
      context.method_25294(slot.field_7873 - 1, slot.field_7872 - 1, slot.field_7873 + 17, slot.field_7872, borderColor.getRGB());
      context.method_25294(slot.field_7873 - 1, slot.field_7872 + 16, slot.field_7873 + 17, slot.field_7872 + 17, borderColor.getRGB());
      context.method_25294(slot.field_7873 - 1, slot.field_7872 - 1, slot.field_7873, slot.field_7872 + 17, borderColor.getRGB());
      context.method_25294(slot.field_7873 + 16, slot.field_7872 - 1, slot.field_7873 + 17, slot.field_7872 + 17, borderColor.getRGB());
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
   }

   private void Kbf91cdda56e46d882c() {
      this.containerCache.clear();
      class_2338 playerPos = mc.field_1724.method_24515();
      int radius = 32;
      int chunkRadius = radius / 16 + 1;
      int playerChunkX = playerPos.method_10263() >> 4;
      int playerChunkZ = playerPos.method_10260() >> 4;

      for(int chunkX = playerChunkX - chunkRadius; chunkX <= playerChunkX + chunkRadius; ++chunkX) {
         label77:
         for(int chunkZ = playerChunkZ - chunkRadius; chunkZ <= playerChunkZ + chunkRadius; ++chunkZ) {
            if (mc.field_1687.method_2935().method_12123(chunkX, chunkZ)) {
               class_2818 chunk = mc.field_1687.method_8497(chunkX, chunkZ);
               Iterator var9 = chunk.method_12214().entrySet().iterator();

               while(true) {
                  class_2338 pos;
                  List<class_1799> items;
                  label66:
                  while(true) {
                     if (!var9.hasNext()) {
                        continue label77;
                     }

                     Map.Entry<class_2338, class_2586> entry = (Map.Entry)var9.next();
                     pos = (class_2338)entry.getKey();
                     class_2586 blockEntity = (class_2586)entry.getValue();
                     double distSq = mc.field_1724.method_5649((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F);
                     if (!(distSq > (double)(radius * radius))) {
                        items = new ArrayList();
                        if (blockEntity instanceof class_2595) {
                           class_2595 chest = (class_2595)blockEntity;
                           int i = 0;

                           while(true) {
                              if (i >= chest.method_5439()) {
                                 break label66;
                              }

                              items.add(chest.method_5438(i));
                              ++i;
                           }
                        }

                        if (blockEntity instanceof class_2627) {
                           class_2627 shulker = (class_2627)blockEntity;
                           int i = 0;

                           while(true) {
                              if (i >= shulker.method_5439()) {
                                 break label66;
                              }

                              items.add(shulker.method_5438(i));
                              ++i;
                           }
                        }
                     }
                  }

                  Map<Color, Integer> foundItems = new HashMap();

                  for(class_1799 stack : items) {
                     if (!stack.method_7960()) {
                        String itemId = class_7923.field_41178.method_10221(stack.method_7909()).toString();
                        if (this.trackedItems.contains(itemId)) {
                           Color color = this.Kf9183ff757f420f160(stack);
                           foundItems.put(color, (Integer)foundItems.getOrDefault(color, 0) + stack.method_7947());
                        }
                     }
                  }

                  if (!foundItems.isEmpty()) {
                     this.containerCache.put(pos, new Kfb2eab2b78d7b89322(foundItems));
                  }
               }
            }
         }
      }

   }

   private void Kb7e507077b88a5b913() {
      for(Map.Entry<class_2338, Kfb2eab2b78d7b89322> entry : this.containerCache.entrySet()) {
         class_2338 pos = (class_2338)entry.getKey();
         Kfb2eab2b78d7b89322 cache = (Kfb2eab2b78d7b89322)entry.getValue();

         for(Map.Entry<Color, Integer> colorEntry : cache.foundItems.entrySet()) {
            Color color = (Color)colorEntry.getKey();
            int count = (Integer)colorEntry.getValue();
            int alpha = Math.min(200, 80 + count * 5);
            Color glowColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
            float x1 = (float)pos.method_10263();
            float y1 = (float)pos.method_10264();
            float z1 = (float)pos.method_10260();
            float x2 = x1 + 1.0F;
            float y2 = y1 + 1.0F;
            float z2 = z1 + 1.0F;
            this.boxRender.drawBox(x1, y1, z1, x2, y2, z2, 0.0F, glowColor, Kdbe29c088d9ae5b9a4.FILL, 0.0F);
            this.boxRender.drawBox(x1, y1, z1, x2, y2, z2, 1.5F, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255), Kdbe29c088d9ae5b9a4.OUTLINE, 0.0F);
         }
      }

      if (!this.containerCache.isEmpty()) {
         this.boxRender.setup3DRender(new class_4587());
      }

   }

   private Color Kf9183ff757f420f160(class_1799 stack) {
      String itemId = class_7923.field_41178.method_10221(stack.method_7909()).toString();
      if (itemId.contains("totem")) {
         return new Color(255, 215, 0, 200);
      } else if (itemId.contains("diamond")) {
         return new Color(0, 255, 255, 200);
      } else if (itemId.contains("emerald")) {
         return new Color(0, 255, 0, 200);
      } else if (itemId.contains("netherite")) {
         return new Color(128, 0, 128, 200);
      } else if (!itemId.contains("golden") && !itemId.contains("gold")) {
         if (itemId.contains("iron")) {
            return new Color(192, 192, 192, 200);
         } else {
            int hash = itemId.hashCode();
            float hue = (float)(hash & '\uffff') / 65535.0F;
            return new Color(Color.HSBtoRGB(hue, 0.8F, 1.0F));
         }
      } else {
         return new Color(255, 215, 0, 200);
      }
   }

   public void addItem(String itemId) {
      this.trackedItems.add(itemId);
      this.containerCache.clear();
      this.updateTicks = 20;
      if (mc.field_1724 != null) {
         mc.field_1724.method_7353(class_2561.method_43470("§a[ItemESP] §fДобавлен предмет: §e" + itemId), false);
      }

   }

   public void removeItem(String itemId) {
      if (this.trackedItems.remove(itemId)) {
         this.containerCache.clear();
         this.updateTicks = 20;
         if (mc.field_1724 != null) {
            mc.field_1724.method_7353(class_2561.method_43470("§a[ItemESP] §fУдален предмет: §e" + itemId), false);
         }
      } else if (mc.field_1724 != null) {
         mc.field_1724.method_7353(class_2561.method_43470("§c[ItemESP] §fПредмет не найден: §e" + itemId), false);
      }

   }

   public void clearItems() {
      int count = this.trackedItems.size();
      this.trackedItems.clear();
      this.containerCache.clear();
      if (mc.field_1724 != null) {
         mc.field_1724.method_7353(class_2561.method_43470("§a[ItemESP] §fОчищено предметов: §e" + count), false);
      }

   }

   public List<String> getItemSuggestions(String partial) {
      List<String> suggestions = new ArrayList();
      String lowerPartial = partial.toLowerCase();

      for(class_2960 id : class_7923.field_41178.method_10235()) {
         String itemId = id.toString();
         if (itemId.toLowerCase().contains(lowerPartial)) {
            suggestions.add(itemId);
         }
      }

      suggestions.sort((a, b) -> {
         boolean aStarts = a.toLowerCase().startsWith(lowerPartial);
         boolean bStarts = b.toLowerCase().startsWith(lowerPartial);
         if (aStarts && !bStarts) {
            return -1;
         } else {
            return !aStarts && bStarts ? 1 : a.compareTo(b);
         }
      });
      return suggestions.subList(0, Math.min(10, suggestions.size()));
   }

   @Generated
   public static K58d4d66f061a2a6fed getInstance() {
      return instance;
   }

   @Generated
   public Set<String> getTrackedItems() {
      return this.trackedItems;
   }
}
