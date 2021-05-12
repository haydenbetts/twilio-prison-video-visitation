package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.presentation.view.visits.InmateChooserView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateChooserPresenter.kt */
final class InmateChooserPresenter$onFirstViewAttach$1<T> implements Consumer<List<? extends Inmate>> {
    final /* synthetic */ InmateChooserPresenter this$0;

    InmateChooserPresenter$onFirstViewAttach$1(InmateChooserPresenter inmateChooserPresenter) {
        this.this$0 = inmateChooserPresenter;
    }

    public final void accept(final List<Inmate> list) {
        CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
        Disposable subscribe = this.this$0.heartbeatRepository.getHeartbeatState().subscribe(new Consumer<State>(this) {
            final /* synthetic */ InmateChooserPresenter$onFirstViewAttach$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(State state) {
                Map<String, String> status = state != null ? state.getStatus() : null;
                List list = list;
                Intrinsics.checkExpressionValueIsNotNull(list, "it");
                Iterable<Inmate> iterable = list;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Inmate inmate : iterable) {
                    String str = status != null ? status.get(inmate.getId()) : null;
                    if (str != null) {
                        inmate = Inmate.copy$default(inmate, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, str, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null);
                    }
                    arrayList.add(inmate);
                }
                ((InmateChooserView) this.this$0.this$0.getViewState()).displayInmates((List) arrayList);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…                        }");
        DisposableKt.plusAssign(access$getDisposables$p, subscribe);
    }
}
