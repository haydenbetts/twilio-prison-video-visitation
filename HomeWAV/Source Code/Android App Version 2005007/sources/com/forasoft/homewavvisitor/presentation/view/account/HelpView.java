package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Contacts;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.fragment.account.Question;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/HelpView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "showContacts", "", "contacts", "Lcom/forasoft/homewavvisitor/model/data/Contacts;", "showQuestions", "questions", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HelpView.kt */
public interface HelpView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: HelpView.kt */
    public static final class DefaultImpls {
        public static void showProgress(HelpView helpView, boolean z) {
            BaseView.DefaultImpls.showProgress(helpView, z);
        }

        public static void updateNotificationMenu(HelpView helpView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(helpView, i);
        }
    }

    void showContacts(Contacts contacts);

    void showQuestions(List<Question> list);
}
