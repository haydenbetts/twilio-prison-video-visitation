package fm.liveswitch.matroska;

import com.google.android.exoplayer2.DefaultLoadControl;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.Encoding;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class AudioRecorder extends fm.liveswitch.AudioRecorder {
    private AudioFormat __inputFormat;
    private boolean __isG722;
    private boolean __isOpus;
    private boolean __isPcma;
    private boolean __isPcmu;
    private int _clusterInterval = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
    private Cluster _currentCluster;
    private ArrayList<SimpleBlock> _currentClusterBlocks;
    private double[] _opusFrameSizes = {10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 40.0d, 60.0d, 10.0d, 20.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d, 2.5d, 5.0d, 10.0d, 20.0d};
    private int _segmentSize;
    private int _segmentSizeOffset;

    public AudioRecorder(String str, AudioFormat audioFormat) {
        super(str);
        this.__inputFormat = audioFormat;
        if (audioFormat.getIsOpus()) {
            this.__isOpus = true;
        } else if (audioFormat.getIsPcmu()) {
            this.__isPcmu = true;
        } else if (audioFormat.getIsPcma()) {
            this.__isPcma = true;
        } else if (audioFormat.getIsG722()) {
            this.__isG722 = true;
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Matroska audio recorder does not support the '", audioFormat.getName(), "' codec.")));
        }
    }

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
        flushCluster();
        byte[] serializeVariableInteger = Element.serializeVariableInteger((long) this._segmentSize, 8);
        super.getFile().writeTo(this._segmentSizeOffset, serializeVariableInteger, 0, ArrayExtensions.getLength(serializeVariableInteger));
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(AudioBuffer audioBuffer, long j) {
        int i;
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        int i2 = 2;
        if (!this.__isOpus) {
            i = this.__isG722 ? (dataBuffer.getLength() * 4) / (this.__inputFormat.getChannelCount() * 2) : (dataBuffer.getLength() * 2) / (this.__inputFormat.getChannelCount() * 2);
        } else if (dataBuffer.getLength() > 0) {
            int read8 = dataBuffer.read8(0);
            int i3 = read8 & 3;
            double d = this._opusFrameSizes[(read8 & 248) >> 3];
            if (i3 == 0) {
                i2 = 1;
            } else if (!(i3 == 1 || i3 == 2)) {
                i2 = (i3 != 3 || dataBuffer.getLength() <= 1) ? 0 : dataBuffer.read8(1) & 63;
            }
            i = (int) (((((double) this.__inputFormat.getClockRate()) * d) * ((double) i2)) / 1000.0d);
        } else {
            i = 0;
        }
        if (i <= 0) {
            return false;
        }
        writeFrame(audioBuffer.getDataBuffer(), j);
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        DataBufferStream dataBufferStream;
        String str;
        Ebml ebml = new Ebml();
        ebml.setWriteDefaultValues(true);
        ebml.setDocTypeVersion(4);
        ebml.setDocTypeReadVersion(2);
        byte[] bytes = ebml.getBytes();
        super.getFileStream().write(bytes, 0, ArrayExtensions.getLength(bytes));
        Audio audio = null;
        if (this.__isOpus) {
            str = TrackEntry.getOpusCodecId();
            dataBufferStream = new DataBufferStream(19, true);
            dataBufferStream.writeBytes(Encoding.getUtf8().getBytes("OpusHead"));
            dataBufferStream.write8(1);
            dataBufferStream.write8(this.__inputFormat.getChannelCount());
            dataBufferStream.write16(0);
            dataBufferStream.write32((long) this.__inputFormat.getClockRate());
            dataBufferStream.write16(0);
            dataBufferStream.write8(0);
        } else if (this.__isPcmu || this.__isPcma || this.__isG722) {
            str = TrackEntry.getPcmCodecId();
            audio = new Audio();
            dataBufferStream = new DataBufferStream(18, true);
            if (this.__isPcmu) {
                dataBufferStream.write16(TrackEntry.getPcmuFormatTag());
            } else if (this.__isPcma) {
                dataBufferStream.write16(TrackEntry.getPcmaFormatTag());
            } else if (this.__isG722) {
                dataBufferStream.write16(TrackEntry.getG722FormatTag());
            } else {
                throw new RuntimeException(new Exception("Unrecognized codec."));
            }
            audio.setChannels((long) this.__inputFormat.getChannelCount());
            dataBufferStream.write16(this.__inputFormat.getChannelCount());
            audio.setSamplingFrequency((float) this.__inputFormat.getClockRate());
            dataBufferStream.write32((long) this.__inputFormat.getClockRate());
            dataBufferStream.write32(0);
            dataBufferStream.write16(0);
            if (this.__isPcmu || this.__isPcma) {
                dataBufferStream.write16(8);
            } else if (this.__isG722) {
                dataBufferStream.write16(4);
            } else {
                throw new RuntimeException(new Exception("Unrecognized codec."));
            }
            dataBufferStream.write16(0);
        } else {
            str = "";
            dataBufferStream = null;
        }
        TrackEntry trackEntry = new TrackEntry();
        trackEntry.setTrackNumber(1);
        trackEntry.setTrackType(TrackType.getAudio());
        trackEntry.setCodecId(str);
        trackEntry.setFlagLacing(false);
        trackEntry.setAudio(audio);
        trackEntry.setCodecPrivate(dataBufferStream.getBuffer().toArray());
        Track track = new Track();
        track.setTrackEntries(new TrackEntry[]{trackEntry});
        Segment segment = new Segment();
        segment.setSizeLength(8);
        segment.setSegmentInfo(new SegmentInfo());
        segment.setTracks(new Track[]{track});
        byte[] bytes2 = segment.getBytes();
        super.getFileStream().write(bytes2, 0, ArrayExtensions.getLength(bytes2));
        this._segmentSize = segment.getSize();
        this._segmentSizeOffset = ArrayExtensions.getLength(bytes) + ArrayExtensions.getLength(segment.getId());
        Cluster cluster = new Cluster();
        cluster.setTimecode(0);
        this._currentCluster = cluster;
        this._currentClusterBlocks = new ArrayList<>();
    }

    private void flushCluster() {
        if (ArrayListExtensions.getCount(this._currentClusterBlocks) > 0) {
            this._currentCluster.setSimpleBlocks((SimpleBlock[]) this._currentClusterBlocks.toArray(new SimpleBlock[0]));
            this._currentClusterBlocks.clear();
            byte[] bytes = this._currentCluster.getBytes();
            super.getFile().write(bytes, 0, ArrayExtensions.getLength(bytes));
            this._segmentSize += ArrayExtensions.getLength(bytes);
        }
    }

    private void writeFrame(DataBuffer dataBuffer, long j) {
        long clockRate = (j * 1000) / ((long) this.__inputFormat.getClockRate());
        if (clockRate - this._currentCluster.getTimecode() > ((long) this._clusterInterval)) {
            flushCluster();
            Cluster cluster = new Cluster();
            cluster.setTimecode(clockRate);
            this._currentCluster = cluster;
        }
        SimpleBlock simpleBlock = new SimpleBlock();
        simpleBlock.setTimecode((int) (clockRate - this._currentCluster.getTimecode()));
        simpleBlock.setTrackNumber(1);
        simpleBlock.setData(dataBuffer.toArray());
        this._currentClusterBlocks.add(simpleBlock);
    }
}
