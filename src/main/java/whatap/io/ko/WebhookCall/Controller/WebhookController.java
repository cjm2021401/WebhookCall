package whatap.io.ko.WebhookCall.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatap.io.ko.WebhookCall.Domain.Information;

@RestController
@RequestMapping("webhook")
public class WebhookController {
    private Logger logger = LoggerFactory.getLogger(WebhookController.class);
    @PostMapping(value = "/message")
    public void ReceiveWebhook(@RequestBody Information information){
        logger.info("Received Webhook from "+information.getProjectName()+" : "+information.getMessage());
    }
}
