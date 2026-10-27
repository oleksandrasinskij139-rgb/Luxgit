package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kc519953af9efbbee7d extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final String username;
   private final int onlineCount;

   public Kc519953af9efbbee7d(String uuid, String username, int onlineCount) {
      super(K9a09c1b70213e7b3e6.USER_JOINED);
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

   public static Kc519953af9efbbee7d fromJson(JsonObject obj) {
      return new Kc519953af9efbbee7d(obj.get("uuid").getAsString(), obj.get("username").getAsString(), obj.get("online_count").getAsInt());
   }
}
