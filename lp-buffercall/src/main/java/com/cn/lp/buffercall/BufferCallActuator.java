package com.cn.lp.buffercall;

import com.cn.lp.buffercall.exception.BufferCallException;

import java.util.concurrent.*;

/**
 * 缓冲调用执行者
 *
 * @author qirong
 */
public class BufferCallActuator {

    private static volatile BufferCallActuator instance;

    public static BufferCallActuator getInstance() {
        if (instance == null) {
            synchronized (BufferCallActuator.class) {
                if(instance == null) {
                    instance = new BufferCallActuator();
                }
            }
        }
        return instance;
    }

    protected ConcurrentHashMap<String, Future> futureMap = new ConcurrentHashMap<>();

    public <T, P1, P2, P3, P4, P5, P6, P7> T execute(String callKey, P7BufferCall<T, P1, P2, P3, P4, P5, P6, P7> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) throws Exception {
        return execute(callKey, -1, invoke, p1, p2, p3, p4, p5, p6, p7);
    }

    public <T, P1, P2, P3, P4, P5, P6, P7> T execute(String callKey, long waitTime, P7BufferCall<T, P1, P2, P3, P4, P5, P6, P7> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2, p3, p4, p5, p6, p7);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1, P2, P3, P4, P5, P6> T execute(String callKey, P6BufferCall<T, P1, P2, P3, P4, P5, P6> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) throws Exception {
        return execute(callKey, -1, invoke, p1, p2, p3, p4, p5, p6);
    }

    public <T, P1, P2, P3, P4, P5, P6> T execute(String callKey, long waitTime, P6BufferCall<T, P1, P2, P3, P4, P5, P6> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2, p3, p4, p5, p6);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1, P2, P3, P4, P5> T execute(String callKey, P5BufferCall<T, P1, P2, P3, P4, P5> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) throws Exception {
        return execute(callKey, -1, invoke, p1, p2, p3, p4, p5);
    }

    public <T, P1, P2, P3, P4, P5> T execute(String callKey, long waitTime, P5BufferCall<T, P1, P2, P3, P4, P5> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2, p3, p4, p5);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1, P2, P3, P4> T execute(String callKey, P4BufferCall<T, P1, P2, P3, P4> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4) throws Exception {
        return execute(callKey, -1, invoke, p1, p2, p3, p4);
    }

    public <T, P1, P2, P3, P4> T execute(String callKey, long waitTime, P4BufferCall<T, P1, P2, P3, P4> invoke,
        P1 p1, P2 p2, P3 p3, P4 p4) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2, p3, p4);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1, P2, P3> T execute(String callKey, P3BufferCall<T, P1, P2, P3> invoke,
        P1 p1, P2 p2, P3 p3) throws Exception {
        return execute(callKey, -1, invoke, p1, p2, p3);
    }

    public <T, P1, P2, P3> T execute(String callKey, long waitTime, P3BufferCall<T, P1, P2, P3> invoke,
        P1 p1, P2 p2, P3 p3) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2, p3);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1, P2> T execute(String callKey, P2BufferCall<T, P1, P2> invoke,
        P1 p1, P2 p2) throws Exception {
        return execute(callKey, -1, invoke, p1, p2);
    }

    public <T, P1, P2> T execute(String callKey, long waitTime, P2BufferCall<T, P1, P2> invoke,
        P1 p1, P2 p2) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1, p2);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T, P1> T execute(String callKey, P1BufferCall<T, P1> invoke,
        P1 p1) throws Exception {
        return execute(callKey, -1, invoke, p1);
    }

    public <T, P1> T execute(String callKey, long waitTime, P1BufferCall<T, P1> invoke,
        P1 p1) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call(p1);
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });

        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T> T execute(String callKey, long waitTime, VoidBufferCall<T> invoke) throws Exception {
        Future<T> future = new FutureTask<>(() -> {
            try {
                T value = invoke.call();
                return value;
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            } finally {
                futureMap.remove(callKey);
            }
        });
        Future oldFuture = futureMap.putIfAbsent(callKey, future);
        if (oldFuture == null) {
            ((FutureTask) future).run();
        } else {
            future = oldFuture;
        }
        try {
            if (waitTime > 0) {
                try {
                    return future.get(waitTime, TimeUnit.MILLISECONDS);
                } catch (TimeoutException te) {
                    throw new BufferCallException(callKey, te);
                }
            } else {
                return future.get();
            }
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else {
                throw new Exception(throwable);
            }
        }
    }

    public <T> T execute(String callKey, VoidBufferCall<T> invoke) throws Exception {
        return execute(callKey, -1, invoke);
    }

    public <T> T speedyExecute(String callKey, VoidBufferCall<T> invoke) {
        try {
            return execute(callKey, -1, invoke);
        } catch (Exception e) {
            throw new BufferCallException(callKey, e);
        }
    }
}
