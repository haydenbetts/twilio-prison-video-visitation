package org.bytedeco.javacv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.File;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileNodeIterator;
import org.bytedeco.opencv.opencv_core.FileStorage;

public class CameraDevice extends ProjectiveDevice {
    private Settings settings;

    public interface Settings {
        void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

        int getBitsPerPixel();

        String getDescription();

        File getDeviceFile();

        String getDeviceFilename();

        Integer getDeviceNumber();

        String getDevicePath();

        String getFormat();

        Class<? extends FrameGrabber> getFrameGrabber();

        double getFrameRate();

        int getImageHeight();

        FrameGrabber.ImageMode getImageMode();

        int getImageWidth();

        String getName();

        int getNumBuffers();

        double getResponseGamma();

        int getTimeout();

        boolean isDeinterlace();

        boolean isTriggerMode();

        void removePropertyChangeListener(PropertyChangeListener propertyChangeListener);

        void setBitsPerPixel(int i);

        void setDeinterlace(boolean z);

        void setDeviceFile(File file) throws PropertyVetoException;

        void setDeviceFilename(String str) throws PropertyVetoException;

        void setDeviceNumber(Integer num) throws PropertyVetoException;

        void setDevicePath(String str) throws PropertyVetoException;

        void setFormat(String str);

        void setFrameGrabber(Class<? extends FrameGrabber> cls);

        void setFrameRate(double d);

        void setImageHeight(int i);

        void setImageMode(FrameGrabber.ImageMode imageMode);

        void setImageWidth(int i);

        void setName(String str);

        void setNumBuffers(int i);

        void setResponseGamma(double d);

        void setTimeout(int i);

        void setTriggerMode(boolean z);
    }

    public CameraDevice(String str) {
        super(str);
    }

