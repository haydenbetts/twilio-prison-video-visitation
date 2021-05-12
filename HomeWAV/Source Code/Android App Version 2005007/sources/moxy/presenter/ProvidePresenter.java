package moxy.presenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProvidePresenter {
    public static final String EMPTY = "";

    String presenterId() default "";

    String tag() default "";
}
