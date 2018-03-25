import org.junit.Test;

import java.util.ArrayList;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-02-25 下午 2:30
 */
public class TestThread {
    @Test
    public void testThread() throws InterruptedException {
        for (int i =0;i<5;i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("内部类");
                }
            }
        });
        a.start();
        for (int i =0;i<5;i++) {
            System.out.println(i);
            a.sleep(1000);
        }
    }
    @Test
    public void array (){
        ArrayList list = new ArrayList();
        list.add(1);
    }

}
