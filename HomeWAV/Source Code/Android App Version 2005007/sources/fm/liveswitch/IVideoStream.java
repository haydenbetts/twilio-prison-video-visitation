package fm.liveswitch;

public interface IVideoStream extends IMediaStream, IStream {
    void addOnDiscardKeyFrameRequest(IAction1<long[]> iAction1);

    boolean getH264Disabled();

    boolean getVp8Disabled();

    boolean getVp9Disabled();

    void raiseKeyFrameRequest(long[] jArr);

    void removeOnDiscardKeyFrameRequest(IAction1<long[]> iAction1);

    void setH264Disabled(boolean z);

    void setVp8Disabled(boolean z);

    void setVp9Disabled(boolean z);
}
