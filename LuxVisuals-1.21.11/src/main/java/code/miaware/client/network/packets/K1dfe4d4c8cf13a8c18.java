package code.miaware.client.network.packets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class K1dfe4d4c8cf13a8c18 {
   private final K9a09c1b70213e7b3e6 type;

   public K1dfe4d4c8cf13a8c18(K9a09c1b70213e7b3e6 type) {
      this.type = type;
   }

   public K9a09c1b70213e7b3e6 getType() {
      return this.type;
   }

   public abstract JsonObject toJson();

   public static K1dfe4d4c8cf13a8c18 fromJson(String json) {
      try {
         JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
         String typeStr = obj.get("type").getAsString();
         K9a09c1b70213e7b3e6 type = K9a09c1b70213e7b3e6.valueOf(typeStr);
         Object var10000;
         switch (type) {
            case CONNECT -> var10000 = K07a88e17db9ac88eb1.fromJson(obj);
            case DISCONNECT -> var10000 = Kf15d2ca4bc95109ab8.fromJson(obj);
            case GLOBAL_MESSAGE -> var10000 = K5137b8284d9c113be5.fromJson(obj);
            case PRIVATE_MESSAGE -> var10000 = Kdda021ad5ad35680c3.fromJson(obj);
            case GET_ONLINE_USERS -> var10000 = K4467215b95d3f04c3e.fromJson(obj);
            case PING -> var10000 = K955e6f774ae3184b17.fromJson(obj);
            case CHECK_TAG_UNIQUENESS -> var10000 = Kca25f92f3787dfa651.fromJson(obj);
            case GET_PRIVATE_HISTORY -> var10000 = Kee87453d84d791d553.fromJson(obj);
            case CONNECT_SUCCESS -> var10000 = K9f435e00db25b9ea5a.fromJson(obj);
            case CONNECT_ERROR -> var10000 = K005cbbf5c2e1224345.fromJson(obj);
            case GLOBAL_MESSAGE_BROADCAST -> var10000 = K8ec145af917f484b0d.fromJson(obj);
            case PRIVATE_MESSAGE_RECEIVED -> var10000 = Kefc91679dc3eb50b7e.fromJson(obj);
            case ONLINE_USERS_LIST -> var10000 = K53fc00552801dcecee.fromJson(obj);
            case USER_JOINED -> var10000 = Kc519953af9efbbee7d.fromJson(obj);
            case USER_LEFT -> var10000 = K16b0e9b7845f4fc9c5.fromJson(obj);
            case PONG -> var10000 = K2ae4c283132be2efbb.fromJson(obj);
            case ERROR -> var10000 = Kbe02ed68e8d31b5c3e.fromJson(obj);
            case TAG_UNIQUENESS_RESPONSE -> var10000 = K79eb27656696aad255.fromJson(obj);
            case PRIVATE_HISTORY_RESPONSE -> var10000 = null;
            default -> throw new MatchException((String)null, (Throwable)null);
         }

         return (K1dfe4d4c8cf13a8c18)var10000;
      } catch (Exception e) {
         System.err.println("Failed to parse packet: " + e.getMessage());
         return null;
      }
   }

   public String toString() {
      return this.toJson().toString();
   }
}
