package whatap.io.ko.WebhookCall.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatap.io.ko.WebhookCall.Domain.Information;
import whatap.io.ko.WebhookCall.Service.CallingService;

import java.net.URISyntaxException;

@RestController
@RequestMapping("webhook")
public class WebhookController {
    private final CallingService callingService;
    private Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    public WebhookController(CallingService callingService){
        this.callingService=callingService;
    }


    @PostMapping(value = "/message")
    public void ReceiveWebhook(@RequestBody Information information){
        logger.info("Received Webhook from "+information.getProjectName()+" : "+information.getMessage());
        try {
            callingService.sendCall();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
