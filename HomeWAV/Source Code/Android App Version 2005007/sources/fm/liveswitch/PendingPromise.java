package fm.liveswitch;

class PendingPromise {
    private IPromise __promise;
    private Object __result;

    public PendingPromise(IPromise iPromise, Object obj) {
        this.__promise = iPromise;
        this.__result = obj;
    }

    public void reject(Exception exc) {
        this.__promise.reject(exc);
    }

    public void resolve() {
        this.__promise.castAndResolve(this.__result);
    }
}
