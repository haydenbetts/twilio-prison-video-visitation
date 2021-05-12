package org.bytedeco.javacpp.tools;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;

public class CacheMojo extends AbstractMojo {
    PluginDescriptor plugin;
    MavenProject project;

    /* access modifiers changed from: package-private */
    public String join(String str, Iterable<String> iterable) {
        String str2;
        String str3 = "";
        for (String next : iterable) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            if (str3.length() > 0) {
                str2 = str;
            } else {
                str2 = "";
            }
            sb.append(str2);
            sb.append(next);
            str3 = sb.toString();
        }
        return str3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x011d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0123, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x012a, code lost:
        r0.error("Failed to execute JavaCPP Loader: " + r1.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0149, code lost:
        throw new org.apache.maven.plugin.MojoExecutionException("Failed to execute JavaCPP Loader", r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x011d A[ExcHandler: IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoClassDefFoundError | InvocationTargetException (e java.lang.Throwable), Splitter:B:1:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() throws org.apache.maven.plugin.MojoExecutionException {
        /*
            r12 = this;
            org.apache.maven.plugin.logging.Log r0 = r12.getLog()
            org.bytedeco.javacpp.tools.CacheMojo$1 r1 = new org.bytedeco.javacpp.tools.CacheMojo$1
            r1.<init>(r0)
            org.bytedeco.javacpp.tools.CacheMojo$2 r2 = new org.bytedeco.javacpp.tools.CacheMojo$2
            r2.<init>()
            org.apache.maven.project.MavenProject r3 = r12.project     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.Set r3 = r3.getArtifacts()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r4.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            org.bytedeco.javacpp.tools.UserClassLoader r5 = new org.bytedeco.javacpp.tools.UserClassLoader     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r5.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            org.bytedeco.javacpp.tools.ClassScanner r6 = new org.bytedeco.javacpp.tools.ClassScanner     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r6.<init>(r1, r4, r5, r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2 = 1
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            org.apache.maven.plugin.descriptor.PluginDescriptor r8 = r12.plugin     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            org.apache.maven.artifact.Artifact r8 = r8.getPluginArtifact()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.io.File r8 = r8.getFile()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r9 = 0
            r7[r9] = r8     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r5.addPaths(r7)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
        L_0x003e:
            boolean r7 = r3.hasNext()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            if (r7 == 0) goto L_0x005a
            java.lang.Object r7 = r3.next()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            org.apache.maven.artifact.Artifact r7 = (org.apache.maven.artifact.Artifact) r7     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.io.File r7 = r7.getFile()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r8[r9] = r7     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r5.addPaths(r8)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            goto L_0x003e
        L_0x005a:
            r3 = 0
            r6.addPackage(r3, r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r3.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.Iterator r6 = r4.iterator()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
        L_0x0067:
            boolean r7 = r6.hasNext()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            if (r7 == 0) goto L_0x00a1
            java.lang.Object r7 = r6.next()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Class r7 = (java.lang.Class) r7     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r8 = "cachePackage"
            java.lang.Class[] r10 = new java.lang.Class[r9]     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.reflect.Method r8 = r7.getMethod(r8, r10)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r10.<init>()     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r11 = "Caching "
            r10.append(r11)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r10.append(r7)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r10 = r10.toString()     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r1.info(r10)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Object r7 = r8.invoke(r7, r10)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.io.File r7 = (java.io.File) r7     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            if (r7 == 0) goto L_0x0067
            java.lang.String r7 = r7.getCanonicalPath()     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r3.add(r7)     // Catch:{ NoSuchMethodException -> 0x0067, IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            goto L_0x0067
        L_0x00a1:
            java.lang.String r1 = "org.bytedeco.javacpp.Loader"
            java.lang.Class r1 = java.lang.Class.forName(r1, r2, r5)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r5 = "load"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Class<java.lang.Class[]> r7 = java.lang.Class[].class
            r6[r9] = r7     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            int r6 = r4.size()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Object[] r4 = r4.toArray(r6)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2[r9] = r4     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.Object r1 = r5.invoke(r1, r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.util.LinkedHashSet r2 = new java.util.LinkedHashSet     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            int r4 = r1.length     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
        L_0x00cf:
            if (r9 >= r4) goto L_0x00e4
            r5 = r1[r9]     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            if (r5 == 0) goto L_0x00e1
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r6.<init>(r5)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r5 = r6.getParent()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2.add(r5)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
        L_0x00e1:
            int r9 = r9 + 1
            goto L_0x00cf
        L_0x00e4:
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r4.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r5 = "PATH="
            r4.append(r5)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r5 = java.io.File.pathSeparator     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r2 = r12.join(r5, r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r4.append(r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r2 = r4.toString()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r1.println(r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2.<init>()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r4 = "PACKAGEPATH="
            r2.append(r4)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r4 = java.io.File.pathSeparator     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r3 = r12.join(r4, r3)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r2.append(r3)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            r1.println(r2)     // Catch:{ IOException -> 0x0129, ClassNotFoundException -> 0x0127, NoClassDefFoundError -> 0x0125, NoSuchMethodException -> 0x0123, IllegalAccessException -> 0x0121, IllegalArgumentException -> 0x011f, InvocationTargetException -> 0x011d }
            return
        L_0x011d:
            r1 = move-exception
            goto L_0x012a
        L_0x011f:
            r1 = move-exception
            goto L_0x012a
        L_0x0121:
            r1 = move-exception
            goto L_0x012a
        L_0x0123:
            r1 = move-exception
            goto L_0x012a
        L_0x0125:
            r1 = move-exception
            goto L_0x012a
        L_0x0127:
            r1 = move-exception
            goto L_0x012a
        L_0x0129:
            r1 = move-exception
        L_0x012a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to execute JavaCPP Loader: "
            r2.append(r3)
            java.lang.String r3 = r1.getMessage()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.error(r2)
            org.apache.maven.plugin.MojoExecutionException r0 = new org.apache.maven.plugin.MojoExecutionException
            java.lang.String r2 = "Failed to execute JavaCPP Loader"
            r0.<init>(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.CacheMojo.execute():void");
    }
}
