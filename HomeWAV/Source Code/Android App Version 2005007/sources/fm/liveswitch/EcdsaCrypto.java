package fm.liveswitch;

public class EcdsaCrypto {
    private static EcdsaNamedCurve _defaultNamedCurve = EcdsaNamedCurve.P256;

    public static EcdsaNamedCurve getDefaultNamedCurve() {
        return _defaultNamedCurve;
    }

    public static void setDefaultNamedCurve(EcdsaNamedCurve ecdsaNamedCurve) {
        _defaultNamedCurve = ecdsaNamedCurve;
    }

    public static EcdsaKey createKey() {
        return createKey(_defaultNamedCurve);
    }

    /* renamed from: fm.liveswitch.EcdsaCrypto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$fm$liveswitch$CryptoLibrary;

        static {
            int[] iArr = new int[CryptoLibrary.values().length];
            $SwitchMap$fm$liveswitch$CryptoLibrary = iArr;
            try {
                iArr[CryptoLibrary.BouncyCastle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static EcdsaKey createKey(EcdsaNamedCurve ecdsaNamedCurve) {
        int i = AnonymousClass1.$SwitchMap$fm$liveswitch$CryptoLibrary[Platform.getInstance().getCryptoLibrary().ordinal()];
        return BouncyCastleEcdsaCrypto.createKey(ecdsaNamedCurve);
    }

    public static byte[] sign(byte[] bArr, EcdsaKey ecdsaKey) {
        int i = AnonymousClass1.$SwitchMap$fm$liveswitch$CryptoLibrary[Platform.getInstance().getCryptoLibrary().ordinal()];
        return BouncyCastleEcdsaCrypto.sign(bArr, ecdsaKey);
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, EcdsaKey ecdsaKey) {
        int i = AnonymousClass1.$SwitchMap$fm$liveswitch$CryptoLibrary[Platform.getInstance().getCryptoLibrary().ordinal()];
        return BouncyCastleEcdsaCrypto.verify(bArr, bArr2, ecdsaKey);
    }
}
