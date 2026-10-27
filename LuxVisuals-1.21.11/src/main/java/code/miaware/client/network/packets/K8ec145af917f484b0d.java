package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K8ec145af917f484b0d extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final String username;
   private final String message;
   private final long timestamp;
   private final String prefix;

   public K8ec145af917f484b0d(String uuid, String username, String message, long timestamp, String prefix) {
      super(K9a09c1b70213e7b3e6.GLOBAL_MESSAGE_BROADCAST);
      this.uuid = uuid;
      this.username = username;
      this.message = message;
      this.timestamp = timestamp;
      this.prefix = prefix;
   }

   public K8ec145af917f484b0d(String uuid, String username, String message, long timestamp) {
      this(uuid, username, message, timestamp, (String)null);
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

   public long getTimestamp() {
      return this.timestamp;
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
      obj.addProperty("timestamp", this.timestamp);
      if (this.prefix != null) {
         obj.addProperty("prefix", this.prefix);
      }

      return obj;
   }

   public static K8ec145af917f484b0d fromJson(JsonObject obj) {
      String prefix = obj.has("prefix") ? obj.get("prefix").getAsString() : null;
      return new K8ec145af917f484b0d(obj.get("uuid").getAsString(), obj.get("username").getAsString(), obj.get("message").getAsString(), obj.get("timestamp").getAsLong(), prefix);
   }
}
