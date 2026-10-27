package code.miaware.client.features.cosmetics;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;

public enum K9a0eb2753a3143de82 {
   CAPE("cape", "Плащи", "Capes", "Плащ", "Cape"),
   WINGS("wings", "Крылья", "Wings", "Крылья", "Wings"),
   BACK("back", "Спина", "Back", "Спина", "Back"),
   PET("pet", "Питомцы", "Pets", "Питомец", "Pet"),
   HAT("hat", "Шляпы", "Hats", "Шляпа", "Hat"),
   ELYTRA("elytra", "Элитры", "Elytra", "Элитры", "Elytra"),
   SWORD("sword", "Мечи", "Swords", "Меч", "Sword");

   public final String key;
   private final String pluralRu;
   private final String pluralEn;
   private final String singleRu;
   private final String singleEn;

   private K9a0eb2753a3143de82(String var3, String var4, String var5, String var6, String var7) {
      this.key = var3;
      this.pluralRu = var4;
      this.pluralEn = var5;
      this.singleRu = var6;
      this.singleEn = var7;
   }

   private static boolean ru() {
      try {
         return Ka35e7d6b31db9984f1.getInstance().isRussian();
      } catch (Throwable var1) {
         return false;
      }
   }

   public String plural() {
      return ru() ? this.pluralRu : this.pluralEn;
   }

   public String single() {
      return ru() ? this.singleRu : this.singleEn;
   }

   public static K9a0eb2753a3143de82 byKey(String var0) {
      for(K9a0eb2753a3143de82 var4 : values()) {
         if (var4.key.equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return BACK;
   }

   // $FF: synthetic method
   private static K9a0eb2753a3143de82[] $values() {
      return new K9a0eb2753a3143de82[]{CAPE, WINGS, BACK, PET, HAT, ELYTRA, SWORD};
   }
}
