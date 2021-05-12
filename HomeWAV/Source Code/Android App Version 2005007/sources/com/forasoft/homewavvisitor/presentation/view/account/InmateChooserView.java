package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import java.util.List;
import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/InmateChooserView;", "Lmoxy/MvpView;", "displayInmates", "", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateChooserView.kt */
public interface InmateChooserView extends MvpView {
    void displayInmates(List<Inmate> list);
}
