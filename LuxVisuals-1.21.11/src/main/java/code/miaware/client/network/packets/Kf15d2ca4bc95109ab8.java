package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kf15d2ca4bc95109ab8 extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;

   public Kf15d2ca4bc95109ab8(String uuid) {
      super(K9a09c1b70213e7b3e6.DISCONNECT);
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

   public static Kf15d2ca4bc95109ab8 fromJson(JsonObject obj) {
      return new Kf15d2ca4bc95109ab8(obj.get("uuid").getAsString());
   }
}
