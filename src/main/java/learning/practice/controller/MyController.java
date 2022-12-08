package learning.practice.controller;

import org.springframework.stereotype.Controller;

@Controller("mycontroller")
public class MyController {

    public String sayHello(){
        System.out.println("Say Hello");
        return "hi folks";
    }
}
