package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
@Opaque
public class AVSHA extends Pointer {
    public AVSHA() {
        super((Pointer) null);
    }

    public AVSHA(Pointer pointer) {
        super(pointer);
    }
}
