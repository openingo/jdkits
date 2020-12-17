/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.java.util.concurrent;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorsX
 *
 * @author Qicz
 */
public final class ExecutorsX {

    private ExecutorsX() {
    }

    /**
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue.  At any point, at most
     * {@code nThreads} threads will be active processing tasks.
     * If additional tasks are submitted when all threads are active,
     * they will wait in the queue until a thread is available.
     * If any thread terminates due to a failure during execution
     * prior to shutdown, a new one will take its place if needed to
     * execute subsequent tasks.  The threads in the pool will exist
     * until it is explicitly {@link ExecutorService#shutdown shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @param queueCapacity linked blocking queue capacity
     * @return the newly created thread pool
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public static ExecutorService newFixedThreadPool(int nThreads, int queueCapacity) {
        return newFixedThreadPool(nThreads, queueCapacity, Executors.defaultThreadFactory());
    }

    /**
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue, using the provided
     * ThreadFactory to create new threads when needed.  At any point,
     * at most {@code nThreads} threads will be active processing
     * tasks.  If additional tasks are submitted when all threads are
     * active, they will wait in the queue until a thread is
     * available.  If any thread terminates due to a failure during
     * execution prior to shutdown, a new one will take its place if
     * needed to execute subsequent tasks.  The threads in the pool will
     * exist until it is explicitly {@link ExecutorService#shutdown
     * shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @param queueCapacity linked blocking queue capacity
     * @param threadFactory the factory to use when creating new threads
     * @return the newly created thread pool
     * @throws NullPointerException if threadFactory is null
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public static ExecutorService newFixedThreadPool(int nThreads, int queueCapacity, ThreadFactory threadFactory) {
        return newFixedThreadPool(nThreads, queueCapacity, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue, using the provided
     * ThreadFactory to create new threads when needed.  At any point,
     * at most {@code nThreads} threads will be active processing
     * tasks.  If additional tasks are submitted when all threads are
     * active, they will wait in the queue until a thread is
     * available.  If any thread terminates due to a failure during
     * execution prior to shutdown, a new one will take its place if
     * needed to execute subsequent tasks.  The threads in the pool will
     * exist until it is explicitly {@link ExecutorService#shutdown
     * shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @param queueCapacity linked blocking queue capacity
     * @param threadFactory the factory to use when creating new threads
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @return the newly created thread pool
     * @throws NullPointerException if threadFactory is null
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public static ExecutorService newFixedThreadPool(int nThreads, int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                threadFactory,
                handler);
    }

    /**
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue. (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.)  Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any
     * given time. Unlike the otherwise equivalent
     * {@code newFixedThreadPool(1)} the returned executor is
     * guaranteed not to be reconfigurable to use additional threads.
     *
     * @param queueCapacity linked blocking queue capacity
     *
     * @return the newly created single-threaded Executor
     */
    public static ExecutorService newSingleThreadExecutor(int queueCapacity) {
        return newSingleThreadExecutor(queueCapacity, Executors.defaultThreadFactory());
    }

    /**
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue, and uses the provided ThreadFactory to
     * create a new thread when needed. Unlike the otherwise
     * equivalent {@code newFixedThreadPool(1, threadFactory)} the
     * returned executor is guaranteed not to be reconfigurable to use
     * additional threads.
     *
     * @param queueCapacity linked blocking queue capacity
     * @param threadFactory the factory to use when creating new
     * threads
     *
     * @return the newly created single-threaded Executor
     * @throws NullPointerException if threadFactory is null
     */
    public static ExecutorService newSingleThreadExecutor(int queueCapacity, ThreadFactory threadFactory) {
        return newSingleThreadExecutor(queueCapacity, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue, and uses the provided ThreadFactory to
     * create a new thread when needed. Unlike the otherwise
     * equivalent {@code newFixedThreadPool(1, threadFactory)} the
     * returned executor is guaranteed not to be reconfigurable to use
     * additional threads.
     *
     * @param queueCapacity linked blocking queue capacity
     * @param threadFactory the factory to use when creating new
     * threads
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @return the newly created single-threaded Executor
     * @throws NullPointerException if threadFactory is null
     */
    public static ExecutorService newSingleThreadExecutor(int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                threadFactory,
                handler));
    }

