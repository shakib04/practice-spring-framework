package learning.practice;

import learning.practice.controller.ConstractorInjectedController;
import learning.practice.controller.MyController;
import learning.practice.controller.PropertyInjectedController;
import learning.practice.controller.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        MyController myController = (MyController) context.getBean("mycontroller");
        System.out.println("------primary bean");
        String msg = myController.sayHello();
        System.out.println(msg);

        System.out.println("-----property");
        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) context.getBean("propertyInjectedController");
        System.out.println(propertyInjectedController.getGreeting());

        System.out.println("-----property");
        SetterInjectedController setterInjectedController = (SetterInjectedController) context.getBean("setterInjectedController");
        System.out.println(setterInjectedController.getGreeting());

        System.out.println("-----property");
        ConstractorInjectedController constractorInjectedController = (ConstractorInjectedController) context.getBean("constractorInjectedController");
        System.out.println(constractorInjectedController.getGreeting());;
    }
}