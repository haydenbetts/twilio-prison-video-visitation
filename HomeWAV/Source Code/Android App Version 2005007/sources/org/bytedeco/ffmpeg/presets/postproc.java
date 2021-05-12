package org.bytedeco.ffmpeg.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.postproc", inherit = {avutil.class}, target = "org.bytedeco.ffmpeg.postproc", value = {@Platform(cinclude = {"<libpostproc/postprocess.h>"}, link = {"postproc@.55"}), @Platform(preload = {"postproc-55"}, value = {"windows"})})
public class postproc implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("QP_STORE_T").cppTypes(new String[0]).valueTypes("byte").pointerTypes("BytePointer")).put(new Info("LIBPOSTPROC_VERSION_INT < (52<<16)").define(false));
    }
}
