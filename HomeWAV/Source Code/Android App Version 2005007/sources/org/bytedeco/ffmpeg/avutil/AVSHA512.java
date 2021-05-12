package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
@Opaque
public class AVSHA512 extends Pointer {
    public AVSHA512() {
        super((Pointer) null);
    }

    public AVSHA512(Pointer pointer) {
        super(pointer);
    }
}
