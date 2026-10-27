package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kca25f92f3787dfa651 extends K1dfe4d4c8cf13a8c18 {
   private final String uuid;
   private final String tag;

   public Kca25f92f3787dfa651(String uuid, String tag) {
      super(K9a09c1b70213e7b3e6.CHECK_TAG_UNIQUENESS);
      this.uuid = uuid;
      this.tag = tag;
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getTag() {
      return this.tag;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("uuid", this.uuid);
      obj.addProperty("tag", this.tag);
      return obj;
   }

   public static Kca25f92f3787dfa651 fromJson(JsonObject obj) {
      String uuid = obj.get("uuid").getAsString();
      String tag = obj.get("tag").getAsString();
      return new Kca25f92f3787dfa651(uuid, tag);
   }
}
