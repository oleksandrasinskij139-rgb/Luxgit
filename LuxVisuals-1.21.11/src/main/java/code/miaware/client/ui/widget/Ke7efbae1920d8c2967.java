package code.miaware.client.ui.widget;

public record Ke7efbae1920d8c2967(String label, String description, K8c553198855f5f0c73 state, K3d6990b4df3a62d6f4 setter) {
   public static Ke7efbae1920d8c2967 of(String label, String description, K8c553198855f5f0c73 state, K3d6990b4df3a62d6f4 setter) {
      return new Ke7efbae1920d8c2967(label, description == null ? "" : description, state, setter);
   }

   private void toggle() {
      this.setter.set(!this.state.get());
   }
}
