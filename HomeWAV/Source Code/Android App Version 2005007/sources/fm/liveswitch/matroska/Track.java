package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class Track extends Element {
    private TrackEntry[] _trackEntries;

    public static byte[] getEbmlId() {
        return new byte[]{22, 84, -82, 107};
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (getTrackEntries() != null) {
            for (TrackEntry bytes : getTrackEntries()) {
                byteOutputStream.writeBuffer(bytes.getBytes());
            }
        }
        return byteOutputStream.toArray();
    }

    public TrackEntry[] getTrackEntries() {
        return this._trackEntries;
    }

    public void merge(Track track) {
        if (track != null) {
            ArrayList arrayList = new ArrayList();
            ArrayListExtensions.addRange(arrayList, (T[]) getTrackEntries());
            ArrayListExtensions.addRange(arrayList, (T[]) track.getTrackEntries());
            setTrackEntries((TrackEntry[]) arrayList.toArray(new TrackEntry[0]));
        }
    }

    public void setTrackEntries(TrackEntry[] trackEntryArr) {
        this._trackEntries = trackEntryArr;
    }

    public Track() {
    }

    public Track(byte[] bArr) {
        this();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, TrackEntry.getEbmlId())) {
                arrayList.add(new TrackEntry(readValue));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaTrack: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            setTrackEntries((TrackEntry[]) arrayList.toArray(new TrackEntry[0]));
        }
    }
}
