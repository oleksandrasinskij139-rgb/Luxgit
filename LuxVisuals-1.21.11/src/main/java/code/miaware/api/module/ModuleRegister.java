package code.miaware.api.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleRegister {
   String name();

   Kf76f042931183af5ba category();

   int bind() default -999;

   String description() default "";
}
