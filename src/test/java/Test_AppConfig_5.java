import edu.io.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;

public class Test_AppConfig_5 {

    private static final int THREADS = 1000;
    private static AppConfig[] instances = new AppConfig[THREADS];

    @Test
    void test_getInstance_returns_the_same_instance_from_threads()
            throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREADS);
        for (int i = 0; i < THREADS; i++) {
            final int index = i;
            new Thread(() -> {
                instances[index] = AppConfig.getInstance();
                latch.countDown();
            }).start();
        }
        latch.await();

        Assertions.assertEquals(
                1,
                java.util.Arrays.stream(instances).distinct().count());
    }
}
