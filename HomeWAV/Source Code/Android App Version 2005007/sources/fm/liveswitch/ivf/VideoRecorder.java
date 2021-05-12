package fm.liveswitch.ivf;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.Encoding;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.vp8.Utility;

public class VideoRecorder extends fm.liveswitch.VideoRecorder {
    private int __clockRate;
    private int __frameCount;
    private int __height;
    private volatile boolean __keyFrameWritten;
    private int __width;

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
        super.getFileStream().write16To(12, this.__width);
        super.getFileStream().write16To(14, this.__height);
        super.getFileStream().write32To(16, (long) this.__clockRate);
        super.getFileStream().write32To(24, (long) this.__frameCount);
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(VideoBuffer videoBuffer, long j) {
        if (!this.__keyFrameWritten && Utility.isKeyFrame(videoBuffer.getDataBuffers()[0])) {
            this.__clockRate = ((VideoFormat) videoBuffer.getFormat()).getClockRate();
            this.__width = Utility.deriveWidth(videoBuffer.getDataBuffers()[0]);
            this.__height = Utility.deriveHeight(videoBuffer.getDataBuffers()[0]);
            this.__keyFrameWritten = true;
        }
        DataBuffer dataBuffer = videoBuffer.getDataBuffer();
        if (!this.__keyFrameWritten) {
            return false;
        }
        super.getFileStream().write32((long) dataBuffer.getLength());
        super.getFileStream().write64(j);
        super.getFileStream().write(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
        this.__frameCount++;
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        super.getFileStream().write(Encoding.getUtf8().getBytes("DKIF"));
        super.getFileStream().write16(0);
        super.getFileStream().write16(32);
        super.getFileStream().write(Encoding.getUtf8().getBytes("VP80"));
        super.getFileStream().write16(0);
        super.getFileStream().write16(0);
        super.getFileStream().write32(0);
        super.getFileStream().write32(1);
        super.getFileStream().write32(0);
        super.getFileStream().write32(0);
    }

    public VideoRecorder(String str) {
        super(str);
    }
}
