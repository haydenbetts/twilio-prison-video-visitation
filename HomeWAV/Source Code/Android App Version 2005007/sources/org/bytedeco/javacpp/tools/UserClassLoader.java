package org.bytedeco.javacpp.tools;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Marker;

class UserClassLoader extends URLClassLoader {
    private List<String> paths = new ArrayList();

    public UserClassLoader() {
        super(new URL[0]);
    }

    public UserClassLoader(ClassLoader classLoader) {
        super(new URL[0], classLoader);
    }

    public void addPaths(String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                File file = new File(str);
                if (file.getName().equals(Marker.ANY_MARKER)) {
                    File[] listFiles = file.getParentFile().listFiles();
                    String[] strArr2 = new String[listFiles.length];
                    int i = 0;
                    for (File path : listFiles) {
                        String path2 = path.getPath();
                        if (path2.endsWith(".jar") || path2.endsWith(".JAR")) {
                            strArr2[i] = path2;
                            i++;
                        }
                    }
                    addPaths((String[]) Arrays.copyOf(strArr2, i));
                } else if (!this.paths.contains(str) && file.exists()) {
                    this.paths.add(str);
                    try {
                        addURL(file.toURI().toURL());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public String[] getPaths() {
        if (this.paths.isEmpty()) {
            addPaths(System.getProperty("user.dir"));
        }
        List<String> list = this.paths;
        return (String[]) list.toArray(new String[list.size()]);
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str) throws ClassNotFoundException {
        if (this.paths.isEmpty()) {
            addPaths(System.getProperty("user.dir"));
        }
        return super.findClass(str);
    }
}
