import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class LinkTest {

    @Test
    public void builderPopulatesAllFields() {
        Link link = Link.builder()
                .href("href")
                .text("text")
                .build();

        Assertions.assertEquals("href", link.getHref());
        Assertions.assertEquals(Optional.of("text"), link.getText());
    }
}
