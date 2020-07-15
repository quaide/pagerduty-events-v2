package pagerdutyevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

public class Image {

  @JsonProperty private String src;
  @JsonProperty private Optional<String> href;
  @JsonProperty private Optional<String> alt;

  public String getSrc() {
    return src;
  }

  public Optional<String> getHref() {
    return href;
  }

  public Optional<String> getAlt() {
    return alt;
  }

  private Image(Builder builder) {
    if (builder.src == null) {
      throw new IllegalStateException("src is required for images");
    }
    this.src = builder.src;
    this.href = builder.href;
    this.alt = builder.alt;
  }

  public static Builder builder() {
    return new Builder();
  }

  static class Builder {
    private String src;
    private Optional<String> href;
    private Optional<String> alt;

    private Builder() {}

    public Builder src(String src) {
      this.src = src;
      return this;
    }

    public Builder href(String href) {
      this.href = Optional.ofNullable(href);
      return this;
    }

    public Builder alt(String alt) {
      this.alt = Optional.ofNullable(alt);
      return this;
    }

    public Image build() {
      return new Image(this);
    }
  }
}
