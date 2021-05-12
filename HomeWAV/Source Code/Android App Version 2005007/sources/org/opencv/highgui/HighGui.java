package org.opencv.highgui;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;

public final class HighGui {
    public static final int WINDOW_AUTOSIZE = 1;
    public static final int WINDOW_NORMAL = 0;
    public static CountDownLatch latch = new CountDownLatch(1);
    public static int n_closed_windows = 0;
    public static int pressedKey = -1;
    public static Map<String, ImageWindow> windows = new HashMap();

    public static void namedWindow(String str) {
        namedWindow(str, 1);
    }

    public static void namedWindow(String str, int i) {
        ImageWindow imageWindow = new ImageWindow(str, i);
        if (windows.get(str) == null) {
            windows.put(str, imageWindow);
        }
    }

    public static void imshow(String str, Mat mat) {
        if (mat.empty()) {
            System.err.println("Error: Empty image in imshow");
            System.exit(-1);
            return;
        }
        ImageWindow imageWindow = windows.get(str);
        if (imageWindow == null) {
            windows.put(str, new ImageWindow(str, mat));
            return;
        }
        imageWindow.setMat(mat);
    }

    public static Image toBufferedImage(Mat mat) {
        int i = mat.channels() > 1 ? 5 : 10;
        int channels = mat.channels() * mat.cols() * mat.rows();
        byte[] bArr = new byte[channels];
        mat.get(0, 0, bArr);
        BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(), i);
        System.arraycopy(bArr, 0, bufferedImage.getRaster().getDataBuffer().getData(), 0, channels);
        return bufferedImage;
    }

    public static JFrame createJFrame(String str, int i) {
        JFrame jFrame = new JFrame(str);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                HighGui.n_closed_windows++;
                if (HighGui.n_closed_windows == HighGui.windows.size()) {
                    HighGui.latch.countDown();
                }
            }
        });
        jFrame.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent keyEvent) {
            }

            public void keyTyped(KeyEvent keyEvent) {
            }

            public void keyPressed(KeyEvent keyEvent) {
                HighGui.pressedKey = keyEvent.getKeyCode();
                HighGui.latch.countDown();
            }
        });
        if (i == 1) {
            jFrame.setResizable(false);
        }
        return jFrame;
    }

    public static void waitKey() {
        waitKey(0);
    }

    public static int waitKey(int i) {
        latch = new CountDownLatch(1);
        n_closed_windows = 0;
        pressedKey = -1;
        if (windows.isEmpty()) {
            System.err.println("Error: waitKey must be used after an imshow");
            System.exit(-1);
        }
        Iterator<Map.Entry<String, ImageWindow>> it = windows.entrySet().iterator();
        while (it.hasNext()) {
            ImageWindow imageWindow = (ImageWindow) it.next().getValue();
            if (imageWindow.alreadyUsed.booleanValue()) {
                it.remove();
                imageWindow.frame.dispose();
            }
        }
        for (ImageWindow next : windows.values()) {
            if (next.img != null) {
                ImageIcon imageIcon = new ImageIcon(toBufferedImage(next.img));
                if (next.lbl == null) {
                    next.setFrameLabelVisible(createJFrame(next.name, next.flag), new JLabel(imageIcon));
                } else {
                    next.lbl.setIcon(imageIcon);
                }
            } else {
                PrintStream printStream = System.err;
                printStream.println("Error: no imshow associated with namedWindow: \"" + next.name + "\"");
                System.exit(-1);
            }
        }
        if (i == 0) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            latch.await((long) i, TimeUnit.MILLISECONDS);
        }
        for (ImageWindow imageWindow2 : windows.values()) {
            imageWindow2.alreadyUsed = true;
        }
        return pressedKey;
    }

    public static void destroyWindow(String str) {
        if (windows.get(str) != null) {
            windows.remove(str);
        }
    }

    public static void destroyAllWindows() {
        windows.clear();
    }

    public static void resizeWindow(String str, int i, int i2) {
        ImageWindow imageWindow = windows.get(str);
        if (imageWindow != null) {
            imageWindow.setNewDimension(i, i2);
        }
    }

    public static void moveWindow(String str, int i, int i2) {
        ImageWindow imageWindow = windows.get(str);
        if (imageWindow != null) {
            imageWindow.setNewPosition(i, i2);
        }
    }
}
