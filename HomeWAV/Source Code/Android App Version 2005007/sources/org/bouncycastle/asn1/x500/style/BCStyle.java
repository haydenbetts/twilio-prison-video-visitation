package org.bouncycastle.asn1.x500.style;

import androidx.exifinterface.media.ExifInterface;
import com.urbanairship.util.Attributes;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;

public class BCStyle extends AbstractX500NameStyle {
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY;
    public static final ASN1ObjectIdentifier C;
    public static final ASN1ObjectIdentifier CN;
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP;
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE;
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH;
    public static final ASN1ObjectIdentifier DC;
    public static final ASN1ObjectIdentifier DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54").intern();
    public static final ASN1ObjectIdentifier DN_QUALIFIER;
    private static final Hashtable DefaultLookUp;
    private static final Hashtable DefaultSymbols;
    public static final ASN1ObjectIdentifier E;
    public static final ASN1ObjectIdentifier EmailAddress;
    public static final ASN1ObjectIdentifier GENDER;
    public static final ASN1ObjectIdentifier GENERATION;
    public static final ASN1ObjectIdentifier GIVENNAME;
    public static final ASN1ObjectIdentifier INITIALS;
    public static final X500NameStyle INSTANCE = new BCStyle();
    public static final ASN1ObjectIdentifier L;
    public static final ASN1ObjectIdentifier NAME;
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH;
    public static final ASN1ObjectIdentifier O;
    public static final ASN1ObjectIdentifier OU;
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH;
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS;
    public static final ASN1ObjectIdentifier POSTAL_CODE;
    public static final ASN1ObjectIdentifier PSEUDONYM;
    public static final ASN1ObjectIdentifier SERIALNUMBER;
    public static final ASN1ObjectIdentifier SN;
    public static final ASN1ObjectIdentifier ST;
    public static final ASN1ObjectIdentifier STREET;
    public static final ASN1ObjectIdentifier SURNAME;
    public static final ASN1ObjectIdentifier T;
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER;
    public static final ASN1ObjectIdentifier UID;
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER;
    public static final ASN1ObjectIdentifier UnstructuredAddress;
    public static final ASN1ObjectIdentifier UnstructuredName;
    protected final Hashtable defaultLookUp = copyHashTable(DefaultLookUp);
    protected final Hashtable defaultSymbols = copyHashTable(DefaultSymbols);

