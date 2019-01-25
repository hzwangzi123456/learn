package threadlearn;

/**
 * @author wangzi
 * @date 19/1/7 下午4:28.
 */
public class test implements Runnable {

    private static int sum = 0;

    private static test instance = new test();
    @Override
    public void run() {
        for(int i = 0;i < 1000;i ++) {
            synchronized (Integer.valueOf(i)) {
                sum ++ ;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(sum);
    }
}
