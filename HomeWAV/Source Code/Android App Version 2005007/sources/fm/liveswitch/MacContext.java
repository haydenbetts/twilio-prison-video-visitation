package fm.liveswitch;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacContext extends MacContextBase {
    private Mac _mac;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public MacContext(MacType macType, DataBuffer dataBuffer) {
        super(macType);
        String str;
        try {
            int i = AnonymousClass1.$SwitchMap$fm$liveswitch$MacType[macType.ordinal()];
            if (i == 1) {
                str = "HmacMD5";
            } else if (i == 2) {
                str = "HmacSHA1";
            } else if (i == 3) {
                str = "HmacSHA256";
            } else {
                throw new Exception("Unrecognized MAC type.");
            }
            Mac instance = Mac.getInstance(str);
            this._mac = instance;
            instance.init(new SecretKeySpec(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength(), str));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: fm.liveswitch.MacContext$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$fm$liveswitch$MacType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                fm.liveswitch.MacType[] r0 = fm.liveswitch.MacType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$fm$liveswitch$MacType = r0
                fm.liveswitch.MacType r1 = fm.liveswitch.MacType.HmacMd5     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$fm$liveswitch$MacType     // Catch:{ NoSuchFieldError -> 0x001d }
                fm.liveswitch.MacType r1 = fm.liveswitch.MacType.HmacSha1     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$fm$liveswitch$MacType     // Catch:{ NoSuchFieldError -> 0x0028 }
                fm.liveswitch.MacType r1 = fm.liveswitch.MacType.HmacSha256     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MacContext.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public DataBuffer doCompute(DataBuffer dataBuffer) {
        this._mac.update(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
        return DataBuffer.wrap(this._mac.doFinal());
    }
}
