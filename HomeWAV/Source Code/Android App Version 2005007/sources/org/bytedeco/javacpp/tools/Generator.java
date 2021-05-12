package org.bytedeco.javacpp.tools;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.CLongPointer;
import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.Adapter;
import org.bytedeco.javacpp.annotation.Allocator;
import org.bytedeco.javacpp.annotation.ArrayAllocator;
import org.bytedeco.javacpp.annotation.ByPtr;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByPtrRef;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.CriticalRegion;
import org.bytedeco.javacpp.annotation.Function;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.javacpp.annotation.ValueSetter;
import org.bytedeco.javacpp.annotation.Virtual;
import org.slf4j.Marker;

public class Generator {
    static final String JNI_VERSION = "JNI_VERSION_1_6";
    static final List<Class> baseClasses = Arrays.asList(new Class[]{Loader.class, Pointer.class, BytePointer.class, ShortPointer.class, IntPointer.class, LongPointer.class, FloatPointer.class, DoublePointer.class, CharPointer.class, BooleanPointer.class, PointerPointer.class, BoolPointer.class, CLongPointer.class, SizeTPointer.class});
    boolean accessesEnums;
    Map<Method, MethodInformation> annotationCache;
    IndexedSet<Class> arrayDeallocators;
    Map<String, String> callbacks;
    IndexedSet<Class> deallocators;
    final String encoding;
    IndexedSet<Class> functions;
    IndexedSet<Class> jclasses;
    PrintWriter jniConfigOut;
    final Logger logger;
    boolean mayThrowExceptions;
    Map<Class, Set<String>> members;
    PrintWriter out;
    PrintWriter out2;
    boolean passesStrings;
    final Properties properties;
    PrintWriter reflectConfigOut;
    boolean usesAdapters;
    Map<Class, Set<String>> virtualFunctions;
    Map<Class, Set<String>> virtualMembers;

    enum BooleanEnum {
        BOOLEAN;
        
        boolean value;
    }

    enum ByteEnum {
        BYTE;
        
        byte value;
    }

    enum IntEnum {
        INT;
        
        int value;
    }

    enum LongEnum {
        LONG;
        
        long value;
    }

    enum ShortEnum {
        SHORT;
        
        short value;
    }

    public Generator(Logger logger2, Properties properties2) {
        this(logger2, properties2, (String) null);
    }

    public Generator(Logger logger2, Properties properties2, String str) {
        this.logger = logger2;
        this.properties = properties2;
        this.encoding = str;
    }

    public boolean generate(String str, String str2, String str3, String str4, String str5, String str6, String str7, Class<?>... clsArr) throws IOException {
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        try {
            this.out = new PrintWriter(new Writer() {
                public void close() {
                }

                public void flush() {
                }

                public void write(char[] cArr, int i, int i2) {
                }
            });
            this.reflectConfigOut = null;
            this.jniConfigOut = null;
            this.out2 = null;
            this.callbacks = new LinkedHashMap();
            this.functions = new IndexedSet<>();
            this.deallocators = new IndexedSet<>();
            this.arrayDeallocators = new IndexedSet<>();
            this.jclasses = new IndexedSet<>();
            this.members = new LinkedHashMap();
            this.virtualFunctions = new LinkedHashMap();
            this.virtualMembers = new LinkedHashMap();
            this.annotationCache = new LinkedHashMap();
            this.mayThrowExceptions = false;
            this.usesAdapters = false;
            this.passesStrings = false;
            if (str6 == null || str6.isEmpty()) {
                for (Class index : baseClasses) {
                    this.jclasses.index(index);
                }
            }
            if (classes(true, true, true, true, str5, str6, str7, clsArr)) {
                String str11 = str;
                File file = new File(str);
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                this.out = this.encoding != null ? new PrintWriter(file, this.encoding) : new PrintWriter(file);
                if (str10 != null) {
                    Logger logger2 = this.logger;
                    logger2.info("Generating " + str10);
                    File file2 = new File(str10);
                    File parentFile2 = file2.getParentFile();
                    if (parentFile2 != null) {
                        parentFile2.mkdirs();
                    }
                    this.out2 = this.encoding != null ? new PrintWriter(file2, this.encoding) : new PrintWriter(file2);
                }
                if (str8 != null) {
                    Logger logger3 = this.logger;
                    logger3.info("Generating " + str8);
                    File file3 = new File(str8);
                    File parentFile3 = file3.getParentFile();
                    if (parentFile3 != null) {
                        parentFile3.mkdirs();
                    }
                    this.jniConfigOut = this.encoding != null ? new PrintWriter(file3, this.encoding) : new PrintWriter(file3);
                }
                if (str9 != null) {
                    Logger logger4 = this.logger;
                    logger4.info("Generating " + str9);
                    File file4 = new File(str9);
                    File parentFile4 = file4.getParentFile();
                    if (parentFile4 != null) {
                        parentFile4.mkdirs();
                    }
                    this.reflectConfigOut = this.encoding != null ? new PrintWriter(file4, this.encoding) : new PrintWriter(file4);
                }
                return classes(this.mayThrowExceptions, this.usesAdapters, this.passesStrings, this.accessesEnums, str5, str6, str7, clsArr);
            }
            PrintWriter printWriter = this.out;
            if (printWriter != null) {
                printWriter.close();
            }
            PrintWriter printWriter2 = this.out2;
            if (printWriter2 != null) {
                printWriter2.close();
            }
            PrintWriter printWriter3 = this.jniConfigOut;
            if (printWriter3 != null) {
                printWriter3.close();
            }
            PrintWriter printWriter4 = this.reflectConfigOut;
            if (printWriter4 != null) {
                printWriter4.close();
            }
            return false;
        } finally {
            PrintWriter printWriter5 = this.out;
            if (printWriter5 != null) {
                printWriter5.close();
            }
            PrintWriter printWriter6 = this.out2;
            if (printWriter6 != null) {
                printWriter6.close();
            }
            PrintWriter printWriter7 = this.jniConfigOut;
            if (printWriter7 != null) {
                printWriter7.close();
            }
            PrintWriter printWriter8 = this.reflectConfigOut;
            if (printWriter8 != null) {
                printWriter8.close();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: org.bytedeco.javacpp.tools.Generator} */
    /* JADX WARNING: type inference failed for: r2v178, types: [java.lang.Object[]] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean classes(boolean r26, boolean r27, boolean r28, boolean r29, java.lang.String r30, java.lang.String r31, java.lang.String r32, java.lang.Class<?>... r33) {
        /*
            r25 = this;
            r1 = r25
            r0 = r31
            r2 = r33
            java.lang.Class<org.bytedeco.javacpp.tools.Generator> r3 = org.bytedeco.javacpp.tools.Generator.class
            java.lang.Package r3 = r3.getPackage()
            java.lang.String r3 = r3.getImplementationVersion()
            if (r3 != 0) goto L_0x0014
            java.lang.String r3 = "unknown"
        L_0x0014:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "// Generated by JavaCPP version "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = ": DO NOT EDIT THIS FILE"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.io.PrintWriter r4 = r1.out
            r4.println(r3)
            java.io.PrintWriter r4 = r1.out
            r4.println()
            java.io.PrintWriter r4 = r1.out2
            if (r4 == 0) goto L_0x0040
            r4.println(r3)
            java.io.PrintWriter r3 = r1.out2
            r3.println()
        L_0x0040:
            java.util.Properties r3 = r1.properties
            r4 = 1
            org.bytedeco.javacpp.ClassProperties r3 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r2, (java.util.Properties) r3, (boolean) r4)
            java.lang.String r5 = "platform.pragma"
            java.util.List r5 = r3.get(r5)
            java.util.Iterator r5 = r5.iterator()
        L_0x0051:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0074
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "#pragma "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.println(r6)
            goto L_0x0051
        L_0x0074:
            java.lang.String r5 = "platform.define"
            java.util.List r5 = r3.get(r5)
            java.util.Iterator r5 = r5.iterator()
        L_0x007e:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00a1
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "#define "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.println(r6)
            goto L_0x007e
        L_0x00a1:
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "#ifdef _WIN32"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define _JAVASOFT_JNI_MD_H_"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNIEXPORT __declspec(dllexport)"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNIIMPORT __declspec(dllimport)"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNICALL __stdcall"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef int jint;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef long long jlong;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef signed char jbyte;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "#elif defined(__GNUC__) && !defined(__ANDROID__)"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define _JAVASOFT_JNI_MD_H_"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNIEXPORT __attribute__((visibility(\"default\")))"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNIIMPORT"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    #define JNICALL"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef int jint;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef long long jlong;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "    typedef signed char jbyte;"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r6 = "#endif"
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r7 = "#include <jni.h>"
            r5.println(r7)
            java.io.PrintWriter r5 = r1.out2
            if (r5 == 0) goto L_0x0146
            java.lang.String r7 = "#include <jni.h>"
            r5.println(r7)
        L_0x0146:
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r7 = "#ifdef __ANDROID__"
            r5.println(r7)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r8 = "    #include <android/log.h>"
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r8 = "#elif defined(__APPLE__) && defined(__OBJC__)"
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r8 = "    #include <TargetConditionals.h>"
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r8 = "    #include <Foundation/Foundation.h>"
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r8 = "#ifdef __linux__"
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <malloc.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <sys/types.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <sys/stat.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <sys/sysinfo.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <fcntl.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <unistd.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <dlfcn.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <link.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "    #include <pthread.h>"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r9 = "#elif defined(__APPLE__)"
            r5.println(r9)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <sys/types.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <sys/sysctl.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <mach/mach_init.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <mach/mach_host.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <mach/task.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <unistd.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <dlfcn.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <mach-o/dyld.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <pthread.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "#elif defined(_WIN32) && !defined(NO_WINDOWS_H)"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #define NOMINMAX"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <windows.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "    #include <psapi.h>"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r10 = "#elif defined(_WIN32)"
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "    extern \"C\" unsigned long __stdcall GetCurrentThreadId();"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#if defined(__ANDROID__) || TARGET_OS_IPHONE"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "    #define NewWeakGlobalRef(obj) NewGlobalRef(obj)"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "    #define DeleteWeakGlobalRef(obj) DeleteGlobalRef(obj)"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            r5.println()
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <limits.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <stddef.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#ifndef _WIN32"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "    #include <stdint.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            r5.println(r6)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <stdio.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <stdlib.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <string.h>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <exception>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <memory>"
            r5.println(r11)
            java.io.PrintWriter r5 = r1.out
            java.lang.String r11 = "#include <new>"
            r5.println(r11)
            java.lang.String r5 = "    }"
            if (r0 == 0) goto L_0x02ac
            boolean r11 = r31.isEmpty()
            if (r11 == 0) goto L_0x032f
        L_0x02ac:
            java.io.PrintWriter r11 = r1.out
            r11.println()
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#if defined(NATIVE_ALLOCATOR) && defined(NATIVE_DEALLOCATOR)"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void* operator new(std::size_t size, const std::nothrow_t&) throw() {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        return NATIVE_ALLOCATOR(size);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void* operator new[](std::size_t size, const std::nothrow_t&) throw() {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        return NATIVE_ALLOCATOR(size);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void* operator new(std::size_t size) throw(std::bad_alloc) {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        return NATIVE_ALLOCATOR(size);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void* operator new[](std::size_t size) throw(std::bad_alloc) {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        return NATIVE_ALLOCATOR(size);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void operator delete(void* ptr) throw() {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        NATIVE_DEALLOCATOR(ptr);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    void operator delete[](void* ptr) throw() {"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "        NATIVE_DEALLOCATOR(ptr);"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println(r5)
            java.io.PrintWriter r11 = r1.out
            r11.println(r6)
        L_0x032f:
            java.io.PrintWriter r11 = r1.out
            r11.println()
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#define jlong_to_ptr(a) ((void*)(uintptr_t)(a))"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#define ptr_to_jlong(a) ((jlong)(uintptr_t)(a))"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            r11.println()
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#if defined(_MSC_VER)"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    #define JavaCPP_noinline __declspec(noinline)"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    #define JavaCPP_hidden /* hidden by default */"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#elif defined(__GNUC__)"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    #define JavaCPP_noinline __attribute__((noinline)) __attribute__ ((unused))"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "    #define JavaCPP_hidden   __attribute__((visibility(\"hidden\"))) __attribute__ ((unused))"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r12 = "#else"
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r13 = "    #define JavaCPP_noinline"
            r11.println(r13)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r13 = "    #define JavaCPP_hidden"
            r11.println(r13)
            java.io.PrintWriter r11 = r1.out
            r11.println(r6)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r13 = "#if __cplusplus >= 201103L || _MSC_VER >= 1900"
            r11.println(r13)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r13 = "    #define JavaCPP_override override"
            r11.println(r13)
            java.io.PrintWriter r11 = r1.out
            r11.println(r12)
            java.io.PrintWriter r11 = r1.out
            java.lang.String r13 = "    #define JavaCPP_override"
            r11.println(r13)
            java.io.PrintWriter r11 = r1.out
            r11.println(r6)
            java.io.PrintWriter r11 = r1.out
            r11.println()
            java.lang.String r11 = ""
            if (r30 != 0) goto L_0x03f0
            java.lang.String r13 = "platform.library.static"
            java.lang.String r14 = "false"
            java.lang.String r13 = r3.getProperty(r13, r14)
            java.lang.String r13 = r13.toLowerCase()
            java.lang.String r14 = "true"
            boolean r14 = r13.equals(r14)
            if (r14 != 0) goto L_0x03d8
            java.lang.String r14 = "t"
            boolean r14 = r13.equals(r14)
            if (r14 != 0) goto L_0x03d8
            boolean r13 = r13.equals(r11)
            if (r13 == 0) goto L_0x03d6
            goto L_0x03d8
        L_0x03d6:
            r13 = r11
            goto L_0x03f2
        L_0x03d8:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "_"
            r13.append(r14)
            java.lang.String r14 = "platform.library"
            java.lang.String r14 = r3.getProperty(r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            goto L_0x03f2
        L_0x03f0:
            r13 = r30
        L_0x03f2:
            java.lang.String r14 = "\""
            r15 = 2
            java.lang.String r4 = "}"
            if (r2 == 0) goto L_0x04f9
            java.lang.String r2 = "platform.exclude"
            java.util.List r2 = r3.get(r2)
            r17 = r13
            java.util.List[] r13 = new java.util.List[r15]
            java.lang.String r15 = "platform.include"
            java.util.List r15 = r3.get(r15)
            r18 = 0
            r13[r18] = r15
            java.lang.String r15 = "platform.cinclude"
            java.util.List r3 = r3.get(r15)
            r15 = 1
            r13[r15] = r3
            r3 = 0
        L_0x0417:
            r15 = 2
            if (r3 >= r15) goto L_0x04f6
            r15 = r13[r3]
            if (r15 == 0) goto L_0x04ea
            r15 = r13[r3]
            int r15 = r15.size()
            if (r15 <= 0) goto L_0x04ea
            r15 = 1
            if (r3 != r15) goto L_0x0448
            java.io.PrintWriter r15 = r1.out
            r18 = r11
            java.lang.String r11 = "extern \"C\" {"
            r15.println(r11)
            java.io.PrintWriter r11 = r1.out2
            if (r11 == 0) goto L_0x044a
            java.lang.String r15 = "#ifdef __cplusplus"
            r11.println(r15)
            java.io.PrintWriter r11 = r1.out2
            java.lang.String r15 = "extern \"C\" {"
            r11.println(r15)
            java.io.PrintWriter r11 = r1.out2
            r11.println(r6)
            goto L_0x044a
        L_0x0448:
            r18 = r11
        L_0x044a:
            r11 = r13[r3]
            java.util.Iterator r11 = r11.iterator()
        L_0x0450:
            boolean r15 = r11.hasNext()
            if (r15 == 0) goto L_0x04c7
            java.lang.Object r15 = r11.next()
            java.lang.String r15 = (java.lang.String) r15
            boolean r19 = r2.contains(r15)
            if (r19 == 0) goto L_0x0463
            goto L_0x0450
        L_0x0463:
            r19 = r2
            java.lang.String r2 = "#include "
            r20 = r11
            java.lang.String r11 = "<"
            boolean r11 = r15.startsWith(r11)
            if (r11 != 0) goto L_0x0488
            boolean r11 = r15.startsWith(r14)
            if (r11 != 0) goto L_0x0488
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r2)
            r2 = 34
            r11.append(r2)
            java.lang.String r2 = r11.toString()
        L_0x0488:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r2)
            r11.append(r15)
            java.lang.String r2 = r11.toString()
            java.lang.String r11 = ">"
            boolean r11 = r15.endsWith(r11)
            if (r11 != 0) goto L_0x04b6
            boolean r11 = r15.endsWith(r14)
            if (r11 != 0) goto L_0x04b6
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r2)
            r2 = 34
            r11.append(r2)
            java.lang.String r2 = r11.toString()
        L_0x04b6:
            java.io.PrintWriter r11 = r1.out
            r11.println(r2)
            java.io.PrintWriter r11 = r1.out2
            if (r11 == 0) goto L_0x04c2
            r11.println(r2)
        L_0x04c2:
            r2 = r19
            r11 = r20
            goto L_0x0450
        L_0x04c7:
            r19 = r2
            r2 = 1
            if (r3 != r2) goto L_0x04e4
            java.io.PrintWriter r2 = r1.out
            r2.println(r4)
            java.io.PrintWriter r2 = r1.out2
            if (r2 == 0) goto L_0x04e4
            java.lang.String r11 = "#ifdef __cplusplus"
            r2.println(r11)
            java.io.PrintWriter r2 = r1.out2
            r2.println(r4)
            java.io.PrintWriter r2 = r1.out2
            r2.println(r6)
        L_0x04e4:
            java.io.PrintWriter r2 = r1.out
            r2.println()
            goto L_0x04ee
        L_0x04ea:
            r19 = r2
            r18 = r11
        L_0x04ee:
            int r3 = r3 + 1
            r11 = r18
            r2 = r19
            goto L_0x0417
        L_0x04f6:
            r18 = r11
            goto L_0x04fd
        L_0x04f9:
            r18 = r11
            r17 = r13
        L_0x04fd:
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = "static JavaVM* JavaCPP_vm = NULL;"
            r2.println(r3)
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = "static bool JavaCPP_haveAllocObject = false;"
            r2.println(r3)
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = "static bool JavaCPP_haveNonvirtual = false;"
            r2.println(r3)
            java.io.PrintWriter r2 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r11 = "static const char* JavaCPP_classNames["
            r3.append(r11)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r11 = r1.jclasses
            int r11 = r11.size()
            r3.append(r11)
            java.lang.String r11 = "] = {"
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r2 = r1.jclasses
            java.util.Iterator r2 = r2.iterator()
            r3 = 0
        L_0x053a:
            boolean r11 = r2.hasNext()
            java.lang.String r13 = ","
            if (r11 == 0) goto L_0x059c
            java.lang.Object r11 = r2.next()
            java.lang.Class r11 = (java.lang.Class) r11
            java.io.PrintWriter r15 = r1.out
            r21 = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r22 = r9
            java.lang.String r9 = "        \""
            r10.append(r9)
            java.lang.String r9 = r11.getName()
            r23 = r8
            r0 = 47
            r8 = 46
            java.lang.String r0 = r9.replace(r8, r0)
            r10.append(r0)
            r10.append(r14)
            java.lang.String r0 = r10.toString()
            r15.print(r0)
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x057e
            java.io.PrintWriter r0 = r1.out
            r0.println(r13)
        L_0x057e:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r0 = r1.members
            java.lang.Object r0 = r0.get(r11)
            java.util.Set r0 = (java.util.Set) r0
            if (r0 == 0) goto L_0x0593
            int r8 = r0.size()
            if (r8 <= r3) goto L_0x0593
            int r0 = r0.size()
            r3 = r0
        L_0x0593:
            r0 = r31
            r10 = r21
            r9 = r22
            r8 = r23
            goto L_0x053a
        L_0x059c:
            r23 = r8
            r22 = r9
            r21 = r10
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = " };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r8 = "static jclass JavaCPP_classes["
            r2.append(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r1.jclasses
            int r8 = r8.size()
            r2.append(r8)
            java.lang.String r8 = "] = { NULL };"
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_addressFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_positionFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_limitFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_capacityFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_deallocatorFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_ownerAddressFID = NULL;"
            r0.println(r2)
            if (r29 == 0) goto L_0x0619
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_booleanValueFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_byteValueFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_shortValueFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_intValueFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_longValueFID = NULL;"
            r0.println(r2)
        L_0x0619:
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_initMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_arrayMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_arrayOffsetMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_bufferPositionFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_bufferLimitFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jfieldID JavaCPP_bufferCapacityFID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_stringMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_getBytesMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static jmethodID JavaCPP_toStringMID = NULL;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "static inline void JavaCPP_log(const char* fmt, ...) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    va_list ap;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    va_start(ap, fmt);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r7)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    __android_log_vprint(ANDROID_LOG_ERROR, \"javacpp\", fmt, ap);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "#elif defined(__APPLE__) && defined(__OBJC__)"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    NSLogv([NSString stringWithUTF8String:fmt], ap);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r12)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    vfprintf(stderr, fmt, ap);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    fprintf(stderr, \"\\n\");"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r6)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    va_end(ap);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out
            r0.println(r7)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    static pthread_key_t JavaCPP_current_env;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    static JavaCPP_noinline void JavaCPP_detach_env(void *data)"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "        if (JavaCPP_vm) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "            JavaCPP_vm->DetachCurrentThread();"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "        }"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r5)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r8 = "    static JavaCPP_noinline void JavaCPP_create_pthread_key(void)"
            r0.println(r8)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r8 = "    {"
            r0.println(r8)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r8 = "        pthread_key_create(&JavaCPP_current_env, JavaCPP_detach_env);"
            r0.println(r8)
            java.io.PrintWriter r0 = r1.out
            r0.println(r5)
            java.io.PrintWriter r0 = r1.out
            r0.println(r6)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.lang.String r0 = "            }"
            r8 = r31
            if (r8 == 0) goto L_0x0722
            boolean r9 = r31.isEmpty()
            if (r9 == 0) goto L_0x071a
            goto L_0x0722
        L_0x071a:
            r21 = r13
            r11 = r22
            r22 = r14
            goto L_0x0ee1
        L_0x0722:
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "static inline jboolean JavaCPP_trimMemory() {"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "#if defined(__linux__) && !defined(__ANDROID__)"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "    return (jboolean)malloc_trim(0);"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            r9.println(r12)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "    return 0;"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            r9.println(r6)
            java.io.PrintWriter r9 = r1.out
            r9.println(r4)
            java.io.PrintWriter r9 = r1.out
            r9.println()
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "static inline jlong JavaCPP_physicalBytes() {"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r10 = "    jlong size = 0;"
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            r10 = r23
            r9.println(r10)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "    static int fd = open(\"/proc/self/statm\", O_RDONLY, 0);"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "    if (fd >= 0) {"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "        char line[256];"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "        char* s;"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "        int n;"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "        if ((n = pread(fd, line, sizeof(line), 0)) > 0 && (s = (char*)memchr(line, ' ', n)) != NULL) {"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "            size = (jlong)(atoll(s + 1) * getpagesize());"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            r9.println(r2)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r11 = "        // no close(fd);"
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            r9.println(r5)
            java.io.PrintWriter r9 = r1.out
            r11 = r22
            r9.println(r11)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r15 = "    task_basic_info info;"
            r9.println(r15)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r15 = "    mach_msg_type_number_t count = TASK_BASIC_INFO_COUNT;"
            r9.println(r15)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r15 = "    if (task_info(current_task(), TASK_BASIC_INFO, (task_info_t)&info, &count) == KERN_SUCCESS) {"
            r9.println(r15)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r15 = "        size = (jlong)info.resident_size;"
            r9.println(r15)
            java.io.PrintWriter r9 = r1.out
            r9.println(r5)
            java.io.PrintWriter r9 = r1.out
            r15 = r21
            r9.println(r15)
            java.io.PrintWriter r9 = r1.out
            java.lang.String r8 = "    PROCESS_MEMORY_COUNTERS counters;"
            r9.println(r8)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (GetProcessMemoryInfo(GetCurrentProcess(), &counters, sizeof(counters))) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        size = (jlong)counters.WorkingSetSize;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return size;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline jlong JavaCPP_totalPhysicalBytes() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jlong size = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    struct sysinfo info;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (sysinfo(&info) == 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        size = (jlong)info.totalram * info.mem_unit;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    size_t length = sizeof(size);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    sysctlbyname(\"hw.memsize\", &size, &length, NULL, 0);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    MEMORYSTATUSEX status;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    status.dwLength = sizeof(status);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (GlobalMemoryStatusEx(&status)) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        size = status.ullTotalPhys;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return size;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline jlong JavaCPP_availablePhysicalBytes() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jlong size = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    int fd = open(\"/proc/meminfo\", O_RDONLY, 0);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (fd >= 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char temp[4096];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char *s;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        int n;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if ((n = read(fd, temp, sizeof(temp))) > 0 && (s = (char*)memmem(temp, n, \"MemAvailable:\", 13)) != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            size = (jlong)(atoll(s + 13) * 1024);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        close(fd);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (size == 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        struct sysinfo info;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (sysinfo(&info) == 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            size = (jlong)info.freeram * info.mem_unit;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    vm_statistics_data_t info;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    mach_msg_type_number_t count = HOST_VM_INFO_COUNT;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (host_statistics(mach_host_self(), HOST_VM_INFO, (host_info_t)&info, &count) == KERN_SUCCESS) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        size = (jlong)info.free_count * getpagesize();"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    MEMORYSTATUSEX status;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    status.dwLength = sizeof(status);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (GlobalMemoryStatusEx(&status)) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        size = status.ullAvailPhys;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return size;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline jint JavaCPP_totalProcessors() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jint total = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    total = sysconf(_SC_NPROCESSORS_CONF);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    size_t length = sizeof(total);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    sysctlbyname(\"hw.logicalcpu_max\", &total, &length, NULL, 0);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    SYSTEM_INFO info;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    GetSystemInfo(&info);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    total = info.dwNumberOfProcessors;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return total;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline jint JavaCPP_totalCores() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jint total = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    const int n = sysconf(_SC_NPROCESSORS_CONF);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    int pids[n], cids[n];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    for (int i = 0; i < n; i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        int fd = 0, pid = 0, cid = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char temp[256];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/physical_package_id\", i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (read(fd, temp, sizeof(temp)) > 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                pid = atoi(temp);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            close(fd);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/core_id\", i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (read(fd, temp, sizeof(temp)) > 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                cid = atoi(temp);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            close(fd);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        bool found = false;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (int j = 0; j < total; j++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (pids[j] == pid && cids[j] == cid) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                found = true;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                break;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (!found) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            pids[total] = pid;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            cids[total] = cid;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            total++;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    size_t length = sizeof(total);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    sysctlbyname(\"hw.physicalcpu_max\", &total, &length, NULL, 0);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    SYSTEM_LOGICAL_PROCESSOR_INFORMATION *info = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    DWORD length = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    BOOL success = GetLogicalProcessorInformation(info, &length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    while (!success && GetLastError() == ERROR_INSUFFICIENT_BUFFER) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        info = (SYSTEM_LOGICAL_PROCESSOR_INFORMATION*)realloc(info, length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        success = GetLogicalProcessorInformation(info, &length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (success && info != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        length /= sizeof(SYSTEM_LOGICAL_PROCESSOR_INFORMATION);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (DWORD i = 0; i < length; i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (info[i].Relationship == RelationProcessorCore) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                total++;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    free(info);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return total;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline jint JavaCPP_totalChips() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jint total = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    const int n = sysconf(_SC_NPROCESSORS_CONF);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    int pids[n];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    for (int i = 0; i < n; i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        int fd = 0, pid = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char temp[256];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/physical_package_id\", i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (read(fd, temp, sizeof(temp)) > 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                pid = atoi(temp);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            close(fd);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        bool found = false;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (int j = 0; j < total; j++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (pids[j] == pid) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                found = true;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                break;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (!found) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            pids[total] = pid;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            total++;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    size_t length = sizeof(total);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    sysctlbyname(\"hw.packages\", &total, &length, NULL, 0);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    SYSTEM_LOGICAL_PROCESSOR_INFORMATION *info = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    DWORD length = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    BOOL success = GetLogicalProcessorInformation(info, &length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    while (!success && GetLastError() == ERROR_INSUFFICIENT_BUFFER) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        info = (SYSTEM_LOGICAL_PROCESSOR_INFORMATION*)realloc(info, length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        success = GetLogicalProcessorInformation(info, &length);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (success && info != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        length /= sizeof(SYSTEM_LOGICAL_PROCESSOR_INFORMATION);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (DWORD i = 0; i < length; i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (info[i].Relationship == RelationProcessorPackage) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                total++;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    free(info);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return total;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#if defined(__linux__) && !(defined(__ANDROID__) && defined(__arm__))"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static int JavaCPP_dlcallback(dl_phdr_info *info, size_t size, void *data) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    void *handle = dlopen(info->dlpi_name, RTLD_LAZY);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (handle != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        void *address = dlsym(handle, ((char**)data)[0]);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        dlclose(handle);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (address != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            ((void**)data)[1] = address;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            return 1;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static JavaCPP_noinline jclass JavaCPP_getClass(JNIEnv* env, int i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline void JavaCPP_loadGlobal(JNIEnv* env, jclass cls, const char* filename) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#ifdef _WIN32"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    HMODULE handle = LoadLibrary(filename);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (handle == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char temp[256];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(temp, \"LoadLibrary() failed with 0x%lx\", GetLastError());"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r21 = r13
            java.lang.String r13 = "        env->ThrowNew(JavaCPP_getClass(env, "
            r9.append(r13)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r13 = r1.jclasses
            r22 = r14
            java.lang.Class<java.lang.UnsatisfiedLinkError> r14 = java.lang.UnsatisfiedLinkError.class
            int r13 = r13.index(r14)
            r9.append(r13)
            java.lang.String r13 = "), temp);"
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r12)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    void *handle = dlopen(filename, RTLD_LAZY | RTLD_GLOBAL);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (handle == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r13 = "        env->ThrowNew(JavaCPP_getClass(env, "
            r9.append(r13)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r13 = r1.jclasses
            java.lang.Class<java.lang.UnsatisfiedLinkError> r14 = java.lang.UnsatisfiedLinkError.class
            int r13 = r13.index(r14)
            r9.append(r13)
            java.lang.String r13 = "), dlerror());"
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline void* JavaCPP_addressof(const char* name) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    void *address = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    address = dlsym(RTLD_DEFAULT, name);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#if !(defined(__ANDROID__) && defined(__arm__))"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (address == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        void *data[] = { (char*)name, NULL };"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        dl_iterate_phdr(JavaCPP_dlcallback, data);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        address = data[1];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    address = dlsym(RTLD_DEFAULT, name);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (address == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (uint32_t i = 0; i < _dyld_image_count(); i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            const char *libname = _dyld_get_image_name(i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (libname != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                void *handle = dlopen(libname, RTLD_LAZY);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                if (handle != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                    address = dlsym(handle, name);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                    dlclose(handle);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                    if (address != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                        break;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                    }"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                }"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    HANDLE process = GetCurrentProcess();"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    HMODULE *modules = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    DWORD length = 0, needed = 0;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    BOOL success = EnumProcessModules(process, modules, length, &needed);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    while (success && needed > length) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        modules = (HMODULE*)realloc(modules, length = needed);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        success = EnumProcessModules(process, modules, length, &needed);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (success && modules != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        length = needed / sizeof(HMODULE);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        for (DWORD i = 0; i < length; i++) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            address = (void*)GetProcAddress(modules[i], name);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            if (address != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "                break;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r0)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    free(modules);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return address;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static inline JavaVM* JavaCPP_getJavaVM() {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return JavaCPP_vm;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
        L_0x0ee1:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static JavaCPP_noinline jclass JavaCPP_getClass(JNIEnv* env, int i) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (JavaCPP_classes[i] == NULL && env->PushLocalFrame(1) == 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        jclass cls = env->FindClass(JavaCPP_classNames[i]);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (cls == NULL || env->ExceptionCheck()) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JavaCPP_log(\"Error loading class %s.\", JavaCPP_classNames[i]);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            return NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        JavaCPP_classes[i] = (jclass)env->NewWeakGlobalRef(cls);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (JavaCPP_classes[i] == NULL || env->ExceptionCheck()) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JavaCPP_log(\"Error creating global reference of class %s.\", JavaCPP_classNames[i]);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            return NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        env->PopLocalFrame(NULL);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return JavaCPP_classes[i];"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static JavaCPP_noinline jfieldID JavaCPP_getFieldID(JNIEnv* env, int i, const char* name, const char* sig) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    jclass cls = JavaCPP_getClass(env, i);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (cls == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        return NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jfieldID fid = env->GetFieldID(cls, name, sig);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (fid == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error getting field ID of %s/%s\", JavaCPP_classNames[i], name);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return fid;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            if (r29 == 0) goto L_0x0ff7
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline jfieldID JavaCPP_getFieldID(JNIEnv* env, const char* clsName, const char* name, const char* sig) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jclass cls = env->FindClass(clsName);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (cls == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error loading class %s.\", clsName);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jfieldID fid = env->GetFieldID(cls, name, sig);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (fid == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error getting field ID of %s/%s\", clsName, name);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return fid;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
        L_0x0ff7:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline jmethodID JavaCPP_getMethodID(JNIEnv* env, int i, const char* name, const char* sig) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jclass cls = JavaCPP_getClass(env, i);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (cls == NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jmethodID mid = env->GetMethodID(cls, name, sig);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (mid == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error getting method ID of %s/%s\", JavaCPP_classNames[i], name);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return mid;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline jmethodID JavaCPP_getStaticMethodID(JNIEnv* env, int i, const char* name, const char* sig) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jclass cls = JavaCPP_getClass(env, i);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (cls == NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jmethodID mid = env->GetStaticMethodID(cls, name, sig);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (mid == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error getting static method ID of %s/%s\", JavaCPP_classNames[i], name);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return mid;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline jobject JavaCPP_createPointer(JNIEnv* env, int i, jclass cls = NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (cls == NULL && (cls = JavaCPP_getClass(env, i)) == NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (JavaCPP_haveAllocObject) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        return env->AllocObject(cls);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    } else {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        jmethodID mid = env->GetMethodID(cls, \"<init>\", \"(Lorg/bytedeco/javacpp/Pointer;)V\");"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        if (mid == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "            JavaCPP_log(\"Error getting Pointer constructor of %s, while VM does not support AllocObject()\", JavaCPP_classNames[i]);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "            return NULL;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        return env->NewObject(cls, mid, NULL);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline void JavaCPP_initPointer(JNIEnv* env, jobject obj, const void* ptr, jlong size, void* owner, void (*deallocator)(void*)) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (owner != NULL && deallocator != NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        jvalue args[4];"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        args[0].j = ptr_to_jlong(ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        args[1].j = size;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        args[2].j = ptr_to_jlong(owner);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        args[3].j = ptr_to_jlong(deallocator);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        if (JavaCPP_haveNonvirtual) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "            env->CallNonvirtualVoidMethodA(obj, JavaCPP_getClass(env, "
            r10.append(r13)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r13 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r14 = org.bytedeco.javacpp.Pointer.class
            int r13 = r13.index(r14)
            r10.append(r13)
            java.lang.String r13 = "), JavaCPP_initMID, args);"
            r10.append(r13)
            java.lang.String r10 = r10.toString()
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        } else {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "            env->CallVoidMethodA(obj, JavaCPP_initMID, args);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    } else {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        env->SetLongField(obj, JavaCPP_addressFID, ptr_to_jlong(ptr));"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        env->SetLongField(obj, JavaCPP_limitFID, (jlong)size);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        env->SetLongField(obj, JavaCPP_capacityFID, (jlong)size);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            if (r26 != 0) goto L_0x1196
            if (r28 == 0) goto L_0x1208
        L_0x1196:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline jstring JavaCPP_createString(JNIEnv* env, const char* ptr) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (ptr == NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "#ifdef MODIFIED_UTF8_STRING"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return env->NewStringUTF(ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r12)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    size_t length = strlen(ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jbyteArray bytes = env->NewByteArray(length < INT_MAX ? length : INT_MAX);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    env->SetByteArrayRegion(bytes, 0, length < INT_MAX ? length : INT_MAX, (signed char*)ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "    return (jstring)env->NewObject(JavaCPP_getClass(env, "
            r10.append(r13)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r13 = r1.jclasses
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            int r13 = r13.index(r14)
            r10.append(r13)
            java.lang.String r13 = "), JavaCPP_stringMID, bytes);"
            r10.append(r13)
            java.lang.String r10 = r10.toString()
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
        L_0x1208:
            if (r28 == 0) goto L_0x12ce
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline const char* JavaCPP_getStringBytes(JNIEnv* env, jstring str) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (str == NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "#ifdef MODIFIED_UTF8_STRING"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return env->GetStringUTFChars(str, NULL);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r12)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jbyteArray bytes = (jbyteArray)env->CallObjectMethod(str, JavaCPP_getBytesMID);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (bytes == NULL || env->ExceptionCheck()) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        JavaCPP_log(\"Error getting bytes from string.\");"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    jsize length = env->GetArrayLength(bytes);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    signed char* ptr = new (std::nothrow) signed char[length + 1];"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (ptr != NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        env->GetByteArrayRegion(bytes, 0, length, ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        ptr[length] = 0;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    return (const char*)ptr;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "static JavaCPP_noinline void JavaCPP_releaseStringBytes(JNIEnv* env, jstring str, const char* ptr) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "#ifdef MODIFIED_UTF8_STRING"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    if (str != NULL) {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "        env->ReleaseStringUTFChars(str, ptr);"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r12)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "    delete[] ptr;"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
        L_0x12ce:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "class JavaCPP_hidden JavaCPP_exception : public std::exception {"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = "public:"
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "    JavaCPP_exception(const char* str) throw() {"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "        if (str == NULL) {"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "            strcpy(msg, \"Unknown exception.\");"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "        } else {"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "            strncpy(msg, str, sizeof(msg));"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "            msg[sizeof(msg) - 1] = 0;"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "    virtual const char* what() const throw() { return msg; }"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "    char msg[1024];"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r13 = "};"
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            if (r26 == 0) goto L_0x13b8
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#ifndef GENERIC_EXCEPTION_CLASS"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#define GENERIC_EXCEPTION_CLASS std::exception"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#ifndef GENERIC_EXCEPTION_TOSTRING"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#define GENERIC_EXCEPTION_TOSTRING what()"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "static JavaCPP_noinline jthrowable JavaCPP_handleException(JNIEnv* env, int i) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    jstring str = NULL;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    try {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        throw;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    } catch (GENERIC_EXCEPTION_CLASS& e) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str = JavaCPP_createString(env, e.GENERIC_EXCEPTION_TOSTRING);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    } catch (...) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str = JavaCPP_createString(env, \"Unknown exception.\");"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    jmethodID mid = JavaCPP_getMethodID(env, i, \"<init>\", \"(Ljava/lang/String;)V\");"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    if (mid == NULL) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    return (jthrowable)env->NewObject(JavaCPP_getClass(env, i), mid, str);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
        L_0x13b8:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r8.<init>()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.Class<org.bytedeco.javacpp.Pointer> r9 = org.bytedeco.javacpp.Pointer.class
            java.lang.String r9 = r9.getName()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r8.append(r9)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.String r9 = "$Deallocator"
            r8.append(r9)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.String r8 = r8.toString()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.Class<org.bytedeco.javacpp.Pointer> r9 = org.bytedeco.javacpp.Pointer.class
            java.lang.ClassLoader r9 = r9.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r14 = 0
            java.lang.Class r8 = java.lang.Class.forName(r8, r14, r9)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r9.<init>()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.Class<org.bytedeco.javacpp.Pointer> r14 = org.bytedeco.javacpp.Pointer.class
            java.lang.String r14 = r14.getName()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r9.append(r14)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.String r14 = "$NativeDeallocator"
            r9.append(r14)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.String r9 = r9.toString()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            java.lang.Class<org.bytedeco.javacpp.Pointer> r14 = org.bytedeco.javacpp.Pointer.class
            java.lang.ClassLoader r14 = r14.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            r15 = 0
            java.lang.Class r9 = java.lang.Class.forName(r9, r15, r14)     // Catch:{ ClassNotFoundException -> 0x2cc9 }
            if (r27 == 0) goto L_0x1a05
            java.io.PrintWriter r14 = r1.out
            java.lang.String r15 = "static JavaCPP_noinline void* JavaCPP_getPointerOwner(JNIEnv* env, jobject obj) {"
            r14.println(r15)
            java.io.PrintWriter r14 = r1.out
            java.lang.String r15 = "    if (obj != NULL) {"
            r14.println(r15)
            java.io.PrintWriter r14 = r1.out
            java.lang.String r15 = "        jobject deallocator = env->GetObjectField(obj, JavaCPP_deallocatorFID);"
            r14.println(r15)
            java.io.PrintWriter r14 = r1.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r26 = r8
            java.lang.String r8 = "        if (deallocator != NULL && env->IsInstanceOf(deallocator, JavaCPP_getClass(env, "
            r15.append(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r1.jclasses
            int r8 = r8.index(r9)
            r15.append(r8)
            java.lang.String r8 = "))) {"
            r15.append(r8)
            java.lang.String r8 = r15.toString()
            r14.println(r8)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            return jlong_to_ptr(env->GetLongField(deallocator, JavaCPP_ownerAddressFID));"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    return NULL;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#include <vector>"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "template<typename P, typename T = P, typename A = std::allocator<T> > class JavaCPP_hidden VectorAdapter {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    VectorAdapter(const P* ptr, typename std::vector<T,A>::size_type size, void* owner) : ptr((P*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        vec2(ptr ? std::vector<T,A>((P*)ptr, (P*)ptr + size) : std::vector<T,A>()), vec(vec2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    VectorAdapter(const std::vector<T,A>& vec) : ptr(0), size(0), owner(0), vec2(vec), vec(vec2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    VectorAdapter(      std::vector<T,A>& vec) : ptr(0), size(0), owner(0), vec(vec) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    VectorAdapter(const std::vector<T,A>* vec) : ptr(0), size(0), owner(0), vec(*(std::vector<T,A>*)vec) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(P* ptr, typename std::vector<T,A>::size_type size, void* owner) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->ptr = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->size = size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->owner = owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        vec.assign(ptr, ptr + size);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    static void deallocate(void* owner) { operator delete(owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator P*() {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (vec.size() > size) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            ptr = (P*)(operator new(sizeof(P) * vec.size(), std::nothrow_t()));"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (ptr) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            std::copy(vec.begin(), vec.end(), ptr);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        size = vec.size();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        owner = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        return ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const P*()        { size = vec.size(); return &vec[0]; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator std::vector<T,A>&() { return vec; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator std::vector<T,A>*() { return ptr ? &vec : 0; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    P* ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    typename std::vector<T,A>::size_type size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void* owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    std::vector<T,A> vec2;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    std::vector<T,A>& vec;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#include <string>"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "template<typename T = char> class JavaCPP_hidden StringAdapter {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const          char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const signed   char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const unsigned char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const       wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const unsigned short* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const   signed   int* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const std::basic_string<T>& str) : ptr(0), size(0), owner(0), str2(str), str(str2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(      std::basic_string<T>& str) : ptr(0), size(0), owner(0), str(str) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    StringAdapter(const std::basic_string<T>* str) : ptr(0), size(0), owner(0), str(*(std::basic_string<T>*)str) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(char* ptr, typename std::basic_string<T>::size_type size, void* owner) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->ptr = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->size = size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->owner = owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str.assign(ptr ? ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const          char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const signed   char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const unsigned char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->ptr = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->size = size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->owner = owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        str.assign(ptr ? ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const        wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const unsigned short* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(const   signed   int* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    static void deallocate(void* owner) { delete[] (T*)owner; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator char*() {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        const char* data = str.data();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (str.size() > size) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            ptr = new (std::nothrow) char[str.size()+1];"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            if (ptr) memset(ptr, 0, str.size()+1);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (ptr && memcmp(ptr, data, str.size()) != 0) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            memcpy(ptr, data, str.size());"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            if (size > str.size()) ptr[str.size()] = 0;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        size = str.size();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        owner = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        return ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator       signed   char*() { return (signed   char*)(operator char*)(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator       unsigned char*() { return (unsigned char*)(operator char*)(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const          char*() { size = str.size(); return                 str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const signed   char*() { size = str.size(); return (signed   char*)str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const unsigned char*() { size = str.size(); return (unsigned char*)str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator wchar_t*() {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        const wchar_t* data = str.data();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (str.size() > size) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            ptr = new (std::nothrow) wchar_t[str.size()+1];"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            if (ptr) memset(ptr, 0, sizeof(wchar_t) * (str.size()+1));"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (ptr && memcmp(ptr, data, sizeof(wchar_t) * str.size()) != 0) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            memcpy(ptr, data, sizeof(wchar_t) * str.size());"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            if (size > str.size()) ptr[str.size()] = 0;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        size = str.size();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        owner = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        return ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator     unsigned   short*() { return (unsigned short*)(operator wchar_t*)(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator       signed     int*() { return (  signed   int*)(operator wchar_t*)(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const        wchar_t*() { size = str.size(); return                  str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const unsigned short*() { size = str.size(); return (unsigned short*)str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator const   signed   int*() { size = str.size(); return (  signed   int*)str.c_str(); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator         std::basic_string<T>&() { return str; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator         std::basic_string<T>*() { return ptr ? &str : 0; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    T* ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    typename std::basic_string<T>::size_type size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void* owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    std::basic_string<T> str2;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    std::basic_string<T>& str;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#ifdef SHARED_PTR_NAMESPACE"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "template<class T> class SharedPtrAdapter {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    typedef SHARED_PTR_NAMESPACE::shared_ptr<T> S;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    SharedPtrAdapter(const T* ptr, size_t size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            sharedPtr2(owner != NULL && owner != ptr ? *(S*)owner : S((T*)ptr)), sharedPtr(sharedPtr2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    SharedPtrAdapter(const S& sharedPtr) : ptr(0), size(0), owner(0), sharedPtr2(sharedPtr), sharedPtr(sharedPtr2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    SharedPtrAdapter(      S& sharedPtr) : ptr(0), size(0), owner(0), sharedPtr(sharedPtr) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    SharedPtrAdapter(const S* sharedPtr) : ptr(0), size(0), owner(0), sharedPtr(*(S*)sharedPtr) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(T* ptr, size_t size, void* owner) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->ptr = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->size = size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->owner = owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->sharedPtr = owner != NULL && owner != ptr ? *(S*)owner : S((T*)ptr);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    static void deallocate(void* owner) { delete (S*)owner; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator typename SHARED_PTR_NAMESPACE::remove_const<T>::type*() {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        ptr = sharedPtr.get();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (owner == NULL || owner == ptr) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            owner = new S(sharedPtr);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        return (typename SHARED_PTR_NAMESPACE::remove_const<T>::type*)ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator S&() { return sharedPtr; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator S*() { return &sharedPtr; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    T* ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    size_t size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void* owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    S sharedPtr2;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    S& sharedPtr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "#ifdef UNIQUE_PTR_NAMESPACE"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "template<class T, class D = UNIQUE_PTR_NAMESPACE::default_delete<T> > class UniquePtrAdapter {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    typedef UNIQUE_PTR_NAMESPACE::unique_ptr<T,D> U;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    UniquePtrAdapter(const T* ptr, size_t size, void* owner) : ptr((T*)ptr), size(size), owner(owner),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            uniquePtr2(owner != NULL && owner != ptr ? U() : U((T*)ptr)),"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            uniquePtr(owner != NULL && owner != ptr ? *(U*)owner : uniquePtr2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    UniquePtrAdapter(U&& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr2(UNIQUE_PTR_NAMESPACE::move(uniquePtr)), uniquePtr(uniquePtr2) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    UniquePtrAdapter(const U& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr((U&)uniquePtr) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    UniquePtrAdapter(      U& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr(uniquePtr) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    UniquePtrAdapter(const U* uniquePtr) : ptr(0), size(0), owner(0), uniquePtr(*(U*)uniquePtr) { }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void assign(T* ptr, size_t size, void* owner) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->ptr = ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->size = size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->owner = owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        this->uniquePtr = owner != NULL && owner != ptr ? *(U*)owner : U((T*)ptr);"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    static void deallocate(void* owner) { delete (U*)owner; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator typename UNIQUE_PTR_NAMESPACE::remove_const<T>::type*() {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        ptr = uniquePtr.get();"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        if (ptr == uniquePtr2.get() && (owner == NULL || owner == ptr)) {"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            // only move the pointer if we actually own it through uniquePtr2"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "            owner = new U(UNIQUE_PTR_NAMESPACE::move(uniquePtr));"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "        return (typename UNIQUE_PTR_NAMESPACE::remove_const<T>::type*)ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator U&() const { return uniquePtr; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator U&&() { return UNIQUE_PTR_NAMESPACE::move(uniquePtr); }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    operator U*() { return &uniquePtr; }"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    T* ptr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    size_t size;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    void* owner;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    U uniquePtr2;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r14 = "    U& uniquePtr;"
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r14 = r18
            r8.println(r14)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "#if __cplusplus >= 201103L || _MSC_VER >= 1900"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "#include <utility>"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "template<class T> class MoveAdapter {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r10)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    MoveAdapter(const T* ptr, size_t size, void* owner) : ptr(&movedPtr), size(size), owner(owner), movedPtr(std::move(*(T*)ptr)) { }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    MoveAdapter(const T& ptr) : ptr(&movedPtr), size(0), owner(0), movedPtr(std::move((T&)ptr)) { }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    MoveAdapter(T&& ptr) : ptr(&movedPtr), size(0), owner(0), movedPtr((T&&)ptr) { }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    void assign(T* ptr, size_t size, void* owner) {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        this->ptr = &this->movedPtr;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        this->size = size;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        this->owner = owner;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        this->movedPtr = std::move(*ptr);"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    static void deallocate(void* owner) { delete (T*)owner; }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    operator T*() {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        ptr = new T(std::move(movedPtr));"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        owner = ptr;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        return ptr;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    operator const T*() { return ptr; }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    operator T&&() { return std::move(movedPtr); }"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    T* ptr;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    size_t size;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    void* owner;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    T movedPtr;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r13)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            goto L_0x1a09
        L_0x1a05:
            r26 = r8
            r14 = r18
        L_0x1a09:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r1.functions
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x1a22
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r8 = r1.virtualFunctions
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x1a1a
            goto L_0x1a22
        L_0x1a1a:
            r28 = r9
            r18 = r14
            r14 = r17
            goto L_0x1c64
        L_0x1a22:
            java.io.PrintWriter r8 = r1.out
            r8.println(r7)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "  static pthread_once_t JavaCPP_once = PTHREAD_ONCE_INIT;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "  static pthread_mutex_t JavaCPP_lock = PTHREAD_MUTEX_INITIALIZER;"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "static JavaCPP_noinline void JavaCPP_detach(bool detach) {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "#if !defined(NO_JNI_DETACH_THREAD) && !defined(__ANDROID__)"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "    if (detach && JavaCPP_vm->DetachCurrentThread() != JNI_OK) {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "        JavaCPP_log(\"Could not detach the JavaVM from the current thread.\");"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            java.io.PrintWriter r8 = r1.out
            r8.println()
            boolean r8 = r17.isEmpty()
            if (r8 != 0) goto L_0x1aa3
            java.io.PrintWriter r8 = r1.out
            java.lang.String r15 = "extern \"C\" {"
            r8.println(r15)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r18 = r14
            java.lang.String r14 = "JNIEXPORT jint JNICALL JNI_OnLoad"
            r15.append(r14)
            r14 = r17
            r15.append(r14)
            r28 = r9
            java.lang.String r9 = "(JavaVM* vm, void* reserved);"
            r15.append(r9)
            java.lang.String r9 = r15.toString()
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r4)
            goto L_0x1aa9
        L_0x1aa3:
            r28 = r9
            r18 = r14
            r14 = r17
        L_0x1aa9:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "static JavaCPP_noinline bool JavaCPP_getEnv(JNIEnv** env) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    bool attached = false;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    JavaVM *vm = JavaCPP_vm;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (vm == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out2
            if (r8 == 0) goto L_0x1ae3
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#if !defined(__ANDROID__) && !TARGET_OS_IPHONE"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        int size = 1;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (JNI_GetCreatedJavaVMs(&vm, 1, &size) != JNI_OK || size == 0) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
        L_0x1ae3:
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JavaCPP_log(\"Could not get any created JavaVM.\");"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            *env = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            return false;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out2
            if (r8 == 0) goto L_0x1b0d
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#if !defined(__ANDROID__) && !TARGET_OS_IPHONE"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
        L_0x1b0d:
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r7)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    pthread_mutex_lock(&JavaCPP_lock);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    pthread_once(&JavaCPP_once, JavaCPP_create_pthread_key);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if ((*env = (JNIEnv *)pthread_getspecific(JavaCPP_current_env)) != NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        attached = true;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        goto done;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (vm->GetEnv((void**)env, JNI_VERSION_1_6) != JNI_OK) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        struct {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JNIEnv **env;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            operator JNIEnv**() { return env; } // Android JNI"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            operator void**() { return (void**)env; } // standard JNI"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        } env2 = { env };"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        JavaVMAttachArgs args;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        args.version = JNI_VERSION_1_6;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        args.group = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        char name[64] = {0};"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "#ifdef _WIN32"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(name, \"JavaCPP Thread ID %lu\", GetCurrentThreadId());"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r11)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(name, \"JavaCPP Thread ID %u\", pthread_mach_thread_np(pthread_self()));"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r12)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        sprintf(name, \"JavaCPP Thread ID %lu\", pthread_self());"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        args.name = name;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        if (vm->AttachCurrentThread(env2, &args) != JNI_OK) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JavaCPP_log(\"Could not attach the JavaVM to the current thread.\");"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            *env = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            goto done;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r7)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        pthread_setspecific(JavaCPP_current_env, *env);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r6)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "        attached = true;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "    if (JavaCPP_vm == NULL) {"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "        if (JNI_OnLoad"
            r9.append(r11)
            r9.append(r14)
            java.lang.String r11 = "(vm, NULL) < 0) {"
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            JavaCPP_detach(attached);"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            *env = NULL;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "            goto done;"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r2)
            java.io.PrintWriter r8 = r1.out
            r8.println(r5)
            java.io.PrintWriter r8 = r1.out
            java.lang.String r9 = "done:"
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            r8.println(r7)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r8 = "    pthread_mutex_unlock(&JavaCPP_lock);"
            r7.println(r8)
            java.io.PrintWriter r7 = r1.out
            r7.println(r6)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r8 = "    return attached;"
            r7.println(r8)
            java.io.PrintWriter r7 = r1.out
            r7.println(r4)
            java.io.PrintWriter r7 = r1.out
            r7.println()
        L_0x1c64:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r7 = r1.functions
            java.util.Iterator r7 = r7.iterator()
        L_0x1c6a:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x1dbf
            java.lang.Object r8 = r7.next()
            java.lang.Class r8 = (java.lang.Class) r8
            java.lang.String[] r9 = r1.cppTypeName(r8)
            r11 = 0
            r15 = r9[r11]
            java.lang.String r11 = "\\("
            java.lang.String[] r11 = r15.split(r11)
            r27 = r7
            r15 = 2
            java.lang.String[] r7 = new java.lang.String[r15]
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r23 = r2
            r17 = 0
            r2 = r11[r17]
            r15.append(r2)
            int r2 = r11.length
            r24 = r4
            r4 = 2
            if (r2 <= r4) goto L_0x1c9f
            java.lang.String r2 = "(*"
            goto L_0x1ca1
        L_0x1c9f:
            r2 = r18
        L_0x1ca1:
            r15.append(r2)
            java.lang.String r2 = r15.toString()
            r7[r17] = r2
            int r2 = r11.length
            if (r2 <= r4) goto L_0x1cc1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r15 = ")("
            r2.append(r15)
            r15 = r11[r4]
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            goto L_0x1cc3
        L_0x1cc1:
            r2 = r18
        L_0x1cc3:
            r15 = 1
            r7[r15] = r2
            int r2 = r11.length
            if (r2 <= r4) goto L_0x1cd1
            int r2 = r11.length
            java.lang.Object[] r2 = java.util.Arrays.copyOfRange(r11, r4, r2)
            r11 = r2
            java.lang.String[] r11 = (java.lang.String[]) r11
        L_0x1cd1:
            java.lang.String[] r2 = new java.lang.String[r15]
            r4 = r11[r15]
            r16 = 0
            r2[r16] = r4
            java.lang.String r2 = constValueTypeName(r2)
            r11[r15] = r2
            r2 = r9[r15]
            java.lang.String r2 = r2.substring(r15)
            java.lang.String r4 = functionClassName(r8)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r15 = "struct JavaCPP_hidden "
            r9.append(r15)
            r9.append(r4)
            java.lang.String r15 = " {"
            r9.append(r15)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r15 = "    "
            r9.append(r15)
            r9.append(r4)
            java.lang.String r15 = "() : ptr(NULL), obj(NULL) { }"
            r9.append(r15)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            if (r2 == 0) goto L_0x1d56
            int r8 = r2.length()
            if (r8 <= 0) goto L_0x1d56
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r15 = "    "
            r9.append(r15)
            r17 = r0
            r15 = 0
            r0 = r7[r15]
            r9.append(r0)
            java.lang.String r0 = "operator()"
            r9.append(r0)
            r9.append(r2)
            r15 = 1
            r0 = r7[r15]
            r9.append(r0)
            java.lang.String r0 = ";"
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r8.println(r0)
            goto L_0x1d58
        L_0x1d56:
            r17 = r0
        L_0x1d58:
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "    "
            r8.append(r9)
            r9 = 0
            r15 = r7[r9]
            r8.append(r15)
            java.lang.String r9 = "("
            r8.append(r9)
            r9 = 1
            r11 = r11[r9]
            r8.append(r11)
            java.lang.String r11 = "*ptr)"
            r8.append(r11)
            r8.append(r2)
            r2 = r7[r9]
            r8.append(r2)
            java.lang.String r2 = ";"
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    jobject obj; static jmethodID mid;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r13)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "jmethodID "
            r2.append(r7)
            r2.append(r4)
            java.lang.String r4 = "::mid = NULL;"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            r7 = r27
            r0 = r17
            r2 = r23
            r4 = r24
            goto L_0x1c6a
        L_0x1dbf:
            r17 = r0
            r23 = r2
            r24 = r4
            java.io.PrintWriter r0 = r1.out
            r0.println()
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.jclasses
            java.util.Iterator r0 = r0.iterator()
        L_0x1dd0:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x1ec5
            java.lang.Object r2 = r0.next()
            java.lang.Class r2 = (java.lang.Class) r2
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r4 = r1.virtualFunctions
            java.lang.Object r4 = r4.get(r2)
            java.util.Set r4 = (java.util.Set) r4
            if (r4 != 0) goto L_0x1de7
            goto L_0x1dd0
        L_0x1de7:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r7 = r1.virtualMembers
            java.lang.Object r7 = r7.get(r2)
            java.util.Set r7 = (java.util.Set) r7
            java.lang.String[] r2 = r1.cppTypeName(r2)
            java.lang.String r2 = valueTypeName(r2)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "JavaCPP_"
            r8.append(r9)
            java.lang.String r9 = mangle(r2)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.io.PrintWriter r9 = r1.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r15 = "class JavaCPP_hidden "
            r11.append(r15)
            r11.append(r8)
            java.lang.String r15 = " : public "
            r11.append(r15)
            r11.append(r2)
            java.lang.String r2 = " {"
            r11.append(r2)
            java.lang.String r2 = r11.toString()
            r9.println(r2)
            java.io.PrintWriter r2 = r1.out
            r2.println(r10)
            java.io.PrintWriter r2 = r1.out
            java.lang.String r9 = "    jobject obj;"
            r2.println(r9)
            java.util.Iterator r2 = r4.iterator()
        L_0x1e3f:
            boolean r9 = r2.hasNext()
            if (r9 == 0) goto L_0x1e6b
            java.lang.Object r9 = r2.next()
            java.lang.String r9 = (java.lang.String) r9
            java.io.PrintWriter r11 = r1.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r27 = r0
            java.lang.String r0 = "    static jmethodID "
            r15.append(r0)
            r15.append(r9)
            java.lang.String r0 = ";"
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r11.println(r0)
            r0 = r27
            goto L_0x1e3f
        L_0x1e6b:
            r27 = r0
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.util.Iterator r0 = r7.iterator()
        L_0x1e76:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x1e88
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.io.PrintWriter r7 = r1.out
            r7.println(r2)
            goto L_0x1e76
        L_0x1e88:
            java.io.PrintWriter r0 = r1.out
            r0.println(r13)
            java.util.Iterator r0 = r4.iterator()
        L_0x1e91:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x1ec1
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "jmethodID "
            r7.append(r9)
            r7.append(r8)
            java.lang.String r9 = "::"
            r7.append(r9)
            r7.append(r2)
            java.lang.String r2 = " = NULL;"
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r4.println(r2)
            goto L_0x1e91
        L_0x1ec1:
            r0 = r27
            goto L_0x1dd0
        L_0x1ec5:
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.util.Map<java.lang.String, java.lang.String> r0 = r1.callbacks
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x1ed4:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x1ee6
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.io.PrintWriter r4 = r1.out
            r4.println(r2)
            goto L_0x1ed4
        L_0x1ee6:
            java.io.PrintWriter r0 = r1.out
            r0.println()
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.deallocators
            java.util.Iterator r0 = r0.iterator()
        L_0x1ef1:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2019
            java.lang.Object r2 = r0.next()
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "JavaCPP_"
            r4.append(r7)
            java.lang.String r7 = r2.getName()
            java.lang.String r7 = mangle(r7)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "static void "
            r8.append(r9)
            r8.append(r4)
            java.lang.String r4 = "_deallocate(void *p) { "
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r7.print(r4)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r4 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r4 = r4.isAssignableFrom(r2)
            if (r4 == 0) goto L_0x1fa5
            java.lang.String r2 = functionClassName(r2)
            java.util.Map<java.lang.String, java.lang.String> r4 = r1.callbacks
            boolean r4 = r4.containsKey(r2)
            if (r4 == 0) goto L_0x1f80
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "\n    int n = sizeof("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "_instances) / sizeof("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "_instances[0]);\n    for (int i = 0; i < n; i++) { if ("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "_instances[i].obj == (("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "*)p)->obj) "
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "_instances[i].obj = NULL; }"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4.print(r7)
        L_0x1f80:
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "\n    JNIEnv *e; bool a = JavaCPP_getEnv(&e); if (e != NULL) e->DeleteWeakGlobalRef((jweak)(("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "*)p)->obj); delete ("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r2 = "*)p; JavaCPP_detach(a); }"
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r4.println(r2)
            goto L_0x1ef1
        L_0x1fa5:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r4 = r1.virtualFunctions
            boolean r4 = r4.containsKey(r2)
            if (r4 == 0) goto L_0x1fef
            java.lang.String[] r2 = r1.cppTypeName(r2)
            java.lang.String r2 = valueTypeName(r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "JavaCPP_"
            r4.append(r7)
            java.lang.String r2 = mangle(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "JNIEnv *e; bool a = JavaCPP_getEnv(&e); if (e != NULL) e->DeleteWeakGlobalRef((jweak)(("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "*)p)->obj); delete ("
            r7.append(r8)
            r7.append(r2)
            java.lang.String r2 = "*)p; JavaCPP_detach(a); }"
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r4.println(r2)
            goto L_0x1ef1
        L_0x1fef:
            java.lang.String[] r2 = r1.cppTypeName(r2)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "delete ("
            r7.append(r8)
            r8 = 0
            r9 = r2[r8]
            r7.append(r9)
            r8 = 1
            r2 = r2[r8]
            r7.append(r2)
            java.lang.String r2 = ")p; }"
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r4.println(r2)
            goto L_0x1ef1
        L_0x2019:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.arrayDeallocators
            java.util.Iterator r0 = r0.iterator()
        L_0x201f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2075
            java.lang.Object r2 = r0.next()
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "JavaCPP_"
            r4.append(r7)
            java.lang.String r7 = r2.getName()
            java.lang.String r7 = mangle(r7)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            java.lang.String[] r2 = r1.cppTypeName(r2)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "static void "
            r8.append(r9)
            r8.append(r4)
            java.lang.String r4 = "_deallocateArray(void* p) { delete[] ("
            r8.append(r4)
            r4 = 0
            r9 = r2[r4]
            r8.append(r9)
            r4 = 1
            r2 = r2[r4]
            r8.append(r2)
            java.lang.String r2 = ")p; }"
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r7.println(r2)
            goto L_0x201f
        L_0x2075:
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "static const char* JavaCPP_members["
            r2.append(r4)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r4 = r1.jclasses
            int r4 = r4.size()
            r2.append(r4)
            java.lang.String r4 = "]["
            r2.append(r4)
            r4 = 1
            int r3 = r3 + r4
            r2.append(r3)
            java.lang.String r4 = "] = {"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.jclasses
            java.util.Iterator r0 = r0.iterator()
        L_0x20ab:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2133
            java.io.PrintWriter r2 = r1.out
            java.lang.String r4 = "        { "
            r2.print(r4)
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r2 = r1.members
            java.lang.Object r4 = r0.next()
            java.lang.Object r2 = r2.get(r4)
            java.util.Set r2 = (java.util.Set) r2
            if (r2 != 0) goto L_0x20c8
            r2 = 0
            goto L_0x20cc
        L_0x20c8:
            java.util.Iterator r2 = r2.iterator()
        L_0x20cc:
            if (r2 == 0) goto L_0x210d
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x20d5
            goto L_0x210d
        L_0x20d5:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x210a
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = r22
            r7.append(r8)
            java.lang.Object r9 = r2.next()
            java.lang.String r9 = (java.lang.String) r9
            r7.append(r9)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4.print(r7)
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x2107
            java.io.PrintWriter r4 = r1.out
            java.lang.String r7 = ", "
            r4.print(r7)
        L_0x2107:
            r22 = r8
            goto L_0x20d5
        L_0x210a:
            r8 = r22
            goto L_0x2116
        L_0x210d:
            r8 = r22
            java.io.PrintWriter r2 = r1.out
            java.lang.String r4 = "NULL"
            r2.print(r4)
        L_0x2116:
            java.io.PrintWriter r2 = r1.out
            java.lang.String r4 = " }"
            r2.print(r4)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x212b
            java.io.PrintWriter r2 = r1.out
            r4 = r21
            r2.println(r4)
            goto L_0x212d
        L_0x212b:
            r4 = r21
        L_0x212d:
            r21 = r4
            r22 = r8
            goto L_0x20ab
        L_0x2133:
            r4 = r21
            r8 = r22
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = " };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "static int JavaCPP_offsets["
            r2.append(r7)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r7 = r1.jclasses
            int r7 = r7.size()
            r2.append(r7)
            java.lang.String r7 = "]["
            r2.append(r7)
            r2.append(r3)
            java.lang.String r3 = "] = {"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.jclasses
            java.util.Iterator r0 = r0.iterator()
        L_0x216d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2227
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = "        { "
            r2.print(r3)
            java.lang.Object r2 = r0.next()
            java.lang.Class r2 = (java.lang.Class) r2
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r3 = r1.members
            java.lang.Object r3 = r3.get(r2)
            java.util.Set r3 = (java.util.Set) r3
            if (r3 != 0) goto L_0x218c
            r3 = 0
            goto L_0x2190
        L_0x218c:
            java.util.Iterator r3 = r3.iterator()
        L_0x2190:
            if (r3 == 0) goto L_0x220c
            boolean r7 = r3.hasNext()
            if (r7 != 0) goto L_0x2199
            goto L_0x220c
        L_0x2199:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x2213
            java.lang.String[] r7 = r1.cppTypeName(r2)
            java.lang.String r7 = valueTypeName(r7)
            java.lang.Object r9 = r3.next()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r10 = "sizeof"
            boolean r10 = r10.equals(r9)
            if (r10 == 0) goto L_0x21db
            java.lang.String r9 = "void"
            boolean r9 = r9.equals(r7)
            if (r9 == 0) goto L_0x21bf
            java.lang.String r7 = "void*"
        L_0x21bf:
            java.io.PrintWriter r9 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "sizeof("
            r10.append(r11)
            r10.append(r7)
            java.lang.String r7 = ")"
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            r9.print(r7)
            goto L_0x21fe
        L_0x21db:
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = "offsetof("
            r11.append(r13)
            r11.append(r7)
            java.lang.String r7 = ", "
            r11.append(r7)
            r11.append(r9)
            java.lang.String r7 = ")"
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r10.print(r7)
        L_0x21fe:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x2199
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = ", "
            r7.print(r9)
            goto L_0x2199
        L_0x220c:
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = "-1"
            r2.print(r3)
        L_0x2213:
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = " }"
            r2.print(r3)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x216d
            java.io.PrintWriter r2 = r1.out
            r2.println(r4)
            goto L_0x216d
        L_0x2227:
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = " };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "static int JavaCPP_memberOffsetSizes["
            r2.append(r3)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r3 = r1.jclasses
            int r3 = r3.size()
            r2.append(r3)
            java.lang.String r3 = "] = { "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.print(r2)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.jclasses
            java.util.Iterator r0 = r0.iterator()
        L_0x2255:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2282
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r2 = r1.members
            java.lang.Object r3 = r0.next()
            java.lang.Object r2 = r2.get(r3)
            java.util.Set r2 = (java.util.Set) r2
            java.io.PrintWriter r3 = r1.out
            if (r2 != 0) goto L_0x226d
            r2 = 1
            goto L_0x2271
        L_0x226d:
            int r2 = r2.size()
        L_0x2271:
            r3.print(r2)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x2255
            java.io.PrintWriter r2 = r1.out
            java.lang.String r3 = ", "
            r2.print(r3)
            goto L_0x2255
        L_0x2282:
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = " };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "extern \"C\" {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out2
            if (r0 == 0) goto L_0x239a
            r0.println()
            java.io.PrintWriter r0 = r1.out2
            java.lang.String r2 = "#ifdef __cplusplus"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out2
            java.lang.String r2 = "extern \"C\" {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out2
            r0.println(r6)
            java.io.PrintWriter r0 = r1.out2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "JNIIMPORT int JavaCPP_init"
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = "(int argc, const char *argv[]);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "JNIEXPORT int JavaCPP_init"
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = "(int argc, const char *argv[]) {"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "#if defined(__ANDROID__) || TARGET_OS_IPHONE"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    return JNI_OK;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r12)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    if (JavaCPP_vm != NULL) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "        return JNI_OK;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r5)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    int err;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    JavaVM *vm;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    JNIEnv *env;"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    int nOptions = 1 + (argc > 255 ? 255 : argc);"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    JavaVMOption options[256] = { { NULL } };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "    options[0].optionString = (char*)\"-Djava.class.path="
            r2.append(r3)
            r3 = 92
            r7 = r32
            r9 = 47
            java.lang.String r3 = r7.replace(r3, r9)
            r2.append(r3)
            java.lang.String r3 = "\";"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    for (int i = 1; i < nOptions && argv != NULL; i++) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "        options[i].optionString = (char*)argv[i - 1];"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r5)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r2 = "    JavaVMInitArgs vm_args = { JNI_VERSION_1_6, nOptions, options };"
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "    return (err = JNI_CreateJavaVM(&vm, (void**)&env, &vm_args)) == JNI_OK && vm != NULL && (err = JNI_OnLoad"
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = "(vm, NULL)) >= 0 ? JNI_OK : err;"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println(r6)
            java.io.PrintWriter r0 = r1.out
            r2 = r24
            r0.println(r2)
            goto L_0x239c
        L_0x239a:
            r2 = r24
        L_0x239c:
            r0 = r31
            if (r0 == 0) goto L_0x23e1
            boolean r3 = r31.isEmpty()
            if (r3 != 0) goto L_0x23e1
            java.io.PrintWriter r3 = r1.out
            r3.println()
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIEXPORT jint JNICALL JNI_OnLoad"
            r7.append(r9)
            r7.append(r0)
            java.lang.String r9 = "(JavaVM* vm, void* reserved);"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIEXPORT void JNICALL JNI_OnUnload"
            r7.append(r9)
            r7.append(r0)
            java.lang.String r9 = "(JavaVM* vm, void* reserved);"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
        L_0x23e1:
            java.io.PrintWriter r3 = r1.out
            r3.println()
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIEXPORT jint JNICALL JNI_OnLoad"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "(JavaVM* vm, void* reserved) {"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.lang.String r3 = "        return JNI_ERR;"
            if (r0 == 0) goto L_0x2430
            boolean r7 = r31.isEmpty()
            if (r7 != 0) goto L_0x2430
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    if (JNI_OnLoad"
            r9.append(r10)
            r9.append(r0)
            java.lang.String r10 = "(vm, reserved) == JNI_ERR) {"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
        L_0x2430:
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    JNIEnv* env;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "        JavaCPP_log(\"Could not get JNIEnv for JNI_VERSION_1_6 inside JNI_OnLoad"
            r9.append(r10)
            r9.append(r14)
            java.lang.String r10 = "().\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_vm == vm) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "        return env->GetVersion();"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    JavaCPP_vm = vm;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    JavaCPP_haveAllocObject = env->functions->AllocObject != NULL;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    JavaCPP_haveNonvirtual = env->functions->CallNonvirtualVoidMethodA != NULL;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    jmethodID putMemberOffsetMID = JavaCPP_getStaticMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Loader> r11 = org.bytedeco.javacpp.Loader.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"putMemberOffset\", \"(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/Class;\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (putMemberOffsetMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    for (int i = 0; i < "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            int r10 = r10.size()
            r9.append(r10)
            java.lang.String r10 = " && !env->ExceptionCheck(); i++) {"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "        for (int j = 0; j < JavaCPP_memberOffsetSizes[i] && !env->ExceptionCheck(); j++) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "            if (env->PushLocalFrame(3) == 0) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                jvalue args[3];"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                args[0].l = env->NewStringUTF(JavaCPP_classNames[i]);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                args[1].l = JavaCPP_members[i][j] == NULL ? NULL : env->NewStringUTF(JavaCPP_members[i][j]);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                args[2].i = JavaCPP_offsets[i][j];"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "                jclass cls = (jclass)env->CallStaticObjectMethodA(JavaCPP_getClass(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Loader> r11 = org.bytedeco.javacpp.Loader.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = "), putMemberOffsetMID, args);"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                if (env->ExceptionCheck()) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                    JavaCPP_log(\"Error putting member offsets for class %s.\", JavaCPP_classNames[i]);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                    return JNI_ERR;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                }"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                JavaCPP_classes[i] = cls == NULL ? NULL : (jclass)env->NewWeakGlobalRef(cls);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                if (env->ExceptionCheck()) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                    JavaCPP_log(\"Error creating global reference of class %s.\", JavaCPP_classNames[i]);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                    return JNI_ERR;"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                }"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "                env->PopLocalFrame(NULL);"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r9 = r17
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r9 = r23
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_addressFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"address\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_addressFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_positionFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"position\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_positionFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_limitFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"limit\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_limitFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_capacityFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"capacity\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_capacityFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_deallocatorFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"deallocator\", \""
            r9.append(r10)
            r10 = 1
            java.lang.Class[] r11 = new java.lang.Class[r10]
            r10 = 0
            r11[r10] = r26
            java.lang.String r10 = signature(r11)
            r9.append(r10)
            java.lang.String r10 = "\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_deallocatorFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_ownerAddressFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            r11 = r28
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"ownerAddress\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_ownerAddressFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            if (r29 == 0) goto L_0x27f4
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_booleanValueFID = JavaCPP_getFieldID(env, \""
            r9.append(r10)
            java.lang.Class<org.bytedeco.javacpp.tools.Generator$BooleanEnum> r10 = org.bytedeco.javacpp.tools.Generator.BooleanEnum.class
            java.lang.String r10 = r10.getName()
            r11 = 46
            r13 = 47
            java.lang.String r10 = r10.replace(r11, r13)
            r9.append(r10)
            java.lang.String r10 = "\", \"value\", \"Z\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_booleanValueFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_byteValueFID = JavaCPP_getFieldID(env, \""
            r9.append(r10)
            java.lang.Class<org.bytedeco.javacpp.tools.Generator$ByteEnum> r10 = org.bytedeco.javacpp.tools.Generator.ByteEnum.class
            java.lang.String r10 = r10.getName()
            r11 = 46
            r13 = 47
            java.lang.String r10 = r10.replace(r11, r13)
            r9.append(r10)
            java.lang.String r10 = "\", \"value\", \"B\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_byteValueFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_shortValueFID = JavaCPP_getFieldID(env, \""
            r9.append(r10)
            java.lang.Class<org.bytedeco.javacpp.tools.Generator$ShortEnum> r10 = org.bytedeco.javacpp.tools.Generator.ShortEnum.class
            java.lang.String r10 = r10.getName()
            r11 = 46
            r13 = 47
            java.lang.String r10 = r10.replace(r11, r13)
            r9.append(r10)
            java.lang.String r10 = "\", \"value\", \"S\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_shortValueFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_intValueFID = JavaCPP_getFieldID(env, \""
            r9.append(r10)
            java.lang.Class<org.bytedeco.javacpp.tools.Generator$IntEnum> r10 = org.bytedeco.javacpp.tools.Generator.IntEnum.class
            java.lang.String r10 = r10.getName()
            r11 = 46
            r13 = 47
            java.lang.String r10 = r10.replace(r11, r13)
            r9.append(r10)
            java.lang.String r10 = "\", \"value\", \"I\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_intValueFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_longValueFID = JavaCPP_getFieldID(env, \""
            r9.append(r10)
            java.lang.Class<org.bytedeco.javacpp.tools.Generator$LongEnum> r10 = org.bytedeco.javacpp.tools.Generator.LongEnum.class
            java.lang.String r10 = r10.getName()
            r11 = 46
            r13 = 47
            java.lang.String r10 = r10.replace(r11, r13)
            r9.append(r10)
            java.lang.String r10 = "\", \"value\", \"J\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_longValueFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
        L_0x27f4:
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_initMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<org.bytedeco.javacpp.Pointer> r11 = org.bytedeco.javacpp.Pointer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"init\", \"(JJJJ)V\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_initMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_arrayMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.nio.Buffer> r11 = java.nio.Buffer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"array\", \"()Ljava/lang/Object;\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_arrayMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_arrayOffsetMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.nio.Buffer> r11 = java.nio.Buffer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"arrayOffset\", \"()I\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_arrayOffsetMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_bufferPositionFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.nio.Buffer> r11 = java.nio.Buffer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"position\", \"I\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_bufferPositionFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_bufferLimitFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.nio.Buffer> r11 = java.nio.Buffer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"limit\", \"I\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_bufferLimitFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_bufferCapacityFID = JavaCPP_getFieldID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.nio.Buffer> r11 = java.nio.Buffer.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"capacity\", \"I\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_bufferCapacityFID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_stringMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"<init>\", \"([B)V\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_stringMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_getBytesMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"getBytes\", \"()[B\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_getBytesMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r7 = r1.out
            r7.println(r5)
            java.io.PrintWriter r7 = r1.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "    JavaCPP_toStringMID = JavaCPP_getMethodID(env, "
            r9.append(r10)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r10 = r1.jclasses
            java.lang.Class<java.lang.Object> r11 = java.lang.Object.class
            int r10 = r10.index(r11)
            r9.append(r10)
            java.lang.String r10 = ", \"toString\", \"()Ljava/lang/String;\");"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            java.lang.String r9 = "    if (JavaCPP_toStringMID == NULL) {"
            r7.println(r9)
            java.io.PrintWriter r7 = r1.out
            r7.println(r3)
            java.io.PrintWriter r3 = r1.out
            r3.println(r5)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    return env->GetVersion();"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            r3.println(r2)
            java.io.PrintWriter r3 = r1.out
            r3.println()
            java.io.PrintWriter r3 = r1.out2
            if (r3 == 0) goto L_0x2a5c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIIMPORT int JavaCPP_uninit"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "();"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out2
            r3.println()
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIEXPORT int JavaCPP_uninit"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "() {"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "#if defined(__ANDROID__) || TARGET_OS_IPHONE"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    return JNI_OK;"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            r3.println(r12)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    JavaVM *vm = JavaCPP_vm;"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "    JNI_OnUnload"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "(JavaCPP_vm, NULL);"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    return vm->DestroyJavaVM();"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            r3.println(r6)
            java.io.PrintWriter r3 = r1.out
            r3.println(r2)
        L_0x2a5c:
            java.io.PrintWriter r3 = r1.out
            r3.println()
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "JNIEXPORT void JNICALL JNI_OnUnload"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "(JavaVM* vm, void* reserved) {"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    JNIEnv* env;"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "        JavaCPP_log(\"Could not get JNIEnv for JNI_VERSION_1_6 inside JNI_OnUnLoad"
            r7.append(r9)
            r7.append(r14)
            java.lang.String r9 = "().\");"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "        return;"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            r3.println(r5)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "    for (int i = 0; i < "
            r7.append(r9)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r9 = r1.jclasses
            int r9 = r9.size()
            r7.append(r9)
            java.lang.String r9 = "; i++) {"
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "        env->DeleteWeakGlobalRef((jweak)JavaCPP_classes[i]);"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r7 = "        JavaCPP_classes[i] = NULL;"
            r3.println(r7)
            java.io.PrintWriter r3 = r1.out
            r3.println(r5)
            if (r0 == 0) goto L_0x2b08
            boolean r3 = r31.isEmpty()
            if (r3 != 0) goto L_0x2b08
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "    JNI_OnUnload"
            r5.append(r7)
            r5.append(r0)
            java.lang.String r7 = "(vm, reserved);"
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r3.println(r5)
        L_0x2b08:
            java.io.PrintWriter r3 = r1.out
            java.lang.String r5 = "    JavaCPP_vm = NULL;"
            r3.println(r5)
            java.io.PrintWriter r3 = r1.out
            r3.println(r2)
            java.io.PrintWriter r3 = r1.out
            r3.println()
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            if (r0 == 0) goto L_0x2b2b
            boolean r0 = r31.isEmpty()
            if (r0 == 0) goto L_0x2b27
            goto L_0x2b2b
        L_0x2b27:
            r5 = r33
            r0 = 0
            goto L_0x2b33
        L_0x2b2b:
            java.util.List<java.lang.Class> r0 = baseClasses
            r3.addAll(r0)
            r5 = r33
            r0 = 1
        L_0x2b33:
            if (r5 == 0) goto L_0x2b4c
            java.util.List r7 = java.util.Arrays.asList(r33)
            r3.addAll(r7)
            int r7 = r5.length
            r9 = 0
        L_0x2b3e:
            if (r9 >= r7) goto L_0x2b4c
            r10 = r5[r9]
            java.util.Properties r11 = r1.properties
            boolean r10 = org.bytedeco.javacpp.Loader.checkPlatform(r10, r11)
            r0 = r0 | r10
            int r9 = r9 + 1
            goto L_0x2b3e
        L_0x2b4c:
            r5 = r0
            java.util.Iterator r7 = r3.iterator()
        L_0x2b51:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x2b87
            java.lang.Object r0 = r7.next()
            r9 = r0
            java.lang.Class r9 = (java.lang.Class) r9
            r1.methods(r9)     // Catch:{ NoClassDefFoundError -> 0x2b62 }
            goto L_0x2b51
        L_0x2b62:
            r0 = move-exception
            r10 = r0
            org.bytedeco.javacpp.tools.Logger r0 = r1.logger
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Could not generate code for class "
            r11.append(r12)
            java.lang.String r9 = r9.getCanonicalName()
            r11.append(r9)
            java.lang.String r9 = ": "
            r11.append(r9)
            r11.append(r10)
            java.lang.String r9 = r11.toString()
            r0.warn(r9)
            goto L_0x2b51
        L_0x2b87:
            java.io.PrintWriter r0 = r1.out
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            r0.println()
            java.io.PrintWriter r0 = r1.out2
            if (r0 == 0) goto L_0x2ba4
            java.lang.String r7 = "#ifdef __cplusplus"
            r0.println(r7)
            java.io.PrintWriter r0 = r1.out2
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out2
            r0.println(r6)
        L_0x2ba4:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.jclasses
            java.util.Set r0 = r0.keySet()
            r3.addAll(r0)
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            java.util.List<java.lang.Class> r2 = baseClasses
            r0.addAll(r2)
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r0.add(r2)
            java.lang.Class<java.nio.Buffer> r2 = java.nio.Buffer.class
            r0.add(r2)
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            r0.add(r2)
            java.lang.Class<sun.misc.Unsafe> r2 = sun.misc.Unsafe.class
            r0.add(r2)
            java.util.LinkedHashSet r2 = new java.util.LinkedHashSet
            r2.<init>(r3)
            java.util.Iterator r2 = r2.iterator()
        L_0x2bd4:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x2bea
            java.lang.Object r6 = r2.next()
            java.lang.Class r6 = (java.lang.Class) r6
        L_0x2be0:
            java.lang.Class r6 = r6.getEnclosingClass()
            if (r6 == 0) goto L_0x2bd4
            r3.add(r6)
            goto L_0x2be0
        L_0x2bea:
            java.lang.Class<sun.misc.Unsafe> r2 = sun.misc.Unsafe.class
            r3.add(r2)
            r2 = 2
            java.io.PrintWriter[] r6 = new java.io.PrintWriter[r2]
            java.io.PrintWriter r7 = r1.jniConfigOut
            r9 = 0
            r6[r9] = r7
            java.io.PrintWriter r7 = r1.reflectConfigOut
            r10 = 1
            r6[r10] = r7
        L_0x2bfc:
            if (r9 >= r2) goto L_0x2cc8
            r7 = r6[r9]
            if (r7 != 0) goto L_0x2c04
            goto L_0x2cc4
        L_0x2c04:
            java.lang.String r10 = "["
            r7.println(r10)
            java.util.Iterator r10 = r3.iterator()
            r11 = r18
        L_0x2c0f:
            boolean r12 = r10.hasNext()
            if (r12 == 0) goto L_0x2cbc
            java.lang.Object r12 = r10.next()
            java.lang.Class r12 = (java.lang.Class) r12
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r11)
            java.lang.String r11 = "  {"
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            r7.println(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = "    \"name\" : \""
            r11.append(r13)
            java.lang.String r13 = r12.getName()
            r11.append(r13)
            r11.append(r8)
            java.lang.String r11 = r11.toString()
            r7.print(r11)
            boolean r11 = r0.contains(r12)
            if (r11 != 0) goto L_0x2c71
            java.lang.Class r11 = r12.getEnclosingClass()
            boolean r11 = r0.contains(r11)
            if (r11 == 0) goto L_0x2c5b
            goto L_0x2c71
        L_0x2c5b:
            java.lang.Class<org.bytedeco.javacpp.LoadEnabled> r11 = org.bytedeco.javacpp.LoadEnabled.class
            boolean r11 = r11.isAssignableFrom(r12)
            if (r11 == 0) goto L_0x2c9c
            r7.println(r4)
            java.lang.String r11 = "    \"allDeclaredConstructors\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allPublicConstructors\" : true"
            r7.print(r11)
            goto L_0x2c9c
        L_0x2c71:
            r7.println(r4)
            java.lang.String r11 = "    \"allDeclaredConstructors\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allPublicConstructors\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allDeclaredMethods\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allPublicMethods\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allDeclaredFields\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allPublicFields\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allDeclaredClasses\" : true,"
            r7.println(r11)
            java.lang.String r11 = "    \"allPublicClasses\" : true"
            r7.print(r11)
        L_0x2c9c:
            r7.println()
            java.lang.String r11 = "  }"
            r7.print(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r4)
            java.lang.String r13 = java.lang.System.lineSeparator()
            r11.append(r13)
            java.lang.String r11 = r11.toString()
            r12.getEnclosingClass()
            goto L_0x2c0f
        L_0x2cbc:
            r7.println()
            java.lang.String r10 = "]"
            r7.println(r10)
        L_0x2cc4:
            int r9 = r9 + 1
            goto L_0x2bfc
        L_0x2cc8:
            return r5
        L_0x2cc9:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.classes(boolean, boolean, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Class[]):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Class<? super ?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Class<? super ?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v56, resolved type: java.lang.Class<? super ?>} */
    /* JADX WARNING: type inference failed for: r6v22, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r6v27, types: [java.lang.Object[]] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0441  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0443  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x049d  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x04b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean methods(java.lang.Class<?> r25) {
        /*
            r24 = this;
            r7 = r24
            r8 = r25
            java.util.Properties r0 = r7.properties
            boolean r0 = org.bytedeco.javacpp.Loader.checkPlatform(r8, r0)
            r9 = 0
            if (r0 != 0) goto L_0x000e
            return r9
        L_0x000e:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r0 = r7.members
            java.lang.Object r0 = r0.get(r8)
            java.util.Set r0 = (java.util.Set) r0
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r1 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r1 = r8.isAnnotationPresent(r1)
            if (r1 != 0) goto L_0x004a
            java.lang.Class<org.bytedeco.javacpp.Loader> r1 = org.bytedeco.javacpp.Loader.class
            if (r8 == r1) goto L_0x004a
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r1 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r1 = r1.isAssignableFrom(r8)
            if (r1 != 0) goto L_0x004a
            java.lang.Class r1 = r25.getEnclosingClass()
            java.lang.Class<org.bytedeco.javacpp.Pointer> r2 = org.bytedeco.javacpp.Pointer.class
            if (r1 == r2) goto L_0x004a
            if (r0 != 0) goto L_0x003f
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r0 = r7.members
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.put(r8, r1)
            r0 = r1
        L_0x003f:
            java.lang.String r1 = "sizeof"
            boolean r2 = r0.contains(r1)
            if (r2 != 0) goto L_0x004a
            r0.add(r1)
        L_0x004a:
            r10 = r0
            java.lang.Class[] r0 = r25.getDeclaredClasses()
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x0052:
            if (r2 >= r1) goto L_0x0072
            r4 = r0[r2]
            java.lang.Class<org.bytedeco.javacpp.Pointer> r5 = org.bytedeco.javacpp.Pointer.class
            boolean r5 = r5.isAssignableFrom(r4)
            if (r5 != 0) goto L_0x006a
            java.lang.Class<org.bytedeco.javacpp.Pointer> r5 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class r6 = r4.getEnclosingClass()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x006f
        L_0x006a:
            boolean r4 = r7.methods(r4)
            r3 = r3 | r4
        L_0x006f:
            int r2 = r2 + 1
            goto L_0x0052
        L_0x0072:
            java.lang.reflect.Method[] r0 = r25.getDeclaredMethods()
            int r1 = r0.length
            org.bytedeco.javacpp.tools.MethodInformation[] r1 = new org.bytedeco.javacpp.tools.MethodInformation[r1]
            r2 = 0
        L_0x007a:
            int r4 = r0.length
            if (r2 >= r4) goto L_0x0088
            r4 = r0[r2]
            org.bytedeco.javacpp.tools.MethodInformation r4 = r7.methodInformation(r4)
            r1[r2] = r4
            int r2 = r2 + 1
            goto L_0x007a
        L_0x0088:
            java.lang.Class r2 = r25.getSuperclass()
            r11 = r0
            r12 = r1
        L_0x008e:
            if (r2 == 0) goto L_0x0108
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            if (r2 == r0) goto L_0x0108
            int r0 = r25.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isAbstract(r0)
            if (r0 != 0) goto L_0x0108
            java.lang.reflect.Method[] r0 = r2.getDeclaredMethods()
            int r1 = r0.length
            r4 = 0
        L_0x00a4:
            if (r4 >= r1) goto L_0x0102
            r5 = r0[r4]
            java.lang.Class<org.bytedeco.javacpp.annotation.Virtual> r6 = org.bytedeco.javacpp.annotation.Virtual.class
            boolean r6 = r5.isAnnotationPresent(r6)
            if (r6 == 0) goto L_0x00fe
            java.lang.String r6 = r5.getName()
            java.lang.Class[] r14 = r5.getParameterTypes()
            int r15 = r11.length
        L_0x00b9:
            if (r9 >= r15) goto L_0x00d6
            r17 = r11[r9]
            java.lang.String r13 = r17.getName()
            boolean r13 = r6.equals(r13)
            if (r13 == 0) goto L_0x00d3
            java.lang.Class[] r13 = r17.getParameterTypes()
            boolean r13 = java.util.Arrays.equals(r14, r13)
            if (r13 == 0) goto L_0x00d3
            r6 = 1
            goto L_0x00d7
        L_0x00d3:
            int r9 = r9 + 1
            goto L_0x00b9
        L_0x00d6:
            r6 = 0
        L_0x00d7:
            if (r6 != 0) goto L_0x00fe
            int r6 = r11.length
            r9 = 1
            int r6 = r6 + r9
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r11, r6)
            r11 = r6
            java.lang.reflect.Method[] r11 = (java.lang.reflect.Method[]) r11
            int r6 = r11.length
            int r6 = r6 - r9
            r11[r6] = r5
            int r6 = r12.length
            int r6 = r6 + r9
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r12, r6)
            r12 = r6
            org.bytedeco.javacpp.tools.MethodInformation[] r12 = (org.bytedeco.javacpp.tools.MethodInformation[]) r12
            int r6 = r11.length
            int r6 = r6 - r9
            org.bytedeco.javacpp.tools.MethodInformation r5 = r7.methodInformation(r5)
            r12[r6] = r5
            int r5 = r11.length
            int r5 = r5 - r9
            r5 = r12[r5]
            r5.cls = r8
        L_0x00fe:
            int r4 = r4 + 1
            r9 = 0
            goto L_0x00a4
        L_0x0102:
            java.lang.Class r2 = r2.getSuperclass()
            r9 = 0
            goto L_0x008e
        L_0x0108:
            int r0 = r11.length
            boolean[] r9 = new boolean[r0]
            java.lang.reflect.Method[] r13 = functionMethods(r8, r9)
            r0 = 1
            r14 = 0
        L_0x0111:
            int r1 = r11.length
            if (r14 >= r1) goto L_0x0520
            r1 = r11[r14]
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r2 = org.bytedeco.javacpp.annotation.Platform.class
            java.lang.annotation.Annotation r1 = r1.getAnnotation(r2)
            org.bytedeco.javacpp.annotation.Platform r1 = (org.bytedeco.javacpp.annotation.Platform) r1
            java.util.Properties r2 = r7.properties
            r4 = 0
            java.lang.String[] r5 = new java.lang.String[r4]
            boolean r1 = org.bytedeco.javacpp.Loader.checkPlatform(r1, r2, r5)
            if (r1 != 0) goto L_0x012a
            goto L_0x0198
        L_0x012a:
            r15 = r12[r14]
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r25.getName()
            java.lang.String r2 = mangle(r2)
            r1.append(r2)
            java.lang.String r2 = "_"
            r1.append(r2)
            r2 = r11[r14]
            java.lang.String r2 = r2.getName()
            java.lang.String r2 = mangle(r2)
            r1.append(r2)
            java.lang.String r6 = r1.toString()
            boolean r1 = r9[r14]
            if (r1 == 0) goto L_0x015d
            java.lang.Class<?>[] r1 = r15.parameterTypes
            int r1 = r1.length
            if (r1 <= 0) goto L_0x015d
            r1 = 0
            goto L_0x0173
        L_0x015d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JavaCPP_"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = "_callback"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0173:
            boolean r2 = r9[r14]
            if (r2 == 0) goto L_0x01a0
            if (r13 != 0) goto L_0x01a0
            org.bytedeco.javacpp.tools.Logger r1 = r7.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "No callback method call() or apply() has been not declared in \""
            r2.append(r4)
            java.lang.String r4 = r25.getCanonicalName()
            r2.append(r4)
            java.lang.String r4 = "\". No code will be generated for callback allocator."
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.warn(r2)
        L_0x0198:
            r23 = r12
            r20 = r13
        L_0x019c:
            r2 = 0
            r5 = 1
            goto L_0x0518
        L_0x01a0:
            if (r13 == 0) goto L_0x0227
            int r5 = r13.length
            r17 = r0
            r4 = 0
        L_0x01a6:
            if (r4 >= r5) goto L_0x0223
            r2 = r13[r4]
            if (r2 == 0) goto L_0x01b0
            boolean r0 = r9[r14]
            if (r0 != 0) goto L_0x01c4
        L_0x01b0:
            r0 = r11[r14]
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0214
            r0 = r11[r14]
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isNative(r0)
            if (r0 != 0) goto L_0x0214
        L_0x01c4:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r7.functions
            r0.index(r8)
            r0 = r11[r14]
            java.lang.Class<org.bytedeco.javacpp.annotation.Name> r3 = org.bytedeco.javacpp.annotation.Name.class
            java.lang.annotation.Annotation r0 = r0.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Name r0 = (org.bytedeco.javacpp.annotation.Name) r0
            if (r0 == 0) goto L_0x01f0
            java.lang.String[] r3 = r0.value()
            int r3 = r3.length
            if (r3 <= 0) goto L_0x01f0
            java.lang.String[] r3 = r0.value()
            r16 = 0
            r3 = r3[r16]
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x01f0
            java.lang.String[] r0 = r0.value()
            r1 = r0[r16]
        L_0x01f0:
            r19 = r1
            int r3 = r15.allocatorMax
            r20 = 0
            r0 = r24
            r1 = r25
            r21 = r3
            r3 = r19
            r22 = r4
            r4 = r21
            r21 = r5
            r5 = r17
            r23 = r12
            r12 = r6
            r6 = r20
            r0.callback(r1, r2, r3, r4, r5, r6)
            r1 = r19
            r3 = 1
            r17 = 0
            goto L_0x021b
        L_0x0214:
            r22 = r4
            r21 = r5
            r23 = r12
            r12 = r6
        L_0x021b:
            int r4 = r22 + 1
            r6 = r12
            r5 = r21
            r12 = r23
            goto L_0x01a6
        L_0x0223:
            r23 = r12
            r12 = r6
            goto L_0x022c
        L_0x0227:
            r23 = r12
            r12 = r6
            r17 = r0
        L_0x022c:
            r6 = r1
            r19 = r3
            r0 = r11[r14]
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isNative(r0)
            if (r0 != 0) goto L_0x0247
            r0 = r11[r14]
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isAbstract(r0)
            if (r0 == 0) goto L_0x0285
        L_0x0247:
            boolean r0 = r15.valueGetter
            if (r0 != 0) goto L_0x0285
            boolean r0 = r15.valueSetter
            if (r0 != 0) goto L_0x0285
            boolean r0 = r15.memberGetter
            if (r0 != 0) goto L_0x0285
            boolean r0 = r15.memberSetter
            if (r0 != 0) goto L_0x0285
            boolean r0 = r25.isInterface()
            if (r0 != 0) goto L_0x0285
            r0 = r11[r14]
            java.lang.Class<org.bytedeco.javacpp.annotation.Virtual> r1 = org.bytedeco.javacpp.annotation.Virtual.class
            boolean r0 = r0.isAnnotationPresent(r1)
            if (r0 != 0) goto L_0x026b
            boolean r0 = r15.allocator
            if (r0 == 0) goto L_0x0285
        L_0x026b:
            r2 = r11[r14]
            java.lang.String[] r0 = r15.memberName
            r1 = 0
            r3 = r0[r1]
            int r4 = r15.allocatorMax
            boolean r0 = r15.allocator
            r1 = 1
            r5 = r0 ^ 1
            r0 = r24
            r1 = r25
            r20 = r13
            r13 = r6
            r6 = r15
            r0.callback(r1, r2, r3, r4, r5, r6)
            goto L_0x0288
        L_0x0285:
            r20 = r13
            r13 = r6
        L_0x0288:
            r0 = r11[r14]
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isNative(r0)
            if (r0 != 0) goto L_0x029a
            r0 = r17
            r3 = r19
            goto L_0x019c
        L_0x029a:
            boolean r0 = r15.memberGetter
            if (r0 != 0) goto L_0x02a2
            boolean r0 = r15.memberSetter
            if (r0 == 0) goto L_0x02c2
        L_0x02a2:
            boolean r0 = r15.noOffset
            if (r0 != 0) goto L_0x02c2
            if (r10 == 0) goto L_0x02c2
            int r0 = r15.modifiers
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 != 0) goto L_0x02c2
            java.lang.String[] r0 = r15.memberName
            r1 = 0
            r0 = r0[r1]
            boolean r0 = r10.contains(r0)
            if (r0 != 0) goto L_0x02c2
            java.lang.String[] r0 = r15.memberName
            r0 = r0[r1]
            r10.add(r0)
        L_0x02c2:
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JNIEXPORT "
            r1.append(r2)
            java.lang.Class<?> r2 = r15.returnType
            java.lang.String r2 = jniTypeName(r2)
            r1.append(r2)
            java.lang.String r2 = " JNICALL Java_"
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            r0.print(r1)
            boolean r0 = r15.overloaded
            if (r0 == 0) goto L_0x030a
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "__"
            r1.append(r2)
            java.lang.Class<?>[] r2 = r15.parameterTypes
            java.lang.String r2 = signature(r2)
            java.lang.String r2 = mangle(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.print(r1)
        L_0x030a:
            int r0 = r15.modifiers
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 == 0) goto L_0x031a
            java.io.PrintWriter r0 = r7.out
            java.lang.String r1 = "(JNIEnv* env, jclass cls"
            r0.print(r1)
            goto L_0x0321
        L_0x031a:
            java.io.PrintWriter r0 = r7.out
            java.lang.String r1 = "(JNIEnv* env, jobject obj"
            r0.print(r1)
        L_0x0321:
            r0 = 0
        L_0x0322:
            java.lang.Class<?>[] r1 = r15.parameterTypes
            int r1 = r1.length
            if (r0 >= r1) goto L_0x0350
            java.io.PrintWriter r1 = r7.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ", "
            r2.append(r3)
            java.lang.Class<?>[] r3 = r15.parameterTypes
            r3 = r3[r0]
            java.lang.String r3 = jniTypeName(r3)
            r2.append(r3)
            java.lang.String r3 = " arg"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.print(r2)
            int r0 = r0 + 1
            goto L_0x0322
        L_0x0350:
            java.io.PrintWriter r0 = r7.out
            java.lang.String r1 = ") {"
            r0.println(r1)
            boolean r0 = r9[r14]
            if (r0 == 0) goto L_0x0364
            int r0 = r15.allocatorMax
            r7.callbackAllocator(r8, r13, r0)
            r2 = 0
            r5 = 1
            goto L_0x0515
        L_0x0364:
            int r0 = r15.modifiers
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            java.lang.String r1 = "    }"
            if (r0 != 0) goto L_0x04d8
            java.lang.Class<org.bytedeco.javacpp.Pointer> r0 = org.bytedeco.javacpp.Pointer.class
            boolean r0 = r0.isAssignableFrom(r8)
            if (r0 == 0) goto L_0x04d8
            boolean r0 = r15.allocator
            if (r0 != 0) goto L_0x04d8
            boolean r0 = r15.arrayAllocator
            if (r0 != 0) goto L_0x04d8
            boolean r0 = r15.deallocator
            if (r0 != 0) goto L_0x04d8
            java.lang.String[] r0 = r24.cppTypeName(r25)
            r2 = 0
            r3 = r0[r2]
            java.lang.String r4 = "void*"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x039e
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r3 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r3 = r8.isAnnotationPresent(r3)
            if (r3 != 0) goto L_0x039e
            java.lang.String r3 = "char*"
            r0[r2] = r3
            goto L_0x03c9
        L_0x039e:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r2 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r2 = r2.isAssignableFrom(r8)
            if (r2 == 0) goto L_0x03c9
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r2 = r7.functions
            r2.index(r8)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = functionClassName(r25)
            r2.append(r3)
            java.lang.String r3 = "*"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            r0[r3] = r2
            java.lang.String r2 = ""
            r4 = 1
            r0[r4] = r2
            goto L_0x03ca
        L_0x03c9:
            r3 = 0
        L_0x03ca:
            java.io.PrintWriter r2 = r7.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "    "
            r4.append(r5)
            r5 = r0[r3]
            r4.append(r5)
            java.lang.String r5 = " ptr"
            r4.append(r5)
            r5 = 1
            r6 = r0[r5]
            r4.append(r6)
            java.lang.String r6 = " = ("
            r4.append(r6)
            r6 = r0[r3]
            r4.append(r6)
            r0 = r0[r5]
            r4.append(r0)
            java.lang.String r0 = ")jlong_to_ptr(env->GetLongField(obj, JavaCPP_addressFID));"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r2.println(r0)
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    if (ptr == NULL) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "        env->ThrowNew(JavaCPP_getClass(env, "
            r2.append(r3)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r4 = r7.jclasses
            java.lang.Class<java.lang.NullPointerException> r6 = java.lang.NullPointerException.class
            int r4 = r4.index(r6)
            r2.append(r4)
            java.lang.String r4 = "), \"This pointer address is NULL.\");"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "        return"
            r2.append(r4)
            java.lang.Class<?> r6 = r15.returnType
            java.lang.Class r12 = java.lang.Void.TYPE
            java.lang.String r13 = ";"
            java.lang.String r18 = " 0;"
            if (r6 != r12) goto L_0x0443
            r6 = r13
            goto L_0x0445
        L_0x0443:
            r6 = r18
        L_0x0445:
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            r0.println(r1)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r0 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r0 = r0.isAssignableFrom(r8)
            if (r0 == 0) goto L_0x04ae
            boolean r0 = r15.valueGetter
            if (r0 != 0) goto L_0x04ae
            boolean r0 = r15.valueSetter
            if (r0 != 0) goto L_0x04ae
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    if (ptr->ptr == NULL) {"
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r3 = r7.jclasses
            java.lang.Class<java.lang.NullPointerException> r6 = java.lang.NullPointerException.class
            int r3 = r3.index(r6)
            r2.append(r3)
            java.lang.String r3 = "), \"This function pointer address is NULL.\");"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.Class<?> r3 = r15.returnType
            java.lang.Class r4 = java.lang.Void.TYPE
            if (r3 != r4) goto L_0x049d
            goto L_0x049f
        L_0x049d:
            r13 = r18
        L_0x049f:
            r2.append(r13)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            r0.println(r1)
        L_0x04ae:
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r0 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r0 = r8.isAnnotationPresent(r0)
            if (r0 != 0) goto L_0x04d9
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    jlong position = env->GetLongField(obj, JavaCPP_positionFID);"
            r0.println(r2)
            boolean r0 = r15.bufferGetter
            if (r0 == 0) goto L_0x04d0
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    jlong limit = env->GetLongField(obj, JavaCPP_limitFID);"
            r0.println(r2)
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    jlong capacity = env->GetLongField(obj, JavaCPP_capacityFID);"
            r0.println(r2)
            goto L_0x04d9
        L_0x04d0:
            java.io.PrintWriter r0 = r7.out
            java.lang.String r2 = "    ptr += position;"
            r0.println(r2)
            goto L_0x04d9
        L_0x04d8:
            r5 = 1
        L_0x04d9:
            r7.parametersBefore(r15)
            java.lang.String r0 = r7.returnBefore(r15)
            r2 = 0
            r7.call(r15, r0, r2)
            r7.returnAfter(r15)
            r7.parametersAfter(r15)
            java.lang.Class<?> r0 = r15.throwsException
            if (r0 == 0) goto L_0x0501
            java.io.PrintWriter r0 = r7.out
            java.lang.String r3 = "    if (exc != NULL) {"
            r0.println(r3)
            java.io.PrintWriter r0 = r7.out
            java.lang.String r3 = "        env->Throw(exc);"
            r0.println(r3)
            java.io.PrintWriter r0 = r7.out
            r0.println(r1)
        L_0x0501:
            java.lang.Class<?> r0 = r15.returnType
            java.lang.Class r1 = java.lang.Void.TYPE
            if (r0 == r1) goto L_0x050e
            java.io.PrintWriter r0 = r7.out
            java.lang.String r1 = "    return rarg;"
            r0.println(r1)
        L_0x050e:
            java.io.PrintWriter r0 = r7.out
            java.lang.String r1 = "}"
            r0.println(r1)
        L_0x0515:
            r0 = r17
            r3 = 1
        L_0x0518:
            int r14 = r14 + 1
            r13 = r20
            r12 = r23
            goto L_0x0111
        L_0x0520:
            java.io.PrintWriter r0 = r7.out
            r0.println()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.methods(java.lang.Class):boolean");
    }

    /* access modifiers changed from: package-private */
    public void parametersBefore(MethodInformation methodInformation) {
        String str;
        AdapterInformation adapterInformation;
        String str2;
        String[] strArr;
        AdapterInformation adapterInformation2;
        String str3;
        String str4;
        char c;
        String str5;
        AdapterInformation adapterInformation3;
        String str6;
        MethodInformation methodInformation2 = methodInformation;
        boolean z = false;
        int i = (methodInformation2.parameterTypes.length <= 0 || methodInformation2.parameterTypes[0] != Class.class) ? 0 : 1;
        String str7 = "";
        String str8 = str7;
        AdapterInformation adapterInformation4 = null;
        while (i < methodInformation2.parameterTypes.length) {
            if (!methodInformation2.parameterTypes[i].isPrimitive()) {
                Annotation by = by(methodInformation2, i);
                String cast = cast(methodInformation2, i);
                if (methodInformation2.parameterRaw[i]) {
                    strArr = new String[]{str7};
                } else {
                    strArr = cppTypeName(methodInformation2.parameterTypes[i]);
                }
                if (methodInformation2.parameterRaw[i]) {
                    adapterInformation2 = null;
                } else {
                    adapterInformation2 = adapterInformation(z, methodInformation2, i);
                }
                str2 = str8;
                String str9 = " 0;";
                String str10 = cast;
                adapterInformation = adapterInformation4;
                if (Enum.class.isAssignableFrom(methodInformation2.parameterTypes[i])) {
                    this.accessesEnums = true;
                    String enumValueType = enumValueType(methodInformation2.parameterTypes[i]);
                    if (enumValueType != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Character.toUpperCase(enumValueType.charAt(0)));
                        String str11 = ";";
                        sb.append(enumValueType.substring(1));
                        String sb2 = sb.toString();
                        this.out.println("    if (arg" + i + " == NULL) {");
                        this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"Enum for argument " + i + " is NULL.\");");
                        PrintWriter printWriter = this.out;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("        return");
                        sb3.append(methodInformation2.returnType == Void.TYPE ? str11 : str9);
                        printWriter.println(sb3.toString());
                        this.out.println("    }");
                        this.out.println("    " + strArr[0] + " val" + i + strArr[1] + " = (" + strArr[0] + strArr[1] + ")env->Get" + sb2 + "Field(arg" + i + ", JavaCPP_" + enumValueType + "ValueFID);");
                    }
                    str = str7;
                } else {
                    String str12 = ";";
                    if (FunctionPointer.class.isAssignableFrom(methodInformation2.parameterTypes[i])) {
                        str4 = "    if (arg";
                        this.functions.index(methodInformation2.parameterTypes[i]);
                        if (methodInformation2.parameterTypes[i] == FunctionPointer.class) {
                            Logger logger2 = this.logger;
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("Method \"");
                            str3 = "Method \"";
                            sb4.append(methodInformation2.method);
                            sb4.append("\" has an abstract FunctionPointer parameter, but a concrete subclass is required. Compilation will most likely fail.");
                            logger2.warn(sb4.toString());
                        } else {
                            str3 = "Method \"";
                        }
                        c = 0;
                        strArr[0] = functionClassName(methodInformation2.parameterTypes[i]) + Marker.ANY_MARKER;
                        strArr[1] = str7;
                    } else {
                        str4 = "    if (arg";
                        str3 = "Method \"";
                        c = 0;
                    }
                    if (strArr[c].length() == 0 || methodInformation2.parameterRaw[i]) {
                        str = str7;
                        methodInformation2.parameterRaw[i] = true;
                        strArr[0] = jniTypeName(methodInformation2.parameterTypes[i]);
                        this.out.println("    " + strArr[0] + " ptr" + i + " = arg" + i + str12);
                        str8 = str2;
                        i++;
                        adapterInformation4 = adapterInformation;
                        str7 = str;
                        z = false;
                    } else {
                        str = str7;
                        if ("void*".equals(strArr[0]) && !methodInformation2.parameterTypes[i].isAnnotationPresent(Opaque.class)) {
                            strArr[0] = "char*";
                        }
                        PrintWriter printWriter2 = this.out;
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("    ");
                        String str13 = "    ";
                        sb5.append(strArr[0]);
                        sb5.append(" ptr");
                        sb5.append(i);
                        sb5.append(strArr[1]);
                        sb5.append(" = ");
                        printWriter2.print(sb5.toString());
                        String str14 = "    void* owner";
                        String str15 = " = arg";
                        if (Pointer.class.isAssignableFrom(methodInformation2.parameterTypes[i])) {
                            String str16 = ");";
                            this.out.println("arg" + i + " == NULL ? NULL : (" + strArr[0] + strArr[1] + ")jlong_to_ptr(env->GetLongField(arg" + i + ", JavaCPP_addressFID));");
                            if ((i == 0 && FunctionPointer.class.isAssignableFrom(methodInformation2.cls) && methodInformation2.cls.isAnnotationPresent(Namespace.class)) || (((by instanceof ByVal) && ((ByVal) by).nullValue().length() == 0) || ((by instanceof ByRef) && ((ByRef) by).nullValue().length() == 0))) {
                                this.out.println("    if (ptr" + i + " == NULL) {");
                                this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"Pointer address of argument " + i + " is NULL.\");");
                                PrintWriter printWriter3 = this.out;
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("        return");
                                sb6.append(methodInformation2.returnType == Void.TYPE ? str12 : str9);
                                printWriter3.println(sb6.toString());
                                this.out.println("    }");
                            }
                            if (adapterInformation2 == null && adapterInformation == null) {
                                str6 = str15;
                                str5 = str16;
                            } else {
                                PrintWriter printWriter4 = this.out;
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("    jlong size");
                                sb7.append(i);
                                str6 = str15;
                                sb7.append(str6);
                                sb7.append(i);
                                sb7.append(" == NULL ? 0 : env->GetLongField(arg");
                                sb7.append(i);
                                sb7.append(", JavaCPP_limitFID);");
                                printWriter4.println(sb7.toString());
                                PrintWriter printWriter5 = this.out;
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append(str14);
                                sb8.append(i);
                                sb8.append(" = JavaCPP_getPointerOwner(env, arg");
                                sb8.append(i);
                                str5 = str16;
                                sb8.append(str5);
                                printWriter5.println(sb8.toString());
                            }
                            if (!methodInformation2.parameterTypes[i].isAnnotationPresent(Opaque.class)) {
                                this.out.println("    jlong position" + i + str6 + i + " == NULL ? 0 : env->GetLongField(arg" + i + ", JavaCPP_positionFID);");
                                PrintWriter printWriter6 = this.out;
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append("    ptr");
                                sb9.append(i);
                                sb9.append(" += position");
                                sb9.append(i);
                                String str17 = str12;
                                sb9.append(str17);
                                printWriter6.println(sb9.toString());
                                if (!(adapterInformation2 == null && adapterInformation == null)) {
                                    this.out.println("    size" + i + " -= position" + i + str17);
                                }
                            }
                        } else {
                            str5 = ");";
                            String str18 = str12;
                            String str19 = str14;
                            String str20 = str15;
                            String str21 = "    }";
                            if (methodInformation2.parameterTypes[i] == String.class) {
                                this.passesStrings = true;
                                this.out.println("JavaCPP_getStringBytes(env, arg" + i + str5);
                                if (!(adapterInformation2 == null && adapterInformation == null)) {
                                    this.out.println("    jlong size" + i + " = 0;");
                                    this.out.println(str19 + i + " = (void*)ptr" + i + str18);
                                }
                            } else if (methodInformation2.parameterTypes[i].isArray() && methodInformation2.parameterTypes[i].getComponentType().isPrimitive()) {
                                this.out.print("arg" + i + " == NULL ? NULL : ");
                                String name = methodInformation2.parameterTypes[i].getComponentType().getName();
                                if (methodInformation2.criticalRegion || methodInformation2.valueGetter || methodInformation2.valueSetter || methodInformation2.memberGetter || methodInformation2.memberSetter) {
                                    this.out.println("(j" + name + "*)env->GetPrimitiveArrayCritical(arg" + i + ", NULL);");
                                } else {
                                    PrintWriter printWriter7 = this.out;
                                    printWriter7.println("env->Get" + (Character.toUpperCase(name.charAt(0)) + name.substring(1)) + "ArrayElements(arg" + i + ", NULL);");
                                }
                                if (!(adapterInformation2 == null && adapterInformation == null)) {
                                    this.out.println("    jlong size" + i + str20 + i + " == NULL ? 0 : env->GetArrayLength(arg" + i + str5);
                                    PrintWriter printWriter8 = this.out;
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append(str19);
                                    sb10.append(i);
                                    sb10.append(" = (void*)ptr");
                                    sb10.append(i);
                                    sb10.append(str18);
                                    printWriter8.println(sb10.toString());
                                }
                            } else if (Buffer.class.isAssignableFrom(methodInformation2.parameterTypes[i])) {
                                PrintWriter printWriter9 = this.out;
                                StringBuilder sb11 = new StringBuilder();
                                sb11.append("arg");
                                sb11.append(i);
                                sb11.append(" == NULL ? NULL : (");
                                String str22 = " == NULL ? NULL : (";
                                sb11.append(strArr[0]);
                                sb11.append(strArr[1]);
                                sb11.append(")env->GetDirectBufferAddress(arg");
                                sb11.append(i);
                                sb11.append(str5);
                                printWriter9.println(sb11.toString());
                                if (!(adapterInformation2 == null && adapterInformation == null)) {
                                    this.out.println("    jlong size" + i + str20 + i + " == NULL ? 0 : env->GetIntField(arg" + i + ", JavaCPP_bufferLimitFID);");
                                    PrintWriter printWriter10 = this.out;
                                    StringBuilder sb12 = new StringBuilder();
                                    sb12.append(str19);
                                    sb12.append(i);
                                    sb12.append(" = (void*)ptr");
                                    sb12.append(i);
                                    sb12.append(str18);
                                    printWriter10.println(sb12.toString());
                                }
                                if (methodInformation2.parameterTypes[i] != Buffer.class) {
                                    String simpleName = methodInformation2.parameterTypes[i].getSimpleName();
                                    String substring = simpleName.substring(0, simpleName.length() - 6);
                                    String str23 = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                                    this.out.println("    j" + str23 + "Array arr" + i + " = NULL;");
                                    PrintWriter printWriter11 = this.out;
                                    StringBuilder sb13 = new StringBuilder();
                                    sb13.append("    jlong offset");
                                    sb13.append(i);
                                    sb13.append(" = 0;");
                                    printWriter11.println(sb13.toString());
                                    this.out.println(str4 + i + " != NULL && ptr" + i + " == NULL) {");
                                    this.out.println("        arr" + i + " = (j" + str23 + "Array)env->CallObjectMethod(arg" + i + ", JavaCPP_arrayMID);");
                                    PrintWriter printWriter12 = this.out;
                                    StringBuilder sb14 = new StringBuilder();
                                    sb14.append("        offset");
                                    sb14.append(i);
                                    sb14.append(" = env->CallIntMethod(arg");
                                    sb14.append(i);
                                    sb14.append(", JavaCPP_arrayOffsetMID);");
                                    printWriter12.println(sb14.toString());
                                    this.out.println("        if (env->ExceptionOccurred() != NULL) {");
                                    this.out.println("            env->ExceptionClear();");
                                    this.out.println("        } else {");
                                    if (methodInformation2.criticalRegion) {
                                        this.out.println("            ptr" + i + " = arr" + i + str22 + strArr[0] + strArr[1] + ")env->GetPrimitiveArrayCritical(arr" + i + ", NULL) + offset" + i + str18);
                                    } else {
                                        this.out.println("            ptr" + i + " = arr" + i + " == NULL ? NULL : env->Get" + substring + "ArrayElements(arr" + i + ", NULL) + offset" + i + str18);
                                    }
                                    this.out.println("        }");
                                    this.out.println(str21);
                                }
                                this.out.println("    jlong position" + i + str20 + i + " == NULL ? 0 : env->GetIntField(arg" + i + ", JavaCPP_bufferPositionFID);");
                                PrintWriter printWriter13 = this.out;
                                StringBuilder sb15 = new StringBuilder();
                                sb15.append("    ptr");
                                sb15.append(i);
                                sb15.append(" += position");
                                sb15.append(i);
                                sb15.append(str18);
                                printWriter13.println(sb15.toString());
                                if (!(adapterInformation2 == null && adapterInformation == null)) {
                                    this.out.println("    size" + i + " -= position" + i + str18);
                                }
                            } else {
                                this.out.println("arg" + i + str18);
                                this.logger.warn(str3 + methodInformation2.method + "\" has an unsupported parameter of type \"" + methodInformation2.parameterTypes[i].getCanonicalName() + "\". Compilation will most likely fail.");
                            }
                        }
                        if (adapterInformation2 != null) {
                            this.usesAdapters = true;
                            str8 = str13 + adapterInformation2.name + " adapter" + i + "(";
                            adapterInformation3 = adapterInformation2;
                        } else {
                            str8 = str2;
                            adapterInformation3 = adapterInformation;
                        }
                        if (adapterInformation3 != null) {
                            if (!FunctionPointer.class.isAssignableFrom(methodInformation2.cls)) {
                                str8 = str8 + str10;
                            }
                            String str24 = str8 + "ptr" + i + ", size" + i + ", owner" + i;
                            int i2 = adapterInformation3.argc - 1;
                            adapterInformation3.argc = i2;
                            if (i2 > 0) {
                                str24 = str24 + ", ";
                            }
                            str8 = str24;
                        }
                        if (adapterInformation3 == null || adapterInformation3.argc > 0) {
                            adapterInformation = adapterInformation3;
                            i++;
                            adapterInformation4 = adapterInformation;
                            str7 = str;
                            z = false;
                        } else {
                            this.out.println(str8 + str5);
                            adapterInformation = null;
                            i++;
                            adapterInformation4 = adapterInformation;
                            str7 = str;
                            z = false;
                        }
                    }
                }
            } else {
                str = str7;
                adapterInformation = adapterInformation4;
                str2 = str8;
            }
            str8 = str2;
            i++;
            adapterInformation4 = adapterInformation;
            str7 = str;
            z = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x03f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String returnBefore(org.bytedeco.javacpp.tools.MethodInformation r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.lang.Class<?> r2 = r1.returnType
            java.lang.Class r3 = java.lang.Void.TYPE
            java.lang.String r4 = " rptr"
            java.lang.String r5 = ""
            java.lang.String r6 = " = "
            r7 = 1
            r8 = 0
            if (r2 != r3) goto L_0x0042
            boolean r2 = r1.allocator
            if (r2 != 0) goto L_0x001a
            boolean r2 = r1.arrayAllocator
            if (r2 == 0) goto L_0x040e
        L_0x001a:
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r2 = r0.jclasses
            java.lang.Class<?> r3 = r1.cls
            r2.index(r3)
            java.lang.Class<?> r2 = r1.cls
            java.lang.String[] r2 = r0.cppTypeName(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r5 = r2[r8]
            r3.append(r5)
            r3.append(r4)
            r2 = r2[r7]
            r3.append(r2)
            r3.append(r6)
            java.lang.String r5 = r3.toString()
            goto L_0x040e
        L_0x0042:
            java.lang.Class<?> r2 = r1.returnType
            java.lang.annotation.Annotation[] r3 = r1.annotations
            java.lang.String r2 = r0.cast((java.lang.Class<?>) r2, (java.lang.annotation.Annotation[]) r3)
            boolean r3 = r1.returnRaw
            if (r3 == 0) goto L_0x0053
            java.lang.String[] r3 = new java.lang.String[]{r5}
            goto L_0x005b
        L_0x0053:
            java.lang.Class<?> r3 = r1.returnType
            java.lang.annotation.Annotation[] r9 = r1.annotations
            java.lang.String[] r3 = r0.cppCastTypeName(r3, r9)
        L_0x005b:
            java.lang.annotation.Annotation[] r9 = r1.annotations
            java.lang.annotation.Annotation r9 = r0.by(r9)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r10 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r11 = r1.cls
            boolean r10 = r10.isAssignableFrom(r11)
            if (r10 == 0) goto L_0x007f
            java.lang.Class<?> r10 = r1.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.Namespace> r11 = org.bytedeco.javacpp.annotation.Namespace.class
            boolean r10 = r10.isAnnotationPresent(r11)
            if (r10 != 0) goto L_0x007f
            boolean r10 = r1.valueGetter
            if (r10 == 0) goto L_0x007f
            java.lang.Class<?> r3 = r1.cls
            java.lang.String[] r3 = r0.cppTypeName(r3)
        L_0x007f:
            boolean r10 = r1.valueSetter
            if (r10 != 0) goto L_0x0407
            boolean r10 = r1.memberSetter
            if (r10 != 0) goto L_0x0407
            boolean r10 = r1.noReturnGetter
            if (r10 == 0) goto L_0x008d
            goto L_0x0407
        L_0x008d:
            java.lang.Class<?> r10 = r1.returnType
            boolean r10 = r10.isPrimitive()
            java.lang.String r11 = "*"
            java.lang.String r12 = " rval"
            java.lang.String r13 = "    "
            if (r10 == 0) goto L_0x00ef
            java.io.PrintWriter r4 = r0.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r13)
            java.lang.Class<?> r10 = r1.returnType
            java.lang.String r10 = jniTypeName(r10)
            r5.append(r10)
            java.lang.String r10 = " rarg = 0;"
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = r3[r8]
            r4.append(r5)
            r4.append(r12)
            r3 = r3[r7]
            r4.append(r3)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r5 = r4.toString()
            boolean r2 = r9 instanceof org.bytedeco.javacpp.annotation.ByPtr
            if (r2 != 0) goto L_0x00de
            boolean r2 = r9 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r2 == 0) goto L_0x040e
        L_0x00de:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            r2.append(r11)
            java.lang.String r5 = r2.toString()
            goto L_0x040e
        L_0x00ef:
            java.lang.Class<java.lang.Enum> r10 = java.lang.Enum.class
            java.lang.Class<?> r14 = r1.returnType
            boolean r10 = r10.isAssignableFrom(r14)
            if (r10 == 0) goto L_0x013c
            r0.accessesEnums = r7
            java.io.PrintWriter r4 = r0.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "    jobject rarg = JavaCPP_createPointer(env, "
            r5.append(r9)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r9 = r0.jclasses
            java.lang.Class<?> r10 = r1.returnType
            int r9 = r9.index(r10)
            r5.append(r9)
            java.lang.String r9 = ");"
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = r3[r8]
            r4.append(r5)
            r4.append(r12)
            r3 = r3[r7]
            r4.append(r3)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r5 = r4.toString()
            goto L_0x040e
        L_0x013c:
            java.lang.String r10 = valueTypeName(r3)
            java.lang.annotation.Annotation[] r12 = r1.annotations
            org.bytedeco.javacpp.tools.AdapterInformation r12 = r0.adapterInformation((boolean) r8, (java.lang.String) r10, (java.lang.annotation.Annotation[]) r12)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "rptr = "
            r14.append(r15)
            r14.append(r2)
            java.lang.String r14 = r14.toString()
            r15 = r3[r8]
            int r15 = r15.length()
            java.lang.String r7 = " rarg = NULL;"
            if (r15 == 0) goto L_0x03ad
            boolean r15 = r1.returnRaw
            if (r15 == 0) goto L_0x0167
            goto L_0x03ad
        L_0x0167:
            java.lang.Class<org.bytedeco.javacpp.Pointer> r15 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r8 = r1.returnType
            boolean r8 = r15.isAssignableFrom(r8)
            if (r8 != 0) goto L_0x01f3
            java.lang.Class<java.nio.Buffer> r8 = java.nio.Buffer.class
            java.lang.Class<?> r15 = r1.returnType
            boolean r8 = r8.isAssignableFrom(r15)
            if (r8 != 0) goto L_0x01f3
            java.lang.Class<?> r8 = r1.returnType
            boolean r8 = r8.isArray()
            if (r8 == 0) goto L_0x0190
            java.lang.Class<?> r8 = r1.returnType
            java.lang.Class r8 = r8.getComponentType()
            boolean r8 = r8.isPrimitive()
            if (r8 == 0) goto L_0x0190
            goto L_0x01f3
        L_0x0190:
            java.lang.Class<?> r2 = r1.returnType
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r2 != r3) goto L_0x01c6
            java.io.PrintWriter r2 = r0.out
            java.lang.String r3 = "    jstring rarg = NULL;"
            r2.println(r3)
            java.io.PrintWriter r2 = r0.out
            java.lang.String r3 = "    const char* rptr;"
            r2.println(r3)
            boolean r2 = r9 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r2 == 0) goto L_0x01ad
            java.lang.String r2 = "std::string rstr("
        L_0x01aa:
            r5 = r2
            goto L_0x03ee
        L_0x01ad:
            boolean r2 = r9 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r2 == 0) goto L_0x01b4
            java.lang.String r2 = "rptr = NULL; const char** rptrptr = (const char**)"
            goto L_0x01aa
        L_0x01b4:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r14)
            java.lang.String r3 = "(const char*)"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            goto L_0x01aa
        L_0x01c6:
            org.bytedeco.javacpp.tools.Logger r2 = r0.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Method \""
            r3.append(r4)
            java.lang.reflect.Method r4 = r1.method
            r3.append(r4)
            java.lang.String r4 = "\" has unsupported return type \""
            r3.append(r4)
            java.lang.Class<?> r4 = r1.returnType
            java.lang.String r4 = r4.getCanonicalName()
            r3.append(r4)
            java.lang.String r4 = "\". Compilation will most likely fail."
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.warn(r3)
            goto L_0x03ed
        L_0x01f3:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r8 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r15 = r1.returnType
            boolean r8 = r8.isAssignableFrom(r15)
            java.lang.String r15 = "("
            if (r8 == 0) goto L_0x0252
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r0.functions
            java.lang.Class<?> r10 = r1.returnType
            r8.index(r10)
            java.lang.String r8 = "if (rptr != NULL) rptr->ptr = "
            java.lang.reflect.Method r10 = r1.method
            java.lang.Class<org.bytedeco.javacpp.annotation.Virtual> r14 = org.bytedeco.javacpp.annotation.Virtual.class
            boolean r10 = r10.isAnnotationPresent(r14)
            if (r10 == 0) goto L_0x0232
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            r10.append(r15)
            r8 = 0
            r14 = r3[r8]
            r10.append(r14)
            r8 = 1
            r14 = r3[r8]
            r10.append(r14)
            java.lang.String r8 = ")&"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
        L_0x0232:
            r14 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.Class<?> r10 = r1.returnType
            java.lang.String r10 = functionClassName(r10)
            r8.append(r10)
            r8.append(r11)
            java.lang.String r8 = r8.toString()
            r10 = 0
            r3[r10] = r8
            r8 = 1
            r3[r8] = r5
            java.lang.String r10 = valueTypeName(r3)
        L_0x0252:
            boolean r5 = r9 instanceof org.bytedeco.javacpp.annotation.ByVal
            java.lang.String r8 = "* rptrptr"
            if (r5 == 0) goto L_0x0283
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r14)
            java.lang.Class<?> r5 = r1.returnType
            java.lang.reflect.Method r11 = r1.method
            boolean r5 = noException(r5, r11)
            if (r5 == 0) goto L_0x026d
            java.lang.String r5 = "new (std::nothrow) "
            goto L_0x026f
        L_0x026d:
            java.lang.String r5 = "new "
        L_0x026f:
            r2.append(r5)
            r2.append(r10)
            r5 = 1
            r11 = r3[r5]
            r2.append(r11)
            r2.append(r15)
            java.lang.String r14 = r2.toString()
            goto L_0x02d8
        L_0x0283:
            boolean r5 = r9 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r5 == 0) goto L_0x0299
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r14)
            java.lang.String r5 = "&"
            r2.append(r5)
            java.lang.String r14 = r2.toString()
            goto L_0x02d8
        L_0x0299:
            boolean r5 = r9 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r5 == 0) goto L_0x02d8
            int r5 = r2.length()
            if (r5 <= 0) goto L_0x02b5
            r5 = 0
            r11 = r3[r5]
            r14 = r3[r5]
            int r14 = r14.length()
            r15 = 1
            int r14 = r14 - r15
            java.lang.String r11 = r11.substring(r5, r14)
            r3[r5] = r11
            goto L_0x02b6
        L_0x02b5:
            r5 = 0
        L_0x02b6:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r14 = "rptr = NULL; "
            r11.append(r14)
            r14 = r3[r5]
            r11.append(r14)
            r11.append(r8)
            r5 = 1
            r14 = r3[r5]
            r11.append(r14)
            r11.append(r6)
            r11.append(r2)
            java.lang.String r14 = r11.toString()
        L_0x02d8:
            if (r12 == 0) goto L_0x030c
            java.lang.Class<?> r2 = r1.returnType
            boolean r2 = r2.isArray()
            if (r2 == 0) goto L_0x030c
            java.lang.Class<?> r2 = r1.returnType
            java.lang.Class r2 = r2.getComponentType()
            boolean r2 = r2.isPrimitive()
            if (r2 == 0) goto L_0x030c
            r2 = 0
            r5 = r3[r2]
            java.lang.String r11 = "const "
            boolean r5 = r5.startsWith(r11)
            if (r5 != 0) goto L_0x030c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r11)
            r11 = r3[r2]
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r3[r2] = r5
        L_0x030c:
            boolean r2 = r1.bufferGetter
            java.lang.String r5 = ";"
            if (r2 == 0) goto L_0x0321
            java.io.PrintWriter r2 = r0.out
            java.lang.String r3 = "    jobject rarg = NULL;"
            r2.println(r3)
            java.io.PrintWriter r2 = r0.out
            java.lang.String r3 = "    char* rptr;"
            r2.println(r3)
            goto L_0x0361
        L_0x0321:
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r13)
            java.lang.Class<?> r15 = r1.returnType
            java.lang.String r15 = jniTypeName(r15)
            r11.append(r15)
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r2.println(r7)
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r13)
            r11 = 0
            r13 = r3[r11]
            r7.append(r13)
            r7.append(r4)
            r4 = 1
            r3 = r3[r4]
            r7.append(r3)
            r7.append(r5)
            java.lang.String r3 = r7.toString()
            r2.println(r3)
        L_0x0361:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r2 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r3 = r1.returnType
            boolean r2 = r2.isAssignableFrom(r3)
            if (r2 == 0) goto L_0x03ab
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "    rptr = new (std::nothrow) "
            r3.append(r4)
            r3.append(r10)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            boolean r2 = r9 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r2 == 0) goto L_0x03ab
            java.lang.Class<?> r2 = r1.returnType
            java.lang.String[] r2 = r0.cppTypeName(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 0
            r4 = r2[r4]
            r3.append(r4)
            r3.append(r8)
            r4 = 1
            r2 = r2[r4]
            r3.append(r2)
            r3.append(r6)
            java.lang.String r2 = r3.toString()
            goto L_0x01aa
        L_0x03ab:
            r4 = 1
            goto L_0x03ed
        L_0x03ad:
            r4 = 1
            r1.returnRaw = r4
            java.lang.Class<?> r2 = r1.returnType
            java.lang.String r2 = jniTypeName(r2)
            r4 = 0
            r3[r4] = r2
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r13)
            r6 = r3[r4]
            r5.append(r6)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r2.println(r5)
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r13)
            r3 = r3[r4]
            r5.append(r3)
            java.lang.String r3 = " rptr;"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.println(r3)
        L_0x03ed:
            r5 = r14
        L_0x03ee:
            if (r12 == 0) goto L_0x040e
            r2 = 1
            r0.usesAdapters = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r12.name
            r2.append(r3)
            java.lang.String r3 = " radapter("
            r2.append(r3)
            java.lang.String r5 = r2.toString()
            goto L_0x040e
        L_0x0407:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r3 = "    jobject rarg = obj;"
            r2.println(r3)
        L_0x040e:
            java.lang.Class<?> r1 = r1.throwsException
            if (r1 == 0) goto L_0x0420
            java.io.PrintWriter r1 = r0.out
            java.lang.String r2 = "    jthrowable exc = NULL;"
            r1.println(r2)
            java.io.PrintWriter r1 = r0.out
            java.lang.String r2 = "    try {"
            r1.println(r2)
        L_0x0420:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.returnBefore(org.bytedeco.javacpp.tools.MethodInformation):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x059e  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x05ed  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x05ef  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0612  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0637  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0649  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0667  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0862  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x087f  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x08aa  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x08b5  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x089d A[EDGE_INSN: B:322:0x089d->B:316:0x089d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:328:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0275  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void call(org.bytedeco.javacpp.tools.MethodInformation r26, java.lang.String r27, boolean r28) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            java.lang.String r3 = ""
            if (r28 == 0) goto L_0x000c
            r4 = r3
            goto L_0x0015
        L_0x000c:
            java.lang.Class<?> r4 = r1.throwsException
            if (r4 == 0) goto L_0x0013
            java.lang.String r4 = "        "
            goto L_0x0015
        L_0x0013:
            java.lang.String r4 = "    "
        L_0x0015:
            java.lang.Class<?>[] r5 = r1.parameterTypes
            int r5 = r5.length
            r6 = 0
            if (r5 <= 0) goto L_0x0025
            java.lang.Class<?>[] r5 = r1.parameterTypes
            r5 = r5[r6]
            java.lang.Class<java.lang.Class> r8 = java.lang.Class.class
            if (r5 != r8) goto L_0x0025
            r5 = 1
            goto L_0x0026
        L_0x0025:
            r5 = 0
        L_0x0026:
            java.lang.reflect.Method r8 = r1.method
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r9 = org.bytedeco.javacpp.annotation.Index.class
            java.lang.annotation.Annotation r8 = r8.getAnnotation(r9)
            org.bytedeco.javacpp.annotation.Index r8 = (org.bytedeco.javacpp.annotation.Index) r8
            if (r8 != 0) goto L_0x0040
            java.lang.reflect.Method r9 = r1.pairedMethod
            if (r9 == 0) goto L_0x0040
            java.lang.reflect.Method r8 = r1.pairedMethod
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r9 = org.bytedeco.javacpp.annotation.Index.class
            java.lang.annotation.Annotation r8 = r8.getAnnotation(r9)
            org.bytedeco.javacpp.annotation.Index r8 = (org.bytedeco.javacpp.annotation.Index) r8
        L_0x0040:
            boolean r9 = r1.deallocator
            if (r9 == 0) goto L_0x00b3
            java.io.PrintWriter r1 = r0.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "void* allocatedAddress = jlong_to_ptr(arg0);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r0.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "void (*deallocatorAddress)(void*) = (void(*)(void*))jlong_to_ptr(arg1);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r0.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "if (deallocatorAddress != NULL && allocatedAddress != NULL) {"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r0.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "    (*deallocatorAddress)(allocatedAddress);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r0.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "}"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            return
        L_0x00b3:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r9 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r10 = r1.cls
            boolean r9 = r9.isAssignableFrom(r10)
            java.lang.String r10 = "*"
            java.lang.String r11 = "ptr->"
            java.lang.String r12 = "]"
            java.lang.String r13 = "["
            java.lang.String r14 = "."
            java.lang.String r15 = "(*ptr)"
            java.lang.String r16 = " = "
            java.lang.String r6 = ", "
            java.lang.String r7 = "ptr"
            r19 = r3
            java.lang.String r3 = "("
            r20 = r5
            java.lang.String r5 = ")"
            if (r9 != 0) goto L_0x0285
            boolean r9 = r1.valueGetter
            if (r9 != 0) goto L_0x00e7
            boolean r9 = r1.valueSetter
            if (r9 != 0) goto L_0x00e7
            boolean r9 = r1.memberGetter
            if (r9 != 0) goto L_0x00e7
            boolean r9 = r1.memberSetter
            if (r9 == 0) goto L_0x0285
        L_0x00e7:
            java.lang.Class<?>[] r9 = r1.parameterTypes
            int r9 = r9.length
            r18 = 1
            int r9 = r9 + -1
            r21 = r12
            boolean r12 = r1.valueSetter
            if (r12 != 0) goto L_0x00f8
            boolean r12 = r1.memberSetter
            if (r12 == 0) goto L_0x012c
        L_0x00f8:
            java.lang.annotation.Annotation r12 = r0.by(r1, r9)
            boolean r12 = r12 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r12 != 0) goto L_0x012c
            r12 = 0
            org.bytedeco.javacpp.tools.AdapterInformation r22 = r0.adapterInformation((boolean) r12, (org.bytedeco.javacpp.tools.MethodInformation) r1, (int) r9)
            if (r22 != 0) goto L_0x012c
            java.lang.Class<?>[] r12 = r1.parameterTypes
            r12 = r12[r9]
            r22 = r13
            java.lang.Class<java.lang.String> r13 = java.lang.String.class
            if (r12 != r13) goto L_0x012e
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            java.lang.String r4 = "strcpy((char*)"
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            r2.print(r4)
            r4 = r5
            r2 = r6
            r9 = 1
            goto L_0x01c9
        L_0x012c:
            r22 = r13
        L_0x012e:
            r12 = 1
            if (r9 < r12) goto L_0x01a6
            java.lang.Class<?>[] r9 = r1.parameterTypes
            r13 = 0
            r9 = r9[r13]
            boolean r9 = r9.isArray()
            if (r9 == 0) goto L_0x01a6
            java.lang.Class<?>[] r9 = r1.parameterTypes
            r9 = r9[r13]
            java.lang.Class r9 = r9.getComponentType()
            boolean r9 = r9.isPrimitive()
            if (r9 == 0) goto L_0x01a6
            java.lang.Class<?>[] r9 = r1.parameterTypes
            r9 = r9[r12]
            java.lang.Class r13 = java.lang.Integer.TYPE
            if (r9 == r13) goto L_0x015a
            java.lang.Class<?>[] r9 = r1.parameterTypes
            r9 = r9[r12]
            java.lang.Class r12 = java.lang.Long.TYPE
            if (r9 != r12) goto L_0x01a6
        L_0x015a:
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            java.lang.String r4 = "memcpy("
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            r2.print(r4)
            boolean r2 = r1.memberGetter
            java.lang.String r4 = "ptr0 + arg1, "
            if (r2 != 0) goto L_0x018b
            boolean r2 = r1.valueGetter
            if (r2 == 0) goto L_0x017b
            goto L_0x018b
        L_0x017b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            goto L_0x0191
        L_0x018b:
            java.io.PrintWriter r2 = r0.out
            r2.print(r4)
            r2 = r6
        L_0x0191:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r9 = " * sizeof(*ptr0)"
            r4.append(r9)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r9 = 1
            r20 = 2
            goto L_0x01c9
        L_0x01a6:
            java.io.PrintWriter r9 = r0.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            r12.append(r2)
            java.lang.String r2 = r12.toString()
            r9.print(r2)
            boolean r2 = r1.valueGetter
            if (r2 != 0) goto L_0x01c2
            boolean r2 = r1.memberGetter
            if (r2 == 0) goto L_0x01c4
        L_0x01c2:
            r16 = r19
        L_0x01c4:
            r2 = r16
            r4 = r19
            r9 = 0
        L_0x01c9:
            int r12 = r1.modifiers
            boolean r12 = java.lang.reflect.Modifier.isStatic(r12)
            if (r12 != 0) goto L_0x0275
            java.lang.Class<org.bytedeco.javacpp.Pointer> r12 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r13 = r1.cls
            boolean r12 = r12.isAssignableFrom(r13)
            if (r12 != 0) goto L_0x01dd
            goto L_0x0275
        L_0x01dd:
            boolean r12 = r1.memberGetter
            if (r12 != 0) goto L_0x0238
            boolean r12 = r1.memberSetter
            if (r12 == 0) goto L_0x01e6
            goto L_0x0238
        L_0x01e6:
            java.lang.Class<?> r11 = r1.returnType
            java.lang.annotation.Annotation[] r12 = r1.annotations
            java.lang.String r11 = r0.cast((java.lang.Class<?>) r11, (java.lang.annotation.Annotation[]) r12)
            if (r8 != 0) goto L_0x0220
            int r12 = r11.length()
            if (r12 <= 0) goto L_0x0220
            java.io.PrintWriter r12 = r0.out
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r27 = r4
            java.lang.String r4 = "*("
            r13.append(r4)
            int r4 = r11.length()
            r23 = r6
            r6 = 1
            int r4 = r4 - r6
            java.lang.String r4 = r11.substring(r6, r4)
            r13.append(r4)
            java.lang.String r4 = "*)&"
            r13.append(r4)
            java.lang.String r4 = r13.toString()
            r12.print(r4)
            goto L_0x0224
        L_0x0220:
            r27 = r4
            r23 = r6
        L_0x0224:
            java.io.PrintWriter r4 = r0.out
            if (r8 == 0) goto L_0x0229
            goto L_0x0234
        L_0x0229:
            int r6 = r1.dim
            if (r6 > 0) goto L_0x0233
            if (r9 == 0) goto L_0x0230
            goto L_0x0233
        L_0x0230:
            java.lang.String r15 = "*ptr"
            goto L_0x0234
        L_0x0233:
            r15 = r7
        L_0x0234:
            r4.print(r15)
            goto L_0x0282
        L_0x0238:
            r27 = r4
            r23 = r6
            if (r8 == 0) goto L_0x025b
            java.io.PrintWriter r4 = r0.out
            r4.print(r15)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            java.lang.String[] r6 = r1.memberName
            r9 = 0
            r6 = r6[r9]
            r4.append(r6)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            goto L_0x0282
        L_0x025b:
            r9 = 0
            java.io.PrintWriter r4 = r0.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r11)
            java.lang.String[] r11 = r1.memberName
            r11 = r11[r9]
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            r4.print(r6)
            goto L_0x0282
        L_0x0275:
            r27 = r4
            r23 = r6
            java.io.PrintWriter r4 = r0.out
            java.lang.String r6 = cppScopeName((org.bytedeco.javacpp.tools.MethodInformation) r26)
            r4.print(r6)
        L_0x0282:
            r4 = r27
            goto L_0x02a9
        L_0x0285:
            r23 = r6
            r21 = r12
            r22 = r13
            boolean r6 = r1.bufferGetter
            if (r6 == 0) goto L_0x02ac
            java.io.PrintWriter r6 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            r9.append(r2)
            r9.append(r7)
            java.lang.String r2 = r9.toString()
            r6.print(r2)
        L_0x02a6:
            r2 = r19
            r4 = r2
        L_0x02a9:
            r12 = 0
            goto L_0x054a
        L_0x02ac:
            java.io.PrintWriter r6 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            r6.print(r2)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r2 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r4 = r1.cls
            boolean r2 = r2.isAssignableFrom(r4)
            if (r2 == 0) goto L_0x031a
            java.lang.Class<?> r2 = r1.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.Namespace> r4 = org.bytedeco.javacpp.annotation.Namespace.class
            boolean r2 = r2.isAnnotationPresent(r4)
            if (r2 == 0) goto L_0x02f6
            java.io.PrintWriter r2 = r0.out
            java.lang.String r4 = "(ptr0->*(ptr->ptr))"
            r2.print(r4)
            boolean r2 = r1.valueGetter
            if (r2 != 0) goto L_0x02eb
            boolean r2 = r1.valueSetter
            if (r2 == 0) goto L_0x02e4
            goto L_0x02eb
        L_0x02e4:
            r2 = r3
            r4 = r5
        L_0x02e6:
            r12 = 0
            r20 = 1
            goto L_0x054a
        L_0x02eb:
            boolean r2 = r1.valueGetter
            if (r2 == 0) goto L_0x02f1
            r16 = r19
        L_0x02f1:
            r2 = r16
            r4 = r19
            goto L_0x02e6
        L_0x02f6:
            boolean r2 = r1.valueGetter
            if (r2 != 0) goto L_0x0308
            boolean r2 = r1.valueSetter
            if (r2 == 0) goto L_0x02ff
            goto L_0x0308
        L_0x02ff:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r4 = "(*ptr->ptr)"
            r2.print(r4)
            goto L_0x0546
        L_0x0308:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r4 = "ptr->ptr"
            r2.print(r4)
            boolean r2 = r1.valueGetter
            if (r2 == 0) goto L_0x0315
            r16 = r19
        L_0x0315:
            r2 = r16
            r4 = r19
            goto L_0x02a9
        L_0x031a:
            boolean r2 = r1.allocator
            java.lang.String r4 = "JavaCPP_"
            if (r2 == 0) goto L_0x0385
            java.lang.Class<?> r2 = r1.cls
            java.lang.String[] r2 = r0.cppTypeName(r2)
            java.lang.String r6 = valueTypeName(r2)
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r9 = r0.virtualFunctions
            java.lang.Class<?> r11 = r1.cls
            boolean r9 = r9.containsKey(r11)
            if (r9 == 0) goto L_0x0347
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            java.lang.String r4 = mangle(r6)
            r9.append(r4)
            java.lang.String r6 = r9.toString()
        L_0x0347:
            java.lang.Class<?> r4 = r1.cls
            java.lang.Class<org.bytedeco.javacpp.Pointer> r9 = org.bytedeco.javacpp.Pointer.class
            if (r4 != r9) goto L_0x034f
            goto L_0x02a6
        L_0x034f:
            java.io.PrintWriter r4 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.Class<?> r11 = r1.cls
            java.lang.reflect.Method r12 = r1.method
            boolean r11 = noException(r11, r12)
            if (r11 == 0) goto L_0x0363
            java.lang.String r11 = "new (std::nothrow) "
            goto L_0x0365
        L_0x0363:
            java.lang.String r11 = "new "
        L_0x0365:
            r9.append(r11)
            r9.append(r6)
            r6 = 1
            r2 = r2[r6]
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            r4.print(r2)
            boolean r2 = r1.arrayAllocator
            if (r2 == 0) goto L_0x0382
            r4 = r21
            r2 = r22
            goto L_0x02a9
        L_0x0382:
            r2 = r3
            goto L_0x0547
        L_0x0385:
            int r2 = r1.modifiers
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 != 0) goto L_0x053d
            java.lang.Class<org.bytedeco.javacpp.Pointer> r2 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r6 = r1.cls
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 != 0) goto L_0x0399
            goto L_0x053d
        L_0x0399:
            java.lang.String[] r2 = r1.memberName
            r6 = 0
            r2 = r2[r6]
            java.lang.Class<?> r6 = r1.cls
            java.lang.String[] r6 = r0.cppTypeName(r6)
            java.lang.String r6 = valueTypeName(r6)
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r9 = r0.virtualFunctions
            java.lang.Class<?> r12 = r1.cls
            boolean r9 = r9.containsKey(r12)
            java.lang.String r12 = "(("
            if (r9 == 0) goto L_0x0431
            if (r28 != 0) goto L_0x0431
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            java.lang.String r4 = mangle(r6)
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            java.lang.reflect.Method r6 = r1.method
            int r6 = r6.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isPublic(r6)
            if (r6 == 0) goto L_0x03f2
            java.io.PrintWriter r6 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "(dynamic_cast<"
            r9.append(r11)
            r9.append(r4)
            java.lang.String r11 = "*>(ptr) != NULL ? "
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r6.print(r9)
            r6 = 1
            goto L_0x03f3
        L_0x03f2:
            r6 = 0
        L_0x03f3:
            java.lang.reflect.Method r9 = r1.method
            java.lang.Class<org.bytedeco.javacpp.annotation.Virtual> r11 = org.bytedeco.javacpp.annotation.Virtual.class
            boolean r9 = r9.isAnnotationPresent(r11)
            if (r9 == 0) goto L_0x0410
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r9 = "super_"
            r2.append(r9)
            java.lang.String r9 = r1.name
            r2.append(r9)
            java.lang.String r2 = r2.toString()
        L_0x0410:
            java.io.PrintWriter r9 = r0.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r12)
            r11.append(r4)
            java.lang.String r4 = "*)ptr)->"
            r11.append(r4)
            r11.append(r2)
            java.lang.String r2 = r11.toString()
            r9.print(r2)
            r2 = r3
            r4 = r5
            r12 = r6
            goto L_0x054a
        L_0x0431:
            if (r28 == 0) goto L_0x046e
            java.lang.reflect.Method r4 = r1.method
            java.lang.Class r4 = r4.getDeclaringClass()
            java.lang.Class<?> r6 = r1.cls
            if (r4 == r6) goto L_0x046e
            java.lang.reflect.Method r4 = r1.method
            java.lang.Class r4 = r4.getDeclaringClass()
            java.lang.String[] r4 = r0.cppTypeName(r4)
            java.io.PrintWriter r6 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r12)
            r11 = 0
            r12 = r4[r11]
            r9.append(r12)
            r11 = 1
            r4 = r4[r11]
            r9.append(r4)
            java.lang.String r4 = ")ptr)->"
            r9.append(r4)
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            r6.print(r2)
            goto L_0x0546
        L_0x046e:
            if (r8 == 0) goto L_0x0489
            java.io.PrintWriter r4 = r0.out
            r4.print(r15)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            goto L_0x0547
        L_0x0489:
            java.lang.String r4 = "operator"
            boolean r4 = r2.startsWith(r4)
            if (r4 == 0) goto L_0x049c
            r4 = 8
            java.lang.String r4 = r2.substring(r4)
            java.lang.String r4 = r4.trim()
            goto L_0x049e
        L_0x049c:
            r4 = r19
        L_0x049e:
            java.lang.Class<?>[] r6 = r1.parameterTypes
            int r6 = r6.length
            if (r6 <= 0) goto L_0x0528
            java.lang.String r6 = "="
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "+"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "-"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            boolean r6 = r4.equals(r10)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "/"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "%"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "=="
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "!="
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "<"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = ">"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = "<="
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0501
            java.lang.String r6 = ">="
            boolean r6 = r4.equals(r6)
            if (r6 == 0) goto L_0x0528
        L_0x0501:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r6 = "((*ptr)"
            r2.print(r6)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            goto L_0x02a9
        L_0x0528:
            java.io.PrintWriter r4 = r0.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r11)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            r4.print(r2)
            goto L_0x0546
        L_0x053d:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r4 = cppScopeName((org.bytedeco.javacpp.tools.MethodInformation) r26)
            r2.print(r4)
        L_0x0546:
            r2 = r3
        L_0x0547:
            r4 = r5
            goto L_0x02a9
        L_0x054a:
            r6 = r20
        L_0x054c:
            java.lang.Class<?>[] r9 = r1.parameterTypes
            int r9 = r9.length
            java.lang.String r11 = " : "
            if (r6 > r9) goto L_0x089d
            int r9 = r1.dim
            int r9 = r20 + r9
            if (r6 != r9) goto L_0x0595
            java.lang.String[] r9 = r1.memberName
            int r9 = r9.length
            r13 = 1
            if (r9 <= r13) goto L_0x0568
            java.io.PrintWriter r9 = r0.out
            java.lang.String[] r15 = r1.memberName
            r15 = r15[r13]
            r9.print(r15)
        L_0x0568:
            java.io.PrintWriter r9 = r0.out
            r9.print(r2)
            boolean r9 = r1.withEnv
            if (r9 == 0) goto L_0x0595
            java.io.PrintWriter r9 = r0.out
            int r13 = r1.modifiers
            boolean r13 = java.lang.reflect.Modifier.isStatic(r13)
            if (r13 == 0) goto L_0x057e
            java.lang.String r13 = "env, cls"
            goto L_0x0580
        L_0x057e:
            java.lang.String r13 = "env, obj"
        L_0x0580:
            r9.print(r13)
            java.lang.Class<?>[] r9 = r1.parameterTypes
            int r9 = r9.length
            int r9 = r9 - r20
            int r13 = r1.dim
            int r9 = r9 - r13
            if (r9 <= 0) goto L_0x0595
            java.io.PrintWriter r9 = r0.out
            r13 = r23
            r9.print(r13)
            goto L_0x0597
        L_0x0595:
            r13 = r23
        L_0x0597:
            java.lang.Class<?>[] r9 = r1.parameterTypes
            int r9 = r9.length
            if (r6 != r9) goto L_0x059e
            goto L_0x089d
        L_0x059e:
            int r9 = r1.dim
            int r9 = r20 + r9
            if (r6 >= r9) goto L_0x05d9
            if (r8 == 0) goto L_0x05cf
            java.lang.String r9 = r8.function()
            int r9 = r9.length()
            if (r9 != 0) goto L_0x05b1
            goto L_0x05cf
        L_0x05b1:
            java.io.PrintWriter r9 = r0.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r14)
            r16 = r2
            java.lang.String r2 = r8.function()
            r15.append(r2)
            r15.append(r3)
            java.lang.String r2 = r15.toString()
            r9.print(r2)
            goto L_0x05db
        L_0x05cf:
            r16 = r2
            java.io.PrintWriter r2 = r0.out
            r9 = r22
            r2.print(r9)
            goto L_0x05dd
        L_0x05d9:
            r16 = r2
        L_0x05db:
            r9 = r22
        L_0x05dd:
            java.lang.annotation.Annotation r2 = r0.by(r1, r6)
            java.lang.String r15 = r0.cast((org.bytedeco.javacpp.tools.MethodInformation) r1, (int) r6)
            r22 = r9
            boolean[] r9 = r1.parameterRaw
            boolean r9 = r9[r6]
            if (r9 == 0) goto L_0x05ef
            r9 = 0
            goto L_0x05f6
        L_0x05ef:
            r9 = 0
            org.bytedeco.javacpp.tools.AdapterInformation r23 = r0.adapterInformation((boolean) r9, (org.bytedeco.javacpp.tools.MethodInformation) r1, (int) r6)
            r9 = r23
        L_0x05f6:
            r23 = r14
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r14 = org.bytedeco.javacpp.FunctionPointer.class
            r27 = r15
            java.lang.Class<?> r15 = r1.cls
            boolean r14 = r14.isAssignableFrom(r15)
            if (r14 == 0) goto L_0x0637
            java.lang.Class<?> r14 = r1.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.Namespace> r15 = org.bytedeco.javacpp.annotation.Namespace.class
            boolean r14 = r14.isAnnotationPresent(r15)
            if (r14 != 0) goto L_0x0637
            boolean r14 = r1.valueSetter
            if (r14 == 0) goto L_0x0637
            java.lang.Class<?> r14 = r1.cls
            java.lang.String[] r14 = r0.cppTypeName(r14)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r3)
            r28 = r12
            r17 = 0
            r12 = r14[r17]
            r15.append(r12)
            r12 = 1
            r14 = r14[r12]
            r15.append(r14)
            r15.append(r5)
            java.lang.String r15 = r15.toString()
            goto L_0x063d
        L_0x0637:
            r28 = r12
            r17 = 0
            r15 = r27
        L_0x063d:
            java.lang.Class<java.lang.Enum> r12 = java.lang.Enum.class
            java.lang.Class<?>[] r14 = r1.parameterTypes
            r14 = r14[r6]
            boolean r12 = r12.isAssignableFrom(r14)
            if (r12 == 0) goto L_0x0667
            r12 = 1
            r0.accessesEnums = r12
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            java.lang.String r11 = "val"
            r9.append(r11)
            r9.append(r6)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x0667:
            java.lang.String r12 = "(void*)"
            boolean r12 = r12.equals(r15)
            if (r12 != 0) goto L_0x0677
            java.lang.String r12 = "(void *)"
            boolean r12 = r12.equals(r15)
            if (r12 == 0) goto L_0x069a
        L_0x0677:
            java.lang.Class<?>[] r12 = r1.parameterTypes
            r12 = r12[r6]
            java.lang.Class r14 = java.lang.Long.TYPE
            if (r12 != r14) goto L_0x069a
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "jlong_to_ptr(arg"
            r9.append(r11)
            r9.append(r6)
            r9.append(r5)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x069a:
            java.lang.Class<?>[] r12 = r1.parameterTypes
            r12 = r12[r6]
            boolean r12 = r12.isPrimitive()
            if (r12 == 0) goto L_0x06ce
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByPtr
            if (r9 != 0) goto L_0x06ac
            boolean r2 = r2 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r2 == 0) goto L_0x06b3
        L_0x06ac:
            java.io.PrintWriter r2 = r0.out
            java.lang.String r9 = "&"
            r2.print(r9)
        L_0x06b3:
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            java.lang.String r11 = "arg"
            r9.append(r11)
            r9.append(r6)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x06ce:
            if (r9 == 0) goto L_0x0749
            java.lang.String r2 = r9.cast
            java.lang.String r2 = r2.trim()
            int r11 = r2.length()
            if (r11 <= 0) goto L_0x06fa
            boolean r11 = r2.startsWith(r3)
            if (r11 != 0) goto L_0x06fa
            boolean r11 = r2.endsWith(r5)
            if (r11 != 0) goto L_0x06fa
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r3)
            r11.append(r2)
            r11.append(r5)
            java.lang.String r2 = r11.toString()
        L_0x06fa:
            java.lang.String r11 = r9.cast2
            java.lang.String r11 = r11.trim()
            int r12 = r11.length()
            if (r12 <= 0) goto L_0x0724
            boolean r12 = r11.startsWith(r3)
            if (r12 != 0) goto L_0x0724
            boolean r12 = r11.endsWith(r5)
            if (r12 != 0) goto L_0x0724
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r3)
            r12.append(r11)
            r12.append(r5)
            java.lang.String r11 = r12.toString()
        L_0x0724:
            java.io.PrintWriter r12 = r0.out
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r2)
            r14.append(r11)
            java.lang.String r2 = "adapter"
            r14.append(r2)
            r14.append(r6)
            java.lang.String r2 = r14.toString()
            r12.print(r2)
            int r2 = r9.argc
            r9 = 1
            int r2 = r2 - r9
            int r6 = r6 + r2
        L_0x0745:
            r24 = r3
            goto L_0x085c
        L_0x0749:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r9 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?>[] r12 = r1.parameterTypes
            r12 = r12[r6]
            boolean r9 = r9.isAssignableFrom(r12)
            if (r9 == 0) goto L_0x07ae
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r9 != 0) goto L_0x07ae
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r9 != 0) goto L_0x07ae
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            java.lang.String r11 = "->ptr)"
            java.lang.String r12 = "(ptr"
            if (r9 == 0) goto L_0x0780
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            r9.append(r12)
            r9.append(r6)
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x0780:
            java.io.PrintWriter r9 = r0.out
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r15)
            r14.append(r12)
            r14.append(r6)
            java.lang.String r12 = " == NULL ? NULL : "
            r14.append(r12)
            boolean r2 = r2 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r2 == 0) goto L_0x079c
            java.lang.String r2 = "&ptr"
            goto L_0x079d
        L_0x079c:
            r2 = r7
        L_0x079d:
            r14.append(r2)
            r14.append(r6)
            r14.append(r11)
            java.lang.String r2 = r14.toString()
            r9.print(r2)
            goto L_0x0745
        L_0x07ae:
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r9 != 0) goto L_0x0802
            boolean r12 = r2 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r12 == 0) goto L_0x07bf
            java.lang.Class<?>[] r12 = r1.parameterTypes
            r12 = r12[r6]
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            if (r12 == r14) goto L_0x07bf
            goto L_0x0802
        L_0x07bf:
            boolean r2 = r2 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r2 == 0) goto L_0x07e9
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            java.lang.String r11 = "(arg"
            r9.append(r11)
            r9.append(r6)
            java.lang.String r11 = " == NULL ? NULL : &ptr"
            r9.append(r11)
            r9.append(r6)
            r9.append(r5)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x07e9:
            java.io.PrintWriter r2 = r0.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            r9.append(r7)
            r9.append(r6)
            java.lang.String r9 = r9.toString()
            r2.print(r9)
            goto L_0x0745
        L_0x0802:
            if (r9 == 0) goto L_0x080b
            org.bytedeco.javacpp.annotation.ByVal r2 = (org.bytedeco.javacpp.annotation.ByVal) r2
            java.lang.String r2 = r2.nullValue()
            goto L_0x0818
        L_0x080b:
            boolean r9 = r2 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r9 == 0) goto L_0x0816
            org.bytedeco.javacpp.annotation.ByRef r2 = (org.bytedeco.javacpp.annotation.ByRef) r2
            java.lang.String r2 = r2.nullValue()
            goto L_0x0818
        L_0x0816:
            r2 = r19
        L_0x0818:
            java.io.PrintWriter r9 = r0.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            int r14 = r2.length()
            if (r14 <= 0) goto L_0x0842
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r7)
            r14.append(r6)
            r24 = r3
            java.lang.String r3 = " == NULL ? "
            r14.append(r3)
            r14.append(r2)
            r14.append(r11)
            java.lang.String r2 = r14.toString()
            goto L_0x0846
        L_0x0842:
            r24 = r3
            r2 = r19
        L_0x0846:
            r12.append(r2)
            r12.append(r10)
            r12.append(r15)
            r12.append(r7)
            r12.append(r6)
            java.lang.String r2 = r12.toString()
            r9.print(r2)
        L_0x085c:
            int r2 = r1.dim
            int r2 = r20 + r2
            if (r6 >= r2) goto L_0x087f
            if (r8 == 0) goto L_0x0877
            java.lang.String r2 = r8.function()
            int r2 = r2.length()
            if (r2 != 0) goto L_0x086f
            goto L_0x0877
        L_0x086f:
            java.io.PrintWriter r2 = r0.out
            r2.print(r5)
            r3 = r21
            goto L_0x088d
        L_0x0877:
            java.io.PrintWriter r2 = r0.out
            r3 = r21
            r2.print(r3)
            goto L_0x088d
        L_0x087f:
            r3 = r21
            java.lang.Class<?>[] r2 = r1.parameterTypes
            int r2 = r2.length
            r9 = 1
            int r2 = r2 - r9
            if (r6 >= r2) goto L_0x088d
            java.io.PrintWriter r2 = r0.out
            r2.print(r13)
        L_0x088d:
            int r6 = r6 + 1
            r12 = r28
            r21 = r3
            r2 = r16
            r14 = r23
            r3 = r24
            r23 = r13
            goto L_0x054c
        L_0x089d:
            r28 = r12
            java.io.PrintWriter r2 = r0.out
            r2.print(r4)
            java.lang.String[] r2 = r1.memberName
            int r2 = r2.length
            r3 = 2
            if (r2 <= r3) goto L_0x08b3
            java.io.PrintWriter r2 = r0.out
            java.lang.String[] r4 = r1.memberName
            r3 = r4[r3]
            r2.print(r3)
        L_0x08b3:
            if (r28 == 0) goto L_0x08be
            r2 = 1
            r0.call(r1, r11, r2)
            java.io.PrintWriter r1 = r0.out
            r1.print(r5)
        L_0x08be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.call(org.bytedeco.javacpp.tools.MethodInformation, java.lang.String, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x049d  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x061c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void returnAfter(org.bytedeco.javacpp.tools.MethodInformation r14) {
        /*
            r13 = this;
            java.lang.Class<?> r0 = r14.throwsException
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = "        "
            goto L_0x0009
        L_0x0007:
            java.lang.String r0 = "    "
        L_0x0009:
            boolean r1 = r14.returnRaw
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0014
            java.lang.String[] r1 = new java.lang.String[]{r2}
            goto L_0x001c
        L_0x0014:
            java.lang.Class<?> r1 = r14.returnType
            java.lang.annotation.Annotation[] r3 = r14.annotations
            java.lang.String[] r1 = r13.cppCastTypeName(r1, r3)
        L_0x001c:
            java.lang.annotation.Annotation[] r3 = r14.annotations
            java.lang.annotation.Annotation r3 = r13.by(r3)
            java.lang.String r4 = valueTypeName(r1)
            java.lang.annotation.Annotation[] r5 = r14.annotations
            r6 = 0
            org.bytedeco.javacpp.tools.AdapterInformation r4 = r13.adapterInformation((boolean) r6, (java.lang.String) r4, (java.lang.annotation.Annotation[]) r5)
            boolean r5 = r14.deallocator
            java.lang.String r7 = ";"
            if (r5 == 0) goto L_0x0035
            r5 = r2
            goto L_0x0036
        L_0x0035:
            r5 = r7
        L_0x0036:
            java.lang.annotation.Annotation[] r8 = r14.annotations
            java.lang.annotation.Annotation r8 = r13.by(r8)
            boolean r8 = r8 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r8 == 0) goto L_0x0063
            java.lang.Class<?> r8 = r14.returnType
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            if (r8 != r9) goto L_0x0063
            if (r4 != 0) goto L_0x0063
            java.io.PrintWriter r8 = r13.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = ");\n"
            r9.append(r10)
            r9.append(r0)
            java.lang.String r10 = "rptr = rstr.c_str()"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.print(r9)
        L_0x0063:
            java.lang.Class<?> r8 = r14.returnType
            boolean r8 = r8.isPrimitive()
            java.lang.String r9 = ")"
            if (r8 != 0) goto L_0x007e
            if (r4 == 0) goto L_0x007e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r9)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
        L_0x007e:
            java.lang.Class<org.bytedeco.javacpp.Pointer> r8 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r10 = r14.returnType
            boolean r8 = r8.isAssignableFrom(r10)
            java.lang.String r10 = "}"
            if (r8 != 0) goto L_0x00ae
            java.lang.Class<?> r8 = r14.returnType
            boolean r8 = r8.isArray()
            if (r8 == 0) goto L_0x009e
            java.lang.Class<?> r8 = r14.returnType
            java.lang.Class r8 = r8.getComponentType()
            boolean r8 = r8.isPrimitive()
            if (r8 != 0) goto L_0x00ae
        L_0x009e:
            java.lang.Class<java.nio.Buffer> r8 = java.nio.Buffer.class
            java.lang.Class<?> r11 = r14.returnType
            boolean r8 = r8.isAssignableFrom(r11)
            if (r8 != 0) goto L_0x00ae
            java.lang.Class<?> r8 = r14.returnType
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            if (r8 != r11) goto L_0x01a1
        L_0x00ae:
            boolean r8 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r8 == 0) goto L_0x00c5
            if (r4 != 0) goto L_0x00c5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            goto L_0x016d
        L_0x00c5:
            boolean r8 = r3 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r8 == 0) goto L_0x016c
            java.io.PrintWriter r8 = r13.out
            r8.println(r5)
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "if (rptrptr == NULL) {"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "    env->ThrowNew(JavaCPP_getClass(env, "
            r8.append(r9)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r9 = r13.jclasses
            java.lang.Class<java.lang.NullPointerException> r11 = java.lang.NullPointerException.class
            int r9 = r9.index(r11)
            r8.append(r9)
            java.lang.String r9 = "), \"Return pointer address is NULL.\");"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "} else {"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r5 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r8 = r14.returnType
            boolean r5 = r5.isAssignableFrom(r8)
            if (r5 == 0) goto L_0x0141
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "    rptr->ptr = *rptrptr;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            goto L_0x0157
        L_0x0141:
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "    rptr = *rptrptr;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
        L_0x0157:
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            goto L_0x016d
        L_0x016c:
            r2 = r5
        L_0x016d:
            if (r4 == 0) goto L_0x01a0
            java.lang.Class<?> r5 = r14.returnType
            boolean r5 = r5.isArray()
            if (r5 == 0) goto L_0x01a0
            java.lang.Class<?> r5 = r14.returnType
            java.lang.Class r5 = r5.getComponentType()
            boolean r5 = r5.isPrimitive()
            if (r5 == 0) goto L_0x01a0
            r5 = r1[r6]
            java.lang.String r8 = "const "
            boolean r5 = r5.startsWith(r8)
            if (r5 != 0) goto L_0x01a0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r8)
            r8 = r1[r6]
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r1[r6] = r5
        L_0x01a0:
            r5 = r2
        L_0x01a1:
            java.io.PrintWriter r1 = r13.out
            r1.println(r5)
            java.lang.Class<?> r1 = r14.returnType
            java.lang.Class r2 = java.lang.Void.TYPE
            r5 = 1
            if (r1 != r2) goto L_0x02b6
            boolean r1 = r14.allocator
            if (r1 != 0) goto L_0x01b5
            boolean r1 = r14.arrayAllocator
            if (r1 == 0) goto L_0x0882
        L_0x01b5:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "jlong rcapacity = "
            r2.append(r3)
            boolean r3 = r14.arrayAllocator
            if (r3 == 0) goto L_0x01cb
            java.lang.String r3 = "arg0;"
            goto L_0x01cd
        L_0x01cb:
            java.lang.String r3 = "1;"
        L_0x01cd:
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.lang.Class<?> r1 = r14.cls
            java.lang.Class<org.bytedeco.javacpp.Pointer> r2 = org.bytedeco.javacpp.Pointer.class
            if (r1 == r2) goto L_0x01f1
            java.lang.Class<?> r1 = r14.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.NoDeallocator> r2 = org.bytedeco.javacpp.annotation.NoDeallocator.class
            boolean r1 = r1.isAnnotationPresent(r2)
            if (r1 != 0) goto L_0x01f1
            java.lang.reflect.Method r1 = r14.method
            java.lang.Class<org.bytedeco.javacpp.annotation.NoDeallocator> r2 = org.bytedeco.javacpp.annotation.NoDeallocator.class
            boolean r1 = r1.isAnnotationPresent(r2)
            if (r1 == 0) goto L_0x01f2
        L_0x01f1:
            r6 = 1
        L_0x01f2:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "JavaCPP_initPointer(env, obj, rptr, rcapacity, rptr, "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.print(r2)
            if (r6 == 0) goto L_0x0212
            java.io.PrintWriter r1 = r13.out
            java.lang.String r2 = "NULL);"
            r1.println(r2)
            goto L_0x026d
        L_0x0212:
            boolean r1 = r14.arrayAllocator
            java.lang.String r2 = "&JavaCPP_"
            if (r1 == 0) goto L_0x0243
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.Class<?> r2 = r14.cls
            java.lang.String r2 = r2.getName()
            java.lang.String r2 = mangle(r2)
            r3.append(r2)
            java.lang.String r2 = "_deallocateArray);"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.println(r2)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r1 = r13.arrayDeallocators
            java.lang.Class<?> r2 = r14.cls
            r1.index(r2)
            goto L_0x026d
        L_0x0243:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.Class<?> r2 = r14.cls
            java.lang.String r2 = r2.getName()
            java.lang.String r2 = mangle(r2)
            r3.append(r2)
            java.lang.String r2 = "_deallocate);"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.println(r2)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r1 = r13.deallocators
            java.lang.Class<?> r2 = r14.cls
            r1.index(r2)
        L_0x026d:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r1 = r13.virtualFunctions
            java.lang.Class<?> r2 = r14.cls
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0882
            java.lang.Class<?> r14 = r14.cls
            java.lang.String[] r14 = r13.cppTypeName(r14)
            java.lang.String r14 = valueTypeName(r14)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JavaCPP_"
            r1.append(r2)
            java.lang.String r14 = mangle(r14)
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "(("
            r2.append(r0)
            r2.append(r14)
            java.lang.String r14 = "*)rptr)->obj = env->NewWeakGlobalRef(obj);"
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            r1.println(r14)
            goto L_0x0882
        L_0x02b6:
            boolean r1 = r14.valueSetter
            if (r1 != 0) goto L_0x0882
            boolean r1 = r14.memberSetter
            if (r1 != 0) goto L_0x0882
            boolean r1 = r14.noReturnGetter
            if (r1 == 0) goto L_0x02c4
            goto L_0x0882
        L_0x02c4:
            java.lang.Class<?> r1 = r14.returnType
            boolean r1 = r1.isPrimitive()
            if (r1 == 0) goto L_0x02f2
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "rarg = ("
            r2.append(r0)
            java.lang.Class<?> r14 = r14.returnType
            java.lang.String r14 = jniTypeName(r14)
            r2.append(r14)
            java.lang.String r14 = ")rval;"
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            r1.println(r14)
            goto L_0x0882
        L_0x02f2:
            boolean r1 = r14.returnRaw
            if (r1 == 0) goto L_0x030e
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "rarg = rptr;"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
            goto L_0x0882
        L_0x030e:
            java.lang.Class<java.lang.Enum> r1 = java.lang.Enum.class
            java.lang.Class<?> r2 = r14.returnType
            boolean r1 = r1.isAssignableFrom(r2)
            java.lang.String r2 = "    env->Set"
            if (r1 == 0) goto L_0x0397
            r13.accessesEnums = r5
            java.lang.Class<?> r14 = r14.returnType
            java.lang.String r14 = r13.enumValueType(r14)
            if (r14 == 0) goto L_0x0882
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r3 = r14.charAt(r6)
            char r3 = java.lang.Character.toUpperCase(r3)
            r1.append(r3)
            java.lang.String r3 = r14.substring(r5)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.io.PrintWriter r3 = r13.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = "if (rarg != NULL) {"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintWriter r3 = r13.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = "Field(rarg, JavaCPP_"
            r4.append(r1)
            r4.append(r14)
            java.lang.String r1 = "ValueFID, (j"
            r4.append(r1)
            r4.append(r14)
            java.lang.String r14 = ")rval);"
            r4.append(r14)
            java.lang.String r14 = r4.toString()
            r3.println(r14)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
            goto L_0x0882
        L_0x0397:
            if (r4 == 0) goto L_0x0424
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "rptr = radapter;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            java.lang.Class<?> r1 = r14.returnType
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            if (r1 == r8) goto L_0x0490
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "jlong rcapacity = (jlong)radapter.size;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            java.lang.Class<org.bytedeco.javacpp.Pointer> r1 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r8 = r14.returnType
            boolean r1 = r1.isAssignableFrom(r8)
            if (r1 == 0) goto L_0x040d
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "void* rowner = radapter.owner;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "void (*deallocator)(void*) = rowner != NULL ? &"
            r8.append(r9)
            java.lang.String r9 = r4.name
            r8.append(r9)
            java.lang.String r9 = "::deallocate : 0;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            goto L_0x0490
        L_0x040d:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "void (*deallocator)(void*) = 0;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            goto L_0x0490
        L_0x0424:
            boolean r1 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r1 != 0) goto L_0x0435
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r1 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r8 = r14.returnType
            boolean r1 = r1.isAssignableFrom(r8)
            if (r1 == 0) goto L_0x0433
            goto L_0x0435
        L_0x0433:
            r1 = 0
            goto L_0x0491
        L_0x0435:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "jlong rcapacity = 1;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "void* rowner = (void*)rptr;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = "void (*deallocator)(void*) = &JavaCPP_"
            r8.append(r9)
            java.lang.Class<?> r9 = r14.returnType
            java.lang.String r9 = r9.getName()
            java.lang.String r9 = mangle(r9)
            r8.append(r9)
            java.lang.String r9 = "_deallocate;"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.println(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r1 = r13.deallocators
            java.lang.Class<?> r8 = r14.returnType
            r1.index(r8)
        L_0x0490:
            r1 = 1
        L_0x0491:
            java.lang.Class<org.bytedeco.javacpp.Pointer> r8 = org.bytedeco.javacpp.Pointer.class
            java.lang.Class<?> r9 = r14.returnType
            boolean r8 = r8.isAssignableFrom(r9)
            java.lang.String r9 = "if (rptr != NULL) {"
            if (r8 == 0) goto L_0x061c
            java.io.PrintWriter r2 = r13.out
            r2.print(r0)
            boolean r2 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r2 != 0) goto L_0x0570
            int r2 = r14.modifiers
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            java.lang.String r4 = "} else "
            if (r2 == 0) goto L_0x0531
            java.lang.Class<?>[] r2 = r14.parameterTypes
            int r2 = r2.length
            if (r2 <= 0) goto L_0x0531
            r2 = 0
        L_0x04b6:
            java.lang.Class<?>[] r5 = r14.parameterTypes
            int r5 = r5.length
            if (r2 >= r5) goto L_0x0570
            java.lang.String r5 = r13.cast((org.bytedeco.javacpp.tools.MethodInformation) r14, (int) r2)
            java.lang.annotation.Annotation[][] r8 = r14.parameterAnnotations
            r8 = r8[r2]
            java.lang.annotation.Annotation[] r11 = r14.annotations
            boolean r8 = java.util.Arrays.equals(r8, r11)
            if (r8 == 0) goto L_0x052e
            java.lang.Class<?>[] r8 = r14.parameterTypes
            r8 = r8[r2]
            java.lang.Class<?> r11 = r14.returnType
            if (r8 != r11) goto L_0x052e
            boolean r8 = r3 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r8 != 0) goto L_0x052e
            boolean r8 = r3 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r8 != 0) goto L_0x052e
            java.io.PrintWriter r8 = r13.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "if (rptr == "
            r11.append(r12)
            r11.append(r5)
            java.lang.String r5 = "ptr"
            r11.append(r5)
            r11.append(r2)
            java.lang.String r5 = ") {"
            r11.append(r5)
            java.lang.String r5 = r11.toString()
            r8.println(r5)
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r11 = "    rarg = arg"
            r8.append(r11)
            r8.append(r2)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r13.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            r8.append(r4)
            java.lang.String r8 = r8.toString()
            r5.print(r8)
        L_0x052e:
            int r2 = r2 + 1
            goto L_0x04b6
        L_0x0531:
            int r2 = r14.modifiers
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 != 0) goto L_0x0570
            java.lang.Class<?> r2 = r14.cls
            java.lang.Class<?> r3 = r14.returnType
            if (r2 != r3) goto L_0x0570
            java.io.PrintWriter r2 = r13.out
            java.lang.String r3 = "if (rptr == ptr) {"
            r2.println(r3)
            java.io.PrintWriter r2 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r5 = "    rarg = obj;"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintWriter r2 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.print(r3)
        L_0x0570:
            java.io.PrintWriter r2 = r13.out
            r2.println(r9)
            java.io.PrintWriter r2 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r4 = "    rarg = JavaCPP_createPointer(env, "
            r3.append(r4)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r4 = r13.jclasses
            java.lang.Class<?> r5 = r14.returnType
            int r4 = r4.index(r5)
            r3.append(r4)
            java.lang.Class<?>[] r4 = r14.parameterTypes
            int r4 = r4.length
            if (r4 <= 0) goto L_0x059f
            java.lang.Class<?>[] r14 = r14.parameterTypes
            r14 = r14[r6]
            java.lang.Class<java.lang.Class> r4 = java.lang.Class.class
            if (r14 != r4) goto L_0x059f
            java.lang.String r14 = ", arg0);"
            goto L_0x05a1
        L_0x059f:
            java.lang.String r14 = ");"
        L_0x05a1:
            r3.append(r14)
            java.lang.String r14 = r3.toString()
            r2.println(r14)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "    if (rarg != NULL) {"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r14.println(r2)
            if (r1 == 0) goto L_0x05da
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "        JavaCPP_initPointer(env, rarg, rptr, rcapacity, rowner, deallocator);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            goto L_0x05f0
        L_0x05da:
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "        env->SetLongField(rarg, JavaCPP_addressFID, ptr_to_jlong(rptr));"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
        L_0x05f0:
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    }"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
            goto L_0x0882
        L_0x061c:
            java.lang.Class<?> r1 = r14.returnType
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r1 != r7) goto L_0x0664
            r13.passesStrings = r5
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    rarg = JavaCPP_createString(env, rptr);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
            goto L_0x0882
        L_0x0664:
            java.lang.Class<?> r1 = r14.returnType
            boolean r1 = r1.isArray()
            java.lang.String r7 = "jlong rcapacity = rptr != NULL ? 1 : 0;"
            if (r1 == 0) goto L_0x0767
            java.lang.Class<?> r1 = r14.returnType
            java.lang.Class r1 = r1.getComponentType()
            boolean r1 = r1.isPrimitive()
            if (r1 == 0) goto L_0x0767
            if (r4 != 0) goto L_0x0694
            boolean r1 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r1 != 0) goto L_0x0694
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r1.println(r3)
        L_0x0694:
            java.lang.Class<?> r14 = r14.returnType
            java.lang.Class r14 = r14.getComponentType()
            java.lang.String r14 = r14.getName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r3 = r14.charAt(r6)
            char r3 = java.lang.Character.toUpperCase(r3)
            r1.append(r3)
            java.lang.String r3 = r14.substring(r5)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.io.PrintWriter r3 = r13.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r3.println(r5)
            java.io.PrintWriter r3 = r13.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            java.lang.String r6 = "    rarg = env->New"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r6 = "Array(rcapacity < INT_MAX ? rcapacity : INT_MAX);"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.println(r5)
            java.io.PrintWriter r3 = r13.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r2)
            r5.append(r1)
            java.lang.String r1 = "ArrayRegion(rarg, 0, rcapacity < INT_MAX ? rcapacity : INT_MAX, (j"
            r5.append(r1)
            r5.append(r14)
            java.lang.String r14 = "*)rptr);"
            r5.append(r14)
            java.lang.String r14 = r5.toString()
            r3.println(r14)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            if (r4 == 0) goto L_0x0882
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "if (deallocator != 0 && rptr != NULL) {"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    (*(void(*)(void*))jlong_to_ptr(deallocator))((void*)rptr);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
            goto L_0x0882
        L_0x0767:
            java.lang.Class<java.nio.Buffer> r1 = java.nio.Buffer.class
            java.lang.Class<?> r2 = r14.returnType
            boolean r1 = r1.isAssignableFrom(r2)
            if (r1 == 0) goto L_0x0882
            boolean r1 = r14.bufferGetter
            if (r1 == 0) goto L_0x07b8
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "jlong rposition = position;"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "jlong rlimit = limit;"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "jlong rcapacity = capacity;"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            goto L_0x07d2
        L_0x07b8:
            if (r4 != 0) goto L_0x07d2
            boolean r1 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r1 != 0) goto L_0x07d2
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
        L_0x07d2:
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "    jlong rcapacityptr = rcapacity * sizeof(rptr[0]);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintWriter r1 = r13.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "    rarg = env->NewDirectByteBuffer((void*)rptr, rcapacityptr < INT_MAX ? rcapacityptr : INT_MAX);"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            boolean r14 = r14.bufferGetter
            if (r14 == 0) goto L_0x086e
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    jlong rpositionptr = rposition * sizeof(rptr[0]);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    jlong rlimitptr = rlimit * sizeof(rptr[0]);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    env->SetIntField(rarg, JavaCPP_bufferPositionFID, rpositionptr < INT_MAX ? rpositionptr : INT_MAX);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "    env->SetIntField(rarg, JavaCPP_bufferLimitFID, rlimitptr < INT_MAX ? rlimitptr : INT_MAX);"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r14.println(r1)
        L_0x086e:
            java.io.PrintWriter r14 = r13.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r14.println(r0)
        L_0x0882:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.returnAfter(org.bytedeco.javacpp.tools.MethodInformation):void");
    }

    /* access modifiers changed from: package-private */
    public void parametersAfter(MethodInformation methodInformation) {
        String str;
        MethodInformation methodInformation2;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        MethodInformation methodInformation3 = methodInformation;
        String str12 = "    }";
        String str13 = ");";
        boolean z = true;
        if (methodInformation3.throwsException != null) {
            this.mayThrowExceptions = true;
            this.out.println("    } catch (...) {");
            this.out.println("        exc = JavaCPP_handleException(env, " + this.jclasses.index(methodInformation3.throwsException) + str13);
            this.out.println(str12);
            this.out.println();
        }
        char c = 0;
        int i = (methodInformation3.parameterTypes.length <= 0 || methodInformation3.parameterTypes[0] != Class.class) ? 0 : 1;
        while (i < methodInformation3.parameterTypes.length) {
            if (methodInformation3.parameterRaw[i] || Enum.class.isAssignableFrom(methodInformation3.parameterTypes[i])) {
                str = str13;
            } else {
                Annotation by = by(methodInformation3, i);
                String cast = cast(methodInformation3, i);
                String[] cppCastTypeName = cppCastTypeName(methodInformation3.parameterTypes[i], methodInformation3.parameterAnnotations[i]);
                AdapterInformation adapterInformation = adapterInformation(z, methodInformation3, i);
                if ("void*".equals(cppCastTypeName[c]) && !methodInformation3.parameterTypes[i].isAnnotationPresent(Opaque.class)) {
                    cppCastTypeName[c] = "char*";
                }
                String str14 = (cast.contains(" const *") || cast.startsWith("(const ")) ? "JNI_ABORT" : "0";
                boolean isAssignableFrom = Pointer.class.isAssignableFrom(methodInformation3.parameterTypes[i]);
                String str15 = "    if (rptr";
                String str16 = str14;
                String str17 = ".owner";
                String str18 = "    if (arg";
                String str19 = " = adapter";
                Annotation annotation = by;
                String str20 = "    void* rowner";
                String str21 = str12;
                String str22 = "adapter";
                String str23 = str13;
                String str24 = " = ";
                String str25 = " rptr";
                String str26 = ") {";
                String str27 = "    ";
                String str28 = "ptr";
                if (!isAssignableFrom) {
                    String str29 = str22;
                    String str30 = " != ";
                    String str31 = str15;
                    String str32 = str17;
                    String str33 = str19;
                    String str34 = str27;
                    String str35 = str18;
                    str12 = str21;
                    str = str23;
                    methodInformation2 = methodInformation;
                    String str36 = str25;
                    if (methodInformation2.parameterTypes[i] == String.class) {
                        this.out.println("    JavaCPP_releaseStringBytes(env, arg" + i + ", ptr" + i + str);
                    } else {
                        String str37 = ", ptr";
                        String str38 = ", (j";
                        String str39 = " != NULL) ";
                        String str40 = "env->Release";
                        String str41 = ", ";
                        if (!methodInformation2.parameterTypes[i].isArray() || !methodInformation2.parameterTypes[i].getComponentType().isPrimitive()) {
                            methodInformation3 = methodInformation2;
                            String str42 = str36;
                            String str43 = str39;
                            String str44 = str37;
                            String str45 = str29;
                            String str46 = str20;
                            String str47 = str31;
                            String str48 = str16;
                            if (Buffer.class.isAssignableFrom(methodInformation3.parameterTypes[i]) && methodInformation3.parameterTypes[i] != Buffer.class) {
                                int i2 = 0;
                                while (adapterInformation != null && i2 < adapterInformation.argc) {
                                    PrintWriter printWriter = this.out;
                                    String str49 = str41;
                                    StringBuilder sb = new StringBuilder();
                                    String str50 = str44;
                                    sb.append(str34);
                                    sb.append(cppCastTypeName[0]);
                                    sb.append(str42);
                                    int i3 = i + i2;
                                    sb.append(i3);
                                    sb.append(cppCastTypeName[1]);
                                    sb.append(str24);
                                    sb.append(cast);
                                    sb.append(str45);
                                    sb.append(i);
                                    sb.append(";");
                                    printWriter.println(sb.toString());
                                    PrintWriter printWriter2 = this.out;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str46);
                                    sb2.append(i3);
                                    sb2.append(str33);
                                    sb2.append(i);
                                    sb2.append(str32);
                                    if (i2 > 0) {
                                        StringBuilder sb3 = new StringBuilder();
                                        str2 = str24;
                                        sb3.append(i2 + 1);
                                        sb3.append(";");
                                        str3 = sb3.toString();
                                    } else {
                                        str2 = str24;
                                        str3 = ";";
                                    }
                                    sb2.append(str3);
                                    printWriter2.println(sb2.toString());
                                    PrintWriter printWriter3 = this.out;
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(str47);
                                    sb4.append(i3);
                                    String str51 = str30;
                                    sb4.append(str51);
                                    sb4.append(cast);
                                    sb4.append(str28);
                                    sb4.append(i3);
                                    sb4.append(str26);
                                    printWriter3.println(sb4.toString());
                                    this.out.println("        " + adapterInformation.name + "::deallocate(rowner" + i3 + str);
                                    this.out.println(str12);
                                    i2++;
                                    MethodInformation methodInformation4 = methodInformation;
                                    str44 = str50;
                                    str47 = str47;
                                    str24 = str2;
                                    str41 = str49;
                                    str30 = str51;
                                }
                                String str52 = str44;
                                String str53 = str41;
                                this.out.print("    if (arr" + i + str43);
                                methodInformation3 = methodInformation;
                                String simpleName = methodInformation3.parameterTypes[i].getSimpleName();
                                c = 0;
                                String substring = simpleName.substring(0, simpleName.length() - 6);
                                String str54 = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                                if (methodInformation3.criticalRegion) {
                                    this.out.println("env->ReleasePrimitiveArrayCritical(arr" + i + str52 + i + " - position" + i + str53 + str48 + str);
                                } else {
                                    this.out.println(str40 + substring + "ArrayElements(arr" + i + str38 + str54 + "*)(ptr" + i + " - position" + i + "), " + str48 + str);
                                }
                                i++;
                                str13 = str;
                                z = true;
                            }
                            c = 0;
                        } else {
                            int i4 = 0;
                            while (true) {
                                if (adapterInformation == null) {
                                    str4 = str39;
                                    str5 = str41;
                                    break;
                                }
                                str5 = str41;
                                if (i4 >= adapterInformation.argc) {
                                    str4 = str39;
                                    break;
                                }
                                PrintWriter printWriter4 = this.out;
                                StringBuilder sb5 = new StringBuilder();
                                String str55 = str39;
                                sb5.append(str34);
                                sb5.append(cppCastTypeName[0]);
                                sb5.append(str36);
                                int i5 = i + i4;
                                sb5.append(i5);
                                String str56 = str36;
                                sb5.append(cppCastTypeName[1]);
                                sb5.append(str24);
                                sb5.append(cast);
                                String str57 = str29;
                                sb5.append(str57);
                                sb5.append(i);
                                sb5.append(";");
                                printWriter4.println(sb5.toString());
                                PrintWriter printWriter5 = this.out;
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append(str20);
                                sb6.append(i5);
                                String str58 = str20;
                                sb6.append(str33);
                                sb6.append(i);
                                String str59 = str32;
                                sb6.append(str59);
                                if (i4 > 0) {
                                    str32 = str59;
                                    StringBuilder sb7 = new StringBuilder();
                                    str6 = str57;
                                    sb7.append(i4 + 1);
                                    sb7.append(";");
                                    str7 = sb7.toString();
                                } else {
                                    str6 = str57;
                                    str32 = str59;
                                    str7 = ";";
                                }
                                sb6.append(str7);
                                printWriter5.println(sb6.toString());
                                this.out.println(str31 + i5 + str30 + cast + str28 + i5 + str26);
                                PrintWriter printWriter6 = this.out;
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("        ");
                                sb8.append(adapterInformation.name);
                                sb8.append("::deallocate(rowner");
                                sb8.append(i5);
                                sb8.append(str);
                                printWriter6.println(sb8.toString());
                                this.out.println(str12);
                                i4++;
                                MethodInformation methodInformation5 = methodInformation;
                                str20 = str58;
                                str41 = str5;
                                str39 = str55;
                                str36 = str56;
                                str29 = str6;
                            }
                            this.out.print(str35 + i + str4);
                            methodInformation3 = methodInformation;
                            if (methodInformation3.criticalRegion || methodInformation3.valueGetter || methodInformation3.valueSetter || methodInformation3.memberGetter || methodInformation3.memberSetter) {
                                this.out.println("env->ReleasePrimitiveArrayCritical(arg" + i + str37 + i + str5 + str16 + str);
                            } else {
                                String name = methodInformation3.parameterTypes[i].getComponentType().getName();
                                PrintWriter printWriter7 = this.out;
                                printWriter7.println(str40 + (Character.toUpperCase(name.charAt(0)) + name.substring(1)) + "ArrayElements(arg" + i + str38 + name + "*)ptr" + i + str5 + str16 + str);
                            }
                            c = 0;
                        }
                    }
                } else if (adapterInformation != null) {
                    String str60 = " != ";
                    int i6 = 0;
                    while (i6 < adapterInformation.argc) {
                        PrintWriter printWriter8 = this.out;
                        AdapterInformation adapterInformation2 = adapterInformation;
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append(str27);
                        String str61 = str27;
                        sb9.append(cppCastTypeName[0]);
                        sb9.append(str25);
                        int i7 = i + i6;
                        sb9.append(i7);
                        String str62 = str25;
                        sb9.append(cppCastTypeName[1]);
                        sb9.append(str24);
                        sb9.append(cast);
                        sb9.append(str22);
                        sb9.append(i);
                        sb9.append(";");
                        printWriter8.println(sb9.toString());
                        PrintWriter printWriter9 = this.out;
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append("    jlong rsize");
                        sb10.append(i7);
                        sb10.append(" = (jlong)adapter");
                        sb10.append(i);
                        sb10.append(".size");
                        if (i6 > 0) {
                            StringBuilder sb11 = new StringBuilder();
                            str8 = str22;
                            sb11.append(i6 + 1);
                            sb11.append(";");
                            str9 = sb11.toString();
                        } else {
                            str8 = str22;
                            str9 = ";";
                        }
                        sb10.append(str9);
                        printWriter9.println(sb10.toString());
                        PrintWriter printWriter10 = this.out;
                        StringBuilder sb12 = new StringBuilder();
                        sb12.append(str20);
                        sb12.append(i7);
                        sb12.append(str19);
                        sb12.append(i);
                        sb12.append(str17);
                        if (i6 > 0) {
                            str10 = (i6 + 1) + ";";
                        } else {
                            str10 = ";";
                        }
                        sb12.append(str10);
                        printWriter10.println(sb12.toString());
                        this.out.println(str15 + i7 + str60 + cast + str28 + i7 + str26);
                        PrintWriter printWriter11 = this.out;
                        StringBuilder sb13 = new StringBuilder();
                        sb13.append("        JavaCPP_initPointer(env, arg");
                        sb13.append(i);
                        sb13.append(", rptr");
                        sb13.append(i7);
                        sb13.append(", rsize");
                        sb13.append(i7);
                        sb13.append(", rowner");
                        sb13.append(i7);
                        sb13.append(", &");
                        adapterInformation = adapterInformation2;
                        sb13.append(adapterInformation.name);
                        sb13.append("::deallocate);");
                        printWriter11.println(sb13.toString());
                        this.out.println("    } else {");
                        PrintWriter printWriter12 = this.out;
                        StringBuilder sb14 = new StringBuilder();
                        sb14.append("        env->SetLongField(arg");
                        sb14.append(i);
                        sb14.append(", JavaCPP_limitFID, rsize");
                        sb14.append(i7);
                        String str63 = str15;
                        String str64 = str62;
                        String str65 = str17;
                        String str66 = str19;
                        if (!methodInformation.parameterTypes[i].isAnnotationPresent(Opaque.class)) {
                            str11 = " + position" + i7;
                        } else {
                            str11 = "";
                        }
                        sb14.append(str11);
                        sb14.append(str23);
                        printWriter12.println(sb14.toString());
                        this.out.println(str21);
                        i6++;
                        str25 = str64;
                        str15 = str63;
                        str27 = str61;
                        str17 = str65;
                        str22 = str8;
                        str19 = str66;
                    }
                    str12 = str21;
                    str = str23;
                    methodInformation3 = methodInformation;
                    c = 0;
                } else {
                    methodInformation2 = methodInformation;
                    Annotation annotation2 = annotation;
                    str12 = str21;
                    str = str23;
                    if (((annotation2 instanceof ByPtrPtr) || (annotation2 instanceof ByPtrRef)) && !methodInformation2.valueSetter && !methodInformation2.memberSetter) {
                        if (!methodInformation2.parameterTypes[i].isAnnotationPresent(Opaque.class)) {
                            this.out.println("    ptr" + i + " -= position" + i + ";");
                        }
                        this.out.println(str18 + i + " != NULL) env->SetLongField(arg" + i + ", JavaCPP_addressFID, ptr_to_jlong(ptr" + i + "));");
                    }
                }
                methodInformation3 = methodInformation2;
                c = 0;
            }
            i++;
            str13 = str;
            z = true;
        }
    }

    /* JADX WARNING: type inference failed for: r2v40, types: [java.lang.Object[]] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0c4a  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0d5d  */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x14d0  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x1731  */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x1784  */
    /* JADX WARNING: Removed duplicated region for block: B:378:0x1791  */
    /* JADX WARNING: Removed duplicated region for block: B:431:0x14e9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void callback(java.lang.Class<?> r41, java.lang.reflect.Method r42, java.lang.String r43, int r44, boolean r45, org.bytedeco.javacpp.tools.MethodInformation r46) {
        /*
            r40 = this;
            r0 = r40
            r1 = r41
            r2 = r42
            r3 = r43
            r4 = r44
            r5 = r46
            java.lang.Class r6 = r42.getReturnType()
            java.lang.Class[] r7 = r42.getParameterTypes()
            java.lang.annotation.Annotation[] r8 = r42.getAnnotations()
            java.lang.annotation.Annotation[][] r9 = r42.getParameterAnnotations()
            java.lang.String r10 = functionClassName(r41)
            r11 = 1
            java.lang.reflect.Method[] r12 = new java.lang.reflect.Method[r11]
            r13 = 0
            r12[r13] = r2
            java.lang.String[] r12 = r0.cppFunctionTypeName(r12)
            r14 = r12[r13]
            java.lang.String r15 = "\\("
            java.lang.String[] r14 = r14.split(r15)
            r15 = 2
            java.lang.String[] r11 = new java.lang.String[r15]
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r2 = r14[r13]
            r15.append(r2)
            int r2 = r14.length
            java.lang.String r18 = ""
            r13 = 2
            if (r2 <= r13) goto L_0x0048
            java.lang.String r2 = "(*"
            goto L_0x004a
        L_0x0048:
            r2 = r18
        L_0x004a:
            r15.append(r2)
            java.lang.String r2 = r15.toString()
            r15 = 0
            r11[r15] = r2
            int r2 = r14.length
            if (r2 <= r13) goto L_0x006b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r15 = ")("
            r2.append(r15)
            r15 = r14[r13]
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            goto L_0x006d
        L_0x006b:
            r2 = r18
        L_0x006d:
            r15 = 1
            r11[r15] = r2
            int r2 = r14.length
            if (r2 <= r13) goto L_0x007b
            int r2 = r14.length
            java.lang.Object[] r2 = java.util.Arrays.copyOfRange(r14, r13, r2)
            r14 = r2
            java.lang.String[] r14 = (java.lang.String[]) r14
        L_0x007b:
            java.lang.String[] r2 = new java.lang.String[r15]
            r13 = r14[r15]
            r16 = 0
            r2[r16] = r13
            java.lang.String r2 = constValueTypeName(r2)
            r14[r15] = r2
            r2 = r12[r15]
            java.lang.String r2 = r2.substring(r15)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r42.getName()
            java.lang.String r13 = mangle(r13)
            r12.append(r13)
            java.lang.String r13 = "__"
            r12.append(r13)
            java.lang.Class[] r13 = r42.getParameterTypes()
            java.lang.String r13 = signature(r13)
            java.lang.String r13 = mangle(r13)
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            java.lang.String r13 = "}"
            java.lang.String r15 = "("
            r20 = r9
            java.lang.String r9 = "arg"
            r21 = r8
            java.lang.String r8 = "    return "
            r22 = r8
            java.lang.String r8 = "    "
            r23 = r13
            java.lang.String r13 = ");"
            r24 = r13
            java.lang.String r13 = ";"
            if (r5 == 0) goto L_0x037e
            java.lang.String r4 = " const"
            boolean r4 = r2.endsWith(r4)
            if (r4 == 0) goto L_0x00e7
            int r4 = r2.length()
            int r4 = r4 + -6
            r25 = r10
            r10 = 0
            java.lang.String r4 = r2.substring(r10, r4)
            goto L_0x00ea
        L_0x00e7:
            r25 = r10
            r4 = r2
        L_0x00ea:
            boolean r10 = r5.returnRaw
            if (r10 == 0) goto L_0x00f3
            java.lang.String[] r10 = new java.lang.String[]{r18}
            goto L_0x00f9
        L_0x00f3:
            java.lang.Class<?> r10 = r5.cls
            java.lang.String[] r10 = r0.cppTypeName(r10)
        L_0x00f9:
            java.lang.String r10 = valueTypeName(r10)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r26 = r12
            java.lang.String r12 = "JavaCPP_"
            r3.append(r12)
            java.lang.String r12 = mangle(r10)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r12 = r0.virtualMembers
            java.lang.Object r12 = r12.get(r1)
            java.util.Set r12 = (java.util.Set) r12
            if (r12 != 0) goto L_0x012c
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r12 = r0.virtualMembers
            r27 = r6
            java.util.LinkedHashSet r6 = new java.util.LinkedHashSet
            r6.<init>()
            r12.put(r1, r6)
            r12 = r6
            goto L_0x012e
        L_0x012c:
            r27 = r6
        L_0x012e:
            boolean r6 = r5.arrayAllocator
            if (r6 == 0) goto L_0x0133
            return
        L_0x0133:
            boolean r6 = r5.allocator
            if (r6 == 0) goto L_0x019e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r8)
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = " : "
            r2.append(r3)
            r2.append(r10)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r3 = 0
        L_0x0155:
            int r4 = r7.length
            if (r3 >= r4) goto L_0x0183
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            r4.append(r9)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            int r4 = r7.length
            r6 = 1
            int r4 = r4 - r6
            if (r3 >= r4) goto L_0x0180
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = ", "
            r4.append(r2)
            java.lang.String r2 = r4.toString()
        L_0x0180:
            int r3 = r3 + 1
            goto L_0x0155
        L_0x0183:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "), obj(NULL) { }"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r29 = r13
            r1 = r18
            r3 = r26
            r6 = r27
            goto L_0x0368
        L_0x019e:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r6 = r0.virtualFunctions
            java.lang.Object r6 = r6.get(r1)
            java.util.Set r6 = (java.util.Set) r6
            if (r6 != 0) goto L_0x01b3
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r6 = r0.virtualFunctions
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            r6.put(r1, r0)
            r6 = r0
        L_0x01b3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "using "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r1 = "::"
            r0.append(r1)
            r28 = r6
            java.lang.String[] r6 = r5.memberName
            r19 = 0
            r6 = r6[r19]
            r0.append(r6)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.util.Iterator r6 = r12.iterator()
        L_0x01db:
            boolean r29 = r6.hasNext()
            if (r29 == 0) goto L_0x0217
            java.lang.Object r29 = r6.next()
            r30 = r6
            r6 = r29
            java.lang.String r6 = (java.lang.String) r6
            r29 = r13
            java.lang.String r13 = "\n"
            r31 = r12
            r12 = 2
            java.lang.String[] r6 = r6.split(r13, r12)
            r13 = 0
            r6 = r6[r13]
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r8)
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            boolean r6 = r6.equals(r13)
            if (r6 == 0) goto L_0x0210
            r6 = 0
            goto L_0x021c
        L_0x0210:
            r13 = r29
            r6 = r30
            r12 = r31
            goto L_0x01db
        L_0x0217:
            r31 = r12
            r29 = r13
            r6 = 1
        L_0x021c:
            if (r6 == 0) goto L_0x0233
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r8)
            r6.append(r0)
            java.lang.String r0 = "\n    "
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            goto L_0x0234
        L_0x0233:
            r0 = r8
        L_0x0234:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            java.lang.String r0 = "virtual "
            r6.append(r0)
            r0 = 0
            r12 = r11[r0]
            r6.append(r12)
            int r12 = r14.length
            r13 = 1
            if (r12 <= r13) goto L_0x024e
            r12 = r14[r13]
            goto L_0x0250
        L_0x024e:
            r12 = r18
        L_0x0250:
            r6.append(r12)
            java.lang.String[] r12 = r5.memberName
            r12 = r12[r0]
            r6.append(r12)
            r6.append(r2)
            r12 = r11[r13]
            r6.append(r12)
            java.lang.String r12 = " JavaCPP_override;\n    "
            r6.append(r12)
            r12 = r11[r0]
            r6.append(r12)
            java.lang.String r0 = "super_"
            r6.append(r0)
            java.lang.String r0 = r5.name
            r6.append(r0)
            r6.append(r4)
            r0 = r11[r13]
            r6.append(r0)
            java.lang.String r0 = " { "
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            java.lang.reflect.Method r4 = r5.method
            java.lang.Class<org.bytedeco.javacpp.annotation.Virtual> r6 = org.bytedeco.javacpp.annotation.Virtual.class
            java.lang.annotation.Annotation r4 = r4.getAnnotation(r6)
            org.bytedeco.javacpp.annotation.Virtual r4 = (org.bytedeco.javacpp.annotation.Virtual) r4
            boolean r4 = r4.value()
            if (r4 == 0) goto L_0x02be
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r0 = "throw JavaCPP_exception(\"Cannot call pure virtual function "
            r4.append(r0)
            r4.append(r10)
            r4.append(r1)
            java.lang.String[] r0 = r5.memberName
            r6 = 0
            r0 = r0[r6]
            r4.append(r0)
            java.lang.String r0 = "().\"); }"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r6 = r27
            goto L_0x0329
        L_0x02be:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.Class r0 = java.lang.Void.TYPE
            r6 = r27
            if (r6 == r0) goto L_0x02cf
            java.lang.String r0 = "return "
            goto L_0x02d1
        L_0x02cf:
            r0 = r18
        L_0x02d1:
            r4.append(r0)
            r4.append(r10)
            r4.append(r1)
            java.lang.String[] r0 = r5.memberName
            r10 = 0
            r0 = r0[r10]
            r4.append(r0)
            r4.append(r15)
            java.lang.String r0 = r4.toString()
            r4 = 0
        L_0x02ea:
            int r10 = r7.length
            if (r4 >= r10) goto L_0x0318
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r0)
            r10.append(r9)
            r10.append(r4)
            java.lang.String r0 = r10.toString()
            int r10 = r7.length
            r12 = 1
            int r10 = r10 - r12
            if (r4 >= r10) goto L_0x0315
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r0)
            java.lang.String r0 = ", "
            r10.append(r0)
            java.lang.String r0 = r10.toString()
        L_0x0315:
            int r4 = r4 + 1
            goto L_0x02ea
        L_0x0318:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r0 = "); }"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
        L_0x0329:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r10 = 0
            r12 = r11[r10]
            r4.append(r12)
            int r12 = r14.length
            r13 = 1
            if (r12 <= r13) goto L_0x033b
            r12 = r14[r13]
            goto L_0x033d
        L_0x033b:
            r12 = r18
        L_0x033d:
            r4.append(r12)
            r4.append(r3)
            r4.append(r1)
            java.lang.String[] r1 = r5.memberName
            r1 = r1[r10]
            r4.append(r1)
            r4.append(r2)
            r1 = r11[r13]
            r4.append(r1)
            java.lang.String r1 = " {"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3 = r26
            r2 = r28
            r2.add(r3)
            r2 = r0
            r12 = r31
        L_0x0368:
            r12.add(r2)
            r0 = r43
            r5 = r44
            r2 = r1
            r26 = r3
            r17 = r8
            r10 = r23
            r8 = r24
            r12 = r25
            r1 = r40
            goto L_0x05e1
        L_0x037e:
            r0 = r3
            r25 = r10
            r3 = r12
            r29 = r13
            r1 = r40
            if (r0 == 0) goto L_0x05d3
            java.util.Map<java.lang.String, java.lang.String> r4 = r1.callbacks
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "static "
            r10.append(r12)
            r12 = r25
            r10.append(r12)
            java.lang.String r13 = " "
            r10.append(r13)
            r10.append(r12)
            java.lang.String r13 = "_instances["
            r10.append(r13)
            r13 = r44
            r10.append(r13)
            r26 = r3
            java.lang.String r3 = "];"
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            r4.put(r12, r3)
            java.lang.Class<org.bytedeco.javacpp.annotation.Convention> r3 = org.bytedeco.javacpp.annotation.Convention.class
            r4 = r41
            java.lang.annotation.Annotation r3 = r4.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Convention r3 = (org.bytedeco.javacpp.annotation.Convention) r3
            if (r3 == 0) goto L_0x0411
            java.lang.String r10 = r3.extern()
            java.lang.String r4 = "C"
            boolean r4 = r10.equals(r4)
            if (r4 != 0) goto L_0x0411
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r5 = "extern \""
            r10.append(r5)
            java.lang.String r5 = r3.extern()
            r10.append(r5)
            java.lang.String r5 = "\" {"
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out2
            if (r4 == 0) goto L_0x0411
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "extern \""
            r5.append(r10)
            java.lang.String r10 = r3.extern()
            r5.append(r10)
            java.lang.String r10 = "\" {"
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
        L_0x0411:
            r4 = 0
        L_0x0412:
            if (r4 >= r13) goto L_0x0508
            java.io.PrintWriter r5 = r1.out2
            if (r5 == 0) goto L_0x045a
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r17 = r8
            java.lang.String r8 = "JNIIMPORT "
            r10.append(r8)
            r8 = 0
            r13 = r11[r8]
            r10.append(r13)
            int r8 = r14.length
            r13 = 1
            if (r8 <= r13) goto L_0x0431
            r8 = r14[r13]
            goto L_0x0433
        L_0x0431:
            r8 = r18
        L_0x0433:
            r10.append(r8)
            r10.append(r0)
            if (r4 <= 0) goto L_0x0440
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            goto L_0x0442
        L_0x0440:
            r8 = r18
        L_0x0442:
            r10.append(r8)
            r10.append(r2)
            r8 = r11[r13]
            r10.append(r8)
            r8 = r29
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            r5.println(r10)
            goto L_0x045e
        L_0x045a:
            r17 = r8
            r8 = r29
        L_0x045e:
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "JNIEXPORT "
            r10.append(r13)
            r29 = r8
            r13 = 0
            r8 = r11[r13]
            r10.append(r8)
            int r8 = r14.length
            r13 = 1
            if (r8 <= r13) goto L_0x0479
            r8 = r14[r13]
            goto L_0x047b
        L_0x0479:
            r8 = r18
        L_0x047b:
            r10.append(r8)
            r10.append(r0)
            if (r4 <= 0) goto L_0x0488
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            goto L_0x048a
        L_0x0488:
            r8 = r18
        L_0x048a:
            r10.append(r8)
            r10.append(r2)
            r8 = r11[r13]
            r10.append(r8)
            java.lang.String r8 = " {"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.Class r10 = java.lang.Void.TYPE
            if (r6 == r10) goto L_0x04af
            r10 = r22
            goto L_0x04b1
        L_0x04af:
            r10 = r17
        L_0x04b1:
            r8.append(r10)
            r8.append(r12)
            java.lang.String r10 = "_instances["
            r8.append(r10)
            r8.append(r4)
            java.lang.String r10 = "]("
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r5.print(r8)
            r5 = 0
        L_0x04cc:
            int r8 = r7.length
            if (r5 >= r8) goto L_0x04f2
            java.io.PrintWriter r8 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r9)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            r8.print(r10)
            int r8 = r7.length
            r10 = 1
            int r8 = r8 - r10
            if (r5 >= r8) goto L_0x04ef
            java.io.PrintWriter r8 = r1.out
            java.lang.String r10 = ", "
            r8.print(r10)
        L_0x04ef:
            int r5 = r5 + 1
            goto L_0x04cc
        L_0x04f2:
            java.io.PrintWriter r5 = r1.out
            r8 = r24
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            r10 = r23
            r5.println(r10)
            int r4 = r4 + 1
            r13 = r44
            r8 = r17
            goto L_0x0412
        L_0x0508:
            r17 = r8
            r10 = r23
            r8 = r24
            if (r3 == 0) goto L_0x0528
            java.lang.String r3 = r3.extern()
            java.lang.String r4 = "C"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0528
            java.io.PrintWriter r3 = r1.out
            r3.println(r10)
            java.io.PrintWriter r3 = r1.out2
            if (r3 == 0) goto L_0x0528
            r3.println(r10)
        L_0x0528:
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "static "
            r4.append(r5)
            r5 = 0
            r13 = r11[r5]
            r4.append(r13)
            r4.append(r15)
            int r5 = r14.length
            r13 = 1
            if (r5 <= r13) goto L_0x0544
            r5 = r14[r13]
            goto L_0x0546
        L_0x0544:
            r5 = r18
        L_0x0546:
            r4.append(r5)
            java.lang.String r5 = "*"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = "s["
            r4.append(r5)
            r5 = r44
            r4.append(r5)
            java.lang.String r13 = "])"
            r4.append(r13)
            r4.append(r2)
            r13 = 1
            r14 = r11[r13]
            r4.append(r14)
            java.lang.String r13 = " = {"
            r4.append(r13)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            r3 = 0
        L_0x0576:
            if (r3 >= r5) goto L_0x05a6
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "        "
            r13.append(r14)
            r13.append(r0)
            if (r3 <= 0) goto L_0x058e
            java.lang.Integer r14 = java.lang.Integer.valueOf(r3)
            goto L_0x0590
        L_0x058e:
            r14 = r18
        L_0x0590:
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r4.print(r13)
            int r3 = r3 + 1
            if (r3 >= r5) goto L_0x0576
            java.io.PrintWriter r4 = r1.out
            java.lang.String r13 = ","
            r4.println(r13)
            goto L_0x0576
        L_0x05a6:
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = " };"
            r3.println(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 0
            r13 = r11[r4]
            r3.append(r13)
            r3.append(r12)
            java.lang.String r4 = "::operator()"
            r3.append(r4)
            r3.append(r2)
            r2 = 1
            r4 = r11[r2]
            r3.append(r4)
            java.lang.String r2 = " {"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            goto L_0x05e1
        L_0x05d3:
            r5 = r44
            r26 = r3
            r17 = r8
            r10 = r23
            r8 = r24
            r12 = r25
            r2 = r18
        L_0x05e1:
            if (r45 != 0) goto L_0x05e4
            return
        L_0x05e4:
            java.io.PrintWriter r3 = r1.out
            r3.println(r2)
            java.lang.Class r2 = java.lang.Void.TYPE
            if (r6 == r2) goto L_0x0624
            java.io.PrintWriter r2 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = r17
            r3.append(r4)
            java.lang.String r13 = jniTypeName(r6)
            r3.append(r13)
            java.lang.String r13 = " rarg = 0;"
            r3.append(r13)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.lang.String r2 = "rarg = "
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r6 != r3) goto L_0x0628
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "(jstring)"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            goto L_0x0628
        L_0x0624:
            r4 = r17
            r2 = r18
        L_0x0628:
            r3 = r21
            java.lang.String r13 = r1.cast((java.lang.Class<?>) r6, (java.lang.annotation.Annotation[]) r3)
            java.lang.annotation.Annotation r14 = r1.by(r3)
            java.lang.String[] r17 = r1.cppTypeName(r6)
            r21 = r11
            java.lang.String r11 = valueTypeName(r17)
            r45 = r14
            r14 = 0
            org.bytedeco.javacpp.tools.AdapterInformation r3 = r1.adapterInformation((boolean) r14, (java.lang.String) r11, (java.lang.annotation.Annotation[]) r3)
            boolean r11 = noException(r41, r42)
            r14 = 1
            r11 = r11 ^ r14
            if (r11 == 0) goto L_0x0655
            java.io.PrintWriter r14 = r1.out
            r23 = r13
            java.lang.String r13 = "    jthrowable exc = NULL;"
            r14.println(r13)
            goto L_0x0657
        L_0x0655:
            r23 = r13
        L_0x0657:
            java.io.PrintWriter r13 = r1.out
            java.lang.String r14 = "    JNIEnv* env;"
            r13.println(r14)
            java.io.PrintWriter r13 = r1.out
            java.lang.String r14 = "    bool attached = JavaCPP_getEnv(&env);"
            r13.println(r14)
            java.io.PrintWriter r13 = r1.out
            java.lang.String r14 = "    if (env == NULL) {"
            r13.println(r14)
            java.io.PrintWriter r13 = r1.out
            java.lang.String r14 = "        goto end;"
            r13.println(r14)
            java.io.PrintWriter r13 = r1.out
            java.lang.String r14 = "    }"
            r13.println(r14)
            java.io.PrintWriter r13 = r1.out
            r24 = r3
            java.lang.String r3 = "{"
            r13.println(r3)
            int r3 = r7.length
            java.lang.String r13 = "    } else {"
            r25 = r10
            java.lang.String r10 = ".\");"
            r27 = r11
            java.lang.String r11 = ")"
            r28 = r2
            java.lang.String r2 = " != NULL) {"
            if (r3 <= 0) goto L_0x1007
            java.io.PrintWriter r3 = r1.out
            r30 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r31 = r12
            java.lang.String r12 = "    jvalue args["
            r6.append(r12)
            int r12 = r7.length
            r6.append(r12)
            java.lang.String r12 = "];"
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            r3.println(r6)
            r3 = 0
        L_0x06b5:
            int r6 = r7.length
            if (r3 >= r6) goto L_0x0fff
            r6 = r20[r3]
            java.lang.annotation.Annotation r6 = r1.by(r6)
            r12 = r7[r3]
            boolean r12 = r12.isPrimitive()
            java.lang.String r5 = "    args["
            if (r12 == 0) goto L_0x0730
            java.io.PrintWriter r12 = r1.out
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            r0.append(r3)
            java.lang.String r5 = "]."
            r0.append(r5)
            r32 = r9
            r5 = 1
            java.lang.Class[] r9 = new java.lang.Class[r5]
            r5 = r7[r3]
            r19 = 0
            r9[r19] = r5
            java.lang.String r5 = signature(r9)
            java.lang.String r5 = r5.toLowerCase()
            r0.append(r5)
            java.lang.String r5 = " = ("
            r0.append(r5)
            r5 = r7[r3]
            java.lang.String r5 = jniTypeName(r5)
            r0.append(r5)
            r0.append(r11)
            boolean r5 = r6 instanceof org.bytedeco.javacpp.annotation.ByPtr
            if (r5 != 0) goto L_0x070d
            boolean r5 = r6 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r5 == 0) goto L_0x070a
            goto L_0x070d
        L_0x070a:
            r5 = r32
            goto L_0x070f
        L_0x070d:
            java.lang.String r5 = "*arg"
        L_0x070f:
            r0.append(r5)
            r0.append(r3)
            r9 = r29
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            r12.println(r0)
            r38 = r4
            r0 = r8
            r33 = r10
            r34 = r11
            r29 = r13
            r35 = r15
            r15 = r42
            goto L_0x0fe8
        L_0x0730:
            r32 = r9
            r9 = r29
            java.lang.Class<java.lang.Enum> r0 = java.lang.Enum.class
            r12 = r7[r3]
            boolean r0 = r0.isAssignableFrom(r12)
            java.lang.String r12 = "].l = obj"
            if (r0 == 0) goto L_0x081d
            r0 = 1
            r1.accessesEnums = r0
            r6 = r7[r3]
            java.lang.String r6 = r1.enumValueType(r6)
            if (r6 == 0) goto L_0x080e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r29 = r13
            r13 = 0
            char r33 = r6.charAt(r13)
            char r13 = java.lang.Character.toUpperCase(r33)
            r0.append(r13)
            r33 = r10
            r13 = 1
            java.lang.String r10 = r6.substring(r13)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r34 = r11
            java.lang.String r11 = "    jobject obj"
            r13.append(r11)
            r13.append(r3)
            java.lang.String r11 = " = JavaCPP_createPointer(env, "
            r13.append(r11)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r11 = r1.jclasses
            r35 = r15
            r15 = r7[r3]
            int r11 = r11.index(r15)
            r13.append(r11)
            r13.append(r8)
            java.lang.String r11 = r13.toString()
            r10.println(r11)
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r5)
            r11.append(r3)
            r11.append(r12)
            r11.append(r3)
            r11.append(r9)
            java.lang.String r5 = r11.toString()
            r10.println(r5)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "    if (obj"
            r10.append(r11)
            r10.append(r3)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            r5.println(r10)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "        env->Set"
            r10.append(r11)
            r10.append(r0)
            java.lang.String r0 = "Field(obj"
            r10.append(r0)
            r10.append(r3)
            java.lang.String r0 = ", JavaCPP_"
            r10.append(r0)
            r10.append(r6)
            java.lang.String r0 = "ValueFID, (j"
            r10.append(r0)
            r10.append(r6)
            java.lang.String r0 = ")arg"
            r10.append(r0)
            r10.append(r3)
            r10.append(r8)
            java.lang.String r0 = r10.toString()
            r5.println(r0)
            java.io.PrintWriter r0 = r1.out
            r0.println(r14)
            goto L_0x0816
        L_0x080e:
            r33 = r10
            r34 = r11
            r29 = r13
            r35 = r15
        L_0x0816:
            r15 = r42
            r38 = r4
            r0 = r8
            goto L_0x0fe8
        L_0x081d:
            r33 = r10
            r34 = r11
            r29 = r13
            r35 = r15
            r0 = r7[r3]
            java.lang.String[] r0 = r1.cppTypeName(r0)
            java.lang.String r10 = valueTypeName(r0)
            r11 = r20[r3]
            r13 = 0
            org.bytedeco.javacpp.tools.AdapterInformation r11 = r1.adapterInformation((boolean) r13, (java.lang.String) r10, (java.lang.annotation.Annotation[]) r11)
            if (r11 == 0) goto L_0x0867
            r13 = 1
            r1.usesAdapters = r13
            java.io.PrintWriter r13 = r1.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r4)
            r36 = r12
            java.lang.String r12 = r11.name
            r15.append(r12)
            java.lang.String r12 = " adapter"
            r15.append(r12)
            r15.append(r3)
            java.lang.String r12 = "(arg"
            r15.append(r12)
            r15.append(r3)
            r15.append(r8)
            java.lang.String r12 = r15.toString()
            r13.println(r12)
            goto L_0x0869
        L_0x0867:
            r36 = r12
        L_0x0869:
            java.lang.Class<org.bytedeco.javacpp.Pointer> r12 = org.bytedeco.javacpp.Pointer.class
            r13 = r7[r3]
            boolean r12 = r12.isAssignableFrom(r13)
            java.lang.String r13 = "    if (ptr"
            if (r12 != 0) goto L_0x089f
            java.lang.Class<java.nio.Buffer> r12 = java.nio.Buffer.class
            r15 = r7[r3]
            boolean r12 = r12.isAssignableFrom(r15)
            if (r12 != 0) goto L_0x089f
            r12 = r7[r3]
            boolean r12 = r12.isArray()
            if (r12 == 0) goto L_0x0894
            r12 = r7[r3]
            java.lang.Class r12 = r12.getComponentType()
            boolean r12 = r12.isPrimitive()
            if (r12 == 0) goto L_0x0894
            goto L_0x089f
        L_0x0894:
            r15 = r42
            r38 = r4
            r37 = r5
            r0 = r8
            r8 = r33
            goto L_0x0b3e
        L_0x089f:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r15 = r35
            r12.append(r15)
            r19 = 0
            r15 = r0[r19]
            r12.append(r15)
            r37 = r5
            r15 = 1
            r5 = r0[r15]
            r12.append(r5)
            r5 = r34
            r12.append(r5)
            java.lang.String r12 = r12.toString()
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r15 = org.bytedeco.javacpp.FunctionPointer.class
            r5 = r7[r3]
            boolean r5 = r15.isAssignableFrom(r5)
            if (r5 == 0) goto L_0x08f4
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r5 = r1.functions
            r10 = r7[r3]
            r5.index(r10)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r10 = r7[r3]
            java.lang.String r10 = functionClassName(r10)
            r5.append(r10)
            java.lang.String r10 = "*"
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r10 = 0
            r0[r10] = r5
            r5 = 1
            r0[r5] = r18
            java.lang.String r10 = valueTypeName(r0)
            goto L_0x0913
        L_0x08f4:
            java.util.Map<java.lang.Class, java.util.Set<java.lang.String>> r5 = r1.virtualFunctions
            r15 = r7[r3]
            boolean r5 = r5.containsKey(r15)
            if (r5 == 0) goto L_0x0913
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r15 = "JavaCPP_"
            r5.append(r15)
            java.lang.String r10 = mangle(r10)
            r5.append(r10)
            java.lang.String r10 = r5.toString()
        L_0x0913:
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r4)
            r38 = r7[r3]
            r39 = r8
            java.lang.String r8 = jniTypeName(r38)
            r15.append(r8)
            java.lang.String r8 = " obj"
            r15.append(r8)
            r15.append(r3)
            java.lang.String r8 = " = NULL;"
            r15.append(r8)
            java.lang.String r8 = r15.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            r38 = r4
            r15 = 0
            r4 = r0[r15]
            r8.append(r4)
            java.lang.String r4 = " ptr"
            r8.append(r4)
            r8.append(r3)
            r4 = 1
            r15 = r0[r4]
            r8.append(r15)
            java.lang.String r4 = " = NULL;"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r5.println(r4)
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r4 = org.bytedeco.javacpp.FunctionPointer.class
            r5 = r7[r3]
            boolean r4 = r4.isAssignableFrom(r5)
            java.lang.String r5 = "    ptr"
            if (r4 == 0) goto L_0x09e2
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r3)
            java.lang.String r5 = " = new (std::nothrow) "
            r4.append(r5)
            r4.append(r10)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r13)
            r4.append(r3)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "        ptr"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r5 = "->ptr = "
            r4.append(r5)
            r4.append(r12)
            java.lang.String r5 = "&arg"
            r4.append(r5)
            r4.append(r3)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            r0.println(r14)
        L_0x09d8:
            r15 = r42
            r8 = r33
            r0 = r39
        L_0x09de:
            r33 = r10
            goto L_0x0b3c
        L_0x09e2:
            if (r11 == 0) goto L_0x0a04
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r3)
            java.lang.String r5 = " = adapter"
            r4.append(r5)
            r4.append(r3)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r0.println(r4)
            goto L_0x09d8
        L_0x0a04:
            boolean r4 = r6 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r4 == 0) goto L_0x0a55
            r8 = r7[r3]
            java.lang.Class<org.bytedeco.javacpp.Pointer> r15 = org.bytedeco.javacpp.Pointer.class
            if (r8 == r15) goto L_0x0a55
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r5)
            r8.append(r3)
            r5 = r7[r3]
            r15 = r42
            boolean r5 = noException(r5, r15)
            if (r5 == 0) goto L_0x0a28
            java.lang.String r5 = " = new (std::nothrow) "
            goto L_0x0a2a
        L_0x0a28:
            java.lang.String r5 = " = new "
        L_0x0a2a:
            r8.append(r5)
            r8.append(r10)
            r5 = 1
            r0 = r0[r5]
            r8.append(r0)
            java.lang.String r0 = "(*"
            r8.append(r0)
            r8.append(r12)
            java.lang.String r0 = "&arg"
            r8.append(r0)
            r8.append(r3)
            r0 = r39
            r8.append(r0)
            java.lang.String r5 = r8.toString()
            r4.println(r5)
            r8 = r33
            goto L_0x09de
        L_0x0a55:
            r15 = r42
            r0 = r39
            if (r4 != 0) goto L_0x0b0d
            boolean r4 = r6 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r4 == 0) goto L_0x0a61
            goto L_0x0b0d
        L_0x0a61:
            boolean r4 = r6 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r4 == 0) goto L_0x0adf
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "    if (arg"
            r5.append(r8)
            r5.append(r3)
            java.lang.String r8 = " == NULL) {"
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "        JavaCPP_log(\"Pointer address of argument "
            r5.append(r8)
            r5.append(r3)
            java.lang.String r8 = " is NULL in callback for "
            r5.append(r8)
            java.lang.String r8 = r41.getCanonicalName()
            r5.append(r8)
            r8 = r33
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            r5 = r29
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r33 = r10
            java.lang.String r10 = "        ptr"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = " = "
            r5.append(r10)
            r5.append(r12)
            java.lang.String r10 = "*arg"
            r5.append(r10)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            goto L_0x0b3c
        L_0x0adf:
            r4 = r29
            r8 = r33
            r33 = r10
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r3)
            java.lang.String r5 = " = "
            r4.append(r5)
            r4.append(r12)
            r5 = r32
            r4.append(r5)
            r4.append(r3)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r10.println(r4)
            goto L_0x0b3c
        L_0x0b0d:
            r4 = r32
            r8 = r33
            r33 = r10
            java.io.PrintWriter r10 = r1.out
            r32 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r3)
            java.lang.String r5 = " = "
            r4.append(r5)
            r4.append(r12)
            java.lang.String r5 = "&arg"
            r4.append(r5)
            r4.append(r3)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r10.println(r4)
        L_0x0b3c:
            r10 = r33
        L_0x0b3e:
            java.lang.String r4 = "    jlong size"
            if (r11 == 0) goto L_0x0bb9
            r5 = r7[r3]
            java.lang.Class<java.lang.String> r12 = java.lang.String.class
            if (r5 == r12) goto L_0x0bb4
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            r12.append(r3)
            r33 = r8
            java.lang.String r8 = " = (jlong)adapter"
            r12.append(r8)
            r12.append(r3)
            java.lang.String r8 = ".size;"
            r12.append(r8)
            java.lang.String r8 = r12.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r12 = "    void* owner"
            r8.append(r12)
            r8.append(r3)
            java.lang.String r12 = " = adapter"
            r8.append(r12)
            r8.append(r3)
            java.lang.String r12 = ".owner;"
            r8.append(r12)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r12 = "    void (*deallocator"
            r8.append(r12)
            r8.append(r3)
            java.lang.String r12 = ")(void*) = &"
            r8.append(r12)
            java.lang.String r12 = r11.name
            r8.append(r12)
            java.lang.String r12 = "::deallocate;"
            r8.append(r12)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            goto L_0x0bb6
        L_0x0bb4:
            r33 = r8
        L_0x0bb6:
            r5 = 1
            goto L_0x0c40
        L_0x0bb9:
            r33 = r8
            boolean r5 = r6 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r5 == 0) goto L_0x0bc5
            r5 = r7[r3]
            java.lang.Class<org.bytedeco.javacpp.Pointer> r8 = org.bytedeco.javacpp.Pointer.class
            if (r5 != r8) goto L_0x0bcf
        L_0x0bc5:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r5 = org.bytedeco.javacpp.FunctionPointer.class
            r8 = r7[r3]
            boolean r5 = r5.isAssignableFrom(r8)
            if (r5 == 0) goto L_0x0c3f
        L_0x0bcf:
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            r8.append(r3)
            java.lang.String r12 = " = 1;"
            r8.append(r12)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r12 = "    void* owner"
            r8.append(r12)
            r8.append(r3)
            java.lang.String r12 = " = ptr"
            r8.append(r12)
            r8.append(r3)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r12 = "    void (*deallocator"
            r8.append(r12)
            r8.append(r3)
            java.lang.String r12 = ")(void*) = &JavaCPP_"
            r8.append(r12)
            r12 = r7[r3]
            java.lang.String r12 = r12.getName()
            java.lang.String r12 = mangle(r12)
            r8.append(r12)
            java.lang.String r12 = "_deallocate;"
            r8.append(r12)
            java.lang.String r8 = r8.toString()
            r5.println(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r5 = r1.deallocators
            r8 = r7[r3]
            r5.index(r8)
            goto L_0x0bb6
        L_0x0c3f:
            r5 = 0
        L_0x0c40:
            java.lang.Class<org.bytedeco.javacpp.Pointer> r8 = org.bytedeco.javacpp.Pointer.class
            r12 = r7[r3]
            boolean r8 = r8.isAssignableFrom(r12)
            if (r8 == 0) goto L_0x0d5d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "    obj"
            r4.append(r8)
            r4.append(r3)
            java.lang.String r8 = " = JavaCPP_createPointer(env, "
            r4.append(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r1.jclasses
            r11 = r7[r3]
            int r8 = r8.index(r11)
            r4.append(r8)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r8 = r20[r3]
            r11 = 1
            org.bytedeco.javacpp.tools.AdapterInformation r8 = r1.adapterInformation((boolean) r11, (java.lang.String) r10, (java.lang.annotation.Annotation[]) r8)
            if (r8 != 0) goto L_0x0cb5
            boolean r8 = r6 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r8 != 0) goto L_0x0cb5
            boolean r6 = r6 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r6 == 0) goto L_0x0c80
            goto L_0x0cb5
        L_0x0c80:
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r13)
            r8.append(r3)
            java.lang.String r10 = " != NULL) { "
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r6.println(r8)
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r10 = r38
            r8.append(r10)
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r6.println(r4)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            goto L_0x0cbc
        L_0x0cb5:
            r10 = r38
            java.io.PrintWriter r6 = r1.out
            r6.println(r4)
        L_0x0cbc:
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "    if (obj"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = " != NULL) { "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            if (r5 == 0) goto L_0x0d13
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "        JavaCPP_initPointer(env, obj"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = ", ptr"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = ", size"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = ", owner"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = ", deallocator"
            r5.append(r6)
            r5.append(r3)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            goto L_0x0d36
        L_0x0d13:
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "        env->SetLongField(obj"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = ", JavaCPP_addressFID, ptr_to_jlong(ptr"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = "));"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
        L_0x0d36:
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = r37
            r5.append(r6)
            r5.append(r3)
            r8 = r36
            r5.append(r8)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            goto L_0x0db4
        L_0x0d5d:
            r8 = r36
            r6 = r37
            r10 = r38
            r5 = r7[r3]
            java.lang.Class<java.lang.String> r12 = java.lang.String.class
            if (r5 != r12) goto L_0x0db8
            r5 = 1
            r1.passesStrings = r5
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r12 = "    jstring obj"
            r5.append(r12)
            r5.append(r3)
            java.lang.String r12 = " = JavaCPP_createString(env, (const char*)"
            r5.append(r12)
            if (r11 == 0) goto L_0x0d85
            java.lang.String r11 = "adapter"
            goto L_0x0d87
        L_0x0d85:
            r11 = r32
        L_0x0d87:
            r5.append(r11)
            r5.append(r3)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            r5.append(r3)
            r5.append(r8)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
        L_0x0db4:
            r38 = r10
            goto L_0x0fe8
        L_0x0db8:
            r5 = r7[r3]
            boolean r5 = r5.isArray()
            if (r5 == 0) goto L_0x0f10
            r5 = r7[r3]
            java.lang.Class r5 = r5.getComponentType()
            boolean r5 = r5.isPrimitive()
            if (r5 == 0) goto L_0x0f10
            if (r11 != 0) goto L_0x0def
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            r12.append(r3)
            java.lang.String r4 = " = ptr"
            r12.append(r4)
            r12.append(r3)
            java.lang.String r4 = " != NULL ? 1 : 0;"
            r12.append(r4)
            java.lang.String r4 = r12.toString()
            r5.println(r4)
        L_0x0def:
            r4 = r7[r3]
            java.lang.Class r4 = r4.getComponentType()
            java.lang.String r4 = r4.getName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r12 = 0
            char r36 = r4.charAt(r12)
            char r12 = java.lang.Character.toUpperCase(r36)
            r5.append(r12)
            r38 = r10
            r12 = 1
            java.lang.String r10 = r4.substring(r12)
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r13)
            r12.append(r3)
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            r10.println(r12)
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "        obj"
            r12.append(r13)
            r12.append(r3)
            java.lang.String r13 = " = env->New"
            r12.append(r13)
            r12.append(r5)
            java.lang.String r13 = "Array(size"
            r12.append(r13)
            r12.append(r3)
            java.lang.String r13 = " < INT_MAX ? size"
            r12.append(r13)
            r12.append(r3)
            java.lang.String r13 = " : INT_MAX);"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r10.println(r12)
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "        env->Set"
            r12.append(r13)
            r12.append(r5)
            java.lang.String r5 = "ArrayRegion(obj"
            r12.append(r5)
            r12.append(r3)
            java.lang.String r5 = ", 0, size"
            r12.append(r5)
            r12.append(r3)
            java.lang.String r5 = " < INT_MAX ? size"
            r12.append(r5)
            r12.append(r3)
            java.lang.String r5 = " : INT_MAX, (j"
            r12.append(r5)
            r12.append(r4)
            java.lang.String r4 = "*)ptr"
            r12.append(r4)
            r12.append(r3)
            r12.append(r0)
            java.lang.String r4 = r12.toString()
            r10.println(r4)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            if (r11 == 0) goto L_0x0ef1
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "    if (deallocator"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = " != 0 && ptr"
            r5.append(r10)
            r5.append(r3)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "        (*(void(*)(void*))jlong_to_ptr(deallocator"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = "))((void*)ptr"
            r5.append(r10)
            r5.append(r3)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
        L_0x0ef1:
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            r5.append(r3)
            r5.append(r8)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            goto L_0x0fe8
        L_0x0f10:
            r38 = r10
            java.lang.Class<java.nio.Buffer> r5 = java.nio.Buffer.class
            r10 = r7[r3]
            boolean r5 = r5.isAssignableFrom(r10)
            if (r5 == 0) goto L_0x0fbf
            if (r11 != 0) goto L_0x0f3f
            java.io.PrintWriter r5 = r1.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r4)
            r10.append(r3)
            java.lang.String r4 = " = ptr"
            r10.append(r4)
            r10.append(r3)
            java.lang.String r4 = " != NULL ? 1 : 0;"
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            r5.println(r4)
        L_0x0f3f:
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r13)
            r5.append(r3)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "        jlong sizeptr = size"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = " * sizeof(ptr"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = "[0]);"
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "        obj"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = " = env->NewDirectByteBuffer((void*)ptr"
            r5.append(r10)
            r5.append(r3)
            java.lang.String r10 = ", sizeptr < INT_MAX ? sizeptr : INT_MAX);"
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            r5.append(r3)
            r5.append(r8)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            goto L_0x0fe8
        L_0x0fbf:
            org.bytedeco.javacpp.tools.Logger r4 = r1.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Callback \""
            r5.append(r6)
            r5.append(r15)
            java.lang.String r6 = "\" has unsupported parameter type \""
            r5.append(r6)
            r6 = r7[r3]
            java.lang.String r6 = r6.getCanonicalName()
            r5.append(r6)
            java.lang.String r6 = "\". Compilation will most likely fail."
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.warn(r5)
        L_0x0fe8:
            int r3 = r3 + 1
            r5 = r44
            r8 = r0
            r13 = r29
            r10 = r33
            r11 = r34
            r15 = r35
            r4 = r38
            r0 = r43
            r29 = r9
            r9 = r32
            goto L_0x06b5
        L_0x0fff:
            r38 = r4
            r0 = r8
            r33 = r10
            r34 = r11
            goto L_0x1012
        L_0x1007:
            r38 = r4
            r30 = r6
            r0 = r8
            r33 = r10
            r34 = r11
            r31 = r12
        L_0x1012:
            r35 = r15
            r9 = r29
            r15 = r42
            r29 = r13
            r3 = r46
            if (r3 == 0) goto L_0x10be
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "    if ("
            r5.append(r6)
            r6 = r26
            r5.append(r6)
            java.lang.String r8 = " == NULL) {"
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "        "
            r5.append(r8)
            r5.append(r6)
            java.lang.String r8 = " = JavaCPP_getMethodID(env, "
            r5.append(r8)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r8 = r1.jclasses
            r10 = r41
            int r8 = r8.index(r10)
            r5.append(r8)
            java.lang.String r8 = ", \""
            r5.append(r8)
            java.lang.reflect.Method r8 = r3.method
            java.lang.String r8 = r8.getName()
            r5.append(r8)
            java.lang.String r8 = "\", \"("
            r5.append(r8)
            java.lang.reflect.Method r8 = r3.method
            java.lang.Class[] r8 = r8.getParameterTypes()
            java.lang.String r8 = signature(r8)
            r5.append(r8)
            r8 = r34
            r5.append(r8)
            r8 = 1
            java.lang.Class[] r11 = new java.lang.Class[r8]
            java.lang.reflect.Method r3 = r3.method
            java.lang.Class r3 = r3.getReturnType()
            r8 = 0
            r11[r8] = r3
            java.lang.String r3 = signature(r11)
            r5.append(r3)
            java.lang.String r3 = "\");"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.println(r3)
            java.io.PrintWriter r3 = r1.out
            r3.println(r14)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "    jmethodID mid = "
            r4.append(r5)
            r4.append(r6)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            goto L_0x11fa
        L_0x10be:
            r10 = r41
            r3 = r43
            r8 = r34
            if (r3 == 0) goto L_0x11fa
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "    if (obj == NULL) {"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "        obj = JavaCPP_createPointer(env, "
            r5.append(r6)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r6 = r1.jclasses
            int r6 = r6.index(r10)
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "        obj = obj == NULL ? NULL : env->NewGlobalRef(obj);"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "        if (obj == NULL) {"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "            JavaCPP_log(\"Error creating global reference of "
            r5.append(r6)
            java.lang.String r6 = r41.getCanonicalName()
            r5.append(r6)
            java.lang.String r6 = " instance for callback.\");"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "        } else {"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "            env->SetLongField(obj, JavaCPP_addressFID, ptr_to_jlong(this));"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.String r5 = "        }"
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "        for (int i = 0; i < "
            r5.append(r6)
            r6 = r44
            r5.append(r6)
            java.lang.String r6 = "; i++) {"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "            if (this == &"
            r5.append(r6)
            r6 = r31
            r5.append(r6)
            java.lang.String r6 = "_instances[i]) {"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "                ptr = "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = "s[i];"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.println(r3)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "                break;"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "            }"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "        }"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            r3.println(r14)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "    if (mid == NULL) {"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "        mid = JavaCPP_getMethodID(env, "
            r4.append(r5)
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r5 = r1.jclasses
            int r5 = r5.index(r10)
            r4.append(r5)
            java.lang.String r5 = ", \""
            r4.append(r5)
            java.lang.String r5 = r42.getName()
            r4.append(r5)
            java.lang.String r5 = "\", \"("
            r4.append(r5)
            java.lang.Class[] r5 = r42.getParameterTypes()
            java.lang.String r5 = signature(r5)
            r4.append(r5)
            r4.append(r8)
            r5 = 1
            java.lang.Class[] r6 = new java.lang.Class[r5]
            java.lang.Class r5 = r42.getReturnType()
            r8 = 0
            r6[r8] = r5
            java.lang.String r5 = signature(r6)
            r4.append(r5)
            java.lang.String r5 = "\");"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            r3.println(r14)
        L_0x11fa:
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "    if (obj == NULL) {"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "        JavaCPP_log(\"Function pointer object is NULL in callback for "
            r4.append(r5)
            java.lang.String r5 = r41.getCanonicalName()
            r4.append(r5)
            r5 = r33
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r4 = "    } else if (mid == NULL) {"
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "        JavaCPP_log(\"Error getting method ID of function caller \\\""
            r4.append(r6)
            r4.append(r15)
            java.lang.String r6 = "\\\" for callback.\");"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintWriter r3 = r1.out
            r4 = r29
            r3.println(r4)
            boolean r3 = r30.isPrimitive()
            if (r3 == 0) goto L_0x1271
            java.lang.String r3 = r30.getName()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r8 = 0
            char r11 = r3.charAt(r8)
            char r8 = java.lang.Character.toUpperCase(r11)
            r6.append(r8)
            r8 = 1
            java.lang.String r3 = r3.substring(r8)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            goto L_0x1273
        L_0x1271:
            java.lang.String r3 = "Object"
        L_0x1273:
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r11 = "        "
            r8.append(r11)
            r11 = r28
            r8.append(r11)
            java.lang.String r11 = "env->Call"
            r8.append(r11)
            r8.append(r3)
            java.lang.String r3 = "MethodA(obj, mid, "
            r8.append(r3)
            int r3 = r7.length
            if (r3 != 0) goto L_0x1297
            java.lang.String r3 = "NULL);"
            goto L_0x1299
        L_0x1297:
            java.lang.String r3 = "args);"
        L_0x1299:
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            r6.println(r3)
            if (r27 == 0) goto L_0x12ba
            java.io.PrintWriter r3 = r1.out
            java.lang.String r6 = "        if ((exc = env->ExceptionOccurred()) != NULL) {"
            r3.println(r6)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r6 = "            env->ExceptionClear();"
            r3.println(r6)
            java.io.PrintWriter r3 = r1.out
            java.lang.String r6 = "        }"
            r3.println(r6)
        L_0x12ba:
            java.io.PrintWriter r3 = r1.out
            r3.println(r14)
            r3 = 0
        L_0x12c0:
            int r6 = r7.length
            if (r3 >= r6) goto L_0x14f3
            java.lang.Class<org.bytedeco.javacpp.Pointer> r6 = org.bytedeco.javacpp.Pointer.class
            r8 = r7[r3]
            boolean r6 = r6.isAssignableFrom(r8)
            if (r6 == 0) goto L_0x14c6
            r6 = r7[r3]
            java.lang.String[] r6 = r1.cppTypeName(r6)
            r8 = r20[r3]
            java.lang.annotation.Annotation r8 = r1.by(r8)
            r11 = r7[r3]
            r12 = r20[r3]
            java.lang.String r11 = r1.cast((java.lang.Class<?>) r11, (java.lang.annotation.Annotation[]) r12)
            java.lang.String r12 = valueTypeName(r6)
            r13 = r20[r3]
            r10 = 1
            org.bytedeco.javacpp.tools.AdapterInformation r12 = r1.adapterInformation((boolean) r10, (java.lang.String) r12, (java.lang.annotation.Annotation[]) r13)
            r10 = 0
            r13 = r6[r10]
            java.lang.String r10 = "void*"
            boolean r10 = r10.equals(r13)
            if (r10 == 0) goto L_0x1306
            r10 = r7[r3]
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r13 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r10 = r10.isAnnotationPresent(r13)
            if (r10 != 0) goto L_0x1306
            java.lang.String r10 = "char*"
            r13 = 0
            r6[r13] = r10
        L_0x1306:
            if (r12 != 0) goto L_0x1310
            boolean r10 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r10 != 0) goto L_0x1310
            boolean r10 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r10 == 0) goto L_0x14c6
        L_0x1310:
            java.io.PrintWriter r10 = r1.out
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r29 = r4
            r4 = r38
            r13.append(r4)
            r19 = 0
            r15 = r6[r19]
            r13.append(r15)
            java.lang.String r15 = " rptr"
            r13.append(r15)
            r13.append(r3)
            r15 = 1
            r4 = r6[r15]
            r13.append(r4)
            java.lang.String r4 = " = ("
            r13.append(r4)
            r4 = r6[r19]
            r13.append(r4)
            r4 = r6[r15]
            r13.append(r4)
            java.lang.String r4 = ")jlong_to_ptr(env->GetLongField(obj"
            r13.append(r4)
            r13.append(r3)
            java.lang.String r4 = ", JavaCPP_addressFID));"
            r13.append(r4)
            java.lang.String r4 = r13.toString()
            r10.println(r4)
            if (r12 == 0) goto L_0x139c
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r10 = "    jlong rsize"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = " = env->GetLongField(obj"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = ", JavaCPP_limitFID);"
            r6.append(r10)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r10 = "    void* rowner"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = " = JavaCPP_getPointerOwner(env, obj"
            r6.append(r10)
            r6.append(r3)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
        L_0x139c:
            r4 = r7[r3]
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r6 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r4 = r4.isAnnotationPresent(r6)
            if (r4 != 0) goto L_0x1417
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r4 = org.bytedeco.javacpp.FunctionPointer.class
            r6 = r7[r3]
            boolean r4 = r4.isAssignableFrom(r6)
            if (r4 != 0) goto L_0x1417
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r10 = "    jlong rposition"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = " = env->GetLongField(obj"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = ", JavaCPP_positionFID);"
            r6.append(r10)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r10 = "    rptr"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = " += rposition"
            r6.append(r10)
            r6.append(r3)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            if (r12 == 0) goto L_0x1417
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r10 = "    rsize"
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = " -= rposition"
            r6.append(r10)
            r6.append(r3)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
        L_0x1417:
            if (r12 == 0) goto L_0x144c
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "    adapter"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = ".assign(rptr"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = ", rsize"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = ", rowner"
            r6.append(r8)
            r6.append(r3)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            goto L_0x14c8
        L_0x144c:
            boolean r4 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r4 == 0) goto L_0x1498
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "    if (arg"
            r6.append(r8)
            r6.append(r3)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "        *arg"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = " = *"
            r6.append(r8)
            r6.append(r11)
            java.lang.String r8 = "&rptr"
            r6.append(r8)
            r6.append(r3)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            java.io.PrintWriter r4 = r1.out
            r4.println(r14)
            goto L_0x14c8
        L_0x1498:
            boolean r4 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r4 == 0) goto L_0x14c8
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "    arg"
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = " = "
            r6.append(r8)
            r6.append(r11)
            java.lang.String r8 = "rptr"
            r6.append(r8)
            r6.append(r3)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
            goto L_0x14c8
        L_0x14c6:
            r29 = r4
        L_0x14c8:
            r4 = r7[r3]
            boolean r4 = r4.isPrimitive()
            if (r4 != 0) goto L_0x14e9
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "    env->DeleteLocalRef(obj"
            r6.append(r8)
            r6.append(r3)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r4.println(r6)
        L_0x14e9:
            int r3 = r3 + 1
            r10 = r41
            r15 = r42
            r4 = r29
            goto L_0x12c0
        L_0x14f3:
            r29 = r4
            java.io.PrintWriter r0 = r1.out
            r2 = r25
            r0.println(r2)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r3 = "end:"
            r0.println(r3)
            java.lang.Class r0 = java.lang.Void.TYPE
            r3 = r30
            if (r3 == r0) goto L_0x172c
            r0 = 0
            r4 = r17[r0]
            java.lang.String r6 = "void*"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x1520
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r4 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r4 = r3.isAnnotationPresent(r4)
            if (r4 != 0) goto L_0x1520
            java.lang.String r4 = "char*"
            r17[r0] = r4
        L_0x1520:
            java.lang.Class<java.lang.Enum> r0 = java.lang.Enum.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x15c0
            r0 = 1
            r1.accessesEnums = r0
            java.lang.String r4 = r1.enumValueType(r3)
            if (r4 == 0) goto L_0x172d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = 0
            char r8 = r4.charAt(r7)
            char r7 = java.lang.Character.toUpperCase(r8)
            r6.append(r7)
            java.lang.String r7 = r4.substring(r0)
            r6.append(r7)
            java.lang.String r0 = r6.toString()
            java.io.PrintWriter r6 = r1.out
            java.lang.String r7 = "    if (rarg == NULL) {"
            r6.println(r7)
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "        JavaCPP_log(\"Enum for return is NULL in callback for "
            r7.append(r8)
            java.lang.String r8 = r41.getCanonicalName()
            r7.append(r8)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            r6.println(r7)
            java.io.PrintWriter r6 = r1.out
            r6.println(r14)
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = r38
            r7.append(r8)
            r8 = 0
            r10 = r17[r8]
            r7.append(r10)
            java.lang.String r10 = " rval"
            r7.append(r10)
            r10 = 1
            r11 = r17[r10]
            r7.append(r11)
            java.lang.String r11 = " = ("
            r7.append(r11)
            r11 = r17[r8]
            r7.append(r11)
            r8 = r17[r10]
            r7.append(r8)
            java.lang.String r8 = ")(rarg == NULL ? 0 : env->Get"
            r7.append(r8)
            r7.append(r0)
            java.lang.String r0 = "Field(rarg, JavaCPP_"
            r7.append(r0)
            r7.append(r4)
            java.lang.String r0 = "ValueFID));"
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r6.println(r0)
            goto L_0x172c
        L_0x15c0:
            r8 = r38
            java.lang.Class<org.bytedeco.javacpp.Pointer> r0 = org.bytedeco.javacpp.Pointer.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x165a
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r0 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x15f3
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.functions
            r0.index(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = functionClassName(r3)
            r0.append(r4)
            java.lang.String r4 = "*"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r4 = 0
            r17[r4] = r0
            r0 = 1
            r17[r0] = r18
            goto L_0x15f5
        L_0x15f3:
            r0 = 1
            r4 = 0
        L_0x15f5:
            java.io.PrintWriter r6 = r1.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r8)
            r8 = r17[r4]
            r7.append(r8)
            java.lang.String r8 = " rptr"
            r7.append(r8)
            r8 = r17[r0]
            r7.append(r8)
            java.lang.String r8 = " = rarg == NULL ? NULL : ("
            r7.append(r8)
            r8 = r17[r4]
            r7.append(r8)
            r4 = r17[r0]
            r7.append(r4)
            java.lang.String r0 = ")jlong_to_ptr(env->GetLongField(rarg, JavaCPP_addressFID));"
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r6.println(r0)
            if (r24 == 0) goto L_0x1639
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    jlong rsize = rarg == NULL ? 0 : env->GetLongField(rarg, JavaCPP_limitFID);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    void* rowner = JavaCPP_getPointerOwner(env, rarg);"
            r0.println(r4)
        L_0x1639:
            java.lang.Class<org.bytedeco.javacpp.annotation.Opaque> r0 = org.bytedeco.javacpp.annotation.Opaque.class
            boolean r0 = r3.isAnnotationPresent(r0)
            if (r0 != 0) goto L_0x172c
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    jlong rposition = rarg == NULL ? 0 : env->GetLongField(rarg, JavaCPP_positionFID);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    rptr += rposition;"
            r0.println(r4)
            if (r24 == 0) goto L_0x172c
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    rsize -= rposition;"
            r0.println(r4)
            goto L_0x172c
        L_0x165a:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r3 != r0) goto L_0x1699
            r0 = 1
            r1.passesStrings = r0
            java.io.PrintWriter r4 = r1.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r8)
            r7 = 0
            r8 = r17[r7]
            r6.append(r8)
            java.lang.String r7 = " rptr"
            r6.append(r7)
            r7 = r17[r0]
            r6.append(r7)
            java.lang.String r0 = " = JavaCPP_getStringBytes(env, rarg);"
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r4.println(r0)
            if (r24 == 0) goto L_0x172c
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    jlong rsize = 0;"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    void* rowner = (void*)rptr;"
            r0.println(r4)
            goto L_0x172c
        L_0x1699:
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x16fd
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r8)
            r6 = 0
            r7 = r17[r6]
            r4.append(r7)
            java.lang.String r7 = " rptr"
            r4.append(r7)
            r7 = 1
            r8 = r17[r7]
            r4.append(r8)
            java.lang.String r8 = " = rarg == NULL ? NULL : ("
            r4.append(r8)
            r8 = r17[r6]
            r4.append(r8)
            r6 = r17[r7]
            r4.append(r6)
            java.lang.String r6 = ")env->GetDirectBufferAddress(rarg);"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r0.println(r4)
            if (r24 == 0) goto L_0x172c
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    jlong rsize = rarg == NULL ? 0 : env->GetIntField(rarg, JavaCPP_bufferLimitFID);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    void* rowner = (void*)rptr;"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    jlong rposition = rarg == NULL ? 0 : env->GetIntField(rarg, JavaCPP_bufferPositionFID);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    rptr += rposition;"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    rsize -= rposition;"
            r0.println(r4)
            goto L_0x172c
        L_0x16fd:
            boolean r0 = r3.isPrimitive()
            if (r0 != 0) goto L_0x172c
            org.bytedeco.javacpp.tools.Logger r0 = r1.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Callback \""
            r4.append(r6)
            r6 = r42
            r4.append(r6)
            java.lang.String r6 = "\" has unsupported return type \""
            r4.append(r6)
            java.lang.String r6 = r3.getCanonicalName()
            r4.append(r6)
            java.lang.String r6 = "\". Compilation will most likely fail."
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r0.warn(r4)
        L_0x172c:
            r0 = 1
        L_0x172d:
            r1.passesStrings = r0
            if (r27 == 0) goto L_0x1784
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "    if (exc != NULL) {"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        jstring str = (jstring)env->CallObjectMethod(exc, JavaCPP_toStringMID);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        env->DeleteLocalRef(exc);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        const char *msg = JavaCPP_getStringBytes(env, str);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        JavaCPP_exception e(msg);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        JavaCPP_releaseStringBytes(env, str, msg);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        env->DeleteLocalRef(str);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        JavaCPP_detach(attached);"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r4 = "        throw e;"
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            r4 = r29
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r6 = "        JavaCPP_detach(attached);"
            r0.println(r6)
            java.io.PrintWriter r0 = r1.out
            r0.println(r14)
            goto L_0x178d
        L_0x1784:
            r4 = r29
            java.io.PrintWriter r0 = r1.out
            java.lang.String r6 = "    JavaCPP_detach(attached);"
            r0.println(r6)
        L_0x178d:
            java.lang.Class r0 = java.lang.Void.TYPE
            if (r3 == r0) goto L_0x1918
            boolean r0 = r3.isPrimitive()
            if (r0 == 0) goto L_0x17c4
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r6 = r22
            r3.append(r6)
            r7 = r23
            r3.append(r7)
            r8 = r45
            boolean r4 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtr
            if (r4 != 0) goto L_0x17b6
            boolean r4 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r4 == 0) goto L_0x17b3
            goto L_0x17b6
        L_0x17b3:
            java.lang.String r4 = "rarg;"
            goto L_0x17b8
        L_0x17b6:
            java.lang.String r4 = "&rarg;"
        L_0x17b8:
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x17c4:
            r8 = r45
            r6 = r22
            r7 = r23
            java.lang.Class<java.lang.Enum> r0 = java.lang.Enum.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x17ed
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r7)
            java.lang.String r4 = "rval;"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x17ed:
            if (r24 == 0) goto L_0x1819
            r0 = 1
            r1.usesAdapters = r0
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r4 = r24
            java.lang.String r4 = r4.name
            r3.append(r4)
            r4 = r35
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = "rptr, rsize, rowner);"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x1819:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r0 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 == 0) goto L_0x1841
            org.bytedeco.javacpp.tools.IndexedSet<java.lang.Class> r0 = r1.functions
            r0.index(r3)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r7)
            java.lang.String r4 = "(rptr == NULL ? NULL : rptr->ptr);"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x1841:
            boolean r0 = r8 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r0 != 0) goto L_0x1884
            boolean r0 = r8 instanceof org.bytedeco.javacpp.annotation.ByRef
            if (r0 == 0) goto L_0x184a
            goto L_0x1884
        L_0x184a:
            boolean r0 = r8 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            if (r0 == 0) goto L_0x1869
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r7)
            java.lang.String r4 = "&rptr;"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x1869:
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r7)
            java.lang.String r4 = "rptr;"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            goto L_0x1918
        L_0x1884:
            java.io.PrintWriter r0 = r1.out
            java.lang.String r3 = "    if (rptr == NULL) {"
            r0.println(r3)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "        JavaCPP_log(\"Return pointer address is NULL in callback for "
            r3.append(r6)
            java.lang.String r6 = r41.getCanonicalName()
            r3.append(r6)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "        static "
            r3.append(r5)
            r5 = 1
            java.lang.String[] r6 = new java.lang.String[r5]
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r10 = 0
            r11 = r21[r10]
            r8.append(r11)
            r11 = r21[r5]
            r8.append(r11)
            java.lang.String r8 = r8.toString()
            java.lang.String r8 = r8.trim()
            r6[r10] = r8
            java.lang.String r6 = constValueTypeName(r6)
            r3.append(r6)
            java.lang.String r6 = " empty"
            r3.append(r6)
            r5 = r17[r5]
            r3.append(r5)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            java.io.PrintWriter r0 = r1.out
            java.lang.String r3 = "        return empty;"
            r0.println(r3)
            java.io.PrintWriter r0 = r1.out
            r0.println(r4)
            java.io.PrintWriter r0 = r1.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "        return *"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = "rptr;"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.println(r3)
            java.io.PrintWriter r0 = r1.out
            r0.println(r14)
        L_0x1918:
            java.io.PrintWriter r0 = r1.out
            r0.println(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.callback(java.lang.Class, java.lang.reflect.Method, java.lang.String, int, boolean, org.bytedeco.javacpp.tools.MethodInformation):void");
    }

    /* access modifiers changed from: package-private */
    public void callbackAllocator(Class<?> cls, String str, int i) {
        String[] cppTypeName = cppTypeName(cls);
        String functionClassName = functionClassName(cls);
        this.out.println("    obj = env->NewWeakGlobalRef(obj);");
        this.out.println("    if (obj == NULL) {");
        PrintWriter printWriter = this.out;
        printWriter.println("        JavaCPP_log(\"Error creating global reference of " + cls.getCanonicalName() + " instance for callback.\");");
        this.out.println("        return;");
        this.out.println("    }");
        PrintWriter printWriter2 = this.out;
        printWriter2.println("    " + functionClassName + "* rptr = new (std::nothrow) " + functionClassName + ";");
        this.out.println("    if (rptr != NULL) {");
        this.out.println("        rptr->obj = obj;");
        PrintWriter printWriter3 = this.out;
        printWriter3.println("        JavaCPP_initPointer(env, obj, rptr, 1, rptr, &JavaCPP_" + mangle(cls.getName()) + "_deallocate);");
        this.deallocators.index(cls);
        if (str != null) {
            PrintWriter printWriter4 = this.out;
            printWriter4.println("        for (int i = 0; i < " + i + "; i++) {");
            PrintWriter printWriter5 = this.out;
            printWriter5.println("            if (" + functionClassName + "_instances[i].obj == NULL) {");
            PrintWriter printWriter6 = this.out;
            printWriter6.println("                rptr->ptr = " + str + "s[i];");
            PrintWriter printWriter7 = this.out;
            printWriter7.println("                " + functionClassName + "_instances[i] = *rptr;");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
        } else {
            PrintWriter printWriter8 = this.out;
            printWriter8.println("        rptr->ptr = (" + cppTypeName[0] + cppTypeName[1] + ")jlong_to_ptr(arg0);");
        }
        this.out.println("    }");
        this.out.println("}");
    }

    static String functionClassName(Class<?> cls) {
        Name name = (Name) cls.getAnnotation(Name.class);
        if (name != null) {
            return name.value()[0];
        }
        return "JavaCPP_" + mangle(cls.getName());
    }

    static Method[] functionMethods(Class<?> cls, boolean[] zArr) {
        if (!FunctionPointer.class.isAssignableFrom(cls)) {
            return null;
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        Method[] methodArr = new Method[3];
        for (int i = 0; i < declaredMethods.length; i++) {
            String name = declaredMethods[i].getName();
            int modifiers = declaredMethods[i].getModifiers();
            Class[] parameterTypes = declaredMethods[i].getParameterTypes();
            Class<?> returnType = declaredMethods[i].getReturnType();
            if (!Modifier.isStatic(modifiers)) {
                if (zArr != null && name.startsWith("allocate") && Modifier.isNative(modifiers) && returnType == Void.TYPE && (parameterTypes.length == 0 || (parameterTypes.length == 1 && (parameterTypes[0] == Integer.TYPE || parameterTypes[0] == Long.TYPE)))) {
                    zArr[i] = true;
                } else if (name.startsWith(NotificationCompat.CATEGORY_CALL) || name.startsWith("apply")) {
                    methodArr[0] = declaredMethods[i];
                } else if (name.startsWith("get") && Modifier.isNative(modifiers) && cls.isAnnotationPresent(Namespace.class)) {
                    methodArr[1] = declaredMethods[i];
                } else if (name.startsWith("put") && Modifier.isNative(modifiers) && cls.isAnnotationPresent(Namespace.class)) {
                    methodArr[2] = declaredMethods[i];
                }
            }
        }
        if (methodArr[0] == null && methodArr[1] == null && methodArr[2] == null) {
            return null;
        }
        return methodArr;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0339 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0342  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x04f2  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x04f4  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x0516  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0532  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x0542  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x056b  */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x0577  */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x05cd  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x05d1  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.MethodInformation methodInformation(java.lang.reflect.Method r35) {
        /*
            r34 = this;
            r0 = r34
            r1 = r35
            org.bytedeco.javacpp.tools.MethodInformation r2 = new org.bytedeco.javacpp.tools.MethodInformation
            r2.<init>()
            java.lang.Class r3 = r35.getDeclaringClass()
            r2.cls = r3
            r2.method = r1
            java.lang.annotation.Annotation[] r3 = r35.getAnnotations()
            r2.annotations = r3
            int r3 = r35.getModifiers()
            r2.modifiers = r3
            java.lang.Class r3 = r35.getReturnType()
            r2.returnType = r3
            java.lang.String r3 = r35.getName()
            r2.name = r3
            java.lang.Class<org.bytedeco.javacpp.annotation.Name> r3 = org.bytedeco.javacpp.annotation.Name.class
            java.lang.annotation.Annotation r3 = r1.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Name r3 = (org.bytedeco.javacpp.annotation.Name) r3
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x003a
            java.lang.String[] r6 = r3.value()
            goto L_0x0040
        L_0x003a:
            java.lang.String[] r6 = new java.lang.String[r5]
            java.lang.String r7 = r2.name
            r6[r4] = r7
        L_0x0040:
            r2.memberName = r6
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r6 = org.bytedeco.javacpp.annotation.Index.class
            java.lang.annotation.Annotation r6 = r1.getAnnotation(r6)
            org.bytedeco.javacpp.annotation.Index r6 = (org.bytedeco.javacpp.annotation.Index) r6
            java.lang.Class<?> r7 = r2.cls
            java.lang.reflect.Method r8 = r2.method
            int r7 = allocatorMax(r7, r8)
            r2.allocatorMax = r7
            if (r6 == 0) goto L_0x005b
            int r7 = r6.value()
            goto L_0x005c
        L_0x005b:
            r7 = 0
        L_0x005c:
            r2.dim = r7
            java.lang.Class[] r7 = r35.getParameterTypes()
            r2.parameterTypes = r7
            java.lang.annotation.Annotation[][] r7 = r35.getParameterAnnotations()
            r2.parameterAnnotations = r7
            java.lang.Class<?> r7 = r2.cls
            java.lang.reflect.Method r8 = r2.method
            boolean r7 = criticalRegion(r7, r8)
            r2.criticalRegion = r7
            java.lang.Class<org.bytedeco.javacpp.annotation.Raw> r7 = org.bytedeco.javacpp.annotation.Raw.class
            boolean r7 = r1.isAnnotationPresent(r7)
            r2.returnRaw = r7
            boolean r7 = r2.returnRaw
            if (r7 == 0) goto L_0x008d
            java.lang.Class<org.bytedeco.javacpp.annotation.Raw> r7 = org.bytedeco.javacpp.annotation.Raw.class
            java.lang.annotation.Annotation r7 = r1.getAnnotation(r7)
            org.bytedeco.javacpp.annotation.Raw r7 = (org.bytedeco.javacpp.annotation.Raw) r7
            boolean r7 = r7.withEnv()
            goto L_0x008e
        L_0x008d:
            r7 = 0
        L_0x008e:
            r2.withEnv = r7
            java.lang.annotation.Annotation[][] r7 = r2.parameterAnnotations
            int r7 = r7.length
            boolean[] r7 = new boolean[r7]
            r2.parameterRaw = r7
            r7 = 0
        L_0x0098:
            java.lang.annotation.Annotation[][] r8 = r2.parameterAnnotations
            int r8 = r8.length
            if (r7 >= r8) goto L_0x00ca
            r8 = 0
        L_0x009e:
            java.lang.annotation.Annotation[][] r9 = r2.parameterAnnotations
            r9 = r9[r7]
            int r9 = r9.length
            if (r8 >= r9) goto L_0x00c7
            java.lang.annotation.Annotation[][] r9 = r2.parameterAnnotations
            r9 = r9[r7]
            r9 = r9[r8]
            boolean r9 = r9 instanceof org.bytedeco.javacpp.annotation.Raw
            if (r9 == 0) goto L_0x00c4
            boolean[] r9 = r2.parameterRaw
            r9[r7] = r5
            boolean r9 = r2.withEnv
            java.lang.annotation.Annotation[][] r10 = r2.parameterAnnotations
            r10 = r10[r7]
            r10 = r10[r8]
            org.bytedeco.javacpp.annotation.Raw r10 = (org.bytedeco.javacpp.annotation.Raw) r10
            boolean r10 = r10.withEnv()
            r9 = r9 | r10
            r2.withEnv = r9
        L_0x00c4:
            int r8 = r8 + 1
            goto L_0x009e
        L_0x00c7:
            int r7 = r7 + 1
            goto L_0x0098
        L_0x00ca:
            java.lang.Class<?> r7 = r2.returnType
            java.lang.Class r8 = java.lang.Void.TYPE
            if (r7 != r8) goto L_0x00f0
            java.lang.Class<?>[] r7 = r2.parameterTypes
            int r7 = r7.length
            if (r7 <= 0) goto L_0x00ee
            java.lang.Class<?>[] r7 = r2.parameterTypes
            r7 = r7[r4]
            boolean r7 = r7.isArray()
            if (r7 == 0) goto L_0x00ee
            java.lang.Class<?>[] r7 = r2.parameterTypes
            r7 = r7[r4]
            java.lang.Class r7 = r7.getComponentType()
            boolean r7 = r7.isPrimitive()
            if (r7 == 0) goto L_0x00ee
            goto L_0x00f0
        L_0x00ee:
            r7 = 0
            goto L_0x00f1
        L_0x00f0:
            r7 = 1
        L_0x00f1:
            java.lang.Class<?> r8 = r2.returnType
            java.lang.Class r9 = java.lang.Void.TYPE
            if (r8 == r9) goto L_0x00fd
            java.lang.Class<?> r8 = r2.returnType
            java.lang.Class<?> r9 = r2.cls
            if (r8 != r9) goto L_0x0104
        L_0x00fd:
            java.lang.Class<?>[] r8 = r2.parameterTypes
            int r8 = r8.length
            if (r8 <= 0) goto L_0x0104
            r8 = 1
            goto L_0x0105
        L_0x0104:
            r8 = 0
        L_0x0105:
            int r9 = r2.modifiers
            boolean r9 = java.lang.reflect.Modifier.isStatic(r9)
            if (r9 != 0) goto L_0x0115
            java.lang.Class<?> r9 = r2.returnType
            java.lang.Class r10 = java.lang.Void.TYPE
            if (r9 != r10) goto L_0x0115
            r9 = 1
            goto L_0x0116
        L_0x0115:
            r9 = 0
        L_0x0116:
            if (r9 == 0) goto L_0x012f
            java.lang.Class<?>[] r10 = r2.parameterTypes
            int r10 = r10.length
            if (r10 != r5) goto L_0x012f
            java.lang.Class<?>[] r10 = r2.parameterTypes
            r10 = r10[r4]
            java.lang.Class r11 = java.lang.Integer.TYPE
            if (r10 == r11) goto L_0x012d
            java.lang.Class<?>[] r10 = r2.parameterTypes
            r10 = r10[r4]
            java.lang.Class r11 = java.lang.Long.TYPE
            if (r10 != r11) goto L_0x012f
        L_0x012d:
            r10 = 1
            goto L_0x0130
        L_0x012f:
            r10 = 0
        L_0x0130:
            java.lang.Class<?> r11 = r2.cls
            java.lang.reflect.Method[] r11 = r11.getDeclaredMethods()
            int r12 = r11.length
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r19 = 0
            r20 = 0
        L_0x0142:
            java.lang.String r5 = "get"
            if (r14 >= r12) goto L_0x0387
            r4 = r11[r14]
            r23 = r11
            java.util.Map<java.lang.reflect.Method, org.bytedeco.javacpp.tools.MethodInformation> r11 = r0.annotationCache
            java.lang.Object r11 = r11.get(r4)
            org.bytedeco.javacpp.tools.MethodInformation r11 = (org.bytedeco.javacpp.tools.MethodInformation) r11
            if (r11 != 0) goto L_0x0186
            java.util.Map<java.lang.reflect.Method, org.bytedeco.javacpp.tools.MethodInformation> r11 = r0.annotationCache
            r24 = r12
            org.bytedeco.javacpp.tools.MethodInformation r12 = new org.bytedeco.javacpp.tools.MethodInformation
            r12.<init>()
            r11.put(r4, r12)
            int r11 = r4.getModifiers()
            r12.modifiers = r11
            java.lang.Class r11 = r4.getReturnType()
            r12.returnType = r11
            java.lang.String r11 = r4.getName()
            r12.name = r11
            java.lang.Class[] r11 = r4.getParameterTypes()
            r12.parameterTypes = r11
            java.lang.annotation.Annotation[] r11 = r4.getAnnotations()
            r12.annotations = r11
            java.lang.annotation.Annotation[][] r11 = r4.getParameterAnnotations()
            r12.parameterAnnotations = r11
            r11 = r12
            goto L_0x0188
        L_0x0186:
            r24 = r12
        L_0x0188:
            java.lang.Class<?>[] r12 = r2.parameterTypes
            int r12 = r12.length
            if (r12 <= 0) goto L_0x019b
            java.lang.Class<?>[] r12 = r2.parameterTypes
            r22 = 0
            r12 = r12[r22]
            r25 = r3
            java.lang.Class<java.lang.Class> r3 = java.lang.Class.class
            if (r12 != r3) goto L_0x019f
            r3 = 1
            goto L_0x01a0
        L_0x019b:
            r25 = r3
            r22 = 0
        L_0x019f:
            r3 = 0
        L_0x01a0:
            java.lang.Class<?>[] r12 = r11.parameterTypes
            int r12 = r12.length
            if (r12 <= 0) goto L_0x01b1
            java.lang.Class<?>[] r12 = r11.parameterTypes
            r12 = r12[r22]
            r26 = r13
            java.lang.Class<java.lang.Class> r13 = java.lang.Class.class
            if (r12 != r13) goto L_0x01b3
            r12 = 1
            goto L_0x01b4
        L_0x01b1:
            r26 = r13
        L_0x01b3:
            r12 = 0
        L_0x01b4:
            boolean r13 = r1.equals(r4)
            if (r13 != 0) goto L_0x0372
            int r13 = r11.modifiers
            boolean r13 = java.lang.reflect.Modifier.isNative(r13)
            if (r13 != 0) goto L_0x01c4
            goto L_0x0372
        L_0x01c4:
            java.lang.String r13 = "put"
            r27 = r4
            if (r7 == 0) goto L_0x01e0
            java.lang.String r4 = r2.name
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x01e0
            java.lang.String r4 = r11.name
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x01e0
            r4 = 1
            r5 = 0
        L_0x01dc:
            r13 = 0
            r28 = 0
            goto L_0x0207
        L_0x01e0:
            if (r8 == 0) goto L_0x01f5
            java.lang.String r4 = r2.name
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x01f5
            java.lang.String r4 = r11.name
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x01f5
            r4 = 0
            r5 = 1
            goto L_0x01dc
        L_0x01f5:
            java.lang.String r4 = r11.name
            java.lang.String r5 = r2.name
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0372
            r4 = 1
            r2.overloaded = r4
            r13 = r7
            r28 = r8
            r4 = 0
            r5 = 0
        L_0x0207:
            r31 = r6
            r30 = r13
            r13 = 0
            r29 = 1
        L_0x020e:
            java.lang.Class<?>[] r6 = r2.parameterTypes
            int r6 = r6.length
            int r6 = r6 - r3
            if (r13 >= r6) goto L_0x0231
            java.lang.Class<?>[] r6 = r11.parameterTypes
            int r6 = r6.length
            int r6 = r6 - r12
            if (r13 >= r6) goto L_0x0231
            java.lang.Class<?>[] r6 = r2.parameterTypes
            int r32 = r13 + r3
            r6 = r6[r32]
            r32 = r10
            java.lang.Class<?>[] r10 = r11.parameterTypes
            int r33 = r13 + r12
            r10 = r10[r33]
            if (r6 == r10) goto L_0x022c
            r29 = 0
        L_0x022c:
            int r13 = r13 + 1
            r10 = r32
            goto L_0x020e
        L_0x0231:
            r32 = r10
            if (r29 != 0) goto L_0x0237
            goto L_0x0376
        L_0x0237:
            if (r4 == 0) goto L_0x0259
            java.lang.Class<?>[] r6 = r2.parameterTypes
            int r6 = r6.length
            if (r6 <= 0) goto L_0x0259
            java.lang.Class<?>[] r6 = r2.parameterTypes
            r10 = 0
            r6 = r6[r10]
            boolean r6 = r6.isArray()
            if (r6 == 0) goto L_0x0259
            java.lang.Class<?>[] r6 = r2.parameterTypes
            r6 = r6[r10]
            java.lang.Class r6 = r6.getComponentType()
            boolean r6 = r6.isPrimitive()
            if (r6 == 0) goto L_0x0259
            r6 = 1
            goto L_0x025a
        L_0x0259:
            r6 = 0
        L_0x025a:
            if (r5 == 0) goto L_0x027c
            java.lang.Class<?>[] r10 = r11.parameterTypes
            int r10 = r10.length
            if (r10 <= 0) goto L_0x027c
            java.lang.Class<?>[] r10 = r11.parameterTypes
            r13 = 0
            r10 = r10[r13]
            boolean r10 = r10.isArray()
            if (r10 == 0) goto L_0x027c
            java.lang.Class<?>[] r10 = r11.parameterTypes
            r10 = r10[r13]
            java.lang.Class r10 = r10.getComponentType()
            boolean r10 = r10.isPrimitive()
            if (r10 == 0) goto L_0x027c
            r10 = 1
            goto L_0x027d
        L_0x027c:
            r10 = 0
        L_0x027d:
            if (r7 == 0) goto L_0x02dd
            java.lang.Class<?>[] r13 = r11.parameterTypes
            int r13 = r13.length
            r29 = r6 ^ 1
            int r13 = r13 - r29
            r29 = r4
            java.lang.Class<?>[] r4 = r2.parameterTypes
            int r4 = r4.length
            int r4 = r4 - r3
            if (r13 != r4) goto L_0x02dd
            if (r6 == 0) goto L_0x029c
            java.lang.Class<?>[] r4 = r2.parameterTypes
            java.lang.Class<?>[] r13 = r2.parameterTypes
            int r13 = r13.length
            r21 = 1
            int r13 = r13 + -1
            r4 = r4[r13]
            goto L_0x02a0
        L_0x029c:
            r21 = 1
            java.lang.Class<?> r4 = r2.returnType
        L_0x02a0:
            java.lang.Class<?>[] r13 = r11.parameterTypes
            r33 = r3
            java.lang.Class<?>[] r3 = r11.parameterTypes
            int r3 = r3.length
            int r3 = r3 + -1
            r3 = r13[r3]
            if (r4 != r3) goto L_0x02df
            java.lang.Class<?> r3 = r11.returnType
            java.lang.Class r4 = java.lang.Void.TYPE
            if (r3 == r4) goto L_0x02b9
            java.lang.Class<?> r3 = r11.returnType
            java.lang.Class<?> r4 = r2.cls
            if (r3 != r4) goto L_0x02df
        L_0x02b9:
            java.lang.annotation.Annotation[][] r3 = r11.parameterAnnotations
            java.lang.annotation.Annotation[][] r4 = r11.parameterAnnotations
            int r4 = r4.length
            r13 = 1
            int r4 = r4 - r13
            r3 = r3[r4]
            int r3 = r3.length
            if (r3 == 0) goto L_0x02d5
            java.lang.annotation.Annotation[][] r3 = r11.parameterAnnotations
            java.lang.annotation.Annotation[][] r4 = r11.parameterAnnotations
            int r4 = r4.length
            int r4 = r4 - r13
            r3 = r3[r4]
            java.lang.annotation.Annotation[] r4 = r2.annotations
            boolean r3 = java.util.Arrays.equals(r3, r4)
            if (r3 == 0) goto L_0x02df
        L_0x02d5:
            r15 = r6
            r13 = r27
            r16 = r29
            r17 = r30
            goto L_0x0337
        L_0x02dd:
            r33 = r3
        L_0x02df:
            if (r8 == 0) goto L_0x0335
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            r4 = r10 ^ 1
            int r3 = r3 - r4
            java.lang.Class<?>[] r4 = r11.parameterTypes
            int r4 = r4.length
            int r4 = r4 - r12
            if (r3 != r4) goto L_0x0335
            if (r10 == 0) goto L_0x02f9
            java.lang.Class<?>[] r3 = r11.parameterTypes
            java.lang.Class<?>[] r4 = r11.parameterTypes
            int r4 = r4.length
            r6 = 1
            int r4 = r4 - r6
            r3 = r3[r4]
            goto L_0x02fc
        L_0x02f9:
            r6 = 1
            java.lang.Class<?> r3 = r11.returnType
        L_0x02fc:
            java.lang.Class<?>[] r4 = r2.parameterTypes
            java.lang.Class<?>[] r10 = r2.parameterTypes
            int r10 = r10.length
            int r10 = r10 - r6
            r4 = r4[r10]
            if (r3 != r4) goto L_0x0335
            java.lang.Class<?> r3 = r2.returnType
            java.lang.Class r4 = java.lang.Void.TYPE
            if (r3 == r4) goto L_0x0312
            java.lang.Class<?> r3 = r2.returnType
            java.lang.Class<?> r4 = r2.cls
            if (r3 != r4) goto L_0x0335
        L_0x0312:
            java.lang.annotation.Annotation[][] r3 = r2.parameterAnnotations
            java.lang.annotation.Annotation[][] r4 = r2.parameterAnnotations
            int r4 = r4.length
            r6 = 1
            int r4 = r4 - r6
            r3 = r3[r4]
            int r3 = r3.length
            if (r3 == 0) goto L_0x032e
            java.lang.annotation.Annotation[][] r3 = r2.parameterAnnotations
            java.lang.annotation.Annotation[][] r4 = r2.parameterAnnotations
            int r4 = r4.length
            int r4 = r4 - r6
            r3 = r3[r4]
            java.lang.annotation.Annotation[] r4 = r11.annotations
            boolean r3 = java.util.Arrays.equals(r3, r4)
            if (r3 == 0) goto L_0x0335
        L_0x032e:
            r19 = r5
            r13 = r27
            r20 = r28
            goto L_0x0337
        L_0x0335:
            r13 = r26
        L_0x0337:
            if (r17 != 0) goto L_0x033b
            if (r20 == 0) goto L_0x0378
        L_0x033b:
            r3 = r33
        L_0x033d:
            java.lang.Class<?>[] r4 = r2.parameterTypes
            int r4 = r4.length
            if (r3 >= r4) goto L_0x0378
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r4 = org.bytedeco.javacpp.annotation.Index.class
            boolean r4 = r1.isAnnotationPresent(r4)
            if (r4 != 0) goto L_0x036f
            if (r13 == 0) goto L_0x0354
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r4 = org.bytedeco.javacpp.annotation.Index.class
            boolean r4 = r13.isAnnotationPresent(r4)
            if (r4 != 0) goto L_0x036f
        L_0x0354:
            java.lang.Class<?>[] r4 = r2.parameterTypes
            r4 = r4[r3]
            java.lang.Class r5 = java.lang.Integer.TYPE
            if (r4 == r5) goto L_0x036f
            java.lang.Class<?>[] r4 = r2.parameterTypes
            r4 = r4[r3]
            java.lang.Class r5 = java.lang.Long.TYPE
            if (r4 == r5) goto L_0x036f
            java.lang.Class<?>[] r4 = r2.parameterTypes
            int r4 = r4.length
            r5 = 1
            int r4 = r4 - r5
            r17 = 0
            if (r3 >= r4) goto L_0x036f
            r20 = 0
        L_0x036f:
            int r3 = r3 + 1
            goto L_0x033d
        L_0x0372:
            r31 = r6
            r32 = r10
        L_0x0376:
            r13 = r26
        L_0x0378:
            int r14 = r14 + 1
            r11 = r23
            r12 = r24
            r3 = r25
            r6 = r31
            r10 = r32
            r4 = 0
            goto L_0x0142
        L_0x0387:
            r25 = r3
            r31 = r6
            r32 = r10
            r26 = r13
            java.lang.annotation.Annotation[] r3 = r2.annotations
            java.lang.annotation.Annotation r3 = r0.behavior(r3)
            if (r7 == 0) goto L_0x03a4
            boolean r4 = r3 instanceof org.bytedeco.javacpp.annotation.ValueGetter
            if (r4 == 0) goto L_0x03a4
            r4 = 1
            r2.valueGetter = r4
            r2.noReturnGetter = r15
        L_0x03a0:
            r13 = r26
            goto L_0x04bf
        L_0x03a4:
            r4 = 1
            if (r8 == 0) goto L_0x03ae
            boolean r6 = r3 instanceof org.bytedeco.javacpp.annotation.ValueSetter
            if (r6 == 0) goto L_0x03ae
            r2.valueSetter = r4
            goto L_0x03a0
        L_0x03ae:
            if (r7 == 0) goto L_0x03b9
            boolean r6 = r3 instanceof org.bytedeco.javacpp.annotation.MemberGetter
            if (r6 == 0) goto L_0x03b9
            r2.memberGetter = r4
            r2.noReturnGetter = r15
            goto L_0x03a0
        L_0x03b9:
            if (r8 == 0) goto L_0x03c2
            boolean r6 = r3 instanceof org.bytedeco.javacpp.annotation.MemberSetter
            if (r6 == 0) goto L_0x03c2
            r2.memberSetter = r4
            goto L_0x03a0
        L_0x03c2:
            if (r9 == 0) goto L_0x03cb
            boolean r6 = r3 instanceof org.bytedeco.javacpp.annotation.Allocator
            if (r6 == 0) goto L_0x03cb
            r2.allocator = r4
            goto L_0x03a0
        L_0x03cb:
            if (r32 == 0) goto L_0x03d6
            boolean r6 = r3 instanceof org.bytedeco.javacpp.annotation.ArrayAllocator
            if (r6 == 0) goto L_0x03d6
            r2.arrayAllocator = r4
            r2.allocator = r4
            goto L_0x03a0
        L_0x03d6:
            if (r3 != 0) goto L_0x048c
            java.lang.Class<?> r3 = r2.returnType
            java.lang.Class r4 = java.lang.Void.TYPE
            if (r3 != r4) goto L_0x040b
            java.lang.String r3 = r2.name
            java.lang.String r4 = "deallocate"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x040b
            int r3 = r2.modifiers
            boolean r3 = java.lang.reflect.Modifier.isStatic(r3)
            if (r3 != 0) goto L_0x040b
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            r4 = 2
            if (r3 != r4) goto L_0x040b
            java.lang.Class<?>[] r3 = r2.parameterTypes
            r4 = 0
            r3 = r3[r4]
            java.lang.Class r4 = java.lang.Long.TYPE
            if (r3 != r4) goto L_0x040b
            java.lang.Class<?>[] r3 = r2.parameterTypes
            r4 = 1
            r3 = r3[r4]
            java.lang.Class r6 = java.lang.Long.TYPE
            if (r3 != r6) goto L_0x040b
            r2.deallocator = r4
            goto L_0x03a0
        L_0x040b:
            if (r9 == 0) goto L_0x041b
            java.lang.String r3 = r2.name
            java.lang.String r4 = "allocate"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x041b
            r3 = 1
            r2.allocator = r3
            goto L_0x03a0
        L_0x041b:
            r3 = 1
            if (r32 == 0) goto L_0x042e
            java.lang.String r4 = r2.name
            java.lang.String r6 = "allocateArray"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x042e
            r2.arrayAllocator = r3
            r2.allocator = r3
            goto L_0x03a0
        L_0x042e:
            java.lang.Class<?> r3 = r2.returnType
            java.lang.Class<java.nio.ByteBuffer> r4 = java.nio.ByteBuffer.class
            boolean r3 = r3.isAssignableFrom(r4)
            if (r3 == 0) goto L_0x0454
            java.lang.String r3 = r2.name
            java.lang.String r4 = "asDirectBuffer"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0454
            int r3 = r2.modifiers
            boolean r3 = java.lang.reflect.Modifier.isStatic(r3)
            if (r3 != 0) goto L_0x0454
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            if (r3 != 0) goto L_0x0454
            r3 = 1
            r2.bufferGetter = r3
            goto L_0x03a0
        L_0x0454:
            if (r16 != 0) goto L_0x0482
            if (r17 != 0) goto L_0x0465
            if (r7 == 0) goto L_0x0465
            java.lang.String r3 = r2.name
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0465
            if (r31 == 0) goto L_0x0465
            goto L_0x0482
        L_0x0465:
            if (r19 == 0) goto L_0x046f
            r3 = 1
            r2.valueSetter = r3
            r13 = r26
            r2.pairedMethod = r13
            goto L_0x04bf
        L_0x046f:
            r13 = r26
            r3 = 1
            if (r17 == 0) goto L_0x047b
            r2.memberGetter = r3
            r2.noReturnGetter = r15
            r2.pairedMethod = r13
            goto L_0x04bf
        L_0x047b:
            if (r20 == 0) goto L_0x04bf
            r2.memberSetter = r3
            r2.pairedMethod = r13
            goto L_0x04bf
        L_0x0482:
            r13 = r26
            r3 = 1
            r2.valueGetter = r3
            r2.noReturnGetter = r15
            r2.pairedMethod = r13
            goto L_0x04bf
        L_0x048c:
            r13 = r26
            boolean r4 = r3 instanceof org.bytedeco.javacpp.annotation.Function
            if (r4 != 0) goto L_0x04bf
            org.bytedeco.javacpp.tools.Logger r2 = r0.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Method \""
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = "\" cannot behave like a \""
            r4.append(r1)
            java.lang.Class r1 = r3.annotationType()
            java.lang.String r1 = r1.getSimpleName()
            r4.append(r1)
            java.lang.String r1 = "\". No code will be generated."
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.warn(r1)
            r1 = 0
            return r1
        L_0x04bf:
            if (r25 != 0) goto L_0x04d7
            java.lang.reflect.Method r3 = r2.pairedMethod
            if (r3 == 0) goto L_0x04d7
            java.lang.reflect.Method r3 = r2.pairedMethod
            java.lang.Class<org.bytedeco.javacpp.annotation.Name> r4 = org.bytedeco.javacpp.annotation.Name.class
            java.lang.annotation.Annotation r3 = r3.getAnnotation(r4)
            org.bytedeco.javacpp.annotation.Name r3 = (org.bytedeco.javacpp.annotation.Name) r3
            if (r3 == 0) goto L_0x04d7
            java.lang.String[] r3 = r3.value()
            r2.memberName = r3
        L_0x04d7:
            java.lang.Class<?> r3 = r2.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.NoOffset> r4 = org.bytedeco.javacpp.annotation.NoOffset.class
            boolean r3 = r3.isAnnotationPresent(r4)
            if (r3 != 0) goto L_0x04f4
            java.lang.Class<org.bytedeco.javacpp.annotation.NoOffset> r3 = org.bytedeco.javacpp.annotation.NoOffset.class
            boolean r3 = r1.isAnnotationPresent(r3)
            if (r3 != 0) goto L_0x04f4
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r3 = org.bytedeco.javacpp.annotation.Index.class
            boolean r3 = r1.isAnnotationPresent(r3)
            if (r3 == 0) goto L_0x04f2
            goto L_0x04f4
        L_0x04f2:
            r3 = 0
            goto L_0x04f5
        L_0x04f4:
            r3 = 1
        L_0x04f5:
            r2.noOffset = r3
            boolean r3 = r2.noOffset
            if (r3 != 0) goto L_0x0519
            java.lang.reflect.Method r3 = r2.pairedMethod
            if (r3 == 0) goto L_0x0519
            java.lang.reflect.Method r3 = r2.pairedMethod
            java.lang.Class<org.bytedeco.javacpp.annotation.NoOffset> r4 = org.bytedeco.javacpp.annotation.NoOffset.class
            boolean r3 = r3.isAnnotationPresent(r4)
            if (r3 != 0) goto L_0x0516
            java.lang.reflect.Method r3 = r2.pairedMethod
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r4 = org.bytedeco.javacpp.annotation.Index.class
            boolean r3 = r3.isAnnotationPresent(r4)
            if (r3 == 0) goto L_0x0514
            goto L_0x0516
        L_0x0514:
            r3 = 0
            goto L_0x0517
        L_0x0516:
            r3 = 1
        L_0x0517:
            r2.noOffset = r3
        L_0x0519:
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            if (r3 == 0) goto L_0x0529
            java.lang.Class<?>[] r3 = r2.parameterTypes
            r4 = 0
            r3 = r3[r4]
            boolean r3 = r3.isArray()
            if (r3 != 0) goto L_0x0569
        L_0x0529:
            boolean r3 = r2.valueGetter
            if (r3 != 0) goto L_0x0542
            boolean r3 = r2.memberGetter
            if (r3 == 0) goto L_0x0532
            goto L_0x0542
        L_0x0532:
            boolean r3 = r2.memberSetter
            if (r3 != 0) goto L_0x053a
            boolean r3 = r2.valueSetter
            if (r3 == 0) goto L_0x0547
        L_0x053a:
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            r4 = 1
            int r3 = r3 - r4
            r2.dim = r3
            goto L_0x0547
        L_0x0542:
            java.lang.Class<?>[] r3 = r2.parameterTypes
            int r3 = r3.length
            r2.dim = r3
        L_0x0547:
            boolean r3 = r2.valueGetter
            if (r3 != 0) goto L_0x054f
            boolean r3 = r2.valueSetter
            if (r3 == 0) goto L_0x0569
        L_0x054f:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r3 = org.bytedeco.javacpp.FunctionPointer.class
            java.lang.Class<?> r4 = r2.cls
            boolean r3 = r3.isAssignableFrom(r4)
            if (r3 == 0) goto L_0x0569
            java.lang.Class<?> r3 = r2.cls
            java.lang.Class<org.bytedeco.javacpp.annotation.Namespace> r4 = org.bytedeco.javacpp.annotation.Namespace.class
            boolean r3 = r3.isAnnotationPresent(r4)
            if (r3 == 0) goto L_0x0569
            int r3 = r2.dim
            r4 = 1
            int r3 = r3 - r4
            r2.dim = r3
        L_0x0569:
            if (r13 == 0) goto L_0x0577
            java.lang.Class<org.bytedeco.javacpp.annotation.Index> r3 = org.bytedeco.javacpp.annotation.Index.class
            java.lang.annotation.Annotation r3 = r13.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Index r3 = (org.bytedeco.javacpp.annotation.Index) r3
            r18 = r3
            r3 = 0
            goto L_0x057a
        L_0x0577:
            r3 = 0
            r18 = 0
        L_0x057a:
            r2.throwsException = r3
            java.lang.Class<?> r3 = r2.cls
            boolean r3 = noException(r3, r1)
            if (r3 != 0) goto L_0x05d5
            java.lang.annotation.Annotation[] r3 = r2.annotations
            java.lang.annotation.Annotation r3 = r0.by(r3)
            boolean r3 = r3 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r3 == 0) goto L_0x0596
            java.lang.Class<?> r3 = r2.returnType
            boolean r3 = noException(r3, r1)
            if (r3 == 0) goto L_0x05c6
        L_0x0596:
            if (r31 == 0) goto L_0x05a2
            java.lang.String r3 = r31.function()
            int r3 = r3.length()
            if (r3 > 0) goto L_0x05c6
        L_0x05a2:
            if (r18 == 0) goto L_0x05ae
            java.lang.String r3 = r18.function()
            int r3 = r3.length()
            if (r3 > 0) goto L_0x05c6
        L_0x05ae:
            boolean r3 = r2.deallocator
            if (r3 != 0) goto L_0x05d5
            boolean r3 = r2.valueGetter
            if (r3 != 0) goto L_0x05d5
            boolean r3 = r2.valueSetter
            if (r3 != 0) goto L_0x05d5
            boolean r3 = r2.memberGetter
            if (r3 != 0) goto L_0x05d5
            boolean r3 = r2.memberSetter
            if (r3 != 0) goto L_0x05d5
            boolean r3 = r2.bufferGetter
            if (r3 != 0) goto L_0x05d5
        L_0x05c6:
            java.lang.Class[] r1 = r35.getExceptionTypes()
            int r3 = r1.length
            if (r3 <= 0) goto L_0x05d1
            r3 = 0
            r1 = r1[r3]
            goto L_0x05d3
        L_0x05d1:
            java.lang.Class<java.lang.RuntimeException> r1 = java.lang.RuntimeException.class
        L_0x05d3:
            r2.throwsException = r1
        L_0x05d5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.methodInformation(java.lang.reflect.Method):org.bytedeco.javacpp.tools.MethodInformation");
    }

    static int allocatorMax(Class<?> cls, Method method) {
        Class<? super Object> cls2;
        Allocator allocator = (Allocator) method.getAnnotation(Allocator.class);
        Class<?> cls3 = cls;
        while (allocator == null && cls3 != null) {
            allocator = (Allocator) cls3.getAnnotation(Allocator.class);
            if (allocator != null) {
                break;
            }
            if (cls3.getEnclosingClass() != null) {
                cls2 = cls3.getEnclosingClass();
            } else {
                cls2 = cls3.getSuperclass();
            }
            cls3 = cls2;
        }
        if (allocator == null) {
            return ((Integer) Allocator.class.getDeclaredMethod("max", new Class[0]).getDefaultValue()).intValue();
        }
        try {
            return allocator.max();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean criticalRegion(Class<?> cls, Method method) {
        Class<? super Object> cls2;
        boolean z = baseClasses.contains(cls) || method.isAnnotationPresent(CriticalRegion.class);
        Class<?> cls3 = cls;
        while (!z && cls3 != null) {
            z = cls3.isAnnotationPresent(CriticalRegion.class);
            if (z) {
                break;
            }
            if (cls3.getEnclosingClass() != null) {
                cls2 = cls3.getEnclosingClass();
            } else {
                cls2 = cls3.getSuperclass();
            }
            cls3 = cls2;
        }
        return z;
    }

    static boolean noException(Class<?> cls, Method method) {
        Class<? super Object> cls2;
        boolean z = baseClasses.contains(cls) || method.isAnnotationPresent(NoException.class);
        Class<?> cls3 = cls;
        while (!z && cls3 != null) {
            z = cls3.isAnnotationPresent(NoException.class);
            if (z) {
                break;
            }
            if (cls3.getEnclosingClass() != null) {
                cls2 = cls3.getEnclosingClass();
            } else {
                cls2 = cls3.getSuperclass();
            }
            cls3 = cls2;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public AdapterInformation adapterInformation(boolean z, MethodInformation methodInformation, int i) {
        if (z && (methodInformation.parameterTypes[i] == String.class || methodInformation.valueSetter || methodInformation.memberSetter)) {
            return null;
        }
        String cast = cast(methodInformation, i);
        if (cast != null && cast.startsWith("(") && cast.endsWith(")")) {
            cast = cast.substring(1, cast.length() - 1);
        }
        if (cast == null || cast.length() == 0) {
            cast = cppCastTypeName(methodInformation.parameterTypes[i], methodInformation.parameterAnnotations[i])[0];
        }
        String valueTypeName = valueTypeName(cast);
        AdapterInformation adapterInformation = adapterInformation(z, valueTypeName, methodInformation.parameterAnnotations[i]);
        if (adapterInformation == null && methodInformation.pairedMethod != null && i == methodInformation.parameterTypes.length - 1) {
            return (methodInformation.valueSetter || methodInformation.memberSetter) ? adapterInformation(z, valueTypeName, methodInformation.pairedMethod.getAnnotations()) : adapterInformation;
        }
        return adapterInformation;
    }

    /* access modifiers changed from: package-private */
    public AdapterInformation adapterInformation(boolean z, String str, Annotation... annotationArr) {
        int i;
        Annotation[] annotationArr2 = annotationArr;
        int length = annotationArr2.length;
        int i2 = 0;
        String str2 = str;
        int i3 = 0;
        while (true) {
            i = 1;
            if (i3 >= length) {
                break;
            }
            Annotation annotation = annotationArr2[i3];
            if (annotation instanceof Cast) {
                Cast cast = (Cast) annotation;
                if (cast.value().length > 0 && cast.value()[0].length() > 0) {
                    str2 = constValueTypeName(cast.value()[0]);
                }
            }
            i3++;
        }
        int length2 = annotationArr2.length;
        String str3 = "";
        String str4 = str2;
        int i4 = 0;
        boolean z2 = false;
        String str5 = str3;
        AdapterInformation adapterInformation = null;
        while (i4 < length2) {
            Annotation annotation2 = annotationArr2[i4];
            Adapter adapter = annotation2 instanceof Adapter ? (Adapter) annotation2 : (Adapter) annotation2.annotationType().getAnnotation(Adapter.class);
            if (adapter != null) {
                AdapterInformation adapterInformation2 = new AdapterInformation();
                adapterInformation2.name = adapter.value();
                adapterInformation2.argc = adapter.argc();
                if (annotation2 != adapter) {
                    try {
                        Class<? extends Annotation> annotationType = annotation2.annotationType();
                        if (annotationType.isAnnotationPresent(Const.class)) {
                            z2 = true;
                        }
                        try {
                            String obj = annotationType.getDeclaredMethod(CommonProperties.VALUE, new Class[i2]).invoke(annotation2, new Object[i2]).toString();
                            if (obj != null && obj.length() > 0) {
                                str4 = obj;
                            }
                        } catch (NoSuchMethodException unused) {
                            str4 = null;
                        }
                        Cast cast2 = (Cast) annotationType.getAnnotation(Cast.class);
                        if (cast2 != null && str5.length() == 0) {
                            str5 = cast2.value()[i2];
                            if (str4 != null) {
                                str5 = str5 + "< " + str4 + " >";
                            }
                            if (cast2.value().length > i) {
                                str5 = str5 + cast2.value()[i];
                            }
                            if (cast2.value().length > 2) {
                                str3 = cast2.value()[2];
                            }
                        }
                    } catch (Exception e) {
                        this.logger.warn("Could not invoke the value() method on annotation \"" + annotation2 + "\": " + e);
                    }
                    if (str4 != null && str4.length() > 0) {
                        adapterInformation2.name += "< " + str4 + " >";
                    }
                    adapterInformation = adapterInformation2;
                } else {
                    adapterInformation = adapterInformation2;
                    i4++;
                    i2 = 0;
                }
            } else {
                if (annotation2 instanceof Const) {
                    i = 1;
                    z2 = true;
                } else if (annotation2 instanceof Cast) {
                    Cast cast3 = (Cast) annotation2;
                    i = 1;
                    if (cast3.value().length > 1) {
                        str5 = cast3.value()[1];
                    }
                    if (cast3.value().length > 2) {
                        str3 = cast3.value()[2];
                    }
                }
                i4++;
                i2 = 0;
            }
            i = 1;
            i4++;
            i2 = 0;
        }
        if (adapterInformation != null) {
            adapterInformation.cast = str5;
            adapterInformation.cast2 = str3;
            adapterInformation.constant = z2;
        }
        if (!z || !z2) {
            return adapterInformation;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String cast(MethodInformation methodInformation, int i) {
        String cast = cast(methodInformation.parameterTypes[i], methodInformation.parameterAnnotations[i]);
        if ((cast == null || cast.length() == 0) && i == methodInformation.parameterTypes.length - 1) {
            return ((methodInformation.valueSetter || methodInformation.memberSetter) && methodInformation.pairedMethod != null) ? cast(methodInformation.pairedMethod.getReturnType(), methodInformation.pairedMethod.getAnnotations()) : cast;
        }
        return cast;
    }

    /* access modifiers changed from: package-private */
    public String cast(Class<?> cls, Annotation... annotationArr) {
        String[] strArr;
        int length = annotationArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                strArr = null;
                break;
            }
            Cast cast = annotationArr[i];
            if ((!(cast instanceof Cast) || cast.value()[0].length() <= 0) && !(cast instanceof Const)) {
                i++;
            }
        }
        strArr = cppCastTypeName(cls, annotationArr);
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        return "(" + strArr[0] + strArr[1] + ")";
    }

    /* access modifiers changed from: package-private */
    public Annotation by(MethodInformation methodInformation, int i) {
        Annotation by = by(methodInformation.parameterAnnotations[i]);
        if (by != null || methodInformation.pairedMethod == null) {
            return by;
        }
        return ((methodInformation.valueSetter || methodInformation.memberSetter) && i == methodInformation.parameterAnnotations.length + -1) ? by(methodInformation.pairedMethod.getAnnotations()) : by;
    }

    /* access modifiers changed from: package-private */
    public Annotation by(Annotation... annotationArr) {
        Annotation annotation = null;
        for (Annotation annotation2 : annotationArr) {
            if ((annotation2 instanceof ByPtr) || (annotation2 instanceof ByPtrPtr) || (annotation2 instanceof ByPtrRef) || (annotation2 instanceof ByRef) || (annotation2 instanceof ByVal)) {
                if (annotation != null) {
                    this.logger.warn("\"By\" annotation \"" + annotation + "\" already found. Ignoring superfluous annotation \"" + annotation2 + "\".");
                } else {
                    annotation = annotation2;
                }
            }
        }
        return annotation;
    }

    /* access modifiers changed from: package-private */
    public Annotation behavior(Annotation... annotationArr) {
        Annotation annotation = null;
        for (Annotation annotation2 : annotationArr) {
            if ((annotation2 instanceof Function) || (annotation2 instanceof Allocator) || (annotation2 instanceof ArrayAllocator) || (annotation2 instanceof ValueSetter) || (annotation2 instanceof ValueGetter) || (annotation2 instanceof MemberGetter) || (annotation2 instanceof MemberSetter)) {
                if (annotation != null) {
                    this.logger.warn("Behavior annotation \"" + annotation + "\" already found. Ignoring superfluous annotation \"" + annotation2 + "\".");
                } else {
                    annotation = annotation2;
                }
            }
        }
        return annotation;
    }

    /* access modifiers changed from: package-private */
    public String enumValueType(Class<?> cls) {
        try {
            Field field = cls.getField(CommonProperties.VALUE);
            if (!field.getType().isPrimitive()) {
                Logger logger2 = this.logger;
                logger2.warn("Field \"value\" of enum type \"" + cls.getCanonicalName() + "\" is not of a primitive type. Compilation will most likely fail.");
            }
            return field.getType().getName();
        } catch (NoSuchFieldException unused) {
            Logger logger3 = this.logger;
            logger3.warn("Field \"value\" missing from enum type \"" + cls.getCanonicalName() + ". Compilation will most likely fail.");
            return null;
        }
    }

    static String constValueTypeName(String... strArr) {
        String str = strArr[0];
        return (str.endsWith(Marker.ANY_MARKER) || str.endsWith("&")) ? str.substring(0, str.length() - 1) : str;
    }

    static String valueTypeName(String... strArr) {
        String str = strArr[0];
        if (str.startsWith("const ")) {
            str = str.substring(6);
        }
        if (str.endsWith(" const")) {
            str = str.substring(0, str.length() - 6);
        }
        if (str.endsWith(Marker.ANY_MARKER) || str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str.endsWith(" const") ? str.substring(0, str.length() - 6) : str;
    }

    static boolean constFunction(Class<?> cls, Method method) {
        if (cls.isAnnotationPresent(Const.class)) {
            return true;
        }
        if (!method.isAnnotationPresent(Const.class)) {
            return false;
        }
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (annotation instanceof Const) {
                boolean[] value = ((Const) annotation).value();
                if (value.length <= 2 || !value[2]) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        r5 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] cppAnnotationTypeName(java.lang.Class<?> r10, java.lang.annotation.Annotation... r11) {
        /*
            r9 = this;
            java.lang.String[] r0 = r9.cppCastTypeName(r10, r11)
            r1 = 0
            r2 = r0[r1]
            r3 = 1
            r4 = r0[r3]
            int r5 = r11.length
            r6 = 0
        L_0x000c:
            if (r6 >= r5) goto L_0x002c
            r7 = r11[r6]
            boolean r8 = r7 instanceof org.bytedeco.javacpp.annotation.Cast
            if (r8 == 0) goto L_0x0023
            r8 = r7
            org.bytedeco.javacpp.annotation.Cast r8 = (org.bytedeco.javacpp.annotation.Cast) r8
            java.lang.String[] r8 = r8.value()
            r8 = r8[r1]
            int r8 = r8.length()
            if (r8 > 0) goto L_0x0027
        L_0x0023:
            boolean r7 = r7 instanceof org.bytedeco.javacpp.annotation.Const
            if (r7 == 0) goto L_0x0029
        L_0x0027:
            r5 = 1
            goto L_0x002d
        L_0x0029:
            int r6 = r6 + 1
            goto L_0x000c
        L_0x002c:
            r5 = 0
        L_0x002d:
            java.lang.annotation.Annotation r11 = r9.by(r11)
            boolean r6 = r11 instanceof org.bytedeco.javacpp.annotation.ByVal
            if (r6 == 0) goto L_0x003a
            java.lang.String r2 = constValueTypeName(r0)
            goto L_0x0099
        L_0x003a:
            boolean r6 = r11 instanceof org.bytedeco.javacpp.annotation.ByRef
            java.lang.String r7 = "&"
            if (r6 == 0) goto L_0x0054
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = constValueTypeName(r0)
            r10.append(r11)
            r10.append(r7)
            java.lang.String r2 = r10.toString()
            goto L_0x0099
        L_0x0054:
            boolean r6 = r11 instanceof org.bytedeco.javacpp.annotation.ByPtrPtr
            java.lang.String r8 = "*"
            if (r6 == 0) goto L_0x006c
            if (r5 != 0) goto L_0x006c
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r2)
            r10.append(r8)
            java.lang.String r2 = r10.toString()
            goto L_0x0099
        L_0x006c:
            boolean r5 = r11 instanceof org.bytedeco.javacpp.annotation.ByPtrRef
            if (r5 == 0) goto L_0x0080
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r2)
            r10.append(r7)
            java.lang.String r2 = r10.toString()
            goto L_0x0099
        L_0x0080:
            boolean r11 = r11 instanceof org.bytedeco.javacpp.annotation.ByPtr
            if (r11 == 0) goto L_0x0099
            boolean r10 = r10.isPrimitive()
            if (r10 == 0) goto L_0x0099
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r2)
            r10.append(r8)
            java.lang.String r2 = r10.toString()
        L_0x0099:
            r0[r1] = r2
            r0[r3] = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.cppAnnotationTypeName(java.lang.Class, java.lang.annotation.Annotation[]):java.lang.String[]");
    }

    /* access modifiers changed from: package-private */
    public String[] cppCastTypeName(Class<?> cls, Annotation... annotationArr) {
        String str;
        String[] strArr = null;
        boolean z = false;
        boolean z2 = false;
        for (Cast cast : annotationArr) {
            if (cast instanceof Cast) {
                z = strArr != null;
                String str2 = cast.value()[0];
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= str2.length()) {
                        str = "";
                        break;
                    }
                    char charAt = str2.charAt(i);
                    if (charAt != '<') {
                        if (charAt != '>') {
                            if (i2 == 0 && charAt == ')') {
                                str = str2.substring(i).trim();
                                str2 = str2.substring(0, i).trim();
                                break;
                            }
                        } else {
                            i2--;
                        }
                    } else {
                        i2++;
                    }
                    i++;
                }
                strArr = str2.length() > 0 ? new String[]{str2, str} : null;
            } else if (cast instanceof Const) {
                boolean[] value = ((Const) cast).value();
                if ((value.length != 1 || value[0]) && (value.length <= 1 || value[0] || value[1])) {
                    z = strArr != null;
                    if (!z) {
                        strArr = cppTypeName(cls);
                        if (!strArr[0].contains("(*")) {
                            if (value.length > 1 && value[1] && !strArr[0].endsWith(" const")) {
                                strArr[0] = strArr[0] + " const";
                            }
                            if (value.length > 0 && value[0] && !strArr[0].startsWith("const ")) {
                                strArr[0] = "const " + strArr[0];
                            }
                        } else if (value.length > 0 && value[0] && !strArr[0].endsWith(" const")) {
                            strArr[0] = strArr[0] + " const";
                        }
                        Annotation by = by(annotationArr);
                        if (by instanceof ByPtrPtr) {
                            strArr[0] = strArr[0] + Marker.ANY_MARKER;
                        } else if (by instanceof ByPtrRef) {
                            strArr[0] = strArr[0] + "&";
                        }
                    }
                }
            } else if ((cast instanceof Adapter) || cast.annotationType().isAnnotationPresent(Adapter.class)) {
                z2 = true;
            }
        }
        if (z && !z2) {
            this.logger.warn("Without \"Adapter\", \"Cast\" and \"Const\" annotations are mutually exclusive.");
        }
        return strArr == null ? cppTypeName(cls) : strArr;
    }

    /* access modifiers changed from: package-private */
    public String[] cppTypeName(Class<?> cls) {
        String str;
        String str2;
        if (cls == Buffer.class || cls == Pointer.class) {
            str = "void*";
        } else if (cls == byte[].class || cls == ByteBuffer.class || cls == BytePointer.class) {
            str = "signed char*";
        } else if (cls == short[].class || cls == ShortBuffer.class || cls == ShortPointer.class) {
            str = "short*";
        } else if (cls == int[].class || cls == IntBuffer.class || cls == IntPointer.class) {
            str = "int*";
        } else if (cls == long[].class || cls == LongBuffer.class || cls == LongPointer.class) {
            str = "jlong*";
        } else if (cls == float[].class || cls == FloatBuffer.class || cls == FloatPointer.class) {
            str = "float*";
        } else if (cls == double[].class || cls == DoubleBuffer.class || cls == DoublePointer.class) {
            str = "double*";
        } else if (cls == char[].class || cls == CharBuffer.class || cls == CharPointer.class) {
            str = "unsigned short*";
        } else if (cls == boolean[].class || cls == BooleanPointer.class) {
            str = "unsigned char*";
        } else if (cls == PointerPointer.class) {
            str = "void**";
        } else if (cls == String.class) {
            str = "const char*";
        } else if (cls == Byte.TYPE) {
            str = "signed char";
        } else if (cls == Long.TYPE) {
            str = "jlong";
        } else if (cls == Character.TYPE) {
            str = "unsigned short";
        } else if (cls == Boolean.TYPE) {
            str = "unsigned char";
        } else if (cls.isPrimitive()) {
            str = cls.getName();
        } else {
            if (FunctionPointer.class.isAssignableFrom(cls)) {
                String[] cppFunctionTypeName = cppFunctionTypeName(functionMethods(cls, (boolean[]) null));
                if (cppFunctionTypeName != null) {
                    return cppFunctionTypeName;
                }
            } else {
                String cppScopeName = cppScopeName(cls);
                if (cppScopeName.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(cppScopeName);
                    if (Enum.class.isAssignableFrom(cls)) {
                        str2 = "";
                    } else {
                        str2 = Marker.ANY_MARKER;
                    }
                    sb.append(str2);
                    str = sb.toString();
                } else {
                    Logger logger2 = this.logger;
                    logger2.warn("The class " + cls.getCanonicalName() + " does not map to any C++ type. Compilation will most likely fail.");
                }
            }
            str = "";
        }
        return new String[]{str, ""};
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0013 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] cppFunctionTypeName(java.lang.reflect.Method... r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = 0
            if (r14 == 0) goto L_0x0010
            int r2 = r14.length
            r3 = 0
        L_0x0006:
            if (r3 >= r2) goto L_0x0010
            r4 = r14[r3]
            if (r4 == 0) goto L_0x000d
            goto L_0x0011
        L_0x000d:
            int r3 = r3 + 1
            goto L_0x0006
        L_0x0010:
            r4 = r0
        L_0x0011:
            if (r4 != 0) goto L_0x0014
            return r0
        L_0x0014:
            java.lang.Class r2 = r4.getDeclaringClass()
            java.lang.Class<org.bytedeco.javacpp.annotation.Convention> r3 = org.bytedeco.javacpp.annotation.Convention.class
            java.lang.annotation.Annotation r3 = r2.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Convention r3 = (org.bytedeco.javacpp.annotation.Convention) r3
            java.lang.String r5 = ""
            if (r3 != 0) goto L_0x0026
            r3 = r5
            goto L_0x003b
        L_0x0026:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r3 = r3.value()
            r6.append(r3)
            java.lang.String r3 = " "
            r6.append(r3)
            java.lang.String r3 = r6.toString()
        L_0x003b:
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r6 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r6 = r6.isAssignableFrom(r2)
            if (r6 == 0) goto L_0x004c
            java.lang.Class<org.bytedeco.javacpp.annotation.Namespace> r6 = org.bytedeco.javacpp.annotation.Namespace.class
            java.lang.annotation.Annotation r6 = r2.getAnnotation(r6)
            org.bytedeco.javacpp.annotation.Namespace r6 = (org.bytedeco.javacpp.annotation.Namespace) r6
            goto L_0x004d
        L_0x004c:
            r6 = r0
        L_0x004d:
            if (r6 == 0) goto L_0x005a
            java.lang.String r7 = r6.value()
            int r7 = r7.length()
            if (r7 != 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r0 = r6
        L_0x005b:
            if (r0 != 0) goto L_0x005e
            goto L_0x0062
        L_0x005e:
            java.lang.String r5 = r0.value()
        L_0x0062:
            int r6 = r5.length()
            if (r6 <= 0) goto L_0x007f
            java.lang.String r6 = "::"
            boolean r7 = r5.endsWith(r6)
            if (r7 != 0) goto L_0x007f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            r7.append(r6)
            java.lang.String r5 = r7.toString()
        L_0x007f:
            java.lang.Class r6 = r4.getReturnType()
            java.lang.Class[] r7 = r4.getParameterTypes()
            java.lang.annotation.Annotation[] r8 = r4.getAnnotations()
            java.lang.annotation.Annotation[][] r9 = r4.getParameterAnnotations()
            java.lang.String[] r6 = r13.cppAnnotationTypeName(r6, r8)
            java.lang.String r10 = valueTypeName(r6)
            org.bytedeco.javacpp.tools.AdapterInformation r8 = r13.adapterInformation((boolean) r1, (java.lang.String) r10, (java.lang.annotation.Annotation[]) r8)
            r10 = 1
            if (r8 == 0) goto L_0x00a9
            java.lang.String r11 = r8.cast
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x00a9
            java.lang.String r6 = r8.cast
            goto L_0x00bc
        L_0x00a9:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r11 = r6[r1]
            r8.append(r11)
            r6 = r6[r10]
            r8.append(r6)
            java.lang.String r6 = r8.toString()
        L_0x00bc:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            java.lang.String r6 = " ("
            r8.append(r6)
            r8.append(r3)
            r8.append(r5)
            java.lang.String r3 = "*"
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            r14 = r14[r1]
            java.lang.String r5 = ")"
            if (r4 != r14) goto L_0x01af
            java.lang.Class<org.bytedeco.javacpp.FunctionPointer> r14 = org.bytedeco.javacpp.FunctionPointer.class
            boolean r14 = r14.isAssignableFrom(r2)
            if (r14 == 0) goto L_0x0114
            if (r0 == 0) goto L_0x0114
            int r14 = r7.length
            if (r14 == 0) goto L_0x00f5
            java.lang.Class<org.bytedeco.javacpp.Pointer> r14 = org.bytedeco.javacpp.Pointer.class
            r6 = r7[r1]
            boolean r14 = r14.isAssignableFrom(r6)
            if (r14 != 0) goto L_0x0114
        L_0x00f5:
            org.bytedeco.javacpp.tools.Logger r14 = r13.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "First parameter of caller method call() or apply() for member function pointer "
            r6.append(r8)
            java.lang.String r8 = r2.getCanonicalName()
            r6.append(r8)
            java.lang.String r8 = " is not a Pointer. Compilation will most likely fail."
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r14.warn(r6)
        L_0x0114:
            if (r0 != 0) goto L_0x0118
            r14 = 0
            goto L_0x0119
        L_0x0118:
            r14 = 1
        L_0x0119:
            java.lang.String r0 = ")("
        L_0x011b:
            int r6 = r7.length
            if (r14 >= r6) goto L_0x01a0
            r6 = r7[r14]
            r8 = r9[r14]
            java.lang.String[] r6 = r13.cppAnnotationTypeName(r6, r8)
            java.lang.String r8 = valueTypeName(r6)
            r11 = r9[r14]
            org.bytedeco.javacpp.tools.AdapterInformation r8 = r13.adapterInformation((boolean) r1, (java.lang.String) r8, (java.lang.annotation.Annotation[]) r11)
            if (r8 == 0) goto L_0x0147
            boolean r11 = r8.constant
            if (r11 == 0) goto L_0x0147
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r0)
            java.lang.String r0 = "const "
            r11.append(r0)
            java.lang.String r0 = r11.toString()
        L_0x0147:
            java.lang.String r11 = " arg"
            if (r8 == 0) goto L_0x016b
            java.lang.String r12 = r8.cast
            int r12 = r12.length()
            if (r12 <= 0) goto L_0x016b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            java.lang.String r0 = r8.cast
            r6.append(r0)
            r6.append(r11)
            r6.append(r14)
            java.lang.String r0 = r6.toString()
            goto L_0x0187
        L_0x016b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            r0 = r6[r1]
            r8.append(r0)
            r8.append(r11)
            r8.append(r14)
            r0 = r6[r10]
            r8.append(r0)
            java.lang.String r0 = r8.toString()
        L_0x0187:
            int r6 = r7.length
            int r6 = r6 - r10
            if (r14 >= r6) goto L_0x019c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            java.lang.String r0 = ", "
            r6.append(r0)
            java.lang.String r0 = r6.toString()
        L_0x019c:
            int r14 = r14 + 1
            goto L_0x011b
        L_0x01a0:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r0)
            r14.append(r5)
            java.lang.String r5 = r14.toString()
        L_0x01af:
            boolean r14 = constFunction(r2, r4)
            if (r14 == 0) goto L_0x01c6
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r5)
            java.lang.String r0 = " const"
            r14.append(r0)
            java.lang.String r5 = r14.toString()
        L_0x01c6:
            r14 = 2
            java.lang.String[] r14 = new java.lang.String[r14]
            r14[r1] = r3
            r14[r10] = r5
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.cppFunctionTypeName(java.lang.reflect.Method[]):java.lang.String[]");
    }

    static String cppScopeName(MethodInformation methodInformation) {
        String str;
        String cppScopeName = cppScopeName(methodInformation.cls);
        if (methodInformation.method.isAnnotationPresent(Virtual.class)) {
            cppScopeName = "JavaCPP_" + mangle(cppScopeName);
        }
        Namespace namespace = (Namespace) methodInformation.method.getAnnotation(Namespace.class);
        if (namespace == null && methodInformation.pairedMethod != null) {
            namespace = (Namespace) methodInformation.pairedMethod.getAnnotation(Namespace.class);
        }
        if (namespace == null) {
            str = "";
        } else {
            str = namespace.value();
        }
        if ((namespace != null && namespace.value().length() == 0) || str.startsWith("::")) {
            cppScopeName = "";
        }
        if (cppScopeName.length() > 0 && !cppScopeName.endsWith("::")) {
            cppScopeName = cppScopeName + "::";
        }
        String str2 = cppScopeName + str;
        if (str.length() > 0 && !str.endsWith("::")) {
            str2 = str2 + "::";
        }
        return str2 + methodInformation.memberName[0];
    }

    static String cppScopeName(Class<?> cls) {
        String str;
        String str2;
        String str3 = "";
        while (cls != null) {
            Namespace namespace = (Namespace) cls.getAnnotation(Namespace.class);
            if (namespace == null) {
                str = "";
            } else {
                str = namespace.value();
            }
            if ((Enum.class.isAssignableFrom(cls) || Pointer.class.isAssignableFrom(cls)) && (!baseClasses.contains(cls) || cls.isAnnotationPresent(Name.class))) {
                Name name = (Name) cls.getAnnotation(Name.class);
                if (name == null) {
                    String name2 = cls.getName();
                    int lastIndexOf = name2.lastIndexOf("$");
                    if (lastIndexOf < 0) {
                        lastIndexOf = name2.lastIndexOf(".");
                    }
                    str2 = name2.substring(lastIndexOf + 1);
                } else {
                    str2 = name.value()[0];
                }
                if (str.length() > 0 && !str.endsWith("::")) {
                    str = str + "::";
                }
                str = str + str2;
            }
            if (str3.length() > 0 && !str3.startsWith("class ") && !str3.startsWith("struct ") && !str3.startsWith("union ") && !str.endsWith("::")) {
                str = str + "::";
            }
            str3 = str + str3;
            if ((namespace != null && namespace.value().length() == 0) || str.startsWith("::")) {
                break;
            }
            cls = cls.getEnclosingClass();
        }
        return str3;
    }

    static String jniTypeName(Class cls) {
        if (cls == Byte.TYPE) {
            return "jbyte";
        }
        if (cls == Short.TYPE) {
            return "jshort";
        }
        if (cls == Integer.TYPE) {
            return "jint";
        }
        if (cls == Long.TYPE) {
            return "jlong";
        }
        if (cls == Float.TYPE) {
            return "jfloat";
        }
        if (cls == Double.TYPE) {
            return "jdouble";
        }
        if (cls == Character.TYPE) {
            return "jchar";
        }
        if (cls == Boolean.TYPE) {
            return "jboolean";
        }
        if (cls == byte[].class) {
            return "jbyteArray";
        }
        if (cls == short[].class) {
            return "jshortArray";
        }
        if (cls == int[].class) {
            return "jintArray";
        }
        if (cls == long[].class) {
            return "jlongArray";
        }
        if (cls == float[].class) {
            return "jfloatArray";
        }
        if (cls == double[].class) {
            return "jdoubleArray";
        }
        if (cls == char[].class) {
            return "jcharArray";
        }
        if (cls == boolean[].class) {
            return "jbooleanArray";
        }
        if (cls.isArray()) {
            return "jobjectArray";
        }
        if (cls == String.class) {
            return "jstring";
        }
        if (cls == Class.class) {
            return "jclass";
        }
        return cls == Void.TYPE ? "void" : "jobject";
    }

    static String signature(Class... clsArr) {
        StringBuilder sb = new StringBuilder(clsArr.length * 2);
        for (Class cls : clsArr) {
            if (cls == Byte.TYPE) {
                sb.append("B");
            } else if (cls == Short.TYPE) {
                sb.append(ExifInterface.LATITUDE_SOUTH);
            } else if (cls == Integer.TYPE) {
                sb.append("I");
            } else if (cls == Long.TYPE) {
                sb.append("J");
            } else if (cls == Float.TYPE) {
                sb.append("F");
            } else if (cls == Double.TYPE) {
                sb.append("D");
            } else if (cls == Boolean.TYPE) {
                sb.append("Z");
            } else if (cls == Character.TYPE) {
                sb.append("C");
            } else if (cls == Void.TYPE) {
                sb.append(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            } else if (cls.isArray()) {
                sb.append(cls.getName().replace('.', '/'));
            } else {
                sb.append("L");
                sb.append(cls.getName().replace('.', '/'));
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006f, code lost:
        if (r4 != 3) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String mangle(java.lang.String r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r7.length()
            r2 = 2
            int r1 = r1 * 2
            r0.<init>(r1)
            r1 = 0
        L_0x000d:
            int r3 = r7.length()
            if (r1 >= r3) goto L_0x0087
            char r3 = r7.charAt(r1)
            r4 = 48
            if (r3 < r4) goto L_0x001f
            r4 = 57
            if (r3 <= r4) goto L_0x002f
        L_0x001f:
            r4 = 65
            if (r3 < r4) goto L_0x0027
            r4 = 90
            if (r3 <= r4) goto L_0x002f
        L_0x0027:
            r4 = 97
            if (r3 < r4) goto L_0x0033
            r4 = 122(0x7a, float:1.71E-43)
            if (r3 > r4) goto L_0x0033
        L_0x002f:
            r0.append(r3)
            goto L_0x0084
        L_0x0033:
            r4 = 95
            if (r3 != r4) goto L_0x003d
            java.lang.String r3 = "_1"
            r0.append(r3)
            goto L_0x0084
        L_0x003d:
            r4 = 59
            if (r3 != r4) goto L_0x0047
            java.lang.String r3 = "_2"
            r0.append(r3)
            goto L_0x0084
        L_0x0047:
            r4 = 91
            if (r3 != r4) goto L_0x0051
            java.lang.String r3 = "_3"
            r0.append(r3)
            goto L_0x0084
        L_0x0051:
            r4 = 46
            if (r3 == r4) goto L_0x007f
            r4 = 47
            if (r3 != r4) goto L_0x005a
            goto L_0x007f
        L_0x005a:
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            java.lang.String r4 = "_0"
            r0.append(r4)
            int r4 = r3.length()
            r5 = 1
            java.lang.String r6 = "0"
            if (r4 == r5) goto L_0x0072
            if (r4 == r2) goto L_0x0075
            r5 = 3
            if (r4 == r5) goto L_0x0078
            goto L_0x007b
        L_0x0072:
            r0.append(r6)
        L_0x0075:
            r0.append(r6)
        L_0x0078:
            r0.append(r6)
        L_0x007b:
            r0.append(r3)
            goto L_0x0084
        L_0x007f:
            java.lang.String r3 = "_"
            r0.append(r3)
        L_0x0084:
            int r1 = r1 + 1
            goto L_0x000d
        L_0x0087:
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Generator.mangle(java.lang.String):java.lang.String");
    }
}
