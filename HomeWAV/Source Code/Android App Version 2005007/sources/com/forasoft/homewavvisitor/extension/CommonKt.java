package com.forasoft.homewavvisitor.extension;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.R;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.anko.sdk15.listeners.__TextWatcher;
import org.json.JSONObject;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ø\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\bH\b¢\u0006\u0002\u0010\t\u001a*\u0010\n\u001a\u0004\u0018\u0001H\u0005\"\u0010\b\u0000\u0010\u0005\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00050\u000b2\u0006\u0010\f\u001a\u00020\rH\b¢\u0006\u0002\u0010\u000e\u001a\u0012\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010*\u00020\u0010\u001a:\u0010\u000f\u001a&\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005 \u0011*\u0012\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00120\u0012\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u0012\u001a:\u0010\u000f\u001a&\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005 \u0011*\u0012\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00130\u0013\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u0013\u001a:\u0010\u000f\u001a&\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005 \u0011*\u0012\u0012\f\u0012\n \u0011*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00140\u0014\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u0014\u001a\"\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00170\u0016*\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0016\u001a\"\u0010\u0018\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u0005\u0018\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u0002H\u0005H\b¢\u0006\u0002\u0010\u001c\u001a\n\u0010\u001d\u001a\u00020\u0019*\u00020\u001e\u001a\n\u0010\u001f\u001a\u00020\r*\u00020 \u001a\f\u0010!\u001a\u00020\u0019*\u0004\u0018\u00010\u0002\u001a\u0012\u0010\"\u001a\u00020\u0019*\u00020#2\u0006\u0010$\u001a\u00020\r\u001a/\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00050&\"\u0004\b\u0000\u0010\u0005*\u00020'2\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u0002H\u00050)¢\u0006\u0002\b*\u001a\u0012\u0010+\u001a\u00020\u0001*\u00020,2\u0006\u0010-\u001a\u00020\r\u001a\n\u0010.\u001a\u00020\r*\u00020\u0006\u001a-\u0010/\u001a\u00020\u0019*\u0002002!\u00101\u001a\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u00190)\u001a'\u00106\u001a\u0004\u0018\u00010'*\u0002072\u0006\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\r0;¢\u0006\u0002\u0010<\u001a\f\u0010=\u001a\u00020\u0019*\u0004\u0018\u00010\u0002\u001a\f\u0010>\u001a\u00020\u0019*\u0004\u0018\u00010\u0002\u001a\u0014\u0010?\u001a\u0004\u0018\u00010\r*\u00020@2\u0006\u0010A\u001a\u00020\r\u001a#\u0010B\u001a\u00020\u0019*\u00020C2\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\u00190)¢\u0006\u0002\b*\u001a\n\u0010F\u001a\u00020G*\u00020\r\u001a\n\u0010H\u001a\u00020I*\u00020\r\u001a\n\u0010J\u001a\u00020\u0017*\u00020\r\u001a\u0016\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u0014*\b\u0012\u0004\u0012\u00020L0M\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006N"}, d2 = {"isVisible", "", "Landroid/view/View;", "(Landroid/view/View;)Z", "requireNotNullResult", "T", "", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "safeValueOf", "", "type", "", "(Ljava/lang/String;)Ljava/lang/Enum;", "applyAsync", "Lio/reactivex/Completable;", "kotlin.jvm.PlatformType", "Lio/reactivex/Flowable;", "Lio/reactivex/Observable;", "Lio/reactivex/Single;", "asPartMap", "", "Lokhttp3/RequestBody;", "bind", "", "Ltoothpick/config/Module;", "instance", "(Ltoothpick/config/Module;Ljava/lang/Object;)V", "clearDecorations", "Landroidx/recyclerview/widget/RecyclerView;", "format", "Ljava/util/Date;", "hide", "loadRoundedImage", "Landroid/widget/ImageView;", "url", "map", "", "Landroid/database/Cursor;", "transform", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "matches", "Ljava/util/regex/Pattern;", "s", "objectScopeName", "onItemSelected", "Landroid/widget/Spinner;", "l", "", "Lkotlin/ParameterName;", "name", "position", "query", "Landroid/content/ContentResolver;", "uri", "Landroid/net/Uri;", "projection", "", "(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;)Landroid/database/Cursor;", "show", "startBlinkingAnimation", "stringOrNull", "Lorg/json/JSONObject;", "key", "textChangedListener", "Landroid/widget/AutoCompleteTextView;", "init", "Lorg/jetbrains/anko/sdk15/listeners/__TextWatcher;", "toEditable", "Landroid/text/Editable;", "toFloatExceptionless", "", "toRequestBody", "toSingle", "Landroidx/work/WorkInfo;", "Landroidx/lifecycle/LiveData;", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: common.kt */
public final class CommonKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WorkInfo.State.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[WorkInfo.State.SUCCEEDED.ordinal()] = 1;
            iArr[WorkInfo.State.FAILED.ordinal()] = 2;
        }
    }

    public static final Editable toEditable(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$toEditable");
        return new SpannableStringBuilder(str);
    }

    public static final void hide(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public static final void show(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isVisible");
        return view.getVisibility() == 0;
    }

    public static final void startBlinkingAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(600);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(-1);
        if (view != null) {
            view.startAnimation(alphaAnimation);
        }
    }

    public static final void onItemSelected(Spinner spinner, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(spinner, "$this$onItemSelected");
        Intrinsics.checkParameterIsNotNull(function1, "l");
        spinner.setOnItemSelectedListener(new CommonKt$onItemSelected$1(function1));
    }

    public static final void textChangedListener(AutoCompleteTextView autoCompleteTextView, Function1<? super __TextWatcher, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "$this$textChangedListener");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        __TextWatcher __textwatcher = new __TextWatcher();
        function1.invoke(__textwatcher);
        autoCompleteTextView.addTextChangedListener(__textwatcher);
    }

    public static final void clearDecorations(RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$clearDecorations");
        if (recyclerView.getItemDecorationCount() > 0) {
            for (int itemDecorationCount = recyclerView.getItemDecorationCount() - 1; itemDecorationCount >= 0; itemDecorationCount--) {
                recyclerView.removeItemDecorationAt(itemDecorationCount);
            }
        }
    }

    public static final <T> Single<T> applyAsync(Single<T> single) {
        Intrinsics.checkParameterIsNotNull(single, "$this$applyAsync");
        return single.compose(CommonKt$applyAsync$1.INSTANCE);
    }

    public static final <T> Flowable<T> applyAsync(Flowable<T> flowable) {
        Intrinsics.checkParameterIsNotNull(flowable, "$this$applyAsync");
        return flowable.compose(CommonKt$applyAsync$2.INSTANCE);
    }

    public static final <T> Observable<T> applyAsync(Observable<T> observable) {
        Intrinsics.checkParameterIsNotNull(observable, "$this$applyAsync");
        return observable.compose(CommonKt$applyAsync$3.INSTANCE);
    }

    public static final /* synthetic */ <T> void bind(Module module, T t) {
        Intrinsics.checkParameterIsNotNull(module, "$this$bind");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        module.bind(Object.class).toInstance(t);
    }

    public static final Completable applyAsync(Completable completable) {
        Intrinsics.checkParameterIsNotNull(completable, "$this$applyAsync");
        return completable.compose(CommonKt$applyAsync$4.INSTANCE);
    }

    public static final boolean matches(Pattern pattern, String str) {
        Intrinsics.checkParameterIsNotNull(pattern, "$this$matches");
        Intrinsics.checkParameterIsNotNull(str, "s");
        return pattern.matcher(str).matches();
    }

    public static final String format(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$format");
        String format = new SimpleDateFormat("MM/dd/yyyy").format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "SimpleDateFormat(\"MM/dd/yyyy\").format(this)");
        return format;
    }

    public static final float toFloatExceptionless(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$toFloatExceptionless");
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static final <T> T requireNotNullResult(Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "block");
        T invoke = function0.invoke();
        if (invoke != null) {
            return invoke;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public static final void loadRoundedImage(ImageView imageView, String str) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$loadRoundedImage");
        Intrinsics.checkParameterIsNotNull(str, "url");
        Glide.with(imageView.getContext()).load(str).asBitmap().into(new CommonKt$loadRoundedImage$1(imageView, (CircleImageView) imageView.findViewById(R.id.iv_avatar)));
    }

    public static final String stringOrNull(JSONObject jSONObject, String str) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "$this$stringOrNull");
        Intrinsics.checkParameterIsNotNull(str, "key");
        if (jSONObject.has(str)) {
            return jSONObject.getString(str);
        }
        return null;
    }

    public static final RequestBody toRequestBody(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$toRequestBody");
        return RequestBody.Companion.create(str, MediaType.Companion.parse(ErrorAttachmentLog.CONTENT_TYPE_TEXT_PLAIN));
    }

    public static final Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "$this$query");
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Intrinsics.checkParameterIsNotNull(strArr, "projection");
        return contentResolver.query(uri, strArr, (String) null, (String[]) null, (String) null);
    }

    public static final Single<WorkInfo> toSingle(LiveData<WorkInfo> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "$this$toSingle");
        Single<WorkInfo> create = Single.create(new CommonKt$toSingle$1(liveData));
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { emitter …)\n            }\n        }");
        return create;
    }

    public static final <T> List<T> map(Cursor cursor, Function1<? super Cursor, ? extends T> function1) {
        Intrinsics.checkParameterIsNotNull(cursor, "$this$map");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        cursor.moveToFirst();
        List<T> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            arrayList.add(function1.invoke(cursor));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public static final String objectScopeName(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$objectScopeName");
        return obj.getClass().getSimpleName() + '_' + obj.hashCode();
    }

    public static final /* synthetic */ <T extends Enum<T>> T safeValueOf(String str) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        try {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return Enum.valueOf(Enum.class, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static final Map<String, RequestBody> asPartMap(Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(map, "$this$asPartMap");
        Collection arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(TuplesKt.to((String) next.getKey(), RequestBody.Companion.create$default(RequestBody.Companion, (String) next.getValue(), (MediaType) null, 1, (Object) null)));
        }
        return MapsKt.toMap((List) arrayList);
    }
}
