package com.forasoft.homewavvisitor.extension;

import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.WorkInfo;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.UploadWorker;
import io.reactivex.SingleEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "info", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: common.kt */
final class CommonKt$toSingle$1$observer$1<T> implements Observer<WorkInfo> {
    final /* synthetic */ SingleEmitter $emitter;

    CommonKt$toSingle$1$observer$1(SingleEmitter singleEmitter) {
        this.$emitter = singleEmitter;
    }

    public final void onChanged(WorkInfo workInfo) {
        if (workInfo != null) {
            Data outputData = workInfo.getOutputData();
            Intrinsics.checkExpressionValueIsNotNull(outputData, "info.outputData");
            int i = CommonKt.WhenMappings.$EnumSwitchMapping$0[workInfo.getState().ordinal()];
            if (i == 1) {
                this.$emitter.onSuccess(workInfo);
            } else if (i == 2) {
                this.$emitter.onError(new Throwable(outputData.getString(UploadWorker.KEY_ERROR_MESSAGE)));
            }
        }
    }
}
