package com.forasoft.homewavvisitor.presentation.view.conversations;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.data.auth.User;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class ConversationView$$State extends MvpViewState<ConversationView> implements ConversationView {
    public void setInmate(Inmate inmate) {
        SetInmateCommand setInmateCommand = new SetInmateCommand(inmate);
        this.viewCommands.beforeApply(setInmateCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView inmate2 : this.views) {
                inmate2.setInmate(inmate);
            }
            this.viewCommands.afterApply(setInmateCommand);
        }
    }

    public void setupList(User user) {
        SetupListCommand setupListCommand = new SetupListCommand(user);
        this.viewCommands.beforeApply(setupListCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView conversationView : this.views) {
                conversationView.setupList(user);
            }
            this.viewCommands.afterApply(setupListCommand);
        }
    }

    public void clearSelection() {
        ClearSelectionCommand clearSelectionCommand = new ClearSelectionCommand();
        this.viewCommands.beforeApply(clearSelectionCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView clearSelection : this.views) {
                clearSelection.clearSelection();
            }
            this.viewCommands.afterApply(clearSelectionCommand);
        }
    }

    public void setMessageFilter(int i) {
        SetMessageFilterCommand setMessageFilterCommand = new SetMessageFilterCommand(i);
        this.viewCommands.beforeApply(setMessageFilterCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView messageFilter : this.views) {
                messageFilter.setMessageFilter(i);
            }
            this.viewCommands.afterApply(setMessageFilterCommand);
        }
    }

    public void displayMessages(List<? extends ChatItem> list) {
        DisplayMessagesCommand displayMessagesCommand = new DisplayMessagesCommand(list);
        this.viewCommands.beforeApply(displayMessagesCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView displayMessages : this.views) {
                displayMessages.displayMessages(list);
            }
            this.viewCommands.afterApply(displayMessagesCommand);
        }
    }

    public void updateSendButton(boolean z) {
        UpdateSendButtonCommand updateSendButtonCommand = new UpdateSendButtonCommand(z);
        this.viewCommands.beforeApply(updateSendButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView updateSendButton : this.views) {
                updateSendButton.updateSendButton(z);
            }
            this.viewCommands.afterApply(updateSendButtonCommand);
        }
    }

    public void displayRecordedVideo(Protocol protocol, int i, String str) {
        DisplayRecordedVideoCommand displayRecordedVideoCommand = new DisplayRecordedVideoCommand(protocol, i, str);
        this.viewCommands.beforeApply(displayRecordedVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView displayRecordedVideo : this.views) {
                displayRecordedVideo.displayRecordedVideo(protocol, i, str);
            }
            this.viewCommands.afterApply(displayRecordedVideoCommand);
        }
    }

    public void displayVideoFromGallery(String str) {
        DisplayVideoFromGalleryCommand displayVideoFromGalleryCommand = new DisplayVideoFromGalleryCommand(str);
        this.viewCommands.beforeApply(displayVideoFromGalleryCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView displayVideoFromGallery : this.views) {
                displayVideoFromGallery.displayVideoFromGallery(str);
            }
            this.viewCommands.afterApply(displayVideoFromGalleryCommand);
        }
    }

    public void displayTakenPicture(String str) {
        DisplayTakenPictureCommand displayTakenPictureCommand = new DisplayTakenPictureCommand(str);
        this.viewCommands.beforeApply(displayTakenPictureCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView displayTakenPicture : this.views) {
                displayTakenPicture.displayTakenPicture(str);
            }
            this.viewCommands.afterApply(displayTakenPictureCommand);
        }
    }

    public void showConfirmDialog(int i, String... strArr) {
        ShowConfirmDialogCommand showConfirmDialogCommand = new ShowConfirmDialogCommand(i, strArr);
        this.viewCommands.beforeApply(showConfirmDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showConfirmDialog : this.views) {
                showConfirmDialog.showConfirmDialog(i, strArr);
            }
            this.viewCommands.afterApply(showConfirmDialogCommand);
        }
    }

    public void clearInputs() {
        ClearInputsCommand clearInputsCommand = new ClearInputsCommand();
        this.viewCommands.beforeApply(clearInputsCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView clearInputs : this.views) {
                clearInputs.clearInputs();
            }
            this.viewCommands.afterApply(clearInputsCommand);
        }
    }

    public void showMessageProcessingError() {
        ShowMessageProcessingErrorCommand showMessageProcessingErrorCommand = new ShowMessageProcessingErrorCommand();
        this.viewCommands.beforeApply(showMessageProcessingErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showMessageProcessingError : this.views) {
                showMessageProcessingError.showMessageProcessingError();
            }
            this.viewCommands.afterApply(showMessageProcessingErrorCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    public void showWarningMessage() {
        ShowWarningMessageCommand showWarningMessageCommand = new ShowWarningMessageCommand();
        this.viewCommands.beforeApply(showWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showWarningMessage : this.views) {
                showWarningMessage.showWarningMessage();
            }
            this.viewCommands.afterApply(showWarningMessageCommand);
        }
    }

    public void hideWarningMessage() {
        HideWarningMessageCommand hideWarningMessageCommand = new HideWarningMessageCommand();
        this.viewCommands.beforeApply(hideWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView hideWarningMessage : this.views) {
                hideWarningMessage.hideWarningMessage();
            }
            this.viewCommands.afterApply(hideWarningMessageCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (ConversationView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    /* compiled from: ConversationView$$State */
    public class SetInmateCommand extends ViewCommand<ConversationView> {
        public final Inmate inmate;

        SetInmateCommand(Inmate inmate2) {
            super("setInmate", AddToEndSingleStrategy.class);
            this.inmate = inmate2;
        }

        public void apply(ConversationView conversationView) {
            conversationView.setInmate(this.inmate);
        }
    }

    /* compiled from: ConversationView$$State */
    public class SetupListCommand extends ViewCommand<ConversationView> {
        public final User user;

        SetupListCommand(User user2) {
            super("setupList", AddToEndSingleStrategy.class);
            this.user = user2;
        }

        public void apply(ConversationView conversationView) {
            conversationView.setupList(this.user);
        }
    }

    /* compiled from: ConversationView$$State */
    public class ClearSelectionCommand extends ViewCommand<ConversationView> {
        ClearSelectionCommand() {
            super("clearSelection", OneExecutionStateStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.clearSelection();
        }
    }

    /* compiled from: ConversationView$$State */
    public class SetMessageFilterCommand extends ViewCommand<ConversationView> {
        public final int maxLength;

        SetMessageFilterCommand(int i) {
            super("setMessageFilter", AddToEndSingleStrategy.class);
            this.maxLength = i;
        }

        public void apply(ConversationView conversationView) {
            conversationView.setMessageFilter(this.maxLength);
        }
    }

    /* compiled from: ConversationView$$State */
    public class DisplayMessagesCommand extends ViewCommand<ConversationView> {
        public final List<? extends ChatItem> messages;

        DisplayMessagesCommand(List<? extends ChatItem> list) {
            super("displayMessages", AddToEndSingleStrategy.class);
            this.messages = list;
        }

        public void apply(ConversationView conversationView) {
            conversationView.displayMessages(this.messages);
        }
    }

    /* compiled from: ConversationView$$State */
    public class UpdateSendButtonCommand extends ViewCommand<ConversationView> {
        public final boolean isEnabled;

        UpdateSendButtonCommand(boolean z) {
            super("updateSendButton", AddToEndSingleStrategy.class);
            this.isEnabled = z;
        }

        public void apply(ConversationView conversationView) {
            conversationView.updateSendButton(this.isEnabled);
        }
    }

    /* compiled from: ConversationView$$State */
    public class DisplayRecordedVideoCommand extends ViewCommand<ConversationView> {
        public final Protocol protocol;
        public final String streamName;
        public final int videoLength;

        DisplayRecordedVideoCommand(Protocol protocol2, int i, String str) {
            super("displayRecordedVideo", OneExecutionStateStrategy.class);
            this.protocol = protocol2;
            this.videoLength = i;
            this.streamName = str;
        }

        public void apply(ConversationView conversationView) {
            conversationView.displayRecordedVideo(this.protocol, this.videoLength, this.streamName);
        }
    }

    /* compiled from: ConversationView$$State */
    public class DisplayVideoFromGalleryCommand extends ViewCommand<ConversationView> {
        public final String uri;

        DisplayVideoFromGalleryCommand(String str) {
            super("displayVideoFromGallery", OneExecutionStateStrategy.class);
            this.uri = str;
        }

        public void apply(ConversationView conversationView) {
            conversationView.displayVideoFromGallery(this.uri);
        }
    }

    /* compiled from: ConversationView$$State */
    public class DisplayTakenPictureCommand extends ViewCommand<ConversationView> {
        public final String uri;

        DisplayTakenPictureCommand(String str) {
            super("displayTakenPicture", OneExecutionStateStrategy.class);
            this.uri = str;
        }

        public void apply(ConversationView conversationView) {
            conversationView.displayTakenPicture(this.uri);
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowConfirmDialogCommand extends ViewCommand<ConversationView> {
        public final String[] args;
        public final int message;

        ShowConfirmDialogCommand(int i, String[] strArr) {
            super("showConfirmDialog", OneExecutionStateStrategy.class);
            this.message = i;
            this.args = strArr;
        }

        public void apply(ConversationView conversationView) {
            conversationView.showConfirmDialog(this.message, this.args);
        }
    }

    /* compiled from: ConversationView$$State */
    public class ClearInputsCommand extends ViewCommand<ConversationView> {
        ClearInputsCommand() {
            super("clearInputs", OneExecutionStateStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.clearInputs();
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowMessageProcessingErrorCommand extends ViewCommand<ConversationView> {
        ShowMessageProcessingErrorCommand() {
            super("showMessageProcessingError", SkipStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.showMessageProcessingError();
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowServerErrorCommand extends ViewCommand<ConversationView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.showServerError();
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowWarningMessageCommand extends ViewCommand<ConversationView> {
        ShowWarningMessageCommand() {
            super("showWarningMessage", OneExecutionStateStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.showWarningMessage();
        }
    }

    /* compiled from: ConversationView$$State */
    public class HideWarningMessageCommand extends ViewCommand<ConversationView> {
        HideWarningMessageCommand() {
            super("hideWarningMessage", OneExecutionStateStrategy.class);
        }

        public void apply(ConversationView conversationView) {
            conversationView.hideWarningMessage();
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowMessageCommand extends ViewCommand<ConversationView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(ConversationView conversationView) {
            conversationView.showMessage(this.message);
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowMessage1Command extends ViewCommand<ConversationView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(ConversationView conversationView) {
            conversationView.showMessage(this.resId);
        }
    }

    /* compiled from: ConversationView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<ConversationView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(ConversationView conversationView) {
            conversationView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: ConversationView$$State */
    public class ShowProgressCommand extends ViewCommand<ConversationView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(ConversationView conversationView) {
            conversationView.showProgress(this.show);
        }
    }
}
