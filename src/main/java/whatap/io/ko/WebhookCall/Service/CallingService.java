package whatap.io.ko.WebhookCall.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whatap.io.ko.WebhookCall.Controller.WebhookController;
import whatap.io.ko.WebhookCall.Domain.Information;

@Service
public class CallingService {
    private final String Sid = "";
    private final String Token= "";
    private final String from = "+13202876958";
    private final String to = "+821044274435";
    private Logger logger = LoggerFactory.getLogger(CallingService.class);
    private int x=0;
    private int y=3;
    //x시간동안 y개 수

    private ArrayList<Information> InfoArrayList = new ArrayList<>();
    //시간 , information
    public void addInfoArrayList(Information information){
        InfoArrayList.add(information);
        checkList();
    }

    public void checkList(){
        if(InfoArrayList.size()>y){
            InfoArrayList.remove(0);
        }

        if(InfoArrayList.size()==y){

            LocalDateTime start=InfoArrayList.get(0).getLocalDateTime();
            LocalDateTime end=InfoArrayList.get(InfoArrayList.size()-1).getLocalDateTime();
            long time= ChronoUnit.HOURS.between(start,end);
            if(time<=x){
                boolean success=true;
                try {
                    sendCall();
                } catch (URISyntaxException e) {
                    success=false;
                    e.printStackTrace();
                }
               finally {
                    if(success) logger.info("We make a call to client");
                    else logger.error("we fail to make a call to client");
                }
            }
        }
    }

    public void sendCall() throws URISyntaxException {
        Twilio.init(Sid, Token);


        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
    }
}
