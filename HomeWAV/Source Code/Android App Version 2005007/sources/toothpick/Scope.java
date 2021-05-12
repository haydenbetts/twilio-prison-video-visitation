package toothpick;

import java.lang.annotation.Annotation;
import javax.inject.Provider;
import toothpick.config.Module;

public interface Scope {

    @FunctionalInterface
    public interface ScopeConfig {
        void configure(Scope scope);
    }

    <T> T getInstance(Class<T> cls);

    <T> T getInstance(Class<T> cls, String str);

    <T> Lazy<T> getLazy(Class<T> cls);

    <T> Lazy<T> getLazy(Class<T> cls, String str);

    Object getName();

    Scope getParentScope();

    <A extends Annotation> Scope getParentScope(Class<A> cls);

    <T> Provider<T> getProvider(Class<T> cls);

    <T> Provider<T> getProvider(Class<T> cls, String str);

    Scope getRootScope();

    void inject(Object obj);

    Scope installModules(Module... moduleArr);

    Scope installTestModules(Module... moduleArr);

    boolean isScopeAnnotationSupported(Class<? extends Annotation> cls);

    Scope openSubScope(Object obj);

    Scope openSubScope(Object obj, ScopeConfig scopeConfig);

    void release();

    Scope supportScopeAnnotation(Class<? extends Annotation> cls);
}
