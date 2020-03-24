import com.fasterxml.jackson.annotation.JsonProperty;

public class EventsResponse {
    @JsonProperty
    private String status;
    @JsonProperty
    private String message;
    @JsonProperty("dedup_key")
    private String dedupKey;
    @JsonProperty
    private String[] errors;
}
