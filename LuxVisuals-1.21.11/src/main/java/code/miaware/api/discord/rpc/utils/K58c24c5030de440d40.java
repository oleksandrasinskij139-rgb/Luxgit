package code.miaware.api.discord.rpc.utils;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class K58c24c5030de440d40 {
   private final K834e4ca50eb6914ef7 richPresence = new K834e4ca50eb6914ef7();

   public K58c24c5030de440d40 setSmallImage(String var1) {
      return this.setSmallImage(var1, "");
   }

   public K58c24c5030de440d40 setDetails(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.richPresence.details = var1.substring(0, Math.min(var1.length(), 128));
      }

      return this;
   }

   public K58c24c5030de440d40 setLargeImage(String var1, String var2) {
      this.richPresence.largeImageKey = var1;
      this.richPresence.largeImageText = var2;
      return this;
   }

   public K58c24c5030de440d40 setState(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.richPresence.state = var1.substring(0, Math.min(var1.length(), 128));
      }

      return this;
   }

   public K58c24c5030de440d40 setInstance(boolean var1) {
      if ((this.richPresence.button_label_1 == null || !this.richPresence.button_label_1.isEmpty()) && (this.richPresence.button_label_2 == null || !this.richPresence.button_label_2.isEmpty())) {
         this.richPresence.instance = var1 ? 1 : 0;
      }

      return this;
   }

   public K58c24c5030de440d40 setButtons(K50a5e57795455cd1cd var1) {
      return this.setButtons(Collections.singletonList(var1));
   }

   public K58c24c5030de440d40 setSmallImage(String var1, String var2) {
      this.richPresence.smallImageKey = var1;
      this.richPresence.smallImageText = var2;
      return this;
   }

   public K58c24c5030de440d40 setParty(String var1, int var2, int var3) {
      if ((this.richPresence.button_label_1 == null || !this.richPresence.button_label_1.isEmpty()) && (this.richPresence.button_label_2 == null || !this.richPresence.button_label_2.isEmpty())) {
         this.richPresence.partyId = var1;
         this.richPresence.partySize = var2;
         this.richPresence.partyMax = var3;
      }

      return this;
   }

   public K58c24c5030de440d40 setButtons(List<K50a5e57795455cd1cd> var1) {
      if (var1 != null && !var1.isEmpty()) {
         int var2 = Math.min(var1.size(), 2);
         this.richPresence.button_label_1 = ((K50a5e57795455cd1cd)var1.get(0)).getLabel();
         this.richPresence.button_url_1 = ((K50a5e57795455cd1cd)var1.get(0)).getUrl();
         if (var2 == 2) {
            this.richPresence.button_label_2 = ((K50a5e57795455cd1cd)var1.get(1)).getLabel();
            this.richPresence.button_url_2 = ((K50a5e57795455cd1cd)var1.get(1)).getUrl();
         }
      }

      return this;
   }

   public K58c24c5030de440d40 setStartTimestamp(OffsetDateTime var1) {
      this.richPresence.startTimestamp = var1.toEpochSecond();
      return this;
   }

   public K58c24c5030de440d40 setSecrets(String var1, String var2, String var3) {
      if ((this.richPresence.button_label_1 == null || !this.richPresence.button_label_1.isEmpty()) && (this.richPresence.button_label_2 == null || !this.richPresence.button_label_2.isEmpty())) {
         this.richPresence.matchSecret = var1;
         this.richPresence.joinSecret = var2;
         this.richPresence.spectateSecret = var3;
      }

      return this;
   }

   public void setButtons(K50a5e57795455cd1cd var1, K50a5e57795455cd1cd var2) {
      this.setButtons(Arrays.asList(var1, var2));
   }

   public void setStartTimestamp(long var1) {
      this.richPresence.startTimestamp = var1;
   }

   public K58c24c5030de440d40 setSecrets(String var1, String var2) {
      if ((this.richPresence.button_label_1 == null || !this.richPresence.button_label_1.isEmpty()) && (this.richPresence.button_label_2 == null || !this.richPresence.button_label_2.isEmpty())) {
         this.richPresence.joinSecret = var1;
         this.richPresence.spectateSecret = var2;
      }

      return this;
   }

   public K58c24c5030de440d40 setEndTimestamp(long var1) {
      this.richPresence.endTimestamp = var1;
      return this;
   }

   public K58c24c5030de440d40 setEndTimestamp(OffsetDateTime var1) {
      this.richPresence.endTimestamp = var1.toEpochSecond();
      return this;
   }

   public K58c24c5030de440d40 setLargeImage(String var1) {
      return this.setLargeImage(var1, "");
   }

   public K834e4ca50eb6914ef7 build() {
      return this.richPresence;
   }
}
