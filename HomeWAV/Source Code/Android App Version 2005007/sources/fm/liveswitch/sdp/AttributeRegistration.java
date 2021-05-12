package fm.liveswitch.sdp;

import fm.liveswitch.IFunction1;

class AttributeRegistration {
    private IFunction1<AttributeCreationArgs, Attribute> _creationDelegate;
    private boolean _mediaLevel;
    private String _name;
    private boolean _sessionLevel;

    public AttributeRegistration(String str, boolean z, boolean z2, IFunction1<AttributeCreationArgs, Attribute> iFunction1) {
        setName(str);
        setSessionLevel(z);
        setMediaLevel(z2);
        setCreationDelegate(iFunction1);
    }

    public IFunction1<AttributeCreationArgs, Attribute> getCreationDelegate() {
        return this._creationDelegate;
    }

    public boolean getMediaLevel() {
        return this._mediaLevel;
    }

    public String getName() {
        return this._name;
    }

    public boolean getSessionLevel() {
        return this._sessionLevel;
    }

    public void setCreationDelegate(IFunction1<AttributeCreationArgs, Attribute> iFunction1) {
        this._creationDelegate = iFunction1;
    }

    public void setMediaLevel(boolean z) {
        this._mediaLevel = z;
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setSessionLevel(boolean z) {
        this._sessionLevel = z;
    }
}
