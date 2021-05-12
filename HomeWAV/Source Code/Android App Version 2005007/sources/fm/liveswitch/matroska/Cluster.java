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

public class Cluster extends Element {
    private static byte[] _positionId = {-89};
    private static byte[] _prevSizeId = {-85};
    private static byte[] _timecodeId = {-25};
    private BlockGroup[] _blockGroups;
    private NullableLong _position = new NullableLong();
    private NullableLong _prevSize = new NullableLong();
    private SimpleBlock[] _simpleBlocks;
    private long _timecode;

    public static long getDefaultTimecode() {
        return 0;
    }

    public Cluster() {
    }

    public Cluster(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _timecodeId)) {
                setTimecode(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _positionId)) {
                setPosition(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, _prevSizeId)) {
                setPrevSize(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (Element.compare(readId, BlockGroup.getEbmlId())) {
                arrayList.add(new BlockGroup(readValue));
            } else if (Element.compare(readId, SimpleBlock.getEbmlId())) {
                arrayList2.add(new SimpleBlock(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaCluster: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            setBlockGroups((BlockGroup[]) arrayList.toArray(new BlockGroup[0]));
        }
        if (ArrayListExtensions.getCount(arrayList2) > 0) {
            setSimpleBlocks((SimpleBlock[]) arrayList2.toArray(new SimpleBlock[0]));
        }
    }

    public BlockGroup[] getBlockGroups() {
        return this._blockGroups;
    }

    public static byte[] getEbmlId() {
        return new byte[]{31, 67, -74, 117};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        super.writeUnsignedInteger(getTimecode(), _timecodeId, byteOutputStream);
        if (getPosition().getHasValue()) {
            super.writeUnsignedInteger(getPosition().getValue(), _positionId, byteOutputStream);
        }
        if (getPrevSize().getHasValue()) {
            super.writeUnsignedInteger(getPrevSize().getValue(), _prevSizeId, byteOutputStream);
        }
        if (getBlockGroups() != null) {
            for (BlockGroup bytes : getBlockGroups()) {
                byteOutputStream.writeBuffer(bytes.getBytes());
            }
        }
        if (getSimpleBlocks() != null) {
            for (SimpleBlock bytes2 : getSimpleBlocks()) {
                byteOutputStream.writeBuffer(bytes2.getBytes());
            }
        }
        return byteOutputStream.toArray();
    }

    public NullableLong getPosition() {
        return this._position;
    }

    public NullableLong getPrevSize() {
        return this._prevSize;
    }

    public SimpleBlock[] getSimpleBlocks() {
        return this._simpleBlocks;
    }

    public long getTimecode() {
        return this._timecode;
    }

    public void merge(Cluster cluster) {
        if (cluster != null) {
            if (cluster.getBlockGroups() != null) {
                for (BlockGroup block : cluster.getBlockGroups()) {
                    Block block2 = block.getBlock();
                    block2.setTimecode(block2.getTimecode() + ((int) (getTimecode() - cluster.getTimecode())));
                }
            }
            if (cluster.getSimpleBlocks() != null) {
                for (SimpleBlock simpleBlock : cluster.getSimpleBlocks()) {
                    simpleBlock.setTimecode(simpleBlock.getTimecode() + ((int) (getTimecode() - cluster.getTimecode())));
                }
            }
            if (getBlockGroups() == null) {
                setBlockGroups(cluster.getBlockGroups());
            } else if (cluster.getBlockGroups() != null) {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= ArrayExtensions.getLength((Object[]) getBlockGroups()) && i2 >= ArrayExtensions.getLength((Object[]) cluster.getBlockGroups())) {
                        break;
                    }
                    BlockGroup blockGroup = i < ArrayExtensions.getLength((Object[]) getBlockGroups()) ? getBlockGroups()[i] : null;
                    BlockGroup blockGroup2 = i2 < ArrayExtensions.getLength((Object[]) cluster.getBlockGroups()) ? cluster.getBlockGroups()[i2] : null;
                    if (blockGroup == null || blockGroup2 == null) {
                        if (blockGroup != null) {
                            arrayList.add(blockGroup);
                        } else if (blockGroup2 != null) {
                            arrayList.add(blockGroup2);
                            i2++;
                        }
                    } else if (blockGroup.getBlock().getTimecode() <= blockGroup2.getBlock().getTimecode()) {
                        arrayList.add(blockGroup);
                    } else {
                        arrayList.add(blockGroup2);
                        i2++;
                    }
                    i++;
                }
                setBlockGroups((BlockGroup[]) arrayList.toArray(new BlockGroup[0]));
            }
            if (getSimpleBlocks() == null) {
                setSimpleBlocks(cluster.getSimpleBlocks());
            } else if (cluster.getSimpleBlocks() != null) {
                ArrayList arrayList2 = new ArrayList();
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i3 < ArrayExtensions.getLength((Object[]) getSimpleBlocks()) || i4 < ArrayExtensions.getLength((Object[]) cluster.getSimpleBlocks())) {
                        SimpleBlock simpleBlock2 = i3 < ArrayExtensions.getLength((Object[]) getSimpleBlocks()) ? getSimpleBlocks()[i3] : null;
                        SimpleBlock simpleBlock3 = i4 < ArrayExtensions.getLength((Object[]) cluster.getSimpleBlocks()) ? cluster.getSimpleBlocks()[i4] : null;
                        if (simpleBlock2 == null || simpleBlock3 == null) {
                            if (simpleBlock2 != null) {
                                arrayList2.add(simpleBlock2);
                            } else if (simpleBlock3 != null) {
                                arrayList2.add(simpleBlock3);
                                i4++;
                            }
                        } else if (simpleBlock2.getTimecode() <= simpleBlock3.getTimecode()) {
                            arrayList2.add(simpleBlock2);
                        } else {
                            arrayList2.add(simpleBlock3);
                            i4++;
                        }
                        i3++;
                    } else {
                        setSimpleBlocks((SimpleBlock[]) arrayList2.toArray(new SimpleBlock[0]));
                        return;
                    }
                }
            }
        }
    }

    public void setBlockGroups(BlockGroup[] blockGroupArr) {
        this._blockGroups = blockGroupArr;
    }

    public void setPosition(NullableLong nullableLong) {
        this._position = nullableLong;
    }

    public void setPrevSize(NullableLong nullableLong) {
        this._prevSize = nullableLong;
    }

    public void setSimpleBlocks(SimpleBlock[] simpleBlockArr) {
        this._simpleBlocks = simpleBlockArr;
    }

    public void setTimecode(long j) {
        this._timecode = j;
    }
}
