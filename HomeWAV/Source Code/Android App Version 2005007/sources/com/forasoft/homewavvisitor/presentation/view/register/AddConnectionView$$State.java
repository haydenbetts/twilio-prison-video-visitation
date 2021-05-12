package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class AddConnectionView$$State extends MvpViewState<AddConnectionView> implements AddConnectionView {
    public void setConnectionsAdapter(AddConnectionAdapter addConnectionAdapter) {
        SetConnectionsAdapterCommand setConnectionsAdapterCommand = new SetConnectionsAdapterCommand(addConnectionAdapter);
        this.viewCommands.beforeApply(setConnectionsAdapterCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView connectionsAdapter : this.views) {
                connectionsAdapter.setConnectionsAdapter(addConnectionAdapter);
            }
            this.viewCommands.afterApply(setConnectionsAdapterCommand);
        }
    }

    public void setInmatesAutocomplete(int i, List<Inmate> list) {
        SetInmatesAutocompleteCommand setInmatesAutocompleteCommand = new SetInmatesAutocompleteCommand(i, list);
        this.viewCommands.beforeApply(setInmatesAutocompleteCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView inmatesAutocomplete : this.views) {
                inmatesAutocomplete.setInmatesAutocomplete(i, list);
            }
            this.viewCommands.afterApply(setInmatesAutocompleteCommand);
        }
    }

    public void showSkipButton() {
        ShowSkipButtonCommand showSkipButtonCommand = new ShowSkipButtonCommand();
        this.viewCommands.beforeApply(showSkipButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showSkipButton : this.views) {
                showSkipButton.showSkipButton();
            }
            this.viewCommands.afterApply(showSkipButtonCommand);
        }
    }

    public void showNextButton() {
        ShowNextButtonCommand showNextButtonCommand = new ShowNextButtonCommand();
        this.viewCommands.beforeApply(showNextButtonCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showNextButton : this.views) {
                showNextButton.showNextButton();
            }
            this.viewCommands.afterApply(showNextButtonCommand);
        }
    }

    public void showEditPhoto(boolean z, boolean z2) {
        ShowEditPhotoCommand showEditPhotoCommand = new ShowEditPhotoCommand(z, z2);
        this.viewCommands.beforeApply(showEditPhotoCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showEditPhoto : this.views) {
                showEditPhoto.showEditPhoto(z, z2);
            }
            this.viewCommands.afterApply(showEditPhotoCommand);
        }
    }

    public void hideAddConnectionLink() {
        HideAddConnectionLinkCommand hideAddConnectionLinkCommand = new HideAddConnectionLinkCommand();
        this.viewCommands.beforeApply(hideAddConnectionLinkCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView hideAddConnectionLink : this.views) {
                hideAddConnectionLink.hideAddConnectionLink();
            }
            this.viewCommands.afterApply(hideAddConnectionLinkCommand);
        }
    }

    public void showFacilityAgreement(String str) {
        ShowFacilityAgreementCommand showFacilityAgreementCommand = new ShowFacilityAgreementCommand(str);
        this.viewCommands.beforeApply(showFacilityAgreementCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showFacilityAgreement : this.views) {
                showFacilityAgreement.showFacilityAgreement(str);
            }
            this.viewCommands.afterApply(showFacilityAgreementCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand(z);
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (AddConnectionView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class SetConnectionsAdapterCommand extends ViewCommand<AddConnectionView> {
        public final AddConnectionAdapter connectionAdapter;

        SetConnectionsAdapterCommand(AddConnectionAdapter addConnectionAdapter) {
            super("setConnectionsAdapter", AddToEndSingleStrategy.class);
            this.connectionAdapter = addConnectionAdapter;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.setConnectionsAdapter(this.connectionAdapter);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class SetInmatesAutocompleteCommand extends ViewCommand<AddConnectionView> {
        public final int connectionPosition;
        public final List<Inmate> inmates;

        SetInmatesAutocompleteCommand(int i, List<Inmate> list) {
            super("setInmatesAutocomplete", AddToEndSingleStrategy.class);
            this.connectionPosition = i;
            this.inmates = list;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.setInmatesAutocomplete(this.connectionPosition, this.inmates);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowSkipButtonCommand extends ViewCommand<AddConnectionView> {
        ShowSkipButtonCommand() {
            super("showSkipButton", AddToEndSingleStrategy.class);
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showSkipButton();
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowNextButtonCommand extends ViewCommand<AddConnectionView> {
        ShowNextButtonCommand() {
            super("showNextButton", AddToEndSingleStrategy.class);
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showNextButton();
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowEditPhotoCommand extends ViewCommand<AddConnectionView> {
        public final boolean hasPhotoId;
        public final boolean requirePhotoId;

        ShowEditPhotoCommand(boolean z, boolean z2) {
            super("showEditPhoto", AddToEndSingleStrategy.class);
            this.requirePhotoId = z;
            this.hasPhotoId = z2;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showEditPhoto(this.requirePhotoId, this.hasPhotoId);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class HideAddConnectionLinkCommand extends ViewCommand<AddConnectionView> {
        HideAddConnectionLinkCommand() {
            super("hideAddConnectionLink", AddToEndSingleStrategy.class);
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.hideAddConnectionLink();
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowFacilityAgreementCommand extends ViewCommand<AddConnectionView> {
        public final String agreementUrl;

        ShowFacilityAgreementCommand(String str) {
            super("showFacilityAgreement", AddToEndSingleStrategy.class);
            this.agreementUrl = str;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showFacilityAgreement(this.agreementUrl);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowMessageCommand extends ViewCommand<AddConnectionView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showMessage(this.message);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowMessage1Command extends ViewCommand<AddConnectionView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showMessage(this.resId);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<AddConnectionView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowProgressCommand extends ViewCommand<AddConnectionView> {
        public final boolean show;

        ShowProgressCommand(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showProgress(this.show);
        }
    }

    /* compiled from: AddConnectionView$$State */
    public class ShowServerErrorCommand extends ViewCommand<AddConnectionView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(AddConnectionView addConnectionView) {
            addConnectionView.showServerError();
        }
    }
}
