package toothpick.util;

import java.util.Iterator;

public interface ReusableIterator<E> extends Iterator<E> {
    void rewind();
}
