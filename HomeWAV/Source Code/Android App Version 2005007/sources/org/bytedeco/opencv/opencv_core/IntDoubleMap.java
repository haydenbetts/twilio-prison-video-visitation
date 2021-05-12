package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::map<int,double>"})
public class IntDoubleMap extends Pointer {
    private native void allocate();

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @Index
    public native double get(int i);

    public native IntDoubleMap put(int i, double d);

    @ByRef
    @Name({"operator ="})
    public native IntDoubleMap put(@ByRef IntDoubleMap intDoubleMap);

    public native long size();

    static {
        Loader.load();
    }

    public IntDoubleMap(Pointer pointer) {
        super(pointer);
    }

    public IntDoubleMap() {
        allocate();
    }

    public boolean empty() {
        return size() == 0;
    }

    @Name({"iterator"})
    @NoOffset
    public static class Iterator extends Pointer {
        @Name({"operator =="})
        public native boolean equals(@ByRef Iterator iterator);

        @MemberGetter
        @Name({"operator *().first"})
        public native int first();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        @MemberGetter
        @Name({"operator *().second"})
        public native double second();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }
}
