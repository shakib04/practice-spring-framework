package learning.practice;

import learning.practice.service.impl.RabbitSenderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitSenderServiceTest {

  @Autowired
  private RabbitSenderServiceImpl rabbitSenderService;

  @Test
  public void testFallbackTriggered() {
    // Simulate a failure scenario
    rabbitSenderService.sendMessage("testDestination", "testPayload");
    // Verify fallback logic (e.g., check logs, assertions, etc.)
  }
}

