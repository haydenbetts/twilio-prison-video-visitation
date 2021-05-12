package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.Holder;
import fm.liveswitch.StreamDirection;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.ice.FingerprintAttribute;
import fm.liveswitch.sdp.ice.OptionTag;
import fm.liveswitch.sdp.ice.OptionsAttribute;
import fm.liveswitch.sdp.ice.PasswordAttribute;
import fm.liveswitch.sdp.ice.TrickleIceOptionTag;
import fm.liveswitch.sdp.ice.UfragAttribute;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

public class Message {
    private ArrayList<Bandwidth> __bandwidths;
    private ArrayList<MediaDescription> __mediaDescriptions;
    private AttributeCollection __sessionAttributes;
    private ArrayList<TimeDescription> __timeDescriptions;
    private ConnectionData _connectionData;
    private String _emailAddress;
    private EncryptionKey _encryptionKey;
    private Origin _origin;
    private String _phoneNumber;
    private String _protocolVersion;
    private String _sessionInformation;
    private String _sessionName;
    private TimeZones _timeZoneAdjustments;
    private URI _uri;

    public void addBandwidth(Bandwidth bandwidth) {
        this.__bandwidths.add(bandwidth);
    }

    public void addMediaDescription(MediaDescription mediaDescription) {
        this.__mediaDescriptions.add(mediaDescription);
    }

    public void addSessionAttribute(Attribute attribute) {
        if (Attribute.isSessionLevel(attribute.getClass())) {
            this.__sessionAttributes.addAttribute(attribute);
        } else if (Attribute.isMediaLevel(attribute.getClass())) {
            throw new RuntimeException(new Exception("Attribute is media-only."));
        } else {
            throw new RuntimeException(new Exception("Attribute is not registered."));
        }
    }

    public void addTimeDescription(TimeDescription timeDescription) {
        this.__timeDescriptions.add(timeDescription);
    }

