package com.forasoft.homewavvisitor.model;

import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/model/MediaLoader;", "", "loadMedia", "Lio/reactivex/Single;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaLoader.kt */
public interface MediaLoader {
    Single<List<Media>> loadMedia();
}
