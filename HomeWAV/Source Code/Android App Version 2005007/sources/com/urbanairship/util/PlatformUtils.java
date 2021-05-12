package com.urbanairship.util;

import com.urbanairship.channel.ChannelRegistrationPayload;

public class PlatformUtils {
    public static String asString(int i) {
        return i != 1 ? i != 2 ? "unknown" : ChannelRegistrationPayload.ANDROID_DEVICE_TYPE : ChannelRegistrationPayload.AMAZON_DEVICE_TYPE;
    }

    public static boolean isPlatformValid(int i) {
        return i == 1 || i == 2;
    }

    public static int parsePlatform(int i) {
        return i != 1 ? 2 : 1;
    }
}
