package org.bytedeco.opencv.opencv_dnn;

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
import org.bytedeco.opencv.presets.opencv_dnn;

@Properties(inherit = {opencv_dnn.class})
@Name({"std::vector<std::vector<cv::dnn::MatShape> >"})
public class MatShapeVectorVector extends Pointer {
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
    public native MatShapeVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef MatShapeVector matShapeVector);

    public native MatShapeVectorVector put(@Cast({"size_t"}) long j, MatShapeVector matShapeVector);

    @ByRef
    @Name({"operator ="})
    public native MatShapeVectorVector put(@ByRef MatShapeVectorVector matShapeVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public MatShapeVectorVector(Pointer pointer) {
        super(pointer);
    }

    public MatShapeVectorVector(MatShapeVector matShapeVector) {
        this(1);
        put(0, matShapeVector);
    }

    public MatShapeVectorVector(MatShapeVector... matShapeVectorArr) {
        this((long) matShapeVectorArr.length);
        put(matShapeVectorArr);
    }

    public MatShapeVectorVector() {
        allocate();
    }

    public MatShapeVectorVector(long j) {
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
        public native MatShapeVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public MatShapeVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        MatShapeVector[] matShapeVectorArr = new MatShapeVector[size];
        for (int i = 0; i < size; i++) {
            matShapeVectorArr[i] = get((long) i);
        }
        return matShapeVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public MatShapeVector pop_back() {
        long size = size() - 1;
        MatShapeVector matShapeVector = get(size);
        resize(size);
        return matShapeVector;
    }

    public MatShapeVectorVector push_back(MatShapeVector matShapeVector) {
        long size = size();
        resize(1 + size);
        return put(size, matShapeVector);
    }

    public MatShapeVectorVector put(MatShapeVector matShapeVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, matShapeVector);
    }

    public MatShapeVectorVector put(MatShapeVector... matShapeVectorArr) {
        if (size() != ((long) matShapeVectorArr.length)) {
            resize((long) matShapeVectorArr.length);
        }
        for (int i = 0; i < matShapeVectorArr.length; i++) {
            put((long) i, matShapeVectorArr[i]);
        }
        return this;
    }
}
