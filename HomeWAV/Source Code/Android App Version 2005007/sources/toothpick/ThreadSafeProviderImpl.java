package toothpick;

import java.lang.ref.WeakReference;
import javax.inject.Provider;

public class ThreadSafeProviderImpl<T> implements Provider<T>, Lazy<T> {
    private Class<T> clazz;
    private volatile T instance;
    private boolean isLazy;
    private String name;
    private WeakReference<Scope> scope;
    private String scopeName;

    public ThreadSafeProviderImpl(Scope scope2, Class<T> cls, String str, boolean z) {
        this.scope = new WeakReference<>(scope2);
        this.scopeName = scope2.getName().toString();
        this.clazz = cls;
        this.name = str;
        this.isLazy = z;
    }

    public T get() {
        if (this.isLazy && this.instance != null) {
            return this.instance;
        }
        synchronized (this) {
            if (this.isLazy) {
                if (this.instance == null) {
                    this.instance = getScope().getInstance(this.clazz, this.name);
                    this.scope.clear();
                }
                T t = this.instance;
                return t;
            }
            T instance2 = getScope().getInstance(this.clazz, this.name);
            return instance2;
        }
    }

    private Scope getScope() {
        Scope scope2 = (Scope) this.scope.get();
        if (scope2 != null) {
            return scope2;
        }
        Object[] objArr = new Object[2];
        objArr[0] = this.isLazy ? "lazy" : "provider";
        objArr[1] = this.scopeName;
        throw new IllegalStateException(String.format("The instance provided by the %s cannot be created when the associated scope: %s has been closed", objArr));
    }
}
