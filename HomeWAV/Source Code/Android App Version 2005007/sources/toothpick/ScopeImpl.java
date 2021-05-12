package toothpick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Provider;
import toothpick.Scope;
import toothpick.config.Binding;
import toothpick.config.Module;
import toothpick.configuration.ConfigurationHolder;
import toothpick.configuration.IllegalBindingException;
import toothpick.locators.FactoryLocator;

public class ScopeImpl extends ScopeNode {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    static final IdentityHashMap<Class, InternalProvider> mapClassesToUnNamedUnScopedProviders = new IdentityHashMap<>();
    private boolean hasTestModules;
    final IdentityHashMap<Class, Map<String, InternalScopedProvider>> mapClassesToNamedScopedProviders = new IdentityHashMap<>();
    final IdentityHashMap<Class, InternalScopedProvider> mapClassesToUnNamedScopedProviders = new IdentityHashMap<>();

    public ScopeImpl(Object obj) {
        super(obj);
        installBindingForScopeClass();
    }

    public <T> T getInstance(Class<T> cls) {
        return getInstance(cls, (String) null);
    }

    public <T> T getInstance(Class<T> cls, String str) {
        crashIfClosed();
        ConfigurationHolder.configuration.checkCyclesStart(cls, str);
        try {
            return lookupProvider(cls, str).get(this);
        } finally {
            ConfigurationHolder.configuration.checkCyclesEnd(cls, str);
        }
    }

    public <T> Provider<T> getProvider(Class<T> cls) {
        return getProvider(cls, (String) null);
    }

    public <T> Provider<T> getProvider(Class<T> cls, String str) {
        crashIfClosed();
        return new ThreadSafeProviderImpl(this, cls, str, false);
    }

    public <T> Lazy<T> getLazy(Class<T> cls) {
        return getLazy(cls, (String) null);
    }

    public <T> Lazy<T> getLazy(Class<T> cls, String str) {
        crashIfClosed();
        return new ThreadSafeProviderImpl(this, cls, str, true);
    }

    public synchronized Scope installTestModules(Module... moduleArr) {
        if (!this.hasTestModules) {
            installModules(true, moduleArr);
            this.hasTestModules = true;
        } else {
            throw new IllegalStateException("TestModules can only be installed once per scope.");
        }
        return this;
    }

    public Scope installModules(Module... moduleArr) {
        installModules(false, moduleArr);
        return this;
    }

    public void inject(Object obj) {
        Toothpick.inject(obj, this);
    }

