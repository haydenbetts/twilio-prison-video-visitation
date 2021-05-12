package toothpick;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import toothpick.Scope;
import toothpick.configuration.Configuration;
import toothpick.configuration.ConfigurationHolder;

public class Toothpick {
    private static final ConcurrentHashMap<Object, Scope> MAP_KEY_TO_SCOPE = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Object, Scope> ROOT_SCOPES = new ConcurrentHashMap<>();
    static Injector injector = new InjectorImpl();

    protected Toothpick() {
        throw new RuntimeException("Constructor can't be invoked even via reflection.");
    }

    public static Scope openRootScope() {
        ConcurrentHashMap<Object, Scope> concurrentHashMap = ROOT_SCOPES;
        synchronized (concurrentHashMap) {
            if (concurrentHashMap.size() > 1) {
                throw new RuntimeException("openRootScope() is not supported when multiple root scopes are enabled. Use 'Configuration.preventMultipleRootScopes()' to enable it.");
            } else if (concurrentHashMap.size() == 1) {
                Scope next = concurrentHashMap.values().iterator().next();
                return next;
            } else {
                Scope openScope = openScope(Toothpick.class);
                return openScope;
            }
        }
    }

    public static boolean isScopeOpen(Object obj) {
        if (obj != null) {
            return MAP_KEY_TO_SCOPE.containsKey(obj);
        }
        throw new IllegalArgumentException("null scope names are not allowed.");
    }

    public static Scope openScopes(Object... objArr) {
        if (objArr == null) {
            throw new IllegalArgumentException("null scope names are not allowed.");
        } else if (objArr.length != 0) {
            ScopeNode scopeNode = (ScopeNode) openScope(objArr[0], true);
            for (int i = 1; i < objArr.length; i++) {
                scopeNode = scopeNode.addChild((ScopeNode) openScope(objArr[i], false));
            }
            return scopeNode;
        } else {
            throw new IllegalArgumentException("Minimally, one scope name is required.");
        }
    }

    public static Scope openScope(Object obj) {
        return openScope(obj, true);
    }

    public static Scope openScope(Object obj, Scope.ScopeConfig scopeConfig) {
        if (isScopeOpen(obj)) {
            return openScope(obj);
        }
        Scope openScope = openScope(obj, true);
        scopeConfig.configure(openScope);
        return openScope;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static toothpick.Scope openScope(java.lang.Object r3, boolean r4) {
        /*
            java.util.concurrent.ConcurrentHashMap<java.lang.Object, toothpick.Scope> r0 = ROOT_SCOPES
            monitor-enter(r0)
            if (r3 == 0) goto L_0x002e
            java.util.concurrent.ConcurrentHashMap<java.lang.Object, toothpick.Scope> r1 = MAP_KEY_TO_SCOPE     // Catch:{ all -> 0x002c }
            java.lang.Object r2 = r1.get(r3)     // Catch:{ all -> 0x002c }
            toothpick.Scope r2 = (toothpick.Scope) r2     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r2
        L_0x0011:
            toothpick.ScopeImpl r2 = new toothpick.ScopeImpl     // Catch:{ all -> 0x002c }
            r2.<init>(r3)     // Catch:{ all -> 0x002c }
            java.lang.Object r1 = r1.putIfAbsent(r3, r2)     // Catch:{ all -> 0x002c }
            toothpick.Scope r1 = (toothpick.Scope) r1     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x0020
            r2 = r1
            goto L_0x002a
        L_0x0020:
            if (r4 == 0) goto L_0x002a
            r0.put(r3, r2)     // Catch:{ all -> 0x002c }
            toothpick.configuration.Configuration r3 = toothpick.configuration.ConfigurationHolder.configuration     // Catch:{ all -> 0x002c }
            r3.checkMultipleRootScopes(r2)     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r2
        L_0x002c:
            r3 = move-exception
            goto L_0x0036
        L_0x002e:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "null scope names are not allowed."
            r3.<init>(r4)     // Catch:{ all -> 0x002c }
            throw r3     // Catch:{ all -> 0x002c }
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: toothpick.Toothpick.openScope(java.lang.Object, boolean):toothpick.Scope");
    }

    public static void closeScope(Object obj) {
        ConcurrentHashMap<Object, Scope> concurrentHashMap = ROOT_SCOPES;
        synchronized (concurrentHashMap) {
            ScopeNode scopeNode = (ScopeNode) MAP_KEY_TO_SCOPE.remove(obj);
            if (scopeNode != null) {
                ScopeNode parentScope = scopeNode.getParentScope();
                if (parentScope != null) {
                    parentScope.removeChild(scopeNode);
                } else {
                    ConfigurationHolder.configuration.onScopeForestReset();
                    concurrentHashMap.remove(obj);
                }
                removeScopeAndChildrenFromMap(scopeNode);
            }
        }
    }

    public static void reset() {
        Iterator<T> it = Collections.list(MAP_KEY_TO_SCOPE.keys()).iterator();
        while (it.hasNext()) {
            closeScope(it.next());
        }
        ConfigurationHolder.configuration.onScopeForestReset();
        ScopeImpl.resetUnScopedProviders();
    }

    public static void reset(Scope scope) {
        ((ScopeNode) scope).reset();
    }

    public static void release(Scope scope) {
        ((ScopeNode) scope).release();
    }

    public static void inject(Object obj, Scope scope) {
        injector.inject(obj, scope);
    }

    private static void removeScopeAndChildrenFromMap(ScopeNode scopeNode) {
        MAP_KEY_TO_SCOPE.remove(scopeNode.getName());
        scopeNode.close();
        for (ScopeNode removeScopeAndChildrenFromMap : scopeNode.childrenScopes.values()) {
            removeScopeAndChildrenFromMap(removeScopeAndChildrenFromMap);
        }
    }

    public static void setConfiguration(Configuration configuration) {
        ConfigurationHolder.configuration = configuration;
    }

    static int getScopeNamesSize() {
        return MAP_KEY_TO_SCOPE.size();
    }
}
