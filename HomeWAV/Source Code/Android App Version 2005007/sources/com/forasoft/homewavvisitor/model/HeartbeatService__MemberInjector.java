package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class HeartbeatService__MemberInjector implements MemberInjector<HeartbeatService> {
    public void inject(HeartbeatService heartbeatService, Scope scope) {
        heartbeatService.api = (HomewavApi) scope.getInstance(HomewavApi.class);
    }
}
