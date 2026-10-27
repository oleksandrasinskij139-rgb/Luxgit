package code.miaware.api.system.backend;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import java.util.Arrays;

public abstract class K46bff99e1f90a113f9 extends K5d3444fb5779729097 implements Ke298bfdac4e34a00d9 {
   public abstract String getName();

   public static String[] getValues(K46bff99e1f90a113f9... choices) {
      return (String[])Arrays.stream(choices).map(K46bff99e1f90a113f9::getName).toArray((x$0) -> new String[x$0]);
   }

   public static K46bff99e1f90a113f9 getChoiceByName(String value, K46bff99e1f90a113f9... choices) {
      if (choices != null) {
         if (value == null) {
            return choices.length == 0 ? null : choices[0];
         } else {
            for(K46bff99e1f90a113f9 choice : choices) {
               if (choice != null && value.equals(choice.getName())) {
                  return choice;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   @SafeVarargs
   public static <T extends Ke298bfdac4e34a00d9> T getChoiceByName(String value, T... choices) {
      if (choices != null && choices.length != 0) {
         for(T choice : choices) {
            if (choice.getName().equals(value)) {
               return choice;
            }
         }

         return (T)choices[0];
      } else {
         return null;
      }
   }
}
