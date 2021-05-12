package com.braintreepayments.api.interfaces;

import com.braintreepayments.api.models.ThreeDSecureRequest;

public interface ThreeDSecurePrepareLookupListener {
    void onPrepareLookupComplete(ThreeDSecureRequest threeDSecureRequest, String str);
}
