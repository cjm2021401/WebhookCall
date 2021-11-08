package whatap.io.ko.WebhookCall.Domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="webhookLog")
public class WebhookLog {
    @Id
    private String id;
    private String oname;
    private String message;
    private String level;
    private String time;

    public WebhookLog(String oname, String message, String level, String time) {
        this.oname = oname;
        this.message = message;
        this.level = level;
        this.time = time;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
