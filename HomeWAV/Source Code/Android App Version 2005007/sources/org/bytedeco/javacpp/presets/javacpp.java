package org.bytedeco.javacpp.presets;

import java.util.List;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(global = "org.bytedeco.javacpp.presets.javacpp", value = {@Platform(compiler = {"cpp11"}), @Platform(preload = {"api-ms-win-crt-locale-l1-1-0", "api-ms-win-crt-string-l1-1-0", "api-ms-win-crt-stdio-l1-1-0", "api-ms-win-crt-math-l1-1-0", "api-ms-win-crt-heap-l1-1-0", "api-ms-win-crt-runtime-l1-1-0", "api-ms-win-crt-convert-l1-1-0", "api-ms-win-crt-environment-l1-1-0", "api-ms-win-crt-time-l1-1-0", "api-ms-win-crt-filesystem-l1-1-0", "api-ms-win-crt-utility-l1-1-0", "api-ms-win-crt-multibyte-l1-1-0", "api-ms-win-core-string-l1-1-0", "api-ms-win-core-errorhandling-l1-1-0", "api-ms-win-core-timezone-l1-1-0", "api-ms-win-core-file-l1-1-0", "api-ms-win-core-namedpipe-l1-1-0", "api-ms-win-core-handle-l1-1-0", "api-ms-win-core-file-l2-1-0", "api-ms-win-core-heap-l1-1-0", "api-ms-win-core-libraryloader-l1-1-0", "api-ms-win-core-synch-l1-1-0", "api-ms-win-core-processthreads-l1-1-0", "api-ms-win-core-processenvironment-l1-1-0", "api-ms-win-core-datetime-l1-1-0", "api-ms-win-core-localization-l1-2-0", "api-ms-win-core-sysinfo-l1-1-0", "api-ms-win-core-synch-l1-2-0", "api-ms-win-core-console-l1-1-0", "api-ms-win-core-debug-l1-1-0", "api-ms-win-core-rtlsupport-l1-1-0", "api-ms-win-core-processthreads-l1-1-1", "api-ms-win-core-file-l1-2-0", "api-ms-win-core-profile-l1-1-0", "api-ms-win-core-memory-l1-1-0", "api-ms-win-core-util-l1-1-0", "api-ms-win-core-interlocked-l1-1-0", "ucrtbase", "vcruntime140", "vcruntime140_1", "msvcp140", "msvcp140_1", "concrt140", "vcomp140"}, value = {"windows"}), @Platform(preloadpath = {"C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.CRT/", "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.OpenMP/", "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x86/"}, value = {"windows-x86"}), @Platform(preloadpath = {"C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.CRT/", "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.OpenMP/", "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x64/"}, value = {"windows-x86_64"})})
public class javacpp implements LoadEnabled {
    public void init(ClassProperties classProperties) {
        String property = classProperties.getProperty("platform");
        List<String> list = classProperties.get("platform.preloadpath");
        String str = System.getenv("VCToolsRedistDir");
        if (str != null && str.length() > 0) {
            property.hashCode();
            if (property.equals("windows-x86_64")) {
                list.add(0, str + "\\x64\\Microsoft.VC142.CRT");
                list.add(1, str + "\\x64\\Microsoft.VC142.OpenMP");
                list.add(2, str + "\\x64\\Microsoft.VC141.CRT");
                list.add(3, str + "\\x64\\Microsoft.VC141.OpenMP");
            } else if (property.equals("windows-x86")) {
                list.add(0, str + "\\x86\\Microsoft.VC142.CRT");
                list.add(1, str + "\\x86\\Microsoft.VC142.OpenMP");
                list.add(2, str + "\\x86\\Microsoft.VC141.CRT");
                list.add(3, str + "\\x86\\Microsoft.VC141.OpenMP");
            }
        }
    }
}
