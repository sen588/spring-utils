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
        System.out.println("抛出异常插入：");
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));
        System.out.println("抛出异常移除：");
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());

        System.out.println("抛出异常检查：");
        System.out.println(blockingQueue.element());

        System.out.println("特殊值插入：");
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println("特殊值移除：");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println("特殊值检查：");
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
    }
}
