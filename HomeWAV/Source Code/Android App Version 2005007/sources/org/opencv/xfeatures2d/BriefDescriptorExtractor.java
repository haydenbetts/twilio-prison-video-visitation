package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class BriefDescriptorExtractor extends Feature2D {
    private static native long create_0(int i, boolean z);

    private static native long create_1(int i);

    private static native long create_2();

    private static native void delete(long j);

    protected BriefDescriptorExtractor(long j) {
        super(j);
    }

    public static BriefDescriptorExtractor __fromPtr__(long j) {
        return new BriefDescriptorExtractor(j);
    }

    public static BriefDescriptorExtractor create(int i, boolean z) {
        return __fromPtr__(create_0(i, z));
    }

    public static BriefDescriptorExtractor create(int i) {
        return __fromPtr__(create_1(i));
    }

    public static BriefDescriptorExtractor create() {
        return __fromPtr__(create_2());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
