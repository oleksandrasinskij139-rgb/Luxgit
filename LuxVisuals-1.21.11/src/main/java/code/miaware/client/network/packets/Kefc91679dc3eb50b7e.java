package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kefc91679dc3eb50b7e extends K1dfe4d4c8cf13a8c18 {
   private final String fromUuid;
   private final String fromUsername;
   private final String message;
   private final long timestamp;

   public Kefc91679dc3eb50b7e(String fromUuid, String fromUsername, String message, long timestamp) {
      super(K9a09c1b70213e7b3e6.PRIVATE_MESSAGE_RECEIVED);
      this.fromUuid = fromUuid;
      this.fromUsername = fromUsername;
      this.message = message;
      this.timestamp = timestamp;
   }

   public String getFromUuid() {
      return this.fromUuid;
   }

   public String getFromUsername() {
      return this.fromUsername;
   }

   public String getMessage() {
      return this.message;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("from_uuid", this.fromUuid);
      obj.addProperty("from_username", this.fromUsername);
      obj.addProperty("message", this.message);
      obj.addProperty("timestamp", this.timestamp);
      return obj;
   }

   public static Kefc91679dc3eb50b7e fromJson(JsonObject obj) {
      return new Kefc91679dc3eb50b7e(obj.get("from_uuid").getAsString(), obj.get("from_username").getAsString(), obj.get("message").getAsString(), obj.get("timestamp").getAsLong());
   }
}
