package fm.liveswitch;

import java.util.ArrayList;

public class AudioBufferCollection extends MediaBufferCollection<AudioBuffer, AudioBufferCollection, AudioFormat> {
    /* access modifiers changed from: protected */
    public AudioBuffer[] arrayFromList(ArrayList<AudioBuffer> arrayList) {
        return (AudioBuffer[]) arrayList.toArray(new AudioBuffer[0]);
    }

    /* access modifiers changed from: protected */
    public AudioBufferCollection createCollection() {
        return new AudioBufferCollection();
    }
}
