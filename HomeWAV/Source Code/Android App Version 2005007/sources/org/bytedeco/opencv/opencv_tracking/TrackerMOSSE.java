package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerMOSSE extends Tracker {
    @opencv_core.Ptr
    public static native TrackerMOSSE create();

    static {
        Loader.load();
    }

    public TrackerMOSSE(Pointer pointer) {
        super(pointer);
    }
}
