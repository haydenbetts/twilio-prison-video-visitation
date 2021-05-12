package com.forasoft.homewavvisitor.ui.fragment.conversations;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class ImageViewFragment__Factory implements Factory<ImageViewFragment> {
    private MemberInjector<ImageViewFragment> memberInjector = new ImageViewFragment__MemberInjector();

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

    public ImageViewFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        this.memberInjector.inject(imageViewFragment, targetScope);
        return imageViewFragment;
    }
}
