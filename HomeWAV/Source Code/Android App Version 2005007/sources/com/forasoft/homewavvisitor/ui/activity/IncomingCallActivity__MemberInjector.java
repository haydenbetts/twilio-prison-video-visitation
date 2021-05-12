package com.forasoft.homewavvisitor.ui.activity;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallActivity__MemberInjector implements MemberInjector<IncomingCallActivity> {
    private MemberInjector<BaseActivity> superMemberInjector = new BaseActivity__MemberInjector();

    public void inject(IncomingCallActivity incomingCallActivity, Scope scope) {
        this.superMemberInjector.inject(incomingCallActivity, scope);
        incomingCallActivity.router = (HomewavRouter) scope.getInstance(HomewavRouter.class);
        incomingCallActivity.pusherHolder = (PusherHolder) scope.getInstance(PusherHolder.class);
        incomingCallActivity.heartbeatRepository = (HeartbeatRepository) scope.getInstance(HeartbeatRepository.class);
    }
}
