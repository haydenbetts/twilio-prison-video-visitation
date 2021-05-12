package com.forasoft.homewavvisitor.presentation.view.account;

import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;

public class EditAccountView$$State extends MvpViewState<EditAccountView> implements EditAccountView {
    public void showSateAbbreviation(String str) {
        ShowSateAbbreviationCommand showSateAbbreviationCommand = new ShowSateAbbreviationCommand(str);
        this.viewCommands.beforeApply(showSateAbbreviationCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showSateAbbreviation : this.views) {
                showSateAbbreviation.showSateAbbreviation(str);
            }
            this.viewCommands.afterApply(showSateAbbreviationCommand);
        }
    }

    public void showPickStateDialog(List<String> list) {
        ShowPickStateDialogCommand showPickStateDialogCommand = new ShowPickStateDialogCommand(list);
        this.viewCommands.beforeApply(showPickStateDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showPickStateDialog : this.views) {
                showPickStateDialog.showPickStateDialog(list);
            }
            this.viewCommands.afterApply(showPickStateDialogCommand);
        }
    }

    public void showProgress() {
        ShowProgressCommand showProgressCommand = new ShowProgressCommand();
        this.viewCommands.beforeApply(showProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showProgress : this.views) {
                showProgress.showProgress();
            }
            this.viewCommands.afterApply(showProgressCommand);
        }
    }

    public void hideProgress() {
        HideProgressCommand hideProgressCommand = new HideProgressCommand();
        this.viewCommands.beforeApply(hideProgressCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView hideProgress : this.views) {
                hideProgress.hideProgress();
            }
            this.viewCommands.afterApply(hideProgressCommand);
        }
    }

    public void setEmail(String str) {
        SetEmailCommand setEmailCommand = new SetEmailCommand(str);
        this.viewCommands.beforeApply(setEmailCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView email : this.views) {
                email.setEmail(str);
            }
            this.viewCommands.afterApply(setEmailCommand);
        }
    }

    public void setUsername(String str) {
        SetUsernameCommand setUsernameCommand = new SetUsernameCommand(str);
        this.viewCommands.beforeApply(setUsernameCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView username : this.views) {
                username.setUsername(str);
            }
            this.viewCommands.afterApply(setUsernameCommand);
        }
    }

    public void setFirstName(String str) {
        SetFirstNameCommand setFirstNameCommand = new SetFirstNameCommand(str);
        this.viewCommands.beforeApply(setFirstNameCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView firstName : this.views) {
                firstName.setFirstName(str);
            }
            this.viewCommands.afterApply(setFirstNameCommand);
        }
    }

    public void setPin(String str) {
        SetPinCommand setPinCommand = new SetPinCommand(str);
        this.viewCommands.beforeApply(setPinCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView pin : this.views) {
                pin.setPin(str);
            }
            this.viewCommands.afterApply(setPinCommand);
        }
    }

    public void setLastName(String str) {
        SetLastNameCommand setLastNameCommand = new SetLastNameCommand(str);
        this.viewCommands.beforeApply(setLastNameCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView lastName : this.views) {
                lastName.setLastName(str);
            }
            this.viewCommands.afterApply(setLastNameCommand);
        }
    }

    public void setBirthDate(String str) {
        SetBirthDateCommand setBirthDateCommand = new SetBirthDateCommand(str);
        this.viewCommands.beforeApply(setBirthDateCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView birthDate : this.views) {
                birthDate.setBirthDate(str);
            }
            this.viewCommands.afterApply(setBirthDateCommand);
        }
    }

    public void setPhone(String str) {
        SetPhoneCommand setPhoneCommand = new SetPhoneCommand(str);
        this.viewCommands.beforeApply(setPhoneCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView phone : this.views) {
                phone.setPhone(str);
            }
            this.viewCommands.afterApply(setPhoneCommand);
        }
    }

    public void setStreet(String str) {
        SetStreetCommand setStreetCommand = new SetStreetCommand(str);
        this.viewCommands.beforeApply(setStreetCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView street : this.views) {
                street.setStreet(str);
            }
            this.viewCommands.afterApply(setStreetCommand);
        }
    }

    public void setCity(String str) {
        SetCityCommand setCityCommand = new SetCityCommand(str);
        this.viewCommands.beforeApply(setCityCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView city : this.views) {
                city.setCity(str);
            }
            this.viewCommands.afterApply(setCityCommand);
        }
    }

    public void setState(String str) {
        SetStateCommand setStateCommand = new SetStateCommand(str);
        this.viewCommands.beforeApply(setStateCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView state : this.views) {
                state.setState(str);
            }
            this.viewCommands.afterApply(setStateCommand);
        }
    }

    public void setZip(String str) {
        SetZipCommand setZipCommand = new SetZipCommand(str);
        this.viewCommands.beforeApply(setZipCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView zip : this.views) {
                zip.setZip(str);
            }
            this.viewCommands.afterApply(setZipCommand);
        }
    }

    public void showPasswordField() {
        ShowPasswordFieldCommand showPasswordFieldCommand = new ShowPasswordFieldCommand();
        this.viewCommands.beforeApply(showPasswordFieldCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showPasswordField : this.views) {
                showPasswordField.showPasswordField();
            }
            this.viewCommands.afterApply(showPasswordFieldCommand);
        }
    }

    public void showDatePickDialog(int i, int i2, int i3) {
        ShowDatePickDialogCommand showDatePickDialogCommand = new ShowDatePickDialogCommand(i, i2, i3);
        this.viewCommands.beforeApply(showDatePickDialogCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showDatePickDialog : this.views) {
                showDatePickDialog.showDatePickDialog(i, i2, i3);
            }
            this.viewCommands.afterApply(showDatePickDialogCommand);
        }
    }

    public void showDate(String str) {
        ShowDateCommand showDateCommand = new ShowDateCommand(str);
        this.viewCommands.beforeApply(showDateCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showDate : this.views) {
                showDate.showDate(str);
            }
            this.viewCommands.afterApply(showDateCommand);
        }
    }

    public void showFieldError(String str, String str2) {
        ShowFieldErrorCommand showFieldErrorCommand = new ShowFieldErrorCommand(str, str2);
        this.viewCommands.beforeApply(showFieldErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showFieldError : this.views) {
                showFieldError.showFieldError(str, str2);
            }
            this.viewCommands.afterApply(showFieldErrorCommand);
        }
    }

    public void scrollToError(String str) {
        ScrollToErrorCommand scrollToErrorCommand = new ScrollToErrorCommand(str);
        this.viewCommands.beforeApply(scrollToErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView scrollToError : this.views) {
                scrollToError.scrollToError(str);
            }
            this.viewCommands.afterApply(scrollToErrorCommand);
        }
    }

    public void clearErrors() {
        ClearErrorsCommand clearErrorsCommand = new ClearErrorsCommand();
        this.viewCommands.beforeApply(clearErrorsCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView clearErrors : this.views) {
                clearErrors.clearErrors();
            }
            this.viewCommands.afterApply(clearErrorsCommand);
        }
    }

    public void showMessage(String str) {
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(str);
        this.viewCommands.beforeApply(showMessageCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showMessage : this.views) {
                showMessage.showMessage(str);
            }
            this.viewCommands.afterApply(showMessageCommand);
        }
    }

    public void showMessage(int i) {
        ShowMessage1Command showMessage1Command = new ShowMessage1Command(i);
        this.viewCommands.beforeApply(showMessage1Command);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showMessage : this.views) {
                showMessage.showMessage(i);
            }
            this.viewCommands.afterApply(showMessage1Command);
        }
    }

    public void updateNotificationMenu(int i) {
        UpdateNotificationMenuCommand updateNotificationMenuCommand = new UpdateNotificationMenuCommand(i);
        this.viewCommands.beforeApply(updateNotificationMenuCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView updateNotificationMenu : this.views) {
                updateNotificationMenu.updateNotificationMenu(i);
            }
            this.viewCommands.afterApply(updateNotificationMenuCommand);
        }
    }

    public void showProgress(boolean z) {
        ShowProgress1Command showProgress1Command = new ShowProgress1Command(z);
        this.viewCommands.beforeApply(showProgress1Command);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showProgress : this.views) {
                showProgress.showProgress(z);
            }
            this.viewCommands.afterApply(showProgress1Command);
        }
    }

    public void showServerError() {
        ShowServerErrorCommand showServerErrorCommand = new ShowServerErrorCommand();
        this.viewCommands.beforeApply(showServerErrorCommand);
        if (!hasNotView().booleanValue()) {
            for (EditAccountView showServerError : this.views) {
                showServerError.showServerError();
            }
            this.viewCommands.afterApply(showServerErrorCommand);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowSateAbbreviationCommand extends ViewCommand<EditAccountView> {
        public final String abbreviation;

        ShowSateAbbreviationCommand(String str) {
            super("showSateAbbreviation", AddToEndSingleStrategy.class);
            this.abbreviation = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showSateAbbreviation(this.abbreviation);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowPickStateDialogCommand extends ViewCommand<EditAccountView> {
        public final List<String> statesList;

        ShowPickStateDialogCommand(List<String> list) {
            super("showPickStateDialog", AddToEndSingleStrategy.class);
            this.statesList = list;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showPickStateDialog(this.statesList);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowProgressCommand extends ViewCommand<EditAccountView> {
        ShowProgressCommand() {
            super("showProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showProgress();
        }
    }

    /* compiled from: EditAccountView$$State */
    public class HideProgressCommand extends ViewCommand<EditAccountView> {
        HideProgressCommand() {
            super("hideProgress", AddToEndSingleStrategy.class);
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.hideProgress();
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetEmailCommand extends ViewCommand<EditAccountView> {
        public final String email;

        SetEmailCommand(String str) {
            super("setEmail", AddToEndSingleStrategy.class);
            this.email = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setEmail(this.email);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetUsernameCommand extends ViewCommand<EditAccountView> {
        public final String username;

        SetUsernameCommand(String str) {
            super("setUsername", AddToEndSingleStrategy.class);
            this.username = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setUsername(this.username);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetFirstNameCommand extends ViewCommand<EditAccountView> {
        public final String firstName;

        SetFirstNameCommand(String str) {
            super("setFirstName", AddToEndSingleStrategy.class);
            this.firstName = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setFirstName(this.firstName);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetPinCommand extends ViewCommand<EditAccountView> {
        public final String pin;

        SetPinCommand(String str) {
            super("setPin", AddToEndSingleStrategy.class);
            this.pin = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setPin(this.pin);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetLastNameCommand extends ViewCommand<EditAccountView> {
        public final String lastName;

        SetLastNameCommand(String str) {
            super("setLastName", AddToEndSingleStrategy.class);
            this.lastName = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setLastName(this.lastName);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetBirthDateCommand extends ViewCommand<EditAccountView> {
        public final String birthDate;

        SetBirthDateCommand(String str) {
            super("setBirthDate", AddToEndSingleStrategy.class);
            this.birthDate = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setBirthDate(this.birthDate);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetPhoneCommand extends ViewCommand<EditAccountView> {
        public final String phone;

        SetPhoneCommand(String str) {
            super("setPhone", AddToEndSingleStrategy.class);
            this.phone = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setPhone(this.phone);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetStreetCommand extends ViewCommand<EditAccountView> {
        public final String street;

        SetStreetCommand(String str) {
            super("setStreet", AddToEndSingleStrategy.class);
            this.street = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setStreet(this.street);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetCityCommand extends ViewCommand<EditAccountView> {
        public final String city;

        SetCityCommand(String str) {
            super("setCity", AddToEndSingleStrategy.class);
            this.city = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setCity(this.city);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetStateCommand extends ViewCommand<EditAccountView> {
        public final String state;

        SetStateCommand(String str) {
            super("setState", AddToEndSingleStrategy.class);
            this.state = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setState(this.state);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class SetZipCommand extends ViewCommand<EditAccountView> {
        public final String zip;

        SetZipCommand(String str) {
            super("setZip", AddToEndSingleStrategy.class);
            this.zip = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.setZip(this.zip);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowPasswordFieldCommand extends ViewCommand<EditAccountView> {
        ShowPasswordFieldCommand() {
            super("showPasswordField", AddToEndSingleStrategy.class);
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showPasswordField();
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowDatePickDialogCommand extends ViewCommand<EditAccountView> {
        public final int day;
        public final int month;
        public final int year;

        ShowDatePickDialogCommand(int i, int i2, int i3) {
            super("showDatePickDialog", AddToEndSingleStrategy.class);
            this.day = i;
            this.month = i2;
            this.year = i3;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showDatePickDialog(this.day, this.month, this.year);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowDateCommand extends ViewCommand<EditAccountView> {
        public final String formatedDate;

        ShowDateCommand(String str) {
            super("showDate", AddToEndSingleStrategy.class);
            this.formatedDate = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showDate(this.formatedDate);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowFieldErrorCommand extends ViewCommand<EditAccountView> {
        public final String field;
        public final String message;

        ShowFieldErrorCommand(String str, String str2) {
            super("showFieldError", AddToEndSingleStrategy.class);
            this.field = str;
            this.message = str2;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showFieldError(this.field, this.message);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ScrollToErrorCommand extends ViewCommand<EditAccountView> {
        public final String field;

        ScrollToErrorCommand(String str) {
            super("scrollToError", AddToEndSingleStrategy.class);
            this.field = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.scrollToError(this.field);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ClearErrorsCommand extends ViewCommand<EditAccountView> {
        ClearErrorsCommand() {
            super("clearErrors", AddToEndSingleStrategy.class);
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.clearErrors();
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowMessageCommand extends ViewCommand<EditAccountView> {
        public final String message;

        ShowMessageCommand(String str) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.message = str;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showMessage(this.message);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowMessage1Command extends ViewCommand<EditAccountView> {
        public final int resId;

        ShowMessage1Command(int i) {
            super("showMessage", OneExecutionStateStrategy.class);
            this.resId = i;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showMessage(this.resId);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class UpdateNotificationMenuCommand extends ViewCommand<EditAccountView> {
        public final int count;

        UpdateNotificationMenuCommand(int i) {
            super("updateNotificationMenu", AddToEndSingleStrategy.class);
            this.count = i;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.updateNotificationMenu(this.count);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowProgress1Command extends ViewCommand<EditAccountView> {
        public final boolean show;

        ShowProgress1Command(boolean z) {
            super("showProgress", AddToEndSingleStrategy.class);
            this.show = z;
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showProgress(this.show);
        }
    }

    /* compiled from: EditAccountView$$State */
    public class ShowServerErrorCommand extends ViewCommand<EditAccountView> {
        ShowServerErrorCommand() {
            super("showServerError", SkipStrategy.class);
        }

        public void apply(EditAccountView editAccountView) {
            editAccountView.showServerError();
        }
    }
}
