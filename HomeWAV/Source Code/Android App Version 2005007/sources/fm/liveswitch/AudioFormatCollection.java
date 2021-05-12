package fm.liveswitch;

import java.util.ArrayList;

public class AudioFormatCollection extends MediaFormatCollection<AudioFormat, AudioFormatCollection> {
    /* access modifiers changed from: protected */
    public AudioFormat[] arrayFromList(ArrayList<AudioFormat> arrayList) {
        return (AudioFormat[]) arrayList.toArray(new AudioFormat[0]);
    }

    /* access modifiers changed from: protected */
    public AudioFormatCollection createCollection() {
        return new AudioFormatCollection();
    }
}
