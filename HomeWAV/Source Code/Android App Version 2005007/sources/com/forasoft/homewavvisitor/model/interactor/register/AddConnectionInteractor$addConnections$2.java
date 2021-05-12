package com.forasoft.homewavvisitor.model.interactor.register;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionInteractor.kt */
final class AddConnectionInteractor$addConnections$2<T> implements Consumer<Disposable> {
    public static final AddConnectionInteractor$addConnections$2 INSTANCE = new AddConnectionInteractor$addConnections$2();

    AddConnectionInteractor$addConnections$2() {
    }

    public final void accept(Disposable disposable) {
        Timber.d("on subscribe: ", new Object[0]);
    }
}
