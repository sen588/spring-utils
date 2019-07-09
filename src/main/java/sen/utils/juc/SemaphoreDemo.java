package sen.utils.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args)
    {
        /*
        三个车位
         */
        Semaphore semaphore = new Semaphore(3);

        /*
        十辆车
         */
        for (int i = 1; i <= 10; i++)
        {
            new Thread(()->
            {
                try {
                    semaphore.acquire();
                    System.out.println("第 " + Thread.currentThread().getName() + " 辆车抢到车位！");
                    try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace();}
                    System.out.println("第 " + Thread.currentThread().getName() + " 辆车离开车位。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
