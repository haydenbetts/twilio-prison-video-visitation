package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/register/AddConnectionView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "hideAddConnectionLink", "", "setConnectionsAdapter", "connectionAdapter", "Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter;", "setInmatesAutocomplete", "connectionPosition", "", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showEditPhoto", "requirePhotoId", "", "hasPhotoId", "showFacilityAgreement", "agreementUrl", "", "showNextButton", "showSkipButton", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: AddConnectionView.kt */
public interface AddConnectionView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: AddConnectionView.kt */
    public static final class DefaultImpls {
        public static void showEditPhoto(AddConnectionView addConnectionView, boolean z, boolean z2) {
        }

        public static void showProgress(AddConnectionView addConnectionView, boolean z) {
            BaseView.DefaultImpls.showProgress(addConnectionView, z);
        }

        public static void updateNotificationMenu(AddConnectionView addConnectionView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(addConnectionView, i);
        }
    }

    void hideAddConnectionLink();

    void setConnectionsAdapter(AddConnectionAdapter addConnectionAdapter);

    void setInmatesAutocomplete(int i, List<Inmate> list);

    void showEditPhoto(boolean z, boolean z2);

    void showFacilityAgreement(String str);

    void showNextButton();

    void showSkipButton();
}
