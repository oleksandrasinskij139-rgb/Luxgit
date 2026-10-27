package code.miaware.client.ui.clickgui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Generated;

public class K8001222dc0dc25d83d {
   private final String nickname;
   private final String message;
   private final String time;
   private final boolean isRead;
   private final boolean isOwnMessage;
   private final String prefix;
   private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

   public K8001222dc0dc25d83d(String nickname, String message, boolean isOwnMessage) {
      this(nickname, message, isOwnMessage, false, (String)null);
   }

   public K8001222dc0dc25d83d(String nickname, String message, boolean isOwnMessage, boolean isRead) {
      this(nickname, message, isOwnMessage, isRead, (String)null);
   }

   public K8001222dc0dc25d83d(String nickname, String message, boolean isOwnMessage, boolean isRead, String prefix) {
      this.nickname = nickname;
      this.message = message;
      this.time = LocalTime.now().format(TIME_FORMATTER);
      this.isRead = isRead;
      this.isOwnMessage = isOwnMessage;
      this.prefix = prefix;
   }

   @Generated
   public String getNickname() {
      return this.nickname;
   }

   @Generated
   public String getMessage() {
      return this.message;
   }

   @Generated
   public String getTime() {
      return this.time;
   }

   @Generated
   public boolean isRead() {
      return this.isRead;
   }

   @Generated
   public boolean isOwnMessage() {
      return this.isOwnMessage;
   }

   @Generated
   public String getPrefix() {
      return this.prefix;
   }
}
