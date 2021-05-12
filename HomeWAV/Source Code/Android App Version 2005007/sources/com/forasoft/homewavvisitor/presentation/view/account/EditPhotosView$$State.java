package com.forasoft.homewavvisitor.presentation.view.account;

import android.net.Uri;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class EditPhotosView$$State extends MvpViewState<EditPhotosView> implements EditPhotosView {
    public void showProfileImage(Uri uri) {
        ShowProfileImageCommand showProfileImageCommand = new ShowProfileImageCommand(uri);
        this.viewCommands.beforeApply(showProfileImageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showProfileImage : this.views) {
                showProfileImage.showProfileImage(uri);
            }
            this.viewCommands.afterApply(showProfileImageCommand);
        }
    }

    public void showPhotoIdImage(Uri uri) {
        ShowPhotoIdImageCommand showPhotoIdImageCommand = new ShowPhotoIdImageCommand(uri);
        this.viewCommands.beforeApply(showPhotoIdImageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showPhotoIdImage : this.views) {
                showPhotoIdImage.showPhotoIdImage(uri);
            }
            this.viewCommands.afterApply(showPhotoIdImageCommand);
        }
    }

    public void activateNextButton() {
        ActivateNextButtonCommand activateNextButtonCommand = new ActivateNextButtonCommand();
        this.viewCommands.beforeApply(activateNextButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView activateNextButton : this.views) {
                activateNextButton.activateNextButton();
            }
            this.viewCommands.afterApply(activateNextButtonCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void hideProfileProgress() {
        HideProfileProgressCommand hideProfileProgressCommand = new HideProfileProgressCommand();
        this.viewCommands.beforeApply(hideProfileProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView hideProfileProgress : this.views) {
                hideProfileProgress.hideProfileProgress();
            }
            this.viewCommands.afterApply(hideProfileProgressCommand);
        }
    }

    public void showProfileProgress() {
        ShowProfileProgressCommand showProfileProgressCommand = new ShowProfileProgressCommand();
        this.viewCommands.beforeApply(showProfileProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showProfileProgress : this.views) {
                showProfileProgress.showProfileProgress();
            }
            this.viewCommands.afterApply(showProfileProgressCommand);
        }
    }

    public void blockNextButton() {
        BlockNextButtonCommand blockNextButtonCommand = new BlockNextButtonCommand();
        this.viewCommands.beforeApply(blockNextButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView blockNextButton : this.views) {
                blockNextButton.blockNextButton();
            }
            this.viewCommands.afterApply(blockNextButtonCommand);
        }
    }

    public void hideIdPhotoProgress() {
        HideIdPhotoProgressCommand hideIdPhotoProgressCommand = new HideIdPhotoProgressCommand();
        this.viewCommands.beforeApply(hideIdPhotoProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView hideIdPhotoProgress : this.views) {
                hideIdPhotoProgress.hideIdPhotoProgress();
            }
            this.viewCommands.afterApply(hideIdPhotoProgressCommand);
        }
    }

    public void showPhotoIdProgress() {
        ShowPhotoIdProgressCommand showPhotoIdProgressCommand = new ShowPhotoIdProgressCommand();
        this.viewCommands.beforeApply(showPhotoIdProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showPhotoIdProgress : this.views) {
                showPhotoIdProgress.showPhotoIdProgress();
            }
            this.viewCommands.afterApply(showPhotoIdProgressCommand);
        }
    }

    public void loadPhotoIdImage(String str) {
        LoadPhotoIdImageCommand loadPhotoIdImageCommand = new LoadPhotoIdImageCommand(str);
        this.viewCommands.beforeApply(loadPhotoIdImageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView loadPhotoIdImage : this.views) {
                loadPhotoIdImage.loadPhotoIdImage(str);
            }
            this.viewCommands.afterApply(loadPhotoIdImageCommand);
        }
    }

    public void loadProfileImage(String str) {
        LoadProfileImageCommand loadProfileImageCommand = new LoadProfileImageCommand(str);
        this.viewCommands.beforeApply(loadProfileImageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView loadProfileImage : this.views) {
                loadProfileImage.loadProfileImage(str);
            }
            this.viewCommands.afterApply(loadProfileImageCommand);
        }
    }

    public void showApproveDialog() {
        ShowApproveDialogCommand showApproveDialogCommand = new ShowApproveDialogCommand();
        this.viewCommands.beforeApply(showApproveDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showApproveDialog : this.views) {
                showApproveDialog.showApproveDialog();
            }
            this.viewCommands.afterApply(showApproveDialogCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (EditPhotosView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowProfileImageCommand extends ViewCommand<EditPhotosView> {
        public final Uri profileImageUri;

        ShowProfileImageCommand(Uri uri) {
            super("showProfileImage", AddToEndSingleStrategy.class);
            this.profileImageUri = uri;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showProfileImage(this.profileImageUri);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowPhotoIdImageCommand extends ViewCommand<EditPhotosView> {
        public final Uri imageUri;

        ShowPhotoIdImageCommand(Uri uri) {
            super("showPhotoIdImage", AddToEndSingleStrategy.class);
            this.imageUri = uri;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showPhotoIdImage(this.imageUri);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ActivateNextButtonCommand extends ViewCommand<EditPhotosView> {
        ActivateNextButtonCommand() {
            super("activateNextButton", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.activateNextButton();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowProgressCommand extends ViewCommand<EditPhotosView> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class HideProgressCommand extends ViewCommand<EditPhotosView> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.hideProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class HideProfileProgressCommand extends ViewCommand<EditPhotosView> {
        HideProfileProgressCommand() {
            super("hideProfileProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.hideProfileProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowProfileProgressCommand extends ViewCommand<EditPhotosView> {
        ShowProfileProgressCommand() {
            super("showProfileProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showProfileProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class BlockNextButtonCommand extends ViewCommand<EditPhotosView> {
        BlockNextButtonCommand() {
            super("blockNextButton", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.blockNextButton();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class HideIdPhotoProgressCommand extends ViewCommand<EditPhotosView> {
        HideIdPhotoProgressCommand() {
            super("hideIdPhotoProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.hideIdPhotoProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowPhotoIdProgressCommand extends ViewCommand<EditPhotosView> {
        ShowPhotoIdProgressCommand() {
            super("showPhotoIdProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showPhotoIdProgress();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class LoadPhotoIdImageCommand extends ViewCommand<EditPhotosView> {
        public final String photoIdUrl;

        LoadPhotoIdImageCommand(String str) {
            super("loadPhotoIdImage", AddToEndSingleStrategy.class);
            this.photoIdUrl = str;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.loadPhotoIdImage(this.photoIdUrl);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class LoadProfileImageCommand extends ViewCommand<EditPhotosView> {
        public final String photoProfileUrl;

        LoadProfileImageCommand(String str) {
            super("loadProfileImage", AddToEndSingleStrategy.class);
            this.photoProfileUrl = str;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.loadProfileImage(this.photoProfileUrl);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowApproveDialogCommand extends ViewCommand<EditPhotosView> {
        ShowApproveDialogCommand() {
            super("showApproveDialog", OneExecutionStateStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showApproveDialog();
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowMessageCommand extends ViewCommand<EditPhotosView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showMessage(this.message);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowMessage1Command extends ViewCommand<EditPhotosView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showMessage(this.resId);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<EditPhotosView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowProgress1Command extends ViewCommand<EditPhotosView> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showProgress(this.show);
        }
    }

    /* compiled from: EditPhotosView$$State */
    public class ShowServerErrorCommand extends ViewCommand<EditPhotosView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(EditPhotosView editPhotosView) {
            editPhotosView.showServerError();
        }
    }
}
