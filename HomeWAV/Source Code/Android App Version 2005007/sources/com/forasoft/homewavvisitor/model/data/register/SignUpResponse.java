package com.forasoft.homewavvisitor.model.data.register;

import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.auth.UserPhoto;
import com.google.gson.annotations.SerializedName;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.util.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.opencv.global.opencv_core;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\bq\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bá\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u001d\u001a\u00020\u0004\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0001\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010$\u001a\u00020\u0004\u0012\u0006\u0010%\u001a\u00020\u0004\u0012\b\u0010&\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010'\u001a\u00020\u0004\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010)\u001a\u00020\u0004\u0012\u0006\u0010*\u001a\u00020\u0004\u0012\u0006\u0010+\u001a\u00020\u0004\u0012\u0006\u0010,\u001a\u00020\u0004\u0012\b\u0010-\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010.\u001a\u00020\u0004\u0012\b\u0010/\u001a\u0004\u0018\u00010\u0001\u0012\b\u00100\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u00101\u001a\u00020\u0004\u0012\u0006\u00102\u001a\u00020\u0004\u0012\u0006\u00103\u001a\u00020\u0004\u0012\u0006\u00104\u001a\u00020\u0004\u0012\u0006\u00105\u001a\u00020\u0004\u0012\u0006\u00106\u001a\u00020\u0004\u0012\b\u00107\u001a\u0004\u0018\u000108\u0012\b\u00109\u001a\u0004\u0018\u000108\u0012\u0006\u0010:\u001a\u00020\u0006¢\u0006\u0002\u0010;J\u000b\u0010p\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010q\u001a\u00020\u0004HÆ\u0003J\t\u0010r\u001a\u00020\u0004HÆ\u0003J\t\u0010s\u001a\u00020\u0004HÆ\u0003J\t\u0010t\u001a\u00020\u0004HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010v\u001a\u00020\u0004HÆ\u0003J\t\u0010w\u001a\u00020\u0004HÆ\u0003J\t\u0010x\u001a\u00020\u0004HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010z\u001a\u00020\u0004HÆ\u0003J\t\u0010{\u001a\u00020\u0004HÆ\u0003J\t\u0010|\u001a\u00020\u0004HÆ\u0003J\t\u0010}\u001a\u00020\u0004HÆ\u0003J\t\u0010~\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0006HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0006HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0001\u001a\u0004\u0018\u000108HÆ\u0003J\f\u0010 \u0001\u001a\u0004\u0018\u000108HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0006HÆ\u0003J\f\u0010¢\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010£\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010¥\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003JÒ\u0004\u0010¦\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u00042\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010'\u001a\u00020\u00042\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00042\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010.\u001a\u00020\u00042\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u00042\b\b\u0002\u00103\u001a\u00020\u00042\b\b\u0002\u00104\u001a\u00020\u00042\b\b\u0002\u00105\u001a\u00020\u00042\b\b\u0002\u00106\u001a\u00020\u00042\n\b\u0002\u00107\u001a\u0004\u0018\u0001082\n\b\u0002\u00109\u001a\u0004\u0018\u0001082\b\b\u0002\u0010:\u001a\u00020\u0006HÆ\u0001J\u0015\u0010§\u0001\u001a\u00020\u00062\t\u0010¨\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000b\u0010©\u0001\u001a\u00030ª\u0001HÖ\u0001J\n\u0010«\u0001\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bB\u0010AR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010?R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bD\u0010=R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bE\u0010=R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010?R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bG\u0010=R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010?R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010?R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010?R\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010?R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bL\u0010=R\u0011\u0010\u0012\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010?R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010?R\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010?R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010=R\u0011\u0010\u0016\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010?R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010?R\u0011\u0010\u0018\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010?R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010?R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bO\u0010=R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bP\u0010=R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010=R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010?R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bS\u0010=R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bT\u0010=R\u0013\u0010 \u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bU\u0010=R\u0013\u0010!\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bV\u0010=R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bW\u0010=R\u0013\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bX\u0010=R\u0011\u0010$\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010?R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010?R\u0013\u00107\u001a\u0004\u0018\u000108¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0013\u0010&\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010?R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010?R\u0013\u00109\u001a\u0004\u0018\u000108¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\\R\u0013\u0010(\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010?R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010?R\u0011\u0010*\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010?R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bc\u0010?R\u0011\u0010,\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010?R\u0013\u0010-\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\be\u0010=R\u0011\u0010.\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010?R\u0013\u0010/\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bg\u0010=R\u0013\u00100\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\bh\u0010=R\u0011\u00101\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010?R\u0011\u00102\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010?R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010?R\u0011\u00104\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010?R\u0016\u0010:\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bm\u0010AR\u0011\u00105\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010?R\u0011\u00106\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010?¨\u0006¬\u0001"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/register/SignUpResponse;", "", "braintree_user_id", "calls_free", "", "can_text_message", "", "can_video_message", "city", "code", "code_created", "created", "device_os", "dob", "email", "first_name", "full_name", "gcm_token", "id", "is_admin", "is_fraud", "is_fraud_set_by", "is_mega_admin", "is_operator", "is_visitor", "last_name", "message_notification_email", "message_notification_phone", "middle_initial", "notification_subscription_enabled", "operator_auto_delete", "operator_id", "operator_permissions", "operator_prison_id", "operator_prison_id_list", "operator_prison_name", "password", "password_changed_on", "person_capture_id", "phone", "photo_id_capture_id", "php_session_id", "pin", "should_record", "street1", "street2", "super_admin_permissions", "timezone", "timezone_php", "us_state", "us_state_abbreviation", "us_state_id", "username", "visitor_id", "zipcode", "person_capture", "Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;", "photo_id_capture", "verified", "(Ljava/lang/Object;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;Z)V", "getBraintree_user_id", "()Ljava/lang/Object;", "getCalls_free", "()Ljava/lang/String;", "getCan_text_message", "()Z", "getCan_video_message", "getCity", "getCode", "getCode_created", "getCreated", "getDevice_os", "getDob", "getEmail", "getFirst_name", "getFull_name", "getGcm_token", "getId", "getLast_name", "getMessage_notification_email", "getMessage_notification_phone", "getMiddle_initial", "getNotification_subscription_enabled", "getOperator_auto_delete", "getOperator_id", "getOperator_permissions", "getOperator_prison_id", "getOperator_prison_id_list", "getOperator_prison_name", "getPassword", "getPassword_changed_on", "getPerson_capture", "()Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;", "getPerson_capture_id", "getPhone", "getPhoto_id_capture", "getPhoto_id_capture_id", "getPhp_session_id", "getPin", "getShould_record", "getStreet1", "getStreet2", "getSuper_admin_permissions", "getTimezone", "getTimezone_php", "getUs_state", "getUs_state_abbreviation", "getUs_state_id", "getUsername", "getVerified", "getVisitor_id", "getZipcode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUpResponse.kt */
public final class SignUpResponse {
    private final Object braintree_user_id;
    private final String calls_free;
    private final boolean can_text_message;
    private final boolean can_video_message;
    private final String city;
    private final Object code;
    private final Object code_created;
    private final String created;
    private final Object device_os;
    private final String dob;
    private final String email;
    private final String first_name;
    private final String full_name;
    private final Object gcm_token;
    private final String id;
    private final String is_admin;
    private final String is_fraud;
    private final Object is_fraud_set_by;
    private final String is_mega_admin;
    private final String is_operator;
    private final String is_visitor;
    private final String last_name;
    private final Object message_notification_email;
    private final Object message_notification_phone;
    private final Object middle_initial;
    private final String notification_subscription_enabled;
    private final Object operator_auto_delete;
    private final Object operator_id;
    private final Object operator_permissions;
    private final Object operator_prison_id;
    private final Object operator_prison_id_list;
    private final Object operator_prison_name;
    private final String password;
    private final String password_changed_on;
    private final UserPhoto person_capture;
    private final String person_capture_id;
    private final String phone;
    private final UserPhoto photo_id_capture;
    private final String photo_id_capture_id;
    private final String php_session_id;
    private final String pin;
    private final String should_record;
    private final String street1;
    private final Object street2;
    private final String super_admin_permissions;
    private final Object timezone;
    private final Object timezone_php;
    private final String us_state;
    private final String us_state_abbreviation;
    private final String us_state_id;
    private final String username;
    @SerializedName("signup_verified")
    private final boolean verified;
    private final String visitor_id;
    private final String zipcode;

    public static /* synthetic */ SignUpResponse copy$default(SignUpResponse signUpResponse, Object obj, String str, boolean z, boolean z2, String str2, Object obj2, Object obj3, String str3, Object obj4, String str4, String str5, String str6, String str7, Object obj5, String str8, String str9, String str10, Object obj6, String str11, String str12, String str13, String str14, Object obj7, Object obj8, Object obj9, String str15, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, Object obj16, String str25, Object obj17, Object obj18, String str26, String str27, String str28, String str29, String str30, String str31, UserPhoto userPhoto, UserPhoto userPhoto2, boolean z3, int i, int i2, Object obj19) {
        SignUpResponse signUpResponse2 = signUpResponse;
        int i3 = i;
        int i4 = i2;
        return signUpResponse.copy((i3 & 1) != 0 ? signUpResponse2.braintree_user_id : obj, (i3 & 2) != 0 ? signUpResponse2.calls_free : str, (i3 & 4) != 0 ? signUpResponse2.can_text_message : z, (i3 & 8) != 0 ? signUpResponse2.can_video_message : z2, (i3 & 16) != 0 ? signUpResponse2.city : str2, (i3 & 32) != 0 ? signUpResponse2.code : obj2, (i3 & 64) != 0 ? signUpResponse2.code_created : obj3, (i3 & 128) != 0 ? signUpResponse2.created : str3, (i3 & 256) != 0 ? signUpResponse2.device_os : obj4, (i3 & 512) != 0 ? signUpResponse2.dob : str4, (i3 & 1024) != 0 ? signUpResponse2.email : str5, (i3 & 2048) != 0 ? signUpResponse2.first_name : str6, (i3 & 4096) != 0 ? signUpResponse2.full_name : str7, (i3 & 8192) != 0 ? signUpResponse2.gcm_token : obj5, (i3 & 16384) != 0 ? signUpResponse2.id : str8, (i3 & 32768) != 0 ? signUpResponse2.is_admin : str9, (i3 & 65536) != 0 ? signUpResponse2.is_fraud : str10, (i3 & 131072) != 0 ? signUpResponse2.is_fraud_set_by : obj6, (i3 & 262144) != 0 ? signUpResponse2.is_mega_admin : str11, (i3 & 524288) != 0 ? signUpResponse2.is_operator : str12, (i3 & 1048576) != 0 ? signUpResponse2.is_visitor : str13, (i3 & 2097152) != 0 ? signUpResponse2.last_name : str14, (i3 & 4194304) != 0 ? signUpResponse2.message_notification_email : obj7, (i3 & 8388608) != 0 ? signUpResponse2.message_notification_phone : obj8, (i3 & 16777216) != 0 ? signUpResponse2.middle_initial : obj9, (i3 & opencv_core.ACCESS_WRITE) != 0 ? signUpResponse2.notification_subscription_enabled : str15, (i3 & 67108864) != 0 ? signUpResponse2.operator_auto_delete : obj10, (i3 & 134217728) != 0 ? signUpResponse2.operator_id : obj11, (i3 & 268435456) != 0 ? signUpResponse2.operator_permissions : obj12, (i3 & 536870912) != 0 ? signUpResponse2.operator_prison_id : obj13, (i3 & 1073741824) != 0 ? signUpResponse2.operator_prison_id_list : obj14, (i3 & Integer.MIN_VALUE) != 0 ? signUpResponse2.operator_prison_name : obj15, (i4 & 1) != 0 ? signUpResponse2.password : str16, (i4 & 2) != 0 ? signUpResponse2.password_changed_on : str17, (i4 & 4) != 0 ? signUpResponse2.person_capture_id : str18, (i4 & 8) != 0 ? signUpResponse2.phone : str19, (i4 & 16) != 0 ? signUpResponse2.photo_id_capture_id : str20, (i4 & 32) != 0 ? signUpResponse2.php_session_id : str21, (i4 & 64) != 0 ? signUpResponse2.pin : str22, (i4 & 128) != 0 ? signUpResponse2.should_record : str23, (i4 & 256) != 0 ? signUpResponse2.street1 : str24, (i4 & 512) != 0 ? signUpResponse2.street2 : obj16, (i4 & 1024) != 0 ? signUpResponse2.super_admin_permissions : str25, (i4 & 2048) != 0 ? signUpResponse2.timezone : obj17, (i4 & 4096) != 0 ? signUpResponse2.timezone_php : obj18, (i4 & 8192) != 0 ? signUpResponse2.us_state : str26, (i4 & 16384) != 0 ? signUpResponse2.us_state_abbreviation : str27, (i4 & 32768) != 0 ? signUpResponse2.us_state_id : str28, (i4 & 65536) != 0 ? signUpResponse2.username : str29, (i4 & 131072) != 0 ? signUpResponse2.visitor_id : str30, (i4 & 262144) != 0 ? signUpResponse2.zipcode : str31, (i4 & 524288) != 0 ? signUpResponse2.person_capture : userPhoto, (i4 & 1048576) != 0 ? signUpResponse2.photo_id_capture : userPhoto2, (i4 & 2097152) != 0 ? signUpResponse2.verified : z3);
    }

    public final Object component1() {
        return this.braintree_user_id;
    }

    public final String component10() {
        return this.dob;
    }

    public final String component11() {
        return this.email;
    }

    public final String component12() {
        return this.first_name;
    }

    public final String component13() {
        return this.full_name;
    }

    public final Object component14() {
        return this.gcm_token;
    }

    public final String component15() {
        return this.id;
    }

    public final String component16() {
        return this.is_admin;
    }

    public final String component17() {
        return this.is_fraud;
    }

    public final Object component18() {
        return this.is_fraud_set_by;
    }

    public final String component19() {
        return this.is_mega_admin;
    }

    public final String component2() {
        return this.calls_free;
    }

    public final String component20() {
        return this.is_operator;
    }

    public final String component21() {
        return this.is_visitor;
    }

    public final String component22() {
        return this.last_name;
    }

    public final Object component23() {
        return this.message_notification_email;
    }

    public final Object component24() {
        return this.message_notification_phone;
    }

    public final Object component25() {
        return this.middle_initial;
    }

    public final String component26() {
        return this.notification_subscription_enabled;
    }

    public final Object component27() {
        return this.operator_auto_delete;
    }

    public final Object component28() {
        return this.operator_id;
    }

    public final Object component29() {
        return this.operator_permissions;
    }

    public final boolean component3() {
        return this.can_text_message;
    }

    public final Object component30() {
        return this.operator_prison_id;
    }

    public final Object component31() {
        return this.operator_prison_id_list;
    }

    public final Object component32() {
        return this.operator_prison_name;
    }

    public final String component33() {
        return this.password;
    }

    public final String component34() {
        return this.password_changed_on;
    }

    public final String component35() {
        return this.person_capture_id;
    }

    public final String component36() {
        return this.phone;
    }

    public final String component37() {
        return this.photo_id_capture_id;
    }

    public final String component38() {
        return this.php_session_id;
    }

    public final String component39() {
        return this.pin;
    }

    public final boolean component4() {
        return this.can_video_message;
    }

    public final String component40() {
        return this.should_record;
    }

    public final String component41() {
        return this.street1;
    }

    public final Object component42() {
        return this.street2;
    }

    public final String component43() {
        return this.super_admin_permissions;
    }

    public final Object component44() {
        return this.timezone;
    }

    public final Object component45() {
        return this.timezone_php;
    }

    public final String component46() {
        return this.us_state;
    }

    public final String component47() {
        return this.us_state_abbreviation;
    }

    public final String component48() {
        return this.us_state_id;
    }

    public final String component49() {
        return this.username;
    }

    public final String component5() {
        return this.city;
    }

    public final String component50() {
        return this.visitor_id;
    }

    public final String component51() {
        return this.zipcode;
    }

    public final UserPhoto component52() {
        return this.person_capture;
    }

    public final UserPhoto component53() {
        return this.photo_id_capture;
    }

    public final boolean component54() {
        return this.verified;
    }

    public final Object component6() {
        return this.code;
    }

    public final Object component7() {
        return this.code_created;
    }

    public final String component8() {
        return this.created;
    }

    public final Object component9() {
        return this.device_os;
    }

    public final SignUpResponse copy(Object obj, String str, boolean z, boolean z2, String str2, Object obj2, Object obj3, String str3, Object obj4, String str4, String str5, String str6, String str7, Object obj5, String str8, String str9, String str10, Object obj6, String str11, String str12, String str13, String str14, Object obj7, Object obj8, Object obj9, String str15, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, Object obj16, String str25, Object obj17, Object obj18, String str26, String str27, String str28, String str29, String str30, String str31, UserPhoto userPhoto, UserPhoto userPhoto2, boolean z3) {
        Object obj19 = obj;
        Intrinsics.checkParameterIsNotNull(str, "calls_free");
        Intrinsics.checkParameterIsNotNull(str2, "city");
        Intrinsics.checkParameterIsNotNull(str3, "created");
        Intrinsics.checkParameterIsNotNull(str4, "dob");
        Intrinsics.checkParameterIsNotNull(str5, "email");
        Intrinsics.checkParameterIsNotNull(str6, Attributes.FIRST_NAME);
        Intrinsics.checkParameterIsNotNull(str7, Attributes.FULL_NAME);
        Intrinsics.checkParameterIsNotNull(str8, "id");
        Intrinsics.checkParameterIsNotNull(str9, "is_admin");
        Intrinsics.checkParameterIsNotNull(str10, "is_fraud");
        Intrinsics.checkParameterIsNotNull(str11, "is_mega_admin");
        Intrinsics.checkParameterIsNotNull(str12, "is_operator");
        Intrinsics.checkParameterIsNotNull(str13, "is_visitor");
        Intrinsics.checkParameterIsNotNull(str14, Attributes.LAST_NAME);
        Intrinsics.checkParameterIsNotNull(str15, "notification_subscription_enabled");
        Intrinsics.checkParameterIsNotNull(str16, "password");
        Intrinsics.checkParameterIsNotNull(str17, "password_changed_on");
        Intrinsics.checkParameterIsNotNull(str19, ShippingInfoWidget.PHONE_FIELD);
        Intrinsics.checkParameterIsNotNull(str21, "php_session_id");
        Intrinsics.checkParameterIsNotNull(str22, "pin");
        Intrinsics.checkParameterIsNotNull(str23, "should_record");
        Intrinsics.checkParameterIsNotNull(str24, PostalAddressParser.STREET_ADDRESS_KEY);
        Intrinsics.checkParameterIsNotNull(str25, "super_admin_permissions");
        Intrinsics.checkParameterIsNotNull(str26, "us_state");
        Intrinsics.checkParameterIsNotNull(str27, "us_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str28, "us_state_id");
        Intrinsics.checkParameterIsNotNull(str29, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str30, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str31, "zipcode");
        return new SignUpResponse(obj, str, z, z2, str2, obj2, obj3, str3, obj4, str4, str5, str6, str7, obj5, str8, str9, str10, obj6, str11, str12, str13, str14, obj7, obj8, obj9, str15, obj10, obj11, obj12, obj13, obj14, obj15, str16, str17, str18, str19, str20, str21, str22, str23, str24, obj16, str25, obj17, obj18, str26, str27, str28, str29, str30, str31, userPhoto, userPhoto2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignUpResponse)) {
            return false;
        }
        SignUpResponse signUpResponse = (SignUpResponse) obj;
        return Intrinsics.areEqual(this.braintree_user_id, signUpResponse.braintree_user_id) && Intrinsics.areEqual((Object) this.calls_free, (Object) signUpResponse.calls_free) && this.can_text_message == signUpResponse.can_text_message && this.can_video_message == signUpResponse.can_video_message && Intrinsics.areEqual((Object) this.city, (Object) signUpResponse.city) && Intrinsics.areEqual(this.code, signUpResponse.code) && Intrinsics.areEqual(this.code_created, signUpResponse.code_created) && Intrinsics.areEqual((Object) this.created, (Object) signUpResponse.created) && Intrinsics.areEqual(this.device_os, signUpResponse.device_os) && Intrinsics.areEqual((Object) this.dob, (Object) signUpResponse.dob) && Intrinsics.areEqual((Object) this.email, (Object) signUpResponse.email) && Intrinsics.areEqual((Object) this.first_name, (Object) signUpResponse.first_name) && Intrinsics.areEqual((Object) this.full_name, (Object) signUpResponse.full_name) && Intrinsics.areEqual(this.gcm_token, signUpResponse.gcm_token) && Intrinsics.areEqual((Object) this.id, (Object) signUpResponse.id) && Intrinsics.areEqual((Object) this.is_admin, (Object) signUpResponse.is_admin) && Intrinsics.areEqual((Object) this.is_fraud, (Object) signUpResponse.is_fraud) && Intrinsics.areEqual(this.is_fraud_set_by, signUpResponse.is_fraud_set_by) && Intrinsics.areEqual((Object) this.is_mega_admin, (Object) signUpResponse.is_mega_admin) && Intrinsics.areEqual((Object) this.is_operator, (Object) signUpResponse.is_operator) && Intrinsics.areEqual((Object) this.is_visitor, (Object) signUpResponse.is_visitor) && Intrinsics.areEqual((Object) this.last_name, (Object) signUpResponse.last_name) && Intrinsics.areEqual(this.message_notification_email, signUpResponse.message_notification_email) && Intrinsics.areEqual(this.message_notification_phone, signUpResponse.message_notification_phone) && Intrinsics.areEqual(this.middle_initial, signUpResponse.middle_initial) && Intrinsics.areEqual((Object) this.notification_subscription_enabled, (Object) signUpResponse.notification_subscription_enabled) && Intrinsics.areEqual(this.operator_auto_delete, signUpResponse.operator_auto_delete) && Intrinsics.areEqual(this.operator_id, signUpResponse.operator_id) && Intrinsics.areEqual(this.operator_permissions, signUpResponse.operator_permissions) && Intrinsics.areEqual(this.operator_prison_id, signUpResponse.operator_prison_id) && Intrinsics.areEqual(this.operator_prison_id_list, signUpResponse.operator_prison_id_list) && Intrinsics.areEqual(this.operator_prison_name, signUpResponse.operator_prison_name) && Intrinsics.areEqual((Object) this.password, (Object) signUpResponse.password) && Intrinsics.areEqual((Object) this.password_changed_on, (Object) signUpResponse.password_changed_on) && Intrinsics.areEqual((Object) this.person_capture_id, (Object) signUpResponse.person_capture_id) && Intrinsics.areEqual((Object) this.phone, (Object) signUpResponse.phone) && Intrinsics.areEqual((Object) this.photo_id_capture_id, (Object) signUpResponse.photo_id_capture_id) && Intrinsics.areEqual((Object) this.php_session_id, (Object) signUpResponse.php_session_id) && Intrinsics.areEqual((Object) this.pin, (Object) signUpResponse.pin) && Intrinsics.areEqual((Object) this.should_record, (Object) signUpResponse.should_record) && Intrinsics.areEqual((Object) this.street1, (Object) signUpResponse.street1) && Intrinsics.areEqual(this.street2, signUpResponse.street2) && Intrinsics.areEqual((Object) this.super_admin_permissions, (Object) signUpResponse.super_admin_permissions) && Intrinsics.areEqual(this.timezone, signUpResponse.timezone) && Intrinsics.areEqual(this.timezone_php, signUpResponse.timezone_php) && Intrinsics.areEqual((Object) this.us_state, (Object) signUpResponse.us_state) && Intrinsics.areEqual((Object) this.us_state_abbreviation, (Object) signUpResponse.us_state_abbreviation) && Intrinsics.areEqual((Object) this.us_state_id, (Object) signUpResponse.us_state_id) && Intrinsics.areEqual((Object) this.username, (Object) signUpResponse.username) && Intrinsics.areEqual((Object) this.visitor_id, (Object) signUpResponse.visitor_id) && Intrinsics.areEqual((Object) this.zipcode, (Object) signUpResponse.zipcode) && Intrinsics.areEqual((Object) this.person_capture, (Object) signUpResponse.person_capture) && Intrinsics.areEqual((Object) this.photo_id_capture, (Object) signUpResponse.photo_id_capture) && this.verified == signUpResponse.verified;
    }

    public int hashCode() {
        Object obj = this.braintree_user_id;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        String str = this.calls_free;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        boolean z = this.can_text_message;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode2 + (z ? 1 : 0)) * 31;
        boolean z3 = this.can_video_message;
        if (z3) {
            z3 = true;
        }
        int i3 = (i2 + (z3 ? 1 : 0)) * 31;
        String str2 = this.city;
        int hashCode3 = (i3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Object obj2 = this.code;
        int hashCode4 = (hashCode3 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Object obj3 = this.code_created;
        int hashCode5 = (hashCode4 + (obj3 != null ? obj3.hashCode() : 0)) * 31;
        String str3 = this.created;
        int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Object obj4 = this.device_os;
        int hashCode7 = (hashCode6 + (obj4 != null ? obj4.hashCode() : 0)) * 31;
        String str4 = this.dob;
        int hashCode8 = (hashCode7 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.email;
        int hashCode9 = (hashCode8 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.first_name;
        int hashCode10 = (hashCode9 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.full_name;
        int hashCode11 = (hashCode10 + (str7 != null ? str7.hashCode() : 0)) * 31;
        Object obj5 = this.gcm_token;
        int hashCode12 = (hashCode11 + (obj5 != null ? obj5.hashCode() : 0)) * 31;
        String str8 = this.id;
        int hashCode13 = (hashCode12 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.is_admin;
        int hashCode14 = (hashCode13 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.is_fraud;
        int hashCode15 = (hashCode14 + (str10 != null ? str10.hashCode() : 0)) * 31;
        Object obj6 = this.is_fraud_set_by;
        int hashCode16 = (hashCode15 + (obj6 != null ? obj6.hashCode() : 0)) * 31;
        String str11 = this.is_mega_admin;
        int hashCode17 = (hashCode16 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.is_operator;
        int hashCode18 = (hashCode17 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.is_visitor;
        int hashCode19 = (hashCode18 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.last_name;
        int hashCode20 = (hashCode19 + (str14 != null ? str14.hashCode() : 0)) * 31;
        Object obj7 = this.message_notification_email;
        int hashCode21 = (hashCode20 + (obj7 != null ? obj7.hashCode() : 0)) * 31;
        Object obj8 = this.message_notification_phone;
        int hashCode22 = (hashCode21 + (obj8 != null ? obj8.hashCode() : 0)) * 31;
        Object obj9 = this.middle_initial;
        int hashCode23 = (hashCode22 + (obj9 != null ? obj9.hashCode() : 0)) * 31;
        String str15 = this.notification_subscription_enabled;
        int hashCode24 = (hashCode23 + (str15 != null ? str15.hashCode() : 0)) * 31;
        Object obj10 = this.operator_auto_delete;
        int hashCode25 = (hashCode24 + (obj10 != null ? obj10.hashCode() : 0)) * 31;
        Object obj11 = this.operator_id;
        int hashCode26 = (hashCode25 + (obj11 != null ? obj11.hashCode() : 0)) * 31;
        Object obj12 = this.operator_permissions;
        int hashCode27 = (hashCode26 + (obj12 != null ? obj12.hashCode() : 0)) * 31;
        Object obj13 = this.operator_prison_id;
        int hashCode28 = (hashCode27 + (obj13 != null ? obj13.hashCode() : 0)) * 31;
        Object obj14 = this.operator_prison_id_list;
        int hashCode29 = (hashCode28 + (obj14 != null ? obj14.hashCode() : 0)) * 31;
        Object obj15 = this.operator_prison_name;
        int hashCode30 = (hashCode29 + (obj15 != null ? obj15.hashCode() : 0)) * 31;
        String str16 = this.password;
        int hashCode31 = (hashCode30 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.password_changed_on;
        int hashCode32 = (hashCode31 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.person_capture_id;
        int hashCode33 = (hashCode32 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.phone;
        int hashCode34 = (hashCode33 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.photo_id_capture_id;
        int hashCode35 = (hashCode34 + (str20 != null ? str20.hashCode() : 0)) * 31;
        String str21 = this.php_session_id;
        int hashCode36 = (hashCode35 + (str21 != null ? str21.hashCode() : 0)) * 31;
        String str22 = this.pin;
        int hashCode37 = (hashCode36 + (str22 != null ? str22.hashCode() : 0)) * 31;
        String str23 = this.should_record;
        int hashCode38 = (hashCode37 + (str23 != null ? str23.hashCode() : 0)) * 31;
        String str24 = this.street1;
        int hashCode39 = (hashCode38 + (str24 != null ? str24.hashCode() : 0)) * 31;
        Object obj16 = this.street2;
        int hashCode40 = (hashCode39 + (obj16 != null ? obj16.hashCode() : 0)) * 31;
        String str25 = this.super_admin_permissions;
        int hashCode41 = (hashCode40 + (str25 != null ? str25.hashCode() : 0)) * 31;
        Object obj17 = this.timezone;
        int hashCode42 = (hashCode41 + (obj17 != null ? obj17.hashCode() : 0)) * 31;
        Object obj18 = this.timezone_php;
        int hashCode43 = (hashCode42 + (obj18 != null ? obj18.hashCode() : 0)) * 31;
        String str26 = this.us_state;
        int hashCode44 = (hashCode43 + (str26 != null ? str26.hashCode() : 0)) * 31;
        String str27 = this.us_state_abbreviation;
        int hashCode45 = (hashCode44 + (str27 != null ? str27.hashCode() : 0)) * 31;
        String str28 = this.us_state_id;
        int hashCode46 = (hashCode45 + (str28 != null ? str28.hashCode() : 0)) * 31;
        String str29 = this.username;
        int hashCode47 = (hashCode46 + (str29 != null ? str29.hashCode() : 0)) * 31;
        String str30 = this.visitor_id;
        int hashCode48 = (hashCode47 + (str30 != null ? str30.hashCode() : 0)) * 31;
        String str31 = this.zipcode;
        int hashCode49 = (hashCode48 + (str31 != null ? str31.hashCode() : 0)) * 31;
        UserPhoto userPhoto = this.person_capture;
        int hashCode50 = (hashCode49 + (userPhoto != null ? userPhoto.hashCode() : 0)) * 31;
        UserPhoto userPhoto2 = this.photo_id_capture;
        if (userPhoto2 != null) {
            i = userPhoto2.hashCode();
        }
        int i4 = (hashCode50 + i) * 31;
        boolean z4 = this.verified;
        if (!z4) {
            z2 = z4;
        }
        return i4 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "SignUpResponse(braintree_user_id=" + this.braintree_user_id + ", calls_free=" + this.calls_free + ", can_text_message=" + this.can_text_message + ", can_video_message=" + this.can_video_message + ", city=" + this.city + ", code=" + this.code + ", code_created=" + this.code_created + ", created=" + this.created + ", device_os=" + this.device_os + ", dob=" + this.dob + ", email=" + this.email + ", first_name=" + this.first_name + ", full_name=" + this.full_name + ", gcm_token=" + this.gcm_token + ", id=" + this.id + ", is_admin=" + this.is_admin + ", is_fraud=" + this.is_fraud + ", is_fraud_set_by=" + this.is_fraud_set_by + ", is_mega_admin=" + this.is_mega_admin + ", is_operator=" + this.is_operator + ", is_visitor=" + this.is_visitor + ", last_name=" + this.last_name + ", message_notification_email=" + this.message_notification_email + ", message_notification_phone=" + this.message_notification_phone + ", middle_initial=" + this.middle_initial + ", notification_subscription_enabled=" + this.notification_subscription_enabled + ", operator_auto_delete=" + this.operator_auto_delete + ", operator_id=" + this.operator_id + ", operator_permissions=" + this.operator_permissions + ", operator_prison_id=" + this.operator_prison_id + ", operator_prison_id_list=" + this.operator_prison_id_list + ", operator_prison_name=" + this.operator_prison_name + ", password=" + this.password + ", password_changed_on=" + this.password_changed_on + ", person_capture_id=" + this.person_capture_id + ", phone=" + this.phone + ", photo_id_capture_id=" + this.photo_id_capture_id + ", php_session_id=" + this.php_session_id + ", pin=" + this.pin + ", should_record=" + this.should_record + ", street1=" + this.street1 + ", street2=" + this.street2 + ", super_admin_permissions=" + this.super_admin_permissions + ", timezone=" + this.timezone + ", timezone_php=" + this.timezone_php + ", us_state=" + this.us_state + ", us_state_abbreviation=" + this.us_state_abbreviation + ", us_state_id=" + this.us_state_id + ", username=" + this.username + ", visitor_id=" + this.visitor_id + ", zipcode=" + this.zipcode + ", person_capture=" + this.person_capture + ", photo_id_capture=" + this.photo_id_capture + ", verified=" + this.verified + ")";
    }

    public SignUpResponse(Object obj, String str, boolean z, boolean z2, String str2, Object obj2, Object obj3, String str3, Object obj4, String str4, String str5, String str6, String str7, Object obj5, String str8, String str9, String str10, Object obj6, String str11, String str12, String str13, String str14, Object obj7, Object obj8, Object obj9, String str15, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, Object obj16, String str25, Object obj17, Object obj18, String str26, String str27, String str28, String str29, String str30, String str31, UserPhoto userPhoto, UserPhoto userPhoto2, boolean z3) {
        String str32 = str;
        String str33 = str2;
        String str34 = str3;
        String str35 = str4;
        String str36 = str5;
        String str37 = str6;
        String str38 = str7;
        String str39 = str8;
        String str40 = str9;
        String str41 = str10;
        String str42 = str11;
        String str43 = str12;
        String str44 = str13;
        String str45 = str14;
        String str46 = str16;
        Intrinsics.checkParameterIsNotNull(str32, "calls_free");
        Intrinsics.checkParameterIsNotNull(str33, "city");
        Intrinsics.checkParameterIsNotNull(str34, "created");
        Intrinsics.checkParameterIsNotNull(str35, "dob");
        Intrinsics.checkParameterIsNotNull(str36, "email");
        Intrinsics.checkParameterIsNotNull(str37, Attributes.FIRST_NAME);
        Intrinsics.checkParameterIsNotNull(str38, Attributes.FULL_NAME);
        Intrinsics.checkParameterIsNotNull(str39, "id");
        Intrinsics.checkParameterIsNotNull(str40, "is_admin");
        Intrinsics.checkParameterIsNotNull(str41, "is_fraud");
        Intrinsics.checkParameterIsNotNull(str42, "is_mega_admin");
        Intrinsics.checkParameterIsNotNull(str43, "is_operator");
        Intrinsics.checkParameterIsNotNull(str44, "is_visitor");
        Intrinsics.checkParameterIsNotNull(str45, Attributes.LAST_NAME);
        Intrinsics.checkParameterIsNotNull(str15, "notification_subscription_enabled");
        Intrinsics.checkParameterIsNotNull(str16, "password");
        Intrinsics.checkParameterIsNotNull(str17, "password_changed_on");
        Intrinsics.checkParameterIsNotNull(str19, ShippingInfoWidget.PHONE_FIELD);
        Intrinsics.checkParameterIsNotNull(str21, "php_session_id");
        Intrinsics.checkParameterIsNotNull(str22, "pin");
        Intrinsics.checkParameterIsNotNull(str23, "should_record");
        Intrinsics.checkParameterIsNotNull(str24, PostalAddressParser.STREET_ADDRESS_KEY);
        Intrinsics.checkParameterIsNotNull(str25, "super_admin_permissions");
        Intrinsics.checkParameterIsNotNull(str26, "us_state");
        Intrinsics.checkParameterIsNotNull(str27, "us_state_abbreviation");
        Intrinsics.checkParameterIsNotNull(str28, "us_state_id");
        Intrinsics.checkParameterIsNotNull(str29, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str30, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str31, "zipcode");
        this.braintree_user_id = obj;
        this.calls_free = str32;
        this.can_text_message = z;
        this.can_video_message = z2;
        this.city = str33;
        this.code = obj2;
        this.code_created = obj3;
        this.created = str34;
        this.device_os = obj4;
        this.dob = str35;
        this.email = str36;
        this.first_name = str37;
        this.full_name = str38;
        this.gcm_token = obj5;
        this.id = str39;
        this.is_admin = str40;
        this.is_fraud = str41;
        this.is_fraud_set_by = obj6;
        this.is_mega_admin = str42;
        this.is_operator = str43;
        this.is_visitor = str44;
        this.last_name = str45;
        this.message_notification_email = obj7;
        this.message_notification_phone = obj8;
        this.middle_initial = obj9;
        this.notification_subscription_enabled = str15;
        this.operator_auto_delete = obj10;
        this.operator_id = obj11;
        this.operator_permissions = obj12;
        this.operator_prison_id = obj13;
        this.operator_prison_id_list = obj14;
        this.operator_prison_name = obj15;
        this.password = str16;
        this.password_changed_on = str17;
        this.person_capture_id = str18;
        this.phone = str19;
        this.photo_id_capture_id = str20;
        this.php_session_id = str21;
        this.pin = str22;
        this.should_record = str23;
        this.street1 = str24;
        this.street2 = obj16;
        this.super_admin_permissions = str25;
        this.timezone = obj17;
        this.timezone_php = obj18;
        this.us_state = str26;
        this.us_state_abbreviation = str27;
        this.us_state_id = str28;
        this.username = str29;
        this.visitor_id = str30;
        this.zipcode = str31;
        this.person_capture = userPhoto;
        this.photo_id_capture = userPhoto2;
        this.verified = z3;
    }

    public final Object getBraintree_user_id() {
        return this.braintree_user_id;
    }

    public final String getCalls_free() {
        return this.calls_free;
    }

    public final boolean getCan_text_message() {
        return this.can_text_message;
    }

    public final boolean getCan_video_message() {
        return this.can_video_message;
    }

    public final String getCity() {
        return this.city;
    }

    public final Object getCode() {
        return this.code;
    }

    public final Object getCode_created() {
        return this.code_created;
    }

    public final String getCreated() {
        return this.created;
    }

    public final Object getDevice_os() {
        return this.device_os;
    }

    public final String getDob() {
        return this.dob;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final String getFull_name() {
        return this.full_name;
    }

    public final Object getGcm_token() {
        return this.gcm_token;
    }

    public final String getId() {
        return this.id;
    }

    public final String is_admin() {
        return this.is_admin;
    }

    public final String is_fraud() {
        return this.is_fraud;
    }

    public final Object is_fraud_set_by() {
        return this.is_fraud_set_by;
    }

    public final String is_mega_admin() {
        return this.is_mega_admin;
    }

    public final String is_operator() {
        return this.is_operator;
    }

    public final String is_visitor() {
        return this.is_visitor;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final Object getMessage_notification_email() {
        return this.message_notification_email;
    }

    public final Object getMessage_notification_phone() {
        return this.message_notification_phone;
    }

    public final Object getMiddle_initial() {
        return this.middle_initial;
    }

    public final String getNotification_subscription_enabled() {
        return this.notification_subscription_enabled;
    }

    public final Object getOperator_auto_delete() {
        return this.operator_auto_delete;
    }

    public final Object getOperator_id() {
        return this.operator_id;
    }

    public final Object getOperator_permissions() {
        return this.operator_permissions;
    }

    public final Object getOperator_prison_id() {
        return this.operator_prison_id;
    }

    public final Object getOperator_prison_id_list() {
        return this.operator_prison_id_list;
    }

    public final Object getOperator_prison_name() {
        return this.operator_prison_name;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getPassword_changed_on() {
        return this.password_changed_on;
    }

    public final String getPerson_capture_id() {
        return this.person_capture_id;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getPhoto_id_capture_id() {
        return this.photo_id_capture_id;
    }

    public final String getPhp_session_id() {
        return this.php_session_id;
    }

    public final String getPin() {
        return this.pin;
    }

    public final String getShould_record() {
        return this.should_record;
    }

    public final String getStreet1() {
        return this.street1;
    }

    public final Object getStreet2() {
        return this.street2;
    }

    public final String getSuper_admin_permissions() {
        return this.super_admin_permissions;
    }

    public final Object getTimezone() {
        return this.timezone;
    }

    public final Object getTimezone_php() {
        return this.timezone_php;
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

    public final String getUsername() {
        return this.username;
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }

    public final String getZipcode() {
        return this.zipcode;
    }

    public final UserPhoto getPerson_capture() {
        return this.person_capture;
    }

    public final UserPhoto getPhoto_id_capture() {
        return this.photo_id_capture;
    }

    public final boolean getVerified() {
        return this.verified;
    }
}
