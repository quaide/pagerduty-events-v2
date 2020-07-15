package pagerdutyevents;

import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventsRequestTest {

  private static final String ROUTING_KEY = "fe986131749f41eaa68cd7dfb544128b";
  private static final String DEDUP_KEY = "60cf136dff0a4dde9044f8e0cc606d99";

  @Test
  public void routingKeyRequired() {
    Payload payload =
        Payload.builder().source("source").summary("summary").severity("severity").build();

    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          EventsRequest.builder()
              .eventAction(EventsRequest.EventAction.trigger)
              .payload(payload)
              .build();
        });
  }

  @Test
  public void eventActionRequired() {
    Payload payload =
        Payload.builder().source("source").summary("summary").severity("severity").build();

    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          EventsRequest.builder().routingKey("routing key").payload(payload).build();
        });
  }

  @Test
  public void payloadRequired() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          EventsRequest.builder()
              .eventAction(EventsRequest.EventAction.trigger)
              .routingKey("routing key")
              .build();
        });
  }

  @Test
  public void dedupKeyRequiredOnAcknowledge() {
    Payload payload =
        Payload.builder().source("source").summary("summary").severity("severity").build();

    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          EventsRequest.builder()
              .eventAction(EventsRequest.EventAction.acknowledge)
              .routingKey("routing key")
              .payload(payload)
              .build();
        });
  }

  @Test
  public void dedupKeyRequiredOnResolve() {
    Payload payload =
        Payload.builder().source("source").summary("summary").severity("severity").build();

    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          EventsRequest.builder()
              .eventAction(EventsRequest.EventAction.resolve)
              .routingKey("routing key")
              .payload(payload)
              .build();
        });
  }

  @Test
  public void builderPopulatesAllFields() {
    Object[] images = new Object[1];
    Image image = Image.builder().src("src").href("href").alt("alt").build();
    images[0] = image;

    Object[] links = new Object[1];
    Link link = Link.builder().href("href").text("text").build();
    links[0] = link;

    Payload payload =
        Payload.builder()
            .summary("summary")
            .source("source")
            .severity("severity")
            .timestamp(Instant.EPOCH)
            .component("component")
            .group("group")
            .payloadClass("payloadClass")
            .customDetails("custom details")
            .build();
    EventsRequest eventsRequest =
        EventsRequest.builder()
            .payload(payload)
            .routingKey(ROUTING_KEY)
            .eventAction(EventsRequest.EventAction.trigger)
            .dedupKey(DEDUP_KEY)
            .images(images)
            .links(links)
            .build();

    Assertions.assertEquals(payload, eventsRequest.getPayload());
    Assertions.assertEquals(ROUTING_KEY, eventsRequest.getRoutingKey());
    Assertions.assertEquals(EventsRequest.EventAction.trigger, eventsRequest.getEventAction());
    Assertions.assertEquals(Optional.of(DEDUP_KEY), eventsRequest.getDedupKey());
    Assertions.assertEquals(images, eventsRequest.getImages());
    Assertions.assertEquals(links, eventsRequest.getLinks());
  }
}
