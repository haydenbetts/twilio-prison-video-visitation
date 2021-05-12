package com.forasoft.homewavvisitor.dao.converters;

import kotlin.Metadata;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/converters/LocalDateTimeConverter;", "", "()V", "fromLocalDateTime", "", "date", "Lorg/threeten/bp/LocalDateTime;", "(Lorg/threeten/bp/LocalDateTime;)Ljava/lang/Long;", "toLocalDateTime", "epochSecond", "(Ljava/lang/Long;)Lorg/threeten/bp/LocalDateTime;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LocalDateTimeConverter.kt */
public final class LocalDateTimeConverter {
    public final Long fromLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Long.valueOf(localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
        }
        return null;
    }

    public final LocalDateTime toLocalDateTime(Long l) {
        if (l != null) {
            return Instant.ofEpochSecond(l.longValue()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }
}
