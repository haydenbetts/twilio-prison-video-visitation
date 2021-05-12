package fm.liveswitch.opus;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioEncoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.Error;
import fm.liveswitch.Global;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.MediaControlFrame;
import fm.liveswitch.ReportBlock;
import fm.liveswitch.ReportControlFrame;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.pcm.Format;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.rtp.MapAttribute;

public class Encoder extends AudioEncoder {
    private EncoderConfig __codecConfig;
    private Native __encoder;
    private double __lastPercentLoss;
    private double __packetTime;
    private double __percentLossAlpha;
    private double __percentLossVariance;
    private boolean __remoteSupportsFec;
    private long __reportsReceived;
    private double __smoothedFractionLoss;
    private boolean _disableFec;

    public String getLabel() {
        return "Opus Encoder";
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        Native nativeR = this.__encoder;
        if (nativeR != null) {
            nativeR.destroy();
            this.__encoder = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        int i;
        MediaControlFrame[] mediaControlFrameArr2 = mediaControlFrameArr;
        if (this.__encoder != null) {
            EncoderConfig codecConfig = getCodecConfig();
            int length = mediaControlFrameArr2.length;
            int i2 = 0;
            boolean z = false;
            while (i2 < length) {
                MediaControlFrame mediaControlFrame = mediaControlFrameArr2[i2];
                if (mediaControlFrame instanceof ReportControlFrame) {
                    this.__reportsReceived++;
                    ReportBlock[] reportBlocks = ((ReportControlFrame) mediaControlFrame).getReportBlocks();
                    int length2 = reportBlocks.length;
                    int i3 = 0;
                    while (i3 < length2) {
                        ReportBlock reportBlock = reportBlocks[i3];
                        if (reportBlock.getSynchronizationSource() == super.getOutputSynchronizationSource()) {
                            double abs = MathAssistant.abs(reportBlock.getPercentLost() - this.__lastPercentLoss);
                            this.__percentLossVariance = abs;
                            double d = this.__percentLossAlpha;
                            i = i2;
                            double d2 = (this.__smoothedFractionLoss * d) + ((1.0d - d) * abs);
                            this.__smoothedFractionLoss = d2;
                            codecConfig.setPacketLossPercent((int) (d2 * 100.0d));
                            if (!getDisableFec() && !codecConfig.getForwardErrorCorrection() && this.__remoteSupportsFec && this.__smoothedFractionLoss * 100.0d >= ((double) getPercentLossToTriggerFEC())) {
                                Log.debug(StringExtensions.format("Activating FEC for {0}.", (Object) getLabel()));
                                codecConfig.setForwardErrorCorrection(true);
                                z = true;
                            }
                        } else {
                            i = i2;
                        }
                        i3++;
                        i2 = i;
                    }
                }
                i2++;
            }
            if (z) {
                setCodecConfig(codecConfig);
            }
        }
        super.doProcessControlFrames(mediaControlFrameArr);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioBuffer encode = encode(audioBuffer, (AudioFormat) super.getOutputFormat(), (double) audioFrame.getDuration(), this.__remoteSupportsFec);
        if (encode != null) {
            audioFrame.addBuffer(encode);
            raiseFrame(audioFrame);
            encode.getDataBuffer().free();
        }
    }

    /* access modifiers changed from: protected */
    public Error doProcessSdpMediaDescription(MediaDescription mediaDescription, boolean z, boolean z2) {
        MapAttribute rtpMapAttribute;
        Error doProcessSdpMediaDescription = super.doProcessSdpMediaDescription(mediaDescription, z, z2);
        if (doProcessSdpMediaDescription != null) {
            return doProcessSdpMediaDescription;
        }
        if (z2 || (rtpMapAttribute = mediaDescription.getRtpMapAttribute(((AudioFormat) super.getOutputFormat()).getName(), ((AudioFormat) super.getOutputFormat()).getClockRate(), ((AudioFormat) super.getOutputFormat()).getParameters())) == null) {
            return null;
        }
        this.__remoteSupportsFec = Global.equals(mediaDescription.getFormatParameterValue(rtpMapAttribute.getPayloadType(), "useinbandfec"), "1");
        return null;
    }

    private AudioBuffer encode(AudioBuffer audioBuffer, AudioFormat audioFormat, double d, boolean z) {
        Native nativeR = this.__encoder;
        if (nativeR == null || d != this.__packetTime) {
            if (nativeR != null) {
                nativeR.destroy();
            }
            this.__packetTime = d;
            if (this.__encoder == null) {
                Log.debug("Creating new Opus native encoder.");
            } else {
                Log.debug("Recreating Opus native encoder.");
            }
            this.__encoder = new Native(true, super.getConfig().getClockRate(), super.getConfig().getChannelCount(), this.__packetTime);
            EncoderConfig codecConfig = getCodecConfig();
            codecConfig.setForwardErrorCorrection(z);
            setCodecConfig(codecConfig);
            this.__encoder.setBitrate(getBitrate());
        }
        int bitrate = getBitrate();
        int targetOutputBitrate = getTargetOutputBitrate();
        if (targetOutputBitrate > 0 && targetOutputBitrate != bitrate) {
            if (bitrate > 0) {
                Log.debug(StringExtensions.format("Changing {0} bitrate from {1}kbps to {2}kbps.", getLabel(), IntegerExtensions.toString(Integer.valueOf(bitrate)), IntegerExtensions.toString(Integer.valueOf(targetOutputBitrate))));
            }
            this.__encoder.setBitrate(targetOutputBitrate);
            setBitrate(targetOutputBitrate);
        }
        AudioBuffer encode = this.__encoder.encode(audioBuffer, audioFormat);
        if (encode == null) {
            return null;
        }
        encode.getDataBuffer().setLittleEndian(audioFormat.getLittleEndian());
        return encode;
    }

    public Encoder() {
        this(Format.getDefaultConfig());
    }

    public Encoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
        this.__remoteSupportsFec = false;
        this.__packetTime = -1.0d;
        this.__reportsReceived = 0;
        this.__smoothedFractionLoss = 0.0d;
        this.__percentLossAlpha = 0.75d;
        this.__lastPercentLoss = 0.0d;
        this.__percentLossVariance = -1.0d;
        this.__codecConfig = new EncoderConfig();
        setTargetBitrate(32);
    }

