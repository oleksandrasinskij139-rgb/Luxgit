package code.miaware.api.system.backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class K04bfb908d9b141e27c {
   private static final K04bfb908d9b141e27c instance = new K04bfb908d9b141e27c();
   private String currentRole = "User";
   private Color currentRoleColor = new Color(30, 100, 255);
   private boolean loaded = false;
   private static final Map<String, Color> DEFAULT_ROLE_COLORS = new HashMap();
   private static final String[] PREMIUM_ROLES;
   private static final String ROLES_URL = "https://raw.githubusercontent.com/Excluz1ver/k3d-roles/refs/heads/main/role.json";
   private static final String ROLES_KEY = "roles";

   public void loadRole() {
   }

   public String getRole() {
      if (!this.loaded) {
         this.loadRole();
      }

      return this.currentRole;
   }

   public Color getRoleColor() {
      if (!this.loaded) {
         this.loadRole();
      }

      return this.currentRoleColor;
   }

   private Color K6f71070eb965c58fe3(String role) {
      return (Color)DEFAULT_ROLE_COLORS.getOrDefault(role, new Color(255, 255, 255));
   }

   public boolean hasPremiumAccess() {
      return true;
   }

   private Map<String, String> Kfe6ca7eddf3e4b1c66() {
      try {
         String urlWithTimestamp = "https://raw.githubusercontent.com/Excluz1ver/k3d-roles/refs/heads/main/role.json?t=" + System.currentTimeMillis();
         URL url = new URL(urlWithTimestamp);
         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
         connection.setRequestMethod("GET");
         connection.setConnectTimeout(5000);
         connection.setReadTimeout(5000);
         connection.setUseCaches(false);
         connection.setRequestProperty("Cache-Control", "no-cache");
         int responseCode = connection.getResponseCode();
         System.out.println("[RoleManager] HTTP код: " + responseCode);
         if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
               response.append(line);
            }

            reader.close();
            String jsonContent = response.toString();
            System.out.println("[RoleManager] JSON ответ длина: " + jsonContent.length() + " символов");
            System.out.println("[RoleManager] JSON ответ: " + jsonContent);
            Gson gson = new Gson();
            Map<String, Object> json = (Map)gson.fromJson(jsonContent, (new TypeToken<Map<String, Object>>() {
            }).getType());
            System.out.println("[RoleManager] Распарсенный JSON: " + String.valueOf(json));
            if (json != null && json.containsKey("roles")) {
               Object rolesObj = json.get("roles");
               System.out.println("[RoleManager] Объект roles: " + String.valueOf(rolesObj));
               String var14 = rolesObj != null ? rolesObj.getClass().getName() : "null";
               System.out.println("[RoleManager] Тип объекта roles: " + var14);
               if (rolesObj instanceof Map) {
                  Map<String, String> rolesMap = (Map)rolesObj;
                  System.out.println("[RoleManager] Роли успешно загружены: " + rolesMap.size() + " записей");
                  return rolesMap;
               }

               System.out.println("[RoleManager] ОШИБКА: roles не является Map!");
            } else {
               System.out.println("[RoleManager] ОШИБКА: JSON не содержит ключ 'roles'!");
               Object var10001 = json != null ? json.keySet() : "null";
               System.out.println("[RoleManager] Доступные ключи: " + String.valueOf(var10001));
            }

            return new HashMap();
         }
      } catch (Exception e) {
         System.out.println("[RoleManager] Ошибка загрузки: " + e.getMessage());
         e.printStackTrace();
      }

      return null;
   }

   @Generated
   public static K04bfb908d9b141e27c getInstance() {
      return instance;
   }

   static {
      DEFAULT_ROLE_COLORS.put("User", new Color(30, 100, 255));
      DEFAULT_ROLE_COLORS.put("Premium", new Color(255, 215, 0));
      DEFAULT_ROLE_COLORS.put("YouTube", new Color(255, 0, 0));
      DEFAULT_ROLE_COLORS.put("TikTok", new Color(0, 0, 0));
      DEFAULT_ROLE_COLORS.put("Friend", new Color(50, 200, 100));
      DEFAULT_ROLE_COLORS.put("Tester", new Color(255, 215, 0));
      DEFAULT_ROLE_COLORS.put("Developer", new Color(255, 150, 50));
      DEFAULT_ROLE_COLORS.put("Moderator", new Color(100, 200, 255));
      DEFAULT_ROLE_COLORS.put("Admin", new Color(255, 100, 100));
      DEFAULT_ROLE_COLORS.put("Owner", new Color(255, 0, 255));
      PREMIUM_ROLES = new String[]{"Premium", "YouTube", "Friend", "Moderator", "Admin", "Owner", "Developer", "Tester"};
   }
}
