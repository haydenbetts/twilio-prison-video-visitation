package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.navigation.BusEvent;
import com.forasoft.homewavvisitor.navigation.BusListener;
import com.forasoft.homewavvisitor.navigation.OnBottomNavigationClicked;
import com.forasoft.homewavvisitor.navigation.OnInmateAdded;
import io.reactivex.subjects.BehaviorSubject;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter$busListener$1", "Lcom/forasoft/homewavvisitor/navigation/BusListener;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/navigation/BusEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
public final class HomePresenter$busListener$1 implements BusListener {
    final /* synthetic */ HomePresenter this$0;

    HomePresenter$busListener$1(HomePresenter homePresenter) {
        this.this$0 = homePresenter;
    }

    public void onEvent(BusEvent busEvent) {
        Intrinsics.checkParameterIsNotNull(busEvent, "event");
        if (busEvent instanceof OnInmateAdded) {
            BehaviorSubject access$getInmatesSubject$p = this.this$0.inmatesSubject;
            Object value = this.this$0.inmatesSubject.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "inmatesSubject.value");
            access$getInmatesSubject$p.onNext(CollectionsKt.plus((Collection) value, ((OnInmateAdded) busEvent).getInmate()));
        }
        if (busEvent instanceof OnBottomNavigationClicked) {
            this.this$0.onTabSelected();
        }
    }
}
