package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.system.RingtoneManager;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallService__MemberInjector implements MemberInjector<IncomingCallService> {
    public void inject(IncomingCallService incomingCallService, Scope scope) {
        incomingCallService.api = (HomewavApi) scope.getInstance(HomewavApi.class);
        incomingCallService.ringtone = (RingtoneManager) scope.getInstance(RingtoneManager.class);
        incomingCallService.authHolder = (AuthHolder) scope.getInstance(AuthHolder.class);
        incomingCallService.pusherHolder = (PusherHolder) scope.getInstance(PusherHolder.class);
    }
}
