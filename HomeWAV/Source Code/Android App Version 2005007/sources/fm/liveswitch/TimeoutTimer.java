package fm.liveswitch;

import java.util.Timer;
import java.util.TimerTask;

public class TimeoutTimer implements ITimeoutTimer {
    private IAction1<Object> callback;
    private int currentTimeout = 0;
    private Object state;
    private Timer timer;
    private Object timerLock = new Object();

    public TimeoutTimer(IAction1<Object> iAction1, Object obj) {
        this.callback = iAction1;
        this.state = obj;
    }

    public void start(int i) {
        synchronized (this.timerLock) {
            if (this.timer == null) {
                this.currentTimeout = i;
                int max = MathAssistant.max(0, i);
                Timer timer2 = new Timer();
                this.timer = timer2;
                timer2.schedule(new TimerTask() {
                    public void run() {
                        TimeoutTimer.this.timerCallback();
                    }
                }, (long) max);
            } else {
                throw new RuntimeException("Timer is currently active.");
            }
        }
    }

    public boolean stop() {
        synchronized (this.timerLock) {
            Timer timer2 = this.timer;
            if (timer2 == null) {
                return false;
            }
            timer2.cancel();
            this.timer = null;
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void timerCallback() {
        boolean z;
        IAction1<Object> iAction1;
        synchronized (this.timerLock) {
            z = this.currentTimeout >= 0 && stop();
        }
        if (z && (iAction1 = this.callback) != null) {
            iAction1.invoke(this.state);
        }
    }
}
