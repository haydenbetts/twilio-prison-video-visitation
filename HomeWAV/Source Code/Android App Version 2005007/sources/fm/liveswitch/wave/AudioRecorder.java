package fm.liveswitch.wave;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.StringExtensions;

public class AudioRecorder extends fm.liveswitch.AudioRecorder {
    private long __dataSize = 0;
    private long __headerSize = 44;
    private AudioFormat __inputFormat;
    private boolean __isPcm;
    private boolean __isPcma;
    private boolean __isPcmu;

    public AudioRecorder(String str, AudioFormat audioFormat) {
        super(str);
        this.__inputFormat = audioFormat;
        if (audioFormat.getIsPcmu()) {
            this.__isPcmu = true;
        } else if (audioFormat.getIsPcma()) {
            this.__isPcma = true;
        } else if (audioFormat.getIsPcm()) {
            this.__isPcm = true;
        } else {
            throw new RuntimeException(new Exception(StringExtensions.format("WAVE audio recorder does not support the '{0}' codec.", (Object) audioFormat.getName())));
        }
    }

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
        if (super.getFile() != null) {
            super.getFileStream().write32To(4, (this.__headerSize - 8) + this.__dataSize);
            super.getFileStream().write32To(40, this.__dataSize);
        }
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(AudioBuffer audioBuffer, long j) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        if (super.getFile() == null) {
            return true;
        }
        this.__dataSize += (long) dataBuffer.getLength();
        super.getFileStream().write(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        DataBufferStream dataBufferStream = new DataBufferStream(44, true);
        dataBufferStream.write8(82);
        dataBufferStream.write8(73);
        dataBufferStream.write8(70);
        dataBufferStream.write8(70);
        dataBufferStream.write32(this.__headerSize - 8);
        dataBufferStream.write8(87);
        dataBufferStream.write8(65);
        dataBufferStream.write8(86);
        dataBufferStream.write8(69);
        dataBufferStream.write8(102);
        dataBufferStream.write8(109);
        dataBufferStream.write8(116);
        dataBufferStream.write8(32);
        dataBufferStream.write32(16);
        int i = 8;
        if (this.__isPcm) {
            dataBufferStream.write16(1);
            i = 16;
        } else if (this.__isPcmu) {
            dataBufferStream.write16(7);
        } else if (this.__isPcma) {
            dataBufferStream.write16(6);
        } else {
            i = 0;
        }
        dataBufferStream.write16(this.__inputFormat.getChannelCount());
        dataBufferStream.write32((long) this.__inputFormat.getClockRate());
        int i2 = i / 8;
        dataBufferStream.write32((long) (this.__inputFormat.getClockRate() * this.__inputFormat.getChannelCount() * i2));
        dataBufferStream.write16(this.__inputFormat.getChannelCount() * i2);
        if (this.__isPcm) {
            dataBufferStream.write16(i);
        } else {
            dataBufferStream.write16(i);
        }
        dataBufferStream.write8(100);
        dataBufferStream.write8(97);
        dataBufferStream.write8(116);
        dataBufferStream.write8(97);
        dataBufferStream.write32(0);
        if (super.getFile() != null) {
            super.getFileStream().write(dataBufferStream.getBuffer().toArray());
        }
    }
}
