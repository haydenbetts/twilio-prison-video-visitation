package fm.liveswitch;

import java.util.ArrayList;

class SctpRestartOfAnAssociationWithNewAddresses extends SctpErrorCause {
    private SctpTlvParameter[] _addresses;

    public SctpTlvParameter[] getAddresses() {
        return this._addresses;
    }

    public static byte[] getBytes(SctpRestartOfAnAssociationWithNewAddresses sctpRestartOfAnAssociationWithNewAddresses) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpRestartOfAnAssociationWithNewAddresses.getCauseCode(), false));
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpRestartOfAnAssociationWithNewAddresses.getAddresses()); i++) {
            byteCollection.addRange(sctpRestartOfAnAssociationWithNewAddresses.getAddresses()[i].getBytes());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpRestartOfAnAssociationWithNewAddresses parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = 4;
            ArrayList arrayList = new ArrayList();
            while (i < fromBytes16) {
                SctpTlvParameter parseBytes = SctpTlvParameter.parseBytes(bArr, i, integerHolder);
                i += integerHolder.getValue();
                arrayList.add(parseBytes);
            }
            integerHolder.setValue(i);
            Log.debug("SCTP Error: association restart with a new address.");
            return new SctpRestartOfAnAssociationWithNewAddresses((SctpTlvParameter[]) arrayList.toArray(new SctpTlvParameter[0]));
        } catch (Exception unused) {
            Log.warn("Could not parse RestartOfAnAssociationWithNewAddresses");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpRestartOfAnAssociationWithNewAddresses(SctpTlvParameter[] sctpTlvParameterArr) {
        super.setCauseCode(11);
        setAddresses(sctpTlvParameterArr);
    }

    public void setAddresses(SctpTlvParameter[] sctpTlvParameterArr) {
        this._addresses = sctpTlvParameterArr;
    }
}
