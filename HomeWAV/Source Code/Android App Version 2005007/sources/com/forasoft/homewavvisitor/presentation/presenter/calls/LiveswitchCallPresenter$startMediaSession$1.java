package com.forasoft.homewavvisitor.presentation.presenter.calls;

import fm.liveswitch.Channel;
import fm.liveswitch.IAction1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a(\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0014\u0012\u000e\b\u0001\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "channels", "", "Lfm/liveswitch/Channel;", "kotlin.jvm.PlatformType", "invoke", "([Lfm/liveswitch/Channel;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$startMediaSession$1<T> implements IAction1<Channel[]> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$startMediaSession$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(Channel[] channelArr) {
        Channel channel;
        Channel channel2;
        LiveswitchCallPresenter liveswitchCallPresenter = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(channelArr, "channels");
        int length = channelArr.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            channel = null;
            if (i2 >= length) {
                channel2 = null;
                break;
            }
            channel2 = channelArr[i2];
            Intrinsics.checkExpressionValueIsNotNull(channel2, "it");
            if (Intrinsics.areEqual((Object) channel2.getId(), (Object) this.this$0.callData.getChannelId())) {
                break;
            }
            i2++;
        }
        liveswitchCallPresenter.liveswitchChannel = channel2;
        LiveswitchCallPresenter liveswitchCallPresenter2 = this.this$0;
        int length2 = channelArr.length;
        while (true) {
            if (i >= length2) {
                break;
            }
            Channel channel3 = channelArr[i];
            Intrinsics.checkExpressionValueIsNotNull(channel3, "it");
            if (Intrinsics.areEqual((Object) channel3.getId(), (Object) this.this$0.callData.getMessageChannelId())) {
                channel = channel3;
                break;
            }
            i++;
        }
        liveswitchCallPresenter2.liveswitchMessageChannel = channel;
        this.this$0.listenRemoteEvents();
        this.this$0.sendLocalMedia();
    }
}
