package fm.liveswitch;

import java.util.ArrayList;

public class IceServerTest {
    /* access modifiers changed from: private */
    public Scheduler __scheduler;
    private AddressType[] _iceAddressTypes;
    private IceGatherer _iceGatherer;
    private IcePortRange _icePortRange;
    private IceServer _iceServer;
    /* access modifiers changed from: private */
    public volatile boolean _running;
    /* access modifiers changed from: private */
    public Object _runningLock;

    private Promise<IceServerTestResult> doRun(final Promise<IceServerTestResult> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    synchronized (IceServerTest.this._runningLock) {
                        if (!IceServerTest.this._running) {
                            boolean unused = IceServerTest.this._running = true;
                        } else {
                            throw new RuntimeException(new Exception("Test is already running."));
                        }
                    }
                    Object obj = new Object();
                    IceServer[] iceServerArr = IceServerTest.this.getIceServer() == null ? null : new IceServer[]{IceServerTest.this.getIceServer()};
                    Scheduler unused2 = IceServerTest.this.__scheduler = new Scheduler(obj);
                    IceServerTest.this.setIceGatherer(new IceGatherer(obj, IceServerTest.this.__scheduler, new IceGatherOptions(IceGatherPolicy.All, iceServerArr, IceServerTest.this.getIcePortRange(), IceServerTest.this.getIceAddressTypes()), new IceParameters("test", "test")));
                    IceServerTest.this.doRun(promise, new ArrayList());
                } catch (Exception e) {
                    boolean unused3 = IceServerTest.this._running = false;
                    if (IceServerTest.this.__scheduler != null) {
                        IceServerTest.this.__scheduler.stop();
                    }
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    /* access modifiers changed from: private */
    public void doRun(final Promise<IceServerTestResult> promise, final ArrayList<Candidate> arrayList) {
        getIceGatherer().addOnStateChange(new IAction1<IceGatherer>() {
            public void invoke(IceGatherer iceGatherer) {
                if (Global.equals(iceGatherer.getState(), IceGatheringState.Failed)) {
                    IceServerTest.this.__scheduler.stop();
                    promise.reject(new Exception(StringExtensions.format("ICE gatherer failed.{0}", (Object) iceGatherer.getError() == null ? StringExtensions.empty : StringExtensions.concat(" ", iceGatherer.getError().getException().getMessage()))));
                } else if (Global.equals(iceGatherer.getState(), IceGatheringState.Complete)) {
                    promise.resolve(new IceServerTestResult((Candidate[]) arrayList.toArray(new Candidate[0])));
                    iceGatherer.stop();
                    IceServerTest.this.__scheduler.stop();
                }
            }
        });
        getIceGatherer().addOnLocalCandidate(new IAction2<IceGatherer, IceCandidate>() {
            public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                arrayList.add(iceCandidate.toCandidate(1, 0));
            }
        });
        if (!getIceGatherer().start()) {
            this.__scheduler.stop();
            throw new RuntimeException(new Exception("Could not start ICE gatherer."));
        }
    }

    public AddressType[] getIceAddressTypes() {
        return this._iceAddressTypes;
    }

    private IceGatherer getIceGatherer() {
        return this._iceGatherer;
    }

    public IcePortRange getIcePortRange() {
        return this._icePortRange;
    }

    public IceServer getIceServer() {
        return this._iceServer;
    }

    public IceServerTest(IceServer iceServer) {
        this(iceServer, (IcePortRange) null);
    }

    public IceServerTest(IceServer iceServer, IcePortRange icePortRange) {
        this(iceServer, icePortRange, (AddressType[]) null);
    }

    public IceServerTest(IceServer iceServer, IcePortRange icePortRange, AddressType[] addressTypeArr) {
        this._running = false;
        this._runningLock = new Object();
        icePortRange = icePortRange == null ? new IcePortRange() : icePortRange;
        addressTypeArr = addressTypeArr == null ? new AddressType[]{AddressType.IPv4, AddressType.IPv6} : addressTypeArr;
        setIceServer(iceServer);
        setIcePortRange(icePortRange);
        setIceAddressTypes(addressTypeArr);
    }

    public Future<IceServerTestResult> run() {
        return doRun(new Promise());
    }

    private void setIceAddressTypes(AddressType[] addressTypeArr) {
        this._iceAddressTypes = addressTypeArr;
    }

    /* access modifiers changed from: private */
    public void setIceGatherer(IceGatherer iceGatherer) {
        this._iceGatherer = iceGatherer;
    }

    private void setIcePortRange(IcePortRange icePortRange) {
        this._icePortRange = icePortRange;
    }

    private void setIceServer(IceServer iceServer) {
        this._iceServer = iceServer;
    }
}
