package fm.liveswitch;

import fm.liveswitch.IInput;
import fm.liveswitch.IOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

public interface IOutput<TIOutput extends IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends IElement {
    void addOutput(TIInput tiinput);

    void addOutputs(TIInput[] tiinputArr);

    TIInput getOutput();

    TFormat getOutputFormat();

    TIInput[] getOutputs();

    String getPipelineJsonFromOutput();

    boolean removeOutput(TIInput tiinput);

    void removeOutputs();

    void removeOutputs(TIInput[] tiinputArr);
}
