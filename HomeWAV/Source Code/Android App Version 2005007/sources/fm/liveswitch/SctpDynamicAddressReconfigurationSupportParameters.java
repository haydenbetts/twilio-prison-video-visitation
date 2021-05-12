package fm.liveswitch;

class SctpDynamicAddressReconfigurationSupportParameters {
    private boolean _dynamicAddressReconfigurationSupportedByThisEndpoint;
    private boolean _dynamicAddressReconfigurationUsedInThisAssociation;

    public boolean getDynamicAddressReconfigurationSupportedByThisEndpoint() {
        return this._dynamicAddressReconfigurationSupportedByThisEndpoint;
    }

    public boolean getDynamicAddressReconfigurationUsedInThisAssociation() {
        return this._dynamicAddressReconfigurationUsedInThisAssociation;
    }

    public SctpDynamicAddressReconfigurationSupportParameters(boolean z) {
        setDynamicAddressReconfigurationSupportedByThisEndpoint(z);
    }

    /* access modifiers changed from: package-private */
    public void setDynamicAddressReconfigurationSupportedByThisEndpoint(boolean z) {
        this._dynamicAddressReconfigurationSupportedByThisEndpoint = z;
    }

    public void setDynamicAddressReconfigurationUsedInThisAssociation(boolean z) {
        this._dynamicAddressReconfigurationUsedInThisAssociation = z;
    }
}
