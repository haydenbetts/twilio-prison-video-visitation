package org.bouncycastle.x509.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.jce.provider.X509AttrCertParser;
import org.bouncycastle.jce.provider.X509CRLParser;
import org.bouncycastle.jce.provider.X509CertParser;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.slf4j.Marker;

public class LDAPStoreHelper {
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private static int cacheSize = 32;
    private static long lifeTime = 60000;
    private Map cacheMap = new HashMap(cacheSize);
    private X509LDAPCertStoreParameters params;

    public LDAPStoreHelper(X509LDAPCertStoreParameters x509LDAPCertStoreParameters) {
        this.params = x509LDAPCertStoreParameters;
    }

    private synchronized void addToCache(String str, List list) {
        Map map;
        Date date = new Date(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        arrayList.add(date);
        arrayList.add(list);
        if (this.cacheMap.containsKey(str)) {
            map = this.cacheMap;
        } else {
            if (this.cacheMap.size() >= cacheSize) {
                long time = date.getTime();
                Object obj = null;
                for (Map.Entry entry : this.cacheMap.entrySet()) {
                    long time2 = ((Date) ((List) entry.getValue()).get(0)).getTime();
                    if (time2 < time) {
                        obj = entry.getKey();
                        time = time2;
                    }
                }
                this.cacheMap.remove(obj);
            }
            map = this.cacheMap;
        }
        map.put(str, arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c A[LOOP:0: B:26:0x009c->B:28:0x009f, LOOP_START, PHI: r4 
      PHI: (r4v5 int) = (r4v1 int), (r4v6 int) binds: [B:25:0x009a, B:28:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d9 A[LOOP:1: B:34:0x00d3->B:36:0x00d9, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List attrCertSubjectSerialSearch(org.bouncycastle.x509.X509AttributeCertStoreSelector r7, java.lang.String[] r8, java.lang.String[] r9, java.lang.String[] r10) throws org.bouncycastle.util.StoreException {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            r3 = 0
            if (r2 == 0) goto L_0x003d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            if (r2 == 0) goto L_0x002a
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            java.lang.String r2 = r2.toString()
            r1.add(r2)
        L_0x002a:
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            if (r2 == 0) goto L_0x003d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            goto L_0x003e
        L_0x003d:
            r2 = r3
        L_0x003e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            if (r4 == 0) goto L_0x006d
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r4 = r4.getHolder()
            java.security.Principal[] r4 = r4.getEntityNames()
            if (r4 == 0) goto L_0x005e
            org.bouncycastle.x509.X509AttributeCertificate r2 = r7.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r2.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
        L_0x005e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            java.math.BigInteger r4 = r4.getSerialNumber()
            java.lang.String r4 = r4.toString()
            r1.add(r4)
        L_0x006d:
            r4 = 0
            if (r2 == 0) goto L_0x0087
            r3 = r2[r4]
            boolean r3 = r3 instanceof javax.security.auth.x500.X500Principal
            if (r3 == 0) goto L_0x0081
            r2 = r2[r4]
            javax.security.auth.x500.X500Principal r2 = (javax.security.auth.x500.X500Principal) r2
            java.lang.String r3 = "RFC1779"
            java.lang.String r3 = r2.getName(r3)
            goto L_0x0087
        L_0x0081:
            r2 = r2[r4]
            java.lang.String r3 = r2.getName()
        L_0x0087:
            java.math.BigInteger r2 = r7.getSerialNumber()
            if (r2 == 0) goto L_0x0098
            java.math.BigInteger r7 = r7.getSerialNumber()
            java.lang.String r7 = r7.toString()
            r1.add(r7)
        L_0x0098:
            java.lang.String r7 = "*"
            if (r3 == 0) goto L_0x00c1
        L_0x009c:
            int r2 = r10.length
            if (r4 >= r2) goto L_0x00c1
            r2 = r10[r4]
            java.lang.String r2 = r6.parseDN(r3, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            r5.append(r2)
            r5.append(r7)
            java.lang.String r2 = r5.toString()
            java.util.List r2 = r6.search(r9, r2, r8)
            r0.addAll(r2)
            int r4 = r4 + 1
            goto L_0x009c
        L_0x00c1:
            int r10 = r1.size()
            if (r10 <= 0) goto L_0x00f1
            org.bouncycastle.jce.X509LDAPCertStoreParameters r10 = r6.params
            java.lang.String r10 = r10.getSearchForSerialNumberIn()
            if (r10 == 0) goto L_0x00f1
            java.util.Iterator r10 = r1.iterator()
        L_0x00d3:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x00f1
            java.lang.Object r2 = r10.next()
            java.lang.String r2 = (java.lang.String) r2
            org.bouncycastle.jce.X509LDAPCertStoreParameters r4 = r6.params
            java.lang.String r4 = r4.getSearchForSerialNumberIn()
            java.lang.String[] r4 = r6.splitString(r4)
            java.util.List r2 = r6.search(r4, r2, r8)
            r0.addAll(r2)
            goto L_0x00d3
        L_0x00f1:
            int r10 = r1.size()
            if (r10 != 0) goto L_0x0100
            if (r3 != 0) goto L_0x0100
            java.util.List r7 = r6.search(r9, r7, r8)
            r0.addAll(r7)
        L_0x0100:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.attrCertSubjectSerialSearch(org.bouncycastle.x509.X509AttributeCertStoreSelector, java.lang.String[], java.lang.String[], java.lang.String[]):java.util.List");
    }

    private List cRLIssuerSearch(X509CRLStoreSelector x509CRLStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        HashSet<X500Principal> hashSet = new HashSet<>();
        if (x509CRLStoreSelector.getIssuers() != null) {
            hashSet.addAll(x509CRLStoreSelector.getIssuers());
        }
        if (x509CRLStoreSelector.getCertificateChecking() != null) {
            hashSet.add(getCertificateIssuer(x509CRLStoreSelector.getCertificateChecking()));
        }
        if (x509CRLStoreSelector.getAttrCertificateChecking() != null) {
            Principal[] principals = x509CRLStoreSelector.getAttrCertificateChecking().getIssuer().getPrincipals();
            for (int i = 0; i < principals.length; i++) {
                if (principals[i] instanceof X500Principal) {
                    hashSet.add(principals[i]);
                }
            }
        }
        String str = null;
        for (X500Principal name : hashSet) {
            str = name.getName("RFC1779");
            for (String parseDN : strArr3) {
                arrayList.addAll(search(strArr2, Marker.ANY_MARKER + parseDN(str, parseDN) + Marker.ANY_MARKER, strArr));
            }
        }
        if (str == null) {
            arrayList.addAll(search(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
    }

    private List certSubjectSerialSearch(X509CertStoreSelector x509CertStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String subjectAsString = getSubjectAsString(x509CertStoreSelector);
        String bigInteger = x509CertStoreSelector.getSerialNumber() != null ? x509CertStoreSelector.getSerialNumber().toString() : null;
        if (x509CertStoreSelector.getCertificate() != null) {
            subjectAsString = x509CertStoreSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            bigInteger = x509CertStoreSelector.getCertificate().getSerialNumber().toString();
        }
        if (subjectAsString != null) {
            for (String parseDN : strArr3) {
                arrayList.addAll(search(strArr2, Marker.ANY_MARKER + parseDN(subjectAsString, parseDN) + Marker.ANY_MARKER, strArr));
            }
        }
        if (!(bigInteger == null || this.params.getSearchForSerialNumberIn() == null)) {
            arrayList.addAll(search(splitString(this.params.getSearchForSerialNumberIn()), bigInteger, strArr));
        }
        if (bigInteger == null && subjectAsString == null) {
            arrayList.addAll(search(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
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

    private Set createAttributeCertificates(List list, X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        X509AttrCertParser x509AttrCertParser = new X509AttrCertParser();
        while (it.hasNext()) {
            try {
                x509AttrCertParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) x509AttrCertParser.engineRead();
                if (x509AttributeCertStoreSelector.match(x509AttributeCertificate)) {
                    hashSet.add(x509AttributeCertificate);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    private Set createCRLs(List list, X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        X509CRLParser x509CRLParser = new X509CRLParser();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                x509CRLParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509CRL x509crl = (X509CRL) x509CRLParser.engineRead();
                if (x509CRLStoreSelector.match((Object) x509crl)) {
                    hashSet.add(x509crl);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    private Set createCerts(List list, X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        X509CertParser x509CertParser = new X509CertParser();
        while (it.hasNext()) {
            try {
                x509CertParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509Certificate x509Certificate = (X509Certificate) x509CertParser.engineRead();
                if (x509CertStoreSelector.match((Object) x509Certificate)) {
                    hashSet.add(x509Certificate);
                }
            } catch (Exception unused) {
            }
        }
        return hashSet;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0028 */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[ExcHandler: IOException | CertificateParsingException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:5:0x0028] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set createCrossCertificatePairs(java.util.List r9, org.bouncycastle.x509.X509CertPairStoreSelector r10) throws org.bouncycastle.util.StoreException {
        /*
            r8 = this;
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = 0
        L_0x0006:
            int r2 = r9.size()
            if (r1 >= r2) goto L_0x006c
            org.bouncycastle.jce.provider.X509CertPairParser r2 = new org.bouncycastle.jce.provider.X509CertPairParser     // Catch:{ StreamParsingException -> 0x0028 }
            r2.<init>()     // Catch:{ StreamParsingException -> 0x0028 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ StreamParsingException -> 0x0028 }
            java.lang.Object r4 = r9.get(r1)     // Catch:{ StreamParsingException -> 0x0028 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0028 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0028 }
            r3.<init>(r4)     // Catch:{ StreamParsingException -> 0x0028 }
            r2.engineInit(r3)     // Catch:{ StreamParsingException -> 0x0028 }
            java.lang.Object r2 = r2.engineRead()     // Catch:{ StreamParsingException -> 0x0028 }
            org.bouncycastle.x509.X509CertificatePair r2 = (org.bouncycastle.x509.X509CertificatePair) r2     // Catch:{ StreamParsingException -> 0x0028 }
            goto L_0x0060
        L_0x0028:
            java.lang.Object r2 = r9.get(r1)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            byte[] r2 = (byte[]) r2     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            byte[] r2 = (byte[]) r2     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            int r3 = r1 + 1
            java.lang.Object r4 = r9.get(r3)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            byte[] r4 = (byte[]) r4     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            byte[] r4 = (byte[]) r4     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.x509.X509CertificatePair r5 = new org.bouncycastle.x509.X509CertificatePair     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.x509.CertificatePair r6 = new org.bouncycastle.asn1.x509.CertificatePair     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.ASN1InputStream r7 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            r7.<init>((byte[]) r2)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.ASN1Primitive r2 = r7.readObject()     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.x509.Certificate r2 = org.bouncycastle.asn1.x509.Certificate.getInstance(r2)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.ASN1InputStream r7 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            r7.<init>((byte[]) r4)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.ASN1Primitive r4 = r7.readObject()     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            org.bouncycastle.asn1.x509.Certificate r4 = org.bouncycastle.asn1.x509.Certificate.getInstance(r4)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            r6.<init>(r2, r4)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            r5.<init>(r6)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            r1 = r3
            r2 = r5
        L_0x0060:
            boolean r3 = r10.match(r2)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
            if (r3 == 0) goto L_0x0069
            r0.add(r2)     // Catch:{ IOException | CertificateParsingException -> 0x0069, IOException | CertificateParsingException -> 0x0069 }
        L_0x0069:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.createCrossCertificatePairs(java.util.List, org.bouncycastle.x509.X509CertPairStoreSelector):java.util.Set");
    }

    private List crossCertificatePairSubjectSearch(X509CertPairStoreSelector x509CertPairStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String subjectAsString = x509CertPairStoreSelector.getForwardSelector() != null ? getSubjectAsString(x509CertPairStoreSelector.getForwardSelector()) : null;
        if (!(x509CertPairStoreSelector.getCertPair() == null || x509CertPairStoreSelector.getCertPair().getForward() == null)) {
            subjectAsString = x509CertPairStoreSelector.getCertPair().getForward().getSubjectX500Principal().getName("RFC1779");
        }
        if (subjectAsString != null) {
            for (String parseDN : strArr3) {
                arrayList.addAll(search(strArr2, Marker.ANY_MARKER + parseDN(subjectAsString, parseDN) + Marker.ANY_MARKER, strArr));
            }
        }
        if (subjectAsString == null) {
            arrayList.addAll(search(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
    }

    private X500Principal getCertificateIssuer(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal();
    }

    private List getFromCache(String str) {
        List list = (List) this.cacheMap.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (list == null || ((Date) list.get(0)).getTime() < currentTimeMillis - lifeTime) {
            return null;
        }
        return (List) list.get(1);
    }

    private String getSubjectAsString(X509CertStoreSelector x509CertStoreSelector) {
        try {
            byte[] subjectAsBytes = x509CertStoreSelector.getSubjectAsBytes();
            if (subjectAsBytes != null) {
                return new X500Principal(subjectAsBytes).getName("RFC1779");
            }
            return null;
        } catch (IOException e) {
            throw new StoreException("exception processing name: " + e.getMessage(), e);
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0042  */
    private java.lang.String parseDN(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toLowerCase()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r6.toLowerCase()
            r1.append(r2)
            java.lang.String r2 = "="
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            r1 = -1
            if (r0 != r1) goto L_0x0023
            java.lang.String r5 = ""
            return r5
        L_0x0023:
            int r6 = r6.length()
            int r0 = r0 + r6
            java.lang.String r5 = r5.substring(r0)
            r6 = 44
            int r0 = r5.indexOf(r6)
            if (r0 != r1) goto L_0x0038
        L_0x0034:
            int r0 = r5.length()
        L_0x0038:
            int r2 = r0 + -1
            char r2 = r5.charAt(r2)
            r3 = 92
            if (r2 != r3) goto L_0x004b
            int r0 = r0 + 1
            int r0 = r5.indexOf(r6, r0)
            if (r0 != r1) goto L_0x0038
            goto L_0x0034
        L_0x004b:
            r6 = 0
            java.lang.String r5 = r5.substring(r6, r0)
            r0 = 61
            int r0 = r5.indexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r5 = r5.substring(r0)
            char r0 = r5.charAt(r6)
            r2 = 32
            if (r0 != r2) goto L_0x0068
            java.lang.String r5 = r5.substring(r1)
        L_0x0068:
            java.lang.String r0 = "\""
            boolean r2 = r5.startsWith(r0)
            if (r2 == 0) goto L_0x0074
            java.lang.String r5 = r5.substring(r1)
        L_0x0074:
            boolean r0 = r5.endsWith(r0)
            if (r0 == 0) goto L_0x0083
            int r0 = r5.length()
            int r0 = r0 - r1
            java.lang.String r5 = r5.substring(r6, r0)
        L_0x0083:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.parseDN(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00fb, code lost:
        if (r3 != null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0108, code lost:
        if (r3 == null) goto L_0x010b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List search(java.lang.String[] r10, java.lang.String r11, java.lang.String[] r12) throws org.bouncycastle.util.StoreException {
        /*
            r9 = this;
            java.lang.String r0 = "("
            java.lang.String r1 = "(|"
            r2 = 0
            r3 = 0
            java.lang.String r4 = ""
            java.lang.String r5 = ")"
            if (r10 != 0) goto L_0x000e
            r10 = r3
            goto L_0x0051
        L_0x000e:
            java.lang.String r6 = "**"
            boolean r6 = r11.equals(r6)
            if (r6 == 0) goto L_0x0018
            java.lang.String r11 = "*"
        L_0x0018:
            r7 = r4
            r6 = 0
        L_0x001a:
            int r8 = r10.length
            if (r6 >= r8) goto L_0x003f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            r8.append(r0)
            r7 = r10[r6]
            r8.append(r7)
            java.lang.String r7 = "="
            r8.append(r7)
            r8.append(r11)
            r8.append(r5)
            java.lang.String r7 = r8.toString()
            int r6 = r6 + 1
            goto L_0x001a
        L_0x003f:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            r10.append(r7)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
        L_0x0051:
            r11 = r4
        L_0x0052:
            int r6 = r12.length
            if (r2 >= r6) goto L_0x0071
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r11)
            r6.append(r0)
            r11 = r12[r2]
            r6.append(r11)
            java.lang.String r11 = "=*)"
            r6.append(r11)
            java.lang.String r11 = r6.toString()
            int r2 = r2 + 1
            goto L_0x0052
        L_0x0071:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r11)
            r0.append(r5)
            java.lang.String r11 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "(&"
            r0.append(r1)
            r0.append(r10)
            r0.append(r4)
            r0.append(r11)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            if (r10 != 0) goto L_0x00a0
            goto L_0x00a1
        L_0x00a0:
            r11 = r0
        L_0x00a1:
            java.util.List r10 = r9.getFromCache(r11)
            if (r10 == 0) goto L_0x00a8
            return r10
        L_0x00a8:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            javax.naming.directory.DirContext r3 = r9.connectLDAP()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.directory.SearchControls r0 = new javax.naming.directory.SearchControls     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            r0.<init>()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            r1 = 2
            r0.setSearchScope(r1)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            r1 = 0
            r0.setCountLimit(r1)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            r0.setReturningAttributes(r12)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            org.bouncycastle.jce.X509LDAPCertStoreParameters r12 = r9.params     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            java.lang.String r12 = r12.getBaseDN()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.NamingEnumeration r12 = r3.search(r12, r11, r0)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
        L_0x00cc:
            boolean r0 = r12.hasMoreElements()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            if (r0 == 0) goto L_0x00f8
            java.lang.Object r0 = r12.next()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.directory.SearchResult r0 = (javax.naming.directory.SearchResult) r0     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.directory.Attributes r0 = r0.getAttributes()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.NamingEnumeration r0 = r0.getAll()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            java.lang.Object r0 = r0.next()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.directory.Attribute r0 = (javax.naming.directory.Attribute) r0     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            javax.naming.NamingEnumeration r0 = r0.getAll()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
        L_0x00ea:
            boolean r1 = r0.hasMore()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            if (r1 == 0) goto L_0x00cc
            java.lang.Object r1 = r0.next()     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            r10.add(r1)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            goto L_0x00ea
        L_0x00f8:
            r9.addToCache(r11, r10)     // Catch:{ NamingException -> 0x0108, all -> 0x0101 }
            if (r3 == 0) goto L_0x010b
        L_0x00fd:
            r3.close()     // Catch:{ Exception -> 0x010b }
            goto L_0x010b
        L_0x0101:
            r10 = move-exception
            if (r3 == 0) goto L_0x0107
            r3.close()     // Catch:{ Exception -> 0x0107 }
        L_0x0107:
            throw r10
        L_0x0108:
            if (r3 == 0) goto L_0x010b
            goto L_0x00fd
        L_0x010b:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.search(java.lang.String[], java.lang.String, java.lang.String[]):java.util.List");
    }

    private String[] splitString(String str) {
        return str.split("\\s+");
    }

    public Collection getAACertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAACertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapAACertificateAttributeName());
        String[] splitString3 = splitString(this.params.getAACertificateSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAttributeAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeAuthorityRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeAuthorityRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeAuthorityRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getAttributeCertificateAttributes(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeCertificateAttributeAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeCertificateAttributeAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeCertificateAttributeSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAttributeCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeCertificateRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeCertificateRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeCertificateRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getAttributeDescriptorCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeDescriptorCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeDescriptorCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeDescriptorCertificateSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAuthorityRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAuthorityRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAuthorityRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getCACertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCACertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapCACertificateAttributeName());
        String[] splitString3 = splitString(this.params.getCACertificateSubjectAttributeName());
        Set createCerts = createCerts(certSubjectSerialSearch(x509CertStoreSelector, splitString, splitString2, splitString3), x509CertStoreSelector);
        if (createCerts.size() == 0) {
            createCerts.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), splitString, splitString2, splitString3), x509CertStoreSelector));
        }
        return createCerts;
    }

    public Collection getCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCertificateRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapCertificateRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getCertificateRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getCrossCertificatePairs(X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCrossCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapCrossCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getCrossCertificateSubjectAttributeName());
        Set createCrossCertificatePairs = createCrossCertificatePairs(crossCertificatePairSubjectSearch(x509CertPairStoreSelector, splitString, splitString2, splitString3), x509CertPairStoreSelector);
        if (createCrossCertificatePairs.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            X509CertPairStoreSelector x509CertPairStoreSelector2 = new X509CertPairStoreSelector();
            x509CertPairStoreSelector2.setForwardSelector(x509CertStoreSelector);
            x509CertPairStoreSelector2.setReverseSelector(x509CertStoreSelector);
            createCrossCertificatePairs.addAll(createCrossCertificatePairs(crossCertificatePairSubjectSearch(x509CertPairStoreSelector2, splitString, splitString2, splitString3), x509CertPairStoreSelector));
        }
        return createCrossCertificatePairs;
    }

    public Collection getDeltaCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getDeltaRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapDeltaRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getDeltaRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getUserCertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getUserCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapUserCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getUserCertificateSubjectAttributeName());
        Set createCerts = createCerts(certSubjectSerialSearch(x509CertStoreSelector, splitString, splitString2, splitString3), x509CertStoreSelector);
        if (createCerts.size() == 0) {
            createCerts.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), splitString, splitString2, splitString3), x509CertStoreSelector));
        }
        return createCerts;
    }
}
