package learning.practice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import learning.practice.dto.LoanDisburseInputDto;
import learning.practice.service.RabbitSenderService;
import learning.practice.service.impl.RabbitSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private RabbitSenderServiceImpl rabbitSenderService;

  @GetMapping("/api/send")
  public boolean run()
  {
    return rabbitSenderService.sendMessage("scbMiddlewareSupplier",
        LoanDisburseInputDto.getList(500)
    );
  }

}
