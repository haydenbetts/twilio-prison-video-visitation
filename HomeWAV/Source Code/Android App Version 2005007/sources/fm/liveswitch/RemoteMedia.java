package fm.liveswitch;

import java.util.ArrayList;

public class RemoteMedia extends Media<AudioTrack, VideoTrack> implements IRemoteMedia<AudioTrack, VideoTrack>, IMedia<AudioTrack, VideoTrack> {
    /* access modifiers changed from: protected */
    public AudioTrack[] arrayFromAudioTracks(ArrayList<AudioTrack> arrayList) {
        return (AudioTrack[]) arrayList.toArray(new AudioTrack[0]);
    }

    /* access modifiers changed from: protected */
    public VideoTrack[] arrayFromVideoTracks(ArrayList<VideoTrack> arrayList) {
        return (VideoTrack[]) arrayList.toArray(new VideoTrack[0]);
    }

    public Future<Object> changeAudioSinkOutput(SinkOutput sinkOutput) {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.changeSinkOutput(sinkOutput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("No audio track."));
        return promise;
    }

    public Future<Object> changeVideoSinkOutput(SinkOutput sinkOutput) {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.changeSinkOutput(sinkOutput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("No video track."));
        return promise;
    }

    /* access modifiers changed from: protected */
    public ArrayList<AudioTrack> createAudioTrackCollection() {
        return new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public ArrayList<VideoTrack> createVideoTrackCollection() {
        return new ArrayList<>();
    }

    public AudioSink getAudioSink() {
        AudioSink[] audioSinks = getAudioSinks();
        if (audioSinks == null || ArrayExtensions.getLength((Object[]) audioSinks) == 0) {
            return null;
        }
        return audioSinks[0];
    }

    public SinkOutput getAudioSinkOutput() {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getSinkOutput();
        }
        return null;
    }

    public Future<SinkOutput[]> getAudioSinkOutputs() {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getSinkOutputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SinkOutput[0]);
        return promise;
    }

    public AudioSink[] getAudioSinks() {
        ArrayList arrayList = new ArrayList();
        for (AudioTrack sinks : getAudioTracks()) {
            AudioSink[] audioSinkArr = (AudioSink[]) sinks.getSinks();
            if (audioSinkArr != null) {
                ArrayListExtensions.addRange(arrayList, (T[]) audioSinkArr);
            }
        }
        return (AudioSink[]) arrayList.toArray(new AudioSink[0]);
    }

    public MediaSinkBase[] getMediaSinks() {
        ArrayList arrayList = new ArrayList();
        ArrayListExtensions.addRange(arrayList, (T[]) getAudioSinks());
        ArrayListExtensions.addRange(arrayList, (T[]) getVideoSinks());
        return (MediaSinkBase[]) arrayList.toArray(new MediaSinkBase[0]);
    }

    public VideoSink getVideoSink() {
        VideoSink[] videoSinks = getVideoSinks();
        if (videoSinks == null || ArrayExtensions.getLength((Object[]) videoSinks) == 0) {
            return null;
        }
        return videoSinks[0];
    }

    public SinkOutput getVideoSinkOutput() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.getSinkOutput();
        }
        return null;
    }

    public Future<SinkOutput[]> getVideoSinkOutputs() {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            return videoTrack.getSinkOutputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SinkOutput[0]);
        return promise;
    }

    public VideoSink[] getVideoSinks() {
        ArrayList arrayList = new ArrayList();
        for (VideoTrack sinks : getVideoTracks()) {
            VideoSink[] videoSinkArr = (VideoSink[]) sinks.getSinks();
            if (videoSinkArr != null) {
                ArrayListExtensions.addRange(arrayList, (T[]) videoSinkArr);
            }
        }
        return (VideoSink[]) arrayList.toArray(new VideoSink[0]);
    }

    public void setAudioSinkOutput(SinkOutput sinkOutput) {
        AudioTrack audioTrack = (AudioTrack) super.getAudioTrack();
        if (audioTrack != null) {
            audioTrack.setSinkOutput(sinkOutput);
        }
    }

    public void setVideoSinkOutput(SinkOutput sinkOutput) {
        VideoTrack videoTrack = (VideoTrack) super.getVideoTrack();
        if (videoTrack != null) {
            videoTrack.setSinkOutput(sinkOutput);
        }
    }
}
