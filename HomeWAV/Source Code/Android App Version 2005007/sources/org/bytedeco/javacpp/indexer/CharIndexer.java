package org.bytedeco.javacpp.indexer;

import java.nio.CharBuffer;
import org.bytedeco.javacpp.CharPointer;

public abstract class CharIndexer extends Indexer {
    public static final int VALUE_BYTES = 2;

    public abstract char get(long j);

    public abstract char get(long j, long j2);

    public abstract char get(long j, long j2, long j3);

    public abstract char get(long... jArr);

    public abstract CharIndexer get(long j, long j2, char[] cArr, int i, int i2);

    public abstract CharIndexer get(long j, char[] cArr, int i, int i2);

    public abstract CharIndexer get(long[] jArr, char[] cArr, int i, int i2);

    public abstract CharIndexer put(long j, char c);

    public abstract CharIndexer put(long j, long j2, char c);

    public abstract CharIndexer put(long j, long j2, long j3, char c);

    public abstract CharIndexer put(long j, long j2, char[] cArr, int i, int i2);

    public abstract CharIndexer put(long j, char[] cArr, int i, int i2);

    public abstract CharIndexer put(long[] jArr, char c);

    public abstract CharIndexer put(long[] jArr, char[] cArr, int i, int i2);

    protected CharIndexer(Index index) {
        super(index);
    }

    protected CharIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static CharIndexer create(char[] cArr) {
        return new CharArrayIndexer(cArr);
    }

    public static CharIndexer create(CharBuffer charBuffer) {
        return new CharBufferIndexer(charBuffer);
    }

    public static CharIndexer create(CharPointer charPointer) {
        return new CharRawIndexer(charPointer);
    }

    public static CharIndexer create(char[] cArr, Index index) {
        return new CharArrayIndexer(cArr, index);
    }

    public static CharIndexer create(CharBuffer charBuffer, Index index) {
        return new CharBufferIndexer(charBuffer, index);
    }

    public static CharIndexer create(CharPointer charPointer, Index index) {
        return new CharRawIndexer(charPointer, index);
    }

    public static CharIndexer create(char[] cArr, long... jArr) {
        return new CharArrayIndexer(cArr, jArr);
    }

    public static CharIndexer create(CharBuffer charBuffer, long... jArr) {
        return new CharBufferIndexer(charBuffer, jArr);
    }

    public static CharIndexer create(CharPointer charPointer, long... jArr) {
        return new CharRawIndexer(charPointer, jArr);
    }

    public static CharIndexer create(char[] cArr, long[] jArr, long[] jArr2) {
        return new CharArrayIndexer(cArr, jArr, jArr2);
    }

    public static CharIndexer create(CharBuffer charBuffer, long[] jArr, long[] jArr2) {
        return new CharBufferIndexer(charBuffer, jArr, jArr2);
    }

    public static CharIndexer create(CharPointer charPointer, long[] jArr, long[] jArr2) {
        return new CharRawIndexer(charPointer, jArr, jArr2);
    }

    public static CharIndexer create(CharPointer charPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(charPointer, Index.create(jArr, jArr2), z);
    }

    public static CharIndexer create(CharPointer charPointer, Index index, boolean z) {
        if (!z) {
            final long position = charPointer.position();
            char[] cArr = new char[((int) Math.min(charPointer.limit() - position, 2147483647L))];
            charPointer.get(cArr);
            final CharPointer charPointer2 = charPointer;
            return new CharArrayIndexer(cArr, index) {
                public void release() {
                    charPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new CharRawIndexer(charPointer, index);
        } else {
            return new CharBufferIndexer(charPointer.asBuffer(), index);
        }
    }

    public CharIndexer get(long j, char[] cArr) {
        return get(j, cArr, 0, cArr.length);
    }

    public CharIndexer get(long j, long j2, char[] cArr) {
        return get(j, j2, cArr, 0, cArr.length);
    }

    public CharIndexer get(long[] jArr, char[] cArr) {
        return get(jArr, cArr, 0, cArr.length);
    }

    public CharIndexer put(long j, char... cArr) {
        return put(j, cArr, 0, cArr.length);
    }

    public CharIndexer put(long j, long j2, char... cArr) {
        return put(j, j2, cArr, 0, cArr.length);
    }

    public CharIndexer put(long[] jArr, char... cArr) {
        return put(jArr, cArr, 0, cArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public CharIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (char) ((int) d));
    }
}
