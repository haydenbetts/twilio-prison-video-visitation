package org.bytedeco.ffmpeg.postproc;

import org.bytedeco.ffmpeg.presets.postproc;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {postproc.class})
@Opaque
public class pp_context extends Pointer {
    public pp_context() {
        super((Pointer) null);
    }

    public pp_context(Pointer pointer) {
        super(pointer);
    }
}
