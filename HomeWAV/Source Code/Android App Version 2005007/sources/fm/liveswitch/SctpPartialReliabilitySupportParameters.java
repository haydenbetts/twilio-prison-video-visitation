package fm.liveswitch;

class SctpPartialReliabilitySupportParameters {
    private boolean __partialReliabilityUsedInThisAssociation = false;
    private boolean __remoteIndicatedLackOfPRSupport = false;
    private boolean _partialReliabilitySupportedByThisEndpoint;
    private boolean _remoteIndicatedLackOfPRSupport;

    public boolean getPartialReliabilitySupportedByThisEndpoint() {
        return this._partialReliabilitySupportedByThisEndpoint;
    }

    public boolean getPartialReliabilityUsedInThisAssociation() {
        return this.__partialReliabilityUsedInThisAssociation && !this.__remoteIndicatedLackOfPRSupport;
    }

    /* access modifiers changed from: package-private */
    public boolean getRemoteIndicatedLackOfPRSupport() {
        return this._remoteIndicatedLackOfPRSupport;
    }

    public SctpPartialReliabilitySupportParameters(boolean z) {
        setPartialReliabilitySupportedByThisEndpoint(z);
    }

    /* access modifiers changed from: package-private */
    public void setPartialReliabilitySupportedByThisEndpoint(boolean z) {
        this._partialReliabilitySupportedByThisEndpoint = z;
    }

    public void setPartialReliabilityUsedInThisAssociation(boolean z) {
        if (this.__remoteIndicatedLackOfPRSupport || !this.__partialReliabilityUsedInThisAssociation) {
            this.__partialReliabilityUsedInThisAssociation = false;
        } else {
            this.__partialReliabilityUsedInThisAssociation = z;
        }
    }

    /* access modifiers changed from: package-private */
    public void setRemoteIndicatedLackOfPRSupport(boolean z) {
        this._remoteIndicatedLackOfPRSupport = z;
    }
}
