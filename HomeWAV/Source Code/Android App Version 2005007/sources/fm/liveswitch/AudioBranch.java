package fm.liveswitch;

import java.util.ArrayList;

public class AudioBranch extends MediaBranch<IAudioOutput, IAudioOutputCollection, IAudioInput, IAudioInputCollection, IAudioElement, AudioSource, AudioSink, AudioPipe, AudioTrack, AudioBranch, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> implements IAudioElement, IMediaElement, IElement {
    public String getLabel() {
        return "Audio Branch";
    }

    /* access modifiers changed from: protected */
    public AudioTrack[] arrayFromTracks(ArrayList<AudioTrack> arrayList) {
        return (AudioTrack[]) arrayList.toArray(new AudioTrack[0]);
    }

    public AudioBranch(AudioTrack[] audioTrackArr) {
        super(audioTrackArr);
    }
}
