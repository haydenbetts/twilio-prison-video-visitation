package moxy.presenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import moxy.MvpPresenter;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProvidePresenterTag {
    public static final String EMPTY = "";

    Class<? extends MvpPresenter<?>> presenterClass();

    String presenterId() default "";
}
