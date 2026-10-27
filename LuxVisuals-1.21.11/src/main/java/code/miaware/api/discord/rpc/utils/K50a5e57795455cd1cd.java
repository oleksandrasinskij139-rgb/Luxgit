package code.miaware.api.discord.rpc.utils;

import java.io.Serializable;

public class K50a5e57795455cd1cd implements Serializable {
   private final String url;
   private final String label;

   public static K50a5e57795455cd1cd create(String label, String url) {
      label = label.substring(0, Math.min(label.length(), 31));
      return new K50a5e57795455cd1cd(label, url);
   }

   protected K50a5e57795455cd1cd(String label, String url) {
      this.label = label;
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }

   public String getLabel() {
      return this.label;
   }
}
