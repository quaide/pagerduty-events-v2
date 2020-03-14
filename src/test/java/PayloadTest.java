import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PayloadTest {
    @Test
    public void severityRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Payload payload = Payload.builder()
                .summary("summary")
                .source("source")
                .build();
            }
        );
    }

    @Test
    public void summaryRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Payload payload = Payload.builder()
                .severity("severity")
                .source("source")
                .build();
            }
        );
    }

    @Test
    public void sourceRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Payload payload = Payload.builder()
                .summary("summary")
                .severity("source")
                .build();
            }
        );
    }
}
