package fm.liveswitch;

public class ManagedAutoResetEvent {
    private ManagedCondition __condition = new ManagedCondition();

    public void set() {
        synchronized (this.__condition) {
            this.__condition.pulseAll();
        }
    }

    public void waitOne() {
        synchronized (this.__condition) {
            this.__condition.halt();
        }
    }

    public boolean waitOne(int i) {
        boolean halt;
        synchronized (this.__condition) {
            halt = this.__condition.halt(i);
        }
        return halt;
    }
}
