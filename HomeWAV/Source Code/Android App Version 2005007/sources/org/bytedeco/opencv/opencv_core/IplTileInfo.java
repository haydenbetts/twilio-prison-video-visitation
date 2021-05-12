package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Opaque
public class IplTileInfo extends Pointer {
    public IplTileInfo() {
        super((Pointer) null);
    }

    public IplTileInfo(Pointer pointer) {
        super(pointer);
    }
}
