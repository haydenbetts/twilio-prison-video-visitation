package fm.liveswitch;

import java.util.HashMap;

public class LayoutFrame {
    private String _clientId;
    private String _deviceId;
    private int _height;
    private int _orientation;
    private String _userId;
    private String _viewId;
    private int _width;
    private int _x;
    private int _y;

    public static LayoutFrame fromJson(String str) {
        return (LayoutFrame) JsonSerializer.deserializeObject(str, new IFunction0<LayoutFrame>() {
            public LayoutFrame invoke() {
                return new LayoutFrame();
            }
        }, new IAction3<LayoutFrame, String, String>() {
            public void invoke(LayoutFrame layoutFrame, String str, String str2) {
                if (str.equals("x")) {
                    layoutFrame.setX(ParseAssistant.parseIntegerValue(str2));
                } else if (str.equals("y")) {
                    layoutFrame.setY(ParseAssistant.parseIntegerValue(str2));
                } else if (str.equals("w") || str.equals("width")) {
                    layoutFrame.setWidth(ParseAssistant.parseIntegerValue(str2));
                } else if (str.equals("h") || str.equals("height")) {
                    layoutFrame.setHeight(ParseAssistant.parseIntegerValue(str2));
                } else if (str.equals("orientation")) {
                    layoutFrame.setOrientation(ParseAssistant.parseIntegerValue(str2));
                } else if (str.equals("uid")) {
                    layoutFrame.setUserId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("did")) {
                    layoutFrame.setDeviceId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("cid")) {
                    layoutFrame.setClientId(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public String getClientId() {
        return this._clientId;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public int getHeight() {
        return this._height;
    }

    public int getOrientation() {
        return this._orientation;
    }

    public static LayoutFrame getScaledFrame(LayoutScale layoutScale, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        if (i == 0 || i2 == 0 || i3 == 0 || i4 == 0) {
            if (i == 0 || i3 == 0) {
                i6 = i / 2;
                i7 = 0;
            } else {
                i7 = i;
                i6 = 0;
            }
            if (i2 == 0 || i4 == 0) {
                i10 = i6;
                i = i7;
                i5 = i2 / 2;
                i2 = 0;
                return new LayoutFrame(i10, i5, i, i2);
            }
        } else {
            if (Global.equals(layoutScale, LayoutScale.Contain)) {
                float f = (float) i;
                float f2 = (float) i2;
                float f3 = f / f2;
                float f4 = ((float) i3) / ((float) i4);
                if (f3 > f4) {
                    i7 = (int) (f2 * f4);
                    i6 = (i - i7) / 2;
                } else {
                    if (f3 < f4) {
                        i8 = (int) (f / f4);
                        i9 = (i2 - i8) / 2;
                    }
                    i5 = 0;
                    return new LayoutFrame(i10, i5, i, i2);
                }
            } else {
                if (Global.equals(layoutScale, LayoutScale.Cover)) {
                    float f5 = (float) i;
                    float f6 = (float) i2;
                    float f7 = f5 / f6;
                    float f8 = ((float) i3) / ((float) i4);
                    if (f7 < f8) {
                        i7 = (int) (f6 * f8);
                        i6 = (i - i7) / 2;
                    } else if (f7 > f8) {
                        i8 = (int) (f5 / f8);
                        i9 = (i2 - i8) / 2;
                    }
                }
                i5 = 0;
                return new LayoutFrame(i10, i5, i, i2);
            }
            int i11 = i9;
            i2 = i8;
            i5 = i11;
            return new LayoutFrame(i10, i5, i, i2);
        }
        i10 = i6;
        i = i7;
        i5 = 0;
        return new LayoutFrame(i10, i5, i, i2);
    }

    public String getUserId() {
        return this._userId;
    }

    public String getViewId() {
        return this._viewId;
    }

    public int getWidth() {
        return this._width;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public boolean isEquivalent(LayoutFrame layoutFrame) {
        return layoutFrame.getX() == getX() && layoutFrame.getY() == getY() && layoutFrame.getWidth() == getWidth() && layoutFrame.getHeight() == getHeight();
    }

    private LayoutFrame() {
    }

    public LayoutFrame(int i, int i2, int i3, int i4) {
        setX(i);
        setY(i2);
        setWidth(i3);
        setHeight(i4);
    }

    public void setClientId(String str) {
        this._clientId = str;
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setHeight(int i) {
        this._height = i;
    }

    public void setOrientation(int i) {
        this._orientation = i;
    }

    public void setUserId(String str) {
        this._userId = str;
    }

    public void setViewId(String str) {
        this._viewId = str;
    }

    public void setWidth(int i) {
        this._width = i;
    }

    public void setX(int i) {
        this._x = i;
    }

    public void setY(int i) {
        this._y = i;
    }

    public static String toJson(LayoutFrame layoutFrame) {
        return JsonSerializer.serializeObject(layoutFrame, new IAction2<LayoutFrame, HashMap<String, String>>() {
            public void invoke(LayoutFrame layoutFrame, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "x", IntegerExtensions.toString(Integer.valueOf(layoutFrame.getX())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "y", IntegerExtensions.toString(Integer.valueOf(layoutFrame.getY())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "width", IntegerExtensions.toString(Integer.valueOf(layoutFrame.getWidth())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "height", IntegerExtensions.toString(Integer.valueOf(layoutFrame.getHeight())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "orientation", IntegerExtensions.toString(Integer.valueOf(layoutFrame.getOrientation())));
                if (layoutFrame.getUserId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "uid", JsonSerializer.serializeString(layoutFrame.getUserId()));
                }
                if (layoutFrame.getDeviceId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "did", JsonSerializer.serializeString(layoutFrame.getDeviceId()));
                }
                if (layoutFrame.getClientId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cid", JsonSerializer.serializeString(layoutFrame.getClientId()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
