package fm.liveswitch;

import fm.liveswitch.sdp.rtp.RidAttribute;
import fm.liveswitch.sdp.rtp.RidRestriction;
import fm.liveswitch.sdp.rtp.SimulcastStream;
import fm.liveswitch.sdp.rtp.SimulcastStreamId;
import fm.liveswitch.sdp.rtp.SsrcAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class EncodingInfo extends Info {
    private static String __pausedKey = "paused";
    private int __bitrate = -1;
    private double __frameRate = -1.0d;
    private double __scale = -1.0d;
    private long __synchronizationSource = -1;
    private boolean _deactivated;
    private String _rtpStreamId;
    private Size _size;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("rtpStreamId")) {
            setRtpStreamId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("synchronizationSource")) {
            setSynchronizationSource(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("deactivated")) {
            setDeactivated(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals(FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE)) {
            setBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("frameRate")) {
            setFrameRate(JsonSerializer.deserializeDouble(str2).getValue());
        } else if (str.equals("size")) {
            setSize(Size.fromJson(str2));
        } else if (str.equals("scale")) {
            setScale(JsonSerializer.deserializeDouble(str2).getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public EncodingInfo extend(EncodingInfo encodingInfo) {
        if (!Global.equals(Boolean.valueOf(getDeactivated()), Boolean.valueOf(encodingInfo.getDeactivated()))) {
            setDeactivated(false);
        }
        if (getBitrate() == -1 || encodingInfo.getBitrate() > getBitrate()) {
            setBitrate(encodingInfo.getBitrate());
        }
        if (getFrameRate() == -1.0d || encodingInfo.getFrameRate() > getFrameRate()) {
            setFrameRate(encodingInfo.getFrameRate());
        }
        if ((getWidth() == -1 && getHeight() == -1) || (encodingInfo.getWidth() > getWidth() && encodingInfo.getHeight() > getHeight())) {
            setSize(encodingInfo.getSize());
        }
        if (getScale() == -1.0d || encodingInfo.getScale() > getScale()) {
            setScale(encodingInfo.getScale());
        }
        return this;
    }

    private static EncodingInfo findEncoding(ArrayList<EncodingInfo> arrayList, long j, String str) {
        if (j >= 0) {
            Iterator<EncodingInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                EncodingInfo next = it.next();
                if (next.getSynchronizationSource() == j) {
                    return next;
                }
            }
        }
        if (str == null) {
            return null;
        }
        Iterator<EncodingInfo> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            EncodingInfo next2 = it2.next();
            if (Global.equals(next2.getRtpStreamId(), str)) {
                return next2;
            }
        }
        return null;
    }

    public static EncodingInfo fromJson(String str) {
        return (EncodingInfo) JsonSerializer.deserializeObject(str, new IFunction0<EncodingInfo>() {
            public EncodingInfo invoke() {
                return new EncodingInfo();
            }
        }, new IAction3<EncodingInfo, String, String>() {
            public void invoke(EncodingInfo encodingInfo, String str, String str2) {
                encodingInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static EncodingInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, EncodingInfo>() {
            public String getId() {
                return "fm.liveswitch.EncodingInfo.fromJson";
            }

            public EncodingInfo invoke(String str) {
                return EncodingInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (EncodingInfo[]) deserializeObjectArray.toArray(new EncodingInfo[0]);
    }

    public static EncodingInfo fromSdpRidAttribute(RidAttribute ridAttribute) {
        if (ridAttribute == null) {
            return null;
        }
        if (ridAttribute.getId() != null) {
            EncodingInfo encodingInfo = new EncodingInfo();
            encodingInfo.setRtpStreamId(ridAttribute.getId());
            encodingInfo.setSdpRidRestrictions(ridAttribute.getRestrictions());
            return encodingInfo;
        }
        throw new RuntimeException(new Exception("RTP stream ID cannot be null."));
    }

    public int getBitrate() {
        return this.__bitrate;
    }

    public boolean getDeactivated() {
        return this._deactivated;
    }

    public double getFrameRate() {
        return this.__frameRate;
    }

    public int getHeight() {
        Size size = getSize();
        if (size == null) {
            return -1;
        }
        return size.getHeight();
    }

    public boolean getIsEmpty() {
        return getRtpStreamId() == null && getSynchronizationSource() == -1 && !getDeactivated() && getBitrate() == -1 && getFrameRate() == -1.0d && getSize() == null && getScale() == -1.0d;
    }

    private String getRidRestrictionValue(RidRestriction[] ridRestrictionArr, String str) {
        for (RidRestriction ridRestriction : ridRestrictionArr) {
            if (Global.equals(ridRestriction.getKey(), str)) {
                return ridRestriction.getValue();
            }
        }
        return null;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public double getScale() {
        return this.__scale;
    }

    public int getScaledHeight() {
        Size scaledSize = getScaledSize();
        if (scaledSize == null) {
            return -1;
        }
        return scaledSize.getHeight();
    }

    public Size getScaledSize() {
        Size size = getSize();
        if (size == null) {
            return null;
        }
        double scale = getScale();
        return scale > 0.0d ? new Size((int) (((double) size.getWidth()) * scale), (int) (((double) size.getHeight()) * scale)) : size;
    }

    public int getScaledWidth() {
        Size scaledSize = getScaledSize();
        if (scaledSize == null) {
            return -1;
        }
        return scaledSize.getWidth();
    }

    /* access modifiers changed from: package-private */
    public RidRestriction[] getSdpRidRestrictions() {
        ArrayList arrayList = new ArrayList();
        int bitrate = getBitrate();
        if (bitrate > 0) {
            arrayList.add(new RidRestriction(RidRestriction.getMaxBitrateKey(), IntegerExtensions.toString(Integer.valueOf(bitrate * 1000))));
        }
        double frameRate = getFrameRate();
        if (frameRate > 0.0d) {
            arrayList.add(new RidRestriction(RidRestriction.getMaxFramesPerSecondKey(), DoubleExtensions.toString(Double.valueOf(frameRate))));
        }
        double scale = getScale();
        int width = getWidth();
        if (width > 0) {
            if (scale > 0.0d) {
                width = (int) MathAssistant.round(((double) width) * scale);
            }
            arrayList.add(new RidRestriction(RidRestriction.getMaxWidthKey(), IntegerExtensions.toString(Integer.valueOf(width))));
        }
        int height = getHeight();
        if (height > 0) {
            if (scale > 0.0d) {
                height = (int) MathAssistant.round(((double) height) * scale);
            }
            arrayList.add(new RidRestriction(RidRestriction.getMaxHeightKey(), IntegerExtensions.toString(Integer.valueOf(height))));
        }
        return (RidRestriction[]) arrayList.toArray(new RidRestriction[0]);
    }

    /* access modifiers changed from: package-private */
    public SimulcastStream getSdpSimulcastStream() {
        if (getSdpSimulcastStreamId() == null) {
            return null;
        }
        return new SimulcastStream(getSdpSimulcastStreamId());
    }

    /* access modifiers changed from: package-private */
    public SimulcastStreamId getSdpSimulcastStreamId() {
        String rtpStreamId = getRtpStreamId();
        if (rtpStreamId == null) {
            return null;
        }
        return new SimulcastStreamId(rtpStreamId, getDeactivated());
    }

    /* access modifiers changed from: package-private */
    public SsrcAttribute[] getSdpSsrcRestrictionAttributes() {
        ArrayList arrayList = new ArrayList();
        for (RidRestriction ridRestriction : getSdpRidRestrictions()) {
            arrayList.add(new SsrcAttribute(getSynchronizationSource(), ridRestriction.getKey(), ridRestriction.getValue()));
        }
        if (getDeactivated()) {
            arrayList.add(new SsrcAttribute(getSynchronizationSource(), __pausedKey, "1"));
        }
        return (SsrcAttribute[]) arrayList.toArray(new SsrcAttribute[0]);
    }

    public Size getSize() {
        return this._size;
    }

    private String getSsrcRestrictionAttributeValue(SsrcAttribute[] ssrcAttributeArr, String str) {
        for (SsrcAttribute ssrcAttribute : ssrcAttributeArr) {
            if (ssrcAttribute.getSynchronizationSource() == getSynchronizationSource() && Global.equals(ssrcAttribute.getName(), str)) {
                return ssrcAttribute.getValue();
            }
        }
        return null;
    }

    public long getSynchronizationSource() {
        return this.__synchronizationSource;
    }

    public int getWidth() {
        Size size = getSize();
        if (size == null) {
            return -1;
        }
        return size.getWidth();
    }

    public static boolean isEquivalent(EncodingInfo encodingInfo, EncodingInfo encodingInfo2) {
        return isEquivalent(encodingInfo, encodingInfo2, false);
    }

    public static boolean isEquivalent(EncodingInfo encodingInfo, EncodingInfo encodingInfo2, boolean z) {
        if (Global.equals(encodingInfo, encodingInfo2)) {
            return true;
        }
        if (encodingInfo == null || encodingInfo2 == null) {
            return false;
        }
        return isEquivalentNoCheck(encodingInfo, encodingInfo2, z);
    }

    public boolean isEquivalent(EncodingInfo encodingInfo) {
        return isEquivalent(encodingInfo, false);
    }

    public boolean isEquivalent(EncodingInfo encodingInfo, boolean z) {
        return isEquivalent(this, encodingInfo, z);
    }

    private static boolean isEquivalentNoCheck(EncodingInfo encodingInfo, EncodingInfo encodingInfo2, boolean z) {
        return (z || Global.equals(encodingInfo.getRtpStreamId(), encodingInfo2.getRtpStreamId())) && (z || encodingInfo.getSynchronizationSource() == encodingInfo2.getSynchronizationSource()) && ((z || Global.equals(Boolean.valueOf(encodingInfo.getDeactivated()), Boolean.valueOf(encodingInfo2.getDeactivated()))) && encodingInfo.getBitrate() == encodingInfo2.getBitrate() && encodingInfo.getFrameRate() == encodingInfo2.getFrameRate() && Size.isEquivalent(encodingInfo.getSize(), encodingInfo2.getSize()) && encodingInfo.getScale() == encodingInfo2.getScale());
    }

    public static EncodingInfo max(EncodingInfo encodingInfo, EncodingInfo encodingInfo2) {
        EncodingInfo encodingInfo3 = new EncodingInfo();
        encodingInfo3.setBitrate(maxInteger(encodingInfo.getBitrate(), encodingInfo2.getBitrate()));
        encodingInfo3.setFrameRate(maxDouble(encodingInfo.getFrameRate(), encodingInfo2.getFrameRate()));
        Size scaledSize = encodingInfo.getScaledSize();
        if (Global.equals(maxSize(scaledSize, encodingInfo2.getScaledSize()), scaledSize)) {
            encodingInfo3.setSize(encodingInfo.getSize());
            encodingInfo3.setScale(encodingInfo.getScale());
            return encodingInfo3;
        }
        encodingInfo3.setSize(encodingInfo2.getSize());
        encodingInfo3.setScale(encodingInfo2.getScale());
        return encodingInfo3;
    }

    private static double maxDouble(double d, double d2) {
        if (d == -1.0d) {
            return d2;
        }
        return d2 == -1.0d ? d : MathAssistant.max(d, d2);
    }

    private static int maxInteger(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        return i2 == -1 ? i : MathAssistant.max(i, i2);
    }

    private static Size maxSize(Size size, Size size2) {
        if (size == null) {
            return size2;
        }
        return (size2 == null || size2.getWidth() * size2.getHeight() <= size.getWidth() * size.getHeight()) ? size : size2;
    }

    static EncodingInfo[] merge(EncodingInfo[] encodingInfoArr) {
        ArrayList arrayList = new ArrayList();
        for (EncodingInfo encodingInfo : encodingInfoArr) {
            EncodingInfo findEncoding = findEncoding(arrayList, encodingInfo.getSynchronizationSource(), encodingInfo.getRtpStreamId());
            if (findEncoding == null) {
                EncodingInfo encodingInfo2 = new EncodingInfo();
                encodingInfo2.setSynchronizationSource(encodingInfo.getSynchronizationSource());
                encodingInfo2.setRtpStreamId(encodingInfo.getRtpStreamId());
                encodingInfo2.setDeactivated(encodingInfo.getDeactivated());
                arrayList.add(encodingInfo2.extend(encodingInfo));
            } else {
                findEncoding.extend(encodingInfo);
            }
        }
        return (EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]);
    }

    public static EncodingInfo min(EncodingInfo encodingInfo, EncodingInfo encodingInfo2) {
        EncodingInfo encodingInfo3 = new EncodingInfo();
        encodingInfo3.setBitrate(minInteger(encodingInfo.getBitrate(), encodingInfo2.getBitrate()));
        encodingInfo3.setFrameRate(minDouble(encodingInfo.getFrameRate(), encodingInfo2.getFrameRate()));
        Size scaledSize = encodingInfo.getScaledSize();
        if (Global.equals(minSize(scaledSize, encodingInfo2.getScaledSize()), scaledSize)) {
            encodingInfo3.setSize(encodingInfo.getSize());
            encodingInfo3.setScale(encodingInfo.getScale());
            return encodingInfo3;
        }
        encodingInfo3.setSize(encodingInfo2.getSize());
        encodingInfo3.setScale(encodingInfo2.getScale());
        return encodingInfo3;
    }

    private static double minDouble(double d, double d2) {
        if (d == -1.0d) {
            return d2;
        }
        return d2 == -1.0d ? d : MathAssistant.min(d, d2);
    }

    private static int minInteger(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        return i2 == -1 ? i : MathAssistant.min(i, i2);
    }

    private static Size minSize(Size size, Size size2) {
        if (size == null) {
            return size2;
        }
        return (size2 == null || size2.getWidth() * size2.getHeight() >= size.getWidth() * size.getHeight()) ? size : size2;
    }

    private int parseBitrate(String str) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str, integerHolder);
        int value = integerHolder.getValue();
        if (str == null || !tryParseIntegerValue) {
            return -1;
        }
        return MathAssistant.max(0, value / 1000);
    }

    private boolean parseDeactivated(String str) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str, integerHolder);
        int value = integerHolder.getValue();
        if (str == null || !tryParseIntegerValue || value != 1) {
            return false;
        }
        return true;
    }

    private double parseFrameRate(String str) {
        DoubleHolder doubleHolder = new DoubleHolder(0.0d);
        boolean tryParseDoubleValue = ParseAssistant.tryParseDoubleValue(str, doubleHolder);
        double value = doubleHolder.getValue();
        if (str == null || !tryParseDoubleValue) {
            return -1.0d;
        }
        return MathAssistant.max(0.0d, value);
    }

    private Size parseSize(String str, String str2) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(str2, integerHolder2);
        int value2 = integerHolder2.getValue();
        if (str == null || str2 == null || !tryParseIntegerValue || !tryParseIntegerValue2) {
            return null;
        }
        return new Size(MathAssistant.max(0, value), MathAssistant.max(0, value2));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getRtpStreamId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rtpStreamId", JsonSerializer.serializeString(getRtpStreamId()));
        }
        if (getSynchronizationSource() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "synchronizationSource", JsonSerializer.serializeLong(new NullableLong(getSynchronizationSource())));
        }
        if (getDeactivated()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deactivated", JsonSerializer.serializeBoolean(new NullableBoolean(getDeactivated())));
        }
        if (getBitrate() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, JsonSerializer.serializeInteger(new NullableInteger(getBitrate())));
        }
        if (getFrameRate() != -1.0d) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameRate", JsonSerializer.serializeDouble(new NullableDouble(getFrameRate())));
        }
        if (getSize() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "size", Size.toJson(getSize()));
        }
        if (getScale() != -1.0d) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "scale", JsonSerializer.serializeDouble(new NullableDouble(getScale())));
        }
    }

    public void setBitrate(int i) {
        this.__bitrate = i;
    }

    public void setDeactivated(boolean z) {
        this._deactivated = z;
    }

    public void setFrameRate(double d) {
        this.__frameRate = d;
    }

    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    public void setScale(double d) {
        this.__scale = d;
    }

    /* access modifiers changed from: package-private */
    public void setSdpRidRestrictions(RidRestriction[] ridRestrictionArr) {
        if (ridRestrictionArr != null) {
            setBitrate(parseBitrate(getRidRestrictionValue(ridRestrictionArr, RidRestriction.getMaxBitrateKey())));
            setFrameRate(parseFrameRate(getRidRestrictionValue(ridRestrictionArr, RidRestriction.getMaxFramesPerSecondKey())));
            setSize(parseSize(getRidRestrictionValue(ridRestrictionArr, RidRestriction.getMaxWidthKey()), getRidRestrictionValue(ridRestrictionArr, RidRestriction.getMaxHeightKey())));
        }
    }

    /* access modifiers changed from: package-private */
    public void setSdpSimulcastStreamId(SimulcastStreamId simulcastStreamId) {
        if (simulcastStreamId == null) {
            setRtpStreamId((String) null);
            setDeactivated(false);
            return;
        }
        setRtpStreamId(simulcastStreamId.getId());
        setDeactivated(simulcastStreamId.getPaused());
    }

    /* access modifiers changed from: package-private */
    public void setSdpSsrcRestrictionAttributes(SsrcAttribute[] ssrcAttributeArr) {
        if (ssrcAttributeArr != null) {
            setDeactivated(parseDeactivated(getSsrcRestrictionAttributeValue(ssrcAttributeArr, __pausedKey)));
            setBitrate(parseBitrate(getSsrcRestrictionAttributeValue(ssrcAttributeArr, RidRestriction.getMaxBitrateKey())));
            setFrameRate(parseFrameRate(getSsrcRestrictionAttributeValue(ssrcAttributeArr, RidRestriction.getMaxFramesPerSecondKey())));
            setSize(parseSize(getSsrcRestrictionAttributeValue(ssrcAttributeArr, RidRestriction.getMaxWidthKey()), getSsrcRestrictionAttributeValue(ssrcAttributeArr, RidRestriction.getMaxHeightKey())));
        }
    }

    public void setSize(Size size) {
        this._size = size;
    }

    public void setSynchronizationSource(long j) {
        this.__synchronizationSource = j;
    }

    public static String toJson(EncodingInfo encodingInfo) {
        return JsonSerializer.serializeObject(encodingInfo, new IAction2<EncodingInfo, HashMap<String, String>>() {
            public void invoke(EncodingInfo encodingInfo, HashMap<String, String> hashMap) {
                encodingInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(EncodingInfo[] encodingInfoArr) {
        return JsonSerializer.serializeObjectArray(encodingInfoArr, new IFunctionDelegate1<EncodingInfo, String>() {
            public String getId() {
                return "fm.liveswitch.EncodingInfo.toJson";
            }

            public String invoke(EncodingInfo encodingInfo) {
                return EncodingInfo.toJson(encodingInfo);
            }
        });
    }

    public static RidAttribute toSdpRidAttribute(EncodingInfo encodingInfo, String str) {
        return toSdpRidAttribute(encodingInfo, str, true);
    }

    public static RidAttribute toSdpRidAttribute(EncodingInfo encodingInfo, String str, boolean z) {
        RidRestriction[] ridRestrictionArr = null;
        if (encodingInfo == null) {
            return null;
        }
        if (encodingInfo.getRtpStreamId() != null) {
            String rtpStreamId = encodingInfo.getRtpStreamId();
            if (z) {
                ridRestrictionArr = encodingInfo.getSdpRidRestrictions();
            }
            return new RidAttribute(rtpStreamId, str, ridRestrictionArr);
        }
        throw new RuntimeException(new Exception("RTP stream ID cannot be null."));
    }

    public RidAttribute toSdpRidAttribute(String str) {
        return toSdpRidAttribute(str, true);
    }

    public RidAttribute toSdpRidAttribute(String str, boolean z) {
        return toSdpRidAttribute(this, str, z);
    }

    public String toString() {
        boolean z;
        Size scaledSize;
        ArrayList arrayList = new ArrayList();
        String rtpStreamId = getRtpStreamId();
        if (rtpStreamId != null) {
            arrayList.add(StringExtensions.format("RTP Stream ID: {0}", (Object) rtpStreamId));
        }
        long synchronizationSource = getSynchronizationSource();
        if (synchronizationSource != -1) {
            arrayList.add(StringExtensions.format("Synchronization Source: {0}", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource))));
        }
        boolean deactivated = getDeactivated();
        if (deactivated) {
            arrayList.add(StringExtensions.format("Deactivated: {0}", (Object) BooleanExtensions.toString(Boolean.valueOf(deactivated))));
        }
        int bitrate = getBitrate();
        boolean z2 = true;
        if (bitrate != -1) {
            arrayList.add(StringExtensions.format("Bitrate: {0}", (Object) IntegerExtensions.toString(Integer.valueOf(bitrate))));
            z = true;
        } else {
            z = false;
        }
        double frameRate = getFrameRate();
        if (frameRate != -1.0d) {
            arrayList.add(StringExtensions.format("Frame Rate: {0}", (Object) DoubleExtensions.toString(Double.valueOf(frameRate))));
            z = true;
        }
        Size size = getSize();
        if (size != null) {
            arrayList.add(StringExtensions.format("Size: {0}", (Object) size.toString()));
        }
        double scale = getScale();
        if (scale != -1.0d) {
            arrayList.add(StringExtensions.format("Scale: {0}", (Object) DoubleExtensions.toString(Double.valueOf(scale))));
            if (!(scale == 1.0d || (scaledSize = getScaledSize()) == null)) {
                arrayList.add(StringExtensions.format("Scaled Size: {0}", (Object) scaledSize.toString()));
            }
        } else {
            z2 = z;
        }
        String join = StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0]));
        return !z2 ? StringExtensions.format("{0} [Unrestricted]", (Object) join).trim() : join;
    }
}
