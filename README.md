# PagerDuty Events v2

A lightweight and flexible Java client designed to work with the [PagerDuty Events V2 API](https://developer.pagerduty.com/docs/events-api-v2/overview/). This allows for the manipulation of incidents within the PagerDuty service, specifically via the *trigger*, *acknowledge*, and *resolve* actions.

## Getting Started

### Installation
The client can be added with your favorite dependency management tool, the latest version can be found here: https://search.maven.org/artifact/io.github.quaide/pagerduty-events-v2-client. 

#### Maven
```
<dependency>
  <groupId>io.github.quaide</groupId>
  <artifactId>pagerduty-events-v2-client</artifactId>
  <version>1.0.1</version>
</dependency>
```

#### Gradle
```
dependencies {
    implementation 'io.github.quaide:pagerduty-events-v2-client:1.0.1'
}
```

### Usage

The `PagerDutyEventsV2Client` constructor optionally takes an [`HttpClient`](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html). `HttpClient httpClient = HttpClient.newHttpClient();` returns a new `HttpClient` with default settings. 

To build a request, you must build an instance of `EventsRequest`. The following fields are required:

`EventsRequest`:
- `routingKey`
- `eventAction`
- `dedupKey` (required IFF the value of `eventAction` is `acknowledge` or `resolve`) 
- `payload`
  - `summary`
  - `source`
  - `severity`

Refer to [PagerDuty documentation](https://developer.pagerduty.com/docs/events-api-v2/trigger-events/) for more information regarding their API.

After building your `EventRequest`, the request can be POSTed via `pagerDutyEventsV2Client.post()`.

The following is a complete example:

```
import pagerdutyevents.EventsRequest;
import pagerdutyevents.EventsResponse;
import pagerdutyevents.PagerDutyEventsV2Client;
import pagerdutyevents.Payload;

import java.net.http.HttpClient;

public class TestPagerDutyClient {
    private static final String ROUTING_KEY = "12345678901234567890123456789012";

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        PagerDutyEventsV2Client pagerDutyEventsV2Client = new PagerDutyEventsV2Client(httpClient);
        Payload payload =
            Payload.builder()
                .summary("testing PagerDuty Events API v2 client")
                .severity(Payload.Severity.info)
                .source("quaide-test")
                .build();
        EventsRequest eventsRequest =
            EventsRequest.builder()
                .routingKey(ROUTING_KEY)
                .eventAction(EventsRequest.EventAction.trigger)
                .payload(payload)
                .build();

        EventsResponse eventsResponse = pagerDutyEventsV2Client.post(eventsRequest);
    }
}
```

## Built With
- [Java](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/)
- [Gradle](https://gradle.org/)
- [JUnit](https://junit.org/junit5/)
- [JaCoCo](https://www.eclemma.org/jacoco/)
- [Mockito](https://site.mockito.org/)

## Authors
- [Quaide Tranter](http://www.quaidetranter.com)
- [Mitchell Irvin](https://github.com/mitchellirvin)

## License

Licensed under the [MIT License](https://opensource.org/licenses/MIT).
