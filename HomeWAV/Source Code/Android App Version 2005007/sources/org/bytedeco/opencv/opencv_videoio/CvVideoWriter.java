package org.bytedeco.opencv.opencv_videoio;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videoio;

@Properties(inherit = {opencv_videoio.class})
@Opaque
public class CvVideoWriter extends Pointer {
    public CvVideoWriter() {
        super((Pointer) null);
    }

    public CvVideoWriter(Pointer pointer) {
        super(pointer);
    }
}
