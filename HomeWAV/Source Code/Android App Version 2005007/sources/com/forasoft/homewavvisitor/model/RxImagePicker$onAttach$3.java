package com.forasoft.homewavvisitor.model;

import io.reactivex.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* compiled from: RxImagePicker.kt */
final /* synthetic */ class RxImagePicker$onAttach$3 extends MutablePropertyReference0 {
    RxImagePicker$onAttach$3(RxImagePicker rxImagePicker) {
        super(rxImagePicker);
    }

    public String getName() {
        return "publishSubjectMultipleImages";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(RxImagePicker.class);
    }

    public String getSignature() {
        return "getPublishSubjectMultipleImages()Lio/reactivex/subjects/PublishSubject;";
    }

    public Object get() {
        return RxImagePicker.access$getPublishSubjectMultipleImages$p((RxImagePicker) this.receiver);
    }

    public void set(Object obj) {
        ((RxImagePicker) this.receiver).publishSubjectMultipleImages = (PublishSubject) obj;
    }
}
