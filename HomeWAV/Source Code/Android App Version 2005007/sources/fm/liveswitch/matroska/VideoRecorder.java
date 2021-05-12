package fm.liveswitch.matroska;

import com.google.android.exoplayer2.DefaultLoadControl;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.vp8.Utility;
import java.util.ArrayList;

public class VideoRecorder extends fm.liveswitch.VideoRecorder {
    private VideoFormat __inputFormat;
    private boolean __isH264;
    private boolean __isVp8;
    private boolean __isVp9;
    private int _clusterInterval = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
    private Cluster _currentCluster;
    private ArrayList<SimpleBlock> _currentClusterBlocks;
    private int _frameCount;
    private volatile boolean _keyFrameWritten;
    private int _segmentSize;
    private int _segmentSizeOffset;

    /* access modifiers changed from: protected */
    public void doUpdateHeader() {
        flushCluster();
        byte[] serializeVariableInteger = Element.serializeVariableInteger((long) this._segmentSize, 8);
        super.getFile().writeTo(this._segmentSizeOffset, serializeVariableInteger, 0, ArrayExtensions.getLength(serializeVariableInteger));
    }

    /* access modifiers changed from: protected */
    public boolean doWrite(VideoBuffer videoBuffer, long j) {
        DataBuffer dataBuffer = videoBuffer.getDataBuffer();
        if (!this._keyFrameWritten) {
            if (this.__isVp8) {
                if (!Utility.isKeyFrame(dataBuffer)) {
                    return false;
                }
                this._keyFrameWritten = true;
            } else if (this.__isVp9) {
                if (!fm.liveswitch.vp9.Utility.isKeyFrame(dataBuffer)) {
                    return false;
                }
                this._keyFrameWritten = true;
            } else if (this.__isH264) {
                if (!fm.liveswitch.h264.Utility.isKeyFrame(dataBuffer)) {
                    return false;
                }
                this._keyFrameWritten = true;
            }
        }
        writeFrame(dataBuffer, j);
        this._frameCount++;
        return true;
    }

    /* access modifiers changed from: protected */
    public void doWriteHeader() {
        String str;
        Ebml ebml = new Ebml();
        ebml.setWriteDefaultValues(true);
        ebml.setDocTypeVersion(4);
        ebml.setDocTypeReadVersion(2);
        byte[] bytes = ebml.getBytes();
        super.getFileStream().write(bytes, 0, ArrayExtensions.getLength(bytes));
        if (this.__isVp8) {
            str = TrackEntry.getVp8CodecId();
        } else if (this.__isVp9) {
            str = TrackEntry.getVp9CodecId();
        } else {
            str = this.__isH264 ? TrackEntry.getH264CodecId() : "";
        }
        TrackEntry trackEntry = new TrackEntry();
        trackEntry.setTrackNumber(1);
        trackEntry.setTrackType(TrackType.getVideo());
        trackEntry.setCodecId(str);
        trackEntry.setFlagLacing(false);
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

    public VideoRecorder(String str, VideoFormat videoFormat) {
        super(str);
        if (videoFormat.getIsVp8()) {
            this.__isVp8 = true;
        } else if (videoFormat.getIsVp9()) {
            this.__isVp9 = true;
        } else if (videoFormat.getIsH264()) {
            this.__isH264 = true;
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Matroska video recorder does not currently support the '", videoFormat.getName(), "' codec.")));
        }
        this.__inputFormat = videoFormat;
    }

    private void writeFrame(DataBuffer dataBuffer, long j) {
        long clockRate = (j * 1000) / ((long) this.__inputFormat.getClockRate());
        if (clockRate - this._currentCluster.getTimecode() > ((long) this._clusterInterval)) {
            flushCluster();
            Cluster cluster = new Cluster();
            cluster.setTimecode(clockRate);
            this._currentCluster = cluster;
        }
        byte b = 0;
        if ((this.__isVp8 && Utility.isKeyFrame(dataBuffer)) || ((this.__isH264 && fm.liveswitch.h264.Utility.isKeyFrame(dataBuffer)) || (this.__isVp9 && fm.liveswitch.vp9.Utility.isKeyFrame(dataBuffer)))) {
            b = SimpleBlockFlags.getKeyframe();
        }
        SimpleBlock simpleBlock = new SimpleBlock();
        simpleBlock.setFlags(b);
        simpleBlock.setTimecode((int) (clockRate - this._currentCluster.getTimecode()));
        simpleBlock.setTrackNumber(1);
        simpleBlock.setData(dataBuffer.toArray());
        this._currentClusterBlocks.add(simpleBlock);
    }
}
