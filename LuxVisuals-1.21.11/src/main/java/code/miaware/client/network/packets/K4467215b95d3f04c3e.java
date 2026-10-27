package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K4467215b95d3f04c3e extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;

   public K4467215b95d3f04c3e(String uuid) {
      super(K9a09c1b70213e7b3e6.GET_ONLINE_USERS);
      this.uuid = uuid;
   }

   public String getUuid() {
      return this.uuid;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("uuid", this.uuid);
      return obj;
   }

   public static K4467215b95d3f04c3e fromJson(JsonObject obj) {
      return new K4467215b95d3f04c3e(obj.get("uuid").getAsString());
   }
}
