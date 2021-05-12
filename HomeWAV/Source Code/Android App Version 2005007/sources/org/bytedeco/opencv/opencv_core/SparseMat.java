package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv")
@NoOffset
public class SparseMat extends Pointer {
    public static final int HASH_BIT = Integer.MIN_VALUE;
    public static final int HASH_SCALE = 1540483477;
    public static final int MAGIC_VAL = 1123876864;
    public static final int MAX_DIM = 32;

    private native void allocate();

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2);

    private native void allocate(int i, @Const IntPointer intPointer, int i2);

    private native void allocate(int i, @Const int[] iArr, int i2);

    private native void allocate(@ByRef @Const Mat mat);

    private native void allocate(@ByRef @Const SparseMat sparseMat);

    private native void allocateArray(long j);

    public native void addref();

    public native void assignTo(@ByRef SparseMat sparseMat);

    public native void assignTo(@ByRef SparseMat sparseMat, int i);

    @ByVal
    public native SparseMatIterator begin();

    public native int channels();

    public native void clear();

    @ByVal
    public native SparseMat clone();

    public native void convertTo(@ByRef Mat mat, int i);

    public native void convertTo(@ByRef Mat mat, int i, double d, double d2);

    public native void convertTo(@ByRef SparseMat sparseMat, int i);

    public native void convertTo(@ByRef SparseMat sparseMat, int i, double d);

    public native void copyTo(@ByRef Mat mat);

    public native void copyTo(@ByRef SparseMat sparseMat);

    public native void create(int i, @Const IntBuffer intBuffer, int i2);

    public native void create(int i, @Const IntPointer intPointer, int i2);

    public native void create(int i, @Const int[] iArr, int i2);

    public native int depth();

    public native int dims();

    @Cast({"size_t"})
    public native long elemSize();

    @Cast({"size_t"})
    public native long elemSize1();

    @ByVal
    public native SparseMatIterator end();

    public native void erase(int i, int i2);

    public native void erase(int i, int i2, int i3);

    public native void erase(int i, int i2, int i3, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native void erase(int i, int i2, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native void erase(@Const IntBuffer intBuffer);

    public native void erase(@Const IntBuffer intBuffer, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native void erase(@Const IntPointer intPointer);

    public native void erase(@Const IntPointer intPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native void erase(@Const int[] iArr);

    public native void erase(@Const int[] iArr, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native int flags();

    public native SparseMat flags(int i);

    @Cast({"size_t"})
    public native long hash(int i);

    @Cast({"size_t"})
    public native long hash(int i, int i2);

    @Cast({"size_t"})
    public native long hash(int i, int i2, int i3);

    @Cast({"size_t"})
    public native long hash(@Const IntBuffer intBuffer);

    @Cast({"size_t"})
    public native long hash(@Const IntPointer intPointer);

    @Cast({"size_t"})
    public native long hash(@Const int[] iArr);

    public native Hdr hdr();

    public native SparseMat hdr(Hdr hdr);

    @Cast({"uchar*"})
    public native ByteBuffer newNode(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j);

    @Cast({"uchar*"})
    public native BytePointer newNode(@Const IntPointer intPointer, @Cast({"size_t"}) long j);

    @Cast({"uchar*"})
    public native byte[] newNode(@Const int[] iArr, @Cast({"size_t"}) long j);

    public native Node node(@Cast({"size_t"}) long j);

    @Cast({"size_t"})
    public native long nzcount();

    @Cast({"uchar*"})
    public native ByteBuffer ptr(@Const IntBuffer intBuffer, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native ByteBuffer ptr(@Const IntBuffer intBuffer, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2, int i3, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2, int i3, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"uchar*"})
    public native BytePointer ptr(@Const IntPointer intPointer, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native BytePointer ptr(@Const IntPointer intPointer, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"uchar*"})
    public native byte[] ptr(@Const int[] iArr, @Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native byte[] ptr(@Const int[] iArr, @Cast({"bool"}) boolean z, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @ByRef
    @Name({"operator ="})
    public native SparseMat put(@ByRef @Const Mat mat);

    @ByRef
    @Name({"operator ="})
    public native SparseMat put(@ByRef @Const SparseMat sparseMat);

    public native void release();

    public native void removeNode(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, @Cast({"size_t"}) long j3);

    public native void resizeHashTab(@Cast({"size_t"}) long j);

    public native int size(int i);

    @Const
    public native IntPointer size();

    public native int type();

    static {
        Loader.load();
    }

    public SparseMat(Pointer pointer) {
        super(pointer);
    }

    public SparseMat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SparseMat position(long j) {
        return (SparseMat) super.position(j);
    }

    public SparseMat getPointer(long j) {
        return new SparseMat(this).position(this.position + j);
    }

    @NoOffset
    public static class Hdr extends Pointer {
        private native void allocate(int i, @Const IntBuffer intBuffer, int i2);

        private native void allocate(int i, @Const IntPointer intPointer, int i2);

        private native void allocate(int i, @Const int[] iArr, int i2);

        public native void clear();

        public native int dims();

        public native Hdr dims(int i);

        @Cast({"size_t"})
        public native long freeList();

        public native Hdr freeList(long j);

        @Cast({"size_t*"})
        @StdVector
        public native SizeTPointer hashtab();

        public native Hdr hashtab(SizeTPointer sizeTPointer);

        @Cast({"size_t"})
        public native long nodeCount();

        public native Hdr nodeCount(long j);

        @Cast({"size_t"})
        public native long nodeSize();

        public native Hdr nodeSize(long j);

        @Cast({"uchar*"})
        @StdVector
        public native BytePointer pool();

        public native Hdr pool(BytePointer bytePointer);

        public native int refcount();

        public native Hdr refcount(int i);

        public native int size(int i);

        @MemberGetter
        public native IntPointer size();

        public native Hdr size(int i, int i2);

        public native int valueOffset();

        public native Hdr valueOffset(int i);

        static {
            Loader.load();
        }

        public Hdr(Pointer pointer) {
            super(pointer);
        }

        public Hdr(int i, @Const IntPointer intPointer, int i2) {
            super((Pointer) null);
            allocate(i, intPointer, i2);
        }

        public Hdr(int i, @Const IntBuffer intBuffer, int i2) {
            super((Pointer) null);
            allocate(i, intBuffer, i2);
        }

        public Hdr(int i, @Const int[] iArr, int i2) {
            super((Pointer) null);
            allocate(i, iArr, i2);
        }
    }

    public static class Node extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @Cast({"size_t"})
        public native long hashval();

        public native Node hashval(long j);

        public native int idx(int i);

        @MemberGetter
        public native IntPointer idx();

        public native Node idx(int i, int i2);

        @Cast({"size_t"})
        public native long next();

        public native Node next(long j);

        static {
            Loader.load();
        }

        public Node() {
            super((Pointer) null);
            allocate();
        }

        public Node(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Node(Pointer pointer) {
            super(pointer);
        }

        public Node position(long j) {
            return (Node) super.position(j);
        }

        public Node getPointer(long j) {
            return new Node((Pointer) this).position(this.position + j);
        }
    }

    public SparseMat() {
        super((Pointer) null);
        allocate();
    }

    public SparseMat(int i, @Const IntPointer intPointer, int i2) {
        super((Pointer) null);
        allocate(i, intPointer, i2);
    }

    public SparseMat(int i, @Const IntBuffer intBuffer, int i2) {
        super((Pointer) null);
        allocate(i, intBuffer, i2);
    }

    public SparseMat(int i, @Const int[] iArr, int i2) {
        super((Pointer) null);
        allocate(i, iArr, i2);
    }

    public SparseMat(@ByRef @Const SparseMat sparseMat) {
        super((Pointer) null);
        allocate(sparseMat);
    }

    public SparseMat(@ByRef @Const Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }
}
