package code.miaware.api.system.configs;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.client.ui.theme.K047a33db8928fe0997;
import code.miaware.client.ui.theme.K2c59a7f522ec4ea35b;
import code.miaware.client.ui.theme.K51ad80c57a5258fb43;
import code.miaware.client.ui.theme.K56aaf6fb28094e22de;
import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import code.miaware.client.ui.theme.K89eed12a5c142a77f4;
import code.miaware.client.ui.theme.Theme;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Generated;

public class Ke74a88931f3b59a118 {
   private static final Ke74a88931f3b59a118 instance = new Ke74a88931f3b59a118();
   private final String path;
   private final File dir;
   private final File lastFile;
   private static final Charset UTF8;

   public Ke74a88931f3b59a118(String path) {
      this.path = path;
      this.dir = new File(path);
      this.lastFile = new File(this.dir, "last_selected");
      this.K43b61815a7ce807c32();
   }

   public Ke74a88931f3b59a118() {
      this(K93d0ef925a2fcdf0d6.CONFIG_PATH_THEMES);
   }

   public void save(Theme theme) {
      if (theme != null) {
         this.K43b61815a7ce807c32();
         Path out = this.Kca4cd584b916501c08(this.safeFileName(theme.getName()));
         List<String> lines = new ArrayList();
         lines.add("name=" + theme.getName());

         for(K89eed12a5c142a77f4 ec : theme.getElementColors()) {
            String var10001 = ec.getName();
            lines.add(var10001 + "=" + ec.getColor().getRGB());
         }

         try {
            Files.write(out, lines, UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }
   }

   public void saveAll() {
      for(K56aaf6fb28094e22de ts : K6ea7546faa48739c0d.getInstance().getThemeSelectables()) {
         if (ts != null && ts.getTheme() != null) {
            this.save(ts.getTheme());
         }
      }

   }

   public void saveLastSelected(Theme theme) {
      if (theme != null) {
         this.K43b61815a7ce807c32();

         try {
            Files.writeString(this.lastFile.toPath(), this.safeFileName(theme.getName()), UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }
   }

   public boolean remove(String themeName) {
      if (themeName != null && !themeName.isEmpty()) {
         this.K43b61815a7ce807c32();
         File[] files = this.dir.listFiles((d, n) -> n.toLowerCase().endsWith(".theme"));
         if (files == null) {
            return false;
         } else {
            for(File file : files) {
               try {
                  Stream<String> lines = Files.lines(file.toPath(), UTF8);

                  boolean var9;
                  label55: {
                     try {
                        String first = (String)lines.findFirst().orElse("");
                        if (first.startsWith("name=") && themeName.equals(first.substring(5).trim())) {
                           var9 = file.delete();
                           break label55;
                        }
                     } catch (Throwable var11) {
                        if (lines != null) {
                           try {
                              lines.close();
                           } catch (Throwable var10) {
                              var11.addSuppressed(var10);
                           }
                        }

                        throw var11;
                     }

                     if (lines != null) {
                        lines.close();
                     }
                     continue;
                  }

                  if (lines != null) {
                     lines.close();
                  }

                  return var9;
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public Theme load(String name) {
      if (name != null && !name.isEmpty()) {
         File f = this.Kca4cd584b916501c08(this.safeFileName(name)).toFile();
         return f.exists() ? this.load(f) : null;
      } else {
         return null;
      }
   }

   public Theme load(File file) {
      if (file != null && file.exists()) {
         Map<String, String> map = this.Kb6fe9be0d7235fb2fb(file.toPath());
         String name = (String)map.getOrDefault("name", this.K7e584ba5f13dac1988(file.getName()));
         Theme theme = new Theme(name);

         for(K89eed12a5c142a77f4 ec : theme.getElementColors()) {
            String key = ec.getName();
            if (map.containsKey(key)) {
               try {
                  int rgb = Integer.parseInt((String)map.get(key));
                  ec.setColor(new Color(rgb, true));
               } catch (NumberFormatException var9) {
               }
            }
         }

         this.Kd0f4e41d28b43fb929(theme);
         return theme;
      } else {
         return null;
      }
   }

   public void refresh() {
      List<K56aaf6fb28094e22de> selectables = K6ea7546faa48739c0d.getInstance().getThemeSelectables();
      selectables.clear();
      this.K96830bd33aa8d32c96();
      this.K971ccfa7b716c32b47();

      for(K56aaf6fb28094e22de ts : selectables) {
         if (ts != null && ts.getTheme() != null) {
            this.save(ts.getTheme());
         }
      }

   }

   public Theme loadLastSelected() {
      if (!this.lastFile.exists()) {
         return null;
      } else {
         try {
            String safeName = this.safeFileName(Files.readString(this.lastFile.toPath(), UTF8).trim());
            if (K2c59a7f522ec4ea35b.PRESETS.stream().noneMatch((p) -> p.nameEn().equals(safeName))) {
               return null;
            } else {
               File f = this.Kca4cd584b916501c08(safeName).toFile();
               return f.exists() ? this.load(f) : null;
            }
         } catch (IOException e) {
            e.printStackTrace();
            return null;
         }
      }
   }

   private void K43b61815a7ce807c32() {
      if (!this.dir.exists()) {
         this.dir.mkdirs();
      }

   }

   private void K971ccfa7b716c32b47() {
      for(int i = 0; i < K2c59a7f522ec4ea35b.PRESETS.size(); ++i) {
         K6ea7546faa48739c0d.getInstance().getThemeSelectables().add(new K56aaf6fb28094e22de(K047a33db8928fe0997.create(i)));
      }

   }

   private void K96830bd33aa8d32c96() {
      this.K43b61815a7ce807c32();
      Set<String> allowed = new HashSet();

      for(K51ad80c57a5258fb43 preset : K2c59a7f522ec4ea35b.PRESETS) {
         String var10001 = this.safeFileName(preset.nameEn());
         allowed.add(var10001 + ".theme");
      }

      Path base = this.dir.toPath().toAbsolutePath().normalize();

      try {
         Stream<Path> entries = Files.list(base);

         try {
            for(Path entry : entries.toList()) {
               Path candidate = entry.toAbsolutePath().normalize();
               String name = candidate.getFileName().toString();
               if (candidate.getParent().equals(base) && name.endsWith(".theme") && !allowed.contains(name)) {
                  Files.deleteIfExists(candidate);
               }
            }
         } catch (Throwable var9) {
            if (entries != null) {
               try {
                  entries.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (entries != null) {
            entries.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   private Map<String, String> Kb6fe9be0d7235fb2fb(Path p) {
      Map<String, String> map = new HashMap();

      try {
         for(String raw : Files.readAllLines(p, UTF8)) {
            String line = raw.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
               int idx = line.indexOf(61);
               if (idx > 0) {
                  String key = line.substring(0, idx);
                  String value = line.substring(idx + 1);
                  map.put(key, value);
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return map;
   }

   public String safeFileName(String name) {
      if (name != null && !name.isBlank()) {
         String sanitized = name.replace("..", "_").replaceAll("[\\\\/:*?\"<>|]", "_").trim();
         return sanitized.isEmpty() ? "theme" : sanitized;
      } else {
         return "theme";
      }
   }

   private void Kd0f4e41d28b43fb929(Theme theme) {
   }

   private String K7e584ba5f13dac1988(String name) {
      int idx = name.lastIndexOf(46);
      return idx <= 0 ? name : name.substring(0, idx);
   }

   private Path Kca4cd584b916501c08(String name) {
      Path baseDir = this.dir.toPath().toAbsolutePath().normalize();
      Path resolved = baseDir.resolve(name + ".theme").normalize();
      if (!resolved.startsWith(baseDir)) {
         throw new IllegalArgumentException("Theme path escaped themes directory");
      } else {
         return resolved;
      }
   }

   @Generated
   public String getPath() {
      return this.path;
   }

   @Generated
   public File getDir() {
      return this.dir;
   }

   @Generated
   public File getLastFile() {
      return this.lastFile;
   }

   @Generated
   public static Ke74a88931f3b59a118 getInstance() {
      return instance;
   }

   static {
      UTF8 = StandardCharsets.UTF_8;
   }
}
