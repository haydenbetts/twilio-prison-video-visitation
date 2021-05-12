package com.braintreepayments.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.braintreepayments.api.models.ThreeDSecureLookup;
import com.cardinalcommerce.cardinalmobilesdk.Cardinal;
import com.cardinalcommerce.cardinalmobilesdk.models.ValidateResponse;
import com.cardinalcommerce.cardinalmobilesdk.services.CardinalValidateReceiver;

public class ThreeDSecureActivity extends AppCompatActivity implements CardinalValidateReceiver {
    static final String EXTRA_JWT = "com.braintreepayments.api.ThreeDSecureActivity.EXTRA_JWT";
    static final String EXTRA_THREE_D_SECURE_LOOKUP = "com.braintreepayments.api.ThreeDSecureActivity.EXTRA_THREE_D_SECURE_LOOKUP";
    static final String EXTRA_VALIDATION_RESPONSE = "com.braintreepayments.api.ThreeDSecureActivity.EXTRA_VALIDATION_RESPONSE";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        ThreeDSecureLookup threeDSecureLookup = (ThreeDSecureLookup) extras.getParcelable(EXTRA_THREE_D_SECURE_LOOKUP);
        Cardinal.getInstance().cca_continue(threeDSecureLookup.getTransactionId(), threeDSecureLookup.getPareq(), this, this);
    }

    public void onValidated(Context context, ValidateResponse validateResponse, String str) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_JWT, str);
        intent.putExtra(EXTRA_THREE_D_SECURE_LOOKUP, getIntent().getExtras().getParcelable(EXTRA_THREE_D_SECURE_LOOKUP));
        intent.putExtra(EXTRA_VALIDATION_RESPONSE, validateResponse);
        setResult(-1, intent);
        finish();
    }
}
