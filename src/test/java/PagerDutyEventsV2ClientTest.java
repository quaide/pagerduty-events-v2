import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PagerDutyEventsV2ClientTest {

    @Test
    public void postTrigger() throws IOException, InterruptedException {
        Payload payload = Payload.builder()
                .summary("test alert")
                .severity("info")
                .source("test source")
                .build();
        EventsRequest eventsRequest = EventsRequest.builder()
                .routingKey("fe986131749f41eaa68cd7dfb544128b")
                .eventAction(EventsRequest.EventAction.trigger)
                .dedupKey("60cf136dff0a4dde9044f8e0cc606d99")
                .payload(payload)
                .build();
        PagerDutyEventsV2Client pagerDutyEventsV2Client = new PagerDutyEventsV2Client();

        EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

        Assertions.assertEquals("success", eventsResponse.getStatus());
        Assertions.assertEquals("60cf136dff0a4dde9044f8e0cc606d99", eventsResponse.getDedupKey());
        Assertions.assertEquals("Event processed", eventsResponse.getMessage());
        Assertions.assertNull(eventsResponse.getErrors());
    }

    @Test
    public void postAcknowledge() throws IOException, InterruptedException {
        EventsRequest eventsRequest = EventsRequest.builder()
            .routingKey("fe986131749f41eaa68cd7dfb544128b")
            .dedupKey("60cf136dff0a4dde9044f8e0cc606d99")
            .eventAction(EventsRequest.EventAction.acknowledge)
            .build();
        PagerDutyEventsV2Client pagerDutyEventsV2Client = new PagerDutyEventsV2Client();

        EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

        Assertions.assertEquals("success", eventsResponse.getStatus());
    }
}

