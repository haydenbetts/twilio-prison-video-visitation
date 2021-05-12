package org.bytedeco.javacpp.tools;

import java.util.Iterator;
import java.util.LinkedHashMap;

class IndexedSet<E> extends LinkedHashMap<E, Integer> implements Iterable<E> {
    IndexedSet() {
    }

    public int index(E e) {
        Integer num = (Integer) get(e);
        if (num == null) {
            num = Integer.valueOf(size());
            put(e, num);
        }
        return num.intValue();
    }

    public Iterator<E> iterator() {
        return keySet().iterator();
    }
}
