package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;

public class opencv_bioinspired extends org.bytedeco.opencv.presets.opencv_bioinspired {
    public static final int RETINA_COLOR_BAYER = 2;
    public static final int RETINA_COLOR_DIAGONAL = 1;
    public static final int RETINA_COLOR_RANDOM = 0;

    static {
        Loader.load();
    }
}
