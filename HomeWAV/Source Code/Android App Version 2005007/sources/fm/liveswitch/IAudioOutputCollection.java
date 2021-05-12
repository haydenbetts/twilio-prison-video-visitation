package fm.liveswitch;

import java.util.ArrayList;

public class IAudioOutputCollection extends IMediaOutputCollection<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat, IAudioOutputCollection> {
    /* access modifiers changed from: protected */
    public IAudioOutput[] arrayFromList(ArrayList<IAudioOutput> arrayList) {
        return (IAudioOutput[]) arrayList.toArray(new IAudioOutput[0]);
    }

    /* access modifiers changed from: protected */
    public IAudioOutputCollection createCollection() {
        return new IAudioOutputCollection((IAudioInput) super.getInput());
    }

    public IAudioOutputCollection(IAudioInput iAudioInput) {
        super(iAudioInput);
    }
}
