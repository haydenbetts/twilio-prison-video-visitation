package org.bytedeco.javacv;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvErrorCallback;

public class JavaCvErrorCallback extends CvErrorCallback {
    static JavaCvErrorCallback instance;
    private long lastErrorTime;
    /* access modifiers changed from: private */
    public Component parent;
    private int rc;
    private boolean showDialog;

    public JavaCvErrorCallback() {
        this(false);
    }

    public JavaCvErrorCallback(boolean z) {
        this(z, (Component) null);
    }

    public JavaCvErrorCallback(boolean z, Component component) {
        this(z, component, 0);
    }

    public JavaCvErrorCallback(boolean z, Component component, int i) {
        this.lastErrorTime = 0;
        instance = this;
        this.parent = component;
        this.showDialog = z;
        this.rc = i;
    }

    public int call(int i, BytePointer bytePointer, BytePointer bytePointer2, BytePointer bytePointer3, int i2, Pointer pointer) {
        final String str = opencv_core.cvErrorStr(i) + " (" + bytePointer2.getString() + ")\nin function " + bytePointer.getString() + ", " + bytePointer3.getString() + "(" + i2 + ")";
        Logger.getLogger(JavaCvErrorCallback.class.getName()).log(Level.SEVERE, "OpenCV Error: " + str, new Exception("Strack trace"));
        if (this.showDialog) {
            if (System.currentTimeMillis() - this.lastErrorTime > 1000) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        JOptionPane.showMessageDialog(JavaCvErrorCallback.this.parent, str, "OpenCV Error", 0);
                    }
                });
            }
            this.lastErrorTime = System.currentTimeMillis();
        }
        return this.rc;
    }
}
