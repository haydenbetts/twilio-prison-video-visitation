package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::details")
@Properties(inherit = {opencv_core.class})
@Opaque
public class TlsStorage extends Pointer {
    public TlsStorage() {
        super((Pointer) null);
    }

    public TlsStorage(Pointer pointer) {
        super(pointer);
    }
}