    static {
        ASN1ObjectIdentifier intern = new ASN1ObjectIdentifier("2.5.4.6").intern();
        C = intern;
        ASN1ObjectIdentifier intern2 = new ASN1ObjectIdentifier("2.5.4.10").intern();
        O = intern2;
        ASN1ObjectIdentifier intern3 = new ASN1ObjectIdentifier("2.5.4.11").intern();
        OU = intern3;
        ASN1ObjectIdentifier intern4 = new ASN1ObjectIdentifier("2.5.4.12").intern();
        T = intern4;
        ASN1ObjectIdentifier intern5 = new ASN1ObjectIdentifier("2.5.4.3").intern();
        CN = intern5;
        ASN1ObjectIdentifier intern6 = new ASN1ObjectIdentifier("2.5.4.5").intern();
        SN = intern6;
        ASN1ObjectIdentifier intern7 = new ASN1ObjectIdentifier("2.5.4.9").intern();
        STREET = intern7;
        SERIALNUMBER = intern6;
        ASN1ObjectIdentifier intern8 = new ASN1ObjectIdentifier("2.5.4.7").intern();
        L = intern8;
        ASN1ObjectIdentifier intern9 = new ASN1ObjectIdentifier("2.5.4.8").intern();
        ST = intern9;
        ASN1ObjectIdentifier intern10 = new ASN1ObjectIdentifier("2.5.4.4").intern();
        SURNAME = intern10;
        ASN1ObjectIdentifier intern11 = new ASN1ObjectIdentifier("2.5.4.42").intern();
        GIVENNAME = intern11;
        ASN1ObjectIdentifier intern12 = new ASN1ObjectIdentifier("2.5.4.43").intern();
        INITIALS = intern12;
        ASN1ObjectIdentifier intern13 = new ASN1ObjectIdentifier("2.5.4.44").intern();
        GENERATION = intern13;
        ASN1ObjectIdentifier intern14 = new ASN1ObjectIdentifier("2.5.4.45").intern();
        UNIQUE_IDENTIFIER = intern14;
        ASN1ObjectIdentifier intern15 = new ASN1ObjectIdentifier("2.5.4.15").intern();
        BUSINESS_CATEGORY = intern15;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = intern15;
        ASN1ObjectIdentifier intern16 = new ASN1ObjectIdentifier("2.5.4.17").intern();
        POSTAL_CODE = intern16;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = intern16;
        ASN1ObjectIdentifier intern17 = new ASN1ObjectIdentifier("2.5.4.46").intern();
        DN_QUALIFIER = intern17;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = intern17;
        ASN1ObjectIdentifier intern18 = new ASN1ObjectIdentifier("2.5.4.65").intern();
        PSEUDONYM = intern18;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = intern18;
        ASN1ObjectIdentifier intern19 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1").intern();
        DATE_OF_BIRTH = intern19;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = intern19;
        ASN1ObjectIdentifier intern20 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2").intern();
        PLACE_OF_BIRTH = intern20;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = intern20;
        ASN1ObjectIdentifier intern21 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3").intern();
        GENDER = intern21;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = intern21;
        ASN1ObjectIdentifier intern22 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4").intern();
        COUNTRY_OF_CITIZENSHIP = intern22;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = intern22;
        ASN1ObjectIdentifier intern23 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5").intern();
        COUNTRY_OF_RESIDENCE = intern23;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = intern23;
        ASN1ObjectIdentifier intern24 = new ASN1ObjectIdentifier("1.3.36.8.3.14").intern();
        NAME_AT_BIRTH = intern24;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = intern24;
        ASN1ObjectIdentifier intern25 = new ASN1ObjectIdentifier("2.5.4.16").intern();
        POSTAL_ADDRESS = intern25;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = intern25;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = X509ObjectIdentifiers.id_at_telephoneNumber;
        TELEPHONE_NUMBER = aSN1ObjectIdentifier12;
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = X509ObjectIdentifiers.id_at_name;
        NAME = aSN1ObjectIdentifier13;
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = aSN1ObjectIdentifier13;
        ASN1ObjectIdentifier aSN1ObjectIdentifier15 = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
        EmailAddress = aSN1ObjectIdentifier15;
        ASN1ObjectIdentifier aSN1ObjectIdentifier16 = aSN1ObjectIdentifier12;
        ASN1ObjectIdentifier aSN1ObjectIdentifier17 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
        UnstructuredName = aSN1ObjectIdentifier17;
        ASN1ObjectIdentifier aSN1ObjectIdentifier18 = intern14;
        ASN1ObjectIdentifier aSN1ObjectIdentifier19 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
        UnstructuredAddress = aSN1ObjectIdentifier19;
        E = aSN1ObjectIdentifier15;
        ASN1ObjectIdentifier aSN1ObjectIdentifier20 = aSN1ObjectIdentifier17;
        ASN1ObjectIdentifier aSN1ObjectIdentifier21 = aSN1ObjectIdentifier19;
        ASN1ObjectIdentifier aSN1ObjectIdentifier22 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
        DC = aSN1ObjectIdentifier22;
        ASN1ObjectIdentifier aSN1ObjectIdentifier23 = intern13;
        ASN1ObjectIdentifier aSN1ObjectIdentifier24 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
        UID = aSN1ObjectIdentifier24;
        Hashtable hashtable = new Hashtable();
        DefaultSymbols = hashtable;
        ASN1ObjectIdentifier aSN1ObjectIdentifier25 = intern12;
        Hashtable hashtable2 = new Hashtable();
        DefaultLookUp = hashtable2;
        hashtable.put(intern, "C");
        hashtable.put(intern2, "O");
        hashtable.put(intern4, ExifInterface.GPS_DIRECTION_TRUE);
        hashtable.put(intern3, "OU");
        hashtable.put(intern5, "CN");
        hashtable.put(intern8, "L");
        hashtable.put(intern9, "ST");
        hashtable.put(intern6, "SERIALNUMBER");
        hashtable.put(aSN1ObjectIdentifier15, ExifInterface.LONGITUDE_EAST);
        hashtable.put(aSN1ObjectIdentifier22, "DC");
        hashtable.put(aSN1ObjectIdentifier24, "UID");
        hashtable.put(intern7, "STREET");
        hashtable.put(intern10, "SURNAME");
        hashtable.put(intern11, "GIVENNAME");
        ASN1ObjectIdentifier aSN1ObjectIdentifier26 = intern11;
        hashtable.put(aSN1ObjectIdentifier25, "INITIALS");
        hashtable.put(aSN1ObjectIdentifier23, "GENERATION");
        hashtable.put(aSN1ObjectIdentifier21, "unstructuredAddress");
        hashtable.put(aSN1ObjectIdentifier20, "unstructuredName");
        hashtable.put(aSN1ObjectIdentifier18, "UniqueIdentifier");
        hashtable.put(aSN1ObjectIdentifier3, "DN");
        hashtable.put(aSN1ObjectIdentifier4, "Pseudonym");
        hashtable.put(aSN1ObjectIdentifier11, "PostalAddress");
        hashtable.put(aSN1ObjectIdentifier10, "NameAtBirth");
        hashtable.put(aSN1ObjectIdentifier8, "CountryOfCitizenship");
        hashtable.put(aSN1ObjectIdentifier9, "CountryOfResidence");
        hashtable.put(aSN1ObjectIdentifier7, "Gender");
        hashtable.put(aSN1ObjectIdentifier6, "PlaceOfBirth");
        hashtable.put(aSN1ObjectIdentifier5, "DateOfBirth");
        hashtable.put(aSN1ObjectIdentifier2, "PostalCode");
        hashtable.put(aSN1ObjectIdentifier, "BusinessCategory");
        hashtable.put(aSN1ObjectIdentifier16, "TelephoneNumber");
        ASN1ObjectIdentifier aSN1ObjectIdentifier27 = aSN1ObjectIdentifier14;
        hashtable.put(aSN1ObjectIdentifier27, "Name");
        Hashtable hashtable3 = hashtable2;
        hashtable3.put("c", intern);
        hashtable3.put("o", intern2);
        hashtable3.put("t", intern4);
        hashtable3.put("ou", intern3);
        hashtable3.put("cn", intern5);
        hashtable3.put("l", intern8);
        hashtable3.put("st", intern9);
        hashtable3.put("sn", intern6);
        hashtable3.put("serialnumber", intern6);
        hashtable3.put("street", intern7);
        hashtable3.put("emailaddress", aSN1ObjectIdentifier15);
        hashtable3.put("dc", aSN1ObjectIdentifier22);
        hashtable3.put("e", aSN1ObjectIdentifier15);
        hashtable3.put("uid", aSN1ObjectIdentifier24);
        hashtable3.put("surname", intern10);
        hashtable3.put("givenname", aSN1ObjectIdentifier26);
        hashtable3.put("initials", aSN1ObjectIdentifier25);
        hashtable3.put("generation", aSN1ObjectIdentifier23);
        hashtable3.put("unstructuredaddress", aSN1ObjectIdentifier21);
        hashtable3.put("unstructuredname", aSN1ObjectIdentifier20);
        hashtable3.put("uniqueidentifier", aSN1ObjectIdentifier18);
        hashtable3.put("dn", aSN1ObjectIdentifier3);
        hashtable3.put("pseudonym", aSN1ObjectIdentifier4);
        hashtable3.put("postaladdress", aSN1ObjectIdentifier11);
        hashtable3.put("nameofbirth", aSN1ObjectIdentifier10);
        hashtable3.put("countryofcitizenship", aSN1ObjectIdentifier8);
        hashtable3.put("countryofresidence", aSN1ObjectIdentifier9);
        hashtable3.put(Attributes.GENDER, aSN1ObjectIdentifier7);
        hashtable3.put("placeofbirth", aSN1ObjectIdentifier6);
        hashtable3.put("dateofbirth", aSN1ObjectIdentifier5);
        hashtable3.put("postalcode", aSN1ObjectIdentifier2);
        hashtable3.put("businesscategory", aSN1ObjectIdentifier);
        hashtable3.put("telephonenumber", aSN1ObjectIdentifier16);
        hashtable3.put("name", aSN1ObjectIdentifier27);
    }

