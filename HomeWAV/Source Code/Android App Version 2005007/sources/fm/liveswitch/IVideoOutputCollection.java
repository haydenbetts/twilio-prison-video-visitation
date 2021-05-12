package fm.liveswitch;

import java.util.ArrayList;

public class IVideoOutputCollection extends IMediaOutputCollection<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat, IVideoOutputCollection> {
    /* access modifiers changed from: protected */
    public IVideoOutput[] arrayFromList(ArrayList<IVideoOutput> arrayList) {
        return (IVideoOutput[]) arrayList.toArray(new IVideoOutput[0]);
    }

    /* access modifiers changed from: protected */
    public IVideoOutputCollection createCollection() {
        return new IVideoOutputCollection((IVideoInput) super.getInput());
    }

    public IVideoOutputCollection(IVideoInput iVideoInput) {
        super(iVideoInput);
    }
}
