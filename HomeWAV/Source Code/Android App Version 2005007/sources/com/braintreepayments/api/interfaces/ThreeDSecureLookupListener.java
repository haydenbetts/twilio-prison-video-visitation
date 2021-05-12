package com.braintreepayments.api.interfaces;

import com.braintreepayments.api.models.ThreeDSecureLookup;
import com.braintreepayments.api.models.ThreeDSecureRequest;

public interface ThreeDSecureLookupListener {
    void onLookupComplete(ThreeDSecureRequest threeDSecureRequest, ThreeDSecureLookup threeDSecureLookup);
}
