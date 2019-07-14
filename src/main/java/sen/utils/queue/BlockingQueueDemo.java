package sen.utils.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo
{

    /*
    队列：先进先出

    队列空时，从队列中获取操作时队列将会被阻塞

    队列满时，从队列中添加操作时队列将会被阻塞
     */
    public static void main(String[] args)
    {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("往阻塞队列插入值");
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        System.out.println("移除阻塞队列的值");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }
}
