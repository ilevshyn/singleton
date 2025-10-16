import edu.io.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_AppConfig_3 {

    @Test
    void test_getInstance_returns_instance() {
        Assertions.assertNotNull(AppConfig.getInstance());
    }

    @Test
    void test_getInstance_returns_the_same_instance() {
        Assertions.assertEquals(
                AppConfig.getInstance(),
                AppConfig.getInstance());
    }

    @Test
    void test_class_has_no_public_ctors() {
        Assertions.assertEquals(0, AppConfig.class.getConstructors().length);
    }

    @Test
    void test_set_and_get_works() {
        AppConfig c = AppConfig.getInstance();
        c.set("mode", "light");
        Assertions.assertEquals("light", c.get("mode"));
        c.set("mode", "dark");
        Assertions.assertEquals("dark", c.get("mode"));
    }
}
