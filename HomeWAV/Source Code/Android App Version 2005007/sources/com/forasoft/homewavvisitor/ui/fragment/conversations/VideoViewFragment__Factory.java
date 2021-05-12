package com.forasoft.homewavvisitor.ui.fragment.conversations;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class VideoViewFragment__Factory implements Factory<VideoViewFragment> {
    private MemberInjector<VideoViewFragment> memberInjector = new VideoViewFragment__MemberInjector();

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

    public VideoViewFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        VideoViewFragment videoViewFragment = new VideoViewFragment();
        this.memberInjector.inject(videoViewFragment, targetScope);
        return videoViewFragment;
    }
}
