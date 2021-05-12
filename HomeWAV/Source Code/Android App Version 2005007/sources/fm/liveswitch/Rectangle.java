package fm.liveswitch;

import java.util.HashMap;

public class Rectangle {
    private static Rectangle __empty;
    private Point __origin;
    private Size __size;

    public static Rectangle fromJson(String str) {
        return (Rectangle) JsonSerializer.deserializeObject(str, new IFunction0<Rectangle>() {
            public Rectangle invoke() {
                return new Rectangle();
            }
        }, new IAction3<Rectangle, String, String>() {
            public void invoke(Rectangle rectangle, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, TtmlNode.ATTR_TTS_ORIGIN)) {
                    rectangle.setOrigin(Point.fromJson(str2));
                } else if (Global.equals(str, "size")) {
                    rectangle.setSize(Size.fromJson(str2));
                }
            }
        });
    }

    public static Rectangle getEmpty() {
        if (__empty == null) {
            __empty = new Rectangle();
        }
        return __empty;
    }

    public int getHeight() {
        return getSize().getHeight();
    }

    public Point getOrigin() {
        return this.__origin;
    }

    public Size getSize() {
        return this.__size;
    }

    public int getWidth() {
        return getSize().getWidth();
    }

    public int getX() {
        return getOrigin().getX();
    }

    public int getY() {
        return getOrigin().getY();
    }

    public static boolean isEquivalent(Rectangle rectangle, Rectangle rectangle2) {
        if (Global.equals(rectangle, rectangle2)) {
            return true;
        }
        if (rectangle == null || rectangle2 == null) {
            return false;
        }
        return isEquivalentNoCheck(rectangle, rectangle2);
    }

    public boolean isEquivalent(Rectangle rectangle) {
        return isEquivalent(this, rectangle);
    }

    private static boolean isEquivalentNoCheck(Rectangle rectangle, Rectangle rectangle2) {
        return Point.isEquivalent(rectangle.getOrigin(), rectangle2.getOrigin()) && Size.isEquivalent(rectangle.getSize(), rectangle2.getSize());
    }

    public Rectangle() {
        setOrigin(Point.getEmpty());
        setSize(Size.getEmpty());
    }

    public Rectangle(Point point, Size size) {
        setOrigin(point);
        setSize(size);
    }

    public void setOrigin(Point point) {
        if (point != null) {
            this.__origin = point;
            return;
        }
        throw new RuntimeException(new Exception("Origin cannot be null."));
    }

    public void setSize(Size size) {
        if (size != null) {
            this.__size = size;
            return;
        }
        throw new RuntimeException(new Exception("Size cannot be null."));
    }

    public static String toJson(Rectangle rectangle) {
        return JsonSerializer.serializeObject(rectangle, new IAction2<Rectangle, HashMap<String, String>>(rectangle) {
            final /* synthetic */ Rectangle val$rectangle;

            {
                this.val$rectangle = r1;
            }

            public void invoke(Rectangle rectangle, HashMap<String, String> hashMap) {
                if (this.val$rectangle.getOrigin() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), TtmlNode.ATTR_TTS_ORIGIN, Point.toJson(this.val$rectangle.getOrigin()));
                }
                if (this.val$rectangle.getSize() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "size", Size.toJson(this.val$rectangle.getSize()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public String toString() {
        return StringExtensions.format("{0}/{1}", getOrigin().toString(), getSize().toString());
    }
}
