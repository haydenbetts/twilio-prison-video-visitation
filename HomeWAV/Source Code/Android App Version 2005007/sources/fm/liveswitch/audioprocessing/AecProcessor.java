package fm.liveswitch.audioprocessing;

import fm.liveswitch.AecPipe;
import fm.liveswitch.Architecture;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.Global;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.Platform;
import fm.liveswitch.SourceLanguage;
import fm.liveswitch.pcm.Format;

public class AecProcessor extends AecPipe {
    private Native __native;
    private int __tailLength;

    public String getLabel() {
        return "AEC Processor";
    }

    public AecProcessor() {
        this(-1);
    }

    public AecProcessor(AudioConfig audioConfig) {
        this(audioConfig, -1);
    }

    public AecProcessor(AudioConfig audioConfig, int i) {
        this((AudioFormat) new Format(audioConfig), i);
    }

    public AecProcessor(AudioFormat audioFormat) {
        this(audioFormat, -1);
    }

    public AecProcessor(AudioFormat audioFormat, int i) {
        super(audioFormat);
        this.__tailLength = 128;
        if (i != -1) {
            setTailLength(i);
        }
        this.__native = new Native(audioFormat.getClockRate(), audioFormat.getChannelCount());
    }

    public AecProcessor(IAudioOutput iAudioOutput) {
        this(iAudioOutput, -1);
    }

    public AecProcessor(IAudioOutput iAudioOutput, int i) {
        this((AudioFormat) iAudioOutput.getOutputFormat(), i);
        super.addInput(iAudioOutput);
    }

    public AecProcessor(int i) {
        this(new AudioConfig(48000, 2), i);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        super.doDestroy();
        Native nativeR = this.__native;
        if (nativeR != null) {
            nativeR.destroy();
            this.__native = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        Native nativeR = this.__native;
        if (nativeR != null) {
            AudioBuffer capture = nativeR.capture(audioFrame, audioBuffer, (AudioFormat) super.getOutputFormat(), this.__tailLength);
            if (capture != null) {
                audioFrame.addBuffer(capture);
                raiseFrame(audioFrame);
                capture.getDataBuffer().free();
            } else if (capture == null && Global.equals(Platform.getInstance().getSourceLanguage(), SourceLanguage.Java) && Global.equals(Platform.getInstance().getArchitecture(), Architecture.X86)) {
                raiseFrame(audioFrame);
            }
        }
    }

    public int getTailLength() {
        return this.__tailLength;
    }

    /* access modifiers changed from: protected */
    public void processSpeakerFrame(AudioFrame audioFrame) {
        Native nativeR = this.__native;
        if (nativeR != null) {
            nativeR.render(audioFrame);
        }
    }

    public void setTailLength(int i) {
        this.__tailLength = MathAssistant.max(32, MathAssistant.min(512, i));
    }
}
