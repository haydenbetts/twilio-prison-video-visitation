package com.forasoft.homewavvisitor.presentation.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004\"\u0015\u0010\r\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0004\"\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004\"\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\b¨\u0006\u0013"}, d2 = {"asConfirmationDate", "", "Ljava/util/Date;", "getAsConfirmationDate", "(Ljava/util/Date;)Ljava/lang/String;", "asDayMonth", "Lorg/threeten/bp/LocalDateTime;", "getAsDayMonth", "(Lorg/threeten/bp/LocalDateTime;)Ljava/lang/String;", "asDayOfWeek", "getAsDayOfWeek", "asDetailedDate", "getAsDetailedDate", "asDetailedDateTime", "getAsDetailedDateTime", "asFullTime", "getAsFullTime", "asShortTime", "getAsShortTime", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: DateExtensions.kt */
public final class DateExtensionsKt {
    public static final String getAsShortTime(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$asShortTime");
        String format = new SimpleDateFormat("h:mm a", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"h:mm a…ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsDetailedDateTime(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$asDetailedDateTime");
        String format = new SimpleDateFormat("EEEE, MMMM dd - h:mm a", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"EEEE, …ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsFullTime(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$asFullTime");
        String format = new SimpleDateFormat("dd/MM/yyyy, h:mm a", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"dd/MM/…ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsDetailedDate(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$asDetailedDate");
        String format = new SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"EEEE, …ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsConfirmationDate(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$asConfirmationDate");
        String format = new SimpleDateFormat("EEEE, MMMM dd, h:mm a", Locale.ENGLISH).format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"EEEE, …ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsShortTime(LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(localDateTime, "$this$asShortTime");
        String format = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH).format(localDateTime);
        Intrinsics.checkExpressionValueIsNotNull(format, "DateTimeFormatter.ofPatt…ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsDayOfWeek(LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(localDateTime, "$this$asDayOfWeek");
        String format = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH).format(localDateTime);
        Intrinsics.checkExpressionValueIsNotNull(format, "DateTimeFormatter.ofPatt…ale.ENGLISH).format(this)");
        return format;
    }

    public static final String getAsDayMonth(LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(localDateTime, "$this$asDayMonth");
        String format = DateTimeFormatter.ofPattern("MM.dd", Locale.ENGLISH).format(localDateTime);
        Intrinsics.checkExpressionValueIsNotNull(format, "DateTimeFormatter.ofPatt…ale.ENGLISH).format(this)");
        return format;
    }
}
