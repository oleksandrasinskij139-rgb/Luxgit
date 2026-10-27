package code.miaware.client.features.modules.render.killeffect;

public enum K1037f35c571a07da2f {
   SOUL_CATCHER("Душелов"),
   ABYSS_MAW("Пасть бездны"),
   SOUL_RIFT("Разрыв души"),
   SOLAR_BURN("Солнечное испепеление");

   public final String label;

   private K1037f35c571a07da2f(String label) {
      this.label = label;
   }

   public static K1037f35c571a07da2f fromName(String name) {
      for(K1037f35c571a07da2f style : values()) {
         if (style.label.equals(name)) {
            return style;
         }
      }

      return SOUL_CATCHER;
   }

   // $FF: synthetic method
   private static K1037f35c571a07da2f[] $values() {
      return new K1037f35c571a07da2f[]{SOUL_CATCHER, ABYSS_MAW, SOUL_RIFT, SOLAR_BURN};
   }
}
