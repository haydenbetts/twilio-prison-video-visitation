package toothpick.locators;

public class NoFactoryFoundException extends RuntimeException {
    public NoFactoryFoundException(Class cls) {
        this(cls, (Throwable) null);
    }

    public NoFactoryFoundException(Class cls, Throwable th) {
        super(String.format("No factory could be found for class %s. Check that the class has either a @Inject annotated constructor or contains @Inject annotated members.", new Object[]{cls.getName()}), th);
    }
}
