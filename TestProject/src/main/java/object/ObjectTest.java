package object;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: TestProject
 * @description:
 * @author: zhangfl
 * @create: 2021-06-22 17:03
 **/
public class ObjectTest {
    static ObjectTest a = new ObjectTest();  // 1
    public static void main(String[] args) {
        System.out.println("new 后"+ ClassLayout.parseInstance(a).toPrintable());

        a.hashCode();   // 2
        System.out.println("计算hashcode后"+ClassLayout.parseInstance(a).toPrintable());

        synchronized (a) {   // 3
            System.out.println("偏向后"+ClassLayout.parseInstance(a).toPrintable());
        }

        new Thread("t1"){   // 4
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock();
            }
        }.start();

        new Thread("t2"){  //5
            @Override
            public void run() {
                lock();
            }
        }.start();
    }

    public static void lock(){
        synchronized (a){
            System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(a).toPrintable());
        }
    }
}
