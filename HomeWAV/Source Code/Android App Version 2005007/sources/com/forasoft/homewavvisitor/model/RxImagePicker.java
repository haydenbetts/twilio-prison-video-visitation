package com.forasoft.homewavvisitor.model;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 82\u00020\u0001:\u000289B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\u0012\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J\u0012\u0010!\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u0013H\u0002J\"\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010&\u001a\u00020\u00192\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010)\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u000eH\u0002J\u0016\u0010*\u001a\u00020\u00192\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0002J+\u0010,\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\n0.2\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0002\u00101J\b\u00102\u001a\u00020\u0019H\u0002J\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e042\u0006\u00105\u001a\u00020\fJ\u001e\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e042\u0006\u00105\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0014\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001004H\u0007J\b\u00107\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00100\u0006X.¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/forasoft/homewavvisitor/model/RxImagePicker;", "Landroidx/fragment/app/Fragment;", "()V", "allowMultipleImages", "", "attachedSubject", "Lio/reactivex/subjects/PublishSubject;", "canceledSubject", "", "chooserTitle", "", "imageSource", "Lcom/forasoft/homewavvisitor/model/RxImagePicker$Sources;", "publishSubject", "Landroid/net/Uri;", "publishSubjectMultipleImages", "", "checkPermission", "createChooserIntent", "Landroid/content/Intent;", "createImageUri", "createPickFromDocumentsIntent", "createPickFromGalleryIntent", "createTakePhotoIntent", "grantReadPermission", "", "context", "Landroid/content/Context;", "intent", "uri", "handleGalleryResult", "data", "initSubjects", "isPhoto", "onActivityResult", "requestCode", "resultCode", "onAttach", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onImagePicked", "onImagesPicked", "uris", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "pickImage", "requestImage", "Lio/reactivex/Observable;", "source", "requestMultipleImages", "requestPickImage", "Companion", "Sources", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RxImagePicker.kt */
public final class RxImagePicker extends Fragment {
    private static final int CHOOSER = 102;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int SELECT_PHOTO = 100;
    /* access modifiers changed from: private */
    public static final String TAG;
    private static final int TAKE_PHOTO = 101;
    private static Uri cameraPictureUrl;
    private HashMap _$_findViewCache;
    private boolean allowMultipleImages;
    /* access modifiers changed from: private */
    public PublishSubject<Boolean> attachedSubject;
    /* access modifiers changed from: private */
    public PublishSubject<Integer> canceledSubject;
    private String chooserTitle;
    private Sources imageSource;
    /* access modifiers changed from: private */
    public PublishSubject<Uri> publishSubject;
    /* access modifiers changed from: private */
    public PublishSubject<List<Uri>> publishSubjectMultipleImages;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/model/RxImagePicker$Sources;", "", "(Ljava/lang/String;I)V", "CAMERA", "GALLERY", "DOCUMENTS", "CHOOSER", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RxImagePicker.kt */
    public enum Sources {
        CAMERA,
        GALLERY,
        DOCUMENTS,
        CHOOSER
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Sources.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Sources.CAMERA.ordinal()] = 1;
            iArr[Sources.GALLERY.ordinal()] = 2;
            iArr[Sources.DOCUMENTS.ordinal()] = 3;
            iArr[Sources.CHOOSER.ordinal()] = 4;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ PublishSubject access$getAttachedSubject$p(RxImagePicker rxImagePicker) {
        PublishSubject<Boolean> publishSubject2 = rxImagePicker.attachedSubject;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("attachedSubject");
        }
        return publishSubject2;
    }

    public static final /* synthetic */ PublishSubject access$getCanceledSubject$p(RxImagePicker rxImagePicker) {
        PublishSubject<Integer> publishSubject2 = rxImagePicker.canceledSubject;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canceledSubject");
        }
        return publishSubject2;
    }

    public static final /* synthetic */ PublishSubject access$getPublishSubject$p(RxImagePicker rxImagePicker) {
        PublishSubject<Uri> publishSubject2 = rxImagePicker.publishSubject;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubject");
        }
        return publishSubject2;
    }

    public static final /* synthetic */ PublishSubject access$getPublishSubjectMultipleImages$p(RxImagePicker rxImagePicker) {
        PublishSubject<List<Uri>> publishSubject2 = rxImagePicker.publishSubjectMultipleImages;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubjectMultipleImages");
        }
        return publishSubject2;
    }

    public final Observable<Uri> requestImage(Sources sources, String str) {
        Intrinsics.checkParameterIsNotNull(sources, "source");
        this.chooserTitle = str;
        return requestImage(sources);
    }

    public final Observable<Uri> requestImage(Sources sources) {
        Intrinsics.checkParameterIsNotNull(sources, "source");
        initSubjects();
        this.allowMultipleImages = false;
        this.imageSource = sources;
        requestPickImage();
        PublishSubject<Uri> publishSubject2 = this.publishSubject;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubject");
        }
        PublishSubject<Integer> publishSubject3 = this.canceledSubject;
        if (publishSubject3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canceledSubject");
        }
        Observable<Uri> takeUntil = publishSubject2.takeUntil((ObservableSource<U>) publishSubject3);
        Intrinsics.checkExpressionValueIsNotNull(takeUntil, "publishSubject.takeUntil(canceledSubject)");
        return takeUntil;
    }

    public final Observable<List<Uri>> requestMultipleImages() {
        initSubjects();
        this.imageSource = Sources.GALLERY;
        this.allowMultipleImages = true;
        requestPickImage();
        PublishSubject<List<Uri>> publishSubject2 = this.publishSubjectMultipleImages;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubjectMultipleImages");
        }
        PublishSubject<Integer> publishSubject3 = this.canceledSubject;
        if (publishSubject3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canceledSubject");
        }
        Observable<List<Uri>> takeUntil = publishSubject2.takeUntil((ObservableSource<U>) publishSubject3);
        Intrinsics.checkExpressionValueIsNotNull(takeUntil, "publishSubjectMultipleIm…akeUntil(canceledSubject)");
        return takeUntil;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    private final void initSubjects() {
        PublishSubject<Uri> create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create()");
        this.publishSubject = create;
        PublishSubject<Boolean> create2 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "PublishSubject.create()");
        this.attachedSubject = create2;
        PublishSubject<Integer> create3 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "PublishSubject.create()");
        this.canceledSubject = create3;
        PublishSubject<List<Uri>> create4 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "PublishSubject.create()");
        this.publishSubjectMultipleImages = create4;
    }

    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        RxImagePicker rxImagePicker = this;
        boolean z = false;
        boolean z2 = (!(rxImagePicker.attachedSubject != null)) | (!(rxImagePicker.publishSubject != null)) | (!(rxImagePicker.publishSubjectMultipleImages != null));
        if (rxImagePicker.canceledSubject != null) {
            z = true;
        }
        if ((!z) || z2) {
            initSubjects();
        }
        PublishSubject<Boolean> publishSubject2 = this.attachedSubject;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("attachedSubject");
        }
        publishSubject2.onNext(true);
        PublishSubject<Boolean> publishSubject3 = this.attachedSubject;
        if (publishSubject3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("attachedSubject");
        }
        publishSubject3.onComplete();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if ((!(iArr.length == 0)) && iArr[0] == 0) {
            pickImage();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 100:
                    handleGalleryResult(intent);
                    return;
                case 101:
                    onImagePicked(cameraPictureUrl);
                    return;
                case 102:
                    if (isPhoto(intent)) {
                        onImagePicked(cameraPictureUrl);
                        return;
                    } else {
                        handleGalleryResult(intent);
                        return;
                    }
                default:
                    return;
            }
        } else {
            PublishSubject<Integer> publishSubject2 = this.canceledSubject;
            if (publishSubject2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canceledSubject");
            }
            publishSubject2.onNext(Integer.valueOf(i));
        }
    }

    private final boolean isPhoto(Intent intent) {
        return intent == null || (intent.getData() == null && intent.getClipData() == null);
    }

    private final void handleGalleryResult(Intent intent) {
        if (this.allowMultipleImages) {
            ArrayList arrayList = new ArrayList();
            ClipData clipData = intent != null ? intent.getClipData() : null;
            if (clipData != null) {
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    ClipData.Item itemAt = clipData.getItemAt(i);
                    Intrinsics.checkExpressionValueIsNotNull(itemAt, "clipData.getItemAt(i)");
                    arrayList.add(itemAt.getUri());
                }
            } else {
                if (intent == null) {
                    Intrinsics.throwNpe();
                }
                Uri data = intent.getData();
                if (data == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(data);
            }
            onImagesPicked(arrayList);
            return;
        }
        if (intent == null) {
            Intrinsics.throwNpe();
        }
        onImagePicked(intent.getData());
    }

    private final void requestPickImage() {
        if (!isAdded()) {
            PublishSubject<Boolean> publishSubject2 = this.attachedSubject;
            if (publishSubject2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("attachedSubject");
            }
            publishSubject2.subscribe((Consumer<? super Boolean>) new RxImagePicker$requestPickImage$1(this));
            return;
        }
        pickImage();
    }

    /* access modifiers changed from: private */
    public final void pickImage() {
        if (checkPermission()) {
            int i = 0;
            Intent intent = null;
            Sources sources = this.imageSource;
            if (sources != null) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[sources.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        intent = createPickFromGalleryIntent();
                    } else if (i2 == 3) {
                        intent = createPickFromDocumentsIntent();
                    } else if (i2 == 4) {
                        intent = createChooserIntent(this.chooserTitle);
                        i = 102;
                    }
                    i = 100;
                } else {
                    intent = createTakePhotoIntent();
                    i = 101;
                }
            }
            startActivityForResult(intent, i);
        }
    }

    private final Intent createTakePhotoIntent() {
        Uri createImageUri = createImageUri();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", createImageUri);
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        grantReadPermission(requireContext, intent, createImageUri);
        return intent;
    }

    private final Intent createChooserIntent(String str) {
        Uri createImageUri = createImageUri();
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        for (ResolveInfo next : requireContext.getPackageManager().queryIntentActivities(intent, 0)) {
            String str2 = next.activityInfo.packageName;
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
            intent2.setPackage(str2);
            intent2.putExtra("output", createImageUri);
            Context requireContext2 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
            grantReadPermission(requireContext2, intent2, createImageUri);
            arrayList.add(intent2);
        }
        Intent createChooser = Intent.createChooser(createPickFromDocumentsIntent(), str);
        Object[] array = arrayList.toArray(new Intent[0]);
        if (array != null) {
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) array);
            Intrinsics.checkExpressionValueIsNotNull(createChooser, "chooserIntent");
            return createChooser;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final Intent createPickFromGalleryIntent() {
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (Build.VERSION.SDK_INT >= 19) {
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.allowMultipleImages);
        }
        return intent;
    }

    private final Intent createPickFromDocumentsIntent() {
        Intent intent;
        if (Build.VERSION.SDK_INT >= 19) {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.allowMultipleImages);
            Intrinsics.checkExpressionValueIsNotNull(intent.addFlags(64), "pictureChooseIntent.addF…RSISTABLE_URI_PERMISSION)");
        } else {
            intent = new Intent("android.intent.action.GET_CONTENT");
        }
        intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
        intent.addFlags(1);
        intent.setType("image/*");
        return intent;
    }

    private final boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
        return false;
    }

    private final Uri createImageUri() {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File externalFilesDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File createTempFile = File.createTempFile("IMG_" + format, ".jpg", externalFilesDir);
        cameraPictureUrl = Uri.fromFile(createTempFile);
        Context requireContext = requireContext();
        StringBuilder sb = new StringBuilder();
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        sb.append(requireContext2.getPackageName());
        sb.append(".provider");
        Uri uriForFile = FileProvider.getUriForFile(requireContext, sb.toString(), createTempFile);
        Intrinsics.checkExpressionValueIsNotNull(uriForFile, "FileProvider.getUriForFi…           file\n        )");
        return uriForFile;
    }

    private final void grantReadPermission(Context context, Intent intent, Uri uri) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
            context.grantUriPermission(resolveInfo.activityInfo.packageName, uri, 3);
        }
    }

    private final void onImagesPicked(List<? extends Uri> list) {
        PublishSubject<List<Uri>> publishSubject2 = this.publishSubjectMultipleImages;
        if (publishSubject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubjectMultipleImages");
        }
        publishSubject2.onNext(list);
        PublishSubject<List<Uri>> publishSubject3 = this.publishSubjectMultipleImages;
        if (publishSubject3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubjectMultipleImages");
        }
        publishSubject3.onComplete();
    }

    private final void onImagePicked(Uri uri) {
        if (uri != null) {
            PublishSubject<Uri> publishSubject2 = this.publishSubject;
            if (publishSubject2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("publishSubject");
            }
            publishSubject2.onNext(uri);
        }
        PublishSubject<Uri> publishSubject3 = this.publishSubject;
        if (publishSubject3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publishSubject");
        }
        publishSubject3.onComplete();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/RxImagePicker$Companion;", "", "()V", "CHOOSER", "", "SELECT_PHOTO", "TAG", "", "TAKE_PHOTO", "cameraPictureUrl", "Landroid/net/Uri;", "with", "Lcom/forasoft/homewavvisitor/model/RxImagePicker;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RxImagePicker.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RxImagePicker with(FragmentManager fragmentManager) {
            Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
            RxImagePicker rxImagePicker = (RxImagePicker) fragmentManager.findFragmentByTag(RxImagePicker.TAG);
            if (rxImagePicker != null) {
                return rxImagePicker;
            }
            RxImagePicker rxImagePicker2 = new RxImagePicker();
            fragmentManager.beginTransaction().add((Fragment) rxImagePicker2, RxImagePicker.TAG).commit();
            return rxImagePicker2;
        }
    }

    static {
        String simpleName = RxImagePicker.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "RxImagePicker::class.java.simpleName");
        TAG = simpleName;
    }
}
