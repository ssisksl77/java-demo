package java8_impatiant.concurrent.sec01;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableDemo {
    public static void main(String[] args) {
        Runnable hellos = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Hello " + i);
            }
        };

        Runnable goodbyes = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Good Bye " + i );
            }
        };

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(hellos);
        executor.execute(goodbyes);
    }
}
