package fm.liveswitch;

import java.util.HashMap;

public class Point {
    private static Point __empty;
    private int __x;
    private int __y;

    public static Point fromJson(String str) {
        return (Point) JsonSerializer.deserializeObject(str, new IFunction0<Point>() {
            public Point invoke() {
                return new Point();
            }
        }, new IAction3<Point, String, String>() {
            public void invoke(Point point, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "x")) {
                    point.setX(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "y")) {
                    point.setY(JsonSerializer.deserializeInteger(str2).getValue());
                }
            }
        });
    }

    public static Point getEmpty() {
        if (__empty == null) {
            __empty = new Point();
        }
        return __empty;
    }

    public int getX() {
        return this.__x;
    }

    public int getY() {
        return this.__y;
    }

    public static boolean isEquivalent(Point point, Point point2) {
        if (Global.equals(point, point2)) {
            return true;
        }
        if (point == null || point2 == null) {
            return false;
        }
        return isEquivalentNoCheck(point, point2);
    }

    public boolean isEquivalent(Point point) {
        return isEquivalent(this, point);
    }

    private static boolean isEquivalentNoCheck(Point point, Point point2) {
        return point.getX() == point2.getX() && point.getY() == point2.getY();
    }

    public Point() {
        setX(0);
        setY(0);
    }

    public Point(int i, int i2) {
        setX(i);
        setY(i2);
    }

    public void setX(int i) {
        this.__x = i;
    }

    public void setY(int i) {
        this.__y = i;
    }

    public static String toJson(Point point) {
        return JsonSerializer.serializeObject(point, new IAction2<Point, HashMap<String, String>>(point) {
            final /* synthetic */ Point val$point;

            {
                this.val$point = r1;
            }

            public void invoke(Point point, HashMap<String, String> hashMap) {
                if (this.val$point.getX() != -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "x", JsonSerializer.serializeInteger(new NullableInteger(this.val$point.getX())));
                }
                if (this.val$point.getY() != -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "y", JsonSerializer.serializeInteger(new NullableInteger(this.val$point.getY())));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public String toString() {
        return StringExtensions.format("{0},{1}", IntegerExtensions.toString(Integer.valueOf(getX())), IntegerExtensions.toString(Integer.valueOf(getY())));
    }
}
