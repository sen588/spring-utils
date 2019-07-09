package sen.utils.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 国已灭亡！！！");
                downLatch.countDown();
            }, mySQL.forEach_mySQL(i).getUserName()).start();
        }
        downLatch.await();
        System.out.println("秦国一统天下。");
    }
}


enum mySQL {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    private Integer id;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    mySQL(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public static mySQL forEach_mySQL(int index) {
        mySQL[] sql = mySQL.values();
        for (mySQL values : sql) {
            if (index == values.id) {
                return values;
            }
        }
        return null;
    }
}