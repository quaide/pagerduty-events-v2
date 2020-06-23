import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Images {

    @JsonProperty
    private String src;
    @JsonProperty
    private Optional<String> href;
    @JsonProperty
    private Optional<String> alt;

    public Images (String src, String href, String alt) {
        this.src = src;
        this.href = Optional.ofNullable(href);
        this.alt = Optional.ofNullable(alt);
    }

    public String getSrc() {
        return src;
    }

    public Optional<String> getHref() {
        return href;
    }

    public Optional<String> getAlt() {
        return alt;
    }
}
