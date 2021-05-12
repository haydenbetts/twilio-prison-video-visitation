package moxy.locators;

import moxy.MvpProcessor;
import moxy.PresenterBinder;

public class PresenterBinderLocator {
    private PresenterBinderLocator() {
    }

    public static <Delegated> PresenterBinder<Delegated> getPresenterBinders(Class<?> cls) {
        Class<? super Object> superclass;
        do {
            PresenterBinder<?> locatePresenterBinder = locatePresenterBinder(r1);
            Class<? super Object> cls2 = cls;
            if (locatePresenterBinder != null) {
                return locatePresenterBinder;
            }
            superclass = cls2.getSuperclass();
            cls2 = superclass;
        } while (superclass != null);
        return null;
    }

    private static PresenterBinder<?> locatePresenterBinder(Class<?> cls) {
        try {
            return (PresenterBinder) Class.forName(cls.getName() + MvpProcessor.PRESENTER_BINDER_SUFFIX).newInstance();
        } catch (Exception unused) {
            return null;
        }
    }
}
