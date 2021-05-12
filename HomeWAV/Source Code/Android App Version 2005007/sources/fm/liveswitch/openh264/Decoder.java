package fm.liveswitch.openh264;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoDecoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.h264.Format;
import fm.liveswitch.h264.ProfileLevelId;
import fm.liveswitch.h264.Utility;

public class Decoder extends VideoDecoder {
    private DecoderConfig __codecConfig;
    private Native __decoder;

    public String getLabel() {
        return "OpenH264 Decoder";
    }

    public Decoder() {
        this(ProfileLevelId.getDefault());
    }

    public Decoder(IVideoOutput iVideoOutput) {
        this(iVideoOutput, ProfileLevelId.getDefault());
    }

    public Decoder(IVideoOutput iVideoOutput, ProfileLevelId profileLevelId) {
        this(profileLevelId);
        super.addInput(iVideoOutput);
    }

    public Decoder(ProfileLevelId profileLevelId) {
        super(new Format(profileLevelId), VideoFormat.getI420());
        this.__decoder = new Native(false);
        setCodecConfig(new DecoderConfig());
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
                super.sendKeyFrameRequest("H.264 decoder needs a keyframe.");
            }
        }
    }

    public DecoderConfig getCodecConfig() {
        return this.__codecConfig.deepCopy();
    }

    public boolean getNeedsKeyFrame() {
        return this.__decoder.getNeedsKeyFrame();
    }

    public ProfileLevelId getProfileLevelId() {
        return new ProfileLevelId(((VideoFormat) super.getInputFormat()).getProfile(), ((VideoFormat) super.getInputFormat()).getLevel());
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }

    public int setCodecConfig(DecoderConfig decoderConfig) {
        int decoderConfig2 = this.__decoder.setDecoderConfig(decoderConfig.getNativeConfig());
        if (decoderConfig2 == 0) {
            this.__codecConfig = decoderConfig.deepCopy();
        }
        return decoderConfig2;
    }

    private void setNeedsKeyFrame(boolean z) {
        this.__decoder.setNeedsKeyFrame(z);
    }
}
