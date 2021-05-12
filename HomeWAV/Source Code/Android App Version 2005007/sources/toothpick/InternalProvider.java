package toothpick;

import javax.inject.Provider;
import toothpick.locators.FactoryLocator;

public class InternalProvider<T> {
    private Factory<? extends T> factory;
    private Class<? extends T> factoryClass;
    volatile T instance;
    private boolean isProvidingReleasable;
    private boolean isProvidingSingleton;
    private boolean isReleasable;
    private boolean isSingleton;
    private Factory<? extends Provider<? extends T>> providerFactory;
    private Class<? extends Provider<? extends T>> providerFactoryClass;
    volatile Provider<? extends T> providerInstance;

    InternalProvider(T t) {
        if (t != null) {
            this.instance = t;
            this.isSingleton = true;
            return;
        }
        throw new IllegalArgumentException("The instance can't be null.");
    }

    InternalProvider(Provider<? extends T> provider, boolean z, boolean z2) {
        if (provider != null) {
            this.providerInstance = provider;
            boolean z3 = true;
            this.isSingleton = true;
            this.isProvidingSingleton = z;
            this.isProvidingReleasable = (!z || !z2) ? false : z3;
            return;
        }
        throw new IllegalArgumentException("The provider can't be null.");
    }

    InternalProvider(Factory<T> factory2) {
        if (factory2 != null) {
            this.factory = factory2;
            boolean hasSingletonAnnotation = factory2.hasSingletonAnnotation();
            this.isSingleton = hasSingletonAnnotation;
            boolean z = true;
            this.isReleasable = hasSingletonAnnotation && factory2.hasReleasableAnnotation();
            boolean hasProvidesSingletonAnnotation = factory2.hasProvidesSingletonAnnotation();
            this.isProvidingSingleton = hasProvidesSingletonAnnotation;
            this.isProvidingReleasable = (!hasProvidesSingletonAnnotation || !factory2.hasProvidesReleasableAnnotation()) ? false : z;
            return;
        }
        throw new IllegalArgumentException("The factory can't be null.");
    }

    InternalProvider(Class<? extends T> cls, boolean z, boolean z2) {
        if (cls != null) {
            this.factoryClass = cls;
            this.isSingleton = z;
            this.isReleasable = z && z2;
            return;
        }
        throw new IllegalArgumentException("The factory class can't be null.");
    }

    InternalProvider(Class<? extends Provider<? extends T>> cls, boolean z, boolean z2, boolean z3, boolean z4) {
        if (cls != null) {
            this.providerFactoryClass = cls;
            this.isProvidingSingleton = z3;
            boolean z5 = true;
            this.isProvidingReleasable = z3 && z4;
            this.isSingleton = z;
            this.isReleasable = (!z || !z2) ? false : z5;
            return;
        }
        throw new IllegalArgumentException("The factory class can't be null.");
    }

    public synchronized T get(Scope scope) {
        if (this.instance != null) {
            return this.instance;
        } else if (this.providerInstance == null) {
            Class<? extends T> cls = this.factoryClass;
            boolean z = true;
            if (cls != null && this.factory == null) {
                Factory<? extends T> factory2 = FactoryLocator.getFactory(cls);
                this.factory = factory2;
                boolean hasSingletonAnnotation = factory2.hasSingletonAnnotation() | this.isSingleton;
                this.isSingleton = hasSingletonAnnotation;
                this.isReleasable = (hasSingletonAnnotation && this.factory.hasReleasableAnnotation()) | this.isReleasable;
                this.factoryClass = null;
            }
            Factory<? extends T> factory3 = this.factory;
            if (factory3 == null) {
                Class<? extends Provider<? extends T>> cls2 = this.providerFactoryClass;
                if (cls2 != null && this.providerFactory == null) {
                    Factory<? extends Provider<? extends T>> factory4 = FactoryLocator.getFactory(cls2);
                    this.providerFactory = factory4;
                    boolean hasSingletonAnnotation2 = factory4.hasSingletonAnnotation() | this.isSingleton;
                    this.isSingleton = hasSingletonAnnotation2;
                    this.isReleasable = (hasSingletonAnnotation2 && this.providerFactory.hasReleasableAnnotation()) | this.isReleasable;
                    boolean hasProvidesSingletonAnnotation = this.isProvidingSingleton | this.providerFactory.hasProvidesSingletonAnnotation();
                    this.isProvidingSingleton = hasProvidesSingletonAnnotation;
                    boolean z2 = this.isProvidingReleasable;
                    if (!hasProvidesSingletonAnnotation || !this.providerFactory.hasProvidesReleasableAnnotation()) {
                        z = false;
                    }
                    this.isProvidingReleasable = z2 | z;
                    this.providerFactoryClass = null;
                }
                Factory<? extends Provider<? extends T>> factory5 = this.providerFactory;
                if (factory5 == null) {
                    throw new IllegalStateException("A provider can only be used with an instance, a provider, a factory or a provider factory. Should not happen.");
                } else if (this.isSingleton) {
                    this.providerInstance = (Provider) factory5.createInstance(scope);
                    this.providerFactory = null;
                    if (this.isProvidingSingleton) {
                        this.instance = this.providerInstance.get();
                        return this.instance;
                    }
                    return this.providerInstance.get();
                } else if (this.isProvidingSingleton) {
                    this.instance = ((Provider) factory5.createInstance(scope)).get();
                    this.providerFactory = null;
                    return this.instance;
                } else {
                    return ((Provider) factory5.createInstance(scope)).get();
                }
            } else if (this.isSingleton) {
                this.instance = factory3.createInstance(scope);
                this.factory = null;
                return this.instance;
            } else {
                return factory3.createInstance(scope);
            }
        } else if (this.isProvidingSingleton) {
            this.instance = this.providerInstance.get();
            return this.instance;
        } else {
            return this.providerInstance.get();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReleasable() {
        return this.isReleasable || this.isProvidingReleasable;
    }

    /* access modifiers changed from: package-private */
    public void release() {
        if (this.isReleasable) {
            if (this.providerInstance != null) {
                this.providerInstance = null;
            } else {
                this.instance = null;
            }
        }
        if (this.isProvidingReleasable) {
            this.instance = null;
        }
    }
}
