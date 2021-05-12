package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
@Opaque
public class AVMD5 extends Pointer {
    public AVMD5() {
        super((Pointer) null);
    }

    public AVMD5(Pointer pointer) {
        super(pointer);
    }
}
