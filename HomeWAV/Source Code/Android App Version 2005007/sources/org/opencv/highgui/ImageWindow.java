package org.opencv.highgui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public final class ImageWindow {
    public static final int WINDOW_AUTOSIZE = 1;
    public static final int WINDOW_NORMAL = 0;
    public Boolean alreadyUsed = false;
    public int flag;
    public JFrame frame = null;
    public int height = -1;
    public Mat img = null;
    public Boolean imgToBeResized = false;
    public JLabel lbl = null;
    public String name;
    public Boolean positionToBeChanged = false;
    public int width = -1;
    public Boolean windowToBeResized = false;
    public int x = -1;
    public int y = -1;

    public ImageWindow(String str, Mat mat) {
        this.name = str;
        this.img = mat;
        this.flag = 0;
    }

    public ImageWindow(String str, int i) {
        this.name = str;
        this.flag = i;
    }

    public static Size keepAspectRatioSize(int i, int i2, int i3, int i4) {
        int i5;
        if (i > i3) {
            i5 = (i3 * i2) / i;
        } else {
            i3 = i;
            i5 = i2;
        }
        if (i5 > i4) {
            i3 = (i * i4) / i2;
        } else {
            i4 = i5;
        }
        return new Size((double) i3, (double) i4);
    }

    public void setMat(Mat mat) {
        this.img = mat;
        this.alreadyUsed = false;
        if (this.imgToBeResized.booleanValue()) {
            resizeImage();
            this.imgToBeResized = false;
        }
    }

    public void setFrameLabelVisible(JFrame jFrame, JLabel jLabel) {
        this.frame = jFrame;
        this.lbl = jLabel;
        if (this.windowToBeResized.booleanValue()) {
            jLabel.setPreferredSize(new Dimension(this.width, this.height));
            this.windowToBeResized = false;
        }
        if (this.positionToBeChanged.booleanValue()) {
            jFrame.setLocation(this.x, this.y);
            this.positionToBeChanged = false;
        }
        jFrame.add(jLabel);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void setNewDimension(int i, int i2) {
        if (this.width != i || this.height != i2) {
            this.width = i;
            this.height = i2;
            if (this.img != null) {
                resizeImage();
            } else {
                this.imgToBeResized = true;
            }
            JLabel jLabel = this.lbl;
            if (jLabel != null) {
                jLabel.setPreferredSize(new Dimension(i, i2));
            } else {
                this.windowToBeResized = true;
            }
        }
    }

    public void setNewPosition(int i, int i2) {
        if (this.x != i || this.y != i2) {
            this.x = i;
            this.y = i2;
            JFrame jFrame = this.frame;
            if (jFrame != null) {
                jFrame.setLocation(i, i2);
            } else {
                this.positionToBeChanged = true;
            }
        }
    }

    private void resizeImage() {
        if (this.flag == 0) {
            Size keepAspectRatioSize = keepAspectRatioSize(this.img.width(), this.img.height(), this.width, this.height);
            Mat mat = this.img;
            Imgproc.resize(mat, mat, keepAspectRatioSize, 0.0d, 0.0d, 5);
        }
    }
}
