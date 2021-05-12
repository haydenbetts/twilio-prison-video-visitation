package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.sdp.MediaDescription;

public interface IMediaOutput<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement {
    void addOnDisabledChange(IAction0 iAction0);

    void addOnPausedChange(IAction0 iAction0);

    void addOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1);

    void addOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1);

    void addOnRaiseFrame(IAction1<TFrame> iAction1);

    int getCcmSequenceNumber();

    int getMaxOutputBitrate();

    EncodingInfo getMaxOutputEncoding();

    int getMinOutputBitrate();

    EncodingInfo getMinOutputEncoding();

    boolean getOutputDeactivated();

    boolean getOutputMuted();

    String getOutputRtpStreamId();

    boolean getOutputSynchronizable();

    long getOutputSynchronizationSource();

    long getPipelineSystemDelay(TFormat tformat);

    long getSystemDelay();

    int getTargetOutputBitrate();

    EncodingInfo getTargetOutputEncoding();

    void incrementCcmSequenceNumber();

    void processControlFrames(MediaControlFrame[] mediaControlFrameArr);

    Error processSdpMediaDescriptionFromOutput(MediaDescription mediaDescription, boolean z, boolean z2);

    void processSourceStatsFromOutput(MediaSourceStats mediaSourceStats);

    void processTrackStatsFromOutput(MediaTrackStats mediaTrackStats);

    void removeOnDisabledChange(IAction0 iAction0);

    void removeOnPausedChange(IAction0 iAction0);

    void removeOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1);

    void removeOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1);

    void removeOnRaiseFrame(IAction1<TFrame> iAction1);
}
