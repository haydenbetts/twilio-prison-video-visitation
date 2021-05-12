package com.forasoft.homewavvisitor.extension;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/ObservableEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
/* compiled from: context.kt */
final class ContextKt$volumeObservable$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ AudioManager $am;
    final /* synthetic */ Context $this_volumeObservable;

    ContextKt$volumeObservable$1(Context context, AudioManager audioManager) {
        this.$this_volumeObservable = context;
        this.$am = audioManager;
    }

    public final void subscribe(ObservableEmitter<Integer> observableEmitter) {
        Intrinsics.checkParameterIsNotNull(observableEmitter, "emitter");
        final ContextKt$volumeObservable$1$observer$1 contextKt$volumeObservable$1$observer$1 = new ContextKt$volumeObservable$1$observer$1(this, observableEmitter, new Handler(Looper.getMainLooper()));
        this.$this_volumeObservable.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, contextKt$volumeObservable$1$observer$1);
        observableEmitter.setDisposable(Disposables.fromAction(new Action(this) {
            final /* synthetic */ ContextKt$volumeObservable$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.$this_volumeObservable.getContentResolver().unregisterContentObserver(contextKt$volumeObservable$1$observer$1);
            }
        }));
    }
}
