package fm.liveswitch;

public class JitterAudioPipe extends AudioPipe {
    private JitterBuffer<AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> __buffer;

    public String getLabel() {
        return "Jitter Audio Pipe";
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__buffer.destroy();
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        this.__buffer.push(audioFrame);
    }

    public JitterAudioPipe(AudioFormat audioFormat) {
        super(audioFormat, audioFormat);
        this.__buffer = new JitterBuffer<>(((AudioFormat) super.getInputFormat()).getClockRate(), new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaPipe<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.AudioPipe,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat>.raiseFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                JitterAudioPipe.this.raiseFrame(audioFrame);
            }
        });
    }

    public JitterAudioPipe(AudioFormat audioFormat, int i) {
        super(audioFormat, audioFormat);
        JitterBuffer<AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> jitterBuffer = new JitterBuffer<>(((AudioFormat) super.getInputFormat()).getClockRate(), new IActionDelegate1<AudioFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaPipe<fm.liveswitch.IAudioOutput,fm.liveswitch.IAudioOutputCollection,fm.liveswitch.IAudioInput,fm.liveswitch.IAudioInputCollection,fm.liveswitch.AudioPipe,fm.liveswitch.AudioFrame,fm.liveswitch.AudioBuffer,fm.liveswitch.AudioBufferCollection,fm.liveswitch.AudioFormat>.raiseFrame";
            }

            public void invoke(AudioFrame audioFrame) {
                JitterAudioPipe.this.raiseFrame(audioFrame);
            }
        });
        jitterBuffer.setLength(i);
        this.__buffer = jitterBuffer;
    }

    private void onPull(AudioFrame audioFrame) {
        raiseFrame(audioFrame);
    }
}
