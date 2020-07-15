package pagerdutyevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

public class Link {

  @JsonProperty private String href;
  @JsonProperty private Optional<String> text;

  public String getHref() {
    return href;
  }

  public Optional<String> getText() {
    return text;
  }

  private Link(Builder builder) {
    if (builder.href == null) {
      throw new IllegalStateException("href is required for links");
    }
    this.href = builder.href;
    this.text = builder.text;
  }

  public static Builder builder() {
    return new Builder();
  }

  static class Builder {
    private String href;
    private Optional<String> text;

    private Builder() {}

    public Builder href(String href) {
      this.href = href;
      return this;
    }

    public Builder text(String text) {
      this.text = Optional.ofNullable(text);
      return this;
    }

    public Link build() {
      return new Link(this);
    }
  }
}
