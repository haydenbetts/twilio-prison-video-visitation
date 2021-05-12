package fm.liveswitch;

public class JitterVideoPipe extends VideoPipe {
    private JitterBuffer<VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> __buffer;

    public String getLabel() {
        return "Jitter Video Pipe";
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__buffer.destroy();
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        this.__buffer.push(videoFrame);
    }

    public JitterVideoPipe(VideoFormat videoFormat) {
        super(videoFormat, videoFormat);
        this.__buffer = new JitterBuffer<>(((VideoFormat) super.getInputFormat()).getClockRate(), new IActionDelegate1<VideoFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaPipe<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.VideoPipe,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat>.raiseFrame";
            }

            public void invoke(VideoFrame videoFrame) {
                JitterVideoPipe.this.raiseFrame(videoFrame);
            }
        });
    }

    public JitterVideoPipe(VideoFormat videoFormat, int i) {
        super(videoFormat, videoFormat);
        JitterBuffer<VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> jitterBuffer = new JitterBuffer<>(((VideoFormat) super.getInputFormat()).getClockRate(), new IActionDelegate1<VideoFrame>() {
            public String getId() {
                return "fm.liveswitch.MediaPipe<fm.liveswitch.IVideoOutput,fm.liveswitch.IVideoOutputCollection,fm.liveswitch.IVideoInput,fm.liveswitch.IVideoInputCollection,fm.liveswitch.VideoPipe,fm.liveswitch.VideoFrame,fm.liveswitch.VideoBuffer,fm.liveswitch.VideoBufferCollection,fm.liveswitch.VideoFormat>.raiseFrame";
            }

            public void invoke(VideoFrame videoFrame) {
                JitterVideoPipe.this.raiseFrame(videoFrame);
            }
        });
        jitterBuffer.setLength(i);
        this.__buffer = jitterBuffer;
    }

    private void onPull(VideoFrame videoFrame) {
        raiseFrame(videoFrame);
    }
}
