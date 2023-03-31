package java20migration;

public class TraditionalThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int threadNumber = i;
            threads[i] = new Thread(() -> {
                while(true) {
                    System.out.println(String.format("Thread[%s]", threadNumber));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            threads[i].start();

        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();

        }
    }
}
