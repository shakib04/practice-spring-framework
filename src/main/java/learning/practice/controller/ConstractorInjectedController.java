package learning.practice.controller;

import learning.practice.service.GreetingService;

public class ConstractorInjectedController {
    private GreetingService greetingService;

    public ConstractorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
