package learning.practice;

import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main
//    implements ApplicationRunner
{

  public static void main(String[] args)
  {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public Consumer<String> scbMiddlewareConsumer()
  {
    return o -> {
      System.out.println(o);
    };
  }

  @Autowired
  private StreamBridge streamBridge;

//    @Override
//    public void run(ApplicationArguments args) {
//        streamBridge.send("scbMiddlewareSupplier", LoanDisburseInputDto.getList(500));
//    }

}