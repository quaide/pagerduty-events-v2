package pagerdutyevents;

import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PayloadTest {
  @Test
  public void severityRequired() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          Payload.builder().summary("summary").source("source").build();
        });
  }

  @Test
  public void summaryRequired() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          Payload.builder().severity(Payload.Severity.warning).source("source").build();
        });
  }

  @Test
  public void sourceRequired() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          Payload.builder().summary("summary").severity(Payload.Severity.error).build();
        });
  }

  @Test
  public void optionalPropertiesAreEmptyWhenNotGiven() {
    Payload payload =
        Payload.builder()
            .summary("summary")
            .source("source")
            .severity(Payload.Severity.critical)
            .build();

    Assertions.assertEquals("summary", payload.getSummary());
    Assertions.assertEquals("source", payload.getSource());
    Assertions.assertEquals(Payload.Severity.critical, payload.getSeverity());
    Assertions.assertTrue(payload.getTimestamp().isEmpty());
    Assertions.assertTrue(payload.getComponent().isEmpty());
    Assertions.assertTrue(payload.getGroup().isEmpty());
    Assertions.assertTrue(payload.getPayloadClass().isEmpty());
    Assertions.assertTrue(payload.getCustomDetails().isEmpty());
  }

  @Test
  public void payloadContainsExpectedProperties() {
    Payload payload =
        Payload.builder()
            .summary("summary")
            .source("source")
            .severity(Payload.Severity.info)
            .timestamp(Instant.EPOCH)
            .component("component")
            .group("group")
            .payloadClass("payloadClass")
            .customDetails("custom details")
            .build();

    Assertions.assertEquals("summary", payload.getSummary());
    Assertions.assertEquals("source", payload.getSource());
    Assertions.assertEquals(Payload.Severity.info, payload.getSeverity());
    Assertions.assertEquals(Instant.EPOCH, payload.getTimestamp().orElseThrow());
    Assertions.assertEquals("component", payload.getComponent().orElseThrow());
    Assertions.assertEquals("group", payload.getGroup().orElseThrow());
    Assertions.assertEquals("payloadClass", payload.getPayloadClass().orElseThrow());
    Assertions.assertEquals("custom details", payload.getCustomDetails().orElseThrow());
  }
}
