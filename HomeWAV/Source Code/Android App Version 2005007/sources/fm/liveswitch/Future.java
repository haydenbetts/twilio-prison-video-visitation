package fm.liveswitch;

public abstract class Future<T> extends FutureBase<T> {
    public abstract Future<T> fail(IAction1<Exception> iAction1);

    public abstract Future<T> fail(IFunction1<Exception, Future<T>> iFunction1);

    public abstract Future<T> then(IAction1<T> iAction1);

    public abstract Future<T> then(IAction1<T> iAction1, IAction1<Exception> iAction12);

    public abstract <R> Future<R> then(IFunction1<T, Future<R>> iFunction1);

    public abstract <R> Future<R> then(IFunction1<T, Future<R>> iFunction1, IAction1<Exception> iAction1);

    private void doWait(final ManagedCondition managedCondition, int i) {
        then(new IAction1<T>() {
            public void invoke(T t) {
                synchronized (managedCondition) {
                    managedCondition.pulse();
                }
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                synchronized (managedCondition) {
                    managedCondition.pulse();
                }
            }
        });
        synchronized (managedCondition) {
            if (Global.equals(super.getState(), FutureState.Pending)) {
                if (i == -1) {
                    managedCondition.halt();
                } else if (!managedCondition.halt(i)) {
                    throw new RuntimeException(new Exception("Operation timed out."));
                }
            }
        }
    }

    private T doWaitForResult(int i) {
        waitForPromise(i);
        if (super.getException() == null) {
            return super.getResult();
        }
        throw new RuntimeException(super.getException());
    }

    protected Future() {
    }

    public void waitForPromise() {
        waitForPromise(-1);
    }

    public void waitForPromise(int i) {
        doWait(new ManagedCondition(), i);
    }

    public T waitForResult() {
        return waitForResult(-1);
    }

    public T waitForResult(int i) {
        return doWaitForResult(i);
    }
}
