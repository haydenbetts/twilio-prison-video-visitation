package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import org.bytedeco.javacpp.Pointer;

public abstract class Indexer implements AutoCloseable {
    protected final Index index;
    protected Indexable indexable;
    @Deprecated
    protected long[] sizes;
    @Deprecated
    protected long[] strides;

    public Object array() {
        return null;
    }

    public Buffer buffer() {
        return null;
    }

    public abstract double getDouble(long... jArr);

    public Pointer pointer() {
        return null;
    }

    public abstract Indexer putDouble(long[] jArr, double d);

    public abstract <I extends Indexer> I reindex(Index index2);

    public abstract void release();

    public void close() {
        release();
    }

    protected Indexer(Index index2) {
        this.index = index2;
        this.sizes = index2.sizes();
        if (index2 instanceof StrideIndex) {
            this.strides = ((StrideIndex) index2).strides();
        }
    }

    protected Indexer(long[] jArr, long[] jArr2) {
        this(Index.create(jArr, jArr2));
    }

    public int rank() {
        return this.index.rank();
    }

    public long[] sizes() {
        return this.index.sizes();
    }

    @Deprecated
    public long[] strides() {
        return this.strides;
    }

    public long size(int i) {
        return this.index.size(i);
    }

    @Deprecated
    public long stride(int i) {
        long[] jArr = this.strides;
        if (jArr != null) {
            return jArr[i];
        }
        return -1;
    }

    @Deprecated
    public long rows() {
        long[] jArr = this.sizes;
        if (jArr.length <= 0 || jArr.length >= 4) {
            return -1;
        }
        return jArr[0];
    }

    @Deprecated
    public long cols() {
        long[] jArr = this.sizes;
        if (jArr.length <= 1 || jArr.length >= 4) {
            return -1;
        }
        return jArr[1];
    }

    @Deprecated
    public long width() {
        long[] jArr = this.sizes;
        if (jArr.length <= 1 || jArr.length >= 4) {
            return -1;
        }
        return jArr[1];
    }

    @Deprecated
    public long height() {
        long[] jArr = this.sizes;
        if (jArr.length <= 0 || jArr.length >= 4) {
            return -1;
        }
        return jArr[0];
    }

    @Deprecated
    public long channels() {
        long[] jArr = this.sizes;
        if (jArr.length <= 2 || jArr.length >= 4) {
            return -1;
        }
        return jArr[2];
    }

    protected static final long checkIndex(long j, long j2) {
        if (j >= 0 && j < j2) {
            return j;
        }
        throw new IndexOutOfBoundsException(Long.toString(j));
    }

    @Deprecated
    public static long[] strides(long... jArr) {
        return StrideIndex.defaultStrides(jArr);
    }

    public long index(long j) {
        return this.index.index(j);
    }

    public long index(long j, long j2) {
        return this.index.index(j, j2);
    }

    public long index(long j, long j2, long j3) {
        return this.index.index(j, j2, j3);
    }

    public long index(long... jArr) {
        return this.index.index(jArr);
    }

    public Indexable indexable() {
        return this.indexable;
    }

    public Indexer indexable(Indexable indexable2) {
        this.indexable = indexable2;
        return this;
    }

    public String toString() {
        int i;
        long[] jArr = this.sizes;
        long j = jArr.length > 0 ? jArr[0] : 1;
        long j2 = jArr.length > 1 ? jArr[1] : 1;
        long j3 = jArr.length > 2 ? jArr[2] : 1;
        StringBuilder sb = new StringBuilder(j > 1 ? "\n[ " : "[ ");
        int i2 = 0;
        while (true) {
            long j4 = (long) i2;
            if (j4 < j) {
                int i3 = 0;
                while (true) {
                    long j5 = (long) i3;
                    if (j5 >= j2) {
                        break;
                    }
                    int i4 = (j3 > 1 ? 1 : (j3 == 1 ? 0 : -1));
                    if (i4 > 0) {
                        sb.append("(");
                    }
                    long j6 = j;
                    int i5 = 0;
                    while (true) {
                        long j7 = (long) i5;
                        i = i2;
                        if (j7 >= j3) {
                            break;
                        }
                        int i6 = i3;
                        long j8 = j4;
                        sb.append((float) getDouble(j4, j5, j7));
                        if (j7 < j3 - 1) {
                            sb.append(", ");
                        }
                        i5++;
                        i2 = i;
                        i3 = i6;
                        j4 = j8;
                    }
                    int i7 = i3;
                    long j9 = j4;
                    if (i4 > 0) {
                        sb.append(")");
                    }
                    if (j5 < j2 - 1) {
                        sb.append(", ");
                    }
                    i3 = i7 + 1;
                    j = j6;
                    i2 = i;
                    j4 = j9;
                }
                int i8 = i2;
                long j10 = j;
                if (j4 < j10 - 1) {
                    sb.append("\n  ");
                }
                i2 = i8 + 1;
                j = j10;
            } else {
                sb.append(" ]");
                return sb.toString();
            }
        }
    }
}
