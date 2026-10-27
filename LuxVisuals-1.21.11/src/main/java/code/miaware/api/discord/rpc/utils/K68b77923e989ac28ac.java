package code.miaware.api.discord.rpc.utils;

import code.miaware.api.discord.rpc.callbacks.K2f87fa9c4589755105;
import code.miaware.api.discord.rpc.callbacks.K5ee1561ded49ca64f8;
import code.miaware.api.discord.rpc.callbacks.K90f36c5a52739e7876;
import code.miaware.api.discord.rpc.callbacks.Ka7a0c93f7cda9bb6df;
import code.miaware.api.discord.rpc.callbacks.Kd9cc74617eb2ba5e73;
import code.miaware.api.discord.rpc.callbacks.Ked68a0bbe8cc68dee7;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class K68b77923e989ac28ac extends Structure {
   public Ka7a0c93f7cda9bb6df disconnected;
   public K5ee1561ded49ca64f8 joinRequest;
   public Ked68a0bbe8cc68dee7 spectateGame;
   public Kd9cc74617eb2ba5e73 ready;
   public K2f87fa9c4589755105 errored;
   public K90f36c5a52739e7876 joinGame;

   protected List<String> getFieldOrder() {
      return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
   }
}
