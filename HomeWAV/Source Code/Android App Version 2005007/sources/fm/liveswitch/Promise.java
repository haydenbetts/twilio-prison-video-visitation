package fm.liveswitch;

public class Promise<T> extends PromiseBase<T> {
    private Future<T> doFail(final IAction1<Exception> iAction1, final Promise<T> promise) {
        super.process(promise, new IAction1<T>() {
            public void invoke(T t) {
                promise.resolve(t);
            }
        }, new IAction1<Exception>() {
            public void invoke(Exception exc) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(exc);
                }
                promise.reject(exc);
            }
        });
        return promise;
    }

    private Future<T> doFail(final IFunction1<Exception, Future<T>> iFunction1, final Promise<T> promise) {
        super.process(promise, new IAction1<T>() {
            public void invoke(T t) {
                promise.resolve(t);
            }
        }, new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Future future = (Future) iFunction1.invoke(exc);
                if (future == null) {
                    Log.error("Reject function may be return null.");
                } else {
                    future.then(new IAction1<T>() {
                        public void invoke(T t) {
                            promise.resolve(t);
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            promise.reject(exc);
                        }
                    });
                }
            }
        });
        return promise;
    }

    private Future<T> doThen(final IAction1<T> iAction1, final IAction1<Exception> iAction12, final Promise<T> promise) {
        super.process(promise, new IAction1<T>() {
            public void invoke(T t) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(t);
                }
                promise.resolve(t);
            }
        }, new IAction1<Exception>() {
            public void invoke(Exception exc) {
                IAction1 iAction1 = iAction12;
                if (iAction1 != null) {
                    iAction1.invoke(exc);
                }
                promise.reject(exc);
            }
        });
        return promise;
    }

    private <R> Future<R> doThen(final IFunction1<T, Future<R>> iFunction1, final IAction1<Exception> iAction1, final Promise<R> promise) {
        super.process(promise, new IAction1<T>() {
            public void invoke(T t) {
                Future future = (Future) iFunction1.invoke(t);
                if (future == null) {
                    Log.error("Resolve function may not return null.");
                } else {
                    future.then(new IAction1<R>() {
                        public void invoke(R r) {
                            promise.resolve(r);
                        }
                    }, (IAction1<Exception>) new IAction1<Exception>() {
                        public void invoke(Exception exc) {
                            promise.reject(exc);
                        }
                    });
                }
            }
        }, new IAction1<Exception>() {
            public void invoke(Exception exc) {
                IAction1 iAction1 = iAction1;
                if (iAction1 != null) {
                    iAction1.invoke(exc);
                }
                promise.reject(exc);
            }
        });
        return promise;
    }

    private static Future<Object> doWrapAsync(final Promise<Object> promise, final IAction0 iAction0) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    IAction0 iAction0 = iAction0;
                    if (iAction0 != null) {
                        iAction0.invoke();
                    }
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    private static <R> Future<R> doWrapAsync(final Promise<R> promise, final IFunction0<R> iFunction0) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    IFunction0 iFunction0 = iFunction0;
                    if (iFunction0 == null) {
                        promise.resolve(null);
                    } else {
                        promise.resolve(iFunction0.invoke());
                    }
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    public Future<T> fail(IAction1<Exception> iAction1) {
        return doFail(iAction1, new Promise());
    }

    public Future<T> fail(IFunction1<Exception, Future<T>> iFunction1) {
        return doFail(iFunction1, new Promise());
    }

    public Promise() {
    }

    public Promise(IAction2<IAction1<T>, IAction1<Exception>> iAction2) {
        iAction2.invoke(new IAction1<T>() {
            public void invoke(T t) {
                Promise.this.resolve(t);
            }
        }, new IAction1<Exception>() {
            public void invoke(Exception exc) {
                Promise.this.reject(exc);
            }
        });
    }

    public Future<T> then(IAction1<T> iAction1) {
        return then(iAction1, (IAction1<Exception>) null);
    }

    public Future<T> then(IAction1<T> iAction1, IAction1<Exception> iAction12) {
        return doThen(iAction1, iAction12, new Promise());
    }

    public <R> Future<R> then(IFunction1<T, Future<R>> iFunction1) {
        return then(iFunction1, (IAction1<Exception>) null);
    }

    public <R> Future<R> then(IFunction1<T, Future<R>> iFunction1, IAction1<Exception> iAction1) {
        if (iFunction1 != null) {
            return doThen(iFunction1, iAction1, new Promise());
        }
        throw new RuntimeException(new Exception("Resolve function may not be null."));
    }

    public static Future<Object> wrap(IAction0 iAction0) {
        Promise promise = new Promise();
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                promise.reject(e);
            }
        }
        promise.resolve(null);
        return promise;
    }

    public static <R> Future<R> wrap(IFunction0<R> iFunction0) {
        Promise promise = new Promise();
        if (iFunction0 == null) {
            try {
                promise.resolve(null);
                return promise;
            } catch (Exception e) {
                promise.reject(e);
            }
        } else {
            promise.resolve(iFunction0.invoke());
            return promise;
        }
    }

    public static Future<Object> wrapAsync(IAction0 iAction0) {
        return doWrapAsync((Promise<Object>) new Promise(), iAction0);
    }

    public static <R> Future<R> wrapAsync(IFunction0<R> iFunction0) {
        return doWrapAsync(new Promise(), iFunction0);
    }
}
