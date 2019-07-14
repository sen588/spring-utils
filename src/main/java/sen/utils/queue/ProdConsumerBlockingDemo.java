package sen.utils.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProd() throws InterruptedException {
        String Data;
        boolean retValue;
        while (FLAG)
        {
            Data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(Data, 2L, TimeUnit.SECONDS);
            if(retValue)
            {
                System.out.println(Thread.currentThread().getName() + "\t 开始插入值：" + Data);
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入值失败：" + Data);
            }
            try { TimeUnit.SECONDS.sleep( 1); } catch (InterruptedException e) { e.printStackTrace();}
        }
        System.out.println(Thread.currentThread().getName() + "大老板叫停，FLAG=false,生产结束。");
    }

    public void myConsumer() throws InterruptedException {
        String retValue;
        while (FLAG)
        {
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(retValue == null || retValue.equalsIgnoreCase(""))
            {
                System.out.println(Thread.currentThread().getName() + "\t 取值等待2秒失败，所有线程结束运行。");
                FLAG = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 取值成功：" + atomicInteger);
        }
    }
    
    public void stop()
    {
        this.FLAG = false;
    }
}

public class ProdConsumerBlockingDemo {

    public static void main(String[] args)
    {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->
        {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程已启动，调用myProd方法。");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"myProd").start();

        new Thread(()->
        {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程已启动，调用myConsumer方法。");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"myConsumer").start();

        try { TimeUnit.SECONDS.sleep( 5); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("大老板 " + Thread.currentThread().getName() + " 线程叫停，调用stop方法，停止线程工作。");
        myResource.stop();
    }
}
/*
运行结果：
    java.util.concurrent.ArrayBlockingQueue
    myProd	 生产线程已启动，调用myProd方法。
    myConsumer	 消费线程已启动，调用myConsumer方法。
    myProd	 开始插入值：1
    myConsumer	 取值成功：1
    myConsumer	 取值成功：2
    myProd	 开始插入值：2
    myProd	 开始插入值：3
    myConsumer	 取值成功：3
    myProd	 开始插入值：4
    myConsumer	 取值成功：4
    myProd	 开始插入值：5
    myConsumer	 取值成功：5
    大老板 main 线程叫停，调用stop方法，停止线程工作。
    myProd大老板叫停，FLAG=false,生产结束。
    myConsumer	 取值等待2秒失败，所有线程结束运行。
 */