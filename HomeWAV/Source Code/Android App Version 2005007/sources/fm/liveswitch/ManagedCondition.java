package fm.liveswitch;

public class ManagedCondition {
    public void halt() {
        try {
            wait();
        } catch (InterruptedException unused) {
        }
    }

    public boolean halt(int i) {
        if (i == 0) {
            i = 1;
        }
        try {
            wait((long) i);
            return true;
        } catch (InterruptedException unused) {
            return false;
        }
    }

    public void pulse() {
        notify();
    }

    public void pulseAll() {
        notifyAll();
    }
}
