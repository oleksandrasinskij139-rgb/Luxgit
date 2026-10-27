package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I2_i1.IIIII_1lIii1iill1_III1iIIiIil_i;
import II1II1II1II1II1II1II1II1.I2_i1.IIIiIl111_IilI_l___i1__li;
import II1II1II1II1II1II1II1II1.I2_i1.i1_I_Il1I_lIIii___l_1__IIlll;
import II1II1II1II1II1II1II1II1.I2_i1.iII1I1l__ilIi1l1l111_I11I__111I;
import II1II1II1II1II1II1II1II1.I2_i1.i___lII11ii_iII1i_iiI_1l1il_i;
import II1II1II1II1II1II1II1II1.I2_i1.li1_II_l1IIIi1Iii1li1l;
import II1II1II1II1II1II1II1II1.I7_i6.I_il_ll1ilii__llIl_Il1i_ilI;
import II1II1II1II1II1II1II1II1.I8_i7.i1iliI1II_1___I__111_i11i_l;
import II1II1II1II1II1II1II1II1.l2_I1.I_llII1lII_1Ii_li1I1i_i_I_1I;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kb9fa339b3f0f7560be;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LuxModuleAdapter extends i1iliI1II_1___I__111_i11i_l {
   private final Kab8a8c478f7d0077cd lux;
   private final List<Link> links = new ArrayList();

   public LuxModuleAdapter(Kab8a8c478f7d0077cd var1, boolean var2) {
      this.lux = var1;

      String var3;
      try {
         var3 = var1.getDisplayName();
      } catch (Throwable var9) {
         var3 = null;
      }

      if (var3 == null || var3.isEmpty()) {
         var3 = var1.getName();
      }

      if (var2) {
         var3 = var3 + " (Lux)";
      }

      this.setName(var3);
      String var4 = var1.getDescription();
      this.setDescription(var4 == null ? "" : var4);
      this.setCategory("Interface".equals(var1.getName()) ? I_llII1lII_1Ii_li1I1i_i_I_1I.HUD : mapCategory(var1.getCategory()));

      for(Kdced9e23f1e229ba9d var6 : var1.getSettings()) {
         try {
            Link var7 = this.makeLink(var6);
            if (var7 != null) {
               this.links.add(var7);
               this.setup(new I_il_ll1ilii__llIl_Il1i_ilI[]{var7.phantom()});
            }
         } catch (Throwable var8) {
            PrintStream var10000 = System.out;
            String var10001 = var1.getName();
            var10000.println("[LuxBridge] настройка пропущена: " + var10001 + " / " + var6.getName() + " : " + String.valueOf(var8));
         }
      }

   }

   public Kab8a8c478f7d0077cd getLux() {
      return this.lux;
   }

   public static I_llII1lII_1Ii_li1I1i_i_I_1I mapCategory(Kf76f042931183af5ba var0) {
      if (var0 == null) {
         return I_llII1lII_1Ii_li1I1i_i_I_1I.MISC;
      } else {
         switch (var0) {
            case COMBAT -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.EVENTS;
            }
            case MOVEMENT -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.MOVEMENT;
            }
            case RENDER -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.VISUALS;
            }
            case PLAYER -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.PLAYER;
            }
            case COSMETICS -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.COSMETICS;
            }
            default -> {
               return I_llII1lII_1Ii_li1I1i_i_I_1I.MISC;
            }
         }
      }
   }

   public void sync() {
      for(Link var2 : this.links) {
         try {
            var2.sync();
         } catch (Throwable var4) {
         }
      }

   }

   public boolean isEnable() {
      return this.lux.isEnabled();
   }

   public boolean isUserEnabled() {
      return this.lux.isEnabled();
   }

   public void setEnable(boolean var1) {
      this.lux.setEnabled(var1);
   }

   public void toggle() {
      this.lux.toggle();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public boolean isAvailable() {
      return true;
   }

   public boolean isServerSuspended() {
      return false;
   }

   public void suspendByServer() {
   }

   public void resumeByServer() {
   }

   public void handleModuleState(i1iliI1II_1___I__111_i11i_l var1, int var2) {
   }

   public int getKey() {
      int var1 = this.lux.getBind();
      return this.lux.hasBind() ? var1 : -1;
   }

   public void setKey(int var1) {
      this.lux.setBind(var1 < 0 ? -999 : var1);
   }

   private static String dn(Kdced9e23f1e229ba9d<?> var0) {
      String var1;
      try {
         var1 = var0.getDisplayName();
      } catch (Throwable var3) {
         var1 = null;
      }

      return var1 != null && !var1.isEmpty() ? var1 : var0.getName();
   }

   private static void vis(Kdced9e23f1e229ba9d<?> var0, I_il_ll1ilii__llIl_Il1i_ilI var1) {
      var1.setVisible(() -> {
         try {
            return var0.isVisible();
         } catch (Throwable var2) {
            return true;
         }
      });
   }

   private Link makeLink(Kdced9e23f1e229ba9d<?> var1) {
      if (var1 instanceof K00fef91b3d4d1b3ecc var8) {
         return this.boolLink(var8);
      } else if (var1 instanceof Kb9fa339b3f0f7560be var7) {
         return this.buttonLink(var7);
      } else if (var1 instanceof Kb9e875ead00e0d03bd var6) {
         return this.floatLink(var6);
      } else if (var1 instanceof Kbd66519397e9a89967 var5) {
         return this.modeLink(var5);
      } else if (var1 instanceof Ka4b85e821e3749c3d9 var4) {
         return this.multiLink(var4);
      } else if (var1 instanceof Ka52d224feda41b0761 var3) {
         return this.colorLink(var3);
      } else if (var1 instanceof K1d3d0b4020f933282f var2) {
         return this.keyLink(var2);
      } else {
         return null;
      }
   }

   private Link boolLink(final K00fef91b3d4d1b3ecc var1) {
      final i___lII11ii_iII1i_iiI_1l1il_i var2 = new i___lII11ii_iII1i_iiI_1l1il_i(dn(var1));
      vis(var1, var2);
      boolean var3 = Boolean.TRUE.equals(var1.getValue());
      var2.setValue(var3);
      final boolean[] var4 = new boolean[]{var3, var3};
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var2;
         }

         public void sync() {
            boolean var1x = Boolean.TRUE.equals(var1.getValue());
            boolean var2x = var2.isValue();
            if (var2x != var4[1]) {
               var1.setValue(var2x);
               var4[1] = var2x;
               var4[0] = Boolean.TRUE.equals(var1.getValue());
            } else if (var1x != var4[0]) {
               var2.setValue(var1x);
               var4[0] = var1x;
               var4[1] = var2.isValue();
            }

         }
      };
   }

   private Link buttonLink(final Kb9fa339b3f0f7560be var1) {
      final i___lII11ii_iII1i_iiI_1l1il_i var2 = new i___lII11ii_iII1i_iiI_1l1il_i(dn(var1));
      vis(var1, var2);
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var2;
         }

         public void sync() {
            if (var2.isValue()) {
               var2.setValue(false);
               Runnable var1x = (Runnable)var1.getValue();
               if (var1x != null) {
                  var1x.run();
               }
            }

         }
      };
   }

   private Link floatLink(final Kb9e875ead00e0d03bd var1) {
      float var2 = var1.getMin();
      float var3 = var1.getMax();
      float var4 = var1.getStep();
      Float var5 = var1.getValue();
      float var6 = var5 == null ? 0.0F : var5;
      if (var2 == Float.MIN_VALUE || var2 <= -1.7014117E38F) {
         var2 = Math.min(0.0F, var6);
      }

      if (var3 >= 1.7014117E38F) {
         var3 = Math.max(100.0F, var6 * 2.0F);
      }

      if (var3 <= var2) {
         var3 = var2 + 1.0F;
      }

      final IIIiIl111_IilI_l___i1__li var7 = new IIIiIl111_IilI_l___i1__li(dn(var1), var6, var2, var3);
      if (var4 >= 1.0F && (double)var4 == Math.rint((double)var4) && (double)var2 == Math.rint((double)var2) && (double)var3 == Math.rint((double)var3)) {
         var7.range((int)var2, (int)var3);
      }

      vis(var1, var7);
      var7.setValue(var6);
      final float[] var8 = new float[]{var6, var6};
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var7;
         }

         public void sync() {
            Float var1x = var1.getValue();
            float var2 = var1x == null ? 0.0F : var1x;
            float var3 = var7.getValue();
            if (Float.compare(var3, var8[1]) != 0) {
               var1.setValue(var3);
               var8[1] = var3;
               Float var4 = var1.getValue();
               var8[0] = var4 == null ? var3 : var4;
            } else if (Float.compare(var2, var8[0]) != 0) {
               var7.setValue(var2);
               var8[0] = var2;
               var8[1] = var7.getValue();
            }

         }
      };
   }

   private Link modeLink(final Kbd66519397e9a89967 var1) {
      List var2 = var1.getModes();
      final IIIII_1lIii1iill1_III1iIIiIil_i var3 = new IIIII_1lIii1iill1_III1iIIiIil_i(dn(var1), (String[])var2.toArray(new String[0]));
      vis(var1, var3);
      String var4 = (String)var1.getValue();
      if (var4 != null) {
         var3.set(var4);
      }

      final String[] var5 = new String[]{var4, var3.get()};
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var3;
         }

         public void sync() {
            String var1x = (String)var1.getValue();
            String var2 = var3.get();
            if (!Objects.equals(var2, var5[1])) {
               if (var2 != null) {
                  var1.setValue(var2);
               }

               var5[1] = var2;
               var5[0] = (String)var1.getValue();
            } else if (!Objects.equals(var1x, var5[0])) {
               if (var1x != null) {
                  var3.set(var1x);
               }

               var5[0] = var1x;
               var5[1] = var3.get();
            }

         }
      };
   }

   private Link multiLink(Ka4b85e821e3749c3d9 var1) {
      final List var2 = (List)var1.getValue();
      final ArrayList var3 = new ArrayList();

      for(K00fef91b3d4d1b3ecc var5 : var2) {
         var3.add(new iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI(dn(var5), Boolean.TRUE.equals(var5.getValue())));
      }

      final iII1I1l__ilIi1l1l111_I11I__111I var8 = new iII1I1l__ilIi1l1l111_I11I__111I(dn(var1), (iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI[])var3.toArray(new iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI[0]));
      vis(var1, var8);
      final boolean[] var9 = new boolean[var2.size()];
      final boolean[] var6 = new boolean[var2.size()];

      for(int var7 = 0; var7 < var2.size(); ++var7) {
         var9[var7] = var6[var7] = Boolean.TRUE.equals(((K00fef91b3d4d1b3ecc)var2.get(var7)).getValue());
      }

      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var8;
         }

         public void sync() {
            for(int var1 = 0; var1 < var2.size(); ++var1) {
               boolean var2x = Boolean.TRUE.equals(((K00fef91b3d4d1b3ecc)var2.get(var1)).getValue());
               boolean var3x = ((iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI)var3.get(var1)).isEnabled();
               if (var3x != var6[var1]) {
                  ((K00fef91b3d4d1b3ecc)var2.get(var1)).setValue(var3x);
                  var6[var1] = var3x;
                  var9[var1] = Boolean.TRUE.equals(((K00fef91b3d4d1b3ecc)var2.get(var1)).getValue());
               } else if (var2x != var9[var1]) {
                  ((iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI)var3.get(var1)).setEnabled(var2x);
                  var9[var1] = var2x;
                  var6[var1] = ((iII1I1l__ilIi1l1l111_I11I__111I.il1l___I1Iil__ll1Il1I1lI)var3.get(var1)).isEnabled();
               }
            }

         }
      };
   }

   private Link colorLink(final Ka52d224feda41b0761 var1) {
      final li1_II_l1IIIi1Iii1li1l var2 = new li1_II_l1IIIi1Iii1li1l(dn(var1));
      vis(var1, var2);
      Color var3 = (Color)var1.getValue();
      if (var3 != null) {
         var2.setCustomColor(var3);
      }

      final int[] var4 = new int[]{var3 == null ? 0 : var3.getRGB(), var2.getColor() == null ? 0 : var2.getColor().getRGB()};
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var2;
         }

         public void sync() {
            Color var1x = (Color)var1.getValue();
            Color var2x = var2.getColor();
            int var3 = var1x == null ? 0 : var1x.getRGB();
            int var4x = var2x == null ? 0 : var2x.getRGB();
            if (var4x != var4[1]) {
               if (var2x != null) {
                  var1.setValue(new Color(var4x, true));
               }

               var4[1] = var4x;
               Color var5 = (Color)var1.getValue();
               var4[0] = var5 == null ? 0 : var5.getRGB();
            } else if (var3 != var4[0]) {
               if (var1x != null) {
                  var2.setCustomColor(var1x);
               }

               var4[0] = var3;
               Color var6 = var2.getColor();
               var4[1] = var6 == null ? 0 : var6.getRGB();
            }

         }
      };
   }

   private Link keyLink(final K1d3d0b4020f933282f var1) {
      final i1_I_Il1I_lIIii___l_1__IIlll var2 = new i1_I_Il1I_lIIii___l_1__IIlll(dn(var1));
      vis(var1, var2);
      Integer var3 = (Integer)var1.getValue();
      int var4 = var3 == null ? -1 : (var3 == -999 ? -1 : var3);
      var2.setKey(var4);
      final int[] var5 = new int[]{var4, var2.getKey()};
      return new Link() {
         public I_il_ll1ilii__llIl_Il1i_ilI phantom() {
            return var2;
         }

         public void sync() {
            Integer var1x = (Integer)var1.getValue();
            int var2x = var1x == null ? -1 : (var1x == -999 ? -1 : var1x);
            int var3 = var2.getKey();
            if (var3 != var5[1]) {
               var1.setValue(var3);
               var5[1] = var3;
               Integer var4 = (Integer)var1.getValue();
               var5[0] = var4 == null ? -1 : (var4 == -999 ? -1 : var4);
            } else if (var2x != var5[0]) {
               var2.setKey(var2x);
               var5[0] = var2x;
               var5[1] = var2.getKey();
            }

         }
      };
   }

   private interface Link {
      I_il_ll1ilii__llIl_Il1i_ilI phantom();

      void sync();
   }
}
