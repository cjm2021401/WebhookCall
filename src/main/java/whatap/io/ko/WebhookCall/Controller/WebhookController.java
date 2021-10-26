package whatap.io.ko.WebhookCall.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatap.io.ko.WebhookCall.Domain.Information;

@RestController
@RequestMapping("webhook")
public class WebhookController {
    @PostMapping(value = "/message")
    public void ReceiveWebhook(@RequestBody Information information){
        System.out.println(information.getMessage());
    }
}
