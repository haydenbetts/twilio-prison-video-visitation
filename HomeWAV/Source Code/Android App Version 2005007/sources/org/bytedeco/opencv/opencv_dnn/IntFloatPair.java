package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_dnn;

@Properties(inherit = {opencv_dnn.class})
@Name({"std::pair<int,float>"})
@NoOffset
public class IntFloatPair extends Pointer {
    private native void allocate();

    @MemberGetter
    public native int first();

    public native IntFloatPair first(int i);

    @ByRef
    @Name({"operator ="})
    public native IntFloatPair put(@ByRef IntFloatPair intFloatPair);

    @MemberGetter
    public native float second();

    public native IntFloatPair second(float f);

    static {
        Loader.load();
    }

    public IntFloatPair(Pointer pointer) {
        super(pointer);
    }

    public IntFloatPair(int i, float f) {
        this();
        put(i, f);
    }

    public IntFloatPair() {
        allocate();
    }

    public IntFloatPair put(int i, float f) {
        first(i);
        second(f);
        return this;
    }
}
