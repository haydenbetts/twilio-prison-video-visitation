package fm.liveswitch;

import java.util.ArrayList;

public class SdesChunk {
    private static ILog __log = Log.getLogger(SdesChunk.class);
    private DataBuffer _dataBuffer;
    private int _length;
    private SdesItem[] _sourceDescriptionItems;

    public static int getFixedPayloadHeaderLength() {
        return 4;
    }

    public String getCanonicalName() {
        for (SdesItem sdesItem : getSourceDescriptionItems()) {
            if (sdesItem.getType() == SdesItemType.getCanonicalName()) {
                return sdesItem.getText();
            }
        }
        return null;
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    /* access modifiers changed from: package-private */
    public int getLength() {
        return this._length;
    }

    public String getMid() {
        for (SdesItem sdesItem : getSourceDescriptionItems()) {
            if (sdesItem.getType() == SdesItemType.getMid()) {
                return sdesItem.getText();
            }
        }
        return null;
    }

    public SdesItem[] getSourceDescriptionItems() {
        return this._sourceDescriptionItems;
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public SdesChunk(DataBuffer dataBuffer) {
        this(dataBuffer, 0);
    }

    SdesChunk(DataBuffer dataBuffer, int i) {
        if (dataBuffer.getLength() >= getFixedPayloadHeaderLength()) {
            int fixedPayloadHeaderLength = getFixedPayloadHeaderLength() + i;
            ArrayList arrayList = new ArrayList();
            while (true) {
                if (fixedPayloadHeaderLength >= dataBuffer.getLength()) {
                    break;
                }
                int read8 = dataBuffer.read8(fixedPayloadHeaderLength);
                int i2 = fixedPayloadHeaderLength + 1;
                if (read8 == 0) {
                    int i3 = 4 - (i2 % 4);
                    setLength((i2 + (i3 == 4 ? 0 : i3)) - i);
                } else if (i2 >= dataBuffer.getLength()) {
                    __log.warn(StringExtensions.format("Malformed SDES chunk of type {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(read8))));
                    break;
                } else {
                    int read82 = dataBuffer.read8(i2);
                    int i4 = i2 + 1 + read82;
                    if (i4 >= dataBuffer.getLength()) {
                        __log.warn(StringExtensions.format("Malformed SDES chunk of type {0} and length {1}.", IntegerExtensions.toString(Integer.valueOf(read8)), IntegerExtensions.toString(Integer.valueOf(read82))));
                        break;
                    } else {
                        arrayList.add(new SdesItem(dataBuffer.subset(fixedPayloadHeaderLength, read82 + 2)));
                        fixedPayloadHeaderLength = i4;
                    }
                }
            }
            setSourceDescriptionItems((SdesItem[]) arrayList.toArray(new SdesItem[0]));
            setDataBuffer(dataBuffer.subset(i, getLength()));
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("DataBuffer must be at least {0} bytes for an SDES chunk.", (Object) IntegerExtensions.toString(Integer.valueOf(getFixedPayloadHeaderLength())))));
    }

    public SdesChunk(long j, SdesItem sdesItem) {
        this(j, new SdesItem[]{sdesItem});
    }

    public SdesChunk(long j, SdesItem[] sdesItemArr) {
        int fixedPayloadHeaderLength = getFixedPayloadHeaderLength();
        for (SdesItem dataBuffer : sdesItemArr) {
            fixedPayloadHeaderLength += dataBuffer.getDataBuffer().getLength();
        }
        int i = fixedPayloadHeaderLength + (4 - (fixedPayloadHeaderLength % 4));
        setDataBuffer(DataBuffer.allocate(i));
        setSynchronizationSource(j);
        setSourceDescriptionItems(sdesItemArr);
        int fixedPayloadHeaderLength2 = getFixedPayloadHeaderLength();
        for (SdesItem sdesItem : sdesItemArr) {
            getDataBuffer().write(sdesItem.getDataBuffer(), fixedPayloadHeaderLength2);
            fixedPayloadHeaderLength2 += sdesItem.getDataBuffer().getLength();
        }
        setLength(i);
    }

    private void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    private void setLength(int i) {
        this._length = i;
    }

    private void setSourceDescriptionItems(SdesItem[] sdesItemArr) {
        this._sourceDescriptionItems = sdesItemArr;
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }
}
