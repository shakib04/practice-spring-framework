package learning.practice;

import learning.practice.controller.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        MyController myController = (MyController) context.getBean("mycontroller");
        String msg = myController.sayHello();
        System.out.println(msg);
    }
}