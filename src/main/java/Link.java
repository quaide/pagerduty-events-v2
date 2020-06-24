import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Link {

    @JsonProperty
    private String href;
    @JsonProperty
    private Optional<String> text;

    public Link(String href, String text) {
        this.href = href;
        this.text = Optional.ofNullable(text);
    }

    public String getHref() {
        return href;
    }

    public Optional<String> getText() {
        return text;
    }

    //add builder
}
