package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaFormat;
import org.bytedeco.ffmpeg.global.avcodec;

public abstract class MediaRecorder<TBuffer extends MediaBuffer<TFormat, TBuffer>, TFormat extends MediaFormat<TFormat>> {
    private TFormat __format = null;
    private String __openPath;
    private String __path;
    private long _basePresentationTimestamp = -1;
    private FileStream _file;
    private FileAssistant _fileStream;
    private int _flushEvery = avcodec.AV_CODEC_ID_PROBE;
    private boolean _wasOpened;
    private long _writtenSinceLastFlush = 0;

    /* access modifiers changed from: protected */
    public abstract void doUpdateHeader();

    /* access modifiers changed from: protected */
    public abstract boolean doWrite(TBuffer tbuffer, long j);

    /* access modifiers changed from: protected */
    public abstract void doWriteHeader();

    public void close() {
        if (getFile() != null) {
            doUpdateHeader();
            getFile().close();
            setFile((FileStream) null);
            this.__openPath = null;
        }
    }

    /* access modifiers changed from: protected */
    public FileStream getFile() {
        return this._file;
    }

    /* access modifiers changed from: protected */
    public FileAssistant getFileStream() {
        return this._fileStream;
    }

    public TFormat getFormat() {
        return this.__format;
    }

    public boolean getOpened() {
        return getWasOpened();
    }

    public String getOpenPath() {
        return this.__openPath;
    }

    public String getPath() {
        return this.__path;
    }

    /* access modifiers changed from: protected */
    public boolean getWasOpened() {
        return this._wasOpened;
    }

    public MediaRecorder(String str) {
        this.__path = str;
    }

    public void open() {
        try {
            setFile(new FileStream(getPath()));
            int i = 1;
            while (getFile().exists()) {
                String substring = getPath().substring(StringExtensions.lastIndexOf(getPath(), "."));
                setFile(new FileStream(StringExtensions.format("{0}-{1}{2}", StringExtensions.substring(getPath(), 0, StringExtensions.getLength(getPath()) - StringExtensions.getLength(substring)), IntegerExtensions.toString(Integer.valueOf(i)), substring)));
                i++;
            }
            getFile().open(FileStreamAccess.Write);
            this.__openPath = getFile().getPath();
            setFileStream(new FileAssistant(getFile()));
            getFileStream().setLittleEndian(true);
            doWriteHeader();
            setWasOpened(true);
        } catch (Exception e) {
            Log.error("Recorder cannot open file.", e);
            setWasOpened(false);
        }
    }

    /* access modifiers changed from: protected */
    public void setFile(FileStream fileStream) {
        this._file = fileStream;
    }

    /* access modifiers changed from: protected */
    public void setFileStream(FileAssistant fileAssistant) {
        this._fileStream = fileAssistant;
    }

    /* access modifiers changed from: protected */
    public void setWasOpened(boolean z) {
        this._wasOpened = z;
    }

    public boolean write(TBuffer tbuffer, long j) {
        String str;
        if (getFile() != null) {
            if (this._basePresentationTimestamp == -1) {
                this._basePresentationTimestamp = j;
            }
            boolean doWrite = doWrite(tbuffer, j - this._basePresentationTimestamp);
            if (doWrite) {
                long length = this._writtenSinceLastFlush + ((long) tbuffer.getDataBuffer().getLength());
                this._writtenSinceLastFlush = length;
                if (length > ((long) this._flushEvery)) {
                    getFile().flush();
                    this._writtenSinceLastFlush = 0;
                }
            }
            return doWrite;
        }
        String concat = StringExtensions.concat("Cannot write to ", this.__path, ".");
        if (getWasOpened()) {
            str = StringExtensions.concat(concat, " The file was closed.");
        } else {
            str = StringExtensions.concat(concat, " The file must be opened first.");
        }
        Log.error(str);
        return false;
    }
}
