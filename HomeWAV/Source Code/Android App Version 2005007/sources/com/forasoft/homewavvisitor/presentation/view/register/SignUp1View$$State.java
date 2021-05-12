package com.forasoft.homewavvisitor.presentation.view.register;

import android.net.Uri;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class SignUp1View$$State extends MvpViewState<SignUp1View> implements SignUp1View {
    public void showProfileImage(Uri uri) {
        ShowProfileImageCommand showProfileImageCommand = new ShowProfileImageCommand(uri);
        this.viewCommands.beforeApply(showProfileImageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showProfileImage : this.views) {
                showProfileImage.showProfileImage(uri);
            }
            this.viewCommands.afterApply(showProfileImageCommand);
        }
    }

    public void showPhotoIdImage(Uri uri) {
        ShowPhotoIdImageCommand showPhotoIdImageCommand = new ShowPhotoIdImageCommand(uri);
        this.viewCommands.beforeApply(showPhotoIdImageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showPhotoIdImage : this.views) {
                showPhotoIdImage.showPhotoIdImage(uri);
            }
            this.viewCommands.afterApply(showPhotoIdImageCommand);
        }
    }

    public void activateNextButton() {
        ActivateNextButtonCommand activateNextButtonCommand = new ActivateNextButtonCommand();
        this.viewCommands.beforeApply(activateNextButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View activateNextButton : this.views) {
                activateNextButton.activateNextButton();
            }
            this.viewCommands.afterApply(activateNextButtonCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void hideProfileProgress() {
        HideProfileProgressCommand hideProfileProgressCommand = new HideProfileProgressCommand();
        this.viewCommands.beforeApply(hideProfileProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View hideProfileProgress : this.views) {
                hideProfileProgress.hideProfileProgress();
            }
            this.viewCommands.afterApply(hideProfileProgressCommand);
        }
    }

    public void showProfileProgress() {
        ShowProfileProgressCommand showProfileProgressCommand = new ShowProfileProgressCommand();
        this.viewCommands.beforeApply(showProfileProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showProfileProgress : this.views) {
                showProfileProgress.showProfileProgress();
            }
            this.viewCommands.afterApply(showProfileProgressCommand);
        }
    }

    public void blockNextButton() {
        BlockNextButtonCommand blockNextButtonCommand = new BlockNextButtonCommand();
        this.viewCommands.beforeApply(blockNextButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View blockNextButton : this.views) {
                blockNextButton.blockNextButton();
            }
            this.viewCommands.afterApply(blockNextButtonCommand);
        }
    }

    public void hideIdPhotoProgress() {
        HideIdPhotoProgressCommand hideIdPhotoProgressCommand = new HideIdPhotoProgressCommand();
        this.viewCommands.beforeApply(hideIdPhotoProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View hideIdPhotoProgress : this.views) {
                hideIdPhotoProgress.hideIdPhotoProgress();
            }
            this.viewCommands.afterApply(hideIdPhotoProgressCommand);
        }
    }

    public void showPhotoIdProgress() {
        ShowPhotoIdProgressCommand showPhotoIdProgressCommand = new ShowPhotoIdProgressCommand();
        this.viewCommands.beforeApply(showPhotoIdProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showPhotoIdProgress : this.views) {
                showPhotoIdProgress.showPhotoIdProgress();
            }
            this.viewCommands.afterApply(showPhotoIdProgressCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (SignUp1View showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowProfileImageCommand extends ViewCommand<SignUp1View> {
        public final Uri profileImageUri;

        ShowProfileImageCommand(Uri uri) {
            super("showProfileImage", AddToEndSingleStrategy.class);
            this.profileImageUri = uri;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showProfileImage(this.profileImageUri);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowPhotoIdImageCommand extends ViewCommand<SignUp1View> {
        public final Uri imageUri;

        ShowPhotoIdImageCommand(Uri uri) {
            super("showPhotoIdImage", AddToEndSingleStrategy.class);
            this.imageUri = uri;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showPhotoIdImage(this.imageUri);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ActivateNextButtonCommand extends ViewCommand<SignUp1View> {
        ActivateNextButtonCommand() {
            super("activateNextButton", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.activateNextButton();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowProgressCommand extends ViewCommand<SignUp1View> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class HideProgressCommand extends ViewCommand<SignUp1View> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.hideProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class HideProfileProgressCommand extends ViewCommand<SignUp1View> {
        HideProfileProgressCommand() {
            super("hideProfileProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.hideProfileProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowProfileProgressCommand extends ViewCommand<SignUp1View> {
        ShowProfileProgressCommand() {
            super("showProfileProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showProfileProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class BlockNextButtonCommand extends ViewCommand<SignUp1View> {
        BlockNextButtonCommand() {
            super("blockNextButton", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.blockNextButton();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class HideIdPhotoProgressCommand extends ViewCommand<SignUp1View> {
        HideIdPhotoProgressCommand() {
            super("hideIdPhotoProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.hideIdPhotoProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowPhotoIdProgressCommand extends ViewCommand<SignUp1View> {
        ShowPhotoIdProgressCommand() {
            super("showPhotoIdProgress", AddToEndSingleStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showPhotoIdProgress();
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowMessageCommand extends ViewCommand<SignUp1View> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showMessage(this.message);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowMessage1Command extends ViewCommand<SignUp1View> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showMessage(this.resId);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<SignUp1View> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowProgress1Command extends ViewCommand<SignUp1View> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showProgress(this.show);
        }
    }

    /* compiled from: SignUp1View$$State */
    public class ShowServerErrorCommand extends ViewCommand<SignUp1View> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(SignUp1View signUp1View) {
            signUp1View.showServerError();
        }
    }
}
