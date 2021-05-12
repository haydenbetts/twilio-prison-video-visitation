package fm.liveswitch;

import java.security.MessageDigest;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class HashContext extends HashContextBase {
    private MessageDigest _digest;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public HashContext(HashType hashType) {
        super(hashType);
        String str;
        try {
            int i = AnonymousClass1.$SwitchMap$fm$liveswitch$HashType[hashType.ordinal()];
            if (i == 1) {
                str = "MD5";
            } else if (i == 2) {
                str = McElieceCCA2KeyGenParameterSpec.SHA1;
            } else if (i == 3) {
                str = McElieceCCA2KeyGenParameterSpec.SHA256;
            } else {
                throw new Exception("Unrecognized hash type.");
            }
            this._digest = MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: fm.liveswitch.HashContext$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$fm$liveswitch$HashType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                fm.liveswitch.HashType[] r0 = fm.liveswitch.HashType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$fm$liveswitch$HashType = r0
                fm.liveswitch.HashType r1 = fm.liveswitch.HashType.Md5     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$fm$liveswitch$HashType     // Catch:{ NoSuchFieldError -> 0x001d }
                fm.liveswitch.HashType r1 = fm.liveswitch.HashType.Sha1     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$fm$liveswitch$HashType     // Catch:{ NoSuchFieldError -> 0x0028 }
                fm.liveswitch.HashType r1 = fm.liveswitch.HashType.Sha256     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.HashContext.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public DataBuffer doCompute(DataBuffer dataBuffer) {
        this._digest.update(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
        return DataBuffer.wrap(this._digest.digest());
    }
}
