package java20migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FoodFighter {
    static Logger logger = LoggerFactory.getLogger(FoodFighter.class);
    private static final String steak = "🥩";
    private static final String fork = "フォーク";
    private static final String knife = "ナイフ";

    private static final boolean バーチャルスレッドを使用 = false;

    private static final boolean デッドロックさせる = true;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length / 2; i++) {
            threads[i] = startThread(new Fighter(fork, knife)::fight, i);
        }
        for (int i = threads.length / 2; i < threads.length; i++) {
            if (デッドロックさせる) {
                threads[i] = startThread(new Fighter(knife, fork)::fight, i);

            } else {
                threads[i] = startThread(new Fighter(fork, knife)::fight, i);

            }
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static Thread startThread(Runnable runnable, int index) {
        Thread thread;
        if (バーチャルスレッドを使用) {
            thread = Thread.startVirtualThread(runnable);
        } else {
            thread = new Thread(runnable);
            thread.start();
        }
        thread.setName("Fighter Thread[" + index + "]");
        logger.info("Fighter Thread[" + index + "]起動完了");

        return thread;
    }

    static class Fighter {
        Object first;
        Object second;

        public Fighter(Object first, Object second) {
            this.first = first;
            this.second = second;
        }

        public void fight() {
            while (true) {
                synchronized (first) {
                    synchronized (second) {
                        synchronized (steak) {
                            while (true) {
//                                Math.sqrt(System.currentTimeMillis());

                                logger.info("(´〜｀)ﾓｸﾞﾓｸﾞ" + getLength());
                            }
                        }
                    }
                }
            }

        }

    }

    private static int getLength() {

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 8080), 3000000);
            try (PrintWriter requestWriter = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                requestWriter.println("GET / HTTP/1.1");
                requestWriter.println("Host: localhost");
                requestWriter.println("Connection: close");
                requestWriter.println();

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = responseReader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                return response.length();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
