package code.miaware.client.network;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.api.utils.other.Kf97c386dcb8076f4c4;
import code.miaware.client.config.K3e319beb26054fd921;
import code.miaware.client.network.packets.K1dfe4d4c8cf13a8c18;
import code.miaware.client.network.packets.K4467215b95d3f04c3e;
import code.miaware.client.network.packets.K5137b8284d9c113be5;
import code.miaware.client.network.packets.K955e6f774ae3184b17;
import code.miaware.client.network.packets.Kca25f92f3787dfa651;
import code.miaware.client.network.packets.Kdda021ad5ad35680c3;
import code.miaware.client.network.packets.Kee87453d84d791d553;
import code.miaware.client.network.packets.Kf15d2ca4bc95109ab8;
import code.miaware.client.ui.clickgui.ScreenIRCGui;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1109;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_5250;
import org.lwjgl.glfw.GLFW;

public class K3e5d28a93090bc4a75 {
   private static final K3e5d28a93090bc4a75 instance = new K3e5d28a93090bc4a75();
   private String serverHost = "2.26.230.83";
   private int serverPort = 25566;
   private String version = "1.0.0";
   private Socket socket;
   private BufferedReader reader;
   private OutputStream outputStream;
   private Thread receiveThread;
   private Thread pingThread;
   private boolean connected = false;
   private String username;
   private String uuid;
   private String persistentUUID;

   private K3e5d28a93090bc4a75() {
   }

   public static K3e5d28a93090bc4a75 getInstance() {
      return instance;
   }

   public boolean connect(String username, String uuid) {
      return false;
   }

   public void disconnect() {
      if (this.connected) {
         this.connected = false;

         try {
            if (this.outputStream != null && this.socket != null && !this.socket.isClosed()) {
               try {
                  Kf15d2ca4bc95109ab8 disconnectPacket = new Kf15d2ca4bc95109ab8(this.uuid);
                  String json = disconnectPacket.toJson().toString();
                  byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
                  this.outputStream.write(data);
                  this.outputStream.flush();
               } catch (Exception var7) {
               }
            }
         } catch (Exception var8) {
         }

         try {
            if (this.reader != null) {
               this.reader.close();
            }
         } catch (Exception var6) {
         }

         try {
            if (this.outputStream != null) {
               this.outputStream.close();
            }
         } catch (Exception var5) {
         }

         try {
            if (this.socket != null) {
               this.socket.close();
            }
         } catch (Exception var4) {
         }

         System.out.println("[IRC] Отключено от сервера");
      }
   }

   private void Kc210e75efbc8c1cc06(K1dfe4d4c8cf13a8c18 packet) {
      if (this.connected && this.outputStream != null) {
         try {
            String json = packet.toJson().toString();
            System.out.println("[IRC DEBUG] Отправляем JSON: " + json);
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            PrintStream var10000 = System.out;
            String var10001 = String.valueOf(packet.getType());
            var10000.println("[IRC] Отправлено: " + var10001 + " (" + data.length + " байт)");
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки пакета: " + e.getMessage());
            e.printStackTrace();
            this.disconnect();
         }

      } else {
         System.err.println("[IRC] Не подключён к серверу");
      }
   }

   public void sendGlobalMessage(String message) {
      if (!this.connected) {
         System.err.println("[IRC] Не подключён к серверу");
      } else {
         String displayName = ScreenIRCGui.getInstance().getDisplayName();
         String prefix = ScreenIRCGui.getInstance().getSelectedPrefix();
         K5137b8284d9c113be5 packet = new K5137b8284d9c113be5(this.uuid, displayName, message, prefix);
         this.Kc210e75efbc8c1cc06(packet);
      }
   }

   public void sendPrivateMessage(String toUsername, String message) {
      if (!this.connected) {
         System.err.println("[IRC] Не подключён к серверу");
      } else {
         String displayName = ScreenIRCGui.getInstance().getDisplayName();
         K8ee912418ef60c15b7.getInstance().addPrivateMessage(toUsername, message, true);
         Kdda021ad5ad35680c3 packet = new Kdda021ad5ad35680c3(this.uuid, displayName, toUsername, message);
         this.Kc210e75efbc8c1cc06(packet);
      }
   }

