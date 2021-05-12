package com.forasoft.homewavvisitor.dao.converters;

import com.forasoft.homewavvisitor.model.data.Sender;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/converters/SenderConverter;", "", "()V", "fromSender", "", "sender", "Lcom/forasoft/homewavvisitor/model/data/Sender;", "toSender", "value", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SenderConverter.kt */
public final class SenderConverter {
    public final int fromSender(Sender sender) {
        Intrinsics.checkParameterIsNotNull(sender, "sender");
        return sender == Sender.INMATE ? 0 : 1;
    }

    public final Sender toSender(int i) {
        if (i == 0) {
            return Sender.INMATE;
        }
        return Sender.VISITOR;
    }
}
