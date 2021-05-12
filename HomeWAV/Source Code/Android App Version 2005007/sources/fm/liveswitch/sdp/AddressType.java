package fm.liveswitch.sdp;

import fm.liveswitch.LocalNetwork;

public abstract class AddressType {
    public static String getIP4() {
        return "IP4";
    }

    public static String getIP6() {
        return "IP6";
    }

    public static String getAddressTypeForAddress(String str) {
        fm.liveswitch.AddressType addressType = LocalNetwork.getAddressType(str);
        if (addressType == fm.liveswitch.AddressType.IPv4) {
            return getIP4();
        }
        if (addressType == fm.liveswitch.AddressType.IPv6) {
            return getIP6();
        }
        throw new RuntimeException(new Exception("Only IPv4 and IPv6 addresses are supported by SDP."));
    }
}
