package com.forasoft.homewavvisitor.model.server.apis;

import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.MediaWithCategory;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageInfo;
import com.forasoft.homewavvisitor.model.data.ScheduleResponse;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.data.SupportInfo;
import com.forasoft.homewavvisitor.model.data.TwilioToken;
import com.forasoft.homewavvisitor.model.data.UserInfo;
import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.server.response.AirshipTags;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.model.server.response.RefundResponse;
import com.forasoft.homewavvisitor.model.server.response.ReportResponse;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.model.server.response.Terms;
import com.urbanairship.channel.ChannelRegistrationPayload;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007H'J\u001e\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\n2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\u001e\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u0007H'J.\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00110\u00040\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u0007H'J(\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0003\u0010\u0017\u001a\u00020\u0007H'J2\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\n2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\u0019\u001a\u00020\u00072\b\b\u0003\u0010\u001a\u001a\u00020\u0007H'J$\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00040\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u0007H'J*\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00040\u00032\u000e\b\u0001\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H'J&\u0010\u001f\u001a\u00020 2\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0003\u0010\u0019\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u0007H'J(\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00040\u00032\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010%\u001a\u00020\u0007H'J2\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\b\b\u0001\u0010'\u001a\u00020\u00072\b\b\u0003\u0010(\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007H'J\u001e\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\n2\b\b\u0001\u0010'\u001a\u00020\u0007H'J\u001e\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\u001a\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00110\u00040\u0003H'J$\u0010.\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u00110\r0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J?\u00100\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\u00110\r0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u00072\b\b\u0003\u00102\u001a\u0002032\n\b\u0003\u00104\u001a\u0004\u0018\u000103H'¢\u0006\u0002\u00105J(\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u0007H'J\u001a\u00108\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\u00110\r0\u0003H'J2\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0\r0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u00072\b\b\u0001\u0010;\u001a\u00020<2\b\b\u0001\u0010=\u001a\u00020\u0007H'J\u0014\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u0003H'J\u0014\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00040\u0003H'J\u001a\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00040\u0003H'J(\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u0007H'J\u001a\u0010E\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00110\u00040\u0003H'J*\u0010F\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070G0\u00040\u00032\b\b\u0001\u0010H\u001a\u00020\u0007H'J\u0014\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\r0\u0003H'J\u001e\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0\r0\u00032\b\b\u0003\u0010M\u001a\u00020\u0007H'J(\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0\u00040\u00032\b\b\u0001\u0010P\u001a\u00020\u00072\b\b\u0001\u0010\u001c\u001a\u00020\u0007H'J$\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\u000e\b\u0001\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H'J(\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0\u00040\u00032\b\b\u0001\u0010U\u001a\u00020\u00072\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J8\u0010V\u001a\b\u0012\u0004\u0012\u00020W0\u00032\b\b\u0001\u0010P\u001a\u00020\u00072\b\b\u0001\u0010\u001c\u001a\u00020\u00072\b\b\u0001\u0010X\u001a\u00020\u00072\n\b\u0001\u0010Y\u001a\u0004\u0018\u00010\u0007H'J2\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020[0\r0\u00032\b\b\u0001\u0010\\\u001a\u00020\u00072\b\b\u0001\u0010]\u001a\u00020\u00072\b\b\u0003\u0010^\u001a\u00020\u0007H'JG\u0010_\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\r0\u00032\u0019\b\u0001\u0010`\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\t\u0012\u00070a¢\u0006\u0002\bb0G2\n\b\u0003\u0010c\u001a\u0004\u0018\u00010d2\n\b\u0003\u0010e\u001a\u0004\u0018\u00010dH'J(\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0001\u0010;\u001a\u00020<2\b\b\u0001\u0010\u001c\u001a\u00020\u0007H'J\u0014\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u0003H'J<\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0001\u0010%\u001a\u00020\u00072\b\b\u0001\u0010i\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u0007H'J(\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u0007H'J,\u0010k\u001a\b\u0012\u0004\u0012\u00020l0\u00032\b\b\u0001\u0010\u001c\u001a\u00020d2\b\b\u0001\u0010m\u001a\u00020d2\b\b\u0001\u0010X\u001a\u00020dH'J$\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\u000e\b\u0001\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H'¨\u0006o"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "", "acceptCall", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "id", "", "ui", "cancelCall", "Lio/reactivex/Observable;", "", "cancelVisit", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "slotId", "checkVersion", "", "version", "deviceType", "checkinWebrtcCall", "Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "pubId", "role", "declineCall", "cause", "detail", "deleteChat", "inmateId", "deleteMessages", "ids", "endWebrtcCall", "Lio/reactivex/Completable;", "src", "getAirshipChannelTags", "Lcom/forasoft/homewavvisitor/model/server/response/AirshipTags;", "scheme", "channelId", "getCallMessage", "callId", "status", "getCallStatus", "getFacility", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "getImages", "Lcom/forasoft/homewavvisitor/model/data/MediaWithCategory;", "getInmates", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getLastMessages", "Lcom/forasoft/homewavvisitor/model/data/Message;", "limit", "", "page", "(Ljava/lang/String;ILjava/lang/Integer;)Lio/reactivex/Single;", "getLiveswitchCallData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "getMessages", "getSchedule", "Lcom/forasoft/homewavvisitor/model/data/ScheduleResponse;", "start", "", "period", "getSupportInfo", "Lcom/forasoft/homewavvisitor/model/data/SupportInfo;", "getTerms", "Lcom/forasoft/homewavvisitor/model/server/response/Terms;", "getTutorials", "getTwilioToken", "Lcom/forasoft/homewavvisitor/model/data/TwilioToken;", "getVideos", "getVisitorAgreementUrl", "", "prisonId", "getVisits", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "heartbeat", "Lcom/forasoft/homewavvisitor/model/data/State;", "ctx", "initMessage", "Lcom/forasoft/homewavvisitor/model/data/MessageInfo;", "visitorId", "readMessages", "messages", "reportError", "Lcom/forasoft/homewavvisitor/model/server/response/ReportResponse;", "problems", "requestRefund", "Lcom/forasoft/homewavvisitor/model/server/response/RefundResponse;", "amount", "notes", "saveToken", "Lcom/forasoft/homewavvisitor/model/data/UserInfo;", "userId", "token", "os", "sendMessage", "data", "Lokhttp3/RequestBody;", "Lkotlin/jvm/JvmSuppressWildcards;", "video", "Lokhttp3/MultipartBody$Part;", "image", "sendVisitRequest", "setTermsAgreed", "startLiveswitchCall", "applicationId", "startWebrtcCall", "transferFunds", "Lokhttp3/ResponseBody;", "targetInmateId", "undoChatDeletion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomewavApi.kt */
public interface HomewavApi {
    @GET("visitor/call/connect")
    Single<ApiResponse<CallWrapper>> acceptCall(@Query("id") String str, @Query("ui") String str2);

    @GET("visitor/call/cancel")
    Observable<ApiResponse> cancelCall(@Query("id") String str);

    @GET("app/schedules/cancel")
    Single<Response<Empty>> cancelVisit(@Query("time_slot_id") String str);

    @GET("app/check-version")
    Single<ApiResponse<List<Empty>>> checkVersion(@Query("version") String str, @Query("device_type") String str2);

    @GET("calls/webrtc/checkin")
    Single<ApiResponse<CallStatusResponse>> checkinWebrtcCall(@Query("call_pubid") String str, @Query("role") String str2);

    @GET("visitor/call/disconnect")
    Observable<ApiResponse<Empty>> declineCall(@Query("id") String str, @Query("cause") String str2, @Query("detail") String str3);

    @FormUrlEncoded
    @POST("app/message/deleteThread")
    Single<ApiResponse<List<String>>> deleteChat(@Field("inmate_id") String str);

    @FormUrlEncoded
    @POST("app/message/delete/")
    Single<ApiResponse<List<String>>> deleteMessages(@Field("messages[]") List<String> list);

    @GET("calls/webrtc/end")
    Completable endWebrtcCall(@Query("call_pubid") String str, @Query("cause") String str2, @Query("src") String str3);

    @GET("app/visitor/airship_get_channel_tags")
    Single<ApiResponse<AirshipTags>> getAirshipChannelTags(@Query("scheme") String str, @Query("channelId") String str2);

    @GET("calls/get_message")
    Single<ApiResponse<String>> getCallMessage(@Query("id") String str, @Query("status") String str2, @Query("ui") String str3);

    @GET("app/visitor/call/status")
    Observable<ApiResponse<CallStatusResponse>> getCallStatus(@Query("id") String str);

    @GET("app/prisons/{id}")
    Single<ApiResponse<Facility>> getFacility(@Path("id") String str);

    @GET("app/gallery/list?type=images")
    Single<ApiResponse<List<MediaWithCategory>>> getImages();

    @FormUrlEncoded
    @POST("app/inmates-by-visitor")
    Single<Response<List<Inmate>>> getInmates(@Field("id") String str);

    @GET("app/visitor/chat")
    Single<Response<List<Message>>> getLastMessages(@Query("inmate_id") String str, @Query("limit") int i, @Query("page") Integer num);

    @GET("calls/webrtc/grant-liveswitch-access")
    Single<ApiResponse<LiveswitchCallDataResponse>> getLiveswitchCallData(@Query("call_pubid") String str, @Query("src") String str2);

    @GET("app/message/list")
    Single<Response<List<Message>>> getMessages();

    @GET("app/schedules/select")
    Single<Response<ScheduleResponse>> getSchedule(@Query("inmate_id") String str, @Query("start") long j, @Query("period") String str2);

    @GET("app/support-info")
    Single<ApiResponse<SupportInfo>> getSupportInfo();

    @GET("app/visitor/terms")
    Single<ApiResponse<Terms>> getTerms();

    @GET("app/visitor/tutorials")
    Single<ApiResponse<List<String>>> getTutorials();

    @GET("calls/webrtc/grant-access")
    Single<ApiResponse<TwilioToken>> getTwilioToken(@Query("call_pubid") String str, @Query("src") String str2);

    @GET("app/gallery/list?type=videos")
    Single<ApiResponse<List<MediaWithCategory>>> getVideos();

    @GET("prison/get_visitor_agreement")
    Single<ApiResponse<Map<String, String>>> getVisitorAgreementUrl(@Query("prison_id") String str);

    @GET("app/schedules/scheduled")
    Single<Response<ScheduledResponse>> getVisits();

    @GET("visitor/heartbeat")
    Single<Response<State>> heartbeat(@Query("ctx") String str);

    @GET("message/init")
    Single<ApiResponse<MessageInfo>> initMessage(@Query("visitor_id") String str, @Query("inmate_id") String str2);

    @FormUrlEncoded
    @POST("app/message/read-all")
    Single<ApiResponse<Empty>> readMessages(@Field("messages[]") List<String> list);

    @GET("report/report-error?type=call&station_id=")
    Single<ApiResponse<ReportResponse>> reportError(@Query("problems") String str, @Query("id") String str2);

    @FormUrlEncoded
    @POST("app/refund")
    Single<RefundResponse> requestRefund(@Field("visitor_id") String str, @Field("inmate_id") String str2, @Field("amount") String str3, @Field("notes") String str4);

    @FormUrlEncoded
    @POST("app/save-gcm-token")
    Single<Response<UserInfo>> saveToken(@Field("user_id") String str, @Field("fcm_token") String str2, @Field("device_os") String str3);

    @POST("message/send")
    @Multipart
    Single<Response<Message>> sendMessage(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part, @Part MultipartBody.Part part2);

    @GET("app/schedules/request")
    Single<ApiResponse<Empty>> sendVisitRequest(@Query("start") long j, @Query("inmate_id") String str);

    @POST("visitor/set-terms-agreed")
    Single<ApiResponse<Object>> setTermsAgreed();

    @GET("calls/webrtc/start")
    Single<ApiResponse<CallWrapper>> startLiveswitchCall(@Query("call_pubid") String str, @Query("channel_id") String str2, @Query("app_id") String str3, @Query("src") String str4);

    @GET("calls/webrtc/start")
    Single<ApiResponse<CallWrapper>> startWebrtcCall(@Query("call_pubid") String str, @Query("src") String str2);

    @POST("https://app.homewav.com//visitor/transfer-funds")
    @Multipart
    Single<ResponseBody> transferFunds(@Part MultipartBody.Part part, @Part MultipartBody.Part part2, @Part MultipartBody.Part part3);

    @FormUrlEncoded
    @POST("app/message/undo/")
    Single<ApiResponse<Empty>> undoChatDeletion(@Field("messages[]") List<String> list);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: HomewavApi.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Single saveToken$default(HomewavApi homewavApi, String str, String str2, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    str3 = ChannelRegistrationPayload.ANDROID_DEVICE_TYPE;
                }
                return homewavApi.saveToken(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: saveToken");
        }

        public static /* synthetic */ Single heartbeat$default(HomewavApi homewavApi, String str, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = "mobile";
                }
                return homewavApi.heartbeat(str);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: heartbeat");
        }

        public static /* synthetic */ Single getLastMessages$default(HomewavApi homewavApi, String str, int i, Integer num, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = 10;
                }
                if ((i2 & 4) != 0) {
                    num = null;
                }
                return homewavApi.getLastMessages(str, i, num);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLastMessages");
        }

        public static /* synthetic */ Single sendMessage$default(HomewavApi homewavApi, Map map, MultipartBody.Part part, MultipartBody.Part part2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    part = null;
                }
                if ((i & 4) != 0) {
                    part2 = null;
                }
                return homewavApi.sendMessage(map, part, part2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendMessage");
        }

        public static /* synthetic */ Single acceptCall$default(HomewavApi homewavApi, String str, String str2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "visitor";
                }
                return homewavApi.acceptCall(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: acceptCall");
        }

        public static /* synthetic */ Single startWebrtcCall$default(HomewavApi homewavApi, String str, String str2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "visitor";
                }
                return homewavApi.startWebrtcCall(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startWebrtcCall");
        }

        public static /* synthetic */ Single startLiveswitchCall$default(HomewavApi homewavApi, String str, String str2, String str3, String str4, int i, Object obj) {
            if (obj == null) {
                if ((i & 8) != 0) {
                    str4 = "visitor";
                }
                return homewavApi.startLiveswitchCall(str, str2, str3, str4);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startLiveswitchCall");
        }

        public static /* synthetic */ Single checkinWebrtcCall$default(HomewavApi homewavApi, String str, String str2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "visitor";
                }
                return homewavApi.checkinWebrtcCall(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkinWebrtcCall");
        }

        public static /* synthetic */ Completable endWebrtcCall$default(HomewavApi homewavApi, String str, String str2, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "HangupRequested";
                }
                if ((i & 4) != 0) {
                    str3 = "visitor";
                }
                return homewavApi.endWebrtcCall(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: endWebrtcCall");
        }

        public static /* synthetic */ Single getTwilioToken$default(HomewavApi homewavApi, String str, String str2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "visitor";
                }
                return homewavApi.getTwilioToken(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTwilioToken");
        }

        public static /* synthetic */ Single getLiveswitchCallData$default(HomewavApi homewavApi, String str, String str2, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "visitor";
                }
                return homewavApi.getLiveswitchCallData(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLiveswitchCallData");
        }

        public static /* synthetic */ Single getCallMessage$default(HomewavApi homewavApi, String str, String str2, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "0";
                }
                if ((i & 4) != 0) {
                    str3 = "visitor";
                }
                return homewavApi.getCallMessage(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallMessage");
        }

        public static /* synthetic */ Observable declineCall$default(HomewavApi homewavApi, String str, String str2, String str3, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "homewav.call.ended";
                }
                if ((i & 4) != 0) {
                    str3 = "";
                }
                return homewavApi.declineCall(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: declineCall");
        }
    }
}
