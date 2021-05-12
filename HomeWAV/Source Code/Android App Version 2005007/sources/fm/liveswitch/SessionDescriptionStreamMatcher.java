package fm.liveswitch;

import fm.liveswitch.StreamBase;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaType;
import java.util.ArrayList;

class SessionDescriptionStreamMatcher<TStream extends StreamBase> {
    private ArrayList<Integer> __internalAudioStreamIndex = new ArrayList<>();
    private ArrayList<Integer> __internalDataStreamIndex = new ArrayList<>();
    private int[] __internalStreamIndexByOfferIndex;
    private ArrayList<Integer> __internalVideoStreamIndex = new ArrayList<>();
    private ArrayList<Integer> __offererAudioStreamIndex = new ArrayList<>();
    private ArrayList<Integer> __offererDataStreamIndex = new ArrayList<>();
    private int[] __offererStreamIndexByInternalIndex;
    private ArrayList<Integer> __offererVideoStreamIndex = new ArrayList<>();

    public int getInternalStreamIndexFor(int i) {
        if (ArrayExtensions.getLength(this.__internalStreamIndexByOfferIndex) > i) {
            return this.__internalStreamIndexByOfferIndex[i];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getInternalStreamMediaIndexForStream(StreamType streamType, int i) {
        if (Global.equals(streamType, StreamType.Audio)) {
            return ((Integer) ArrayListExtensions.getItem(this.__internalAudioStreamIndex).get(i)).intValue();
        }
        if (Global.equals(streamType, StreamType.Video)) {
            return ((Integer) ArrayListExtensions.getItem(this.__internalVideoStreamIndex).get(i)).intValue();
        }
        if (Global.equals(streamType, StreamType.Application)) {
            return ((Integer) ArrayListExtensions.getItem(this.__internalDataStreamIndex).get(i)).intValue();
        }
        return -1;
    }

    public int getOffererStreamIndexFor(int i) {
        if (ArrayExtensions.getLength(this.__offererStreamIndexByInternalIndex) > i) {
            return this.__offererStreamIndexByInternalIndex[i];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getOffererStreamMediaIndexForStream(StreamType streamType, int i) {
        if (Global.equals(streamType, StreamType.Audio)) {
            return ((Integer) ArrayListExtensions.getItem(this.__offererAudioStreamIndex).get(i)).intValue();
        }
        if (Global.equals(streamType, StreamType.Video)) {
            return ((Integer) ArrayListExtensions.getItem(this.__offererVideoStreamIndex).get(i)).intValue();
        }
        if (Global.equals(streamType, StreamType.Application)) {
            return ((Integer) ArrayListExtensions.getItem(this.__offererDataStreamIndex).get(i)).intValue();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Error populateInternalStreamTypeIndexes(ArrayList<TStream> arrayList) {
        for (int i = 0; i < ArrayListExtensions.getCount(arrayList); i++) {
            StreamType type = ((StreamBase) ArrayListExtensions.getItem(arrayList).get(i)).getType();
            if (Global.equals(type, StreamType.Audio)) {
                this.__internalAudioStreamIndex.add(Integer.valueOf(i));
            } else if (Global.equals(type, StreamType.Video)) {
                this.__internalVideoStreamIndex.add(Integer.valueOf(i));
            } else if (!Global.equals(type, StreamType.Application)) {
                return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("Sdp offer contains a stream of type {0}. Processing of such streams is not currently supported.", (Object) type.toString())));
            } else {
                this.__internalDataStreamIndex.add(Integer.valueOf(i));
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Error populateOffererStreamTypeIndexes(MediaDescription[] mediaDescriptionArr) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaDescriptionArr); i++) {
            StreamType streamType = MediaType.toStreamType(mediaDescriptionArr[i].getMedia().getMediaType());
            if (Global.equals(streamType, StreamType.Audio)) {
                this.__offererAudioStreamIndex.add(Integer.valueOf(i));
            } else if (Global.equals(streamType, StreamType.Video)) {
                this.__offererVideoStreamIndex.add(Integer.valueOf(i));
            } else if (!Global.equals(streamType, StreamType.Application)) {
                return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("Sdp offer contains a stream of type {0}. Processing of such streams is not currently supported.", (Object) streamType.toString())));
            } else {
                this.__offererDataStreamIndex.add(Integer.valueOf(i));
            }
        }
        return null;
    }

    public void reset() {
        reset(ArrayExtensions.getLength(this.__offererStreamIndexByInternalIndex));
    }

    public void reset(int i) {
        this.__offererAudioStreamIndex.clear();
        this.__offererVideoStreamIndex.clear();
        this.__offererDataStreamIndex.clear();
        this.__offererStreamIndexByInternalIndex = new int[i];
        this.__internalAudioStreamIndex.clear();
        this.__internalVideoStreamIndex.clear();
        this.__internalDataStreamIndex.clear();
        this.__internalStreamIndexByOfferIndex = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.__offererStreamIndexByInternalIndex[i2] = i2;
            this.__internalStreamIndexByOfferIndex[i2] = i2;
        }
    }

    public SessionDescriptionStreamMatcher(int i) {
        reset(i);
    }

    public void setMatchingIndexes(int i, int i2) {
        if (ArrayExtensions.getLength(this.__offererStreamIndexByInternalIndex) > i) {
            this.__offererStreamIndexByInternalIndex[i] = i2;
            this.__internalStreamIndexByOfferIndex[i2] = i;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot set offerer stream index for the internal stream index {0}. Number of streams exceeded.", (Object) IntegerExtensions.toString(Integer.valueOf(i)))));
    }
}
