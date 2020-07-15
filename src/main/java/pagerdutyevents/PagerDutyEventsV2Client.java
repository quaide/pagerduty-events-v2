package pagerdutyevents;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PagerDutyStateException extends RuntimeException {
  public PagerDutyStateException(String message, Exception e) {
    super(message, e);
  }
}

/**
 * The public API for PagerDuty Events v2 API for incident event monitoring system. This client will
 * POST a JSON object as an API request. The constructor requires the consumer to build an
 * HttpClient. Then, the client requires an pagerdutyevents.EventsRequest object to be built prior
 * to calling eventPost which interacts with the Events API.
 */
public class PagerDutyEventsV2Client {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private final HttpClient httpClient;

  public PagerDutyEventsV2Client(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * Http Request to be sent to Pager Duty's API. Requires a JSON object that will be serialized
   * prior to sending the request. pagerdutyevents.EventsRequest object is to be built prior to
   * calling post().
   *
   * @param eventsRequest The JSON object to be sent to PagerDuty API
   * @return JSON mapped to to pagerdutyevents.EventsResponse class
   */
  public EventsResponse post(EventsRequest eventsRequest) {
    try {
      String json = OBJECT_MAPPER.writeValueAsString(eventsRequest);
      HttpRequest request =
          HttpRequest.newBuilder()
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .uri(URI.create("https://events.pagerduty.com/v2/enqueue"))
              .header("Content-Type", "application/json")
              .build();
      HttpResponse<String> response =
          httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      return OBJECT_MAPPER.readValue(response.body(), EventsResponse.class);
    } catch (IOException | InterruptedException e) {
      throw new PagerDutyStateException("Incorrect Http Request State", e);
    }
  }
}
