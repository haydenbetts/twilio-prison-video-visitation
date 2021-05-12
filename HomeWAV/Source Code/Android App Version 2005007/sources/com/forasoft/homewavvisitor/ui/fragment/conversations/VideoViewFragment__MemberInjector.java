package com.forasoft.homewavvisitor.ui.fragment.conversations;

import ru.terrakok.cicerone.Router;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class VideoViewFragment__MemberInjector implements MemberInjector<VideoViewFragment> {
    public void inject(VideoViewFragment videoViewFragment, Scope scope) {
        videoViewFragment.router = (Router) scope.getInstance(Router.class);
        videoViewFragment.streamUrl = (String) scope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.StreamUrl");
    }
}
