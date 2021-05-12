package com.forasoft.homewavvisitor.model.notifications;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class MessageListenerService__Factory implements Factory<MessageListenerService> {
    private MemberInjector<MessageListenerService> memberInjector = new MessageListenerService__MemberInjector();

    public Scope getTargetScope(Scope scope) {
        return scope;
    }

    public boolean hasProvidesReleasableAnnotation() {
        return false;
    }

    public boolean hasProvidesSingletonAnnotation() {
        return false;
    }

    public boolean hasReleasableAnnotation() {
        return false;
    }

    public boolean hasScopeAnnotation() {
        return false;
    }

    public boolean hasSingletonAnnotation() {
        return false;
    }

    public MessageListenerService createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        MessageListenerService messageListenerService = new MessageListenerService();
        this.memberInjector.inject(messageListenerService, targetScope);
        return messageListenerService;
    }
}
