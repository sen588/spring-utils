package sen.utils.queue;

import java.util.concurrent.TimeUnit;

class HoldLockTread implements Runnable
{
    private String lockA;
    private String lockB;

    public HoldLockTread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA)
        {
            try { TimeUnit.SECONDS.sleep( 2); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 握住lockA，尝试获取lockB。");
            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName() + " 握住lockB，尝试获取lockA。");
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args)
    {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockTread(lockA, lockB),"A").start();
        new Thread(new HoldLockTread(lockB, lockA),"B").start();

    }
}
/*
    死锁产生结果：
        D:\exploit\ideaExploit\appliance\spring-utils>jps -l
            //端口号
            13456 sen.utils.queue.DeadLockDemo
            14532 org.jetbrains.idea.maven.server.RemoteMavenServer
            9924
            16184 org.jetbrains.kotlin.daemon.KotlinCompileDaemon
            5708 sun.tools.jps.Jps
            8252 org.jetbrains.jps.cmdline.Launcher

        //通过jstack命令查看该端口号详细信息
        D:\exploit\ideaExploit\appliance\spring-utils>jstack 13456
            Java stack information for the threads listed above:
            ===================================================
            "B":
                    at sen.utils.queue.HoldLockTread.run(DeadLockDemo.java:23)

                    //B线程尝试获取的线程资源0x00000000d5fa4908
                    - waiting to lock <0x00000000d5fa4908> (a java.lang.String)

                    //B线程已锁的线程资源0x00000000d5fa4940
                    - locked <0x00000000d5fa4940> (a java.lang.String)
                    at java.lang.Thread.run(Thread.java:748)
            "A":
                    at sen.utils.queue.HoldLockTread.run(DeadLockDemo.java:23)

                    //A线程尝试获取的线程资源0x00000000d5fa4940
                    - waiting to lock <0x00000000d5fa4940> (a java.lang.String)

                    //A线程已锁的线程资源0x0000000d5fa49080
                    - locked <0x00000000d5fa4908> (a java.lang.String)
                    at java.lang.Thread.run(Thread.java:748)

            //产生一个死锁
            Found 1 deadlock.
 */
