package toothpick;

import javax.inject.Provider;

public class InternalScopedProvider<T> extends InternalProvider<T> {
    protected Scope scope;

    public InternalScopedProvider(Scope scope2, Factory<T> factory) {
        super(factory);
        this.scope = scope2;
    }

    public InternalScopedProvider(Scope scope2, T t) {
        super(t);
        this.scope = scope2;
    }

    public InternalScopedProvider(Scope scope2, Provider<? extends T> provider, boolean z, boolean z2) {
        super(provider, z, z2);
        this.scope = scope2;
    }

    public InternalScopedProvider(Scope scope2, Class<? extends T> cls, boolean z, boolean z2) {
        super(cls, z, z2);
        this.scope = scope2;
    }

    public InternalScopedProvider(Scope scope2, Class<? extends Provider<? extends T>> cls, boolean z, boolean z2, boolean z3, boolean z4) {
        super(cls, z, z2, z3, z4);
        this.scope = scope2;
    }

    public T get(Scope scope2) {
        return super.get(this.scope);
    }
}
