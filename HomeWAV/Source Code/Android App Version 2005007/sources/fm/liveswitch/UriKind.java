package fm.liveswitch;

public enum UriKind {
    RelativeOrAbsolute(0),
    Absolute(1),
    Relative(2);
    
    private final int value;

    private UriKind(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }
}
