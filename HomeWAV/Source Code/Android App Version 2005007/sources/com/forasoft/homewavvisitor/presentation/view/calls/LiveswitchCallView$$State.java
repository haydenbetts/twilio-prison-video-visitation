package com.forasoft.homewavvisitor.presentation.view.calls;

import com.forasoft.homewavvisitor.model.liveswitch.CameraLocalMedia;
import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class LiveswitchCallView$$State extends MvpViewState<LiveswitchCallView> implements LiveswitchCallView {
    public void displayInmateVideo(RemoteMedia remoteMedia) {
        DisplayInmateVideoCommand displayInmateVideoCommand = new DisplayInmateVideoCommand(remoteMedia);
        this.viewCommands.beforeApply(displayInmateVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView displayInmateVideo : this.views) {
                displayInmateVideo.displayInmateVideo(remoteMedia);
            }
            this.viewCommands.afterApply(displayInmateVideoCommand);
        }
    }

    public void removeInmateVideo(String str) {
        RemoveInmateVideoCommand removeInmateVideoCommand = new RemoveInmateVideoCommand(str);
        this.viewCommands.beforeApply(removeInmateVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView removeInmateVideo : this.views) {
                removeInmateVideo.removeInmateVideo(str);
            }
            this.viewCommands.afterApply(removeInmateVideoCommand);
        }
    }

    public void displayVisitorVideo(CameraLocalMedia cameraLocalMedia) {
        DisplayVisitorVideoCommand displayVisitorVideoCommand = new DisplayVisitorVideoCommand(cameraLocalMedia);
        this.viewCommands.beforeApply(displayVisitorVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView displayVisitorVideo : this.views) {
                displayVisitorVideo.displayVisitorVideo(cameraLocalMedia);
            }
            this.viewCommands.afterApply(displayVisitorVideoCommand);
        }
    }

    public void removeVisitorVideo(String str) {
        RemoveVisitorVideoCommand removeVisitorVideoCommand = new RemoveVisitorVideoCommand(str);
        this.viewCommands.beforeApply(removeVisitorVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView removeVisitorVideo : this.views) {
                removeVisitorVideo.removeVisitorVideo(str);
            }
            this.viewCommands.afterApply(removeVisitorVideoCommand);
        }
    }

    public void updateRemainingTime(long j) {
        UpdateRemainingTimeCommand updateRemainingTimeCommand = new UpdateRemainingTimeCommand(j);
        this.viewCommands.beforeApply(updateRemainingTimeCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView updateRemainingTime : this.views) {
                updateRemainingTime.updateRemainingTime(j);
            }
            this.viewCommands.afterApply(updateRemainingTimeCommand);
        }
    }

    public void showConnectionState(int i) {
        ShowConnectionStateCommand showConnectionStateCommand = new ShowConnectionStateCommand(i);
        this.viewCommands.beforeApply(showConnectionStateCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showConnectionState : this.views) {
                showConnectionState.showConnectionState(i);
            }
            this.viewCommands.afterApply(showConnectionStateCommand);
        }
    }

    public void showReportBugButton() {
        ShowReportBugButtonCommand showReportBugButtonCommand = new ShowReportBugButtonCommand();
        this.viewCommands.beforeApply(showReportBugButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showReportBugButton : this.views) {
                showReportBugButton.showReportBugButton();
            }
            this.viewCommands.afterApply(showReportBugButtonCommand);
        }
    }

    public void setRecordingWarningText(int i, int i2) {
        SetRecordingWarningTextCommand setRecordingWarningTextCommand = new SetRecordingWarningTextCommand(i, i2);
        this.viewCommands.beforeApply(setRecordingWarningTextCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView recordingWarningText : this.views) {
                recordingWarningText.setRecordingWarningText(i, i2);
            }
            this.viewCommands.afterApply(setRecordingWarningTextCommand);
        }
    }

    public void showAdminMessage(String str) {
        ShowAdminMessageCommand showAdminMessageCommand = new ShowAdminMessageCommand(str);
        this.viewCommands.beforeApply(showAdminMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showAdminMessage : this.views) {
                showAdminMessage.showAdminMessage(str);
            }
            this.viewCommands.afterApply(showAdminMessageCommand);
        }
    }

    public void showWarningMessage() {
        ShowWarningMessageCommand showWarningMessageCommand = new ShowWarningMessageCommand();
        this.viewCommands.beforeApply(showWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showWarningMessage : this.views) {
                showWarningMessage.showWarningMessage();
            }
            this.viewCommands.afterApply(showWarningMessageCommand);
        }
    }

    public void hideWarningMessage() {
        HideWarningMessageCommand hideWarningMessageCommand = new HideWarningMessageCommand();
        this.viewCommands.beforeApply(hideWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView hideWarningMessage : this.views) {
                hideWarningMessage.hideWarningMessage();
            }
            this.viewCommands.afterApply(hideWarningMessageCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (LiveswitchCallView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class DisplayInmateVideoCommand extends ViewCommand<LiveswitchCallView> {
        public final RemoteMedia remoteMedia;

        DisplayInmateVideoCommand(RemoteMedia remoteMedia2) {
            super("displayInmateVideo", AddToEndSingleStrategy.class);
            this.remoteMedia = remoteMedia2;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.displayInmateVideo(this.remoteMedia);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class RemoveInmateVideoCommand extends ViewCommand<LiveswitchCallView> {
        public final String mediaId;

        RemoveInmateVideoCommand(String str) {
            super("removeInmateVideo", AddToEndSingleStrategy.class);
            this.mediaId = str;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.removeInmateVideo(this.mediaId);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class DisplayVisitorVideoCommand extends ViewCommand<LiveswitchCallView> {
        public final CameraLocalMedia localMedia;

        DisplayVisitorVideoCommand(CameraLocalMedia cameraLocalMedia) {
            super("displayVisitorVideo", AddToEndSingleStrategy.class);
            this.localMedia = cameraLocalMedia;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.displayVisitorVideo(this.localMedia);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class RemoveVisitorVideoCommand extends ViewCommand<LiveswitchCallView> {
        public final String mediaId;

        RemoveVisitorVideoCommand(String str) {
            super("removeVisitorVideo", AddToEndSingleStrategy.class);
            this.mediaId = str;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.removeVisitorVideo(this.mediaId);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class UpdateRemainingTimeCommand extends ViewCommand<LiveswitchCallView> {
        public final long remainingTime;

        UpdateRemainingTimeCommand(long j) {
            super("updateRemainingTime", AddToEndSingleStrategy.class);
            this.remainingTime = j;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.updateRemainingTime(this.remainingTime);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowConnectionStateCommand extends ViewCommand<LiveswitchCallView> {
        public final int resId;

        ShowConnectionStateCommand(int i) {
            super("showConnectionState", AddToEndSingleStrategy.class);
            this.resId = i;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showConnectionState(this.resId);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowReportBugButtonCommand extends ViewCommand<LiveswitchCallView> {
        ShowReportBugButtonCommand() {
            super("showReportBugButton", AddToEndSingleStrategy.class);
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showReportBugButton();
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class SetRecordingWarningTextCommand extends ViewCommand<LiveswitchCallView> {
        public final int englishResId;
        public final int spanishResId;

        SetRecordingWarningTextCommand(int i, int i2) {
            super("setRecordingWarningText", AddToEndSingleStrategy.class);
            this.englishResId = i;
            this.spanishResId = i2;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.setRecordingWarningText(this.englishResId, this.spanishResId);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowAdminMessageCommand extends ViewCommand<LiveswitchCallView> {
        public final String message;

        ShowAdminMessageCommand(String str) {
            super("showAdminMessage", AddToEndSingleStrategy.class);
            this.message = str;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showAdminMessage(this.message);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowWarningMessageCommand extends ViewCommand<LiveswitchCallView> {
        ShowWarningMessageCommand() {
            super("showWarningMessage", AddToEndSingleStrategy.class);
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showWarningMessage();
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class HideWarningMessageCommand extends ViewCommand<LiveswitchCallView> {
        HideWarningMessageCommand() {
            super("hideWarningMessage", AddToEndSingleStrategy.class);
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.hideWarningMessage();
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowMessageCommand extends ViewCommand<LiveswitchCallView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showMessage(this.message);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowMessage1Command extends ViewCommand<LiveswitchCallView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showMessage(this.resId);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<LiveswitchCallView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowProgressCommand extends ViewCommand<LiveswitchCallView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showProgress(this.show);
        }
    }

    /* compiled from: LiveswitchCallView$$State */
    public class ShowServerErrorCommand extends ViewCommand<LiveswitchCallView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(LiveswitchCallView liveswitchCallView) {
            liveswitchCallView.showServerError();
        }
    }
}
