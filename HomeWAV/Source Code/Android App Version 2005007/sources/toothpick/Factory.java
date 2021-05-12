package toothpick;

public interface Factory<T> {
    T createInstance(Scope scope);

    Scope getTargetScope(Scope scope);

    boolean hasProvidesReleasableAnnotation();

    boolean hasProvidesSingletonAnnotation();

    boolean hasReleasableAnnotation();

    boolean hasScopeAnnotation();

    boolean hasSingletonAnnotation();
}
