package tvi.webrtc;

import javax.annotation.Nullable;
import tvi.webrtc.EglBase;

public class DefaultVideoDecoderFactory implements VideoDecoderFactory {
    private final HardwareVideoDecoderFactory hardwareVideoDecoderFactory;
    private final SoftwareVideoDecoderFactory softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();

    public DefaultVideoDecoderFactory(EglBase.Context context) {
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactory(context, false);
    }

    @Nullable
    public VideoDecoder createDecoder(String str) {
        VideoDecoder createDecoder = this.hardwareVideoDecoderFactory.createDecoder(str);
        if (createDecoder != null) {
            return createDecoder;
        }
        return this.softwareVideoDecoderFactory.createDecoder(str);
    }
}
