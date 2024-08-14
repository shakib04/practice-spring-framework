package learning.practice.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {

  @GetMapping("/error")
  public String error(){
    return "hello.html";
  }
}
