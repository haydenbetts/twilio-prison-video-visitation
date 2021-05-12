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
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_dnn;

@Properties(inherit = {opencv_dnn.class})
@Name({"std::vector<cv::Mat*>"})
public class MatPointerVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    public native Mat get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, Mat mat);

    public native MatPointerVector put(@Cast({"size_t"}) long j, Mat mat);

    @ByRef
    @Name({"operator ="})
    public native MatPointerVector put(@ByRef MatPointerVector matPointerVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public MatPointerVector(Pointer pointer) {
        super(pointer);
    }

    public MatPointerVector(Mat mat) {
        this(1);
        put(0, mat);
    }

    public MatPointerVector(Mat... matArr) {
        this((long) matArr.length);
        put(matArr);
    }

    public MatPointerVector() {
        allocate();
    }

    public MatPointerVector(long j) {
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

        @Const
        @Name({"operator *"})
        public native Mat get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Mat[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Mat[] matArr = new Mat[size];
        for (int i = 0; i < size; i++) {
            matArr[i] = get((long) i);
        }
        return matArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Mat pop_back() {
        long size = size() - 1;
        Mat mat = get(size);
        resize(size);
        return mat;
    }

    public MatPointerVector push_back(Mat mat) {
        long size = size();
        resize(1 + size);
        return put(size, mat);
    }

    public MatPointerVector put(Mat mat) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, mat);
    }

    public MatPointerVector put(Mat... matArr) {
        if (size() != ((long) matArr.length)) {
            resize((long) matArr.length);
        }
        for (int i = 0; i < matArr.length; i++) {
            put((long) i, matArr[i]);
        }
        return this;
    }
}
