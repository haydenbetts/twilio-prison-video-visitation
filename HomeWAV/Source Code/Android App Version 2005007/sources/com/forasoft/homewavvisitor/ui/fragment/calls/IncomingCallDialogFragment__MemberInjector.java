package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.AirshipAnalytics;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.system.RingtoneManager;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallDialogFragment__MemberInjector implements MemberInjector<IncomingCallDialogFragment> {
    public void inject(IncomingCallDialogFragment incomingCallDialogFragment, Scope scope) {
        incomingCallDialogFragment.api = (HomewavApi) scope.getInstance(HomewavApi.class);
        incomingCallDialogFragment.inmateDao = (InmateDao) scope.getInstance(InmateDao.class);
        incomingCallDialogFragment.notificationDao = (NotificationDao) scope.getInstance(NotificationDao.class);
        incomingCallDialogFragment.analytics = (AirshipAnalytics) scope.getInstance(AirshipAnalytics.class);
        incomingCallDialogFragment.ringtone = (RingtoneManager) scope.getInstance(RingtoneManager.class);
    }
}
