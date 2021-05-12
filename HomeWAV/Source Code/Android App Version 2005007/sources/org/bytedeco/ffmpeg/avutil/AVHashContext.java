package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
@Opaque
public class AVHashContext extends Pointer {
    public AVHashContext() {
        super((Pointer) null);
    }

    public AVHashContext(Pointer pointer) {
        super(pointer);
    }
}
