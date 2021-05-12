package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.LockedRandomizer;
import fm.liveswitch.Log;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.jvm.internal.ByteCompanionObject;

public class Segment extends Element {
    private Cluster[] _clusters;
    private SeekHead[] _seekHeads;
    private SegmentInfo _segmentInfo;
    private Track[] _tracks;

    public Cluster[] getClusters() {
        return this._clusters;
    }

    public static byte[] getEbmlId() {
        return new byte[]{24, 83, ByteCompanionObject.MIN_VALUE, 103};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getSegmentInfo() != null) {
            byteOutputStream.writeBuffer(getSegmentInfo().getBytes());
        }
        if (getSeekHeads() != null) {
            for (SeekHead bytes : getSeekHeads()) {
                byteOutputStream.writeBuffer(bytes.getBytes());
            }
        }
        if (getTracks() != null) {
            for (Track bytes2 : getTracks()) {
                byteOutputStream.writeBuffer(bytes2.getBytes());
            }
        }
        if (getClusters() != null) {
            for (Cluster bytes3 : getClusters()) {
                byteOutputStream.writeBuffer(bytes3.getBytes());
            }
        }
        return byteOutputStream.toArray();
    }

    public SeekHead[] getSeekHeads() {
        return this._seekHeads;
    }

    public SegmentInfo getSegmentInfo() {
        return this._segmentInfo;
    }

    public Track[] getTracks() {
        return this._tracks;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void merge(fm.liveswitch.matroska.Segment r15) {
        /*
            r14 = this;
            if (r15 == 0) goto L_0x012d
            fm.liveswitch.matroska.SegmentInfo r0 = r14.getSegmentInfo()
            if (r0 != 0) goto L_0x0010
            fm.liveswitch.matroska.SegmentInfo r0 = r15.getSegmentInfo()
            r14.setSegmentInfo(r0)
            goto L_0x001b
        L_0x0010:
            fm.liveswitch.matroska.SegmentInfo r0 = r14.getSegmentInfo()
            fm.liveswitch.matroska.SegmentInfo r1 = r15.getSegmentInfo()
            r0.merge(r1)
        L_0x001b:
            fm.liveswitch.matroska.SeekHead[] r0 = r14.getSeekHeads()
            r1 = 0
            if (r0 != 0) goto L_0x002a
            fm.liveswitch.matroska.SeekHead[] r0 = r15.getSeekHeads()
            r14.setSeekHeads(r0)
            goto L_0x004e
        L_0x002a:
            fm.liveswitch.matroska.SeekHead[] r0 = r15.getSeekHeads()
            if (r0 == 0) goto L_0x004e
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            fm.liveswitch.matroska.SeekHead[] r2 = r14.getSeekHeads()
            fm.liveswitch.ArrayListExtensions.addRange(r0, (T[]) r2)
            fm.liveswitch.matroska.SeekHead[] r2 = r15.getSeekHeads()
            fm.liveswitch.ArrayListExtensions.addRange(r0, (T[]) r2)
            fm.liveswitch.matroska.SeekHead[] r2 = new fm.liveswitch.matroska.SeekHead[r1]
            java.lang.Object[] r0 = r0.toArray(r2)
            fm.liveswitch.matroska.SeekHead[] r0 = (fm.liveswitch.matroska.SeekHead[]) r0
            r14.setSeekHeads(r0)
        L_0x004e:
            fm.liveswitch.matroska.Track[] r0 = r14.getTracks()
            r2 = 0
            if (r0 != 0) goto L_0x005e
            fm.liveswitch.matroska.Track[] r0 = r15.getTracks()
            r14.setTracks(r0)
            goto L_0x00c8
        L_0x005e:
            fm.liveswitch.matroska.Track[] r0 = r15.getTracks()
            if (r0 == 0) goto L_0x00c8
            r6 = 1
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            fm.liveswitch.matroska.Track[] r4 = r14.getTracks()
            r3 = r14
            r5 = r2
            r8 = r13
            long r11 = r3.updateTracks(r4, r5, r6, r8)
            fm.liveswitch.matroska.Track[] r9 = r15.getTracks()
            r8 = r14
            r10 = r0
            r8.updateTracks(r9, r10, r11, r13)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            fm.liveswitch.matroska.Track[] r4 = r14.getTracks()
            fm.liveswitch.ArrayListExtensions.addRange(r3, (T[]) r4)
            fm.liveswitch.matroska.Track[] r4 = r15.getTracks()
            int r5 = r4.length
            r6 = 0
            r7 = 0
        L_0x009c:
            if (r6 >= r5) goto L_0x00bc
            r8 = r4[r6]
            fm.liveswitch.matroska.Track[] r9 = r14.getTracks()
            int r9 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r9)
            if (r7 >= r9) goto L_0x00b4
            fm.liveswitch.matroska.Track[] r9 = r14.getTracks()
            r9 = r9[r7]
            r9.merge(r8)
            goto L_0x00b7
        L_0x00b4:
            r3.add(r8)
        L_0x00b7:
            int r7 = r7 + 1
            int r6 = r6 + 1
            goto L_0x009c
        L_0x00bc:
            fm.liveswitch.matroska.Track[] r4 = new fm.liveswitch.matroska.Track[r1]
            java.lang.Object[] r3 = r3.toArray(r4)
            fm.liveswitch.matroska.Track[] r3 = (fm.liveswitch.matroska.Track[]) r3
            r14.setTracks(r3)
            goto L_0x00c9
        L_0x00c8:
            r0 = r2
        L_0x00c9:
            fm.liveswitch.matroska.Cluster[] r3 = r14.getClusters()
            if (r3 != 0) goto L_0x00d7
            fm.liveswitch.matroska.Cluster[] r15 = r15.getClusters()
            r14.setClusters(r15)
            goto L_0x012d
        L_0x00d7:
            fm.liveswitch.matroska.Cluster[] r3 = r15.getClusters()
            if (r3 == 0) goto L_0x012d
            if (r2 == 0) goto L_0x00e6
            fm.liveswitch.matroska.Cluster[] r3 = r14.getClusters()
            r14.updateClusters(r3, r2)
        L_0x00e6:
            if (r0 == 0) goto L_0x00ef
            fm.liveswitch.matroska.Cluster[] r2 = r15.getClusters()
            r14.updateClusters(r2, r0)
        L_0x00ef:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            fm.liveswitch.matroska.Cluster[] r2 = r14.getClusters()
            fm.liveswitch.ArrayListExtensions.addRange(r0, (T[]) r2)
            fm.liveswitch.matroska.Cluster[] r15 = r15.getClusters()
            int r2 = r15.length
            r3 = 0
            r4 = 0
        L_0x0102:
            if (r3 >= r2) goto L_0x0122
            r5 = r15[r3]
            fm.liveswitch.matroska.Cluster[] r6 = r14.getClusters()
            int r6 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r6)
            if (r4 >= r6) goto L_0x011a
            fm.liveswitch.matroska.Cluster[] r6 = r14.getClusters()
            r6 = r6[r4]
            r6.merge(r5)
            goto L_0x011d
        L_0x011a:
            r0.add(r5)
        L_0x011d:
            int r4 = r4 + 1
            int r3 = r3 + 1
            goto L_0x0102
        L_0x0122:
            fm.liveswitch.matroska.Cluster[] r15 = new fm.liveswitch.matroska.Cluster[r1]
            java.lang.Object[] r15 = r0.toArray(r15)
            fm.liveswitch.matroska.Cluster[] r15 = (fm.liveswitch.matroska.Cluster[]) r15
            r14.setClusters(r15)
        L_0x012d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.matroska.Segment.merge(fm.liveswitch.matroska.Segment):void");
    }

    public Segment() {
    }

    public Segment(byte[] bArr) {
        this();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, SegmentInfo.getEbmlId())) {
                setSegmentInfo(new SegmentInfo(readValue));
            } else if (Element.compare(readId, SeekHead.getEbmlId())) {
                arrayList.add(new SeekHead(readValue));
            } else if (Element.compare(readId, Track.getEbmlId())) {
                arrayList2.add(new Track(readValue));
            } else if (Element.compare(readId, Cluster.getEbmlId())) {
                arrayList3.add(new Cluster(readValue));
            } else if (!Element.compare(readId, Cues.getEbmlId()) && !Element.compare(readId, Attachments.getEbmlId()) && !Element.compare(readId, Chapters.getEbmlId()) && !Element.compare(readId, Tags.getEbmlId()) && !Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaSegment: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            setSeekHeads((SeekHead[]) arrayList.toArray(new SeekHead[0]));
        }
        if (ArrayListExtensions.getCount(arrayList2) > 0) {
            setTracks((Track[]) arrayList2.toArray(new Track[0]));
        }
        if (ArrayListExtensions.getCount(arrayList3) > 0) {
            setClusters((Cluster[]) arrayList3.toArray(new Cluster[0]));
        }
    }

    public void setClusters(Cluster[] clusterArr) {
        this._clusters = clusterArr;
    }

    public void setSeekHeads(SeekHead[] seekHeadArr) {
        this._seekHeads = seekHeadArr;
    }

    public void setSegmentInfo(SegmentInfo segmentInfo) {
        this._segmentInfo = segmentInfo;
    }

    public void setTracks(Track[] trackArr) {
        this._tracks = trackArr;
    }

    private void updateClusters(Cluster[] clusterArr, HashMap<String, Long> hashMap) {
        for (Cluster cluster : clusterArr) {
            BlockGroup[] blockGroups = cluster.getBlockGroups();
            if (blockGroups != null) {
                for (BlockGroup block : blockGroups) {
                    Block block2 = block.getBlock();
                    if (block2 != null) {
                        String longExtensions = LongExtensions.toString(Long.valueOf(block2.getTrackNumber()));
                        if (hashMap.containsKey(longExtensions)) {
                            block2.setTrackNumber(HashMapExtensions.getItem(hashMap).get(longExtensions).longValue());
                        }
                    }
                }
            }
            SimpleBlock[] simpleBlocks = cluster.getSimpleBlocks();
            if (simpleBlocks != null) {
                for (SimpleBlock simpleBlock : simpleBlocks) {
                    String longExtensions2 = LongExtensions.toString(Long.valueOf(simpleBlock.getTrackNumber()));
                    if (hashMap.containsKey(longExtensions2)) {
                        simpleBlock.setTrackNumber(HashMapExtensions.getItem(hashMap).get(longExtensions2).longValue());
                    }
                }
            }
        }
    }

    private long updateTracks(Track[] trackArr, HashMap<String, Long> hashMap, long j, ArrayList<Long> arrayList) {
        for (Track trackEntries : trackArr) {
            for (TrackEntry trackEntry : trackEntries.getTrackEntries()) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), LongExtensions.toString(Long.valueOf(trackEntry.getTrackNumber())), Long.valueOf(j));
                trackEntry.setTrackNumber(j);
                j++;
                while (arrayList.contains(Long.valueOf(trackEntry.getTrackUid()))) {
                    trackEntry.setTrackUid(LockedRandomizer.nextLong());
                }
                arrayList.add(Long.valueOf(trackEntry.getTrackUid()));
            }
        }
        return j;
    }
}
