package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.NullableLong;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class BlockGroup extends Element {
    private static byte[] _blockDurationId = {-101};
    private static byte[] _discardPaddingId = {117, -94};
    private static byte[] _referenceBlockId = {-5};
    private Block _block;
    private NullableLong _blockDuration = new NullableLong();
    private NullableLong _discardPadding = new NullableLong();
    private long[] _referenceBlocks;

    public static byte[] getEbmlId() {
        return new byte[]{-96};
    }

    public BlockGroup() {
    }

    public BlockGroup(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, Block.getEbmlId())) {
                setBlock(new Block(readValue));
            } else if (!Element.compare(readId, BlockAdditions.getEbmlId())) {
                if (Element.compare(readId, _referenceBlockId)) {
                    arrayList.add(Long.valueOf(Element.readSignedInteger(readValue)));
                } else if (Element.compare(readId, _blockDurationId)) {
                    setBlockDuration(new NullableLong(Element.readSignedInteger(readValue)));
                } else if (Element.compare(readId, _discardPaddingId)) {
                    setDiscardPadding(new NullableLong(Element.readSignedInteger(readValue)));
                } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                    Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaBlockGroup: ", BitAssistant.getHexString(readId)));
                }
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            long[] jArr = new long[ArrayListExtensions.getCount(arrayList)];
            for (int i2 = 0; i2 < ArrayListExtensions.getCount(arrayList); i2++) {
                jArr[i2] = ((Long) ArrayListExtensions.getItem(arrayList).get(i2)).longValue();
            }
            setReferenceBlocks(jArr);
        }
    }

    public Block getBlock() {
        return this._block;
    }

    public NullableLong getBlockDuration() {
        return this._blockDuration;
    }

    public NullableLong getDiscardPadding() {
        return this._discardPadding;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        if (getBlock() == null) {
            return null;
        }
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        byteOutputStream.writeBuffer(getBlock().getBytes());
        if (getReferenceBlocks() != null) {
            for (long writeSignedInteger : getReferenceBlocks()) {
                super.writeSignedInteger(writeSignedInteger, _referenceBlockId, byteOutputStream);
            }
        }
        if (getBlockDuration().getHasValue()) {
            super.writeSignedInteger(getBlockDuration().getValue(), _blockDurationId, byteOutputStream);
        }
        if (getDiscardPadding().getHasValue()) {
            super.writeSignedInteger(getDiscardPadding().getValue(), _discardPaddingId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public long[] getReferenceBlocks() {
        return this._referenceBlocks;
    }

    public void setBlock(Block block) {
        this._block = block;
    }

    public void setBlockDuration(NullableLong nullableLong) {
        this._blockDuration = nullableLong;
    }

    public void setDiscardPadding(NullableLong nullableLong) {
        this._discardPadding = nullableLong;
    }

    public void setReferenceBlocks(long[] jArr) {
        this._referenceBlocks = jArr;
    }
}
