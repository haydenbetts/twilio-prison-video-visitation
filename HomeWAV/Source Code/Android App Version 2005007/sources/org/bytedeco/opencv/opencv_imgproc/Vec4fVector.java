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
import org.bytedeco.opencv.opencv_core.Scalar4f;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Name({"std::vector<cv::Vec4f>"})
public class Vec4fVector extends Pointer {
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
    @Cast({"cv::Vec4f*"})
    public native Scalar4f get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef @Cast({"cv::Vec4f*"}) Scalar4f scalar4f);

    public native Vec4fVector put(@Cast({"size_t"}) long j, Scalar4f scalar4f);

    @ByRef
    @Name({"operator ="})
    public native Vec4fVector put(@ByRef Vec4fVector vec4fVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Vec4fVector(Pointer pointer) {
        super(pointer);
    }

    public Vec4fVector(Scalar4f scalar4f) {
        this(1);
        put(0, scalar4f);
    }

    public Vec4fVector(Scalar4f... scalar4fArr) {
        this((long) scalar4fArr.length);
        put(scalar4fArr);
    }

    public Vec4fVector() {
        allocate();
    }

    public Vec4fVector(long j) {
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
        @Cast({"cv::Vec4f*"})
        @Const
        @Name({"operator *"})
        public native Scalar4f get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Scalar4f[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Scalar4f[] scalar4fArr = new Scalar4f[size];
        for (int i = 0; i < size; i++) {
            scalar4fArr[i] = get((long) i);
        }
        return scalar4fArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Scalar4f pop_back() {
        long size = size() - 1;
        Scalar4f scalar4f = get(size);
        resize(size);
        return scalar4f;
    }

    public Vec4fVector push_back(Scalar4f scalar4f) {
        long size = size();
        resize(1 + size);
        return put(size, scalar4f);
    }

    public Vec4fVector put(Scalar4f scalar4f) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, scalar4f);
    }

    public Vec4fVector put(Scalar4f... scalar4fArr) {
        if (size() != ((long) scalar4fArr.length)) {
            resize((long) scalar4fArr.length);
        }
        for (int i = 0; i < scalar4fArr.length; i++) {
            put((long) i, scalar4fArr[i]);
        }
        return this;
    }
}
