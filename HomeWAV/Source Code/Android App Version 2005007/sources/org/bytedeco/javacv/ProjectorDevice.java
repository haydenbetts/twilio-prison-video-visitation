package org.bytedeco.javacv;

import java.awt.DisplayMode;
import java.beans.PropertyChangeListener;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileNodeIterator;
import org.bytedeco.opencv.opencv_core.FileStorage;

public class ProjectorDevice extends ProjectiveDevice {
    private static ThreadLocal<CvMat> B4x3 = CvMat.createThreadLocal(4, 3);
    private static ThreadLocal<CvMat> x23x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> x34x1 = CvMat.createThreadLocal(4, 1);
    private Settings settings;

    public interface Settings {
        void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

        int getBitDepth();

        String getDescription();

        int getImageHeight();

        int getImageWidth();

        long getLatency();

        String getName();

        int getRefreshRate();

        double getResponseGamma();

        int getScreenNumber();

        boolean isUseOpenGL();

        void removePropertyChangeListener(PropertyChangeListener propertyChangeListener);

        void setBitDepth(int i);

        void setImageHeight(int i);

        void setImageWidth(int i);

        void setLatency(long j);

        void setName(String str);

        void setRefreshRate(int i);

        void setResponseGamma(double d);

        void setScreenNumber(int i);

        void setUseOpenGL(boolean z);
    }

    public ProjectorDevice(String str) {
        super(str);
    }

