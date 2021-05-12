package com.forasoft.homewavvisitor.model.server.apis;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.data.payment.BraintreeToken;
import com.forasoft.homewavvisitor.model.data.payment.Handling;
import com.forasoft.homewavvisitor.model.data.payment.NonceResponse;
import com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse;
import com.forasoft.homewavvisitor.model.data.payment.PaynearmeResponse;
import com.forasoft.homewavvisitor.model.server.response.AddCardResponse;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.BraintreeResult;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u0006H'J*\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u0006H'J\u001a\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00040\u0003H'J\u001a\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00040\u0003H'J2\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u0006H'J\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u00062\b\b\u0001\u0010\u0016\u001a\u00020\u0017H'J\u001e\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u0017H'JP\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\b\b\u0001\u0010\u001e\u001a\u00020\u0006H'Jk\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010 \u001a\u00020\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\b\b\u0001\u0010\u001e\u001a\u00020\u00062\b\b\u0001\u0010!\u001a\u00020\u00062\n\b\u0001\u0010\"\u001a\u0004\u0018\u00010\u0007H'¢\u0006\u0002\u0010#JF\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010&\u001a\u00020\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u0006H'J(\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(0\u00032\b\b\u0001\u0010\u001d\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u0007H'J(\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070(0\u00032\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020,H'J4\u0010-\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u0007H'J4\u0010.\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u0007H'¨\u0006/"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/apis/PaymentApi;", "", "deleteCard", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "", "token", "deleteCardFromStripe", "getCards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "getCardsFromStripe", "getHandlings", "Lcom/forasoft/homewavvisitor/model/data/payment/Handling;", "scope", "inmateId", "occupantId", "getNonce", "Lcom/forasoft/homewavvisitor/model/data/payment/NonceResponse;", "brainTreeUrl", "jsonStringBody", "Lcom/google/gson/JsonObject;", "getToken", "Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "process", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "amount", "nonce", "gateway", "processByStripe", "stripeToken", "idempotencyKey", "useSavedCard", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "processWithPaynearme", "Lcom/forasoft/homewavvisitor/model/data/payment/PaynearmeResponse;", "email", "saveCard", "Lcom/forasoft/homewavvisitor/model/server/response/AddCardResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/BraintreeResult;", "default", "saveCardInStripe", "", "updateCardStatus", "updateCardStatusFromStripe", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentApi.kt */
public interface PaymentApi {
    @FormUrlEncoded
    @POST("app/cards/delete")
    Single<ApiResponse<Map<String, Boolean>>> deleteCard(@Field("card_token") String str);

    @FormUrlEncoded
    @POST("app/cards/stripe/delete")
    Single<ApiResponse<Map<String, Boolean>>> deleteCardFromStripe(@Field("card_token") String str);

    @POST("app/cards/list")
    Single<ApiResponse<List<Card>>> getCards();

    @POST("app/cards/stripe/list")
    Single<ApiResponse<List<Card>>> getCardsFromStripe();

    @FormUrlEncoded
    @POST("app/payment/handling")
    Single<ApiResponse<Handling>> getHandlings(@Field("scope") String str, @Field("inmate_id") String str2, @Field("occupant_id") String str3);

    @POST
    Single<NonceResponse> getNonce(@Url String str, @Body JsonObject jsonObject);

    @POST("app/payment/get-token")
    @Headers({"Content-Type: application/json"})
    Single<ApiResponse<BraintreeToken>> getToken(@Body JsonObject jsonObject);

    @FormUrlEncoded
    @POST("app/payment/process")
    Single<ApiResponse<PaymentProcessedResponse>> process(@Field("scope") String str, @Field("amount") String str2, @Field("payment_method_nonce") String str3, @Field("inmate_id") String str4, @Field("occupant_id") String str5, @Field("gateway") String str6);

    @FormUrlEncoded
    @POST("app/payment/process")
    Single<ApiResponse<PaymentProcessedResponse>> processByStripe(@Field("scope") String str, @Field("amount") String str2, @Field("stripe_param") String str3, @Field("inmate_id") String str4, @Field("occupant_id") String str5, @Field("gateway") String str6, @Field("idempotency") String str7, @Field("use_saved_card") Boolean bool);

    @FormUrlEncoded
    @POST("paynearme/create-order")
    Single<ApiResponse<PaynearmeResponse>> processWithPaynearme(@Field("scope") String str, @Field("amount") String str2, @Field("email") String str3, @Field("inmate_id") String str4, @Field("occupant_id") String str5);

    @FormUrlEncoded
    @POST("app/cards/add")
    Single<AddCardResponse<BraintreeResult>> saveCard(@Field("payment_method_nonce") String str, @Field("make_default") boolean z);

    @FormUrlEncoded
    @POST("app/cards/stripe/add")
    Single<AddCardResponse<Boolean>> saveCardInStripe(@Field("stripe_token") String str, @Field("make_default") int i);

    @FormUrlEncoded
    @POST("app/cards/makeDefault")
    Single<ApiResponse<Map<String, Boolean>>> updateCardStatus(@Field("card_token") String str, @Field("make_default") boolean z);

    @FormUrlEncoded
    @POST("app/cards/stripe/makeDefault")
    Single<ApiResponse<Map<String, Boolean>>> updateCardStatusFromStripe(@Field("card_token") String str, @Field("make_default") boolean z);
}
