package com.forasoft.homewavvisitor.model;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/model/MediaStoreMediaLoader;", "Lcom/forasoft/homewavvisitor/model/MediaLoader;", "context", "Landroid/content/Context;", "inmateId", "", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "(Landroid/content/Context;Ljava/lang/String;Lcom/forasoft/homewavvisitor/dao/InmateDao;)V", "getImages", "Lio/reactivex/Observable;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "getVideos", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "loadMedia", "Lio/reactivex/Single;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
public final class MediaStoreMediaLoader implements MediaLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String VIDEO_MP4 = "video/mp4";
    /* access modifiers changed from: private */
    public final Context context;
    private final InmateDao inmateDao;
    private final String inmateId;

    @Inject
    public MediaStoreMediaLoader(Context context2, @InmateId String str, InmateDao inmateDao2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        this.context = context2;
        this.inmateId = str;
        this.inmateDao = inmateDao2;
    }

    public Single<List<Media>> loadMedia() {
        Single<R> flatMap = this.inmateDao.getInmate(this.inmateId).flatMap(new MediaStoreMediaLoader$loadMedia$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "inmateDao.getInmate(inma…      }\n                }");
        return flatMap;
    }

    /* access modifiers changed from: private */
    public final Observable<List<Media>> getVideos(Inmate inmate) {
        Observable<List<Media>> fromCallable = Observable.fromCallable(new MediaStoreMediaLoader$getVideos$1(this, inmate));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Observable.fromCallable …e_length.toLong() }\n    }");
        return fromCallable;
    }

    /* access modifiers changed from: private */
    public final Observable<List<Media>> getImages() {
        Observable<List<Media>> fromCallable = Observable.fromCallable(new MediaStoreMediaLoader$getImages$1(this));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Observable.fromCallable …(it)).toString()) }\n    }");
        return fromCallable;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/model/MediaStoreMediaLoader$Companion;", "", "()V", "VIDEO_MP4", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MediaStoreMediaLoader.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
