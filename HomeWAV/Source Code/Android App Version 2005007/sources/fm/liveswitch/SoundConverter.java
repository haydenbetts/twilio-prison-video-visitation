package fm.liveswitch;

import fm.liveswitch.pcm.Format;

public class SoundConverter extends AudioPipe {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(SoundConverter.class);
    private Resampler __resampler;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Sound Converter";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        boolean z;
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        AudioFormat audioFormat = (AudioFormat) audioBuffer.getFormat();
        boolean z2 = false;
        if (!audioFormat.isCompatible((AudioFormat) super.getOutputFormat())) {
            int clockRate = ((AudioFormat) audioBuffer.getFormat()).getClockRate();
            int channelCount = ((AudioFormat) audioBuffer.getFormat()).getChannelCount();
            int clockRate2 = ((AudioFormat) super.getOutputFormat()).getClockRate();
            int channelCount2 = ((AudioFormat) super.getOutputFormat()).getChannelCount();
            boolean z3 = true;
            if (channelCount != channelCount2) {
                DataBuffer take = __dataBufferPool.take(SoundUtility.calculateOutputLengthForChannelCount(dataBuffer.getLength(), channelCount, channelCount2), dataBuffer.getLittleEndian());
                SoundUtility.convertChannelCount(dataBuffer, channelCount, take, channelCount2);
                audioFormat = audioFormat.clone();
                audioFormat.setChannelCount(channelCount2);
                dataBuffer = take;
                z = true;
                z2 = true;
            } else {
                z = false;
            }
            if (clockRate != clockRate2) {
                if (this.__resampler == null) {
                    this.__resampler = new Resampler(clockRate, clockRate2);
                }
                DataBuffer take2 = __dataBufferPool.take(this.__resampler.getOutputLength(dataBuffer), dataBuffer.getLittleEndian());
                if (this.__resampler.resample(dataBuffer, take2)) {
                    if (z2) {
                        dataBuffer.free();
                    }
                    audioFormat = audioFormat.clone();
                    audioFormat.setClockRate(clockRate2);
                    dataBuffer = take2;
                    z2 = true;
                } else {
                    take2.free();
                    throw new RuntimeException(new Exception(StringExtensions.format("Could not convert audio buffer clock rate from {0} to {1}.", IntegerExtensions.toString(Integer.valueOf(clockRate)), IntegerExtensions.toString(Integer.valueOf(clockRate2)))));
                }
            } else {
                z3 = z;
            }
            if (z3) {
                audioFrame.addBuffer(new AudioBuffer(dataBuffer, audioFormat));
            }
        }
        raiseFrame(audioFrame);
        if (z2) {
            dataBuffer.free();
        }
    }

    public SoundConverter(IAudioOutput iAudioOutput, AudioConfig audioConfig) {
        this(iAudioOutput.getConfig(), audioConfig);
        super.addInput(iAudioOutput);
    }

    public SoundConverter(AudioConfig audioConfig, AudioConfig audioConfig2) {
        super(new Format(audioConfig), new Format(audioConfig2));
    }

    public SoundConverter(IAudioOutput[] iAudioOutputArr, AudioConfig audioConfig) {
        this(iAudioOutputArr[0].getConfig(), audioConfig);
        super.addInputs((TIOutput[]) iAudioOutputArr);
    }

    public SoundConverter(AudioConfig audioConfig) {
        super(new Format(audioConfig));
    }
}
