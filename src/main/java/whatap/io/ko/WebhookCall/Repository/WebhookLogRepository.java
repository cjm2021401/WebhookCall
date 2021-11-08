package whatap.io.ko.WebhookCall.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import whatap.io.ko.WebhookCall.Domain.WebhookLog;

@Repository
public interface WebhookLogRepository extends MongoRepository<WebhookLog, String> {

}
