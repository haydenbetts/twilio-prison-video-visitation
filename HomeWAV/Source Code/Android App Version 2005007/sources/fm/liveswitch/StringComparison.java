package fm.liveswitch;

public enum StringComparison {
    CurrentCulture(0),
    CurrentCultureIgnoreCase(1),
    InvariantCulture(2),
    InvariantCultureIgnoreCase(3),
    Ordinal(4),
    OrdinalIgnoreCase(5);
    
    private final int value;

    private StringComparison(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }
}
