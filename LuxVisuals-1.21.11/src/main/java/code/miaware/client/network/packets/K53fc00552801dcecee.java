package code.miaware.client.network.packets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class K53fc00552801dcecee extends K1dfe4d4c8cf13a8c18 {
   private final List<K42cf2cb0ea210a8d94> users;

   public K53fc00552801dcecee(List<K42cf2cb0ea210a8d94> users) {
      super(K9a09c1b70213e7b3e6.ONLINE_USERS_LIST);
      this.users = users;
   }

   public List<K42cf2cb0ea210a8d94> getUsers() {
      return this.users;
   }

   public JsonObject toJson() {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", this.getType().name());
      JsonArray usersArray = new JsonArray();

      for(K42cf2cb0ea210a8d94 user : this.users) {
         JsonObject userObj = new JsonObject();
         userObj.addProperty("uuid", user.uuid);
         userObj.addProperty("username", user.username);
         userObj.addProperty("status", user.status);
         usersArray.add(userObj);
      }

      obj.add("users", usersArray);
      return obj;
   }

   public static K53fc00552801dcecee fromJson(JsonObject obj) {
      List<K42cf2cb0ea210a8d94> users = new ArrayList();
      JsonArray usersArray = obj.getAsJsonArray("users");

      for(int i = 0; i < usersArray.size(); ++i) {
         JsonObject userObj = usersArray.get(i).getAsJsonObject();
         users.add(new K42cf2cb0ea210a8d94(userObj.get("uuid").getAsString(), userObj.get("username").getAsString(), userObj.get("status").getAsString()));
      }

      return new K53fc00552801dcecee(users);
   }
}
