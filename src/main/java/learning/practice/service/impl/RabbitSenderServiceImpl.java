package learning.practice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import learning.practice.service.RabbitSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RabbitSenderServiceImpl {

  private static final Logger log = LoggerFactory.getLogger(RabbitSenderServiceImpl.class);
  @Autowired
  private StreamBridge streamBridge;

  RestTemplate restTemplate = new RestTemplate();

  int i = 0;

  //  @CircuitBreaker(name = "rabbitCircuitBreaker", fallbackMethod = "fallback")
//  @Retry(name = "rabbitRetry")
  @Retryable(value = {MessageHandlingException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
  public boolean sendMessage(String destination, Object payload)
  {
    log.info("rabbit sending >> {}", ++i);
    return streamBridge.send(destination, payload);
//    ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8084/users", List.class);
//    return false;
  }

  public boolean fallback(String destination, Object payload, Throwable t)
  {
    // Handle the fallback logic here
    System.out.println("sending message " + ++i);
    System.out.println("Fallback triggered due to: " + t.getMessage());
    return streamBridge.send(destination, payload);
  }
}
