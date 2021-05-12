package toothpick.config;

import java.lang.annotation.Annotation;
import javax.inject.Provider;
import javax.inject.Qualifier;

public class Binding<T> {
    /* access modifiers changed from: private */
    public Class<? extends T> implementationClass;
    /* access modifiers changed from: private */
    public T instance;
    /* access modifiers changed from: private */
    public boolean isCreatingReleasable;
    /* access modifiers changed from: private */
    public boolean isCreatingSingleton;
    /* access modifiers changed from: private */
    public boolean isProvidingReleasable;
    /* access modifiers changed from: private */
    public boolean isProvidingSingleton;
    private Class<T> key;
    /* access modifiers changed from: private */
    public Mode mode = Mode.SIMPLE;
    /* access modifiers changed from: private */
    public String name;
    /* access modifiers changed from: private */
    public Class<? extends Provider<? extends T>> providerClass;
    /* access modifiers changed from: private */
    public Provider<? extends T> providerInstance;

    public enum Mode {
        SIMPLE,
        CLASS,
        INSTANCE,
        PROVIDER_INSTANCE,
        PROVIDER_CLASS
    }

    public Binding(Class<T> cls) {
        this.key = cls;
    }

    public Binding<T>.CanBeReleasable singleton() {
        this.isCreatingSingleton = true;
        return new CanBeReleasable();
    }

    public Mode getMode() {
        return this.mode;
    }

    public Class<T> getKey() {
        return this.key;
    }

    public Class<? extends T> getImplementationClass() {
        return this.implementationClass;
    }

    public T getInstance() {
        return this.instance;
    }

    public Provider<? extends T> getProviderInstance() {
        return this.providerInstance;
    }

    public Class<? extends Provider<? extends T>> getProviderClass() {
        return this.providerClass;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCreatingSingleton() {
        return this.isCreatingSingleton;
    }

    public boolean isProvidingSingleton() {
        return this.isProvidingSingleton;
    }

    public boolean isCreatingReleasable() {
        return this.isCreatingReleasable;
    }

    public boolean isProvidingReleasable() {
        return this.isProvidingReleasable;
    }

    public class CanBeNamed extends Binding<T>.CanBeBound {
        public CanBeNamed() {
            super();
        }

        public Binding<T>.CanBeBound withName(String str) {
            String unused = Binding.this.name = str;
            return new CanBeBound();
        }

        public <A extends Annotation> Binding<T>.CanBeBound withName(Class<A> cls) {
            if (cls.isAnnotationPresent(Qualifier.class)) {
                String unused = Binding.this.name = cls.getCanonicalName();
                return new CanBeBound();
            }
            throw new IllegalArgumentException(String.format("Only qualifier annotation annotations can be used to define a binding name. Add @Qualifier to %s", new Object[]{cls}));
        }
    }

    public class CanBeBound {
        public CanBeBound() {
        }

        public Binding<T>.CanBeReleasable singleton() {
            boolean unused = Binding.this.isCreatingSingleton = true;
            return new CanBeReleasable();
        }

        public void toInstance(T t) {
            Object unused = Binding.this.instance = t;
            Mode unused2 = Binding.this.mode = Mode.INSTANCE;
        }

        public Binding<T>.CanBeSingleton to(Class<? extends T> cls) {
            Class unused = Binding.this.implementationClass = cls;
            Mode unused2 = Binding.this.mode = Mode.CLASS;
            return new CanBeSingleton();
        }

        public Binding<T>.CanProvideSingletonOrSingleton toProvider(Class<? extends Provider<? extends T>> cls) {
            Class unused = Binding.this.providerClass = cls;
            Mode unused2 = Binding.this.mode = Mode.PROVIDER_CLASS;
            return new CanProvideSingletonOrSingleton();
        }

        public Binding<T>.CanProvideSingleton toProviderInstance(Provider<? extends T> provider) {
            Provider unused = Binding.this.providerInstance = provider;
            Mode unused2 = Binding.this.mode = Mode.PROVIDER_INSTANCE;
            return new CanProvideSingleton();
        }
    }

    public class CanBeSingleton {
        public CanBeSingleton() {
        }

        public Binding<T>.CanBeReleasable singleton() {
            boolean unused = Binding.this.isCreatingSingleton = true;
            return new CanBeReleasable();
        }
    }

    public class CanBeReleasable {
        public CanBeReleasable() {
        }

        public void releasable() {
            boolean unused = Binding.this.isCreatingReleasable = true;
        }
    }

    public class CanProvideSingleton {
        public CanProvideSingleton() {
        }

        public Binding<T>.CanProvideReleasable providesSingleton() {
            boolean unused = Binding.this.isProvidingSingleton = true;
            return new CanProvideReleasable();
        }
    }

    public class CanProvideReleasable {
        public CanProvideReleasable() {
        }

        public void providesReleasable() {
            boolean unused = Binding.this.isProvidingReleasable = true;
        }
    }

    public class CanProvideSingletonOrSingleton extends Binding<T>.CanBeSingleton {
        public CanProvideSingletonOrSingleton() {
            super();
        }

        public Binding<T>.CanProvideReleasableAndThenOnlySingleton providesSingleton() {
            boolean unused = Binding.this.isProvidingSingleton = true;
            return new CanProvideReleasableAndThenOnlySingleton();
        }
    }

    public class CanProvideReleasableAndThenOnlySingleton {
        public CanProvideReleasableAndThenOnlySingleton() {
        }

        public Binding<T>.CanBeOnlySingleton providesReleasable() {
            boolean unused = Binding.this.isProvidingReleasable = true;
            return new CanBeOnlySingleton();
        }
    }

    public class CanBeOnlySingleton {
        public CanBeOnlySingleton() {
        }

        public void singleton() {
            boolean unused = Binding.this.isCreatingSingleton = true;
        }
    }
}
