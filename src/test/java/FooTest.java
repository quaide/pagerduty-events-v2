import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class FooTest {

    @Test
    public void Foo() throws JsonProcessingException {
        Foo foo = new Foo("quaide");

        String json = new ObjectMapper().writeValueAsString(foo);

        System.out.println(json);
    }
}
