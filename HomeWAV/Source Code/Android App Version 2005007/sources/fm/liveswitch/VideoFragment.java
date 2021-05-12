package fm.liveswitch;

public class VideoFragment {
    private DataBuffer _buffer;
    private boolean _first;
    private boolean _last;

    public void destroy() {
    }

    public void free() {
        DataBuffer buffer = getBuffer();
        if (buffer != null) {
            buffer.free();
        }
    }

    public DataBuffer getBuffer() {
        return this._buffer;
    }

    public boolean getFirst() {
        return this._first;
    }

    public boolean getLast() {
        return this._last;
    }

    public void keep() {
        DataBuffer buffer = getBuffer();
        if (buffer != null) {
            buffer.keep();
        }
    }

    public void setBuffer(DataBuffer dataBuffer) {
        this._buffer = dataBuffer;
    }

    public void setFirst(boolean z) {
        this._first = z;
    }

    public void setLast(boolean z) {
        this._last = z;
    }
}
