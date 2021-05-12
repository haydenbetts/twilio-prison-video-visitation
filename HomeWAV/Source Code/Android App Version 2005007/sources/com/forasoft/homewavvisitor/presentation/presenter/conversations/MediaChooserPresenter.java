package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import android.net.Uri;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Image;
import com.forasoft.homewavvisitor.model.Media;
import com.forasoft.homewavvisitor.model.MediaLoader;
import com.forasoft.homewavvisitor.model.Video;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.TakeGifResult;
import com.forasoft.homewavvisitor.model.data.TakePictureResult;
import com.forasoft.homewavvisitor.model.data.TakeVideoResult;
import com.forasoft.homewavvisitor.model.repository.ImagesRepository;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.MediaChooserView;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0014J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/MediaChooserPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/MediaChooserView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "inmateId", "", "mediaLoader", "Lcom/forasoft/homewavvisitor/model/MediaLoader;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "imagesRepository", "Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/MediaLoader;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;)V", "inmate", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getPath", "uri", "onBackPressed", "", "onFirstViewAttach", "onSendClicked", "media", "Lcom/forasoft/homewavvisitor/model/Media;", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: MediaChooserPresenter.kt */
public final class MediaChooserPresenter extends BasePresenter<MediaChooserView> {
    private final ImagesRepository imagesRepository;
    private final BehaviorSubject<Inmate> inmate;
    private final InmateDao inmateDao;
    private final String inmateId;
    private final MediaLoader mediaLoader;
    private final HomewavRouter router;

    @Inject
    public MediaChooserPresenter(HomewavRouter homewavRouter, @InmateId String str, MediaLoader mediaLoader2, InmateDao inmateDao2, ImagesRepository imagesRepository2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(mediaLoader2, "mediaLoader");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(imagesRepository2, "imagesRepository");
        this.router = homewavRouter;
        this.inmateId = str;
        this.mediaLoader = mediaLoader2;
        this.inmateDao = inmateDao2;
        this.imagesRepository = imagesRepository2;
        BehaviorSubject<Inmate> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.inmate = create;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.mediaLoader.loadMedia()).subscribe(new MediaChooserPresenter$sam$io_reactivex_functions_Consumer$0(new MediaChooserPresenter$onFirstViewAttach$1((MediaChooserView) getViewState())), MediaChooserPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "mediaLoader.loadMedia()\n…viewState::showMedia, {})");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = CommonKt.applyAsync(this.inmateDao.getInmate(this.inmateId)).subscribe(new MediaChooserPresenter$sam$io_reactivex_functions_Consumer$0(new MediaChooserPresenter$onFirstViewAttach$3(this.inmate)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "inmateDao.getInmate(inma…subscribe(inmate::onNext)");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    public final void onSendClicked(Media media) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        if (media instanceof Image) {
            Image image = (Image) media;
            if (Intrinsics.areEqual((Object) image.getMimeType(), (Object) "image/gif")) {
                this.router.exitWithResult(1, new TakeGifResult(image.getUri()));
                return;
            }
            ImagesRepository imagesRepository2 = this.imagesRepository;
            Uri parse = Uri.parse(image.getUri());
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(media.uri)");
            String uri = ImagesRepository.getScaledImage$default(imagesRepository2, parse, 0, 0, 6, (Object) null).toString();
            Intrinsics.checkExpressionValueIsNotNull(uri, "uri.toString()");
            this.router.exitWithResult(1, new TakePictureResult(getPath(uri)));
        } else if (media instanceof Video) {
            Video video = (Video) media;
            this.router.exitWithResult(1, new TakeVideoResult(video.getUri(), (int) (video.getDuration() / ((long) 1000))));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private final String getPath(String str) {
        if (!StringsKt.startsWith$default(str, "file://", false, 2, (Object) null) || str.length() <= 7) {
            String decode = Uri.decode(str);
            Intrinsics.checkExpressionValueIsNotNull(decode, "Uri.decode(uri)");
            return decode;
        } else if (str != null) {
            String substring = str.substring(7);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            String decode2 = Uri.decode(substring);
            Intrinsics.checkExpressionValueIsNotNull(decode2, "Uri.decode(uri.substring(7))");
            return decode2;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
