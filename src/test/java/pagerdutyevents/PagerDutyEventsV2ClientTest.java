package pagerdutyevents;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagerDutyEventsV2ClientTest {

  private static final String ROUTING_KEY = "fe986131749f41eaa68cd7dfb544128b";
  private static final String DEDUP_KEY = "60cf136dff0a4dde9044f8e0cc606d99";
  private static final String SERIALIZED_EVENTS_RESPONSE =
      "{\"status\":\"success\",\"message\":\"Event processed\",\"dedup_key\":\"60cf136dff0a4dde9044f8e0cc606d99\",\"errors\":[\"'dedup_key' must be a string\"]}";
  private static final String EXPECTED_RESPONSE_STATUS = "success";
  private static final String EXPECTED_RESPONSE_MESSAGE = "Event processed";
  private static final String[] EXPECTED_RESPONSE_ERRORS =
      new String[] {"'dedup_key' must be a string"};

  private final HttpClient mockHttpClient = Mockito.mock(HttpClient.class);

  @Mock private HttpResponse<String> mockHttpResponse;
  private final PagerDutyEventsV2Client pagerDutyEventsV2Client =
      new PagerDutyEventsV2Client(mockHttpClient);

  @Test
  public void postException() throws IOException, InterruptedException {
    Mockito.when(
            mockHttpClient.send(
                Mockito.any(HttpRequest.class),
                ArgumentMatchers.eq(HttpResponse.BodyHandlers.ofString())))
        .thenThrow(InterruptedException.class);
    Payload payload =
        Payload.builder().summary("test alert").severity("info").source("test source").build();
    EventsRequest eventsRequest =
        EventsRequest.builder()
            .routingKey(ROUTING_KEY)
            .eventAction(EventsRequest.EventAction.trigger)
            .dedupKey(DEDUP_KEY)
            .payload(payload)
            .build();

    Assertions.assertThrows(
        PagerDutyStateException.class,
        () -> {
          pagerDutyEventsV2Client.post(eventsRequest);
        });
  }

  @Test
  public void postTrigger() throws PagerDutyStateException {
    stubHttpClientAndResponse();
    Payload payload =
        Payload.builder().summary("test alert").severity("info").source("test source").build();
    EventsRequest eventsRequest =
        EventsRequest.builder()
            .routingKey(ROUTING_KEY)
            .eventAction(EventsRequest.EventAction.trigger)
            .dedupKey(DEDUP_KEY)
            .payload(payload)
            .build();

    EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

    assertEventsResponseEqualsExpected(eventsResponse);
  }

  public void stubHttpClientAndResponse() {
    try {
      Mockito.when(
              mockHttpClient.send(
                  Mockito.any(HttpRequest.class),
                  ArgumentMatchers.eq(HttpResponse.BodyHandlers.ofString())))
          .thenReturn(mockHttpResponse);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Failed to stub mockHttpClient.send()");
    }
    Mockito.when(mockHttpResponse.body()).thenReturn(SERIALIZED_EVENTS_RESPONSE);
  }

  private void assertEventsResponseEqualsExpected(EventsResponse eventsResponse) {
    Assertions.assertEquals(EXPECTED_RESPONSE_STATUS, eventsResponse.getStatus());
    Assertions.assertEquals(DEDUP_KEY, eventsResponse.getDedupKey());
    Assertions.assertEquals(EXPECTED_RESPONSE_MESSAGE, eventsResponse.getMessage());
    Assertions.assertArrayEquals(EXPECTED_RESPONSE_ERRORS, eventsResponse.getErrors());
  }

  @Test
  public void postAcknowledge() throws PagerDutyStateException {
    stubHttpClientAndResponse();
    EventsRequest eventsRequest = eventsRequestFrom(EventsRequest.EventAction.acknowledge);

    EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

    assertEventsResponseEqualsExpected(eventsResponse);
  }

  @Test
  public void postResolve() throws PagerDutyStateException {
    stubHttpClientAndResponse();
    EventsRequest eventsRequest = eventsRequestFrom(EventsRequest.EventAction.resolve);

    EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);

    assertEventsResponseEqualsExpected(eventsResponse);
  }

  private static EventsRequest eventsRequestFrom(EventsRequest.EventAction eventAction) {
    return EventsRequest.builder()
        .routingKey(ROUTING_KEY)
        .dedupKey(DEDUP_KEY)
        .eventAction(eventAction)
        .build();
  }
}
