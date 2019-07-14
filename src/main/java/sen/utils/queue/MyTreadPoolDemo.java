package sen.utils.queue;

import java.util.concurrent.*;

public class MyTreadPoolDemo {

    public static void main(String[] args)
    {
        //获取cpu的核数
        int cup = Runtime.getRuntime().availableProcessors() + 1;
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,//1、corePoolSize：线程池中的常驻核心线程数
                cup,//2、maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
                1L,//3、keepAliveTime：多余的空闲线程的存活时间当前池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
                TimeUnit.SECONDS,//4、unit：keepAliveTime的单位
                new LinkedBlockingQueue<>(3),//5、workQueue：任务队列，被提交但尚未被执行的任务
                Executors.defaultThreadFactory(),//6、threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
                new ThreadPoolExecutor.AbortPolicy());//7、handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）时如何来拒绝请求执行的runnable的策略

        /*
        拒绝策约：
            AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行了

            CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。

            DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加人队列中尝试再次提交当前任务。

            DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略。
        */
        try {
            for(int i = 1; i <= 10; i++)
            {
                threadPool.execute(()->
                {
                    System.out.println(Thread.currentThread().getName() + " :开始办理业务。");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
