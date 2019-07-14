package sen.utils.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
带返回值
 */
class MyTread implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args)
    {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyTread());

        Thread thread = new Thread(futureTask);
        thread.start();
    }
}
