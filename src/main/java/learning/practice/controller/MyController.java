package learning.practice.controller;

import learning.practice.service.GreetingService;
import org.springframework.stereotype.Controller;

@Controller("mycontroller")
public class MyController {

    private final GreetingService greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
