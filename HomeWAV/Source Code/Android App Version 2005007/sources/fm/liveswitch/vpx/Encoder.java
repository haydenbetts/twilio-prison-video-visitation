package fm.liveswitch.vpx;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoEncoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public abstract class Encoder extends VideoEncoder {
    private EncoderConfig __config;
    private Native __encoder;

    public abstract Codec getCodec();

    /* access modifiers changed from: protected */
    public abstract boolean isKeyFrame(DataBuffer dataBuffer);

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
            if (isKeyFrame(encode.getDataBuffer())) {
                Log.debug(StringExtensions.format("{0} generated keyframe.", (Object) getLabel()));
            }
            videoFrame.addBuffer(encode);
            raiseFrame(videoFrame);
            encode.getDataBuffer().free();
        }
    }

    public Encoder(VideoFormat videoFormat) {
        super(VideoFormat.getI420(), videoFormat);
        Native nativeR = new Native(true);
        this.__encoder = nativeR;
        nativeR.setCodec(getCodec());
        EncoderConfig encoderConfig = new EncoderConfig();
        this.__config = encoderConfig;
        setCodecConfig(encoderConfig);
        setTargetBitrate(768);
    }

    public EncoderConfig getCodecConfig() {
        return this.__config.deepCopy();
    }

    public boolean getForceKeyFrame() {
        return this.__encoder.getForceKeyFrame();
    }

    public double getQuality() {
        return 1.0d - (((double) (this.__config.getMaxQuantizer() - 31)) / 32.0d);
    }

    public int setCodecConfig(EncoderConfig encoderConfig) {
        int encoderConfig2 = this.__encoder.setEncoderConfig(encoderConfig.getNativeConfig());
        if (encoderConfig2 == 0) {
            this.__config = encoderConfig.deepCopy();
        }
        return encoderConfig2;
    }

    public void setForceKeyFrame(boolean z) {
        this.__encoder.setForceKeyFrame(z);
    }

    public void setQuality(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int i = ((int) ((1.0d - d) * 32.0d)) + 31;
        if (i != this.__config.getMaxQuantizer()) {
            this.__config.setMaxQuantizer(i);
            if (setCodecConfig(this.__config) != 0) {
                throw new RuntimeException(new Exception(StringExtensions.concat("Unable to update ", getLabel(), " configuration.")));
            }
        }
    }
}
