package com.forasoft.homewavvisitor.presentation.view.register;

import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class SignUp2View$$State extends MvpViewState<SignUp2View> implements SignUp2View {
    public void showSateAbbreviation(String str) {
        ShowSateAbbreviationCommand showSateAbbreviationCommand = new ShowSateAbbreviationCommand(str);
        this.viewCommands.beforeApply(showSateAbbreviationCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showSateAbbreviation : this.views) {
                showSateAbbreviation.showSateAbbreviation(str);
            }
            this.viewCommands.afterApply(showSateAbbreviationCommand);
        }
    }

    public void showPickStateDialog(List<String> list) {
        ShowPickStateDialogCommand showPickStateDialogCommand = new ShowPickStateDialogCommand(list);
        this.viewCommands.beforeApply(showPickStateDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showPickStateDialog : this.views) {
                showPickStateDialog.showPickStateDialog(list);
            }
            this.viewCommands.afterApply(showPickStateDialogCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void showDatePickDialog(int i, int i2, int i3) {
        ShowDatePickDialogCommand showDatePickDialogCommand = new ShowDatePickDialogCommand(i, i2, i3);
        this.viewCommands.beforeApply(showDatePickDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showDatePickDialog : this.views) {
                showDatePickDialog.showDatePickDialog(i, i2, i3);
            }
            this.viewCommands.afterApply(showDatePickDialogCommand);
        }
    }

    public void showDate(String str) {
        ShowDateCommand showDateCommand = new ShowDateCommand(str);
        this.viewCommands.beforeApply(showDateCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showDate : this.views) {
                showDate.showDate(str);
            }
            this.viewCommands.afterApply(showDateCommand);
        }
    }

    public void showFieldError(String str, String str2) {
        ShowFieldErrorCommand showFieldErrorCommand = new ShowFieldErrorCommand(str, str2);
        this.viewCommands.beforeApply(showFieldErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showFieldError : this.views) {
                showFieldError.showFieldError(str, str2);
            }
            this.viewCommands.afterApply(showFieldErrorCommand);
        }
    }

    public void clearErrors() {
        ClearErrorsCommand clearErrorsCommand = new ClearErrorsCommand();
        this.viewCommands.beforeApply(clearErrorsCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View clearErrors : this.views) {
                clearErrors.clearErrors();
            }
            this.viewCommands.afterApply(clearErrorsCommand);
        }
    }

    public void scrollToError(String str) {
        ScrollToErrorCommand scrollToErrorCommand = new ScrollToErrorCommand(str);
        this.viewCommands.beforeApply(scrollToErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View scrollToError : this.views) {
                scrollToError.scrollToError(str);
            }
            this.viewCommands.afterApply(scrollToErrorCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp2View showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowSateAbbreviationCommand extends ViewCommand<SignUp2View> {
        public final String abbreviation;

        ShowSateAbbreviationCommand(String str) {
            super("showSateAbbreviation", AddToEndSingleStrategy.class);
            this.abbreviation = str;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showSateAbbreviation(this.abbreviation);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowPickStateDialogCommand extends ViewCommand<SignUp2View> {
        public final List<String> statesList;

        ShowPickStateDialogCommand(List<String> list) {
            super("showPickStateDialog", SkipStrategy.class);
            this.statesList = list;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showPickStateDialog(this.statesList);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowProgressCommand extends ViewCommand<SignUp2View> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showProgress();
        }
    }

    /* compiled from: SignUp2View$$State */
    public class HideProgressCommand extends ViewCommand<SignUp2View> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.hideProgress();
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowDatePickDialogCommand extends ViewCommand<SignUp2View> {
        public final int day;
        public final int month;
        public final int year;

        ShowDatePickDialogCommand(int i, int i2, int i3) {
            super("showDatePickDialog", SkipStrategy.class);
            this.day = i;
            this.month = i2;
            this.year = i3;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showDatePickDialog(this.day, this.month, this.year);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowDateCommand extends ViewCommand<SignUp2View> {
        public final String formatedDate;

        ShowDateCommand(String str) {
            super("showDate", AddToEndSingleStrategy.class);
            this.formatedDate = str;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showDate(this.formatedDate);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowFieldErrorCommand extends ViewCommand<SignUp2View> {
        public final String field;
        public final String message;

        ShowFieldErrorCommand(String str, String str2) {
            super("showFieldError", AddToEndSingleStrategy.class);
            this.field = str;
            this.message = str2;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showFieldError(this.field, this.message);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ClearErrorsCommand extends ViewCommand<SignUp2View> {
        ClearErrorsCommand() {
            super("clearErrors", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.clearErrors();
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ScrollToErrorCommand extends ViewCommand<SignUp2View> {
        public final String field;

        ScrollToErrorCommand(String str) {
            super("scrollToError", AddToEndSingleStrategy.class);
            this.field = str;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.scrollToError(this.field);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowMessageCommand extends ViewCommand<SignUp2View> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showMessage(this.message);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowMessage1Command extends ViewCommand<SignUp2View> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showMessage(this.resId);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<SignUp2View> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowProgress1Command extends ViewCommand<SignUp2View> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showProgress(this.show);
        }
    }

    /* compiled from: SignUp2View$$State */
    public class ShowServerErrorCommand extends ViewCommand<SignUp2View> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(SignUp2View signUp2View) {
            signUp2View.showServerError();
        }
    }
}
