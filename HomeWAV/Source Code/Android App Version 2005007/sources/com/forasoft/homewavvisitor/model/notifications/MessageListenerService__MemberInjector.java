package com.forasoft.homewavvisitor.model.notifications;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class MessageListenerService__MemberInjector implements MemberInjector<MessageListenerService> {
    public void inject(MessageListenerService messageListenerService, Scope scope) {
        messageListenerService.notificationDao = (NotificationDao) scope.getInstance(NotificationDao.class);
        messageListenerService.userDao = (UserDao) scope.getInstance(UserDao.class);
        messageListenerService.settings = (Settings) scope.getInstance(Settings.class);
        messageListenerService.authHolder = (AuthHolder) scope.getInstance(AuthHolder.class);
        messageListenerService.api = (HomewavApi) scope.getInstance(HomewavApi.class);
    }
}
