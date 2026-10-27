package code.miaware.api.auth;

import java.util.Map;
import java.util.UUID;

public record Ka9b4e2127b25b6f5fd() {
   public UUID uuidByName(String username) {
      try {
         K7ae5509f6232a01dbc response = K22f4c97f72c51b13c3.get("https://api.minecraftservices.com/users/profiles/minecraft/" + username, Map.of());
         if (response.code() != 200) {
            throw new RuntimeException("Fail " + username);
         } else {
            Kfaeef2e0786d1c0e69 profileResponse = (Kfaeef2e0786d1c0e69)K0f9c0d7e4c9164ae8b.fromJson(response.text(), Kfaeef2e0786d1c0e69.class);
            return K439206e487150f9ae0.parseUuid(profileResponse.id);
         }
      } catch (Exception var4) {
         return null;
      }
   }
}
