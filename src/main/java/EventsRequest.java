import com.fasterxml.jackson.annotation.JsonProperty;

public class EventsRequest {
    public enum EventAction {
        trigger,
        acknoledge,
        resolve;
    }

    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("routing_key")
    private String routingKey;
    @JsonProperty("event_action")
    private EventAction eventAction;
    @JsonProperty("dedup_key")
    private String dedupKey;
    @JsonProperty("images")
    Object[] imageArray;
    @JsonProperty("links")
    Object[] linkArray;

    public Payload getPayload() {
        return payload;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public EventAction getEventAction() {
        return eventAction;
    }

    public String getDedupKey() {
        return dedupKey;
    }

    public Object[] getImageArray() {
        return imageArray;
    }

    public Object[] getLinkArray() {
        return linkArray;
    }

    private EventsRequest(
            String routingKey,
            EventAction eventAction,
            String dedupKey,
            Payload payload,
            Object[] imageArray,
            Object[] linkArray) {
        if(routingKey == null) {
            throw new IllegalStateException("Routing Key is required on class EventsRequest");
        }
        if(eventAction == null) {
            throw new IllegalStateException("Event Action is required on class EventsRequest");
        }
        if(payload == null) {
            throw new IllegalStateException("Payload is required on class EventsRequest");
        }
        this.routingKey = routingKey;
        this.eventAction = eventAction;
        this.dedupKey = dedupKey;
        this.payload = payload;
        this.imageArray = imageArray;
        this.linkArray = linkArray;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private Payload payload;
        private String routingKey;
        private EventAction eventAction;
        private String dedupKey;
        Object[] imageArray;
        Object[] linkArray;

        private Builder() {

        }

        public Builder payload(Payload payload) {
            this.payload = payload;
            return this;
        }

        public Builder routingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public Builder eventAction(EventAction eventAction) {
            this.eventAction = eventAction;
            return this;
        }

        public Builder dedupKey(String dedupKey) {
            this.dedupKey = dedupKey;
            return this;
        }

        public Builder imageArray(Object[] imageArray) {
            this.imageArray = imageArray;
            return this;
        }

        public Builder linkArray(Object[] linkArray) {
            this.linkArray = linkArray;
            return this;
        }

        public EventsRequest build() {
            return new EventsRequest(
                    this.routingKey,
                    this.eventAction,
                    this.dedupKey,
                    this.payload,
                    this.imageArray,
                    this.linkArray
            );
        }
    }
}
