package fm.liveswitch;

public class TransportAddress {
    private static String[] _masks = {"0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0", "255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0", "255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0", "255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252", "255.255.255.254", "255.255.255.255"};
    private String __ipAddress;
    private int _port;

    public static boolean checkMask(String str, String str2, String str3) {
        if (!(str == null || str2 == null || str3 == null)) {
            try {
                byte[] addressBytes = LocalNetwork.getAddressBytes(str);
                byte[] addressBytes2 = LocalNetwork.getAddressBytes(str2);
                byte[] addressBytes3 = LocalNetwork.getAddressBytes(str3);
                if (!(addressBytes == null || addressBytes2 == null || addressBytes3 == null || ArrayExtensions.getLength(addressBytes) != ArrayExtensions.getLength(addressBytes3))) {
                    if (ArrayExtensions.getLength(addressBytes2) == ArrayExtensions.getLength(addressBytes3)) {
                        for (int i = 0; i < ArrayExtensions.getLength(addressBytes3); i++) {
                            byte b = addressBytes3[i];
                            if ((addressBytes[i] & b) != (b & addressBytes2[i])) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        TransportAddress transportAddress = (TransportAddress) Global.tryCast(obj, TransportAddress.class);
        if (transportAddress == null) {
            return false;
        }
        if (getIPAddress() == null) {
            if (transportAddress.getIPAddress() == null && transportAddress.getPort() == getPort()) {
                return true;
            }
            return false;
        } else if (!Global.equals(transportAddress.getIPAddress(), getIPAddress()) || transportAddress.getPort() != getPort()) {
            return false;
        } else {
            return true;
        }
    }

    public AddressType getAddressType() {
        if (isIPv4(getIPAddress())) {
            return AddressType.IPv4;
        }
        if (isIPv6(getIPAddress())) {
            return AddressType.IPv6;
        }
        return AddressType.Unknown;
    }

    public String getIPAddress() {
        return this.__ipAddress;
    }

    public int getPort() {
        return this._port;
    }

    public int hashCode() {
        return ((getIPAddress() != null ? 391 + getIPAddress().hashCode() : 17) * 23) + new Integer(getPort()).hashCode();
    }

    public static boolean isAny(String str) {
        try {
            byte[] addressBytes = LocalNetwork.getAddressBytes(str);
            for (int i = 0; i < ArrayExtensions.getLength(addressBytes); i++) {
                if (BitAssistant.castInteger(addressBytes[i]) != 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isIPAddress(String str) {
        AddressType addressType = LocalNetwork.getAddressType(str);
        return Global.equals(addressType, AddressType.IPv4) || Global.equals(addressType, AddressType.IPv6);
    }

    public static boolean isIPv4(String str) {
        return ArrayExtensions.getLength(LocalNetwork.getAddressBytes(str)) == 4;
    }

    public static boolean isIPv6(String str) {
        return ArrayExtensions.getLength(LocalNetwork.getAddressBytes(str)) == 16;
    }

    public static boolean isLinkLocal(String str) {
        try {
            byte[] addressBytes = LocalNetwork.getAddressBytes(str);
            if (ArrayExtensions.getLength(addressBytes) == 16) {
                boolean z = BitAssistant.castInteger(addressBytes[0]) == 254 && BitAssistant.castInteger(addressBytes[1]) == 128;
                for (int i = 2; i < 8; i++) {
                    z = z && BitAssistant.castInteger(addressBytes[i]) == 0;
                }
                return z;
            } else if (BitAssistant.castInteger(addressBytes[0]) == 169 && BitAssistant.castInteger(addressBytes[1]) == 254) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.error(StringExtensions.format("Error parsing IP address '{0}'; could not determine if it is a link-local address.", (Object) str), e);
            return false;
        }
    }

    public static boolean isLoopback(String str) {
        try {
            byte[] addressBytes = LocalNetwork.getAddressBytes(str);
            if (ArrayExtensions.getLength(addressBytes) == 16) {
                boolean z = BitAssistant.castInteger(addressBytes[15]) == 1;
                for (int i = 0; i < 15; i++) {
                    z = z && BitAssistant.castInteger(addressBytes[i]) == 0;
                }
                return z;
            } else if (BitAssistant.castInteger(addressBytes[0]) == 127) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.error(StringExtensions.format("Error parsing IP address '{0}'; could not determine if it is a loopback address.", (Object) str), e);
            return false;
        }
    }

    public static boolean isPrivate(String str) {
        try {
            byte[] addressBytes = LocalNetwork.getAddressBytes(str);
            if (ArrayExtensions.getLength(addressBytes) == 16) {
                if ((BitAssistant.castInteger(addressBytes[0]) & 252) == 252 && (BitAssistant.castInteger(addressBytes[0]) & 253) == 253) {
                    return true;
                }
                return false;
            } else if (BitAssistant.castInteger(addressBytes[0]) == 10 || ((BitAssistant.castInteger(addressBytes[0]) == 172 && BitAssistant.castInteger(addressBytes[1]) >= 16 && BitAssistant.castInteger(addressBytes[1]) <= 31) || (BitAssistant.castInteger(addressBytes[0]) == 192 && BitAssistant.castInteger(addressBytes[1]) == 168))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.error(StringExtensions.format("Error parsing IP address '{0}'; could not determine if it is a private address.", (Object) str), e);
            return false;
        }
    }

    public static boolean isReserved(String str) {
        try {
            byte[] addressBytes = LocalNetwork.getAddressBytes(str);
            if (ArrayExtensions.getLength(addressBytes) != 16) {
                return false;
            }
            int castInteger = BitAssistant.castInteger(addressBytes[3]);
            if (BitAssistant.castInteger(addressBytes[0]) != 32 || BitAssistant.castInteger(addressBytes[1]) != 1) {
                return false;
            }
            if (BitAssistant.castInteger(addressBytes[2]) == 0 || (castInteger == 1 && castInteger <= 248)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.error(StringExtensions.format("Error parsing IP address '{0}'; could not determine if it is a reserved address.", (Object) str), e);
            return false;
        }
    }

    public static String maskFromPrefixLength(int i) {
        if (i >= ArrayExtensions.getLength((Object[]) _masks) || i < 0) {
            return null;
        }
        return _masks[i];
    }

    public static String sanitizeIPAddress(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return str;
        }
        while (str.startsWith("/")) {
            str = str.substring(1);
        }
        int indexOf = StringExtensions.indexOf(str, "/");
        if (indexOf >= 0) {
            str = StringExtensions.substring(str, 0, indexOf);
        }
        int indexOf2 = StringExtensions.indexOf(str, "%");
        return indexOf2 >= 0 ? StringExtensions.substring(str, 0, indexOf2) : str;
    }

    public void setIPAddress(String str) {
        this.__ipAddress = sanitizeIPAddress(str);
    }

    public void setPort(int i) {
        this._port = i;
    }

    public String toString() {
        if (Global.equals(LocalNetwork.getAddressType(getIPAddress()), AddressType.IPv4)) {
            return StringExtensions.format("{0}:{1}", getIPAddress(), IntegerExtensions.toString(Integer.valueOf(getPort())));
        }
        return StringExtensions.format("[{0}]:{1}", getIPAddress(), IntegerExtensions.toString(Integer.valueOf(getPort())));
    }

    public TransportAddress(String str, int i) {
        setIPAddress(str);
        setPort(i);
    }
}
