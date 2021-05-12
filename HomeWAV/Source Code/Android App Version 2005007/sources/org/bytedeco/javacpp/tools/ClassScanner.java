package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class ClassScanner {
    final ClassFilter classFilter;
    final Collection<Class> classes;
    final UserClassLoader loader;
    final Logger logger;

    ClassScanner(Logger logger2, Collection<Class> collection, UserClassLoader userClassLoader) {
        this(logger2, collection, userClassLoader, (ClassFilter) null);
    }

    ClassScanner(Logger logger2, Collection<Class> collection, UserClassLoader userClassLoader, ClassFilter classFilter2) {
        this.logger = logger2;
        this.classes = collection;
        this.loader = userClassLoader;
        this.classFilter = classFilter2;
    }

    public Collection<Class> getClasses() {
        return this.classes;
    }

    public UserClassLoader getClassLoader() {
        return this.loader;
    }

    public void addClass(String str) throws ClassNotFoundException, NoClassDefFoundError {
        if (str != null) {
            if (str.endsWith(".class")) {
                str = str.substring(0, str.length() - 6);
            }
            addClass((Class) Class.forName(str, false, this.loader));
        }
    }

    public void addClass(Class cls) {
        if (!this.classes.contains(cls)) {
            this.classes.add(cls);
        }
    }

    public void addMatchingFile(String str, String str2, boolean z, byte... bArr) throws ClassNotFoundException, NoClassDefFoundError {
        if (str != null && str.endsWith(".class") && !str.contains("-")) {
            ClassFilter classFilter2 = this.classFilter;
            if (classFilter2 != null && !classFilter2.keep(str, bArr)) {
                return;
            }
            if (str2 == null || ((z && str.startsWith(str2)) || str.regionMatches(0, str2, 0, Math.max(str.lastIndexOf(47), str2.lastIndexOf(47))))) {
                addClass(str.replace('/', '.'));
            }
        }
    }

    public void addMatchingDir(String str, File file, String str2, boolean z) throws ClassNotFoundException, IOException, NoClassDefFoundError {
        String str3;
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles);
        for (File file2 : listFiles) {
            if (str == null) {
                str3 = file2.getName();
            } else {
                str3 = str + file2.getName();
            }
            if (file2.isDirectory()) {
                addMatchingDir(str3 + "/", file2, str2, z);
            } else {
                addMatchingFile(str3, str2, z, Files.readAllBytes(file2.toPath()));
            }
        }
    }

    public void addPackage(String str, boolean z) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        String str2;
        Throwable th;
        String str3 = str;
        boolean z2 = z;
        String[] paths = this.loader.getPaths();
        if (str3 == null || str.length() <= 0) {
            str2 = str3;
        } else {
            str2 = str3.replace('.', '/') + "/";
        }
        int size = this.classes.size();
        for (String file : paths) {
            File file2 = new File(file);
            if (file2.isDirectory()) {
                addMatchingDir((String) null, file2, str2, z2);
            } else {
                JarFile jarFile = new JarFile(file2);
                try {
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextElement = entries.nextElement();
                        String name = nextElement.getName();
                        long size2 = nextElement.getSize();
                        nextElement.getTime();
                        if (size2 > 0) {
                            InputStream inputStream = jarFile.getInputStream(nextElement);
                            int i = (int) size2;
                            try {
                                byte[] bArr = new byte[i];
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= i) {
                                        break;
                                    }
                                    int read = inputStream.read(bArr, i2, i - i2);
                                    if (read < 0) {
                                        break;
                                    }
                                    i2 += read;
                                }
                                addMatchingFile(name, str2, z2, bArr);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                throw th3;
                            }
                        }
                    }
                    jarFile.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                } finally {
                    th = th4;
                    try {
                    } catch (Throwable th5) {
                        Throwable th6 = th5;
                        try {
                            jarFile.close();
                        } catch (Throwable th7) {
                            th.addSuppressed(th7);
                        }
                        throw th6;
                    }
                }
            }
        }
        if (this.classes.size() == 0 && (str3 == null || str.length() == 0)) {
            this.logger.warn("No classes found in the unnamed package");
        } else if (size == this.classes.size() && str3 != null) {
            this.logger.warn("No classes found in package " + str3);
        }
    }

    public void addClassOrPackage(String str) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        if (str != null) {
            String replace = str.replace('/', '.');
            if (replace.endsWith(".**")) {
                addPackage(replace.substring(0, replace.length() - 3), true);
            } else if (replace.endsWith(".*")) {
                addPackage(replace.substring(0, replace.length() - 2), false);
            } else {
                addClass(replace);
            }
        }
    }
}
