package fm.liveswitch;

class DtlsContentType {
    public static int getAlert() {
        return 21;
    }

    public static int getApplicationData() {
        return 23;
    }

    public static int getChangeCipherSpec() {
        return 20;
    }

    public static int getHandshake() {
        return 22;
    }

    public static String getDisplayName(int i) {
        if (i == getChangeCipherSpec()) {
            return "ChangeCipherSpec";
        }
        if (i == getAlert()) {
            return "Alert";
        }
        if (i == getHandshake()) {
            return "Handshake";
        }
        return i == getApplicationData() ? "ApplicationData" : "Unknown";
    }
}
