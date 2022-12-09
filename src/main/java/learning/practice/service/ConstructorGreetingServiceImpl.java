package learning.practice.service;

import org.springframework.stereotype.Service;

@Service("constructorGreetingService")
public class ConstructorGreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Constructor";
    }
}
