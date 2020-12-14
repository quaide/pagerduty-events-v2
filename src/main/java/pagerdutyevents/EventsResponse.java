package pagerdutyevents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventsResponse {
  @JsonProperty private String status;

  @JsonProperty private String message;

  @JsonProperty("dedup_key")
  private String dedupKey;

  @JsonProperty private String[] errors;

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getDedupKey() {
    return dedupKey;
  }

  public String[] getErrors() {
    return errors;
  }

  //  @Override
  //  public String toString() {
  //    return "EventsResponse{" +
  //            "status='" + status + '\'' +
  //            ", message='" + message + '\'' +
  //            ", dedupKey='" + dedupKey + '\'' +
  //            ", errors=" + Arrays.toString(errors) +
  //            '}';
  //  }
}
