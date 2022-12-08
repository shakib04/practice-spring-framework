package learning.practice.controller;

import learning.practice.service.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstractorInjectedControllerTest {

    ConstractorInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new ConstractorInjectedController(
                new GreetingServiceImpl()
        );
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}