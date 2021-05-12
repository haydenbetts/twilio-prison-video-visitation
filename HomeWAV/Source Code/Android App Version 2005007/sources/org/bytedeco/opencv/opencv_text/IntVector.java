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
@Name({"std::vector<int>"})
public class IntVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    public native int get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, int i);

    public native IntVector put(@Cast({"size_t"}) long j, int i);

    @ByRef
    @Name({"operator ="})
    public native IntVector put(@ByRef IntVector intVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public IntVector(Pointer pointer) {
        super(pointer);
    }

    public IntVector(int... iArr) {
        this((long) iArr.length);
        put(iArr);
    }

    public IntVector() {
        allocate();
    }

    public IntVector(long j) {
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
        public native int get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public int[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = get((long) i);
        }
        return iArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public int pop_back() {
        long size = size() - 1;
        int i = get(size);
        resize(size);
        return i;
    }

    public IntVector push_back(int i) {
        long size = size();
        resize(1 + size);
        return put(size, i);
    }

    public IntVector put(int i) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, i);
    }

    public IntVector put(int... iArr) {
        if (size() != ((long) iArr.length)) {
            resize((long) iArr.length);
        }
        for (int i = 0; i < iArr.length; i++) {
            put((long) i, iArr[i]);
        }
        return this;
    }
}
