package ru.phantom;

import II1II1II1II1II1II1II1II1.I0_i9.i1_i1_I_lIIi_1_I_l1_II_iII111i_;
import II1II1II1II1II1II1II1II1.I0_i9.lI1l1Ii_illiiil111_1i_i_l;
import II1II1II1II1II1II1II1II1.I1_i0.liI_IillIi_I1i1Ii_IliIliliII1;
import II1II1II1II1II1II1II1II1.I2_i1.l_lii_i_l11Ii_lI1_1_11_iI_I1i1;
import II1II1II1II1II1II1II1II1.I5_i4.l1iliI1iIi1li1IIIl1iI1Iil;
import II1II1II1II1II1II1II1II1.I6_i5.IIIiii1liIIl__1i_I_iIi1l_1I_l1;
import II1II1II1II1II1II1II1II1.I6_i5.liIl_i_ii1__ll_1i_IIiiI1I1I__;
import II1II1II1II1II1II1II1II1.I7_i6.iII_iII_1lI_1l1_I1l1_II1_1;
import II1II1II1II1II1II1II1II1.I7_i6.liIiI__lIlIiI1IIil1IiI_1_1l;
import II1II1II1II1II1II1II1II1.I9_i8.i1_lII1i1l_1_ii1_l_IiillllIllII;
import II1II1II1II1II1II1II1II1.l1_I0.i_Iiiiii11111llili_1_lIi1I_ll1_;
import II1II1II1II1II1II1II1II1.l2_I1.iiili11l1I_1I1_ll_l111_l;
import II1II1II1II1II1II1II1II1.l3_I2.iII___1_il1I1i_l_iI_il1il;
import II1II1II1II1II1II1II1II1.l3_I2.i_1_lIi_II_l_1lI_I1__II_I;
import II1II1II1II1II1II1II1II1.l3_I2.lil_I__iiiI1__IlIIl1i1IIliII_;
import II1II1II1II1II1II1II1II1.l6_I5.l_I_1i1_lIi1_l11l_1_1l1_1iIiI;
import II1II1II1II1II1II1II1II1.l8_I7.lII_11liI_1Iil_iIiI1i_llil_1__;
import II1II1II1II1II1II1II1II1.l8_I7.l__11IIIii1I__I1i1l1Ii11iI_11l;
import II1II1II1II1II1II1II1II1.l9_I8.ii_llIII1Ii1iIlll11_11_;
import com.google.common.eventbus.EventBus;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.luxvisuals.menu.Patch;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import lombok.Generated;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3264;
import net.minecraft.class_3300;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Phantom {
   INSTANCE;

   public static final String NAME = "Phantom";
   public static final String BUILD_TYPE = "Beta";
   public static final String VERSION = "4.0.5";
   public static final String MOD_ID = "Phantom".toLowerCase();
   public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
   private EventBus eventBus;
   private i1_lII1i1l_1_ii1_l_IiillllIllII moduleManager;
   private iiili11l1I_1I1_ll_l111_l language;
   private l_I_1i1_lIi1_l11l_1_1l1_1iIiI commandManager;
   private liIl_i_ii1__ll_1i_IIiiI1I1I__ configManager;
   private iII_iII_1lI_1l1_I1l1_II1_1 draggableManager;
   private lil_I__iiiI1__IlIIl1i1IIliII_ hudManager;
   private i_Iiiiii11111llili_1_lIi1I_ll1_ friendManager;
   private l1iliI1iIi1li1IIIl1iI1Iil wayPointsManager;
   private lII_11liI_1Iil_iIiI1i_llil_1__ macrosManager;
   private i1_i1_I_lIIi_1_I_l1_II_iII111i_ capesManager;
   private liIiI__lIlIiI1IIil1IiI_1_1l notificationManager;

   @iII___1_il1I1i_l_iI_il1il
   @i_1_lIi_II_l_1lI_I1__II_I
   public void initialize() {
      this.setupLogFilter();
      this.disableOpenGlDebug();
      LOGGER.info((String)"Initializing {}...", (Object)"Phantom");
      this.forceEarlyInit();
      this.eventBus = new EventBus();
      this.eventBus.register(this);
      this.draggableManager = new iII_iII_1lI_1l1_I1l1_II1_1();
      this.hudManager = new lil_I__iiiI1__IlIIl1i1IIliII_();
      this.notificationManager = new liIiI__lIlIiI1IIil1IiI_1_1l();
      this.moduleManager = new i1_lII1i1l_1_ii1_l_IiillllIllII();
      this.moduleManager.init();
      this.hudManager.init();
      this.configManager = new liIl_i_ii1__ll_1i_IIiiI1I1I__();
      this.configManager.init();
      this.capesManager = new i1_i1_I_lIIi_1_I_l1_II_iII111i_();
      this.capesManager.init();
      if (!this.configManager.loadAutoConfig()) {
         this.configManager.saveAutoConfig();
      }

      this.language = iiili11l1I_1I1_ll_l111_l.RU;
      this.commandManager = new l_I_1i1_lIi1_l11l_1_1l1_1iIiI();
      this.commandManager.initCommands();
      this.friendManager = new i_Iiiiii11111llili_1_lIi1I_ll1_();
      this.wayPointsManager = new l1iliI1iIi1li1IIIl1iI1Iil();
      this.macrosManager = new lII_11liI_1Iil_iIiI1i_llil_1__();
      this.macrosManager.init();
      l__11IIIii1I__I1i1l1Ii11iI_11l.ll1ilI_1il1_iI1__I__i1_i_1lI1();
      System.out.println("IAS loaded from: " + String.valueOf(liI_IillIi_I1i1Ii_IliIliliII1.class.getProtectionDomain().getCodeSource().getLocation()));
      liI_IillIi_I1i1Ii_IliIliliII1.II_iIlIi1i____l_l_ii_1I(Path.of(System.getProperty("user.home"), "phantom"), (new File(class_310.method_1551().field_1697, "configs")).toPath(), "1.21.4", "Fabric", "3.2.1", "1.21.1");
      ResourceManagerHelper.get(class_3264.field_14188).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
         public static final char[][] lI_lllIli_l1Ii1_lIlI1i1_1__I_ = new char[(825688064 | 5874) ^ 825693872][];

         public class_2960 getFabricId() {
            int var2 = System.identityHashCode((Object)null);
            if ((var2 * (var2 + 1) & 1) != 0) {
               throw new Error("unreachable");
            } else {
               String var10000 = lI_1i1_1iIII1IIIi_I1IIlIl(352166016 * -1212040977 + -1688524672);
               if (var10000 == null) {
                  byte[] var3 = new byte[-1752332834 * 1973095541 + -1767806561];
                  var3[-241078050 * -2126181125 + 999681622] = (byte)(-2092431056 * -1429931003 + -1647537593);
                  var3[~216508575 - -1599115603 ^ 1382607026] = (byte)((-203882496 | 20931) ^ -203861534);
                  var3[(1807220736 | '裆') ^ 1807255748] = (byte)(-926558511 * 919305975 + 2074598908);
                  var3[-341214318 * 467587075 + -100602547] = (byte)(~-913016716 - 241080587 ^ 671936133);
                  var3[(198836224 | 16965) ^ 198853185] = (byte)(~-586544162 - -1676946602 ^ -2031476499);
                  var3[~177848024 - 2021800728 ^ 2095318538] = (byte)((-337117184 | '\uf148') ^ -337055468);
                  var3[-2136931469 * 1679416809 + -1421961893] = (byte)(-1487852036 * 1594655261 + 648270555);
                  var3[(-23855104 | '鿱') ^ -23814154] = (byte)(~69229166 - -746974074 ^ 677744994);
                  var3[~-86682961 - -1282599766 ^ 1369282734] = (byte)((1760034816 | '끒') ^ 1760079937);
                  var3[(1899626496 | '\uf3b3') ^ 1899688890] = (byte)(1678672387 * -1555036743 + -552993526);
                  var3[~-298002484 - 478648430 ^ -180645937] = (byte)(-1764567994 * 1464846337 + 1740405703);
                  var3[~156223114 - 1118615491 ^ -1274838599] = (byte)(650710687 * -442643675 + -1009398184);
                  var3[~854039066 - 1490841765 ^ 1950086476] = (byte)(-862454971 * 64003633 + 314855771);
                  var3[(1555496960 | '\ue36b') ^ 1555555174] = (byte)(~1788481365 - -1134715733 ^ 653765751);
                  var3[(-1798569984 | 27936) ^ -1798542034] = (byte)((-1188364288 | 21761) ^ 1188342521);
                  var3[1671390421 * 509975565 + 292361534] = (byte)(515990205 * -1511501267 + 1511594651);
                  var3[~1264478875 - -1391716492 ^ 127237600] = (byte)(-442480740 * -1216843969 + -379470767);
                  var3[(-1656225792 | '궅') ^ -1656181356] = (byte)(~80321240 - 575628148 ^ -655949410);
                  var3[(1253965824 | 12592) ^ 1253978402] = (byte)(~1877662899 - 2062729638 ^ 354574825);
                  var3[1766473983 * -1710069311 + 846096596] = (byte)(~1717433151 - -1864851070 ^ 147417879);
                  var3[~-1983374836 - -1390016892 ^ -921575557] = (byte)((1588854784 | '茕') ^ 1588888374);
                  var10000 = l__I_liiI_11I1iil1_illI1i(var3, (-314310656 | '컯') ^ -314257681);
               }

               Object var10001 = null;
               Thread.currentThread().hashCode();
               return Phantom.id(var10000);
            }
         }

         public void method_14491(class_3300 var1) {
            int var3 = System.identityHashCode((Object)null);
            if ((var3 * (var3 + 1) & 1) != 0) {
               throw new Error("unreachable");
            } else {
               Object var10000 = null;
               Thread.currentThread().hashCode();
               ii_llIII1Ii1iIlll11_11_.llliIIl_Iill_1IIIl1Ii_l1_1i();
               var10000 = null;
               Thread.currentThread().hashCode();
               Phantom var5 = Phantom.getInstance();
               Object var10001 = null;
               Thread.currentThread().hashCode();
               i1_i1_I_lIIi_1_I_l1_II_iII111i_ var6 = var5.getCapesManager();
               var10001 = null;
               Thread.currentThread().hashCode();
               lI1l1Ii_illiiil111_1i_i_l.Il_ilIIi_1ii1__iIIl1_II(var6);
            }
         }

         // $FF: synthetic method
         private static String lI_1i1_1iIII1IIIi_I1IIlIl(int var0) {
            char[][] var1 = lI_lllIli_l1Ii1_lIlI1i1_1__I_;
            if (var1 != null && var0 >= 0 && var0 < var1.length) {
               char[] var2 = var1[var0];
               if (var2 != null) {
                  int var3 = 743045913 ^ var0 ^ 'ꕚ';
                  char[] var4 = new char[var2.length];

                  for(int var5 = 0; var5 < var2.length; ++var5) {
                     var4[var5] = (char)(var2[var5] ^ var3 + var5 * 17);
                  }

                  String var6 = new String(var4);
                  Arrays.fill(var4, '\u0000');
                  return var6;
               }
            }

            return null;
         }

         // $FF: synthetic method
         private static String l__I_liiI_11I1iil1_illI1i(byte[] var0, int var1) {
            String var2 = lI_1i1_1iIII1IIIi_I1IIlIl(var1);
            if (var2 != null) {
               return var2;
            } else {
               int var14 = var0.length - 4;
               if (var14 <= 0) {
                  return new String(new byte[0], StandardCharsets.UTF_8);
               } else {
                  int var3 = (var0[0] & 255) << 24 | (var0[1] & 255) << 16 | (var0[2] & 255) << 8 | var0[3] & 255;
                  int var4 = 743045913 ^ var3 ^ var14 * -1640531527 ^ 1831565813;
                  int var5 = (var3 ^ 1063460864 ^ var14 * -2048144789) + 324508639;
                  byte[] var6 = new byte[var14];

                  for(int var7 = 0; var7 < var14; ++var7) {
                     int var15 = var4 ^ var4 << 13;
                     int var16 = var15 ^ var15 >>> 17;
                     var4 = var16 ^ var16 << 5;
                     var5 = var5 * 1103515245 + 12345;
                     int var8 = var4 + var5 ^ (var5 >>> 16 | var5 << 16);
                     int var9 = (var8 ^ var7 * 37 ^ var14 * 19 ^ var3 >>> ((var7 & 3) << 3) & 255) & 255;
                     int var10 = (var8 >>> 8 ^ var7) & 7;
                     int var11 = var7 == 0 ? (var3 ^ 743045913) & 255 : var0[var7 + 3] & 255;
                     int var12 = (var0[var7 + 4] ^ var9 ^ 165) & 255;
                     int var13 = var10 != 0 ? (var12 >>> var10 | var12 << 8 - var10) & 255 : var12;
                     var6[var7] = (byte)(var13 - var11 - var9 & 255);
                  }

                  String var17 = new String(var6, StandardCharsets.UTF_8);
                  char[] var18 = var17.toCharArray();
                  int var19 = 743045913 ^ var1 ^ 'ꕚ';
                  char[] var20 = new char[var18.length];

                  for(int var21 = 0; var21 < var18.length; ++var21) {
                     var20[var21] = (char)(var18[var21] ^ var19 + var21 * 17);
                  }

                  if (lI_lllIli_l1Ii1_lIlI1i1_1__I_ != null) {
                     lI_lllIli_l1Ii1_lIlI1i1_1__I_[var1] = var20;
                  }

                  Arrays.fill(var18, '\u0000');
                  Arrays.fill(var6, (byte)0);
                  Arrays.fill(var0, (byte)0);
                  return var17;
               }
            }
         }
      });
      LOGGER.info((String)"{} initialized", (Object)"Phantom");
   }

   private void setupLogFilter() {
      try {
         LoggerContext context = (LoggerContext)LogManager.getContext(false);
         Configuration configuration = context.getConfiguration();
         Filter filter = new AbstractFilter() {
            public static final char[][] II111i_li_IilllI_i_IiI11i1i_ = new char[~-1732333657 - 839008133 ^ 893325489][];

            public Filter.Result filter(LogEvent var1) {
               int var3 = System.identityHashCode((Object)null);
               if ((var3 * (var3 + 1) & 1) != 0) {
                  throw new Error("unreachable");
               } else {
                  if (var1 != null) {
                     Object var10001 = null;
                     Thread.currentThread().hashCode();
                     if (var1.getMessage() != null) {
                        var10001 = null;
                        Thread.currentThread().hashCode();
                        Message var10000 = var1.getMessage();
                        var10001 = null;
                        Thread.currentThread().hashCode();
                        String var5 = var10000.getFormattedMessage();
                        if (var5 == null) {
                           return Result.NEUTRAL;
                        }

                        var10001 = null;
                        Thread.currentThread().hashCode();
                        String var4 = var5.trim();
                        String var9 = i1l1lIl1l111I1_lii_lIIIlIlI(93679516 * -414757655 + -711517436);
                        if (var9 == null) {
                           byte[] var10 = new byte[(-157876224 | 11305) ^ -157864923];
                           var10[-640221920 * -72234881 + -1787335392] = (byte)(933223303 * -79976237 + 1242593187);
                           var10[(-1174339584 | '賞') ^ -1174303521] = (byte)(-1206415535 * 790476425 + -1605126226);
                           var10[~-1566742436 - 1772185227 ^ -205442790] = (byte)((1730019328 | '믃') ^ -1730067380);
                           var10[~1239831674 - 2021509332 ^ 1033626290] = (byte)(~-723132804 - -1235569870 ^ -1958702642);
                           var10[~-1371815613 - 1030882728 ^ 340932880] = (byte)(169079817 * 600827577 + 1007521685);
                           var10[-1079188360 * 678434113 + 1910252941] = (byte)((-1626865664 | 15540) ^ 1626850135);
                           var10[1598340795 * -867447517 + 135605621] = (byte)((308215808 | 29369) ^ -308245150);
                           var10[1044724092 * -357575113 + 491716195] = (byte)(1882290352 * 127481083 + 30075737);
                           var10[~-2086845714 - 424668292 ^ 1662177413] = (byte)((-251133952 | 10833) ^ 251123085);
                           var10[-1529717786 * -91944213 + 550545383] = (byte)(-138265687 * -161398049 + 425948546);
                           var10[-649465761 * 1873474979 + 315295885] = (byte)(~-1728585411 - 1656425376 ^ -72160078);
                           var10[(-47906816 | '裑') ^ -47871782] = (byte)(-632285324 * 148364979 + -1824718478);
                           var9 = I_1_1Il_1l_1l_I1l_I1l__(var10, 220863458 * 1760190623 + -1144000862);
                        }

                        Object var10002 = null;
                        Thread.currentThread().hashCode();
                        if (!var4.equals(var9)) {
                           var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~1713663593 - 355683928 ^ -2069347521);
                           if (var9 == null) {
                              byte[] var12 = new byte[~798036951 - -532578793 ^ -265458175];
                              var12[589909172 * 1414810617 + 2018224364] = (byte)(-149128750 * -1280701563 + -1450922039);
                              var12[(-2088763392 | '\uec20') ^ -2088702943] = (byte)(~-96621020 - -1818068048 ^ -1914689059);
                              var12[1542760869 * -483308455 + -1644230235] = (byte)(1980867526 * -1067419679 + 488506734);
                              var12[-677776642 * -1109341887 + 1038081925] = (byte)(-1006155289 * -792298247 + -193775115);
                              var12[~1122985024 - -2054573008 ^ 931587979] = (byte)(-615281443 * -1434057477 + 691236631);
                              var12[(-2008154112 | 30017) ^ -2008124092] = (byte)(~-1531435038 - 1045328892 ^ 486106180);
                              var12[(-177471488 | 24916) ^ -177446574] = (byte)(490592157 * 1510069487 + 1930997920);
                              var12[1154530245 * -733663225 + 989654436] = (byte)(~1748543241 - 407033592 ^ -2139390425);
                              var12[~-1384612111 - 30718230 ^ 1353893872] = (byte)((1091108864 | '蛙') ^ -1091143415);
                              var12[(829292544 | '녌') ^ 829337925] = (byte)(-764399933 * 1100323361 + -1461081495);
                              var12[-860422832 * -877974151 + 1854616890] = (byte)((-1580662784 | 4481) ^ -1580658217);
                              var12[~979111166 - -1720609592 ^ 741498418] = (byte)((-1899560960 | 30233) ^ -1899530673);
                              var12[~790994458 - 645795432 ^ -1436789903] = (byte)(~1570648901 - 82212267 ^ -1652861178);
                              var12[(-1467809792 | '\uecdd') ^ -1467749168] = (byte)(~257009620 - 1121957753 ^ -1378967371);
                              var12[-427946885 * -567769503 + 310174579] = (byte)(-1223626950 * -1601422291 + -969123160);
                              var12[-1497256821 * -921081721 + 1697811138] = (byte)(766558999 * -1094863953 + 298225290);
                              var9 = I_1_1Il_1l_1l_I1l_I1l__(var12, (737476608 | 8059) ^ 737484666);
                           }

                           var10002 = null;
                           Thread.currentThread().hashCode();
                           if (!var4.equals(var9)) {
                              var9 = i1l1lIl1l111I1_lii_lIIIlIlI((-1021509632 | '\ueefb') ^ -1021448455);
                              if (var9 == null) {
                                 byte[] var14 = new byte[~1763659188 - -1856016618 ^ 92357412];
                                 var14[(-1664090112 | 32555) ^ -1664057557] = (byte)((1271267328 | 5905) ^ -1271273289);
                                 var14[(-845414400 | 'ꫠ') ^ -845370655] = (byte)(~-175641528 - 410917274 ^ 235275649);
                                 var14[1071539328 * 1461088179 + -957597054] = (byte)((-362283008 | 24408) ^ 362258586);
                                 var14[-2082281080 * 1305400999 + 1951529035] = (byte)((845152256 | 22696) ^ -845174992);
                                 var14[(787087360 | '鳯') ^ 787127531] = (byte)(1564568787 * 1916189523 + 248092284);
                                 var14[~1353576222 - -727443519 ^ -626132699] = (byte)((-768344064 | 'ꦷ') ^ -768300560);
                                 var14[1274279434 * 115375381 + 555347764] = (byte)(~-1072279545 - -389568164 ^ -1461847776);
                                 var14[144904286 * -57921039 + 754162057] = (byte)((-861732864 | 20493) ^ 861712342);
                                 var14[~-1483453060 - -1658135228 ^ -1153379017] = (byte)((1959723008 | '끖') ^ -1959768179);
                                 var14[332465879 * -954017437 + 500177892] = (byte)((1734868992 | '꼲') ^ 1734913794);
                                 var14[~842835270 - 1705255020 ^ 1746876999] = (byte)(~1978438166 - 1763495197 ^ 553033930);
                                 var14[(1330053120 | 31479) ^ 1330084604] = (byte)(-678006357 * -1343439591 + 834643710);
                                 var14[-35844233 * 1660898599 + -534586901] = (byte)(~1157507172 - -1325930904 ^ 168423803);
                                 var14[-1965605680 * -977480023 + -1303184707] = (byte)(339438639 * 1136103923 + -2135379978);
                                 var14[~559855292 - -90036066 ^ -469819221] = (byte)((326172672 | '\ue6f5') ^ -326231779);
                                 var14[~-1768128590 - 539015643 ^ 1229112957] = (byte)(-218089703 * 189947887 + 1431849113);
                                 var14[1909585023 * 1200362879 + 1632979471] = (byte)(-611992779 * 7010857 + -1160691074);
                                 var9 = I_1_1Il_1l_1l_I1l_I1l__(var14, ~-1244112538 - -1580192872 ^ -1470661885);
                              }

                              var10002 = null;
                              Thread.currentThread().hashCode();
                              if (!var4.equals(var9)) {
                                 var9 = i1l1lIl1l111I1_lii_lIIIlIlI(-1372232504 * -1691069167 + 593356475);
                                 if (var9 == null) {
                                    byte[] var16 = new byte[-40099046 * 1498149663 + -1037561365];
                                    var16[1801173592 * -957557113 + -608490600] = (byte)(~-474313668 - 750092413 ^ -275778776);
                                    var16[921661841 * 684292697 + -137794920] = (byte)((-418578432 | 124) ^ 418578351);
                                    var16[~-1423721967 - -1436357398 ^ -1434887930] = (byte)(1885535875 * 2026610541 + 1877296415);
                                    var16[~-559587456 - -959085412 ^ 1518672864] = (byte)((-1992818688 | '\ue764') ^ 1992759514);
                                    var16[(2111569920 | 31956) ^ 2111601872] = (byte)(1850994174 * -748076983 + -1755077927);
                                    var16[(-1139277824 | 6477) ^ -1139271352] = (byte)(-2062370981 * 989240241 + -644275887);
                                    var16[~-850027323 - -1762078478 ^ -1682861490] = (byte)(~470817237 - 1443508672 ^ -1914325899);
                                    var16[~-442687236 - -1493329256 ^ 1936016492] = (byte)(260422357 * 1516593515 + 1061630895);
                                    var16[~2137540582 - -1586892954 ^ -550647621] = (byte)(-536811101 * -1222169941 + 473575923);
                                    var16[~2137777416 - -1012640665 ^ -1125136743] = (byte)((-2131361792 | 'ﲺ') ^ -2131297145);
                                    var16[~-1230697159 - -1382857743 ^ -1681412385] = (byte)((1380188160 | 21526) ^ 1380209727);
                                    var16[~-1762861537 - -445743024 ^ -2086362725] = (byte)(~972469528 - -2135696772 ^ 1163227219);
                                    var16[~-378387506 - -940444829 ^ 1318832322] = (byte)(87745411 * -447084259 + 726048513);
                                    var16[-994468741 * 314720829 + 171442366] = (byte)(~439002303 - 1288654363 ^ -1727656662);
                                    var16[~146472546 - -1587871940 ^ 1441399407] = (byte)((1682374656 | '飯') ^ -1682438738);
                                    var16[~-1896925437 - 1759648977 ^ 137276452] = (byte)(~-727717119 - -1293806635 ^ 2021523807);
                                    var16[(1179123712 | '겢') ^ 1179167922] = (byte)(-1799962125 * 1592671793 + -1138620923);
                                    var9 = I_1_1Il_1l_1l_I1l_I1l__(var16, 919578910 * 378012455 + -881728911);
                                 }

                                 var10002 = null;
                                 Thread.currentThread().hashCode();
                                 if (!var4.equals(var9)) {
                                    var9 = i1l1lIl1l111I1_lii_lIIIlIlI((-1713963008 | 10478) ^ -1713952534);
                                    if (var9 == null) {
                                       byte[] var18 = new byte[-1797043243 * 237625041 + -1953537752];
                                       var18[(-97058816 | 31736) ^ -97027080] = (byte)(~2129225589 - 364405651 ^ -1801336046);
                                       var18[(-887357440 | '\uf2e9') ^ -887295256] = (byte)((-1294204928 | 8513) ^ 1294196417);
                                       var18[767050406 * 2077927517 + -185149004] = (byte)(~-1840055657 - 2062196238 ^ 222140637);
                                       var18[~735933753 - 860615648 ^ -1596549403] = (byte)(~-1790228121 - -1788116251 ^ -716622884);
                                       var18[~1192730388 - -881802724 ^ -310927669] = (byte)((1200947200 | '뱢') ^ -1200995396);
                                       var18[(-393281536 | '쟽') ^ -393230344] = (byte)(-1502344833 * 716462729 + -1656470214);
                                       var18[(497680384 | 19718) ^ 497700096] = (byte)(~343615137 - 932073128 ^ -1275688196);
                                       var18[(908328960 | '麎') ^ 908369545] = (byte)(~1419573714 - -1256731898 ^ -162841770);
                                       var18[(-336789504 | 3285) ^ -336786211] = (byte)(~640242358 - -1942407622 ^ -1302165305);
                                       var18[~944098962 - -1825954722 ^ 881855750] = (byte)((-1486094336 | 'ꐽ') ^ -1486052318);
                                       var18[1028009464 * 1689212829 + 1171162866] = (byte)(~8149258 - 64245092 ^ -72394327);
                                       var18[(1432158208 | '싲') ^ 1432208121] = (byte)(299501153 * -1982287737 + 1724642051);
                                       var18[-1069596303 * 1849438275 + -290410631] = (byte)(-1270339885 * 298082855 + 765022215);
                                       var9 = I_1_1Il_1l_1l_I1l_I1l__(var18, 60496644 * -1297144169 + -1715177304);
                                    }

                                    var10002 = null;
                                    Thread.currentThread().hashCode();
                                    if (!var4.equals(var9)) {
                                       var9 = i1l1lIl1l111I1_lii_lIIIlIlI((1406337024 | '\ud8e2') ^ 1406392551);
                                       if (var9 == null) {
                                          byte[] var20 = new byte[(871563264 | '숦') ^ 871612975];
                                          var20[1896400951 * -1453784231 + -1350909983] = (byte)(-1627950514 * -294134639 + -1453639165);
                                          var20[-2132921079 * 1111974973 + 1993791708] = (byte)(1363611335 * 624571579 + 369215693);
                                          var20[~1930839743 - -1420096094 ^ -510743652] = (byte)(~-1017265541 - 914218489 ^ -103047113);
                                          var20[2023096827 * 1548055859 + -1366367230] = (byte)(~1477497515 - -1052617949 ^ 424879537);
                                          var20[-1320041693 * -2077920081 + 2090909463] = (byte)(427818417 * 1957710071 + -931630591);
                                          var20[~1994593137 - -624346526 ^ -1370246615] = (byte)(1905398964 * 420218937 + -1721515107);
                                          var20[-368150294 * 699823047 + 504554528] = (byte)((1956839424 | '덭') ^ 1956885292);
                                          var20[470100632 * -71068747 + -1088710001] = (byte)((-223739904 | '趸') ^ -223703568);
                                          var20[-1003342622 * 1458102031 + -332527414] = (byte)((855965696 | 'ꏬ') ^ 856007640);
                                          var9 = I_1_1Il_1l_1l_I1l_I1l__(var20, 1817962390 * -1821321761 + -1331595685);
                                       }

                                       var10002 = null;
                                       Thread.currentThread().hashCode();
                                       if (!var4.equals(var9)) {
                                          var9 = i1l1lIl1l111I1_lii_lIIIlIlI((81002496 | '\ud80f') ^ 81057801);
                                          if (var9 == null) {
                                             byte[] var22 = new byte[1384797601 * -535710307 + 49129292];
                                             var22[(259784704 | 1011) ^ 259785715] = (byte)((-99221504 | 20759) ^ -99200654);
                                             var22[-33816918 * 1714504313 + -2118988377] = (byte)((-617283584 | 15442) ^ -617268175);
                                             var22[~1173428605 - -644418517 ^ -529010091] = (byte)(718787999 * -357192979 + -1561811432);
                                             var22[(-521076736 | '饮') ^ -521037459] = (byte)((-1762197504 | '\uf785') ^ 1762134022);
                                             var22[~411615031 - -1845059041 ^ 1433444013] = (byte)((-1634533376 | '精') ^ 1634469248);
                                             var22[-2025002796 * -1448322643 + 1906635969] = (byte)(~-316379263 - 959888619 ^ 643509344);
                                             var22[~1914008462 - -1553095199 ^ -360913258] = (byte)((1688600576 | '렧') ^ 1688647755);
                                             var22[~-9514193 - -856177541 ^ 865691730] = (byte)(~-251083093 - -238850929 ^ -489934022);
                                             var22[(-603455488 | '\uf4ad') ^ -603392859] = (byte)((1048903680 | 26363) ^ -1048929970);
                                             var9 = I_1_1Il_1l_1l_I1l_I1l__(var22, (-1886322688 | 'ꮱ') ^ -1886278729);
                                          }

                                          var10002 = null;
                                          Thread.currentThread().hashCode();
                                          if (!var4.equals(var9)) {
                                             var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~1385903229 - -1779586697 ^ 393683468);
                                             if (var9 == null) {
                                                byte[] var24 = new byte[1850684000 * 919873345 + -172517953];
                                                var24[-829373169 * 396124645 + -273610091] = (byte)(85510444 * -898811681 + -1012194588);
                                                var24[(160825344 | 27093) ^ 160852436] = (byte)((-654114816 | '꽈') ^ 654069984);
                                                var24[-1996924994 * 1680536713 + 1841492820] = (byte)(-1657375300 * -547617477 + -1600769497);
                                                var24[-216283160 * -696749471 + -558632677] = (byte)(-262066868 * 1379222597 + -1391814054);
                                                var24[(1903296512 | 13767) ^ 1903310275] = (byte)(-1049981629 * 1144894505 + -1515895851);
                                                var24[1239870185 * 333563085 + 1148354416] = (byte)(-525547276 * -1440704607 + -1571966326);
                                                var24[(-1159725056 | '뗣') ^ -1159678491] = (byte)(~-1071376188 - -260292925 ^ -1331669109);
                                                var24[(-501547008 | '\uf011') ^ -501485546] = (byte)(-1573143612 * -617694315 + -2015655174);
                                                var24[(-1368260608 | 1682) ^ -1368258918] = (byte)(~-1037198760 - -417008930 ^ -1454207647);
                                                var24[(1409417216 | 99) ^ 1409417322] = (byte)(1891215976 * -152165117 + 2072115333);
                                                var24[(1218576384 | '쪎') ^ 1218628228] = (byte)((-1372454912 | '섨') ^ -1372405379);
                                                var24[-547778898 * 414530093 + 1105756021] = (byte)(~355446958 - 955094729 ^ -1310541679);
                                                var24[-2069846120 * 772294877 + -982740524] = (byte)(~-1012392998 - 1155434094 ^ -143041074);
                                                var24[~431936720 - 1545008564 ^ -1976945290] = (byte)(-1007616907 * 1995382673 + 917755647);
                                                var24[~-67040946 - -381100765 ^ 448141696] = (byte)((201981952 | '뙙') ^ -202028640);
                                                var24[(331415552 | '\ued35') ^ 331476282] = (byte)((-1732444160 | '\uf227') ^ 1732382115);
                                                var24[(1557790720 | '납') ^ 1557835961] = (byte)(827403558 * 1638545667 + -138214778);
                                                var24[(253493248 | 21189) ^ 253514452] = (byte)(-739527365 * 1988237109 + -1191891514);
                                                var24[1401694188 * 648310831 + -1130533954] = (byte)((1249968128 | '쨻') ^ 1250019862);
                                                var24[~-729024169 - 1216055329 ^ -487031148] = (byte)(590512134 * 2117940111 + -774157687);
                                                var24[~-1357052925 - -1604903566 ^ -1333010786] = (byte)(696058435 * -829103151 + 255049318);
                                                var24[-443162242 * 1631336065 + 442551191] = (byte)(-119779940 * 32576233 + 1445750014);
                                                var24[-1881855131 * -1087575497 + 1865243491] = (byte)(~-1166970328 - -100500214 ^ 1267470468);
                                                var24[(-1186463744 | 14176) ^ -1186449545] = (byte)((1499725824 | '됱') ^ -1499771907);
                                                var24[~1389670865 - -514910149 ^ -874760725] = (byte)((1559887872 | 19290) ^ -1559907181);
                                                var24[447844060 * -1658326821 + 78154213] = (byte)(~-2056945937 - 597863668 ^ 1459082315);
                                                var24[-1835175194 * -241694913 + -561221760] = (byte)(~-1703964955 - 803638368 ^ 900326651);
                                                var24[~572616808 - 1016543148 ^ -1589159952] = (byte)(-404868137 * 1154559219 + 1218762485);
                                                var24[(-1644822528 | 28964) ^ -1644793544] = (byte)(1868287750 * 1437479425 + -299825897);
                                                var24[(-1681522688 | 22459) ^ -1681500250] = (byte)(~510125318 - -1913384932 ^ 1403259616);
                                                var24[1772851954 * -791608427 + -455564476] = (byte)((-1263075328 | '\uf113') ^ -1263013560);
                                                var9 = I_1_1Il_1l_1l_I1l_I1l__(var24, ~-1389682811 - 1779270670 ^ -389587861);
                                             }

                                             var10002 = null;
                                             Thread.currentThread().hashCode();
                                             if (!var5.contains(var9)) {
                                                var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~1319203502 - 1819751848 ^ 1156011937);
                                                if (var9 == null) {
                                                   byte[] var26 = new byte[(-1368522752 | 21772) ^ -1368500962];
                                                   var26[(1521614848 | 27171) ^ 1521642019] = (byte)(793163286 * -473395153 + -932695608);
                                                   var26[~164115347 - 610668122 ^ -774783469] = (byte)((1328611328 | 11125) ^ -1328622343);
                                                   var26[(-34799616 | 23352) ^ -34776262] = (byte)(-1708885489 * 130297729 + -994460198);
                                                   var26[-259419660 * -2078946751 + -1763481329] = (byte)(1780140774 * -645111757 + -456436713);
                                                   var26[(241696768 | 22553) ^ 241719325] = (byte)(1402800492 * -2116576679 + 554222038);
                                                   var26[1357077797 * 359418877 + 510073716] = (byte)((506396672 | 10973) ^ 506407669);
                                                   var26[1578312803 * -1530777307 + -1103181129] = (byte)((1168244736 | '힖') ^ 1168299968);
                                                   var26[269570944 * 431636657 + 1680163975] = (byte)(~-305603082 - -542961089 ^ -848564116);
                                                   var26[~-1625292727 - -2070697781 ^ -598976797] = (byte)(-899639275 * -1974596291 + 920433096);
                                                   var26[287254243 * 1448895129 + -1679106978] = (byte)(~-757278543 - -804848494 ^ 1562127091);
                                                   var26[1915564078 * 1713039453 + 1497354068] = (byte)((-934805504 | 17196) ^ -934788342);
                                                   var26[~1361995055 - -401546666 ^ -960448399] = (byte)(~-451273758 - 791954016 ^ -340680286);
                                                   var26[552025538 * -1425787835 + -1676086590] = (byte)(~-1113629771 - -747759673 ^ 1861389463);
                                                   var26[(327876608 | 18125) ^ 327894720] = (byte)(576502087 * -312833581 + 813058947);
                                                   var26[~818275067 - 612080300 ^ -1430355370] = (byte)(~-815587226 - 2086008953 ^ -1270421750);
                                                   var26[-189286763 * 344384391 + -700802948] = (byte)(~1560134188 - 452327139 ^ -2012461333);
                                                   var26[-1604858893 * 1076123903 + -1404808957] = (byte)((-707067904 | 11514) ^ 707056416);
                                                   var26[(587464704 | '骉') ^ 587504280] = (byte)(-1680774919 * 1318046185 + 1105523760);
                                                   var9 = I_1_1Il_1l_1l_I1l_I1l__(var26, 414504423 * -956539677 + -513585101);
                                                }

                                                var10002 = null;
                                                Thread.currentThread().hashCode();
                                                if (!var5.contains(var9)) {
                                                   var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~381640287 - 1302970833 ^ -1684611130);
                                                   if (var9 == null) {
                                                      byte[] var28 = new byte[(858259456 | '\uddee') ^ 858316281];
                                                      var28[(1392574464 | 18488) ^ 1392592952] = (byte)(-1714250839 * 821551451 + 1464082868);
                                                      var28[1707481914 * 1205829603 + -1404168813] = (byte)((-1630142464 | '馋') ^ 1630103139);
                                                      var28[(905445376 | 24184) ^ 905469562] = (byte)(1706150842 * -1298170817 + -32848654);
                                                      var28[-1995752133 * -1288278475 + -1595213620] = (byte)(~-1053034970 - 1966436828 ^ -913401883);
                                                      var28[449423502 * 527769575 + 767145442] = (byte)(1844684129 * -2081801829 + 1610499351);
                                                      var28[(632553472 | '롥') ^ 632600672] = (byte)((753074176 | '蜮') ^ -753108827);
                                                      var28[~-54393164 - -776589593 ^ 830982754] = (byte)((1896939520 | 29698) ^ -1896969310);
                                                      var28[-99673172 * 1593874421 + -231206805] = (byte)(~-807554767 - 1921293371 ^ -1113738562);
                                                      var28[(-1012006912 | 17780) ^ -1011989124] = (byte)(1188151259 * 1243125239 + 1282239780);
                                                      var28[(336527360 | 6088) ^ 336533441] = (byte)((-1621295104 | '\uab18') ^ -1621251318);
                                                      var28[~1980358794 - 1039112047 ^ 1275496460] = (byte)((228655104 | 28177) ^ -228683300);
                                                      var28[(-480837632 | '衅') ^ -480802738] = (byte)((-335478784 | 4904) ^ 335473822);
                                                      var28[~994505662 - -1099291755 ^ 104786080] = (byte)((1901723648 | 'ꉫ') ^ -1901765167);
                                                      var28[1364364109 * 440719983 + -1608382550] = (byte)((-1166278656 | 20174) ^ -1166258457);
                                                      var28[~465085365 - -1472903413 ^ 1007818033] = (byte)((-1680211968 | 20103) ^ 1680191869);
                                                      var28[(-1998127104 | 23554) ^ -1998103539] = (byte)(-1256380384 * -18249017 + 966037214);
                                                      var28[~-1072937922 - 872046783 ^ 200891154] = (byte)(1359782182 * -629844381 + 1738336766);
                                                      var28[(1257046016 | 11726) ^ 1257057759] = (byte)(1184922697 * 1492272667 + 370470499);
                                                      var28[~622461856 - -1529155644 ^ 906693769] = (byte)((1719074816 | 21555) ^ -1719096422);
                                                      var28[(-461570048 | 'ꏃ') ^ -461528112] = (byte)((1457127424 | 3284) ^ 1457130675);
                                                      var28[~618398977 - -1167591516 ^ 549192526] = (byte)(~1943159283 - -164220225 ^ -1778939115);
                                                      var28[~147980163 - 764743228 ^ -912723371] = (byte)(-600409454 * -1795688837 + 757911431);
                                                      var28[(-1087307776 | '깺') ^ -1087263124] = (byte)(1966334648 * 2134727839 + 1098125256);
                                                      var9 = I_1_1Il_1l_1l_I1l_I1l__(var28, -1669560739 * -1613834117 + -587794086);
                                                   }

                                                   var10002 = null;
                                                   Thread.currentThread().hashCode();
                                                   if (!var5.contains(var9)) {
                                                      var9 = i1l1lIl1l111I1_lii_lIIIlIlI(862142452 * 1801209181 + -1660616602);
                                                      if (var9 == null) {
                                                         byte[] var30 = new byte[-1408479633 * -909890453 + -983293009];
                                                         var30[-2124859627 * -1451963507 + 816513647] = (byte)(~-1866008227 - -1950369060 ^ 478590042);
                                                         var30[854964907 * 996434187 + 1609183144] = (byte)(31341420 * -784189921 + 1739982366);
                                                         var30[(295501824 | 3447) ^ 295505269] = (byte)(~-504927220 - 1724806445 ^ -1219879228);
                                                         var30[1006725148 * -96363467 + -249011657] = (byte)(-1439840850 * -653586415 + -635371756);
                                                         var30[-853486809 * 1955582881 + 1367936893] = (byte)((1612840960 | '\udf2e') ^ -1612898116);
                                                         var30[~1281003794 - -913644904 ^ -367358896] = (byte)((58851328 | '驪') ^ -58915251);
                                                         var30[-1836787796 * 336707083 + 1506612130] = (byte)(1938265944 * 97391755 + 750646180);
                                                         var30[(-243662848 | 17020) ^ -243645829] = (byte)((1281425408 | 'ﲞ') ^ -1281490144);
                                                         var30[~709130228 - 586862671 ^ -1295992908] = (byte)((834207744 | '貢') ^ -834243824);
                                                         var30[-898908360 * -1634962365 + 277320801] = (byte)((129892352 | 22147) ^ 129914531);
                                                         var30[533702466 * 623794827 + 563542836] = (byte)((-409731072 | 18423) ^ 409712744);
                                                         var30[(-920387584 | 26432) ^ -920361141] = (byte)(~168542399 - -111743095 ^ 56799257);
                                                         var30[(-1385693184 | 9073) ^ -1385684099] = (byte)(659387909 * -1281018333 + -1186279693);
                                                         var30[(1958215680 | 8426) ^ 1958224103] = (byte)(-1620763113 * 651239369 + -1607493891);
                                                         var30[~-1764984725 - 356123956 ^ 1408860782] = (byte)(~-1902654078 - 563581564 ^ 1339072533);
                                                         var30[(1967128576 | '脭') ^ 1967161634] = (byte)(~-2022288840 - 425398723 ^ -1596890124);
                                                         var30[1613342219 * -1495787755 + 593650729] = (byte)(1446765882 * -245881993 + 1407288428);
                                                         var30[1680031999 * -1207582265 + 127744728] = (byte)(~285220838 - 414601822 ^ 699822691);
                                                         var30[(175636480 | '︁') ^ 175701523] = (byte)(~1373597949 - -475626135 ^ -897971734);
                                                         var30[(476577792 | '苗') ^ 476611268] = (byte)((-1565458432 | 9715) ^ -1565448815);
                                                         var9 = I_1_1Il_1l_1l_I1l_I1l__(var30, -270289348 * -1412934803 + -1793723266);
                                                      }

                                                      var10002 = null;
                                                      Thread.currentThread().hashCode();
                                                      if (!var5.contains(var9)) {
                                                         var9 = i1l1lIl1l111I1_lii_lIIIlIlI(655270570 * -316905377 + 1659146997);
                                                         if (var9 == null) {
                                                            byte[] var32 = new byte[(-2092040192 | 20489) ^ -2092019711];
                                                            var32[~1316630486 - -939665465 ^ -376965022] = (byte)((-1787691008 | '\uf7ee') ^ 1787627529);
                                                            var32[(-1803419648 | '\udb8e') ^ -1803363441] = (byte)((-1376714752 | 4738) ^ 1376710001);
                                                            var32[~257429207 - -1538398059 ^ 1280968849] = (byte)(1060231338 * -1849416489 + -1098702503);
                                                            var32[~1687177969 - 726988040 ^ 1880801285] = (byte)(-865186561 * 800337961 + -1617306927);
                                                            var32[(-2022113280 | 'ꬄ') ^ -2022069504] = (byte)((309264384 | '쭟') ^ -309316409);
                                                            var32[(2082734080 | 29530) ^ 2082763615] = (byte)((-212336640 | 29584) ^ 212306998);
                                                            var32[~1328140419 - -1541291369 ^ 213150947] = (byte)((-1800994816 | 24239) ^ 1800970519);
                                                            var32[(-1940455424 | '킉') ^ -1940402034] = (byte)((-2098266112 | 'ﻒ') ^ 2098200876);
                                                            var9 = I_1_1Il_1l_1l_I1l_I1l__(var32, -1933293389 * -952843231 + -1448241928);
                                                         }

                                                         var10002 = null;
                                                         Thread.currentThread().hashCode();
                                                         if (!var5.contains(var9)) {
                                                            var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~-1402399403 - 1241737780 ^ 160661626);
                                                            if (var9 == null) {
                                                               byte[] var34 = new byte[(-795410432 | 4187) ^ -795406257];
                                                               var34[(337313792 | 25361) ^ 337339153] = (byte)((-619380736 | '蚁') ^ 619346254);
                                                               var34[-2022072887 * 1138880835 + -990348442] = (byte)(1406454109 * 1430144057 + 714896965);
                                                               var34[-1002967675 * 1363308757 + -196923303] = (byte)(~937459375 - -827814058 ^ 109645361);
                                                               var34[(-397410304 | 26838) ^ -397383467] = (byte)((-1595801600 | 10831) ^ -1595790823);
                                                               var34[-688679398 * 735242545 + -1469146358] = (byte)(~-1600245604 - 1620984185 ^ -20738586);
                                                               var34[~1488169479 - -855189031 ^ -632980454] = (byte)(-1420672115 * 1008996549 + 2040870042);
                                                               var34[(1152581632 | 7503) ^ 1152589129] = (byte)((-1752891392 | '\uf555') ^ 1752828655);
                                                               var34[(1997602816 | 13800) ^ 1997616623] = (byte)((511705088 | 13898) ^ -511719039);
                                                               var34[-1428712124 * 141179273 + -1539507548] = (byte)((505020416 | 10262) ^ 505030675);
                                                               var34[~-610107320 - 715520604 ^ -105413294] = (byte)((1094975488 | '\udc50') ^ -1095031837);
                                                               var34[-147747906 * 1671470937 + 2033936636] = (byte)(~2127076511 - 339523221 ^ 1828367488);
                                                               var34[~-1443015700 - 1438223442 ^ 4792266] = (byte)(~-1620182390 - 1377292404 ^ -242890081);
                                                               var34[~-9633431 - 805630551 ^ -795997133] = (byte)((-402784256 | 30382) ^ -402753916);
                                                               var34[-220475330 * 1197567405 + 810278951] = (byte)(~1506121865 - 978801562 ^ -1810043883);
                                                               var34[~-921014588 - 741683808 ^ 179330773] = (byte)(-574340127 * -280170245 + 1528988211);
                                                               var34[-2084648034 * -997416505 + 1178163773] = (byte)((249430016 | 974) ^ 249431037);
                                                               var34[~1906048650 - 1723743764 ^ 665174897] = (byte)(729524584 * -1709585499 + -825337774);
                                                               var34[(-822280192 | '뷉') ^ -822231592] = (byte)(~1974476918 - 2070664578 ^ 249825809);
                                                               var34[~-2017933475 - -844817279 ^ -1432216525] = (byte)(-1075505210 * -689279745 + -171324988);
                                                               var34[~-2079404847 - -2009297720 ^ -206264715] = (byte)((-850395136 | '캦') ^ 850342213);
                                                               var9 = I_1_1Il_1l_1l_I1l_I1l__(var34, -498425275 * -1223788409 + 2077103529);
                                                            }

                                                            var10002 = null;
                                                            Thread.currentThread().hashCode();
                                                            if (!var5.contains(var9)) {
                                                               var9 = i1l1lIl1l111I1_lii_lIIIlIlI(917996137 * 1132141973 + 972567536);
                                                               if (var9 == null) {
                                                                  byte[] var36 = new byte[449682569 * 87501265 + -509459657];
                                                                  var36[(-831782912 | 25839) ^ -831757073] = (byte)(-1708130848 * 2069799971 + 1418512943);
                                                                  var36[518804520 * -1528442207 + -930200871] = (byte)((-1601568768 | 7841) ^ -1601560862);
                                                                  var36[(-628293632 | 17924) ^ -628275706] = (byte)(~-262797108 - 1929119246 ^ 1666322063);
                                                                  var36[(992739328 | 25223) ^ 992764548] = (byte)(~-624375828 - -713457320 ^ 1337833192);
                                                                  var36[~1588659536 - -1730124063 ^ 141464522] = (byte)((-1709572096 | 23955) ^ -1709548048);
                                                                  var36[(1457455104 | '홀') ^ 1457509957] = (byte)(1174118375 * 865812879 + 788203290);
                                                                  var36[(681181184 | '\uec57') ^ 681241681] = (byte)(~347962264 - -1498352671 ^ 1150390401);
                                                                  var36[~1126099492 - -463571453 ^ -662528033] = (byte)(~-1884232299 - 995979621 ^ 888252719);
                                                                  var36[~1604648939 - 293074785 ^ -1897723717] = (byte)(~-851953006 - -1882881030 ^ -1560133335);
                                                                  var36[~-1026753393 - -1603136226 ^ -1665077669] = (byte)(~312702110 - -1725172496 ^ -1412470385);
                                                                  var36[-1975841277 * 340256015 + -198410531] = (byte)(~32182590 - -919745126 ^ -887562563);
                                                                  var36[~-1854453024 - 940792370 ^ 913660646] = (byte)(~-1462641118 - -381633336 ^ 1844274536);
                                                                  var36[-1707281043 * 1940056679 + -215376079] = (byte)(~-1686275480 - -1255869228 ^ -1352822539);
                                                                  var36[(-1684406272 | 24793) ^ -1684381484] = (byte)(415689637 * 1322253413 + -1725719484);
                                                                  var36[-900516418 * -1565893567 + -849145136] = (byte)(1171944132 * -998074615 + 1511545625);
                                                                  var36[(71565312 | '솑') ^ 71614878] = (byte)((-2050097152 | '뤕') ^ 2050049722);
                                                                  var9 = I_1_1Il_1l_1l_I1l_I1l__(var36, -1946571941 * -349741481 + 492722720);
                                                               }

                                                               var10002 = null;
                                                               Thread.currentThread().hashCode();
                                                               if (!var5.contains(var9)) {
                                                                  var9 = i1l1lIl1l111I1_lii_lIIIlIlI(874617714 * -1436555693 + -2078003688);
                                                                  if (var9 == null) {
                                                                     byte[] var38 = new byte[(-1062076416 | 19066) ^ -1062057354];
                                                                     var38[1962461229 * -1491682045 + 1718513273] = (byte)(-1546915558 * -667683935 + 1857269987);
                                                                     var38[-1944773940 * 105090973 + -2119000859] = (byte)(-1718191021 * 1489465755 + 1753028289);
                                                                     var38[(1008599040 | '\ue189') ^ 1008656779] = (byte)(1425052274 * 1876407925 + -1171126823);
                                                                     var38[~-584582204 - 1751200383 ^ -1166618177] = (byte)(~-1027363762 - -593858988 ^ -1621222698);
                                                                     var38[(-1189675008 | '詭') ^ -1189639575] = (byte)((1370030080 | '뚸') ^ 1370076813);
                                                                     var38[998729233 * 466308319 + 944114486] = (byte)(2122182680 * -1106530133 + -876077020);
                                                                     var38[-16318207 * -2061447855 + 232448437] = (byte)((857341952 | '鳈') ^ -857382111);
                                                                     var38[(-72810496 | 'ꪥ') ^ -72766814] = (byte)((-1735983104 | '놀') ^ 1735937663);
                                                                     var38[(-772472832 | 25044) ^ -772447780] = (byte)(~-1018287063 - 1032136456 ^ 13849428);
                                                                     var38[(1488191488 | '\uefe8') ^ 1488252897] = (byte)(~-1749137922 - -1189385462 ^ 1356443957);
                                                                     var38[~-751552820 - 1039318746 ^ -287765933] = (byte)(~-364114473 - 37173182 ^ 326941286);
                                                                     var38[560926362 * 426488183 + -2014725003] = (byte)(-1521644693 * -419762391 + -655505818);
                                                                     var9 = I_1_1Il_1l_1l_I1l_I1l__(var38, ~-1755467135 - 1868724878 ^ -113257730);
                                                                  }

                                                                  var10002 = null;
                                                                  Thread.currentThread().hashCode();
                                                                  if (!var5.contains(var9)) {
                                                                     var9 = i1l1lIl1l111I1_lii_lIIIlIlI(~1390510831 - -279386405 ^ -1111124422);
                                                                     if (var9 == null) {
                                                                        byte[] var40 = new byte[(100859904 | 'ﭤ') ^ 100924267];
                                                                        var40[-1334245201 * -116675771 + 967637] = (byte)((-2117140480 | 4982) ^ 2117135605);
                                                                        var40[(-2063859712 | 30533) ^ -2063829180] = (byte)((527040512 | 1519) ^ -527042029);
                                                                        var40[-1779757156 * 196562983 + -674504898] = (byte)(~-494545342 - 492060825 ^ 2484536);
                                                                        var40[1062932651 * 1907342077 + 1493125380] = (byte)((-937033728 | '릯') ^ -936986123);
                                                                        var40[(1090781184 | '\uf2e5') ^ 1090843361] = (byte)(1452654981 * 210317061 + 73321904);
                                                                        var40[~1246465761 - -323905189 ^ -922560570] = (byte)((1408172032 | 'Ꝼ') ^ -1408214889);
                                                                        var40[1985712225 * 2059882085 + -226788415] = (byte)(~1255739353 - 153463985 ^ 1409203364);
                                                                        var40[~568195463 - 1618819651 ^ 2107952178] = (byte)(64879204 * 1483690495 + -129109449);
                                                                        var40[1519246367 * -1779698733 + -1716705925] = (byte)((-2112290816 | 13048) ^ -2112277771);
                                                                        var40[889448714 * 676547817 + 2013523439] = (byte)(~1151732760 - 1606029661 ^ -1537204964);
                                                                        var40[1962593589 * -1508173675 + 1681037361] = (byte)(-1832645467 * -1539064425 + -1378371185);
                                                                        var40[~114068146 - -2054703079 ^ 1940634943] = (byte)(356215515 * 1504280977 + 1525489405);
                                                                        var40[-880725895 * 1484750431 + 1093432613] = (byte)(~-720276484 - -1621280552 ^ -1953410270);
                                                                        var40[2135453528 * -1714625533 + -1394847227] = (byte)((1824391168 | 7020) ^ 1824398089);
                                                                        var40[-1361828310 * 1290834589 + -1967086516] = (byte)(~-1595867944 - -2119967363 ^ 579131964);
                                                                        var9 = I_1_1Il_1l_1l_I1l_I1l__(var40, 1472656957 * 2004957101 + 2145877462);
                                                                     }

                                                                     var10002 = null;
                                                                     Thread.currentThread().hashCode();
                                                                     if (!var5.contains(var9)) {
                                                                        var9 = i1l1lIl1l111I1_lii_lIIIlIlI(-842508167 * 1460191277 + -1364872501);
                                                                        if (var9 == null) {
                                                                           byte[] var42 = new byte[~-1701478518 - 193492120 ^ 1507986388];
                                                                           var42[(209715200 | '葄') ^ 209749060] = (byte)((492961792 | 1255) ^ 492963040);
                                                                           var42[631908687 * 1872560605 + 998115278] = (byte)(92217153 * 346498127 + -168216744);
                                                                           var42[846646920 * -1187289517 + 1809373674] = (byte)(-153639408 * -1638592839 + -809230728);
                                                                           var42[~-561991393 - -400728565 ^ 962719958] = (byte)(~1771128022 - 1402712222 ^ 1121127070);
                                                                           var42[(625672192 | 22765) ^ 625694953] = (byte)(~-540305403 - -496869746 ^ 1037175092);
                                                                           var42[179018205 * 878505081 + -1818195312] = (byte)(579095482 * -466960911 + -1056414854);
                                                                           var42[(1547698176 | '\uefe6') ^ 1547759584] = (byte)((1268056064 | 21194) ^ -1268077309);
                                                                           var42[~1922060840 - 501832602 ^ 1871073850] = (byte)(-172968079 * 1963558489 + -40758344);
                                                                           var42[(-1504706560 | '\uf774') ^ -1504643204] = (byte)(148971819 * 821682011 + 1765916851);
                                                                           var9 = I_1_1Il_1l_1l_I1l_I1l__(var42, (-226623488 | 4394) ^ -226619078);
                                                                        }

                                                                        var10002 = null;
                                                                        Thread.currentThread().hashCode();
                                                                        if (!var5.contains(var9)) {
                                                                           return Result.NEUTRAL;
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }

                                             return Result.DENY;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        return Result.DENY;
                     }
                  }

                  return Result.NEUTRAL;
               }
            }

            // $FF: synthetic method
            private static String i1l1lIl1l111I1_lii_lIIIlIlI(int var0) {
               char[][] var1 = II111i_li_IilllI_i_IiI11i1i_;
               if (var1 != null && var0 >= 0 && var0 < var1.length) {
                  char[] var2 = var1[var0];
                  if (var2 != null) {
                     int var3 = 743045912 ^ var0 ^ 'ꕚ';
                     char[] var4 = new char[var2.length];

                     for(int var5 = 0; var5 < var2.length; ++var5) {
                        var4[var5] = (char)(var2[var5] ^ var3 + var5 * 17);
                     }

                     String var6 = new String(var4);
                     Arrays.fill(var4, '\u0000');
                     return var6;
                  }
               }

               return null;
            }

            // $FF: synthetic method
            private static String I_1_1Il_1l_1l_I1l_I1l__(byte[] var0, int var1) {
               String var2 = i1l1lIl1l111I1_lii_lIIIlIlI(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  int var14 = var0.length - 4;
                  if (var14 <= 0) {
                     return new String(new byte[0], StandardCharsets.UTF_8);
                  } else {
                     int var3 = (var0[0] & 255) << 24 | (var0[1] & 255) << 16 | (var0[2] & 255) << 8 | var0[3] & 255;
                     int var4 = 743045912 ^ var3 ^ var14 * -1640531527 ^ 1831565813;
                     int var5 = (var3 ^ 1063452672 ^ var14 * -2048144789) + 324508639;
                     byte[] var6 = new byte[var14];

                     for(int var7 = 0; var7 < var14; ++var7) {
                        int var15 = var4 ^ var4 << 13;
                        int var16 = var15 ^ var15 >>> 17;
                        var4 = var16 ^ var16 << 5;
                        var5 = var5 * 1103515245 + 12345;
                        int var8 = var4 + var5 ^ (var5 >>> 16 | var5 << 16);
                        int var9 = (var8 ^ var7 * 37 ^ var14 * 19 ^ var3 >>> ((var7 & 3) << 3) & 255) & 255;
                        int var10 = (var8 >>> 8 ^ var7) & 7;
                        int var11 = var7 == 0 ? (var3 ^ 743045912) & 255 : var0[var7 + 3] & 255;
                        int var12 = (var0[var7 + 4] ^ var9 ^ 165) & 255;
                        int var13 = var10 != 0 ? (var12 >>> var10 | var12 << 8 - var10) & 255 : var12;
                        var6[var7] = (byte)(var13 - var11 - var9 & 255);
                     }

                     String var17 = new String(var6, StandardCharsets.UTF_8);
                     char[] var18 = var17.toCharArray();
                     int var19 = 743045912 ^ var1 ^ 'ꕚ';
                     char[] var20 = new char[var18.length];

                     for(int var21 = 0; var21 < var18.length; ++var21) {
                        var20[var21] = (char)(var18[var21] ^ var19 + var21 * 17);
                     }

                     if (II111i_li_IilllI_i_IiI11i1i_ != null) {
                        II111i_li_IilllI_i_IiI11i1i_[var1] = var20;
                     }

                     Arrays.fill(var18, '\u0000');
                     Arrays.fill(var6, (byte)0);
                     Arrays.fill(var0, (byte)0);
                     return var17;
                  }
               }
            }
         };
         configuration.getRootLogger().addFilter(filter);

         for(LoggerConfig loggerConfig : configuration.getLoggers().values()) {
            loggerConfig.addFilter(filter);
         }

         context.updateLoggers();
      } catch (Throwable throwable) {
         LOGGER.warn("Failed to install log filter", throwable);
      }

   }

   private void disableOpenGlDebug() {
      try {
         RenderSystem.recordRenderCall(() -> {
            try {
               GL11.glDisable(37600);
            } catch (Exception var1) {
            }

         });
      } catch (Throwable throwable) {
         LOGGER.warn("Failed to disable OpenGL debug output", throwable);
      }

   }

   private void forceEarlyInit() {
      try {
         l_lii_i_l11Ii_lI1_1_11_iI_I1i1.lliIII1Ii_i_Iil__lli1_lIIl_();
         IIIiii1liIIl__1i_I_iIi1l_1I_l1.I_l1i1III_I1iIIIIlIlI__iliii();
         LOGGER.info("Early init: MSDF fonts and Render loaded");
      } catch (Exception e) {
         LOGGER.warn((String)"Early init failed (non-critical): {}", (Object)e.getMessage());
      }

   }

   public void shutdown() {
      if (this.configManager != null) {
         this.configManager.saveAutoConfig();
      }

      l__11IIIii1I__I1i1l1Ii11iI_11l.iilil_llI1lilii1_i_Ili_l_111();
      liI_IillIi_I1i1Ii_IliIliliII1.i_l__iIllII1iIi11_i1ilI1I();
      LOGGER.info("Shutting down...");
   }

   public static Phantom getInstance() {
      return INSTANCE;
   }

   @Generated
   public EventBus getEventBus() {
      return this.eventBus;
   }

   @Generated
   public lil_I__iiiI1__IlIIl1i1IIliII_ getHudManager() {
      return this.hudManager;
   }

   @Generated
   public l_I_1i1_lIi1_l11l_1_1l1_1iIiI getCommandManager() {
      return this.commandManager;
   }

   @Generated
   public liIl_i_ii1__ll_1i_IIiiI1I1I__ getConfigManager() {
      return this.configManager;
   }

   @Generated
   public i1_lII1i1l_1_ii1_l_IiillllIllII getModuleManager() {
      return this.moduleManager;
   }

   @Generated
   public iII_iII_1lI_1l1_I1l1_II1_1 getDraggableManager() {
      return this.draggableManager;
   }

   @Generated
   public iiili11l1I_1I1_ll_l111_l getLanguage() {
      return this.language;
   }

   @Generated
   public i_Iiiiii11111llili_1_lIi1I_ll1_ getFriendManager() {
      return this.friendManager;
   }

   @Generated
   public l1iliI1iIi1li1IIIl1iI1Iil getWayPointsManager() {
      return this.wayPointsManager;
   }

   @Generated
   public lII_11liI_1Iil_iIiI1i_llil_1__ getMacrosManager() {
      return this.macrosManager;
   }

   @Generated
   public i1_i1_I_lIIi_1_I_l1_II_iII111i_ getCapesManager() {
      return this.capesManager;
   }

   @Generated
   public liIiI__lIlIiI1IIil1IiI_1_1l getNotificationManager() {
      return this.notificationManager;
   }

   public void toggleLanguage() {
      if (this.language == iiili11l1I_1I1_ll_l111_l.ENG) {
         this.language = iiili11l1I_1I1_ll_l111_l.RU;
      } else {
         this.language = iiili11l1I_1I1_ll_l111_l.ENG;
      }

   }

   public void setLanguage(iiili11l1I_1I1_ll_l111_l language) {
      this.language = language;
   }

   public static class_2960 id(String path) {
      path = Patch.safe(path);
      return class_2960.method_60655(MOD_ID, path);
   }

   // $FF: synthetic method
   private static Phantom[] $values() {
      return new Phantom[]{INSTANCE};
   }
}
