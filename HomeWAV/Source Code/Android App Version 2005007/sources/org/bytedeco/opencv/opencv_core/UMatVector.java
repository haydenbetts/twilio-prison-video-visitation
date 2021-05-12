package org.bytedeco.opencv.opencv_core;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<cv::UMat>"})
public class UMatVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @ByRef
    @Index(function = "at")
    public native UMat get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef UMat uMat);

    public native UMatVector put(@Cast({"size_t"}) long j, UMat uMat);

    @ByRef
    @Name({"operator ="})
    public native UMatVector put(@ByRef UMatVector uMatVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public UMatVector(Pointer pointer) {
        super(pointer);
    }

    public UMatVector(UMat uMat) {
        this(1);
        put(0, uMat);
    }

    public UMatVector(UMat... uMatArr) {
        this((long) uMatArr.length);
        put(uMatArr);
    }

    public UMatVector() {
        allocate();
    }

    public UMatVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    @Name({"iterator"})
    @NoOffset
    public static class Iterator extends Pointer {
        @Name({"operator =="})
        public native boolean equals(@ByRef Iterator iterator);

        @ByRef
        @Const
        @Name({"operator *"})
        public native UMat get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public UMat[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        UMat[] uMatArr = new UMat[size];
        for (int i = 0; i < size; i++) {
            uMatArr[i] = get((long) i);
        }
        return uMatArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public UMat pop_back() {
        long size = size() - 1;
        UMat uMat = get(size);
        resize(size);
        return uMat;
    }

    public UMatVector push_back(UMat uMat) {
        long size = size();
        resize(1 + size);
        return put(size, uMat);
    }

    public UMatVector put(UMat uMat) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, uMat);
    }

    public UMatVector put(UMat... uMatArr) {
        if (size() != ((long) uMatArr.length)) {
            resize((long) uMatArr.length);
        }
        for (int i = 0; i < uMatArr.length; i++) {
            put((long) i, uMatArr[i]);
        }
        return this;
    }
}
