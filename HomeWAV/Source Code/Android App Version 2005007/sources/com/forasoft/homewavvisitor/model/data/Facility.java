package com.forasoft.homewavvisitor.model.data;

import com.forasoft.homewavvisitor.model.UploadWorker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.opencv.global.opencv_core;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0003\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001BÅ\u0005\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\u0006\u0010-\u001a\u00020\u0003\u0012\u0006\u0010.\u001a\u00020\u0003\u0012\u0006\u0010/\u001a\u00020\u0003\u0012\u0006\u00100\u001a\u00020\u0003\u0012\u0006\u00101\u001a\u00020\u0003\u0012\u0006\u00102\u001a\u00020\u0003\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0003\u0012\u0006\u00105\u001a\u00020\u0003\u0012\u0006\u00106\u001a\u00020\u0003\u0012\u0006\u00107\u001a\u00020\u0003\u0012\u0006\u00108\u001a\u00020\u0003\u0012\u0006\u00109\u001a\u00020\u0003\u0012\u0006\u0010:\u001a\u00020\u0003\u0012\u0006\u0010;\u001a\u00020\u0003\u0012\u0006\u0010<\u001a\u00020\u0003\u0012\u0006\u0010=\u001a\u00020\u0003\u0012\u0006\u0010>\u001a\u00020\u0003\u0012\u0006\u0010?\u001a\u00020\u0003\u0012\u0006\u0010@\u001a\u00020\u0003\u0012\b\u0010A\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010B\u001a\u00020\u0003\u0012\u0006\u0010C\u001a\u00020\u0003\u0012\u0006\u0010D\u001a\u00020\u0003\u0012\u0006\u0010E\u001a\u00020\u0003\u0012\u0006\u0010F\u001a\u00020\u0003\u0012\u0006\u0010G\u001a\u00020\u0003\u0012\u0006\u0010H\u001a\u00020\u0003\u0012\u0006\u0010I\u001a\u00020\u0003\u0012\u0006\u0010J\u001a\u00020\u0003\u0012\u0006\u0010K\u001a\u00020\u0003\u0012\u0006\u0010L\u001a\u00020\u0003\u0012\u0006\u0010M\u001a\u00020\u0003\u0012\b\u0010N\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010O\u001a\u00020\u0003\u0012\u0006\u0010P\u001a\u00020\u0003\u0012\b\u0010Q\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010R\u001a\u00020\u0003\u0012\u0006\u0010S\u001a\u00020\u0003\u0012\b\u0010T\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010U\u001a\u00020\u0003\u0012\b\u0010V\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010W\u001a\u00020\u0003\u0012\b\u0010X\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010Y\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010ZJ\n\u0010¶\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010·\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¸\u0001\u001a\u00020\nHÆ\u0003J\n\u0010¹\u0001\u001a\u00020\nHÆ\u0003J\n\u0010º\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010»\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¼\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010½\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¾\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¿\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010À\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Â\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ã\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ä\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Å\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Æ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ç\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010È\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010É\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ê\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ë\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ì\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Í\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Î\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ï\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ð\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ñ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ò\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ó\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ô\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Õ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ö\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010×\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ø\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ù\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ú\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Û\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ü\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ý\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Þ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ß\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010à\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010â\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ã\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ä\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010å\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010æ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ç\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010è\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010é\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ê\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ë\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ì\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010í\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010î\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ï\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010ð\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010ñ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ò\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ó\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ô\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010õ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ö\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010÷\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ø\u0001\u001a\u00020\nHÆ\u0003J\n\u0010ù\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ú\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010û\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ü\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ý\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010þ\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010ÿ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0002\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010gJ\n\u0010\u0002\u001a\u00020\u0003HÆ\u0003Jü\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u00020\u00032\b\b\u0002\u00107\u001a\u00020\u00032\b\b\u0002\u00108\u001a\u00020\u00032\b\b\u0002\u00109\u001a\u00020\u00032\b\b\u0002\u0010:\u001a\u00020\u00032\b\b\u0002\u0010;\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u00032\b\b\u0002\u0010@\u001a\u00020\u00032\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010B\u001a\u00020\u00032\b\b\u0002\u0010C\u001a\u00020\u00032\b\b\u0002\u0010D\u001a\u00020\u00032\b\b\u0002\u0010E\u001a\u00020\u00032\b\b\u0002\u0010F\u001a\u00020\u00032\b\b\u0002\u0010G\u001a\u00020\u00032\b\b\u0002\u0010H\u001a\u00020\u00032\b\b\u0002\u0010I\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\u00032\b\b\u0002\u0010K\u001a\u00020\u00032\b\b\u0002\u0010L\u001a\u00020\u00032\b\b\u0002\u0010M\u001a\u00020\u00032\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010O\u001a\u00020\u00032\b\b\u0002\u0010P\u001a\u00020\u00032\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010R\u001a\u00020\u00032\b\b\u0002\u0010S\u001a\u00020\u00032\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010U\u001a\u00020\u00032\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010W\u001a\u00020\u00032\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0003\u0010\u0002J\u0015\u0010\u0002\u001a\u00020\n2\t\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000b\u0010\u0002\u001a\u00030\u0002HÖ\u0001J\u0007\u0010\u0002\u001a\u00020\nJ\u0007\u0010\u0002\u001a\u00020\nJ\u0007\u0010\u0002\u001a\u00020\nJ\t\u0010\u0002\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\\R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\\R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\\R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\\R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\\R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\\R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\be\u0010\\R\u0015\u0010Y\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010gR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\\R\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bj\u0010cR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bk\u0010cR\u0013\u0010X\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\\R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bm\u0010\\R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\\R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\\R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\\R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\\R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\br\u0010\\R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bs\u0010\\R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\\R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bu\u0010\\R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\\R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\\R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\\R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\by\u0010\\R\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\\R\u0011\u0010#\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\\R\u0011\u0010\u001e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b|\u0010\\R\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b}\u0010\\R\u0011\u0010 \u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\\R\u0011\u0010!\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\\R\u0012\u0010$\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010%\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010&\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010'\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010(\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010)\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010*\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010+\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010,\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010-\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010.\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010/\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00100\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00101\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00102\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00109\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00103\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010\"\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00104\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00105\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00106\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00107\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u00108\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010:\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010;\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010<\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010=\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010>\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010?\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0012\u0010@\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0001\u0010\\R\u0015\u0010A\u001a\u0004\u0018\u00010\u0001¢\u0006\n\n\u0000\u001a\u0006\b\u0001\u0010\u0001R\u0012\u0010W\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010\\R\u0012\u0010B\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¡\u0001\u0010\\R\u0012\u0010C\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\\R\u0012\u0010D\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b£\u0001\u0010\\R\u0014\u0010V\u001a\u0004\u0018\u00010\u0003¢\u0006\t\n\u0000\u001a\u0005\b¤\u0001\u0010\\R\u0012\u0010E\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¥\u0001\u0010\\R\u0012\u0010F\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u0010\\R\u0012\u0010G\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b§\u0001\u0010\\R\u0012\u0010H\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¨\u0001\u0010\\R\u0012\u0010I\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b©\u0001\u0010\\R\u0012\u0010J\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\\R\u0012\u0010K\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b«\u0001\u0010\\R\u0012\u0010L\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¬\u0001\u0010\\R\u0012\u0010M\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b­\u0001\u0010\\R\u0015\u0010N\u001a\u0004\u0018\u00010\u0001¢\u0006\n\n\u0000\u001a\u0006\b®\u0001\u0010\u0001R\u0012\u0010O\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b¯\u0001\u0010\\R\u0012\u0010P\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b°\u0001\u0010\\R\u0015\u0010Q\u001a\u0004\u0018\u00010\u0001¢\u0006\n\n\u0000\u001a\u0006\b±\u0001\u0010\u0001R\u0012\u0010R\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b²\u0001\u0010\\R\u0012\u0010S\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b³\u0001\u0010\\R\u0015\u0010T\u001a\u0004\u0018\u00010\u0001¢\u0006\n\n\u0000\u001a\u0006\b´\u0001\u0010\u0001R\u0012\u0010U\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\bµ\u0001\u0010\\¨\u0006\u0002"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Facility;", "", "auto_approve_inmates", "", "auto_approve_messages", "available_times", "blocked_phone_numbers", "call_video_dimensions", "calls_disabled", "can_schedule", "", "change_visitor_impacts_all_inmates", "created", "fms_app", "has_documents", "has_request_forms", "id", "inmate_agreement_document_url", "inmate_agreement_message", "inmate_agreement_signature_required", "inmate_credit_value_on_signup", "inmate_credit_value_on_signup_limit", "inmate_hide_setup_link", "inmate_session_timeout", "inmate_tags", "inmate_welcome_info", "inmate_zone_builder", "international_twilio_calls_disabled", "lobby_news", "max_call_length", "max_text_message_length", "max_video_age", "max_video_message_length", "max_voice_call_length", "price_per_s3_video_message", "max_s3_video_message_length", "min_call_length", "min_voice_call_length", "name", "news", "notes", "notify_pending_inmate_email", "notify_pending_message_email", "occupant_acc_paypal_credit_sku", "occupant_acc_paypal_id", "occupant_credits_disabled", "occupant_movies_disabled", "occupant_voice_calls_disabled", "occupant_voice_price_per_minute", "paypal_credit_sku", "paypal_id", "price_per_minute", "price_per_text_message", "price_per_video_message", "pubid", "public_ip_addresses", "require_photo_id", "photo_lock_after_approval", "require_resident", "residents_auto_delete_inmates", "residents_checksum", "residents_importer", "residents_updated", "session_ad_code", "session_image_link", "session_image_url", "timezone", "timezone_id", "timezone_php", "twilio_fixed_markup_per_minute", "twilio_maximum_per_minute_rate", "twilio_percentage_markup_per_minute", "us_state", "us_state_abbreviation", "us_state_id", "video_bandwidth_down", "video_bandwidth_up", "video_camera_dimensions", "video_codec", "video_fps", "video_quality", "visitor_blocked_phone_numbers", "visitor_phone_voice_calls_disabled", "voice_price_per_minute", "welcome_image_url", "welcome_info", "transfer_fee", "talk_to_me_credits_disabled", "hide_adding_inmate_general_funds", "error_reporting", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAuto_approve_inmates", "()Ljava/lang/String;", "getAuto_approve_messages", "getAvailable_times", "getBlocked_phone_numbers", "getCall_video_dimensions", "getCalls_disabled", "getCan_schedule", "()Z", "getChange_visitor_impacts_all_inmates", "getCreated", "getError_reporting", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getFms_app", "getHas_documents", "getHas_request_forms", "getHide_adding_inmate_general_funds", "getId", "getInmate_agreement_document_url", "getInmate_agreement_message", "getInmate_agreement_signature_required", "getInmate_credit_value_on_signup", "getInmate_credit_value_on_signup_limit", "getInmate_hide_setup_link", "getInmate_session_timeout", "getInmate_tags", "getInmate_welcome_info", "getInmate_zone_builder", "getInternational_twilio_calls_disabled", "getLobby_news", "getMax_call_length", "getMax_s3_video_message_length", "getMax_text_message_length", "getMax_video_age", "getMax_video_message_length", "getMax_voice_call_length", "getMin_call_length", "getMin_voice_call_length", "getName", "getNews", "getNotes", "getNotify_pending_inmate_email", "getNotify_pending_message_email", "getOccupant_acc_paypal_credit_sku", "getOccupant_acc_paypal_id", "getOccupant_credits_disabled", "getOccupant_movies_disabled", "getOccupant_voice_calls_disabled", "getOccupant_voice_price_per_minute", "getPaypal_credit_sku", "getPaypal_id", "getPhoto_lock_after_approval", "getPrice_per_minute", "getPrice_per_s3_video_message", "getPrice_per_text_message", "getPrice_per_video_message", "getPubid", "getPublic_ip_addresses", "getRequire_photo_id", "getRequire_resident", "getResidents_auto_delete_inmates", "getResidents_checksum", "getResidents_importer", "getResidents_updated", "getSession_ad_code", "getSession_image_link", "getSession_image_url", "()Ljava/lang/Object;", "getTalk_to_me_credits_disabled", "getTimezone", "getTimezone_id", "getTimezone_php", "getTransfer_fee", "getTwilio_fixed_markup_per_minute", "getTwilio_maximum_per_minute_rate", "getTwilio_percentage_markup_per_minute", "getUs_state", "getUs_state_abbreviation", "getUs_state_id", "getVideo_bandwidth_down", "getVideo_bandwidth_up", "getVideo_camera_dimensions", "getVideo_codec", "getVideo_fps", "getVideo_quality", "getVisitor_blocked_phone_numbers", "getVisitor_phone_voice_calls_disabled", "getVoice_price_per_minute", "getWelcome_image_url", "getWelcome_info", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "component7", "component70", "component71", "component72", "component73", "component74", "component75", "component76", "component77", "component78", "component79", "component8", "component80", "component81", "component82", "component83", "component84", "component85", "component86", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/forasoft/homewavvisitor/model/data/Facility;", "equals", "other", "hashCode", "", "isErrorReportEnabled", "isGeneralFundsDisabled", "isTalkToMeFundsDisabled", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Facility.kt */
public final class Facility {
    private final String auto_approve_inmates;
    private final String auto_approve_messages;
    private final String available_times;
    private final String blocked_phone_numbers;
    private final String call_video_dimensions;
    private final String calls_disabled;
    private final boolean can_schedule;
    private final String change_visitor_impacts_all_inmates;
    private final String created;
    private final Boolean error_reporting;
    private final String fms_app;
    private final boolean has_documents;
    private final boolean has_request_forms;
    private final String hide_adding_inmate_general_funds;
    private final String id;
    private final String inmate_agreement_document_url;
    private final String inmate_agreement_message;
    private final String inmate_agreement_signature_required;
    private final String inmate_credit_value_on_signup;
    private final String inmate_credit_value_on_signup_limit;
    private final String inmate_hide_setup_link;
    private final String inmate_session_timeout;
    private final String inmate_tags;
    private final String inmate_welcome_info;
    private final String inmate_zone_builder;
    private final String international_twilio_calls_disabled;
    private final String lobby_news;
    private final String max_call_length;
    private final String max_s3_video_message_length;
    private final String max_text_message_length;
    private final String max_video_age;
    private final String max_video_message_length;
    private final String max_voice_call_length;
    private final String min_call_length;
    private final String min_voice_call_length;
    private final String name;
    private final String news;
    private final String notes;
    private final String notify_pending_inmate_email;
    private final String notify_pending_message_email;
    private final String occupant_acc_paypal_credit_sku;
    private final String occupant_acc_paypal_id;
    private final String occupant_credits_disabled;
    private final String occupant_movies_disabled;
    private final String occupant_voice_calls_disabled;
    private final String occupant_voice_price_per_minute;
    private final String paypal_credit_sku;
    private final String paypal_id;
    private final String photo_lock_after_approval;
    private final String price_per_minute;
    private final String price_per_s3_video_message;
    private final String price_per_text_message;
    private final String price_per_video_message;
    private final String pubid;
    private final String public_ip_addresses;
    private final String require_photo_id;
    private final String require_resident;
    private final String residents_auto_delete_inmates;
    private final String residents_checksum;
    private final String residents_importer;
    private final String residents_updated;
    private final String session_ad_code;
    private final String session_image_link;
    private final Object session_image_url;
    private final String talk_to_me_credits_disabled;
    private final String timezone;
    private final String timezone_id;
    private final String timezone_php;
    private final String transfer_fee;
    private final String twilio_fixed_markup_per_minute;
    private final String twilio_maximum_per_minute_rate;
    private final String twilio_percentage_markup_per_minute;
    private final String us_state;
    private final String us_state_abbreviation;
    private final String us_state_id;
    private final String video_bandwidth_down;
    private final String video_bandwidth_up;
    private final String video_camera_dimensions;
    private final Object video_codec;
    private final String video_fps;
    private final String video_quality;
    private final Object visitor_blocked_phone_numbers;
    private final String visitor_phone_voice_calls_disabled;
    private final String voice_price_per_minute;
    private final Object welcome_image_url;
    private final String welcome_info;

