package fm.liveswitch.vpx;

import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoDecoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public abstract class Decoder extends VideoDecoder {
    private Native __decoder;

    public abstract Codec getCodec();

    public Decoder(VideoFormat videoFormat) {
        super(videoFormat, VideoFormat.getI420());
        Native nativeR = new Native(false);
        this.__decoder = nativeR;
        nativeR.setCodec(getCodec());
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__decoder.destroy();
        this.__decoder = null;
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        super.doProcessFrame(videoFrame, videoBuffer);
        if (!super.getDelayDecode()) {
            VideoBuffer decode = this.__decoder.decode(videoBuffer, (VideoFormat) super.getOutputFormat());
            if (decode != null) {
                videoFrame.addBuffer(decode);
                raiseFrame(videoFrame);
                decode.getDataBuffer().free();
            }
            if (getNeedsKeyFrame()) {
                super.sendKeyFrameRequest(StringExtensions.concat(getLabel(), " needs a keyframe."));
            }
        }
    }

    public boolean getNeedsKeyFrame() {
        return this.__decoder.getNeedsKeyFrame();
    }

    private void setNeedsKeyFrame(boolean z) {
        this.__decoder.setNeedsKeyFrame(z);
    }
}
