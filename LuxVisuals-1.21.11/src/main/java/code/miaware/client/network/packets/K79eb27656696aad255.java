package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class K79eb27656696aad255 extends K1dfe4d4c8cf13a8c18 {
   private final String tag;
   private final boolean isUnique;

   public K79eb27656696aad255(String tag, boolean isUnique) {
      super(K9a09c1b70213e7b3e6.TAG_UNIQUENESS_RESPONSE);
      this.tag = tag;
      this.isUnique = isUnique;
   }

   public String getTag() {
      return this.tag;
   }

   public boolean isUnique() {
      return this.isUnique;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("tag", this.tag);
      obj.addProperty("isUnique", this.isUnique);
      return obj;
   }

   public static K79eb27656696aad255 fromJson(JsonObject obj) {
      String tag = obj.get("tag").getAsString();
      boolean isUnique = obj.get("isUnique").getAsBoolean();
      return new K79eb27656696aad255(tag, isUnique);
   }
}