    public static /* synthetic */ Facility copy$default(Facility facility, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, String str8, String str9, boolean z2, boolean z3, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, Object obj, String str59, String str60, String str61, String str62, String str63, String str64, String str65, String str66, String str67, String str68, String str69, String str70, Object obj2, String str71, String str72, Object obj3, String str73, String str74, Object obj4, String str75, String str76, String str77, String str78, Boolean bool, int i, int i2, int i3, Object obj5) {
        Facility facility2 = facility;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        return facility.copy((i4 & 1) != 0 ? facility2.auto_approve_inmates : str, (i4 & 2) != 0 ? facility2.auto_approve_messages : str2, (i4 & 4) != 0 ? facility2.available_times : str3, (i4 & 8) != 0 ? facility2.blocked_phone_numbers : str4, (i4 & 16) != 0 ? facility2.call_video_dimensions : str5, (i4 & 32) != 0 ? facility2.calls_disabled : str6, (i4 & 64) != 0 ? facility2.can_schedule : z, (i4 & 128) != 0 ? facility2.change_visitor_impacts_all_inmates : str7, (i4 & 256) != 0 ? facility2.created : str8, (i4 & 512) != 0 ? facility2.fms_app : str9, (i4 & 1024) != 0 ? facility2.has_documents : z2, (i4 & 2048) != 0 ? facility2.has_request_forms : z3, (i4 & 4096) != 0 ? facility2.id : str10, (i4 & 8192) != 0 ? facility2.inmate_agreement_document_url : str11, (i4 & 16384) != 0 ? facility2.inmate_agreement_message : str12, (i4 & 32768) != 0 ? facility2.inmate_agreement_signature_required : str13, (i4 & 65536) != 0 ? facility2.inmate_credit_value_on_signup : str14, (i4 & 131072) != 0 ? facility2.inmate_credit_value_on_signup_limit : str15, (i4 & 262144) != 0 ? facility2.inmate_hide_setup_link : str16, (i4 & 524288) != 0 ? facility2.inmate_session_timeout : str17, (i4 & 1048576) != 0 ? facility2.inmate_tags : str18, (i4 & 2097152) != 0 ? facility2.inmate_welcome_info : str19, (i4 & 4194304) != 0 ? facility2.inmate_zone_builder : str20, (i4 & 8388608) != 0 ? facility2.international_twilio_calls_disabled : str21, (i4 & 16777216) != 0 ? facility2.lobby_news : str22, (i4 & opencv_core.ACCESS_WRITE) != 0 ? facility2.max_call_length : str23, (i4 & 67108864) != 0 ? facility2.max_text_message_length : str24, (i4 & 134217728) != 0 ? facility2.max_video_age : str25, (i4 & 268435456) != 0 ? facility2.max_video_message_length : str26, (i4 & 536870912) != 0 ? facility2.max_voice_call_length : str27, (i4 & 1073741824) != 0 ? facility2.price_per_s3_video_message : str28, (i4 & Integer.MIN_VALUE) != 0 ? facility2.max_s3_video_message_length : str29, (i5 & 1) != 0 ? facility2.min_call_length : str30, (i5 & 2) != 0 ? facility2.min_voice_call_length : str31, (i5 & 4) != 0 ? facility2.name : str32, (i5 & 8) != 0 ? facility2.news : str33, (i5 & 16) != 0 ? facility2.notes : str34, (i5 & 32) != 0 ? facility2.notify_pending_inmate_email : str35, (i5 & 64) != 0 ? facility2.notify_pending_message_email : str36, (i5 & 128) != 0 ? facility2.occupant_acc_paypal_credit_sku : str37, (i5 & 256) != 0 ? facility2.occupant_acc_paypal_id : str38, (i5 & 512) != 0 ? facility2.occupant_credits_disabled : str39, (i5 & 1024) != 0 ? facility2.occupant_movies_disabled : str40, (i5 & 2048) != 0 ? facility2.occupant_voice_calls_disabled : str41, (i5 & 4096) != 0 ? facility2.occupant_voice_price_per_minute : str42, (i5 & 8192) != 0 ? facility2.paypal_credit_sku : str43, (i5 & 16384) != 0 ? facility2.paypal_id : str44, (i5 & 32768) != 0 ? facility2.price_per_minute : str45, (i5 & 65536) != 0 ? facility2.price_per_text_message : str46, (i5 & 131072) != 0 ? facility2.price_per_video_message : str47, (i5 & 262144) != 0 ? facility2.pubid : str48, (i5 & 524288) != 0 ? facility2.public_ip_addresses : str49, (i5 & 1048576) != 0 ? facility2.require_photo_id : str50, (i5 & 2097152) != 0 ? facility2.photo_lock_after_approval : str51, (i5 & 4194304) != 0 ? facility2.require_resident : str52, (i5 & 8388608) != 0 ? facility2.residents_auto_delete_inmates : str53, (i5 & 16777216) != 0 ? facility2.residents_checksum : str54, (i5 & opencv_core.ACCESS_WRITE) != 0 ? facility2.residents_importer : str55, (i5 & 67108864) != 0 ? facility2.residents_updated : str56, (i5 & 134217728) != 0 ? facility2.session_ad_code : str57, (i5 & 268435456) != 0 ? facility2.session_image_link : str58, (i5 & 536870912) != 0 ? facility2.session_image_url : obj, (i5 & 1073741824) != 0 ? facility2.timezone : str59, (i5 & Integer.MIN_VALUE) != 0 ? facility2.timezone_id : str60, (i6 & 1) != 0 ? facility2.timezone_php : str61, (i6 & 2) != 0 ? facility2.twilio_fixed_markup_per_minute : str62, (i6 & 4) != 0 ? facility2.twilio_maximum_per_minute_rate : str63, (i6 & 8) != 0 ? facility2.twilio_percentage_markup_per_minute : str64, (i6 & 16) != 0 ? facility2.us_state : str65, (i6 & 32) != 0 ? facility2.us_state_abbreviation : str66, (i6 & 64) != 0 ? facility2.us_state_id : str67, (i6 & 128) != 0 ? facility2.video_bandwidth_down : str68, (i6 & 256) != 0 ? facility2.video_bandwidth_up : str69, (i6 & 512) != 0 ? facility2.video_camera_dimensions : str70, (i6 & 1024) != 0 ? facility2.video_codec : obj2, (i6 & 2048) != 0 ? facility2.video_fps : str71, (i6 & 4096) != 0 ? facility2.video_quality : str72, (i6 & 8192) != 0 ? facility2.visitor_blocked_phone_numbers : obj3, (i6 & 16384) != 0 ? facility2.visitor_phone_voice_calls_disabled : str73, (i6 & 32768) != 0 ? facility2.voice_price_per_minute : str74, (i6 & 65536) != 0 ? facility2.welcome_image_url : obj4, (i6 & 131072) != 0 ? facility2.welcome_info : str75, (i6 & 262144) != 0 ? facility2.transfer_fee : str76, (i6 & 524288) != 0 ? facility2.talk_to_me_credits_disabled : str77, (i6 & 1048576) != 0 ? facility2.hide_adding_inmate_general_funds : str78, (i6 & 2097152) != 0 ? facility2.error_reporting : bool);
    }

