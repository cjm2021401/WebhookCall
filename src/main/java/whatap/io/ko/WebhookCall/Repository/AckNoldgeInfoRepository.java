package whatap.io.ko.WebhookCall.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import whatap.io.ko.WebhookCall.Domain.AckNoldgeInfo;
import whatap.io.ko.WebhookCall.Domain.WebhookLog;

public interface AckNoldgeInfoRepository extends MongoRepository<AckNoldgeInfo, String> {
}
