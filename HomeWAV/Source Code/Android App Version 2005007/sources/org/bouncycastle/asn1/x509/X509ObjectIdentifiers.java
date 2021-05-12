package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public interface X509ObjectIdentifiers {
    public static final ASN1ObjectIdentifier commonName = new ASN1ObjectIdentifier("2.5.4.3").intern();
    public static final ASN1ObjectIdentifier countryName = new ASN1ObjectIdentifier("2.5.4.6").intern();
    public static final ASN1ObjectIdentifier crlAccessMethod;
    public static final ASN1ObjectIdentifier id_SHA1 = new ASN1ObjectIdentifier("1.3.14.3.2.26").intern();
    public static final ASN1ObjectIdentifier id_ad;
    public static final ASN1ObjectIdentifier id_ad_caIssuers;
    public static final ASN1ObjectIdentifier id_ad_ocsp;
    public static final ASN1ObjectIdentifier id_at_name = new ASN1ObjectIdentifier("2.5.4.41").intern();
    public static final ASN1ObjectIdentifier id_at_telephoneNumber = new ASN1ObjectIdentifier("2.5.4.20").intern();
    public static final ASN1ObjectIdentifier id_ce = new ASN1ObjectIdentifier("2.5.29");
    public static final ASN1ObjectIdentifier id_ea_rsa = new ASN1ObjectIdentifier("2.5.8.1.1").intern();
    public static final ASN1ObjectIdentifier id_pe;
    public static final ASN1ObjectIdentifier id_pkix;
    public static final ASN1ObjectIdentifier localityName = new ASN1ObjectIdentifier("2.5.4.7").intern();
    public static final ASN1ObjectIdentifier ocspAccessMethod;
    public static final ASN1ObjectIdentifier organization = new ASN1ObjectIdentifier("2.5.4.10").intern();
    public static final ASN1ObjectIdentifier organizationalUnitName = new ASN1ObjectIdentifier("2.5.4.11").intern();
    public static final ASN1ObjectIdentifier ripemd160 = new ASN1ObjectIdentifier("1.3.36.3.2.1").intern();
    public static final ASN1ObjectIdentifier ripemd160WithRSAEncryption = new ASN1ObjectIdentifier("1.3.36.3.3.1.2").intern();
    public static final ASN1ObjectIdentifier stateOrProvinceName = new ASN1ObjectIdentifier("2.5.4.8").intern();

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
        id_pkix = aSN1ObjectIdentifier;
        id_pe = aSN1ObjectIdentifier.branch("1");
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("48");
        id_ad = branch;
        ASN1ObjectIdentifier intern = branch.branch("2").intern();
        id_ad_caIssuers = intern;
        ASN1ObjectIdentifier intern2 = branch.branch("1").intern();
        id_ad_ocsp = intern2;
        ocspAccessMethod = intern2;
        crlAccessMethod = intern;
    }
}