    protected BCStyle() {
    }

    public ASN1ObjectIdentifier attrNameToOID(String str) {
        return IETFUtils.decodeAttrName(str, this.defaultLookUp);
    }

    /* access modifiers changed from: protected */
    public ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        return (aSN1ObjectIdentifier.equals(EmailAddress) || aSN1ObjectIdentifier.equals(DC)) ? new DERIA5String(str) : aSN1ObjectIdentifier.equals(DATE_OF_BIRTH) ? new ASN1GeneralizedTime(str) : (aSN1ObjectIdentifier.equals(C) || aSN1ObjectIdentifier.equals(SN) || aSN1ObjectIdentifier.equals(DN_QUALIFIER) || aSN1ObjectIdentifier.equals(TELEPHONE_NUMBER)) ? new DERPrintableString(str) : super.encodeStringValue(aSN1ObjectIdentifier, str);
    }

    public RDN[] fromString(String str) {
        return IETFUtils.rDNsFromString(str, this);
    }

    public String[] oidToAttrNames(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return IETFUtils.findAttrNamesForOID(aSN1ObjectIdentifier, this.defaultLookUp);
    }

    public String oidToDisplayName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) DefaultSymbols.get(aSN1ObjectIdentifier);
    }

    public String toString(X500Name x500Name) {
        StringBuffer stringBuffer = new StringBuffer();
        RDN[] rDNs = x500Name.getRDNs();
        boolean z = true;
        for (RDN appendRDN : rDNs) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(',');
            }
            IETFUtils.appendRDN(stringBuffer, appendRDN, this.defaultSymbols);
        }
        return stringBuffer.toString();
    }
}
