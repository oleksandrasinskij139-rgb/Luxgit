package code.miaware.api.module;

import code.miaware.api.utils.server.K079c3cf92902a5b0ab;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ServerRestriction {
   K079c3cf92902a5b0ab[] blockedServers() default {};

   K079c3cf92902a5b0ab[] allowedServers() default {};
}
