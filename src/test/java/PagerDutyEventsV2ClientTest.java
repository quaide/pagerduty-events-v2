import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ExtendWith(MockitoExtension.class)
public class PagerDutyEventsV2ClientTest {

    HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
    @Mock
    HttpResponse<String> mockHttpResponse;
    PagerDutyEventsV2Client pagerDutyEventsV2Client = new PagerDutyEventsV2Client(mockHttpClient);

    @Test
    public void postTrigger () throws IOException, InterruptedException {
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
        Mockito.when(
                mockHttpClient.send(
                        Mockito.any(HttpRequest.class),
                        ArgumentMatchers.eq(HttpResponse.BodyHandlers.ofString())
                )
        ).thenReturn(mockHttpResponse);
        Mockito.when(mockHttpResponse.body())
                .thenReturn("{\"status\":\"success\",\"message\":\"Event processed\",\"dedup_key\":\"60cf136dff0a4dde9044f8e0cc606d99\"}");

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
        Mockito.when(
                mockHttpClient.send(
                        Mockito.any(HttpRequest.class),
                        ArgumentMatchers.eq(HttpResponse.BodyHandlers.ofString())
                )
        ).thenReturn(mockHttpResponse);
        Mockito.when(mockHttpResponse.body())
                .thenReturn("{\"status\":\"success\",\"message\":\"Event processed\",\"dedup_key\":\"60cf136dff0a4dde9044f8e0cc606d99\"}");

        EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

        Assertions.assertEquals("success", eventsResponse.getStatus());
        Assertions.assertEquals("60cf136dff0a4dde9044f8e0cc606d99", eventsResponse.getDedupKey());
        Assertions.assertEquals("Event processed", eventsResponse.getMessage());
        Assertions.assertNull(eventsResponse.getErrors());
    }

    @Test
    public void postResolve() throws IOException, InterruptedException {
        EventsRequest eventsRequest = EventsRequest.builder()
                .routingKey("fe986131749f41eaa68cd7dfb544128b")
                .dedupKey("60cf136dff0a4dde9044f8e0cc606d99")
                .eventAction(EventsRequest.EventAction.resolve)
                .build();
        Mockito.when(
                mockHttpClient.send(
                        Mockito.any(HttpRequest.class),
                        ArgumentMatchers.eq(HttpResponse.BodyHandlers.ofString())
                )
        ).thenReturn(mockHttpResponse);
        Mockito.when(mockHttpResponse.body())
                .thenReturn("{\"status\":\"success\",\"message\":\"Event processed\",\"dedup_key\":\"60cf136dff0a4dde9044f8e0cc606d99\"}");

        EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

        Assertions.assertEquals("success", eventsResponse.getStatus());
        Assertions.assertEquals("60cf136dff0a4dde9044f8e0cc606d99", eventsResponse.getDedupKey());
        Assertions.assertEquals("Event processed", eventsResponse.getMessage());
        Assertions.assertNull(eventsResponse.getErrors());
    }
}

