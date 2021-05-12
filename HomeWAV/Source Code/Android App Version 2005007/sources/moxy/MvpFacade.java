package moxy;

public final class MvpFacade {
    private static volatile MvpFacade instance;
    private static final Object lock = new Object();
    private MvpProcessor mvpProcessor = new MvpProcessor();
    private PresenterStore presenterStore = new PresenterStore();
    private PresentersCounter presentersCounter = new PresentersCounter();

    private MvpFacade() {
    }

    public static MvpFacade getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MvpFacade();
                }
            }
        }
        return instance;
    }

    public static void init() {
        getInstance();
    }

    public PresenterStore getPresenterStore() {
        return this.presenterStore;
    }

    public void setPresenterStore(PresenterStore presenterStore2) {
        this.presenterStore = presenterStore2;
    }

    public MvpProcessor getMvpProcessor() {
        return this.mvpProcessor;
    }

    public void setMvpProcessor(MvpProcessor mvpProcessor2) {
        this.mvpProcessor = mvpProcessor2;
    }

    public PresentersCounter getPresentersCounter() {
        return this.presentersCounter;
    }

    public void setPresentersCounter(PresentersCounter presentersCounter2) {
        this.presentersCounter = presentersCounter2;
    }
}