    public final String component1() {
        return this.auto_approve_inmates;
    }

    public final String component10() {
        return this.fms_app;
    }

    public final boolean component11() {
        return this.has_documents;
    }

    public final boolean component12() {
        return this.has_request_forms;
    }

    public final String component13() {
        return this.id;
    }

    public final String component14() {
        return this.inmate_agreement_document_url;
    }

    public final String component15() {
        return this.inmate_agreement_message;
    }

    public final String component16() {
        return this.inmate_agreement_signature_required;
    }

    public final String component17() {
        return this.inmate_credit_value_on_signup;
    }

    public final String component18() {
        return this.inmate_credit_value_on_signup_limit;
    }

    public final String component19() {
        return this.inmate_hide_setup_link;
    }

    public final String component2() {
        return this.auto_approve_messages;
    }

    public final String component20() {
        return this.inmate_session_timeout;
    }

    public final String component21() {
        return this.inmate_tags;
    }

    public final String component22() {
        return this.inmate_welcome_info;
    }

    public final String component23() {
        return this.inmate_zone_builder;
    }

    public final String component24() {
        return this.international_twilio_calls_disabled;
    }

    public final String component25() {
        return this.lobby_news;
    }

    public final String component26() {
        return this.max_call_length;
    }

    public final String component27() {
        return this.max_text_message_length;
    }

    public final String component28() {
        return this.max_video_age;
    }

    public final String component29() {
        return this.max_video_message_length;
    }

    public final String component3() {
        return this.available_times;
    }

    public final String component30() {
        return this.max_voice_call_length;
    }

    public final String component31() {
        return this.price_per_s3_video_message;
    }

    public final String component32() {
        return this.max_s3_video_message_length;
    }

    public final String component33() {
        return this.min_call_length;
    }

    public final String component34() {
        return this.min_voice_call_length;
    }

    public final String component35() {
        return this.name;
    }

    public final String component36() {
        return this.news;
    }

    public final String component37() {
        return this.notes;
    }

    public final String component38() {
        return this.notify_pending_inmate_email;
    }

    public final String component39() {
        return this.notify_pending_message_email;
    }

    public final String component4() {
        return this.blocked_phone_numbers;
    }

    public final String component40() {
        return this.occupant_acc_paypal_credit_sku;
    }

    public final String component41() {
        return this.occupant_acc_paypal_id;
    }

    public final String component42() {
        return this.occupant_credits_disabled;
    }

    public final String component43() {
        return this.occupant_movies_disabled;
    }

    public final String component44() {
        return this.occupant_voice_calls_disabled;
    }

    public final String component45() {
        return this.occupant_voice_price_per_minute;
    }

    public final String component46() {
        return this.paypal_credit_sku;
    }

    public final String component47() {
        return this.paypal_id;
    }

    public final String component48() {
        return this.price_per_minute;
    }

    public final String component49() {
        return this.price_per_text_message;
    }

    public final String component5() {
        return this.call_video_dimensions;
    }

    public final String component50() {
        return this.price_per_video_message;
    }

    public final String component51() {
        return this.pubid;
    }

    public final String component52() {
        return this.public_ip_addresses;
    }

    public final String component53() {
        return this.require_photo_id;
    }

    public final String component54() {
        return this.photo_lock_after_approval;
    }

    public final String component55() {
        return this.require_resident;
    }

    public final String component56() {
        return this.residents_auto_delete_inmates;
    }

    public final String component57() {
        return this.residents_checksum;
    }

    public final String component58() {
        return this.residents_importer;
    }

    public final String component59() {
        return this.residents_updated;
    }

    public final String component6() {
        return this.calls_disabled;
    }

    public final String component60() {
        return this.session_ad_code;
    }

    public final String component61() {
        return this.session_image_link;
    }

    public final Object component62() {
        return this.session_image_url;
    }

    public final String component63() {
        return this.timezone;
    }

    public final String component64() {
        return this.timezone_id;
    }

    public final String component65() {
        return this.timezone_php;
    }

    public final String component66() {
        return this.twilio_fixed_markup_per_minute;
    }

