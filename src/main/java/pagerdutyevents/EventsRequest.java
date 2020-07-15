package pagerdutyevents;

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
  private Object[] images;

  @JsonProperty("links")
  private Object[] links;

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

  public Object[] getImages() {
    return images;
  }

  public Object[] getLinks() {
    return links;
  }

  private EventsRequest(Builder builder) {
    if (builder.routingKey == null) {
      throw new IllegalStateException(
          "Routing Key is required on class pagerdutyevents.EventsRequest");
    }
    if (builder.eventAction == null) {
      throw new IllegalStateException(
          "Event Action is required on class pagerdutyevents.EventsRequest");
    }
    if (builder.eventAction.equals(EventAction.trigger) && builder.payload == null) {
      throw new IllegalStateException(
          "pagerdutyevents.Payload is required on class pagerdutyevents.EventsRequest");
    }
    if (builder.eventAction.equals(EventAction.acknowledge) && builder.dedupKey.isEmpty()) {
      throw new IllegalStateException("Dedup Key is required to acknowledge an event");
    }
    if (builder.eventAction.equals(EventAction.resolve) && builder.dedupKey.isEmpty()) {
      throw new IllegalStateException("Dedup Key is required to resolve an event");
    }

    this.routingKey = builder.routingKey;
    this.eventAction = builder.eventAction;
    this.dedupKey = builder.dedupKey;
    this.payload = builder.payload;
    this.images = builder.images;
    this.links = builder.links;
  }

  public static Builder builder() {
    return new Builder();
  }

  static class Builder {
    private Payload payload;
    private String routingKey;
    private EventAction eventAction;
    private Optional<String> dedupKey = Optional.empty();
    private Object[] images = new Object[0];
    private Object[] links = new Object[0];

    private Builder() {}

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

    public Builder images(Object[] images) {
      this.images = images;
      return this;
    }

    public Builder links(Object[] links) {
      this.links = links;
      return this;
    }

    public EventsRequest build() {
      return new EventsRequest(this);
    }
  }
}
