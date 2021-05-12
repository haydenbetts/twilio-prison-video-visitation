package fm.liveswitch;

public interface IVideoOutput extends IMediaOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    double getMaxOutputFrameRate();

    int getMaxOutputHeight();

    double getMaxOutputScale();

    Size getMaxOutputSize();

    int getMaxOutputWidth();

    double getMinOutputFrameRate();

    int getMinOutputHeight();

    double getMinOutputScale();

    Size getMinOutputSize();

    int getMinOutputWidth();

    double getTargetOutputFrameRate();

    int getTargetOutputHeight();

    double getTargetOutputScale();

    Size getTargetOutputSize();

    int getTargetOutputWidth();

    VideoType getVideoType();
}
