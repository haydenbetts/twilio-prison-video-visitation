package com.forasoft.homewavvisitor.presentation.view.conversations;

import com.forasoft.homewavvisitor.model.data.Chat;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class ConversationsView$$State extends MvpViewState<ConversationsView> implements ConversationsView {
    public void displayConversations(List<Chat> list) {
        DisplayConversationsCommand displayConversationsCommand = new DisplayConversationsCommand(list);
        this.viewCommands.beforeApply(displayConversationsCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView displayConversations : this.views) {
                displayConversations.displayConversations(list);
            }
            this.viewCommands.afterApply(displayConversationsCommand);
        }
    }

    public void showSuccessfulDeletionMessage() {
        ShowSuccessfulDeletionMessageCommand showSuccessfulDeletionMessageCommand = new ShowSuccessfulDeletionMessageCommand();
        this.viewCommands.beforeApply(showSuccessfulDeletionMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView showSuccessfulDeletionMessage : this.views) {
                showSuccessfulDeletionMessage.showSuccessfulDeletionMessage();
            }
            this.viewCommands.afterApply(showSuccessfulDeletionMessageCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationsView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    /* compiled from: ConversationsView$$State */
    public class DisplayConversationsCommand extends ViewCommand<ConversationsView> {
        public final List<Chat> chats;

        DisplayConversationsCommand(List<Chat> list) {
            super("displayConversations", AddToEndSingleStrategy.class);
            this.chats = list;
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.displayConversations(this.chats);
        }
    }

    /* compiled from: ConversationsView$$State */
    public class ShowSuccessfulDeletionMessageCommand extends ViewCommand<ConversationsView> {
        ShowSuccessfulDeletionMessageCommand() {
            super("showSuccessfulDeletionMessage", OneExecutionStateStrategy.class);
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.showSuccessfulDeletionMessage();
        }
    }

    /* compiled from: ConversationsView$$State */
    public class ShowServerErrorCommand extends ViewCommand<ConversationsView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.showServerError();
        }
    }

    /* compiled from: ConversationsView$$State */
    public class ShowMessageCommand extends ViewCommand<ConversationsView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.showMessage(this.message);
        }
    }

    /* compiled from: ConversationsView$$State */
    public class ShowMessage1Command extends ViewCommand<ConversationsView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.showMessage(this.resId);
        }
    }

    /* compiled from: ConversationsView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<ConversationsView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: ConversationsView$$State */
    public class ShowProgressCommand extends ViewCommand<ConversationsView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(ConversationsView conversationsView) {
            conversationsView.showProgress(this.show);
        }
    }
}
