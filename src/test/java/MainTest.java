import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {


    @Disabled
    @Test
    @Timeout(value = 22)
    void mainTest() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }
}
