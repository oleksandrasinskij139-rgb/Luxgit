package code.miaware.api.discord.rpc.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Kc339d7f4ca09116837 extends Library {
   Kc339d7f4ca09116837 INSTANCE = (Kc339d7f4ca09116837)Native.loadLibrary("discord-rpc", Kc339d7f4ca09116837.class);

   void Discord_UpdateHandlers(K68b77923e989ac28ac var1);

   void Discord_UpdatePresence(K834e4ca50eb6914ef7 var1);

   void Discord_Respond(String var1, int var2);

   void Discord_Register(String var1, String var2);

   void Discord_Shutdown();

   void Discord_UpdateConnection();

   void Discord_RegisterSteamGame(String var1, String var2);

   void Discord_RunCallbacks();

   void Discord_Initialize(String var1, K68b77923e989ac28ac var2, boolean var3, String var4);

   void Discord_ClearPresence();
}
