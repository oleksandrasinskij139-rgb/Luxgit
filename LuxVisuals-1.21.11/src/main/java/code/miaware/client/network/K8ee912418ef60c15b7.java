package code.miaware.client.network;

import code.miaware.client.ui.clickgui.K8001222dc0dc25d83d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class K8ee912418ef60c15b7 {
   private static final K8ee912418ef60c15b7 instance = new K8ee912418ef60c15b7();
   private final List<K8001222dc0dc25d83d> globalMessages = new ArrayList();
   private final Map<String, List<K8001222dc0dc25d83d>> privateMessages = new HashMap();
   private final Map<String, Integer> unreadCount = new HashMap();
   private final List<K204c47a3480aa2d86b> ircFriends = new ArrayList();

   private K8ee912418ef60c15b7() {
   }

   public static K8ee912418ef60c15b7 getInstance() {
      return instance;
   }

   public void addGlobalMessage(String username, String message, boolean isOwnMessage, String prefix) {
      K8001222dc0dc25d83d chatMessage = new K8001222dc0dc25d83d(username, message, isOwnMessage, true, prefix);
      synchronized(this.globalMessages) {
         this.globalMessages.add(chatMessage);
         if (this.globalMessages.size() > 100) {
            this.globalMessages.remove(0);
         }
      }

      System.out.println("[IRC Manager] Global: " + (prefix != null ? prefix + " " : "") + username + ": " + message);
   }

   public void addGlobalMessage(String username, String message, boolean isOwnMessage) {
      this.addGlobalMessage(username, message, isOwnMessage, (String)null);
   }

   public void addPrivateMessage(String username, String message, boolean isOwnMessage) {
      synchronized(this.privateMessages) {
         String normalizedUsername = username.trim();
         List<K8001222dc0dc25d83d> dialog = (List)this.privateMessages.computeIfAbsent(normalizedUsername, (k) -> new ArrayList());
         K8001222dc0dc25d83d chatMessage = new K8001222dc0dc25d83d(normalizedUsername, message, isOwnMessage, true);
         dialog.add(chatMessage);
         if (dialog.size() > 50) {
            dialog.remove(0);
         }

         if (!isOwnMessage) {
            int newCount = (Integer)this.unreadCount.getOrDefault(normalizedUsername, 0) + 1;
            this.unreadCount.put(normalizedUsername, newCount);
         }

      }
   }

   public List<K8001222dc0dc25d83d> getGlobalMessages() {
      synchronized(this.globalMessages) {
         return new ArrayList(this.globalMessages);
      }
   }

   public List<K8001222dc0dc25d83d> getPrivateMessages(String username) {
      synchronized(this.privateMessages) {
         return new ArrayList((Collection)this.privateMessages.getOrDefault(username, new ArrayList()));
      }
   }

   public List<String> getDialogUsers() {
      synchronized(this.privateMessages) {
         return new ArrayList(this.privateMessages.keySet());
      }
   }

   public void createDialog(String username) {
      synchronized(this.privateMessages) {
         if (!this.privateMessages.containsKey(username)) {
            this.privateMessages.put(username, new ArrayList());
            System.out.println("[IRC Manager] Создан пустой диалог с: " + username);
         }

      }
   }

   public void clearPrivateDialog(String username) {
      synchronized(this.privateMessages) {
         this.privateMessages.remove(username);
         this.unreadCount.remove(username);
      }
   }

   public int getUnreadCount(String username) {
      synchronized(this.privateMessages) {
         return (Integer)this.unreadCount.getOrDefault(username, 0);
      }
   }

   public void markAsRead(String username) {
      synchronized(this.privateMessages) {
         int oldCount = (Integer)this.unreadCount.getOrDefault(username, 0);
         this.unreadCount.put(username, 0);
         System.out.println("[IRC Manager] markAsRead: " + username + " (было: " + oldCount + ", стало: 0)");
      }
   }

   public void clear() {
      synchronized(this.globalMessages) {
         this.globalMessages.clear();
      }

      synchronized(this.privateMessages) {
         this.privateMessages.clear();
         this.unreadCount.clear();
      }

      System.out.println("[IRC Manager] Все сообщения очищены");
   }

   public List<K204c47a3480aa2d86b> getIRCFriends() {
      synchronized(this.ircFriends) {
         return new ArrayList(this.ircFriends);
      }
   }

   public void addIRCFriend(String uuid, String username, String customTag, boolean online) {
      synchronized(this.ircFriends) {
         boolean exists = false;

         for(K204c47a3480aa2d86b friend : this.ircFriends) {
            if (friend.getUuid().equals(uuid)) {
               friend.setUsername(username);
               friend.setCustomTag(customTag);
               friend.setOnline(online);
               exists = true;
               break;
            }
         }

         if (!exists) {
            this.ircFriends.add(new K204c47a3480aa2d86b(uuid, username, customTag, online));
            System.out.println("[IRC Manager] Добавлен IRC друг: " + username + " (UUID: " + uuid + ", tag: " + customTag + ", online: " + online + ")");
         }

      }
   }

   public void removeIRCFriend(String uuid) {
      synchronized(this.ircFriends) {
         this.ircFriends.removeIf((friend) -> friend.getUuid().equals(uuid));
         System.out.println("[IRC Manager] Удалён IRC друг: UUID=" + uuid);
      }
   }

   public void setIRCFriends(List<String[]> friendsData) {
      synchronized(this.ircFriends) {
         this.ircFriends.clear();

         for(String[] data : friendsData) {
            if (data.length >= 4) {
               String uuid = data[0];
               String username = data[1];
               String customTag = data[2];
               boolean online = Boolean.parseBoolean(data[3]);
               this.ircFriends.add(new K204c47a3480aa2d86b(uuid, username, customTag, online));
            }
         }

         System.out.println("[IRC Manager] Загружен список IRC друзей: " + this.ircFriends.size());
      }
   }

   public void updateFriendOnlineStatus(String uuid, boolean online) {
      synchronized(this.ircFriends) {
         for(K204c47a3480aa2d86b friend : this.ircFriends) {
            if (friend.getUuid().equals(uuid)) {
               friend.setOnline(online);
               System.out.println("[IRC Manager] Обновлён статус для UUID " + uuid + ": " + (online ? "онлайн" : "оффлайн"));
               break;
            }
         }

      }
   }

   public K204c47a3480aa2d86b getIRCFriendByUsername(String username) {
      synchronized(this.ircFriends) {
         for(K204c47a3480aa2d86b friend : this.ircFriends) {
            if (friend.getUsername().equals(username)) {
               return friend;
            }
         }

         return null;
      }
   }
}
