package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.register.InmateShort;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.opencv.global.opencv_core;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0003\bÂ\u0001\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B÷\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0018\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\b\u0010,\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010-\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010.\u001a\u00020\u0003\u0012\u0006\u0010/\u001a\u00020\u0003\u0012\b\u00100\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u00101\u001a\u00020\u0003\u0012\b\u00102\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0003\u0012\u0006\u00105\u001a\u00020\u0003\u0012\u0006\u00106\u001a\u00020\u0003\u0012\u0006\u00107\u001a\u00020\u0003\u0012\u0006\u00108\u001a\u00020\u0003\u0012\u0006\u00109\u001a\u00020\u0003\u0012\u0006\u0010:\u001a\u00020\u0003\u0012\u0006\u0010;\u001a\u00020\u0003\u0012\u0006\u0010<\u001a\u00020\u0003\u0012\u0006\u0010=\u001a\u00020\u0003\u0012\u0006\u0010>\u001a\u00020\u0003\u0012\u0006\u0010?\u001a\u00020\u0003\u0012\b\u0010@\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010A\u001a\u00020\u0003\u0012\u0006\u0010B\u001a\u00020\u0003\u0012\u0006\u0010C\u001a\u00020\u0003\u0012\b\u0010D\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010E\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010F\u001a\u00020\u0018\u0012\u0006\u0010G\u001a\u00020\u0018\u0012\b\u0010H\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010I\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010JJ\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0018HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0018HÆ\u0003J\n\u0010¢\u0001\u001a\u00020\u0018HÆ\u0003J\f\u0010£\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010¤\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¥\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¦\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¨\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010©\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010ª\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010«\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¬\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010­\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010®\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¯\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010°\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010±\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010²\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010³\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010´\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010µ\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¶\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010·\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¸\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¹\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010º\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010»\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¼\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010½\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¾\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¿\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010À\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Â\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ã\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ä\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Å\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Æ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ç\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010È\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010É\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010Ê\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Ë\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010Ì\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Í\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Î\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ï\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Ò\u0001\u001a\u00020\u0018HÆ\u0003J\n\u0010Ó\u0001\u001a\u00020\u0018HÆ\u0003J\f\u0010Ô\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Õ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Ö\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010×\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Ø\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0006\u0010Ù\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00101\u001a\u00020\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u00020\u00032\b\b\u0002\u00107\u001a\u00020\u00032\b\b\u0002\u00108\u001a\u00020\u00032\b\b\u0002\u00109\u001a\u00020\u00032\b\b\u0002\u0010:\u001a\u00020\u00032\b\b\u0002\u0010;\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u00032\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010A\u001a\u00020\u00032\b\b\u0002\u0010B\u001a\u00020\u00032\b\b\u0002\u0010C\u001a\u00020\u00032\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010F\u001a\u00020\u00182\b\b\u0002\u0010G\u001a\u00020\u00182\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u000b\u0010Ú\u0001\u001a\u00030Û\u0001HÖ\u0001J\u0016\u0010Ü\u0001\u001a\u00020\u00182\n\u0010Ý\u0001\u001a\u0005\u0018\u00010Þ\u0001HÖ\u0003J\u000b\u0010ß\u0001\u001a\u00030Û\u0001HÖ\u0001J\u0007\u0010à\u0001\u001a\u00020\u0018J\u0007\u0010á\u0001\u001a\u00020\u0018J\u0007\u0010â\u0001\u001a\u00020\u0018J\u0007\u0010ã\u0001\u001a\u00020\u0018J\u0019\u0010ä\u0001\u001a\u00030å\u00012\u0006\u0010/\u001a\u00020\u00032\u0007\u0010æ\u0001\u001a\u00020\u0003J\t\u0010ç\u0001\u001a\u00020\u0003H\u0016J\u001f\u0010è\u0001\u001a\u00030é\u00012\b\u0010ê\u0001\u001a\u00030ë\u00012\b\u0010ì\u0001\u001a\u00030Û\u0001HÖ\u0001R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bM\u0010LR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bN\u0010LR\u0011\u0010\u001a\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010PR\u0011\u0010\u0019\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\bR\u0010PR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bS\u0010LR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bT\u0010LR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bU\u0010LR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bV\u0010LR\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bW\u0010LR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bX\u0010LR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bY\u0010LR\u0011\u0010\u001e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010LR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b[\u0010LR\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010LR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b]\u0010LR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010LR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b_\u0010LR\u0011\u0010 \u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b`\u0010LR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\ba\u0010LR\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bb\u0010LR\u001c\u0010\"\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010L\"\u0004\bd\u0010eR\u0011\u0010#\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010LR\u0013\u0010$\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010LR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bf\u0010LR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bg\u0010LR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bh\u0010LR\u0013\u0010%\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bi\u0010LR\u0011\u0010&\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bj\u0010LR\u0013\u0010-\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bk\u0010LR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bl\u0010LR\u0011\u0010'\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bm\u0010LR\u0011\u0010(\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bn\u0010LR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bo\u0010LR\u0018\u0010I\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010LR\u0013\u0010,\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bq\u0010LR\u0011\u0010+\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\br\u0010LR\u0011\u0010)\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bs\u0010LR\u0011\u0010*\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bt\u0010LR\u0013\u0010H\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bu\u0010LR\u0011\u0010F\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\bv\u0010PR\u0011\u0010G\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\bw\u0010PR\u0011\u0010.\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bx\u0010LR\u0011\u0010/\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\by\u0010LR\u0013\u00100\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bz\u0010LR\u0011\u00101\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b{\u0010LR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b|\u0010LR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b}\u0010LR\u0013\u00102\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b~\u0010LR\u0011\u00103\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010LR\u0012\u00104\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u00105\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u00106\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u00107\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010\u0004\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u00108\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u00109\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010:\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010;\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010<\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010=\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010>\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010?\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0014\u0010@\u001a\u0004\u0018\u00010\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010A\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010B\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0012\u0010C\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0014\u0010D\u001a\u0004\u0018\u00010\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010LR\u0014\u0010E\u001a\u0004\u0018\u00010\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010L¨\u0006í\u0001"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Landroid/os/Parcelable;", "id", "", "visitor_id", "created", "dob", "dob_formatted", "first_name", "identifier", "inmate_location", "last_name", "full_name", "location", "middle_name", "prison_id", "prison_name", "suffix", "approved", "credit_balance", "status", "calls_disabled", "calls_disabled_until", "can_text_message", "", "can_video_message", "can_image_message", "deleted", "deleted_warnings", "description", "error_reported", "fmt_name", "inmate_general_funds_auto_recharge_enabled", "internal_notes", "invisible", "is_fraud", "is_fraud_set_by", "notes", "occupant_id", "prison_max_text_message_length", "prison_max_video_message_length", "prison_price_per_text_message", "prison_price_per_video_message", "prison_price_per_image_message", "prison_price_per_gif_message", "per_minute_charging_enabled", "pubid", "relationship", "require_approval", "should_record", "tags", "talk_to_me_funds_auto_recharge_enabled", "visitor_city", "visitor_created", "visitor_dob", "visitor_first_name", "visitor_last_name", "visitor_name", "visitor_phone", "visitor_pin", "visitor_should_record", "visitor_state", "visitor_state_abbreviation", "visitor_street1", "visitor_street2", "visitor_username", "visitor_zipcode", "voice_calls_disabled", "voice_calls_disabled_until", "zone", "prison_video_calls_disabled", "prison_voice_calls_disabled", "prison_state", "prison_payment_gateway", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;)V", "getApproved", "()Ljava/lang/String;", "getCalls_disabled", "getCalls_disabled_until", "getCan_image_message", "()Z", "getCan_text_message", "getCan_video_message", "getCreated", "getCredit_balance", "getDeleted", "getDeleted_warnings", "getDescription", "getDob", "getDob_formatted", "getError_reported", "getFirst_name", "getFmt_name", "getFull_name", "getId", "getIdentifier", "getInmate_general_funds_auto_recharge_enabled", "getInmate_location", "getInternal_notes", "getInvisible", "setInvisible", "(Ljava/lang/String;)V", "getLast_name", "getLocation", "getMiddle_name", "getNotes", "getOccupant_id", "getPer_minute_charging_enabled", "getPrison_id", "getPrison_max_text_message_length", "getPrison_max_video_message_length", "getPrison_name", "getPrison_payment_gateway", "getPrison_price_per_gif_message", "getPrison_price_per_image_message", "getPrison_price_per_text_message", "getPrison_price_per_video_message", "getPrison_state", "getPrison_video_calls_disabled", "getPrison_voice_calls_disabled", "getPubid", "getRelationship", "getRequire_approval", "getShould_record", "getStatus", "getSuffix", "getTags", "getTalk_to_me_funds_auto_recharge_enabled", "getVisitor_city", "getVisitor_created", "getVisitor_dob", "getVisitor_first_name", "getVisitor_id", "getVisitor_last_name", "getVisitor_name", "getVisitor_phone", "getVisitor_pin", "getVisitor_should_record", "getVisitor_state", "getVisitor_state_abbreviation", "getVisitor_street1", "getVisitor_street2", "getVisitor_username", "getVisitor_zipcode", "getVoice_calls_disabled", "getVoice_calls_disabled_until", "getZone", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "component7", "component70", "component8", "component9", "copy", "describeContents", "", "equals", "other", "", "hashCode", "isApproved", "isFraud", "isVideoCallsDisabled", "isVoiceCallsDisabled", "toShortForm", "Lcom/forasoft/homewavvisitor/model/data/register/InmateShort;", "explanation", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Inmate.kt */
public final class Inmate implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String approved;
    private final String calls_disabled;
    private final String calls_disabled_until;
    private final boolean can_image_message;
    private final boolean can_text_message;
    private final boolean can_video_message;
    private final String created;
    private final String credit_balance;
    private final String deleted;
    private final String deleted_warnings;
    private final String description;
    private final String dob;
    private final String dob_formatted;
    private final String error_reported;
    private final String first_name;
    private final String fmt_name;
    private final String full_name;
    private final String id;
    private final String identifier;
    private final String inmate_general_funds_auto_recharge_enabled;
    private final String inmate_location;
    private final String internal_notes;
    private String invisible;
    private final String is_fraud;
    private final String is_fraud_set_by;
    private final String last_name;
    private final String location;
    private final String middle_name;
    private final String notes;
    private final String occupant_id;
    private final String per_minute_charging_enabled;
    private final String prison_id;
    private final String prison_max_text_message_length;
    private final String prison_max_video_message_length;
    private final String prison_name;
    @SerializedName("payment_gateway")
    private final String prison_payment_gateway;
    private final String prison_price_per_gif_message;
    private final String prison_price_per_image_message;
    private final String prison_price_per_text_message;
    private final String prison_price_per_video_message;
    private final String prison_state;
    private final boolean prison_video_calls_disabled;
    private final boolean prison_voice_calls_disabled;
    private final String pubid;
    private final String relationship;
    private final String require_approval;
    private final String should_record;
    private final String status;
    private final String suffix;
    private final String tags;
    private final String talk_to_me_funds_auto_recharge_enabled;
    private final String visitor_city;
    private final String visitor_created;
    private final String visitor_dob;
    private final String visitor_first_name;
    private final String visitor_id;
    private final String visitor_last_name;
    private final String visitor_name;
    private final String visitor_phone;
    private final String visitor_pin;
    private final String visitor_should_record;
    private final String visitor_state;
    private final String visitor_state_abbreviation;
    private final String visitor_street1;
    private final String visitor_street2;
    private final String visitor_username;
    private final String visitor_zipcode;
    private final String voice_calls_disabled;
    private final String voice_calls_disabled_until;
    private final String zone;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new Inmate(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new Inmate[i];
        }
    }

    public static /* synthetic */ Inmate copy$default(Inmate inmate, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, boolean z, boolean z2, boolean z3, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, String str59, String str60, String str61, String str62, String str63, boolean z4, boolean z5, String str64, String str65, int i, int i2, int i3, Object obj) {
        Inmate inmate2 = inmate;
        int i4 = i;
        int i5 = i2;
        return inmate.copy((i4 & 1) != 0 ? inmate2.id : str, (i4 & 2) != 0 ? inmate2.visitor_id : str2, (i4 & 4) != 0 ? inmate2.created : str3, (i4 & 8) != 0 ? inmate2.dob : str4, (i4 & 16) != 0 ? inmate2.dob_formatted : str5, (i4 & 32) != 0 ? inmate2.first_name : str6, (i4 & 64) != 0 ? inmate2.identifier : str7, (i4 & 128) != 0 ? inmate2.inmate_location : str8, (i4 & 256) != 0 ? inmate2.last_name : str9, (i4 & 512) != 0 ? inmate2.full_name : str10, (i4 & 1024) != 0 ? inmate2.location : str11, (i4 & 2048) != 0 ? inmate2.middle_name : str12, (i4 & 4096) != 0 ? inmate2.prison_id : str13, (i4 & 8192) != 0 ? inmate2.prison_name : str14, (i4 & 16384) != 0 ? inmate2.suffix : str15, (i4 & 32768) != 0 ? inmate2.approved : str16, (i4 & 65536) != 0 ? inmate2.credit_balance : str17, (i4 & 131072) != 0 ? inmate2.status : str18, (i4 & 262144) != 0 ? inmate2.calls_disabled : str19, (i4 & 524288) != 0 ? inmate2.calls_disabled_until : str20, (i4 & 1048576) != 0 ? inmate2.can_text_message : z, (i4 & 2097152) != 0 ? inmate2.can_video_message : z2, (i4 & 4194304) != 0 ? inmate2.can_image_message : z3, (i4 & 8388608) != 0 ? inmate2.deleted : str21, (i4 & 16777216) != 0 ? inmate2.deleted_warnings : str22, (i4 & opencv_core.ACCESS_WRITE) != 0 ? inmate2.description : str23, (i4 & 67108864) != 0 ? inmate2.error_reported : str24, (i4 & 134217728) != 0 ? inmate2.fmt_name : str25, (i4 & 268435456) != 0 ? inmate2.inmate_general_funds_auto_recharge_enabled : str26, (i4 & 536870912) != 0 ? inmate2.internal_notes : str27, (i4 & 1073741824) != 0 ? inmate2.invisible : str28, (i4 & Integer.MIN_VALUE) != 0 ? inmate2.is_fraud : str29, (i5 & 1) != 0 ? inmate2.is_fraud_set_by : str30, (i5 & 2) != 0 ? inmate2.notes : str31, (i5 & 4) != 0 ? inmate2.occupant_id : str32, (i5 & 8) != 0 ? inmate2.prison_max_text_message_length : str33, (i5 & 16) != 0 ? inmate2.prison_max_video_message_length : str34, (i5 & 32) != 0 ? inmate2.prison_price_per_text_message : str35, (i5 & 64) != 0 ? inmate2.prison_price_per_video_message : str36, (i5 & 128) != 0 ? inmate2.prison_price_per_image_message : str37, (i5 & 256) != 0 ? inmate2.prison_price_per_gif_message : str38, (i5 & 512) != 0 ? inmate2.per_minute_charging_enabled : str39, (i5 & 1024) != 0 ? inmate2.pubid : str40, (i5 & 2048) != 0 ? inmate2.relationship : str41, (i5 & 4096) != 0 ? inmate2.require_approval : str42, (i5 & 8192) != 0 ? inmate2.should_record : str43, (i5 & 16384) != 0 ? inmate2.tags : str44, (i5 & 32768) != 0 ? inmate2.talk_to_me_funds_auto_recharge_enabled : str45, (i5 & 65536) != 0 ? inmate2.visitor_city : str46, (i5 & 131072) != 0 ? inmate2.visitor_created : str47, (i5 & 262144) != 0 ? inmate2.visitor_dob : str48, (i5 & 524288) != 0 ? inmate2.visitor_first_name : str49, (i5 & 1048576) != 0 ? inmate2.visitor_last_name : str50, (i5 & 2097152) != 0 ? inmate2.visitor_name : str51, (i5 & 4194304) != 0 ? inmate2.visitor_phone : str52, (i5 & 8388608) != 0 ? inmate2.visitor_pin : str53, (i5 & 16777216) != 0 ? inmate2.visitor_should_record : str54, (i5 & opencv_core.ACCESS_WRITE) != 0 ? inmate2.visitor_state : str55, (i5 & 67108864) != 0 ? inmate2.visitor_state_abbreviation : str56, (i5 & 134217728) != 0 ? inmate2.visitor_street1 : str57, (i5 & 268435456) != 0 ? inmate2.visitor_street2 : str58, (i5 & 536870912) != 0 ? inmate2.visitor_username : str59, (i5 & 1073741824) != 0 ? inmate2.visitor_zipcode : str60, (i5 & Integer.MIN_VALUE) != 0 ? inmate2.voice_calls_disabled : str61, (i3 & 1) != 0 ? inmate2.voice_calls_disabled_until : str62, (i3 & 2) != 0 ? inmate2.zone : str63, (i3 & 4) != 0 ? inmate2.prison_video_calls_disabled : z4, (i3 & 8) != 0 ? inmate2.prison_voice_calls_disabled : z5, (i3 & 16) != 0 ? inmate2.prison_state : str64, (i3 & 32) != 0 ? inmate2.prison_payment_gateway : str65);
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.full_name;
    }

    public final String component11() {
        return this.location;
    }

    public final String component12() {
        return this.middle_name;
    }

    public final String component13() {
        return this.prison_id;
    }

    public final String component14() {
        return this.prison_name;
    }

    public final String component15() {
        return this.suffix;
    }

    public final String component16() {
        return this.approved;
    }

    public final String component17() {
        return this.credit_balance;
    }

    public final String component18() {
        return this.status;
    }

    public final String component19() {
        return this.calls_disabled;
    }

    public final String component2() {
        return this.visitor_id;
    }

    public final String component20() {
        return this.calls_disabled_until;
    }

    public final boolean component21() {
        return this.can_text_message;
    }

    public final boolean component22() {
        return this.can_video_message;
    }

    public final boolean component23() {
        return this.can_image_message;
    }

    public final String component24() {
        return this.deleted;
    }

    public final String component25() {
        return this.deleted_warnings;
    }

    public final String component26() {
        return this.description;
    }

    public final String component27() {
        return this.error_reported;
    }

    public final String component28() {
        return this.fmt_name;
    }

    public final String component29() {
        return this.inmate_general_funds_auto_recharge_enabled;
    }

    public final String component3() {
        return this.created;
    }

    public final String component30() {
        return this.internal_notes;
    }

    public final String component31() {
        return this.invisible;
    }

    public final String component32() {
        return this.is_fraud;
    }

    public final String component33() {
        return this.is_fraud_set_by;
    }

    public final String component34() {
        return this.notes;
    }

    public final String component35() {
        return this.occupant_id;
    }

    public final String component36() {
        return this.prison_max_text_message_length;
    }

    public final String component37() {
        return this.prison_max_video_message_length;
    }

    public final String component38() {
        return this.prison_price_per_text_message;
    }

    public final String component39() {
        return this.prison_price_per_video_message;
    }

    public final String component4() {
        return this.dob;
    }

    public final String component40() {
        return this.prison_price_per_image_message;
    }

    public final String component41() {
        return this.prison_price_per_gif_message;
    }

    public final String component42() {
        return this.per_minute_charging_enabled;
    }

    public final String component43() {
        return this.pubid;
    }

    public final String component44() {
        return this.relationship;
    }

    public final String component45() {
        return this.require_approval;
    }

    public final String component46() {
        return this.should_record;
    }

    public final String component47() {
        return this.tags;
    }

    public final String component48() {
        return this.talk_to_me_funds_auto_recharge_enabled;
    }

    public final String component49() {
        return this.visitor_city;
    }

    public final String component5() {
        return this.dob_formatted;
    }

    public final String component50() {
        return this.visitor_created;
    }

    public final String component51() {
        return this.visitor_dob;
    }

    public final String component52() {
        return this.visitor_first_name;
    }

    public final String component53() {
        return this.visitor_last_name;
    }

    public final String component54() {
        return this.visitor_name;
    }

    public final String component55() {
        return this.visitor_phone;
    }

    public final String component56() {
        return this.visitor_pin;
    }

    public final String component57() {
        return this.visitor_should_record;
    }

    public final String component58() {
        return this.visitor_state;
    }

    public final String component59() {
        return this.visitor_state_abbreviation;
    }

    public final String component6() {
        return this.first_name;
    }

    public final String component60() {
        return this.visitor_street1;
    }

    public final String component61() {
        return this.visitor_street2;
    }

    public final String component62() {
        return this.visitor_username;
    }

    public final String component63() {
        return this.visitor_zipcode;
    }

    public final String component64() {
        return this.voice_calls_disabled;
    }

    public final String component65() {
        return this.voice_calls_disabled_until;
    }

    public final String component66() {
        return this.zone;
    }

    public final boolean component67() {
        return this.prison_video_calls_disabled;
    }

    public final boolean component68() {
        return this.prison_voice_calls_disabled;
    }

    public final String component69() {
        return this.prison_state;
    }

    public final String component7() {
        return this.identifier;
    }

    public final String component70() {
        return this.prison_payment_gateway;
    }

    public final String component8() {
        return this.inmate_location;
    }

    public final String component9() {
        return this.last_name;
    }

    public final Inmate copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, boolean z, boolean z2, boolean z3, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, String str59, String str60, String str61, String str62, String str63, boolean z4, boolean z5, String str64, String str65) {
        String str66 = str;
        Intrinsics.checkParameterIsNotNull(str66, "id");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str23, "description");
        Intrinsics.checkParameterIsNotNull(str24, "error_reported");
        Intrinsics.checkParameterIsNotNull(str25, "fmt_name");
        Intrinsics.checkParameterIsNotNull(str26, "inmate_general_funds_auto_recharge_enabled");
        Intrinsics.checkParameterIsNotNull(str29, "is_fraud");
        Intrinsics.checkParameterIsNotNull(str32, "occupant_id");
        Intrinsics.checkParameterIsNotNull(str33, "prison_max_text_message_length");
        Intrinsics.checkParameterIsNotNull(str34, "prison_max_video_message_length");
        Intrinsics.checkParameterIsNotNull(str35, "prison_price_per_text_message");
        Intrinsics.checkParameterIsNotNull(str36, "prison_price_per_video_message");
        Intrinsics.checkParameterIsNotNull(str37, "prison_price_per_image_message");
        Intrinsics.checkParameterIsNotNull(str40, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str41, "relationship");
        Intrinsics.checkParameterIsNotNull(str43, "should_record");
        Intrinsics.checkParameterIsNotNull(str45, "talk_to_me_funds_auto_recharge_enabled");
        Intrinsics.checkParameterIsNotNull(str46, "visitor_city");
        Intrinsics.checkParameterIsNotNull(str47, "visitor_created");
        Intrinsics.checkParameterIsNotNull(str48, "visitor_dob");
        Intrinsics.checkParameterIsNotNull(str49, "visitor_first_name");
        Intrinsics.checkParameterIsNotNull(str50, "visitor_last_name");
        Intrinsics.checkParameterIsNotNull(str51, "visitor_name");
        Intrinsics.checkParameterIsNotNull(str52, "visitor_phone");
        Intrinsics.checkParameterIsNotNull(str53, "visitor_pin");
        Intrinsics.checkParameterIsNotNull(str54, "visitor_should_record");
        Intrinsics.checkParameterIsNotNull(str55, "visitor_state");
        Intrinsics.checkParameterIsNotNull(str56, "visitor_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str57, "visitor_street1");
        Intrinsics.checkParameterIsNotNull(str59, "visitor_username");
        Intrinsics.checkParameterIsNotNull(str60, "visitor_zipcode");
        Intrinsics.checkParameterIsNotNull(str61, "voice_calls_disabled");
        return new Inmate(str66, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, z, z2, z3, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32, str33, str34, str35, str36, str37, str38, str39, str40, str41, str42, str43, str44, str45, str46, str47, str48, str49, str50, str51, str52, str53, str54, str55, str56, str57, str58, str59, str60, str61, str62, str63, z4, z5, str64, str65);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Inmate)) {
            return false;
        }
        Inmate inmate = (Inmate) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) inmate.id) && Intrinsics.areEqual((Object) this.visitor_id, (Object) inmate.visitor_id) && Intrinsics.areEqual((Object) this.created, (Object) inmate.created) && Intrinsics.areEqual((Object) this.dob, (Object) inmate.dob) && Intrinsics.areEqual((Object) this.dob_formatted, (Object) inmate.dob_formatted) && Intrinsics.areEqual((Object) this.first_name, (Object) inmate.first_name) && Intrinsics.areEqual((Object) this.identifier, (Object) inmate.identifier) && Intrinsics.areEqual((Object) this.inmate_location, (Object) inmate.inmate_location) && Intrinsics.areEqual((Object) this.last_name, (Object) inmate.last_name) && Intrinsics.areEqual((Object) this.full_name, (Object) inmate.full_name) && Intrinsics.areEqual((Object) this.location, (Object) inmate.location) && Intrinsics.areEqual((Object) this.middle_name, (Object) inmate.middle_name) && Intrinsics.areEqual((Object) this.prison_id, (Object) inmate.prison_id) && Intrinsics.areEqual((Object) this.prison_name, (Object) inmate.prison_name) && Intrinsics.areEqual((Object) this.suffix, (Object) inmate.suffix) && Intrinsics.areEqual((Object) this.approved, (Object) inmate.approved) && Intrinsics.areEqual((Object) this.credit_balance, (Object) inmate.credit_balance) && Intrinsics.areEqual((Object) this.status, (Object) inmate.status) && Intrinsics.areEqual((Object) this.calls_disabled, (Object) inmate.calls_disabled) && Intrinsics.areEqual((Object) this.calls_disabled_until, (Object) inmate.calls_disabled_until) && this.can_text_message == inmate.can_text_message && this.can_video_message == inmate.can_video_message && this.can_image_message == inmate.can_image_message && Intrinsics.areEqual((Object) this.deleted, (Object) inmate.deleted) && Intrinsics.areEqual((Object) this.deleted_warnings, (Object) inmate.deleted_warnings) && Intrinsics.areEqual((Object) this.description, (Object) inmate.description) && Intrinsics.areEqual((Object) this.error_reported, (Object) inmate.error_reported) && Intrinsics.areEqual((Object) this.fmt_name, (Object) inmate.fmt_name) && Intrinsics.areEqual((Object) this.inmate_general_funds_auto_recharge_enabled, (Object) inmate.inmate_general_funds_auto_recharge_enabled) && Intrinsics.areEqual((Object) this.internal_notes, (Object) inmate.internal_notes) && Intrinsics.areEqual((Object) this.invisible, (Object) inmate.invisible) && Intrinsics.areEqual((Object) this.is_fraud, (Object) inmate.is_fraud) && Intrinsics.areEqual((Object) this.is_fraud_set_by, (Object) inmate.is_fraud_set_by) && Intrinsics.areEqual((Object) this.notes, (Object) inmate.notes) && Intrinsics.areEqual((Object) this.occupant_id, (Object) inmate.occupant_id) && Intrinsics.areEqual((Object) this.prison_max_text_message_length, (Object) inmate.prison_max_text_message_length) && Intrinsics.areEqual((Object) this.prison_max_video_message_length, (Object) inmate.prison_max_video_message_length) && Intrinsics.areEqual((Object) this.prison_price_per_text_message, (Object) inmate.prison_price_per_text_message) && Intrinsics.areEqual((Object) this.prison_price_per_video_message, (Object) inmate.prison_price_per_video_message) && Intrinsics.areEqual((Object) this.prison_price_per_image_message, (Object) inmate.prison_price_per_image_message) && Intrinsics.areEqual((Object) this.prison_price_per_gif_message, (Object) inmate.prison_price_per_gif_message) && Intrinsics.areEqual((Object) this.per_minute_charging_enabled, (Object) inmate.per_minute_charging_enabled) && Intrinsics.areEqual((Object) this.pubid, (Object) inmate.pubid) && Intrinsics.areEqual((Object) this.relationship, (Object) inmate.relationship) && Intrinsics.areEqual((Object) this.require_approval, (Object) inmate.require_approval) && Intrinsics.areEqual((Object) this.should_record, (Object) inmate.should_record) && Intrinsics.areEqual((Object) this.tags, (Object) inmate.tags) && Intrinsics.areEqual((Object) this.talk_to_me_funds_auto_recharge_enabled, (Object) inmate.talk_to_me_funds_auto_recharge_enabled) && Intrinsics.areEqual((Object) this.visitor_city, (Object) inmate.visitor_city) && Intrinsics.areEqual((Object) this.visitor_created, (Object) inmate.visitor_created) && Intrinsics.areEqual((Object) this.visitor_dob, (Object) inmate.visitor_dob) && Intrinsics.areEqual((Object) this.visitor_first_name, (Object) inmate.visitor_first_name) && Intrinsics.areEqual((Object) this.visitor_last_name, (Object) inmate.visitor_last_name) && Intrinsics.areEqual((Object) this.visitor_name, (Object) inmate.visitor_name) && Intrinsics.areEqual((Object) this.visitor_phone, (Object) inmate.visitor_phone) && Intrinsics.areEqual((Object) this.visitor_pin, (Object) inmate.visitor_pin) && Intrinsics.areEqual((Object) this.visitor_should_record, (Object) inmate.visitor_should_record) && Intrinsics.areEqual((Object) this.visitor_state, (Object) inmate.visitor_state) && Intrinsics.areEqual((Object) this.visitor_state_abbreviation, (Object) inmate.visitor_state_abbreviation) && Intrinsics.areEqual((Object) this.visitor_street1, (Object) inmate.visitor_street1) && Intrinsics.areEqual((Object) this.visitor_street2, (Object) inmate.visitor_street2) && Intrinsics.areEqual((Object) this.visitor_username, (Object) inmate.visitor_username) && Intrinsics.areEqual((Object) this.visitor_zipcode, (Object) inmate.visitor_zipcode) && Intrinsics.areEqual((Object) this.voice_calls_disabled, (Object) inmate.voice_calls_disabled) && Intrinsics.areEqual((Object) this.voice_calls_disabled_until, (Object) inmate.voice_calls_disabled_until) && Intrinsics.areEqual((Object) this.zone, (Object) inmate.zone) && this.prison_video_calls_disabled == inmate.prison_video_calls_disabled && this.prison_voice_calls_disabled == inmate.prison_voice_calls_disabled && Intrinsics.areEqual((Object) this.prison_state, (Object) inmate.prison_state) && Intrinsics.areEqual((Object) this.prison_payment_gateway, (Object) inmate.prison_payment_gateway);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.visitor_id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.created;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.dob;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.dob_formatted;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.first_name;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.identifier;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.inmate_location;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.last_name;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.full_name;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.location;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.middle_name;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.prison_id;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.prison_name;
        int hashCode14 = (hashCode13 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.suffix;
        int hashCode15 = (hashCode14 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.approved;
        int hashCode16 = (hashCode15 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.credit_balance;
        int hashCode17 = (hashCode16 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.status;
        int hashCode18 = (hashCode17 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.calls_disabled;
        int hashCode19 = (hashCode18 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.calls_disabled_until;
        int hashCode20 = (hashCode19 + (str20 != null ? str20.hashCode() : 0)) * 31;
        boolean z = this.can_text_message;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode20 + (z ? 1 : 0)) * 31;
        boolean z3 = this.can_video_message;
        if (z3) {
            z3 = true;
        }
        int i3 = (i2 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.can_image_message;
        if (z4) {
            z4 = true;
        }
        int i4 = (i3 + (z4 ? 1 : 0)) * 31;
        String str21 = this.deleted;
        int hashCode21 = (i4 + (str21 != null ? str21.hashCode() : 0)) * 31;
        String str22 = this.deleted_warnings;
        int hashCode22 = (hashCode21 + (str22 != null ? str22.hashCode() : 0)) * 31;
        String str23 = this.description;
        int hashCode23 = (hashCode22 + (str23 != null ? str23.hashCode() : 0)) * 31;
        String str24 = this.error_reported;
        int hashCode24 = (hashCode23 + (str24 != null ? str24.hashCode() : 0)) * 31;
        String str25 = this.fmt_name;
        int hashCode25 = (hashCode24 + (str25 != null ? str25.hashCode() : 0)) * 31;
        String str26 = this.inmate_general_funds_auto_recharge_enabled;
        int hashCode26 = (hashCode25 + (str26 != null ? str26.hashCode() : 0)) * 31;
        String str27 = this.internal_notes;
        int hashCode27 = (hashCode26 + (str27 != null ? str27.hashCode() : 0)) * 31;
        String str28 = this.invisible;
        int hashCode28 = (hashCode27 + (str28 != null ? str28.hashCode() : 0)) * 31;
        String str29 = this.is_fraud;
        int hashCode29 = (hashCode28 + (str29 != null ? str29.hashCode() : 0)) * 31;
        String str30 = this.is_fraud_set_by;
        int hashCode30 = (hashCode29 + (str30 != null ? str30.hashCode() : 0)) * 31;
        String str31 = this.notes;
        int hashCode31 = (hashCode30 + (str31 != null ? str31.hashCode() : 0)) * 31;
        String str32 = this.occupant_id;
        int hashCode32 = (hashCode31 + (str32 != null ? str32.hashCode() : 0)) * 31;
        String str33 = this.prison_max_text_message_length;
        int hashCode33 = (hashCode32 + (str33 != null ? str33.hashCode() : 0)) * 31;
        String str34 = this.prison_max_video_message_length;
        int hashCode34 = (hashCode33 + (str34 != null ? str34.hashCode() : 0)) * 31;
        String str35 = this.prison_price_per_text_message;
        int hashCode35 = (hashCode34 + (str35 != null ? str35.hashCode() : 0)) * 31;
        String str36 = this.prison_price_per_video_message;
        int hashCode36 = (hashCode35 + (str36 != null ? str36.hashCode() : 0)) * 31;
        String str37 = this.prison_price_per_image_message;
        int hashCode37 = (hashCode36 + (str37 != null ? str37.hashCode() : 0)) * 31;
        String str38 = this.prison_price_per_gif_message;
        int hashCode38 = (hashCode37 + (str38 != null ? str38.hashCode() : 0)) * 31;
        String str39 = this.per_minute_charging_enabled;
        int hashCode39 = (hashCode38 + (str39 != null ? str39.hashCode() : 0)) * 31;
        String str40 = this.pubid;
        int hashCode40 = (hashCode39 + (str40 != null ? str40.hashCode() : 0)) * 31;
        String str41 = this.relationship;
        int hashCode41 = (hashCode40 + (str41 != null ? str41.hashCode() : 0)) * 31;
        String str42 = this.require_approval;
        int hashCode42 = (hashCode41 + (str42 != null ? str42.hashCode() : 0)) * 31;
        String str43 = this.should_record;
        int hashCode43 = (hashCode42 + (str43 != null ? str43.hashCode() : 0)) * 31;
        String str44 = this.tags;
        int hashCode44 = (hashCode43 + (str44 != null ? str44.hashCode() : 0)) * 31;
        String str45 = this.talk_to_me_funds_auto_recharge_enabled;
        int hashCode45 = (hashCode44 + (str45 != null ? str45.hashCode() : 0)) * 31;
        String str46 = this.visitor_city;
        int hashCode46 = (hashCode45 + (str46 != null ? str46.hashCode() : 0)) * 31;
        String str47 = this.visitor_created;
        int hashCode47 = (hashCode46 + (str47 != null ? str47.hashCode() : 0)) * 31;
        String str48 = this.visitor_dob;
        int hashCode48 = (hashCode47 + (str48 != null ? str48.hashCode() : 0)) * 31;
        String str49 = this.visitor_first_name;
        int hashCode49 = (hashCode48 + (str49 != null ? str49.hashCode() : 0)) * 31;
        String str50 = this.visitor_last_name;
        int hashCode50 = (hashCode49 + (str50 != null ? str50.hashCode() : 0)) * 31;
        String str51 = this.visitor_name;
        int hashCode51 = (hashCode50 + (str51 != null ? str51.hashCode() : 0)) * 31;
        String str52 = this.visitor_phone;
        int hashCode52 = (hashCode51 + (str52 != null ? str52.hashCode() : 0)) * 31;
        String str53 = this.visitor_pin;
        int hashCode53 = (hashCode52 + (str53 != null ? str53.hashCode() : 0)) * 31;
        String str54 = this.visitor_should_record;
        int hashCode54 = (hashCode53 + (str54 != null ? str54.hashCode() : 0)) * 31;
        String str55 = this.visitor_state;
        int hashCode55 = (hashCode54 + (str55 != null ? str55.hashCode() : 0)) * 31;
        String str56 = this.visitor_state_abbreviation;
        int hashCode56 = (hashCode55 + (str56 != null ? str56.hashCode() : 0)) * 31;
        String str57 = this.visitor_street1;
        int hashCode57 = (hashCode56 + (str57 != null ? str57.hashCode() : 0)) * 31;
        String str58 = this.visitor_street2;
        int hashCode58 = (hashCode57 + (str58 != null ? str58.hashCode() : 0)) * 31;
        String str59 = this.visitor_username;
        int hashCode59 = (hashCode58 + (str59 != null ? str59.hashCode() : 0)) * 31;
        String str60 = this.visitor_zipcode;
        int hashCode60 = (hashCode59 + (str60 != null ? str60.hashCode() : 0)) * 31;
        String str61 = this.voice_calls_disabled;
        int hashCode61 = (hashCode60 + (str61 != null ? str61.hashCode() : 0)) * 31;
        String str62 = this.voice_calls_disabled_until;
        int hashCode62 = (hashCode61 + (str62 != null ? str62.hashCode() : 0)) * 31;
        String str63 = this.zone;
        int hashCode63 = (hashCode62 + (str63 != null ? str63.hashCode() : 0)) * 31;
        boolean z5 = this.prison_video_calls_disabled;
        if (z5) {
            z5 = true;
        }
        int i5 = (hashCode63 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.prison_voice_calls_disabled;
        if (!z6) {
            z2 = z6;
        }
        int i6 = (i5 + (z2 ? 1 : 0)) * 31;
        String str64 = this.prison_state;
        int hashCode64 = (i6 + (str64 != null ? str64.hashCode() : 0)) * 31;
        String str65 = this.prison_payment_gateway;
        if (str65 != null) {
            i = str65.hashCode();
        }
        return hashCode64 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.id);
        parcel.writeString(this.visitor_id);
        parcel.writeString(this.created);
        parcel.writeString(this.dob);
        parcel.writeString(this.dob_formatted);
        parcel.writeString(this.first_name);
        parcel.writeString(this.identifier);
        parcel.writeString(this.inmate_location);
        parcel.writeString(this.last_name);
        parcel.writeString(this.full_name);
        parcel.writeString(this.location);
        parcel.writeString(this.middle_name);
        parcel.writeString(this.prison_id);
        parcel.writeString(this.prison_name);
        parcel.writeString(this.suffix);
        parcel.writeString(this.approved);
        parcel.writeString(this.credit_balance);
        parcel.writeString(this.status);
        parcel.writeString(this.calls_disabled);
        parcel.writeString(this.calls_disabled_until);
        parcel.writeInt(this.can_text_message ? 1 : 0);
        parcel.writeInt(this.can_video_message ? 1 : 0);
        parcel.writeInt(this.can_image_message ? 1 : 0);
        parcel.writeString(this.deleted);
        parcel.writeString(this.deleted_warnings);
        parcel.writeString(this.description);
        parcel.writeString(this.error_reported);
        parcel.writeString(this.fmt_name);
        parcel.writeString(this.inmate_general_funds_auto_recharge_enabled);
        parcel.writeString(this.internal_notes);
        parcel.writeString(this.invisible);
        parcel.writeString(this.is_fraud);
        parcel.writeString(this.is_fraud_set_by);
        parcel.writeString(this.notes);
        parcel.writeString(this.occupant_id);
        parcel.writeString(this.prison_max_text_message_length);
        parcel.writeString(this.prison_max_video_message_length);
        parcel.writeString(this.prison_price_per_text_message);
        parcel.writeString(this.prison_price_per_video_message);
        parcel.writeString(this.prison_price_per_image_message);
        parcel.writeString(this.prison_price_per_gif_message);
        parcel.writeString(this.per_minute_charging_enabled);
        parcel.writeString(this.pubid);
        parcel.writeString(this.relationship);
        parcel.writeString(this.require_approval);
        parcel.writeString(this.should_record);
        parcel.writeString(this.tags);
        parcel.writeString(this.talk_to_me_funds_auto_recharge_enabled);
        parcel.writeString(this.visitor_city);
        parcel.writeString(this.visitor_created);
        parcel.writeString(this.visitor_dob);
        parcel.writeString(this.visitor_first_name);
        parcel.writeString(this.visitor_last_name);
        parcel.writeString(this.visitor_name);
        parcel.writeString(this.visitor_phone);
        parcel.writeString(this.visitor_pin);
        parcel.writeString(this.visitor_should_record);
        parcel.writeString(this.visitor_state);
        parcel.writeString(this.visitor_state_abbreviation);
        parcel.writeString(this.visitor_street1);
        parcel.writeString(this.visitor_street2);
        parcel.writeString(this.visitor_username);
        parcel.writeString(this.visitor_zipcode);
        parcel.writeString(this.voice_calls_disabled);
        parcel.writeString(this.voice_calls_disabled_until);
        parcel.writeString(this.zone);
        parcel.writeInt(this.prison_video_calls_disabled ? 1 : 0);
        parcel.writeInt(this.prison_voice_calls_disabled ? 1 : 0);
        parcel.writeString(this.prison_state);
        parcel.writeString(this.prison_payment_gateway);
    }

    public Inmate(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, boolean z, boolean z2, boolean z3, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, String str59, String str60, String str61, String str62, String str63, boolean z4, boolean z5, String str64, String str65) {
        String str66 = str;
        String str67 = str2;
        String str68 = str23;
        String str69 = str24;
        String str70 = str25;
        String str71 = str26;
        String str72 = str29;
        String str73 = str32;
        String str74 = str33;
        String str75 = str34;
        String str76 = str35;
        String str77 = str36;
        String str78 = str37;
        String str79 = str40;
        String str80 = str43;
        Intrinsics.checkParameterIsNotNull(str66, "id");
        Intrinsics.checkParameterIsNotNull(str67, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str68, "description");
        Intrinsics.checkParameterIsNotNull(str69, "error_reported");
        Intrinsics.checkParameterIsNotNull(str70, "fmt_name");
        Intrinsics.checkParameterIsNotNull(str71, "inmate_general_funds_auto_recharge_enabled");
        Intrinsics.checkParameterIsNotNull(str72, "is_fraud");
        Intrinsics.checkParameterIsNotNull(str73, "occupant_id");
        Intrinsics.checkParameterIsNotNull(str74, "prison_max_text_message_length");
        Intrinsics.checkParameterIsNotNull(str75, "prison_max_video_message_length");
        Intrinsics.checkParameterIsNotNull(str76, "prison_price_per_text_message");
        Intrinsics.checkParameterIsNotNull(str77, "prison_price_per_video_message");
        Intrinsics.checkParameterIsNotNull(str78, "prison_price_per_image_message");
        Intrinsics.checkParameterIsNotNull(str79, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str41, "relationship");
        Intrinsics.checkParameterIsNotNull(str43, "should_record");
        Intrinsics.checkParameterIsNotNull(str45, "talk_to_me_funds_auto_recharge_enabled");
        Intrinsics.checkParameterIsNotNull(str46, "visitor_city");
        Intrinsics.checkParameterIsNotNull(str47, "visitor_created");
        Intrinsics.checkParameterIsNotNull(str48, "visitor_dob");
        Intrinsics.checkParameterIsNotNull(str49, "visitor_first_name");
        Intrinsics.checkParameterIsNotNull(str50, "visitor_last_name");
        Intrinsics.checkParameterIsNotNull(str51, "visitor_name");
        Intrinsics.checkParameterIsNotNull(str52, "visitor_phone");
        Intrinsics.checkParameterIsNotNull(str53, "visitor_pin");
        Intrinsics.checkParameterIsNotNull(str54, "visitor_should_record");
        Intrinsics.checkParameterIsNotNull(str55, "visitor_state");
        Intrinsics.checkParameterIsNotNull(str56, "visitor_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str57, "visitor_street1");
        Intrinsics.checkParameterIsNotNull(str59, "visitor_username");
        Intrinsics.checkParameterIsNotNull(str60, "visitor_zipcode");
        Intrinsics.checkParameterIsNotNull(str61, "voice_calls_disabled");
        this.id = str66;
        this.visitor_id = str67;
        this.created = str3;
        this.dob = str4;
        this.dob_formatted = str5;
        this.first_name = str6;
        this.identifier = str7;
        this.inmate_location = str8;
        this.last_name = str9;
        this.full_name = str10;
        this.location = str11;
        this.middle_name = str12;
        this.prison_id = str13;
        this.prison_name = str14;
        this.suffix = str15;
        this.approved = str16;
        this.credit_balance = str17;
        this.status = str18;
        this.calls_disabled = str19;
        this.calls_disabled_until = str20;
        this.can_text_message = z;
        this.can_video_message = z2;
        this.can_image_message = z3;
        this.deleted = str21;
        this.deleted_warnings = str22;
        this.description = str68;
        this.error_reported = str69;
        this.fmt_name = str70;
        this.inmate_general_funds_auto_recharge_enabled = str71;
        this.internal_notes = str27;
        this.invisible = str28;
        this.is_fraud = str72;
        this.is_fraud_set_by = str30;
        this.notes = str31;
        this.occupant_id = str73;
        this.prison_max_text_message_length = str74;
        this.prison_max_video_message_length = str75;
        this.prison_price_per_text_message = str76;
        this.prison_price_per_video_message = str77;
        this.prison_price_per_image_message = str78;
        this.prison_price_per_gif_message = str38;
        this.per_minute_charging_enabled = str39;
        this.pubid = str79;
        this.relationship = str41;
        this.require_approval = str42;
        this.should_record = str43;
        this.tags = str44;
        this.talk_to_me_funds_auto_recharge_enabled = str45;
        this.visitor_city = str46;
        this.visitor_created = str47;
        this.visitor_dob = str48;
        this.visitor_first_name = str49;
        this.visitor_last_name = str50;
        this.visitor_name = str51;
        this.visitor_phone = str52;
        this.visitor_pin = str53;
        this.visitor_should_record = str54;
        this.visitor_state = str55;
        this.visitor_state_abbreviation = str56;
        this.visitor_street1 = str57;
        this.visitor_street2 = str58;
        this.visitor_username = str59;
        this.visitor_zipcode = str60;
        this.voice_calls_disabled = str61;
        this.voice_calls_disabled_until = str62;
        this.zone = str63;
        this.prison_video_calls_disabled = z4;
        this.prison_voice_calls_disabled = z5;
        this.prison_state = str64;
        this.prison_payment_gateway = str65;
    }

    public final String getId() {
        return this.id;
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }

    public final String getCreated() {
        return this.created;
    }

    public final String getDob() {
        return this.dob;
    }

    public final String getDob_formatted() {
        return this.dob_formatted;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final String getInmate_location() {
        return this.inmate_location;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final String getFull_name() {
        return this.full_name;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getMiddle_name() {
        return this.middle_name;
    }

    public final String getPrison_id() {
        return this.prison_id;
    }

    public final String getPrison_name() {
        return this.prison_name;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public final String getApproved() {
        return this.approved;
    }

    public final String getCredit_balance() {
        return this.credit_balance;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getCalls_disabled() {
        return this.calls_disabled;
    }

    public final String getCalls_disabled_until() {
        return this.calls_disabled_until;
    }

    public final boolean getCan_text_message() {
        return this.can_text_message;
    }

    public final boolean getCan_video_message() {
        return this.can_video_message;
    }

    public final boolean getCan_image_message() {
        return this.can_image_message;
    }

    public final String getDeleted() {
        return this.deleted;
    }

    public final String getDeleted_warnings() {
        return this.deleted_warnings;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getError_reported() {
        return this.error_reported;
    }

    public final String getFmt_name() {
        return this.fmt_name;
    }

    public final String getInmate_general_funds_auto_recharge_enabled() {
        return this.inmate_general_funds_auto_recharge_enabled;
    }

    public final String getInternal_notes() {
        return this.internal_notes;
    }

    public final String getInvisible() {
        return this.invisible;
    }

    public final void setInvisible(String str) {
        this.invisible = str;
    }

    public final String is_fraud() {
        return this.is_fraud;
    }

    public final String is_fraud_set_by() {
        return this.is_fraud_set_by;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getOccupant_id() {
        return this.occupant_id;
    }

    public final String getPrison_max_text_message_length() {
        return this.prison_max_text_message_length;
    }

    public final String getPrison_max_video_message_length() {
        return this.prison_max_video_message_length;
    }

    public final String getPrison_price_per_text_message() {
        return this.prison_price_per_text_message;
    }

    public final String getPrison_price_per_video_message() {
        return this.prison_price_per_video_message;
    }

    public final String getPrison_price_per_image_message() {
        return this.prison_price_per_image_message;
    }

    public final String getPrison_price_per_gif_message() {
        return this.prison_price_per_gif_message;
    }

    public final String getPer_minute_charging_enabled() {
        return this.per_minute_charging_enabled;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getRelationship() {
        return this.relationship;
    }

    public final String getRequire_approval() {
        return this.require_approval;
    }

    public final String getShould_record() {
        return this.should_record;
    }

    public final String getTags() {
        return this.tags;
    }

    public final String getTalk_to_me_funds_auto_recharge_enabled() {
        return this.talk_to_me_funds_auto_recharge_enabled;
    }

    public final String getVisitor_city() {
        return this.visitor_city;
    }

    public final String getVisitor_created() {
        return this.visitor_created;
    }

    public final String getVisitor_dob() {
        return this.visitor_dob;
    }

    public final String getVisitor_first_name() {
        return this.visitor_first_name;
    }

    public final String getVisitor_last_name() {
        return this.visitor_last_name;
    }

    public final String getVisitor_name() {
        return this.visitor_name;
    }

    public final String getVisitor_phone() {
        return this.visitor_phone;
    }

    public final String getVisitor_pin() {
        return this.visitor_pin;
    }

    public final String getVisitor_should_record() {
        return this.visitor_should_record;
    }

    public final String getVisitor_state() {
        return this.visitor_state;
    }

    public final String getVisitor_state_abbreviation() {
        return this.visitor_state_abbreviation;
    }

    public final String getVisitor_street1() {
        return this.visitor_street1;
    }

    public final String getVisitor_street2() {
        return this.visitor_street2;
    }

    public final String getVisitor_username() {
        return this.visitor_username;
    }

    public final String getVisitor_zipcode() {
        return this.visitor_zipcode;
    }

    public final String getVoice_calls_disabled() {
        return this.voice_calls_disabled;
    }

    public final String getVoice_calls_disabled_until() {
        return this.voice_calls_disabled_until;
    }

    public final String getZone() {
        return this.zone;
    }

    public final boolean getPrison_video_calls_disabled() {
        return this.prison_video_calls_disabled;
    }

    public final boolean getPrison_voice_calls_disabled() {
        return this.prison_voice_calls_disabled;
    }

    public final String getPrison_state() {
        return this.prison_state;
    }

    public final String getPrison_payment_gateway() {
        return this.prison_payment_gateway;
    }

    public String toString() {
        return this.first_name + ' ' + this.last_name + " (" + this.dob_formatted + ')';
    }

    public final InmateShort toShortForm(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "relationship");
        Intrinsics.checkParameterIsNotNull(str2, "explanation");
        return new InmateShort(this.dob, this.first_name, this.identifier, this.last_name, this.middle_name, this.prison_id, this.suffix, str, str2);
    }

    public final boolean isVideoCallsDisabled() {
        return this.prison_video_calls_disabled;
    }

    public final boolean isVoiceCallsDisabled() {
        return this.prison_voice_calls_disabled;
    }

    public final boolean isApproved() {
        return Intrinsics.areEqual((Object) this.approved, (Object) "yes");
    }

    public final boolean isFraud() {
        return Intrinsics.areEqual((Object) this.is_fraud, (Object) "1");
    }
}
