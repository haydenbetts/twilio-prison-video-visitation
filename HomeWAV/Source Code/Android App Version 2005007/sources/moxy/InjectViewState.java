package moxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import moxy.viewstate.MvpViewState;

@Target({ElementType.TYPE})
public @interface InjectViewState {
    Class<? extends MvpViewState> value() default DefaultViewState.class;

    Class<? extends MvpView> view() default DefaultView.class;
}
