package fm.liveswitch;

import java.util.HashMap;

public class Size {
    private static Size __empty;
    private int __height;
    private int __width;

    public static Size fromJson(String str) {
        return (Size) JsonSerializer.deserializeObject(str, new IFunction0<Size>() {
            public Size invoke() {
                return new Size();
            }
        }, new IAction3<Size, String, String>() {
            public void invoke(Size size, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "width")) {
                    size.setWidth(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "height")) {
                    size.setHeight(JsonSerializer.deserializeInteger(str2).getValue());
                }
            }
        });
    }

    public static Size getEmpty() {
        if (__empty == null) {
            __empty = new Size();
        }
        return __empty;
    }

    public int getHeight() {
        return this.__height;
    }

    public int getWidth() {
        return this.__width;
    }

    public static boolean isEquivalent(Size size, Size size2) {
        if (Global.equals(size, size2)) {
            return true;
        }
        if (size == null || size2 == null) {
            return false;
        }
        return isEquivalentNoCheck(size, size2);
    }

    public boolean isEquivalent(Size size) {
        return isEquivalent(this, size);
    }

    private static boolean isEquivalentNoCheck(Size size, Size size2) {
        return size.getWidth() == size2.getWidth() && size.getHeight() == size2.getHeight();
    }

    public void setHeight(int i) {
        if (i >= 0) {
            this.__height = i;
            return;
        }
        throw new RuntimeException(new Exception("Height cannot be less than 0."));
    }

    public void setWidth(int i) {
        if (i >= 0) {
            this.__width = i;
            return;
        }
        throw new RuntimeException(new Exception("Width cannot be less than 0."));
    }

    public Size() {
        setWidth(0);
        setHeight(0);
    }

    public Size(int i, int i2) {
        setWidth(i);
        setHeight(i2);
    }

    public static String toJson(Size size) {
        return JsonSerializer.serializeObject(size, new IAction2<Size, HashMap<String, String>>(size) {
            final /* synthetic */ Size val$size;

            {
                this.val$size = r1;
            }

            public void invoke(Size size, HashMap<String, String> hashMap) {
                if (this.val$size.getWidth() != -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "width", JsonSerializer.serializeInteger(new NullableInteger(this.val$size.getWidth())));
                }
                if (this.val$size.getHeight() != -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "height", JsonSerializer.serializeInteger(new NullableInteger(this.val$size.getHeight())));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public String toString() {
        return StringExtensions.format("{0}x{1}", IntegerExtensions.toString(Integer.valueOf(getWidth())), IntegerExtensions.toString(Integer.valueOf(getHeight())));
    }
}
