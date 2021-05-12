package org.bytedeco.javacv;

import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.IplImage;

public interface ImageAligner {
    IplImage[] getImages();

    IplImage getMaskImage();

    ImageTransformer.Parameters getParameters();

    int getPyramidLevel();

    double getRMSE();

    IplImage getResidualImage();

    CvRect getRoi();

    Settings getSettings();

    IplImage getTargetImage();

    IplImage getTemplateImage();

    IplImage getTransformedImage();

    double[] getTransformedRoiPts();

    boolean iterate(double[] dArr);

    void setParameters(ImageTransformer.Parameters parameters);

    void setPyramidLevel(int i);

    void setSettings(Settings settings);

    void setTargetImage(IplImage iplImage);

    void setTemplateImage(IplImage iplImage, double[] dArr);

    public static class Settings extends BaseChildSettings implements Cloneable {
        int pyramidLevelMax = 4;
        int pyramidLevelMin = 0;
        boolean thresholdsMulRMSE = false;
        double[] thresholdsOutlier = {0.2d};
        double[] thresholdsZero = {0.04d, 0.03d, 0.02d, 0.01d, 0.0d};

        public Settings() {
        }

        public Settings(Settings settings) {
            this.pyramidLevelMin = settings.pyramidLevelMin;
            this.pyramidLevelMax = settings.pyramidLevelMax;
            this.thresholdsZero = settings.thresholdsZero;
            this.thresholdsOutlier = settings.thresholdsOutlier;
            this.thresholdsMulRMSE = settings.thresholdsMulRMSE;
        }

        public int getPyramidLevelMin() {
            return this.pyramidLevelMin;
        }

        public void setPyramidLevelMin(int i) {
            this.pyramidLevelMin = i;
        }

        public int getPyramidLevelMax() {
            return this.pyramidLevelMax;
        }

        public void setPyramidLevelMax(int i) {
            this.pyramidLevelMax = i;
        }

        public double[] getThresholdsZero() {
            return this.thresholdsZero;
        }

        public void setThresholdsZero(double[] dArr) {
            this.thresholdsZero = dArr;
        }

        public double[] getThresholdsOutlier() {
            return this.thresholdsOutlier;
        }

        public void setThresholdsOutlier(double[] dArr) {
            this.thresholdsOutlier = dArr;
        }

        public boolean isThresholdsMulRMSE() {
            return this.thresholdsMulRMSE;
        }

        public void setThresholdsMulRMSE(boolean z) {
            this.thresholdsMulRMSE = z;
        }

        public Settings clone() {
            return new Settings(this);
        }
    }
}
