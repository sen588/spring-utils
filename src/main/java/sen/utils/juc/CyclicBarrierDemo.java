package sen.utils.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Thread(()->{System.out.println("集齐七龙珠召唤神龙。");}));

        for (int i = 1; i <= 7 ; i++) {
            final int number = i;
            new Thread(()->
            {
                System.out.println("集齐第：" + number + " 龙珠！");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
