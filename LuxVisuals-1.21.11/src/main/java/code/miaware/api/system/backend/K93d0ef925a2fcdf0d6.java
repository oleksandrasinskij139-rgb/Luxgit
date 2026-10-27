package code.miaware.api.system.backend;

import java.nio.file.Path;
import lombok.Generated;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;

public final class K93d0ef925a2fcdf0d6 {
   public static final String NAME = "lux visuals";
   public static final String FOLDER_NAME = "luxvisuals";
   public static final String VERSION = "0.0.3";
   private static final Path GAME_DIR = FabricLoader.getInstance().getGameDir();
   private static final Path CLIENT_DIR = FabricLoader.getInstance().getConfigDir().resolve("luxvisuals");
   public static final String GAME_PATH;
   public static final String CONFIG_PATH_AI_MODELS;
   public static final String CONFIG_PATH_OTHER;
   public static final String CONFIG_PATH_THEMES;
   public static final String CONFIG_PATH_MAIN;

   public static String getUsername() {
      class_310 mc = class_310.method_1551();
      return mc.method_1548() != null ? mc.method_1548().method_1676() : "Player";
   }

   @Generated
   private K93d0ef925a2fcdf0d6() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      GAME_PATH = GAME_DIR.toAbsolutePath().toString();
      CONFIG_PATH_AI_MODELS = CLIENT_DIR.resolve("ai_models").toAbsolutePath().toString();
      CONFIG_PATH_OTHER = CLIENT_DIR.resolve("other").toAbsolutePath().toString();
      CONFIG_PATH_THEMES = CLIENT_DIR.resolve("themes").toAbsolutePath().toString();
      CONFIG_PATH_MAIN = CLIENT_DIR.resolve("main").toAbsolutePath().toString();
   }
}
