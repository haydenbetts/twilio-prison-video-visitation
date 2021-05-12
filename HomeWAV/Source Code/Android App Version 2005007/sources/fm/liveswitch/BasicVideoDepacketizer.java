package fm.liveswitch;

public class BasicVideoDepacketizer extends VideoDepacketizer<VideoFragment> {
    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Basic Video Depacketizer";
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return true;
    }

    public BasicVideoDepacketizer(VideoFormat videoFormat) {
        super(videoFormat);
    }

    public BasicVideoDepacketizer(IVideoOutput iVideoOutput) {
        super(iVideoOutput);
    }

    /* access modifiers changed from: protected */
    public VideoFragment doCreateFragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setFirst(true);
        videoFragment.setLast(true);
        videoFragment.setBuffer(dataBuffer);
        return videoFragment;
    }
}
