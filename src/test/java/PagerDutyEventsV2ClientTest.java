import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class PagerDutyEventsV2ClientTest {

    @Test
    public void post() throws IOException, InterruptedException {
        PagerDutyEventsV2Client pagerDutyEventsV2Client = new PagerDutyEventsV2Client();

        HttpResponse<String> httpResponse = pagerDutyEventsV2Client.post();

        Assertions.assertEquals(202, httpResponse.statusCode());
    }
}
