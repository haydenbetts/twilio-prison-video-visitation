package com.braintreepayments.api;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.Wallet;

public class GooglePaymentActivity extends AppCompatActivity {
    protected static final String EXTRA_ENVIRONMENT = "com.braintreepayments.api.EXTRA_ENVIRONMENT";
    protected static final String EXTRA_PAYMENT_DATA_REQUEST = "com.braintreepayments.api.EXTRA_PAYMENT_DATA_REQUEST";
    private static final String EXTRA_RECREATING = "com.braintreepayments.api.EXTRA_RECREATING";
    private static final int REQUEST_CODE = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null || !bundle.getBoolean(EXTRA_RECREATING)) {
            AutoResolveHelper.resolveTask(Wallet.getPaymentsClient(this, new Wallet.WalletOptions.Builder().setEnvironment(getIntent().getIntExtra(EXTRA_ENVIRONMENT, 3)).build()).loadPaymentData(getIntent().getParcelableExtra(EXTRA_PAYMENT_DATA_REQUEST)), this, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_RECREATING, true);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        setResult(i2, intent);
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(17432576, 17432577);
    }
}
