package fm.liveswitch;

import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;

class RtpParameters<TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> implements RtpIParameters, RtpIFormatParameters<TFormat> {
    private Object __localContributingSourcesLock = new Object();
    private Object __localSynchronizationSourcesLock = new Object();
    private TFormatCollection __negotiatedFormats;
    private ManagedConcurrentDictionary<Integer, TFormat> __payloadTypeRegistry;
    private Object __remoteSynchronizationSourcesLock = new Object();
    private String _canonicalName;
    private int[] _initialReceiveBandwidthEstimates;
    private int[] _initialSendBandwidthEstimates;
    private long[] _localContributingSources;
    private String[] _localRepairedRtpStreamIds;
    private String[] _localRtpStreamIds;
    private long[] _localSynchronizationSources;
    private TFormat _redFormat;
    private String[] _remoteRepairedRtpStreamIds;
    private String[] _remoteRtpStreamIds;
    private long[] _remoteSynchronizationSources;
    private TFormat _ulpFecFormat;

    private static int[] addIntegerValue(int[] iArr, int i) {
        int[] iArr2 = new int[(ArrayExtensions.getLength(iArr) + 1)];
        int i2 = 0;
        for (int i3 = 0; i3 < ArrayExtensions.getLength(iArr); i3++) {
            iArr2[i2] = iArr[i3];
            i2++;
        }
        iArr2[i2] = i;
        return iArr2;
    }

    public boolean addLocalContributingSource(long j) {
        synchronized (this.__localContributingSourcesLock) {
            if (hasLocalContributingSource(j)) {
                return false;
            }
            setLocalContributingSources(addLongValue(getLocalContributingSources(), j));
            return true;
        }
    }

    public boolean addLocalSynchronizationSource(long j, String str, String str2, int i) {
        synchronized (this.__localSynchronizationSourcesLock) {
            if (hasLocalSynchronizationSource(j)) {
                return false;
            }
            if (hasLocalRtpStreamId(str)) {
                return false;
            }
            if (hasLocalRepairedRtpStreamId(str2)) {
                return false;
            }
            setLocalSynchronizationSources(addLongValue(getLocalSynchronizationSources(), j));
            setLocalRtpStreamIds(addStringValue(getLocalRtpStreamIds(), str));
            setLocalRepairedRtpStreamIds(addStringValue(getLocalRepairedRtpStreamIds(), str2));
            setInitialSendBandwidthEstimates(addIntegerValue(getInitialSendBandwidthEstimates(), i));
            return true;
        }
    }

