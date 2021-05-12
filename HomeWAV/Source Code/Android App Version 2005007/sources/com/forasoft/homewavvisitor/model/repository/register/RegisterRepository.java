package com.forasoft.homewavvisitor.model.repository.register;

import android.content.Context;
import android.net.Uri;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateShort;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.google.gson.JsonObject;
import com.urbanairship.util.Attributes;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u001c\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u0018H\u0002J{\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00110 2\b\u0010\"\u001a\u0004\u0018\u00010\u001b2\b\u0010#\u001a\u0004\u0018\u00010\u001b2\b\u0010$\u001a\u0004\u0018\u00010\u001b2\b\u0010%\u001a\u0004\u0018\u00010\u001b2\b\u0010&\u001a\u0004\u0018\u00010\u001b2\b\u0010'\u001a\u0004\u0018\u00010\u001b2\b\u0010(\u001a\u0004\u0018\u00010\u001b2\b\u0010)\u001a\u0004\u0018\u00010\u001b2\b\u0010*\u001a\u0004\u0018\u00010\u001b2\b\u0010+\u001a\u0004\u0018\u00010,¢\u0006\u0002\u0010-J.\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00110 2\b\u0010/\u001a\u0004\u0018\u00010\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u00100\u001a\u00020\u001bJ\u0012\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u00110 J\u0018\u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000205040\u00110 J \u00106\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0012040\u00110 2\u0006\u00107\u001a\u00020\u001bJ.\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00110 2\u0006\u00109\u001a\u00020:2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u0018R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;", "", "context", "Landroid/content/Context;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/dao/UserDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/Constants;)V", "addInmate", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/register/InmateShort;", "createAvatarPart", "Lokhttp3/MultipartBody$Part;", "photo", "Landroid/net/Uri;", "createBody", "Lkotlin/Pair;", "", "Lokhttp3/RequestBody;", "createIdPhotoPart", "photoId", "editProfile", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "oldPassword", "newPassword", "birthDate", "email", "phone", "street", "city", "stateId", "zip", "isNotificationSubscriptionEnabled", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "editUserImages", "photoAvatar", "username", "getVisits", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "loadFacilities", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "loadInmatesForFacility", "facilityId", "register", "signUpDataString", "Lcom/google/gson/JsonObject;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RegisterRepository.kt */
public final class RegisterRepository {
    /* access modifiers changed from: private */
    public final SignUpApi api;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final Constants constants;
    private final Context context;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final UserDao userDao;

    @Inject
    public RegisterRepository(Context context2, UserDao userDao2, InmateDao inmateDao2, AuthHolder authHolder2, SignUpApi signUpApi, Constants constants2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(signUpApi, "api");
        Intrinsics.checkParameterIsNotNull(constants2, "constants");
        this.context = context2;
        this.userDao = userDao2;
        this.inmateDao = inmateDao2;
        this.authHolder = authHolder2;
        this.api = signUpApi;
        this.constants = constants2;
    }

    public final Single<ApiResponse<User>> register(JsonObject jsonObject, Uri uri, Uri uri2) {
        Intrinsics.checkParameterIsNotNull(jsonObject, "signUpDataString");
        Single<ApiResponse<User>> doOnSuccess = this.api.signUp(jsonObject, createAvatarPart(uri), createIdPhotoPart(uri2)).doOnSuccess(new RegisterRepository$register$1(this));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "api.signUp(signUpDataStr… userDao.saveUser(it) } }");
        return doOnSuccess;
    }

    public final Single<ApiResponse<List<Facility>>> loadFacilities() {
        return this.api.loadFacilities();
    }

    public final Single<ApiResponse<List<Inmate>>> loadInmatesForFacility(String str) {
        Intrinsics.checkParameterIsNotNull(str, "facilityId");
        return this.api.loadInmates(str);
    }

    public final Observable<ApiResponse<Inmate>> addInmate(InmateShort inmateShort) {
        Intrinsics.checkParameterIsNotNull(inmateShort, "inmate");
        Observable<R> flatMap = this.userDao.getSingleUser().flatMapObservable(new RegisterRepository$addInmate$1(this, inmateShort)).flatMap(new RegisterRepository$addInmate$2(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "userDao.getSingleUser()\n…      }\n                }");
        return flatMap;
    }

    public final Single<ApiResponse<User>> editProfile(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool) {
        Single<R> doOnSuccess = this.userDao.getSingleUser().flatMap(new RegisterRepository$editProfile$1(this, str2, str, str4, str5, str6, str7, str3, str8, str9, bool)).doOnSuccess(new RegisterRepository$editProfile$2(this));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "userDao.getSingleUser()\n… userDao.saveUser(it) } }");
        return doOnSuccess;
    }

    public final Single<ApiResponse<User>> editUserImages(Uri uri, Uri uri2, String str) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Attributes.USERNAME, str);
        Single<ApiResponse<User>> doOnSuccess = this.api.editProfilePhotos(jsonObject, createAvatarPart(uri), createIdPhotoPart(uri2)).doOnSuccess(new RegisterRepository$editUserImages$1(this));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "api.editProfilePhotos(vi…      }\n                }");
        return doOnSuccess;
    }

    private final MultipartBody.Part createIdPhotoPart(Uri uri) {
        String scheme;
        if (uri == null || ((scheme = uri.getScheme()) != null && StringsKt.startsWith$default(scheme, "http", false, 2, (Object) null))) {
            return null;
        }
        Pair<String, RequestBody> createBody = createBody(uri);
        return MultipartBody.Part.Companion.createFormData("upload_photo_id", createBody.component1(), createBody.component2());
    }

    private final MultipartBody.Part createAvatarPart(Uri uri) {
        String scheme;
        if (uri == null || ((scheme = uri.getScheme()) != null && StringsKt.startsWith$default(scheme, "http", false, 2, (Object) null))) {
            return null;
        }
        Pair<String, RequestBody> createBody = createBody(uri);
        return MultipartBody.Part.Companion.createFormData("upload_person", createBody.component1(), createBody.component2());
    }

    private final Pair<String, RequestBody> createBody(Uri uri) {
        File file = new File(this.context.getFilesDir(), uri.getLastPathSegment());
        return TuplesKt.to(file.getName(), RequestBody.Companion.create(file, MediaType.Companion.parse("image/png")));
    }

    public final Single<ApiResponse<ScheduledResponse>> getVisits() {
        return this.api.getVisits();
    }
}
