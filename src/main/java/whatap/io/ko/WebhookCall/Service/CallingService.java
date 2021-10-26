package whatap.io.ko.WebhookCall.Service;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class CallingService {
    private final String Sid = "AC3b6e7a8a10989a687fad295ce5608765";
    private final String Token= "75ffeb290a19be5e7ec30066a6457bb8";

    public void sendCall() throws URISyntaxException {
        Twilio.init(Sid, Token);

        String from = "+13202876958";
        String to = "+821044274435";

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
        System.out.println(call.getSid());
    }
}
