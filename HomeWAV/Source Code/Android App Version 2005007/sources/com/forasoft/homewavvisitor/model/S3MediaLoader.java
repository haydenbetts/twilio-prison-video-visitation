package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/S3MediaLoader;", "Lcom/forasoft/homewavvisitor/model/MediaLoader;", "inmateId", "", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Ljava/lang/String;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "getImages", "Lio/reactivex/Single;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "getVideos", "loadMedia", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
public final class S3MediaLoader implements MediaLoader {
    /* access modifiers changed from: private */
    public final HomewavApi api;
    private final InmateDao inmateDao;
    private final String inmateId;

    @Inject
    public S3MediaLoader(@InmateId String str, InmateDao inmateDao2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.inmateId = str;
        this.inmateDao = inmateDao2;
        this.api = homewavApi;
    }

    public Single<List<Media>> loadMedia() {
        Single<R> flatMap = this.inmateDao.getInmate(this.inmateId).flatMap(new S3MediaLoader$loadMedia$1(this)).flatMap(new S3MediaLoader$loadMedia$2(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "inmateDao.getInmate(inma…      }\n                }");
        return flatMap;
    }

    /* access modifiers changed from: private */
    public final Single<List<Media>> getVideos() {
        Single<R> map = this.api.getVideos().flatMap(S3MediaLoader$getVideos$1.INSTANCE).map(S3MediaLoader$getVideos$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "api.getVideos()\n        …o(it, \"video/mp4\", 0) } }");
        return map;
    }

    /* access modifiers changed from: private */
    public final Single<List<Media>> getImages() {
        Single<R> map = this.api.getImages().flatMap(S3MediaLoader$getImages$1.INSTANCE).map(S3MediaLoader$getImages$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "api.getImages()\n        …          }\n            }");
        return map;
    }
}
