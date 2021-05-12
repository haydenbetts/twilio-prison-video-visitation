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
@Name({"std::vector<cv::cuda::GpuMat>"})
public class GpuMatVector extends Pointer {
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
    public native GpuMat get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef GpuMat gpuMat);

    public native GpuMatVector put(@Cast({"size_t"}) long j, GpuMat gpuMat);

    @ByRef
    @Name({"operator ="})
    public native GpuMatVector put(@ByRef GpuMatVector gpuMatVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public GpuMatVector(Pointer pointer) {
        super(pointer);
    }

    public GpuMatVector(GpuMat gpuMat) {
        this(1);
        put(0, gpuMat);
    }

    public GpuMatVector(GpuMat... gpuMatArr) {
        this((long) gpuMatArr.length);
        put(gpuMatArr);
    }

    public GpuMatVector() {
        allocate();
    }

    public GpuMatVector(long j) {
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
        public native GpuMat get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public GpuMat[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        GpuMat[] gpuMatArr = new GpuMat[size];
        for (int i = 0; i < size; i++) {
            gpuMatArr[i] = get((long) i);
        }
        return gpuMatArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public GpuMat pop_back() {
        long size = size() - 1;
        GpuMat gpuMat = get(size);
        resize(size);
        return gpuMat;
    }

    public GpuMatVector push_back(GpuMat gpuMat) {
        long size = size();
        resize(1 + size);
        return put(size, gpuMat);
    }

    public GpuMatVector put(GpuMat gpuMat) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, gpuMat);
    }

    public GpuMatVector put(GpuMat... gpuMatArr) {
        if (size() != ((long) gpuMatArr.length)) {
            resize((long) gpuMatArr.length);
        }
        for (int i = 0; i < gpuMatArr.length; i++) {
            put((long) i, gpuMatArr[i]);
        }
        return this;
    }
}
