package fm.liveswitch;

class DtlsFactory {
    DtlsFactory() {
    }

    public static DtlsIClient client(String str, DtlsCertificate dtlsCertificate, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsFingerprint dtlsFingerprint, int[] iArr, IAction1<byte[]> iAction1, IAction1<DataBuffer> iAction12) {
        return new DtlsBouncyCastleClient(dtlsCertificate, dtlsCipherSuiteArr, dtlsProtocolVersion, dtlsFingerprint, iArr, iAction1, iAction12);
    }

    public static DtlsIServer server(String str, DtlsCertificate dtlsCertificate, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsProtocolVersion dtlsProtocolVersion2, DtlsFingerprint dtlsFingerprint, int[] iArr, IAction1<byte[]> iAction1, IAction1<DataBuffer> iAction12) {
        return new DtlsBouncyCastleServer(dtlsCertificate, dtlsCipherSuiteArr, dtlsProtocolVersion, dtlsProtocolVersion2, dtlsFingerprint, iArr, iAction1, iAction12);
    }
}
