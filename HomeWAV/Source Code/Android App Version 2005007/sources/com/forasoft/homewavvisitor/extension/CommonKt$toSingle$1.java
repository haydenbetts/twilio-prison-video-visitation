package com.forasoft.homewavvisitor.extension;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Cancellable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
/* compiled from: common.kt */
final class CommonKt$toSingle$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ LiveData $this_toSingle;

    CommonKt$toSingle$1(LiveData liveData) {
        this.$this_toSingle = liveData;
    }

    public final void subscribe(SingleEmitter<WorkInfo> singleEmitter) {
        Intrinsics.checkParameterIsNotNull(singleEmitter, "emitter");
        final Observer commonKt$toSingle$1$observer$1 = new CommonKt$toSingle$1$observer$1(singleEmitter);
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ CommonKt$toSingle$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.$this_toSingle.observeForever(commonKt$toSingle$1$observer$1);
            }
        });
        singleEmitter.setCancellable(new Cancellable(this) {
            final /* synthetic */ CommonKt$toSingle$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void cancel() {
                this.this$0.$this_toSingle.removeObserver(commonKt$toSingle$1$observer$1);
            }
        });
    }
}