    public final String component67() {
        return this.twilio_maximum_per_minute_rate;
    }

    public final String component68() {
        return this.twilio_percentage_markup_per_minute;
    }

    public final String component69() {
        return this.us_state;
    }

    public final boolean component7() {
        return this.can_schedule;
    }

    public final String component70() {
        return this.us_state_abbreviation;
    }

    public final String component71() {
        return this.us_state_id;
    }

    public final String component72() {
        return this.video_bandwidth_down;
    }

    public final String component73() {
        return this.video_bandwidth_up;
    }

    public final String component74() {
        return this.video_camera_dimensions;
    }

    public final Object component75() {
        return this.video_codec;
    }

    public final String component76() {
        return this.video_fps;
    }

    public final String component77() {
        return this.video_quality;
    }

    public final Object component78() {
        return this.visitor_blocked_phone_numbers;
    }

    public final String component79() {
        return this.visitor_phone_voice_calls_disabled;
    }

    public final String component8() {
        return this.change_visitor_impacts_all_inmates;
    }

    public final String component80() {
        return this.voice_price_per_minute;
    }

    public final Object component81() {
        return this.welcome_image_url;
    }

    public final String component82() {
        return this.welcome_info;
    }

    public final String component83() {
        return this.transfer_fee;
    }

    public final String component84() {
        return this.talk_to_me_credits_disabled;
    }

    public final String component85() {
        return this.hide_adding_inmate_general_funds;
    }

    public final Boolean component86() {
        return this.error_reporting;
    }

    public final String component9() {
        return this.created;
    }

