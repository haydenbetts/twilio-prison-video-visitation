package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;

public class opencv_structured_light extends org.bytedeco.opencv.presets.opencv_structured_light {
    public static final int DECODE_3D_UNDERWORLD = 0;
    public static final int FAPS = 2;
    public static final int FTP = 0;
    public static final int PSP = 1;

    static {
        Loader.load();
    }
}
