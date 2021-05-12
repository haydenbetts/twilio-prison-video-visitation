package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ogl")
@Properties(inherit = {opencv_core.class})
@Opaque
public class Buffer extends Pointer {
    public Buffer() {
        super((Pointer) null);
    }

    public Buffer(Pointer pointer) {
        super(pointer);
    }
}
