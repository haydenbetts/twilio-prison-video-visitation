package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.forasoft.homewavvisitor.model.UploadWorker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.opencv.global.opencv_core;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\bv\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B¯\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010)\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010*\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010+\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010,\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010-\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010.\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010/J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010y\u001a\u00020\u0003HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010~\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0004\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u000b\u0010\u0001\u001a\u00030\u0001HÖ\u0001J\u0017\u0010\u0001\u001a\u00030\u00012\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001HÖ\u0003J\u000b\u0010\u0001\u001a\u00030\u0001HÖ\u0001J\n\u0010\u0001\u001a\u00020\u0003HÖ\u0001J\u001f\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001HÖ\u0001R\u0013\u0010+\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010,\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u00101R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0013\u0010$\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u00101R\u0013\u0010*\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u00101R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b8\u00105R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u00101R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u00101R\u0013\u0010'\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u00101R\u0013\u0010)\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b=\u00101R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u00101R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b?\u00101R\u0013\u0010.\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b@\u00101R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bA\u00101R\u0013\u0010(\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bB\u00101R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bC\u00101R\u0013\u0010-\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bD\u00101R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bE\u00101R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bF\u00101R\u0011\u0010&\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bG\u00101R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bH\u00101R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bI\u00101R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bJ\u00101R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bK\u00101R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bL\u00101R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bM\u00101R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bN\u00101R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bO\u00101R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bP\u00101R\u0013\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bQ\u00101R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bR\u00101R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bS\u00101R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bT\u00101R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bU\u00101R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bV\u00101R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bW\u00101R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bX\u00101R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bY\u00101R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bZ\u00101R\u0013\u0010%\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b[\u00101R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\\\u00101¨\u0006\u0001"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "Landroid/os/Parcelable;", "id", "", "protocol", "disconnect_cause", "visitor_username", "notes", "splicing_priority", "timezone", "splicing_finished", "visitor_id", "visitor_checkin", "beta_splicer", "splicing_outcome", "visitor_location", "prison_name", "splicing_hints", "disconnected", "Lorg/threeten/bp/LocalDateTime;", "ui", "internal_notes", "zone", "station", "reviewed", "etype", "purged", "splicing_started", "inmate_id", "extension_count", "inmate_name", "recorded", "visitor_email", "visitor_station_id", "visitor_name", "tags", "connected", "warning_message", "pubid", "free_seconds", "invalidated", "inmate_checkin", "disconnect_required", "account", "age", "prison_id", "inmate_station_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/threeten/bp/LocalDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/threeten/bp/LocalDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getAge", "getBeta_splicer", "getConnected", "()Lorg/threeten/bp/LocalDateTime;", "getDisconnect_cause", "getDisconnect_required", "getDisconnected", "getEtype", "getExtension_count", "getFree_seconds", "getId", "getInmate_checkin", "getInmate_id", "getInmate_name", "getInmate_station_id", "getInternal_notes", "getInvalidated", "getNotes", "getPrison_id", "getPrison_name", "getProtocol", "getPubid", "getPurged", "getRecorded", "getReviewed", "getSplicing_finished", "getSplicing_hints", "getSplicing_outcome", "getSplicing_priority", "getSplicing_started", "getStation", "getTags", "getTimezone", "getUi", "getVisitor_checkin", "getVisitor_email", "getVisitor_id", "getVisitor_location", "getVisitor_name", "getVisitor_station_id", "getVisitor_username", "getWarning_message", "getZone", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallEntity.kt */
public final class CallEntity implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String account;
    private final String age;
    private final String beta_splicer;
    private final LocalDateTime connected;
    private final String disconnect_cause;
    private final String disconnect_required;
    private final LocalDateTime disconnected;
    private final String etype;
    private final String extension_count;
    private final String free_seconds;
    private final String id;
    private final String inmate_checkin;
    private final String inmate_id;
    private final String inmate_name;
    private final String inmate_station_id;
    private final String internal_notes;
    private final String invalidated;
    private final String notes;
    private final String prison_id;
    private final String prison_name;
    private final String protocol;
    private final String pubid;
    private final String purged;
    private final String recorded;
    private final String reviewed;
    private final String splicing_finished;
    private final String splicing_hints;
    private final String splicing_outcome;
    private final String splicing_priority;
    private final String splicing_started;
    private final String station;
    private final String tags;
    private final String timezone;
    private final String ui;
    private final String visitor_checkin;
    private final String visitor_email;
    private final String visitor_id;
    private final String visitor_location;
    private final String visitor_name;
    private final String visitor_station_id;
    private final String visitor_username;
    private final String warning_message;
    private final String zone;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new CallEntity(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), (LocalDateTime) parcel.readSerializable(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), (LocalDateTime) parcel.readSerializable(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new CallEntity[i];
        }
    }

    public static /* synthetic */ CallEntity copy$default(CallEntity callEntity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, LocalDateTime localDateTime, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, LocalDateTime localDateTime2, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, int i, int i2, Object obj) {
        CallEntity callEntity2 = callEntity;
        int i3 = i;
        int i4 = i2;
        return callEntity.copy((i3 & 1) != 0 ? callEntity2.id : str, (i3 & 2) != 0 ? callEntity2.protocol : str2, (i3 & 4) != 0 ? callEntity2.disconnect_cause : str3, (i3 & 8) != 0 ? callEntity2.visitor_username : str4, (i3 & 16) != 0 ? callEntity2.notes : str5, (i3 & 32) != 0 ? callEntity2.splicing_priority : str6, (i3 & 64) != 0 ? callEntity2.timezone : str7, (i3 & 128) != 0 ? callEntity2.splicing_finished : str8, (i3 & 256) != 0 ? callEntity2.visitor_id : str9, (i3 & 512) != 0 ? callEntity2.visitor_checkin : str10, (i3 & 1024) != 0 ? callEntity2.beta_splicer : str11, (i3 & 2048) != 0 ? callEntity2.splicing_outcome : str12, (i3 & 4096) != 0 ? callEntity2.visitor_location : str13, (i3 & 8192) != 0 ? callEntity2.prison_name : str14, (i3 & 16384) != 0 ? callEntity2.splicing_hints : str15, (i3 & 32768) != 0 ? callEntity2.disconnected : localDateTime, (i3 & 65536) != 0 ? callEntity2.ui : str16, (i3 & 131072) != 0 ? callEntity2.internal_notes : str17, (i3 & 262144) != 0 ? callEntity2.zone : str18, (i3 & 524288) != 0 ? callEntity2.station : str19, (i3 & 1048576) != 0 ? callEntity2.reviewed : str20, (i3 & 2097152) != 0 ? callEntity2.etype : str21, (i3 & 4194304) != 0 ? callEntity2.purged : str22, (i3 & 8388608) != 0 ? callEntity2.splicing_started : str23, (i3 & 16777216) != 0 ? callEntity2.inmate_id : str24, (i3 & opencv_core.ACCESS_WRITE) != 0 ? callEntity2.extension_count : str25, (i3 & 67108864) != 0 ? callEntity2.inmate_name : str26, (i3 & 134217728) != 0 ? callEntity2.recorded : str27, (i3 & 268435456) != 0 ? callEntity2.visitor_email : str28, (i3 & 536870912) != 0 ? callEntity2.visitor_station_id : str29, (i3 & 1073741824) != 0 ? callEntity2.visitor_name : str30, (i3 & Integer.MIN_VALUE) != 0 ? callEntity2.tags : str31, (i4 & 1) != 0 ? callEntity2.connected : localDateTime2, (i4 & 2) != 0 ? callEntity2.warning_message : str32, (i4 & 4) != 0 ? callEntity2.pubid : str33, (i4 & 8) != 0 ? callEntity2.free_seconds : str34, (i4 & 16) != 0 ? callEntity2.invalidated : str35, (i4 & 32) != 0 ? callEntity2.inmate_checkin : str36, (i4 & 64) != 0 ? callEntity2.disconnect_required : str37, (i4 & 128) != 0 ? callEntity2.account : str38, (i4 & 256) != 0 ? callEntity2.age : str39, (i4 & 512) != 0 ? callEntity2.prison_id : str40, (i4 & 1024) != 0 ? callEntity2.inmate_station_id : str41);
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.visitor_checkin;
    }

    public final String component11() {
        return this.beta_splicer;
    }

    public final String component12() {
        return this.splicing_outcome;
    }

    public final String component13() {
        return this.visitor_location;
    }

    public final String component14() {
        return this.prison_name;
    }

    public final String component15() {
        return this.splicing_hints;
    }

    public final LocalDateTime component16() {
        return this.disconnected;
    }

    public final String component17() {
        return this.ui;
    }

    public final String component18() {
        return this.internal_notes;
    }

    public final String component19() {
        return this.zone;
    }

    public final String component2() {
        return this.protocol;
    }

    public final String component20() {
        return this.station;
    }

    public final String component21() {
        return this.reviewed;
    }

    public final String component22() {
        return this.etype;
    }

    public final String component23() {
        return this.purged;
    }

    public final String component24() {
        return this.splicing_started;
    }

    public final String component25() {
        return this.inmate_id;
    }

    public final String component26() {
        return this.extension_count;
    }

    public final String component27() {
        return this.inmate_name;
    }

    public final String component28() {
        return this.recorded;
    }

    public final String component29() {
        return this.visitor_email;
    }

    public final String component3() {
        return this.disconnect_cause;
    }

    public final String component30() {
        return this.visitor_station_id;
    }

    public final String component31() {
        return this.visitor_name;
    }

    public final String component32() {
        return this.tags;
    }

    public final LocalDateTime component33() {
        return this.connected;
    }

    public final String component34() {
        return this.warning_message;
    }

    public final String component35() {
        return this.pubid;
    }

    public final String component36() {
        return this.free_seconds;
    }

    public final String component37() {
        return this.invalidated;
    }

    public final String component38() {
        return this.inmate_checkin;
    }

    public final String component39() {
        return this.disconnect_required;
    }

    public final String component4() {
        return this.visitor_username;
    }

    public final String component40() {
        return this.account;
    }

    public final String component41() {
        return this.age;
    }

    public final String component42() {
        return this.prison_id;
    }

    public final String component43() {
        return this.inmate_station_id;
    }

    public final String component5() {
        return this.notes;
    }

    public final String component6() {
        return this.splicing_priority;
    }

    public final String component7() {
        return this.timezone;
    }

    public final String component8() {
        return this.splicing_finished;
    }

    public final String component9() {
        return this.visitor_id;
    }

    public final CallEntity copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, LocalDateTime localDateTime, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, LocalDateTime localDateTime2, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41) {
        String str42 = str;
        Intrinsics.checkParameterIsNotNull(str42, "id");
        Intrinsics.checkParameterIsNotNull(str33, UploadWorker.KEY_PUB_ID);
        return new CallEntity(str42, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, localDateTime, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, localDateTime2, str32, str33, str34, str35, str36, str37, str38, str39, str40, str41);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallEntity)) {
            return false;
        }
        CallEntity callEntity = (CallEntity) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) callEntity.id) && Intrinsics.areEqual((Object) this.protocol, (Object) callEntity.protocol) && Intrinsics.areEqual((Object) this.disconnect_cause, (Object) callEntity.disconnect_cause) && Intrinsics.areEqual((Object) this.visitor_username, (Object) callEntity.visitor_username) && Intrinsics.areEqual((Object) this.notes, (Object) callEntity.notes) && Intrinsics.areEqual((Object) this.splicing_priority, (Object) callEntity.splicing_priority) && Intrinsics.areEqual((Object) this.timezone, (Object) callEntity.timezone) && Intrinsics.areEqual((Object) this.splicing_finished, (Object) callEntity.splicing_finished) && Intrinsics.areEqual((Object) this.visitor_id, (Object) callEntity.visitor_id) && Intrinsics.areEqual((Object) this.visitor_checkin, (Object) callEntity.visitor_checkin) && Intrinsics.areEqual((Object) this.beta_splicer, (Object) callEntity.beta_splicer) && Intrinsics.areEqual((Object) this.splicing_outcome, (Object) callEntity.splicing_outcome) && Intrinsics.areEqual((Object) this.visitor_location, (Object) callEntity.visitor_location) && Intrinsics.areEqual((Object) this.prison_name, (Object) callEntity.prison_name) && Intrinsics.areEqual((Object) this.splicing_hints, (Object) callEntity.splicing_hints) && Intrinsics.areEqual((Object) this.disconnected, (Object) callEntity.disconnected) && Intrinsics.areEqual((Object) this.ui, (Object) callEntity.ui) && Intrinsics.areEqual((Object) this.internal_notes, (Object) callEntity.internal_notes) && Intrinsics.areEqual((Object) this.zone, (Object) callEntity.zone) && Intrinsics.areEqual((Object) this.station, (Object) callEntity.station) && Intrinsics.areEqual((Object) this.reviewed, (Object) callEntity.reviewed) && Intrinsics.areEqual((Object) this.etype, (Object) callEntity.etype) && Intrinsics.areEqual((Object) this.purged, (Object) callEntity.purged) && Intrinsics.areEqual((Object) this.splicing_started, (Object) callEntity.splicing_started) && Intrinsics.areEqual((Object) this.inmate_id, (Object) callEntity.inmate_id) && Intrinsics.areEqual((Object) this.extension_count, (Object) callEntity.extension_count) && Intrinsics.areEqual((Object) this.inmate_name, (Object) callEntity.inmate_name) && Intrinsics.areEqual((Object) this.recorded, (Object) callEntity.recorded) && Intrinsics.areEqual((Object) this.visitor_email, (Object) callEntity.visitor_email) && Intrinsics.areEqual((Object) this.visitor_station_id, (Object) callEntity.visitor_station_id) && Intrinsics.areEqual((Object) this.visitor_name, (Object) callEntity.visitor_name) && Intrinsics.areEqual((Object) this.tags, (Object) callEntity.tags) && Intrinsics.areEqual((Object) this.connected, (Object) callEntity.connected) && Intrinsics.areEqual((Object) this.warning_message, (Object) callEntity.warning_message) && Intrinsics.areEqual((Object) this.pubid, (Object) callEntity.pubid) && Intrinsics.areEqual((Object) this.free_seconds, (Object) callEntity.free_seconds) && Intrinsics.areEqual((Object) this.invalidated, (Object) callEntity.invalidated) && Intrinsics.areEqual((Object) this.inmate_checkin, (Object) callEntity.inmate_checkin) && Intrinsics.areEqual((Object) this.disconnect_required, (Object) callEntity.disconnect_required) && Intrinsics.areEqual((Object) this.account, (Object) callEntity.account) && Intrinsics.areEqual((Object) this.age, (Object) callEntity.age) && Intrinsics.areEqual((Object) this.prison_id, (Object) callEntity.prison_id) && Intrinsics.areEqual((Object) this.inmate_station_id, (Object) callEntity.inmate_station_id);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.protocol;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.disconnect_cause;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.visitor_username;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.notes;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.splicing_priority;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.timezone;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.splicing_finished;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.visitor_id;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.visitor_checkin;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.beta_splicer;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.splicing_outcome;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.visitor_location;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.prison_name;
        int hashCode14 = (hashCode13 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.splicing_hints;
        int hashCode15 = (hashCode14 + (str15 != null ? str15.hashCode() : 0)) * 31;
        LocalDateTime localDateTime = this.disconnected;
        int hashCode16 = (hashCode15 + (localDateTime != null ? localDateTime.hashCode() : 0)) * 31;
        String str16 = this.ui;
        int hashCode17 = (hashCode16 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.internal_notes;
        int hashCode18 = (hashCode17 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.zone;
        int hashCode19 = (hashCode18 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.station;
        int hashCode20 = (hashCode19 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.reviewed;
        int hashCode21 = (hashCode20 + (str20 != null ? str20.hashCode() : 0)) * 31;
        String str21 = this.etype;
        int hashCode22 = (hashCode21 + (str21 != null ? str21.hashCode() : 0)) * 31;
        String str22 = this.purged;
        int hashCode23 = (hashCode22 + (str22 != null ? str22.hashCode() : 0)) * 31;
        String str23 = this.splicing_started;
        int hashCode24 = (hashCode23 + (str23 != null ? str23.hashCode() : 0)) * 31;
        String str24 = this.inmate_id;
        int hashCode25 = (hashCode24 + (str24 != null ? str24.hashCode() : 0)) * 31;
        String str25 = this.extension_count;
        int hashCode26 = (hashCode25 + (str25 != null ? str25.hashCode() : 0)) * 31;
        String str26 = this.inmate_name;
        int hashCode27 = (hashCode26 + (str26 != null ? str26.hashCode() : 0)) * 31;
        String str27 = this.recorded;
        int hashCode28 = (hashCode27 + (str27 != null ? str27.hashCode() : 0)) * 31;
        String str28 = this.visitor_email;
        int hashCode29 = (hashCode28 + (str28 != null ? str28.hashCode() : 0)) * 31;
        String str29 = this.visitor_station_id;
        int hashCode30 = (hashCode29 + (str29 != null ? str29.hashCode() : 0)) * 31;
        String str30 = this.visitor_name;
        int hashCode31 = (hashCode30 + (str30 != null ? str30.hashCode() : 0)) * 31;
        String str31 = this.tags;
        int hashCode32 = (hashCode31 + (str31 != null ? str31.hashCode() : 0)) * 31;
        LocalDateTime localDateTime2 = this.connected;
        int hashCode33 = (hashCode32 + (localDateTime2 != null ? localDateTime2.hashCode() : 0)) * 31;
        String str32 = this.warning_message;
        int hashCode34 = (hashCode33 + (str32 != null ? str32.hashCode() : 0)) * 31;
        String str33 = this.pubid;
        int hashCode35 = (hashCode34 + (str33 != null ? str33.hashCode() : 0)) * 31;
        String str34 = this.free_seconds;
        int hashCode36 = (hashCode35 + (str34 != null ? str34.hashCode() : 0)) * 31;
        String str35 = this.invalidated;
        int hashCode37 = (hashCode36 + (str35 != null ? str35.hashCode() : 0)) * 31;
        String str36 = this.inmate_checkin;
        int hashCode38 = (hashCode37 + (str36 != null ? str36.hashCode() : 0)) * 31;
        String str37 = this.disconnect_required;
        int hashCode39 = (hashCode38 + (str37 != null ? str37.hashCode() : 0)) * 31;
        String str38 = this.account;
        int hashCode40 = (hashCode39 + (str38 != null ? str38.hashCode() : 0)) * 31;
        String str39 = this.age;
        int hashCode41 = (hashCode40 + (str39 != null ? str39.hashCode() : 0)) * 31;
        String str40 = this.prison_id;
        int hashCode42 = (hashCode41 + (str40 != null ? str40.hashCode() : 0)) * 31;
        String str41 = this.inmate_station_id;
        if (str41 != null) {
            i = str41.hashCode();
        }
        return hashCode42 + i;
    }

    public String toString() {
        return "CallEntity(id=" + this.id + ", protocol=" + this.protocol + ", disconnect_cause=" + this.disconnect_cause + ", visitor_username=" + this.visitor_username + ", notes=" + this.notes + ", splicing_priority=" + this.splicing_priority + ", timezone=" + this.timezone + ", splicing_finished=" + this.splicing_finished + ", visitor_id=" + this.visitor_id + ", visitor_checkin=" + this.visitor_checkin + ", beta_splicer=" + this.beta_splicer + ", splicing_outcome=" + this.splicing_outcome + ", visitor_location=" + this.visitor_location + ", prison_name=" + this.prison_name + ", splicing_hints=" + this.splicing_hints + ", disconnected=" + this.disconnected + ", ui=" + this.ui + ", internal_notes=" + this.internal_notes + ", zone=" + this.zone + ", station=" + this.station + ", reviewed=" + this.reviewed + ", etype=" + this.etype + ", purged=" + this.purged + ", splicing_started=" + this.splicing_started + ", inmate_id=" + this.inmate_id + ", extension_count=" + this.extension_count + ", inmate_name=" + this.inmate_name + ", recorded=" + this.recorded + ", visitor_email=" + this.visitor_email + ", visitor_station_id=" + this.visitor_station_id + ", visitor_name=" + this.visitor_name + ", tags=" + this.tags + ", connected=" + this.connected + ", warning_message=" + this.warning_message + ", pubid=" + this.pubid + ", free_seconds=" + this.free_seconds + ", invalidated=" + this.invalidated + ", inmate_checkin=" + this.inmate_checkin + ", disconnect_required=" + this.disconnect_required + ", account=" + this.account + ", age=" + this.age + ", prison_id=" + this.prison_id + ", inmate_station_id=" + this.inmate_station_id + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.id);
        parcel.writeString(this.protocol);
        parcel.writeString(this.disconnect_cause);
        parcel.writeString(this.visitor_username);
        parcel.writeString(this.notes);
        parcel.writeString(this.splicing_priority);
        parcel.writeString(this.timezone);
        parcel.writeString(this.splicing_finished);
        parcel.writeString(this.visitor_id);
        parcel.writeString(this.visitor_checkin);
        parcel.writeString(this.beta_splicer);
        parcel.writeString(this.splicing_outcome);
        parcel.writeString(this.visitor_location);
        parcel.writeString(this.prison_name);
        parcel.writeString(this.splicing_hints);
        parcel.writeSerializable(this.disconnected);
        parcel.writeString(this.ui);
        parcel.writeString(this.internal_notes);
        parcel.writeString(this.zone);
        parcel.writeString(this.station);
        parcel.writeString(this.reviewed);
        parcel.writeString(this.etype);
        parcel.writeString(this.purged);
        parcel.writeString(this.splicing_started);
        parcel.writeString(this.inmate_id);
        parcel.writeString(this.extension_count);
        parcel.writeString(this.inmate_name);
        parcel.writeString(this.recorded);
        parcel.writeString(this.visitor_email);
        parcel.writeString(this.visitor_station_id);
        parcel.writeString(this.visitor_name);
        parcel.writeString(this.tags);
        parcel.writeSerializable(this.connected);
        parcel.writeString(this.warning_message);
        parcel.writeString(this.pubid);
        parcel.writeString(this.free_seconds);
        parcel.writeString(this.invalidated);
        parcel.writeString(this.inmate_checkin);
        parcel.writeString(this.disconnect_required);
        parcel.writeString(this.account);
        parcel.writeString(this.age);
        parcel.writeString(this.prison_id);
        parcel.writeString(this.inmate_station_id);
    }

    public CallEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, LocalDateTime localDateTime, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, LocalDateTime localDateTime2, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41) {
        String str42 = str33;
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str42, UploadWorker.KEY_PUB_ID);
        this.id = str;
        this.protocol = str2;
        this.disconnect_cause = str3;
        this.visitor_username = str4;
        this.notes = str5;
        this.splicing_priority = str6;
        this.timezone = str7;
        this.splicing_finished = str8;
        this.visitor_id = str9;
        this.visitor_checkin = str10;
        this.beta_splicer = str11;
        this.splicing_outcome = str12;
        this.visitor_location = str13;
        this.prison_name = str14;
        this.splicing_hints = str15;
        this.disconnected = localDateTime;
        this.ui = str16;
        this.internal_notes = str17;
        this.zone = str18;
        this.station = str19;
        this.reviewed = str20;
        this.etype = str21;
        this.purged = str22;
        this.splicing_started = str23;
        this.inmate_id = str24;
        this.extension_count = str25;
        this.inmate_name = str26;
        this.recorded = str27;
        this.visitor_email = str28;
        this.visitor_station_id = str29;
        this.visitor_name = str30;
        this.tags = str31;
        this.connected = localDateTime2;
        this.warning_message = str32;
        this.pubid = str42;
        this.free_seconds = str34;
        this.invalidated = str35;
        this.inmate_checkin = str36;
        this.disconnect_required = str37;
        this.account = str38;
        this.age = str39;
        this.prison_id = str40;
        this.inmate_station_id = str41;
    }

    public final String getId() {
        return this.id;
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public final String getDisconnect_cause() {
        return this.disconnect_cause;
    }

    public final String getVisitor_username() {
        return this.visitor_username;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getSplicing_priority() {
        return this.splicing_priority;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final String getSplicing_finished() {
        return this.splicing_finished;
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }

    public final String getVisitor_checkin() {
        return this.visitor_checkin;
    }

    public final String getBeta_splicer() {
        return this.beta_splicer;
    }

    public final String getSplicing_outcome() {
        return this.splicing_outcome;
    }

    public final String getVisitor_location() {
        return this.visitor_location;
    }

    public final String getPrison_name() {
        return this.prison_name;
    }

    public final String getSplicing_hints() {
        return this.splicing_hints;
    }

    public final LocalDateTime getDisconnected() {
        return this.disconnected;
    }

    public final String getUi() {
        return this.ui;
    }

    public final String getInternal_notes() {
        return this.internal_notes;
    }

    public final String getZone() {
        return this.zone;
    }

    public final String getStation() {
        return this.station;
    }

    public final String getReviewed() {
        return this.reviewed;
    }

    public final String getEtype() {
        return this.etype;
    }

    public final String getPurged() {
        return this.purged;
    }

    public final String getSplicing_started() {
        return this.splicing_started;
    }

    public final String getInmate_id() {
        return this.inmate_id;
    }

    public final String getExtension_count() {
        return this.extension_count;
    }

    public final String getInmate_name() {
        return this.inmate_name;
    }

    public final String getRecorded() {
        return this.recorded;
    }

    public final String getVisitor_email() {
        return this.visitor_email;
    }

    public final String getVisitor_station_id() {
        return this.visitor_station_id;
    }

    public final String getVisitor_name() {
        return this.visitor_name;
    }

    public final String getTags() {
        return this.tags;
    }

    public final LocalDateTime getConnected() {
        return this.connected;
    }

    public final String getWarning_message() {
        return this.warning_message;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getFree_seconds() {
        return this.free_seconds;
    }

    public final String getInvalidated() {
        return this.invalidated;
    }

    public final String getInmate_checkin() {
        return this.inmate_checkin;
    }

    public final String getDisconnect_required() {
        return this.disconnect_required;
    }

    public final String getAccount() {
        return this.account;
    }

    public final String getAge() {
        return this.age;
    }

    public final String getPrison_id() {
        return this.prison_id;
    }

    public final String getInmate_station_id() {
        return this.inmate_station_id;
    }
}
