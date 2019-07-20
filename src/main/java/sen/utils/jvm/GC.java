package sen.utils.jvm;

import java.util.concurrent.TimeUnit;

public class GC {

    public static void main(String[] args)
    {
        System.out.println("GC垃圾回收机制。");
        try { TimeUnit.SECONDS.sleep( Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace();}
    }
}

/*
    1、配置jvm参数：-XX:+PrintGCDetails
        jps -l：命令查询正在运行的线程，第一个参数是进程号
        jinfo -flag MetaspaceSize 进程号：查询元空间的大小，出厂值为 -XX:MetaspaceSize=21807104
    2、设置jvm元空间的数据
        -XX:MetaspaceSize= 大小
        -XX:MetaspaceSize=1024m
    3、升老年区需活过的次数
        -XX:MaxTenuringThreshold= 次数：出厂值为 15
        -XX:MaxTenuringThreshold=15

     4、如何解释-Xms和-Xmx
         -Xms：InitialHeapSize
         -Xmx：MaxHeapSize

     5、jvm调优参数,查看参数盘点家点
        java -XX:+PrintFlagsInitial

        -XX:+PrintFlagsFinal
        带 “=” 的参数未被修
        带 “:=”的参数被手动修改过的值
 */