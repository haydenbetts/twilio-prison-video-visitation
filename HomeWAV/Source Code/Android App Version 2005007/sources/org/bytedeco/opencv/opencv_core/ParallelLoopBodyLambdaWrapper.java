package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class ParallelLoopBodyLambdaWrapper extends ParallelLoopBody {
    private native void allocate(@ByVal opencv_core.Functor functor);

    @Name({"operator ()"})
    public native void apply(@ByRef @Const Range range);

    static {
        Loader.load();
    }

    public ParallelLoopBodyLambdaWrapper(Pointer pointer) {
        super(pointer);
    }

    public ParallelLoopBodyLambdaWrapper(@ByVal opencv_core.Functor functor) {
        super((Pointer) null);
        allocate(functor);
    }
}
