package whatap.io.ko.WebhookCall;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import whatap.io.ko.WebhookCall.Domain.Information;
import whatap.io.ko.WebhookCall.Service.CallingService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class WebhookCallApplicationTests {

	@Autowired
	private CallingService callingService;

	@Test
	void 전화조건검사(CapturedOutput capturedOutput){
		Information information=new Information();
		for(int i=0; i<3; i++){
			information.setMessage("TEST");
			LocalDateTime now = LocalDateTime.now();
			information.setLocalDateTime(now);
			callingService.addInfoArrayList(information);
		}
		assertThat(capturedOutput).toString().contains("We make a call to client");

	}

}
