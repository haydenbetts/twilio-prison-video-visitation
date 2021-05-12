package toothpick.locators;

import toothpick.MemberInjector;

public class MemberInjectorLocator {
    private MemberInjectorLocator() {
    }

    public static <T> MemberInjector<T> getMemberInjector(Class<T> cls) {
        try {
            return (MemberInjector) Class.forName(cls.getName() + "__MemberInjector").newInstance();
        } catch (Exception unused) {
            return null;
        }
    }
}