    /* access modifiers changed from: package-private */
    public BundleGroup[] findBundleGroups() {
        GroupSemanticsType groupSemanticsType = GroupSemanticsType.Bundling;
        GroupAttribute[] groupAttributesFromCollection = MediaDescription.getGroupAttributesFromCollection(this.__sessionAttributes);
        if (groupAttributesFromCollection == null) {
            return null;
        }
        BundleGroup[] bundleGroupArr = new BundleGroup[ArrayExtensions.getLength((Object[]) groupAttributesFromCollection)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) bundleGroupArr); i++) {
            GroupAttribute groupAttribute = groupAttributesFromCollection[i];
            if (Global.equals(groupSemanticsType, groupAttribute.getSemantics())) {
                BundleGroup bundleGroup = new BundleGroup(groupSemanticsType);
                for (String findMediaDescription : groupAttribute.getIds()) {
                    MediaDescription findMediaDescription2 = findMediaDescription(findMediaDescription);
                    if (findMediaDescription2 != null) {
                        bundleGroup.addMediaDescription(findMediaDescription2);
                    }
                }
                bundleGroupArr[i] = bundleGroup;
            }
        }
        return bundleGroupArr;
    }

    /* access modifiers changed from: package-private */
    public MediaDescription findMediaDescription(String str) {
        for (MediaDescription mediaDescription : getMediaDescriptions()) {
            MediaStreamIdAttribute mediaStreamIdentifierAttribute = mediaDescription.getMediaStreamIdentifierAttribute();
            if (mediaStreamIdentifierAttribute != null && Global.equals(mediaStreamIdentifierAttribute.getIdentificationTag(), str)) {
                return mediaDescription;
            }
        }
        return null;
    }

    private MediaDescription[] findMediaDescriptions(String str) {
        ArrayList arrayList = new ArrayList();
        for (MediaDescription mediaDescription : getMediaDescriptions()) {
            Media media = mediaDescription.getMedia();
            if (media != null && Global.equals(media.getMediaType(), str)) {
                arrayList.add(mediaDescription);
            }
        }
        return (MediaDescription[]) arrayList.toArray(new MediaDescription[0]);
    }

    public MediaDescription getApplicationDescription() {
        return getFirstMediaDescription(getApplicationDescriptions());
    }

    public MediaDescription[] getApplicationDescriptions() {
        return findMediaDescriptions(MediaType.getApplication());
    }

    public MediaDescription getAudioDescription() {
        return getFirstMediaDescription(getAudioDescriptions());
    }

    public MediaDescription[] getAudioDescriptions() {
        return findMediaDescriptions(MediaType.getAudio());
    }

    public Bandwidth[] getBandwidths() {
        return (Bandwidth[]) this.__bandwidths.toArray(new Bandwidth[0]);
    }

    public BundleGroup[] getBundleGroups() {
        return findBundleGroups();
    }

    public ConnectionData getConnectionData() {
        return this._connectionData;
    }

    public String getEmailAddress() {
        return this._emailAddress;
    }

    public EncryptionKey getEncryptionKey() {
        return this._encryptionKey;
    }

    private MediaDescription getFirstMediaDescription(MediaDescription[] mediaDescriptionArr) {
        if (mediaDescriptionArr == null || ArrayExtensions.getLength((Object[]) mediaDescriptionArr) == 0) {
            return null;
        }
        return mediaDescriptionArr[0];
    }

    public MediaDescription[] getMediaDescriptions() {
        return (MediaDescription[]) this.__mediaDescriptions.toArray(new MediaDescription[0]);
    }

    public MediaDescription getMessageDescription() {
        return getFirstMediaDescription(getMessageDescriptions());
    }

    public MediaDescription[] getMessageDescriptions() {
        return findMediaDescriptions(MediaType.getMessage());
    }

    public Origin getOrigin() {
        return this._origin;
    }

    public String getPhoneNumber() {
        return this._phoneNumber;
    }

    public String getProtocolVersion() {
        return this._protocolVersion;
    }

    public Attribute[] getSessionAttributes() {
        return this.__sessionAttributes.toArray();
    }

    public String getSessionInformation() {
        return this._sessionInformation;
    }

    public CryptoAttribute[] getSessionLevelCryptoAttributes() {
        return MediaDescription.getCryptoAttributesFromCollection(this.__sessionAttributes);
    }

    public StreamDirection getSessionLevelDirection() {
        return MediaDescription.getStreamDirectionFromCollection(this.__sessionAttributes);
    }

    public FingerprintAttribute getSessionLevelFingerprintAttribute() {
        return MediaDescription.getFingerprintAttributeFromCollection(this.__sessionAttributes);
    }

    public Attribute[] getSessionLevelIceOptionAttributes() {
        return MediaDescription.getIceOptionAttributesFromCollection(this.__sessionAttributes);
    }

    public PasswordAttribute getSessionLevelIcePasswordAttribute() {
        return MediaDescription.getIcePasswordAttributeFromCollection(this.__sessionAttributes);
    }

    public UfragAttribute getSessionLevelIceUfragAttribute() {
        return MediaDescription.getIceUfragAttributeFromCollection(this.__sessionAttributes);
    }

    public boolean getSessionLevelRtcpMultiplexingSupport() {
        return MediaDescription.getRtcpMultiplexingSupportFromCollection(this.__sessionAttributes);
    }

    public Attribute[] getSessionLevelRtpExtMapAttributes() {
        return MediaDescription.getRtpExtMapAttributesFromCollection(this.__sessionAttributes);
    }

    public SetupAttribute getSessionLevelSetupAttribute() {
        return MediaDescription.getSetupAttributeFromCollection(this.__sessionAttributes);
    }

    public String getSessionName() {
        return this._sessionName;
    }

    public boolean getSupportsTrickleIce() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__sessionAttributes.tryGetAttribute(AttributeType.IceOptionsAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute && ((OptionsAttribute) attribute).getTrickleOptionSet()) {
            return true;
        }
        boolean z = true;
        for (MediaDescription iceOptionAttributes : getMediaDescriptions()) {
            boolean z2 = false;
            for (Attribute attribute2 : iceOptionAttributes.getIceOptionAttributes()) {
                z2 = z2 || ((OptionsAttribute) attribute2).getTrickleOptionSet();
            }
            z = z && z2;
        }
        return z;
    }

    public MediaDescription getTextDescription() {
        return getFirstMediaDescription(getTextDescriptions());
    }

    public MediaDescription[] getTextDescriptions() {
        return findMediaDescriptions(MediaType.getText());
    }

    public TimeDescription[] getTimeDescriptions() {
        return (TimeDescription[]) this.__timeDescriptions.toArray(new TimeDescription[0]);
    }

    public TimeZones getTimeZoneAdjustments() {
        return this._timeZoneAdjustments;
    }

    public URI getUri() {
        return this._uri;
    }

    public MediaDescription getVideoDescription() {
        return getFirstMediaDescription(getVideoDescriptions());
    }

    public MediaDescription[] getVideoDescriptions() {
        return findMediaDescriptions(MediaType.getVideo());
    }

    public void insertMediaDescription(int i, MediaDescription mediaDescription) {
        ArrayListExtensions.insert(this.__mediaDescriptions, i, mediaDescription);
    }

    public Message(Origin origin) {
        this(origin, (String) null);
    }

    public Message(Origin origin, String str) {
        this(origin, str, (TimeDescription[]) null);
    }

    public Message(Origin origin, String str, TimeDescription[] timeDescriptionArr) {
        this.__bandwidths = new ArrayList<>();
        this.__timeDescriptions = new ArrayList<>();
        this.__sessionAttributes = new AttributeCollection();
        this.__mediaDescriptions = new ArrayList<>();
        if (origin != null) {
            timeDescriptionArr = timeDescriptionArr == null ? new TimeDescription[]{new TimeDescription(new Timing())} : timeDescriptionArr;
            str = (str == null || StringExtensions.isNullOrEmpty(str.trim())) ? "-" : str;
            setProtocolVersion("0");
            setOrigin(origin);
            setSessionName(str.trim());
            ArrayListExtensions.addRange(this.__timeDescriptions, (T[]) timeDescriptionArr);
            return;
        }
        throw new RuntimeException(new Exception("origin cannot be null."));
    }

    public static Message parse(String str) {
        String[] splitAndClean = Utility.splitAndClean(str);
        if (splitAndClean[0].charAt(0) != 'v' || splitAndClean[1].charAt(0) != 'o' || splitAndClean[2].charAt(0) != 's') {
            return null;
        }
        Message message = new Message(Origin.parse(splitAndClean[1].substring(2)), splitAndClean[2].substring(2));
        message.removeTimeDescriptions();
        int i = 3;
        while (i < ArrayExtensions.getLength((Object[]) splitAndClean)) {
            String str2 = splitAndClean[i];
            if (str2.charAt(0) == 'i') {
                message.setSessionInformation(str2.substring(2));
            } else if (str2.charAt(0) == 'u') {
                try {
                    message.setUri(new URI(str2.substring(2)));
                } catch (Exception unused) {
                }
            } else if (str2.charAt(0) == 'e') {
                message.setEmailAddress(str2.substring(2));
            } else if (str2.charAt(0) == 'p') {
                message.setPhoneNumber(str2.substring(2));
            } else if (str2.charAt(0) == 'c') {
                message.setConnectionData(ConnectionData.parse(str2));
            } else if (str2.charAt(0) == 'b') {
                message.addBandwidth(Bandwidth.parse(str2));
            } else if (str2.charAt(0) == 't') {
                for (int i2 = i + 1; i2 < ArrayExtensions.getLength((Object[]) splitAndClean); i2++) {
                    String str3 = splitAndClean[i2];
                    if (str3.charAt(0) != 'r') {
                        break;
                    }
                    str2 = StringExtensions.concat(str2, "\n", str3);
                    i++;
                }
                message.addTimeDescription(TimeDescription.parse(str2));
            } else if (str2.charAt(0) == 'z') {
                message.setTimeZoneAdjustments(TimeZones.parse(str2));
            } else if (str2.charAt(0) == 'k') {
                message.setEncryptionKey(EncryptionKey.parse(str2));
            } else if (str2.charAt(0) == 'a') {
                message.addSessionAttribute(Attribute.parse(str2));
            } else if (str2.charAt(0) == 'm') {
                for (int i3 = i + 1; i3 < ArrayExtensions.getLength((Object[]) splitAndClean); i3++) {
                    String str4 = splitAndClean[i3];
                    if (str4.charAt(0) == 'm') {
                        break;
                    }
                    str2 = StringExtensions.concat(str2, "\n", str4);
                    i++;
                }
                message.addMediaDescription(MediaDescription.parse(str2));
            }
            i++;
        }
        return message;
    }

    public boolean removeBandwidth(Bandwidth bandwidth) {
        return this.__bandwidths.remove(bandwidth);
    }

    public boolean removeMediaDescription(MediaDescription mediaDescription) {
        return this.__mediaDescriptions.remove(mediaDescription);
    }

    public boolean removeSessionAttribute(AttributeType attributeType) {
        return this.__sessionAttributes.remove(attributeType);
    }

    public boolean removeTimeDescription(TimeDescription timeDescription) {
        return this.__timeDescriptions.remove(timeDescription);
    }

    public void removeTimeDescriptions() {
        this.__timeDescriptions = new ArrayList<>();
    }

    public void setConnectionData(ConnectionData connectionData) {
        this._connectionData = connectionData;
    }

    public void setEmailAddress(String str) {
        this._emailAddress = str;
    }

    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this._encryptionKey = encryptionKey;
    }

    private void setOrigin(Origin origin) {
        this._origin = origin;
    }

    public void setPhoneNumber(String str) {
        this._phoneNumber = str;
    }

    private void setProtocolVersion(String str) {
        this._protocolVersion = str;
    }

    public void setSessionInformation(String str) {
        this._sessionInformation = str;
    }

    private void setSessionName(String str) {
        this._sessionName = str;
    }

    public void setSupportsTrickleIce(boolean z) {
        for (MediaDescription mediaDescription : getMediaDescriptions()) {
            Attribute[] iceOptionAttributes = mediaDescription.getIceOptionAttributes();
            ArrayList arrayList = new ArrayList();
            for (Attribute attribute : iceOptionAttributes) {
                OptionsAttribute optionsAttribute = (OptionsAttribute) attribute;
                optionsAttribute.setTrickleOptionSet(false);
                if (ArrayListExtensions.getCount(optionsAttribute.getTags()) == 0) {
                    arrayList.add(optionsAttribute);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                mediaDescription.removeMediaAttribute((Attribute) it.next());
            }
        }
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__sessionAttributes.tryGetAttribute(AttributeType.IceOptionsAttribute, holder);
        Attribute attribute2 = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            OptionsAttribute optionsAttribute2 = (OptionsAttribute) attribute2;
            optionsAttribute2.setTrickleOptionSet(z);
            ArrayList<OptionTag> tags = optionsAttribute2.getTags();
            if (tags == null || ArrayListExtensions.getCount(tags) == 0) {
                this.__sessionAttributes.remove((Attribute) optionsAttribute2);
            }
        } else if (z) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new TrickleIceOptionTag());
            addSessionAttribute(new OptionsAttribute(arrayList2));
        }
    }

    public void setTimeZoneAdjustments(TimeZones timeZones) {
        this._timeZoneAdjustments = timeZones;
    }

    public void setUri(URI uri) {
        this._uri = uri;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, StringExtensions.concat("v=", getProtocolVersion(), "\r\n"));
        StringBuilderExtensions.append(sb, StringExtensions.concat("o=", getOrigin().toString(), "\r\n"));
        StringBuilderExtensions.append(sb, StringExtensions.concat("s=", getSessionName(), "\r\n"));
        if (getSessionInformation() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat("i=", getSessionInformation(), "\r\n"));
        }
        if (getUri() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat("u=", getUri().toString(), "\r\n"));
        }
        if (getEmailAddress() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat("e=", getEmailAddress(), "\r\n"));
        }
        if (getPhoneNumber() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat("p=", getPhoneNumber(), "\r\n"));
        }
        if (getConnectionData() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(getConnectionData().toString(), "\r\n"));
        }
        for (Bandwidth bandwidth : getBandwidths()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(bandwidth.toString(), "\r\n"));
        }
        for (TimeDescription timeDescription : getTimeDescriptions()) {
            StringBuilderExtensions.append(sb, timeDescription.toString());
        }
        if (getTimeZoneAdjustments() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(getTimeZoneAdjustments().toString(), "\r\n"));
        }
        if (getEncryptionKey() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(getEncryptionKey().toString(), "\r\n"));
        }
        for (Attribute attribute : getSessionAttributes()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(attribute.toString(), "\r\n"));
        }
        for (MediaDescription mediaDescription : getMediaDescriptions()) {
            StringBuilderExtensions.append(sb, mediaDescription.toString());
        }
        return sb.toString();
    }

    public void updateSetupValue(String str) {
        removeSessionAttribute(AttributeType.SetupAttribute);
        addSessionAttribute(new SetupAttribute(str));
    }
}
