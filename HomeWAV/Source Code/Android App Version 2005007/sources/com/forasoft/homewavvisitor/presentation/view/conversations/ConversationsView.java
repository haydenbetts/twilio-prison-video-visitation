package com.forasoft.homewavvisitor.presentation.view.conversations;

import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'J\b\u0010\b\u001a\u00020\u0004H'J\b\u0010\t\u001a\u00020\u0004H'¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "displayConversations", "", "chats", "", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "showServerError", "showSuccessfulDeletionMessage", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationsView.kt */
public interface ConversationsView extends BaseView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: ConversationsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(ConversationsView conversationsView, boolean z) {
            BaseView.DefaultImpls.showProgress(conversationsView, z);
        }

        public static void updateNotificationMenu(ConversationsView conversationsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(conversationsView, i);
        }
    }

    @StateStrategyType(AddToEndSingleStrategy.class)
    void displayConversations(List<Chat> list);

    @StateStrategyType(SkipStrategy.class)
    void showServerError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessfulDeletionMessage();
}
