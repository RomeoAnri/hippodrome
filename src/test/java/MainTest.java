import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void shouldFailAfterTwentyTwoSeconds() throws Exception {
            Main.main(new String[]{});
    }

}
