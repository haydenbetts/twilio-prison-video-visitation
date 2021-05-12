package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::pair<int,int>"})
@NoOffset
public class IntIntPair extends Pointer {
    private native void allocate();

    @MemberGetter
    public native int first();

    public native IntIntPair first(int i);

    @ByRef
    @Name({"operator ="})
    public native IntIntPair put(@ByRef IntIntPair intIntPair);

    @MemberGetter
    public native int second();

    public native IntIntPair second(int i);

    static {
        Loader.load();
    }

    public IntIntPair(Pointer pointer) {
        super(pointer);
    }

    public IntIntPair(int i, int i2) {
        this();
        put(i, i2);
    }

    public IntIntPair() {
        allocate();
    }

    public IntIntPair put(int i, int i2) {
        first(i);
        second(i2);
        return this;
    }
}
