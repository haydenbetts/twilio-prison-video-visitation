package fm.liveswitch.yuv4mpeg;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.ILog;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;

public class VideoRecorder extends fm.liveswitch.VideoRecorder {
    private static ILog __log = Log.getLogger(VideoRecorder.class);
    private long __firstPresentationTimestamp = -1;
    private int __frameCount = 0;
    private int __headerHeight = -1;
    private int __headerWidth = -1;
    private VideoFormat __inputFormat = VideoFormat.getI420();
    private long __lastPresentationTimestamp = -1;

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
        int i;
        if (super.getFile() != null) {
            int i2 = this.__headerWidth;
            if (i2 != -1) {
                String intToPaddedString = intToPaddedString(i2, 4);
                super.getFileStream().write8To(11, intToPaddedString.charAt(0));
                super.getFileStream().write8To(12, intToPaddedString.charAt(1));
                super.getFileStream().write8To(13, intToPaddedString.charAt(2));
                super.getFileStream().write8To(14, intToPaddedString.charAt(3));
            }
            int i3 = this.__headerHeight;
            if (i3 != -1) {
                String intToPaddedString2 = intToPaddedString(i3, 4);
                super.getFileStream().write8To(16, intToPaddedString2.charAt(0));
                super.getFileStream().write8To(17, intToPaddedString2.charAt(1));
                super.getFileStream().write8To(18, intToPaddedString2.charAt(2));
                super.getFileStream().write8To(19, intToPaddedString2.charAt(3));
            }
            long j = this.__firstPresentationTimestamp;
            if (j != -1) {
                long j2 = this.__lastPresentationTimestamp;
                if (j2 != -1 && (i = this.__frameCount) != 0) {
                    String intToPaddedString3 = intToPaddedString((int) MathAssistant.round(((double) i) / (((double) (j2 - j)) / ((double) this.__inputFormat.getClockRate()))), 2);
                    super.getFileStream().write8To(21, intToPaddedString3.charAt(0));
                    super.getFileStream().write8To(22, intToPaddedString3.charAt(1));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(VideoBuffer videoBuffer, long j) {
        if (this.__headerWidth == -1) {
            this.__headerWidth = videoBuffer.getWidth();
        }
        if (this.__headerHeight == -1) {
            this.__headerHeight = videoBuffer.getHeight();
        }
        if (this.__firstPresentationTimestamp == -1) {
            this.__firstPresentationTimestamp = j;
        }
        this.__lastPresentationTimestamp = j;
        this.__frameCount++;
        DataBuffer dataBuffer = videoBuffer.getDataBuffer();
        if (super.getFile() != null) {
            super.getFileStream().write8(70);
            super.getFileStream().write8(82);
            super.getFileStream().write8(65);
            super.getFileStream().write8(77);
            super.getFileStream().write8(69);
            if (videoBuffer.getWidth() != this.__headerWidth) {
                super.getFileStream().write8(32);
                super.getFileStream().write8(87);
                String integerExtensions = IntegerExtensions.toString(Integer.valueOf(videoBuffer.getWidth()));
                for (int i = 0; i < StringExtensions.getLength(integerExtensions); i++) {
                    super.getFileStream().write8(integerExtensions.charAt(i));
                }
            }
            if (videoBuffer.getHeight() != this.__headerHeight) {
                super.getFileStream().write8(32);
                super.getFileStream().write8(72);
                String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(videoBuffer.getHeight()));
                for (int i2 = 0; i2 < StringExtensions.getLength(integerExtensions2); i2++) {
                    super.getFileStream().write8(integerExtensions2.charAt(i2));
                }
            }
            super.getFileStream().write8(10);
            super.getFileStream().write(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        DataBufferStream dataBufferStream = new DataBufferStream(32);
        dataBufferStream.write8(89);
        dataBufferStream.write8(85);
        dataBufferStream.write8(86);
        dataBufferStream.write8(52);
        dataBufferStream.write8(77);
        dataBufferStream.write8(80);
        dataBufferStream.write8(69);
        dataBufferStream.write8(71);
        dataBufferStream.write8(50);
        dataBufferStream.write8(32);
        dataBufferStream.write8(87);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(32);
        dataBufferStream.write8(72);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(48);
        dataBufferStream.write8(32);
        dataBufferStream.write8(70);
        dataBufferStream.write8(48);
        dataBufferStream.write8(49);
        dataBufferStream.write8(58);
        dataBufferStream.write8(49);
        dataBufferStream.write8(32);
        dataBufferStream.write8(73);
        dataBufferStream.write8(112);
        dataBufferStream.write8(32);
        dataBufferStream.write8(65);
        dataBufferStream.write8(49);
        dataBufferStream.write8(58);
        dataBufferStream.write8(49);
        dataBufferStream.write8(32);
        dataBufferStream.write8(67);
        dataBufferStream.write8(52);
        dataBufferStream.write8(50);
        dataBufferStream.write8(48);
        dataBufferStream.write8(10);
        if (super.getFile() != null) {
            super.getFileStream().write(dataBufferStream.getBuffer().toArray());
        }
    }

    private static String intToPaddedString(int i, int i2) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(i));
        while (StringExtensions.getLength(integerExtensions) < i2) {
            integerExtensions = StringExtensions.concat("0", integerExtensions);
        }
        return integerExtensions;
    }

    public VideoRecorder(String str) {
        super(str);
    }
}
