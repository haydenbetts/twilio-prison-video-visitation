package fm.liveswitch;

public class AudioSynchronizer extends AudioPipe implements ISynchronizer {
    private AudioSynchronizeQueue __queue = new AudioSynchronizeQueue(new IActionDelegate1<AudioFrame>() {
        public String getId() {
            return "fm.liveswitch.MediaPipe<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.AudioPipe,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat>.raiseFrame";
        }

        public void invoke(AudioFrame audioFrame) {
            AudioSynchronizer.this.raiseFrame(audioFrame);
        }
    });

    public String getLabel() {
        return "Audio Synchronizer";
    }

    public void activate(boolean z, ISynchronizer[] iSynchronizerArr) {
        this.__queue.activate(z, iSynchronizerArr);
    }

    public AudioSynchronizer(AudioFormat audioFormat) {
        super(audioFormat.clone(), audioFormat.clone());
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__queue.destroy();
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        this.__queue.processFrame(audioFrame, super.getPipelineSystemDelay(audioBuffer.getFormat()));
    }

    public boolean getActive() {
        return this.__queue.getActive();
    }

    public long getHeadSystemTimestamp() {
        return this.__queue.getHeadSystemTimestamp();
    }

    public boolean getMaster() {
        return this.__queue.getMaster();
    }

    public long getMasterSystemTimestamp() {
        return this.__queue.getMasterSystemTimestamp();
    }

    public long getMaxData() {
        return this.__queue.getMaxData();
    }

    public int getMaxDelay() {
        return this.__queue.getMaxDelay();
    }

    public int getQueueCount() {
        return this.__queue.getCount();
    }

    public ISynchronizer[] getSlaves() {
        return this.__queue.getSlaves();
    }

    public void setMasterSystemTimestamp(long j) {
        this.__queue.setMasterSystemTimestamp(j);
    }

    public void setMaxData(long j) {
        this.__queue.setMaxData(j);
    }

    public void setMaxDelay(int i) {
        this.__queue.setMaxDelay(i);
    }
}
