package org.bytedeco.javacpp.indexer;

public class CharArrayIndexer extends CharIndexer {
    protected char[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public CharArrayIndexer(char[] cArr) {
        this(cArr, Index.create((long) cArr.length));
    }

    public CharArrayIndexer(char[] cArr, long... jArr) {
        this(cArr, Index.create(jArr));
    }

    public CharArrayIndexer(char[] cArr, long[] jArr, long[] jArr2) {
        this(cArr, Index.create(jArr, jArr2));
    }

    public CharArrayIndexer(char[] cArr, Index index) {
        super(index);
        this.array = cArr;
    }

    public char[] array() {
        return this.array;
    }

    public CharIndexer reindex(Index index) {
        return new CharArrayIndexer(this.array, index);
    }

    public char get(long j) {
        return this.array[(int) index(j)];
    }

    public CharIndexer get(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public char get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public CharIndexer get(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public char get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public char get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public CharIndexer get(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public CharIndexer put(long j, char c) {
        this.array[(int) index(j)] = c;
        return this;
    }

    public CharIndexer put(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = cArr[i + i3];
        }
        return this;
    }

    public CharIndexer put(long j, long j2, char c) {
        this.array[(int) index(j, j2)] = c;
        return this;
    }

    public CharIndexer put(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = cArr[i + i3];
        }
        return this;
    }

    public CharIndexer put(long j, long j2, long j3, char c) {
        this.array[(int) index(j, j2, j3)] = c;
        return this;
    }

    public CharIndexer put(long[] jArr, char c) {
        this.array[(int) index(jArr)] = c;
        return this;
    }

    public CharIndexer put(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = cArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
