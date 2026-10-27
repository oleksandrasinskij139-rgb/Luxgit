package code.miaware.client.ui.clickgui;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.K86d8caed0012682c7b;
import code.miaware.api.module.setting.K917aeb5cdb0f7aca26;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kb9fa339b3f0f7560be;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.cosmetics.K9a0eb2753a3143de82;
import code.miaware.client.features.cosmetics.Ka5dfe6b89219e29d09;
import code.miaware.client.ui.theme.K2c59a7f522ec4ea35b;
import code.miaware.client.ui.theme.K51ad80c57a5258fb43;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class K87493c3dea3de1905b {
   public static final float SIDEBAR_W = 188.0F;
   public static final float GAP = 8.0F;
   public static final float MAIN_W = 522.0F;
   public static final float TOTAL_W = 718.0F;
   public static final float TOTAL_H = 460.0F;
   private static final float PANEL_R = 12.0F;
   private static final float PAD = 16.0F;
   private static final float LIST_TOP = 62.0F;
   private static final float LIST_BOTTOM = 338.0F;
   private static final float GROUP_H = 23.0F;
   private static final float ITEM_H = 35.0F;
   private static final float CONTENT_X = 18.0F;
   private static final float CONTENT_TOP = 50.0F;
   private static final float CONTENT_W = 489.0F;
   private static final float CONTENT_BOTTOM = 452.0F;
   private static final float COLUMN_GAP = 6.0F;
   private static final float COLUMN_W = 241.5F;
   private static final float CARD_R = 7.0F;
   private static final float CARD_GAP = 5.5F;
   private static final float CARD_HEADER = 31.0F;
   private static final float CARD_TITLE_CY = 15.0F;
   private static final float CARD_PAD_L = 12.0F;
   private static final float CARD_PAD_R = 11.0F;
   private static final float ROWS_TOP = 9.0F;
   private static final float ROWS_BOTTOM = 9.0F;
   private static final float ROW_GAP = 8.0F;
   private static final float ROW_FIELD = 20.0F;
   private static final float ROW_BUTTON = 18.0F;
   private static final float ROW_CHECK = 12.0F;
   private static final float ROW_SLIDER = 21.0F;
   private static final float ROW_SEGMENTS = 37.0F;
   private static final float ROW_SECTION = 12.0F;
   private static final float FIELD_W = 106.0F;
   private static final float FIELD_H = 20.0F;
   private static final float POPUP_ITEM_H = 16.0F;
   private static final int POPUP_MAX_ITEMS = 9;
   private static final float FS_ITEM = 9.8F;
   private static final float FS_TITLE = 9.6F;
   private static final float FS_LABEL = 9.6F;
   private static final float FS_SMALL = 8.4F;
   private static final float FS_GROUP = 7.8F;
   private static final float FS_BADGE = 6.6F;
   private static final float FS_SEARCH = 9.6F;
   private static final float FS_VERSION = 8.9F;
   private static final float FS_TOOLTIP = 9.9F;
   static final Color ACCENT = new Color(213, 177, 252);
   private static final Color GLASS_TINT = new Color(60, 52, 72, 255);
   private static final Color GLASS_SOLID = new Color(104, 94, 114, 255);
   private static final Color POPUP_TINT = new Color(74, 64, 88, 255);
   private static final Color POPUP_SOLID = new Color(121, 108, 133, 255);
   private static final Color CHECK_DARK = new Color(62, 44, 84, 230);
   private static final float A_STRONG = 1.0F;
   private static final float A_TEXT = 0.62F;
   private static final float A_LABEL = 0.36F;
   private static final float A_MUTED = 0.31F;
   private static final float A_GROUP = 0.27F;
   private static final float A_FIELD = 0.06F;
   private static final List<Ka6e5a7cd25e001c633> GROUPS;
   private final Ke4d698fab638f5cee0 env;
   private float s = 1.0F;
   private float ox;
   private float oy;
   private float mouseX;
   private float mouseY;
   private float dt;
   private Kf76f042931183af5ba category;
   private final Set<String> collapsedGroups;
   private final Map<String, K7cc58e1b7729233c63> groupAnims;
   private final Map<Kf76f042931183af5ba, K7cc58e1b7729233c63> itemAnims;
   private final StringBuilder search;
   private boolean searchFocused;
   private final Map<Kab8a8c478f7d0077cd, K2744bf03883d58054c> cards;
   private final List<Kab8a8c478f7d0077cd> leftColumn;
   private final List<Kab8a8c478f7d0077cd> rightColumn;
   private String layoutKey;
   private final K7cc58e1b7729233c63 scroll;
   private float contentHeight;
   private final K7cc58e1b7729233c63 sideScroll;
   private float sideContentHeight;
   private final List<K04ecc14f13fb56c724> hits;
   private final List<K451be487a8a30ea1c8> hoverCards;
   private Kab8a8c478f7d0077cd hoveredModule;
   private final float[] clip;
   private final ArrayList<float[]> clipStack;
   private Kdced9e23f1e229ba9d<?> popupSetting;
   private K8c788a7b392151fdbb popupKind;
   private float popupAnchorX;
   private float popupAnchorY;
   private float popupAnchorW;
   private float[] popupRect;
   private float popupScroll;
   private boolean popupOwnerVisible;
   private K917aeb5cdb0f7aca26 editing;
   private final StringBuilder editBuffer;
   private Kab8a8c478f7d0077cd bindingModule;
   private K1d3d0b4020f933282f bindingSetting;
   private Kb9e875ead00e0d03bd dragSlider;
   private float dragSliderX;
   private float dragSliderW;
   private Ka52d224feda41b0761 dragColor;
   private int dragColorPart;
   private float[] dragColorRect;
   private final float[] colorHsb;
   private String tooltip;
   private Kab8a8c478f7d0077cd tooltipOwner;
   private final K5559dcfe69696f52f8 descriptionTyping;
   private float tooltipX;
   private float tooltipY;
   private static final float PALETTE_SPACE = 38.0F;
   private static final float PALETTE_H = 28.0F;
   private static final float PALETTE_DOT = 17.0F;
   private static final float PALETTE_GAP = 8.0F;
   private static final float PALETTE_STEP = 25.0F;
   private static final int PALETTE_VISIBLE = 6;
   private final K7cc58e1b7729233c63 paletteScroll;
   private final Map<Integer, K7cc58e1b7729233c63> paletteHover;
   private float[] paletteRect;
   private static final float COS_COLUMNS = 2.0F;
   private static final float COS_PREVIEW_W = 156.0F;
   private static final float COS_GAP = 7.0F;
   private static final float COS_CARD_H = 128.0F;
   private static final float COS_BAR_H = 20.0F;
   private static final float COS_GRID_TOP = 30.0F;
   private K9a0eb2753a3143de82 cosFilter;
   private final Map<Integer, K7cc58e1b7729233c63> cosHover;
   private final Map<Integer, K7cc58e1b7729233c63> cosEquip;
   private final Map<String, K7cc58e1b7729233c63> cosChip;
   private final K7cc58e1b7729233c63 cosBinHover;
   private int cosPreviewItem;
   private float cosPreviewYaw;
   private float cosPreviewZoom;
   private boolean cosPreviewDragging;
   private float cosPreviewDragX;
   private float[] cosPreviewRect;
   private static final Object SEARCH_TAG;

   private static Color K44a4682200f8fd6f20(float a) {
      return new Color(250, 246, 255, Kd6be252ed9cd8bc0cb(a * 255.0F));
   }

   private static Color accent(float a) {
      Color acc = K2c59a7f522ec4ea35b.guiAccent();
      return new Color(acc.getRed(), acc.getGreen(), acc.getBlue(), Kd6be252ed9cd8bc0cb(a * 255.0F));
   }

   private static Color K8c29593505886788a7(float a) {
      return new Color(20, 12, 30, Kd6be252ed9cd8bc0cb(a * 255.0F));
   }

   private static Color Kcb3d2fcc2c28199e17(Color c, float mul) {
      return new Color(c.getRed(), c.getGreen(), c.getBlue(), Kd6be252ed9cd8bc0cb((float)c.getAlpha() * mul));
   }

   private static int Kd6be252ed9cd8bc0cb(float v) {
      return Math.max(0, Math.min(255, Math.round(v)));
   }

   static String labelOf(Kf76f042931183af5ba category) {
      if (category == Kf76f042931183af5ba.OTHER) {
         return Ka35e7d6b31db9984f1.getInstance().translate("Misc");
      } else if (category == Kf76f042931183af5ba.COSMETICS) {
         return Ka35e7d6b31db9984f1.getInstance().isRussian() ? "Косметика" : "Cosmetics";
      } else {
         return category.getDisplayLabel();
      }
   }

   static K756e30c0738d06c926 iconOf(Kf76f042931183af5ba category) {
      K756e30c0738d06c926 var10000;
      switch (category) {
         case COMBAT -> var10000 = K756e30c0738d06c926.CATEGORY_COMBAT;
         case MOVEMENT -> var10000 = K756e30c0738d06c926.CATEGORY_MOVEMENT;
         case PLAYER -> var10000 = K756e30c0738d06c926.CATEGORY_PLAYER;
         case RENDER -> var10000 = K756e30c0738d06c926.CATEGORY_RENDER;
         case OTHER -> var10000 = K756e30c0738d06c926.CATEGORY_OTHER;
         case COSMETICS -> var10000 = K756e30c0738d06c926.CATEGORY_COSMETICS;
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }

   public K87493c3dea3de1905b(Ke4d698fab638f5cee0 env) {
      this.category = Kf76f042931183af5ba.COMBAT;
      this.collapsedGroups = new HashSet();
      this.groupAnims = new HashMap();
      this.itemAnims = new HashMap();
      this.search = new StringBuilder();
      this.cards = new IdentityHashMap();
      this.leftColumn = new ArrayList();
      this.rightColumn = new ArrayList();
      this.layoutKey = "";
      this.scroll = new K7cc58e1b7729233c63(0.0F);
      this.sideScroll = new K7cc58e1b7729233c63(0.0F);
      this.hits = new ArrayList();
      this.hoverCards = new ArrayList();
      this.clip = new float[]{-1000000.0F, -1000000.0F, 1000000.0F, 1000000.0F};
      this.clipStack = new ArrayList();
      this.editBuffer = new StringBuilder();
      this.colorHsb = new float[3];
      this.descriptionTyping = new K5559dcfe69696f52f8();
      this.paletteScroll = new K7cc58e1b7729233c63(0.0F);
      this.paletteHover = new HashMap();
      this.cosHover = new HashMap();
      this.cosEquip = new HashMap();
      this.cosChip = new HashMap();
      this.cosBinHover = new K7cc58e1b7729233c63(0.0F);
      this.cosPreviewItem = -1;
      this.cosPreviewYaw = -25.0F;
      this.cosPreviewZoom = 1.0F;
      this.env = env;
   }

   public boolean isTyping() {
      return this.searchFocused || this.editing != null || this.bindingModule != null || this.bindingSetting != null;
   }

   public boolean isDragging() {
      return this.dragSlider != null || this.dragColor != null || this.cosPreviewDragging;
   }

   public float getScale() {
      return this.s;
   }

   public void layout(float screenW, float screenH, float userScale) {
      float fullH = 498.0F;
      float fit = Math.min(screenW * 0.8F / 718.0F, screenH * 0.86F / fullH);
      float available = Math.min(Math.max(1.0F, screenW - 24.0F) / 718.0F, Math.max(1.0F, screenH - 24.0F) / fullH);
      this.s = Math.min(available, Math.max(0.35F, fit * userScale));
      this.ox = (screenW - 718.0F * this.s) / 2.0F;
      this.oy = (screenH - fullH * this.s) / 2.0F + 38.0F * this.s;
   }

   public void offsetY(float dy) {
      this.oy += dy;
   }

   private float u(float v) {
      return v * this.s;
   }

   public void render(K224f9523984e1fa68b c, float mx, float my, float deltaSeconds) {
      this.mouseX = mx;
      this.mouseY = my;
      this.dt = Math.max(0.0F, Math.min(deltaSeconds, 0.1F));
      this.hits.clear();
      this.hoverCards.clear();
      this.clipStack.clear();
      this.clip[0] = -1000000.0F;
      this.clip[1] = -1000000.0F;
      this.clip[2] = 1000000.0F;
      this.clip[3] = 1000000.0F;
      this.tooltip = null;
      this.tooltipOwner = null;
      if (this.dragSlider != null) {
         this.K35a185faddddba4ec4(this.dragSlider, mx);
      }

      if (this.dragColor != null) {
         this.K77e207c9f2a5eded20(mx, my);
      }

      this.popupOwnerVisible = false;
      this.K32cee0d8156e41756f(c);
      this.Kaf8857ca04ba1e8b0e(c);
      this.K7cc8ebf4f0d9cce435(c);
      if (this.popupSetting != null && !this.popupOwnerVisible) {
         this.Kb85d0643865a962467();
      }

      if (this.popupSetting != null) {
         this.K866bbbffb33b929e87(c);
      } else {
         this.popupRect = null;
      }

      this.K9fdc1a0882e55009b2();
      if (this.tooltip != null && !this.isDragging()) {
         this.K2fa02002e992cf5057(c);
      } else {
         this.descriptionTyping.reset();
         this.env.stopDescriptionSound();
      }

   }

   private void K32cee0d8156e41756f(K224f9523984e1fa68b c) {
      float x = this.ox;
      float y = this.oy;
      float w = this.u(188.0F);
      float h = this.u(460.0F);
      this.K14e7e25cdd32117321(c, x, y, w, h, this.u(12.0F));
      float bx = x + this.u(16.0F);
      float by = y + this.u(14.0F);
      float bh = this.u(23.0F);
      c.rect(bx, by, this.u(108.0F), bh, this.u(6.0F), K44a4682200f8fd6f20(0.055F));
      c.logo(bx + this.u(14.5F), by + bh / 2.0F, this.u(9.0F), accent(1.0F));
      c.text(Kc517862407c2394736.SEMIBOLD, this.env.clientName(), bx + this.u(27.0F), by + bh / 2.0F, this.u(9.8F), accent(1.0F));
      float vx = x + this.u(128.0F);
      c.rect(vx, by, this.u(48.0F), bh, this.u(6.0F), K44a4682200f8fd6f20(0.055F));
      String version = this.env.gameVersion();
      float vw = c.textWidth(Kc517862407c2394736.SEMIBOLD, version, this.u(8.9F));
      c.text(Kc517862407c2394736.SEMIBOLD, version, vx + this.u(24.0F) - vw / 2.0F, by + bh / 2.0F, this.u(8.9F), K44a4682200f8fd6f20(0.62F));
      this.Kf517e988cdcbdade48(c, x + this.u(16.0F), y + this.u(53.5F), this.u(160.0F), K44a4682200f8fd6f20(0.06F));
      this.K22e1897085e03d297d(c, x, y);
      this.Kf517e988cdcbdade48(c, x + this.u(16.0F), y + this.u(345.0F), this.u(160.0F), K44a4682200f8fd6f20(0.06F));
      this.K0741757ff55fe32881(c, x + this.u(16.0F), y + this.u(362.0F), this.u(160.0F), this.u(25.0F));
      this.Kc532abc8dc2117c16e(c, x + this.u(16.0F), y + this.u(396.0F), this.u(160.0F), this.u(50.0F));
   }

   private void K22e1897085e03d297d(K224f9523984e1fa68b c, float px, float py) {
      float top = py + this.u(62.0F);
      float bottom = py + this.u(338.0F);
      float viewH = bottom - top;
      float fade = this.u(30.0F);
      float total = 0.0F;

      for(Ka6e5a7cd25e001c633 g : GROUPS) {
         K7cc58e1b7729233c63 open = this.K4b6827bc4efb1d8cf2(g);
         open.update(this.dt, 16.0F);
         total += 23.0F + 35.0F * (float)g.categories().size() * open.value;
      }

      this.sideContentHeight = total;
      float maxScroll = Math.max(0.0F, this.u(total) - viewH);
      this.sideScroll.target = K981e054bcc0843bcd9(this.sideScroll.target, 0.0F, maxScroll);
      this.sideScroll.update(this.dt, 18.0F);
      this.sideScroll.value = K981e054bcc0843bcd9(this.sideScroll.value, 0.0F, maxScroll);
      this.K474ac610b443bade50(c, px, top, this.u(188.0F), viewH);
      float cy = top - this.sideScroll.value;

      for(Ka6e5a7cd25e001c633 g : GROUPS) {
         K7cc58e1b7729233c63 open = this.K4b6827bc4efb1d8cf2(g);
         float headerCenter = cy + this.u(23.0F) / 2.0F;
         float rowAlpha = this.K795ad067d0706e70ad(headerCenter, top, bottom, fade);
         if (rowAlpha > 0.01F) {
            this.K5fc24c0598821b2afc(c, g, px, cy, rowAlpha, open.value);
         }

         this.hit(px + this.u(16.0F), cy, this.u(160.0F), this.u(23.0F), (mx, my, b) -> {
            if (!this.collapsedGroups.remove(g.title())) {
               this.collapsedGroups.add(g.title());
            }

         });
         cy += this.u(23.0F);
         float groupH = this.u(35.0F) * (float)g.categories().size() * open.value;
         if (groupH > 0.5F) {
            this.K474ac610b443bade50(c, px, cy, this.u(188.0F), groupH);
            float iy = cy;

            for(Kf76f042931183af5ba cat : g.categories()) {
               float center = iy + this.u(35.0F) / 2.0F;
               float a = this.K795ad067d0706e70ad(center, top, bottom, fade) * open.value;
               this.K4db7977b2a297b0a03(c, cat, px, iy, a);
               iy += this.u(35.0F);
            }

            this.Kf012206dd4fc483166(c);
         }

         cy += groupH;
      }

      this.Kf012206dd4fc483166(c);
      if (maxScroll > 0.5F) {
         float trackTop = top + this.u(5.0F);
         float trackH = viewH - this.u(10.0F);
         float thumbH = Math.max(this.u(20.0F), trackH * viewH / this.u(total));
         float thumbY = trackTop + (trackH - thumbH) * (this.sideScroll.value / maxScroll);
         c.rect(px + this.u(185.0F), thumbY, this.u(1.5F), thumbH, this.u(0.75F), K44a4682200f8fd6f20(0.75F));
      }

   }

   private void K5fc24c0598821b2afc(K224f9523984e1fa68b c, Ka6e5a7cd25e001c633 g, float px, float y, float alpha, float open) {
      float cy = y + this.u(23.0F) / 2.0F;
      float x0 = px + this.u(16.0F);
      float x1 = px + this.u(176.0F);
      Color col = K44a4682200f8fd6f20(0.27F * alpha);
      c.text(Kc517862407c2394736.MEDIUM, g.title(), x0, cy, this.u(7.8F), col);
      float tw = c.textWidth(Kc517862407c2394736.MEDIUM, g.title(), this.u(7.8F));
      String count = String.valueOf(g.categories().size());
      float chevronCx = x1 - this.u(4.0F);
      float countW = c.textWidth(Kc517862407c2394736.MEDIUM, count, this.u(7.8F));
      float countX = chevronCx - this.u(6.5F) - countW;
      c.text(Kc517862407c2394736.MEDIUM, count, countX, cy, this.u(7.8F), col);
      c.icon(open > 0.5F ? K756e30c0738d06c926.CHEVRON_DOWN : K756e30c0738d06c926.CHEVRON_UP, chevronCx, cy, this.u(9.0F), K44a4682200f8fd6f20(0.3F * alpha));
      float lineX = x0 + tw + this.u(5.0F);
      float lineW = countX - this.u(5.0F) - lineX;
      if (lineW > 0.0F) {
         this.Kf517e988cdcbdade48(c, lineX, cy - this.u(0.3F), lineW, K44a4682200f8fd6f20(0.07F * alpha));
      }

   }

   private void K4db7977b2a297b0a03(K224f9523984e1fa68b c, Kf76f042931183af5ba cat, float px, float y, float alpha) {
      if (!(alpha <= 0.01F)) {
         boolean selected = cat == this.category && this.search.isEmpty();
         K7cc58e1b7729233c63 sel = (K7cc58e1b7729233c63)this.itemAnims.computeIfAbsent(cat, (k) -> new K7cc58e1b7729233c63(selected ? 1.0F : 0.0F));
         sel.target = selected ? 1.0F : 0.0F;
         sel.update(this.dt, 14.0F);
         float t = sel.value;
         float cy = y + this.u(35.0F) / 2.0F;
         boolean hovered = this.K0f2dbddffe52521042(px + this.u(16.0F), y, this.u(160.0F), this.u(35.0F));
         if (t > 0.01F) {
            c.rect(px + this.u(24.0F), cy - this.u(15.0F), this.u(152.0F), this.u(30.0F), this.u(7.0F), new Color(255, 242, 255, Kd6be252ed9cd8bc0cb(16.0F * t * alpha)));
            c.rect(px + this.u(18.0F), cy - this.u(8.5F), this.u(1.5F), this.u(17.0F), this.u(0.75F), accent(t * alpha));
         } else if (hovered) {
            c.rect(px + this.u(16.0F), cy - this.u(15.0F), this.u(160.0F), this.u(30.0F), this.u(7.0F), K44a4682200f8fd6f20(0.025F * alpha));
         }

         float shift = this.u(13.0F) * t;
         Color textCol = K40027418113552ac83(K44a4682200f8fd6f20(0.62F * alpha), accent(alpha), t);
         if (hovered && t < 0.5F) {
            textCol = K44a4682200f8fd6f20(0.78F * alpha);
         }

         c.icon(iconOf(cat), px + this.u(28.0F) + shift, cy, this.u(12.5F), textCol);
         c.text(Kc517862407c2394736.SEMIBOLD, labelOf(cat), px + this.u(41.0F) + shift, cy, this.u(9.8F), textCol);
         this.hit(px + this.u(16.0F), y, this.u(160.0F), this.u(35.0F), (mx, my, b) -> {
            if (b == 0) {
               boolean changed = this.category != cat || !this.search.isEmpty();
               this.category = cat;
               this.search.setLength(0);
               this.searchFocused = false;
               if (changed) {
                  this.scroll.target = 0.0F;
                  this.scroll.value = 0.0F;
               }

            }
         });
      }
   }

   private void K0741757ff55fe32881(K224f9523984e1fa68b c, float x, float y, float w, float h) {
      c.rect(x, y, w, h, this.u(7.0F), K44a4682200f8fd6f20(this.searchFocused ? 0.085F : 0.06F));
      float cy = y + h / 2.0F;
      String text = this.search.toString();
      float textX = x + this.u(10.0F);
      float maxW = w - this.u(32.0F);
      if (text.isEmpty() && !this.searchFocused) {
         c.text(Kc517862407c2394736.MEDIUM, "Search", textX, cy, this.u(9.6F), K44a4682200f8fd6f20(0.62F));
      } else {
         String shown = K60c2f64d4ae8b3a197(c, Kc517862407c2394736.MEDIUM, text, this.u(9.6F), maxW);
         c.text(Kc517862407c2394736.MEDIUM, shown, textX, cy, this.u(9.6F), K44a4682200f8fd6f20(0.85F));
         if (this.searchFocused && this.Kefbfdb1c9d637d503c()) {
            float cw = c.textWidth(Kc517862407c2394736.MEDIUM, shown, this.u(9.6F));
            c.rect(textX + cw + this.u(1.0F), cy - this.u(4.5F), Math.max(0.5F, this.u(0.8F)), this.u(9.0F), 0.0F, accent(1.0F));
         }
      }

      c.icon(K756e30c0738d06c926.SEARCH, x + w - this.u(13.5F), cy, this.u(10.0F), K44a4682200f8fd6f20(this.searchFocused ? 0.85F : 0.62F));
      this.hit(x, y, w, h, SEARCH_TAG, (mx, my, b) -> {
         if (b == 1) {
            this.search.setLength(0);
         }

         this.Kc82f1e74662325e289(true);
      });
   }

   private void Kc532abc8dc2117c16e(K224f9523984e1fa68b c, float x, float y, float w, float h) {
      c.rect(x, y, w, h, this.u(7.0F), K44a4682200f8fd6f20(0.055F));
      float rowCy = y + this.u(14.0F);
      float headS = this.u(11.0F);
      c.head(x + this.u(14.0F) - headS / 2.0F, rowCy - headS / 2.0F, headS);
      String name = this.env.username();
      float nameX = x + this.u(26.0F);
      String nameShown = K78f466497bf3f7de26(c, Kc517862407c2394736.SEMIBOLD, name, this.u(9.8F), this.u(84.0F));
      c.text(Kc517862407c2394736.SEMIBOLD, nameShown, nameX, rowCy, this.u(9.8F), K44a4682200f8fd6f20(1.0F));
      float nameW = c.textWidth(Kc517862407c2394736.SEMIBOLD, nameShown, this.u(9.8F));
      String role = this.env.role().toUpperCase(Locale.ROOT);
      float badgeTextW = c.textWidth(Kc517862407c2394736.BOLD, role, this.u(6.6F));
      float badgeW = badgeTextW + this.u(19.0F);
      float badgeX = nameX + nameW + this.u(6.0F);
      float maxBadgeRight = x + w - this.u(8.0F);
      if (badgeX + badgeW > maxBadgeRight) {
         badgeX = maxBadgeRight - badgeW;
      }

      float badgeH = this.u(13.0F);
      float badgeY = rowCy - badgeH / 2.0F;
      c.rect(badgeX, badgeY, badgeW, badgeH, this.u(4.0F), accent(0.45F));
      c.rect(badgeX + this.u(0.7F), badgeY + this.u(0.7F), badgeW - this.u(1.4F), badgeH - this.u(1.4F), this.u(3.4F), new Color(110, 92, 132, 255));
      c.rect(badgeX + this.u(0.7F), badgeY + this.u(0.7F), badgeW - this.u(1.4F), badgeH - this.u(1.4F), this.u(3.4F), accent(0.1F));
      c.icon(K756e30c0738d06c926.BADGE, badgeX + this.u(6.5F), rowCy, this.u(7.5F), accent(1.0F));
      c.text(Kc517862407c2394736.BOLD, role, badgeX + this.u(12.0F), rowCy, this.u(6.6F), accent(1.0F));
      float subCy = y + this.u(31.0F);
      c.text(Kc517862407c2394736.MEDIUM, "Subscription Free", x + this.u(9.0F), subCy, this.u(8.4F), K44a4682200f8fd6f20(0.62F));
      String value = this.env.subscriptionText();
      float valueW = c.textWidth(Kc517862407c2394736.SEMIBOLD, value, this.u(8.4F));
      c.text(Kc517862407c2394736.SEMIBOLD, value, x + w - this.u(9.0F) - valueW, subCy, this.u(8.4F), K44a4682200f8fd6f20(0.62F));
      float barX = x + this.u(9.0F);
      float barW = w - this.u(18.0F);
      float barY = y + this.u(40.0F);
      c.rect(barX, barY, barW, this.u(1.5F), this.u(0.75F), K44a4682200f8fd6f20(0.1F));
      float progress = K981e054bcc0843bcd9(this.env.subscriptionProgress(), 0.0F, 1.0F);
      if (progress > 0.0F) {
         c.rect(barX, barY, barW * progress, this.u(1.5F), this.u(0.75F), accent(1.0F));
      }

   }

   private void Kaf8857ca04ba1e8b0e(K224f9523984e1fa68b c) {
      float x = this.ox + this.u(196.0F);
      float y = this.oy;
      float w = this.u(522.0F);
      float h = this.u(460.0F);
      this.K14e7e25cdd32117321(c, x, y, w, h, this.u(12.0F));
      this.K147f6e78a434cab85c(c, x, y);
      if (this.category == Kf76f042931183af5ba.COSMETICS && this.search.isEmpty()) {
         this.Kbf305353d8f6989727(c, x, y, w);
      } else {
         List<Kab8a8c478f7d0077cd> modules = this.K88819cee7ed2b88bc5();
         this.K618ccbb65c95dd2169(modules);
         float viewTop = y + this.u(50.0F);
         float viewBottom = y + this.u(452.0F);
         float viewH = viewBottom - viewTop;

         for(Kab8a8c478f7d0077cd m : modules) {
            K2744bf03883d58054c st = this.K7a67e9370fc7889d7f(m);
            st.expand.target = st.expanded ? 1.0F : 0.0F;
            st.expand.update(this.dt, 16.0F);
            float target = this.K3bd4f409a2d839de64(m, st.expanded);
            if (st.height.value < 0.0F) {
               st.height.value = target;
            }

            st.height.target = target;
            st.height.update(this.dt, 16.0F);
            st.toggle.target = m.isEnabled() ? 1.0F : 0.0F;
            st.toggle.update(this.dt, 18.0F);
         }

         float leftH = this.K8b1c4d74f51aaac12a(this.leftColumn);
         float rightH = this.K8b1c4d74f51aaac12a(this.rightColumn);
         this.contentHeight = Math.max(leftH, rightH);
         float maxScroll = Math.max(0.0F, this.u(this.contentHeight) - viewH + this.u(6.0F));
         this.scroll.target = K981e054bcc0843bcd9(this.scroll.target, 0.0F, maxScroll);
         this.scroll.update(this.dt, 18.0F);
         this.scroll.value = K981e054bcc0843bcd9(this.scroll.value, 0.0F, maxScroll);
         this.K474ac610b443bade50(c, x, viewTop, w, viewH);
         float colX = x + this.u(18.0F);
         this.Kcb1ad6ca8776153de2(c, this.leftColumn, colX, viewTop - this.scroll.value, viewTop, viewBottom);
         this.Kcb1ad6ca8776153de2(c, this.rightColumn, colX + this.u(247.5F), viewTop - this.scroll.value, viewTop, viewBottom);
         if (modules.isEmpty()) {
            String empty = !this.search.isEmpty() ? "Nothing found" : (this.category == Kf76f042931183af5ba.COSMETICS ? (Ka35e7d6b31db9984f1.getInstance().isRussian() ? "Косметика скоро появится" : "Cosmetics coming soon") : "No modules");
            float ew = c.textWidth(Kc517862407c2394736.MEDIUM, empty, this.u(9.6F));
            c.text(Kc517862407c2394736.MEDIUM, empty, x + w / 2.0F - ew / 2.0F, viewTop + this.u(40.0F), this.u(9.6F), K44a4682200f8fd6f20(0.36F));
         }

         this.Kf012206dd4fc483166(c);
         if (maxScroll > 0.5F) {
            float trackTop = viewTop + this.u(2.0F);
            float trackH = viewH - this.u(4.0F);
            float thumbH = Math.max(this.u(24.0F), trackH * viewH / (this.u(this.contentHeight) + this.u(6.0F)));
            float thumbY = trackTop + (trackH - thumbH) * (this.scroll.value / maxScroll);
            c.rect(x + this.u(515.0F), thumbY, this.u(1.5F), thumbH, this.u(0.75F), K44a4682200f8fd6f20(0.75F));
         }

      }
   }

   private void K7cc8ebf4f0d9cce435(K224f9523984e1fa68b c) {
      List<K51ad80c57a5258fb43> presets = K2c59a7f522ec4ea35b.PRESETS;
      int selected = K2c59a7f522ec4ea35b.selected();
      float pad = 9.0F;
      float wDesign = pad * 2.0F + 102.0F + 40.0F;
      float w = this.u(wDesign);
      float h = this.u(28.0F);
      float x = this.ox + this.u(718.0F) / 2.0F - w / 2.0F;
      float y = this.oy - this.u(38.0F) + this.u(1.0F);
      this.paletteRect = new float[]{x, y, w, h};
      this.K14e7e25cdd32117321(c, x, y, w, h, h / 2.0F);
      float maxScroll = 25.0F * (float)Math.max(0, presets.size() - 6);
      this.paletteScroll.target = K981e054bcc0843bcd9(this.paletteScroll.target, 0.0F, maxScroll);
      this.paletteScroll.update(this.dt, 16.0F);
      float innerX = x + this.u(pad);
      float innerW = w - this.u(pad * 2.0F);
      float cy = y + h / 2.0F;
      this.K474ac610b443bade50(c, x + this.u(4.0F), y, w - this.u(8.0F), h);

      for(int i = 0; i < presets.size(); ++i) {
         K51ad80c57a5258fb43 p = (K51ad80c57a5258fb43)presets.get(i);
         float cxDot = innerX + this.u(25.0F * (float)i - this.paletteScroll.value) + this.u(17.0F) / 2.0F;
         float edge = Math.min(cxDot - innerX, innerX + innerW - cxDot) / this.u(8.5F);
         float a = K981e054bcc0843bcd9(edge + 0.35F, 0.0F, 1.0F);
         if (!(a <= 0.01F)) {
            float d0 = this.u(17.0F);
            boolean hover = this.K13bb0cf34813da6244(cxDot - d0 / 2.0F, cy - d0 / 2.0F, d0, d0) && this.K13bb0cf34813da6244(x, y, w, h);
            K7cc58e1b7729233c63 hv = (K7cc58e1b7729233c63)this.paletteHover.computeIfAbsent(i, (k) -> new K7cc58e1b7729233c63(0.0F));
            hv.target = hover ? 1.0F : 0.0F;
            hv.update(this.dt, 18.0F);
            float d = d0 * (1.0F + 0.12F * hv.value);
            if (i == selected) {
               float ring = d + this.u(5.0F);
               c.rect(cxDot - ring / 2.0F, cy - ring / 2.0F, ring, ring, ring / 2.0F, K44a4682200f8fd6f20(0.9F * a));
               float gapR = d + this.u(2.6F);
               c.rect(cxDot - gapR / 2.0F, cy - gapR / 2.0F, gapR, gapR, gapR / 2.0F, new Color(70, 62, 84, Kd6be252ed9cd8bc0cb(255.0F * a)));
            }

            Color c1 = Kcb3d2fcc2c28199e17(p.primary(), a);
            Color c2 = Kcb3d2fcc2c28199e17(p.secondary(), a);
            if (p.dual()) {
               Color mid = Kcb3d2fcc2c28199e17(K2c59a7f522ec4ea35b.mix(p.primary(), p.secondary(), 0.5F), a);
               c.gradient(cxDot - d / 2.0F, cy - d / 2.0F, d, d, d / 2.0F, c1, mid, mid, c2);
            } else {
               c.gradient(cxDot - d / 2.0F, cy - d / 2.0F, d, d, d / 2.0F, c2, c1, c1, c1);
            }

            c.rect(cxDot - d * 0.22F, cy - d * 0.36F, d * 0.44F, d * 0.16F, d * 0.08F, K44a4682200f8fd6f20(0.22F * a));
            this.hit(cxDot - d0 / 2.0F, cy - d0 / 2.0F, d0, d0, (mx, my, b) -> {
               if (b == 0) {
                  K2c59a7f522ec4ea35b.select(i);
                  this.env.playSelect();
               } else if (b == 1) {
                  K2c59a7f522ec4ea35b.select(-1);
                  this.env.playSelect();
               }

            });
         }
      }

      this.Kf012206dd4fc483166(c);
   }

   private static Color Kb0577a84736024ea56(Color a, Color b, float t) {
      t = K981e054bcc0843bcd9(t, 0.0F, 1.0F);
      return new Color(Math.round((float)a.getRed() + (float)(b.getRed() - a.getRed()) * t), Math.round((float)a.getGreen() + (float)(b.getGreen() - a.getGreen()) * t), Math.round((float)a.getBlue() + (float)(b.getBlue() - a.getBlue()) * t), Math.round((float)a.getAlpha() + (float)(b.getAlpha() - a.getAlpha()) * t));
   }

   private static String Kc446015e5fadfabe61(String ru, String en) {
      return Ka35e7d6b31db9984f1.getInstance().isRussian() ? ru : en;
   }

   private void Kbf305353d8f6989727(K224f9523984e1fa68b c, float px, float py, float pw) {
      K697051202eac7cfc74 manager = K697051202eac7cfc74.getInstance();
      float x = px + this.u(18.0F);
      float w = this.u(489.0F);
      float top = py + this.u(50.0F);
      if (this.cosPreviewDragging) {
         this.cosPreviewYaw = (this.cosPreviewYaw + (this.mouseX - this.cosPreviewDragX) / this.s * 1.2F) % 360.0F;
         this.cosPreviewDragX = this.mouseX;
      }

      float chipCy = top + this.u(20.0F) / 2.0F;
      float chipX = x + this.K83156395a3cd313093(c, (K9a0eb2753a3143de82)null, Kc446015e5fadfabe61("Все", "All"), manager.count((K9a0eb2753a3143de82)null), x, chipCy) + this.u(5.0F);

      for(K9a0eb2753a3143de82 type : K9a0eb2753a3143de82.values()) {
         chipX += this.K83156395a3cd313093(c, type, type.plural(), manager.count(type), chipX, chipCy) + this.u(5.0F);
      }

      float bin = this.u(20.0F);
      float binX = x + w - bin;
      float binY = chipCy - bin / 2.0F;
      boolean anyWorn = manager.equippedCount() > 0;
      boolean binHover = anyWorn && this.K13bb0cf34813da6244(binX, binY, bin, bin);
      this.cosBinHover.target = binHover ? 1.0F : 0.0F;
      this.cosBinHover.update(this.dt, 16.0F);
      float bh = this.cosBinHover.value;
      c.rect(binX, binY, bin, bin, bin / 2.0F, K44a4682200f8fd6f20(anyWorn ? 0.06F + 0.02F * bh : 0.035F));
      if (bh > 0.01F) {
         c.rect(binX, binY, bin, bin, bin / 2.0F, new Color(255, 110, 120, Kd6be252ed9cd8bc0cb(70.0F * bh)));
      }

      Color binIcon = !anyWorn ? K44a4682200f8fd6f20(0.2F) : Kb0577a84736024ea56(K44a4682200f8fd6f20(0.62F), new Color(255, 170, 175, 255), bh);
      c.icon(K756e30c0738d06c926.TRASH, binX + bin / 2.0F, chipCy, this.u(10.0F), binIcon);
      if (anyWorn) {
         this.hit(binX, binY, bin, bin, (mx, my, b) -> {
            if (b == 0) {
               manager.unequipAll();
               this.cosPreviewItem = -1;
               this.env.playSelect();
            }

         });
      }

      String worn = Kc446015e5fadfabe61("Надето", "Equipped");
      int var10000 = manager.equippedCount();
      String wornCount = var10000 + "/" + K9a0eb2753a3143de82.values().length;
      float wcW = c.textWidth(Kc517862407c2394736.SEMIBOLD, wornCount, this.u(8.4F));
      float wW = c.textWidth(Kc517862407c2394736.MEDIUM, worn, this.u(8.4F));
      float countRight = binX - this.u(8.0F);
      if (bh < 0.5F) {
         float a = 1.0F - bh * 2.0F;
         c.text(Kc517862407c2394736.SEMIBOLD, wornCount, countRight - wcW, chipCy, this.u(8.4F), accent(a));
         c.text(Kc517862407c2394736.MEDIUM, worn, countRight - wcW - this.u(5.0F) - wW, chipCy, this.u(8.4F), K44a4682200f8fd6f20(0.31F * a));
      } else {
         String hint = Kc446015e5fadfabe61("Снять всё", "Remove all");
         float hw = c.textWidth(Kc517862407c2394736.SEMIBOLD, hint, this.u(8.4F));
         c.text(Kc517862407c2394736.SEMIBOLD, hint, countRight - hw, chipCy, this.u(8.4F), new Color(255, 170, 175, Kd6be252ed9cd8bc0cb(255.0F * (bh - 0.5F) * 2.0F)));
      }

      List<Ka5dfe6b89219e29d09> items = new ArrayList();

      for(Ka5dfe6b89219e29d09 e : manager.entries()) {
         if (this.cosFilter == null || e.type == this.cosFilter) {
            items.add(e);
         }
      }

      int cols = 2;
      float gridW = 326.0F;
      float cardW = (gridW - 7.0F * (float)(cols - 1)) / (float)cols;
      int rows = (items.size() + cols - 1) / cols;
      this.contentHeight = (float)rows * 128.0F + (float)Math.max(0, rows - 1) * 7.0F;
      float viewTop = py + this.u(80.0F);
      float viewBottom = py + this.u(452.0F);
      float viewH = viewBottom - viewTop;
      float maxScroll = Math.max(0.0F, this.u(this.contentHeight) - viewH + this.u(6.0F));
      this.scroll.target = K981e054bcc0843bcd9(this.scroll.target, 0.0F, maxScroll);
      this.scroll.update(this.dt, 18.0F);
      this.scroll.value = K981e054bcc0843bcd9(this.scroll.value, 0.0F, maxScroll);
      this.K474ac610b443bade50(c, x, viewTop, this.u(gridW), viewH);

      for(int i = 0; i < items.size(); ++i) {
         float cx = x + this.u((cardW + 7.0F) * (float)(i % cols));
         float cy = viewTop + this.u(135.0F * (float)(i / cols)) - this.scroll.value;
         if (!(cy + this.u(128.0F) < viewTop - this.u(2.0F)) && !(cy > viewBottom + this.u(2.0F))) {
            this.Kf8e3148c666091a2d9(c, manager, (Ka5dfe6b89219e29d09)items.get(i), cx, cy, this.u(cardW), this.u(128.0F));
         }
      }

      if (items.isEmpty()) {
         String empty = Kc446015e5fadfabe61("Здесь пока пусто", "Nothing here yet");
         float ew = c.textWidth(Kc517862407c2394736.MEDIUM, empty, this.u(9.6F));
         c.text(Kc517862407c2394736.MEDIUM, empty, px + pw / 2.0F - ew / 2.0F, viewTop + this.u(40.0F), this.u(9.6F), K44a4682200f8fd6f20(0.36F));
      }

      this.Kf012206dd4fc483166(c);
      this.K74a0d43a52db03c0fe(c, x + this.u(gridW + 7.0F), viewTop, this.u(156.0F), viewH);
      if (maxScroll > 0.5F) {
         float trackTop = viewTop + this.u(2.0F);
         float trackH = viewH - this.u(4.0F);
         float thumbH = Math.max(this.u(24.0F), trackH * viewH / (this.u(this.contentHeight) + this.u(6.0F)));
         float thumbY = trackTop + (trackH - thumbH) * (this.scroll.value / maxScroll);
         c.rect(x + this.u(gridW - 2.0F), thumbY, this.u(1.5F), thumbH, this.u(0.75F), K44a4682200f8fd6f20(0.75F));
      }

   }

   private void K74a0d43a52db03c0fe(K224f9523984e1fa68b c, float x, float y, float w, float h) {
      c.rect(x, y, w, h, this.u(9.0F), K44a4682200f8fd6f20(0.07F));
      c.gradient(x + this.u(1.0F), y + this.u(1.0F), w - this.u(2.0F), h - this.u(2.0F), this.u(8.0F), K8c29593505886788a7(0.32F), K8c29593505886788a7(0.32F), accent(0.12F), accent(0.12F));
      c.text(Kc517862407c2394736.SEMIBOLD, "Cosmetic Preview", x + this.u(12.0F), y + this.u(17.0F), this.u(9.6F), K44a4682200f8fd6f20(0.9F));
      Ka5dfe6b89219e29d09 entry = K697051202eac7cfc74.getInstance().entry(this.cosPreviewItem);
      String label = entry == null ? Kc446015e5fadfabe61("Текущий образ", "Equipped look") : entry.name;
      c.text(Kc517862407c2394736.MEDIUM, K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, label, this.u(8.4F), w - this.u(24.0F)), x + this.u(12.0F), y + this.u(32.0F), this.u(8.4F), accent(0.85F));
      float modelY = y + this.u(45.0F);
      float modelH = h - this.u(111.0F);
      this.cosPreviewRect = new float[]{x, modelY, w, modelH};
      if (!c.cosmeticPreview(x, modelY, w, modelH, this.cosPreviewYaw, this.cosPreviewZoom, this.cosPreviewItem)) {
         c.head(x + w / 2.0F - this.u(20.0F), modelY + modelH / 2.0F - this.u(20.0F), this.u(40.0F));
         c.text(Kc517862407c2394736.MEDIUM, Kc446015e5fadfabe61("Войдите в мир для 3D", "Join a world for 3D"), x + this.u(12.0F), modelY + modelH / 2.0F + this.u(35.0F), this.u(8.4F), K44a4682200f8fd6f20(0.6F));
      }

      this.hit(x, modelY, w, modelH, (mx, my, b) -> {
         if (b == 0) {
            this.cosPreviewDragging = true;
            this.cosPreviewDragX = mx;
         }

      });
      c.text(Kc517862407c2394736.MEDIUM, Kc446015e5fadfabe61("Тяните — поворот", "Drag to rotate"), x + this.u(12.0F), y + h - this.u(51.0F), this.u(8.4F), K44a4682200f8fd6f20(0.5F));
      c.text(Kc517862407c2394736.MEDIUM, Kc446015e5fadfabe61("Колесо — масштаб", "Scroll to zoom"), x + this.u(12.0F), y + h - this.u(38.0F), this.u(8.4F), K44a4682200f8fd6f20(0.5F));
      float bw = (w - this.u(28.0F)) / 3.0F;
      String[] labels = new String[]{"Front", "Back", "Reset"};

      for(int i = 0; i < labels.length; ++i) {
         float bx = x + this.u(8.0F) + (float)i * (bw + this.u(6.0F));
         float by = y + h - this.u(25.0F);
         c.rect(bx, by, bw, this.u(17.0F), this.u(5.0F), K44a4682200f8fd6f20(this.K13bb0cf34813da6244(bx, by, bw, this.u(17.0F)) ? 0.16F : 0.08F));
         float tw = c.textWidth(Kc517862407c2394736.SEMIBOLD, labels[i], this.u(8.4F));
         c.text(Kc517862407c2394736.SEMIBOLD, labels[i], bx + (bw - tw) / 2.0F, by + this.u(8.5F), this.u(8.4F), K44a4682200f8fd6f20(0.8F));
         this.hit(bx, by, bw, this.u(17.0F), (mx, my, b) -> {
            if (b == 0) {
               this.cosPreviewYaw = i == 1 ? 180.0F : (i == 0 ? 0.0F : -25.0F);
               if (i == 2) {
                  this.cosPreviewZoom = 1.0F;
                  this.cosPreviewItem = -1;
               }
            }

         });
      }

   }

   private float K83156395a3cd313093(K224f9523984e1fa68b c, K9a0eb2753a3143de82 type, String label, int count, float x, float cy) {
      String num = String.valueOf(count);
      float labelW = c.textWidth(Kc517862407c2394736.SEMIBOLD, label, this.u(8.4F));
      float numW = c.textWidth(Kc517862407c2394736.MEDIUM, num, this.u(6.6F));
      float h = this.u(20.0F);
      float w = this.u(10.0F) + labelW + this.u(5.0F) + numW + this.u(10.0F);
      float y = cy - h / 2.0F;
      boolean active = this.cosFilter == type;
      boolean hover = this.K13bb0cf34813da6244(x, y, w, h);
      K7cc58e1b7729233c63 a = (K7cc58e1b7729233c63)this.cosChip.computeIfAbsent(type == null ? "all" : type.key, (k) -> new K7cc58e1b7729233c63(0.0F));
      a.target = active ? 1.0F : 0.0F;
      a.update(this.dt, 16.0F);
      float on = a.value;
      c.rect(x, y, w, h, h / 2.0F, K44a4682200f8fd6f20(0.06F + (hover ? 0.03F : 0.0F)));
      if (on > 0.01F) {
         c.rect(x, y, w, h, h / 2.0F, accent(0.5F * on));
         c.rect(x + this.u(0.7F), y + this.u(0.7F), w - this.u(1.4F), h - this.u(1.4F), h / 2.0F - this.u(0.7F), new Color(98, 84, 118, Kd6be252ed9cd8bc0cb(255.0F * on)));
         c.rect(x + this.u(0.7F), y + this.u(0.7F), w - this.u(1.4F), h - this.u(1.4F), h / 2.0F - this.u(0.7F), accent(0.14F * on));
      }

      c.text(Kc517862407c2394736.SEMIBOLD, label, x + this.u(10.0F), cy, this.u(8.4F), K44a4682200f8fd6f20(0.62F + 0.38F * on));
      c.text(Kc517862407c2394736.MEDIUM, num, x + this.u(10.0F) + labelW + this.u(5.0F), cy, this.u(6.6F), on > 0.5F ? accent(1.0F) : K44a4682200f8fd6f20(0.31F));
      this.hit(x, y, w, h, (mx, my, b) -> {
         if (b == 0 && this.cosFilter != type) {
            this.cosFilter = type;
            this.scroll.target = 0.0F;
            this.env.playSelect();
         }

      });
      return w;
   }

   private void Kf8e3148c666091a2d9(K224f9523984e1fa68b c, K697051202eac7cfc74 manager, Ka5dfe6b89219e29d09 e, float x, float y, float w, float h) {
      boolean equipped = manager.isEquipped(e);
      boolean hover = this.K13bb0cf34813da6244(x, y, w, h) && this.K13bb0cf34813da6244(this.clip[0], this.clip[1], this.clip[2] - this.clip[0], this.clip[3] - this.clip[1]);
      if (hover && !this.cosPreviewDragging) {
         this.cosPreviewItem = e.id;
      }

      K7cc58e1b7729233c63 hv = (K7cc58e1b7729233c63)this.cosHover.computeIfAbsent(e.id, (k) -> new K7cc58e1b7729233c63(0.0F));
      hv.target = hover ? 1.0F : 0.0F;
      hv.update(this.dt, 14.0F);
      K7cc58e1b7729233c63 eq = (K7cc58e1b7729233c63)this.cosEquip.computeIfAbsent(e.id, (k) -> new K7cc58e1b7729233c63(equipped ? 1.0F : 0.0F));
      eq.target = equipped ? 1.0F : 0.0F;
      eq.update(this.dt, 14.0F);
      float on = eq.value;
      float r = this.u(9.0F);
      c.rect(x, y, w, h, r, K44a4682200f8fd6f20(0.085F + 0.04F * hv.value));
      if (on > 0.01F) {
         c.rect(x, y, w, h, r, accent(0.65F * on));
      }

      c.glass(x + this.u(0.7F), y + this.u(0.7F), w - this.u(1.4F), h - this.u(1.4F), r - this.u(0.7F), GLASS_TINT, GLASS_SOLID);
      c.rect(x + this.u(0.7F), y + this.u(0.7F), w - this.u(1.4F), h - this.u(1.4F), r - this.u(0.7F), K44a4682200f8fd6f20(0.045F + 0.025F * hv.value));
      float tx = x + this.u(6.0F);
      float ty = y + this.u(6.0F);
      float tw = w - this.u(12.0F);
      float th = this.u(78.0F);
      c.rect(tx, ty, tw, th, this.u(7.0F), K8c29593505886788a7(0.3F));
      c.gradient(tx, ty, tw, th, this.u(7.0F), K44a4682200f8fd6f20(0.035F), K44a4682200f8fd6f20(0.035F), accent(0.2F * on), accent(0.2F * on));
      float lift = this.u(2.5F) * hv.value;
      float size = this.u(60.0F) * (1.0F + 0.07F * hv.value);
      c.image(e.preview.toString(), tx + tw / 2.0F - size / 2.0F, ty + th / 2.0F - size / 2.0F - this.u(2.0F) - lift, size, size, Color.WHITE);
      String type = e.type.single().toUpperCase(Locale.ROOT);
      float badgeW = c.textWidth(Kc517862407c2394736.BOLD, type, this.u(6.072F)) + this.u(9.0F);
      c.rect(tx + this.u(5.0F), ty + this.u(5.0F), badgeW, this.u(11.0F), this.u(5.5F), K8c29593505886788a7(0.35F));
      c.text(Kc517862407c2394736.BOLD, type, tx + this.u(9.5F), ty + this.u(10.5F), this.u(6.072F), K44a4682200f8fd6f20(0.55F));
      if (on > 0.01F) {
         float d = this.u(12.0F);
         float bx = tx + tw - this.u(5.0F) - d;
         float by = ty + this.u(5.0F);
         c.rect(bx, by, d, d, d / 2.0F, accent(on));
         c.icon(K756e30c0738d06c926.CHECK, bx + d / 2.0F, by + d / 2.0F, this.u(7.0F), Kcb3d2fcc2c28199e17(CHECK_DARK, on));
      }

      String name = K78f466497bf3f7de26(c, Kc517862407c2394736.SEMIBOLD, e.name, this.u(9.8F), w - this.u(14.0F));
      c.text(Kc517862407c2394736.SEMIBOLD, name, x + this.u(8.0F), y + this.u(96.0F), this.u(9.8F), K44a4682200f8fd6f20(0.62F + 0.38F * Math.max(on, hv.value * 0.6F)));
      float bx = x + this.u(6.0F);
      float by = y + h - this.u(22.0F);
      float bw = w - this.u(12.0F);
      float bh = this.u(16.0F);
      c.rect(bx, by, bw, bh, bh / 2.0F, K44a4682200f8fd6f20(0.07F + 0.04F * hv.value));
      if (on > 0.01F) {
         c.rect(bx, by, bw, bh, bh / 2.0F, accent(on));
      }

      String action = equipped ? Kc446015e5fadfabe61("Надето", "Equipped") : Kc446015e5fadfabe61("Надеть", "Equip");
      if (equipped && hover) {
         action = Kc446015e5fadfabe61("Снять", "Remove");
      }

      float aw = c.textWidth(Kc517862407c2394736.SEMIBOLD, action, this.u(8.4F));
      Color actionColor = on > 0.5F ? new Color(36, 24, 52, 255) : K44a4682200f8fd6f20(0.62F + 0.3F * hv.value);
      c.text(Kc517862407c2394736.SEMIBOLD, action, bx + bw / 2.0F - aw / 2.0F, by + bh / 2.0F, this.u(8.4F), actionColor);
      this.hit(x, y, w, h, (mx, my, b) -> {
         if (b == 0) {
            manager.toggle(e);
            this.cosPreviewItem = -1;
            this.env.playSelect();
         }

      });
   }

   private void K147f6e78a434cab85c(K224f9523984e1fa68b c, float px, float py) {
      float cy = py + this.u(29.5F);
      float x = px + this.u(33.0F);
      c.logo(x + this.u(1.0F), cy, this.u(8.0F), K44a4682200f8fd6f20(0.62F));
      x += this.u(13.0F);
      String name = this.env.clientName();
      c.text(Kc517862407c2394736.SEMIBOLD, name, x, cy, this.u(9.6F), K44a4682200f8fd6f20(0.62F));
      x += c.textWidth(Kc517862407c2394736.SEMIBOLD, name, this.u(9.6F)) + this.u(10.0F);
      Color muted = K44a4682200f8fd6f20(0.31F);
      c.text(Kc517862407c2394736.MEDIUM, "/", x, cy, this.u(9.6F), muted);
      x += c.textWidth(Kc517862407c2394736.MEDIUM, "/", this.u(9.6F)) + this.u(9.0F);
      boolean searching = !this.search.isEmpty();
      String middle = searching ? "Search" : "Categories";
      c.text(Kc517862407c2394736.MEDIUM, middle, x, cy, this.u(9.6F), muted);
      x += c.textWidth(Kc517862407c2394736.MEDIUM, middle, this.u(9.6F)) + this.u(10.0F);
      c.text(Kc517862407c2394736.MEDIUM, "/", x, cy, this.u(9.6F), muted);
      x += c.textWidth(Kc517862407c2394736.MEDIUM, "/", this.u(9.6F)) + this.u(9.0F);
      if (searching) {
         String q = K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, "\"" + String.valueOf(this.search) + "\"", this.u(9.6F), this.u(220.0F));
         c.text(Kc517862407c2394736.MEDIUM, q, x, cy, this.u(9.6F), muted);
      } else {
         c.icon(iconOf(this.category), x + this.u(4.5F), cy, this.u(10.5F), muted);
         x += this.u(15.0F);
         c.text(Kc517862407c2394736.MEDIUM, labelOf(this.category), x, cy, this.u(9.6F), muted);
      }

   }

   private List<Kab8a8c478f7d0077cd> K88819cee7ed2b88bc5() {
      List<Kab8a8c478f7d0077cd> list = new ArrayList();
      String q = this.search.toString().trim().toLowerCase(Locale.ROOT);
      String qTranslit = K34f641a76dfc7d83e2(q);

      for(Kab8a8c478f7d0077cd m : this.env.modules()) {
         if (q.isEmpty()) {
            if (m.getCategory() == this.category) {
               list.add(m);
            }
         } else {
            String a = m.getName().toLowerCase(Locale.ROOT);
            String b = m.getDisplayName().toLowerCase(Locale.ROOT);
            String a2 = a.replace(" ", "");
            String q2 = q.replace(" ", "");
            if (a.contains(q) || b.contains(q) || a2.contains(q2) || !qTranslit.equals(q) && (a.contains(qTranslit) || a2.contains(qTranslit.replace(" ", "")))) {
               list.add(m);
            }
         }
      }

      list.sort(Comparator.comparing((mx) -> mx.getDisplayName().toLowerCase(Locale.ROOT)));
      return list;
   }

   private void K618ccbb65c95dd2169(List<Kab8a8c478f7d0077cd> modules) {
      StringBuilder key = new StringBuilder(this.search.isEmpty() ? this.category.name() : "?" + String.valueOf(this.search));

      for(Kab8a8c478f7d0077cd m : modules) {
         key.append('|').append(System.identityHashCode(m));
      }

      String k = key.toString();
      if (!k.equals(this.layoutKey)) {
         this.layoutKey = k;
         this.leftColumn.clear();
         this.rightColumn.clear();
         float lh = 0.0F;
         float rh = 0.0F;

         for(Kab8a8c478f7d0077cd m : modules) {
            K2744bf03883d58054c st = this.K7a67e9370fc7889d7f(m);
            float h = this.K3bd4f409a2d839de64(m, st.expanded) + 5.5F;
            if (lh <= rh) {
               this.leftColumn.add(m);
               lh += h;
            } else {
               this.rightColumn.add(m);
               rh += h;
            }
         }

      }
   }

   private float K8b1c4d74f51aaac12a(List<Kab8a8c478f7d0077cd> column) {
      float h = 0.0F;

      for(Kab8a8c478f7d0077cd m : column) {
         h += this.K7a67e9370fc7889d7f(m).height.value + 5.5F;
      }

      return Math.max(0.0F, h - 5.5F);
   }

   private void Kcb1ad6ca8776153de2(K224f9523984e1fa68b c, List<Kab8a8c478f7d0077cd> column, float x, float y, float viewTop, float viewBottom) {
      float cy = y;

      for(Kab8a8c478f7d0077cd m : column) {
         float h = this.u(this.K7a67e9370fc7889d7f(m).height.value);
         if (cy + h >= viewTop - this.u(2.0F) && cy <= viewBottom + this.u(2.0F)) {
            this.K30b12e75a2ce3bdc04(c, m, x, cy, this.u(241.5F), h);
         }

         cy += h + this.u(5.5F);
      }

   }

   private K2744bf03883d58054c K7a67e9370fc7889d7f(Kab8a8c478f7d0077cd m) {
      return (K2744bf03883d58054c)this.cards.computeIfAbsent(m, (k) -> new K2744bf03883d58054c());
   }

   private List<Kdced9e23f1e229ba9d<?>> K7327fddd5a50da3f11(Kab8a8c478f7d0077cd m) {
      List<Kdced9e23f1e229ba9d<?>> list = new ArrayList();

      for(Kdced9e23f1e229ba9d<?> setting : m.getSettings()) {
         if (setting.isVisible()) {
            list.add(setting);
         }
      }

      return list;
   }

   private float K3bd4f409a2d839de64(Kab8a8c478f7d0077cd m, boolean expanded) {
      if (!expanded) {
         return 31.0F;
      } else {
         List<Kdced9e23f1e229ba9d<?>> settings = this.K7327fddd5a50da3f11(m);
         if (settings.isEmpty()) {
            return 31.0F;
         } else {
            float h = 41.0F;

            for(Kdced9e23f1e229ba9d<?> setting : settings) {
               h += this.K934289fbd3858700fa(setting) + 8.0F;
            }

            return h;
         }
      }
   }

   private float K934289fbd3858700fa(Kdced9e23f1e229ba9d<?> setting) {
      if (setting instanceof Kb9e875ead00e0d03bd) {
         return 21.0F;
      } else {
         if (setting instanceof Kbd66519397e9a89967) {
            Kbd66519397e9a89967 mode = (Kbd66519397e9a89967)setting;
            if (this.K177f3057282beef15c(mode)) {
               return 37.0F;
            }
         }

         if (setting instanceof K00fef91b3d4d1b3ecc) {
            return 12.0F;
         } else if (setting instanceof Kb9fa339b3f0f7560be) {
            return 18.0F;
         } else {
            return setting instanceof K86d8caed0012682c7b ? 12.0F : 20.0F;
         }
      }
   }

   private void K30b12e75a2ce3bdc04(K224f9523984e1fa68b c, Kab8a8c478f7d0077cd m, float x, float y, float w, float h) {
      float hoverX = Math.max(x, this.clip[0]);
      float hoverY = Math.max(y, this.clip[1]);
      float hoverW = Math.min(x + w, this.clip[2]) - hoverX;
      float hoverH = Math.min(y + h, this.clip[3]) - hoverY;
      if (hoverW > 0.0F && hoverH > 0.0F) {
         this.hoverCards.add(new K451be487a8a30ea1c8(hoverX, hoverY, hoverW, hoverH, m));
      }

      K2744bf03883d58054c st = this.K7a67e9370fc7889d7f(m);
      boolean hasSettings = !this.K7327fddd5a50da3f11(m).isEmpty();
      float open = st.expand.value;
      c.rect(x, y, w, h, this.u(7.0F), K44a4682200f8fd6f20(0.085F));
      c.glass(x + this.u(0.6F), y + this.u(0.6F), w - this.u(1.2F), h - this.u(1.2F), this.u(6.4F), GLASS_TINT, GLASS_SOLID);
      c.rect(x + this.u(0.6F), y + this.u(0.6F), w - this.u(1.2F), h - this.u(1.2F), this.u(6.4F), K44a4682200f8fd6f20(0.045F));
      float headerH = this.u(31.0F);
      float cy = y + this.u(15.0F);
      float right = x + w - this.u(11.0F);
      boolean locked = this.env.isLocked(m);
      this.hit(x, y, w, headerH, m, (mx, my, b) -> {
         if (b != 2 && this.bindingModule == m) {
            this.bindingModule = null;
         }

         if (b == 0) {
            if (!locked) {
               m.toggle();
            } else if (hasSettings) {
               this.K45279d1975f61ff6a1(m);
            }
         } else if (b == 1) {
            if (hasSettings) {
               this.K45279d1975f61ff6a1(m);
            }
         } else if (b == 2) {
            this.K246a217a36ea133b9b(m);
         }

      });
      float cursor = right;
      if (hasSettings) {
         float chevronCx = x + w - this.u(13.5F);
         c.icon(open > 0.5F ? K756e30c0738d06c926.CHEVRON_UP : K756e30c0738d06c926.CHEVRON_DOWN, chevronCx, cy, this.u(10.5F), K44a4682200f8fd6f20(0.32F));
         this.hit(chevronCx - this.u(6.0F), y, this.u(12.0F), headerH, m, (mx, my, b) -> {
            if (b != 0 && b != 1) {
               if (b == 2) {
                  this.K246a217a36ea133b9b(m);
               }
            } else {
               this.K45279d1975f61ff6a1(m);
            }

         });
         cursor = x + w - this.u(23.0F);
      }

      float toggleW = this.u(16.0F);
      float toggleH = this.u(8.0F);
      float toggleX = cursor - toggleW;
      if (!locked) {
         float t = st.toggle.value;
         c.rect(toggleX, cy - toggleH / 2.0F, toggleW, toggleH, toggleH / 2.0F, K40027418113552ac83(K44a4682200f8fd6f20(0.08F), accent(0.32F), t));
         float knob = this.u(4.6F);
         float knobX = toggleX + this.u(1.8F) + (toggleW - this.u(3.6F) - knob) * t;
         c.rect(knobX, cy - knob / 2.0F, knob, knob, this.u(0.9F), K40027418113552ac83(K44a4682200f8fd6f20(0.16F), accent(1.0F), t));
         this.hit(toggleX - this.u(1.0F), y, toggleW + this.u(2.0F), headerH, m, (mx, my, b) -> {
            if (b == 0) {
               m.toggle();
            } else if (b == 1 && hasSettings) {
               this.K45279d1975f61ff6a1(m);
            } else if (b == 2) {
               this.K246a217a36ea133b9b(m);
            }

         });
      }

      String description = m.getDescription();
      if (description != null && !description.isBlank()) {
         float infoCx = (locked ? cursor : toggleX) - this.u(10.0F);
         float d = this.u(8.0F);
         boolean infoHovered = this.K0f2dbddffe52521042(infoCx - d, cy - d, d * 2.0F, d * 2.0F);
         c.icon(K756e30c0738d06c926.INFO, infoCx, cy, this.u(9.6F), K44a4682200f8fd6f20(infoHovered ? 0.5F : 0.32F));
         if (infoHovered) {
            this.tooltip = description;
            this.tooltipOwner = m;
            this.tooltipX = infoCx;
            this.tooltipY = cy - d / 2.0F;
         }
      }

      Color titleCol = K40027418113552ac83(K44a4682200f8fd6f20(0.62F), accent(1.0F), st.toggle.value);
      float titleX = x + this.u(12.0F);
      boolean binding = this.bindingModule == m;
      String key = binding ? "Press key" : Kc0a9a2a9f7a9a02b5a(this.env.keyName(m.getBind()));
      float kw = !binding && !m.hasBind() ? 0.0F : Math.max(this.u(12.0F), c.textWidth(Kc517862407c2394736.BOLD, key, this.u(7.0F)) + this.u(6.0F));
      float titleRight = (locked ? cursor : toggleX) - this.u(19.0F);
      float titleSpace = Math.max(0.0F, titleRight - titleX - (kw > 0.0F ? kw + this.u(7.0F) : 0.0F));
      String title = K78f466497bf3f7de26(c, Kc517862407c2394736.SEMIBOLD, m.getDisplayName(), this.u(9.6F), titleSpace);
      c.text(Kc517862407c2394736.SEMIBOLD, title, titleX, cy, this.u(9.6F), titleCol);
      float titleW = c.textWidth(Kc517862407c2394736.SEMIBOLD, title, this.u(9.6F));
      if (binding || m.hasBind()) {
         float kx = titleX + titleW + this.u(7.0F);
         float kh = this.u(12.0F);
         c.rect(kx, cy - kh / 2.0F, kw, kh, this.u(3.0F), binding ? accent(0.25F) : K44a4682200f8fd6f20(0.08F));
         float tw = c.textWidth(Kc517862407c2394736.BOLD, key, this.u(7.0F));
         c.text(Kc517862407c2394736.BOLD, key, kx + kw / 2.0F - tw / 2.0F, cy, this.u(7.0F), binding ? accent(1.0F) : K44a4682200f8fd6f20(0.6F));
         this.hit(kx, cy - kh / 2.0F, kw, kh, m, (mx, my, b) -> this.K246a217a36ea133b9b(m));
      }

      if (!(open <= 0.001F) && hasSettings && !(h <= headerH + this.u(1.0F))) {
         this.K474ac610b443bade50(c, x, y + headerH, w, h - headerH);
         this.Kf517e988cdcbdade48(c, x + this.u(12.0F), y + headerH, w - this.u(23.0F), K44a4682200f8fd6f20(0.07F * open));
         float ry = y + headerH + this.u(9.0F);

         for(Kdced9e23f1e229ba9d<?> setting : this.K7327fddd5a50da3f11(m)) {
            float rh = this.u(this.K934289fbd3858700fa(setting));
            if (ry > this.clip[3]) {
               break;
            }

            if (ry + rh >= this.clip[1]) {
               this.Ke3c568f83b95f2ed16(c, setting, x + this.u(12.0F), ry, w - this.u(23.0F), rh);
            }

            ry += rh + this.u(8.0F);
         }

         this.Kf012206dd4fc483166(c);
      }
   }

   private void K45279d1975f61ff6a1(Kab8a8c478f7d0077cd m) {
      K2744bf03883d58054c st = this.K7a67e9370fc7889d7f(m);
      st.expanded = !st.expanded;
      if (this.popupSetting != null && m.getSettings().contains(this.popupSetting)) {
         this.Kb85d0643865a962467();
      }

   }

   private void K246a217a36ea133b9b(Kab8a8c478f7d0077cd m) {
      this.commitEdit();
      this.bindingSetting = null;
      this.bindingModule = this.bindingModule == m ? null : m;
   }

   private void Ke3c568f83b95f2ed16(K224f9523984e1fa68b c, Kdced9e23f1e229ba9d<?> setting, float x, float y, float w, float h) {
      if (setting instanceof Kb9e875ead00e0d03bd slider) {
         this.Kdc806e7f548ccefce7(c, slider, x, y, w);
      } else if (setting instanceof K00fef91b3d4d1b3ecc bool) {
         this.Kbc7b083f6783721ba1(c, bool, x, y, w, h);
      } else if (setting instanceof K917aeb5cdb0f7aca26 str) {
         this.K1944444919601c73eb(c, str, x, y, w, h);
      } else if (setting instanceof K1d3d0b4020f933282f bind) {
         this.Ke4d068510725db06ad(c, bind, x, y, w, h);
      } else if (setting instanceof Kb9fa339b3f0f7560be run) {
         this.Kecaa0da10eb0435fb0(c, run, x, y, w, h);
      } else if (setting instanceof Kbd66519397e9a89967 mode) {
         if (this.K177f3057282beef15c(mode)) {
            this.K6668478be13c0e470d(c, mode, x, y, w);
         } else {
            this.K5e4e564de5c5e6f0b1(c, mode, x, y, w, h);
         }
      } else if (setting instanceof Ka4b85e821e3749c3d9 multi) {
         this.Kbc25f20d74a649f0b1(c, multi, x, y, w, h);
      } else if (setting instanceof Ka52d224feda41b0761 color) {
         this.K7c2c56ebc59c1e7e26(c, color, x, y, w, h);
      } else if (setting instanceof K86d8caed0012682c7b) {
         String t = setting.getDisplayName().toUpperCase(Locale.ROOT);
         c.text(Kc517862407c2394736.MEDIUM, t, x, y + h / 2.0F, this.u(7.8F), K44a4682200f8fd6f20(0.27F));
      }

   }

   private void label(K224f9523984e1fa68b c, Kdced9e23f1e229ba9d<?> setting, float x, float cy, float maxW) {
      String text = K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, setting.getDisplayName(), this.u(9.6F), maxW);
      c.text(Kc517862407c2394736.MEDIUM, text, x, cy, this.u(9.6F), K44a4682200f8fd6f20(0.36F));
   }

   private void Kdc806e7f548ccefce7(K224f9523984e1fa68b c, Kb9e875ead00e0d03bd slider, float x, float y, float w) {
      float labelCy = y + this.u(5.0F);
      String value = formatSlider(slider);
      float vw = c.textWidth(Kc517862407c2394736.MEDIUM, value, this.u(9.6F));
      this.label(c, slider, x, labelCy, w - vw - this.u(10.0F));
      c.text(Kc517862407c2394736.MEDIUM, value, x + w - vw, labelCy, this.u(9.6F), K44a4682200f8fd6f20(0.36F));
      if (this.dragSlider == slider) {
         this.dragSliderX = x;
         this.dragSliderW = w;
      }

      float trackY = y + this.u(18.0F);
      float trackH = this.u(1.5F);
      float min = slider.getMin();
      float max = slider.getMax();
      Float raw = slider.getValue();
      float v = raw == null ? min : raw;
      float pct = max > min ? K981e054bcc0843bcd9((v - min) / (max - min), 0.0F, 1.0F) : 0.0F;
      c.rect(x, trackY - trackH / 2.0F, w, trackH, trackH / 2.0F, K44a4682200f8fd6f20(0.07F));
      c.rect(x, trackY - trackH / 2.0F, w * pct, trackH, trackH / 2.0F, accent(0.78F));
      float thumb = this.u(7.0F);
      boolean active = this.dragSlider == slider || this.K0f2dbddffe52521042(x, y, w, this.u(21.0F));
      float th = active ? thumb * 1.08F : thumb;
      c.rect(x + w * pct - th / 2.0F, trackY - th / 2.0F, th, th, th / 2.0F, K44a4682200f8fd6f20(1.0F));
      this.hit(x - this.u(3.0F), y + this.u(11.0F), w + this.u(6.0F), this.u(14.0F), (mx, my, b) -> {
         if (b == 0) {
            this.dragSlider = slider;
            this.dragSliderX = x;
            this.dragSliderW = w;
            this.K35a185faddddba4ec4(slider, mx);
         }

      });
   }

   private void Kbc7b083f6783721ba1(K224f9523984e1fa68b c, K00fef91b3d4d1b3ecc bool, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      float box = this.u(12.0F);
      this.label(c, bool, x, cy, w - box - this.u(10.0F));
      float bx = x + w - box;
      boolean on = Boolean.TRUE.equals(bool.getValue());
      c.rect(bx, cy - box / 2.0F, box, box, this.u(3.0F), K44a4682200f8fd6f20(on ? 0.1F : 0.06F));
      if (on) {
         float dot = this.u(3.6F);
         c.rect(bx + box / 2.0F - dot / 2.0F, cy - dot / 2.0F, dot, dot, this.u(1.0F), accent(1.0F));
      }

      this.hit(x, y - this.u(3.5F), w, h + this.u(7.0F), (mx, my, b) -> {
         if (b == 0) {
            bool.toggle();
         }

      });
   }

   private void K1944444919601c73eb(K224f9523984e1fa68b c, K917aeb5cdb0f7aca26 str, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      float fw = Math.min(this.u(106.0F), w * 0.6F);
      this.label(c, str, x, cy, w - fw - this.u(8.0F));
      float fx = x + w - fw;
      float fy = cy - this.u(20.0F) / 2.0F;
      boolean focused = this.editing == str;
      c.rect(fx, fy, fw, this.u(20.0F), this.u(4.0F), K44a4682200f8fd6f20(focused ? 0.085F : 0.06F));
      String value = focused ? this.editBuffer.toString() : (str.getValue() == null ? "" : (String)str.getValue());
      boolean empty = value.isEmpty();
      boolean filled = focused || !empty;
      float ix = fx + this.u(8.0F);
      float is = this.u(9.0F);
      c.rect(ix, cy - is / 2.0F, is, is, this.u(2.5F), filled ? accent(1.0F) : K44a4682200f8fd6f20(0.5F));
      float tw = c.textWidth(Kc517862407c2394736.BOLD, "T", this.u(6.2F));
      c.text(Kc517862407c2394736.BOLD, "T", ix + is / 2.0F - tw / 2.0F, cy, this.u(6.2F), K8c29593505886788a7(filled ? 0.55F : 0.45F));
      float textX = ix + is + this.u(5.0F);
      float maxW = fx + fw - this.u(7.0F) - textX;
      if (empty && !focused) {
         String placeholder = str.getPlaceholder() != null && !str.getPlaceholder().isBlank() ? str.getPlaceholder() : "None";
         c.text(Kc517862407c2394736.MEDIUM, K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, placeholder, this.u(9.6F), maxW), textX, cy, this.u(9.6F), K44a4682200f8fd6f20(0.62F));
      } else {
         String shown = focused ? K60c2f64d4ae8b3a197(c, Kc517862407c2394736.MEDIUM, value, this.u(9.6F), maxW - this.u(2.0F)) : K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, value, this.u(9.6F), maxW);
         c.text(Kc517862407c2394736.MEDIUM, shown, textX, cy, this.u(9.6F), accent(1.0F));
         if (focused && this.Kefbfdb1c9d637d503c()) {
            float sw = c.textWidth(Kc517862407c2394736.MEDIUM, shown, this.u(9.6F));
            c.rect(textX + sw + this.u(0.8F), cy - this.u(5.0F), Math.max(0.5F, this.u(0.9F)), this.u(10.0F), 0.0F, new Color(110, 140, 255, 255));
         }
      }

      this.hit(fx, fy, fw, this.u(20.0F), str, (mx, my, b) -> {
         if (b == 1) {
            this.commitEdit();
            str.setValue("");
         } else {
            if (this.editing != str) {
               this.Kd89f5ac73efc041c8b(str);
            }

         }
      });
   }

   private void Ke4d068510725db06ad(K224f9523984e1fa68b c, K1d3d0b4020f933282f bind, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      boolean listening = this.bindingSetting == bind;
      Integer key = (Integer)bind.getValue();
      boolean bound = key != null && key != -999 && key != -1;
      String text = listening ? "..." : (bound ? Kc0a9a2a9f7a9a02b5a(this.env.keyName(key)) : "Press key");
      boolean accentText = listening || bound;
      float tw = c.textWidth(Kc517862407c2394736.MEDIUM, text, this.u(9.6F));
      float bw = Math.max(this.u(35.0F), tw + this.u(26.0F));
      float bx = x + w - bw;
      float bh = this.u(20.0F);
      this.label(c, bind, x, cy, bx - x - this.u(8.0F));
      c.rect(bx, cy - bh / 2.0F, bw, bh, this.u(4.0F), listening ? accent(0.18F) : K44a4682200f8fd6f20(0.06F));
      c.icon(K756e30c0738d06c926.KEYBOARD, bx + this.u(10.5F), cy, this.u(9.5F), accentText ? accent(1.0F) : K44a4682200f8fd6f20(0.62F));
      c.text(Kc517862407c2394736.MEDIUM, text, bx + this.u(18.0F), cy, this.u(9.6F), accentText ? accent(1.0F) : K44a4682200f8fd6f20(0.62F));
      this.hit(bx, cy - bh / 2.0F, bw, bh, bind, (mx, my, b) -> {
         if (b == 1) {
            bind.setValue(-999);
            this.bindingSetting = null;
         } else if (this.bindingSetting == bind && b != 0) {
            bind.setValue(-100 + b);
            this.bindingSetting = null;
         } else {
            this.commitEdit();
            this.bindingModule = null;
            this.bindingSetting = this.bindingSetting == bind ? null : bind;
         }
      });
   }

   private void Kecaa0da10eb0435fb0(K224f9523984e1fa68b c, Kb9fa339b3f0f7560be run, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      String text = "Click";
      float tw = c.textWidth(Kc517862407c2394736.SEMIBOLD, text, this.u(9.6F));
      float bw = tw + this.u(18.0F);
      float bh = this.u(18.0F);
      float bx = x + w - bw;
      this.label(c, run, x, cy, bx - x - this.u(8.0F));
      boolean hov = this.K0f2dbddffe52521042(bx, cy - bh / 2.0F, bw, bh);
      c.rect(bx, cy - bh / 2.0F, bw, bh, this.u(4.0F), K44a4682200f8fd6f20(hov ? 0.1F : 0.06F));
      c.text(Kc517862407c2394736.SEMIBOLD, text, bx + bw / 2.0F - tw / 2.0F, cy, this.u(9.6F), accent(1.0F));
      this.hit(bx, cy - bh / 2.0F, bw, bh, (mx, my, b) -> {
         if (b == 0 && run.getValue() != null) {
            ((Runnable)run.getValue()).run();
         }

      });
   }

   private boolean K177f3057282beef15c(Kbd66519397e9a89967 mode) {
      int n = mode.getModes().size();
      if (n >= 2 && n <= 4) {
         float segW = 218.5F / (float)n;
         float maxLabel = 0.0F;

         for(String option : mode.getModes()) {
            maxLabel = Math.max(maxLabel, K094f7ce75b75eb5500(option));
         }

         return maxLabel + 22.0F <= segW;
      } else {
         return false;
      }
   }

   private static float K094f7ce75b75eb5500(String text) {
      return (float)text.length() * 9.6F * 0.56F;
   }

   private void K6668478be13c0e470d(K224f9523984e1fa68b c, Kbd66519397e9a89967 mode, float x, float y, float w) {
      float labelCy = y + this.u(10.0F);
      String current = mode.getValue() == null ? "" : (String)mode.getValue();
      float vw = c.textWidth(Kc517862407c2394736.MEDIUM, current, this.u(9.6F));
      this.label(c, mode, x, labelCy, w - vw - this.u(10.0F));
      c.text(Kc517862407c2394736.MEDIUM, current, x + w - vw, labelCy, this.u(9.6F), K44a4682200f8fd6f20(0.36F));
      float sy = y + this.u(19.0F);
      float sh = this.u(20.0F);
      c.rect(x, sy, w, sh, this.u(5.0F), K44a4682200f8fd6f20(0.05F));
      List<String> options = mode.getModes();
      float segW = w / (float)options.size();
      float scy = sy + sh / 2.0F;

      for(int i = 0; i < options.size(); ++i) {
         String option = (String)options.get(i);
         float sx = x + segW * (float)i;
         boolean selected = option.equals(current);
         float tw = c.textWidth(Kc517862407c2394736.SEMIBOLD, option, this.u(9.6F));
         if (selected) {
            c.rect(sx, sy, segW, sh, this.u(5.0F), K44a4682200f8fd6f20(0.075F));
            float box = this.u(6.5F);
            float groupW = box + this.u(5.0F) + tw;
            float gx = sx + segW / 2.0F - groupW / 2.0F;
            c.rect(gx, scy - box / 2.0F, box, box, this.u(1.0F), accent(1.0F));
            c.icon(K756e30c0738d06c926.CHECK, gx + box / 2.0F, scy, this.u(6.5F), CHECK_DARK);
            c.text(Kc517862407c2394736.SEMIBOLD, option, gx + box + this.u(5.0F), scy, this.u(9.6F), accent(1.0F));
         } else {
            boolean hov = this.K0f2dbddffe52521042(sx, sy, segW, sh);
            c.text(Kc517862407c2394736.SEMIBOLD, option, sx + segW / 2.0F - tw / 2.0F, scy, this.u(9.6F), K44a4682200f8fd6f20(hov ? 0.8F : 0.62F));
         }

         this.hit(sx, sy, segW, sh, (mx, my, b) -> {
            if (b == 0 && !option.equals(mode.getValue())) {
               mode.setValue(option);
            }

         });
      }

   }

   private void K5e4e564de5c5e6f0b1(K224f9523984e1fa68b c, Kbd66519397e9a89967 mode, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      String value = mode.getValue() == null ? "" : (String)mode.getValue();
      this.Kbec7ca5d2aee493b76(c, mode, x, cy, w, value, K8c788a7b392151fdbb.MODE);
   }

   private void Kbc25f20d74a649f0b1(K224f9523984e1fa68b c, Ka4b85e821e3749c3d9 multi, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      List<String> enabled = multi.getList();
      String value = enabled.isEmpty() ? "None" : String.join(", ", enabled);
      this.Kbec7ca5d2aee493b76(c, multi, x, cy, w, value, K8c788a7b392151fdbb.SELECTOR);
   }

   private void Kbec7ca5d2aee493b76(K224f9523984e1fa68b c, Kdced9e23f1e229ba9d<?> setting, float x, float cy, float w, String value, K8c788a7b392151fdbb kind) {
      float fw = Math.min(this.u(106.0F), w * 0.6F);
      float fx = x + w - fw;
      float fh = this.u(20.0F);
      this.label(c, setting, x, cy, fx - x - this.u(8.0F));
      boolean open = this.popupSetting == setting;
      if (open) {
         this.K45a4fcb93034901ad6(fx, cy + fh / 2.0F, fw, cy);
      }

      boolean hov = this.K0f2dbddffe52521042(fx, cy - fh / 2.0F, fw, fh);
      c.rect(fx, cy - fh / 2.0F, fw, fh, this.u(4.0F), K44a4682200f8fd6f20(!open && !hov ? 0.06F : 0.085F));
      float textX = fx + this.u(9.0F);
      float maxW = fw - this.u(28.0F);
      c.text(Kc517862407c2394736.SEMIBOLD, Kfbbd430e1780d48c01(c, Kc517862407c2394736.SEMIBOLD, value, this.u(9.6F), maxW), textX, cy, this.u(9.6F), accent(1.0F));
      c.icon(open ? K756e30c0738d06c926.CHEVRON_UP : K756e30c0738d06c926.CHEVRON_DOWN, fx + fw - this.u(10.5F), cy, this.u(9.5F), K44a4682200f8fd6f20(0.35F));
      this.hit(fx, cy - fh / 2.0F, fw, fh, (mx, my, b) -> {
         if (open) {
            this.Kb85d0643865a962467();
         } else {
            this.K39fc9b58294b87fa12(setting, kind, fx, cy + fh / 2.0F, fw);
         }

      });
   }

   private void K7c2c56ebc59c1e7e26(K224f9523984e1fa68b c, Ka52d224feda41b0761 color, float x, float y, float w, float h) {
      float cy = y + h / 2.0F;
      Color value = color.getValue() == null ? Color.WHITE : (Color)color.getValue();
      String hex = String.format(Locale.ROOT, "#%02X%02X%02X", value.getRed(), value.getGreen(), value.getBlue());
      float tw = c.textWidth(Kc517862407c2394736.MEDIUM, hex, this.u(9.6F));
      float bw = tw + this.u(27.0F);
      float bh = this.u(20.0F);
      float bx = x + w - bw;
      this.label(c, color, x, cy, bx - x - this.u(8.0F));
      boolean open = this.popupSetting == color;
      if (open) {
         this.K45a4fcb93034901ad6(bx, cy + bh / 2.0F, bw, cy);
      }

      c.rect(bx, cy - bh / 2.0F, bw, bh, this.u(4.0F), K44a4682200f8fd6f20(open ? 0.085F : 0.06F));
      float sw = this.u(9.0F);
      c.rect(bx + this.u(8.0F), cy - sw / 2.0F, sw, sw, this.u(2.5F), new Color(value.getRed(), value.getGreen(), value.getBlue(), 255));
      c.text(Kc517862407c2394736.MEDIUM, hex, bx + this.u(8.0F) + sw + this.u(5.0F), cy, this.u(9.6F), K44a4682200f8fd6f20(0.62F));
      this.hit(bx, cy - bh / 2.0F, bw, bh, (mx, my, b) -> {
         if (open) {
            this.Kb85d0643865a962467();
         } else {
            Color.RGBtoHSB(value.getRed(), value.getGreen(), value.getBlue(), this.colorHsb);
            this.K39fc9b58294b87fa12(color, K8c788a7b392151fdbb.COLOR, bx, cy + bh / 2.0F, bw);
         }

      });
   }

   private void K39fc9b58294b87fa12(Kdced9e23f1e229ba9d<?> setting, K8c788a7b392151fdbb kind, float anchorX, float anchorY, float anchorW) {
      this.commitEdit();
      this.popupSetting = setting;
      this.popupKind = kind;
      this.popupAnchorX = anchorX;
      this.popupAnchorY = anchorY;
      this.popupAnchorW = anchorW;
      this.popupScroll = 0.0F;
   }

   private void K45a4fcb93034901ad6(float anchorX, float anchorY, float anchorW, float ownerCy) {
      this.popupAnchorX = anchorX;
      this.popupAnchorY = anchorY;
      this.popupAnchorW = anchorW;
      if (ownerCy >= this.clip[1] && ownerCy <= this.clip[3]) {
         this.popupOwnerVisible = true;
      }

   }

   private void Kb85d0643865a962467() {
      this.popupSetting = null;
      this.popupKind = null;
      this.popupRect = null;
      this.dragColor = null;
   }

   private void K866bbbffb33b929e87(K224f9523984e1fa68b c) {
      if (this.popupKind == K8c788a7b392151fdbb.COLOR) {
         this.K929f069dfc85b8c184(c, (Ka52d224feda41b0761)this.popupSetting);
      } else {
         Kdced9e23f1e229ba9d var5 = this.popupSetting;
         List<String> items;
         if (var5 instanceof Ka4b85e821e3749c3d9) {
            Ka4b85e821e3749c3d9 multi = (Ka4b85e821e3749c3d9)var5;
            items = multi.getAllNames();
         } else {
            var5 = this.popupSetting;
            if (!(var5 instanceof Kbd66519397e9a89967)) {
               this.Kb85d0643865a962467();
               return;
            }

            Kbd66519397e9a89967 mode = (Kbd66519397e9a89967)var5;
            items = mode.getModes();
         }

         if (items.isEmpty()) {
            this.Kb85d0643865a962467();
         } else {
            float maxText = 0.0F;

            for(String item : items) {
               maxText = Math.max(maxText, c.textWidth(Kc517862407c2394736.SEMIBOLD, item, this.u(9.6F)));
            }

            float pw = Math.max(this.u(86.0F), Math.min(this.u(170.0F), maxText + this.u(38.0F)));
            int visible = Math.min(items.size(), 9);
            float pad = this.u(5.0F);
            float ph = pad * 2.0F + this.u(16.0F) * (float)visible;
            float px = this.popupAnchorX + this.popupAnchorW - pw + this.u(1.0F);
            float py = this.popupAnchorY;
            float screenBottom = this.oy + this.u(460.0F) - this.u(4.0F);
            if (py + ph > screenBottom) {
               py = Math.max(this.oy + this.u(4.0F), this.popupAnchorY - this.u(20.0F) - ph);
            }

            this.popupRect = new float[]{px, py, pw, ph};
            float maxScroll = Math.max(0.0F, (float)(items.size() - visible) * this.u(16.0F));
            this.popupScroll = K981e054bcc0843bcd9(this.popupScroll, 0.0F, maxScroll);
            c.rect(px, py, pw, ph, this.u(6.0F), K44a4682200f8fd6f20(0.12F));
            c.glass(px + this.u(0.6F), py + this.u(0.6F), pw - this.u(1.2F), ph - this.u(1.2F), this.u(5.4F), POPUP_TINT, POPUP_SOLID);
            c.rect(px + this.u(0.6F), py + this.u(0.6F), pw - this.u(1.2F), ph - this.u(1.2F), this.u(5.4F), K44a4682200f8fd6f20(0.05F));
            this.K804470b25f6ee21c90(px, py, pw, ph, (mx, my, b) -> {
            });
            this.K474ac610b443bade50(c, px, py + pad * 0.5F, pw, ph - pad);
            float iy = py + pad - this.popupScroll;

            for(String item : items) {
               float icy = iy + this.u(16.0F) / 2.0F;
               Kdced9e23f1e229ba9d var19 = this.popupSetting;
               boolean selected;
               if (var19 instanceof Ka4b85e821e3749c3d9) {
                  Ka4b85e821e3749c3d9 multi = (Ka4b85e821e3749c3d9)var19;
                  selected = multi.isEnabled(item);
               } else {
                  selected = item.equals(((Kbd66519397e9a89967)this.popupSetting).getValue());
               }

               boolean hov = this.K0f2dbddffe52521042(px, iy, pw, this.u(16.0F)) || this.K13bb0cf34813da6244(px, iy, pw, this.u(16.0F));
               if (hov) {
                  c.rect(px + this.u(3.0F), iy, pw - this.u(6.0F), this.u(16.0F), this.u(4.0F), K44a4682200f8fd6f20(0.04F));
               }

               String shown = K78f466497bf3f7de26(c, Kc517862407c2394736.SEMIBOLD, item, this.u(9.6F), pw - this.u(34.0F));
               c.text(Kc517862407c2394736.SEMIBOLD, shown, px + this.u(10.0F), icy, this.u(9.6F), hov ? accent(1.0F) : accent(selected ? 1.0F : 0.92F));
               float box = this.u(9.0F);
               float bx = px + pw - this.u(9.0F) - box;
               c.rect(bx, icy - box / 2.0F, box, box, this.u(2.5F), K44a4682200f8fd6f20(selected ? 0.12F : 0.07F));
               if (selected) {
                  float dot = this.u(3.0F);
                  c.rect(bx + box / 2.0F - dot / 2.0F, icy - dot / 2.0F, dot, dot, this.u(0.9F), accent(1.0F));
               }

               this.K804470b25f6ee21c90(px, iy, pw, this.u(16.0F), (mx, my, b) -> {
                  if (b == 0) {
                     Kdced9e23f1e229ba9d patt0$temp = this.popupSetting;
                     if (patt0$temp instanceof Ka4b85e821e3749c3d9) {
                        Ka4b85e821e3749c3d9 multi = (Ka4b85e821e3749c3d9)patt0$temp;
                        ((List)multi.getValue()).stream().filter((bs) -> bs.getName().equalsIgnoreCase(item)).findFirst().ifPresent(K00fef91b3d4d1b3ecc::toggle);
                        multi.runAction();
                     } else {
                        patt0$temp = this.popupSetting;
                        if (patt0$temp instanceof Kbd66519397e9a89967) {
                           Kbd66519397e9a89967 mode = (Kbd66519397e9a89967)patt0$temp;
                           mode.setValue(item);
                           this.Kb85d0643865a962467();
                        }
                     }

                  }
               });
               iy += this.u(16.0F);
            }

            this.Kf012206dd4fc483166(c);
         }
      }
   }

   private void K929f069dfc85b8c184(K224f9523984e1fa68b c, Ka52d224feda41b0761 setting) {
      float pw = this.u(124.0F);
      float ph = this.u(104.0F);
      float px = this.popupAnchorX + this.popupAnchorW - pw + this.u(1.0F);
      float py = this.popupAnchorY + this.u(2.0F);
      float screenBottom = this.oy + this.u(460.0F) - this.u(4.0F);
      if (py + ph > screenBottom) {
         py = Math.max(this.oy + this.u(4.0F), this.popupAnchorY - this.u(20.0F) - ph - this.u(2.0F));
      }

      this.popupRect = new float[]{px, py, pw, ph};
      c.rect(px, py, pw, ph, this.u(6.0F), K44a4682200f8fd6f20(0.12F));
      c.glass(px + this.u(0.6F), py + this.u(0.6F), pw - this.u(1.2F), ph - this.u(1.2F), this.u(5.4F), POPUP_TINT, POPUP_SOLID);
      this.K804470b25f6ee21c90(px, py, pw, ph, (mx, my, bx) -> {
      });
      float pad = this.u(7.0F);
      float svX = px + pad;
      float svY = py + pad;
      float svW = pw - pad * 2.0F;
      float svH = this.u(70.0F);
      Color hueColor = Color.getHSBColor(this.colorHsb[0], 1.0F, 1.0F);
      c.gradient(svX, svY, svW, svH, this.u(4.0F), Color.WHITE, hueColor, Color.WHITE, hueColor);
      c.gradient(svX, svY, svW, svH, this.u(4.0F), new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), Color.BLACK, Color.BLACK);
      float knob = this.u(5.0F);
      float kx = svX + svW * this.colorHsb[1];
      float ky = svY + svH * (1.0F - this.colorHsb[2]);
      c.rect(kx - knob / 2.0F - this.u(0.8F), ky - knob / 2.0F - this.u(0.8F), knob + this.u(1.6F), knob + this.u(1.6F), knob, Color.WHITE);
      c.rect(kx - knob / 2.0F, ky - knob / 2.0F, knob, knob, knob / 2.0F, Color.getHSBColor(this.colorHsb[0], this.colorHsb[1], this.colorHsb[2]));
      this.K804470b25f6ee21c90(svX, svY, svW, svH, (mx, my, bx) -> {
         this.dragColor = setting;
         this.dragColorPart = 1;
         this.dragColorRect = new float[]{svX, svY, svW, svH};
         this.K77e207c9f2a5eded20(mx, my);
      });
      float hueY = svY + svH + this.u(7.0F);
      float hueH = this.u(6.0F);
      int segments = 6;
      float segW = svW / (float)segments;

      for(int i = 0; i < segments; ++i) {
         Color a = Color.getHSBColor((float)i / (float)segments, 1.0F, 1.0F);
         Color b = Color.getHSBColor((float)(i + 1) / (float)segments, 1.0F, 1.0F);
         float r = i != 0 && i != segments - 1 ? 0.0F : hueH / 2.0F;
         c.gradient(svX + segW * (float)i - (i > 0 ? this.u(0.3F) : 0.0F), hueY, segW + (i > 0 ? this.u(0.3F) : 0.0F), hueH, r, a, b, a, b);
      }

      float hx = svX + svW * this.colorHsb[0];
      c.rect(hx - this.u(2.0F), hueY - this.u(1.5F), this.u(4.0F), hueH + this.u(3.0F), this.u(2.0F), Color.WHITE);
      this.K804470b25f6ee21c90(svX, hueY - this.u(3.0F), svW, hueH + this.u(6.0F), (mx, my, bx) -> {
         this.dragColor = setting;
         this.dragColorPart = 2;
         this.dragColorRect = new float[]{svX, hueY, svW, hueH};
         this.K77e207c9f2a5eded20(mx, my);
      });
   }

   private void K77e207c9f2a5eded20(float mx, float my) {
      if (this.dragColor != null && this.dragColorRect != null) {
         float[] r = this.dragColorRect;
         if (this.dragColorPart == 1) {
            this.colorHsb[1] = K981e054bcc0843bcd9((mx - r[0]) / r[2], 0.0F, 1.0F);
            this.colorHsb[2] = 1.0F - K981e054bcc0843bcd9((my - r[1]) / r[3], 0.0F, 1.0F);
         } else {
            this.colorHsb[0] = K981e054bcc0843bcd9((mx - r[0]) / r[2], 0.0F, 0.999F);
         }

         Color old = this.dragColor.getValue() == null ? Color.WHITE : (Color)this.dragColor.getValue();
         int rgb = Color.HSBtoRGB(this.colorHsb[0], this.colorHsb[1], this.colorHsb[2]);
         Color next = new Color(rgb >> 16 & 255, rgb >> 8 & 255, rgb & 255, old.getAlpha());
         if (!next.equals(old)) {
            this.dragColor.setValue(next);
         }

      }
   }

   private void K2fa02002e992cf5057(K224f9523984e1fa68b c) {
      String text = this.tooltip.replace('\n', ' ');
      float fs = this.u(9.9F);
      text = K78f466497bf3f7de26(c, Kc517862407c2394736.MEDIUM, text, fs, this.u(340.0F));
      if (!this.descriptionTyping.isFor(this.tooltipOwner, text)) {
         this.env.stopDescriptionSound();
      }

      if (this.descriptionTyping.update(this.tooltipOwner, text, this.dt)) {
         this.env.playDescriptionKey();
      }

      float opacity = this.descriptionTyping.opacity();
      float tw = c.textWidth(Kc517862407c2394736.MEDIUM, text, fs);
      float w = tw + this.u(34.0F);
      float h = this.u(20.0F);
      float x = this.tooltipX - this.u(15.0F);
      float y = this.tooltipY - this.u(9.0F) - h;
      float minX = this.ox + this.u(4.0F);
      float maxX = this.ox + this.u(718.0F) - this.u(4.0F) - w;
      x = K981e054bcc0843bcd9(x, minX, Math.max(minX, maxX));
      if (y < this.oy + this.u(2.0F)) {
         y = this.tooltipY + this.u(17.0F);
      }

      y += this.u(2.0F) * (1.0F - opacity);
      c.rect(x, y, w, h, this.u(6.0F), K44a4682200f8fd6f20(0.1F * opacity));
      c.glass(x + this.u(0.6F), y + this.u(0.6F), w - this.u(1.2F), h - this.u(1.2F), this.u(5.4F), Kcb3d2fcc2c28199e17(GLASS_TINT, opacity), Kcb3d2fcc2c28199e17(GLASS_SOLID, opacity));
      c.rect(x + this.u(0.6F), y + this.u(0.6F), w - this.u(1.2F), h - this.u(1.2F), this.u(5.4F), K44a4682200f8fd6f20(0.16F * opacity));
      float cy = y + h / 2.0F;
      float d = this.u(8.0F);
      float icx = x + this.u(13.0F);
      c.icon(K756e30c0738d06c926.INFO, icx, cy, this.u(9.6F), K44a4682200f8fd6f20(0.4F * opacity));
      String shown = this.descriptionTyping.visibleText();
      c.text(Kc517862407c2394736.MEDIUM, shown, x + this.u(23.0F), cy, fs, K44a4682200f8fd6f20(0.35F * opacity));
      if (this.descriptionTyping.caretVisible()) {
         float caretX = x + this.u(24.0F) + c.textWidth(Kc517862407c2394736.MEDIUM, shown, fs);
         c.rect(caretX, cy - this.u(4.0F), this.u(0.75F), this.u(8.0F), this.u(0.35F), K44a4682200f8fd6f20(0.5F * opacity));
      }

   }

   public boolean mouseClicked(float mx, float my, int button) {
      this.mouseX = mx;
      this.mouseY = my;
      if (this.bindingModule != null && button >= 2) {
         this.bindingModule.setBind(-100 + button);
         this.bindingModule = null;
         this.env.playSelect();
         return true;
      } else if (this.bindingSetting != null && button >= 2) {
         this.bindingSetting.setValue(-100 + button);
         this.bindingSetting = null;
         this.env.playSelect();
         return true;
      } else if (this.popupSetting != null) {
         for(int i = this.hits.size() - 1; i >= 0; --i) {
            K04ecc14f13fb56c724 hit = (K04ecc14f13fb56c724)this.hits.get(i);
            if (hit.popup() && hit.contains(mx, my)) {
               hit.action().run(mx, my, button);
               this.env.playSelect();
               return true;
            }
         }

         this.Kb85d0643865a962467();
         return true;
      } else {
         for(int i = this.hits.size() - 1; i >= 0; --i) {
            K04ecc14f13fb56c724 hit = (K04ecc14f13fb56c724)this.hits.get(i);
            if (!hit.popup() && hit.contains(mx, my)) {
               this.K2d2c8866ad87d589cf(hit.tag());
               hit.action().run(mx, my, button);
               this.env.playSelect();
               return true;
            }
         }

         this.searchFocused = false;
         this.commitEdit();
         this.bindingModule = null;
         this.bindingSetting = null;
         return this.Kf79b4ce608132f9404(mx, my);
      }
   }

   private void K2d2c8866ad87d589cf(Object keep) {
      if (this.searchFocused && keep != SEARCH_TAG) {
         this.searchFocused = false;
      }

      if (this.editing != null && keep != this.editing) {
         this.commitEdit();
      }

      if (this.bindingModule != null && keep != this.bindingModule) {
         this.bindingModule = null;
      }

      if (this.bindingSetting != null && keep != this.bindingSetting) {
         this.bindingSetting = null;
      }

   }

   public void mouseReleased(float mx, float my, int button) {
      this.cosPreviewDragging = false;
      this.dragSlider = null;
      this.dragColor = null;
   }

   public boolean mouseScrolled(float mx, float my, double amount) {
      this.mouseX = mx;
      this.mouseY = my;
      if (this.category == Kf76f042931183af5ba.COSMETICS && this.search.isEmpty() && this.cosPreviewRect != null && this.K13bb0cf34813da6244(this.cosPreviewRect[0], this.cosPreviewRect[1], this.cosPreviewRect[2], this.cosPreviewRect[3])) {
         this.cosPreviewZoom = K981e054bcc0843bcd9(this.cosPreviewZoom + (float)amount * 0.08F, 0.65F, 1.4F);
         return true;
      } else if (amount == (double)0.0F || this.bindingModule == null && this.bindingSetting == null) {
         if (this.popupSetting != null && this.popupRect != null) {
            if (this.K13bb0cf34813da6244(this.popupRect[0], this.popupRect[1], this.popupRect[2], this.popupRect[3])) {
               this.popupScroll -= (float)amount * this.u(16.0F);
               return true;
            }

            this.Kb85d0643865a962467();
         }

         if (this.paletteRect != null && this.K13bb0cf34813da6244(this.paletteRect[0], this.paletteRect[1], this.paletteRect[2], this.paletteRect[3])) {
            K7cc58e1b7729233c63 var8 = this.paletteScroll;
            var8.target -= (float)amount * 25.0F;
            return true;
         } else {
            float mainX = this.ox + this.u(196.0F);
            if (mx >= mainX && mx <= mainX + this.u(522.0F) && my >= this.oy && my <= this.oy + this.u(460.0F)) {
               K7cc58e1b7729233c63 var7 = this.scroll;
               var7.target -= (float)amount * this.u(42.0F);
               return true;
            } else if (mx >= this.ox && mx <= this.ox + this.u(188.0F) && my >= this.oy + this.u(62.0F) && my <= this.oy + this.u(338.0F)) {
               K7cc58e1b7729233c63 var10000 = this.sideScroll;
               var10000.target -= (float)amount * this.u(30.0F);
               return true;
            } else {
               return false;
            }
         }
      } else {
         int key = amount > (double)0.0F ? -200 : -201;
         if (this.bindingModule != null) {
            this.bindingModule.setBind(key);
            this.bindingModule = null;
         } else {
            this.bindingSetting.setValue(key);
            this.bindingSetting = null;
         }

         return true;
      }
   }

   public boolean keyPressed(int key, int modifiers) {
      if (this.bindingModule != null) {
         boolean clear = key == 256 || key == 261 || key == 259;
         this.bindingModule.setBind(clear ? -999 : key);
         this.bindingModule = null;
         return true;
      } else if (this.bindingSetting == null) {
         if (this.editing != null) {
            return this.K7a11b95f9abd0c61e0(this.editBuffer, key, this.editing.getMaxLength(), () -> this.commitEdit(), () -> this.K88a81dfe8689079071());
         } else if (this.searchFocused) {
            return this.K7a11b95f9abd0c61e0(this.search, key, 32, () -> this.searchFocused = false, () -> {
               if (this.search.isEmpty()) {
                  this.searchFocused = false;
               } else {
                  this.search.setLength(0);
               }

            });
         } else if (this.popupSetting != null && key == 256) {
            this.Kb85d0643865a962467();
            return true;
         } else if (key == 70 && this.env.controlDown()) {
            this.Kc82f1e74662325e289(true);
            return true;
         } else {
            return false;
         }
      } else {
         boolean clear = key == 256 || key == 261 || key == 259;
         this.bindingSetting.setValue(clear ? -999 : key);
         this.bindingSetting = null;
         return true;
      }
   }

   public boolean charTyped(char chr) {
      if (Character.isISOControl(chr)) {
         return false;
      } else if (this.editing != null) {
         if (this.editBuffer.length() < this.editing.getMaxLength()) {
            this.editBuffer.append(chr);
         }

         return true;
      } else if (this.searchFocused) {
         if (this.search.length() < 32) {
            this.search.append(chr);
            this.scroll.target = 0.0F;
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean K7a11b95f9abd0c61e0(StringBuilder buffer, int key, int maxLength, Runnable onEnter, Runnable onEscape) {
      switch (key) {
         case 86:
            if (this.env.controlDown()) {
               String clip = this.env.clipboard();
               if (clip != null) {
                  for(char ch : clip.toCharArray()) {
                     if (!Character.isISOControl(ch) && buffer.length() < maxLength) {
                        buffer.append(ch);
                     }
                  }
               }
            }

            return true;
         case 256:
            onEscape.run();
            return true;
         case 257:
         case 335:
            onEnter.run();
            return true;
         case 259:
            if (!buffer.isEmpty()) {
               if (this.env.controlDown()) {
                  buffer.setLength(0);
               } else {
                  buffer.setLength(buffer.length() - 1);
               }
            }

            if (buffer == this.search) {
               this.scroll.target = 0.0F;
            }

            return true;
         default:
            return true;
      }
   }

   private void Kc82f1e74662325e289(boolean focus) {
      this.commitEdit();
      this.bindingModule = null;
      this.bindingSetting = null;
      this.searchFocused = focus;
   }

   private void Kd89f5ac73efc041c8b(K917aeb5cdb0f7aca26 setting) {
      this.commitEdit();
      this.searchFocused = false;
      this.bindingModule = null;
      this.bindingSetting = null;
      this.editing = setting;
      this.editBuffer.setLength(0);
      if (setting.getValue() != null) {
         this.editBuffer.append((String)setting.getValue());
      }

   }

   public void commitEdit() {
      if (this.editing != null) {
         K917aeb5cdb0f7aca26 setting = this.editing;
         this.editing = null;
         String value = this.editBuffer.toString();
         if (!value.equals(setting.getValue())) {
            setting.setValue(value);
         }
      }

   }

   private void K88a81dfe8689079071() {
      this.editing = null;
   }

   public void onClose() {
      this.cosPreviewDragging = false;
      this.cosPreviewRect = null;
      this.hoveredModule = null;
      this.descriptionTyping.reset();
      this.env.stopDescriptionSound();
      this.commitEdit();
      this.Kb85d0643865a962467();
      this.searchFocused = false;
      this.bindingModule = null;
      this.bindingSetting = null;
      this.dragSlider = null;
      this.dragColor = null;
   }

   private boolean Kf79b4ce608132f9404(float mx, float my) {
      return mx >= this.ox && mx <= this.ox + this.u(718.0F) && my >= this.oy && my <= this.oy + this.u(460.0F);
   }

   private void Kf517e988cdcbdade48(K224f9523984e1fa68b c, float x, float y, float w, Color color) {
      c.rect(x, y, w, Math.max(0.5F, this.u(0.6F)), 0.0F, color);
   }

   private void K14e7e25cdd32117321(K224f9523984e1fa68b c, float x, float y, float w, float h, float r) {
      c.glass(x, y, w, h, r, new Color(84, 74, 98, 255), new Color(124, 113, 136, 255));
      c.glass(x + this.u(0.7F), y + this.u(0.7F), w - this.u(1.4F), h - this.u(1.4F), r - this.u(0.7F), GLASS_TINT, GLASS_SOLID);
   }

   private K7cc58e1b7729233c63 K4b6827bc4efb1d8cf2(Ka6e5a7cd25e001c633 g) {
      K7cc58e1b7729233c63 a = (K7cc58e1b7729233c63)this.groupAnims.computeIfAbsent(g.title(), (k) -> new K7cc58e1b7729233c63(1.0F));
      a.target = this.collapsedGroups.contains(g.title()) ? 0.0F : 1.0F;
      return a;
   }

   private float K795ad067d0706e70ad(float centerY, float top, float bottom, float fade) {
      float a = 1.0F;
      if (centerY > bottom - fade) {
         a = Math.min(a, (bottom - centerY) / fade);
      }

      return K981e054bcc0843bcd9(a, 0.0F, 1.0F);
   }

   private void K474ac610b443bade50(K224f9523984e1fa68b c, float x, float y, float w, float h) {
      this.clipStack.add((float[])this.clip.clone());
      float x0 = Math.max(this.clip[0], x);
      float y0 = Math.max(this.clip[1], y);
      float x1 = Math.min(this.clip[2], x + w);
      float y1 = Math.min(this.clip[3], y + h);
      this.clip[0] = x0;
      this.clip[1] = y0;
      this.clip[2] = Math.max(x0, x1);
      this.clip[3] = Math.max(y0, y1);
      c.pushClip(this.clip[0], this.clip[1], this.clip[2] - this.clip[0], this.clip[3] - this.clip[1]);
   }

   private void Kf012206dd4fc483166(K224f9523984e1fa68b c) {
      c.popClip();
      float[] prev = (float[])this.clipStack.remove(this.clipStack.size() - 1);
      System.arraycopy(prev, 0, this.clip, 0, 4);
   }

   private void hit(float x, float y, float w, float h, K4ba12f9dfc8560f577 action) {
      this.hit(x, y, w, h, false, (Object)null, action);
   }

   private void hit(float x, float y, float w, float h, Object tag, K4ba12f9dfc8560f577 action) {
      this.hit(x, y, w, h, false, tag, action);
   }

   private void K804470b25f6ee21c90(float x, float y, float w, float h, K4ba12f9dfc8560f577 action) {
      this.hit(x, y, w, h, true, (Object)null, action);
   }

   private void hit(float x, float y, float w, float h, boolean popup, Object tag, K4ba12f9dfc8560f577 action) {
      float x0 = Math.max(x, this.clip[0]);
      float y0 = Math.max(y, this.clip[1]);
      float x1 = Math.min(x + w, this.clip[2]);
      float y1 = Math.min(y + h, this.clip[3]);
      if (!(x1 <= x0) && !(y1 <= y0)) {
         this.hits.add(new K04ecc14f13fb56c724(x0, y0, x1 - x0, y1 - y0, popup, tag, action));
      }
   }

   private void K9fdc1a0882e55009b2() {
      Kab8a8c478f7d0077cd next = null;

      for(int i = this.hoverCards.size() - 1; i >= 0; --i) {
         K451be487a8a30ea1c8 card = (K451be487a8a30ea1c8)this.hoverCards.get(i);
         if (card.contains(this.mouseX, this.mouseY)) {
            next = card.module();
            break;
         }
      }

      if (next != null && next != this.hoveredModule && this.popupSetting == null && !this.isDragging() && this.bindingModule == null && this.bindingSetting == null) {
         this.env.playHover();
      }

      this.hoveredModule = next;
   }

   private boolean K13bb0cf34813da6244(float x, float y, float w, float h) {
      return this.mouseX >= x && this.mouseX <= x + w && this.mouseY >= y && this.mouseY <= y + h;
   }

   private boolean K0f2dbddffe52521042(float x, float y, float w, float h) {
      if (!this.K13bb0cf34813da6244(x, y, w, h)) {
         return false;
      } else if (!(this.mouseX < this.clip[0]) && !(this.mouseX > this.clip[2]) && !(this.mouseY < this.clip[1]) && !(this.mouseY > this.clip[3])) {
         return this.popupSetting == null || this.popupRect == null || !this.K13bb0cf34813da6244(this.popupRect[0], this.popupRect[1], this.popupRect[2], this.popupRect[3]);
      } else {
         return false;
      }
   }

   private void K35a185faddddba4ec4(Kb9e875ead00e0d03bd slider, float mx) {
      float pct = K981e054bcc0843bcd9((mx - this.dragSliderX) / Math.max(1.0F, this.dragSliderW), 0.0F, 1.0F);
      float value = slider.getMin() + (slider.getMax() - slider.getMin()) * pct;
      Float current = slider.getValue();
      if (current == null || Math.abs(current - value) > 1.0E-6F) {
         slider.setValue(value);
      }

   }

   static String formatSlider(Kb9e875ead00e0d03bd slider) {
      Float v = slider.getValue();
      if (v == null) {
         return "0";
      } else {
         float step = slider.getStep();
         int decimals = 0;
         if (step > 0.0F && step < 1.0F) {
            decimals = Math.max(0, BigDecimal.valueOf((double)step).stripTrailingZeros().scale());
            decimals = Math.min(decimals, 3);
         }

         String text = String.format(Locale.ROOT, "%." + decimals + "f", v);
         if (text.contains(".")) {
            text = text.replaceAll("0+$", "");
            if (text.endsWith(".")) {
               text = text.substring(0, text.length() - 1);
            }
         }

         if (text.equals("-0")) {
            text = "0";
         }

         return text;
      }
   }

   private static String Kc0a9a2a9f7a9a02b5a(String name) {
      if (name != null && !name.isEmpty()) {
         String n = name.replace("_", " ").trim();
         String var10000;
         switch (n.toUpperCase(Locale.ROOT)) {
            case "RIGHT SHIFT" -> var10000 = "RShift";
            case "LEFT SHIFT" -> var10000 = "LShift";
            case "RIGHT CONTROL" -> var10000 = "RCtrl";
            case "LEFT CONTROL" -> var10000 = "LCtrl";
            case "RIGHT ALT" -> var10000 = "RAlt";
            case "LEFT ALT" -> var10000 = "LAlt";
            default -> var10000 = n.length() <= 1 ? n.toUpperCase(Locale.ROOT) : n;
         }

         return var10000;
      } else {
         return "?";
      }
   }

   private boolean Kefbfdb1c9d637d503c() {
      return System.currentTimeMillis() / 500L % 2L == 0L;
   }

   private static String K78f466497bf3f7de26(K224f9523984e1fa68b c, Kc517862407c2394736 font, String text, float size, float maxW) {
      if (text == null) {
         return "";
      } else if (maxW <= 0.0F) {
         return "";
      } else if (c.textWidth(font, text, size) <= maxW) {
         return text;
      } else {
         String dots = "…";

         int end;
         for(end = text.length(); end > 0 && c.textWidth(font, text.substring(0, end) + dots, size) > maxW; --end) {
         }

         String var10000 = text.substring(0, end).stripTrailing();
         return var10000 + dots;
      }
   }

   private static String Kfbbd430e1780d48c01(K224f9523984e1fa68b c, Kc517862407c2394736 font, String text, float size, float maxW) {
      if (text == null) {
         return "";
      } else if (c.textWidth(font, text, size) <= maxW) {
         return text;
      } else {
         String dots = " ...";

         int end;
         for(end = text.length(); end > 0; --end) {
            String var10002 = text.substring(0, end).stripTrailing();
            if (!(c.textWidth(font, var10002 + dots, size) > maxW)) {
               break;
            }
         }

         String head = text.substring(0, end).stripTrailing();
         if (head.endsWith(",")) {
            return head + dots;
         } else {
            int comma = head.lastIndexOf(44);
            if (comma > 0) {
               String var10000 = head.substring(0, comma + 1);
               return var10000 + dots;
            } else {
               return head + "...";
            }
         }
      }
   }

   private static String K60c2f64d4ae8b3a197(K224f9523984e1fa68b c, Kc517862407c2394736 font, String text, float size, float maxW) {
      int start;
      for(start = 0; start < text.length() && c.textWidth(font, text.substring(start), size) > maxW; ++start) {
      }

      return text.substring(start);
   }

   private static String K34f641a76dfc7d83e2(String text) {
      String rus = "йцукенгшщзхъфывапролджэячсмитьбюё";
      String eng = "qwertyuiop[]asdfghjkl;'zxcvbnm,.`";
      StringBuilder out = new StringBuilder(text.length());

      for(char ch : text.toCharArray()) {
         int i = rus.indexOf(ch);
         out.append(i >= 0 ? eng.charAt(i) : ch);
      }

      return out.toString();
   }

   private static float K981e054bcc0843bcd9(float v, float min, float max) {
      return v < min ? min : (v > max ? max : v);
   }

   private static Color K40027418113552ac83(Color a, Color b, float t) {
      t = K981e054bcc0843bcd9(t, 0.0F, 1.0F);
      return new Color(Math.round((float)a.getRed() + (float)(b.getRed() - a.getRed()) * t), Math.round((float)a.getGreen() + (float)(b.getGreen() - a.getGreen()) * t), Math.round((float)a.getBlue() + (float)(b.getBlue() - a.getBlue()) * t), Math.round((float)a.getAlpha() + (float)(b.getAlpha() - a.getAlpha()) * t));
   }

   public void settleAnimations() {
      for(K2744bf03883d58054c st : this.cards.values()) {
         st.height.snap();
         st.toggle.snap();
         st.expand.snap();
      }

      for(K7cc58e1b7729233c63 a : this.groupAnims.values()) {
         a.snap();
      }

      for(K7cc58e1b7729233c63 a : this.itemAnims.values()) {
         a.snap();
      }

      this.scroll.snap();
      this.sideScroll.snap();
   }

   public void setExpanded(Kab8a8c478f7d0077cd module, boolean expanded) {
      this.K7a67e9370fc7889d7f(module).expanded = expanded;
   }

   public void setCategory(Kf76f042931183af5ba category) {
      this.category = category;
   }

   public void setColumns(List<Kab8a8c478f7d0077cd> left, List<Kab8a8c478f7d0077cd> right, List<Kab8a8c478f7d0077cd> all) {
      this.leftColumn.clear();
      this.leftColumn.addAll(left);
      this.rightColumn.clear();
      this.rightColumn.addAll(right);
      StringBuilder key = new StringBuilder(this.search.isEmpty() ? this.category.name() : "?" + String.valueOf(this.search));
      List<Kab8a8c478f7d0077cd> sorted = new ArrayList(all);
      sorted.sort(Comparator.comparing((mx) -> mx.getDisplayName().toLowerCase(Locale.ROOT)));

      for(Kab8a8c478f7d0077cd m : sorted) {
         key.append('|').append(System.identityHashCode(m));
      }

      this.layoutKey = key.toString();
   }

   public void setOrigin(float x, float y, float scale) {
      this.ox = x;
      this.oy = y;
      this.s = scale;
   }

   static {
      GROUPS = List.of(new Ka6e5a7cd25e001c633("VISUALS", List.of(Kf76f042931183af5ba.COMBAT, Kf76f042931183af5ba.MOVEMENT, Kf76f042931183af5ba.PLAYER, Kf76f042931183af5ba.RENDER, Kf76f042931183af5ba.OTHER)), new Ka6e5a7cd25e001c633("COSMETICS", List.of(Kf76f042931183af5ba.COSMETICS)));
      SEARCH_TAG = new Object();
   }
}
