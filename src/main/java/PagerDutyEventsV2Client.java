import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PagerDutyEventsV2Client {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    public EventsResponse post(EventsRequest eventsRequest) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();

        EventsResponse eventsResponse;

        String json = objectMapper.writeValueAsString(eventsRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://events.pagerduty.com/v2/enqueue"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonResponse = response.body();

        eventsResponse = objectMapper.readValue(jsonResponse, EventsResponse.class);

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

        return eventsResponse;
    }
}
