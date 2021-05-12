package fm.liveswitch;

import com.google.android.exoplayer2.C;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

public class LocalNetwork {
    public static String[] getIPAddresses(AddressType[] addressTypeArr) {
        return getIPAddresses(addressTypeArr, true);
    }

    public static String[] getIPAddresses(AddressType[] addressTypeArr, boolean z) {
        try {
            LocalAddress[] localAddresses = getLocalAddresses(addressTypeArr);
            ArrayList arrayList = new ArrayList();
            for (LocalAddress iPAddress : localAddresses) {
                String iPAddress2 = iPAddress.getIPAddress();
                if (z || !TransportAddress.isPrivate(iPAddress2)) {
                    arrayList.add(iPAddress2);
                }
            }
            return (String[]) arrayList.toArray(new String[0]);
        } catch (Exception unused) {
            return new String[0];
        }
    }

    static LocalAddress[] getLocalAddresses(AddressType[] addressTypeArr) {
        try {
            boolean z = false;
            boolean z2 = false;
            for (AddressType addressType : addressTypeArr) {
                if (addressType == AddressType.IPv4) {
                    z = true;
                }
                if (addressType == AddressType.IPv6) {
                    z2 = true;
                }
            }
            ArrayList arrayList = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!nextElement.isLoopback()) {
                    if (nextElement.isUp()) {
                        for (InterfaceAddress next : nextElement.getInterfaceAddresses()) {
                            InetAddress address = next.getAddress();
                            if ((z2 && (address instanceof Inet6Address)) || (z && (address instanceof Inet4Address))) {
                                String sanitizeIPAddress = TransportAddress.sanitizeIPAddress(address.toString());
                                if (!TransportAddress.isLinkLocal(sanitizeIPAddress)) {
                                    arrayList.add(new LocalAddress(sanitizeIPAddress, TransportAddress.maskFromPrefixLength(next.getNetworkPrefixLength()), C.MICROS_PER_SECOND, NetworkType.Unknown));
                                }
                            }
                        }
                    }
                }
            }
            return (LocalAddress[]) arrayList.toArray(new LocalAddress[arrayList.size()]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static AddressType getAddressType(String str) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            if (byName instanceof Inet6Address) {
                return AddressType.IPv6;
            }
            if (byName instanceof Inet4Address) {
                return AddressType.IPv4;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getAddressBytes(String str) {
        try {
            return InetAddress.getByName(str).getAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAddress(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
