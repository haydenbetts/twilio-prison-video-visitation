package fm.liveswitch;

class DtlsHandshakeType {
    public static int getCertificate() {
        return 11;
    }

    public static int getCertificateRequest() {
        return 13;
    }

    public static int getCertificateVerify() {
        return 15;
    }

    public static int getClientHello() {
        return 1;
    }

    public static int getClientKeyExchange() {
        return 16;
    }

    public static int getFinished() {
        return 20;
    }

    public static int getHelloRequest() {
        return 0;
    }

    public static int getHelloVerifyRequest() {
        return 3;
    }

    public static int getServerHello() {
        return 2;
    }

    public static int getServerHelloDone() {
        return 14;
    }

    public static int getServerKeyExchange() {
        return 12;
    }

    public static String getDisplayName(int i) {
        if (i == getHelloRequest()) {
            return "HelloRequest";
        }
        if (i == getClientHello()) {
            return "ClientHello";
        }
        if (i == getServerHello()) {
            return "ServerHello";
        }
        if (i == getHelloVerifyRequest()) {
            return "HelloVerifyRequest";
        }
        if (i == getCertificate()) {
            return "Certificate";
        }
        if (i == getServerKeyExchange()) {
            return "ServerKeyExchange";
        }
        if (i == getCertificateRequest()) {
            return "CertificateRequest";
        }
        if (i == getServerHelloDone()) {
            return "ServerHelloDone";
        }
        if (i == getCertificateVerify()) {
            return "CertificateVerify";
        }
        if (i == getClientKeyExchange()) {
            return "ClientKeyExchange";
        }
        return i == getFinished() ? "Finished" : "Unknown";
    }
}
