package fm.liveswitch.ogg;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.Encoding;
import fm.liveswitch.StringExtensions;

public class AudioRecorder extends fm.liveswitch.AudioRecorder {
    private String __artist = "Frozen Mountain";
    private AudioFormat __inputFormat;
    private boolean __isOpus;
    private boolean __isPcm;
    private boolean __isPcma;
    private boolean __isPcmu;
    private String __title = "IceLink WebRTC Audio Stream";
    private String __vendor = "Frozen Mountain";
    private int _absoluteGranulePosition;
    private double[] _opusFrameSizes = {10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d};
    private int _sequenceNumber;
    private int _streamSerialNumber;

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
    }

    public AudioRecorder(String str, AudioFormat audioFormat) {
        super(str);
        this.__inputFormat = audioFormat;
        if (audioFormat.getIsOpus()) {
            this.__isOpus = true;
        } else if (audioFormat.getIsPcmu()) {
            this.__isPcmu = true;
        } else if (audioFormat.getIsPcma()) {
            this.__isPcma = true;
        } else if (audioFormat.getIsPcm()) {
            this.__isPcm = true;
        } else {
            throw new RuntimeException(new Exception(StringExtensions.format("Ogg audio recorder does not support the '{0}' codec.", (Object) audioFormat.getName())));
        }
        this._streamSerialNumber = 1;
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(AudioBuffer audioBuffer, long j) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        int i = 2;
        if (this.__isOpus) {
            int read8 = dataBuffer.read8(0);
            int i2 = read8 & 3;
            double d = this._opusFrameSizes[(read8 & 248) >> 3];
            if (i2 == 0) {
                i = 1;
            } else if (!(i2 == 1 || i2 == 2)) {
                i = (i2 != 3 || dataBuffer.getLength() <= 1) ? 0 : dataBuffer.read8(1) & 63;
            }
            this._absoluteGranulePosition += (((int) (((((double) this.__inputFormat.getClockRate()) * d) * ((double) i)) / 1000.0d)) * 48000) / this.__inputFormat.getClockRate();
        } else if (this.__isPcm) {
            this._absoluteGranulePosition += dataBuffer.getLength() / (this.__inputFormat.getChannelCount() * 2);
        } else {
            this._absoluteGranulePosition += (dataBuffer.getLength() * 2) / (this.__inputFormat.getChannelCount() * 2);
        }
        Page page = new Page();
        page.setPayload(dataBuffer.toArray());
        page.setAbsoluteGranulePosition(this._absoluteGranulePosition);
        writePage(page);
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        if (this.__isOpus) {
            writeStreamHeaderOpus();
        } else if (this.__isPcm) {
            if (this.__inputFormat.getLittleEndian()) {
                writeStreamHeaderPcm(2);
            } else {
                writeStreamHeaderPcm(3);
            }
        } else if (this.__isPcmu) {
            writeStreamHeaderPcm(16);
        } else if (this.__isPcma) {
            writeStreamHeaderPcm(17);
        }
    }

    public String getArtist() {
        return this.__artist;
    }

    public String getTitle() {
        return this.__title;
    }

    public String getVendor() {
        return this.__vendor;
    }

    public void setArtist(String str) {
        this.__artist = str;
    }

    public void setTitle(String str) {
        this.__title = str;
    }

    public void setVendor(String str) {
        this.__vendor = str;
    }

    private void writePage(Page page) {
        int i = this._sequenceNumber;
        this._sequenceNumber = i + 1;
        page.setSequenceNumber(i);
        page.setStreamSerialNumber(this._streamSerialNumber);
        if (super.getFile() != null) {
            super.getFileStream().write(page.getBytes());
        }
    }

    private void writeStreamHeaderOpus() {
        DataBufferStream dataBufferStream = new DataBufferStream(19, true);
        dataBufferStream.writeBytes(Encoding.getUtf8().getBytes("OpusHead"));
        dataBufferStream.write8(1);
        dataBufferStream.write8(this.__inputFormat.getChannelCount());
        dataBufferStream.write16(0);
        dataBufferStream.write32((long) this.__inputFormat.getClockRate());
        dataBufferStream.write16(0);
        dataBufferStream.write8(0);
        Page page = new Page();
        page.setAbsoluteGranulePosition(0);
        page.setBeginningOfStream(true);
        page.setPayload(dataBufferStream.getBuffer().toArray());
        writePage(page);
        byte[] bytes = Encoding.getUtf8().getBytes(getVendor());
        String[] strArr = {StringExtensions.concat("title=", getTitle()), StringExtensions.concat("artist=", getArtist())};
        int length = ArrayExtensions.getLength(bytes) + 16;
        for (int i = 0; i < 2; i++) {
            length += StringExtensions.getLength(strArr[i]) + 4;
        }
        DataBufferStream dataBufferStream2 = new DataBufferStream(length, true);
        dataBufferStream2.writeBytes(Encoding.getUtf8().getBytes("OpusTags"));
        dataBufferStream2.write32((long) ArrayExtensions.getLength(bytes));
        dataBufferStream2.writeBytes(bytes);
        dataBufferStream2.write32((long) ArrayExtensions.getLength((Object[]) strArr));
        for (int i2 = 0; i2 < 2; i2++) {
            String str = strArr[i2];
            dataBufferStream2.write32((long) StringExtensions.getLength(str));
            dataBufferStream2.writeBytes(Encoding.getUtf8().getBytes(str));
        }
        Page page2 = new Page();
        page2.setAbsoluteGranulePosition(0);
        page2.setPayload(dataBufferStream2.getBuffer().toArray());
        writePage(page2);
    }

    private void writeStreamHeaderPcm(long j) {
        DataBufferStream dataBufferStream = new DataBufferStream(28, false);
        dataBufferStream.writeBytes(Encoding.getUtf8().getBytes("PCM     "));
        dataBufferStream.write16(0);
        dataBufferStream.write16(0);
        dataBufferStream.write32(j);
        dataBufferStream.write32((long) this.__inputFormat.getClockRate());
        dataBufferStream.write8(0);
        dataBufferStream.write8(this.__inputFormat.getChannelCount());
        dataBufferStream.write16(0);
        dataBufferStream.write32(0);
        Page page = new Page();
        page.setAbsoluteGranulePosition(0);
        page.setBeginningOfStream(true);
        page.setPayload(dataBufferStream.getBuffer().toArray());
        writePage(page);
        byte[] bytes = Encoding.getUtf8().getBytes(getVendor());
        DataBufferStream dataBufferStream2 = new DataBufferStream(ArrayExtensions.getLength(bytes) + 8, false);
        dataBufferStream2.write32((long) ArrayExtensions.getLength(bytes));
        dataBufferStream2.writeBytes(bytes);
        dataBufferStream2.write32(0);
        Page page2 = new Page();
        page2.setAbsoluteGranulePosition(0);
        page2.setPayload(dataBufferStream2.getBuffer().toArray());
        writePage(page2);
    }
}