    public Encoder(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }

    public EncoderConfig getCodecConfig() {
        return this.__codecConfig.deepCopy();
    }

    public boolean getDisableFec() {
        return this._disableFec;
    }

    public int getPercentLossToTriggerFEC() {
        return this.__codecConfig.getPacketLossPercent();
    }

    public double getQuality() {
        return ((double) this.__codecConfig.getComplexity()) / 10.0d;
    }

    public int setCodecConfig(EncoderConfig encoderConfig) {
        Native nativeR = this.__encoder;
        int encoderConfig2 = nativeR != null ? nativeR.setEncoderConfig(encoderConfig.getNativeConfig()) : 0;
        if (encoderConfig2 == 0) {
            this.__codecConfig = encoderConfig.deepCopy();
        }
        return encoderConfig2;
    }

    public void setDisableFec(boolean z) {
        this._disableFec = z;
    }

    public void setPercentLossToTriggerFEC(int i) {
        EncoderConfig codecConfig = getCodecConfig();
        codecConfig.setPacketLossPercent(i);
        if (this.__encoder != null) {
            setCodecConfig(codecConfig);
        }
    }

    public void setQuality(double d) {
        EncoderConfig codecConfig = getCodecConfig();
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        codecConfig.setComplexity((int) (d * 10.0d));
        if (this.__encoder != null) {
            setCodecConfig(codecConfig);
        }
    }
}
