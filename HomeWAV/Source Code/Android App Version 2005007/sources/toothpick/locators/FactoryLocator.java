package toothpick.locators;

import toothpick.Factory;

public class FactoryLocator {
    private FactoryLocator() {
    }

    public static <T> Factory<T> getFactory(Class<T> cls) {
        try {
            return (Factory) Class.forName(cls.getName() + "__Factory").newInstance();
        } catch (Exception e) {
            throw new NoFactoryFoundException(cls, e);
        }
    }
}
