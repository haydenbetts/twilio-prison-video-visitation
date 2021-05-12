package org.bytedeco.opencv.opencv_imgproc;

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
import org.bytedeco.opencv.opencv_core.Scalar4i;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Name({"std::vector<cv::Vec4i>"})
public class Vec4iVector extends Pointer {
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
    @Cast({"cv::Vec4i*"})
    public native Scalar4i get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef @Cast({"cv::Vec4i*"}) Scalar4i scalar4i);

    public native Vec4iVector put(@Cast({"size_t"}) long j, Scalar4i scalar4i);

    @ByRef
    @Name({"operator ="})
    public native Vec4iVector put(@ByRef Vec4iVector vec4iVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Vec4iVector(Pointer pointer) {
        super(pointer);
    }

    public Vec4iVector(Scalar4i scalar4i) {
        this(1);
        put(0, scalar4i);
    }

    public Vec4iVector(Scalar4i... scalar4iArr) {
        this((long) scalar4iArr.length);
        put(scalar4iArr);
    }

    public Vec4iVector() {
        allocate();
    }

    public Vec4iVector(long j) {
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
        @Cast({"cv::Vec4i*"})
        @Const
        @Name({"operator *"})
        public native Scalar4i get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Scalar4i[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Scalar4i[] scalar4iArr = new Scalar4i[size];
        for (int i = 0; i < size; i++) {
            scalar4iArr[i] = get((long) i);
        }
        return scalar4iArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Scalar4i pop_back() {
        long size = size() - 1;
        Scalar4i scalar4i = get(size);
        resize(size);
        return scalar4i;
    }

    public Vec4iVector push_back(Scalar4i scalar4i) {
        long size = size();
        resize(1 + size);
        return put(size, scalar4i);
    }

    public Vec4iVector put(Scalar4i scalar4i) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, scalar4i);
    }

    public Vec4iVector put(Scalar4i... scalar4iArr) {
        if (size() != ((long) scalar4iArr.length)) {
            resize((long) scalar4iArr.length);
        }
        for (int i = 0; i < scalar4iArr.length; i++) {
            put((long) i, scalar4iArr[i]);
        }
        return this;
    }
}
