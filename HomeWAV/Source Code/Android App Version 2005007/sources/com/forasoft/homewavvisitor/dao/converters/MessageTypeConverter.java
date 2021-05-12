package com.forasoft.homewavvisitor.dao.converters;

import com.forasoft.homewavvisitor.model.data.MessageType;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/converters/MessageTypeConverter;", "", "()V", "fromMessageType", "", "messageType", "Lcom/forasoft/homewavvisitor/model/data/MessageType;", "toMessageType", "value", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageTypeConverter.kt */
public final class MessageTypeConverter {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MessageType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MessageType.TEXT.ordinal()] = 1;
            iArr[MessageType.VIDEO.ordinal()] = 2;
            iArr[MessageType.IMAGE.ordinal()] = 3;
        }
    }

    public final int fromMessageType(MessageType messageType) {
        Intrinsics.checkParameterIsNotNull(messageType, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        int i = WhenMappings.$EnumSwitchMapping$0[messageType.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? 3 : 2;
        }
        return 1;
    }

    public final MessageType toMessageType(int i) {
        if (i == 0) {
            return MessageType.TEXT;
        }
        if (i == 1) {
            return MessageType.VIDEO;
        }
        if (i != 2) {
            return MessageType.GIF;
        }
        return MessageType.IMAGE;
    }
}
