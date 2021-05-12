package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;

class RtpSendBuffer<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> {
    private Object __ioLock = new Object();
    private RtpPacketPair[] __rtpPacketCache;
    private String _name;

    public int getLength() {
        return ArrayExtensions.getLength((Object[]) this.__rtpPacketCache);
    }

    public String getName() {
        return this._name;
    }

    public RtpPacketPair read(int i) {
        RtpPacketPair rtpPacketPair;
        synchronized (this.__ioLock) {
            rtpPacketPair = this.__rtpPacketCache[i % getLength()];
        }
        if (rtpPacketPair == null || rtpPacketPair.getHeader().getSequenceNumber() == i) {
            return rtpPacketPair;
        }
        return null;
    }

    public RtpSendBuffer(String str, int i) {
        setName(str);
        this.__rtpPacketCache = new RtpPacketPair[i];
    }

    private void setName(String str) {
        this._name = str;
    }

    public boolean write(TFrame tframe) {
        synchronized (this.__ioLock) {
            int length = ArrayExtensions.getLength((Object[]) tframe.getBuffers()) - 1;
            DataBuffer[] dataBufferArr = null;
            while (dataBufferArr == null && length >= 0) {
                MediaBuffer mediaBuffer = tframe.getBuffers()[length];
                if (mediaBuffer.getFormat().getIsPacketized()) {
                    dataBufferArr = mediaBuffer.getDataBuffers();
                }
                length--;
            }
            int i = 0;
            if (dataBufferArr == null) {
                return false;
            }
            boolean z = false;
            while (i < ArrayExtensions.getLength((Object[]) dataBufferArr)) {
                dataBufferArr[i].keep();
                RtpPacketPair rtpPacketPair = new RtpPacketPair(tframe.getLastBuffer().getRtpHeaders()[i], dataBufferArr[i]);
                int sequenceNumber = rtpPacketPair.getHeader().getSequenceNumber();
                RtpPacketPair rtpPacketPair2 = this.__rtpPacketCache[sequenceNumber % getLength()];
                if (rtpPacketPair2 != null) {
                    rtpPacketPair2.getPayload().free();
                }
                this.__rtpPacketCache[sequenceNumber % getLength()] = rtpPacketPair;
                i++;
                z = true;
            }
            return z;
        }
    }
}
