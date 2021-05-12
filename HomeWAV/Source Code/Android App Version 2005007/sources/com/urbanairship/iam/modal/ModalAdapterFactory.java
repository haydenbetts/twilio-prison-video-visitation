package com.urbanairship.iam.modal;

import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageAdapter;

public class ModalAdapterFactory implements InAppMessageAdapter.Factory {
    public InAppMessageAdapter createAdapter(InAppMessage inAppMessage) {
        return ModalAdapter.newAdapter(inAppMessage);
    }
}
