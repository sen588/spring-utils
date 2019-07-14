package sen.utils.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo
{

    /*
    队列：先进先出

    队列空时，从队列中获取操作时队列将会被阻塞

    队列满时，从队列中添加操作时队列将会被阻塞
     */
    public static void main(String[] args) throws InterruptedException {
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
        //System.out.println(blockingQueue.offer("d"));

        System.out.println("特殊值移除：");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println("特殊值检查：");
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());

        /*
        不见不散
         */
        System.out.println("阻塞插入：");
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("阻塞移除：");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        /*
           过时不候
         */
        System.out.println("超时插入：");
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        //超过2秒，插入失败，返回false
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
        System.out.println("超时移除：");
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }
}
