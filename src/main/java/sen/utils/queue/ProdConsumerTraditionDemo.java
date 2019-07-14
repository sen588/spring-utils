package sen.utils.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
资源类
 */
class ShareData
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
       lock.lock();
        try
        {
            //使用while主要防止数据 虚假唤醒
            while (number != 0)
            {
                //等待，生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知，唤醒
            condition.signalAll();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try
        {
            while (number == 0)
            {
                //等待。消费
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }

}

public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {

        /*
        消费模式，生产一个消费一个
         */
        ShareData shareData = new ShareData();
        //A生产者
        for (int i = 1; i <= 5; i++) {
            new Thread(() ->
            {
                shareData.increment();
            }, "A").start();
        }

        //B消费者
        for (int i = 1; i <= 5; i++) {
            new Thread(() ->
            {
                shareData.decrement();
            }, "B").start();
        }
    }
}
