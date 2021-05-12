package fm.liveswitch;

import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import java.util.HashMap;

public abstract class MediaFormatCollection<TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> extends Collection<TFormat, TFormatCollection> {
    private Object __equivalenceLookup = new Object();
    private HashMap<String, TFormat> __payloadTypeLookup = new HashMap<>();
    private Object __payloadTypeLookupLock = new Object();

    /* access modifiers changed from: protected */
    public void addSuccess(TFormat tformat) {
        super.addSuccess(tformat);
        if (tformat.getRegisteredPayloadType() >= 0) {
            cachePayloadType(IntegerExtensions.toString(Integer.valueOf(tformat.getRegisteredPayloadType())), tformat);
        }
    }

    private void cachePayloadType(String str, TFormat tformat) {
        synchronized (this.__payloadTypeLookupLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__payloadTypeLookup), str, tformat);
        }
    }

    /* access modifiers changed from: package-private */
    public TFormat getByPayloadType(String str) {
        synchronized (this.__payloadTypeLookupLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__payloadTypeLookup, str, holder);
            TFormat tformat = (MediaFormat) holder.getValue();
            if (tryGetValue) {
                return tformat;
            }
            return null;
        }
    }

    public TFormat getCompatible(TFormat tformat) {
        synchronized (this.__equivalenceLookup) {
            for (TFormat tformat2 : (MediaFormat[]) super.getValues()) {
                if (tformat.isCompatible(tformat2)) {
                    return tformat2;
                }
            }
            return null;
        }
    }

    public TFormat[] getCompatibles(TFormat tformat) {
        MediaFormatCollection mediaFormatCollection = (MediaFormatCollection) createCollection();
        synchronized (this.__equivalenceLookup) {
            for (MediaFormat mediaFormat : (MediaFormat[]) super.getValues()) {
                if (tformat.isCompatible(mediaFormat)) {
                    mediaFormatCollection.add(mediaFormat);
                }
            }
        }
        return (MediaFormat[]) mediaFormatCollection.getValues();
    }

    public TFormat getEquivalent(TFormat tformat) {
        synchronized (this.__equivalenceLookup) {
            for (TFormat tformat2 : (MediaFormat[]) super.getValues()) {
                if (tformat2.isEquivalent(tformat)) {
                    return tformat2;
                }
            }
            return null;
        }
    }

    public TFormat getEquivalent(TFormat tformat, boolean z) {
        synchronized (this.__equivalenceLookup) {
            for (TFormat tformat2 : (MediaFormat[]) super.getValues()) {
                if (tformat2.isEquivalent(tformat, z)) {
                    return tformat2;
                }
            }
            return null;
        }
    }

    public boolean hasCompatible(TFormat tformat) {
        synchronized (this.__equivalenceLookup) {
            for (MediaFormat isCompatible : (MediaFormat[]) super.getValues()) {
                if (isCompatible.isCompatible(tformat)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean hasEquivalent(TFormat tformat) {
        synchronized (this.__equivalenceLookup) {
            for (MediaFormat isEquivalent : (MediaFormat[]) super.getValues()) {
                if (isEquivalent.isEquivalent(tformat)) {
                    return true;
                }
            }
            return false;
        }
    }

    protected MediaFormatCollection() {
    }

    /* access modifiers changed from: protected */
    public void removeSuccess(TFormat tformat) {
        super.removeSuccess(tformat);
        if (tformat.getRegisteredPayloadType() >= 0) {
            uncachePayloadType(IntegerExtensions.toString(Integer.valueOf(tformat.getRegisteredPayloadType())));
        }
    }

    private void uncachePayloadType(String str) {
        synchronized (this.__payloadTypeLookupLock) {
            HashMapExtensions.remove(this.__payloadTypeLookup, str);
        }
    }
}
