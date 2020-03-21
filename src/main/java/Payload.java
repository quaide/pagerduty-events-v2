import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Payload {
    @JsonProperty
    private String summary;
    @JsonProperty
    private String source;
    @JsonProperty
    private String severity;
    @JsonProperty
    private Instant timestamp;
    @JsonProperty
    private String component;
    @JsonProperty
    private String group;
    @JsonProperty("class")
    private String payloadClass;
    @JsonProperty("custom_details")
    private Object customDetails;

    public String getSummary() {
        return summary;
    }

    public String getSource() {
        return source;
    }

    public String getSeverity() {
        return severity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getComponent() {
        return component;
    }

    public String getGroup() {
        return group;
    }

    public String getPayloadClass() {
        return payloadClass;
    }

    public Object getCustomDetails() {
        return customDetails;
    }

    private Payload(
            String summary,
            String source,
            String severity,
            Instant timestamp,
            String component,
            String group,
            String payloadClass,
            Object customDetails) {
        if(summary == null) {
            throw new IllegalStateException("Summary is required on class Payload");
        }
        if(source == null) {
            throw new IllegalStateException("Source is required on class Payload");
        }
        if(severity == null) {
            throw new IllegalStateException("Severity is required on class Payload");
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

    static class Builder {
        private String summary;
        private String source;
        private String severity;
        private Instant timestamp;
        private String component;
        private String group;
        private String payloadClass;
        private Object customDetails;

        private Builder() {

        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder severity(String severity) {
            this.severity = severity;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder component(String component) {
            this.component = component;
            return this;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }

        public Builder payloadClass(String payloadClass) {
            this.payloadClass = payloadClass;
            return this;
        }

        public Builder customDetails(Object customDetails) {
            this.customDetails = customDetails;
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
                    this.customDetails
            );
        }
    }
}