   public void requestPrivateHistory(String withUsername) {
      if (this.connected) {
         Kee87453d84d791d553 packet = new Kee87453d84d791d553(this.uuid, withUsername);
         this.Kc210e75efbc8c1cc06(packet);
         System.out.println("[IRC] Запрошена история с: " + withUsername);
      }
   }

   public void requestOnlineUsers() {
      if (this.connected) {
         K4467215b95d3f04c3e packet = new K4467215b95d3f04c3e(this.uuid);
         this.Kc210e75efbc8c1cc06(packet);
      }
   }

   public void requestIRCFriendsList() {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "GET_IRC_FRIENDS");
            packet.addProperty("uuid", this.uuid);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[IRC] Запрошен список IRC друзей");
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка запроса списка IRC друзей: " + e.getMessage());
         }

      }
   }

   public void sendFriendRequestResponse(String fromUuid, boolean accepted) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "FRIEND_REQUEST_RESPONSE");
            packet.addProperty("from_uuid", fromUuid);
            packet.addProperty("accepted", accepted);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[IRC] Отправлен ответ на запрос в друзья: " + (accepted ? "принят" : "отклонён"));
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки ответа: " + e.getMessage());
         }

      }
   }

   public void searchUsers(String query) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject json = new JsonObject();
            json.addProperty("type", "SEARCH_USERS");
            json.addProperty("uuid", this.uuid);
            json.addProperty("query", query);
            String jsonString = json.toString();
            System.out.println("[IRC DEBUG] Отправляем поиск: " + jsonString);
            this.outputStream.write((jsonString + "\n").getBytes(StandardCharsets.UTF_8));
            this.outputStream.flush();
            System.out.println("[IRC] Отправлен запрос поиска: " + query);
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки поиска: " + e.getMessage());
         }

      } else {
         System.out.println("[IRC] Нельзя искать: не подключен к серверу");
      }
   }

   public void checkTagUniqueness(String tag) {
      if (!this.connected) {
         System.err.println("[IRC] Не подключён к серверу");
         ScreenIRCGui.getInstance().onTagUniquenessResponse(tag, true);
      } else {
         Kca25f92f3787dfa651 packet = new Kca25f92f3787dfa651(this.uuid, tag);
         this.Kc210e75efbc8c1cc06(packet);
         System.out.println("[IRC] Отправлен запрос проверки уникальности тега: " + tag);
      }
   }

   public void sendAddFriendRequest(String input, boolean isUUID) {
      if (this.connected && this.outputStream != null) {
         System.out.println("[IRC] Отправка запроса на добавление друга: " + input + " (UUID: " + isUUID + ")");
         JsonObject packet = new JsonObject();
         packet.addProperty("type", "ADD_FRIEND");
         packet.addProperty("uuid", this.uuid);
         packet.addProperty("targetInput", input);
         packet.addProperty("isUUID", isUUID);

         try {
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[IRC] Запрос на добавление друга отправлен");
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки запроса: " + e.getMessage());
            e.printStackTrace();
         }

      } else {
         System.out.println("[IRC] Нельзя добавить друга: не подключен к серверу");
      }
   }

   public void sendAddIRCFriendRequest(String target) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "ADD_IRC_FRIEND");
            packet.addProperty("uuid", this.uuid);
            packet.addProperty("target", target);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[IRC] Запрос на добавление IRC друга отправлен: " + target);
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки запроса: " + e.getMessage());
         }

      } else {
         System.out.println("[IRC] Нельзя добавить IRC друга: не подключен к серверу");
      }
   }

   public void sendRemoveIRCFriendRequest(String target) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "REMOVE_IRC_FRIEND");
            packet.addProperty("uuid", this.uuid);
            packet.addProperty("target", target);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[IRC] Запрос на удаление IRC друга отправлен: " + target);
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка отправки запроса: " + e.getMessage());
         }

      } else {
         System.out.println("[IRC] Нельзя удалить IRC друга: не подключен к серверу");
      }
   }

   public void sendPing() {
      if (this.connected) {
         K955e6f774ae3184b17 packet = new K955e6f774ae3184b17(this.uuid, System.currentTimeMillis());
         this.Kc210e75efbc8c1cc06(packet);
      }
   }

   private void Kbaba70539a522363ae() {
      System.out.println("[IRC] Поток PING запущен");

      while(this.connected) {
         try {
            Thread.sleep(30000L);
            if (this.connected) {
               this.sendPing();
               System.out.println("[IRC] Отправлен автоматический PING");
            }
         } catch (InterruptedException var2) {
            break;
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка в PING потоке: " + e.getMessage());
         }
      }

      System.out.println("[IRC] Поток PING остановлен");
   }

   private void K5ac00499fed34db8b8() {
      System.out.println("[IRC] Поток приёма запущен");

      try {
         String line;
         while(this.connected && (line = this.reader.readLine()) != null) {
            System.out.println("[IRC DEBUG] Получена строка от сервера: " + line);

            try {
               JsonObject json = JsonParser.parseString(line).getAsJsonObject();
               String type = json.get("type").getAsString();
               this.Ka14b6bd5a615695738(type, json);
            } catch (Exception e) {
               System.err.println("[IRC] Ошибка обработки пакета: " + e.getMessage());
               e.printStackTrace();
            }
         }

         System.out.println("[IRC DEBUG] Цикл чтения завершён. connected=" + this.connected);
      } catch (IOException e) {
         if (this.connected) {
            System.err.println("[IRC] Соединение потеряно: " + e.getMessage());
            e.printStackTrace();
         }
      } finally {
         System.out.println("[IRC DEBUG] receiveLoop завершается, вызываем disconnect()");
         this.disconnect();
      }

      System.out.println("[IRC] Поток приёма остановлен");
   }

   private void Ka14b6bd5a615695738(String type, JsonObject json) {
      System.out.println("[IRC] Получен пакет: " + type);
      System.out.println("[IRC DEBUG] JSON: " + json.toString());
      switch (type) {
         case "CONNECT_SUCCESS":
            String message = json.get("message").getAsString();
            int onlineCount = json.get("online_count").getAsInt();
            System.out.println("[IRC] " + message + " Онлайн: " + onlineCount);
            ScreenIRCGui gui = ScreenIRCGui.getInstance();
            if (gui != null) {
               gui.resetTagCheckingFlag();
            }

            this.requestOnlineUsers();
            this.requestIRCFriendsList();
            break;
         case "CONNECT_ERROR":
            String error = json.get("error").getAsString();
            System.err.println("[IRC] Ошибка подключения: " + error);
            this.disconnect();
            break;
         case "GLOBAL_MESSAGE_BROADCAST":
            String senderUuid = json.get("uuid").getAsString();
            String senderUsername = json.get("username").getAsString();
            String message = json.get("message").getAsString();
            long timestamp = json.get("timestamp").getAsLong();
            String prefix = json.has("prefix") && !json.get("prefix").isJsonNull() ? json.get("prefix").getAsString() : null;
            K8ee912418ef60c15b7.getInstance().addGlobalMessage(senderUsername, message, senderUuid.equals(this.uuid), prefix);
            break;
         case "PRIVATE_MESSAGE_RECEIVED":
            String fromUuid = json.get("from_uuid").getAsString();
            String fromUsername = json.get("from_username").getAsString();
            String message = json.get("message").getAsString();
            long timestamp = json.get("timestamp").getAsLong();
            System.out.println("[IRC DEBUG] PRIVATE_MESSAGE_RECEIVED: fromUuid=" + fromUuid + ", myUuid=" + this.uuid + ", равны=" + fromUuid.equals(this.uuid));
            if (!fromUuid.equals(this.uuid)) {
               K8ee912418ef60c15b7.getInstance().addPrivateMessage(fromUsername, message, false);
               ScreenIRCGui gui = ScreenIRCGui.getInstance();
               String currentChat = gui != null ? gui.getCurrentPrivateChat() : null;
               System.out.println("[IRC DEBUG] PRIVATE_MESSAGE_RECEIVED от " + fromUsername + ", currentPrivateChat=" + currentChat);
               if (gui != null && fromUsername.equals(currentChat)) {
                  K8ee912418ef60c15b7.getInstance().markAsRead(fromUsername);
                  System.out.println("[IRC DEBUG] Диалог открыт, вызван markAsRead");
               } else {
                  System.out.println("[IRC DEBUG] Диалог НЕ открыт, markAsRead НЕ вызван, счётчик должен увеличиться");
               }

               try {
                  class_310 mc = class_310.method_1551();
                  if (mc != null && mc.method_1483() != null && gui != null && gui.isReceiveSoundEnabled()) {
                     float volume = gui.getIrcSoundVolume() * 20.0F;
                     mc.method_1483().method_4873(class_1109.method_4757(K7d074a66e20f0c1531.IRC_RECEIVE_EVENT, 1.0F, volume));
                  }
               } catch (Exception e) {
                  System.err.println("[IRC] Ошибка воспроизведения звука: " + e.getMessage());
               }

               if ((gui == null || !fromUsername.equals(currentChat)) && gui != null && gui.isWindowsNotificationsEnabled()) {
                  try {
                     K204c47a3480aa2d86b senderFriend = K8ee912418ef60c15b7.getInstance().getIRCFriendByUsername(fromUsername);
                     String displayName = senderFriend != null ? senderFriend.getDisplayName() : fromUsername;
                     Kf97c386dcb8076f4c4.sendNotification("IRC: " + displayName, message);
                  } catch (Exception e) {
                     System.err.println("[IRC] Ошибка отправки уведомления: " + e.getMessage());
                  }
               }
            }
            break;
         case "ONLINE_USERS_LIST":
            System.out.println("[IRC] Получен список онлайн игроков");
            break;
         case "USER_JOINED":
            String joinedUuid = json.get("uuid").getAsString();
            String joinedUsername = json.get("username").getAsString();
            int onlineCount = json.get("online_count").getAsInt();
            System.out.println("[IRC] " + joinedUsername + " присоединился. Онлайн: " + onlineCount);
            K8ee912418ef60c15b7.getInstance().updateFriendOnlineStatus(joinedUuid, true);
            break;
         case "USER_LEFT":
            String leftUuid = json.get("uuid").getAsString();
            String leftUsername = json.get("username").getAsString();
            int onlineCount = json.get("online_count").getAsInt();
            System.out.println("[IRC] " + leftUsername + " вышел. Онлайн: " + onlineCount);
            K8ee912418ef60c15b7.getInstance().updateFriendOnlineStatus(leftUuid, false);
         case "PONG":
            break;
         case "PRIVATE_HISTORY_RESPONSE":
            String withUsername = json.get("with_username").getAsString();
            JsonArray messages = json.getAsJsonArray("messages");
            System.out.println("[IRC] История с " + withUsername + ": " + messages.size() + " сообщений");
            K8ee912418ef60c15b7.getInstance().clearPrivateDialog(withUsername);

            for(JsonElement el : messages) {
               JsonObject m = el.getAsJsonObject();
               String fromUsername = m.get("from_username").getAsString();
               String msgText = m.get("message").getAsString();
               boolean isOwn = m.get("from_uuid").getAsString().equals(this.uuid);
               K8ee912418ef60c15b7.getInstance().addPrivateMessage(withUsername, msgText, isOwn);
            }

            K8ee912418ef60c15b7.getInstance().markAsRead(withUsername);
            System.out.println("[IRC] Счётчик непрочитанных обнулён для " + withUsername);
            break;
         case "TAG_UNIQUENESS_RESPONSE":
            String tag = json.get("tag").getAsString();
            boolean isUnique = json.get("isUnique").getAsBoolean();
            System.out.println("[IRC] Ответ о проверке тега " + tag + ": " + (isUnique ? "свободен" : "занят"));
            ScreenIRCGui gui = ScreenIRCGui.getInstance();
            if (gui != null) {
               gui.onTagUniquenessResponse(tag, isUnique);
            }
            break;
         case "SEARCH_USERS_RESPONSE":
            String query = json.get("query").getAsString();
            JsonArray results = json.getAsJsonArray("results");
            System.out.println("[IRC] Получены результаты поиска для '" + query + "': " + results.size() + " найдено");
            ScreenIRCGui gui2 = ScreenIRCGui.getInstance();
            if (gui2 != null) {
               List<String> usernames = new ArrayList();

               for(JsonElement el : results) {
                  JsonObject userObj = el.getAsJsonObject();
                  String username = userObj.get("username").getAsString();
                  usernames.add(username);
               }

               gui2.onSearchResults(query, usernames);
            }
            break;
         case "ADD_FRIEND_RESPONSE":
            boolean success = json.get("success").getAsBoolean();
            String message = json.get("message").getAsString();
            if (success) {
               String friendUsername = json.get("friendUsername").getAsString();
               System.out.println("[IRC] Друг добавлен: " + friendUsername);
               K137d4e93c7270b0a04.getInstance().add(friendUsername);
               class_310 mc = class_310.method_1551();
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§a[IRC] §fДруг добавлен: §e" + friendUsername), false);
               }
            } else {
               System.err.println("[IRC] Не удалось добавить друга: " + message);
               class_310 mc = class_310.method_1551();
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §f" + message), false);
               }
            }
            break;
         case "ADD_IRC_FRIEND_RESPONSE":
            boolean success = json.get("success").getAsBoolean();
            String message = json.get("message").getAsString();
            System.out.println("[IRC] ADD_IRC_FRIEND_RESPONSE получен: success=" + success + ", message=" + message);
            class_310 mc = class_310.method_1551();
            if (success) {
               String friendUuid = json.get("uuid").getAsString();
               String friendUsername = json.get("username").getAsString();
               System.out.println("[IRC] IRC друг добавлен: " + friendUsername + " (UUID: " + friendUuid + ")");
               K8ee912418ef60c15b7.getInstance().addIRCFriend(friendUuid, friendUsername, "", false);
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§a[IRC] §fIRC друг добавлен: §e" + friendUsername), false);
               }
            } else {
               System.err.println("[IRC] Не удалось добавить IRC друга: " + message);
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §f" + message), false);
               }
            }
            break;
         case "REMOVE_IRC_FRIEND_RESPONSE":
            boolean success = json.get("success").getAsBoolean();
            String message = json.get("message").getAsString();
            class_310 mc = class_310.method_1551();
            if (success) {
               String friendUuid = json.get("uuid").getAsString();
               System.out.println("[IRC] IRC друг удалён: UUID=" + friendUuid);
               K8ee912418ef60c15b7.getInstance().removeIRCFriend(friendUuid);
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§a[IRC] §fIRC друг удалён"), false);
               }
            } else {
               System.err.println("[IRC] Не удалось удалить IRC друга: " + message);
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §f" + message), false);
               }
            }
            break;
         case "IRC_FRIENDS_LIST":
            JsonArray friendsArray = json.getAsJsonArray("friends");
            List<String[]> friendsData = new ArrayList();

            for(JsonElement el : friendsArray) {
               if (el.isJsonArray()) {
                  JsonArray friendInfo = el.getAsJsonArray();
                  if (friendInfo.size() >= 4) {
                     String friendUuid = friendInfo.get(0).getAsString();
                     String username = friendInfo.get(1).getAsString();
                     String customTag = friendInfo.get(2).getAsString();
                     String onlineStr = friendInfo.get(3).getAsString();
                     friendsData.add(new String[]{friendUuid, username, customTag, onlineStr});
                  }
               }
            }

            K8ee912418ef60c15b7.getInstance().setIRCFriends(friendsData);
            System.out.println("[IRC] Получен список IRC друзей: " + friendsData.size());
            break;
         case "ERROR":
            String error = json.get("error").getAsString();
            int code = json.get("code").getAsInt();
            if (code == 403 && error.contains("мут")) {
               class_310 mc = class_310.method_1551();
               if (mc != null && mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fВы ограничены в чате. Приобретите Premium для доступа!"), false);
               }
            }

            System.err.println("[IRC] Ошибка [" + code + "]: " + error);
            break;
         case "FRIEND_REQUEST":
            String fromUuid = json.get("from_uuid").getAsString();
            String fromUsername = json.get("from_username").getAsString();
            System.out.println("[IRC] Получен запрос в друзья от: " + fromUsername);
            ScreenIRCGui gui = ScreenIRCGui.getInstance();
            if (gui != null) {
               gui.showFriendRequest(fromUuid, fromUsername);
            }
            break;
         case "SYSTEM_MESSAGE":
            String message = json.get("message").getAsString();
            System.out.println("[IRC SYSTEM] Raw message: " + message);
            class_310 mc = class_310.method_1551();
            if (mc != null && mc.field_1724 != null) {
               class_5250 text = class_2561.method_43473();
               text.method_10852(class_2561.method_43470(message));
               mc.field_1724.method_7353(text, false);
            }

            System.out.println("[IRC SYSTEM] " + message);
            break;
         case "CLOUD_KEY_GENERATED":
            final String key = json.get("key").getAsString();
            final String keyType = json.get("key_type").getAsString();
            System.out.println("[CloudConfig] Получен ключ: " + key + " (тип: " + keyType + ")");
            class_310.method_1551().execute(new Runnable() {
               public void run() {
                  try {
                     class_310 mc = class_310.method_1551();
                     long windowHandle = mc.method_22683().method_4490();
                     GLFW.glfwSetClipboardString(windowHandle, key);
                     System.out.println("[CloudConfig] Ключ скопирован в буфер обмена через GLFW: " + key);
                     if (mc.field_1724 != null) {
                        String keyTypeName = keyType.equals("one_time") ? "одноразовый" : "постоянный";
                        mc.field_1724.method_7353(class_2561.method_30163("§a[Cloud Config] §fКлюч (" + keyTypeName + ") скопирован: §e" + key), false);
                     }
                  } catch (Exception e) {
                     System.err.println("[CloudConfig] Ошибка копирования: " + e.getMessage());
                     e.printStackTrace();
                     class_310 mc = class_310.method_1551();
                     if (mc != null && mc.field_1724 != null) {
                        mc.field_1724.method_7353(class_2561.method_30163("§c[Cloud Config] §fОшибка копирования ключа!"), false);
                     }
                  }

               }
            });
            break;
         case "CLOUD_CONFIG_LOADED":
            JsonElement configElement = json.get("config");
            String configName = json.get("config_name").getAsString();
            String keyType = json.get("key_type").getAsString();
            System.out.println("[CloudConfig] Получен конфиг: " + configName + " (тип ключа: " + keyType + ")");

            try {
               JsonObject configJson;
               if (configElement.isJsonObject()) {
                  configJson = configElement.getAsJsonObject();
               } else {
                  if (!configElement.isJsonPrimitive()) {
                     throw new Exception("Неверный формат конфига");
                  }

                  configJson = JsonParser.parseString(configElement.getAsString()).getAsJsonObject();
               }

               boolean applied = K3e319beb26054fd921.getInstance().applyConfig(configJson, configName);
               if (applied) {
                  System.out.println("[CloudConfig] Конфиг успешно применён!");
                  class_310.method_1551().execute(() -> {
                     class_310 mc = class_310.method_1551();
                     if (mc != null && mc.field_1724 != null) {
                        String keyTypeName = keyType.equals("one_time") ? "одноразовый" : "постоянный";
                        mc.field_1724.method_7353(class_2561.method_30163("§a[Cloud Config] §fКонфиг загружен: §e" + configName + " §7(" + keyTypeName + ")"), false);
                     }

                  });
               } else {
                  System.err.println("[CloudConfig] Ошибка применения конфига");
                  class_310.method_1551().execute(() -> {
                     class_310 mc = class_310.method_1551();
                     if (mc != null && mc.field_1724 != null) {
                        mc.field_1724.method_7353(class_2561.method_30163("§c[Cloud Config] §fОшибка применения конфига!"), false);
                     }

                  });
               }
            } catch (Exception e) {
               System.err.println("[CloudConfig] Ошибка обработки конфига: " + e.getMessage());
               e.printStackTrace();
               class_310.method_1551().execute(() -> {
                  class_310 mc = class_310.method_1551();
                  if (mc != null && mc.field_1724 != null) {
                     mc.field_1724.method_7353(class_2561.method_30163("§c[Cloud Config] §fОшибка: " + e.getMessage()), false);
                  }

               });
            }
            break;
         default:
            System.err.println("[IRC] Неизвестный тип пакета: " + type);
      }

   }

   public boolean isConnected() {
      return this.connected;
   }

   public String getUsername() {
      return this.username;
   }

   public String getServerHost() {
      return this.serverHost;
   }

   public void setServerHost(String serverHost) {
      this.serverHost = serverHost;
   }

   public int getServerPort() {
      return this.serverPort;
   }

   public void setServerPort(int serverPort) {
      this.serverPort = serverPort;
   }

   public String getPersistentUUID() {
      if (this.persistentUUID != null) {
         return this.persistentUUID;
      } else {
         File configDir = new File(class_310.method_1551().field_1697, ".irc");
         if (!configDir.exists()) {
            configDir.mkdirs();
         }

         File uuidFile = new File(configDir, "client_uuid.txt");
         if (uuidFile.exists()) {
            try {
               this.persistentUUID = (new String(Files.readAllBytes(uuidFile.toPath()), StandardCharsets.UTF_8)).trim();
               System.out.println("[IRC] Загружен постоянный UUID: " + this.persistentUUID);
               return this.persistentUUID;
            } catch (Exception e) {
               System.err.println("[IRC] Ошибка загрузки UUID: " + e.getMessage());
            }
         }

         this.persistentUUID = UUID.randomUUID().toString();

         try {
            Files.write(uuidFile.toPath(), this.persistentUUID.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
            System.out.println("[IRC] Создан новый постоянный UUID: " + this.persistentUUID);
         } catch (Exception e) {
            System.err.println("[IRC] Ошибка сохранения UUID: " + e.getMessage());
         }

         return this.persistentUUID;
      }
   }

   public void saveConfigOneTime(String configName, String configData) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "CLOUD_SAVE_CONFIG_ONE_TIME");
            packet.addProperty("config_name", configName);
            packet.addProperty("config", configData);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[CloudConfig] Запрос одноразового ключа для: " + configName);
         } catch (Exception e) {
            System.err.println("[CloudConfig] Ошибка отправки: " + e.getMessage());
         }

      } else {
         System.err.println("[CloudConfig] Не подключён к серверу");
      }
   }

   public void saveConfigPermanent(String configName, String configData) {
      if (this.connected && this.outputStream != null) {
         try {
            JsonObject packet = new JsonObject();
            packet.addProperty("type", "CLOUD_SAVE_CONFIG_PERMANENT");
            packet.addProperty("config_name", configName);
            packet.addProperty("config", configData);
            String json = packet.toString();
            byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
            this.outputStream.write(data);
            this.outputStream.flush();
            System.out.println("[CloudConfig] Запрос постоянного ключа для: " + configName);
         } catch (Exception e) {
            System.err.println("[CloudConfig] Ошибка отправки: " + e.getMessage());
         }

      } else {
         System.err.println("[CloudConfig] Не подключён к серверу");
      }
   }

   public void loadConfigByKey(String key) {
      if (!this.connected || this.outputStream == null) {
         System.out.println("[CloudConfig] Не подключён к серверу, автоматическое подключение...");
         class_310 mc = class_310.method_1551();
         if (mc.method_1548() == null) {
            System.err.println("[CloudConfig] Ошибка: нет сессии Minecraft");
            return;
         }

         String username = mc.method_1548().method_1676();
         String uuid = this.getPersistentUUID();
         boolean connected = this.connect(username, uuid);
         if (!connected) {
            System.err.println("[CloudConfig] Не удалось подключиться к серверу");
            if (mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_30163("§c[Cloud Config] Не удалось подключиться к серверу"), false);
            }

            return;
         }

         try {
            Thread.sleep(500L);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

         System.out.println("[CloudConfig] Подключено, отправляем запрос...");
      }

      try {
         JsonObject packet = new JsonObject();
         packet.addProperty("type", "CLOUD_LOAD_CONFIG");
         packet.addProperty("key", key);
         String json = packet.toString();
         byte[] data = (json + "\n").getBytes(StandardCharsets.UTF_8);
         this.outputStream.write(data);
         this.outputStream.flush();
         System.out.println("[CloudConfig] Запрос загрузки конфига по ключу: " + key);
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка отправки: " + e.getMessage());
      }

   }
}
