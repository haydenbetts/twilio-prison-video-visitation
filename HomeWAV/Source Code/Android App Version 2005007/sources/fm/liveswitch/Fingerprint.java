package fm.liveswitch;

public abstract class Fingerprint {
    private String _algorithm;
    private String _value;

    public static String getMd2Algorithm() {
        return "md2";
    }

    public static String getMd5Algorithm() {
        return "md5";
    }

    public static String getSha1Algorithm() {
        return "sha-1";
    }

    public static String getSha224Algorithm() {
        return "sha-224";
    }

    public static String getSha256Algorithm() {
        return "sha-256";
    }

    public static String getSha384Algorithm() {
        return "sha-384";
    }

    public static String getSha512Algorithm() {
        return "sha-512";
    }

    public Fingerprint(String str, String str2) {
        setAlgorithm(str);
        setValue(str2);
    }

    public String getAlgorithm() {
        return this._algorithm;
    }

    public String getValue() {
        return this._value;
    }

    private void setAlgorithm(String str) {
        this._algorithm = str;
    }

    private void setValue(String str) {
        this._value = str;
    }
}
