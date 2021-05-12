package fm.liveswitch;

public class IdentityAudioPipe extends AudioPipe {
    private boolean __canChangeBitrate = false;
    private boolean __canPauseBitrate = false;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        raiseFrame(audioFrame);
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return this.__canChangeBitrate;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return this.__canPauseBitrate;
    }

    public String getLabel() {
        return StringExtensions.format("Identity Audio Pipe ({0})", (Object) ((AudioFormat) super.getOutputFormat()).getFullName());
    }

    public IdentityAudioPipe(AudioFormat audioFormat) {
        super(audioFormat.clone(), audioFormat.clone());
    }

    public void updateCanChangeBitrate(boolean z) {
        this.__canChangeBitrate = z;
    }

    public void updateCanPauseBitrate(boolean z) {
        this.__canPauseBitrate = z;
    }

    public void updateMaxInputBitrate(int i) {
        setMaxInputBitrate(i);
    }

    public void updateMaxInputEncoding(EncodingInfo encodingInfo) {
        setMaxInputEncoding(encodingInfo);
    }

    public void updateMaxOutputBitrate(int i) {
        setMaxOutputBitrate(i);
    }

    public void updateMaxOutputEncoding(EncodingInfo encodingInfo) {
        setMaxOutputEncoding(encodingInfo);
    }

    public void updateMinInputBitrate(int i) {
        setMinInputBitrate(i);
    }

    public void updateMinInputEncoding(EncodingInfo encodingInfo) {
        setMinInputEncoding(encodingInfo);
    }

    public void updateMinOutputBitrate(int i) {
        setMinOutputBitrate(i);
    }

    public void updateMinOutputEncoding(EncodingInfo encodingInfo) {
        setMinOutputEncoding(encodingInfo);
    }

    public void updateTargetOutputBitrate(int i) {
        setTargetOutputBitrate(i);
    }

    public void updateTargetOutputEncoding(EncodingInfo encodingInfo) {
        setTargetOutputEncoding(encodingInfo);
    }
}
