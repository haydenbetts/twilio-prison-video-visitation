package fm.liveswitch;

class X501AttributeType {
    public static boolean areEqual(long[] jArr, long[] jArr2) {
        return Asn1ObjectIdentifier.areEqual(jArr, jArr2);
    }

    public static long[] getAliasedObjectName() {
        return new long[]{2, 5, 4, 1};
    }

    public static long[] getCommonName() {
        return new long[]{2, 5, 4, 3};
    }

    public static long[] getCountryName() {
        return new long[]{2, 5, 4, 6};
    }

    public static long[] getDescription() {
        return new long[]{2, 5, 4, 13};
    }

    public static long[] getKnowledgeInformation() {
        return new long[]{2, 5, 4, 2};
    }

    public static long[] getLocalityName() {
        return new long[]{2, 5, 4, 7};
    }

    public static long[] getObjectClass() {
        return new long[]{2, 5, 4, 0};
    }

    public static long[] getOrganizationName() {
        return new long[]{2, 5, 4, 10};
    }

    public static long[] getOrganizationUnitName() {
        return new long[]{2, 5, 4, 11};
    }

    public static long[] getSerialNumber() {
        return new long[]{2, 5, 4, 5};
    }

    public static long[] getStateOrProvinceName() {
        return new long[]{2, 5, 4, 8};
    }

    public static long[] getStreetAddress() {
        return new long[]{2, 5, 4, 9};
    }

    public static long[] getSurname() {
        return new long[]{2, 5, 4, 4};
    }

    public static long[] getTitle() {
        return new long[]{2, 5, 4, 12};
    }
}
