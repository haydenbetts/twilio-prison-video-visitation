package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.ProcessVideoWorker;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.data.RecordVideoResult;
import com.forasoft.homewavvisitor.model.data.Sender;
import com.forasoft.homewavvisitor.model.data.TakeGifResult;
import com.forasoft.homewavvisitor.model.data.TakePictureResult;
import com.forasoft.homewavvisitor.model.data.TakeVideoResult;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import com.forasoft.homewavvisitor.ui.fragment.conversations.MediaChooserFragment;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import org.json.JSONObject;
import retrofit2.HttpException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ó\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003*\u00016\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003By\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f¢\u0006\u0002\u0010 J\u001a\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0>0=0<H\u0002J\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020A0>2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020A0>H\u0002J\b\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020DH\u0002J\u0010\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020+H\u0002J\u0006\u0010I\u001a\u00020JJ\u0010\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020\u000bH\u0002J\b\u0010M\u001a\u00020DH\u0002J\b\u0010N\u001a\u00020DH\u0002J\b\u0010O\u001a\u00020DH\u0002J\u0010\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020RH\u0002J\u0006\u0010S\u001a\u00020JJ\u0012\u0010T\u001a\u00020J2\b\b\u0002\u0010U\u001a\u00020+H\u0002J\u0006\u0010V\u001a\u00020JJ\u0006\u0010W\u001a\u00020JJ\u0006\u0010X\u001a\u00020JJ\u0006\u0010Y\u001a\u00020JJ\u0006\u0010Z\u001a\u00020JJ\u000e\u0010[\u001a\u00020J2\u0006\u0010L\u001a\u00020\u000bJ\u0014\u0010\\\u001a\u00020J2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u000b0>J\b\u0010^\u001a\u00020JH\u0016J\b\u0010_\u001a\u00020JH\u0014J\u0006\u0010`\u001a\u00020JJ\b\u0010a\u001a\u00020JH\u0002J\u000e\u0010a\u001a\u00020J2\u0006\u0010L\u001a\u00020?J\u0006\u0010b\u001a\u00020JJ\u0006\u0010c\u001a\u00020JJ\u0012\u0010d\u001a\u00020J2\b\u0010e\u001a\u0004\u0018\u00010fH\u0016J\u000e\u0010g\u001a\u00020J2\u0006\u0010L\u001a\u00020\u000bJ\u000e\u0010h\u001a\u00020J2\u0006\u0010L\u001a\u00020\u000bJ\b\u0010i\u001a\u00020JH\u0002J\u000e\u0010i\u001a\u00020J2\u0006\u0010L\u001a\u00020?J\u0016\u0010j\u001a\u00020J2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020?0>H\u0002J\u0006\u0010k\u001a\u00020JJ\u0010\u0010l\u001a\u00020\"2\u0006\u0010m\u001a\u00020\"H\u0002J\b\u0010n\u001a\u00020JH\u0002J\u0012\u0010o\u001a\u00020J2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J\u0012\u0010r\u001a\u00020J2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J\u0010\u0010s\u001a\u00020J2\u0006\u0010p\u001a\u00020qH\u0002J\u0012\u0010t\u001a\u00020J2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J\u0012\u0010u\u001a\u00020J2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J\u0010\u0010v\u001a\u00020J2\u0006\u0010H\u001a\u00020+H\u0002J\b\u0010w\u001a\u00020JH\u0002J\u0010\u0010x\u001a\u00020J2\u0006\u0010y\u001a\u00020zH\u0002J\b\u0010{\u001a\u00020JH\u0002J\u001c\u0010|\u001a\b\u0012\u0004\u0012\u00020?0>2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020?0>H\u0002R\u000e\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0004\n\u0002\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0%X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u000206X\u0004¢\u0006\u0004\n\u0002\u00107R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\"X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationView;", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "inmateId", "", "filesDir", "Ljava/io/File;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "messageDao", "Lcom/forasoft/homewavvisitor/dao/MessageDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/data/account/Settings;Ljava/lang/String;Ljava/io/File;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/MessageDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/dao/CallDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/model/Analytics;)V", "currentPage", "", "Ljava/lang/Integer;", "facility", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "gifPath", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "isLastPage", "", "isLoading", "isSendingMessage", "lastMessage", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "lastMessagesUnreadCount", "picturePath", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "pubId", "pusherListener", "com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter$pusherListener$1;", "streamName", "videoLength", "videoPath", "checkNewMessages", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/Message;", "filterByInmate", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "notifications", "getGifData", "Landroidx/work/Data;", "getImageData", "getMessagePrice", "", "isEmptyTextMessage", "getNotificationsCount", "", "getTextData", "message", "getVideoData", "getVideoProcessData", "getVideoUploadData", "handleError", "error", "", "init", "loadMessages", "isFirstTime", "loadMoreMessages", "onAddFundsClicked", "onBackPressed", "onCloseClicked", "onCloudClicked", "onConfirmClicked", "onDeleteClicked", "messages", "onDestroy", "onFirstViewAttach", "onFolderClicked", "onImagePreviewClicked", "onNotificationsClicked", "onPreviewClicked", "onResult", "result", "", "onSendClicked", "onTextChanged", "onVideoPreviewClicked", "readMessages", "recordVideo", "secondsToMinutes", "seconds", "sendEvent", "sendGifMessage", "textRequest", "Landroidx/work/OneTimeWorkRequest;", "sendImageMessage", "sendTextMessage", "sendVideoFromGalleryMessage", "sendVideoMessage", "showConfirmDialog", "showMessages", "subscribeToWorkStatus", "id", "Ljava/util/UUID;", "updateMessages", "updateStatus", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ConversationPresenter.kt */
public final class ConversationPresenter extends BasePresenter<ConversationView> implements ResultListener {
    private final Analytics analytics;
    /* access modifiers changed from: private */
    public final HomewavApi api;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final CallDao callDao;
    /* access modifiers changed from: private */
    public Integer currentPage;
    /* access modifiers changed from: private */
    public final BehaviorSubject<Facility> facility;
    private final File filesDir;
    private String gifPath;
    private final HeartbeatRepository heartbeatRepository;
    /* access modifiers changed from: private */
    public final BehaviorSubject<Inmate> inmate;
    private final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final String inmateId;
    /* access modifiers changed from: private */
    public boolean isLastPage;
    /* access modifiers changed from: private */
    public boolean isLoading;
    /* access modifiers changed from: private */
    public boolean isSendingMessage;
    /* access modifiers changed from: private */
    public ChatItem lastMessage;
    /* access modifiers changed from: private */
    public int lastMessagesUnreadCount;
    /* access modifiers changed from: private */
    public final MessageDao messageDao;
    /* access modifiers changed from: private */
    public final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    private String picturePath;
    private Protocol protocol;
    private String pubId;
    private final PusherHolder pusherHolder;
    private final ConversationPresenter$pusherListener$1 pusherListener = new ConversationPresenter$pusherListener$1(this);
    /* access modifiers changed from: private */
    public final HomewavRouter router;
    /* access modifiers changed from: private */
    public final Settings settings;
    private String streamName;
    private int videoLength;
    private String videoPath;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PusherEvent.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PusherEvent.Type.chat_warning_on.ordinal()] = 1;
            iArr[PusherEvent.Type.chat_warning_off.ordinal()] = 2;
        }
    }

    @Inject
    public ConversationPresenter(HomewavRouter homewavRouter, AuthHolder authHolder2, Settings settings2, @InmateId String str, File file, HomewavApi homewavApi, PusherHolder pusherHolder2, PaymentGateway paymentGateway2, NotificationDao notificationDao2, MessageDao messageDao2, InmateDao inmateDao2, CallDao callDao2, HeartbeatRepository heartbeatRepository2, Analytics analytics2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(settings2, "settings");
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(file, "filesDir");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(pusherHolder2, "pusherHolder");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(messageDao2, "messageDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        this.router = homewavRouter;
        this.authHolder = authHolder2;
        this.settings = settings2;
        this.inmateId = str;
        this.filesDir = file;
        this.api = homewavApi;
        this.pusherHolder = pusherHolder2;
        this.paymentGateway = paymentGateway2;
        this.notificationDao = notificationDao2;
        this.messageDao = messageDao2;
        this.inmateDao = inmateDao2;
        this.callDao = callDao2;
        this.heartbeatRepository = heartbeatRepository2;
        this.analytics = analytics2;
        BehaviorSubject<Inmate> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.inmate = create;
        BehaviorSubject<Facility> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.facility = create2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        User user = this.authHolder.getUser();
        if (user != null) {
            ((ConversationView) getViewState()).setupList(user);
        }
        this.router.setResultListener(1, this);
        this.pusherHolder.listenEvents(this.pusherListener);
        loadMessages(true);
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.heartbeatRepository.getHeartbeatState().subscribe(new ConversationPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear… = newBalance))\n        }");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = CommonKt.applyAsync(this.inmateDao.getInmate(this.inmateId)).subscribe(new ConversationPresenter$sam$io_reactivex_functions_Consumer$0(new ConversationPresenter$onFirstViewAttach$3(this.inmate)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "inmateDao.getInmate(inma…subscribe(inmate::onNext)");
        DisposableKt.plusAssign(disposables2, subscribe2);
        CompositeDisposable disposables3 = getDisposables();
        Disposable subscribe3 = this.inmate.take(1).subscribe(new ConversationPresenter$sam$io_reactivex_functions_Consumer$0(new ConversationPresenter$onFirstViewAttach$4((ConversationView) getViewState())));
        Intrinsics.checkExpressionValueIsNotNull(subscribe3, "inmate.take(1)\n         …ibe(viewState::setInmate)");
        DisposableKt.plusAssign(disposables3, subscribe3);
        CompositeDisposable disposables4 = getDisposables();
        Observable<R> map = this.inmate.take(1).map(ConversationPresenter$onFirstViewAttach$5.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "inmate.take(1)\n         …_message_length.toInt() }");
        Disposable subscribe4 = CommonKt.applyAsync(map).subscribe(new ConversationPresenter$sam$io_reactivex_functions_Consumer$0(new ConversationPresenter$onFirstViewAttach$6((ConversationView) getViewState())));
        Intrinsics.checkExpressionValueIsNotNull(subscribe4, "inmate.take(1)\n         …wState::setMessageFilter)");
        DisposableKt.plusAssign(disposables4, subscribe4);
        CompositeDisposable disposables5 = getDisposables();
        Disposable subscribe5 = this.inmate.take(1).subscribe(new ConversationPresenter$onFirstViewAttach$7(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe5, "inmate.take(1)\n         … }, {})\n                }");
        DisposableKt.plusAssign(disposables5, subscribe5);
    }

    public void onDestroy() {
        String visitor_id;
        super.onDestroy();
        this.router.removeResultListener(1);
        this.pusherHolder.stopListenEvents(this.pusherListener);
        User user = this.authHolder.getUser();
        if (user != null && (visitor_id = user.getVisitor_id()) != null) {
            ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new ConversationPresenter$onDestroy$$inlined$let$lambda$1(visitor_id, this), 31, (Object) null);
        }
    }

    public void onResult(Object obj) {
        String str;
        String str2;
        if (obj instanceof RecordVideoResult) {
            RecordVideoResult recordVideoResult = (RecordVideoResult) obj;
            this.pubId = recordVideoResult.getPubId();
            this.streamName = recordVideoResult.getStreamName();
            this.protocol = recordVideoResult.getProtocol();
            this.videoLength = recordVideoResult.getVideoLength();
            String str3 = null;
            this.picturePath = str3;
            this.gifPath = str3;
            this.videoPath = str3;
            ((ConversationView) getViewState()).displayRecordedVideo(this.protocol, this.videoLength, this.streamName);
        }
        if (obj instanceof TakeVideoResult) {
            String str4 = null;
            this.pubId = str4;
            this.streamName = str4;
            this.protocol = null;
            TakeVideoResult takeVideoResult = (TakeVideoResult) obj;
            this.videoLength = takeVideoResult.getLength();
            this.picturePath = str4;
            this.gifPath = str4;
            Uri parse = Uri.parse(takeVideoResult.getPath());
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(result.path)");
            if (CollectionsKt.contains(SetsKt.setOf("http", "https"), parse.getScheme())) {
                str2 = takeVideoResult.getPath();
            } else {
                str2 = Uri.fromFile(new File(takeVideoResult.getPath())).toString();
            }
            this.videoPath = str2;
            ConversationView conversationView = (ConversationView) getViewState();
            String str5 = this.videoPath;
            if (str5 == null) {
                Intrinsics.throwNpe();
            }
            conversationView.displayVideoFromGallery(str5);
        }
        if (obj instanceof TakePictureResult) {
            String str6 = null;
            this.pubId = str6;
            this.streamName = str6;
            this.protocol = null;
            this.videoLength = 0;
            TakePictureResult takePictureResult = (TakePictureResult) obj;
            Uri parse2 = Uri.parse(takePictureResult.getPicturePath());
            Intrinsics.checkExpressionValueIsNotNull(parse2, "Uri.parse(result.picturePath)");
            if (CollectionsKt.contains(SetsKt.setOf("http", "https"), parse2.getScheme())) {
                str = takePictureResult.getPicturePath();
            } else {
                str = Uri.fromFile(new File(takePictureResult.getPicturePath())).toString();
            }
            this.picturePath = str;
            this.gifPath = str6;
            this.videoPath = str6;
            ConversationView conversationView2 = (ConversationView) getViewState();
            String str7 = this.picturePath;
            if (str7 == null) {
                Intrinsics.throwNpe();
            }
            conversationView2.displayTakenPicture(str7);
        }
        if (obj instanceof TakeGifResult) {
            String str8 = null;
            this.pubId = str8;
            this.streamName = str8;
            this.protocol = null;
            this.videoLength = 0;
            this.picturePath = str8;
            this.gifPath = ((TakeGifResult) obj).getPath();
            this.videoPath = str8;
            ConversationView conversationView3 = (ConversationView) getViewState();
            String str9 = this.gifPath;
            if (str9 == null) {
                Intrinsics.throwNpe();
            }
            conversationView3.displayTakenPicture(str9);
        }
    }

    public final void init() {
        if (this.pubId != null) {
            ((ConversationView) getViewState()).displayRecordedVideo(this.protocol, this.videoLength, this.streamName);
        }
        if (this.videoPath != null) {
            ConversationView conversationView = (ConversationView) getViewState();
            String str = this.videoPath;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            conversationView.displayVideoFromGallery(str);
        }
        if (this.picturePath != null) {
            ConversationView conversationView2 = (ConversationView) getViewState();
            String str2 = this.picturePath;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            conversationView2.displayTakenPicture(str2);
        }
        if (this.gifPath != null) {
            ConversationView conversationView3 = (ConversationView) getViewState();
            String str3 = this.gifPath;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            conversationView3.displayTakenPicture(str3);
        }
    }

    public final void recordVideo() {
        ((ConversationView) getViewState()).clearSelection();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.inmate.take(1).subscribe(new ConversationPresenter$recordVideo$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmate.take(1)\n         …ilable)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* JADX WARNING: type inference failed for: r5v10, types: [androidx.work.WorkRequest] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConfirmClicked(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "message"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            boolean r0 = r4.isSendingMessage
            if (r0 == 0) goto L_0x000a
            return
        L_0x000a:
            r0 = 1
            r4.isSendingMessage = r0
            r1 = 0
            r2 = r1
            androidx.work.OneTimeWorkRequest r2 = (androidx.work.OneTimeWorkRequest) r2
            r3 = r5
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            if (r0 == 0) goto L_0x0046
            androidx.work.OneTimeWorkRequest$Builder r0 = new androidx.work.OneTimeWorkRequest$Builder
            java.lang.Class<com.forasoft.homewavvisitor.model.UploadWorker> r2 = com.forasoft.homewavvisitor.model.UploadWorker.class
            r0.<init>(r2)
            java.lang.String r2 = r4.inmateId
            androidx.work.WorkRequest$Builder r0 = r0.addTag(r2)
            androidx.work.OneTimeWorkRequest$Builder r0 = (androidx.work.OneTimeWorkRequest.Builder) r0
            java.lang.String r2 = "text"
            androidx.work.WorkRequest$Builder r0 = r0.addTag(r2)
            androidx.work.OneTimeWorkRequest$Builder r0 = (androidx.work.OneTimeWorkRequest.Builder) r0
            androidx.work.Data r5 = r4.getTextData(r5)
            androidx.work.WorkRequest$Builder r5 = r0.setInputData(r5)
            androidx.work.OneTimeWorkRequest$Builder r5 = (androidx.work.OneTimeWorkRequest.Builder) r5
            androidx.work.WorkRequest r5 = r5.build()
            r2 = r5
            androidx.work.OneTimeWorkRequest r2 = (androidx.work.OneTimeWorkRequest) r2
        L_0x0046:
            java.lang.String r5 = r4.pubId
            if (r5 == 0) goto L_0x004e
            r4.sendVideoMessage(r2)
            goto L_0x006b
        L_0x004e:
            java.lang.String r5 = r4.videoPath
            if (r5 == 0) goto L_0x0056
            r4.sendVideoFromGalleryMessage(r2)
            goto L_0x006b
        L_0x0056:
            java.lang.String r5 = r4.picturePath
            if (r5 == 0) goto L_0x005e
            r4.sendImageMessage(r2)
            goto L_0x006b
        L_0x005e:
            java.lang.String r5 = r4.gifPath
            if (r5 == 0) goto L_0x0066
            r4.sendGifMessage(r2)
            goto L_0x006b
        L_0x0066:
            if (r2 == 0) goto L_0x006b
            r4.sendTextMessage(r2)
        L_0x006b:
            r5 = r1
            java.lang.String r5 = (java.lang.String) r5
            r4.pubId = r5
            r4.streamName = r5
            com.forasoft.homewavvisitor.model.data.Protocol r1 = (com.forasoft.homewavvisitor.model.data.Protocol) r1
            r4.protocol = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter.onConfirmClicked(java.lang.String):void");
    }

    private final void sendVideoMessage(OneTimeWorkRequest oneTimeWorkRequest) {
        WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(UploadWorker.class).addTag(this.inmateId)).addTag("video")).setInputData(getVideoData())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        OneTimeWorkRequest oneTimeWorkRequest2 = (OneTimeWorkRequest) build;
        if (oneTimeWorkRequest != null) {
            WorkManager.getInstance().beginWith(oneTimeWorkRequest2).then(oneTimeWorkRequest).enqueue();
            UUID id = oneTimeWorkRequest.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "textRequest.id");
            subscribeToWorkStatus(id);
            return;
        }
        WorkManager.getInstance().beginWith(oneTimeWorkRequest2).enqueue();
        UUID id2 = oneTimeWorkRequest2.getId();
        Intrinsics.checkExpressionValueIsNotNull(id2, "videoRequest.id");
        subscribeToWorkStatus(id2);
    }

    private final void sendVideoFromGalleryMessage(OneTimeWorkRequest oneTimeWorkRequest) {
        WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(ProcessVideoWorker.class).addTag(this.inmateId)).addTag(UploadWorker.TAG_RESIZE)).setInputData(getVideoProcessData())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        OneTimeWorkRequest oneTimeWorkRequest2 = (OneTimeWorkRequest) build;
        WorkRequest build2 = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(UploadWorker.class).addTag(this.inmateId)).addTag("video")).setInputData(getVideoUploadData())).build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…\n                .build()");
        OneTimeWorkRequest oneTimeWorkRequest3 = (OneTimeWorkRequest) build2;
        if (oneTimeWorkRequest != null) {
            Uri parse = Uri.parse(this.videoPath);
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(videoPath)");
            if (CollectionsKt.contains(SetsKt.setOf("http", "https"), parse.getScheme())) {
                WorkManager.getInstance().beginWith(oneTimeWorkRequest3).then(oneTimeWorkRequest).enqueue();
            } else {
                WorkManager.getInstance().beginWith(oneTimeWorkRequest2).then(oneTimeWorkRequest3).then(oneTimeWorkRequest).enqueue();
            }
            UUID id = oneTimeWorkRequest.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "textRequest.id");
            subscribeToWorkStatus(id);
            return;
        }
        Uri parse2 = Uri.parse(this.videoPath);
        Intrinsics.checkExpressionValueIsNotNull(parse2, "Uri.parse(videoPath)");
        if (CollectionsKt.contains(SetsKt.setOf("http", "https"), parse2.getScheme())) {
            WorkManager.getInstance().enqueue((WorkRequest) oneTimeWorkRequest3);
        } else {
            WorkManager.getInstance().beginWith(oneTimeWorkRequest2).then(oneTimeWorkRequest3).enqueue();
        }
        UUID id2 = oneTimeWorkRequest3.getId();
        Intrinsics.checkExpressionValueIsNotNull(id2, "uploadVideoRequest.id");
        subscribeToWorkStatus(id2);
    }

    private final void sendImageMessage(OneTimeWorkRequest oneTimeWorkRequest) {
        WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(UploadWorker.class).addTag(this.inmateId)).addTag("image")).setInputData(getImageData())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        OneTimeWorkRequest oneTimeWorkRequest2 = (OneTimeWorkRequest) build;
        if (oneTimeWorkRequest != null) {
            WorkManager.getInstance().beginWith(oneTimeWorkRequest2).then(oneTimeWorkRequest).enqueue();
            UUID id = oneTimeWorkRequest.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "textRequest.id");
            subscribeToWorkStatus(id);
            return;
        }
        WorkManager.getInstance().beginWith(oneTimeWorkRequest2).enqueue();
        UUID id2 = oneTimeWorkRequest2.getId();
        Intrinsics.checkExpressionValueIsNotNull(id2, "imageRequest.id");
        subscribeToWorkStatus(id2);
    }

    private final void sendGifMessage(OneTimeWorkRequest oneTimeWorkRequest) {
        WorkRequest build = ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(UploadWorker.class).addTag(this.inmateId)).addTag("image")).setInputData(getGifData())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OneTimeWorkRequest.Build…\n                .build()");
        OneTimeWorkRequest oneTimeWorkRequest2 = (OneTimeWorkRequest) build;
        if (oneTimeWorkRequest != null) {
            WorkManager.getInstance().beginWith(oneTimeWorkRequest2).then(oneTimeWorkRequest).enqueue();
            UUID id = oneTimeWorkRequest.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "textRequest.id");
            subscribeToWorkStatus(id);
            return;
        }
        WorkManager.getInstance().beginWith(oneTimeWorkRequest2).enqueue();
        UUID id2 = oneTimeWorkRequest2.getId();
        Intrinsics.checkExpressionValueIsNotNull(id2, "gifRequest.id");
        subscribeToWorkStatus(id2);
    }

    private final void sendTextMessage(OneTimeWorkRequest oneTimeWorkRequest) {
        WorkManager.getInstance().beginWith(oneTimeWorkRequest).enqueue();
        UUID id = oneTimeWorkRequest.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "textRequest.id");
        subscribeToWorkStatus(id);
    }

    private final void subscribeToWorkStatus(UUID uuid) {
        CompositeDisposable disposables = getDisposables();
        LiveData<WorkInfo> workInfoByIdLiveData = WorkManager.getInstance().getWorkInfoByIdLiveData(uuid);
        Intrinsics.checkExpressionValueIsNotNull(workInfoByIdLiveData, "WorkManager.getInstance(…tWorkInfoByIdLiveData(id)");
        Disposable subscribe = CommonKt.applyAsync(CommonKt.toSingle(workInfoByIdLiveData)).subscribe(new ConversationPresenter$subscribeToWorkStatus$1(this), new ConversationPresenter$subscribeToWorkStatus$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "WorkManager.getInstance(… false\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void updateMessages() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = checkNewMessages().subscribeOn(Schedulers.io()).subscribe(new ConversationPresenter$updateMessages$1(this), ConversationPresenter$updateMessages$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "checkNewMessages()\n     …essages(it.body!!) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final Data getVideoData() {
        Data.Builder putString = new Data.Builder().putString(UploadWorker.KEY_PUB_ID, this.pubId);
        User user = this.authHolder.getUser();
        Data build = putString.putString(UploadWorker.KEY_VISITOR_ID, user != null ? user.getVisitor_id() : null).putString(UploadWorker.KEY_INMATE_ID, this.inmateId).putString(UploadWorker.KEY_ELAPSED_TIME, String.valueOf(this.videoLength)).putString("type", "video").putString(UploadWorker.KEY_GALLERY_URL, new File(this.filesDir, "message.mp4").getAbsolutePath()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ath)\n            .build()");
        return build;
    }

    private final Data getVideoUploadData() {
        Data.Builder builder = new Data.Builder();
        User user = this.authHolder.getUser();
        Data build = builder.putString(UploadWorker.KEY_VISITOR_ID, user != null ? user.getVisitor_id() : null).putString(UploadWorker.KEY_INMATE_ID, this.inmateId).putString(UploadWorker.KEY_GALLERY_URL, this.videoPath).putString(UploadWorker.KEY_ELAPSED_TIME, String.valueOf(this.videoLength)).putString("type", "video").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …eo\")\n            .build()");
        return build;
    }

    private final Data getVideoProcessData() {
        Data build = new Data.Builder().putString(UploadWorker.KEY_GALLERY_URL, this.videoPath).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ath)\n            .build()");
        return build;
    }

    private final Data getImageData() {
        Data.Builder builder = new Data.Builder();
        User user = this.authHolder.getUser();
        Data build = builder.putString(UploadWorker.KEY_VISITOR_ID, user != null ? user.getVisitor_id() : null).putString(UploadWorker.KEY_INMATE_ID, this.inmateId).putString(UploadWorker.KEY_GALLERY_URL, this.picturePath).putString("type", "image").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …ge\")\n            .build()");
        return build;
    }

    private final Data getGifData() {
        Data.Builder builder = new Data.Builder();
        User user = this.authHolder.getUser();
        Data build = builder.putString(UploadWorker.KEY_VISITOR_ID, user != null ? user.getVisitor_id() : null).putString(UploadWorker.KEY_INMATE_ID, this.inmateId).putString(UploadWorker.KEY_GALLERY_URL, this.gifPath).putString("type", "gif").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …if\")\n            .build()");
        return build;
    }

    private final Data getTextData(String str) {
        Data.Builder builder = new Data.Builder();
        User user = this.authHolder.getUser();
        Data build = builder.putString(UploadWorker.KEY_VISITOR_ID, user != null ? user.getVisitor_id() : null).putString(UploadWorker.KEY_INMATE_ID, this.inmateId).putString("body", str).putString("type", "text").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …xt\")\n            .build()");
        return build;
    }

    public final void onSendClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.inmate.take(1).subscribe(new ConversationPresenter$onSendClicked$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmate.take(1)\n         …      }\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007c, code lost:
        if (r7 != false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b2, code lost:
        if (r7 != false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c7, code lost:
        if (r7 != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float getMessagePrice(boolean r7) {
        /*
            r6 = this;
            io.reactivex.subjects.BehaviorSubject<com.forasoft.homewavvisitor.model.data.Inmate> r0 = r6.inmate
            java.lang.Object r0 = r0.getValue()
            com.forasoft.homewavvisitor.model.data.Inmate r0 = (com.forasoft.homewavvisitor.model.data.Inmate) r0
            java.lang.String r1 = r0.getPrison_price_per_text_message()
            float r1 = java.lang.Float.parseFloat(r1)
            java.lang.String r2 = r6.pubId
            java.lang.String r3 = "1"
            if (r2 == 0) goto L_0x0045
            java.lang.String r2 = r0.getPer_minute_charging_enabled()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            java.lang.String r0 = r0.getPrison_price_per_video_message()
            float r0 = java.lang.Float.parseFloat(r0)
            if (r7 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x0035
            int r7 = r6.videoLength
            int r7 = r6.secondsToMinutes(r7)
            float r7 = (float) r7
            float r1 = r0 * r7
            goto L_0x00cb
        L_0x0035:
            r1 = r0
            goto L_0x00cb
        L_0x0038:
            if (r2 == 0) goto L_0x0043
            int r7 = r6.videoLength
            int r7 = r6.secondsToMinutes(r7)
        L_0x0040:
            float r7 = (float) r7
            float r0 = r0 * r7
        L_0x0043:
            float r0 = r0 + r1
            goto L_0x0035
        L_0x0045:
            java.lang.String r2 = r6.videoPath
            if (r2 == 0) goto L_0x00a6
            java.lang.String r2 = "http"
            java.lang.String r4 = "https"
            java.lang.String[] r2 = new java.lang.String[]{r2, r4}
            java.util.Set r2 = kotlin.collections.SetsKt.setOf(r2)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.String r4 = r6.videoPath
            android.net.Uri r4 = android.net.Uri.parse(r4)
            java.lang.String r5 = "Uri.parse(videoPath)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            java.lang.String r4 = r4.getScheme()
            boolean r2 = kotlin.collections.CollectionsKt.contains(r2, r4)
            if (r2 == 0) goto L_0x007f
            io.reactivex.subjects.BehaviorSubject<com.forasoft.homewavvisitor.model.data.Facility> r0 = r6.facility
            java.lang.Object r0 = r0.getValue()
            com.forasoft.homewavvisitor.model.data.Facility r0 = (com.forasoft.homewavvisitor.model.data.Facility) r0
            java.lang.String r0 = r0.getPrice_per_s3_video_message()
            float r0 = java.lang.Float.parseFloat(r0)
            if (r7 == 0) goto L_0x00b5
            goto L_0x0035
        L_0x007f:
            java.lang.String r2 = r0.getPer_minute_charging_enabled()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            java.lang.String r0 = r0.getPrison_price_per_video_message()
            float r0 = java.lang.Float.parseFloat(r0)
            if (r7 == 0) goto L_0x009d
            if (r2 == 0) goto L_0x0035
            int r7 = r6.videoLength
            int r7 = r6.secondsToMinutes(r7)
            float r7 = (float) r7
            float r0 = r0 * r7
            goto L_0x0035
        L_0x009d:
            if (r2 == 0) goto L_0x0043
            int r7 = r6.videoLength
            int r7 = r6.secondsToMinutes(r7)
            goto L_0x0040
        L_0x00a6:
            java.lang.String r2 = r6.picturePath
            if (r2 == 0) goto L_0x00b7
            java.lang.String r0 = r0.getPrison_price_per_image_message()
            float r0 = java.lang.Float.parseFloat(r0)
            if (r7 == 0) goto L_0x00b5
            goto L_0x0035
        L_0x00b5:
            float r1 = r1 + r0
            goto L_0x00cb
        L_0x00b7:
            java.lang.String r2 = r6.gifPath
            if (r2 == 0) goto L_0x00cb
            java.lang.String r0 = r0.getPrison_price_per_gif_message()
            if (r0 == 0) goto L_0x00c6
            float r0 = java.lang.Float.parseFloat(r0)
            goto L_0x00c7
        L_0x00c6:
            r0 = 0
        L_0x00c7:
            if (r7 == 0) goto L_0x00b5
            goto L_0x0035
        L_0x00cb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter.getMessagePrice(boolean):float");
    }

    private final int secondsToMinutes(int i) {
        return (i / 60) + 1;
    }

    /* access modifiers changed from: private */
    public final void showConfirmDialog(boolean z) {
        Inmate value = this.inmate.getValue();
        float parseFloat = Float.parseFloat(value.getPrison_price_per_text_message());
        if (this.pubId != null) {
            boolean areEqual = Intrinsics.areEqual((Object) value.getPer_minute_charging_enabled(), (Object) "1");
            float parseFloat2 = Float.parseFloat(value.getPrison_price_per_video_message());
            if (z) {
                if (areEqual) {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_3, String.valueOf(parseFloat2));
                } else {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat2));
                }
            } else if (areEqual) {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_2, String.valueOf(parseFloat), String.valueOf(parseFloat2));
            } else {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat + parseFloat2));
            }
        } else if (this.videoPath != null) {
            Uri parse = Uri.parse(this.videoPath);
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(videoPath)");
            if (CollectionsKt.contains(SetsKt.setOf("http", "https"), parse.getScheme())) {
                float parseFloat3 = Float.parseFloat(this.facility.getValue().getPrice_per_s3_video_message());
                if (z) {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat3));
                } else {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat + parseFloat3));
                }
            } else {
                boolean areEqual2 = Intrinsics.areEqual((Object) value.getPer_minute_charging_enabled(), (Object) "1");
                float parseFloat4 = Float.parseFloat(value.getPrison_price_per_video_message());
                if (z) {
                    if (areEqual2) {
                        ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_3, String.valueOf(parseFloat4));
                    } else {
                        ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat4));
                    }
                } else if (areEqual2) {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_2, String.valueOf(parseFloat), String.valueOf(parseFloat4));
                } else {
                    ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat + parseFloat4));
                }
            }
        } else if (this.picturePath != null) {
            float parseFloat5 = Float.parseFloat(value.getPrison_price_per_image_message());
            if (z) {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat5));
            } else {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat5 + parseFloat));
            }
        } else if (this.gifPath != null) {
            String prison_price_per_gif_message = value.getPrison_price_per_gif_message();
            float parseFloat6 = prison_price_per_gif_message != null ? Float.parseFloat(prison_price_per_gif_message) : 0.0f;
            if (z) {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat6));
            } else {
                ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat6 + parseFloat));
            }
        } else {
            ((ConversationView) getViewState()).showConfirmDialog(R.string.dialog_confirm_message_1, String.valueOf(parseFloat));
        }
    }

    public final void onVideoPreviewClicked(Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        ((ConversationView) getViewState()).clearSelection();
        String videoUrl = message.getVideoUrl();
        if (videoUrl == null) {
            String streamUrl = message.getStreamUrl();
            videoUrl = streamUrl != null ? StringsKt.replace$default(streamUrl, "https", "http", false, 4, (Object) null) : null;
        }
        if (videoUrl != null) {
            this.router.navigateTo(new Screens.VideoViewScreen(videoUrl));
        }
    }

    public final void onImagePreviewClicked(Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        ((ConversationView) getViewState()).clearSelection();
        HomewavRouter homewavRouter = this.router;
        String imageUrl = message.getImageUrl();
        if (imageUrl == null) {
            Intrinsics.throwNpe();
        }
        homewavRouter.navigateTo(new Screens.ImageViewScreen(imageUrl));
    }

    public final void onAddFundsClicked() {
        ((ConversationView) getViewState()).clearSelection();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.inmateDao.getInmate(this.inmateId)).subscribe(new ConversationPresenter$onAddFundsClicked$1(this), ConversationPresenter$onAddFundsClicked$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getInmate(inma…n)\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onCloudClicked() {
        ((ConversationView) getViewState()).clearSelection();
        this.router.navigateTo(new Screens.MediaChooserScreen(this.inmateId, MediaChooserFragment.S3_MEDIA_LOADER));
    }

    public final void onFolderClicked() {
        ((ConversationView) getViewState()).clearSelection();
        this.router.navigateTo(new Screens.MediaChooserScreen(this.inmateId, MediaChooserFragment.MEDIA_STORE_MEDIA_LOADER));
    }

    public final void onPreviewClicked() {
        ((ConversationView) getViewState()).clearSelection();
        if (this.picturePath == null && this.gifPath == null) {
            onVideoPreviewClicked();
        } else {
            onImagePreviewClicked();
        }
    }

    private final void onImagePreviewClicked() {
        HomewavRouter homewavRouter = this.router;
        String str = this.picturePath;
        if (str == null && (str = this.gifPath) == null) {
            Intrinsics.throwNpe();
        }
        homewavRouter.navigateTo(new Screens.ImageViewScreen(str));
    }

    private final void onVideoPreviewClicked() {
        String str = this.videoPath;
        if (str == null) {
            str = new File(this.filesDir, "message.mp4").getAbsolutePath();
        } else if (str == null) {
            Intrinsics.throwNpe();
        }
        HomewavRouter homewavRouter = this.router;
        Intrinsics.checkExpressionValueIsNotNull(str, "streamUrl");
        homewavRouter.navigateTo(new Screens.VideoViewScreen(str));
    }

    public final void onCloseClicked() {
        String str = null;
        this.pubId = str;
        this.streamName = str;
        this.protocol = null;
        this.picturePath = str;
        this.gifPath = str;
        this.videoPath = str;
    }

    public final void onTextChanged(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        boolean z = true;
        boolean z2 = (!(str.length() > 0) && this.streamName == null && this.picturePath == null && this.videoPath == null && this.gifPath == null) ? false : true;
        ConversationView conversationView = (ConversationView) getViewState();
        if (!z2 || this.isSendingMessage) {
            z = false;
        }
        conversationView.updateSendButton(z);
    }

    public final void onBackPressed() {
        this.router.exitWithResult(0, this.lastMessage);
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new ConversationPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void onDeleteClicked(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "messages");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.deleteMessages(list)).subscribe(new ConversationPresenter$onDeleteClicked$1(this, list), ConversationPresenter$onDeleteClicked$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.deleteMessages(messa… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void loadMoreMessages() {
        if (!this.isLoading && !this.isLastPage) {
            loadMessages$default(this, false, 1, (Object) null);
        }
    }

    static /* synthetic */ void loadMessages$default(ConversationPresenter conversationPresenter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        conversationPresenter.loadMessages(z);
    }

    private final void loadMessages(boolean z) {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = HomewavApi.DefaultImpls.getLastMessages$default(this.api, this.inmateId, 0, this.currentPage, 2, (Object) null).doOnError(new ConversationPresenter$loadMessages$1(this)).doOnSubscribe(new ConversationPresenter$loadMessages$2(this)).doOnEvent(new ConversationPresenter$loadMessages$3(this)).subscribeOn(Schedulers.io()).subscribe(new ConversationPresenter$loadMessages$4(this, z), new ConversationPresenter$loadMessages$5(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getLastMessages(inma…  }, { handleError(it) })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void showMessages() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.inmate.take(1).subscribe(new ConversationPresenter$showMessages$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmate.take(1)\n         …ssages)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final Single<Response<List<Message>>> checkNewMessages() {
        return HomewavApi.DefaultImpls.getLastMessages$default(this.api, this.inmateId, 0, (Integer) null, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0032, code lost:
        r4 = r4.getStatus();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.forasoft.homewavvisitor.model.data.Message> updateStatus(java.util.List<com.forasoft.homewavvisitor.model.data.Message> r23) {
        /*
            r22 = this;
            r0 = r23
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x0015:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0066
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.forasoft.homewavvisitor.model.data.Message r3 = (com.forasoft.homewavvisitor.model.data.Message) r3
            r2 = r22
            com.forasoft.homewavvisitor.model.repository.HeartbeatRepository r4 = r2.heartbeatRepository
            io.reactivex.subjects.BehaviorSubject r4 = r4.getHeartbeatState()
            java.lang.Object r4 = r4.getValue()
            com.forasoft.homewavvisitor.model.data.State r4 = (com.forasoft.homewavvisitor.model.data.State) r4
            if (r4 == 0) goto L_0x0043
            java.util.Map r4 = r4.getStatus()
            if (r4 == 0) goto L_0x0043
            java.lang.String r5 = r3.getInmateId()
            java.lang.Object r4 = r4.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0044
        L_0x0043:
            r4 = 0
        L_0x0044:
            r19 = r4
            if (r19 == 0) goto L_0x0062
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r20 = 32767(0x7fff, float:4.5916E-41)
            r21 = 0
            com.forasoft.homewavvisitor.model.data.Message r3 = com.forasoft.homewavvisitor.model.data.Message.copy$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
        L_0x0062:
            r1.add(r3)
            goto L_0x0015
        L_0x0066:
            r2 = r22
            java.util.List r1 = (java.util.List) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter.updateStatus(java.util.List):java.util.List");
    }

    /* access modifiers changed from: private */
    public final List<Notification> filterByInmate(List<Notification> list) {
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (Intrinsics.areEqual((Object) this.inmateId, (Object) new JSONObject(((Notification) next).getBody()).optString("inmateId"))) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: private */
    public final void readMessages(List<Message> list) {
        Collection arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Message message = (Message) next;
            if (message.getSender() != Sender.INMATE || (Integer.parseInt(message.getViews()) != 0 && message.getStatus() == MessageStatus.READ)) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        Iterable<Message> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Message id : iterable) {
            arrayList2.add(id.getId());
        }
        List list2 = (List) arrayList2;
        if (!list2.isEmpty()) {
            CompositeDisposable disposables = getDisposables();
            Disposable subscribe = CommonKt.applyAsync(this.api.readMessages(list2)).subscribe(ConversationPresenter$readMessages$1.INSTANCE, ConversationPresenter$readMessages$2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.readMessages(unread)…Async().subscribe({}, {})");
            DisposableKt.plusAssign(disposables, subscribe);
            this.heartbeatRepository.doHeartbeat();
        }
    }

    /* access modifiers changed from: private */
    public final void sendEvent() {
        this.analytics.onSendMessage();
    }

    /* access modifiers changed from: private */
    public final void handleError(Throwable th) {
        String str = null;
        this.pubId = str;
        this.streamName = str;
        this.protocol = null;
        this.picturePath = str;
        this.gifPath = str;
        this.videoPath = str;
        if (th instanceof HttpException) {
            ((ConversationView) getViewState()).showMessage((int) R.string.label_server_error);
        } else if (th instanceof IOException) {
            ((ConversationView) getViewState()).showMessage((int) R.string.label_network_error);
        } else {
            ConversationView conversationView = (ConversationView) getViewState();
            String message = th.getMessage();
            if (message == null) {
                Intrinsics.throwNpe();
            }
            conversationView.showMessage(message);
        }
    }
}
