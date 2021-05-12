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
@Name({"std::vector<std::vector<cv::Mat> >"})
public class MatVectorVector extends Pointer {
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
    public native MatVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef MatVector matVector);

    public native MatVectorVector put(@Cast({"size_t"}) long j, MatVector matVector);

    @ByRef
    @Name({"operator ="})
    public native MatVectorVector put(@ByRef MatVectorVector matVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public MatVectorVector(Pointer pointer) {
        super(pointer);
    }

    public MatVectorVector(MatVector matVector) {
        this(1);
        put(0, matVector);
    }

    public MatVectorVector(MatVector... matVectorArr) {
        this((long) matVectorArr.length);
        put(matVectorArr);
    }

    public MatVectorVector() {
        allocate();
    }

    public MatVectorVector(long j) {
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
        public native MatVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public MatVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        MatVector[] matVectorArr = new MatVector[size];
        for (int i = 0; i < size; i++) {
            matVectorArr[i] = get((long) i);
        }
        return matVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public MatVector pop_back() {
        long size = size() - 1;
        MatVector matVector = get(size);
        resize(size);
        return matVector;
    }

    public MatVectorVector push_back(MatVector matVector) {
        long size = size();
        resize(1 + size);
        return put(size, matVector);
    }

    public MatVectorVector put(MatVector matVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, matVector);
    }

    public MatVectorVector put(MatVector... matVectorArr) {
        if (size() != ((long) matVectorArr.length)) {
            resize((long) matVectorArr.length);
        }
        for (int i = 0; i < matVectorArr.length; i++) {
            put((long) i, matVectorArr[i]);
        }
        return this;
    }
}
