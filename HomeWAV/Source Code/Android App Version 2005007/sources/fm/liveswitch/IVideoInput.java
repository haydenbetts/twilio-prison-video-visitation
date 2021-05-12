package fm.liveswitch;

public interface IVideoInput extends IMediaInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    double getMaxInputFrameRate();

    int getMaxInputHeight();

    double getMaxInputScale();

    Size getMaxInputSize();

    int getMaxInputWidth();

    double getMinInputFrameRate();

    int getMinInputHeight();

    double getMinInputScale();

    Size getMinInputSize();

    int getMinInputWidth();
}
