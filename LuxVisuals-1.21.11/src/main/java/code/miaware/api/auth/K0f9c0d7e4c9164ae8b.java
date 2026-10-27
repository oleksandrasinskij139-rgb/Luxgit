package code.miaware.api.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class K0f9c0d7e4c9164ae8b {
   private static final Gson GSON = (new GsonBuilder()).create();

   public static <T> T fromJson(String json, Class<T> classOfT) {
      return (T)GSON.fromJson(json, classOfT);
   }
}
