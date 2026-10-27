package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kdda021ad5ad35680c3 extends K1dfe4d4c8cf13a8c18 {
   private final String fromUuid;
   private final String fromUsername;
   private final String toUsername;
   private final String message;

   public Kdda021ad5ad35680c3(String fromUuid, String fromUsername, String toUsername, String message) {
      super(K9a09c1b70213e7b3e6.PRIVATE_MESSAGE);
      this.fromUuid = fromUuid;
      this.fromUsername = fromUsername;
      this.toUsername = toUsername;
      this.message = message;
   }

   public String getFromUuid() {
      return this.fromUuid;
   }

   public String getFromUsername() {
      return this.fromUsername;
   }

   public String getToUsername() {
      return this.toUsername;
   }

   public String getMessage() {
      return this.message;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("from_uuid", this.fromUuid);
      obj.addProperty("from_username", this.fromUsername);
      obj.addProperty("to_username", this.toUsername);
      obj.addProperty("message", this.message);
      return obj;
   }

   public static Kdda021ad5ad35680c3 fromJson(JsonObject obj) {
      return new Kdda021ad5ad35680c3(obj.get("from_uuid").getAsString(), obj.get("from_username").getAsString(), obj.get("to_username").getAsString(), obj.get("message").getAsString());
   }
}
