package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.data.RecordVideoResult;
import com.forasoft.homewavvisitor.model.data.TakePictureResult;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.VideoRecordView;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.rxkotlin.Singles;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0014J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001bJ\u0006\u0010#\u001a\u00020\u001bJ\u0006\u0010$\u001a\u00020\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/VideoRecordPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/VideoRecordView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "inmateId", "", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Ljava/lang/String;)V", "cameraFacing", "", "canRecordVideo", "", "canTakePhoto", "maxLength", "Ljava/lang/Integer;", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "pubId", "streamName", "videoLength", "exitWithMessage", "", "message", "onBackPressed", "onFirstViewAttach", "onPictureTaken", "file", "Ljava/io/File;", "onRecordingStarted", "onStopClicked", "onSwitchCamera", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: VideoRecordPresenter.kt */
public final class VideoRecordPresenter extends BasePresenter<VideoRecordView> {
    private final HomewavApi api;
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public int cameraFacing = 1;
    /* access modifiers changed from: private */
    public boolean canRecordVideo;
    /* access modifiers changed from: private */
    public boolean canTakePhoto;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final String inmateId;
    /* access modifiers changed from: private */
    public Integer maxLength;
    /* access modifiers changed from: private */
    public Protocol protocol;
    /* access modifiers changed from: private */
    public String pubId;
    private final HomewavRouter router;
    /* access modifiers changed from: private */
    public String streamName;
    /* access modifiers changed from: private */
    public int videoLength;

    @Inject
    public VideoRecordPresenter(HomewavRouter homewavRouter, HomewavApi homewavApi, InmateDao inmateDao2, AuthHolder authHolder2, @InmateId String str) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        this.router = homewavRouter;
        this.api = homewavApi;
        this.inmateDao = inmateDao2;
        this.authHolder = authHolder2;
        this.inmateId = str;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Singles singles = Singles.INSTANCE;
        SingleSource inmate = this.inmateDao.getInmate(this.inmateId);
        HomewavApi homewavApi = this.api;
        User user = this.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Single<R> map = homewavApi.initMessage(visitor_id, this.inmateId).map(VideoRecordPresenter$onFirstViewAttach$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "api.initMessage(authHold…inmateId).map { it.body }");
        Single zip = Single.zip(inmate, map, new VideoRecordPresenter$onFirstViewAttach$$inlined$zip$1());
        Intrinsics.checkExpressionValueIsNotNull(zip, "Single.zip(s1, s2, BiFun…-> zipper.invoke(t, u) })");
        Disposable subscribe = CommonKt.applyAsync(zip).subscribe(new VideoRecordPresenter$onFirstViewAttach$3(this), new VideoRecordPresenter$onFirstViewAttach$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Singles.zip(\n           …     }\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void exitWithMessage(String str) {
        ((VideoRecordView) getViewState()).showMessage(str);
        this.router.exit();
    }

    public final void onStopClicked() {
        HomewavRouter homewavRouter = this.router;
        String str = this.pubId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.streamName;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        Protocol protocol2 = this.protocol;
        if (protocol2 == null) {
            Intrinsics.throwNpe();
        }
        homewavRouter.exitWithResult(1, new RecordVideoResult(str, str2, protocol2, this.videoLength));
    }

    public final void onRecordingStarted() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new VideoRecordPresenter$onRecordingStarted$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0, 1…oInt())\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Integer num = this.maxLength;
        if (num == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe2 = Observable.timer((long) num.intValue(), TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new VideoRecordPresenter$onRecordingStarted$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "Observable.timer(maxLeng…cribe { onStopClicked() }");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    public final void onPictureTaken(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        HomewavRouter homewavRouter = this.router;
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
        homewavRouter.exitWithResult(1, new TakePictureResult(absolutePath));
    }

    public final void onSwitchCamera() {
        int i = 1;
        if (this.cameraFacing == 1) {
            i = 0;
        }
        this.cameraFacing = i;
        if (this.protocol != null) {
            ((VideoRecordView) getViewState()).recordMp4Video(this.cameraFacing, this.canRecordVideo, this.canTakePhoto);
        }
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
