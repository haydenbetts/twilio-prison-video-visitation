package com.urbanairship.actions;

import android.os.Build;
import com.urbanairship.UAirship;

public class WalletAction extends OpenExternalUrlAction {
    public static final String DEFAULT_REGISTRY_NAME = "wallet_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^w";

    public boolean acceptsArguments(ActionArguments actionArguments) {
        if (UAirship.shared().getPlatformType() == 2 && Build.VERSION.SDK_INT >= 19) {
            return super.acceptsArguments(actionArguments);
        }
        return false;
    }
}
