package com.forasoft.homewavvisitor.extension;

import android.database.ContentObserver;
import android.os.Handler;
import io.reactivex.ObservableEmitter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/extension/ContextKt$volumeObservable$1$observer$1", "Landroid/database/ContentObserver;", "onChange", "", "selfChange", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: context.kt */
public final class ContextKt$volumeObservable$1$observer$1 extends ContentObserver {
    final /* synthetic */ ObservableEmitter $emitter;
    final /* synthetic */ ContextKt$volumeObservable$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContextKt$volumeObservable$1$observer$1(ContextKt$volumeObservable$1 contextKt$volumeObservable$1, ObservableEmitter observableEmitter, Handler handler) {
        super(handler);
        this.this$0 = contextKt$volumeObservable$1;
        this.$emitter = observableEmitter;
    }

    public void onChange(boolean z) {
        this.$emitter.onNext(Integer.valueOf(this.this$0.$am.getStreamVolume(0)));
    }
}
