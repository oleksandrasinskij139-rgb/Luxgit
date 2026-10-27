package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K9f435e00db25b9ea5a extends K1dfe4d4c8cf13a8c18 {
   private final String message;
   private final int onlineCount;

   public K9f435e00db25b9ea5a(String message, int onlineCount) {
      super(K9a09c1b70213e7b3e6.CONNECT_SUCCESS);
      this.message = message;
      this.onlineCount = onlineCount;
   }

   public String getMessage() {
      return this.message;
   }

   public int getOnlineCount() {
      return this.onlineCount;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("message", this.message);
      obj.addProperty("online_count", this.onlineCount);
      return obj;
   }

   public static K9f435e00db25b9ea5a fromJson(JsonObject obj) {
      return new K9f435e00db25b9ea5a(obj.get("message").getAsString(), obj.get("online_count").getAsInt());
   }
}
