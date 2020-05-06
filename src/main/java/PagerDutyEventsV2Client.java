import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PagerDutyEventsV2Client {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final HttpClient httpClient;

    public PagerDutyEventsV2Client(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public EventsResponse post(EventsRequest eventsRequest) throws IOException, InterruptedException {
        String json = OBJECT_MAPPER.writeValueAsString(eventsRequest);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://events.pagerduty.com/v2/enqueue"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return OBJECT_MAPPER.readValue(response.body(), EventsResponse.class);
    }
}
