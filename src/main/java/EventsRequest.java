import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class EventsRequest {
    public enum EventAction {
        trigger,
        acknowledge,
        resolve;
    }

    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("routing_key")
    private String routingKey;
    @JsonProperty("event_action")
    private EventAction eventAction;
    @JsonProperty("dedup_key")
    private Optional<String> dedupKey;
    @JsonProperty("images")
    private Optional<Object[]> images;
    @JsonProperty("links")
    private Optional<Object[]> linkArray;

    public Payload getPayload() {
        return payload;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public EventAction getEventAction() {
        return eventAction;
    }

    public Optional<String> getDedupKey() {
        return dedupKey;
    }

    public Optional<Object[]> getImages() {
        return images;
    }

    public Optional<Object[]> getLinkArray() {
        return linkArray;
    }

    private EventsRequest(Builder builder) {
        if(builder.routingKey == null) {
            throw new IllegalStateException("Routing Key is required on class EventsRequest");
        }
        if(builder.eventAction == null) {
            throw new IllegalStateException("Event Action is required on class EventsRequest");
        }

        if(builder.eventAction.equals(EventAction.trigger)) {
            if(builder.payload == null) {
                throw new IllegalStateException("Payload is required on class EventsRequest");
            }
        }
        if(builder.eventAction.equals(EventAction.acknowledge)) {
            if(builder.dedupKey.isEmpty()) {
                throw new IllegalStateException("Dedup Key is required to acknowledge an event");
            }
        }

        if(builder.eventAction.equals(EventAction.resolve)) {
            if(builder.dedupKey.isEmpty()) {
                throw new IllegalStateException("Dedup Key is required to resolve an event");
            }
        }

        this.routingKey = builder.routingKey;
        this.eventAction = builder.eventAction;
        this.dedupKey = builder.dedupKey;
        this.payload = builder.payload;
        this.images = builder.images;
        this.linkArray = builder.linkArray;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private Payload payload;
        private String routingKey;
        private EventAction eventAction;
        private Optional<String> dedupKey;
        Optional<Object[]> images;
        Optional<Object[]> linkArray;

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
            this.dedupKey = Optional.ofNullable(dedupKey);
            return this;
        }

        public Builder imageArray(Object[] imageArray) {
            this.images = Optional.of(imageArray);
            return this;
        }

        public Builder linkArray(Object[] linkArray) {
            this.linkArray = Optional.ofNullable(linkArray);
            return this;
        }

        public EventsRequest build() {
            return new EventsRequest(this);
        }
    }
}
