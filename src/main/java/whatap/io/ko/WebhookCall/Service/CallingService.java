package whatap.io.ko.WebhookCall.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import whatap.io.ko.WebhookCall.Domain.AckNoldgeInfo;
import whatap.io.ko.WebhookCall.Domain.Information;
import whatap.io.ko.WebhookCall.Domain.WebhookLog;
import whatap.io.ko.WebhookCall.Repository.AckNoldgeInfoRepository;
import whatap.io.ko.WebhookCall.Repository.WebhookLogRepository;

/**
 * Count the number of webhooks received
 * Make a call to me
 */
@Service
public class CallingService {

    @Value("${Sid}")
    private String Sid;
    @Value("${Token}")
    private String Token;
    @Value("${limit_hour}")
    private String x;
    @Value("${number}")
    private String y;
    @Value("${url}")
    private String url;
    /**
     * Number to use when calling
     */
    private final String from = "+13368919908";
    private final String to = "+821044274435";

    private Logger logger = LoggerFactory.getLogger(CallingService.class);

    private List<WebhookLog> logList;
    private List<AckNoldgeInfo> ackNoldgeInfoList;
    private final WebhookLogRepository webhookLogRepository;
    private final AckNoldgeInfoRepository ackNoldgeInfoRepository;

    @Autowired
    public CallingService(WebhookLogRepository webhookLogRepository, AckNoldgeInfoRepository ackNoldgeInfoRepository){
        this.webhookLogRepository=webhookLogRepository;
        this.ackNoldgeInfoRepository=ackNoldgeInfoRepository;
    }

    public void addInfoArrayList(Information information){
        WebhookLog webhookLog=new WebhookLog(information.getOname(),information.getMessage(),information.getLevel(),information.getLocalDateTime().toString());
        webhookLogRepository.insert(webhookLog);
        checkList();
    }

    public void checkList(){
        logList=webhookLogRepository.findAll();
        ackNoldgeInfoList=ackNoldgeInfoRepository.findAll();
        if(logList.size()>Integer.parseInt(y)){
            webhookLogRepository.delete(logList.get(0));
            logList.remove(0);
        }

        if(ackNoldgeInfoList.get(0).getAcknoldge().equals("true")){
           long time=ChronoUnit.HOURS.between(LocalDateTime.parse(ackNoldgeInfoList.get(0).getTime()),LocalDateTime.parse(logList.get(logList.size()-1).getTime()));
           if(time<=Integer.parseInt(x)){
                return ;
           }
           else {
               ackNoldgeInfoList.get(0).setAcknoldge("false");
               ackNoldgeInfoRepository.save(ackNoldgeInfoList.get(0));
           }
        }

        if(logList.size()==Integer.parseInt(y)){

            LocalDateTime start=LocalDateTime.parse(logList.get(0).getTime());
            LocalDateTime end=LocalDateTime.parse(logList.get(logList.size()-1).getTime());
            long time= ChronoUnit.HOURS.between(start,end);
            if(time<=Integer.parseInt(x)){
                boolean success=true;
                try {
                    sendCall();
                } catch (URISyntaxException e) {
                    success=false;
                    e.printStackTrace();
                }
               finally {
                    if(success) logger.info("We make a call to client at "+LocalDateTime.now());
                    else logger.error("we fail to make a call to client");
                }
            }
        }
    }

    public void sendCall() throws URISyntaxException {
        Twilio.init(Sid, Token);
        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml"))
                .setStatusCallback(url)
                .setStatusCallbackMethod(HttpMethod.POST)
                .create();
    }

    public void setAckNowldge() {
        LocalDateTime now =LocalDateTime.now();
        ackNoldgeInfoRepository.deleteAll();
        AckNoldgeInfo ackNoldgeInfo=new AckNoldgeInfo("true", now.toString());
        ackNoldgeInfoRepository.insert(ackNoldgeInfo);
        logger.info("We won't call to cleint for "+x+"hour from "+ackNoldgeInfo.getTime());
    }

}
