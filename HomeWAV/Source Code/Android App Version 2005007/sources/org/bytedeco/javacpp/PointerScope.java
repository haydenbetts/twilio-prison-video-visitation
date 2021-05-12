package org.bytedeco.javacpp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import org.bytedeco.javacpp.tools.Logger;

public class PointerScope implements AutoCloseable {
    private static final Logger logger = Logger.create(PointerScope.class);
    static final ThreadLocal<Deque<PointerScope>> scopeStack = new ThreadLocal<Deque<PointerScope>>() {
        /* access modifiers changed from: protected */
        public Deque initialValue() {
            return new ArrayDeque();
        }
    };
    boolean extend = false;
    Class<? extends Pointer>[] forClasses = null;
    Deque<Pointer> pointerStack = new ArrayDeque();

    public static PointerScope getInnerScope() {
        return (PointerScope) scopeStack.get().peek();
    }

    public static Iterator<PointerScope> getScopeIterator() {
        return scopeStack.get().iterator();
    }

    public PointerScope(Class<? extends Pointer>... clsArr) {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Opening " + this);
        }
        this.forClasses = clsArr;
        scopeStack.get().push(this);
    }

    public Class<? extends Pointer>[] forClasses() {
        return this.forClasses;
    }

    public PointerScope attach(Pointer pointer) {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Attaching " + pointer + " to " + this);
        }
        Class<? extends Pointer>[] clsArr = this.forClasses;
        if (clsArr != null && clsArr.length > 0) {
            int length = clsArr.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i < length) {
                    Class<? extends Pointer> cls = clsArr[i];
                    if (cls != null && cls.isInstance(pointer)) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (!z) {
                throw new IllegalArgumentException(pointer + " is not an instance of a class in forClasses: " + Arrays.toString(this.forClasses));
            }
        }
        this.pointerStack.push(pointer);
        pointer.retainReference();
        return this;
    }

    public PointerScope detach(Pointer pointer) {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Detaching " + pointer + " from " + this);
        }
        this.pointerStack.remove(pointer);
        pointer.releaseReference();
        return this;
    }

    public PointerScope extend() {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Extending " + this);
        }
        this.extend = true;
        return this;
    }

    public void close() {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Closing " + this);
        }
        if (this.extend) {
            this.extend = false;
        } else {
            while (this.pointerStack.size() > 0) {
                this.pointerStack.pop().releaseReference();
            }
        }
        scopeStack.get().remove(this);
    }

    public void deallocate() {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Deallocating " + this);
        }
        while (this.pointerStack.size() > 0) {
            this.pointerStack.pop().deallocate();
        }
    }
}
