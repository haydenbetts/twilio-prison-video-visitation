package fm.liveswitch;

import fm.liveswitch.IInput;
import fm.liveswitch.IOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

public interface IInput<TIOutput extends IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends IElement {
    void addInput(TIOutput tioutput);

    void addInputs(TIOutput[] tioutputArr);

    TIOutput getInput();

    TFormat getInputFormat();

    TIOutput[] getInputs();

    String getPipelineJsonFromInput();

    boolean removeInput(TIOutput tioutput);

    void removeInputs();

    void removeInputs(TIOutput[] tioutputArr);
}
