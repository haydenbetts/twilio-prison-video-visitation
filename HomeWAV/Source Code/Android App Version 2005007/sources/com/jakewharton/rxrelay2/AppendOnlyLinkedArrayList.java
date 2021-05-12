package com.jakewharton.rxrelay2;

import io.reactivex.functions.Predicate;

class AppendOnlyLinkedArrayList<T> {
    private final int capacity;
    private final Object[] head;
    private int offset;
    private Object[] tail;

    public interface NonThrowingPredicate<T> extends Predicate<T> {
        boolean test(T t);
    }

    AppendOnlyLinkedArrayList(int i) {
        this.capacity = i;
        Object[] objArr = new Object[(i + 1)];
        this.head = objArr;
        this.tail = objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Object[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void add(T r4) {
        /*
            r3 = this;
            int r0 = r3.capacity
            int r1 = r3.offset
            if (r1 != r0) goto L_0x0011
            int r1 = r0 + 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Object[] r2 = r3.tail
            r2[r0] = r1
            r3.tail = r1
            r1 = 0
        L_0x0011:
            java.lang.Object[] r0 = r3.tail
            r0[r1] = r4
            int r1 = r1 + 1
            r3.offset = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.rxrelay2.AppendOnlyLinkedArrayList.add(java.lang.Object):void");
    }

    /* access modifiers changed from: package-private */
    public void forEachWhile(NonThrowingPredicate<? super T> nonThrowingPredicate) {
        int i = this.capacity;
        for (Object[] objArr = this.head; objArr != null; objArr = objArr[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null || nonThrowingPredicate.test(objArr2)) {
                    break;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean accept(Relay<? super T> relay) {
        Object[] objArr = this.head;
        int i = this.capacity;
        while (true) {
            if (objArr == null) {
                return false;
            }
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null) {
                    break;
                }
                relay.accept(objArr2);
            }
            objArr = objArr[i];
        }
    }
}
