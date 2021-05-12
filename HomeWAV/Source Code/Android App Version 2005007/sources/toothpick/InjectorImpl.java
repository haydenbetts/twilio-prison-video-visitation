package toothpick;

import toothpick.locators.MemberInjectorLocator;

public class InjectorImpl implements Injector {
    public <T> void inject(T t, Scope scope) {
        Class cls = t.getClass();
        do {
            MemberInjector memberInjector = MemberInjectorLocator.getMemberInjector(cls);
            if (memberInjector != null) {
                memberInjector.inject(t, scope);
                return;
            }
            cls = cls.getSuperclass();
        } while (cls != null);
    }
}
