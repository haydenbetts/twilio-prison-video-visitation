package com.forasoft.homewavvisitor.dao.converters;

import java.util.Date;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/converters/DateConverter;", "", "()V", "fromDate", "", "date", "Ljava/util/Date;", "(Ljava/util/Date;)Ljava/lang/Long;", "toDate", "timestamp", "(Ljava/lang/Long;)Ljava/util/Date;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateConverter.kt */
public final class DateConverter {
    public final Long fromDate(Date date) {
        if (date != null) {
            return Long.valueOf(date.getTime());
        }
        return null;
    }

    public final Date toDate(Long l) {
        if (l != null) {
            return new Date(l.longValue());
        }
        return null;
    }
}
