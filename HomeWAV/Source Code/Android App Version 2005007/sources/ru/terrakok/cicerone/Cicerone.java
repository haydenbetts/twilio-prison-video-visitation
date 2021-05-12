package ru.terrakok.cicerone;

import ru.terrakok.cicerone.BaseRouter;

public class Cicerone<T extends BaseRouter> {
    private T router;

    private Cicerone(T t) {
        this.router = t;
    }

    public NavigatorHolder getNavigatorHolder() {
        return this.router.getCommandBuffer();
    }

    public T getRouter() {
        return this.router;
    }

    public static Cicerone<Router> create() {
        return create(new Router());
    }

    public static <T extends BaseRouter> Cicerone<T> create(T t) {
        return new Cicerone<>(t);
    }
}