    public String toString() {
        ArrayList arrayList;
        ArrayList arrayList2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(':');
        sb.append(System.identityHashCode(this));
        sb.append(LINE_SEPARATOR);
        sb.append("Providers: [");
        synchronized (this.mapClassesToNamedScopedProviders) {
            arrayList = new ArrayList(this.mapClassesToNamedScopedProviders.keySet());
        }
        synchronized (this.mapClassesToUnNamedScopedProviders) {
            arrayList.addAll(this.mapClassesToUnNamedScopedProviders.keySet());
        }
        Collections.sort(arrayList, new ClassNameComparator((AnonymousClass1) null));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(((Class) it.next()).getName());
            sb.append(',');
        }
        if (!arrayList.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        sb.append(LINE_SEPARATOR);
        Iterator it2 = this.childrenScopes.values().iterator();
        while (it2.hasNext()) {
            Scope scope = (Scope) it2.next();
            sb.append(it2.hasNext() ^ true ? '\\' : '+');
            sb.append("---");
            String[] split = scope.toString().split(LINE_SEPARATOR);
            for (int i = 0; i < split.length; i++) {
                String str = split[i];
                if (i != 0) {
                    sb.append("    ");
                }
                sb.append(str);
                sb.append(LINE_SEPARATOR);
            }
        }
        if (getRootScope() == this) {
            sb.append("UnScoped providers: [");
            IdentityHashMap<Class, InternalProvider> identityHashMap = mapClassesToUnNamedUnScopedProviders;
            synchronized (identityHashMap) {
                arrayList2 = new ArrayList(identityHashMap.keySet());
            }
            Collections.sort(arrayList2, new ClassNameComparator((AnonymousClass1) null));
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                sb.append(((Class) it3.next()).getName());
                sb.append(',');
            }
            if (!arrayList2.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private void installModules(boolean z, Module... moduleArr) {
        int length = moduleArr.length;
        int i = 0;
        while (i < length) {
            Module module = moduleArr[i];
            try {
                installModule(z, module);
                i++;
            } catch (Exception e) {
                throw new IllegalStateException(String.format("Module %s couldn't be installed", new Object[]{module.getClass().getName()}), e);
            }
        }
    }

    private void installModule(boolean z, Module module) {
        for (Binding next : module.getBindingSet()) {
            if (next != null) {
                Class key = next.getKey();
                String name = next.getName();
                if (!z) {
                    try {
                        if (getScopedProvider(key, name) != null) {
                        }
                    } catch (Exception e) {
                        throw new IllegalBindingException(String.format("Binding %s couldn't be installed", new Object[]{name}), e);
                    }
                }
                installScopedProvider(key, name, toProvider(next), z);
            } else {
                throw new IllegalStateException("A module can't have a null binding : " + module);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public <T> InternalProvider<T> toProvider(Binding<T> binding) {
        if (binding != null) {
            ConfigurationHolder.configuration.checkIllegalBinding(binding, this);
            int i = AnonymousClass1.$SwitchMap$toothpick$config$Binding$Mode[binding.getMode().ordinal()];
            if (i == 1) {
                return new InternalScopedProvider((Scope) this, binding.getKey(), binding.isCreatingSingleton(), binding.isCreatingReleasable());
            }
            if (i == 2) {
                return new InternalScopedProvider((Scope) this, binding.getImplementationClass(), binding.isCreatingSingleton(), binding.isCreatingReleasable());
            }
            if (i == 3) {
                return new InternalScopedProvider((Scope) this, binding.getInstance());
            }
            if (i == 4) {
                return new InternalScopedProvider((Scope) this, binding.getProviderInstance(), binding.isProvidingSingleton(), binding.isProvidingReleasable());
            }
            if (i == 5) {
                return new InternalScopedProvider(this, binding.getProviderClass(), binding.isCreatingSingleton(), binding.isCreatingReleasable(), binding.isProvidingSingleton(), binding.isProvidingReleasable());
            }
            throw new IllegalStateException(String.format("mode is not handled: %s. This should not happen.", new Object[]{binding.getMode()}));
        }
        throw new IllegalStateException("null binding are not allowed. Should not happen unless getBindingSet is overridden.");
    }

    /* renamed from: toothpick.ScopeImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$toothpick$config$Binding$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                toothpick.config.Binding$Mode[] r0 = toothpick.config.Binding.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$toothpick$config$Binding$Mode = r0
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.SIMPLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$toothpick$config$Binding$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.CLASS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$toothpick$config$Binding$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.INSTANCE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$toothpick$config$Binding$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.PROVIDER_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$toothpick$config$Binding$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.PROVIDER_CLASS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: toothpick.ScopeImpl.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public <T> InternalProvider<? extends T> lookupProvider(Class<T> cls, String str) {
        if (cls != null) {
            InternalProvider<? extends T> scopedProvider = getScopedProvider(cls, str);
            if (scopedProvider != null) {
                return scopedProvider;
            }
            for (Scope scope : this.parentScopes) {
                InternalProvider<? extends T> scopedProvider2 = ((ScopeImpl) scope).getScopedProvider(cls, str);
                if (scopedProvider2 != null) {
                    return scopedProvider2;
                }
            }
            if (str == null) {
                InternalProvider<? extends T> unUnScopedProvider = getUnUnScopedProvider(cls, (String) null);
                if (unUnScopedProvider != null) {
                    return unUnScopedProvider;
                }
                Factory<T> factory = FactoryLocator.getFactory(cls);
                if (!factory.hasScopeAnnotation()) {
                    return installUnScopedProvider(cls, (String) null, new InternalProvider(factory));
                }
                Scope targetScope = factory.getTargetScope(this);
                return ((ScopeImpl) targetScope).installScopedProvider(cls, (String) null, new InternalScopedProvider(targetScope, factory), false);
            }
            throw new RuntimeException(String.format("No binding was defined for class %s and name %s in scope %s and its parents %s", new Object[]{cls.getName(), str, getName(), getParentScopesNames()}));
        }
        throw new IllegalArgumentException("TP can't get an instance of a null class.");
    }

    private <T> InternalProvider<? extends T> getScopedProvider(Class<T> cls, String str) {
        return getInternalProvider(cls, str, true);
    }

    private <T> InternalProvider<? extends T> getUnUnScopedProvider(Class<T> cls, String str) {
        return getInternalProvider(cls, str, false);
    }

    private <T> InternalProvider<? extends T> getInternalProvider(Class<T> cls, String str, boolean z) {
        InternalProvider<? extends T> internalProvider;
        InternalProvider<? extends T> internalProvider2;
        if (str != null) {
            synchronized (this.mapClassesToNamedScopedProviders) {
                Map map = this.mapClassesToNamedScopedProviders.get(cls);
                if (map == null) {
                    return null;
                }
                InternalProvider<? extends T> internalProvider3 = (InternalProvider) map.get(str);
                return internalProvider3;
            }
        } else if (z) {
            synchronized (this.mapClassesToUnNamedScopedProviders) {
                internalProvider2 = this.mapClassesToUnNamedScopedProviders.get(cls);
            }
            return internalProvider2;
        } else {
            IdentityHashMap<Class, InternalProvider> identityHashMap = mapClassesToUnNamedUnScopedProviders;
            synchronized (identityHashMap) {
                internalProvider = identityHashMap.get(cls);
            }
            return internalProvider;
        }
    }

    private <T> InternalProvider<? extends T> installScopedProvider(Class<T> cls, String str, InternalProvider<? extends T> internalProvider, boolean z) {
        return installInternalProvider(cls, str, internalProvider, true, z);
    }

    private <T> InternalProvider<? extends T> installUnScopedProvider(Class<T> cls, String str, InternalProvider<? extends T> internalProvider) {
        return installInternalProvider(cls, str, internalProvider, false, false);
    }

    private <T> InternalProvider<? extends T> installInternalProvider(Class<T> cls, String str, InternalProvider<? extends T> internalProvider, boolean z, boolean z2) {
        if (str != null) {
            return installNamedScopedProvider(cls, str, (InternalScopedProvider) internalProvider, z2);
        }
        if (z) {
            return installUnNamedScopedProvider(cls, (InternalScopedProvider) internalProvider, z2);
        }
        return installUnScopedProvider(cls, internalProvider, z2);
    }

    private <T> InternalProvider<? extends T> installNamedScopedProvider(Class<T> cls, String str, InternalScopedProvider<? extends T> internalScopedProvider, boolean z) {
        synchronized (this.mapClassesToNamedScopedProviders) {
            Map map = this.mapClassesToNamedScopedProviders.get(cls);
            if (map == null) {
                HashMap hashMap = new HashMap(1);
                this.mapClassesToNamedScopedProviders.put(cls, hashMap);
                hashMap.put(str, internalScopedProvider);
                return internalScopedProvider;
            }
            InternalProvider<? extends T> internalProvider = (InternalProvider) map.get(str);
            if (internalProvider != null) {
                if (!z) {
                    return internalProvider;
                }
            }
            map.put(str, internalScopedProvider);
            return internalScopedProvider;
        }
    }

    private <T> InternalProvider<? extends T> installUnNamedScopedProvider(Class<T> cls, InternalScopedProvider<? extends T> internalScopedProvider, boolean z) {
        synchronized (this.mapClassesToUnNamedScopedProviders) {
            InternalScopedProvider internalScopedProvider2 = this.mapClassesToUnNamedScopedProviders.get(cls);
            if (internalScopedProvider2 != null) {
                if (!z) {
                    return internalScopedProvider2;
                }
            }
            this.mapClassesToUnNamedScopedProviders.put(cls, internalScopedProvider);
            return internalScopedProvider;
        }
    }

    private <T> InternalProvider<? extends T> installUnScopedProvider(Class<T> cls, InternalProvider<? extends T> internalProvider, boolean z) {
        IdentityHashMap<Class, InternalProvider> identityHashMap = mapClassesToUnNamedUnScopedProviders;
        synchronized (identityHashMap) {
            InternalProvider<? extends T> internalProvider2 = identityHashMap.get(cls);
            if (internalProvider2 != null) {
                if (!z) {
                    return internalProvider2;
                }
            }
            identityHashMap.put(cls, internalProvider);
            return internalProvider;
        }
    }

    private void crashIfClosed() {
        if (!this.isOpen) {
            throw new IllegalStateException(String.format("The scope with name %s has been already closed. It can't be used to create new instances.", new Object[]{this.name}));
        }
    }

    static void resetUnScopedProviders() {
        mapClassesToUnNamedUnScopedProviders.clear();
    }

    /* access modifiers changed from: protected */
    public void reset() {
        super.reset();
        this.mapClassesToNamedScopedProviders.clear();
        this.mapClassesToUnNamedScopedProviders.clear();
        this.hasTestModules = false;
        installBindingForScopeClass();
    }

    public Scope openSubScope(Object obj) {
        return Toothpick.openScopes(getName(), obj);
    }

    public Scope openSubScope(Object obj, Scope.ScopeConfig scopeConfig) {
        boolean isScopeOpen = Toothpick.isScopeOpen(obj);
        Scope openScopes = Toothpick.openScopes(getName(), obj);
        if (!isScopeOpen) {
            scopeConfig.configure(openScopes);
        }
        return openScopes;
    }

    public void release() {
        for (ScopeNode release : this.childrenScopes.values()) {
            release.release();
        }
        synchronized (this.mapClassesToUnNamedScopedProviders) {
            for (InternalProvider next : this.mapClassesToUnNamedScopedProviders.values()) {
                if (next.isReleasable()) {
                    next.release();
                }
            }
        }
        synchronized (this.mapClassesToNamedScopedProviders) {
            for (Map<String, InternalScopedProvider> values : this.mapClassesToNamedScopedProviders.values()) {
                for (InternalProvider internalProvider : values.values()) {
                    if (internalProvider.isReleasable()) {
                        internalProvider.release();
                    }
                }
            }
        }
    }

    private void installBindingForScopeClass() {
        installScopedProvider(Scope.class, (String) null, new InternalScopedProvider((Scope) this, this), false);
    }

    private static class ClassNameComparator implements Comparator<Class> {
        private ClassNameComparator() {
        }

        /* synthetic */ ClassNameComparator(AnonymousClass1 r1) {
            this();
        }

        public int compare(Class cls, Class cls2) {
            return cls.getName().compareTo(cls2.getName());
        }
    }
}
