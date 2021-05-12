package fm.liveswitch.matroska;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.Global;
import fm.liveswitch.IAction0;
import fm.liveswitch.IActionDelegate0;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.ILog;
import fm.liveswitch.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudioSink extends fm.liveswitch.AudioSink {
    private static ILog __log = Log.getLogger(AudioSink.class);
    private Object __locker;
    /* access modifiers changed from: private */
    public List<IAction0> __onFileClose;
    /* access modifiers changed from: private */
    public List<IAction0> __onFileOpen;
    private AudioRecorder __recorder;
    private long _baseTimestamp;
    private String _lastFilePath;
    private long _lastTimestamp;
    private IAction0 _onFileClose;
    private IAction0 _onFileOpen;
    private String _path;

    public String getLabel() {
        return "Matroska Audio Sink";
    }

    public void addOnFileClose(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onFileClose == null) {
                this._onFileClose = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(AudioSink.this.__onFileClose).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onFileClose.add(iAction0);
        }
    }

    public void addOnFileOpen(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onFileOpen == null) {
                this._onFileOpen = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(AudioSink.this.__onFileOpen).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onFileOpen.add(iAction0);
        }
    }

    public AudioSink(String str) {
        this.__onFileClose = new ArrayList();
        this.__onFileOpen = new ArrayList();
        this._onFileClose = null;
        this._onFileOpen = null;
        this.__locker = new Object();
        initialize(str);
    }

    public AudioSink(String str, AudioFormat audioFormat) {
        super(audioFormat);
        this.__onFileClose = new ArrayList();
        this.__onFileOpen = new ArrayList();
        this._onFileClose = null;
        this._onFileOpen = null;
        this.__locker = new Object();
        initialize(str);
    }

    public AudioSink(String str, IAudioOutput iAudioOutput) {
        this(str, (AudioFormat) iAudioOutput.getOutputFormat());
        super.addInput(iAudioOutput);
    }

    /* access modifiers changed from: private */
    public void audioSink_OnDisabledChange() {
        if (getDisabled()) {
            synchronized (this.__locker) {
                if (this.__recorder != null) {
                    closeRecorder();
                }
            }
        }
    }

    private void closeRecorder() {
        this.__recorder.close();
        this.__recorder = null;
        IAction0 iAction0 = this._onFileClose;
        if (iAction0 != null) {
            iAction0.invoke();
        }
        setBaseTimestamp(-1);
        setLastTimestamp(-1);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        synchronized (this.__locker) {
            if (this.__recorder != null) {
                closeRecorder();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        synchronized (this.__locker) {
            if (!getDisabled()) {
                if (this.__recorder == null) {
                    openRecorder(audioFrame);
                }
                if (!this.__recorder.getOpened()) {
                    __log.warn("Cannot record. Recorder is not ready.");
                } else if (this.__recorder.write(audioBuffer, audioFrame.getTimestamp())) {
                    setLastTimestamp(audioFrame.getTimestamp());
                }
            }
        }
    }

    public long getBaseTimestamp() {
        return this._baseTimestamp;
    }

    public String getLastFilePath() {
        return this._lastFilePath;
    }

    public long getLastTimestamp() {
        return this._lastTimestamp;
    }

    public String getOpenFilePath() {
        AudioRecorder audioRecorder = this.__recorder;
        if (audioRecorder == null) {
            return null;
        }
        return audioRecorder.getOpenPath();
    }

    public String getPath() {
        return this._path;
    }

    private void initialize(String str) {
        setPath(str);
        super.addOnDisabledChange(new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.matroska.AudioSink.audioSink_OnDisabledChange";
            }

            public void invoke() {
                AudioSink.this.audioSink_OnDisabledChange();
            }
        });
    }

    private void openRecorder(AudioFrame audioFrame) {
        AudioRecorder audioRecorder = new AudioRecorder(getPath(), (AudioFormat) super.getInputFormat());
        this.__recorder = audioRecorder;
        audioRecorder.open();
        setLastFilePath(this.__recorder.getOpenPath());
        setBaseTimestamp(audioFrame.getTimestamp());
        setLastTimestamp(audioFrame.getTimestamp());
        IAction0 iAction0 = this._onFileOpen;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public void removeOnFileClose(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onFileClose, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onFileClose.remove(iAction0);
        if (this.__onFileClose.size() == 0) {
            this._onFileClose = null;
        }
    }

    public void removeOnFileOpen(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onFileOpen, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onFileOpen.remove(iAction0);
        if (this.__onFileOpen.size() == 0) {
            this._onFileOpen = null;
        }
    }

    private void setBaseTimestamp(long j) {
        this._baseTimestamp = j;
    }

    private void setLastFilePath(String str) {
        this._lastFilePath = str;
    }

    private void setLastTimestamp(long j) {
        this._lastTimestamp = j;
    }

    private void setPath(String str) {
        this._path = str;
    }
}
