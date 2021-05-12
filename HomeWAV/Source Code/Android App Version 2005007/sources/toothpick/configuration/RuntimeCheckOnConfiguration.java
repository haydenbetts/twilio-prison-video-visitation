package toothpick.configuration;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import toothpick.Scope;
import toothpick.config.Binding;

class RuntimeCheckOnConfiguration implements RuntimeCheckConfiguration {
    private ThreadLocal<LinkedHashSet<Pair>> cycleDetectionStack = new ThreadLocal<LinkedHashSet<Pair>>() {
        /* access modifiers changed from: protected */
        public LinkedHashSet<Pair> initialValue() {
            return new LinkedHashSet<>();
        }
    };

    RuntimeCheckOnConfiguration() {
    }

    /* renamed from: toothpick.configuration.RuntimeCheckOnConfiguration$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$toothpick$config$Binding$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                toothpick.config.Binding$Mode r1 = toothpick.config.Binding.Mode.PROVIDER_CLASS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: toothpick.configuration.RuntimeCheckOnConfiguration.AnonymousClass2.<clinit>():void");
        }
    }

    public void checkIllegalBinding(Binding binding, Scope scope) {
        Class cls;
        int i = AnonymousClass2.$SwitchMap$toothpick$config$Binding$Mode[binding.getMode().ordinal()];
        if (i == 1) {
            cls = binding.getKey();
        } else if (i == 2) {
            cls = binding.getImplementationClass();
        } else if (i == 3) {
            cls = binding.getProviderClass();
        } else {
            return;
        }
        Annotation[] annotations = cls.getAnnotations();
        int length = annotations.length;
        int i2 = 0;
        while (i2 < length) {
            Class<? extends Annotation> annotationType = annotations[i2].annotationType();
            if (!annotationType.isAnnotationPresent(javax.inject.Scope.class) || scope.isScopeAnnotationSupported(annotationType)) {
                i2++;
            } else {
                throw new IllegalBindingException(String.format("Class %s cannot be scoped. It has a scope annotation: %s that is not supported by the current scope: %s", new Object[]{cls.getName(), annotationType.getName(), scope.getName()}));
            }
        }
    }

    public void checkCyclesStart(Class cls, String str) {
        Pair pair = new Pair(cls, str);
        LinkedHashSet linkedHashSet = this.cycleDetectionStack.get();
        if (!linkedHashSet.contains(pair)) {
            linkedHashSet.add(pair);
            return;
        }
        throw new CyclicDependencyException((List<Class<?>>) Pair.getClassList(linkedHashSet), cls);
    }

    public void checkCyclesEnd(Class cls, String str) {
        this.cycleDetectionStack.get().remove(new Pair(cls, str));
    }

    private static class Pair {
        public final Class clazz;
        public final String name;

        Pair(Class cls, String str) {
            this.clazz = cls;
            this.name = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) {
                return false;
            }
            Pair pair = (Pair) obj;
            if (!equal(pair.clazz, this.clazz) || !equal(pair.name, this.name)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            Class cls = this.clazz;
            int i = 0;
            int hashCode = cls == null ? 0 : cls.hashCode();
            String str = this.name;
            if (str != null) {
                i = str.hashCode();
            }
            return hashCode ^ i;
        }

        private boolean equal(Object obj, Object obj2) {
            return Objects.equals(obj, obj2);
        }

        /* access modifiers changed from: private */
        public static List<Class<?>> getClassList(Collection<Pair> collection) {
            ArrayList arrayList = new ArrayList();
            for (Pair pair : collection) {
                arrayList.add(pair.clazz);
            }
            return arrayList;
        }
    }
}
