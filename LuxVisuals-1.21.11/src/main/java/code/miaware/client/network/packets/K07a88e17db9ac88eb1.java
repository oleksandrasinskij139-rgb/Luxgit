package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K07a88e17db9ac88eb1 extends K1dfe4d4c8cf13a8c18 {
   private final String username;
   private final String uuid;
   private final String version;
   private final String customTag;
   private final String role;

   public K07a88e17db9ac88eb1(String username, String uuid, String version) {
      this(username, uuid, version, (String)null, (String)null);
   }

   public K07a88e17db9ac88eb1(String username, String uuid, String version, String customTag) {
      this(username, uuid, version, customTag, (String)null);
   }

   public K07a88e17db9ac88eb1(String username, String uuid, String version, String customTag, String role) {
      super(K9a09c1b70213e7b3e6.CONNECT);
      this.username = username;
      this.uuid = uuid;
      this.version = version;
      this.customTag = customTag;
      this.role = role;
   }

   public String getUsername() {
      return this.username;
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getVersion() {
      return this.version;
   }

   public String getCustomTag() {
      return this.customTag;
   }

   public String getRole() {
      return this.role;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("username", this.username);
      obj.addProperty("uuid", this.uuid);
      obj.addProperty("version", this.version);
      if (this.customTag != null && !this.customTag.isEmpty()) {
         obj.addProperty("customTag", this.customTag);
      }

      if (this.role != null && !this.role.isEmpty()) {
         obj.addProperty("role", this.role);
      }

      return obj;
   }

   public static K07a88e17db9ac88eb1 fromJson(JsonObject obj) {
      String username = obj.get("username").getAsString();
      String uuid = obj.get("uuid").getAsString();
      String version = obj.get("version").getAsString();
      String customTag = obj.has("customTag") ? obj.get("customTag").getAsString() : null;
      String role = obj.has("role") ? obj.get("role").getAsString() : null;
      return new K07a88e17db9ac88eb1(username, uuid, version, customTag, role);
   }
}
