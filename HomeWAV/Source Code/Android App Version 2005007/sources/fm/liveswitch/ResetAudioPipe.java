package fm.liveswitch;

public class ResetAudioPipe extends AudioPipe {
    private IFunction1<MediaControlFrame, Boolean> _controlFrameAllowed;
    private IFunction1<MediaControlFrame, Boolean> _controlFrameResponseAllowed;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaControlFrameArr); i++) {
            if (!isControlFrameResponseAllowed(mediaControlFrameArr[i])) {
                mediaControlFrameArr = MediaControlFrame.removeControlFrame(mediaControlFrameArr, i);
            }
        }
        super.doProcessControlFrameResponses(mediaControlFrameArr);
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaControlFrameArr); i++) {
            if (!isControlFrameAllowed(mediaControlFrameArr[i])) {
                mediaControlFrameArr = MediaControlFrame.removeControlFrame(mediaControlFrameArr, i);
            }
        }
        super.doProcessControlFrames(mediaControlFrameArr);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioBuffer audioBuffer2 = (AudioBuffer) audioFrame.getLastBuffer();
        AudioFrame clone = audioFrame.clone();
        clone.removeBuffers();
        if (audioBuffer2 != null) {
            clone.addBuffer(audioBuffer2);
        }
        raiseFrame(clone);
    }

    public IFunction1<MediaControlFrame, Boolean> getControlFrameAllowed() {
        return this._controlFrameAllowed;
    }

    public IFunction1<MediaControlFrame, Boolean> getControlFrameResponseAllowed() {
        return this._controlFrameResponseAllowed;
    }

    public String getLabel() {
        return StringExtensions.format("Reset Audio Pipe ({0})", (Object) ((AudioFormat) super.getOutputFormat()).getFullName());
    }

    private boolean isControlFrameAllowed(MediaControlFrame mediaControlFrame) {
        IFunction1<MediaControlFrame, Boolean> controlFrameAllowed = getControlFrameAllowed();
        if (controlFrameAllowed == null) {
            return mediaControlFrame instanceof TmmbrControlFrame;
        }
        return controlFrameAllowed.invoke(mediaControlFrame).booleanValue();
    }

    private boolean isControlFrameResponseAllowed(MediaControlFrame mediaControlFrame) {
        IFunction1<MediaControlFrame, Boolean> controlFrameResponseAllowed = getControlFrameResponseAllowed();
        if (controlFrameResponseAllowed == null) {
            return mediaControlFrame instanceof TmmbnControlFrame;
        }
        return controlFrameResponseAllowed.invoke(mediaControlFrame).booleanValue();
    }

    public ResetAudioPipe(AudioFormat audioFormat) {
        super(audioFormat, audioFormat);
    }

    public void setControlFrameAllowed(IFunction1<MediaControlFrame, Boolean> iFunction1) {
        this._controlFrameAllowed = iFunction1;
    }

    public void setControlFrameResponseAllowed(IFunction1<MediaControlFrame, Boolean> iFunction1) {
        this._controlFrameResponseAllowed = iFunction1;
    }
}
