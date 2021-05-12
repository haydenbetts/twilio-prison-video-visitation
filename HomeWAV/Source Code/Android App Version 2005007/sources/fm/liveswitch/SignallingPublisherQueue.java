package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SignallingPublisherQueue {
    /* access modifiers changed from: private */
    public List<IAction1<Exception>> __exception = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<SignallingPublication[]>> __published = new ArrayList();
    private SignallingPublisher __publisher;
    private boolean _active = false;
    private IAction1<Exception> _exception = null;
    private int _maxBatchSize;
    private ArrayList<SignallingPublication> _publications = new ArrayList<>();
    private ManagedCondition _publicationsLock = new ManagedCondition();
    private IAction1<SignallingPublication[]> _published = null;
    private Object _stateLock = new Object();
    private ManagedThread _thread = null;

    public void addException(IAction1<Exception> iAction1) {
        if (iAction1 != null) {
            if (this._exception == null) {
                this._exception = new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        Iterator it = new ArrayList(SignallingPublisherQueue.this.__exception).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(exc);
                        }
                    }
                };
            }
            this.__exception.add(iAction1);
        }
    }

    public void addPublication(SignallingPublication signallingPublication) {
        synchronized (this._publicationsLock) {
            this._publications.add(signallingPublication);
            this._publicationsLock.pulse();
        }
    }

    public void addPublished(IAction1<SignallingPublication[]> iAction1) {
        if (iAction1 != null) {
            if (this._published == null) {
                this._published = new IAction1<SignallingPublication[]>() {
                    public void invoke(SignallingPublication[] signallingPublicationArr) {
                        Iterator it = new ArrayList(SignallingPublisherQueue.this.__published).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(signallingPublicationArr);
                        }
                    }
                };
            }
            this.__published.add(iAction1);
        }
    }

    public int getMaxBatchSize() {
        return this._maxBatchSize;
    }

    public SignallingPublisher getPublisher() {
        return this.__publisher;
    }

    /* access modifiers changed from: private */
    public void loop(ManagedThread managedThread) {
        SignallingPublication[] stealPublications;
        while (this._active) {
            managedThread.loopBegin();
            synchronized (this._publicationsLock) {
                stealPublications = stealPublications();
                if (ArrayExtensions.getLength((Object[]) stealPublications) == 0) {
                    this._publicationsLock.halt();
                    stealPublications = stealPublications();
                }
            }
            if (ArrayExtensions.getLength((Object[]) stealPublications) > 0) {
                Exception e = null;
                try {
                    stealPublications = getPublisher().publishMany(stealPublications);
                } catch (Exception e2) {
                    e = e2;
                }
                if (e == null) {
                    IAction1<SignallingPublication[]> iAction1 = this._published;
                    if (iAction1 != null) {
                        iAction1.invoke(stealPublications);
                    }
                } else {
                    synchronized (this._publicationsLock) {
                        for (SignallingPublication add : stealPublications) {
                            this._publications.add(add);
                        }
                    }
                    IAction1<Exception> iAction12 = this._exception;
                    if (iAction12 != null) {
                        iAction12.invoke(e);
                    }
                }
            }
            managedThread.loopEnd();
        }
    }

    public void removeException(IAction1<Exception> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__exception, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__exception.remove(iAction1);
        if (this.__exception.size() == 0) {
            this._exception = null;
        }
    }

    public void removePublished(IAction1<SignallingPublication[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__published, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__published.remove(iAction1);
        if (this.__published.size() == 0) {
            this._published = null;
        }
    }

    public void setMaxBatchSize(int i) {
        this._maxBatchSize = i;
    }

    public SignallingPublisherQueue(SignallingPublisher signallingPublisher) {
        if (signallingPublisher != null) {
            this.__publisher = signallingPublisher;
            setMaxBatchSize(1000);
            return;
        }
        throw new RuntimeException(new Exception("publisher cannot be null."));
    }

    public void start() {
        synchronized (this._stateLock) {
            if (!this._active) {
                this._active = true;
                ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                    public String getId() {
                        return "fm.liveswitch.SignallingPublisherQueue.loop";
                    }

                    public void invoke(ManagedThread managedThread) {
                        SignallingPublisherQueue.this.loop(managedThread);
                    }
                });
                this._thread = managedThread;
                managedThread.start();
            }
        }
    }

    private SignallingPublication[] stealPublications() {
        int min = MathAssistant.min(getMaxBatchSize(), ArrayListExtensions.getCount(this._publications));
        if (min <= 0) {
            min = ArrayListExtensions.getCount(this._publications);
        }
        SignallingPublication[] signallingPublicationArr = new SignallingPublication[min];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) signallingPublicationArr); i++) {
            signallingPublicationArr[i] = (SignallingPublication) ArrayListExtensions.getItem(this._publications).get(i);
        }
        ArrayListExtensions.removeRange(this._publications, 0, ArrayExtensions.getLength((Object[]) signallingPublicationArr));
        return signallingPublicationArr;
    }

    public void stop() {
        synchronized (this._stateLock) {
            if (this._active) {
                this._active = false;
                synchronized (this._publicationsLock) {
                    this._publicationsLock.pulse();
                }
            }
        }
    }
}
