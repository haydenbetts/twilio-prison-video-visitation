package fm.liveswitch;

import java.util.ArrayList;

public class VideoBranch extends MediaBranch<IVideoOutput, IVideoOutputCollection, IVideoInput, IVideoInputCollection, IVideoElement, VideoSource, VideoSink, VideoPipe, VideoTrack, VideoBranch, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> implements IVideoElement, IMediaElement, IElement {
    public String getLabel() {
        return "Video Branch";
    }

    /* access modifiers changed from: protected */
    public VideoTrack[] arrayFromTracks(ArrayList<VideoTrack> arrayList) {
        return (VideoTrack[]) arrayList.toArray(new VideoTrack[0]);
    }

    public VideoBranch(VideoTrack[] videoTrackArr) {
        super(videoTrackArr);
    }
}
