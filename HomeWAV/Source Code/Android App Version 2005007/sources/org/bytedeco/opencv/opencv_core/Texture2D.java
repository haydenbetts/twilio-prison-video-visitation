package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ogl")
@Properties(inherit = {opencv_core.class})
@Opaque
public class Texture2D extends Pointer {
    public Texture2D() {
        super((Pointer) null);
    }

    public Texture2D(Pointer pointer) {
        super(pointer);
    }
}
