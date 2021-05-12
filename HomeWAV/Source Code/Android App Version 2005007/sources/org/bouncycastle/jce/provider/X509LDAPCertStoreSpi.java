package org.bouncycastle.jce.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.slf4j.Marker;

public class X509LDAPCertStoreSpi extends CertStoreSpi {
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private X509LDAPCertStoreParameters params;

    public X509LDAPCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (certStoreParameters instanceof X509LDAPCertStoreParameters) {
            this.params = (X509LDAPCertStoreParameters) certStoreParameters;
            return;
        }
        throw new InvalidAlgorithmParameterException(X509LDAPCertStoreSpi.class.getName() + ": parameter must be a " + X509LDAPCertStoreParameters.class.getName() + " object\n" + certStoreParameters.toString());
    }

    private Set certSubjectSerialSearch(X509CertSelector x509CertSelector, String[] strArr, String str, String str2) throws CertStoreException {
        String str3;
        String str4;
        Set search;
        HashSet hashSet = new HashSet();
        try {
            if (x509CertSelector.getSubjectAsBytes() == null) {
                if (x509CertSelector.getSubjectAsString() == null) {
                    if (x509CertSelector.getCertificate() == null) {
                        search = search(str, Marker.ANY_MARKER, strArr);
                        hashSet.addAll(search);
                        return hashSet;
                    }
                }
            }
            if (x509CertSelector.getCertificate() != null) {
                str4 = x509CertSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                str3 = x509CertSelector.getCertificate().getSerialNumber().toString();
            } else {
                str4 = x509CertSelector.getSubjectAsBytes() != null ? new X500Principal(x509CertSelector.getSubjectAsBytes()).getName("RFC1779") : x509CertSelector.getSubjectAsString();
                str3 = null;
            }
            hashSet.addAll(search(str, Marker.ANY_MARKER + parseDN(str4, str2) + Marker.ANY_MARKER, strArr));
            if (!(str3 == null || this.params.getSearchForSerialNumberIn() == null)) {
                search = search(this.params.getSearchForSerialNumberIn(), Marker.ANY_MARKER + str3 + Marker.ANY_MARKER, strArr);
                hashSet.addAll(search);
            }
            return hashSet;
        } catch (IOException e) {
            throw new CertStoreException("exception processing selector: " + e);
        }
    }

    private DirContext connectLDAP() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", LDAP_PROVIDER);
        properties.setProperty("java.naming.batchsize", "0");
        properties.setProperty("java.naming.provider.url", this.params.getLdapURL());
        properties.setProperty("java.naming.factory.url.pkgs", URL_CONTEXT_PREFIX);
        properties.setProperty("java.naming.referral", REFERRALS_IGNORE);
        properties.setProperty("java.naming.security.authentication", "none");
        return new InitialDirContext(properties);
    }

    private Set getCACertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.params.getCACertificateAttribute()};
        Set certSubjectSerialSearch = certSubjectSerialSearch(x509CertSelector, strArr, this.params.getLdapCACertificateAttributeName(), this.params.getCACertificateSubjectAttributeName());
        if (certSubjectSerialSearch.isEmpty()) {
            certSubjectSerialSearch.addAll(search((String) null, Marker.ANY_MARKER, strArr));
        }
        return certSubjectSerialSearch;
    }

    private Set getCrossCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.params.getCrossCertificateAttribute()};
        Set certSubjectSerialSearch = certSubjectSerialSearch(x509CertSelector, strArr, this.params.getLdapCrossCertificateAttributeName(), this.params.getCrossCertificateSubjectAttributeName());
        if (certSubjectSerialSearch.isEmpty()) {
            certSubjectSerialSearch.addAll(search((String) null, Marker.ANY_MARKER, strArr));
        }
        return certSubjectSerialSearch;
    }

    private Set getEndCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        return certSubjectSerialSearch(x509CertSelector, new String[]{this.params.getUserCertificateAttribute()}, this.params.getLdapUserCertificateAttributeName(), this.params.getUserCertificateSubjectAttributeName());
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x004e  */
    private java.lang.String parseDN(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toLowerCase()
            java.lang.String r1 = r6.toLowerCase()
            int r0 = r0.indexOf(r1)
            int r6 = r6.length()
            int r0 = r0 + r6
            java.lang.String r5 = r5.substring(r0)
            r6 = 44
            int r0 = r5.indexOf(r6)
            r1 = -1
            if (r0 != r1) goto L_0x0022
        L_0x001e:
            int r0 = r5.length()
        L_0x0022:
            int r2 = r0 + -1
            char r2 = r5.charAt(r2)
            r3 = 92
            if (r2 != r3) goto L_0x0035
            int r0 = r0 + 1
            int r0 = r5.indexOf(r6, r0)
            if (r0 != r1) goto L_0x0022
            goto L_0x001e
        L_0x0035:
            r6 = 0
            java.lang.String r5 = r5.substring(r6, r0)
            r0 = 61
            int r0 = r5.indexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r5 = r5.substring(r0)
            char r0 = r5.charAt(r6)
            r2 = 32
            if (r0 != r2) goto L_0x0052
            java.lang.String r5 = r5.substring(r1)
        L_0x0052:
            java.lang.String r0 = "\""
            boolean r2 = r5.startsWith(r0)
            if (r2 == 0) goto L_0x005e
            java.lang.String r5 = r5.substring(r1)
        L_0x005e:
            boolean r0 = r5.endsWith(r0)
            if (r0 == 0) goto L_0x006d
            int r0 = r5.length()
            int r0 = r0 - r1
            java.lang.String r5 = r5.substring(r6, r0)
        L_0x006d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.X509LDAPCertStoreSpi.parseDN(java.lang.String, java.lang.String):java.lang.String");
    }

    private Set search(String str, String str2, String[] strArr) throws CertStoreException {
        String str3 = str + "=" + str2;
        String str4 = null;
        if (str == null) {
            str3 = str4;
        }
        HashSet hashSet = new HashSet();
        try {
            DirContext connectLDAP = connectLDAP();
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(2);
            searchControls.setCountLimit(0);
            for (int i = 0; i < strArr.length; i++) {
                String[] strArr2 = {strArr[i]};
                searchControls.setReturningAttributes(strArr2);
                String str5 = "(&(" + str3 + ")(" + strArr2[0] + "=*))";
                if (str3 == null) {
                    str5 = "(" + strArr2[0] + "=*)";
                }
                NamingEnumeration search = connectLDAP.search(this.params.getBaseDN(), str5, searchControls);
                while (search.hasMoreElements()) {
                    NamingEnumeration all = ((Attribute) ((SearchResult) search.next()).getAttributes().getAll().next()).getAll();
                    while (all.hasMore()) {
                        hashSet.add(all.next());
                    }
                }
            }
            if (connectLDAP != null) {
                try {
                    connectLDAP.close();
                } catch (Exception unused) {
                }
            }
            return hashSet;
        } catch (Exception e) {
            throw new CertStoreException("Error getting results from LDAP directory " + e);
        } catch (Throwable th) {
            if (str4 != null) {
                try {
                    str4.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        String str;
        String str2;
        String[] strArr = {this.params.getCertificateRevocationListAttribute()};
        if (cRLSelector instanceof X509CRLSelector) {
            X509CRLSelector x509CRLSelector = (X509CRLSelector) cRLSelector;
            HashSet hashSet = new HashSet();
            String ldapCertificateRevocationListAttributeName = this.params.getLdapCertificateRevocationListAttributeName();
            HashSet<byte[]> hashSet2 = new HashSet<>();
            if (x509CRLSelector.getIssuerNames() != null) {
                for (Object next : x509CRLSelector.getIssuerNames()) {
                    if (next instanceof String) {
                        str = this.params.getCertificateRevocationListIssuerAttributeName();
                        str2 = (String) next;
                    } else {
                        str = this.params.getCertificateRevocationListIssuerAttributeName();
                        str2 = new X500Principal((byte[]) next).getName("RFC1779");
                    }
                    String parseDN = parseDN(str2, str);
                    hashSet2.addAll(search(ldapCertificateRevocationListAttributeName, Marker.ANY_MARKER + parseDN + Marker.ANY_MARKER, strArr));
                }
            } else {
                hashSet2.addAll(search(ldapCertificateRevocationListAttributeName, Marker.ANY_MARKER, strArr));
            }
            hashSet2.addAll(search((String) null, Marker.ANY_MARKER, strArr));
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
                for (byte[] byteArrayInputStream : hashSet2) {
                    CRL generateCRL = instance.generateCRL(new ByteArrayInputStream(byteArrayInputStream));
                    if (x509CRLSelector.match(generateCRL)) {
                        hashSet.add(generateCRL);
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException("CRL cannot be constructed from LDAP result " + e);
            }
        } else {
            throw new CertStoreException("selector is not a X509CRLSelector");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:12|13|14|(1:16)|17|(1:19)|20|21|(6:25|26|27|(2:29|43)(1:42)|41|22)|40) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0077 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081 A[Catch:{ Exception -> 0x009d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection engineGetCertificates(java.security.cert.CertSelector r7) throws java.security.cert.CertStoreException {
        /*
            r6 = this;
            boolean r0 = r7 instanceof java.security.cert.X509CertSelector
            if (r0 == 0) goto L_0x00b5
            java.security.cert.X509CertSelector r7 = (java.security.cert.X509CertSelector) r7
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Set r1 = r6.getEndCertificates(r7)
            java.util.Set r2 = r6.getCACertificates(r7)
            r1.addAll(r2)
            java.util.Set r2 = r6.getCrossCertificates(r7)
            r1.addAll(r2)
            java.util.Iterator r1 = r1.iterator()
            java.lang.String r2 = "X.509"
            java.lang.String r3 = "BC"
            java.security.cert.CertificateFactory r2 = java.security.cert.CertificateFactory.getInstance(r2, r3)     // Catch:{ Exception -> 0x009d }
        L_0x0029:
            boolean r3 = r1.hasNext()     // Catch:{ Exception -> 0x009d }
            if (r3 == 0) goto L_0x009c
            java.lang.Object r3 = r1.next()     // Catch:{ Exception -> 0x009d }
            byte[] r3 = (byte[]) r3     // Catch:{ Exception -> 0x009d }
            byte[] r3 = (byte[]) r3     // Catch:{ Exception -> 0x009d }
            if (r3 == 0) goto L_0x0029
            int r4 = r3.length     // Catch:{ Exception -> 0x009d }
            if (r4 != 0) goto L_0x003d
            goto L_0x0029
        L_0x003d:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x009d }
            r4.<init>()     // Catch:{ Exception -> 0x009d }
            r4.add(r3)     // Catch:{ Exception -> 0x009d }
            org.bouncycastle.asn1.ASN1InputStream r5 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            r5.<init>((byte[]) r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            org.bouncycastle.asn1.ASN1Primitive r3 = r5.readObject()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            org.bouncycastle.asn1.x509.CertificatePair r3 = org.bouncycastle.asn1.x509.CertificatePair.getInstance(r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            r4.clear()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            org.bouncycastle.asn1.x509.Certificate r5 = r3.getForward()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            if (r5 == 0) goto L_0x0066
            org.bouncycastle.asn1.x509.Certificate r5 = r3.getForward()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            byte[] r5 = r5.getEncoded()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            r4.add(r5)     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
        L_0x0066:
            org.bouncycastle.asn1.x509.Certificate r5 = r3.getReverse()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            if (r5 == 0) goto L_0x0077
            org.bouncycastle.asn1.x509.Certificate r3 = r3.getReverse()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            byte[] r3 = r3.getEncoded()     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
            r4.add(r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0077 }
        L_0x0077:
            java.util.Iterator r3 = r4.iterator()     // Catch:{ Exception -> 0x009d }
        L_0x007b:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x009d }
            if (r4 == 0) goto L_0x0029
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x009d }
            java.lang.Object r5 = r3.next()     // Catch:{ Exception -> 0x009d }
            byte[] r5 = (byte[]) r5     // Catch:{ Exception -> 0x009d }
            byte[] r5 = (byte[]) r5     // Catch:{ Exception -> 0x009d }
            r4.<init>(r5)     // Catch:{ Exception -> 0x009d }
            java.security.cert.Certificate r4 = r2.generateCertificate(r4)     // Catch:{ Exception -> 0x007b }
            boolean r5 = r7.match(r4)     // Catch:{ Exception -> 0x007b }
            if (r5 == 0) goto L_0x007b
            r0.add(r4)     // Catch:{ Exception -> 0x007b }
            goto L_0x007b
        L_0x009c:
            return r0
        L_0x009d:
            r7 = move-exception
            java.security.cert.CertStoreException r0 = new java.security.cert.CertStoreException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "certificate cannot be constructed from LDAP result: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L_0x00b5:
            java.security.cert.CertStoreException r7 = new java.security.cert.CertStoreException
            java.lang.String r0 = "selector is not a X509CertSelector"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.X509LDAPCertStoreSpi.engineGetCertificates(java.security.cert.CertSelector):java.util.Collection");
    }
}
