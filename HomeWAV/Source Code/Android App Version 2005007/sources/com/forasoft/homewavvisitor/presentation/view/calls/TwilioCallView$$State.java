package com.forasoft.homewavvisitor.presentation.view.calls;

import com.twilio.video.LocalVideoTrack;
import com.twilio.video.RemoteVideoTrack;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class TwilioCallView$$State extends MvpViewState<TwilioCallView> implements TwilioCallView {
    public void displayInmateVideo(RemoteVideoTrack remoteVideoTrack) {
        DisplayInmateVideoCommand displayInmateVideoCommand = new DisplayInmateVideoCommand(remoteVideoTrack);
        this.viewCommands.beforeApply(displayInmateVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView displayInmateVideo : this.views) {
                displayInmateVideo.displayInmateVideo(remoteVideoTrack);
            }
            this.viewCommands.afterApply(displayInmateVideoCommand);
        }
    }

    public void displayVisitorVideo(LocalVideoTrack localVideoTrack) {
        DisplayVisitorVideoCommand displayVisitorVideoCommand = new DisplayVisitorVideoCommand(localVideoTrack);
        this.viewCommands.beforeApply(displayVisitorVideoCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView displayVisitorVideo : this.views) {
                displayVisitorVideo.displayVisitorVideo(localVideoTrack);
            }
            this.viewCommands.afterApply(displayVisitorVideoCommand);
        }
    }

    public void updateRemainingTime(long j) {
        UpdateRemainingTimeCommand updateRemainingTimeCommand = new UpdateRemainingTimeCommand(j);
        this.viewCommands.beforeApply(updateRemainingTimeCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView updateRemainingTime : this.views) {
                updateRemainingTime.updateRemainingTime(j);
            }
            this.viewCommands.afterApply(updateRemainingTimeCommand);
        }
    }

    public void showReportBugButton() {
        ShowReportBugButtonCommand showReportBugButtonCommand = new ShowReportBugButtonCommand();
        this.viewCommands.beforeApply(showReportBugButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showReportBugButton : this.views) {
                showReportBugButton.showReportBugButton();
            }
            this.viewCommands.afterApply(showReportBugButtonCommand);
        }
    }

    public void setRecordingWarningText(int i, int i2) {
        SetRecordingWarningTextCommand setRecordingWarningTextCommand = new SetRecordingWarningTextCommand(i, i2);
        this.viewCommands.beforeApply(setRecordingWarningTextCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView recordingWarningText : this.views) {
                recordingWarningText.setRecordingWarningText(i, i2);
            }
            this.viewCommands.afterApply(setRecordingWarningTextCommand);
        }
    }

    public void showAdminMessage(String str) {
        ShowAdminMessageCommand showAdminMessageCommand = new ShowAdminMessageCommand(str);
        this.viewCommands.beforeApply(showAdminMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showAdminMessage : this.views) {
                showAdminMessage.showAdminMessage(str);
            }
            this.viewCommands.afterApply(showAdminMessageCommand);
        }
    }

    public void showWarningMessage() {
        ShowWarningMessageCommand showWarningMessageCommand = new ShowWarningMessageCommand();
        this.viewCommands.beforeApply(showWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showWarningMessage : this.views) {
                showWarningMessage.showWarningMessage();
            }
            this.viewCommands.afterApply(showWarningMessageCommand);
        }
    }

    public void hideWarningMessage() {
        HideWarningMessageCommand hideWarningMessageCommand = new HideWarningMessageCommand();
        this.viewCommands.beforeApply(hideWarningMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView hideWarningMessage : this.views) {
                hideWarningMessage.hideWarningMessage();
            }
            this.viewCommands.afterApply(hideWarningMessageCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (TwilioCallView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class DisplayInmateVideoCommand extends ViewCommand<TwilioCallView> {
        public final RemoteVideoTrack videoTrack;

        DisplayInmateVideoCommand(RemoteVideoTrack remoteVideoTrack) {
            super("displayInmateVideo", AddToEndSingleStrategy.class);
            this.videoTrack = remoteVideoTrack;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.displayInmateVideo(this.videoTrack);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class DisplayVisitorVideoCommand extends ViewCommand<TwilioCallView> {
        public final LocalVideoTrack videoTrack;

        DisplayVisitorVideoCommand(LocalVideoTrack localVideoTrack) {
            super("displayVisitorVideo", AddToEndSingleStrategy.class);
            this.videoTrack = localVideoTrack;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.displayVisitorVideo(this.videoTrack);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class UpdateRemainingTimeCommand extends ViewCommand<TwilioCallView> {
        public final long remainingTime;

        UpdateRemainingTimeCommand(long j) {
            super("updateRemainingTime", AddToEndSingleStrategy.class);
            this.remainingTime = j;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.updateRemainingTime(this.remainingTime);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowReportBugButtonCommand extends ViewCommand<TwilioCallView> {
        ShowReportBugButtonCommand() {
            super("showReportBugButton", AddToEndSingleStrategy.class);
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showReportBugButton();
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class SetRecordingWarningTextCommand extends ViewCommand<TwilioCallView> {
        public final int englishResId;
        public final int spanishResId;

        SetRecordingWarningTextCommand(int i, int i2) {
            super("setRecordingWarningText", AddToEndSingleStrategy.class);
            this.englishResId = i;
            this.spanishResId = i2;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.setRecordingWarningText(this.englishResId, this.spanishResId);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowAdminMessageCommand extends ViewCommand<TwilioCallView> {
        public final String message;

        ShowAdminMessageCommand(String str) {
            super("showAdminMessage", AddToEndSingleStrategy.class);
            this.message = str;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showAdminMessage(this.message);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowWarningMessageCommand extends ViewCommand<TwilioCallView> {
        ShowWarningMessageCommand() {
            super("showWarningMessage", AddToEndSingleStrategy.class);
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showWarningMessage();
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class HideWarningMessageCommand extends ViewCommand<TwilioCallView> {
        HideWarningMessageCommand() {
            super("hideWarningMessage", AddToEndSingleStrategy.class);
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.hideWarningMessage();
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowMessageCommand extends ViewCommand<TwilioCallView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showMessage(this.message);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowMessage1Command extends ViewCommand<TwilioCallView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showMessage(this.resId);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<TwilioCallView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowProgressCommand extends ViewCommand<TwilioCallView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showProgress(this.show);
        }
    }

    /* compiled from: TwilioCallView$$State */
    public class ShowServerErrorCommand extends ViewCommand<TwilioCallView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(TwilioCallView twilioCallView) {
            twilioCallView.showServerError();
        }
    }
}
