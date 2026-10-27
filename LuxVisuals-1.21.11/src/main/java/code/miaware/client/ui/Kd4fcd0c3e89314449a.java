package code.miaware.client.ui;

public enum Kd4fcd0c3e89314449a {
   STYLE_1("1"),
   STYLE_2("2");

   private final String name;

   private Kd4fcd0c3e89314449a(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public boolean isStrict() {
      return this == STYLE_2;
   }

   public float getRounding() {
      return this.isStrict() ? 0.0F : 1.0F;
   }

   // $FF: synthetic method
   private static Kd4fcd0c3e89314449a[] $values() {
      return new Kd4fcd0c3e89314449a[]{STYLE_1, STYLE_2};
   }
}
