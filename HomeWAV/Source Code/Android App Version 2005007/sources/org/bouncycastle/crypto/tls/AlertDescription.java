package org.bouncycastle.crypto.tls;

public class AlertDescription {
    public static final short access_denied = 49;
    public static final short bad_certificate = 42;
    public static final short bad_certificate_hash_value = 114;
    public static final short bad_certificate_status_response = 113;
    public static final short bad_record_mac = 20;
    public static final short certificate_expired = 45;
    public static final short certificate_revoked = 44;
    public static final short certificate_unknown = 46;
    public static final short certificate_unobtainable = 111;
    public static final short close_notify = 0;
    public static final short decode_error = 50;
    public static final short decompression_failure = 30;
    public static final short decrypt_error = 51;
    public static final short decryption_failed = 21;
    public static final short export_restriction = 60;
    public static final short handshake_failure = 40;
    public static final short illegal_parameter = 47;
    public static final short inappropriate_fallback = 86;
    public static final short insufficient_security = 71;
    public static final short internal_error = 80;
    public static final short no_certificate = 41;
    public static final short no_renegotiation = 100;
    public static final short protocol_version = 70;
    public static final short record_overflow = 22;
    public static final short unexpected_message = 10;
    public static final short unknown_ca = 48;
    public static final short unknown_psk_identity = 115;
    public static final short unrecognized_name = 112;
    public static final short unsupported_certificate = 43;
    public static final short unsupported_extension = 110;
    public static final short user_canceled = 90;

    public static String getName(short s) {
        if (s == 0) {
            return "close_notify";
        }
        if (s == 10) {
            return "unexpected_message";
        }
        if (s == 30) {
            return "decompression_failure";
        }
        if (s == 60) {
            return "export_restriction";
        }
        if (s == 80) {
            return "internal_error";
        }
        if (s == 86) {
            return "inappropriate_fallback";
        }
        if (s == 90) {
            return "user_canceled";
        }
        if (s == 100) {
            return "no_renegotiation";
        }
        if (s == 70) {
            return "protocol_version";
        }
        if (s == 71) {
            return "insufficient_security";
        }
        switch (s) {
            case 20:
                return "bad_record_mac";
            case 21:
                return "decryption_failed";
            case 22:
                return "record_overflow";
            default:
                switch (s) {
                    case 40:
                        return "handshake_failure";
                    case 41:
                        return "no_certificate";
                    case 42:
                        return "bad_certificate";
                    case 43:
                        return "unsupported_certificate";
                    case 44:
                        return "certificate_revoked";
                    case 45:
                        return "certificate_expired";
                    case 46:
                        return "certificate_unknown";
                    case 47:
                        return "illegal_parameter";
                    case 48:
                        return "unknown_ca";
                    case 49:
                        return "access_denied";
                    case 50:
                        return "decode_error";
                    case 51:
                        return "decrypt_error";
                    default:
                        switch (s) {
                            case 110:
                                return "unsupported_extension";
                            case 111:
                                return "certificate_unobtainable";
                            case 112:
                                return "unrecognized_name";
                            case 113:
                                return "bad_certificate_status_response";
                            case 114:
                                return "bad_certificate_hash_value";
                            case 115:
                                return "unknown_psk_identity";
                            default:
                                return "UNKNOWN";
                        }
                }
        }
    }

    public static String getText(short s) {
        return getName(s) + "(" + s + ")";
    }
}
