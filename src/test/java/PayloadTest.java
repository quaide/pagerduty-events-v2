import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.Instant;

public class PayloadTest {
    @Test
    public void severityRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
                    Payload.builder()
                            .summary("summary")
                            .source("source")
                            .build();
                }
        );
    }

    @Test
    public void summaryRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
                    Payload.builder()
                            .severity("severity")
                            .source("source")
                            .build();
                }
        );
    }

    @Test
    public void sourceRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
                    Payload.builder()
                            .summary("summary")
                            .severity("source")
                            .build();
                }
        );
    }

    @Test
    public void payloadContainsExpectedProperties() {
        Payload payload = Payload.builder()
                .summary("summary")
                .source("source")
                .severity("severity")
                .timestamp(Instant.EPOCH)
                .component("component")
                .group("group")
                .payloadClass("payloadClass")
                .customDetails("custom details")
                .build();

        Assertions.assertEquals("summary", payload.getSummary());
        Assertions.assertEquals("source", payload.getSource());
        Assertions.assertEquals("severity", payload.getSeverity());
        Assertions.assertEquals(Instant.EPOCH, payload.getTimestamp());
        Assertions.assertEquals("component", payload.getComponent());
        Assertions.assertEquals("group", payload.getGroup());
        Assertions.assertEquals("payloadClass", payload.getPayloadClass());
        Assertions.assertEquals("custom details", payload.getCustomDetails());
    }
}
