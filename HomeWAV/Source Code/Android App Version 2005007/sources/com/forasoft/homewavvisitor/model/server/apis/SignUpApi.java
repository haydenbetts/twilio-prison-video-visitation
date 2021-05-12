package com.forasoft.homewavvisitor.model.server.apis;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.google.gson.JsonObject;
import com.urbanairship.channel.ChannelRegistrationPayload;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\bf\u0018\u00002\u00020\u0001J\u0001\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u0010\u0011\u001a\u00020\u0012H'J6\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00142\b\b\u0001\u0010\u0016\u001a\u00020\u00172\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0019H'J;\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00142\u0014\b\u0001\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u001d2\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u0012H'¢\u0006\u0002\u0010\u001fJ.\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010!0\u00040\u00142\b\b\u0001\u0010\"\u001a\u00020\u00072\b\b\u0001\u0010#\u001a\u00020\u0007H'J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00040\u0014H'J\u001a\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0!0\u00040\u0014H'J$\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050!0\u00040\u00142\b\b\u0001\u0010)\u001a\u00020\u0007H'JT\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00142\b\b\u0001\u0010+\u001a\u00020\u00072\b\b\u0001\u0010,\u001a\u00020\u00072\b\b\u0001\u0010-\u001a\u00020\u00072\n\b\u0003\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010/\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u00100\u001a\u00020\u0007H'J\u001e\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00142\b\b\u0001\u00102\u001a\u00020\u0007H'J6\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00142\b\b\u0001\u0010\u0016\u001a\u00020\u00172\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0019H'J8\u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010!0\u00040\u00142\b\b\u0001\u0010\"\u001a\u00020\u00072\b\b\u0001\u0010#\u001a\u00020\u00072\b\b\u0001\u00105\u001a\u00020\u0007H'¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "", "addInmate", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "prisonId", "", "identifier", "name", "middleName", "lastName", "dob", "relationship", "relationshipExplanation", "person", "photoId", "visitorAgreed", "", "editProfilePhotos", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "data", "Lcom/google/gson/JsonObject;", "userPhoto", "Lokhttp3/MultipartBody$Part;", "userIdPhoto", "editUser", "params", "", "isNotificationSubscriptionEnabled", "(Ljava/util/Map;Ljava/lang/Boolean;)Lio/reactivex/Single;", "getCode", "", "id", "channel", "getVisits", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "loadFacilities", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "loadInmates", "facilityId", "login", "username", "password", "version", "fcmToken", "voipToken", "deviceType", "resetPassword", "email", "signUp", "verify", "code", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUpApi.kt */
public interface SignUpApi {
    @FormUrlEncoded
    @POST("app/add-inmate")
    Observable<ApiResponse<Inmate>> addInmate(@Field("prison_id") String str, @Field("identifier") String str2, @Field("first_name") String str3, @Field("middle_name") String str4, @Field("last_name") String str5, @Field("dob") String str6, @Field("relationship") String str7, @Field("relationship_other") String str8, @Field("person_capture_pubid") String str9, @Field("photo_id_capture_pubid") String str10, @Field("visitor_agreed") boolean z);

    @POST("app/update-info")
    @Multipart
    Single<ApiResponse<User>> editProfilePhotos(@Part("visitor") JsonObject jsonObject, @Part MultipartBody.Part part, @Part MultipartBody.Part part2);

    @FormUrlEncoded
    @POST("app/update-info")
    Single<ApiResponse<User>> editUser(@FieldMap Map<String, String> map, @Field("notification_subscription_enabled") Boolean bool);

    @FormUrlEncoded
    @POST("app/visitor/verification_required")
    Single<ApiResponse<List<Object>>> getCode(@Field("id") String str, @Field("channel") String str2);

    @GET("app/schedules/scheduled")
    Single<ApiResponse<ScheduledResponse>> getVisits();

    @GET("app/prisons")
    Single<ApiResponse<List<Facility>>> loadFacilities();

    @GET("resident/list")
    Single<ApiResponse<List<Inmate>>> loadInmates(@Query("id") String str);

    @FormUrlEncoded
    @POST("app/login")
    Single<ApiResponse<User>> login(@Query("username") String str, @Field("password") String str2, @Field("version") String str3, @Field("fcm_token") String str4, @Field("voipToken") String str5, @Field("device_type") String str6);

    @FormUrlEncoded
    @POST("app/lost-password")
    Single<ApiResponse<Object>> resetPassword(@Field("email") String str);

    @POST("app/signup")
    @Multipart
    Single<ApiResponse<User>> signUp(@Part("visitor") JsonObject jsonObject, @Part MultipartBody.Part part, @Part MultipartBody.Part part2);

    @FormUrlEncoded
    @POST("app/visitor/enter-code")
    Single<ApiResponse<List<Object>>> verify(@Field("id") String str, @Field("channel") String str2, @Field("type_code") String str3);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SignUpApi.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Single login$default(SignUpApi signUpApi, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
            if (obj == null) {
                return signUpApi.login(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? ChannelRegistrationPayload.ANDROID_DEVICE_TYPE : str6);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: login");
        }

        public static /* synthetic */ Observable addInmate$default(SignUpApi signUpApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z, int i, Object obj) {
            if (obj == null) {
                return signUpApi.addInmate(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, (i & 1024) != 0 ? true : z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addInmate");
        }

        public static /* synthetic */ Single editUser$default(SignUpApi signUpApi, Map map, Boolean bool, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    bool = null;
                }
                return signUpApi.editUser(map, bool);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: editUser");
        }
    }
}
