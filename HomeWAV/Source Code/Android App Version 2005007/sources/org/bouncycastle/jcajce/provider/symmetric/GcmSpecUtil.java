package org.bouncycastle.jcajce.provider.symmetric;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.util.Integers;

class GcmSpecUtil {
    static final Class gcmSpecClass = lookup("javax.crypto.spec.GCMParameterSpec");

    GcmSpecUtil() {
    }

    static GCMParameters extractGcmParameters(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        try {
            Class cls = gcmSpecClass;
            return new GCMParameters((byte[]) cls.getDeclaredMethod("getIV", new Class[0]).invoke(algorithmParameterSpec, new Object[0]), ((Integer) cls.getDeclaredMethod("getTLen", new Class[0]).invoke(algorithmParameterSpec, new Object[0])).intValue() / 8);
        } catch (Exception unused) {
            throw new InvalidParameterSpecException("Cannot process GCMParameterSpec");
        }
    }

    static AlgorithmParameterSpec extractGcmSpec(ASN1Primitive aSN1Primitive) throws InvalidParameterSpecException {
        try {
            GCMParameters instance = GCMParameters.getInstance(aSN1Primitive);
            return (AlgorithmParameterSpec) gcmSpecClass.getConstructor(new Class[]{Integer.TYPE, byte[].class}).newInstance(new Object[]{Integers.valueOf(instance.getIcvLen() * 8), instance.getNonce()});
        } catch (NoSuchMethodException unused) {
            throw new InvalidParameterSpecException("No constructor found!");
        } catch (Exception e) {
            throw new InvalidParameterSpecException("Construction failed: " + e.getMessage());
        }
    }

    static boolean gcmSpecExists() {
        return gcmSpecClass != null;
    }

    static boolean isGcmSpec(Class cls) {
        return gcmSpecClass == cls;
    }

    static boolean isGcmSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        Class cls = gcmSpecClass;
        return cls != null && cls.isInstance(algorithmParameterSpec);
    }

    private static Class lookup(String str) {
        try {
            return GcmSpecUtil.class.getClassLoader().loadClass(str);
        } catch (Exception unused) {
            return null;
        }
    }
}