    public CameraDevice(String str, String str2) throws ProjectiveDevice.Exception {
        super(str, str2);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public CameraDevice(String str, FileStorage fileStorage) throws ProjectiveDevice.Exception {
        super(str, fileStorage);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public CameraDevice(Settings settings2) throws ProjectiveDevice.Exception {
        super((ProjectiveDevice.Settings) settings2);
    }

    public static class SettingsImplementation extends ProjectiveDevice.Settings implements Settings {
        int bpp = 0;
        boolean deinterlace = false;
        File deviceFile = null;
        Integer deviceNumber = null;
        String devicePath = null;
        String format = "";
        Class<? extends FrameGrabber> frameGrabber = null;
        double frameRate = 0.0d;
        int imageHeight = 0;
        FrameGrabber.ImageMode imageMode = FrameGrabber.ImageMode.COLOR;
        int imageWidth = 0;
        int numBuffers = 4;
        int timeout = 10000;
        boolean triggerMode = false;

        public SettingsImplementation() {
            this.name = "Camera  0";
        }

        public SettingsImplementation(ProjectiveDevice.Settings settings) {
            super(settings);
            if (settings instanceof SettingsImplementation) {
                SettingsImplementation settingsImplementation = (SettingsImplementation) settings;
                this.deviceNumber = settingsImplementation.deviceNumber;
                this.deviceFile = settingsImplementation.deviceFile;
                this.devicePath = settingsImplementation.devicePath;
                this.frameGrabber = settingsImplementation.frameGrabber;
                this.format = settingsImplementation.format;
                this.imageWidth = settingsImplementation.imageWidth;
                this.imageHeight = settingsImplementation.imageHeight;
                this.frameRate = settingsImplementation.frameRate;
                this.triggerMode = settingsImplementation.triggerMode;
                this.bpp = settingsImplementation.bpp;
                this.imageMode = settingsImplementation.imageMode;
                this.timeout = settingsImplementation.timeout;
                this.numBuffers = settingsImplementation.numBuffers;
                this.deinterlace = settingsImplementation.deinterlace;
            }
        }

        public Integer getDeviceNumber() {
            return this.deviceNumber;
        }

        /* JADX WARNING: type inference failed for: r8v2, types: [java.lang.Throwable, org.bytedeco.javacv.BaseChildSettings$PropertyVetoExceptionThatNetBeansLikes] */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:6|7|8|9|10) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0015 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setDeviceNumber(java.lang.Integer r8) throws java.beans.PropertyVetoException {
            /*
                r7 = this;
                java.lang.String r0 = "deviceNumber"
                if (r8 == 0) goto L_0x004d
                r1 = 0
                java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r2 = r7.frameGrabber     // Catch:{ NoSuchMethodException -> 0x0027 }
                if (r2 == 0) goto L_0x0020
                r3 = 0
                r4 = 1
                java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x0015 }
                java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x0015 }
                r5[r3] = r6     // Catch:{ NoSuchMethodException -> 0x0015 }
                r2.getConstructor(r5)     // Catch:{ NoSuchMethodException -> 0x0015 }
                goto L_0x0020
            L_0x0015:
                java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r2 = r7.frameGrabber     // Catch:{ NoSuchMethodException -> 0x0027 }
                java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x0027 }
                java.lang.Class<java.lang.Integer> r5 = java.lang.Integer.class
                r4[r3] = r5     // Catch:{ NoSuchMethodException -> 0x0027 }
                r2.getConstructor(r4)     // Catch:{ NoSuchMethodException -> 0x0027 }
            L_0x0020:
                r7.setDevicePath(r1)     // Catch:{ NoSuchMethodException -> 0x0027 }
                r7.setDeviceFile(r1)     // Catch:{ NoSuchMethodException -> 0x0027 }
                goto L_0x004d
            L_0x0027:
                org.bytedeco.javacv.BaseChildSettings$PropertyVetoExceptionThatNetBeansLikes r8 = new org.bytedeco.javacv.BaseChildSettings$PropertyVetoExceptionThatNetBeansLikes
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r3 = r7.frameGrabber
                java.lang.String r3 = r3.getSimpleName()
                r2.append(r3)
                java.lang.String r3 = " does not accept a deviceNumber."
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.beans.PropertyChangeEvent r3 = new java.beans.PropertyChangeEvent
                java.lang.Integer r4 = r7.deviceNumber
                r7.deviceNumber = r1
                r3.<init>(r7, r0, r4, r1)
                r8.<init>(r2, r3)
                throw r8
            L_0x004d:
                java.lang.String r1 = r7.getDescription()
                java.lang.Integer r2 = r7.deviceNumber
                r7.deviceNumber = r8
                r7.firePropertyChange(r0, r2, r8)
                java.lang.String r8 = r7.getDescription()
                java.lang.String r0 = "description"
                r7.firePropertyChange(r0, r1, r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.CameraDevice.SettingsImplementation.setDeviceNumber(java.lang.Integer):void");
        }

        public File getDeviceFile() {
            return this.deviceFile;
        }

        /* JADX WARNING: type inference failed for: r7v2, types: [java.lang.Throwable, org.bytedeco.javacv.BaseChildSettings$PropertyVetoExceptionThatNetBeansLikes] */
        public void setDeviceFile(File file) throws PropertyVetoException {
            if (file != null) {
                try {
                    Class<? extends FrameGrabber> cls = this.frameGrabber;
                    if (cls != null) {
                        cls.getConstructor(new Class[]{File.class});
                    }
                    setDeviceNumber((Integer) null);
                    setDevicePath((String) null);
                } catch (NoSuchMethodException unused) {
                    String str = this.frameGrabber.getSimpleName() + " does not accept a deviceFile.";
                    File file2 = this.deviceFile;
                    this.deviceFile = null;
                    throw new BaseChildSettings.PropertyVetoExceptionThatNetBeansLikes(str, new PropertyChangeEvent(this, "deviceFile", file2, (Object) null));
                }
            }
            String description = getDescription();
            File file3 = this.deviceFile;
            this.deviceFile = file;
            firePropertyChange("deviceFile", file3, file);
            firePropertyChange("description", description, getDescription());
        }

        public String getDeviceFilename() {
            return getDeviceFile() == null ? "" : getDeviceFile().getPath();
        }

        public void setDeviceFilename(String str) throws PropertyVetoException {
            setDeviceFile((str == null || str.length() == 0) ? null : new File(str));
        }

        public String getDevicePath() {
            return this.devicePath;
        }

        /* JADX WARNING: type inference failed for: r7v2, types: [java.lang.Throwable, org.bytedeco.javacv.BaseChildSettings$PropertyVetoExceptionThatNetBeansLikes] */
        public void setDevicePath(String str) throws PropertyVetoException {
            if (str != null) {
                try {
                    Class<? extends FrameGrabber> cls = this.frameGrabber;
                    if (cls != null) {
                        cls.getConstructor(new Class[]{String.class});
                    }
                    setDeviceNumber((Integer) null);
                    setDeviceFile((File) null);
                } catch (NoSuchMethodException unused) {
                    String str2 = this.frameGrabber.getSimpleName() + " does not accept a devicePath.";
                    String str3 = this.devicePath;
                    this.devicePath = null;
                    throw new BaseChildSettings.PropertyVetoExceptionThatNetBeansLikes(str2, new PropertyChangeEvent(this, "devicePath", str3, (Object) null));
                }
            }
            String description = getDescription();
            String str4 = this.devicePath;
            this.devicePath = str;
            firePropertyChange("devicePath", str4, str);
            firePropertyChange("description", description, getDescription());
        }

        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.frameGrabber;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x004c, code lost:
            r6 = r8.deviceNumber;
            r8.deviceNumber = null;
            firePropertyChange("deviceNumber", r6, (java.lang.Object) null);
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            r9.getConstructor(new java.lang.Class[]{java.lang.Integer.class});
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0042 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setFrameGrabber(java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r9) {
            /*
                r8 = this;
                java.lang.String r0 = r8.getDescription()
                java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r1 = r8.frameGrabber
                r8.frameGrabber = r9
                java.lang.String r2 = "frameGrabber"
                r8.firePropertyChange(r2, r1, r9)
                java.lang.String r1 = r8.getDescription()
                java.lang.String r2 = "description"
                r8.firePropertyChange(r2, r0, r1)
                java.lang.String r0 = "devicePath"
                java.lang.String r1 = "deviceFile"
                java.lang.String r2 = "deviceNumber"
                r3 = 0
                if (r9 != 0) goto L_0x0035
                java.lang.Integer r9 = r8.deviceNumber
                r8.deviceNumber = r3
                r8.firePropertyChange(r2, r9, r3)
                java.io.File r9 = r8.deviceFile
                r8.deviceFile = r3
                r8.firePropertyChange(r1, r9, r3)
                java.lang.String r9 = r8.devicePath
                r8.devicePath = r3
                r8.firePropertyChange(r0, r9, r3)
                return
            L_0x0035:
                r4 = 1
                r5 = 0
                java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x0042 }
                java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x0042 }
                r6[r5] = r7     // Catch:{ NoSuchMethodException -> 0x0042 }
                r9.getConstructor(r6)     // Catch:{ NoSuchMethodException -> 0x0042 }
            L_0x0040:
                r2 = 1
                goto L_0x0054
            L_0x0042:
                java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x004c }
                java.lang.Class<java.lang.Integer> r7 = java.lang.Integer.class
                r6[r5] = r7     // Catch:{ NoSuchMethodException -> 0x004c }
                r9.getConstructor(r6)     // Catch:{ NoSuchMethodException -> 0x004c }
                goto L_0x0040
            L_0x004c:
                java.lang.Integer r6 = r8.deviceNumber
                r8.deviceNumber = r3
                r8.firePropertyChange(r2, r6, r3)
                r2 = 0
            L_0x0054:
                java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x005e }
                java.lang.Class<java.io.File> r7 = java.io.File.class
                r6[r5] = r7     // Catch:{ NoSuchMethodException -> 0x005e }
                r9.getConstructor(r6)     // Catch:{ NoSuchMethodException -> 0x005e }
                goto L_0x0065
            L_0x005e:
                java.io.File r6 = r8.deviceFile
                r8.deviceFile = r3
                r8.firePropertyChange(r1, r6, r3)
            L_0x0065:
                java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x006f }
                java.lang.Class<java.lang.String> r4 = java.lang.String.class
                r1[r5] = r4     // Catch:{ NoSuchMethodException -> 0x006f }
                r9.getConstructor(r1)     // Catch:{ NoSuchMethodException -> 0x006f }
                goto L_0x0076
            L_0x006f:
                java.lang.String r9 = r8.devicePath
                r8.devicePath = r3
                r8.firePropertyChange(r0, r9, r3)
            L_0x0076:
                if (r2 == 0) goto L_0x008b
                java.lang.Integer r9 = r8.deviceNumber
                if (r9 != 0) goto L_0x008b
                java.io.File r9 = r8.deviceFile
                if (r9 != 0) goto L_0x008b
                java.lang.String r9 = r8.devicePath
                if (r9 != 0) goto L_0x008b
                java.lang.Integer r9 = java.lang.Integer.valueOf(r5)     // Catch:{ PropertyVetoException -> 0x008b }
                r8.setDeviceNumber(r9)     // Catch:{ PropertyVetoException -> 0x008b }
            L_0x008b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.CameraDevice.SettingsImplementation.setFrameGrabber(java.lang.Class):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
            r1 = r5.deviceNumber;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getDescription() {
            /*
                r5 = this;
                r0 = 0
                java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> r1 = r5.frameGrabber     // Catch:{ Exception -> 0x0018 }
                java.lang.String r2 = "getDeviceDescriptions"
                r3 = 0
                java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0018 }
                java.lang.reflect.Method r1 = r1.getMethod(r2, r4)     // Catch:{ Exception -> 0x0018 }
                java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0018 }
                java.lang.Object r1 = r1.invoke(r0, r2)     // Catch:{ Exception -> 0x0018 }
                java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ Exception -> 0x0018 }
                java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ Exception -> 0x0018 }
                r0 = r1
                goto L_0x0019
            L_0x0018:
            L_0x0019:
                if (r0 == 0) goto L_0x002f
                java.lang.Integer r1 = r5.deviceNumber
                if (r1 == 0) goto L_0x002f
                int r1 = r1.intValue()
                int r2 = r0.length
                if (r1 >= r2) goto L_0x002f
                java.lang.Integer r1 = r5.deviceNumber
                int r1 = r1.intValue()
                r0 = r0[r1]
                return r0
            L_0x002f:
                java.lang.String r0 = ""
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.CameraDevice.SettingsImplementation.getDescription():java.lang.String");
        }

        public String getFormat() {
            return this.format;
        }

        public void setFormat(String str) {
            this.format = str;
        }

        public int getImageWidth() {
            return this.imageWidth;
        }

        public void setImageWidth(int i) {
            this.imageWidth = i;
        }

        public int getImageHeight() {
            return this.imageHeight;
        }

        public void setImageHeight(int i) {
            this.imageHeight = i;
        }

        public double getFrameRate() {
            return this.frameRate;
        }

        public void setFrameRate(double d) {
            this.frameRate = d;
        }

        public boolean isTriggerMode() {
            return this.triggerMode;
        }

        public void setTriggerMode(boolean z) {
            this.triggerMode = z;
        }

        public int getBitsPerPixel() {
            return this.bpp;
        }

        public void setBitsPerPixel(int i) {
            this.bpp = i;
        }

        public FrameGrabber.ImageMode getImageMode() {
            return this.imageMode;
        }

        public void setImageMode(FrameGrabber.ImageMode imageMode2) {
            this.imageMode = imageMode2;
        }

        public int getTimeout() {
            return this.timeout;
        }

        public void setTimeout(int i) {
            this.timeout = i;
        }

        public int getNumBuffers() {
            return this.numBuffers;
        }

        public void setNumBuffers(int i) {
            this.numBuffers = i;
        }

        public boolean isDeinterlace() {
            return this.deinterlace;
        }

        public void setDeinterlace(boolean z) {
            this.deinterlace = z;
        }
    }

    public static class CalibrationSettings extends ProjectiveDevice.CalibrationSettings implements Settings {
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
                this.si = new SettingsImplementation(((CalibrationSettings) calibrationSettings).si);
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

        public Integer getDeviceNumber() {
            return this.si.getDeviceNumber();
        }

        public void setDeviceNumber(Integer num) throws PropertyVetoException {
            this.si.setDeviceNumber(num);
        }

        public File getDeviceFile() {
            return this.si.getDeviceFile();
        }

        public void setDeviceFile(File file) throws PropertyVetoException {
            this.si.setDeviceFile(file);
        }

        public String getDeviceFilename() {
            return this.si.getDeviceFilename();
        }

        public void setDeviceFilename(String str) throws PropertyVetoException {
            this.si.setDeviceFilename(str);
        }

        public String getDevicePath() {
            return this.si.getDevicePath();
        }

        public void setDevicePath(String str) throws PropertyVetoException {
            this.si.setDevicePath(str);
        }

        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.si.getFrameGrabber();
        }

        public void setFrameGrabber(Class<? extends FrameGrabber> cls) {
            this.si.setFrameGrabber(cls);
        }

        public String getDescription() {
            return this.si.getDescription();
        }

        public String getFormat() {
            return this.si.getFormat();
        }

        public void setFormat(String str) {
            this.si.setFormat(str);
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

        public double getFrameRate() {
            return this.si.getFrameRate();
        }

        public void setFrameRate(double d) {
            this.si.setFrameRate(d);
        }

        public boolean isTriggerMode() {
            return this.si.isTriggerMode();
        }

        public void setTriggerMode(boolean z) {
            this.si.setTriggerMode(z);
        }

        public int getBitsPerPixel() {
            return this.si.getBitsPerPixel();
        }

        public void setBitsPerPixel(int i) {
            this.si.setBitsPerPixel(i);
        }

        public FrameGrabber.ImageMode getImageMode() {
            return this.si.getImageMode();
        }

        public void setImageMode(FrameGrabber.ImageMode imageMode) {
            this.si.setImageMode(imageMode);
        }

        public int getTimeout() {
            return this.si.getTimeout();
        }

        public void setTimeout(int i) {
            this.si.setTimeout(i);
        }

        public int getNumBuffers() {
            return this.si.getNumBuffers();
        }

        public void setNumBuffers(int i) {
            this.si.setNumBuffers(i);
        }

        public boolean isDeinterlace() {
            return this.si.isDeinterlace();
        }

        public void setDeinterlace(boolean z) {
            this.si.setDeinterlace(z);
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

        public Integer getDeviceNumber() {
            return this.si.getDeviceNumber();
        }

        public void setDeviceNumber(Integer num) throws PropertyVetoException {
            this.si.setDeviceNumber(num);
        }

        public File getDeviceFile() {
            return this.si.getDeviceFile();
        }

        public void setDeviceFile(File file) throws PropertyVetoException {
            this.si.setDeviceFile(file);
        }

        public String getDeviceFilename() {
            return this.si.getDeviceFilename();
        }

        public void setDeviceFilename(String str) throws PropertyVetoException {
            this.si.setDeviceFilename(str);
        }

        public String getDevicePath() {
            return this.si.getDevicePath();
        }

        public void setDevicePath(String str) throws PropertyVetoException {
            this.si.setDevicePath(str);
        }

        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.si.getFrameGrabber();
        }

        public void setFrameGrabber(Class<? extends FrameGrabber> cls) {
            this.si.setFrameGrabber(cls);
        }

        public String getDescription() {
            return this.si.getDescription();
        }

        public String getFormat() {
            return this.si.getFormat();
        }

        public void setFormat(String str) {
            this.si.setFormat(str);
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

        public double getFrameRate() {
            return this.si.getFrameRate();
        }

        public void setFrameRate(double d) {
            this.si.setFrameRate(d);
        }

        public boolean isTriggerMode() {
            return this.si.isTriggerMode();
        }

        public void setTriggerMode(boolean z) {
            this.si.setTriggerMode(z);
        }

        public int getBitsPerPixel() {
            return this.si.getBitsPerPixel();
        }

        public void setBitsPerPixel(int i) {
            this.si.setBitsPerPixel(i);
        }

        public FrameGrabber.ImageMode getImageMode() {
            return this.si.getImageMode();
        }

        public void setImageMode(FrameGrabber.ImageMode imageMode) {
            this.si.setImageMode(imageMode);
        }

        public int getTimeout() {
            return this.si.getTimeout();
        }

        public void setTimeout(int i) {
            this.si.setTimeout(i);
        }

        public int getNumBuffers() {
            return this.si.getNumBuffers();
        }

        public void setNumBuffers(int i) {
            this.si.setNumBuffers(i);
        }

        public boolean isDeinterlace() {
            return this.si.isDeinterlace();
        }

        public void setDeinterlace(boolean z) {
            this.si.setDeinterlace(z);
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
            settings3.setName("Camera " + String.format("%2d", new Object[]{this.settings.getDeviceNumber()}));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = (org.bytedeco.javacv.FrameGrabber) r6.settings.getFrameGrabber().getConstructor(new java.lang.Class[]{java.lang.Integer.class}).newInstance(new java.lang.Object[]{java.lang.Integer.valueOf(r0)});
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x00a9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacv.FrameGrabber createFrameGrabber() throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r6 = this;
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Class r0 = r0.getFrameGrabber()     // Catch:{ all -> 0x012b }
            java.lang.String r1 = "tryLoad"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x012b }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch:{ all -> 0x012b }
            r1 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x012b }
            r0.invoke(r1, r3)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.io.File r0 = r0.getDeviceFile()     // Catch:{ all -> 0x012b }
            r1 = 1
            if (r0 == 0) goto L_0x0040
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Class r0 = r0.getFrameGrabber()     // Catch:{ all -> 0x012b }
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class<java.io.File> r4 = java.io.File.class
            r3[r2] = r4     // Catch:{ all -> 0x012b }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r3)     // Catch:{ all -> 0x012b }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r3 = r6.settings     // Catch:{ all -> 0x012b }
            java.io.File r3 = r3.getDeviceFile()     // Catch:{ all -> 0x012b }
            r1[r2] = r3     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.FrameGrabber r0 = (org.bytedeco.javacv.FrameGrabber) r0     // Catch:{ all -> 0x012b }
            goto L_0x00c7
        L_0x0040:
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r0.getDevicePath()     // Catch:{ all -> 0x012b }
            if (r0 == 0) goto L_0x0075
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r0.getDevicePath()     // Catch:{ all -> 0x012b }
            int r0 = r0.length()     // Catch:{ all -> 0x012b }
            if (r0 <= 0) goto L_0x0075
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Class r0 = r0.getFrameGrabber()     // Catch:{ all -> 0x012b }
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r3[r2] = r4     // Catch:{ all -> 0x012b }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r3)     // Catch:{ all -> 0x012b }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r3 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.String r3 = r3.getDevicePath()     // Catch:{ all -> 0x012b }
            r1[r2] = r3     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.FrameGrabber r0 = (org.bytedeco.javacv.FrameGrabber) r0     // Catch:{ all -> 0x012b }
            goto L_0x00c7
        L_0x0075:
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Integer r0 = r0.getDeviceNumber()     // Catch:{ all -> 0x012b }
            if (r0 != 0) goto L_0x007f
            r0 = 0
            goto L_0x0089
        L_0x007f:
            org.bytedeco.javacv.CameraDevice$Settings r0 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Integer r0 = r0.getDeviceNumber()     // Catch:{ all -> 0x012b }
            int r0 = r0.intValue()     // Catch:{ all -> 0x012b }
        L_0x0089:
            org.bytedeco.javacv.CameraDevice$Settings r3 = r6.settings     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Class r3 = r3.getFrameGrabber()     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00a9 }
            r4[r2] = r5     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.reflect.Constructor r3 = r3.getConstructor(r4)     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ NoSuchMethodException -> 0x00a9 }
            r4[r2] = r5     // Catch:{ NoSuchMethodException -> 0x00a9 }
            java.lang.Object r3 = r3.newInstance(r4)     // Catch:{ NoSuchMethodException -> 0x00a9 }
            org.bytedeco.javacv.FrameGrabber r3 = (org.bytedeco.javacv.FrameGrabber) r3     // Catch:{ NoSuchMethodException -> 0x00a9 }
            r0 = r3
            goto L_0x00c7
        L_0x00a9:
            org.bytedeco.javacv.CameraDevice$Settings r3 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.Class r3 = r3.getFrameGrabber()     // Catch:{ all -> 0x012b }
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class<java.lang.Integer> r5 = java.lang.Integer.class
            r4[r2] = r5     // Catch:{ all -> 0x012b }
            java.lang.reflect.Constructor r3 = r3.getConstructor(r4)     // Catch:{ all -> 0x012b }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x012b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x012b }
            r1[r2] = r0     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r3.newInstance(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.FrameGrabber r0 = (org.bytedeco.javacv.FrameGrabber) r0     // Catch:{ all -> 0x012b }
        L_0x00c7:
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            java.lang.String r1 = r1.getFormat()     // Catch:{ all -> 0x012b }
            r0.setFormat(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            int r1 = r1.getImageWidth()     // Catch:{ all -> 0x012b }
            r0.setImageWidth(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            int r1 = r1.getImageHeight()     // Catch:{ all -> 0x012b }
            r0.setImageHeight(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            double r1 = r1.getFrameRate()     // Catch:{ all -> 0x012b }
            r0.setFrameRate(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            boolean r1 = r1.isTriggerMode()     // Catch:{ all -> 0x012b }
            r0.setTriggerMode(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            int r1 = r1.getBitsPerPixel()     // Catch:{ all -> 0x012b }
            r0.setBitsPerPixel(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.FrameGrabber$ImageMode r1 = r1.getImageMode()     // Catch:{ all -> 0x012b }
            r0.setImageMode(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            int r1 = r1.getTimeout()     // Catch:{ all -> 0x012b }
            r0.setTimeout(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            int r1 = r1.getNumBuffers()     // Catch:{ all -> 0x012b }
            r0.setNumBuffers(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            double r1 = r1.getResponseGamma()     // Catch:{ all -> 0x012b }
            r0.setGamma(r1)     // Catch:{ all -> 0x012b }
            org.bytedeco.javacv.CameraDevice$Settings r1 = r6.settings     // Catch:{ all -> 0x012b }
            boolean r1 = r1.isDeinterlace()     // Catch:{ all -> 0x012b }
            r0.setDeinterlace(r1)     // Catch:{ all -> 0x012b }
            return r0
        L_0x012b:
            r0 = move-exception
            boolean r1 = r0 instanceof java.lang.reflect.InvocationTargetException
            if (r1 == 0) goto L_0x0136
            java.lang.reflect.InvocationTargetException r0 = (java.lang.reflect.InvocationTargetException) r0
            java.lang.Throwable r0 = r0.getCause()
        L_0x0136:
            boolean r1 = r0 instanceof org.bytedeco.javacv.FrameGrabber.Exception
            if (r1 == 0) goto L_0x013d
            org.bytedeco.javacv.FrameGrabber$Exception r0 = (org.bytedeco.javacv.FrameGrabber.Exception) r0
            throw r0
        L_0x013d:
            org.bytedeco.javacv.FrameGrabber$Exception r1 = new org.bytedeco.javacv.FrameGrabber$Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to create "
            r2.append(r3)
            org.bytedeco.javacv.CameraDevice$Settings r3 = r6.settings
            java.lang.Class r3 = r3.getFrameGrabber()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.CameraDevice.createFrameGrabber():org.bytedeco.javacv.FrameGrabber");
    }

    public static CameraDevice[] read(String str) throws ProjectiveDevice.Exception {
        FileStorage fileStorage = new FileStorage(str, 0);
        CameraDevice[] read = read(fileStorage);
        fileStorage.release();
        return read;
    }

    public static CameraDevice[] read(FileStorage fileStorage) throws ProjectiveDevice.Exception {
        FileNodeIterator begin = fileStorage.get("Cameras").begin();
        int remaining = (int) begin.remaining();
        CameraDevice[] cameraDeviceArr = new CameraDevice[remaining];
        int i = 0;
        while (i < remaining) {
            FileNode multiply = begin.multiply();
            if (!multiply.empty()) {
                cameraDeviceArr[i] = new CameraDevice(multiply.asBytePointer().getString(), fileStorage);
            }
            i++;
            begin.increment();
        }
        return cameraDeviceArr;
    }
}
