package java20migration;

public class VirtuallThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int threadNumber = i;
            threads[i] = Thread.startVirtualThread(() -> {
                //noinspection InfiniteLoopStatement
                while(true) {
                    System.out.println(String.format("Thread[%s]", threadNumber));
                    try {
                        //noinspection BusyWait
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();

        }
    }
}
