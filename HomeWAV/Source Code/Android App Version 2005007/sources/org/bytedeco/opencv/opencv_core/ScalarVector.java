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
@Name({"std::vector<cv::Scalar>"})
public class ScalarVector extends Pointer {
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
    public native Scalar get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Scalar scalar);

    public native ScalarVector put(@Cast({"size_t"}) long j, Scalar scalar);

    @ByRef
    @Name({"operator ="})
    public native ScalarVector put(@ByRef ScalarVector scalarVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public ScalarVector(Pointer pointer) {
        super(pointer);
    }

    public ScalarVector(Scalar scalar) {
        this(1);
        put(0, scalar);
    }

    public ScalarVector(Scalar... scalarArr) {
        this((long) scalarArr.length);
        put(scalarArr);
    }

    public ScalarVector() {
        allocate();
    }

    public ScalarVector(long j) {
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
        public native Scalar get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Scalar[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Scalar[] scalarArr = new Scalar[size];
        for (int i = 0; i < size; i++) {
            scalarArr[i] = get((long) i);
        }
        return scalarArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Scalar pop_back() {
        long size = size() - 1;
        Scalar scalar = get(size);
        resize(size);
        return scalar;
    }

    public ScalarVector push_back(Scalar scalar) {
        long size = size();
        resize(1 + size);
        return put(size, scalar);
    }

    public ScalarVector put(Scalar scalar) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, scalar);
    }

    public ScalarVector put(Scalar... scalarArr) {
        if (size() != ((long) scalarArr.length)) {
            resize((long) scalarArr.length);
        }
        for (int i = 0; i < scalarArr.length; i++) {
            put((long) i, scalarArr[i]);
        }
        return this;
    }
}
