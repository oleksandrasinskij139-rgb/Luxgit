package code.miaware.api.event;

public record K8d7165024ff567f5e7(Runnable action) {
   public void unsubscribe() {
      this.action.run();
   }
}
