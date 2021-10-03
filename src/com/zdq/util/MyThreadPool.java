package com.zdq.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2, 1, TimeUnit.SECONDS, 10);
        for (int i = 0; i < 1; i++) {
            int n = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + "---" + n));
        }
    }


    private final BlockingQueue<Runnable> taskQueue;

    private final HashSet<Worker> workers = new HashSet<>();

    private final int coreSize;

    private long timeout;

    private TimeUnit unit;

    public void execute(Runnable task) {

        if (workers.size() < coreSize) {
            Worker worker = new Worker(task);
            workers.add(worker);
            worker.start();
        } else {
            taskQueue.put(task);
        }

    }

    public MyThreadPool(int coreSize, int queueCapacity) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    public MyThreadPool(int coreSize, long timeout, TimeUnit unit, int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
//            if (timeout > 0 && unit != null) {
//                task = taskQueue.poll(timeout, unit);
//            }
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }


    private static class BlockingQueue<T> {

        private final Deque<T> queue = new ArrayDeque<>();

        private final ReentrantLock lock = new ReentrantLock();

        private final Condition fullWaitSet = lock.newCondition();

        private final Condition emptyWaitSet = lock.newCondition();

        private final int capacity;

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
        }

        public T poll(long timeout, TimeUnit unit) {
            lock.lock();

            try {
                long nanos = unit.toNanos(timeout);
                while (queue.isEmpty()) {
                    try {
                        //还剩多长的等待时间
                        if (nanos <= 0) {
                            return null;
                        }
                        nanos = emptyWaitSet.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                T t = queue.removeFirst();
                fullWaitSet.signal();
                return t;
            } finally {
                lock.unlock();
            }
        }


        public T take() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    try {
                        emptyWaitSet.await();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                T t = queue.removeFirst();
                fullWaitSet.signal();
                return t;
            } finally {
                lock.unlock();
            }
        }

        public void put(T element) {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    try {
                        fullWaitSet.await();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                queue.addLast(element);
                emptyWaitSet.signal();
            } finally {
                lock.unlock();
            }
        }


        public int getCapacity() {
            return capacity;
        }
    }
}
