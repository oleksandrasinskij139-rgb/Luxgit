package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kee87453d84d791d553 extends K1dfe4d4c8cf13a8c18 {
   private final String myUuid;
   private final String withUsername;

   public Kee87453d84d791d553(String myUuid, String withUsername) {
      super(K9a09c1b70213e7b3e6.GET_PRIVATE_HISTORY);
      this.myUuid = myUuid;
      this.withUsername = withUsername;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", "GET_PRIVATE_HISTORY");
      obj.addProperty("uuid", this.myUuid);
      obj.addProperty("with_username", this.withUsername);
      return obj;
   }

   public static Kee87453d84d791d553 fromJson(JsonObject obj) {
      String uuid = obj.get("uuid").getAsString();
      String withUsername = obj.get("with_username").getAsString();
      return new Kee87453d84d791d553(uuid, withUsername);
   }
}
