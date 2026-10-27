package code.miaware.api.utils.other;

final class Kfe798e29e7a8b1d73a {
   final long execNs;
   final long endNs;
   final Runnable action;
   boolean executed = false;

   Kfe798e29e7a8b1d73a(long execNs, long endNs, Runnable action) {
      this.execNs = execNs;
      this.endNs = endNs;
      this.action = action;
   }
}
