package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
@Opaque
public class DCTContext extends Pointer {
    public DCTContext() {
        super((Pointer) null);
    }

    public DCTContext(Pointer pointer) {
        super(pointer);
    }
}
