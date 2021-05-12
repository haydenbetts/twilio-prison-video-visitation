package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

public abstract class IMediaOutputCollection<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TMediaOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TMediaOutputCollection>> extends Collection<TIOutput, TMediaOutputCollection> {
    private TIInput _input;

    /* access modifiers changed from: protected */
    public void addSuccessNoLock(TIOutput tioutput) {
        super.addSuccess(tioutput);
        tioutput.addOutput(getInput());
    }

    public void destroy() {
        removeAll();
        setInput((IMediaInput) null);
    }

    public TIInput getInput() {
        return this._input;
    }

    public IMediaOutputCollection(TIInput tiinput) {
        setInput(tiinput);
    }

    /* access modifiers changed from: protected */
    public void removeSuccessNoLock(TIOutput tioutput) {
        super.removeSuccess(tioutput);
        tioutput.removeOutput(getInput());
    }

    public void setInput(TIInput tiinput) {
        this._input = tiinput;
    }
}
