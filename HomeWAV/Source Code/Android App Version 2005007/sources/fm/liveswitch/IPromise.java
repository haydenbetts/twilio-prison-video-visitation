package fm.liveswitch;

public interface IPromise {
    boolean castAndResolve(Object obj);

    boolean reject(Exception exc);
}
