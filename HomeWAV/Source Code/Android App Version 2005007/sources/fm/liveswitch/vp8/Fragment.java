package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoFragment;

public class Fragment extends VideoFragment {
    private int _keyIndex;
    private boolean _layerSync;
    private boolean _nonReferenceFrame;
    private int _partitionIndex;
    private int _pictureId;
    private int _rtpSequenceNumber;
    private long _rtpTimestamp;
    private boolean _startOfPartition;
    private int _temporalLayerIndex;
    private int _temporalLevelZeroIndex;

    public Fragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        Packet wrap = Packet.wrap(dataBuffer);
        super.setFirst(wrap.getStartOfPartition() && wrap.getPartitionIndex() == 0);
        super.setLast(rtpPacketHeader.getMarker());
        super.setBuffer(wrap.getPayload());
        setRtpTimestamp(rtpPacketHeader.getTimestamp());
        setRtpSequenceNumber(rtpPacketHeader.getSequenceNumber());
        setNonReferenceFrame(wrap.getNonReferenceFrame());
        setStartOfPartition(wrap.getStartOfPartition());
        setPartitionIndex(wrap.getPartitionIndex());
        setPictureId(wrap.getPictureId());
        setTemporalLevelZeroIndex(wrap.getTemporalLevelZeroIndex());
        setTemporalLayerIndex(wrap.getTemporalLayerIndex());
        setLayerSync(wrap.getLayerSync());
        setKeyIndex(wrap.getKeyIndex());
    }

    public int getKeyIndex() {
        return this._keyIndex;
    }

    public boolean getLayerSync() {
        return this._layerSync;
    }

    public boolean getNonReferenceFrame() {
        return this._nonReferenceFrame;
    }

    public int getPartitionIndex() {
        return this._partitionIndex;
    }

    public int getPictureId() {
        return this._pictureId;
    }

    public int getRtpSequenceNumber() {
        return this._rtpSequenceNumber;
    }

    public long getRtpTimestamp() {
        return this._rtpTimestamp;
    }

    public boolean getStartOfPartition() {
        return this._startOfPartition;
    }

    public int getTemporalLayerIndex() {
        return this._temporalLayerIndex;
    }

    public int getTemporalLevelZeroIndex() {
        return this._temporalLevelZeroIndex;
    }

    private void setKeyIndex(int i) {
        this._keyIndex = i;
    }

    private void setLayerSync(boolean z) {
        this._layerSync = z;
    }

    private void setNonReferenceFrame(boolean z) {
        this._nonReferenceFrame = z;
    }

    private void setPartitionIndex(int i) {
        this._partitionIndex = i;
    }

    private void setPictureId(int i) {
        this._pictureId = i;
    }

    private void setRtpSequenceNumber(int i) {
        this._rtpSequenceNumber = i;
    }

    private void setRtpTimestamp(long j) {
        this._rtpTimestamp = j;
    }

    private void setStartOfPartition(boolean z) {
        this._startOfPartition = z;
    }

    private void setTemporalLayerIndex(int i) {
        this._temporalLayerIndex = i;
    }

    private void setTemporalLevelZeroIndex(int i) {
        this._temporalLevelZeroIndex = i;
    }
}
