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

    public HttpResponse<String> post() throws IOException, InterruptedException {
        // json formatted data
        EventsRequest eventsRequest = new EventsRequest(
                "fe986131749f41eaa68cd7dfb544128b",
                "trigger",
                "",
                Payload.builder()
                    .summary("test alert")
                    .severity("info")
                    .source("test source")
                    .build()
        );

        String json = new ObjectMapper().writeValueAsString(eventsRequest);

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://events.pagerduty.com/v2/enqueue"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

        return response;
    }
}
