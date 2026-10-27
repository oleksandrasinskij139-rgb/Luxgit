package code.miaware.client.features.modules.player.chestsorter;

public record K13d4503099adf1f988(int category, int material, int enchanted, int damage, String item, String name) implements Comparable<K13d4503099adf1f988> {
   public int compareTo(K13d4503099adf1f988 other) {
      int result = Integer.compare(this.category, other.category);
      if (result == 0) {
         result = Integer.compare(this.material, other.material);
      }

      if (result == 0) {
         result = Integer.compare(this.enchanted, other.enchanted);
      }

      if (result == 0) {
         result = this.item.compareTo(other.item);
      }

      if (result == 0) {
         result = this.name.compareTo(other.name);
      }

      if (result == 0) {
         result = Integer.compare(this.damage, other.damage);
      }

      return result;
   }
}
