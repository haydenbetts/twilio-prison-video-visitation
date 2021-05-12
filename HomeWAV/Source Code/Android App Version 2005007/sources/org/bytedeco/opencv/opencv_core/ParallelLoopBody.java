package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class ParallelLoopBody extends Pointer {
    @Name({"operator ()"})
    public native void apply(@ByRef @Const Range range);

    static {
        Loader.load();
    }

    public ParallelLoopBody(Pointer pointer) {
        super(pointer);
    }
}
