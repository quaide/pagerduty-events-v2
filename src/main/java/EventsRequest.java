import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Event;

public class EventsRequest {
    @JsonProperty("payload")
    private Payload payload = new Payload();

    @JsonProperty("routing_key")
    private String routingKey;

    @JsonProperty("event_action")
    private String eventAction;

    @JsonProperty("dedup_key")
    private String dedupKey;

    @JsonProperty("images")
    Object[] imageArray;

    @JsonProperty("links")
    Object[] linkArray;

    public EventsRequest(String routingKey, String eventAction, String dedupKey, Payload payload) {
        this.routingKey = routingKey;
        this.eventAction = eventAction;
        this.dedupKey = dedupKey;
        this.payload = payload;
    }

}
