package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Contacts;
import com.forasoft.homewavvisitor.ui.fragment.account.Question;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class HelpView$$State extends MvpViewState<HelpView> implements HelpView {
    public void showQuestions(List<Question> list) {
        ShowQuestionsCommand showQuestionsCommand = new ShowQuestionsCommand(list);
        this.viewCommands.beforeApply(showQuestionsCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView showQuestions : this.views) {
                showQuestions.showQuestions(list);
            }
            this.viewCommands.afterApply(showQuestionsCommand);
        }
    }

    public void showContacts(Contacts contacts) {
        ShowContactsCommand showContactsCommand = new ShowContactsCommand(contacts);
        this.viewCommands.beforeApply(showContactsCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView showContacts : this.views) {
                showContacts.showContacts(contacts);
            }
            this.viewCommands.afterApply(showContactsCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (HelpView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (HelpView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowQuestionsCommand extends ViewCommand<HelpView> {
        public final List<Question> questions;

        ShowQuestionsCommand(List<Question> list) {
            super("showQuestions", AddToEndSingleStrategy.class);
            this.questions = list;
        }

        public void apply(HelpView helpView) {
            helpView.showQuestions(this.questions);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowContactsCommand extends ViewCommand<HelpView> {
        public final Contacts contacts;

        ShowContactsCommand(Contacts contacts2) {
            super("showContacts", AddToEndSingleStrategy.class);
            this.contacts = contacts2;
        }

        public void apply(HelpView helpView) {
            helpView.showContacts(this.contacts);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowMessageCommand extends ViewCommand<HelpView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(HelpView helpView) {
            helpView.showMessage(this.message);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowMessage1Command extends ViewCommand<HelpView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(HelpView helpView) {
            helpView.showMessage(this.resId);
        }
    }

    /* compiled from: HelpView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<HelpView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(HelpView helpView) {
            helpView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowProgressCommand extends ViewCommand<HelpView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(HelpView helpView) {
            helpView.showProgress(this.show);
        }
    }

    /* compiled from: HelpView$$State */
    public class ShowServerErrorCommand extends ViewCommand<HelpView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(HelpView helpView) {
            helpView.showServerError();
        }
    }
}
