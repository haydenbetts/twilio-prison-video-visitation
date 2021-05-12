package fm.liveswitch;

class VideoSynchronizeQueue extends SynchronizeQueue<VideoBuffer, VideoBufferCollection, VideoFormat, VideoFrame> {
    public VideoSynchronizeQueue(IAction1<VideoFrame> iAction1) {
        super(StreamType.Video, iAction1);
    }
}
