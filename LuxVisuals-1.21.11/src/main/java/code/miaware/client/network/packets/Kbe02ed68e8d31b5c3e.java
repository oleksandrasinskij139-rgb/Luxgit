package code.miaware.client.network.packets;

import com.google.gson.JsonObject;

public class Kbe02ed68e8d31b5c3e extends K1dfe4d4c8cf13a8c18 {
   private final String error;
   private final int code;

   public Kbe02ed68e8d31b5c3e(String error, int code) {
      super(K9a09c1b70213e7b3e6.ERROR);
      this.error = error;
      this.code = code;
   }

   public String getError() {
      return this.error;
   }

   public int getCode() {
      return this.code;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      obj.addProperty("error", this.error);
      obj.addProperty("code", this.code);
      return obj;
   }

   public static Kbe02ed68e8d31b5c3e fromJson(JsonObject obj) {
      return new Kbe02ed68e8d31b5c3e(obj.get("error").getAsString(), obj.get("code").getAsInt());
   }
}
