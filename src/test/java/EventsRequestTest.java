import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventsRequestTest {

    private static final String ROUTING_KEY = "fe986131749f41eaa68cd7dfb544128b";
    private static final String DEDUP_KEY = "60cf136dff0a4dde9044f8e0cc606d99";

    @Test
    public void routingKeyRequired() {
        Payload payload = Payload.builder()
            .source("source")
            .summary("summary")
            .severity("severity")
            .build();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            EventsRequest eventsRequest = EventsRequest.builder()
                .eventAction(EventsRequest.EventAction.trigger)
                .payload(payload)
                .build();
            }
        );
    }

    @Test
    public void eventActionRequired() {
        Payload payload = Payload.builder()
                .source("source")
                .summary("summary")
                .severity("severity")
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> {
                    EventsRequest eventsRequest = EventsRequest.builder()
                            .routingKey("routing key")
                            .payload(payload)
                            .build();
                }
        );
    }

    @Test
    public void payloadRequired() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
                    EventsRequest eventsRequest = EventsRequest.builder()
                            .eventAction(EventsRequest.EventAction.trigger)
                            .routingKey("routing key")
                            .build();
                }
        );
    }

    @Test
    public void dedupKeyRequired() {
        Payload payload = Payload.builder()
                .source("source")
                .summary("summary")
                .severity("severity")
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> {
                    EventsRequest eventsRequest = EventsRequest.builder()
                            .eventAction(EventsRequest.EventAction.acknowledge)
                            .routingKey("routing key")
                            .payload(payload)
                            .build();
                }
            );
    }

    @Test
    public void BuilderHandlesImageArray() {
        /*PagerDuty has specific requirements for an image object properties (src, href, alt) */
        Object[] imageArray = new Object[1];
        Images images = new Images("src", "href", "alt");
        imageArray[0] = images;

        Payload payload = Payload.builder()
                .summary("test alert")
                .severity("info")
                .source("test source")
                .build();
        EventsRequest eventsRequest = EventsRequest.builder()
                .routingKey(ROUTING_KEY)
                .eventAction(EventsRequest.EventAction.trigger)
                .dedupKey(DEDUP_KEY)
                .payload(payload)
                .imageArray(imageArray)
                .build();
    }

    @Test
    public void BuilderHandlesLinkArray() {
        /*PagerDuty Link object has properties (href, text)? */
        Object[] link = new Object[2];
        link[0] = "href";
        link[1] = "text";

        Payload payload = Payload.builder()
                .summary("test alert")
                .severity("info")
                .source("test source")
                .build();
        EventsRequest eventsRequest = EventsRequest.builder()
                .routingKey(ROUTING_KEY)
                .eventAction(EventsRequest.EventAction.trigger)
                .dedupKey(DEDUP_KEY)
                .payload(payload)
                .linkArray(link)
                .build();

    }
}
