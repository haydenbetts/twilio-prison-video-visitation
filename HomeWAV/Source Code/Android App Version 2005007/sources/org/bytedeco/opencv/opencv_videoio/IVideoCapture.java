package org.bytedeco.opencv.opencv_videoio;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videoio;

@Namespace("cv")
@Properties(inherit = {opencv_videoio.class})
@Opaque
public class IVideoCapture extends Pointer {
    public IVideoCapture() {
        super((Pointer) null);
    }

    public IVideoCapture(Pointer pointer) {
        super(pointer);
    }
}
