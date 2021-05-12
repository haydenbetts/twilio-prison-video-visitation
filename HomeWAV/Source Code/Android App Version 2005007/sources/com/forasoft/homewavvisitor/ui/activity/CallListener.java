package com.forasoft.homewavvisitor.ui.activity;

import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/CallListener;", "", "onCallCancelled", "", "onCallDeclined", "onDialogButtonClicked", "onLiveswitchCallAccepted", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "onTwilioCallAccepted", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public interface CallListener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: MainActivity.kt */
    public static final class DefaultImpls {
        public static void onCallCancelled(CallListener callListener) {
        }

        public static void onCallDeclined(CallListener callListener) {
        }

        public static void onDialogButtonClicked(CallListener callListener) {
        }
    }

    void onCallCancelled();

    void onCallDeclined();

    void onDialogButtonClicked();

    void onLiveswitchCallAccepted(CallWrapper callWrapper, LiveswitchCallDataResponse liveswitchCallDataResponse);

    void onTwilioCallAccepted(CallWrapper callWrapper);
}
