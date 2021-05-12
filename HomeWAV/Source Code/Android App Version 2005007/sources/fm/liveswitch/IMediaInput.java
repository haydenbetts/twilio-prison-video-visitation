package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.sdp.MediaDescription;

public interface IMediaInput<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement {
    void addOnDisabledChange(IAction0 iAction0);

    void addOnPausedChange(IAction0 iAction0);

    void addOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1);

    void addOnProcessFrame(IAction1<TFrame> iAction1);

    void addOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1);

    boolean getInputDeactivated();

    boolean getInputMuted();

    String getInputRtpStreamId();

    long getInputSynchronizationSource();

    int getMaxInputBitrate();

    EncodingInfo getMaxInputEncoding();

    int getMinInputBitrate();

    EncodingInfo getMinInputEncoding();

    long getPipelineSystemDelay(TFormat tformat);

    ProcessFramePolicy getProcessFramePolicy();

    long getSystemDelay();

    void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr);

    boolean processFrame(TFrame tframe);

    Error processSdpMediaDescriptionFromInput(MediaDescription mediaDescription, boolean z, boolean z2);

    void processSinkStatsFromInput(MediaSinkStats mediaSinkStats);

    void processTrackStatsFromInput(MediaTrackStats mediaTrackStats);

    void removeOnDisabledChange(IAction0 iAction0);

    void removeOnPausedChange(IAction0 iAction0);

    void removeOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1);

    void removeOnProcessFrame(IAction1<TFrame> iAction1);

    void removeOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1);

    void setInputRtpStreamId(String str);

    void setInputSynchronizationSource(long j);

    void setProcessFramePolicy(ProcessFramePolicy processFramePolicy);
}
