package code.miaware.api.event.events.client;

public record K256ab2bf60c15ab260(int key, int action, boolean mouse) {
   public boolean matchesBind(int bind) {
      return bind != -999 && bind != -1 && bind == (this.mouse ? -100 + this.key : this.key);
   }
}
