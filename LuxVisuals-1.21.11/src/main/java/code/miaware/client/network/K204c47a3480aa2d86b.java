package code.miaware.client.network;

import lombok.Generated;

public class K204c47a3480aa2d86b {
   private String uuid;
   private String username;
   private String customTag;
   private boolean online;

   public K204c47a3480aa2d86b(String uuid, String username, String customTag, boolean online) {
      this.uuid = uuid;
      this.username = username;
      this.customTag = customTag;
      this.online = online;
   }

   public String getDisplayName() {
      return this.customTag != null && !this.customTag.isEmpty() ? this.customTag : this.username;
   }

   @Generated
   public String getUuid() {
      return this.uuid;
   }

   @Generated
   public String getUsername() {
      return this.username;
   }

   @Generated
   public String getCustomTag() {
      return this.customTag;
   }

   @Generated
   public boolean isOnline() {
      return this.online;
   }

   @Generated
   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   @Generated
   public void setUsername(String username) {
      this.username = username;
   }

   @Generated
   public void setCustomTag(String customTag) {
      this.customTag = customTag;
   }

   @Generated
   public void setOnline(boolean online) {
      this.online = online;
   }
}
