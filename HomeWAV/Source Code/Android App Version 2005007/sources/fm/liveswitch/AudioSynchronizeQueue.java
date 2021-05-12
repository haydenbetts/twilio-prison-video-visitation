package fm.liveswitch;

class AudioSynchronizeQueue extends SynchronizeQueue<AudioBuffer, AudioBufferCollection, AudioFormat, AudioFrame> {
    public AudioSynchronizeQueue(IAction1<AudioFrame> iAction1) {
        super(StreamType.Audio, iAction1);
    }
}
