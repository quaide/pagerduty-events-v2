import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ImageTest {

    @Test
    public void builderPopulatesAllFields() {
        Image image = Image.builder()
                .src("src")
                .href("href")
                .alt("alt")
                .build();

        Assertions.assertEquals("src", image.getSrc());
        Assertions.assertEquals(Optional.of("href"), image.getHref());
        Assertions.assertEquals(Optional.of("alt"), image.getAlt());
    }
}
