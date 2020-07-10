package cn.fast.web.common.utils;

import java.util.concurrent.*;

/**
 *
 */
public class ThreadPoolUtil {

    /**
     * 线程缓冲队列
     */
    private static BlockingQueue<Runnable> bqueue = new LinkedBlockingDeque<>(50);
    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 10;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long ALIVE_TIME = 2000;

    /**
     * AbortPolicy该策略是线程池的默认策略。使用该策略时，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
     * DiscardPolicy这个策略和AbortPolicy的slient版本，如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。
     * DiscardOldestPolicy这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
     * 因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
     * CallerRunsPolicy使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干。
     * 自定义:如果以上策略都不符合业务场景，那么可以自己定义一个拒绝策略，只要实现RejectedExecutionHandler接口，并且实现rejectedExecution方法就可以了。具体的逻辑就在rejectedExecution方法里去定义就OK了。
     * 例如：我定义了我的一个拒绝策略，叫做MyRejectPolicy，里面的逻辑就是打印处理被拒绝的任务内容
     * 原文：https://blog.csdn.net/jgteng/article/details/54411423
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MILLISECONDS, bqueue, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void submit(Runnable r){
        pool.submit(r);
    }

    static {

        pool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getPool() {
        return pool;
    }

    public static void main(String[] args) {
        System.out.println(pool.getPoolSize());
        System.out.println ("获取CPU个数"+Runtime.getRuntime ().availableProcessors () );
    }
}
