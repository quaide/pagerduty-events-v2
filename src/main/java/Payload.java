import java.sql.Timestamp;

public class Payload {
    private String summary;
    private String source;
    private String severity;
    private Timestamp timestamp;
    private String component;
    private String group;
    private String payloadClass;

    private Object customDetails;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPayloadClass() {
        return payloadClass;
    }

    public void setPayloadClass(String payloadClass) {
        this.payloadClass = payloadClass;
    }

    public Object getCustomDetails() {
        return customDetails;
    }

    public void setCustomDetails(Object customDetails) {
        this.customDetails = customDetails;
    }

    public Payload(String summary, String source, String severity, Timestamp timestamp, String component, String group, String payloadClass, Object customDetails) {
        this.summary = summary;
        this.source = source;
        this.severity = severity;
        this.timestamp = timestamp;
        this.component = component;
        this.group = group;
        this.payloadClass = payloadClass;
        this.customDetails = customDetails;
    }
}
