import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Links {

    @JsonProperty
    private String href;
    @JsonProperty
    private Optional<String> text;

    public String getHref() {
        return href;
    }

    public Optional<String> getText() {
        return text;
    }
}
