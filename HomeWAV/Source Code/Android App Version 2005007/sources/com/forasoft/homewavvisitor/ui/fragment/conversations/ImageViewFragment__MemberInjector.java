package com.forasoft.homewavvisitor.ui.fragment.conversations;

import ru.terrakok.cicerone.Router;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class ImageViewFragment__MemberInjector implements MemberInjector<ImageViewFragment> {
    public void inject(ImageViewFragment imageViewFragment, Scope scope) {
        imageViewFragment.router = (Router) scope.getInstance(Router.class);
    }
}
