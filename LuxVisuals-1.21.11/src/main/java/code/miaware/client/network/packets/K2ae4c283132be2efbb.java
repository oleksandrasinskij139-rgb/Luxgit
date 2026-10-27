package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K2ae4c283132be2efbb extends K1dfe4d4c8cf13a8c18 {
   private final long timestamp;

   public K2ae4c283132be2efbb(long timestamp) {
      super(K9a09c1b70213e7b3e6.PONG);
      this.timestamp = timestamp;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("timestamp", this.timestamp);
      return obj;
   }

   public static K2ae4c283132be2efbb fromJson(JsonObject obj) {
      return new K2ae4c283132be2efbb(obj.get("timestamp").getAsLong());
   }
}
