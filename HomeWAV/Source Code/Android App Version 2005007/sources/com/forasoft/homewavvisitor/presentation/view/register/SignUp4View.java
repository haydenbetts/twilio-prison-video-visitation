package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp4View;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "hideProgress", "", "showConnections", "connections", "", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "showProgress", "showSuccessMessage", "showTotalBalance", "balance", "", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: SignUp4View.kt */
public interface SignUp4View extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SignUp4View.kt */
    public static final class DefaultImpls {
        public static void showProgress(SignUp4View signUp4View, boolean z) {
            BaseView.DefaultImpls.showProgress(signUp4View, z);
        }

        public static void updateNotificationMenu(SignUp4View signUp4View, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(signUp4View, i);
        }
    }

    void hideProgress();

    void showConnections(List<InmateByVisitor> list);

    void showProgress();

    void showSuccessMessage();

    void showTotalBalance(double d);
}
