package com.forasoft.homewavvisitor.dao.converters;

import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/converters/MessageStatusConverter;", "", "()V", "fromStatus", "", "status", "Lcom/forasoft/homewavvisitor/model/data/MessageStatus;", "toStatus", "value", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageStatusConverter.kt */
public final class MessageStatusConverter {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MessageStatus.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MessageStatus.APPROVED.ordinal()] = 1;
            iArr[MessageStatus.PENDING.ordinal()] = 2;
        }
    }

    public final int fromStatus(MessageStatus messageStatus) {
        Intrinsics.checkParameterIsNotNull(messageStatus, NotificationCompat.CATEGORY_STATUS);
        int i = WhenMappings.$EnumSwitchMapping$0[messageStatus.ordinal()];
        if (i != 1) {
            return i != 2 ? 2 : 1;
        }
        return 0;
    }

    public final MessageStatus toStatus(int i) {
        if (i == 0) {
            return MessageStatus.APPROVED;
        }
        if (i != 1) {
            return MessageStatus.READ;
        }
        return MessageStatus.PENDING;
    }
}
