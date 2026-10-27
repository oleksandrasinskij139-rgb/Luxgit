package code.miaware.api.utils.render.fonts;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import com.google.gson.Gson;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public class K57b57df54f7cd42184 {
   private String name;
   private class_2960 dataIdentifier;
   private class_2960 atlasIdentifier;

   public K57b57df54f7cd42184 find(String fontName) {
      this.name = fontName;
      this.dataIdentifier = class_2960.method_60655("luxvisuals".toLowerCase(), "fonts/" + fontName + ".json");
      this.atlasIdentifier = class_2960.method_60655("luxvisuals".toLowerCase(), "fonts/" + fontName + ".png");
      if (fontName.contains("role")) {
         System.out.println("[FontBuilder] find() вызван для: " + fontName);
         System.out.println("[FontBuilder] JSON: " + String.valueOf(this.dataIdentifier));
         System.out.println("[FontBuilder] PNG: " + String.valueOf(this.atlasIdentifier));
      }

      return this;
   }

   public K5fe84dad3db1af4bb7 load() {
      String jsonPath = "fonts/" + this.name + ".json";
      InputStream inputStream = Kc1f6f8c785f6a08448.getFromAssets(jsonPath);
      if (inputStream == null) {
         throw new RuntimeException("Failed to load font data file: " + jsonPath + " (FileUtil.getFromAssets returned null)");
      } else {
         String jsonContent;
         try {
            InputStream var4 = inputStream;

            try {
               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

               try {
                  jsonContent = (String)reader.lines().collect(Collectors.joining("\n"));
               } catch (Throwable var12) {
                  try {
                     reader.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }

                  throw var12;
               }

               reader.close();
            } catch (Throwable var13) {
               if (inputStream != null) {
                  try {
                     var4.close();
                  } catch (Throwable var10) {
                     var13.addSuppressed(var10);
                  }
               }

               throw var13;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } catch (IOException ex) {
            throw new RuntimeException("Failed to read font data file: " + jsonPath, ex);
         }

         System.out.println("[FontBuilder] Loading font: " + this.name + " from " + jsonPath);
         Kc82d15e4daf05d1f41 data = (Kc82d15e4daf05d1f41)(new Gson()).fromJson(jsonContent, Kc82d15e4daf05d1f41.class);
         class_1044 texture = class_310.method_1551().method_1531().method_4619(this.atlasIdentifier);
         if (data == null) {
            throw new RuntimeException("Failed to parse font data file: " + jsonPath + "; Are you sure this is json file? Try to check the correctness of its syntax.");
         } else if (data.atlas() == null) {
            System.out.println("[FontBuilder DEBUG] Atlas is NULL for font: " + this.name);
            System.out.println("[FontBuilder DEBUG] JSON path: " + jsonPath);
            System.out.println("[FontBuilder DEBUG] Data object: " + String.valueOf(data));
            System.out.println("[FontBuilder DEBUG] JSON content: " + jsonContent.substring(0, Math.min(200, jsonContent.length())));
            throw new RuntimeException("Font data loaded but atlas is null: " + jsonPath);
         } else {
            String var10001 = this.name;
            System.out.println("[FontBuilder] Successfully loaded font: " + var10001 + " (" + data.glyphs().size() + " glyphs)");
            RenderSystem.recordRenderCall(() -> texture.method_4527(true, false));
            float aWidth = data.atlas().width();
            float aHeight = data.atlas().height();
            Map<Integer, Kd81f0583cabc01b39d> glyphs = (Map)data.glyphs().stream().collect(Collectors.toMap(Kd3aa729b1195fc158b::unicode, (glyphData) -> new Kd81f0583cabc01b39d(glyphData, aWidth, aHeight)));
            Map<Integer, Map<Integer, Float>> kernings = new HashMap();
            data.kernings().forEach((kerning) -> {
               Map<Integer, Float> map = (Map)kernings.get(kerning.leftChar());
               if (map == null) {
                  map = new HashMap();
                  kernings.put(kerning.leftChar(), map);
               }

               map.put(kerning.rightChar(), kerning.advance());
            });
            return new K5fe84dad3db1af4bb7(this.name, texture, data.atlas(), data.metrics(), glyphs, kernings);
         }
      }
   }
}
