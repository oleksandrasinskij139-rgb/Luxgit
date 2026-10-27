package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K756e8fdd34195c2119;
import code.miaware.client.ui.widget.overlay.K5ffb65634b086e35c7;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_2868;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_304;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_490;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

@ModuleRegister(
   name = "RWHelper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматическое использование ReallyWorld предметов"
)
public class K6f46e31d3de2b9b245 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K6f46e31d3de2b9b245 instance = new K6f46e31d3de2b9b245();
   private final K00fef91b3d4d1b3ecc autoSwap = (new K00fef91b3d4d1b3ecc("Авто перемещение в хотбар")).value(true);
   private final K00fef91b3d4d1b3ecc shortDelay = (new K00fef91b3d4d1b3ecc("Длинная задержка")).value(true);
   private final K00fef91b3d4d1b3ecc rwCooldowns = (new K00fef91b3d4d1b3ecc("RWCooldowns")).value(false);
   private final K1d3d0b4020f933282f antiFlightKey = (new K1d3d0b4020f933282f("Анти Полет")).value(-1);
   private final K1d3d0b4020f933282f trapKey = (new K1d3d0b4020f933282f("Ловушка")).value(-1);
   private final List<K365e6c464b3c40a3f3> abilities = new ArrayList();
   private final K5ffb65634b086e35c7 cooldownsWidget = new K5ffb65634b086e35c7();
   private K462e9c6ddbbb087522 swapState;
   private long lastStateChange;
   private int targetInvSlot;
   private int savedHotbarItem;
   private int savedSelectedSlot;
   private K365e6c464b3c40a3f3 pendingAbility;
   private final Map<Integer, Boolean> keyStates;
   private final Map<String, Long> lastUseTime;
   private static final long ANTI_FLIGHT_COOLDOWN_MS = 15000L;
   private static final long TRAP_COOLDOWN_MS = 50000L;

   public K6f46e31d3de2b9b245() {
      this.swapState = K462e9c6ddbbb087522.IDLE;
      this.lastStateChange = 0L;
      this.targetInvSlot = -1;
      this.savedHotbarItem = -1;
      this.savedSelectedSlot = -1;
      this.pendingAbility = null;
      this.keyStates = new HashMap();
      this.lastUseTime = new HashMap();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.autoSwap, this.shortDelay, this.rwCooldowns, this.antiFlightKey, this.trapKey});
      this.abilities.add(new K365e6c464b3c40a3f3("анти полет", class_1802.field_8153, this.antiFlightKey, "Анти Полет не найден!", "Заюзал Анти Полет!"));
      this.abilities.add(new K365e6c464b3c40a3f3("ловушка", class_1802.field_8786, this.trapKey, "Ловушка не найдена!", "Заюзал Ловушку!"));
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if (mc.field_1755 == null) {
               ;
            }
         }
      }));
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if (mc.field_1755 == null) {
               if (this.swapState != K462e9c6ddbbb087522.IDLE) {
                  this.K04851c4a3ad3f45710();
               } else {
                  for(K365e6c464b3c40a3f3 ability : this.abilities) {
                     int key = (Integer)ability.getBindSetting().getValue();
                     if (key != -1) {
                        boolean isPressed = GLFW.glfwGetKey(mc.method_22683().method_4490(), key) == 1;
                        boolean wasPressed = (Boolean)this.keyStates.getOrDefault(key, false);
                        if (wasPressed && !isPressed) {
                           ability.queueUse();
                        }

                        this.keyStates.put(key, isPressed);
                     }
                  }

                  for(K365e6c464b3c40a3f3 ability : this.abilities) {
                     if (ability.consumeQueuedUse()) {
                        this.Kf16c6347d6b2149fc9(ability);
                        break;
                     }
                  }

               }
            }
         }
      }));
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.rwCooldowns.getValue()) {
            this.cooldownsWidget.render(event.matrixStack());
         }

      }));
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (mc.field_1755 == null) {
               for(K365e6c464b3c40a3f3 ability : this.abilities) {
                  int key = (Integer)ability.getBindSetting().getValue();
                  if (key != -1 && GLFW.glfwGetKey(mc.method_22683().method_4490(), key) == 1) {
                     this.K2d26b70bae1aad0113(event.matrixStack(), event.cameraPos(), ability);
                  }
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, updateEvent, render2DEvent, render3DEvent});
   }

   public void onDisable() {
      for(K365e6c464b3c40a3f3 ability : this.abilities) {
         ability.reset();
      }

      this.swapState = K462e9c6ddbbb087522.IDLE;
      super.onDisable();
   }

   private void K04851c4a3ad3f45710() {
      long currentTime = System.currentTimeMillis();
      int openDelay = (Boolean)this.shortDelay.getValue() ? 150 : 100;
      int swapDelay = (Boolean)this.shortDelay.getValue() ? 150 : 100;
      int useDelay = (Boolean)this.shortDelay.getValue() ? 250 : 150;
      int restoreDelay = (Boolean)this.shortDelay.getValue() ? 150 : 100;
      int closeDelay = (Boolean)this.shortDelay.getValue() ? 150 : 100;
      switch (this.swapState.ordinal()) {
         case 1:
            mc.method_1507(new class_490(mc.field_1724));
            this.swapState = K462e9c6ddbbb087522.WAIT_INVENTORY_OPEN;
            this.lastStateChange = currentTime;
            break;
         case 2:
            if (currentTime - this.lastStateChange >= (long)openDelay) {
               this.swapState = K462e9c6ddbbb087522.SWAP_TO_HOTBAR;
               this.lastStateChange = currentTime;
            }
            break;
         case 3:
            this.savedHotbarItem = 8;
            this.Ke39fb1b91b2bbeb2de(this.targetInvSlot, 8);
            this.swapState = K462e9c6ddbbb087522.CLOSE_AND_USE;
            this.lastStateChange = currentTime;
            break;
         case 4:
            if (currentTime - this.lastStateChange >= (long)swapDelay) {
               mc.method_1507((class_437)null);
               this.savedSelectedSlot = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = 8;
               mc.method_1583();
               if (this.pendingAbility != null) {
                  this.print(this.pendingAbility.getSuccessMessage());
                  this.lastUseTime.put(this.pendingAbility.getInventoryKeyword(), System.currentTimeMillis());
                  this.cooldownsWidget.registerItemUse(this.pendingAbility.getInventoryKeyword());
               }

               this.swapState = K462e9c6ddbbb087522.SWAP_BACK;
               this.lastStateChange = currentTime;
            }
            break;
         case 5:
            if (currentTime - this.lastStateChange >= (long)useDelay) {
               mc.method_1507(new class_490(mc.field_1724));
               this.swapState = K462e9c6ddbbb087522.RESTORE_SLOT;
               this.lastStateChange = currentTime;
            }
            break;
         case 6:
            if (currentTime - this.lastStateChange >= (long)restoreDelay) {
               this.Ke39fb1b91b2bbeb2de(8, this.targetInvSlot);
               mc.execute(() -> {
                  try {
                     Thread.sleep((long)closeDelay);
                     mc.method_1507((class_437)null);
                     if (this.savedSelectedSlot != -1) {
                        mc.field_1724.method_31548().field_7545 = this.savedSelectedSlot;
                     }
                  } catch (Exception e) {
                     e.printStackTrace();
                  }

               });
               this.swapState = K462e9c6ddbbb087522.IDLE;
               this.targetInvSlot = -1;
               this.savedHotbarItem = -1;
               this.savedSelectedSlot = -1;
               this.pendingAbility = null;
            }
      }

   }

   private void Kf16c6347d6b2149fc9(K365e6c464b3c40a3f3 ability) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         long cooldownMs = ability.getInventoryKeyword().equals("анти полет") ? 15000L : 50000L;
         Long lastUse = (Long)this.lastUseTime.get(ability.getInventoryKeyword());
         if (lastUse != null) {
            long elapsed = System.currentTimeMillis() - lastUse;
            if (elapsed < cooldownMs) {
               long remainingSeconds = (cooldownMs - elapsed) / 1000L;
               this.print("Кулдаун! Осталось: " + remainingSeconds + "s");
               return;
            }
         }

         if (!mc.field_1724.method_7357().method_7904(ability.getItem().method_7854())) {
            if (ability.getInventoryKeyword().equals("анти полет")) {
               this.K9ffc5d33cc9ae59acb();
            } else {
               int hotbarSlot = this.K5c868b9561805cbe2f(ability.getInventoryKeyword());
               if (hotbarSlot != -1) {
                  this.Kf26120f8e912295e09(hotbarSlot);
                  this.print(ability.getSuccessMessage());
                  this.lastUseTime.put(ability.getInventoryKeyword(), System.currentTimeMillis());
                  this.cooldownsWidget.registerItemUse(ability.getInventoryKeyword());
               } else {
                  int invSlot = this.Kac5afee75849744135(ability.getInventoryKeyword());
                  if (invSlot == -1) {
                     this.print(ability.getNotFoundMessage());
                  } else {
                     if ((Boolean)this.autoSwap.getValue()) {
                        this.targetInvSlot = invSlot;
                        this.pendingAbility = ability;
                        this.swapState = K462e9c6ddbbb087522.OPEN_INVENTORY;
                        this.lastStateChange = System.currentTimeMillis();
                     } else {
                        this.print(ability.getNotFoundMessage());
                     }

                  }
               }
            }
         }
      }
   }

   private void K9ffc5d33cc9ae59acb() {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         String keyword = "анти полет";
         int hotbarSlot = this.K5c868b9561805cbe2f(keyword);
         if (hotbarSlot != -1) {
            this.Kf6a1ea30196c12bd49(hotbarSlot);
         } else {
            int invSlot = this.Kac5afee75849744135(keyword);
            if (invSlot == -1) {
               this.print("Анти Полет не найден!");
            } else {
               this.K9c72680f74616a1184(invSlot);
            }
         }
      }
   }

   private void Kf6a1ea30196c12bd49(int hotbarSlot) {
      (new Thread(() -> {
         try {
            int originalSlot = mc.field_1724.method_31548().field_7545;
            mc.execute(() -> {
               K756e8fdd34195c2119.sendPacket(new class_2868(hotbarSlot));
               mc.field_1724.method_31548().field_7545 = hotbarSlot;
            });
            Thread.sleep(100L);
            mc.execute(() -> {
               if (mc.field_1761 != null) {
                  int syncId = mc.field_1724.field_7512.field_7763;
                  mc.field_1761.method_2906(syncId, 36 + hotbarSlot, 40, class_1713.field_7791, mc.field_1724);
               }

            });
            Thread.sleep(150L);
            mc.execute(() -> this.K64e56d91586e6dd0d3());
            Thread.sleep(200L);
            mc.execute(() -> {
               if (mc.field_1761 != null) {
                  int syncId = mc.field_1724.field_7512.field_7763;
                  mc.field_1761.method_2906(syncId, 36 + hotbarSlot, 40, class_1713.field_7791, mc.field_1724);
               }

            });
            Thread.sleep(100L);
            mc.execute(() -> {
               if (mc.field_1724 != null) {
                  K756e8fdd34195c2119.sendPacket(new class_2868(originalSlot));
                  mc.field_1724.method_31548().field_7545 = originalSlot;
               }

               this.print("Заюзал Анти Полет!");
               this.lastUseTime.put("анти полет", System.currentTimeMillis());
               this.cooldownsWidget.registerItemUse("анти полет");
            });
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      })).start();
   }

   private void K9c72680f74616a1184(int invSlot) {
      (new Thread(() -> {
         try {
            mc.execute(() -> mc.method_1507(new class_490(mc.field_1724)));
            Thread.sleep(100L);
            mc.field_1761.method_2906(0, invSlot, 0, class_1713.field_7790, mc.field_1724);
            mc.field_1761.method_2906(0, 45, 0, class_1713.field_7790, mc.field_1724);
            Thread.sleep(100L);
            mc.execute(() -> mc.method_1507((class_437)null));
            Thread.sleep(150L);
            mc.execute(() -> this.K64e56d91586e6dd0d3());
            Thread.sleep(200L);
            mc.execute(() -> mc.method_1507(new class_490(mc.field_1724)));
            Thread.sleep(100L);
            mc.field_1761.method_2906(0, 45, 0, class_1713.field_7790, mc.field_1724);
            mc.field_1761.method_2906(0, invSlot, 0, class_1713.field_7790, mc.field_1724);
            Thread.sleep(100L);
            mc.execute(() -> {
               mc.method_1507((class_437)null);
               this.print("Заюзал Анти Полет!");
               this.lastUseTime.put("анти полет", System.currentTimeMillis());
               this.cooldownsWidget.registerItemUse("анти полет");
            });
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      })).start();
   }

   private void K64e56d91586e6dd0d3() {
      if (mc.field_1724 != null && mc.field_1690 != null) {
         class_304 sneakKey = mc.field_1690.field_1832;
         sneakKey.method_23481(true);
         (new Thread(() -> {
            try {
               Thread.sleep(100L);
               mc.execute(() -> {
                  if (mc.field_1690 != null) {
                     sneakKey.method_23481(false);
                  }

               });
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         })).start();
      }
   }

   private int K5c868b9561805cbe2f(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  if (this.K769356e783909d6b93(lowerName, keyword)) {
                     System.out.println("[RWHelper] Найден предмет в хотбаре: '" + displayName + "' для keyword: '" + keyword + "'");
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }

   private int Kac5afee75849744135(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  if (this.K769356e783909d6b93(lowerName, keyword)) {
                     System.out.println("[RWHelper] Найден предмет в инвентаре: '" + displayName + "' для keyword: '" + keyword + "'");
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }

   private boolean K769356e783909d6b93(String displayName, String keyword) {
      return displayName.contains(keyword);
   }

   private int Kff17fac52a63660c95() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960()) {
               return i;
            }
         }

         return -1;
      }
   }

   private void Ke39fb1b91b2bbeb2de(int fromSlot, int toSlot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int fromPacketSlot = fromSlot < 9 ? fromSlot + 36 : fromSlot;
         int toPacketSlot = toSlot < 9 ? toSlot + 36 : toSlot;
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, toPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   private void Kf26120f8e912295e09(int slot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         this.savedSelectedSlot = mc.field_1724.method_31548().field_7545;
         mc.field_1724.method_31548().field_7545 = slot;
         mc.method_1583();
         (new Thread(() -> {
            try {
               Thread.sleep(100L);
               if (mc.field_1724 != null && this.savedSelectedSlot != -1) {
                  mc.field_1724.method_31548().field_7545 = this.savedSelectedSlot;
               }
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         })).start();
      }
   }

   private void K2d26b70bae1aad0113(class_4587 matrices, class_243 cameraPos, K365e6c464b3c40a3f3 ability) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_243 playerPos = mc.field_1724.method_19538();
         boolean isAntiFlight = ability.getInventoryKeyword().equals("анти полет");
         double minX;
         double maxX;
         double minZ;
         double maxZ;
         double minY;
         double maxY;
         if (isAntiFlight) {
            class_1923 chunkPos = new class_1923(class_2338.method_49638(playerPos));
            minX = (double)chunkPos.method_8326();
            maxX = (double)(chunkPos.method_8326() + 16);
            minZ = (double)chunkPos.method_8328();
            maxZ = (double)(chunkPos.method_8328() + 16);
            minY = Math.floor(playerPos.field_1351);
            maxY = minY + 0.05;
         } else {
            if (!ability.getInventoryKeyword().equals("ловушка")) {
               return;
            }

            minX = playerPos.field_1352 - (double)3.0F;
            maxX = playerPos.field_1352 + (double)3.0F;
            minZ = playerPos.field_1350 - (double)3.0F;
            maxZ = playerPos.field_1350 + (double)3.0F;
            minY = playerPos.field_1351;
            maxY = minY + (double)3.0F;
         }

         matrices.method_22903();
         matrices.method_22904(-cameraPos.field_1352, -cameraPos.field_1351, -cameraPos.field_1350);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.disableCull();
         class_289 tessellator = class_289.method_1348();
         Matrix4f matrix = matrices.method_23760().method_23761();
         boolean hasPlayersInZone = false;

         for(class_1657 player : mc.field_1687.method_18456()) {
            if (player != mc.field_1724) {
               class_243 pos = player.method_19538();
               boolean inZone;
               if (isAntiFlight) {
                  inZone = pos.field_1352 >= minX && pos.field_1352 < maxX && pos.field_1350 >= minZ && pos.field_1350 < maxZ;
               } else {
                  class_238 playerBox = player.method_5829();
                  boolean intersectsX = playerBox.field_1320 >= minX && playerBox.field_1323 <= maxX;
                  boolean intersectsZ = playerBox.field_1324 >= minZ && playerBox.field_1321 <= maxZ;
                  boolean intersectsY = playerBox.field_1325 >= minY && playerBox.field_1322 <= maxY;
                  inZone = intersectsX && intersectsZ && intersectsY;
               }

               if (inZone) {
                  hasPlayersInZone = true;
                  PrintStream var10000 = System.out;
                  String var10001 = player.method_5477().getString();
                  var10000.println("[RWHelper] Игрок в зоне: " + var10001 + " pos: " + String.valueOf(pos));
                  break;
               }
            }
         }

         class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         float floorR;
         float floorG;
         float floorB;
         if (hasPlayersInZone) {
            floorR = 0.2F;
            floorG = 1.0F;
            floorB = 0.3F;
         } else {
            floorR = 1.0F;
            floorG = 0.2F;
            floorB = 0.2F;
         }

         float floorA = 0.25F;
         buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ).method_22915(floorR, floorG, floorB, floorA);
         buffer.method_22918(matrix, (float)maxX, (float)minY, (float)minZ).method_22915(floorR, floorG, floorB, floorA);
         buffer.method_22918(matrix, (float)maxX, (float)minY, (float)maxZ).method_22915(floorR, floorG, floorB, floorA);
         buffer.method_22918(matrix, (float)minX, (float)minY, (float)maxZ).method_22915(floorR, floorG, floorB, floorA);
         class_286.method_43433(buffer.method_60800());
         float whiteR = 1.0F;
         float whiteG = 1.0F;
         float whiteB = 1.0F;
         float glowAlpha1 = 0.6F;
         float glowAlpha2 = 0.3F;
         float glowAlpha3 = 0.15F;
         float lineWidth = 0.08F;

         for(int pass = 0; pass < 3; ++pass) {
            buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
            float width = lineWidth * (1.0F + (float)pass * 0.5F);
            float alpha = pass == 0 ? glowAlpha1 : (pass == 1 ? glowAlpha2 : glowAlpha3);
            buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX, (float)minY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX, (float)minY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX, (float)minY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX, (float)minY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX, (float)minY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX, (float)minY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX - width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX + width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX + width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)minX - width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX - width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX + width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX + width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
            buffer.method_22918(matrix, (float)maxX - width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
            class_286.method_43433(buffer.method_60800());
         }

         if (!isAntiFlight) {
            for(int pass = 0; pass < 3; ++pass) {
               buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
               float width = lineWidth * (1.0F + (float)pass * 0.5F);
               float alpha = pass == 0 ? glowAlpha1 : (pass == 1 ? glowAlpha2 : glowAlpha3);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX - width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX - width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               class_286.method_43433(buffer.method_60800());
            }

            float cornerLineWidth = 0.06F;

            for(int pass = 0; pass < 3; ++pass) {
               buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
               float width = cornerLineWidth * (1.0F + (float)pass * 0.5F);
               float alpha = pass == 0 ? glowAlpha1 : (pass == 1 ? glowAlpha2 : glowAlpha3);
               buffer.method_22918(matrix, (float)minX - width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX - width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)minY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)maxY, (float)minZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)minY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)minY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)minZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)minZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX + width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX - width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)minY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)minY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX - width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)minY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX + width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX - width, (float)maxY, (float)maxZ).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)minY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)minY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)maxZ + width).method_22915(whiteR, whiteG, whiteB, alpha);
               buffer.method_22918(matrix, (float)minX, (float)maxY, (float)maxZ - width).method_22915(whiteR, whiteG, whiteB, alpha);
               class_286.method_43433(buffer.method_60800());
            }
         } else {
            double cornerHeight = (double)12.0F;
            int segments = 20;
            float cornerLineWidth = 0.06F;
            double[][] corners = new double[][]{{minX, minZ}, {maxX, minZ}, {maxX, maxZ}, {minX, maxZ}};

            for(int pass = 0; pass < 3; ++pass) {
               buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
               float width = cornerLineWidth * (1.0F + (float)pass * 0.5F);

               for(double[] corner : corners) {
                  double x = corner[0];
                  double z = corner[1];

                  for(int i = 0; i < segments; ++i) {
                     double y1 = minY + cornerHeight / (double)segments * (double)i;
                     double y2 = minY + cornerHeight / (double)segments * (double)(i + 1);
                     float baseFade1 = 1.0F - (float)i / (float)segments;
                     float baseFade2 = 1.0F - (float)(i + 1) / (float)segments;
                     float passAlpha = pass == 0 ? glowAlpha1 : (pass == 1 ? glowAlpha2 : glowAlpha3);
                     float alpha1 = passAlpha * baseFade1;
                     float alpha2 = passAlpha * baseFade2;
                     buffer.method_22918(matrix, (float)(x - (double)width), (float)y1, (float)z).method_22915(whiteR, whiteG, whiteB, alpha1);
                     buffer.method_22918(matrix, (float)(x + (double)width), (float)y1, (float)z).method_22915(whiteR, whiteG, whiteB, alpha1);
                     buffer.method_22918(matrix, (float)(x + (double)width), (float)y2, (float)z).method_22915(whiteR, whiteG, whiteB, alpha2);
                     buffer.method_22918(matrix, (float)(x - (double)width), (float)y2, (float)z).method_22915(whiteR, whiteG, whiteB, alpha2);
                     buffer.method_22918(matrix, (float)x, (float)y1, (float)(z - (double)width)).method_22915(whiteR, whiteG, whiteB, alpha1);
                     buffer.method_22918(matrix, (float)x, (float)y1, (float)(z + (double)width)).method_22915(whiteR, whiteG, whiteB, alpha1);
                     buffer.method_22918(matrix, (float)x, (float)y2, (float)(z + (double)width)).method_22915(whiteR, whiteG, whiteB, alpha2);
                     buffer.method_22918(matrix, (float)x, (float)y2, (float)(z - (double)width)).method_22915(whiteR, whiteG, whiteB, alpha2);
                  }
               }

               class_286.method_43433(buffer.method_60800());
            }
         }

         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         matrices.method_22909();
      }
   }

   @Generated
   public static K6f46e31d3de2b9b245 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getRwCooldowns() {
      return this.rwCooldowns;
   }
}
