package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class LayerFactory extends Pointer {
    @opencv_core.Ptr
    public static native Layer createLayerInstance(@opencv_core.Str String str, @ByRef LayerParams layerParams);

    @opencv_core.Ptr
    public static native Layer createLayerInstance(@opencv_core.Str BytePointer bytePointer, @ByRef LayerParams layerParams);

    public static native void registerLayer(@opencv_core.Str String str, Constructor constructor);

    public static native void registerLayer(@opencv_core.Str BytePointer bytePointer, Constructor constructor);

    public static native void unregisterLayer(@opencv_core.Str String str);

    public static native void unregisterLayer(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public LayerFactory(Pointer pointer) {
        super(pointer);
    }

    @Convention(extern = "C++", value = "")
    public static class Constructor extends FunctionPointer {
        private native void allocate();

        @opencv_core.Ptr
        public native Layer call(@ByRef LayerParams layerParams);

        static {
            Loader.load();
        }

        public Constructor(Pointer pointer) {
            super(pointer);
        }

        protected Constructor() {
            allocate();
        }
    }
}
