package com.stripe.android;

import android.content.Context;
import android.os.AsyncTask;
import com.stripe.android.RequestOptions;
import com.stripe.android.StripeApiHandler;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.exception.APIConnectionException;
import com.stripe.android.exception.APIException;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.exception.CardException;
import com.stripe.android.exception.InvalidRequestException;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.AccountParams;
import com.stripe.android.model.BankAccount;
import com.stripe.android.model.Card;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.StripePaymentSource;
import com.stripe.android.model.Token;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class Stripe {
    /* access modifiers changed from: private */
    public final StripeApiHandler mApiHandler;
    /* access modifiers changed from: private */
    public final Context mContext;
    private String mDefaultPublishableKey;
    /* access modifiers changed from: private */
    public StripeApiHandler.LoggingResponseListener mLoggingResponseListener;
    private final SourceCreator mSourceCreator = new SourceCreator() {
        public void create(SourceParams sourceParams, String str, String str2, Executor executor, SourceCallback sourceCallback) {
            Stripe.this.executeTask(executor, new CreateSourceTask(Stripe.this.mContext, Stripe.this.mApiHandler, sourceParams, str, str2, sourceCallback));
        }
    };
    private String mStripeAccount;
    TokenCreator mTokenCreator = new TokenCreator() {
        public void create(Map<String, Object> map, String str, String str2, String str3, Executor executor, TokenCallback tokenCallback) {
            Executor executor2 = executor;
            Stripe.this.executeTask(executor2, new CreateTokenTask(Stripe.this.mContext, Stripe.this.mApiHandler, map, str, str2, str3, tokenCallback, Stripe.this.mLoggingResponseListener));
        }
    };

    interface SourceCreator {
        void create(SourceParams sourceParams, String str, String str2, Executor executor, SourceCallback sourceCallback);
    }

    interface TokenCreator {
        void create(Map<String, Object> map, String str, String str2, String str3, Executor executor, TokenCallback tokenCallback);
    }

    public Stripe(Context context) {
        this.mContext = context.getApplicationContext();
        this.mApiHandler = new StripeApiHandler();
    }

    public Stripe(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mApiHandler = new StripeApiHandler();
        setDefaultPublishableKey(str);
    }

    public void createBankAccountToken(BankAccount bankAccount, TokenCallback tokenCallback) {
        createBankAccountToken(bankAccount, this.mDefaultPublishableKey, (Executor) null, tokenCallback);
    }

    public void createBankAccountToken(BankAccount bankAccount, String str, Executor executor, TokenCallback tokenCallback) {
        if (bankAccount != null) {
            createTokenFromParams(StripeNetworkUtils.hashMapFromBankAccount(this.mContext, bankAccount), str, Token.TYPE_BANK_ACCOUNT, executor, tokenCallback);
            return;
        }
        throw new RuntimeException("Required parameter: 'bankAccount' is requred to create a token");
    }

    public void createPiiToken(String str, TokenCallback tokenCallback) {
        createPiiToken(str, this.mDefaultPublishableKey, (Executor) null, tokenCallback);
    }

    public void createPiiToken(String str, String str2, Executor executor, TokenCallback tokenCallback) {
        createTokenFromParams(StripeNetworkUtils.hashMapFromPersonalId(str), str2, Token.TYPE_PII, executor, tokenCallback);
    }

    public Token createBankAccountTokenSynchronous(BankAccount bankAccount) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return createBankAccountTokenSynchronous(bankAccount, this.mDefaultPublishableKey);
    }

    public Token createBankAccountTokenSynchronous(BankAccount bankAccount, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        validateKey(str);
        RequestOptions build = RequestOptions.builder(str, this.mStripeAccount, "source").build();
        StripeApiHandler stripeApiHandler = this.mApiHandler;
        Context context = this.mContext;
        return stripeApiHandler.createToken(context, StripeNetworkUtils.hashMapFromBankAccount(context, bankAccount), build, Token.TYPE_BANK_ACCOUNT, this.mLoggingResponseListener);
    }

    public void createCvcUpdateToken(String str, TokenCallback tokenCallback) {
        createCvcUpdateToken(str, this.mDefaultPublishableKey, (Executor) null, tokenCallback);
    }

    public void createCvcUpdateToken(String str, String str2, Executor executor, TokenCallback tokenCallback) {
        createTokenFromParams(StripeNetworkUtils.mapFromCvc(str), str2, Token.TYPE_CVC_UPDATE, executor, tokenCallback);
    }

    public void createSource(SourceParams sourceParams, SourceCallback sourceCallback) {
        createSource(sourceParams, sourceCallback, (String) null, (Executor) null);
    }

    public void createSource(SourceParams sourceParams, SourceCallback sourceCallback, String str, Executor executor) {
        if (str == null) {
            str = this.mDefaultPublishableKey;
        }
        String str2 = str;
        if (str2 != null) {
            this.mSourceCreator.create(sourceParams, str2, this.mStripeAccount, executor, sourceCallback);
        }
    }

    public void createToken(Card card, TokenCallback tokenCallback) {
        createToken(card, this.mDefaultPublishableKey, tokenCallback);
    }

    public void createToken(Card card, String str, TokenCallback tokenCallback) {
        createToken(card, str, (Executor) null, tokenCallback);
    }

    public void createToken(Card card, Executor executor, TokenCallback tokenCallback) {
        createToken(card, this.mDefaultPublishableKey, executor, tokenCallback);
    }

    public void createToken(Card card, String str, Executor executor, TokenCallback tokenCallback) {
        if (card != null) {
            createTokenFromParams(StripeNetworkUtils.hashMapFromCard(this.mContext, card), str, "card", executor, tokenCallback);
            return;
        }
        throw new RuntimeException("Required Parameter: 'card' is required to create a token");
    }

    public Source createSourceSynchronous(SourceParams sourceParams) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return createSourceSynchronous(sourceParams, (String) null);
    }

    public Source createSourceSynchronous(SourceParams sourceParams, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        if (str == null) {
            str = this.mDefaultPublishableKey;
        }
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        return this.mApiHandler.createSource((StripeNetworkUtils.UidProvider) null, this.mContext, sourceParams, str2, this.mStripeAccount, this.mLoggingResponseListener);
    }

    public PaymentIntent retrievePaymentIntentSynchronous(PaymentIntentParams paymentIntentParams, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return this.mApiHandler.retrievePaymentIntent(this.mContext, paymentIntentParams, str, this.mStripeAccount, this.mLoggingResponseListener);
    }

    public PaymentIntent confirmPaymentIntentSynchronous(PaymentIntentParams paymentIntentParams, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return this.mApiHandler.confirmPaymentIntent((StripeNetworkUtils.UidProvider) null, this.mContext, paymentIntentParams, str, this.mStripeAccount, this.mLoggingResponseListener);
    }

    public PaymentMethod createPaymentMethodSynchronous(PaymentMethodCreateParams paymentMethodCreateParams, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return this.mApiHandler.createPaymentMethod(paymentMethodCreateParams, this.mContext, str, this.mStripeAccount, this.mLoggingResponseListener);
    }

    public Token createTokenSynchronous(Card card) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return createTokenSynchronous(card, this.mDefaultPublishableKey);
    }

    public Token createTokenSynchronous(Card card, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        validateKey(str);
        RequestOptions build = RequestOptions.builder(str, this.mStripeAccount, "source").build();
        StripeApiHandler stripeApiHandler = this.mApiHandler;
        Context context = this.mContext;
        return stripeApiHandler.createToken(context, StripeNetworkUtils.hashMapFromCard(context, card), build, "card", this.mLoggingResponseListener);
    }

    public Token createPiiTokenSynchronous(String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return createPiiTokenSynchronous(str, this.mDefaultPublishableKey);
    }

    public Token createPiiTokenSynchronous(String str, String str2) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        validateKey(str2);
        return this.mApiHandler.createToken(this.mContext, StripeNetworkUtils.hashMapFromPersonalId(str), RequestOptions.builder(str2, this.mStripeAccount, "source").build(), Token.TYPE_PII, this.mLoggingResponseListener);
    }

    public Token createCvcUpdateTokenSynchronous(String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return createCvcUpdateTokenSynchronous(str, this.mDefaultPublishableKey);
    }

    public Token createCvcUpdateTokenSynchronous(String str, String str2) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        validateKey(str2);
        return this.mApiHandler.createToken(this.mContext, StripeNetworkUtils.mapFromCvc(str), RequestOptions.builder(str2, this.mStripeAccount, "source").build(), Token.TYPE_CVC_UPDATE, this.mLoggingResponseListener);
    }

    public Token createAccountTokenSynchronous(AccountParams accountParams) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return createAccountTokenSynchronous(accountParams, this.mDefaultPublishableKey);
    }

    public Token createAccountTokenSynchronous(AccountParams accountParams, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        if ((str == null ? this.mDefaultPublishableKey : str) == null) {
            return null;
        }
        validateKey(str);
        try {
            return this.mApiHandler.createToken(this.mContext, accountParams.toParamMap(), RequestOptions.builder(str, this.mStripeAccount, "source").build(), "account", this.mLoggingResponseListener);
        } catch (CardException unused) {
            return null;
        }
    }

    public void logEventSynchronous(List<String> list, StripePaymentSource stripePaymentSource) {
        Map<String, Object> map;
        RequestOptions.RequestOptionsBuilder builder = RequestOptions.builder(this.mDefaultPublishableKey);
        String str = this.mStripeAccount;
        if (str != null) {
            builder.setStripeAccount(str);
        }
        RequestOptions build = builder.build();
        if (stripePaymentSource instanceof Token) {
            map = LoggingUtils.getTokenCreationParams(this.mContext, list, this.mDefaultPublishableKey, ((Token) stripePaymentSource).getType());
        } else {
            map = LoggingUtils.getSourceCreationParams(this.mContext, list, this.mDefaultPublishableKey, ((Source) stripePaymentSource).getType());
        }
        this.mApiHandler.logApiCall(map, build, this.mLoggingResponseListener);
    }

    public Source retrieveSourceSynchronous(String str, String str2) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return retrieveSourceSynchronous(str, str2, (String) null);
    }

    public Source retrieveSourceSynchronous(String str, String str2, String str3) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        if (str3 == null) {
            str3 = this.mDefaultPublishableKey;
        }
        if (str3 == null) {
            return null;
        }
        return this.mApiHandler.retrieveSource(str, str2, str3, this.mStripeAccount);
    }

    public void setDefaultPublishableKey(String str) {
        validateKey(str);
        this.mDefaultPublishableKey = str;
    }

    public void setStripeAccount(String str) {
        this.mStripeAccount = str;
    }

    /* access modifiers changed from: package-private */
    public void setLoggingResponseListener(StripeApiHandler.LoggingResponseListener loggingResponseListener) {
        this.mLoggingResponseListener = loggingResponseListener;
    }

    private void createTokenFromParams(Map<String, Object> map, String str, String str2, Executor executor, TokenCallback tokenCallback) {
        if (tokenCallback != null) {
            validateKey(str);
            this.mTokenCreator.create(map, str, this.mStripeAccount, str2, executor, tokenCallback);
            return;
        }
        throw new RuntimeException("Required Parameter: 'callback' is required to use the created token and handle errors");
    }

    private void validateKey(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid Publishable Key: You must use a valid publishable key to create a token.  For more info, see https://stripe.com/docs/stripe.js.");
        } else if (str.startsWith("sk_")) {
            throw new IllegalArgumentException("Invalid Publishable Key: You are using a secret key to create a token, instead of the publishable one. For more info, see https://stripe.com/docs/stripe.js");
        }
    }

    /* access modifiers changed from: private */
    public void executeTask(Executor executor, AsyncTask<Void, Void, ResponseWrapper> asyncTask) {
        if (executor != null) {
            asyncTask.executeOnExecutor(executor, new Void[0]);
        } else {
            asyncTask.execute(new Void[0]);
        }
    }

    private static class ResponseWrapper {
        final Exception error;
        final Source source;
        final Token token;

        private ResponseWrapper(Token token2) {
            this.token = token2;
            this.source = null;
            this.error = null;
        }

        private ResponseWrapper(Source source2) {
            this.source = source2;
            this.error = null;
            this.token = null;
        }

        private ResponseWrapper(Exception exc) {
            this.error = exc;
            this.source = null;
            this.token = null;
        }
    }

    private static class CreateSourceTask extends AsyncTask<Void, Void, ResponseWrapper> {
        private final StripeApiHandler mApiHandler;
        private final WeakReference<Context> mContextRef;
        private final String mPublishableKey;
        private final SourceCallback mSourceCallback;
        private final SourceParams mSourceParams;
        private final String mStripeAccount;

        CreateSourceTask(Context context, StripeApiHandler stripeApiHandler, SourceParams sourceParams, String str, String str2, SourceCallback sourceCallback) {
            this.mContextRef = new WeakReference<>(context);
            this.mApiHandler = stripeApiHandler;
            this.mSourceParams = sourceParams;
            this.mPublishableKey = str;
            this.mStripeAccount = str2;
            this.mSourceCallback = sourceCallback;
        }

        /* access modifiers changed from: protected */
        public ResponseWrapper doInBackground(Void... voidArr) {
            try {
                return new ResponseWrapper(this.mApiHandler.createSource((StripeNetworkUtils.UidProvider) null, (Context) this.mContextRef.get(), this.mSourceParams, this.mPublishableKey, this.mStripeAccount, (StripeApiHandler.LoggingResponseListener) null));
            } catch (StripeException e) {
                return new ResponseWrapper((Exception) e);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ResponseWrapper responseWrapper) {
            if (responseWrapper.source != null) {
                this.mSourceCallback.onSuccess(responseWrapper.source);
            } else if (responseWrapper.error != null) {
                this.mSourceCallback.onError(responseWrapper.error);
            }
        }
    }

    private static class CreateTokenTask extends AsyncTask<Void, Void, ResponseWrapper> {
        private final StripeApiHandler mApiHandler;
        private final TokenCallback mCallback;
        private final WeakReference<Context> mContextRef;
        private final StripeApiHandler.LoggingResponseListener mLoggingResponseListener;
        private final String mPublishableKey;
        private final String mStripeAccount;
        private final Map<String, Object> mTokenParams;
        private final String mTokenType;

        CreateTokenTask(Context context, StripeApiHandler stripeApiHandler, Map<String, Object> map, String str, String str2, String str3, TokenCallback tokenCallback, StripeApiHandler.LoggingResponseListener loggingResponseListener) {
            this.mContextRef = new WeakReference<>(context);
            this.mApiHandler = stripeApiHandler;
            this.mTokenParams = map;
            this.mPublishableKey = str;
            this.mStripeAccount = str2;
            this.mTokenType = str3;
            this.mLoggingResponseListener = loggingResponseListener;
            this.mCallback = tokenCallback;
        }

        /* access modifiers changed from: protected */
        public ResponseWrapper doInBackground(Void... voidArr) {
            try {
                return new ResponseWrapper(this.mApiHandler.createToken((Context) this.mContextRef.get(), this.mTokenParams, RequestOptions.builder(this.mPublishableKey, this.mStripeAccount, "source").build(), this.mTokenType, this.mLoggingResponseListener));
            } catch (StripeException e) {
                return new ResponseWrapper((Exception) e);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ResponseWrapper responseWrapper) {
            tokenTaskPostExecution(responseWrapper);
        }

        private void tokenTaskPostExecution(ResponseWrapper responseWrapper) {
            if (responseWrapper.token != null) {
                this.mCallback.onSuccess(responseWrapper.token);
            } else if (responseWrapper.error != null) {
                this.mCallback.onError(responseWrapper.error);
            } else {
                this.mCallback.onError(new RuntimeException("Somehow got neither a token response or an error response"));
            }
        }
    }
}
