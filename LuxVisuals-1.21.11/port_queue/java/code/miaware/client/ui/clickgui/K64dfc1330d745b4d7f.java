package code.miaware.client.ui.clickgui;

import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.K5b5bf2e4f791eb67a2;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1044;
import net.minecraft.class_1068;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_490;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class K64dfc1330d745b4d7f implements K224f9523984e1fa68b {
   private static final class_2960 K_LOGO = class_2960.method_60655("luxvisuals", "images/interface/crown_logo.png");
   private static final float LOGO_ASPECT = 1.946768F;
   private static final float SF_CAP_HEIGHT = 0.711F;
   static final float SHAPE_INSET = 0.4F;
   private static final float GLASS_MIX = 0.64F;
   private final class_310 mc = class_310.method_1551();
   private final Map<K756e30c0738d06c926, class_2960> iconIds = new EnumMap(K756e30c0738d06c926.class);
   private final Map<K756e30c0738d06c926, Boolean> iconFiltered = new EnumMap(K756e30c0738d06c926.class);
   private class_4587 matrices;
   private class_332 context;
   private float alpha = 1.0F;
   private boolean blur;
   private final Map<String, class_2960> imageIds = new HashMap();
   private final Set<class_2960> filteredMenuImages = new HashSet();

   public void begin(class_4587 matrices) {
      this.context = null;
      this.matrices = matrices;
      this.blur = this.K02acd1eb43c8e40e12();
   }

   public void begin(class_332 context) {
      this.begin(context.method_51448());
      this.context = context;
   }

   public boolean cosmeticPreview(float x, float y, float w, float h, float yaw, float zoom, int itemId) {
      if (this.context != null && this.mc.field_1724 != null && this.mc.field_1687 != null && !(this.alpha < 0.1F)) {
         class_746 player = this.mc.field_1724;
         K697051202eac7cfc74 manager = K697051202eac7cfc74.getInstance();
         float body = player.field_6283;
         float prevBody = player.field_6220;
         float head = player.field_6241;
         float prevHead = player.field_6259;
         float oldYaw = player.method_36454();
         float prevYaw = player.field_5982;
         float pitch = player.method_36455();
         float prevPitch = player.field_6004;
         this.pushClip(x, y, w, h);

         try {
            player.field_6283 = player.field_6220 = 180.0F;
            player.field_6241 = player.field_6259 = 180.0F;
            player.method_36456(180.0F);
            player.field_5982 = 180.0F;
            player.method_36457(0.0F);
            player.field_6004 = 0.0F;
            manager.renderPreview(manager.entry(itemId), () -> class_490.method_48472(this.context, x + w / 2.0F, y + h / 2.0F, h * 0.36F * zoom, new Vector3f(0.0F, player.method_17682() / 2.0F, 0.0F), (new Quaternionf()).rotateZ((float)Math.PI).rotateY((float)Math.toRadians((double)yaw)), (Quaternionf)null, player));
         } finally {
            player.field_6283 = body;
            player.field_6220 = prevBody;
            player.field_6241 = head;
            player.field_6259 = prevHead;
            player.method_36456(oldYaw);
            player.field_5982 = prevYaw;
            player.method_36457(pitch);
            player.field_6004 = prevPitch;
            this.popClip();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean K02acd1eb43c8e40e12() {
      try {
         if (this.mc.field_1687 == null) {
            return false;
         } else if (!Kfae29c687712462375.getInstance().isEnabled()) {
            return false;
         } else if (K45978f900e11d6615f.getInstance().isBlurDisabled()) {
            return false;
         } else {
            return Kfae29c687712462375.getGlassy() < 0.99F;
         }
      } catch (Throwable var2) {
         return false;
      }
   }

   public void setAlpha(float alpha) {
      this.alpha = Math.max(0.0F, Math.min(1.0F, alpha));
   }

   private Color a(Color color) {
      return this.alpha >= 0.999F ? color : new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.round((float)color.getAlpha() * this.alpha));
   }

   public void rect(float x, float y, float w, float h, float radius, Color color) {
      if (!(w <= 0.0F) && !(h <= 0.0F) && color.getAlpha() != 0) {
         float r = Math.min(radius, Math.min(w, h) / 2.0F) + 0.4F;
         K2b5e2c5f2b9891b450.RECT.draw(this.matrices, x - 0.4F, y - 0.4F, w + 0.8F, h + 0.8F, r, this.a(color));
      }
   }

   public void glass(float x, float y, float w, float h, float radius, Color tint, Color solid) {
      if (!(w <= 0.0F) && !(h <= 0.0F)) {
         float r = Math.min(radius, Math.min(w, h) / 2.0F) + 0.4F;
         float ix = x - 0.4F;
         float iy = y - 0.4F;
         float iw = w + 0.8F;
         float ih = h + 0.8F;
         if (this.blur) {
            K2b5e2c5f2b9891b450.BLUR_RECT.draw(this.matrices, ix, iy, iw, ih, r, this.a(tint), 0.64F);
         } else {
            K2b5e2c5f2b9891b450.RECT.draw(this.matrices, ix, iy, iw, ih, r, this.a(solid));
         }

      }
   }

   public void gradient(float x, float y, float w, float h, float radius, Color topLeft, Color topRight, Color bottomLeft, Color bottomRight) {
      if (!(w <= 0.0F) && !(h <= 0.0F)) {
         float r = Math.min(radius, Math.min(w, h) / 2.0F) + 0.4F;
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(this.matrices, x - 0.4F, y - 0.4F, w + 0.8F, h + 0.8F, r, this.a(topLeft), this.a(topRight), this.a(bottomLeft), this.a(bottomRight));
      }
   }

   private static K5fe84dad3db1af4bb7 K126fde3f5ca993383a(Kc517862407c2394736 kind) {
      K5fe84dad3db1af4bb7 var10000;
      switch (kind) {
         case SEMIBOLD -> var10000 = K896065ea0a2bba4f47.SF_SEMIBOLD;
         case MEDIUM -> var10000 = K896065ea0a2bba4f47.SF_MEDIUM;
         case BOLD -> var10000 = K896065ea0a2bba4f47.SF_BOLD;
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }

   public void text(Kc517862407c2394736 kind, String text, float x, float centerY, float size, Color color) {
      if (text != null && !text.isEmpty() && color.getAlpha() != 0) {
         K5fe84dad3db1af4bb7 font = K126fde3f5ca993383a(kind);
         float y = centerY - font.getMetrics().baselineHeight() * size + 0.711F * size / 2.0F;
         font.drawText(this.matrices, text, x, y, size, 0.0F, this.a(color).getRGB(), -1, -1.0F, 0.5F, 0.0F, -1, 0.0F);
      }
   }

   public float textWidth(Kc517862407c2394736 kind, String text, float size) {
      return text != null && !text.isEmpty() ? K126fde3f5ca993383a(kind).getWidth(text, size) : 0.0F;
   }

   public void icon(K756e30c0738d06c926 icon, float centerX, float centerY, float size, Color color) {
      if (color.getAlpha() != 0) {
         try {
            class_2960 id = (class_2960)this.iconIds.computeIfAbsent(icon, (k) -> class_2960.method_60655("luxvisuals", "images/clickgui/icons/" + k.texture + ".png"));
            class_1044 texture = this.mc.method_1531().method_4619(id);
            if (!this.iconFiltered.containsKey(icon)) {
               texture.method_4527(true, false);
               this.iconFiltered.put(icon, Boolean.TRUE);
            }

            float s = size + 0.8F;
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, centerX - s / 2.0F, centerY - s / 2.0F, s, s, 0.0F, this.a(color), 0.0F, 0.0F, 1.0F, 1.0F, texture.method_4624());
         } catch (Throwable var9) {
         }

      }
   }

   public void image(String texture, float x, float y, float w, float h, Color tint) {
      if (!(w <= 0.0F) && !(h <= 0.0F) && tint.getAlpha() != 0) {
         try {
            class_2960 id = (class_2960)this.imageIds.computeIfAbsent(texture, class_2960::method_60654);
            class_1044 tex = this.mc.method_1531().method_4619(id);
            if (texture.startsWith("luxvisuals:mainmenu/") && this.filteredMenuImages.add(id)) {
               tex.method_4527(true, false);
            }

            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, x - 0.4F, y - 0.4F, w + 0.8F, h + 0.8F, 0.0F, this.a(tint), 0.0F, 0.0F, 1.0F, 1.0F, tex.method_4624());
         } catch (Throwable var9) {
         }

      }
   }

   public void logo(float centerX, float centerY, float height, Color color) {
      try {
         class_1044 texture = this.mc.method_1531().method_4619(K_LOGO);
         float w = height * 1.946768F;
         K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, centerX - w / 2.0F, centerY - height / 2.0F, w, height, 0.0F, this.a(color), 0.0F, 0.0F, 1.0F, 1.0F, texture.method_4624());
      } catch (Throwable var7) {
         K896065ea0a2bba4f47.SF_BOLD.drawText(this.matrices, "K", centerX - height * 0.3F, centerY - height * 0.6F, height, 0.0F, this.a(color).getRGB(), -1, -1.0F, 0.5F, 0.0F, -1, 0.0F);
      }

   }

   public void head(float x, float y, float size) {
      try {
         class_746 player = this.mc.field_1724;
         class_2960 skin;
         if (player instanceof class_742) {
            skin = ((class_742)player).method_52814().comp_1626();
         } else {
            UUID uuid = this.mc.method_1548() != null ? this.mc.method_1548().method_44717() : null;
            skin = uuid != null ? class_1068.method_4648(uuid).comp_1626() : class_1068.method_4649();
         }

         int texture = this.mc.method_1531().method_4619(skin).method_4624();
         float uv = 0.125F;
         Color white = this.a(Color.WHITE);
         K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, x, y, size, size, size / 2.0F, white, uv, uv, uv, uv, texture);
         K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, x, y, size, size, size / 2.0F, white, 0.625F, uv, uv, uv, texture);
      } catch (Throwable var8) {
         this.rect(x, y, size, size, size / 2.0F, new Color(200, 190, 210, 255));
      }

   }

   public void pushClip(float x, float y, float w, float h) {
      K5b5bf2e4f791eb67a2.start(this.matrices, x, y, Math.max(0.0F, w), Math.max(0.0F, h));
   }

   public void skinHead(String texture, float x, float y, float size) {
      class_2960 id = class_2960.method_60654(texture);
      int gl = this.mc.method_1531().method_4619(id).method_4624();
      K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, x, y, size, size, 3.0F, this.a(Color.WHITE), 0.125F, 0.125F, 0.125F, 0.125F, gl);
      K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(this.matrices, x, y, size, size, 3.0F, this.a(Color.WHITE), 0.625F, 0.125F, 0.125F, 0.125F, gl);
   }

   public void popClip() {
      K5b5bf2e4f791eb67a2.stop(this.matrices);
   }
}
