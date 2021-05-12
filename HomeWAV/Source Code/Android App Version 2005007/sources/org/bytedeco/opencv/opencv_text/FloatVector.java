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
@Name({"std::vector<float>"})
public class FloatVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    public native float get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, float f);

    public native FloatVector put(@Cast({"size_t"}) long j, float f);

    @ByRef
    @Name({"operator ="})
    public native FloatVector put(@ByRef FloatVector floatVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public FloatVector(Pointer pointer) {
        super(pointer);
    }

    public FloatVector(float f) {
        this(1);
        put(0, f);
    }

    public FloatVector(float... fArr) {
        this((long) fArr.length);
        put(fArr);
    }

    public FloatVector() {
        allocate();
    }

    public FloatVector(long j) {
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
        public native float get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public float[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = get((long) i);
        }
        return fArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public float pop_back() {
        long size = size() - 1;
        float f = get(size);
        resize(size);
        return f;
    }

    public FloatVector push_back(float f) {
        long size = size();
        resize(1 + size);
        return put(size, f);
    }

    public FloatVector put(float f) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, f);
    }

    public FloatVector put(float... fArr) {
        if (size() != ((long) fArr.length)) {
            resize((long) fArr.length);
        }
        for (int i = 0; i < fArr.length; i++) {
            put((long) i, fArr[i]);
        }
        return this;
    }
}
