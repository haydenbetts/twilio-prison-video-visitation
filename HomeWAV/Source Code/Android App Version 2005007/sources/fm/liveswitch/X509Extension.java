package fm.liveswitch;

import java.util.ArrayList;

class X509Extension extends Asn1Sequence {
    private boolean _critical;
    private long[] _extensionId;
    private byte[] _extensionValue;

    public static X509Extension fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509Extension x509Extension = new X509Extension();
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 3) {
            x509Extension.setExtensionId(((Asn1ObjectIdentifier) asn1Sequence.getValues()[0]).getValues());
            x509Extension.setExtensionValue(((Asn1OctetString) asn1Sequence.getValues()[1]).getValueBytes());
        } else {
            x509Extension.setExtensionId(((Asn1ObjectIdentifier) asn1Sequence.getValues()[0]).getValues());
            x509Extension.setCritical(((Asn1Boolean) asn1Sequence.getValues()[1]).getValue());
            x509Extension.setExtensionValue(((Asn1OctetString) asn1Sequence.getValues()[2]).getValueBytes());
        }
        return x509Extension;
    }

    public boolean getCritical() {
        return this._critical;
    }

    public long[] getExtensionId() {
        return this._extensionId;
    }

    public byte[] getExtensionValue() {
        return this._extensionValue;
    }

    public void setCritical(boolean z) {
        this._critical = z;
    }

    public void setExtensionId(long[] jArr) {
        this._extensionId = jArr;
    }

    public void setExtensionValue(byte[] bArr) {
        this._extensionValue = bArr;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1ObjectIdentifier(getExtensionId()));
        if (getCritical()) {
            arrayList.add(new Asn1Boolean(getCritical()));
        }
        arrayList.add(new Asn1OctetString(getExtensionValue()));
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509Extension() {
    }

    public X509Extension(long[] jArr, byte[] bArr) {
        this(jArr, bArr, false);
    }

    public X509Extension(long[] jArr, byte[] bArr, boolean z) {
        setExtensionId(jArr);
        setExtensionValue(bArr);
        setCritical(z);
    }
}
