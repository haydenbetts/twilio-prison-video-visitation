package fm.liveswitch;

import java.util.ArrayList;

public class IAudioInputCollection extends IMediaInputCollection<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat, IAudioInputCollection> {
    /* access modifiers changed from: protected */
    public IAudioInput[] arrayFromList(ArrayList<IAudioInput> arrayList) {
        return (IAudioInput[]) arrayList.toArray(new IAudioInput[0]);
    }

    /* access modifiers changed from: protected */
    public IAudioInputCollection createCollection() {
        return new IAudioInputCollection((IAudioOutput) super.getOutput());
    }

    public IAudioInputCollection(IAudioOutput iAudioOutput) {
        super(iAudioOutput);
    }
}
