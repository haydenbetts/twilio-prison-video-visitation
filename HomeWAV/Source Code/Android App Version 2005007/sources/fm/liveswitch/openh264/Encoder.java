package fm.liveswitch.openh264;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoEncoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.h264.Format;
import fm.liveswitch.h264.ProfileLevelId;
import fm.liveswitch.h264.Utility;

public class Encoder extends VideoEncoder {
    private EncoderConfig __codecConfig;
    private Native __encoder;
    private double __quality;

    public String getLabel() {
        return "OpenH264 Encoder";
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__encoder.destroy();
        this.__encoder = null;
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        int bitrate = getBitrate();
        int targetOutputBitrate = getTargetOutputBitrate();
        if (targetOutputBitrate > 0 && targetOutputBitrate != bitrate) {
            if (bitrate > 0) {
                Log.debug(StringExtensions.format("Changing {0} bitrate from {1}kbps to {2}kbps.", getLabel(), IntegerExtensions.toString(Integer.valueOf(bitrate)), IntegerExtensions.toString(Integer.valueOf(targetOutputBitrate))));
            }
            this.__encoder.setBitrate(targetOutputBitrate);
            setBitrate(targetOutputBitrate);
        }
        VideoBuffer encode = this.__encoder.encode(videoBuffer.toPlanar(), (VideoFormat) super.getOutputFormat());
        if (encode != null) {
            if (Utility.isKeyFrame(encode.getDataBuffer())) {
                Log.debug(StringExtensions.format("{0} generated keyframe.", (Object) getLabel()));
            }
            videoFrame.addBuffer(encode);
            raiseFrame(videoFrame);
            encode.getDataBuffer().free();
        }
    }

    public Encoder() {
        this(ProfileLevelId.getDefault());
    }

    public Encoder(IVideoOutput iVideoOutput) {
        this(iVideoOutput, ProfileLevelId.getDefault());
    }

    public Encoder(IVideoOutput iVideoOutput, ProfileLevelId profileLevelId) {
        this(profileLevelId);
        super.addInput(iVideoOutput);
    }

    public Encoder(ProfileLevelId profileLevelId) {
        super(VideoFormat.getI420(), new Format(profileLevelId));
        this.__quality = 0.5d;
        this.__encoder = new Native(true);
        setCodecConfig(new EncoderConfig());
        setTargetBitrate(768);
    }

    public EncoderConfig getCodecConfig() {
        return this.__codecConfig.deepCopy();
    }

    public boolean getForceKeyFrame() {
        return this.__encoder.getForceKeyFrame();
    }

    public ProfileLevelId getProfileLevelId() {
        return new ProfileLevelId(((VideoFormat) super.getOutputFormat()).getProfile(), ((VideoFormat) super.getOutputFormat()).getLevel());
    }

    public double getQuality() {
        return this.__quality;
    }

    public int setCodecConfig(EncoderConfig encoderConfig) {
        int encoderConfig2 = this.__encoder.setEncoderConfig(encoderConfig.getNativeConfig());
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) encoderConfig.getSpatialLayers()) && encoderConfig2 == 0; i++) {
            encoderConfig2 = this.__encoder.setEncoderSpatialLayerConfig(encoderConfig.getSpatialLayers()[i].getNativeConfig(), i);
        }
        if (encoderConfig2 == 0) {
            encoderConfig2 = this.__encoder.applyEncoderConfig();
        }
        if (encoderConfig2 == 0) {
            EncoderConfig deepCopy = encoderConfig.deepCopy();
            this.__codecConfig = deepCopy;
            this.__quality = Global.equals(deepCopy.getComplexityMode(), ComplexityMode.getLow()) ? 0.0d : Global.equals(this.__codecConfig.getComplexityMode(), ComplexityMode.getMedium()) ? 0.5d : 1.0d;
        }
        return encoderConfig2;
    }

    public void setForceKeyFrame(boolean z) {
        this.__encoder.setForceKeyFrame(z);
    }

    public void setQuality(double d) {
        ComplexityMode low = d < 0.4d ? ComplexityMode.getLow() : d < 0.8d ? ComplexityMode.getMedium() : ComplexityMode.getHigh();
        int complexityMode = this.__encoder.setComplexityMode(low);
        if (complexityMode == 0) {
            this.__codecConfig.setComplexityMode(low);
            this.__quality = d;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Setting quality for the OpenH264 encoder failed with code {0}.", (Object) Integer.valueOf(complexityMode))));
    }
}
