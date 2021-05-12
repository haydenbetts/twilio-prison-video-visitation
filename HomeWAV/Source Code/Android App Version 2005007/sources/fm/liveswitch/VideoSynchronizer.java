package fm.liveswitch;

public class VideoSynchronizer extends VideoPipe implements ISynchronizer {
    private VideoSynchronizeQueue __queue = new VideoSynchronizeQueue(new IActionDelegate1<VideoFrame>() {
        public String getId() {
            return "fm.liveswitch.MediaPipe<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.VideoPipe,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat>.raiseFrame";
        }

        public void invoke(VideoFrame videoFrame) {
            VideoSynchronizer.this.raiseFrame(videoFrame);
        }
    });

    public String getLabel() {
        return "Video Synchronizer";
    }

    public void activate(boolean z, ISynchronizer[] iSynchronizerArr) {
        this.__queue.activate(z, iSynchronizerArr);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__queue.destroy();
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        this.__queue.processFrame(videoFrame, super.getPipelineSystemDelay(videoBuffer.getFormat()));
    }

    public boolean getActive() {
        return this.__queue.getActive();
    }

    public long getHeadSystemTimestamp() {
        return this.__queue.getHeadSystemTimestamp();
    }

    public boolean getMaster() {
        return this.__queue.getMaster();
    }

    public long getMasterSystemTimestamp() {
        return this.__queue.getMasterSystemTimestamp();
    }

    public long getMaxData() {
        return this.__queue.getMaxData();
    }

    public int getMaxDelay() {
        return this.__queue.getMaxDelay();
    }

    public int getQueueCount() {
        return this.__queue.getCount();
    }

    public ISynchronizer[] getSlaves() {
        return this.__queue.getSlaves();
    }

    public void setMasterSystemTimestamp(long j) {
        this.__queue.setMasterSystemTimestamp(j);
    }

    public void setMaxData(long j) {
        this.__queue.setMaxData(j);
    }

    public void setMaxDelay(int i) {
        this.__queue.setMaxDelay(i);
    }

    public VideoSynchronizer(VideoFormat videoFormat) {
        super(videoFormat.clone(), videoFormat.clone());
    }
}
