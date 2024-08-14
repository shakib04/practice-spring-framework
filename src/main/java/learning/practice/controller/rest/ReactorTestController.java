package learning.practice.controller.rest;

import learning.practice.web.config.AsyncConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Log4j2
public class ReactorTestController {

  @Autowired
  private AsyncConfig.AsyncService asyncService;

  @GetMapping("")
  public void test()
  {
    Flux.just("Hello World").subscribe(ReactorTestController::print);
  }

  private static void print(String x)
  {
    Runnable runnable = () -> log.info(x);
    runnable.run();
    Thread thread = new Thread(runnable);
    thread.start();
  }

  @GetMapping("/async")
  public void asyncTest()
  {
    asyncService.test();
  }


}