    /**
     * Creates a thread pool that creates new threads as needed, but
     * will reuse previously constructed threads when they are
     * available.  These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * Calls to {@code execute} will reuse previously constructed
     * threads if available. If no existing thread is available, a new
     * thread will be created and added to the pool. Threads that have
     * not been used for sixty seconds are terminated and removed from
     * the cache. Thus, a pool that remains idle for long enough will
     * not consume any resources. Note that pools with similar
     * properties but different details (for example, timeout parameters)
     * may be created using {@link ThreadPoolExecutor} constructors.
     *
     * @param maximumPoolSize max pool size
     * @return the newly created thread pool
     */
    public static ExecutorService newCachedThreadPool(int maximumPoolSize) {
        return newCachedThreadPool(maximumPoolSize, Executors.defaultThreadFactory());
    }

    /**
     * Creates a thread pool that creates new threads as needed, but
     * will reuse previously constructed threads when they are
     * available, and uses the provided
     * ThreadFactory to create new threads when needed.
     * @param maximumPoolSize max pool size
     * @param threadFactory the factory to use when creating new threads
     * @return the newly created thread pool
     * @throws NullPointerException if threadFactory is null
     */
    public static ExecutorService newCachedThreadPool(int maximumPoolSize, ThreadFactory threadFactory) {
        return newCachedThreadPool(maximumPoolSize, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * Creates a thread pool that creates new threads as needed, but
     * will reuse previously constructed threads when they are
     * available, and uses the provided
     * ThreadFactory to create new threads when needed.
     * @param maximumPoolSize max pool size
     * @param threadFactory the factory to use when creating new threads
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @return the newly created thread pool
     * @throws NullPointerException if threadFactory is null
     */
    public static ExecutorService newCachedThreadPool(int maximumPoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(0, maximumPoolSize,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                threadFactory,
                handler);
    }

    /**
     * Creates a single-threaded executor that can schedule commands
     * to run after a given delay, or to execute periodically.
     * (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.)  Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any
     * given time. Unlike the otherwise equivalent
     * {@code newScheduledThreadPool(1)} the returned executor is
     * guaranteed not to be reconfigurable to use additional threads.
     * @param maximumPoolSize max pool size
     * @return the newly created scheduled executor
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor(int maximumPoolSize) {
        return newScheduledThreadPool(1, maximumPoolSize, Executors.defaultThreadFactory());
    }

    /**
     * Creates a single-threaded executor that can schedule commands
     * to run after a given delay, or to execute periodically.  (Note
     * however that if this single thread terminates due to a failure
     * during execution prior to shutdown, a new one will take its
     * place if needed to execute subsequent tasks.)  Tasks are
     * guaranteed to execute sequentially, and no more than one task
     * will be active at any given time. Unlike the otherwise
     * equivalent {@code newScheduledThreadPool(1, threadFactory)}
     * the returned executor is guaranteed not to be reconfigurable to
     * use additional threads.
     * @param maximumPoolSize max pool size
     * @param threadFactory the factory to use when creating new
     * threads
     * @return a newly created scheduled executor
     * @throws NullPointerException if threadFactory is null
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor(int maximumPoolSize, ThreadFactory threadFactory) {
        return newScheduledThreadPool(1, maximumPoolSize, threadFactory);
    }

    /**
     * Creates a thread pool that can schedule commands to run after a
     * given delay, or to execute periodically.
     * @param corePoolSize the number of threads to keep in the pool,
     * even if they are idle
     * @param maximumPoolSize max pool size
     * @return a newly created scheduled thread pool
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, int maximumPoolSize) {
        return newScheduledThreadPool(corePoolSize, maximumPoolSize, Executors.defaultThreadFactory());
    }

    /**
     * Creates a thread pool that can schedule commands to run after a
     * given delay, or to execute periodically.
     * @param corePoolSize the number of threads to keep in the pool,
     * even if they are idle
     * @param maximumPoolSize max pool size
     * @param threadFactory the factory to use when the executor
     * creates a new thread
     * @return a newly created scheduled thread pool
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if threadFactory is null
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, int maximumPoolSize, ThreadFactory threadFactory) {
        return newScheduledThreadPool(corePoolSize, maximumPoolSize, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * Creates a thread pool that can schedule commands to run after a
     * given delay, or to execute periodically.
     * @param corePoolSize the number of threads to keep in the pool,
     * even if they are idle
     * @param maximumPoolSize max pool size
     * @param threadFactory the factory to use when the executor
     * creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @return a newly created scheduled thread pool
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if threadFactory is null
     */
    public static ScheduledExecutorService newScheduledThreadPool(
            int corePoolSize, int maximumPoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler);
        try {
            Field maximumPoolSizeField = scheduledThreadPoolExecutor.getClass().getSuperclass().getDeclaredField("maximumPoolSize");
            maximumPoolSizeField.setAccessible(true);
            maximumPoolSizeField.set(scheduledThreadPoolExecutor, maximumPoolSize);
        } catch (Exception ignored) {

        }
        return new DelegatedScheduledExecutorService(scheduledThreadPoolExecutor);
    }

    /**
     * A wrapper class that exposes only the ExecutorService methods
     * of an ExecutorService implementation.
     */
    public static class DelegatedExecutorService extends AbstractExecutorService {
        private final ExecutorService e;
        DelegatedExecutorService(ExecutorService executor) { e = executor; }
        @Override
        public void execute(Runnable command) { e.execute(command); }
        @Override
        public void shutdown() { e.shutdown(); }
        @Override
        public List<Runnable> shutdownNow() { return e.shutdownNow(); }
        @Override
        public boolean isShutdown() { return e.isShutdown(); }
        @Override
        public boolean isTerminated() { return e.isTerminated(); }
        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit)
                throws InterruptedException {
            return e.awaitTermination(timeout, unit);
        }
        @Override
        public Future<?> submit(Runnable task) {
            return e.submit(task);
        }
        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return e.submit(task);
        }
        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return e.submit(task, result);
        }
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
                throws InterruptedException {
            return e.invokeAll(tasks);
        }
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                             long timeout, TimeUnit unit)
                throws InterruptedException {
            return e.invokeAll(tasks, timeout, unit);
        }
        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
                throws InterruptedException, ExecutionException {
            return e.invokeAny(tasks);
        }
        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                               long timeout, TimeUnit unit)
                throws InterruptedException, ExecutionException, TimeoutException {
            return e.invokeAny(tasks, timeout, unit);
        }
    }

    public static class FinalizableDelegatedExecutorService
            extends DelegatedExecutorService {
        FinalizableDelegatedExecutorService(ExecutorService executor) {
            super(executor);
        }
        @Override
        protected void finalize() {
            super.shutdown();
        }
    }

    /**
     * A wrapper class that exposes only the ScheduledExecutorService
     * methods of a ScheduledExecutorService implementation.
     */
    public static class DelegatedScheduledExecutorService
            extends DelegatedExecutorService
            implements ScheduledExecutorService {
        private final ScheduledExecutorService e;
        DelegatedScheduledExecutorService(ScheduledExecutorService executor) {
            super(executor);
            e = executor;
        }
        @Override
        public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            return e.schedule(command, delay, unit);
        }
        @Override
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            return e.schedule(callable, delay, unit);
        }
        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            return e.scheduleAtFixedRate(command, initialDelay, period, unit);
        }
        @Override
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            return e.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        }
    }
}