    public ProjectorDevice(String str, String str2) throws ProjectiveDevice.Exception {
        super(str, str2);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public ProjectorDevice(String str, FileStorage fileStorage) throws ProjectiveDevice.Exception {
        super(str, fileStorage);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public ProjectorDevice(Settings settings2) throws ProjectiveDevice.Exception {
        super((ProjectiveDevice.Settings) settings2);
    }

    public static class SettingsImplementation extends ProjectiveDevice.Settings implements Settings {
        int bitDepth;
        int imageHeight;
        int imageWidth;
        long latency;
        int refreshRate;
        int screenNumber;
        private boolean useOpenGL;

        public SettingsImplementation() {
            this.screenNumber = CanvasFrame.getScreenDevices().length <= 1 ? 0 : 1;
            this.latency = 200;
            this.imageWidth = 0;
            this.imageHeight = 0;
            this.bitDepth = 0;
            this.refreshRate = 0;
            this.useOpenGL = false;
            this.name = "Projector  0";
            setScreenNumber(this.screenNumber);
        }

        public SettingsImplementation(ProjectiveDevice.Settings settings) {
            super(settings);
            this.screenNumber = CanvasFrame.getScreenDevices().length <= 1 ? 0 : 1;
            this.latency = 200;
            this.imageWidth = 0;
            this.imageHeight = 0;
            this.bitDepth = 0;
            this.refreshRate = 0;
            this.useOpenGL = false;
            if (settings instanceof SettingsImplementation) {
                SettingsImplementation settingsImplementation = (SettingsImplementation) settings;
                this.screenNumber = settingsImplementation.screenNumber;
                this.latency = settingsImplementation.latency;
                this.imageWidth = settingsImplementation.imageWidth;
                this.imageHeight = settingsImplementation.imageHeight;
                this.bitDepth = settingsImplementation.bitDepth;
                this.refreshRate = settingsImplementation.refreshRate;
                this.useOpenGL = settingsImplementation.useOpenGL;
            }
        }

        public int getScreenNumber() {
            return this.screenNumber;
        }

        public void setScreenNumber(int i) {
            DisplayMode displayMode = CanvasFrame.getDisplayMode(i);
            String description = getDescription();
            Integer valueOf = Integer.valueOf(this.screenNumber);
            this.screenNumber = i;
            firePropertyChange("screenNumber", valueOf, Integer.valueOf(i));
            firePropertyChange("description", description, getDescription());
            Integer valueOf2 = Integer.valueOf(this.imageWidth);
            int i2 = 0;
            int width = displayMode == null ? 0 : displayMode.getWidth();
            this.imageWidth = width;
            firePropertyChange("imageWidth", valueOf2, Integer.valueOf(width));
            Integer valueOf3 = Integer.valueOf(this.imageHeight);
            int height = displayMode == null ? 0 : displayMode.getHeight();
            this.imageHeight = height;
            firePropertyChange("imageHeight", valueOf3, Integer.valueOf(height));
            Integer valueOf4 = Integer.valueOf(this.bitDepth);
            int bitDepth2 = displayMode == null ? 0 : displayMode.getBitDepth();
            this.bitDepth = bitDepth2;
            firePropertyChange("bitDepth", valueOf4, Integer.valueOf(bitDepth2));
            Integer valueOf5 = Integer.valueOf(this.refreshRate);
            if (displayMode != null) {
                i2 = displayMode.getRefreshRate();
            }
            this.refreshRate = i2;
            firePropertyChange("refreshRate", valueOf5, Integer.valueOf(i2));
            Double valueOf6 = Double.valueOf(this.responseGamma);
            double gamma = CanvasFrame.getGamma(i);
            this.responseGamma = gamma;
            firePropertyChange("responseGamma", valueOf6, Double.valueOf(gamma));
        }

        public long getLatency() {
            return this.latency;
        }

        public void setLatency(long j) {
            this.latency = j;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
            r1 = r3.screenNumber;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getDescription() {
            /*
                r3 = this;
                java.lang.String[] r0 = org.bytedeco.javacv.CanvasFrame.getScreenDescriptions()
                if (r0 == 0) goto L_0x0010
                int r1 = r3.screenNumber
                if (r1 < 0) goto L_0x0010
                int r2 = r0.length
                if (r1 >= r2) goto L_0x0010
                r0 = r0[r1]
                return r0
            L_0x0010:
                java.lang.String r0 = ""
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.ProjectorDevice.SettingsImplementation.getDescription():java.lang.String");
        }

        public int getImageWidth() {
            return this.imageWidth;
        }

        public void setImageWidth(int i) {
            Integer valueOf = Integer.valueOf(this.imageWidth);
            this.imageWidth = i;
            firePropertyChange("imageWidth", valueOf, Integer.valueOf(i));
        }

        public int getImageHeight() {
            return this.imageHeight;
        }

        public void setImageHeight(int i) {
            Integer valueOf = Integer.valueOf(this.imageHeight);
            this.imageHeight = i;
            firePropertyChange("imageHeight", valueOf, Integer.valueOf(i));
        }

        public int getBitDepth() {
            return this.bitDepth;
        }

        public void setBitDepth(int i) {
            this.bitDepth = i;
        }

        public int getRefreshRate() {
            return this.refreshRate;
        }

        public void setRefreshRate(int i) {
            this.refreshRate = i;
        }

        public boolean isUseOpenGL() {
            return this.useOpenGL;
        }

        public void setUseOpenGL(boolean z) {
            this.useOpenGL = z;
        }
    }

    public static class CalibrationSettings extends ProjectiveDevice.CalibrationSettings implements Settings {
        double brightnessBackground = 0.0d;
        double brightnessForeground = 1.0d;
        SettingsImplementation si = new SettingsImplementation() {
            public void firePropertyChange(String str, Object obj, Object obj2) {
                CalibrationSettings.this.firePropertyChange(str, obj, obj2);
            }
        };

        public CalibrationSettings() {
        }

        public CalibrationSettings(ProjectiveDevice.CalibrationSettings calibrationSettings) {
            super(calibrationSettings);
            if (calibrationSettings instanceof CalibrationSettings) {
                CalibrationSettings calibrationSettings2 = (CalibrationSettings) calibrationSettings;
                this.si = new SettingsImplementation(calibrationSettings2.si);
                this.brightnessBackground = calibrationSettings2.brightnessBackground;
                this.brightnessForeground = calibrationSettings2.brightnessForeground;
            }
        }

        public String getName() {
            return this.si.getName();
        }

        public void setName(String str) {
            this.si.setName(str);
        }

        public double getResponseGamma() {
            return this.si.getResponseGamma();
        }

        public void setResponseGamma(double d) {
            this.si.setResponseGamma(d);
        }

        public int getScreenNumber() {
            return this.si.getScreenNumber();
        }

        public void setScreenNumber(int i) {
            this.si.setScreenNumber(i);
        }

        public long getLatency() {
            return this.si.getLatency();
        }

        public void setLatency(long j) {
            this.si.setLatency(j);
        }

        public String getDescription() {
            return this.si.getDescription();
        }

        public int getImageWidth() {
            return this.si.getImageWidth();
        }

        public void setImageWidth(int i) {
            this.si.setImageWidth(i);
        }

        public int getImageHeight() {
            return this.si.getImageHeight();
        }

        public void setImageHeight(int i) {
            this.si.setImageHeight(i);
        }

        public int getBitDepth() {
            return this.si.getBitDepth();
        }

        public void setBitDepth(int i) {
            this.si.setBitDepth(i);
        }

        public int getRefreshRate() {
            return this.si.getRefreshRate();
        }

        public void setRefreshRate(int i) {
            this.si.setRefreshRate(i);
        }

        public boolean isUseOpenGL() {
            return this.si.isUseOpenGL();
        }

        public void setUseOpenGL(boolean z) {
            this.si.setUseOpenGL(z);
        }

        public double getBrightnessBackground() {
            return this.brightnessBackground;
        }

        public void setBrightnessBackground(double d) {
            Double valueOf = Double.valueOf(this.brightnessBackground);
            this.brightnessBackground = d;
            firePropertyChange("brightnessBackground", valueOf, Double.valueOf(d));
        }

        public double getBrightnessForeground() {
            return this.brightnessForeground;
        }

        public void setBrightnessForeground(double d) {
            Double valueOf = Double.valueOf(this.brightnessForeground);
            this.brightnessForeground = d;
            firePropertyChange("brightnessForeground", valueOf, Double.valueOf(d));
        }
    }

    public static class CalibratedSettings extends ProjectiveDevice.CalibratedSettings implements Settings {
        SettingsImplementation si = new SettingsImplementation() {
            public void firePropertyChange(String str, Object obj, Object obj2) {
                CalibratedSettings.this.firePropertyChange(str, obj, obj2);
            }
        };

        public CalibratedSettings() {
        }

        public CalibratedSettings(ProjectiveDevice.CalibratedSettings calibratedSettings) {
            super(calibratedSettings);
            if (calibratedSettings instanceof CalibratedSettings) {
                this.si = new SettingsImplementation(((CalibratedSettings) calibratedSettings).si);
            }
        }

        public String getName() {
            return this.si.getName();
        }

        public void setName(String str) {
            this.si.setName(str);
        }

        public double getResponseGamma() {
            return this.si.getResponseGamma();
        }

        public void setResponseGamma(double d) {
            this.si.setResponseGamma(d);
        }

        public int getScreenNumber() {
            return this.si.getScreenNumber();
        }

        public void setScreenNumber(int i) {
            this.si.setScreenNumber(i);
        }

        public long getLatency() {
            return this.si.getLatency();
        }

        public void setLatency(long j) {
            this.si.setLatency(j);
        }

        public String getDescription() {
            return this.si.getDescription();
        }

        public int getImageWidth() {
            return this.si.getImageWidth();
        }

        public void setImageWidth(int i) {
            this.si.setImageWidth(i);
        }

        public int getImageHeight() {
            return this.si.getImageHeight();
        }

        public void setImageHeight(int i) {
            this.si.setImageHeight(i);
        }

        public int getBitDepth() {
            return this.si.getBitDepth();
        }

        public void setBitDepth(int i) {
            this.si.setBitDepth(i);
        }

        public int getRefreshRate() {
            return this.si.getRefreshRate();
        }

        public void setRefreshRate(int i) {
            this.si.setRefreshRate(i);
        }

        public boolean isUseOpenGL() {
            return this.si.isUseOpenGL();
        }

        public void setUseOpenGL(boolean z) {
            this.si.setUseOpenGL(z);
        }
    }

    public ProjectiveDevice.Settings getSettings() {
        return (ProjectiveDevice.Settings) this.settings;
    }

    public void setSettings(Settings settings2) {
        setSettings((ProjectiveDevice.Settings) settings2);
    }

    public void setSettings(ProjectiveDevice.Settings settings2) {
        super.setSettings(settings2);
        if (settings2 instanceof ProjectiveDevice.CalibrationSettings) {
            this.settings = new CalibrationSettings((ProjectiveDevice.CalibrationSettings) settings2);
        } else if (settings2 instanceof ProjectiveDevice.CalibratedSettings) {
            this.settings = new CalibratedSettings((ProjectiveDevice.CalibratedSettings) settings2);
        } else {
            this.settings = new SettingsImplementation(settings2);
        }
        if (this.settings.getName() == null || this.settings.getName().length() == 0) {
            Settings settings3 = this.settings;
            settings3.setName("Projector " + String.format("%2d", new Object[]{Integer.valueOf(this.settings.getScreenNumber())}));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacv.CanvasFrame createCanvasFrame() throws org.bytedeco.javacv.CanvasFrame.Exception {
        /*
            r11 = this;
            org.bytedeco.javacv.ProjectorDevice$Settings r0 = r11.settings
            int r0 = r0.getScreenNumber()
            r1 = 0
            if (r0 >= 0) goto L_0x000a
            return r1
        L_0x000a:
            java.awt.DisplayMode r0 = new java.awt.DisplayMode
            org.bytedeco.javacv.ProjectorDevice$Settings r2 = r11.settings
            int r2 = r2.getImageWidth()
            org.bytedeco.javacv.ProjectorDevice$Settings r3 = r11.settings
            int r3 = r3.getImageHeight()
            org.bytedeco.javacv.ProjectorDevice$Settings r4 = r11.settings
            int r4 = r4.getBitDepth()
            org.bytedeco.javacv.ProjectorDevice$Settings r5 = r11.settings
            int r5 = r5.getRefreshRate()
            r0.<init>(r2, r3, r4, r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r2.<init>()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class<org.bytedeco.javacv.CanvasFrame> r3 = org.bytedeco.javacv.CanvasFrame.class
            java.lang.Package r3 = r3.getPackage()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.String r3 = r3.getName()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r2.append(r3)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            org.bytedeco.javacv.ProjectorDevice$Settings r3 = r11.settings     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            boolean r3 = r3.isUseOpenGL()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            if (r3 == 0) goto L_0x0044
            java.lang.String r3 = ".GLCanvasFrame"
            goto L_0x0046
        L_0x0044:
            java.lang.String r3 = ".CanvasFrame"
        L_0x0046:
            r2.append(r3)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.String r2 = r2.toString()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class<org.bytedeco.javacv.CanvasFrame> r3 = org.bytedeco.javacv.CanvasFrame.class
            java.lang.Class r2 = r2.asSubclass(r3)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r3 = 4
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r6 = 0
            r4[r6] = r5     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r7 = 1
            r4[r7] = r5     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class<java.awt.DisplayMode> r5 = java.awt.DisplayMode.class
            r8 = 2
            r4[r8] = r5     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Class r5 = java.lang.Double.TYPE     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r9 = 3
            r4[r9] = r5     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.reflect.Constructor r2 = r2.getConstructor(r4)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            org.bytedeco.javacv.ProjectorDevice$Settings r4 = r11.settings     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.String r4 = r4.getName()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r3[r6] = r4     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            org.bytedeco.javacv.ProjectorDevice$Settings r4 = r11.settings     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            int r4 = r4.getScreenNumber()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r3[r7] = r4     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r3[r8] = r0     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            org.bytedeco.javacv.ProjectorDevice$Settings r0 = r11.settings     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            double r4 = r0.getResponseGamma()     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Double r0 = java.lang.Double.valueOf(r4)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            r3[r9] = r0     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            java.lang.Object r0 = r2.newInstance(r3)     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            org.bytedeco.javacv.CanvasFrame r0 = (org.bytedeco.javacv.CanvasFrame) r0     // Catch:{ ClassNotFoundException -> 0x00ab, InstantiationException -> 0x00a9, IllegalAccessException -> 0x00a7, IllegalArgumentException -> 0x00a5, NoSuchMethodException -> 0x00a3, InvocationTargetException -> 0x009d }
            goto L_0x00af
        L_0x009d:
            r0 = move-exception
            java.lang.Throwable r0 = r0.getCause()
            goto L_0x00ac
        L_0x00a3:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a5:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a7:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a9:
            r0 = move-exception
            goto L_0x00ac
        L_0x00ab:
            r0 = move-exception
        L_0x00ac:
            r10 = r1
            r1 = r0
            r0 = r10
        L_0x00af:
            if (r1 == 0) goto L_0x00c0
            boolean r0 = r1 instanceof org.bytedeco.javacv.CanvasFrame.Exception
            if (r0 == 0) goto L_0x00b8
            org.bytedeco.javacv.CanvasFrame$Exception r1 = (org.bytedeco.javacv.CanvasFrame.Exception) r1
            throw r1
        L_0x00b8:
            org.bytedeco.javacv.CanvasFrame$Exception r0 = new org.bytedeco.javacv.CanvasFrame$Exception
            java.lang.String r2 = "Failed to create CanvasFrame"
            r0.<init>(r2, r1)
            throw r0
        L_0x00c0:
            org.bytedeco.javacv.ProjectorDevice$Settings r1 = r11.settings
            long r1 = r1.getLatency()
            r0.setLatency(r1)
            java.awt.Dimension r1 = r0.getCanvasSize()
            int r2 = r1.width
            int r3 = r11.imageWidth
            if (r2 != r3) goto L_0x00d9
            int r2 = r1.height
            int r3 = r11.imageHeight
            if (r2 == r3) goto L_0x00e0
        L_0x00d9:
            int r2 = r1.width
            int r1 = r1.height
            r11.rescale(r2, r1)
        L_0x00e0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.ProjectorDevice.createCanvasFrame():org.bytedeco.javacv.CanvasFrame");
    }

    public double getAttenuation(double d, double d2, CvMat cvMat, double d3) {
        CvMat cvMat2 = cvMat;
        CvMat cvMat3 = B4x3.get();
        CvMat cvMat4 = x23x1.get();
        CvMat cvMat5 = x34x1.get();
        getBackProjectionMatrix(cvMat2, d3, cvMat3);
        cvMat4.put(d, d2, 1.0d);
        opencv_core.cvMatMul(cvMat3, cvMat4, cvMat5);
        opencv_core.cvGEMM(this.R, this.T, -1.0d, (CvArr) null, 0.0d, cvMat4, 1);
        cvMat5.rows(3);
        opencv_core.cvAddWeighted(cvMat5, 1.0d / cvMat5.get(3), cvMat4, -1.0d, 0.0d, cvMat4);
        double cvDotProduct = opencv_core.cvDotProduct(cvMat4, cvMat4);
        double cvDotProduct2 = (((-Math.signum(d3)) * opencv_core.cvDotProduct(cvMat4, cvMat2)) / (Math.sqrt(cvDotProduct) * Math.sqrt(opencv_core.cvDotProduct(cvMat2, cvMat2)))) / cvDotProduct;
        cvMat5.rows(4);
        return cvDotProduct2;
    }

    public static ProjectorDevice[] read(String str) throws ProjectiveDevice.Exception {
        FileStorage fileStorage = new FileStorage(str, 0);
        ProjectorDevice[] read = read(fileStorage);
        fileStorage.release();
        return read;
    }

    public static ProjectorDevice[] read(FileStorage fileStorage) throws ProjectiveDevice.Exception {
        FileNodeIterator begin = fileStorage.get("Projectors").begin();
        int remaining = (int) begin.remaining();
        ProjectorDevice[] projectorDeviceArr = new ProjectorDevice[remaining];
        int i = 0;
        while (i < remaining) {
            FileNode multiply = begin.multiply();
            if (!multiply.empty()) {
                projectorDeviceArr[i] = new ProjectorDevice(multiply.asBytePointer().getString(), fileStorage);
            }
            i++;
            begin.increment();
        }
        return projectorDeviceArr;
    }
}
