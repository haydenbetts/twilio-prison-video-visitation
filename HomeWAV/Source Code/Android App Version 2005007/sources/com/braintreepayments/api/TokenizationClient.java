package com.braintreepayments.api;

import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.internal.GraphQLConstants;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import org.json.JSONException;

class TokenizationClient {
    static final String PAYMENT_METHOD_ENDPOINT = "payment_methods";

    TokenizationClient() {
    }

    static void tokenize(final BraintreeFragment braintreeFragment, final PaymentMethodBuilder paymentMethodBuilder, final PaymentMethodNonceCallback paymentMethodNonceCallback) {
        paymentMethodBuilder.setSessionId(braintreeFragment.getSessionId());
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!(paymentMethodBuilder instanceof CardBuilder) || !configuration.getGraphQL().isFeatureEnabled(GraphQLConstants.Features.TOKENIZE_CREDIT_CARDS)) {
                    TokenizationClient.tokenizeRest(braintreeFragment, paymentMethodBuilder, paymentMethodNonceCallback);
                } else {
                    TokenizationClient.tokenizeGraphQL(braintreeFragment, (CardBuilder) paymentMethodBuilder, paymentMethodNonceCallback);
                }
            }
        });
    }

    static String versionedPath(String str) {
        return "/v1/" + str;
    }

    /* access modifiers changed from: private */
    public static void tokenizeGraphQL(final BraintreeFragment braintreeFragment, final CardBuilder cardBuilder, final PaymentMethodNonceCallback paymentMethodNonceCallback) {
        braintreeFragment.sendAnalyticsEvent("card.graphql.tokenization.started");
        try {
            braintreeFragment.getGraphQLHttpClient().post(cardBuilder.buildGraphQL(braintreeFragment.getApplicationContext(), braintreeFragment.getAuthorization()), new HttpResponseCallback() {
                public void success(String str) {
                    try {
                        paymentMethodNonceCallback.success(PaymentMethodNonce.parsePaymentMethodNonces(str, cardBuilder.getResponsePaymentMethodType()));
                        braintreeFragment.sendAnalyticsEvent("card.graphql.tokenization.success");
                    } catch (JSONException e) {
                        paymentMethodNonceCallback.failure(e);
                    }
                }

                public void failure(Exception exc) {
                    braintreeFragment.sendAnalyticsEvent("card.graphql.tokenization.failure");
                    paymentMethodNonceCallback.failure(exc);
                }
            });
        } catch (BraintreeException e) {
            paymentMethodNonceCallback.failure(e);
        }
    }

    /* access modifiers changed from: private */
    public static void tokenizeRest(BraintreeFragment braintreeFragment, final PaymentMethodBuilder paymentMethodBuilder, final PaymentMethodNonceCallback paymentMethodNonceCallback) {
        BraintreeHttpClient httpClient = braintreeFragment.getHttpClient();
        httpClient.post(versionedPath("payment_methods/" + paymentMethodBuilder.getApiPath()), paymentMethodBuilder.build(), new HttpResponseCallback() {
            public void success(String str) {
                try {
                    paymentMethodNonceCallback.success(PaymentMethodNonce.parsePaymentMethodNonces(str, paymentMethodBuilder.getResponsePaymentMethodType()));
                } catch (JSONException e) {
                    paymentMethodNonceCallback.failure(e);
                }
            }

            public void failure(Exception exc) {
                paymentMethodNonceCallback.failure(exc);
            }
        });
    }
}