    private static long[] addLongValue(long[] jArr, long j) {
        long[] jArr2 = new long[(ArrayExtensions.getLength(jArr) + 1)];
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength(jArr); i2++) {
            jArr2[i] = jArr[i2];
            i++;
        }
        jArr2[i] = j;
        return jArr2;
    }

    public boolean addRemoteSynchronizationSource(long j, String str, String str2, int i) {
        synchronized (this.__remoteSynchronizationSourcesLock) {
            if (hasRemoteSynchronizationSource(j)) {
                return false;
            }
            if (hasRemoteRtpStreamId(str)) {
                return false;
            }
            if (hasRemoteRepairedRtpStreamId(str2)) {
                return false;
            }
            setRemoteSynchronizationSources(addLongValue(getRemoteSynchronizationSources(), j));
            setRemoteRtpStreamIds(addStringValue(getRemoteRtpStreamIds(), str));
            setRemoteRepairedRtpStreamIds(addStringValue(getRemoteRepairedRtpStreamIds(), str2));
            setInitialReceiveBandwidthEstimates(addIntegerValue(getInitialReceiveBandwidthEstimates(), i));
            return true;
        }
    }

    private static String[] addStringValue(String[] strArr, String str) {
        String[] strArr2 = new String[(ArrayExtensions.getLength((Object[]) strArr) + 1)];
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) strArr); i2++) {
            strArr2[i] = strArr[i2];
            i++;
        }
        strArr2[i] = str;
        return strArr2;
    }

    public boolean addSynchronizationSource(long j, String str, String str2, int i, boolean z) {
        if (z) {
            return addLocalSynchronizationSource(j, str, str2, i);
        }
        return addRemoteSynchronizationSource(j, str, str2, i);
    }

    public String getCanonicalName() {
        return this._canonicalName;
    }

    public int getInitialReceiveBandwidthEstimate(String str) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getRemoteRtpStreamIds()); i++) {
            if (Global.equals(getRemoteRtpStreamIds()[i], str)) {
                return getInitialReceiveBandwidthEstimates()[i];
            }
        }
        return -1;
    }

    public int getInitialReceiveBandwidthEstimate(long j) {
        for (int i = 0; i < ArrayExtensions.getLength(getRemoteSynchronizationSources()); i++) {
            if (getRemoteSynchronizationSources()[i] == j) {
                return getInitialReceiveBandwidthEstimates()[i];
            }
        }
        return -1;
    }

    public int[] getInitialReceiveBandwidthEstimates() {
        return this._initialReceiveBandwidthEstimates;
    }

    public int getInitialSendBandwidthEstimate(String str) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getLocalRtpStreamIds()); i++) {
            if (Global.equals(getLocalRtpStreamIds()[i], str)) {
                return getInitialSendBandwidthEstimates()[i];
            }
        }
        return -1;
    }

    public int getInitialSendBandwidthEstimate(long j) {
        for (int i = 0; i < ArrayExtensions.getLength(getLocalSynchronizationSources()); i++) {
            if (getLocalSynchronizationSources()[i] == j) {
                return getInitialSendBandwidthEstimates()[i];
            }
        }
        return -1;
    }

    public int[] getInitialSendBandwidthEstimates() {
        return this._initialSendBandwidthEstimates;
    }

    private static int getIntegerValueIndex(int[] iArr, int i) {
        if (i < 0) {
            return -1;
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength(iArr); i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public long[] getLocalContributingSources() {
        return this._localContributingSources;
    }

    public String getLocalRepairedRtpStreamId(String str) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getLocalRtpStreamIds()); i++) {
            if (Global.equals(getLocalRtpStreamIds()[i], str)) {
                return getLocalRepairedRtpStreamIds()[i];
            }
        }
        return null;
    }

    public String getLocalRepairedRtpStreamId(long j) {
        for (int i = 0; i < ArrayExtensions.getLength(getLocalSynchronizationSources()); i++) {
            if (getLocalSynchronizationSources()[i] == j) {
                return getLocalRepairedRtpStreamIds()[i];
            }
        }
        return null;
    }

    public String[] getLocalRepairedRtpStreamIds() {
        return this._localRepairedRtpStreamIds;
    }

    public String[] getLocalRtpStreamIds() {
        return this._localRtpStreamIds;
    }

    public long[] getLocalSynchronizationSources() {
        return this._localSynchronizationSources;
    }

    private static int getLongValueIndex(long[] jArr, long j) {
        if (j < 0) {
            return -1;
        }
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            if (jArr[i] == j) {
                return i;
            }
        }
        return -1;
    }

    public TFormat getNegotiatedFormat(int i) {
        Holder holder = new Holder(null);
        this.__payloadTypeRegistry.tryGetValue(Integer.valueOf(i), holder);
        return (MediaFormat) holder.getValue();
    }

    public TFormat[] getNegotiatedFormats() {
        return (MediaFormat[]) this.__negotiatedFormats.getValues();
    }

    public int getNegotiatedPayloadType(TFormat tformat) {
        if (tformat == null) {
            return -1;
        }
        for (MediaFormat mediaFormat : (MediaFormat[]) this.__negotiatedFormats.getValues()) {
            if (tformat.isCompatible(mediaFormat)) {
                return mediaFormat.getRegisteredPayloadType();
            }
        }
        return -1;
    }

    public TFormat getRedFormat() {
        return this._redFormat;
    }

    public String getRemoteRepairedRtpStreamId(String str) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getRemoteRtpStreamIds()); i++) {
            if (Global.equals(getRemoteRtpStreamIds()[i], str)) {
                return getRemoteRepairedRtpStreamIds()[i];
            }
        }
        return null;
    }

    public String getRemoteRepairedRtpStreamId(long j) {
        for (int i = 0; i < ArrayExtensions.getLength(getRemoteSynchronizationSources()); i++) {
            if (getRemoteSynchronizationSources()[i] == j) {
                return getRemoteRepairedRtpStreamIds()[i];
            }
        }
        return null;
    }

    public String[] getRemoteRepairedRtpStreamIds() {
        return this._remoteRepairedRtpStreamIds;
    }

    public String[] getRemoteRtpStreamIds() {
        return this._remoteRtpStreamIds;
    }

    public long[] getRemoteSynchronizationSources() {
        return this._remoteSynchronizationSources;
    }

    private static int getStringValueIndex(String[] strArr, String str) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
            if (Global.equals(strArr[i], str)) {
                return i;
            }
        }
        return -1;
    }

    public TFormat getUlpFecFormat() {
        return this._ulpFecFormat;
    }

    public boolean hasLocalContributingSource(long j) {
        for (long j2 : getLocalContributingSources()) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLocalRepairedRtpStreamId(String str) {
        if (str != null) {
            for (String equals : getLocalRepairedRtpStreamIds()) {
                if (Global.equals(equals, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasLocalRtpStreamId(String str) {
        if (str != null) {
            for (String equals : getLocalRtpStreamIds()) {
                if (Global.equals(equals, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasLocalSynchronizationSource(long j) {
        for (long j2 : getLocalSynchronizationSources()) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNegotiatedPayloadType(int i) {
        return this.__payloadTypeRegistry.containsKey(Integer.valueOf(i));
    }

    public boolean hasRemoteRepairedRtpStreamId(String str) {
        if (str != null) {
            for (String equals : getRemoteRepairedRtpStreamIds()) {
                if (Global.equals(equals, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasRemoteRtpStreamId(String str) {
        if (str != null) {
            for (String equals : getRemoteRtpStreamIds()) {
                if (Global.equals(equals, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasRemoteSynchronizationSource(long j) {
        if (j >= 0) {
            for (long j2 : getRemoteSynchronizationSources()) {
                if (j2 == j) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[] removeIntegerValue(int[] iArr, int i) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        int[] removeIntegerValue = removeIntegerValue(iArr, i, integerHolder);
        integerHolder.getValue();
        return removeIntegerValue;
    }

    private static int[] removeIntegerValue(int[] iArr, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(getIntegerValueIndex(iArr, i));
        return removeIntegerValueAt(iArr, integerHolder.getValue());
    }

    private static int[] removeIntegerValueAt(int[] iArr, int i) {
        if (i < 0) {
            return iArr;
        }
        int[] iArr2 = new int[(ArrayExtensions.getLength(iArr) - 1)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ArrayExtensions.getLength(iArr)) {
            if (i2 == i) {
                i2++;
            } else {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
        }
        return iArr2;
    }

    public boolean removeLocalContributingSource(long j) {
        synchronized (this.__localContributingSourcesLock) {
            if (!hasLocalContributingSource(j)) {
                return false;
            }
            setLocalContributingSources(removeLongValue(getLocalContributingSources(), j));
            return true;
        }
    }

    public boolean removeLocalSynchronizationSource(long j) {
        synchronized (this.__localSynchronizationSourcesLock) {
            if (!hasLocalSynchronizationSource(j)) {
                return false;
            }
            IntegerHolder integerHolder = new IntegerHolder(0);
            long[] removeLongValue = removeLongValue(getLocalSynchronizationSources(), j, integerHolder);
            int value = integerHolder.getValue();
            setLocalSynchronizationSources(removeLongValue);
            setLocalRtpStreamIds(removeStringValueAt(getLocalRtpStreamIds(), value));
            setLocalRepairedRtpStreamIds(removeStringValueAt(getLocalRepairedRtpStreamIds(), value));
            setInitialSendBandwidthEstimates(removeIntegerValueAt(getInitialSendBandwidthEstimates(), value));
            return true;
        }
    }

    private static long[] removeLongValue(long[] jArr, long j) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        long[] removeLongValue = removeLongValue(jArr, j, integerHolder);
        integerHolder.getValue();
        return removeLongValue;
    }

    private static long[] removeLongValue(long[] jArr, long j, IntegerHolder integerHolder) {
        integerHolder.setValue(getLongValueIndex(jArr, j));
        return removeLongValueAt(jArr, integerHolder.getValue());
    }

    private static long[] removeLongValueAt(long[] jArr, int i) {
        if (i < 0) {
            return jArr;
        }
        long[] jArr2 = new long[(ArrayExtensions.getLength(jArr) - 1)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ArrayExtensions.getLength(jArr)) {
            if (i2 == i) {
                i2++;
            } else {
                jArr2[i3] = jArr[i2];
                i3++;
                i2++;
            }
        }
        return jArr2;
    }

    public boolean removeRemoteSynchronizationSource(long j) {
        synchronized (this.__remoteSynchronizationSourcesLock) {
            if (!hasRemoteSynchronizationSource(j)) {
                return false;
            }
            IntegerHolder integerHolder = new IntegerHolder(0);
            long[] removeLongValue = removeLongValue(getRemoteSynchronizationSources(), j, integerHolder);
            int value = integerHolder.getValue();
            setRemoteSynchronizationSources(removeLongValue);
            setRemoteRtpStreamIds(removeStringValueAt(getRemoteRtpStreamIds(), value));
            setRemoteRepairedRtpStreamIds(removeStringValueAt(getRemoteRepairedRtpStreamIds(), value));
            setInitialReceiveBandwidthEstimates(removeIntegerValueAt(getInitialReceiveBandwidthEstimates(), value));
            return true;
        }
    }

    private static String[] removeStringValue(String[] strArr, String str) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        String[] removeStringValue = removeStringValue(strArr, str, integerHolder);
        integerHolder.getValue();
        return removeStringValue;
    }

    private static String[] removeStringValue(String[] strArr, String str, IntegerHolder integerHolder) {
        integerHolder.setValue(getStringValueIndex(strArr, str));
        return removeStringValueAt(strArr, integerHolder.getValue());
    }

    private static String[] removeStringValueAt(String[] strArr, int i) {
        if (i < 0) {
            return strArr;
        }
        String[] strArr2 = new String[(ArrayExtensions.getLength((Object[]) strArr) - 1)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ArrayExtensions.getLength((Object[]) strArr)) {
            if (i2 == i) {
                i2++;
            } else {
                strArr2[i3] = strArr[i2];
                i3++;
                i2++;
            }
        }
        return strArr2;
    }

    public RtpParameters(TFormatCollection tformatcollection) {
        if (tformatcollection.getCount() == 0) {
            setRemoteSynchronizationSources(new long[0]);
            setRemoteRtpStreamIds(new String[0]);
            setRemoteRepairedRtpStreamIds(new String[0]);
            setInitialReceiveBandwidthEstimates(new int[0]);
            setLocalSynchronizationSources(new long[0]);
            setLocalRtpStreamIds(new String[0]);
            setLocalRepairedRtpStreamIds(new String[0]);
            setLocalContributingSources(new long[0]);
            setInitialSendBandwidthEstimates(new int[0]);
            this.__payloadTypeRegistry = new ManagedConcurrentDictionary<>();
            this.__negotiatedFormats = tformatcollection;
            return;
        }
        throw new RuntimeException(new Exception("Format collection must be empty."));
    }

    public void setCanonicalName(String str) {
        this._canonicalName = str;
    }

    private void setInitialReceiveBandwidthEstimates(int[] iArr) {
        this._initialReceiveBandwidthEstimates = iArr;
    }

    private void setInitialSendBandwidthEstimates(int[] iArr) {
        this._initialSendBandwidthEstimates = iArr;
    }

    private void setLocalContributingSources(long[] jArr) {
        this._localContributingSources = jArr;
    }

    private void setLocalRepairedRtpStreamIds(String[] strArr) {
        this._localRepairedRtpStreamIds = strArr;
    }

    private void setLocalRtpStreamIds(String[] strArr) {
        this._localRtpStreamIds = strArr;
    }

    private void setLocalSynchronizationSources(long[] jArr) {
        this._localSynchronizationSources = jArr;
    }

    public void setNegotiatedFormat(int i, TFormat tformat) {
        if (tformat == null) {
            Holder holder = new Holder(tformat);
            boolean tryRemove = this.__payloadTypeRegistry.tryRemove(Integer.valueOf(i), holder);
            MediaFormat mediaFormat = (MediaFormat) holder.getValue();
            if (tryRemove) {
                this.__negotiatedFormats.remove(mediaFormat);
            }
        } else if (this.__payloadTypeRegistry.addOrUpdate(Integer.valueOf(i), tformat) == tformat) {
            tformat.setRegisteredPayloadType(i);
            this.__negotiatedFormats.add(tformat);
        }
    }

    public void setRedFormat(TFormat tformat) {
        this._redFormat = tformat;
    }

    private void setRemoteRepairedRtpStreamIds(String[] strArr) {
        this._remoteRepairedRtpStreamIds = strArr;
    }

    private void setRemoteRtpStreamIds(String[] strArr) {
        this._remoteRtpStreamIds = strArr;
    }

    private void setRemoteSynchronizationSources(long[] jArr) {
        this._remoteSynchronizationSources = jArr;
    }

    public void setUlpFecFormat(TFormat tformat) {
        this._ulpFecFormat = tformat;
    }

    public boolean updateInitialReceiveBandwidthEstimate(String str, int i) {
        synchronized (this.__remoteSynchronizationSourcesLock) {
            if (!hasRemoteRtpStreamId(str)) {
                return false;
            }
            int stringValueIndex = getStringValueIndex(getRemoteRtpStreamIds(), str);
            if (i != -1) {
                getInitialReceiveBandwidthEstimates()[stringValueIndex] = i;
            }
            return true;
        }
    }

    public boolean updateInitialReceiveBandwidthEstimate(long j, int i) {
        synchronized (this.__remoteSynchronizationSourcesLock) {
            if (!hasRemoteSynchronizationSource(j)) {
                return false;
            }
            int longValueIndex = getLongValueIndex(getRemoteSynchronizationSources(), j);
            if (i != -1) {
                getInitialReceiveBandwidthEstimates()[longValueIndex] = i;
            }
            return true;
        }
    }

    public boolean updateInitialSendBandwidthEstimate(String str, int i) {
        synchronized (this.__localSynchronizationSourcesLock) {
            if (!hasLocalRtpStreamId(str)) {
                return false;
            }
            int stringValueIndex = getStringValueIndex(getLocalRtpStreamIds(), str);
            if (i != -1) {
                getInitialSendBandwidthEstimates()[stringValueIndex] = i;
            }
            return true;
        }
    }

    public boolean updateInitialSendBandwidthEstimate(long j, int i) {
        synchronized (this.__localSynchronizationSourcesLock) {
            if (!hasLocalSynchronizationSource(j)) {
                return false;
            }
            int longValueIndex = getLongValueIndex(getLocalSynchronizationSources(), j);
            if (i != -1) {
                getInitialSendBandwidthEstimates()[longValueIndex] = i;
            }
            return true;
        }
    }

    public boolean updateLocalSynchronizationSource(long j, String str, String str2) {
        synchronized (this.__localSynchronizationSourcesLock) {
            if (!hasLocalSynchronizationSource(j)) {
                return false;
            }
            if (hasLocalRtpStreamId(str)) {
                return false;
            }
            if (hasLocalRepairedRtpStreamId(str2)) {
                return false;
            }
            int longValueIndex = getLongValueIndex(getLocalSynchronizationSources(), j);
            if (str != null) {
                getLocalRtpStreamIds()[longValueIndex] = str;
            }
            if (str2 != null) {
                getLocalRepairedRtpStreamIds()[longValueIndex] = str2;
            }
            return true;
        }
    }

    public boolean updateRemoteSynchronizationSource(long j, String str, String str2) {
        synchronized (this.__remoteSynchronizationSourcesLock) {
            if (!hasRemoteSynchronizationSource(j)) {
                return false;
            }
            if (hasRemoteRtpStreamId(str)) {
                return false;
            }
            if (hasRemoteRepairedRtpStreamId(str2)) {
                return false;
            }
            int longValueIndex = getLongValueIndex(getRemoteSynchronizationSources(), j);
            if (str != null) {
                getRemoteRtpStreamIds()[longValueIndex] = str;
            }
            if (str2 != null) {
                getRemoteRepairedRtpStreamIds()[longValueIndex] = str2;
            }
            return true;
        }
    }
}
