package pagerdutyevents;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkTest {

  @Test
  public void builderPopulatesAllFields() {
    Link link = Link.builder().href("href").text("text").build();

    Assertions.assertEquals("href", link.getHref());
    Assertions.assertEquals(Optional.of("text"), link.getText());
  }

  @Test
  public void hrefRequired() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          Link.builder().text("text").build();
        });
  }
}
