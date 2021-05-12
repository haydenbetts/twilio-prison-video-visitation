package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.model.data.account.Settings;
import ru.terrakok.cicerone.Router;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class NotificationSettingsFragment__MemberInjector implements MemberInjector<NotificationSettingsFragment> {
    public void inject(NotificationSettingsFragment notificationSettingsFragment, Scope scope) {
        notificationSettingsFragment.router = (Router) scope.getInstance(Router.class);
        notificationSettingsFragment.settings = (Settings) scope.getInstance(Settings.class);
    }
}
