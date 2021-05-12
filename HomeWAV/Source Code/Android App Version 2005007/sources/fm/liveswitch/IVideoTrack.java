package fm.liveswitch;

public interface IVideoTrack extends IMediaTrack {
    void addOnSize(IAction1<Size> iAction1);

    Size getSize();

    Future<VideoBuffer> grabFrame();

    void removeOnSize(IAction1<Size> iAction1);
}
