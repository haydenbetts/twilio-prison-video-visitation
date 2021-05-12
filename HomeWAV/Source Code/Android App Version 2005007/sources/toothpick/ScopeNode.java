package toothpick;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Scope;
import javax.inject.Singleton;

public abstract class ScopeNode implements Scope {
    protected final ConcurrentHashMap<Object, ScopeNode> childrenScopes = new ConcurrentHashMap<>();
    protected boolean isOpen = true;
    protected Object name;
    protected final List<ScopeNode> parentScopes = new CopyOnWriteArrayList();
    protected final Set<Class<? extends Annotation>> scopeAnnotationClasses = new CopyOnWriteArraySet();

    public ScopeNode(Object obj) {
        if (obj != null) {
            this.name = obj;
            bindScopeAnnotationIfNameIsScopeAnnotation();
            return;
        }
        throw new IllegalArgumentException("A scope can't have a null name");
    }

    public Object getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Scope)) {
            return false;
        }
        Object obj2 = this.name;
        Object obj3 = ((ScopeNode) obj).name;
        if (obj2 != null) {
            if (!obj2.equals(obj3)) {
                return false;
            }
            return true;
        } else if (obj3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public ScopeNode getParentScope() {
        Iterator<ScopeNode> it = this.parentScopes.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public <A extends Annotation> ScopeNode getParentScope(Class<A> cls) {
        checkIsAnnotationScope(cls);
        if (cls == Singleton.class) {
            return getRootScope();
        }
        for (ScopeNode scopeNode = this; scopeNode != null; scopeNode = scopeNode.getParentScope()) {
            if (scopeNode.isScopeAnnotationSupported(cls)) {
                return scopeNode;
            }
        }
        throw new IllegalStateException(String.format("There is no parent scope of %s that supports the scope scopeAnnotationClass %s", new Object[]{this.name, cls.getName()}));
    }

    public ScopeNode getRootScope() {
        if (this.parentScopes.isEmpty()) {
            return this;
        }
        List<ScopeNode> list = this.parentScopes;
        return list.get(list.size() - 1);
    }

    public Scope supportScopeAnnotation(Class<? extends Annotation> cls) {
        checkIsAnnotationScope(cls);
        if (cls != Singleton.class) {
            this.scopeAnnotationClasses.add(cls);
            return this;
        }
        throw new IllegalArgumentException(String.format("The annotation @Singleton is already supported by root scopes. It can't be supported programmatically.", new Object[0]));
    }

    public boolean isScopeAnnotationSupported(Class<? extends Annotation> cls) {
        if (cls == Singleton.class) {
            return this.parentScopes.isEmpty();
        }
        return this.scopeAnnotationClasses.contains(cls);
    }

    /* access modifiers changed from: protected */
    public void reset() {
        this.scopeAnnotationClasses.clear();
        this.isOpen = true;
        bindScopeAnnotationIfNameIsScopeAnnotation();
    }

    /* access modifiers changed from: package-private */
    public Collection<ScopeNode> getChildrenScopes() {
        return this.childrenScopes.values();
    }

    /* access modifiers changed from: package-private */
    public ScopeNode addChild(ScopeNode scopeNode) {
        if (scopeNode != null) {
            ScopeNode parentScope = scopeNode.getParentScope();
            if (parentScope == this) {
                return scopeNode;
            }
            if (parentScope == null) {
                ScopeNode putIfAbsent = this.childrenScopes.putIfAbsent(scopeNode.getName(), scopeNode);
                if (putIfAbsent != null) {
                    return putIfAbsent;
                }
                scopeNode.parentScopes.add(this);
                scopeNode.parentScopes.addAll(this.parentScopes);
                return scopeNode;
            }
            throw new IllegalStateException(String.format("Scope %s already has a parent: %s which is not %s", new Object[]{scopeNode, parentScope, this}));
        }
        throw new IllegalArgumentException("Child must be non null.");
    }

    /* access modifiers changed from: package-private */
    public void removeChild(ScopeNode scopeNode) {
        if (scopeNode != null) {
            ScopeNode parentScope = scopeNode.getParentScope();
            if (parentScope == null) {
                throw new IllegalStateException(String.format("The scope has no parent: %s", new Object[]{scopeNode.getName()}));
            } else if (parentScope == this) {
                this.childrenScopes.remove(scopeNode.getName());
                scopeNode.parentScopes.clear();
            } else {
                throw new IllegalStateException(String.format("The scope %s has parent: different of this: %s", new Object[]{scopeNode.getName(), parentScope.getName(), getName()}));
            }
        } else {
            throw new IllegalArgumentException("Child must be non null.");
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        this.isOpen = false;
    }

    /* access modifiers changed from: package-private */
    public List<Object> getParentScopesNames() {
        ArrayList arrayList = new ArrayList();
        for (ScopeNode name2 : this.parentScopes) {
            arrayList.add(name2.getName());
        }
        return arrayList;
    }

    private void bindScopeAnnotationIfNameIsScopeAnnotation() {
        if (this.name.getClass() == Class.class && Annotation.class.isAssignableFrom((Class) this.name) && isScopeAnnotationClass((Class) this.name)) {
            supportScopeAnnotation((Class) this.name);
        }
    }

    private void checkIsAnnotationScope(Class<? extends Annotation> cls) {
        if (!isScopeAnnotationClass(cls)) {
            throw new IllegalArgumentException(String.format("The annotation %s is not a scope annotation, it is not qualified by javax.inject.Scope.", new Object[]{cls.getName()}));
        }
    }

    private boolean isScopeAnnotationClass(Class<? extends Annotation> cls) {
        return cls.isAnnotationPresent(Scope.class);
    }
}
