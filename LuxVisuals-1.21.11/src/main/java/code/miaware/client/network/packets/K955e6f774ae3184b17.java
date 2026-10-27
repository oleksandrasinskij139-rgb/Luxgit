package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K955e6f774ae3184b17 extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final long timestamp;

   public K955e6f774ae3184b17(String uuid, long timestamp) {
      super(K9a09c1b70213e7b3e6.PING);
      this.uuid = uuid;
      this.timestamp = timestamp;
   }

   public String getUuid() {
      return this.uuid;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("uuid", this.uuid);
      obj.addProperty("timestamp", this.timestamp);
      return obj;
   }

   public static K955e6f774ae3184b17 fromJson(JsonObject obj) {
      return new K955e6f774ae3184b17(obj.get("uuid").getAsString(), obj.get("timestamp").getAsLong());
   }
}