    public final Facility copy(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, String str8, String str9, boolean z2, boolean z3, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, Object obj, String str59, String str60, String str61, String str62, String str63, String str64, String str65, String str66, String str67, String str68, String str69, String str70, Object obj2, String str71, String str72, Object obj3, String str73, String str74, Object obj4, String str75, String str76, String str77, String str78, Boolean bool) {
        String str79 = str;
        Intrinsics.checkParameterIsNotNull(str79, "auto_approve_inmates");
        Intrinsics.checkParameterIsNotNull(str2, "auto_approve_messages");
        Intrinsics.checkParameterIsNotNull(str3, "available_times");
        Intrinsics.checkParameterIsNotNull(str4, "blocked_phone_numbers");
        Intrinsics.checkParameterIsNotNull(str5, "call_video_dimensions");
        Intrinsics.checkParameterIsNotNull(str6, "calls_disabled");
        Intrinsics.checkParameterIsNotNull(str7, "change_visitor_impacts_all_inmates");
        Intrinsics.checkParameterIsNotNull(str8, "created");
        Intrinsics.checkParameterIsNotNull(str9, "fms_app");
        Intrinsics.checkParameterIsNotNull(str10, "id");
        Intrinsics.checkParameterIsNotNull(str12, "inmate_agreement_message");
        Intrinsics.checkParameterIsNotNull(str13, "inmate_agreement_signature_required");
        Intrinsics.checkParameterIsNotNull(str14, "inmate_credit_value_on_signup");
        Intrinsics.checkParameterIsNotNull(str15, "inmate_credit_value_on_signup_limit");
        Intrinsics.checkParameterIsNotNull(str16, "inmate_hide_setup_link");
        Intrinsics.checkParameterIsNotNull(str17, "inmate_session_timeout");
        Intrinsics.checkParameterIsNotNull(str18, "inmate_tags");
        Intrinsics.checkParameterIsNotNull(str19, "inmate_welcome_info");
        Intrinsics.checkParameterIsNotNull(str20, "inmate_zone_builder");
        Intrinsics.checkParameterIsNotNull(str21, "international_twilio_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str22, "lobby_news");
        Intrinsics.checkParameterIsNotNull(str23, "max_call_length");
        Intrinsics.checkParameterIsNotNull(str24, "max_text_message_length");
        Intrinsics.checkParameterIsNotNull(str25, "max_video_age");
        Intrinsics.checkParameterIsNotNull(str26, "max_video_message_length");
        Intrinsics.checkParameterIsNotNull(str27, "max_voice_call_length");
        Intrinsics.checkParameterIsNotNull(str28, "price_per_s3_video_message");
        Intrinsics.checkParameterIsNotNull(str29, "max_s3_video_message_length");
        Intrinsics.checkParameterIsNotNull(str30, "min_call_length");
        Intrinsics.checkParameterIsNotNull(str31, "min_voice_call_length");
        Intrinsics.checkParameterIsNotNull(str32, "name");
        Intrinsics.checkParameterIsNotNull(str33, "news");
        Intrinsics.checkParameterIsNotNull(str34, "notes");
        Intrinsics.checkParameterIsNotNull(str35, "notify_pending_inmate_email");
        Intrinsics.checkParameterIsNotNull(str36, "notify_pending_message_email");
        Intrinsics.checkParameterIsNotNull(str37, "occupant_acc_paypal_credit_sku");
        Intrinsics.checkParameterIsNotNull(str38, "occupant_acc_paypal_id");
        Intrinsics.checkParameterIsNotNull(str39, "occupant_credits_disabled");
        Intrinsics.checkParameterIsNotNull(str40, "occupant_movies_disabled");
        Intrinsics.checkParameterIsNotNull(str41, "occupant_voice_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str42, "occupant_voice_price_per_minute");
        Intrinsics.checkParameterIsNotNull(str43, "paypal_credit_sku");
        Intrinsics.checkParameterIsNotNull(str44, "paypal_id");
        Intrinsics.checkParameterIsNotNull(str45, "price_per_minute");
        Intrinsics.checkParameterIsNotNull(str46, "price_per_text_message");
        Intrinsics.checkParameterIsNotNull(str47, "price_per_video_message");
        Intrinsics.checkParameterIsNotNull(str48, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str49, "public_ip_addresses");
        Intrinsics.checkParameterIsNotNull(str50, "require_photo_id");
        Intrinsics.checkParameterIsNotNull(str51, "photo_lock_after_approval");
        Intrinsics.checkParameterIsNotNull(str52, "require_resident");
        Intrinsics.checkParameterIsNotNull(str53, "residents_auto_delete_inmates");
        Intrinsics.checkParameterIsNotNull(str54, "residents_checksum");
        Intrinsics.checkParameterIsNotNull(str55, "residents_importer");
        Intrinsics.checkParameterIsNotNull(str56, "residents_updated");
        Intrinsics.checkParameterIsNotNull(str57, "session_ad_code");
        Intrinsics.checkParameterIsNotNull(str58, "session_image_link");
        Intrinsics.checkParameterIsNotNull(str59, "timezone");
        Intrinsics.checkParameterIsNotNull(str60, "timezone_id");
        Intrinsics.checkParameterIsNotNull(str61, "timezone_php");
        Intrinsics.checkParameterIsNotNull(str62, "twilio_fixed_markup_per_minute");
        Intrinsics.checkParameterIsNotNull(str63, "twilio_maximum_per_minute_rate");
        Intrinsics.checkParameterIsNotNull(str64, "twilio_percentage_markup_per_minute");
        Intrinsics.checkParameterIsNotNull(str65, "us_state");
        Intrinsics.checkParameterIsNotNull(str66, "us_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str67, "us_state_id");
        Intrinsics.checkParameterIsNotNull(str68, "video_bandwidth_down");
        Intrinsics.checkParameterIsNotNull(str69, "video_bandwidth_up");
        Intrinsics.checkParameterIsNotNull(str70, "video_camera_dimensions");
        Intrinsics.checkParameterIsNotNull(str71, "video_fps");
        Intrinsics.checkParameterIsNotNull(str72, "video_quality");
        Intrinsics.checkParameterIsNotNull(str73, "visitor_phone_voice_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str74, "voice_price_per_minute");
        Intrinsics.checkParameterIsNotNull(str75, "welcome_info");
        Intrinsics.checkParameterIsNotNull(str77, "talk_to_me_credits_disabled");
        return new Facility(str79, str2, str3, str4, str5, str6, z, str7, str8, str9, z2, z3, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32, str33, str34, str35, str36, str37, str38, str39, str40, str41, str42, str43, str44, str45, str46, str47, str48, str49, str50, str51, str52, str53, str54, str55, str56, str57, str58, obj, str59, str60, str61, str62, str63, str64, str65, str66, str67, str68, str69, str70, obj2, str71, str72, obj3, str73, str74, obj4, str75, str76, str77, str78, bool);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Facility)) {
            return false;
        }
        Facility facility = (Facility) obj;
        return Intrinsics.areEqual((Object) this.auto_approve_inmates, (Object) facility.auto_approve_inmates) && Intrinsics.areEqual((Object) this.auto_approve_messages, (Object) facility.auto_approve_messages) && Intrinsics.areEqual((Object) this.available_times, (Object) facility.available_times) && Intrinsics.areEqual((Object) this.blocked_phone_numbers, (Object) facility.blocked_phone_numbers) && Intrinsics.areEqual((Object) this.call_video_dimensions, (Object) facility.call_video_dimensions) && Intrinsics.areEqual((Object) this.calls_disabled, (Object) facility.calls_disabled) && this.can_schedule == facility.can_schedule && Intrinsics.areEqual((Object) this.change_visitor_impacts_all_inmates, (Object) facility.change_visitor_impacts_all_inmates) && Intrinsics.areEqual((Object) this.created, (Object) facility.created) && Intrinsics.areEqual((Object) this.fms_app, (Object) facility.fms_app) && this.has_documents == facility.has_documents && this.has_request_forms == facility.has_request_forms && Intrinsics.areEqual((Object) this.id, (Object) facility.id) && Intrinsics.areEqual((Object) this.inmate_agreement_document_url, (Object) facility.inmate_agreement_document_url) && Intrinsics.areEqual((Object) this.inmate_agreement_message, (Object) facility.inmate_agreement_message) && Intrinsics.areEqual((Object) this.inmate_agreement_signature_required, (Object) facility.inmate_agreement_signature_required) && Intrinsics.areEqual((Object) this.inmate_credit_value_on_signup, (Object) facility.inmate_credit_value_on_signup) && Intrinsics.areEqual((Object) this.inmate_credit_value_on_signup_limit, (Object) facility.inmate_credit_value_on_signup_limit) && Intrinsics.areEqual((Object) this.inmate_hide_setup_link, (Object) facility.inmate_hide_setup_link) && Intrinsics.areEqual((Object) this.inmate_session_timeout, (Object) facility.inmate_session_timeout) && Intrinsics.areEqual((Object) this.inmate_tags, (Object) facility.inmate_tags) && Intrinsics.areEqual((Object) this.inmate_welcome_info, (Object) facility.inmate_welcome_info) && Intrinsics.areEqual((Object) this.inmate_zone_builder, (Object) facility.inmate_zone_builder) && Intrinsics.areEqual((Object) this.international_twilio_calls_disabled, (Object) facility.international_twilio_calls_disabled) && Intrinsics.areEqual((Object) this.lobby_news, (Object) facility.lobby_news) && Intrinsics.areEqual((Object) this.max_call_length, (Object) facility.max_call_length) && Intrinsics.areEqual((Object) this.max_text_message_length, (Object) facility.max_text_message_length) && Intrinsics.areEqual((Object) this.max_video_age, (Object) facility.max_video_age) && Intrinsics.areEqual((Object) this.max_video_message_length, (Object) facility.max_video_message_length) && Intrinsics.areEqual((Object) this.max_voice_call_length, (Object) facility.max_voice_call_length) && Intrinsics.areEqual((Object) this.price_per_s3_video_message, (Object) facility.price_per_s3_video_message) && Intrinsics.areEqual((Object) this.max_s3_video_message_length, (Object) facility.max_s3_video_message_length) && Intrinsics.areEqual((Object) this.min_call_length, (Object) facility.min_call_length) && Intrinsics.areEqual((Object) this.min_voice_call_length, (Object) facility.min_voice_call_length) && Intrinsics.areEqual((Object) this.name, (Object) facility.name) && Intrinsics.areEqual((Object) this.news, (Object) facility.news) && Intrinsics.areEqual((Object) this.notes, (Object) facility.notes) && Intrinsics.areEqual((Object) this.notify_pending_inmate_email, (Object) facility.notify_pending_inmate_email) && Intrinsics.areEqual((Object) this.notify_pending_message_email, (Object) facility.notify_pending_message_email) && Intrinsics.areEqual((Object) this.occupant_acc_paypal_credit_sku, (Object) facility.occupant_acc_paypal_credit_sku) && Intrinsics.areEqual((Object) this.occupant_acc_paypal_id, (Object) facility.occupant_acc_paypal_id) && Intrinsics.areEqual((Object) this.occupant_credits_disabled, (Object) facility.occupant_credits_disabled) && Intrinsics.areEqual((Object) this.occupant_movies_disabled, (Object) facility.occupant_movies_disabled) && Intrinsics.areEqual((Object) this.occupant_voice_calls_disabled, (Object) facility.occupant_voice_calls_disabled) && Intrinsics.areEqual((Object) this.occupant_voice_price_per_minute, (Object) facility.occupant_voice_price_per_minute) && Intrinsics.areEqual((Object) this.paypal_credit_sku, (Object) facility.paypal_credit_sku) && Intrinsics.areEqual((Object) this.paypal_id, (Object) facility.paypal_id) && Intrinsics.areEqual((Object) this.price_per_minute, (Object) facility.price_per_minute) && Intrinsics.areEqual((Object) this.price_per_text_message, (Object) facility.price_per_text_message) && Intrinsics.areEqual((Object) this.price_per_video_message, (Object) facility.price_per_video_message) && Intrinsics.areEqual((Object) this.pubid, (Object) facility.pubid) && Intrinsics.areEqual((Object) this.public_ip_addresses, (Object) facility.public_ip_addresses) && Intrinsics.areEqual((Object) this.require_photo_id, (Object) facility.require_photo_id) && Intrinsics.areEqual((Object) this.photo_lock_after_approval, (Object) facility.photo_lock_after_approval) && Intrinsics.areEqual((Object) this.require_resident, (Object) facility.require_resident) && Intrinsics.areEqual((Object) this.residents_auto_delete_inmates, (Object) facility.residents_auto_delete_inmates) && Intrinsics.areEqual((Object) this.residents_checksum, (Object) facility.residents_checksum) && Intrinsics.areEqual((Object) this.residents_importer, (Object) facility.residents_importer) && Intrinsics.areEqual((Object) this.residents_updated, (Object) facility.residents_updated) && Intrinsics.areEqual((Object) this.session_ad_code, (Object) facility.session_ad_code) && Intrinsics.areEqual((Object) this.session_image_link, (Object) facility.session_image_link) && Intrinsics.areEqual(this.session_image_url, facility.session_image_url) && Intrinsics.areEqual((Object) this.timezone, (Object) facility.timezone) && Intrinsics.areEqual((Object) this.timezone_id, (Object) facility.timezone_id) && Intrinsics.areEqual((Object) this.timezone_php, (Object) facility.timezone_php) && Intrinsics.areEqual((Object) this.twilio_fixed_markup_per_minute, (Object) facility.twilio_fixed_markup_per_minute) && Intrinsics.areEqual((Object) this.twilio_maximum_per_minute_rate, (Object) facility.twilio_maximum_per_minute_rate) && Intrinsics.areEqual((Object) this.twilio_percentage_markup_per_minute, (Object) facility.twilio_percentage_markup_per_minute) && Intrinsics.areEqual((Object) this.us_state, (Object) facility.us_state) && Intrinsics.areEqual((Object) this.us_state_abbreviation, (Object) facility.us_state_abbreviation) && Intrinsics.areEqual((Object) this.us_state_id, (Object) facility.us_state_id) && Intrinsics.areEqual((Object) this.video_bandwidth_down, (Object) facility.video_bandwidth_down) && Intrinsics.areEqual((Object) this.video_bandwidth_up, (Object) facility.video_bandwidth_up) && Intrinsics.areEqual((Object) this.video_camera_dimensions, (Object) facility.video_camera_dimensions) && Intrinsics.areEqual(this.video_codec, facility.video_codec) && Intrinsics.areEqual((Object) this.video_fps, (Object) facility.video_fps) && Intrinsics.areEqual((Object) this.video_quality, (Object) facility.video_quality) && Intrinsics.areEqual(this.visitor_blocked_phone_numbers, facility.visitor_blocked_phone_numbers) && Intrinsics.areEqual((Object) this.visitor_phone_voice_calls_disabled, (Object) facility.visitor_phone_voice_calls_disabled) && Intrinsics.areEqual((Object) this.voice_price_per_minute, (Object) facility.voice_price_per_minute) && Intrinsics.areEqual(this.welcome_image_url, facility.welcome_image_url) && Intrinsics.areEqual((Object) this.welcome_info, (Object) facility.welcome_info) && Intrinsics.areEqual((Object) this.transfer_fee, (Object) facility.transfer_fee) && Intrinsics.areEqual((Object) this.talk_to_me_credits_disabled, (Object) facility.talk_to_me_credits_disabled) && Intrinsics.areEqual((Object) this.hide_adding_inmate_general_funds, (Object) facility.hide_adding_inmate_general_funds) && Intrinsics.areEqual((Object) this.error_reporting, (Object) facility.error_reporting);
    }

    public int hashCode() {
        String str = this.auto_approve_inmates;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.auto_approve_messages;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.available_times;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.blocked_phone_numbers;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.call_video_dimensions;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.calls_disabled;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        boolean z = this.can_schedule;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode6 + (z ? 1 : 0)) * 31;
        String str7 = this.change_visitor_impacts_all_inmates;
        int hashCode7 = (i2 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.created;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.fms_app;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        boolean z3 = this.has_documents;
        if (z3) {
            z3 = true;
        }
        int i3 = (hashCode9 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.has_request_forms;
        if (!z4) {
            z2 = z4;
        }
        int i4 = (i3 + (z2 ? 1 : 0)) * 31;
        String str10 = this.id;
        int hashCode10 = (i4 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.inmate_agreement_document_url;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.inmate_agreement_message;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.inmate_agreement_signature_required;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.inmate_credit_value_on_signup;
        int hashCode14 = (hashCode13 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.inmate_credit_value_on_signup_limit;
        int hashCode15 = (hashCode14 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.inmate_hide_setup_link;
        int hashCode16 = (hashCode15 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.inmate_session_timeout;
        int hashCode17 = (hashCode16 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.inmate_tags;
        int hashCode18 = (hashCode17 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.inmate_welcome_info;
        int hashCode19 = (hashCode18 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.inmate_zone_builder;
        int hashCode20 = (hashCode19 + (str20 != null ? str20.hashCode() : 0)) * 31;
        String str21 = this.international_twilio_calls_disabled;
        int hashCode21 = (hashCode20 + (str21 != null ? str21.hashCode() : 0)) * 31;
        String str22 = this.lobby_news;
        int hashCode22 = (hashCode21 + (str22 != null ? str22.hashCode() : 0)) * 31;
        String str23 = this.max_call_length;
        int hashCode23 = (hashCode22 + (str23 != null ? str23.hashCode() : 0)) * 31;
        String str24 = this.max_text_message_length;
        int hashCode24 = (hashCode23 + (str24 != null ? str24.hashCode() : 0)) * 31;
        String str25 = this.max_video_age;
        int hashCode25 = (hashCode24 + (str25 != null ? str25.hashCode() : 0)) * 31;
        String str26 = this.max_video_message_length;
        int hashCode26 = (hashCode25 + (str26 != null ? str26.hashCode() : 0)) * 31;
        String str27 = this.max_voice_call_length;
        int hashCode27 = (hashCode26 + (str27 != null ? str27.hashCode() : 0)) * 31;
        String str28 = this.price_per_s3_video_message;
        int hashCode28 = (hashCode27 + (str28 != null ? str28.hashCode() : 0)) * 31;
        String str29 = this.max_s3_video_message_length;
        int hashCode29 = (hashCode28 + (str29 != null ? str29.hashCode() : 0)) * 31;
        String str30 = this.min_call_length;
        int hashCode30 = (hashCode29 + (str30 != null ? str30.hashCode() : 0)) * 31;
        String str31 = this.min_voice_call_length;
        int hashCode31 = (hashCode30 + (str31 != null ? str31.hashCode() : 0)) * 31;
        String str32 = this.name;
        int hashCode32 = (hashCode31 + (str32 != null ? str32.hashCode() : 0)) * 31;
        String str33 = this.news;
        int hashCode33 = (hashCode32 + (str33 != null ? str33.hashCode() : 0)) * 31;
        String str34 = this.notes;
        int hashCode34 = (hashCode33 + (str34 != null ? str34.hashCode() : 0)) * 31;
        String str35 = this.notify_pending_inmate_email;
        int hashCode35 = (hashCode34 + (str35 != null ? str35.hashCode() : 0)) * 31;
        String str36 = this.notify_pending_message_email;
        int hashCode36 = (hashCode35 + (str36 != null ? str36.hashCode() : 0)) * 31;
        String str37 = this.occupant_acc_paypal_credit_sku;
        int hashCode37 = (hashCode36 + (str37 != null ? str37.hashCode() : 0)) * 31;
        String str38 = this.occupant_acc_paypal_id;
        int hashCode38 = (hashCode37 + (str38 != null ? str38.hashCode() : 0)) * 31;
        String str39 = this.occupant_credits_disabled;
        int hashCode39 = (hashCode38 + (str39 != null ? str39.hashCode() : 0)) * 31;
        String str40 = this.occupant_movies_disabled;
        int hashCode40 = (hashCode39 + (str40 != null ? str40.hashCode() : 0)) * 31;
        String str41 = this.occupant_voice_calls_disabled;
        int hashCode41 = (hashCode40 + (str41 != null ? str41.hashCode() : 0)) * 31;
        String str42 = this.occupant_voice_price_per_minute;
        int hashCode42 = (hashCode41 + (str42 != null ? str42.hashCode() : 0)) * 31;
        String str43 = this.paypal_credit_sku;
        int hashCode43 = (hashCode42 + (str43 != null ? str43.hashCode() : 0)) * 31;
        String str44 = this.paypal_id;
        int hashCode44 = (hashCode43 + (str44 != null ? str44.hashCode() : 0)) * 31;
        String str45 = this.price_per_minute;
        int hashCode45 = (hashCode44 + (str45 != null ? str45.hashCode() : 0)) * 31;
        String str46 = this.price_per_text_message;
        int hashCode46 = (hashCode45 + (str46 != null ? str46.hashCode() : 0)) * 31;
        String str47 = this.price_per_video_message;
        int hashCode47 = (hashCode46 + (str47 != null ? str47.hashCode() : 0)) * 31;
        String str48 = this.pubid;
        int hashCode48 = (hashCode47 + (str48 != null ? str48.hashCode() : 0)) * 31;
        String str49 = this.public_ip_addresses;
        int hashCode49 = (hashCode48 + (str49 != null ? str49.hashCode() : 0)) * 31;
        String str50 = this.require_photo_id;
        int hashCode50 = (hashCode49 + (str50 != null ? str50.hashCode() : 0)) * 31;
        String str51 = this.photo_lock_after_approval;
        int hashCode51 = (hashCode50 + (str51 != null ? str51.hashCode() : 0)) * 31;
        String str52 = this.require_resident;
        int hashCode52 = (hashCode51 + (str52 != null ? str52.hashCode() : 0)) * 31;
        String str53 = this.residents_auto_delete_inmates;
        int hashCode53 = (hashCode52 + (str53 != null ? str53.hashCode() : 0)) * 31;
        String str54 = this.residents_checksum;
        int hashCode54 = (hashCode53 + (str54 != null ? str54.hashCode() : 0)) * 31;
        String str55 = this.residents_importer;
        int hashCode55 = (hashCode54 + (str55 != null ? str55.hashCode() : 0)) * 31;
        String str56 = this.residents_updated;
        int hashCode56 = (hashCode55 + (str56 != null ? str56.hashCode() : 0)) * 31;
        String str57 = this.session_ad_code;
        int hashCode57 = (hashCode56 + (str57 != null ? str57.hashCode() : 0)) * 31;
        String str58 = this.session_image_link;
        int hashCode58 = (hashCode57 + (str58 != null ? str58.hashCode() : 0)) * 31;
        Object obj = this.session_image_url;
        int hashCode59 = (hashCode58 + (obj != null ? obj.hashCode() : 0)) * 31;
        String str59 = this.timezone;
        int hashCode60 = (hashCode59 + (str59 != null ? str59.hashCode() : 0)) * 31;
        String str60 = this.timezone_id;
        int hashCode61 = (hashCode60 + (str60 != null ? str60.hashCode() : 0)) * 31;
        String str61 = this.timezone_php;
        int hashCode62 = (hashCode61 + (str61 != null ? str61.hashCode() : 0)) * 31;
        String str62 = this.twilio_fixed_markup_per_minute;
        int hashCode63 = (hashCode62 + (str62 != null ? str62.hashCode() : 0)) * 31;
        String str63 = this.twilio_maximum_per_minute_rate;
        int hashCode64 = (hashCode63 + (str63 != null ? str63.hashCode() : 0)) * 31;
        String str64 = this.twilio_percentage_markup_per_minute;
        int hashCode65 = (hashCode64 + (str64 != null ? str64.hashCode() : 0)) * 31;
        String str65 = this.us_state;
        int hashCode66 = (hashCode65 + (str65 != null ? str65.hashCode() : 0)) * 31;
        String str66 = this.us_state_abbreviation;
        int hashCode67 = (hashCode66 + (str66 != null ? str66.hashCode() : 0)) * 31;
        String str67 = this.us_state_id;
        int hashCode68 = (hashCode67 + (str67 != null ? str67.hashCode() : 0)) * 31;
        String str68 = this.video_bandwidth_down;
        int hashCode69 = (hashCode68 + (str68 != null ? str68.hashCode() : 0)) * 31;
        String str69 = this.video_bandwidth_up;
        int hashCode70 = (hashCode69 + (str69 != null ? str69.hashCode() : 0)) * 31;
        String str70 = this.video_camera_dimensions;
        int hashCode71 = (hashCode70 + (str70 != null ? str70.hashCode() : 0)) * 31;
        Object obj2 = this.video_codec;
        int hashCode72 = (hashCode71 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        String str71 = this.video_fps;
        int hashCode73 = (hashCode72 + (str71 != null ? str71.hashCode() : 0)) * 31;
        String str72 = this.video_quality;
        int hashCode74 = (hashCode73 + (str72 != null ? str72.hashCode() : 0)) * 31;
        Object obj3 = this.visitor_blocked_phone_numbers;
        int hashCode75 = (hashCode74 + (obj3 != null ? obj3.hashCode() : 0)) * 31;
        String str73 = this.visitor_phone_voice_calls_disabled;
        int hashCode76 = (hashCode75 + (str73 != null ? str73.hashCode() : 0)) * 31;
        String str74 = this.voice_price_per_minute;
        int hashCode77 = (hashCode76 + (str74 != null ? str74.hashCode() : 0)) * 31;
        Object obj4 = this.welcome_image_url;
        int hashCode78 = (hashCode77 + (obj4 != null ? obj4.hashCode() : 0)) * 31;
        String str75 = this.welcome_info;
        int hashCode79 = (hashCode78 + (str75 != null ? str75.hashCode() : 0)) * 31;
        String str76 = this.transfer_fee;
        int hashCode80 = (hashCode79 + (str76 != null ? str76.hashCode() : 0)) * 31;
        String str77 = this.talk_to_me_credits_disabled;
        int hashCode81 = (hashCode80 + (str77 != null ? str77.hashCode() : 0)) * 31;
        String str78 = this.hide_adding_inmate_general_funds;
        int hashCode82 = (hashCode81 + (str78 != null ? str78.hashCode() : 0)) * 31;
        Boolean bool = this.error_reporting;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode82 + i;
    }

    public Facility(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, String str8, String str9, boolean z2, boolean z3, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, String str56, String str57, String str58, Object obj, String str59, String str60, String str61, String str62, String str63, String str64, String str65, String str66, String str67, String str68, String str69, String str70, Object obj2, String str71, String str72, Object obj3, String str73, String str74, Object obj4, String str75, String str76, String str77, String str78, Boolean bool) {
        String str79 = str;
        String str80 = str2;
        String str81 = str3;
        String str82 = str4;
        String str83 = str5;
        String str84 = str6;
        String str85 = str7;
        String str86 = str8;
        String str87 = str9;
        String str88 = str10;
        String str89 = str12;
        String str90 = str13;
        String str91 = str14;
        String str92 = str15;
        String str93 = str17;
        Intrinsics.checkParameterIsNotNull(str79, "auto_approve_inmates");
        Intrinsics.checkParameterIsNotNull(str80, "auto_approve_messages");
        Intrinsics.checkParameterIsNotNull(str81, "available_times");
        Intrinsics.checkParameterIsNotNull(str82, "blocked_phone_numbers");
        Intrinsics.checkParameterIsNotNull(str83, "call_video_dimensions");
        Intrinsics.checkParameterIsNotNull(str84, "calls_disabled");
        Intrinsics.checkParameterIsNotNull(str85, "change_visitor_impacts_all_inmates");
        Intrinsics.checkParameterIsNotNull(str86, "created");
        Intrinsics.checkParameterIsNotNull(str87, "fms_app");
        Intrinsics.checkParameterIsNotNull(str88, "id");
        Intrinsics.checkParameterIsNotNull(str89, "inmate_agreement_message");
        Intrinsics.checkParameterIsNotNull(str90, "inmate_agreement_signature_required");
        Intrinsics.checkParameterIsNotNull(str91, "inmate_credit_value_on_signup");
        Intrinsics.checkParameterIsNotNull(str92, "inmate_credit_value_on_signup_limit");
        Intrinsics.checkParameterIsNotNull(str16, "inmate_hide_setup_link");
        Intrinsics.checkParameterIsNotNull(str17, "inmate_session_timeout");
        Intrinsics.checkParameterIsNotNull(str18, "inmate_tags");
        Intrinsics.checkParameterIsNotNull(str19, "inmate_welcome_info");
        Intrinsics.checkParameterIsNotNull(str20, "inmate_zone_builder");
        Intrinsics.checkParameterIsNotNull(str21, "international_twilio_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str22, "lobby_news");
        Intrinsics.checkParameterIsNotNull(str23, "max_call_length");
        Intrinsics.checkParameterIsNotNull(str24, "max_text_message_length");
        Intrinsics.checkParameterIsNotNull(str25, "max_video_age");
        Intrinsics.checkParameterIsNotNull(str26, "max_video_message_length");
        Intrinsics.checkParameterIsNotNull(str27, "max_voice_call_length");
        Intrinsics.checkParameterIsNotNull(str28, "price_per_s3_video_message");
        Intrinsics.checkParameterIsNotNull(str29, "max_s3_video_message_length");
        Intrinsics.checkParameterIsNotNull(str30, "min_call_length");
        Intrinsics.checkParameterIsNotNull(str31, "min_voice_call_length");
        Intrinsics.checkParameterIsNotNull(str32, "name");
        Intrinsics.checkParameterIsNotNull(str33, "news");
        Intrinsics.checkParameterIsNotNull(str34, "notes");
        Intrinsics.checkParameterIsNotNull(str35, "notify_pending_inmate_email");
        Intrinsics.checkParameterIsNotNull(str36, "notify_pending_message_email");
        Intrinsics.checkParameterIsNotNull(str37, "occupant_acc_paypal_credit_sku");
        Intrinsics.checkParameterIsNotNull(str38, "occupant_acc_paypal_id");
        Intrinsics.checkParameterIsNotNull(str39, "occupant_credits_disabled");
        Intrinsics.checkParameterIsNotNull(str40, "occupant_movies_disabled");
        Intrinsics.checkParameterIsNotNull(str41, "occupant_voice_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str42, "occupant_voice_price_per_minute");
        Intrinsics.checkParameterIsNotNull(str43, "paypal_credit_sku");
        Intrinsics.checkParameterIsNotNull(str44, "paypal_id");
        Intrinsics.checkParameterIsNotNull(str45, "price_per_minute");
        Intrinsics.checkParameterIsNotNull(str46, "price_per_text_message");
        Intrinsics.checkParameterIsNotNull(str47, "price_per_video_message");
        Intrinsics.checkParameterIsNotNull(str48, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str49, "public_ip_addresses");
        Intrinsics.checkParameterIsNotNull(str50, "require_photo_id");
        Intrinsics.checkParameterIsNotNull(str51, "photo_lock_after_approval");
        Intrinsics.checkParameterIsNotNull(str52, "require_resident");
        Intrinsics.checkParameterIsNotNull(str53, "residents_auto_delete_inmates");
        Intrinsics.checkParameterIsNotNull(str54, "residents_checksum");
        Intrinsics.checkParameterIsNotNull(str55, "residents_importer");
        Intrinsics.checkParameterIsNotNull(str56, "residents_updated");
        Intrinsics.checkParameterIsNotNull(str57, "session_ad_code");
        Intrinsics.checkParameterIsNotNull(str58, "session_image_link");
        Intrinsics.checkParameterIsNotNull(str59, "timezone");
        Intrinsics.checkParameterIsNotNull(str60, "timezone_id");
        Intrinsics.checkParameterIsNotNull(str61, "timezone_php");
        Intrinsics.checkParameterIsNotNull(str62, "twilio_fixed_markup_per_minute");
        Intrinsics.checkParameterIsNotNull(str63, "twilio_maximum_per_minute_rate");
        Intrinsics.checkParameterIsNotNull(str64, "twilio_percentage_markup_per_minute");
        Intrinsics.checkParameterIsNotNull(str65, "us_state");
        Intrinsics.checkParameterIsNotNull(str66, "us_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str67, "us_state_id");
        Intrinsics.checkParameterIsNotNull(str68, "video_bandwidth_down");
        Intrinsics.checkParameterIsNotNull(str69, "video_bandwidth_up");
        Intrinsics.checkParameterIsNotNull(str70, "video_camera_dimensions");
        Intrinsics.checkParameterIsNotNull(str71, "video_fps");
        Intrinsics.checkParameterIsNotNull(str72, "video_quality");
        Intrinsics.checkParameterIsNotNull(str73, "visitor_phone_voice_calls_disabled");
        Intrinsics.checkParameterIsNotNull(str74, "voice_price_per_minute");
        Intrinsics.checkParameterIsNotNull(str75, "welcome_info");
        Intrinsics.checkParameterIsNotNull(str77, "talk_to_me_credits_disabled");
        this.auto_approve_inmates = str79;
        this.auto_approve_messages = str80;
        this.available_times = str81;
        this.blocked_phone_numbers = str82;
        this.call_video_dimensions = str83;
        this.calls_disabled = str84;
        this.can_schedule = z;
        this.change_visitor_impacts_all_inmates = str85;
        this.created = str86;
        this.fms_app = str87;
        this.has_documents = z2;
        this.has_request_forms = z3;
        this.id = str88;
        this.inmate_agreement_document_url = str11;
        this.inmate_agreement_message = str89;
        this.inmate_agreement_signature_required = str90;
        this.inmate_credit_value_on_signup = str91;
        this.inmate_credit_value_on_signup_limit = str92;
        this.inmate_hide_setup_link = str16;
        this.inmate_session_timeout = str17;
        this.inmate_tags = str18;
        this.inmate_welcome_info = str19;
        this.inmate_zone_builder = str20;
        this.international_twilio_calls_disabled = str21;
        this.lobby_news = str22;
        this.max_call_length = str23;
        this.max_text_message_length = str24;
        this.max_video_age = str25;
        this.max_video_message_length = str26;
        this.max_voice_call_length = str27;
        this.price_per_s3_video_message = str28;
        this.max_s3_video_message_length = str29;
        this.min_call_length = str30;
        this.min_voice_call_length = str31;
        this.name = str32;
        this.news = str33;
        this.notes = str34;
        this.notify_pending_inmate_email = str35;
        this.notify_pending_message_email = str36;
        this.occupant_acc_paypal_credit_sku = str37;
        this.occupant_acc_paypal_id = str38;
        this.occupant_credits_disabled = str39;
        this.occupant_movies_disabled = str40;
        this.occupant_voice_calls_disabled = str41;
        this.occupant_voice_price_per_minute = str42;
        this.paypal_credit_sku = str43;
        this.paypal_id = str44;
        this.price_per_minute = str45;
        this.price_per_text_message = str46;
        this.price_per_video_message = str47;
        this.pubid = str48;
        this.public_ip_addresses = str49;
        this.require_photo_id = str50;
        this.photo_lock_after_approval = str51;
        this.require_resident = str52;
        this.residents_auto_delete_inmates = str53;
        this.residents_checksum = str54;
        this.residents_importer = str55;
        this.residents_updated = str56;
        this.session_ad_code = str57;
        this.session_image_link = str58;
        this.session_image_url = obj;
        this.timezone = str59;
        this.timezone_id = str60;
        this.timezone_php = str61;
        this.twilio_fixed_markup_per_minute = str62;
        this.twilio_maximum_per_minute_rate = str63;
        this.twilio_percentage_markup_per_minute = str64;
        this.us_state = str65;
        this.us_state_abbreviation = str66;
        this.us_state_id = str67;
        this.video_bandwidth_down = str68;
        this.video_bandwidth_up = str69;
        this.video_camera_dimensions = str70;
        this.video_codec = obj2;
        this.video_fps = str71;
        this.video_quality = str72;
        this.visitor_blocked_phone_numbers = obj3;
        this.visitor_phone_voice_calls_disabled = str73;
        this.voice_price_per_minute = str74;
        this.welcome_image_url = obj4;
        this.welcome_info = str75;
        this.transfer_fee = str76;
        this.talk_to_me_credits_disabled = str77;
        this.hide_adding_inmate_general_funds = str78;
        this.error_reporting = bool;
    }

    public final String getAuto_approve_inmates() {
        return this.auto_approve_inmates;
    }

    public final String getAuto_approve_messages() {
        return this.auto_approve_messages;
    }

    public final String getAvailable_times() {
        return this.available_times;
    }

    public final String getBlocked_phone_numbers() {
        return this.blocked_phone_numbers;
    }

    public final String getCall_video_dimensions() {
        return this.call_video_dimensions;
    }

    public final String getCalls_disabled() {
        return this.calls_disabled;
    }

    public final boolean getCan_schedule() {
        return this.can_schedule;
    }

    public final String getChange_visitor_impacts_all_inmates() {
        return this.change_visitor_impacts_all_inmates;
    }

    public final String getCreated() {
        return this.created;
    }

    public final String getFms_app() {
        return this.fms_app;
    }

    public final boolean getHas_documents() {
        return this.has_documents;
    }

    public final boolean getHas_request_forms() {
        return this.has_request_forms;
    }

    public final String getId() {
        return this.id;
    }

    public final String getInmate_agreement_document_url() {
        return this.inmate_agreement_document_url;
    }

    public final String getInmate_agreement_message() {
        return this.inmate_agreement_message;
    }

    public final String getInmate_agreement_signature_required() {
        return this.inmate_agreement_signature_required;
    }

    public final String getInmate_credit_value_on_signup() {
        return this.inmate_credit_value_on_signup;
    }

    public final String getInmate_credit_value_on_signup_limit() {
        return this.inmate_credit_value_on_signup_limit;
    }

    public final String getInmate_hide_setup_link() {
        return this.inmate_hide_setup_link;
    }

    public final String getInmate_session_timeout() {
        return this.inmate_session_timeout;
    }

    public final String getInmate_tags() {
        return this.inmate_tags;
    }

    public final String getInmate_welcome_info() {
        return this.inmate_welcome_info;
    }

    public final String getInmate_zone_builder() {
        return this.inmate_zone_builder;
    }

    public final String getInternational_twilio_calls_disabled() {
        return this.international_twilio_calls_disabled;
    }

    public final String getLobby_news() {
        return this.lobby_news;
    }

    public final String getMax_call_length() {
        return this.max_call_length;
    }

    public final String getMax_text_message_length() {
        return this.max_text_message_length;
    }

    public final String getMax_video_age() {
        return this.max_video_age;
    }

    public final String getMax_video_message_length() {
        return this.max_video_message_length;
    }

    public final String getMax_voice_call_length() {
        return this.max_voice_call_length;
    }

    public final String getPrice_per_s3_video_message() {
        return this.price_per_s3_video_message;
    }

    public final String getMax_s3_video_message_length() {
        return this.max_s3_video_message_length;
    }

    public final String getMin_call_length() {
        return this.min_call_length;
    }

    public final String getMin_voice_call_length() {
        return this.min_voice_call_length;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNews() {
        return this.news;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getNotify_pending_inmate_email() {
        return this.notify_pending_inmate_email;
    }

    public final String getNotify_pending_message_email() {
        return this.notify_pending_message_email;
    }

    public final String getOccupant_acc_paypal_credit_sku() {
        return this.occupant_acc_paypal_credit_sku;
    }

    public final String getOccupant_acc_paypal_id() {
        return this.occupant_acc_paypal_id;
    }

    public final String getOccupant_credits_disabled() {
        return this.occupant_credits_disabled;
    }

    public final String getOccupant_movies_disabled() {
        return this.occupant_movies_disabled;
    }

    public final String getOccupant_voice_calls_disabled() {
        return this.occupant_voice_calls_disabled;
    }

    public final String getOccupant_voice_price_per_minute() {
        return this.occupant_voice_price_per_minute;
    }

    public final String getPaypal_credit_sku() {
        return this.paypal_credit_sku;
    }

    public final String getPaypal_id() {
        return this.paypal_id;
    }

    public final String getPrice_per_minute() {
        return this.price_per_minute;
    }

    public final String getPrice_per_text_message() {
        return this.price_per_text_message;
    }

    public final String getPrice_per_video_message() {
        return this.price_per_video_message;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getPublic_ip_addresses() {
        return this.public_ip_addresses;
    }

    public final String getRequire_photo_id() {
        return this.require_photo_id;
    }

    public final String getPhoto_lock_after_approval() {
        return this.photo_lock_after_approval;
    }

    public final String getRequire_resident() {
        return this.require_resident;
    }

    public final String getResidents_auto_delete_inmates() {
        return this.residents_auto_delete_inmates;
    }

    public final String getResidents_checksum() {
        return this.residents_checksum;
    }

    public final String getResidents_importer() {
        return this.residents_importer;
    }

    public final String getResidents_updated() {
        return this.residents_updated;
    }

    public final String getSession_ad_code() {
        return this.session_ad_code;
    }

    public final String getSession_image_link() {
        return this.session_image_link;
    }

    public final Object getSession_image_url() {
        return this.session_image_url;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final String getTimezone_id() {
        return this.timezone_id;
    }

    public final String getTimezone_php() {
        return this.timezone_php;
    }

    public final String getTwilio_fixed_markup_per_minute() {
        return this.twilio_fixed_markup_per_minute;
    }

    public final String getTwilio_maximum_per_minute_rate() {
        return this.twilio_maximum_per_minute_rate;
    }

    public final String getTwilio_percentage_markup_per_minute() {
        return this.twilio_percentage_markup_per_minute;
    }

    public final String getUs_state() {
        return this.us_state;
    }

    public final String getUs_state_abbreviation() {
        return this.us_state_abbreviation;
    }

    public final String getUs_state_id() {
        return this.us_state_id;
    }

    public final String getVideo_bandwidth_down() {
        return this.video_bandwidth_down;
    }

    public final String getVideo_bandwidth_up() {
        return this.video_bandwidth_up;
    }

    public final String getVideo_camera_dimensions() {
        return this.video_camera_dimensions;
    }

    public final Object getVideo_codec() {
        return this.video_codec;
    }

    public final String getVideo_fps() {
        return this.video_fps;
    }

    public final String getVideo_quality() {
        return this.video_quality;
    }

    public final Object getVisitor_blocked_phone_numbers() {
        return this.visitor_blocked_phone_numbers;
    }

    public final String getVisitor_phone_voice_calls_disabled() {
        return this.visitor_phone_voice_calls_disabled;
    }

    public final String getVoice_price_per_minute() {
        return this.voice_price_per_minute;
    }

    public final Object getWelcome_image_url() {
        return this.welcome_image_url;
    }

    public final String getWelcome_info() {
        return this.welcome_info;
    }

    public final String getTransfer_fee() {
        return this.transfer_fee;
    }

    public final String getTalk_to_me_credits_disabled() {
        return this.talk_to_me_credits_disabled;
    }

    public final String getHide_adding_inmate_general_funds() {
        return this.hide_adding_inmate_general_funds;
    }

    public final Boolean getError_reporting() {
        return this.error_reporting;
    }

    public String toString() {
        return this.name;
    }

    public final boolean isGeneralFundsDisabled() {
        return Intrinsics.areEqual((Object) this.occupant_credits_disabled, (Object) "1") || Intrinsics.areEqual((Object) this.hide_adding_inmate_general_funds, (Object) "1");
    }

    public final boolean isTalkToMeFundsDisabled() {
        return Intrinsics.areEqual((Object) this.talk_to_me_credits_disabled, (Object) "1");
    }

    public final boolean isErrorReportEnabled() {
        return Intrinsics.areEqual((Object) this.error_reporting, (Object) true);
    }
}
