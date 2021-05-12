package fm.liveswitch;

import java.util.HashMap;

public class LayoutUtility {
    public static boolean floatLocalPreview(Layout layout, VideoLayout videoLayout, String str) {
        LayoutFrame layoutFrame;
        LayoutFrame layoutFrame2 = null;
        if (layout != null) {
            LayoutFrame localFrame = layout.getLocalFrame();
            LayoutFrame[] remoteFrames = layout.getRemoteFrames();
            if (remoteFrames != null && ArrayExtensions.getLength((Object[]) remoteFrames) > 0) {
                layoutFrame2 = remoteFrames[0];
            }
            layoutFrame = layoutFrame2;
            layoutFrame2 = localFrame;
        } else {
            layoutFrame = null;
        }
        return floatLocalPreview(layoutFrame2, layoutFrame, videoLayout, str);
    }

    public static boolean floatLocalPreview(Layout layout, VideoLayout videoLayout, String str, String str2) {
        LayoutFrame layoutFrame;
        LayoutFrame layoutFrame2 = null;
        if (layout != null) {
            LayoutFrame localFrame = layout.getLocalFrame();
            LayoutFrame[] remoteFrames = layout.getRemoteFrames();
            if (remoteFrames != null && ArrayExtensions.getLength((Object[]) remoteFrames) > 0) {
                for (LayoutFrame layoutFrame3 : remoteFrames) {
                    String viewId = layoutFrame3.getViewId();
                    if (viewId != null && viewId.equals(str2)) {
                        layoutFrame2 = layoutFrame3;
                    }
                }
            }
            layoutFrame = layoutFrame2;
            layoutFrame2 = localFrame;
        } else {
            layoutFrame = null;
        }
        if (videoLayout != null) {
            for (String next : HashMapExtensions.getKeys(videoLayout.getFrames())) {
                LayoutFrame layoutFrame4 = HashMapExtensions.getItem(videoLayout.getFrames()).get(next);
                LayoutPreset.transformFrame(layoutFrame4, layout.getOrigin(), videoLayout.getWidth(), videoLayout.getHeight());
                LayoutPreset.transformFrame(HashMapExtensions.getItem(videoLayout.getBounds()).get(next), layout.getOrigin(), layoutFrame4.getWidth(), layoutFrame4.getHeight());
            }
        }
        return floatLocalPreview(layoutFrame2, layoutFrame, videoLayout, str);
    }

    public static <T> boolean floatLocalPreview(Layout layout, VideoLayout videoLayout, String str, String str2, IViewSink<T> iViewSink) {
        if (!(iViewSink == null || videoLayout == null)) {
            iViewSink.setViewScale(videoLayout.getCrop() ? LayoutScale.Cover : LayoutScale.Contain);
        }
        return floatLocalPreview(layout, videoLayout, str, str2);
    }

    public static boolean floatLocalPreview(LayoutFrame layoutFrame, LayoutFrame layoutFrame2, VideoLayout videoLayout, String str) {
        double d;
        LayoutFrame layoutFrame3 = layoutFrame;
        if (layoutFrame3 == null || layoutFrame2 == null) {
            return false;
        }
        if (videoLayout != null) {
            double width = (double) layoutFrame2.getWidth();
            double height = (double) layoutFrame2.getHeight();
            double width2 = (double) videoLayout.getWidth();
            double height2 = (double) videoLayout.getHeight();
            double d2 = width2 / height2;
            double d3 = 0.0d;
            if (width / height > d2) {
                double d4 = d2 * height;
                d = 0.0d;
                d3 = (width - d4) / 2.0d;
                width = d4;
            } else {
                double d5 = width / d2;
                double d6 = (height - d5) / 2.0d;
                height = d5;
                d = d6;
            }
            HashMap<String, LayoutFrame> frames = videoLayout.getFrames();
            for (String next : HashMapExtensions.getKeys(frames)) {
                if (Global.equals(next, str)) {
                    LayoutFrame layoutFrame4 = HashMapExtensions.getItem(frames).get(next);
                    layoutFrame3.setX(layoutFrame2.getX() + ((int) (d3 + ((((double) layoutFrame4.getX()) / width2) * width))));
                    layoutFrame3.setY(layoutFrame2.getY() + ((int) (d + ((((double) layoutFrame4.getY()) / height2) * height))));
                    layoutFrame3.setWidth((int) ((((double) layoutFrame4.getWidth()) / width2) * width));
                    layoutFrame3.setHeight((int) ((((double) layoutFrame4.getHeight()) / height2) * height));
                    return true;
                }
            }
            return false;
        }
        layoutFrame3.setX(layoutFrame2.getX());
        layoutFrame3.setY(layoutFrame2.getY());
        layoutFrame3.setWidth(layoutFrame2.getWidth());
        layoutFrame3.setHeight(layoutFrame2.getHeight());
        return true;
    }
}
