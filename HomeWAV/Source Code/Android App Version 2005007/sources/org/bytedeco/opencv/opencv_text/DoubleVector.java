package org.bytedeco.opencv.opencv_text;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_text;

@Properties(inherit = {opencv_text.class})
@Name({"std::vector<double>"})
public class DoubleVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    public native double get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, double d);

    public native DoubleVector put(@Cast({"size_t"}) long j, double d);

    @ByRef
    @Name({"operator ="})
    public native DoubleVector put(@ByRef DoubleVector doubleVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public DoubleVector(Pointer pointer) {
        super(pointer);
    }

    public DoubleVector(double d) {
        this(1);
        put(0, d);
    }

    public DoubleVector(double... dArr) {
        this((long) dArr.length);
        put(dArr);
    }

    public DoubleVector() {
        allocate();
    }

    public DoubleVector(long j) {
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

        @Name({"operator *"})
        public native double get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public double[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = get((long) i);
        }
        return dArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public double pop_back() {
        long size = size() - 1;
        double d = get(size);
        resize(size);
        return d;
    }

    public DoubleVector push_back(double d) {
        long size = size();
        resize(1 + size);
        return put(size, d);
    }

    public DoubleVector put(double d) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, d);
    }

    public DoubleVector put(double... dArr) {
        if (size() != ((long) dArr.length)) {
            resize((long) dArr.length);
        }
        for (int i = 0; i < dArr.length; i++) {
            put((long) i, dArr[i]);
        }
        return this;
    }
}
