package moxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Deprecated
@Target({ElementType.TYPE})
public @interface RegisterMoxyReflectorPackages {
    String[] value();
}
