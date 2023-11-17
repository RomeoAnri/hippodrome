import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void shouldFailAfterTwentyTwoSeconds() throws Exception {
            Main.main(new String[]{});
    }

}
