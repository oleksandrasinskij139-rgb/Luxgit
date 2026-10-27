package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I0_i9.I11i1iIiI_11iI_i1___1liIIii;
import II1II1II1II1II1II1II1II1.l2_I1.I_l_IiI__ilii1_I1__Ii__1_ii;
import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.cosmetics.K9a0eb2753a3143de82;
import code.miaware.client.features.cosmetics.Ka5dfe6b89219e29d09;
import net.minecraft.class_2960;

public final class LuxCosmeticAdapter extends I11i1iIiI_11iI_i1___1liIIii {
   private final Ka5dfe6b89219e29d09 entry;

   public LuxCosmeticAdapter(Ka5dfe6b89219e29d09 var1) {
      super("lux_" + var1.id, var1.name, map(var1.type));
      this.entry = var1;
      this.setImgHeight(this.getImgWidth());
   }

   public static I_l_IiI__ilii1_I1__Ii__1_ii map(K9a0eb2753a3143de82 var0) {
      switch (var0) {
         case CAPE -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.CAPES;
         }
         case WINGS -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.WINGS;
         }
         case HAT -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.HAT;
         }
         case BACK -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.BODY;
         }
         case PET -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.PET;
         }
         case SWORD -> {
            return swordsTab();
         }
         default -> {
            return I_l_IiI__ilii1_I1__Ii__1_ii.MODELS;
         }
      }
   }

   private static I_l_IiI__ilii1_I1__Ii__1_ii swordsTab() {
      try {
         return I_l_IiI__ilii1_I1__Ii__1_ii.valueOf("SWORDS");
      } catch (Throwable var1) {
         return I_l_IiI__ilii1_I1__Ii__1_ii.MODELS;
      }
   }

   public Ka5dfe6b89219e29d09 getEntry() {
      return this.entry;
   }

   public boolean isEnable() {
      try {
         return K697051202eac7cfc74.getInstance().isEquipped(this.entry);
      } catch (Throwable var2) {
         return false;
      }
   }

   public void setEnable(boolean var1) {
      try {
         K697051202eac7cfc74 var2 = K697051202eac7cfc74.getInstance();
         if (var2.isEquipped(this.entry) != var1) {
            var2.toggle(this.entry);
         }
      } catch (Throwable var3) {
         String var10001 = this.entry.name;
         System.out.println("[LuxBridge] cosmetic " + var10001 + ": " + String.valueOf(var3));
      }

   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public class_2960 getCapeTexture() {
      try {
         if (this.entry.isCape()) {
            K697051202eac7cfc74 var1 = K697051202eac7cfc74.getInstance();
            if (var1.isEquipped(this.entry)) {
               class_2960 var2 = var1.capeTexture();
               if (var2 != null) {
                  return var2;
               }
            }
         }
      } catch (Throwable var3) {
      }

      return this.entry.texture;
   }
}
