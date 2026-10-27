package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K16b0e9b7845f4fc9c5 extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final String username;
   private final int onlineCount;

   public K16b0e9b7845f4fc9c5(String uuid, String username, int onlineCount) {
      super(K9a09c1b70213e7b3e6.USER_LEFT);
      this.uuid = uuid;
      this.username = username;
      this.onlineCount = onlineCount;
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getUsername() {
      return this.username;
   }

   public int getOnlineCount() {
      return this.onlineCount;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("uuid", this.uuid);
      obj.addProperty("username", this.username);
      obj.addProperty("online_count", this.onlineCount);
      return obj;
   }

   public static K16b0e9b7845f4fc9c5 fromJson(JsonObject obj) {
      return new K16b0e9b7845f4fc9c5(obj.get("uuid").getAsString(), obj.get("username").getAsString(), obj.get("online_count").getAsInt());
   }
}
