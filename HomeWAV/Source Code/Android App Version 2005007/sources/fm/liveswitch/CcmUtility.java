package fm.liveswitch;

import androidx.core.view.InputDeviceCompat;

public class CcmUtility {
    public static int getSequenceNumberDelta(int i, int i2) {
        int i3 = i - i2;
        return i3 < -128 ? i3 + 256 : i3 >= 128 ? i3 + InputDeviceCompat.SOURCE_ANY : i3;
    }
}
