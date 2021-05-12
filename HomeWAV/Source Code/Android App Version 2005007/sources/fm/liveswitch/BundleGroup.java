package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

class BundleGroup {
    private ArrayList<Stream> __streams = new ArrayList<>();
    private boolean _bundleOnly;
    private Stream _taggedMSection;

    public void add(Stream stream) {
        this.__streams.add(stream);
    }

    public boolean getBundleOnly() {
        return this._bundleOnly;
    }

    /* access modifiers changed from: package-private */
    public CoreTransport getCoreTransport() {
        if (getTaggedMSection() != null) {
            return getTaggedMSection().getCoreTransportRtp();
        }
        return null;
    }

    public String[] getMediaStreamIdentifiers() {
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) getStreams())];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getStreams()); i++) {
            strArr[i] = getStreams()[i].getMediaStreamIdentification();
        }
        return strArr;
    }

    public Stream[] getStreams() {
        Stream taggedMSection = getTaggedMSection();
        if (getTaggedMSection() == null) {
            return (Stream[]) this.__streams.toArray(new Stream[0]);
        }
        Stream[] streamArr = new Stream[ArrayListExtensions.getCount(this.__streams)];
        streamArr[0] = taggedMSection;
        Iterator<Stream> it = this.__streams.iterator();
        int i = 1;
        while (it.hasNext()) {
            Stream next = it.next();
            if (!Global.equals(next.getId(), taggedMSection.getId())) {
                streamArr[i] = next;
                i++;
            }
        }
        return streamArr;
    }

    public Stream getTaggedMSection() {
        return this._taggedMSection;
    }

    public boolean remove(String str) {
        Stream stream = null;
        if (getTaggedMSection() != null && Global.equals(getTaggedMSection().getMediaStreamIdentification(), str)) {
            setTaggedMSection((Stream) null);
        }
        Iterator<Stream> it = this.__streams.iterator();
        while (it.hasNext()) {
            Stream next = it.next();
            if (Global.equals(next.getMediaStreamIdentification(), str)) {
                stream = next;
            }
        }
        return stream != null && remove(stream);
    }

    public boolean remove(Stream stream) {
        return remove(stream.getMediaStreamIdentification());
    }

    public void setBundleOnly(boolean z) {
        this._bundleOnly = z;
    }

    public void setTaggedMSection(Stream stream) {
        this._taggedMSection = stream;
    }
}
