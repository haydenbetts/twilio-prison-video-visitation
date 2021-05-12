package com.forasoft.homewavvisitor.presentation.view.conversations;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0005\u001a\u00020\u0004H'J\u0016\u0010\u0006\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH'J$\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H'J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0010H'J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0010H'J\b\u0010\u0014\u001a\u00020\u0004H'J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u000eH&J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001cH&J+\u0010\u001d\u001a\u00020\u00042\b\b\u0001\u0010\u001e\u001a\u00020\u000e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100 \"\u00020\u0010H'¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0004H'J\b\u0010#\u001a\u00020\u0004H'J\b\u0010$\u001a\u00020\u0004H'J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'H&¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "clearInputs", "", "clearSelection", "displayMessages", "messages", "", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "displayRecordedVideo", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "videoLength", "", "streamName", "", "displayTakenPicture", "uri", "displayVideoFromGallery", "hideWarningMessage", "setInmate", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "setMessageFilter", "maxLength", "setupList", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "showConfirmDialog", "message", "args", "", "(I[Ljava/lang/String;)V", "showMessageProcessingError", "showServerError", "showWarningMessage", "updateSendButton", "isEnabled", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationView.kt */
public interface ConversationView extends BaseView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: ConversationView.kt */
    public static final class DefaultImpls {
        public static void showProgress(ConversationView conversationView, boolean z) {
            BaseView.DefaultImpls.showProgress(conversationView, z);
        }

        public static void updateNotificationMenu(ConversationView conversationView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(conversationView, i);
        }
    }

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearInputs();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearSelection();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void displayMessages(List<? extends ChatItem> list);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void displayRecordedVideo(Protocol protocol, int i, String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void displayTakenPicture(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void displayVideoFromGallery(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideWarningMessage();

    void setInmate(Inmate inmate);

    void setMessageFilter(int i);

    void setupList(User user);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showConfirmDialog(int i, String... strArr);

    @StateStrategyType(SkipStrategy.class)
    void showMessageProcessingError();

    @StateStrategyType(SkipStrategy.class)
    void showServerError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showWarningMessage();

    void updateSendButton(boolean z);
}
