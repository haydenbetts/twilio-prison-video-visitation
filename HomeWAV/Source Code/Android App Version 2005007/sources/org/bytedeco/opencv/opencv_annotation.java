package org.bytedeco.opencv;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class}, value = {@Platform(executable = {"opencv_annotation"})})
public class opencv_annotation {
}
