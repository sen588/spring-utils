package sen.utils.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    /*
    同步队列

    生产一个消费一个

    运行结果：
        AAA	 put 1
        BBB	a
        AAA	 put 2
        BBB	b
        AAA	 put 3
        BBB	c
     */
    public static void main(String[] args)
    {

        BlockingQueue<Object> blockingQueue = new SynchronousQueue<>();

        new Thread(()->
        {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("a");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("b");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->
        {
            try {
                try { TimeUnit.SECONDS.sleep( 3); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                try { TimeUnit.SECONDS.sleep( 3); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                try { TimeUnit.SECONDS.sleep( 3); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
