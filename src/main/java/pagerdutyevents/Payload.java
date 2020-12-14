package pagerdutyevents;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Payload {
  public enum Severity {
    INFO,
    WARNING,
    ERROR,
    CRITICAL
  }

  @JsonProperty private String summary;
  @JsonProperty private String source;
  @JsonProperty private Severity severity;
  @JsonProperty private Optional<Instant> timestamp;
  @JsonProperty private Optional<String> component;
  @JsonProperty private Optional<String> group;

  @JsonProperty("class")
  private Optional<String> payloadClass;

  @JsonProperty("custom_details")
  private Optional<Object> customDetails;

  public String getSummary() {
    return summary;
  }

  public String getSource() {
    return source;
  }

  public Severity getSeverity() {
    return severity;
  }

  public Optional<Instant> getTimestamp() {
    return timestamp;
  }

  public Optional<String> getComponent() {
    return component;
  }

  public Optional<String> getGroup() {
    return group;
  }

  public Optional<String> getPayloadClass() {
    return payloadClass;
  }

  public Optional<Object> getCustomDetails() {
    return customDetails;
  }

  private Payload(
      String summary,
      String source,
      Severity severity,
      Optional<Instant> timestamp,
      Optional<String> component,
      Optional<String> group,
      Optional<String> payloadClass,
      Optional<Object> customDetails) {
    if (summary == null) {
      throw new IllegalStateException("Summary is required on class pagerdutyevents.Payload");
    }
    if (source == null) {
      throw new IllegalStateException("Source is required on class pagerdutyevents.Payload");
    }
    if (severity == null) {
      throw new IllegalStateException("Severity is required on class pagerdutyevents.Payload");
    }
    this.summary = summary;
    this.source = source;
    this.severity = severity;
    this.timestamp = timestamp;
    this.component = component;
    this.group = group;
    this.payloadClass = payloadClass;
    this.customDetails = customDetails;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String summary;
    private String source;
    private Severity severity;
    private Optional<Instant> timestamp = Optional.empty();
    private Optional<String> component = Optional.empty();
    private Optional<String> group = Optional.empty();
    private Optional<String> payloadClass = Optional.empty();
    private Optional<Object> customDetails = Optional.empty();

    private Builder() {}

    public Builder summary(String summary) {
      this.summary = summary;
      return this;
    }

    public Builder source(String source) {
      this.source = source;
      return this;
    }

    public Builder severity(Severity severity) {
      this.severity = severity;
      return this;
    }

    public Builder timestamp(Instant timestamp) {
      this.timestamp = Optional.of(timestamp);
      return this;
    }

    public Builder component(String component) {
      this.component = Optional.of(component);
      return this;
    }

    public Builder group(String group) {
      this.group = Optional.of(group);
      return this;
    }

    public Builder payloadClass(String payloadClass) {
      this.payloadClass = Optional.of(payloadClass);
      return this;
    }

    public Builder customDetails(Object customDetails) {
      this.customDetails = Optional.of(customDetails);
      return this;
    }

    public Payload build() {
      return new Payload(
          this.summary,
          this.source,
          this.severity,
          this.timestamp,
          this.component,
          this.group,
          this.payloadClass,
          this.customDetails);
    }
  }
}
