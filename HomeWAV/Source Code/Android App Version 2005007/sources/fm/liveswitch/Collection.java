package fm.liveswitch;

import fm.liveswitch.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Collection<T, TCollection extends Collection<T, TCollection>> {
    private ArrayList<T> __values = new ArrayList<>();
    private T[] __valuesCache = arrayFromList(this.__values);
    private Object __valuesLock = new Object();

    /* access modifiers changed from: protected */
    public void addSuccess(T t) {
    }

    /* access modifiers changed from: protected */
    public void addSuccessNoLock(T t) {
    }

    /* access modifiers changed from: protected */
    public abstract T[] arrayFromList(ArrayList<T> arrayList);

    /* access modifiers changed from: protected */
    public abstract TCollection createCollection();

    /* access modifiers changed from: protected */
    public void removeSuccess(T t) {
    }

    /* access modifiers changed from: protected */
    public void removeSuccessNoLock(T t) {
    }

    public boolean add(T t) {
        boolean z = false;
        if (t != null) {
            synchronized (this.__valuesLock) {
                if (!this.__values.contains(t)) {
                    z = true;
                    this.__values.add(t);
                    this.__valuesCache = arrayFromList(this.__values);
                    addSuccess(t);
                }
            }
            if (z) {
                addSuccessNoLock(t);
            }
        }
        return z;
    }

    public void addMany(T[] tArr) {
        if (tArr != null) {
            ArrayList arrayList = new ArrayList();
            synchronized (this.__valuesLock) {
                for (T t : tArr) {
                    if (!this.__values.contains(t)) {
                        this.__values.add(t);
                        this.__valuesCache = arrayFromList(this.__values);
                        arrayList.add(t);
                        addSuccess(t);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                addSuccessNoLock(it.next());
            }
        }
    }

    public boolean any() {
        return any((IFunction1) null);
    }

    public boolean any(IFunction1<T, Boolean> iFunction1) {
        synchronized (this.__valuesLock) {
            Iterator<T> it = this.__values.iterator();
            while (it.hasNext()) {
                T next = it.next();
                if (iFunction1 != null) {
                    if (iFunction1.invoke(next).booleanValue()) {
                    }
                }
                return true;
            }
            return false;
        }
    }

    public boolean contains(T t) {
        boolean contains;
        if (t == null) {
            return false;
        }
        synchronized (this.__valuesLock) {
            contains = this.__values.contains(t);
        }
        return contains;
    }

    public T first() {
        T firstOrDefault = firstOrDefault();
        if (firstOrDefault != null) {
            return firstOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain any values."));
    }

    public T first(IFunction1<T, Boolean> iFunction1) {
        T firstOrDefault = firstOrDefault(iFunction1);
        if (firstOrDefault != null) {
            return firstOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain any matching values."));
    }

    public T firstOrDefault() {
        return firstOrDefault((IFunction1) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T firstOrDefault(fm.liveswitch.IFunction1<T, java.lang.Boolean> r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__valuesLock
            monitor-enter(r0)
            r1 = 0
        L_0x0004:
            java.util.ArrayList<T> r2 = r4.__values     // Catch:{ all -> 0x002d }
            int r2 = fm.liveswitch.ArrayListExtensions.getCount(r2)     // Catch:{ all -> 0x002d }
            if (r1 >= r2) goto L_0x002a
            java.util.ArrayList<T> r2 = r4.__values     // Catch:{ all -> 0x002d }
            java.util.ArrayList r2 = fm.liveswitch.ArrayListExtensions.getItem(r2)     // Catch:{ all -> 0x002d }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x002d }
            if (r5 != 0) goto L_0x0019
            goto L_0x0025
        L_0x0019:
            java.lang.Object r3 = r5.invoke(r2)     // Catch:{ all -> 0x002d }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x002d }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0027
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r2
        L_0x0027:
            int r1 = r1 + 1
            goto L_0x0004
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            r5 = 0
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Collection.firstOrDefault(fm.liveswitch.IFunction1):java.lang.Object");
    }

    public void forEach(IAction2<T, Integer> iAction2) {
        Object[] values = getValues();
        int length = values.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            iAction2.invoke(values[i], Integer.valueOf(i2));
            i++;
            i2++;
        }
    }

    public int getCount() {
        return ArrayListExtensions.getCount(this.__values);
    }

    public T getValue() {
        synchronized (this.__valuesLock) {
            if (ArrayListExtensions.getCount(this.__values) <= 0) {
                return null;
            }
            T t = ArrayListExtensions.getItem(this.__values).get(0);
            return t;
        }
    }

    public T[] getValues() {
        return this.__valuesCache;
    }

    public T last() {
        T lastOrDefault = lastOrDefault();
        if (lastOrDefault != null) {
            return lastOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain any values."));
    }

    public T last(IFunction1<T, Boolean> iFunction1) {
        T lastOrDefault = lastOrDefault(iFunction1);
        if (lastOrDefault != null) {
            return lastOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain any matching values."));
    }

    public T lastOrDefault() {
        return lastOrDefault((IFunction1) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T lastOrDefault(fm.liveswitch.IFunction1<T, java.lang.Boolean> r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__valuesLock
            monitor-enter(r0)
            java.util.ArrayList<T> r1 = r4.__values     // Catch:{ all -> 0x002f }
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x002f }
            int r1 = r1 + -1
        L_0x000b:
            r2 = -1
            if (r1 <= r2) goto L_0x002c
            java.util.ArrayList<T> r2 = r4.__values     // Catch:{ all -> 0x002f }
            java.util.ArrayList r2 = fm.liveswitch.ArrayListExtensions.getItem(r2)     // Catch:{ all -> 0x002f }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x002f }
            if (r5 != 0) goto L_0x001b
            goto L_0x0027
        L_0x001b:
            java.lang.Object r3 = r5.invoke(r2)     // Catch:{ all -> 0x002f }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x002f }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x0029
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r2
        L_0x0029:
            int r1 = r1 + -1
            goto L_0x000b
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            r5 = 0
            return r5
        L_0x002f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Collection.lastOrDefault(fm.liveswitch.IFunction1):java.lang.Object");
    }

    public boolean remove(T t) {
        boolean z = false;
        if (t != null) {
            synchronized (this.__valuesLock) {
                if (this.__values.remove(t)) {
                    this.__valuesCache = arrayFromList(this.__values);
                    z = true;
                    removeSuccess(t);
                }
            }
            if (z) {
                removeSuccessNoLock(t);
            }
        }
        return z;
    }

    public void removeAll() {
        Object[] arrayFromList;
        int i;
        synchronized (this.__valuesLock) {
            arrayFromList = arrayFromList(this.__values);
            ArrayList<T> arrayList = new ArrayList<>();
            this.__values = arrayList;
            this.__valuesCache = arrayFromList(arrayList);
            for (Object removeSuccess : arrayFromList) {
                removeSuccess(removeSuccess);
            }
        }
        for (Object removeSuccessNoLock : arrayFromList) {
            removeSuccessNoLock(removeSuccessNoLock);
        }
    }

    public T removeFirst() {
        synchronized (this.__valuesLock) {
            if (ArrayListExtensions.getCount(this.__values) <= 0) {
                return null;
            }
            T t = ArrayListExtensions.getItem(this.__values).get(0);
            remove(t);
            return t;
        }
    }

    public T removeFirst(IFunction1<T, Boolean> iFunction1) {
        synchronized (this.__valuesLock) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__values); i++) {
                T t = ArrayListExtensions.getItem(this.__values).get(i);
                if (iFunction1.invoke(t).booleanValue()) {
                    return t;
                }
            }
            return null;
        }
    }

    public T removeLast() {
        synchronized (this.__valuesLock) {
            if (ArrayListExtensions.getCount(this.__values) <= 0) {
                return null;
            }
            T t = ArrayListExtensions.getItem(this.__values).get(ArrayListExtensions.getCount(this.__values) - 1);
            remove(t);
            return t;
        }
    }

    public T removeLast(IFunction1<T, Boolean> iFunction1) {
        synchronized (this.__valuesLock) {
            for (int count = ArrayListExtensions.getCount(this.__values) - 1; count >= 0; count++) {
                T t = ArrayListExtensions.getItem(this.__values).get(count);
                if (iFunction1.invoke(t).booleanValue()) {
                    return t;
                }
            }
            return null;
        }
    }

    public void removeMany(T[] tArr) {
        if (tArr != null && ArrayExtensions.getLength((Object[]) tArr) > 0) {
            ArrayList arrayList = new ArrayList();
            synchronized (this.__valuesLock) {
                for (T t : tArr) {
                    if (this.__values.remove(t)) {
                        this.__valuesCache = arrayFromList(this.__values);
                        arrayList.add(t);
                        removeSuccess(t);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                removeSuccessNoLock(it.next());
            }
        }
    }

    public void replace(T[] tArr) {
        synchronized (this.__valuesLock) {
            this.__values.clear();
            if (tArr != null) {
                ArrayListExtensions.addRange(this.__values, tArr);
            }
            this.__valuesCache = arrayFromList(this.__values);
        }
    }

    public void setValue(T t) {
        synchronized (this.__valuesLock) {
            this.__values.clear();
            if (t != null) {
                this.__values.add(t);
            }
            this.__valuesCache = arrayFromList(this.__values);
        }
    }

    public void setValues(T[] tArr) {
        synchronized (this.__valuesLock) {
            this.__values.clear();
            if (tArr != null) {
                ArrayListExtensions.addRange(this.__values, tArr);
            }
            this.__valuesCache = arrayFromList(this.__values);
        }
    }

    public T single() {
        T singleOrDefault = singleOrDefault();
        if (singleOrDefault != null) {
            return singleOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain a single value."));
    }

    public T single(IFunction1<T, Boolean> iFunction1) {
        T singleOrDefault = singleOrDefault(iFunction1);
        if (singleOrDefault != null) {
            return singleOrDefault;
        }
        throw new RuntimeException(new Exception("The collection does not contain a single matching value."));
    }

    public T singleOrDefault() {
        return singleOrDefault((IFunction1) null);
    }

    public T singleOrDefault(IFunction1<T, Boolean> iFunction1) {
        T t;
        synchronized (this.__valuesLock) {
            int i = 0;
            t = null;
            boolean z = false;
            while (i < ArrayListExtensions.getCount(this.__values)) {
                T t2 = ArrayListExtensions.getItem(this.__values).get(i);
                if (iFunction1 != null) {
                    if (!iFunction1.invoke(t2).booleanValue()) {
                        continue;
                        i++;
                    }
                }
                if (!z) {
                    z = true;
                    t = t2;
                    i++;
                } else {
                    throw new RuntimeException(new Exception("Collection contains more than one matching element."));
                }
            }
        }
        return t;
    }

    public T[] toArray() {
        T[] arrayFromList;
        synchronized (this.__valuesLock) {
            arrayFromList = arrayFromList(this.__values);
        }
        return arrayFromList;
    }

    public T valueAt(int i) {
        T valueAtOrDefault = valueAtOrDefault(i);
        if (valueAtOrDefault != null) {
            return valueAtOrDefault;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Value does not exist at index {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(i)))));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T valueAtOrDefault(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__valuesLock
            monitor-enter(r0)
            java.util.ArrayList<T> r1 = r3.__values     // Catch:{ all -> 0x0028 }
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x0028 }
            if (r4 >= r1) goto L_0x0025
            r1 = 0
        L_0x000c:
            java.util.ArrayList<T> r2 = r3.__values     // Catch:{ all -> 0x0028 }
            int r2 = fm.liveswitch.ArrayListExtensions.getCount(r2)     // Catch:{ all -> 0x0028 }
            if (r1 >= r2) goto L_0x0025
            if (r1 != r4) goto L_0x0022
            java.util.ArrayList<T> r4 = r3.__values     // Catch:{ all -> 0x0028 }
            java.util.ArrayList r4 = fm.liveswitch.ArrayListExtensions.getItem(r4)     // Catch:{ all -> 0x0028 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return r4
        L_0x0022:
            int r1 = r1 + 1
            goto L_0x000c
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            r4 = 0
            return r4
        L_0x0028:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Collection.valueAtOrDefault(int):java.lang.Object");
    }

    public TCollection where(IFunction2<T, Integer, Boolean> iFunction2) {
        TCollection createCollection = createCollection();
        synchronized (this.__valuesLock) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__values); i++) {
                T t = ArrayListExtensions.getItem(this.__values).get(i);
                if (iFunction2 != null) {
                    if (!iFunction2.invoke(t, Integer.valueOf(i)).booleanValue()) {
                    }
                }
                createCollection.add(t);
            }
        }
        return createCollection;
    }
}
