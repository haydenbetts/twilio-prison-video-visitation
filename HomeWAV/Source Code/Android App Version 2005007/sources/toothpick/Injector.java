package toothpick;

public interface Injector {
    <T> void inject(T t, Scope scope);
}
