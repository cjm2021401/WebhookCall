package whatap.io.ko.WebhookCall.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ackNowldgeInfo")
public class AckNoldgeInfo {
    @Id
    private String id;
    private String acknoldge;
    private String time;

    public String getAcknoldge() {
        return acknoldge;
    }

    public void setAcknoldge(String acknoldge) {
        this.acknoldge = acknoldge;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AckNoldgeInfo(String acknoldge, String time) {
        this.acknoldge = acknoldge;
        this.time = time;
    }
}
