import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventsRequestTest {

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
}
