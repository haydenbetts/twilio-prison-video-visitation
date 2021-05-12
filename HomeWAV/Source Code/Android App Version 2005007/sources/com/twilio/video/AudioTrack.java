package com.twilio.video;

import java.util.HashMap;

public abstract class AudioTrack implements Track {
    private static final Logger logger = Logger.getLogger(AudioTrack.class);
    protected final HashMap<AudioSink, AudioSinkProxy> audioSinks = new HashMap<>();
    private boolean isEnabled;
    private final String name;
    private long nativeAudioTrackHandle;

    private native void nativeAddSink(long j, AudioSink audioSink);

    private native void nativeRemoveSink(long j, AudioSink audioSink);

    /* access modifiers changed from: package-private */
    public abstract boolean isReleased();

    /* access modifiers changed from: package-private */
    public abstract void release();

    AudioTrack(long j, boolean z, String str) {
        this.nativeAudioTrackHandle = j;
        this.isEnabled = z;
        this.name = str;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void addSink(AudioSink audioSink) {
        Preconditions.checkNotNull(audioSink);
        if (isReleased()) {
            logger.w("Cannot add sink to released audio track");
        } else if (!this.audioSinks.containsKey(audioSink)) {
            AudioSinkProxy audioSinkProxy = new AudioSinkProxy(audioSink);
            this.audioSinks.put(audioSink, audioSinkProxy);
            nativeAddSink(this.nativeAudioTrackHandle, audioSinkProxy);
        }
    }

    public synchronized void removeSink(AudioSink audioSink) {
        Preconditions.checkNotNull(audioSink);
        if (!isReleased()) {
            AudioSinkProxy audioSinkProxy = this.audioSinks.get(audioSink);
            if (audioSinkProxy != null) {
                nativeRemoveSink(this.nativeAudioTrackHandle, audioSinkProxy);
                this.audioSinks.remove(audioSink);
                audioSinkProxy.release();
            }
        } else {
            logger.w("Cannot remove sink from released audio track");
        }
    }

    /* access modifiers changed from: package-private */
    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }
}
