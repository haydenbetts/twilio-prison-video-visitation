package fm.liveswitch.sdp;

import java.util.ArrayList;

public class BundleGroup {
    private ArrayList<MediaDescription> __mediaDescriptions = new ArrayList<>();
    private ArrayList<String> __mids = new ArrayList<>();
    private boolean _bundleOnly;
    private GroupSemanticsType _groupType;
    private MediaDescription _taggedMSection;

    public void addMediaDescription(MediaDescription mediaDescription) {
        this.__mediaDescriptions.add(mediaDescription);
        if (getTaggedMSection() == null) {
            setTaggedMSection(mediaDescription);
        }
        MediaStreamIdAttribute mediaStreamIdentifierAttribute = mediaDescription.getMediaStreamIdentifierAttribute();
        if (mediaStreamIdentifierAttribute != null) {
            this.__mids.add(mediaStreamIdentifierAttribute.getIdentificationTag());
            if (mediaDescription.getBundleOnly()) {
                setBundleOnly(true);
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception("SDP Media Stream Identifier Attribute missing for media description in bundle group."));
    }

    public BundleGroup(GroupSemanticsType groupSemanticsType) {
        setGroupType(groupSemanticsType);
    }

    public boolean getBundleOnly() {
        return this._bundleOnly;
    }

    public GroupSemanticsType getGroupType() {
        return this._groupType;
    }

    public MediaDescription[] getMediaDescriptions() {
        return (MediaDescription[]) this.__mediaDescriptions.toArray(new MediaDescription[0]);
    }

    public String[] getMids() {
        return (String[]) this.__mids.toArray(new String[0]);
    }

    public MediaDescription getTaggedMSection() {
        return this._taggedMSection;
    }

    private void setBundleOnly(boolean z) {
        this._bundleOnly = z;
    }

    private void setGroupType(GroupSemanticsType groupSemanticsType) {
        this._groupType = groupSemanticsType;
    }

    public void setTaggedMSection(MediaDescription mediaDescription) {
        this._taggedMSection = mediaDescription;
    }
}
