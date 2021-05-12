package toothpick;

public interface MemberInjector<T> {
    void inject(T t, Scope scope);
}
