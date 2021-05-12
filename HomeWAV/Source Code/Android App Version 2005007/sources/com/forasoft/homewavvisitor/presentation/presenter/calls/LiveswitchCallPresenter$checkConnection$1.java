package com.forasoft.homewavvisitor.presentation.presenter.calls;

import air.HomeWAV.R;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.forasoft.homewavvisitor.presentation.view.calls.LiveswitchCallView;
import fm.liveswitch.ConnectionStats;
import fm.liveswitch.IAction1;
import fm.liveswitch.MediaReceiverStats;
import fm.liveswitch.MediaSenderStats;
import fm.liveswitch.MediaStreamStats;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "stats", "Lfm/liveswitch/ConnectionStats;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$checkConnection$1<T> implements IAction1<ConnectionStats> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$checkConnection$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(ConnectionStats connectionStats) {
        int i;
        int i2;
        int i3;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        List arrayList3 = new ArrayList();
        Intrinsics.checkExpressionValueIsNotNull(connectionStats, "stats");
        MediaStreamStats audioStream = connectionStats.getAudioStream();
        MediaReceiverStats mediaReceiverStats = null;
        MediaSenderStats sender = audioStream != null ? audioStream.getSender() : null;
        if (sender != null) {
            arrayList.add(Integer.valueOf(sender.getRoundTripTime()));
        }
        Intrinsics.checkExpressionValueIsNotNull(audioStream, "audioStream");
        MediaReceiverStats receiver = audioStream.getReceiver();
        if (receiver != null) {
            if (receiver.getPacketsReceived() > 0) {
                arrayList2.add(Long.valueOf(receiver.getPacketsLost() / receiver.getPacketsReceived()));
            } else {
                arrayList2.add(0L);
            }
            arrayList3.add(Integer.valueOf(receiver.getJitter()));
        }
        MediaStreamStats videoStream = connectionStats.getVideoStream();
        MediaSenderStats sender2 = videoStream != null ? videoStream.getSender() : null;
        if (sender2 != null) {
            arrayList.add(Integer.valueOf(sender2.getRoundTripTime()));
        }
        if (videoStream != null) {
            mediaReceiverStats = videoStream.getReceiver();
        }
        if (mediaReceiverStats != null) {
            if (mediaReceiverStats.getPacketsReceived() > 0) {
                arrayList2.add(Long.valueOf(mediaReceiverStats.getPacketsLost() / mediaReceiverStats.getPacketsReceived()));
            } else {
                arrayList2.add(0L);
            }
            arrayList3.add(Integer.valueOf(mediaReceiverStats.getJitter()));
        }
        Integer num = arrayList.size() > 0 ? (Integer) Collections.max(arrayList) : 0;
        if (Intrinsics.compare(num.intValue(), 100) < 0) {
            i = 100;
        } else if (Intrinsics.compare(num.intValue(), 150) < 0) {
            i = 90;
        } else if (Intrinsics.compare(num.intValue(), 200) < 0) {
            i = 80;
        } else if (Intrinsics.compare(num.intValue(), (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) < 0) {
            i = 70;
        } else if (Intrinsics.compare(num.intValue(), 300) < 0) {
            i = 60;
        } else if (Intrinsics.compare(num.intValue(), 350) < 0) {
            i = 50;
        } else if (Intrinsics.compare(num.intValue(), 400) < 0) {
            i = 40;
        } else if (Intrinsics.compare(num.intValue(), 450) < 0) {
            i = 30;
        } else {
            i = Intrinsics.compare(num.intValue(), 500) < 0 ? 20 : 10;
        }
        Long l = arrayList2.size() > 0 ? (Long) Collections.max(arrayList2) : 0L;
        if (Double.compare((double) l.longValue(), 0.02d) < 0) {
            i2 = 100;
        } else if (Double.compare((double) l.longValue(), 0.03d) < 0) {
            i2 = 90;
        } else if (Double.compare((double) l.longValue(), 0.04d) < 0) {
            i2 = 80;
        } else if (Double.compare((double) l.longValue(), 0.05d) < 0) {
            i2 = 70;
        } else if (Double.compare((double) l.longValue(), 0.06d) < 0) {
            i2 = 60;
        } else if (Double.compare((double) l.longValue(), 0.07d) < 0) {
            i2 = 50;
        } else if (Double.compare((double) l.longValue(), 0.08d) < 0) {
            i2 = 40;
        } else if (Double.compare((double) l.longValue(), 0.09d) < 0) {
            i2 = 30;
        } else {
            i2 = Double.compare((double) l.longValue(), 0.1d) < 0 ? 20 : 10;
        }
        Integer num2 = arrayList3.size() > 0 ? (Integer) Collections.max(arrayList3) : 0;
        if (Intrinsics.compare(num2.intValue(), 40) < 0) {
            i3 = 100;
        } else if (Intrinsics.compare(num2.intValue(), 60) < 0) {
            i3 = 90;
        } else if (Intrinsics.compare(num2.intValue(), 80) < 0) {
            i3 = 80;
        } else if (Intrinsics.compare(num2.intValue(), 100) < 0) {
            i3 = 70;
        } else if (Intrinsics.compare(num2.intValue(), 120) < 0) {
            i3 = 60;
        } else if (Intrinsics.compare(num2.intValue(), 140) < 0) {
            i3 = 50;
        } else if (Intrinsics.compare(num2.intValue(), 160) < 0) {
            i3 = 40;
        } else if (Intrinsics.compare(num2.intValue(), 180) < 0) {
            i3 = 30;
        } else {
            i3 = Intrinsics.compare(num2.intValue(), 200) < 0 ? 20 : 10;
        }
        int min = Math.min(i, (i2 + i3) / 2);
        ((LiveswitchCallView) this.this$0.getViewState()).showConnectionState((81 <= min && 100 >= min) ? R.drawable.ic_network_connection_5 : (61 <= min && 80 >= min) ? R.drawable.ic_network_connection_4 : (41 <= min && 60 >= min) ? R.drawable.ic_network_connection_3 : (21 <= min && 40 >= min) ? R.drawable.ic_network_connection_2 : (1 <= min && 20 >= min) ? R.drawable.ic_network_connection_1 : R.drawable.ic_network_connection_0);
    }
}
