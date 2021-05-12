package fm.liveswitch;

public class NullAudioSource extends AudioSource {
    private boolean __canChangeBitrate = false;
    private boolean __canPauseBitrate = false;
    private String __inputRtpStreamId = null;
    private long __inputSynchronizationSource = -1;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        return PromiseBase.resolveNow();
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        return PromiseBase.resolveNow();
    }

    public void generateAndProcessPcmFrame() {
        generateAndProcessPcmFrame(20);
    }

    public void generateAndProcessPcmFrame(int i) {
        if (((AudioFormat) super.getOutputFormat()).getIsPcm()) {
            processFrame(AudioFrame.generatePcmFrame(i, super.getConfig()));
            return;
        }
        throw new RuntimeException(new Exception("Cannot generate a PCM frame. Source output format is not PCM."));
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return this.__canChangeBitrate;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return this.__canPauseBitrate;
    }

    public String getInputRtpStreamId() {
        return this.__inputRtpStreamId;
    }

    public long getInputSynchronizationSource() {
        return this.__inputSynchronizationSource;
    }

    public String getLabel() {
        return StringExtensions.format("Null Audio Source ({0})", (Object) ((AudioFormat) super.getOutputFormat()).getFullName());
    }

    public NullAudioSource(AudioFormat audioFormat) {
        super(audioFormat);
        super.setDisableTimestampReset(true);
    }

    public void processControlFrameResponse(MediaControlFrame mediaControlFrame) {
        super.raiseControlFrameResponse(mediaControlFrame);
    }

    public void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        super.raiseControlFrameResponses(mediaControlFrameArr);
    }

    public void processFrame(AudioFrame audioFrame) {
        raiseFrame(audioFrame);
    }

    public void setInputRtpStreamId(String str) {
        this.__inputRtpStreamId = str;
    }

    public void setInputSynchronizationSource(long j) {
        this.__inputSynchronizationSource = j;
    }

    public void updateCanChangeBitrate(boolean z) {
        this.__canChangeBitrate = z;
    }

    public void updateCanPauseBitrate(boolean z) {
        this.__canPauseBitrate = z;
    }

    public void updateMaxOutputBitrate(int i) {
        setMaxOutputBitrate(i);
    }

    public void updateMaxOutputEncoding(EncodingInfo encodingInfo) {
        setMaxOutputEncoding(encodingInfo);
    }

    public void updateMinOutputBitrate(int i) {
        setMinOutputBitrate(i);
    }

    public void updateMinOutputEncoding(EncodingInfo encodingInfo) {
        setMinOutputEncoding(encodingInfo);
    }

    public void updateOutputSynchronizable(boolean z) {
        super.setOutputSynchronizable(z);
    }

    public void updateSystemDelay(long j) {
        super.setSystemDelay(j);
    }

    public void updateTargetOutputBitrate(int i) {
        setTargetOutputBitrate(i);
    }

    public void updateTargetOutputEncoding(EncodingInfo encodingInfo) {
        setTargetOutputEncoding(encodingInfo);
    }
}
