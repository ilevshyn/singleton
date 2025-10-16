import edu.io.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

public class Test_AppConfig_4 {

    final static String path = "./src/test/resources/";
    final static String CONFIG_YAML = path + "config.yaml";
    final static String TEST_YAML = path + "test.yaml";

    private AppConfig c;

    @BeforeEach
    void setup() {
        c = AppConfig.getInstance();
    }

    @AfterAll
    static void teardown() {
        new File(TEST_YAML).delete();
    }

    @Test
    void get_with_type_overload() {
        c.set("number", 1);
        Assertions.assertEquals(1, c.get("number", Integer.class));
    }

    @Test
    void get_throws_when_cannot_cast() {
        c.set("number", "one");
        Assertions.assertThrows(
                ClassCastException.class,
                () -> c.get("number", Integer.class));
    }

    @Test
    void load_works() {
        c.load(CONFIG_YAML);
        Assertions.assertEquals(1, c.get("test", Integer.class));
        Assertions.assertEquals("dark", c.get("mode", String.class));
    }

    @Test
    void load_throws_if_file_doesnt_exist() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> c.load("file_doesnt_exist.yaml"));
    }

    @Test
    void save_overwrites_file() {
        var f = new File(TEST_YAML);
        Assertions.assertFalse(f.exists() && !f.delete());
        c.set("test", 13);
        c.save(TEST_YAML);
        c.load(TEST_YAML);
        Assertions.assertEquals(13, c.get("test", Integer.class));
    }
}
