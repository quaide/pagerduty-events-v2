import com.fasterxml.jackson.annotation.JsonProperty;

public class Foo {
    @JsonProperty("BAR")
    String bar;

    public Foo(String bar) {
        this.bar = bar;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}