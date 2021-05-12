package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

public abstract class IMediaInputCollection<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TMediaInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TMediaInputCollection>> extends Collection<TIInput, TMediaInputCollection> {
    private TIOutput _output;

    /* access modifiers changed from: protected */
    public void addSuccessNoLock(TIInput tiinput) {
        super.addSuccess(tiinput);
        tiinput.addInput(getOutput());
    }

    public void destroy() {
        removeAll();
        setOutput((IMediaOutput) null);
    }

    public TIOutput getOutput() {
        return this._output;
    }

    public IMediaInputCollection(TIOutput tioutput) {
        setOutput(tioutput);
    }

    /* access modifiers changed from: protected */
    public void removeSuccessNoLock(TIInput tiinput) {
        super.removeSuccess(tiinput);
        tiinput.removeInput(getOutput());
    }

    public void setOutput(TIOutput tioutput) {
        this._output = tioutput;
    }
}
