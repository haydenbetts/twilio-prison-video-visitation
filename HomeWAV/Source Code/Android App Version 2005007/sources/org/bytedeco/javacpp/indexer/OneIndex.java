package org.bytedeco.javacpp.indexer;

public class OneIndex extends Index {
    public long index(long j) {
        return j;
    }

    public OneIndex(long j) {
        super(j);
    }

    public long index(long j, long j2) {
        throw new UnsupportedOperationException();
    }

    public long index(long j, long j2, long j3) {
        throw new UnsupportedOperationException();
    }

    public long index(long... jArr) {
        if (jArr.length == 1) {
            return jArr[0];
        }
        throw new UnsupportedOperationException();
    }
}
