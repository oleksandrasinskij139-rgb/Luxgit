package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K5137b8284d9c113be5 extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final String username;
   private final String message;
   private final String prefix;

   public K5137b8284d9c113be5(String uuid, String username, String message, String prefix) {
      super(K9a09c1b70213e7b3e6.GLOBAL_MESSAGE);
      this.uuid = uuid;
      this.username = username;
      this.message = message;
      this.prefix = prefix;
   }

   public K5137b8284d9c113be5(String uuid, String username, String message) {
      this(uuid, username, message, (String)null);
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getUsername() {
      return this.username;
   }

   public String getMessage() {
      return this.message;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("uuid", this.uuid);
      obj.addProperty("username", this.username);
      obj.addProperty("message", this.message);
      if (this.prefix != null) {
         obj.addProperty("prefix", this.prefix);
      }

      return obj;
   }

   public static K5137b8284d9c113be5 fromJson(JsonObject obj) {
      String uuid = obj.get("uuid").getAsString();
      String username = obj.get("username").getAsString();
      String message = obj.get("message").getAsString();
      String prefix = obj.has("prefix") ? obj.get("prefix").getAsString() : null;
      return new K5137b8284d9c113be5(uuid, username, message, prefix);
   }
}
