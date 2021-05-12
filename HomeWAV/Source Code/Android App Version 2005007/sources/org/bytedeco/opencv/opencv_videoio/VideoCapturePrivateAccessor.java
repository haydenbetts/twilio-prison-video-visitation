package org.bytedeco.opencv.opencv_videoio;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videoio;

@Namespace("cv::internal")
@Properties(inherit = {opencv_videoio.class})
@Opaque
public class VideoCapturePrivateAccessor extends Pointer {
    public VideoCapturePrivateAccessor() {
        super((Pointer) null);
    }

    public VideoCapturePrivateAccessor(Pointer pointer) {
        super(pointer);
    }
}
