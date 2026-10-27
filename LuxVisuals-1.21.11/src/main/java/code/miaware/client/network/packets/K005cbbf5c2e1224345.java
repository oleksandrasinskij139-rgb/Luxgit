package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K005cbbf5c2e1224345 extends K1dfe4d4c8cf13a8c18 {
   private final String error;

   public K005cbbf5c2e1224345(String error) {
      super(K9a09c1b70213e7b3e6.CONNECT_ERROR);
      this.error = error;
   }

   public String getError() {
      return this.error;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("error", this.error);
      return obj;
   }

   public static K005cbbf5c2e1224345 fromJson(JsonObject obj) {
      return new K005cbbf5c2e1224345(obj.get("error").getAsString());
   }
}
