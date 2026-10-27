package code.miaware.api.discord.rpc.utils;

import code.miaware.api.discord.rpc.callbacks.K2f87fa9c4589755105;
import code.miaware.api.discord.rpc.callbacks.K5ee1561ded49ca64f8;
import code.miaware.api.discord.rpc.callbacks.K90f36c5a52739e7876;
import code.miaware.api.discord.rpc.callbacks.Ka7a0c93f7cda9bb6df;
import code.miaware.api.discord.rpc.callbacks.Kd9cc74617eb2ba5e73;
import code.miaware.api.discord.rpc.callbacks.Ked68a0bbe8cc68dee7;

public class K99690a624fa8fb05b3 {
   private final K68b77923e989ac28ac handlers = new K68b77923e989ac28ac();

   public K68b77923e989ac28ac build() {
      return this.handlers;
   }

   public K99690a624fa8fb05b3 disconnected(Ka7a0c93f7cda9bb6df var1) {
      this.handlers.disconnected = var1;
      return this;
   }

   public K99690a624fa8fb05b3 errored(K2f87fa9c4589755105 var1) {
      this.handlers.errored = var1;
      return this;
   }

   public K99690a624fa8fb05b3 ready(Kd9cc74617eb2ba5e73 var1) {
      this.handlers.ready = var1;
      return this;
   }

   public K99690a624fa8fb05b3 joinRequest(K5ee1561ded49ca64f8 var1) {
      this.handlers.joinRequest = var1;
      return this;
   }

   public K99690a624fa8fb05b3 joinGame(K90f36c5a52739e7876 var1) {
      this.handlers.joinGame = var1;
      return this;
   }

   public K99690a624fa8fb05b3 spectateGame(Ked68a0bbe8cc68dee7 var1) {
      this.handlers.spectateGame = var1;
      return this;
   }
}
