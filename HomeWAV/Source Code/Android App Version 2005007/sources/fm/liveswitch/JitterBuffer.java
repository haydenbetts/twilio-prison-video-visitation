package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import java.util.ArrayList;
import java.util.Iterator;

class JitterBuffer<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> {
    private static ILog __log = Log.getLogger(JitterBuffer.class);
    private PriorityQueue<TFrame> __frameQueue;
    private Object __frameQueueLock;
    private long __lastRaisedSequenceNumber;
    private long __lastRaisedTimestamp;
    private long __startSystemTimestamp;
    private long __startTimestamp;
    private int _clockRate;
    private IFunction0<Long> _getSystemTimestamp;
    private int _length;
    private IAction1<TFrame> _onFrame;

    /* access modifiers changed from: private */
    public CompareResult compareBySeqeunceNumber(TFrame tframe, TFrame tframe2) {
        long sequenceNumber = tframe.getSequenceNumber();
        if (sequenceNumber == -1) {
            sequenceNumber = tframe.getLastBuffer().getSequenceNumber();
        }
        long sequenceNumber2 = tframe2.getSequenceNumber();
        if (sequenceNumber2 == -1) {
            sequenceNumber2 = tframe2.getLastBuffer().getSequenceNumber();
        }
        int i = ((sequenceNumber - sequenceNumber2) > 0 ? 1 : ((sequenceNumber - sequenceNumber2) == 0 ? 0 : -1));
        if (i > 0) {
            return CompareResult.Positive;
        }
        if (i < 0) {
            return CompareResult.Negative;
        }
        return CompareResult.Equal;
    }

    public void destroy() {
        processFrames(true, false);
    }

    public void flush() {
        processFrames(true, true);
    }

    public int getClockRate() {
        return this._clockRate;
    }

    /* access modifiers changed from: package-private */
    public IFunction0<Long> getGetSystemTimestamp() {
        return this._getSystemTimestamp;
    }

    public int getLength() {
        return this._length;
    }

    public IAction1<TFrame> getOnFrame() {
        return this._onFrame;
    }

    public JitterBuffer(int i, IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            setClockRate(i);
            setOnFrame(iAction1);
            setLength(100);
            setGetSystemTimestamp(new IFunctionDelegate0<Long>() {
                public String getId() {
                    return "fm.liveswitch.ManagedStopwatch.getTimestamp";
                }

                public Long invoke() {
                    return Long.valueOf(ManagedStopwatch.getTimestamp());
                }
            });
            this.__frameQueue = new PriorityQueue<>(new IFunctionDelegate2<TFrame, TFrame, CompareResult>() {
                public String getId() {
                    return "fm.liveswitch.JitterBuffer<TFrame,TBuffer,TBufferCollection,TFormat>.compareBySeqeunceNumber";
                }

                public CompareResult invoke(TFrame tframe, TFrame tframe2) {
                    return JitterBuffer.this.compareBySeqeunceNumber(tframe, tframe2);
                }
            });
            this.__frameQueueLock = new Object();
            this.__startSystemTimestamp = -1;
            this.__startTimestamp = -1;
            this.__lastRaisedTimestamp = -1;
            this.__lastRaisedSequenceNumber = -1;
            return;
        }
        throw new RuntimeException(new Exception("Frame callback cannot be null."));
    }

    private void processFrames(boolean z, boolean z2) {
        long longValue = ((getGetSystemTimestamp().invoke().longValue() - this.__startSystemTimestamp) / ((long) Constants.getTicksPerMillisecond())) - ((long) getLength());
        ArrayList arrayList = new ArrayList();
        synchronized (this.__frameQueue) {
            while (true) {
                if (this.__frameQueue.getCount() <= 0) {
                    break;
                }
                MediaFrame mediaFrame = (MediaFrame) this.__frameQueue.peek();
                if (!z && ((mediaFrame.getTimestamp() - this.__startTimestamp) * ((long) Constants.getMillisecondsPerSecond())) / ((long) getClockRate()) > longValue) {
                    break;
                }
                arrayList.add(this.__frameQueue.dequeue());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MediaFrame mediaFrame2 = (MediaFrame) it.next();
            ArrayList arrayList2 = new ArrayList();
            for (MediaBuffer dataBuffers : mediaFrame2.getBuffers()) {
                for (DataBuffer add : dataBuffers.getDataBuffers()) {
                    arrayList2.add(add);
                }
            }
            if (z2) {
                long sequenceNumber = mediaFrame2.getSequenceNumber();
                if (sequenceNumber == -1) {
                    sequenceNumber = mediaFrame2.getLastBuffer().getSequenceNumber();
                }
                if (sequenceNumber > this.__lastRaisedSequenceNumber) {
                    this.__lastRaisedTimestamp = mediaFrame2.getTimestamp();
                    this.__lastRaisedSequenceNumber = sequenceNumber;
                    getOnFrame().invoke(mediaFrame2);
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ((DataBuffer) it2.next()).free();
            }
        }
    }

    public boolean push(TFrame tframe) {
        if (this.__lastRaisedTimestamp != -1 && tframe.getTimestamp() < this.__lastRaisedTimestamp) {
            return false;
        }
        tframe.keep();
        synchronized (this.__frameQueueLock) {
            if (this.__startSystemTimestamp == -1) {
                this.__startSystemTimestamp = getGetSystemTimestamp().invoke().longValue();
                this.__startTimestamp = tframe.getTimestamp();
            }
            this.__frameQueue.enqueue(tframe);
        }
        raiseFrames();
        return true;
    }

    private void raiseFrames() {
        processFrames(false, true);
    }

    private void setClockRate(int i) {
        this._clockRate = i;
    }

    /* access modifiers changed from: package-private */
    public void setGetSystemTimestamp(IFunction0<Long> iFunction0) {
        this._getSystemTimestamp = iFunction0;
    }

    public void setLength(int i) {
        this._length = i;
    }

    private void setOnFrame(IAction1<TFrame> iAction1) {
        this._onFrame = iAction1;
    }
}
