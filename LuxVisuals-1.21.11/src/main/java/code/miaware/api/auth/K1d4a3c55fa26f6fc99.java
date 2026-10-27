package code.miaware.api.auth;

import java.util.UUID;
import lombok.Generated;

public class K1d4a3c55fa26f6fc99 {
   private final String username;
   private final UUID uuid;
   private final String token;
   private final String type;

   @Generated
   public String getUsername() {
      return this.username;
   }

   @Generated
   public UUID getUuid() {
      return this.uuid;
   }

   @Generated
   public String getToken() {
      return this.token;
   }

   @Generated
   public String getType() {
      return this.type;
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof K1d4a3c55fa26f6fc99)) {
         return false;
      } else {
         K1d4a3c55fa26f6fc99 other = (K1d4a3c55fa26f6fc99)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$username = this.getUsername();
            Object other$username = other.getUsername();
            if (this$username == null) {
               if (other$username != null) {
                  return false;
               }
            } else if (!this$username.equals(other$username)) {
               return false;
            }

            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$token = this.getToken();
            Object other$token = other.getToken();
            if (this$token == null) {
               if (other$token != null) {
                  return false;
               }
            } else if (!this$token.equals(other$token)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof K1d4a3c55fa26f6fc99;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $username = this.getUsername();
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $token = this.getToken();
      result = result * 59 + ($token == null ? 43 : $token.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = this.getUsername();
      return "Session(username=" + var10000 + ", uuid=" + String.valueOf(this.getUuid()) + ", token=" + this.getToken() + ", type=" + this.getType() + ")";
   }

   @Generated
   public K1d4a3c55fa26f6fc99(String username, UUID uuid, String token, String type) {
      this.username = username;
      this.uuid = uuid;
      this.token = token;
      this.type = type;
   }
}
