package org.bytedeco.ffmpeg.swscale;

import org.bytedeco.ffmpeg.presets.swscale;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {swscale.class})
@Opaque
public class SwsContext extends Pointer {
    public SwsContext() {
        super((Pointer) null);
    }

    public SwsContext(Pointer pointer) {
        super(pointer);
    }
}
