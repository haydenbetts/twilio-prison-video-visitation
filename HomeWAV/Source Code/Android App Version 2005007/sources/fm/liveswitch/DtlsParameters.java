package fm.liveswitch;

public class DtlsParameters {
    private DtlsFingerprint[] _fingerprints;
    private DtlsRole _preferredRole;
    private DtlsRole _role;

    public DtlsParameters(DtlsRole dtlsRole, DtlsFingerprint[] dtlsFingerprintArr) {
        this(dtlsRole, dtlsFingerprintArr, dtlsRole);
    }

    public DtlsParameters(DtlsRole dtlsRole, DtlsFingerprint[] dtlsFingerprintArr, DtlsRole dtlsRole2) {
        setRole(dtlsRole);
        setPreferredRole(dtlsRole2);
        setFingerprints(dtlsFingerprintArr);
    }

    public DtlsFingerprint getFingerprint() {
        return (DtlsFingerprint) Utility.firstOrDefault((T[]) getFingerprints());
    }

    public DtlsFingerprint[] getFingerprints() {
        return this._fingerprints;
    }

    public DtlsRole getPreferredRole() {
        return this._preferredRole;
    }

    public DtlsRole getRole() {
        return this._role;
    }

    /* access modifiers changed from: package-private */
    public DtlsRole negotiate(DtlsRole dtlsRole) {
        if (!Global.equals(getRole(), DtlsRole.Auto)) {
            return Global.equals(getRole(), DtlsRole.Client) ? Global.equals(dtlsRole, DtlsRole.Auto) ? DtlsRole.Server : dtlsRole : (!Global.equals(getRole(), DtlsRole.Server) || !Global.equals(dtlsRole, DtlsRole.Auto)) ? dtlsRole : DtlsRole.Client;
        }
        if (Global.equals(dtlsRole, DtlsRole.Client)) {
            setRole(DtlsRole.Server);
            return dtlsRole;
        } else if (Global.equals(dtlsRole, DtlsRole.Server)) {
            setRole(DtlsRole.Client);
            return dtlsRole;
        } else if (Global.equals(getPreferredRole(), DtlsRole.Server)) {
            setRole(DtlsRole.Server);
            return dtlsRole;
        } else {
            setRole(DtlsRole.Client);
            return dtlsRole;
        }
    }

    public void setFingerprint(DtlsFingerprint dtlsFingerprint) {
        DtlsFingerprint[] dtlsFingerprintArr;
        if (dtlsFingerprint == null) {
            dtlsFingerprintArr = null;
        } else {
            dtlsFingerprintArr = new DtlsFingerprint[]{dtlsFingerprint};
        }
        setFingerprints(dtlsFingerprintArr);
    }

    public void setFingerprints(DtlsFingerprint[] dtlsFingerprintArr) {
        this._fingerprints = dtlsFingerprintArr;
    }

    public void setPreferredRole(DtlsRole dtlsRole) {
        this._preferredRole = dtlsRole;
    }

    public void setRole(DtlsRole dtlsRole) {
        this._role = dtlsRole;
    }
}
