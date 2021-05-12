package org.bouncycastle.asn1.rosstandart;

import me.zhanghai.android.materialprogressbar.BuildConfig;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public interface RosstandartObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_tc26;
    public static final ASN1ObjectIdentifier id_tc26_gost_28147_param_Z;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_256_paramSetA;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetA;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetB;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetC;
    public static final ASN1ObjectIdentifier id_tc26_gost_3411_12_256;
    public static final ASN1ObjectIdentifier id_tc26_gost_3411_12_512;
    public static final ASN1ObjectIdentifier id_tc26_hmac_gost_3411_12_256;
    public static final ASN1ObjectIdentifier id_tc26_hmac_gost_3411_12_512;
    public static final ASN1ObjectIdentifier rosstandart;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.643.7");
        rosstandart = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1");
        id_tc26 = branch;
        id_tc26_gost_3411_12_256 = branch.branch("1.2.2");
        id_tc26_gost_3411_12_512 = branch.branch("1.2.3");
        id_tc26_hmac_gost_3411_12_256 = branch.branch("1.4.1");
        id_tc26_hmac_gost_3411_12_512 = branch.branch(BuildConfig.VERSION_NAME);
        id_tc26_gost_3410_12_256_paramSetA = branch.branch("2.1.1.1");
        id_tc26_gost_3410_12_512_paramSetA = branch.branch("2.1.2.1");
        id_tc26_gost_3410_12_512_paramSetB = branch.branch("2.1.2.2");
        id_tc26_gost_3410_12_512_paramSetC = branch.branch("2.1.2.3");
        id_tc26_gost_28147_param_Z = branch.branch("2.5.1.1");
    }
}
