package org.bytedeco.opencv.opencv_core;

import java.util.Arrays;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.ValueSetter;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<cv::String>"})
public class StringVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    @opencv_core.Str
    public native BytePointer get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @opencv_core.Str BytePointer bytePointer);

    @Index(function = "at")
    @ValueSetter
    public native StringVector put(@Cast({"size_t"}) long j, @opencv_core.Str String str);

    public native StringVector put(@Cast({"size_t"}) long j, BytePointer bytePointer);

    @ByRef
    @Name({"operator ="})
    public native StringVector put(@ByRef StringVector stringVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public StringVector(Pointer pointer) {
        super(pointer);
    }

    public StringVector(BytePointer bytePointer) {
        this(1);
        put(0, bytePointer);
    }

    public StringVector(BytePointer... bytePointerArr) {
        this((long) bytePointerArr.length);
        put(bytePointerArr);
    }

    public StringVector(String str) {
        this(1);
        put(0, str);
    }

    public StringVector(String... strArr) {
        this((long) strArr.length);
        put(strArr);
    }

    public StringVector() {
        allocate();
    }

    public StringVector(long j) {
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
        @opencv_core.Str
        public native BytePointer get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public BytePointer[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        BytePointer[] bytePointerArr = new BytePointer[size];
        for (int i = 0; i < size; i++) {
            bytePointerArr[i] = get((long) i);
        }
        return bytePointerArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public BytePointer pop_back() {
        long size = size() - 1;
        BytePointer bytePointer = get(size);
        resize(size);
        return bytePointer;
    }

    public StringVector push_back(BytePointer bytePointer) {
        long size = size();
        resize(1 + size);
        return put(size, bytePointer);
    }

    public StringVector put(BytePointer bytePointer) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, bytePointer);
    }

    public StringVector put(BytePointer... bytePointerArr) {
        if (size() != ((long) bytePointerArr.length)) {
            resize((long) bytePointerArr.length);
        }
        for (int i = 0; i < bytePointerArr.length; i++) {
            put((long) i, bytePointerArr[i]);
        }
        return this;
    }

    public StringVector push_back(String str) {
        long size = size();
        resize(1 + size);
        return put(size, str);
    }

    public StringVector put(String str) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, str);
    }

    public StringVector put(String... strArr) {
        if (size() != ((long) strArr.length)) {
            resize((long) strArr.length);
        }
        for (int i = 0; i < strArr.length; i++) {
            put((long) i, strArr[i]);
        }
        return this;
    }
}
