package fm.liveswitch;

import java.util.ArrayList;

public class IVideoInputCollection extends IMediaInputCollection<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat, IVideoInputCollection> {
    /* access modifiers changed from: protected */
    public IVideoInput[] arrayFromList(ArrayList<IVideoInput> arrayList) {
        return (IVideoInput[]) arrayList.toArray(new IVideoInput[0]);
    }

    /* access modifiers changed from: protected */
    public IVideoInputCollection createCollection() {
        return new IVideoInputCollection((IVideoOutput) super.getOutput());
    }

    public IVideoInputCollection(IVideoOutput iVideoOutput) {
        super(iVideoOutput);
    }
}
